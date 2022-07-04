package hwr.oop.client.scene;

import hwr.oop.client.ClientMain;
import hwr.oop.client.drawable.BoundedTextBoxDrawable;
import hwr.oop.client.drawable.EmptySpaceDrawable;
import hwr.oop.client.drawable.TextBoxDrawable;
import hwr.oop.client.renderer.IRenderer;
import hwr.oop.client.util.Vector2i;

public class ModeSelectScene implements IScene {

	private final Vector2i gameWorldSize;
	private final BoundedTextBoxDrawable eventLog = new BoundedTextBoxDrawable(10);

	private final IRenderer renderer = ClientMain.getSingleton().getCurrentRenderer();

	public ModeSelectScene(Vector2i size) {
		this.gameWorldSize = size;
	}

	@Override
	public void onTextInput(String line) {
		if (line.equalsIgnoreCase("s"))
			ClientMain.getSingleton().setScene(new SingePlayerScene(gameWorldSize));
		else if (line.equalsIgnoreCase("m"))
			ClientMain.getSingleton().setScene(new MultiplayerSetScene(gameWorldSize));
		else
			eventLog.put("Invalide Angabe!");

		renderer.render();
	}

	@Override
	public void onSceneSet() {
		renderer.draw(new TextBoxDrawable("Bitte wähle aus, ob du (S)ingleplayer oder (M)ultiplayer spielen willst!"));
		renderer.draw(new EmptySpaceDrawable());
		renderer.draw(this.eventLog);
	}
}
