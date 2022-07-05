package hwr.oop.client.util;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LocationParserTest {

	@Test
	void test_validParse() {
		Assertions.assertEquals(Optional.of(new Vector2i(5, 2)), LocationParser.parse("5,2"));
	}

	@Test
	void test_invalidParse() {
		Assertions.assertEquals(Optional.empty(), LocationParser.parse("-2,-4"));
		Assertions.assertEquals(Optional.empty(), LocationParser.parse("2,4,dw"));
		Assertions.assertEquals(Optional.empty(), LocationParser.parse("2,"));
		Assertions.assertEquals(Optional.empty(), LocationParser.parse(""));
	}
}