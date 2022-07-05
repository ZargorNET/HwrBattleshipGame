package hwr.oop.client.util;

import hwr.oop.client.ClientMain;
import hwr.oop.client.GameWorld;
import hwr.oop.client.Ship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipSpawnerTest {

	ShipSpawner shipSpawner;
	GameWorld gameWorld;

	@BeforeEach
	void setUp() {
		// So we can use the singleton
		new ClientMain();

		gameWorld = new GameWorld(new Vector2i(10, 10));
		gameWorld.addShip(new Ship(new Vector2i(0, 0), 5, Orientation.EAST));

		shipSpawner = new ShipSpawner(gameWorld);
	}


	@Test
	void test_generateShips() {
		shipSpawner.generateShips();
		Assertions.assertEquals(10, gameWorld.getShips().size());
	}

	@Test
	void test_invalidIsLocationValid() {
		Assertions.assertFalse(shipSpawner.isLocationValid(new Ship(new Vector2i(0, 1), 2, Orientation.SOUTH)));
		Assertions.assertFalse(shipSpawner.isLocationValid(new Ship(new Vector2i(10, 10), 2, Orientation.WEST)));
	}

	@Test
	void test_validIsLocationValid() {
		Assertions.assertTrue(shipSpawner.isLocationValid(new Ship(new Vector2i(0, 2), 4, Orientation.SOUTH)));
		Assertions.assertTrue(shipSpawner.isLocationValid(new Ship(new Vector2i(2, 2), 1, Orientation.SOUTH)));
	}

	@Test
	void test_getAdjacentLocations() {
		var expected = new Vector2i[]{
				new Vector2i(-1, -1),
				new Vector2i(0, -1),
				new Vector2i(1, -1),
				new Vector2i(-1, 0),
				new Vector2i(1, 0),
				new Vector2i(-1, 1),
				new Vector2i(0, 1),
				new Vector2i(1, 1),
		};

		Assertions.assertArrayEquals(expected, shipSpawner.getAdjacentLocations(new Vector2i(0, 0)));
	}
}