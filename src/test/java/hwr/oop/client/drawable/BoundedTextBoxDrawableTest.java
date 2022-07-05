package hwr.oop.client.drawable;

import java.util.Arrays;
import org.fusesource.jansi.Ansi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoundedTextBoxDrawableTest {

	BoundedTextBoxDrawable drawable;

	@BeforeEach
	void setUp() {
		drawable = new BoundedTextBoxDrawable(2, "Hello World");
	}

	@Test
	void test_put() {
		drawable.put("00");
		Assertions.assertArrayEquals(Arrays.asList("Hello World", "00").toArray(), drawable.history.toArray());
		drawable.put("11");
		Assertions.assertArrayEquals(Arrays.asList("00", "11").toArray(), drawable.history.toArray());
	}

	@Test
	void test_clear() {
		drawable.clear();
		Assertions.assertArrayEquals(new String[0], drawable.history.toArray());
	}

	@Test
	void test_draw() {
		drawable.put("aa");
		var ansi = Ansi.ansi();
		drawable.draw(ansi);
		Assertions.assertEquals("Hello World\naa\n", ansi.toString());
	}
}