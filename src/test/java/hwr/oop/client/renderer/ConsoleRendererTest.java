package hwr.oop.client.renderer;

import hwr.oop.client.drawable.EmptySpaceDrawable;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConsoleRendererTest {

	ConsoleRenderer renderer;

	@BeforeEach
	void setUp() {
		renderer = new ConsoleRenderer();
	}

	@Test
	void test_draw() {
		renderer.draw(new EmptySpaceDrawable());
		Assertions.assertEquals(1, renderer.getDrawables().size());
	}

	@Test
	void test_draw2() {
		renderer.draw(Arrays.asList(new EmptySpaceDrawable(), new EmptySpaceDrawable()));
		Assertions.assertEquals(2, renderer.getDrawables().size());
	}

	@Test
	void test_remove() {
		var space = new EmptySpaceDrawable();
		renderer.draw(space);
		Assertions.assertEquals(1, renderer.getDrawables().size());
		renderer.remove(space);
		Assertions.assertEquals(0, renderer.getDrawables().size());
	}

	@Test
	void test_removeAll() {
		renderer.draw(Arrays.asList(new EmptySpaceDrawable(), new EmptySpaceDrawable()));
		renderer.removeAll();

		Assertions.assertEquals(0, renderer.getDrawables().size());
	}
}