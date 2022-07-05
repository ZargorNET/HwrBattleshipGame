package hwr.oop.client;

import hwr.oop.client.util.Orientation;
import hwr.oop.client.util.Vector2i;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ship ship;

	@BeforeEach
	void setUp() {
		ship = new Ship(new Vector2i(2, 2), 2, Orientation.EAST);
	}

	@Test
	void test_hasSunk() {
		ship.setAlive(new Vector2i(2, 2), false);
		ship.setAlive(new Vector2i(3, 2), false);
		Assertions.assertTrue(ship.hasSunk());
	}

	@Test
	void test_getSize() {
		Assertions.assertEquals(ship.getSize(), 2);
	}

	@Test
	void test_alive() {
		ship.setAlive(new Vector2i(2, 2), false);
		Assertions.assertFalse(ship.isAlive(new Vector2i(2, 2)).get());
	}

	@Test
	void test_ConstrucorOrientation() {
		Assertions.assertArrayEquals(new Vector2i[]{new Vector2i(3, 2), new Vector2i(2, 2)}, ship.getLocations().keySet().toArray());
	}
}