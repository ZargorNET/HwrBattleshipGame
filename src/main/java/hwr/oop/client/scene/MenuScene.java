package hwr.oop.client.scene;

import hwr.oop.client.ClientMain;
import hwr.oop.client.drawable.TextBoxDrawable;
import hwr.oop.client.renderer.IRenderer;
import hwr.oop.client.util.Vector2i;

public class MenuScene implements IScene {

	private final IRenderer renderer = ClientMain.getSingleton().getCurrentRenderer();

	private final TextBoxDrawable infoMessage = new TextBoxDrawable();

	@Override
	public void onTextInput(String line) {
		ClientMain.getSingleton().setScene(new SingePlayerScene(new Vector2i(10, 10)));
		renderer.render();
	}

	@Override
	public void onSceneSet() {
		infoMessage.put(
				"Willkommen bei Schiffeversenken!",
				"",
				"Starte indem du etwas tippst!"
		);
		renderer.draw(infoMessage);
	}
}
