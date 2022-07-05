package hwr.oop.client.drawable;

import org.fusesource.jansi.Ansi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmptySpaceDrawableTest {

	EmptySpaceDrawable drawable;

	@BeforeEach
	void setUp() {
		drawable = new EmptySpaceDrawable();
	}

	@Test
	void test_draw() {
		var ansi = Ansi.ansi();
		drawable.draw(ansi);
		Assertions.assertEquals("\n", ansi.toString());
	}
}