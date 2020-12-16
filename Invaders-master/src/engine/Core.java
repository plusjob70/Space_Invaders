package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import screen.DifficultyScreen;
// import screen.GameScreen;
import screen.GameScreen_2;
import screen.HighScoreScreen;
import screen.ScoreScreen;
import screen.Screen;
import screen.TitleScreen;

/**
 * Implements core game logic.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public final class Core {

	/** Width of current screen. */
	private static final int WIDTH = 448;
	/** Height of current screen. */
	private static final int HEIGHT = 520;
	/** Max fps of current screen. */
	private static final int FPS = 60;

	/** Max lives. */
	private static final int MAX_LIVES = 3;
	/** Levels between extra life. */
	private static final int EXTRA_LIFE_FRECUENCY = 3;
	/** Total number of levels. */
	private static final int NUM_LEVELS = 7;
	
	// Easy GameSettings
	/** Easy Difficulty settings for level 1. */
	private static final GameSettings EASY_SETTINGS_LEVEL_1 =
			new GameSettings(2, 2, 30, 3500);
	/** Easy Difficulty settings for level 2. */
	private static final GameSettings EASY_SETTINGS_LEVEL_2 =
			new GameSettings(4, 4, 30, 3000);
	/** Easy Difficulty settings for level 3. */
	private static final GameSettings EASY_SETTINGS_LEVEL_3 =
			new GameSettings(4, 5, 40, 2500);
	/** Easy Difficulty settings for level 4. */
	private static final GameSettings EASY_SETTINGS_LEVEL_4 =
			new GameSettings(6, 6, 20, 2000);
	/** Easy Difficulty settings for level 5. */
	private static final GameSettings EASY_SETTINGS_LEVEL_5 =
			new GameSettings(6, 6, 40, 1500);
	/** Easy Difficulty settings for level 6. */
	private static final GameSettings EASY_SETTINGS_LEVEL_6 =
			new GameSettings(7, 7, 10, 1500);
	/** Easy Difficulty settings for level 7. */
	private static final GameSettings EASY_SETTINGS_LEVEL_7 =
			new GameSettings(8, 7, 2, 1000);

	// Nomal GameSettings
	/** Nomal Difficulty settings for level 1. */
	private static final GameSettings NOMAL_SETTINGS_LEVEL_1 =
			new GameSettings(5, 4, 60, 2000);
	/** Nomal Difficulty settings for level 2. */
	private static final GameSettings NOMAL_SETTINGS_LEVEL_2 =
			new GameSettings(5, 5, 50, 2500);
	/** Nomal Difficulty settings for level 3. */
	private static final GameSettings NOMAL_SETTINGS_LEVEL_3 =
			new GameSettings(6, 5, 40, 1500);
	/** Nomal Difficulty settings for level 4. */
	private static final GameSettings NOMAL_SETTINGS_LEVEL_4 =
			new GameSettings(6, 6, 30, 1500);
	/** Nomal Difficulty settings for level 5. */
	private static final GameSettings NOMAL_SETTINGS_LEVEL_5 =
			new GameSettings(7, 6, 20, 1000);
	/** Nomal Difficulty settings for level 6. */
	private static final GameSettings NOMAL_SETTINGS_LEVEL_6 =
			new GameSettings(7, 7, 10, 1000);
	/** Nomal Difficulty settings for level 7. */
	private static final GameSettings NOMAL_SETTINGS_LEVEL_7 =
			new GameSettings(8, 7, 2, 500);

	// Hard GameSettings
	/** Hard Difficulty settings for level 1. */
	private static final GameSettings HARD_SETTINGS_LEVEL_1 =
			new GameSettings(5, 5, 65, 2000);
	/** Hard Difficulty settings for level 2. */
	private static final GameSettings HARD_SETTINGS_LEVEL_2 =
			new GameSettings(5, 5, 55, 2000);
	/** Hard Difficulty settings for level 3. */
	private static final GameSettings HARD_SETTINGS_LEVEL_3 =
			new GameSettings(6, 5, 45, 1000);
	/** Hard Difficulty settings for level 4. */
	private static final GameSettings HARD_SETTINGS_LEVEL_4 =
			new GameSettings(6, 6, 35, 1000);
	/** Hard Difficulty settings for level 5. */
	private static final GameSettings HARD_SETTINGS_LEVEL_5 =
			new GameSettings(7, 6, 25, 500);
	/** Hard Difficulty settings for level 6. */
	private static final GameSettings HARD_SETTINGS_LEVEL_6 =
			new GameSettings(7, 7, 15, 500);
	/** Hard Difficulty settings for level 7. */
	private static final GameSettings HARD_SETTINGS_LEVEL_7 =
			new GameSettings(8, 7, 5, 50);		
	
	/** Frame to draw the screen on. */
	private static Frame frame;
	/** Screen currently shown. */
	private static Screen currentScreen;
	/** Current Difficulty settings list. */
	private static List<GameSettings> gameSettings;
	/** Easy Difficulty settings list. */
	private static List<GameSettings> easy_GameSettings;
	/** Nomal Difficulty settings list. */
	private static List<GameSettings> nomal_GameSettings;
	/** Hard Difficulty settings list. */
	private static List<GameSettings> hard_GameSettings;
	/** Application logger. */
	private static final Logger LOGGER = Logger.getLogger(Core.class
			.getSimpleName());
	/** Logger handler for printing to disk. */
	private static Handler fileHandler;
	/** Logger handler for printing to console. */
	private static ConsoleHandler consoleHandler;


	/**
	 * Test implementation.
	 * 
	 * @param args
	 *            Program args, ignored.
	 */
	public static void main(final String[] args) {
		try {
			LOGGER.setUseParentHandlers(false);

			fileHandler = new FileHandler("log");
			fileHandler.setFormatter(new MinimalFormatter());

			consoleHandler = new ConsoleHandler();
			consoleHandler.setFormatter(new MinimalFormatter());

			LOGGER.addHandler(fileHandler);
			LOGGER.addHandler(consoleHandler);
			LOGGER.setLevel(Level.ALL);

		} catch (Exception e) {
			// TODO handle exception
			e.printStackTrace();
		}

		frame = new Frame(WIDTH, HEIGHT);
		DrawManager.getInstance().setFrame(frame);
		int width = frame.getWidth();
		int height = frame.getHeight();

		// Init Easy GameSettings.
		easy_GameSettings = new ArrayList<GameSettings>();
		easy_GameSettings.add(EASY_SETTINGS_LEVEL_1);
		easy_GameSettings.add(EASY_SETTINGS_LEVEL_2);
		easy_GameSettings.add(EASY_SETTINGS_LEVEL_3);
		easy_GameSettings.add(EASY_SETTINGS_LEVEL_4);
		easy_GameSettings.add(EASY_SETTINGS_LEVEL_5);
		easy_GameSettings.add(EASY_SETTINGS_LEVEL_6);
		easy_GameSettings.add(EASY_SETTINGS_LEVEL_7);

		// Init Nomal GameSettings.
		nomal_GameSettings = new ArrayList<GameSettings>();
		nomal_GameSettings.add(NOMAL_SETTINGS_LEVEL_1);
		nomal_GameSettings.add(NOMAL_SETTINGS_LEVEL_2);
		nomal_GameSettings.add(NOMAL_SETTINGS_LEVEL_3);
		nomal_GameSettings.add(NOMAL_SETTINGS_LEVEL_4);
		nomal_GameSettings.add(NOMAL_SETTINGS_LEVEL_5);
		nomal_GameSettings.add(NOMAL_SETTINGS_LEVEL_6);
		nomal_GameSettings.add(NOMAL_SETTINGS_LEVEL_7);

		// Init Hard GameSettings.
		hard_GameSettings = new ArrayList<GameSettings>();
		hard_GameSettings.add(HARD_SETTINGS_LEVEL_1);
		hard_GameSettings.add(HARD_SETTINGS_LEVEL_2);
		hard_GameSettings.add(HARD_SETTINGS_LEVEL_3);
		hard_GameSettings.add(HARD_SETTINGS_LEVEL_4);
		hard_GameSettings.add(HARD_SETTINGS_LEVEL_5);
		hard_GameSettings.add(HARD_SETTINGS_LEVEL_6);
		hard_GameSettings.add(HARD_SETTINGS_LEVEL_7);

		// Init Current GameSettings. (Default Value : Nomal Difficult)
		gameSettings = nomal_GameSettings;
		int difficultyCode = 1;
		
		GameState2 gameState;

		int returnCode = 1;
		do {
			gameState = new GameState2(1, 0,0,MAX_LIVES, MAX_LIVES,0, 0, 0, 0, false,false);

			switch (returnCode) {
			case 1:
				// Main menu.
				currentScreen = new TitleScreen(width, height, FPS, difficultyCode);
				LOGGER.info("Starting " + WIDTH + "x" + HEIGHT
						+ " title screen at " + FPS + " fps.");
				returnCode = frame.setScreen(currentScreen);
				LOGGER.info("Closing title screen.");
				break;
			case 2:
				// Game & score.
				do {
					// One extra live every few levels.
					boolean bonusLife = gameState.getLevel() % EXTRA_LIFE_FRECUENCY == 0 
					&& ((gameState.getLivesRemaining1() < MAX_LIVES || gameState.getLivesRemaining1() <=0) || (gameState.getLivesRemaing2() < MAX_LIVES || gameState.getLivesRemaing2()<=0));
					
					currentScreen = new GameScreen_2(gameState,
							gameSettings.get(gameState.getLevel() - 1),
							bonusLife, width, height, FPS);
					LOGGER.info("Starting " + WIDTH + "x" + HEIGHT
							+ " game screen at " + FPS + " fps.");
					frame.setScreen(currentScreen);
					LOGGER.info("Closing game screen.");

					gameState = ((GameScreen_2) currentScreen).getGameState();

					gameState = new GameState2(gameState.getLevel() + 1,
							gameState.getScore1(),
							gameState.getScore2(),
							gameState.getLivesRemaining1(),
							gameState.getLivesRemaing2(),
							gameState.getBulletsShot1(),
							gameState.getBulletsShot2(),
							gameState.getShipsDestroyed1(),
							gameState.getShipsDestroyed2(),
							gameState.getShipEnd1(),
							gameState.getShipEnd2());

				} while ((gameState.getLivesRemaining1() > 0 || gameState.getLivesRemaing2()>0)
						&& gameState.getLevel() <= NUM_LEVELS);

				LOGGER.info("Starting " + WIDTH + "x" + HEIGHT
						+ " score screen at " + FPS + " fps, with a score of "
						+ gameState.getScore1() + ", "
						+ gameState.getLivesRemaining1() + " lives remaining, "
						+ gameState.getBulletsShot1() + " bullets shot and "
						+ gameState.getShipsDestroyed1() + " ships destroyed.");
				currentScreen = new ScoreScreen(width, height, FPS, gameState, difficultyCode);
				returnCode = frame.setScreen(currentScreen);
				LOGGER.info("Closing score screen.");
				break;
			case 3:
				// High scores.
				currentScreen = new HighScoreScreen(width, height, FPS, difficultyCode);
				LOGGER.info("Starting " + WIDTH + "x" + HEIGHT
						+ " high score screen at " + FPS + " fps.");
				returnCode = frame.setScreen(currentScreen);
				LOGGER.info("Closing high score screen.");
				break;
			case 4:
				// Game Difficulty Setting.
				DifficultyScreen difScreen = new DifficultyScreen(width, height, FPS, difficultyCode);
				currentScreen = difScreen;
				LOGGER.info("Starting " + WIDTH + "x" + HEIGHT
						+ " high score screen at " + FPS + " fps,"
						+ "with a difficultyCode is " + difficultyCode + ".");
				returnCode = frame.setScreen(currentScreen);
				difficultyCode = difScreen.getDifficultyCode();
				switch (difficultyCode) {
					case 0:
						gameSettings = easy_GameSettings;
						break;
					case 1:
						gameSettings = nomal_GameSettings;
						break;
					case 2:
						gameSettings = hard_GameSettings;
						break;
					default :
						LOGGER.warning("I don't know this diffiucltyCode");
				}
				LOGGER.info("Change difficultyCode to " + difficultyCode + "."
					+ "Closing diffiuclty screen.");
				break;
			default:
				break;
			}

		} while (returnCode != 0);

		fileHandler.flush();
		fileHandler.close();
		System.exit(0);
	}

	/**
	 * Constructor, not called.
	 */
	private Core() {

	}

	/**
	 * Controls access to the logger.
	 * 
	 * @return Application logger.
	 */
	public static Logger getLogger() {
		return LOGGER;
	}

	/**
	 * Controls access to the drawing manager.
	 * 
	 * @return Application draw manager.
	 */
	public static DrawManager getDrawManager() {
		return DrawManager.getInstance();
	}

	/**
	 * Controls access to the input manager.
	 * 
	 * @return Application input manager.
	 */
	public static InputManager getInputManager() {
		return InputManager.getInstance();
	}

	/**
	 * Controls access to the file manager.
	 * 
	 * @return Application file manager.
	 */
	public static FileManager getFileManager() {
		return FileManager.getInstance();
	}

	
	/**
	 * Controls access to the sound manager.
	 * 
	 * @return Application sound manager.
	 */
	public static SoundManager getSoundManager() {
		return SoundManager.getInstance();
	}


	/**
	 * Controls creation of new cooldowns.
	 * 
	 * @param milliseconds
	 *            Duration of the cooldown.
	 * @return A new cooldown.
	 */
	public static Cooldown getCooldown(final int milliseconds) {
		return new Cooldown(milliseconds);
	}

	/**
	 * Controls creation of new cooldowns with variance.
	 * 
	 * @param milliseconds
	 *            Duration of the cooldown.
	 * @param variance
	 *            Variation in the cooldown duration.
	 * @return A new cooldown with variance.
	 */
	public static Cooldown getVariableCooldown(final int milliseconds,
			final int variance) {
		return new Cooldown(milliseconds, variance);
	}
}