package hwr.oop.client.util;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LocationOrientationParserTest {

	@Test
	void test_validParse() {
		Assertions.assertEquals(Optional.of(new LocationOrientation(new Vector2i(5, 4), Orientation.EAST)), LocationOrientationParser.parse("5,4,e"));
		Assertions.assertEquals(Optional.of(new LocationOrientation(new Vector2i(2, 4), Orientation.WEST)), LocationOrientationParser.parse("2,4,w"));
		Assertions.assertEquals(Optional.of(new LocationOrientation(new Vector2i(1, 2), Orientation.SOUTH)), LocationOrientationParser.parse("1,2,s"));
		Assertions.assertEquals(Optional.of(new LocationOrientation(new Vector2i(1, 2), Orientation.NORTH)), LocationOrientationParser.parse("1,2,n"));
	}

	@Test
	void test_invalidParse() {
		Assertions.assertEquals(Optional.empty(), LocationOrientationParser.parse(""));
		Assertions.assertEquals(Optional.empty(), LocationOrientationParser.parse("2,4"));
		Assertions.assertEquals(Optional.empty(), LocationOrientationParser.parse("2,"));
		Assertions.assertEquals(Optional.empty(), LocationOrientationParser.parse("2,2,d"));
		Assertions.assertEquals(Optional.empty(), LocationOrientationParser.parse("-2,2,e"));
		Assertions.assertEquals(Optional.empty(), LocationOrientationParser.parse("2,2,e,3"));
	}
}