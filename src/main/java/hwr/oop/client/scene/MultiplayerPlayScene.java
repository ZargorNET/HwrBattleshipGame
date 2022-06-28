package hwr.oop.client.scene;

import hwr.oop.client.ClientMain;
import hwr.oop.client.GameWorld;
import hwr.oop.client.drawable.BoundedTextBoxDrawable;
import hwr.oop.client.drawable.EmptySpaceDrawable;
import hwr.oop.client.drawable.GameWorldDrawable;
import hwr.oop.client.drawable.TextBoxDrawable;
import hwr.oop.client.renderer.IRenderer;
import hwr.oop.client.util.Tuple;

public class MultiplayerPlayScene implements IScene {

	private final GameWorld gameWorld;
	private final IRenderer renderer = ClientMain.getSingleton().getCurrentRenderer();

	private final BoundedTextBoxDrawable eventLog = new BoundedTextBoxDrawable(10);

	public MultiplayerPlayScene(Tuple<Integer, Integer> size) {
		this.gameWorld = new GameWorld(size);
	}

	@Override
	public void onTextInput(String line) {
		eventLog.put(line);
		renderer.render();
	}

	@Override
	public void onSceneSet() {
		renderer.draw(new GameWorldDrawable(gameWorld));
		renderer.draw(new EmptySpaceDrawable());
		renderer.draw(new TextBoxDrawable("-------------------------"));
		renderer.draw(eventLog);
		renderer.draw(new TextBoxDrawable("-------------------------"));
		renderer.draw(new TextBoxDrawable(
				"Versenke alle gegnerischen Schiffe!",
				"Schießformat: X,Y"
		));
	}

	@Override
	public void onSceneClose() {

	}
}
