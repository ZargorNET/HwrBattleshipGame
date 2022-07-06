package hwr.oop.client.scene;

import hwr.oop.client.ClientMain;
import hwr.oop.client.util.Vector2i;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModeSelectSceneTest {

	ModeSelectScene selectScene;

	@BeforeEach
	void setUp() {
		selectScene = new ModeSelectScene(new Vector2i(10, 10));
		new ClientMain();
		ClientMain.getSingleton().setScene(selectScene);
	}

	@Test
	void test_validOnTextInput() {
		selectScene.onTextInput("m");
		Assertions.assertEquals(MultiplayerSetScene.class, ClientMain.getSingleton().getCurrentScene().getClass());
	}

	@Test
	void test_validOnTextInput2() {
		selectScene.onTextInput("s");
		Assertions.assertEquals(SingePlayerScene.class, ClientMain.getSingleton().getCurrentScene().getClass());
	}

	@Test
	void test_invalidOnTextInput() {
		selectScene.onTextInput("a");
		Assertions.assertEquals(ModeSelectScene.class, ClientMain.getSingleton().getCurrentScene().getClass());
	}


}