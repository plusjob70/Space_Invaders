package engine;

public class GameState2 {
	/** Current game level. */
	private int level;
	/** Current Player1 score. */
	private int score1;
	private int score2;
	/** Lives currently remaining. */
	private int livesRemaining1;
	private int livesRemaining2;
	/** Bullets shot until now. */
	private int bulletsShot1;
	private int bulletsShot2;
	/** Ships destroyed until now. */
	private int ship1Destroyed;
	private int ship2Destroyed;

	private boolean ship1End;
	private boolean ship2End;

	/**
	 * Constructor.
	 * 
	 * @param level
	 *            Current game level.
	 * @param score
	 *            Current score.
	 * @param livesRemaining
	 *            Lives currently remaining.
	 * @param bulletsShot
	 *            Bullets shot until now.
	 * @param shipsDestroyed
	 *            Ships destroyed until now.
	 */
	public GameState2(final int level, final int score1, final int score2,
			final int livesRemaining1, final int livesRemaining2, final int bulletsShot1, final int bulletsShot2,
			final int ship1Destroyed, final int ship2Destroyed, final boolean ship1End, final boolean ship2End) {
		this.level = level;
		this.score1 = score1;
		this.score2 = score2;
		this.livesRemaining1 = livesRemaining1;
		this.livesRemaining2 = livesRemaining2;
		this.bulletsShot1 = bulletsShot1;
		this.bulletsShot2 = bulletsShot2;
		this.ship1Destroyed = ship1Destroyed;
		this.ship2Destroyed = ship2Destroyed;
		this.ship1End = ship1End;
		this.ship2End = ship2End;
	}

	/**
	 * @return the level
	 */
	public final int getLevel() {
		return level;
	}

	/**
	 * @return the score
	 */
	public final int getScore1() {
		return score1;
	}
	
	public final int getScore2() {
		return score2;
	}

	/**
	 * @return the livesRemaining
	 */
	public final int getLivesRemaining1() {
		return livesRemaining1;
	}
	public final int getLivesRemaing2() {
		return livesRemaining2;
	}

	/**
	 * @return the bulletsShot
	 */
	public final int getBulletsShot1() {
		return bulletsShot1;
	}
	public final int getBulletsShot2() {
		return bulletsShot2;
	}

	/**
	 * @return the shipsDestroyed
	 */
	public final int getShipsDestroyed1() {
		return ship1Destroyed;
	}
	public final int getShipsDestroyed2() {
		return ship2Destroyed;
	}

	public final boolean getShipEnd1(){
		return ship1End;
	}
	public final boolean getShipEnd2(){
		return ship2End;
	}
}
