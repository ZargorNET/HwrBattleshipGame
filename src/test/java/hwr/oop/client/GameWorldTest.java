package hwr.oop.client;

import hwr.oop.client.util.Orientation;
import hwr.oop.client.util.Vector2i;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameWorldTest {

	GameWorld gameWorld;

	@BeforeEach
	void setUp() {
		gameWorld = new GameWorld(new Vector2i(10, 10));
		gameWorld.addShip(new Ship(new Vector2i(0, 0), 5, Orientation.EAST));
	}

	@Test
	void test_validHit() {
		var result = gameWorld.hit(new Vector2i(0, 0));
		Assertions.assertTrue(result.isPresent());
		Assertions.assertTrue(gameWorld.getHits().get(new Vector2i(0, 0)));
	}

	@Test
	void test_invalidHit() {
		var result = gameWorld.hit(new Vector2i(0, 1));
		Assertions.assertTrue(result.isEmpty());
		Assertions.assertFalse(gameWorld.getHits().get(new Vector2i(0, 1)));
	}

	@Test
	void test_invalidGetHit() {
		var result = gameWorld.getHit(new Vector2i(0, 0));
		Assertions.assertTrue(result.isEmpty());
	}

	@Test
	void test_validGetHit() {
		gameWorld.hit(new Vector2i(0, 0));
		gameWorld.hit(new Vector2i(0, 1));
		var result = gameWorld.getHit(new Vector2i(0, 0));
		Assertions.assertTrue(result.get());

		result = gameWorld.getHit(new Vector2i(0, 1));
		Assertions.assertFalse(result.get());
	}

	@Test
	void test_addAllShips() {
		gameWorld.addAllShips(Arrays.asList(
				new Ship(new Vector2i(5, 5), 2, Orientation.WEST),
				new Ship(new Vector2i(9, 5), 2, Orientation.WEST)
		));

		Assertions.assertEquals(3, gameWorld.getShips().size());
	}

	@Test
	void test_setAliveAt() {
		var result = gameWorld.setAliveAt(new Vector2i(0, 0), false);
		Assertions.assertTrue(result.isPresent());
		Assertions.assertFalse(gameWorld.getShipAt(new Vector2i(0, 0)).get().isAlive(new Vector2i(0, 0)).get());

		result = gameWorld.setAliveAt(new Vector2i(0, 1), false);
		Assertions.assertTrue(result.isEmpty());
	}

	@Test
	void test_getShipAt() {
		var result = gameWorld.getShipAt(new Vector2i(0, 0));
		Assertions.assertTrue(result.isPresent());

		result = gameWorld.getShipAt(new Vector2i(0, 1));
		Assertions.assertTrue(result.isEmpty());
	}

	@Test
	void test_invalidAreLocationsFree() {
		var result = gameWorld.areLocationsFree(Arrays.asList(new Vector2i(0, 0), new Vector2i(0, 1)));
		Assertions.assertFalse(result);
	}

	@Test
	void test_validAreLocationsFree() {
		var result = gameWorld.areLocationsFree(Arrays.asList(new Vector2i(0, 1), new Vector2i(0, 2)));
		Assertions.assertTrue(result);
	}
}