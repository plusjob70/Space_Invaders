package engine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Gamestate2test {
	
	GameState2 gamestate2 = new GameState2(10, 10, 10, 10, 10, 10, 10, 10, 10, true, true);

	@Test
	void testGameState2() {
		assertNotNull(gamestate2);
	}

	@Test
	void testGetLevel() {
		assertEquals(gamestate2.getLevel(), 10);
	}

	@Test
	void testGetScore1() {
		assertEquals(gamestate2.getScore1(), 10);
	}

	@Test
	void testGetScore2() {
		assertEquals(gamestate2.getScore2(), 10);
	}

	@Test
	void testGetLivesRemaining1() {
		assertEquals(gamestate2.getLivesRemaining1(), 10);
	}

	@Test
	void testGetLivesRemaing2() {
		assertEquals(gamestate2.getLivesRemaing2(), 10);
	}

	@Test
	void testGetBulletsShot1() {
		assertEquals(gamestate2.getBulletsShot1(), 10);
	}

	@Test
	void testGetBulletsShot2() {
		assertEquals(gamestate2.getBulletsShot2(), 10);
	}

	@Test
	void testGetShipsDestroyed1() {
		assertEquals(gamestate2.getShipsDestroyed1(), 10);
	}

	@Test
	void testGetShipsDestroyed2() {
		assertEquals(gamestate2.getShipsDestroyed2(), 10);
	}

	@Test
	void testGetShipEnd1() {
		assertEquals(gamestate2.getShipEnd1(), true);
	}

	@Test
	void testGetShipEnd2() {
		assertEquals(gamestate2.getShipEnd2(), true);
	}

}
