package hwr.oop.client.drawable;

import java.util.Arrays;
import org.fusesource.jansi.Ansi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TextBoxDrawableTest {

	TextBoxDrawable drawable;

	@BeforeEach
	void setUp() {
		drawable = new TextBoxDrawable("Hello World");
	}

	@Test
	void test_put() {
		drawable.put("00");
		Assertions.assertArrayEquals(Arrays.asList("Hello World", "00").toArray(), drawable.history.toArray());
	}

	@Test
	void test_remove() {
		drawable.remove("Hello World");
		Assertions.assertArrayEquals(new String[0], drawable.history.toArray());
	}

	@Test
	void test_clear() {
		drawable.clear();
		Assertions.assertArrayEquals(new String[0], drawable.history.toArray());
	}

	@Test
	void test_draw() {
		var ansi = Ansi.ansi();
		drawable.draw(ansi);
		Assertions.assertEquals("Hello World\n", ansi.toString());
	}
}