package hwr.oop.client.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Vector2iTest {

	@Test
	void test_add() {
		Assertions.assertEquals(new Vector2i(7, 4), new Vector2i(2, 2).add(5, 2));
	}

	@Test
	void test_zero() {
		Assertions.assertEquals(new Vector2i(0, 0), Vector2i.zero());
	}
}