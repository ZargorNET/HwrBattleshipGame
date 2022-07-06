package hwr.oop.client.scene;

import hwr.oop.client.ClientMain;
import hwr.oop.client.GameWorld;
import hwr.oop.client.drawable.GameWorldDrawable;
import hwr.oop.client.drawable.TextBoxDrawable;
import hwr.oop.client.util.Vector2i;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EndSceneTest {

	EndScene endScene;

	@BeforeEach
	void setUp() {
		new ClientMain(); // singleton
		endScene = new EndScene(new GameWorld(new Vector2i(10, 10)));
	}

	@Test
	void test_onTextInput() {
		endScene.onTextInput("R");
		Assertions.assertEquals(MenuScene.class, ClientMain.getSingleton().getCurrentScene().getClass());
		endScene.onTextInput("Q");
		Assertions.assertFalse(ClientMain.getSingleton().getCurrentRenderer().isStarted());
	}

	@Test
	void test_onSceneSet() {
		endScene.onSceneSet();
		var drawables = ClientMain.getSingleton().getCurrentRenderer().getDrawables();
		Assertions.assertTrue(drawables.stream().anyMatch(d -> d.getClass() == GameWorldDrawable.class));
		Assertions.assertTrue(drawables.stream().anyMatch(d -> d.getClass() == TextBoxDrawable.class));
	}
}