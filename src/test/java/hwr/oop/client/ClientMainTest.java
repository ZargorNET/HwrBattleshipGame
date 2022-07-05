package hwr.oop.client;

import hwr.oop.client.renderer.ConsoleRenderer;
import hwr.oop.client.scene.MenuScene;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientMainTest {

	ClientMain clientMain;

	@BeforeEach
	void setUp() {
		clientMain = new ClientMain();
	}

	@Test
	void test_sceneSwitch() {
		clientMain.setScene(new MenuScene());
		var dummy = new DummyScene();
		clientMain.setScene(dummy);
		Assertions.assertSame(dummy, clientMain.getCurrentScene());
		Assertions.assertTrue(((ConsoleRenderer) clientMain.getCurrentRenderer()).getDrawables().isEmpty());
	}
}