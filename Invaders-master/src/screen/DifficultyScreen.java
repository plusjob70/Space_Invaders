package screen;

import java.awt.event.KeyEvent;

import engine.Cooldown;
import engine.Core;

import engine.SoundManager;


public class DifficultyScreen extends Screen{
    
    /** Milliseconds between changes in user selection. */
	private static final int SELECTION_TIME = 200;
	
	/** Time between changes in user selection. */
    private Cooldown selectionCooldown;

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
	public DifficultyScreen(final int width, final int height, final int fps, int difficultyCode) {
		super(width, height, fps);

		// Defaults to play.
        this.returnCode = 1;
        this.difficultyCode = difficultyCode;
		this.selectionCooldown = Core.getCooldown(SELECTION_TIME);
		this.selectionCooldown.reset();
		
		this.soundManager = Core.getSoundManager();
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
		if (this.selectionCooldown.checkFinished()
				&& this.inputDelay.checkFinished()) {
			if (inputManager.isKeyDown(KeyEvent.VK_UP)
					|| inputManager.isKeyDown(KeyEvent.VK_W)) {
				previousMenuItem();
				this.selectionCooldown.reset();
			}
			if (inputManager.isKeyDown(KeyEvent.VK_DOWN)
					|| inputManager.isKeyDown(KeyEvent.VK_S)) {
				nextMenuItem();
				this.selectionCooldown.reset();
			}
			if (inputManager.isKeyDown(KeyEvent.VK_SPACE)) {
				this.soundManager.ChangeSFX("click");
				this.soundManager.SFXControler(1);
				this.isRunning = false;
			}
		}
    }
    
    /**
     * @return Return DifficultCode.
     */
    public int getDifficultyCode()
    {
        return this.difficultyCode; 
    }

	/**
	 * Shifts the focus to the next menu item.
	 */
	private void nextMenuItem() {
        if (this.difficultyCode == 2)
			this.difficultyCode = 0;
		else
			this.difficultyCode++;
	}

	/**
	 * Shifts the focus to the previous menu item.
	 */
	private void previousMenuItem() {
		if (this.difficultyCode == 0)
			this.difficultyCode = 2;
		else
			this.difficultyCode--;
	}

	/**
	 * Draws the elements associated with the screen.
	 */
	private void draw() {
		drawManager.initDrawing(this);

		drawManager.drawDifficultyTitle(this);
		drawManager.drawDifficultyMenu(this, this.difficultyCode);

		drawManager.completeDrawing(this);
	}
}
