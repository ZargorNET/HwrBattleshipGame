package hwr.oop.client.scene;

import hwr.oop.client.ClientMain;
import hwr.oop.client.GameWorld;
import hwr.oop.client.Ship;
import hwr.oop.client.util.Orientation;
import hwr.oop.client.util.Vector2i;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MultiPlayerSceneTest {

	MultiPlayerScene scene;
	GameWorld player1;
	GameWorld player2;
	boolean player1Turn = true;

	@BeforeEach
	void setUp() {
		new ClientMain();
		player1 = initializeGameWorld();
		player2 = initializeGameWorld();

		scene = new MultiPlayerScene(player1, player2);
		ClientMain.getSingleton().setScene(scene);
	}

	@Test
	void test_invalidOnTextInput() {
		scene.onTextInput("dw,2");
		Assertions.assertTrue(player1.getHits().isEmpty());

		scene.onTextInput("2,d");
		Assertions.assertTrue(player1.getHits().isEmpty());

		scene.onTextInput("-2,0");
		Assertions.assertTrue(player1.getHits().isEmpty());

		scene.onTextInput("0,-1");
		Assertions.assertTrue(player1.getHits().isEmpty());

		scene.onTextInput("200,200");
		Assertions.assertTrue(player1.getHits().isEmpty());
	}

	@Test
	void test_onTextInput() {
		scene.onTextInput("2,0");
		Assertions.assertTrue(hasBeenHit(new Vector2i(2, 0)));

		scene.onTextInput("1,1");
		Assertions.assertTrue(hasBeenHit(new Vector2i(1, 1)));
	}

	@Test
	void test_endOnTextInput() {
		player2.hit(new Vector2i(0, 0));
		player2.hit(new Vector2i(1, 0));
		player2.hit(new Vector2i(2, 0));
		player2.hit(new Vector2i(3, 0));

		scene.onTextInput("4,0");
		Assertions.assertTrue(hasBeenHit(new Vector2i(4, 0)));
		Assertions.assertEquals(EndScene.class, ClientMain.getSingleton().getCurrentScene().getClass());
	}

	private GameWorld initializeGameWorld() {
		var world = new GameWorld(new Vector2i(10, 10));

		world.addShip(new Ship(new Vector2i(0, 0), 5, Orientation.EAST));

		return world;
	}

	private boolean hasBeenHit(Vector2i location) {
		player1Turn = !player1Turn;

		if (player1Turn)
			return player1.getHit(location).isPresent();

		return player2.getHit(location).isPresent();
	}
}