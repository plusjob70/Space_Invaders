package engine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameSettingsTest {
	
	GameSettings gameSettings = new GameSettings(100, 100, 100, 100);

	@Test
	public void testGameSettings() {
		assertNotNull(gameSettings);
	}

	@Test
	void testGetFormationWidth() {
		assertEquals(gameSettings.getFormationWidth(), 100);
	}

	@Test
	void testGetFormationHeight() {
		assertEquals(gameSettings.getFormationHeight(), 100);
	}

	@Test
	void testGetBaseSpeed() {
		assertEquals(gameSettings.getBaseSpeed(), 100);
	}

	@Test
	void testGetShootingFrecuency() {
		assertEquals(gameSettings.getShootingFrecuency(), 100);
	}

}
