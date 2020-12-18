package engine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameStatetest {
	
	GameState gamestate = new GameState(10, 100, 10, 10, 10);

	@Test
	void testGameState() {
		assertNotNull(gamestate);
	}

	@Test
	void testGetLevel() {
		assertEquals(gamestate.getLevel(), 10);
	}

	@Test
	void testGetScore() {
		assertEquals(gamestate.getScore(), 100);
	}

	@Test
	void testGetLivesRemaining() {
		assertEquals(gamestate.getLivesRemaining(), 10);
	}

	@Test
	void testGetBulletsShot() {
		assertEquals(gamestate.getLevel(), 10);
	}

	@Test
	void testGetShipsDestroyed() {
		assertEquals(gamestate.getShipsDestroyed(), 10);
	}

}
