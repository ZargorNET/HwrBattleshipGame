package hwr.oop.client.scene;

import hwr.oop.client.ClientMain;
import hwr.oop.client.Ship;
import hwr.oop.client.util.Orientation;
import hwr.oop.client.util.Vector2i;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SingePlayerSceneTest {

	SingePlayerScene scene;

	@BeforeEach
	void setUp() {
		new ClientMain();
		scene = new SingePlayerScene(new Vector2i(10, 10));
		ClientMain.getSingleton().setScene(scene);

		scene.getGameWorld().getShips().clear();
		scene.getGameWorld().addShip(new Ship(new Vector2i(0, 0), 1, Orientation.WEST));
		scene.getGameWorld().addShip(new Ship(new Vector2i(0, 2), 3, Orientation.EAST));
	}

	@Test
	void test_shipGeneration() {
		var newScene = new SingePlayerScene(new Vector2i(10, 10));
		newScene.onSceneSet();
		Assertions.assertEquals(10, newScene.getGameWorld().getShips().size());
	}

	@Test
	void test_invalidOnTextInput() {
		scene.onTextInput("xx,xxd,dw");
		Assertions.assertTrue(scene.getGameWorld().getHits().isEmpty());

		scene.onTextInput("20,-2");
		Assertions.assertTrue(scene.getGameWorld().getHits().isEmpty());

		scene.onTextInput("40000,40000");
		Assertions.assertTrue(scene.getGameWorld().getHits().isEmpty());
	}

	@Test
	void test_validOnTextInput() {
		scene.onTextInput("0,0");
		Assertions.assertTrue(scene.getGameWorld().getHit(new Vector2i(0, 0)).isPresent());

		scene.onTextInput("1,2");
		Assertions.assertTrue(scene.getGameWorld().getHit(new Vector2i(1, 2)).isPresent());

		scene.onTextInput("1,1");
		Assertions.assertTrue(scene.getGameWorld().getHit(new Vector2i(1, 1)).isPresent());
	}

	@Test
	void test_endOnTextInput() {
		scene.onTextInput("0,0");
		scene.onTextInput("0,2");
		scene.onTextInput("1,2");
		scene.onTextInput("2,2");

		Assertions.assertEquals(EndScene.class, ClientMain.getSingleton().getCurrentScene().getClass());
	}

	@Test
	void test_cheatOnTextInput() {
		scene.onTextInput("cheat");
		Assertions.assertTrue(scene.getGameWorld().getShips().stream().allMatch(Ship::hasSunk));
	}
}