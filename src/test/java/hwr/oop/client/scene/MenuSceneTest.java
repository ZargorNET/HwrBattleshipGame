package hwr.oop.client.scene;

import hwr.oop.client.ClientMain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenuSceneTest {

	MenuScene menuScene;

	@BeforeEach
	void setUp() {
		new ClientMain();
		menuScene = new MenuScene();
		ClientMain.getSingleton().setScene(menuScene);
	}

	@Test
	void test_validOnTextInput() {
		menuScene.onTextInput("20,20");
		Assertions.assertTrue(hasBeenSuccessful());
	}

	@Test
	void test_invalidOnTextInput() {
		menuScene.onTextInput("");
		Assertions.assertFalse(hasBeenSuccessful());

		menuScene.onTextInput("20,-2");
		Assertions.assertFalse(hasBeenSuccessful());

		menuScene.onTextInput("10,a");
		Assertions.assertFalse(hasBeenSuccessful());

		menuScene.onTextInput("0,0");
		Assertions.assertFalse(hasBeenSuccessful());

		menuScene.onTextInput("51,49");
		Assertions.assertFalse(hasBeenSuccessful());
	}

	private boolean hasBeenSuccessful() {
		return ClientMain.getSingleton().getCurrentScene().getClass() == ModeSelectScene.class;
	}
}