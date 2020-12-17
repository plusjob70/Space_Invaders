package screen;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;


import engine.Cooldown;
import engine.Core;
import engine.GameState;
import engine.GameState2;
import engine.Score;

/**
 * Implements the score screen.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public class ScoreScreen extends Screen {

	/** Milliseconds between changes in user selection. */
	private static final int SELECTION_TIME = 200;
	/** Maximum number of high scores. */
	private static final int MAX_HIGH_SCORE_NUM = 10;//FOR 2PLAYER 湲곗〈 7
	/** Code of first mayus character. */
	private static final int FIRST_CHAR = 65;
	/** Code of last mayus character. */
	private static final int LAST_CHAR = 90;

	/** Current score. */
	private int score;
	private int score2;
	/** Player lives left. */
	private int livesRemaining;
	private int livesRemaining2;
	/** Total bullets shot by the player. */
	private int bulletsShot;
	private int bulletsShot2;
	/** Total ships destroyed by the player. */
	private int shipsDestroyed;
	private int shipsDestroyed2;
	/** List of past high scores. */
	private List<Score> highScores;
	/** Checks if current score is a new high score. */
	private boolean isNewRecord;
	private boolean isNewRecord2;
	/** Player name for record input. */
	private char[] name;
	private char[] name2;
	/** Character of players name selected for change. */
	private int nameCharSelected;
	private int nameCharSelected2;
	/** Time between changes in user selection. */
	private Cooldown selectionCooldown;

	/**
	 * Constructor, establishes the properties of the screen.
	 * 
	 * @param width
	 *            Screen width.
	 * @param height
	 *            Screen height.
	 * @param fps
	 *            Frames per second, frame rate at which the game is run.
	 * @param gameState
	 *            Current game state.
	 */
	public ScoreScreen(final int width, final int height, final int fps,
			final GameState gameState, int difficultyCode) {
		super(width, height, fps);

		this.score = gameState.getScore();
		this.livesRemaining = gameState.getLivesRemaining();
		this.bulletsShot = gameState.getBulletsShot();
		this.shipsDestroyed = gameState.getShipsDestroyed();
		this.isNewRecord = false;
		this.name = "AAA".toCharArray();
		this.nameCharSelected = 0;
		this.selectionCooldown = Core.getCooldown(SELECTION_TIME);
		this.selectionCooldown.reset();
		this.difficultyCode = difficultyCode;

		try {
			this.highScores = Core.getFileManager().loadHighScores(this.difficultyCode);
			if (highScores.size() < MAX_HIGH_SCORE_NUM
					|| highScores.get(highScores.size() - 1).getScore()
					< this.score)
				this.isNewRecord = true;

		} catch (IOException e) {
			logger.warning("Couldn't load high scores!");
		}
	}

	//For Player2 ScoreScreen
	/**
	 * Constructor, establishes the properties of the screen. (Use : 2people Player)
	 * 
	 * @param width
	 *            Screen width.
	 * @param height
	 *            Screen height.
	 * @param fps
	 *            Frames per second, frame rate at which the game is run.
	 * @param gameState2
	 *            Current game state2.
	 */
	public ScoreScreen(final int width, final int height, final int fps,
	final GameState2 gameState, int difficultyCode) {
super(width, height, fps);

this.score = gameState.getScore1();
this.score2 = gameState.getScore2();
this.livesRemaining = gameState.getLivesRemaining1();
this.livesRemaining2 = gameState.getLivesRemaing2();
this.bulletsShot = gameState.getBulletsShot1();
this.bulletsShot2 = gameState.getBulletsShot2();
this.shipsDestroyed = gameState.getShipsDestroyed1();
this.shipsDestroyed2 = gameState.getShipsDestroyed2();
this.isNewRecord = false;
this.isNewRecord2 = false;
this.name = "AAA".toCharArray();
this.name2 = "AAA".toCharArray();
this.nameCharSelected = 0;
this.nameCharSelected2 = 0;
this.selectionCooldown = Core.getCooldown(SELECTION_TIME);
this.selectionCooldown.reset();
this.difficultyCode = difficultyCode;

try {
	this.highScores = Core.getFileManager().loadHighScores(this.difficultyCode);
	if (highScores.size()  < MAX_HIGH_SCORE_NUM
			|| highScores.get(highScores.size() - 2).getScore()
			< this.score)
		this.isNewRecord = true;

} catch (IOException e) {
	logger.warning("Couldn't load Player1 high scores!");
}

//FOR 2PLAYER FILE
//Player2 Socre媛� high Socre�씤吏� �솗�씤
try {
	this.highScores = Core.getFileManager().loadHighScores(this.difficultyCode);
	if (highScores.size() < MAX_HIGH_SCORE_NUM
			|| highScores.get(highScores.size() - 1).getScore()
			< this.score2)
		this.isNewRecord2 = true;

} catch (IOException e) {
	logger.warning("Couldn't load high scores!");
}

}

	/**
	 * Starts the action.
	 * 
	 * @return Next screen code.
	 */
	public final int run() {
		super.run();

		return this.returnCode;
	}

	/**
	 * Updates the elements on screen and checks for events.
	 */
	protected final void update() {
		super.update();

		draw();
		if (this.inputDelay.checkFinished()) {
			if (inputManager.isKeyDown(KeyEvent.VK_ESCAPE)) {
				// Return to main menu.
				this.returnCode = 1;
				this.isRunning = false;
				if (this.isNewRecord||this.isNewRecord2)
					saveScore();
			} else if (inputManager.isKeyDown(KeyEvent.VK_SPACE)) {
				// Play again.
				this.returnCode = 2;
				this.isRunning = false;
				if (this.isNewRecord||this.isNewRecord2)
					saveScore();
			}

			//FOR 2PLAYER FILE
			//�쁽�옱 �솕�궡�몴濡쒕쭔 議곗젙. wasd濡� 1�뵆�젅�씠�뼱, �솕�궡�몴濡� 2�뵆�젅�씠�뼱濡� 蹂�寃�
			//Player1 WASD
			if (this.isNewRecord && this.selectionCooldown.checkFinished()) {
				if (inputManager.isKeyDown(KeyEvent.VK_D)) {
					this.nameCharSelected = this.nameCharSelected == 2 ? 0
							: this.nameCharSelected + 1;
					this.selectionCooldown.reset();
				}
				if (inputManager.isKeyDown(KeyEvent.VK_A)) {
					this.nameCharSelected = this.nameCharSelected == 0 ? 2
							: this.nameCharSelected - 1;
					this.selectionCooldown.reset();
				}
				if (inputManager.isKeyDown(KeyEvent.VK_W)) {
					this.name[this.nameCharSelected] =
							(char) (this.name[this.nameCharSelected]
									== LAST_CHAR ? FIRST_CHAR
							: this.name[this.nameCharSelected] + 1);
					this.selectionCooldown.reset();
				}
				if (inputManager.isKeyDown(KeyEvent.VK_S)) {
					this.name[this.nameCharSelected] =
							(char) (this.name[this.nameCharSelected]
									== FIRST_CHAR ? LAST_CHAR
							: this.name[this.nameCharSelected] - 1);
					this.selectionCooldown.reset();
				}
				}

				//Player2 �솕�궡�몴
				if (this.isNewRecord2 && this.selectionCooldown.checkFinished()) {
					//Player2 �솕�궡�몴
					if (inputManager.isKeyDown(KeyEvent.VK_RIGHT)) {
						this.nameCharSelected2 = this.nameCharSelected2 == 2 ? 0
								: this.nameCharSelected2 + 1;
						this.selectionCooldown.reset();
					}
					if (inputManager.isKeyDown(KeyEvent.VK_LEFT)) {
						this.nameCharSelected2 = this.nameCharSelected2 == 0 ? 2
								: this.nameCharSelected2 - 1;
						this.selectionCooldown.reset();
					}
					if (inputManager.isKeyDown(KeyEvent.VK_UP)) {
						this.name2[this.nameCharSelected2] =
								(char) (this.name2[this.nameCharSelected2]
										== LAST_CHAR ? FIRST_CHAR
								: this.name2[this.nameCharSelected2] + 1);
						this.selectionCooldown.reset();
					}
					if (inputManager.isKeyDown(KeyEvent.VK_DOWN)) {
						this.name2[this.nameCharSelected2] =
								(char) (this.name2[this.nameCharSelected2]
										== FIRST_CHAR ? LAST_CHAR
								: this.name2[this.nameCharSelected2] - 1);
						this.selectionCooldown.reset();
					}
				}
		}
	}

	/**
	 * Saves the score as a high score.
	 */
	//FOR 2PLAYER FILE
	private void saveScore() {
		highScores.add(new Score(new String(this.name), score));
		highScores.add(new Score(new String(this.name2), score2));

		//�늻媛� �떊湲곕줉�씤吏�. �쁽�옱�뒗 Player1�옄由ъ뿉�꽌 1�벑, Player2�옄由ъ뿉�꽌 1�벑�씤 �궗�엺�쑝濡쒖젙由�
		if(isNewRecord){
			List<Score> copyScore = new ArrayList<Score>();
			for(int i = 0; i*2<highScores.size();i++){
					copyScore.add(highScores.get(i*2)); //copyScore�뿉 0,2,4,6,8踰덉㎏ �꽔湲�
			}
			Collections.sort(copyScore);
			for(int i=0; i*2<highScores.size(); i++){
				highScores.set(i*2, copyScore.get(i));
			}
		}
		if(isNewRecord2){
			List<Score> copyScore = new ArrayList<Score>();
			for(int i = 0; i*2<highScores.size();i++){
				copyScore.add(highScores.get(i*2+1)); //copyScore�뿉 1,3,5踰덉㎏ �꽔湲�
			}
			Collections.sort(copyScore);
			for(int i=0; i*2<highScores.size(); i++){
				highScores.set(i*2+1, copyScore.get(i));
		}
	}
		
		if (highScores.size() > MAX_HIGH_SCORE_NUM){
			 //湲곗〈 size /2. Player �몮�씠�땲
			highScores.remove(highScores.size() - 1);
			highScores.remove(highScores.size() - 2); 
		}//�뮘�뿉 2媛� �궘�젣 add sound

		try {
			Core.getFileManager().saveHighScores(highScores, this.difficultyCode);
		} catch (IOException e) {
			logger.warning("Couldn't load high scores!");
		}
	}

	/**
	 * Draws the elements associated with the screen.
	 */
	private synchronized void draw() {
		drawManager.initDrawing(this);

		drawManager.drawGameOver(this, this.inputDelay.checkFinished(),
				this.isNewRecord);
		drawManager.drawResults(this, this.score, this.score2, this.livesRemaining, this.livesRemaining2,
				this.shipsDestroyed, this.shipsDestroyed2, (float) this.shipsDestroyed / this.bulletsShot, 
				(float) this.shipsDestroyed2 / this.bulletsShot2, this.isNewRecord, this.isNewRecord2);

		if (this.isNewRecord || this.isNewRecord2)
			drawManager.drawNameInput(this, this.name, this.nameCharSelected, this.name2, this.nameCharSelected2, this.isNewRecord, this.isNewRecord2);

			
		drawManager.completeDrawing(this);
	}
}
