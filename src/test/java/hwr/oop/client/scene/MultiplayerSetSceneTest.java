package hwr.oop.client.scene;

import hwr.oop.client.ClientMain;
import hwr.oop.client.util.Vector2i;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MultiplayerSetSceneTest {

	MultiplayerSetScene scene;

	@BeforeEach
	void setUp() {
		new ClientMain();
		scene = new MultiplayerSetScene(new Vector2i(10, 10));
		ClientMain.getSingleton().setScene(scene);
	}

	@Test
	void test_invalidOnTextInput() {
		scene.onTextInput("20,30,o");
		scene.onTextInput("1,-30,e");
		scene.onTextInput("20,30000,e");
		scene.onTextInput("5,5");
		Assertions.assertTrue(scene.gameWorldPlayer1.getShips().isEmpty());
	}

	@Test
	void test_validOnTextInput() {
		scene.onTextInput("0,0,e");
		scene.onTextInput("0,2,e");
		scene.onTextInput("0,4,e");
		scene.onTextInput("0,6,e");
		scene.onTextInput("0,8,e");
		scene.onTextInput("9,0,s");
		scene.onTextInput("7,5,s");
		scene.onTextInput("9,9,w");
		scene.onTextInput("5,2,e");
		scene.onTextInput("5,7,w");

		Assertions.assertEquals(10, scene.gameWorldPlayer1.getShips().size());

		// Player 2
		scene.onTextInput("0,0,e");
		scene.onTextInput("0,2,e");
		scene.onTextInput("0,4,e");
		scene.onTextInput("0,6,e");
		scene.onTextInput("0,8,e");
		scene.onTextInput("9,0,s");
		scene.onTextInput("7,5,s");
		scene.onTextInput("9,9,w");
		scene.onTextInput("5,2,e");
		scene.onTextInput("5,7,w");

		Assertions.assertEquals(10, scene.gameWorldPlayer2.getShips().size());
		Assertions.assertEquals(MultiPlayerScene.class, ClientMain.getSingleton().getCurrentScene().getClass());
	}
}