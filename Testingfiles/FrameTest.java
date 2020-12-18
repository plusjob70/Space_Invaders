package engine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import screen.Screen;

class FrameTest {
	
	Frame frame = new Frame(100, 100);
	Screen screen = new Screen(100, 100, 100);

	@Test
	void testGetWidth() {
		assertEquals(screen.getWidth(), 100);
	}

	@Test
	void testGetHeight() {
		assertEquals(screen.getHeight(), 100);
	}
	

}
