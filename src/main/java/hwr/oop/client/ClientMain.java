package hwr.oop.client;

import hwr.oop.client.renderer.ConsoleRenderer;
import hwr.oop.client.renderer.IRenderer;
import hwr.oop.client.scene.IScene;
import hwr.oop.client.scene.MenuScene;
import lombok.Getter;
import org.fusesource.jansi.AnsiConsole;

public final class ClientMain {

	@Getter
	private static ClientMain singleton;

	@Getter
	private IScene currentScene;

	@Getter
	private final IRenderer currentRenderer = new ConsoleRenderer();

	private ClientMain() {
		if (singleton == null)
			singleton = this;

		AnsiConsole.systemInstall();
		setScene(new MenuScene());
		this.currentRenderer.start();
	}

	public void setScene(IScene scene) {
		if (currentScene != null)
			currentScene.onSceneClose();
		this.currentRenderer.removeAll();
		currentScene = scene;
		currentScene.onSceneSet();
	}

	public static void main(String[] args) {
		new ClientMain();
	}
}
