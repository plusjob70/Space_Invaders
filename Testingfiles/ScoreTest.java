package engine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ScoreTest {
	
	Score score = new Score("Jennie", 100);

	@Test
	public void testGetName() {
		assertEquals(score.getName(), "Jennie");
	}
	
	@Test
	public void testGetScore() {
		assertEquals(score.getScore(), 100);
	}
	
	@Test
	public void testCompareto() {
		assertNotNull(score);
	}
	
	@Test
	public void testScore() {
		assertNotNull(score);
	}
}
