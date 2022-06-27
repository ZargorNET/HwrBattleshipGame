package hwr.oop.client.scene;

import hwr.oop.client.ClientMain;
import hwr.oop.client.drawable.BoundedTextBoxDrawable;
import hwr.oop.client.renderer.IRenderer;

public class MenuScene implements IScene {

	private final IRenderer renderer = ClientMain.getSingleton().getCurrentRenderer();

	private final BoundedTextBoxDrawable textBox = new BoundedTextBoxDrawable(5);

	@Override
	public void onTextInput(String line) {
		textBox.put(line);
		renderer.render();
	}

	@Override
	public void onSceneSet() {
		renderer.draw(textBox);
		renderer.draw(new BoundedTextBoxDrawable(10) {{
			put("1");
			put("2");
		}});
		textBox.put("Hello World!");
	}

	@Override
	public void onSceneClose() {

	}
}
