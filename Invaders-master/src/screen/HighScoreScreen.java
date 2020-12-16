package screen;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import engine.Core;
import engine.Score;
import engine.SoundManager;

/**
 * Implements the high scores screen, it shows player records.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public class HighScoreScreen extends Screen {

	/** List of past high scores. */
	private List<Score> highScores;
	/** This Control Sound */
	private SoundManager soundManager;

	/**
	 * Constructor, establishes the properties of the screen.
	 * 
	 * @param width
	 *            Screen width.
	 * @param height
	 *            Screen height.
	 * @param fps
	 *            Frames per second, frame rate at which the game is run.
	 */
	public HighScoreScreen(final int width, final int height, final int fps, int difficultyCode) {
		super(width, height, fps);

		this.returnCode = 1;
		this.difficultyCode = difficultyCode;

		this.soundManager = Core.getSoundManager();

		

		try {
			this.highScores = Core.getFileManager().loadHighScores(this.difficultyCode);
		} catch (NumberFormatException | IOException e) {
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
            if (inputManager.isKeyDown(KeyEvent.VK_SPACE)) {
            	this.soundManager.ChangeSFX("click");
				this.soundManager.SFXControler(1);

                this.isRunning = false;
            }
            //Reset
            if (inputManager.isKeyDown(KeyEvent.VK_R)) {
            	this.soundManager.ChangeSFX("click");
				this.soundManager.SFXControler(1);

                ResetHighScoreScreen();
                inputDelay.reset();
            }
        }
    }
    /**
     * Restart HighScoreScreen & Reset High Scores.
     */
    public void ResetHighScoreScreen()
    {
        try {
            Core.getFileManager().resetHighScores(this.difficultyCode);
            this.highScores = Core.getFileManager().loadHighScores(this.difficultyCode);
        } catch (NumberFormatException | IOException e) {
            logger.warning("Couldn't load high scores!");
        }
    }

	/**
	 * Draws the elements associated with the screen.
	 */
	private void draw() {
		drawManager.initDrawing(this);

		drawManager.drawHighScoreMenu(this);

		drawManager.drawHighScores(this, this.highScores);

		drawManager.completeDrawing(this);
	}
}
