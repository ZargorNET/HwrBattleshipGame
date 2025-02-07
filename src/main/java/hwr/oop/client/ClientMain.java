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

	@Getter
	private final int[] shipsToGenerate =  new int[]{5, 4, 4, 3, 3, 3, 2, 2, 2, 2};

	public ClientMain() {
		if (singleton == null)
			singleton = this;

		AnsiConsole.systemInstall();
		setScene(new MenuScene());
	}

	public void setScene(IScene scene) {
		this.currentRenderer.removeAll();
		currentScene = scene;
		currentScene.onSceneSet();
	}

	public static void main(String[] args) {
		var main = new ClientMain();
		main.getCurrentRenderer().start();
	}
}
