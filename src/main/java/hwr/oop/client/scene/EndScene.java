package hwr.oop.client.scene;

import hwr.oop.client.ClientMain;
import hwr.oop.client.GameWorld;
import hwr.oop.client.drawable.EmptySpaceDrawable;
import hwr.oop.client.drawable.GameWorldDrawable;
import hwr.oop.client.drawable.TextBoxDrawable;
import hwr.oop.client.renderer.IRenderer;

public class EndScene implements IScene {

	private final IRenderer renderer = ClientMain.getSingleton().getCurrentRenderer();
	private final GameWorld gameWorld;

	public EndScene(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}

	@Override
	public void onTextInput(String line) {
		if (line.equalsIgnoreCase("R"))
			ClientMain.getSingleton().setScene(new MenuScene());
		if (line.equalsIgnoreCase("Q"))
			System.exit(0);

		renderer.render();
	}

	@Override
	public void onSceneSet() {
		renderer.draw(new GameWorldDrawable(gameWorld));
		renderer.draw(new EmptySpaceDrawable());
		renderer.draw(new TextBoxDrawable(
				"Du hast alle Schiffe versenkt! Glückwunsch!",
				"",
				"Starte neu mit: R",
				"Oder verlasse mit: Q"));
	}
}
