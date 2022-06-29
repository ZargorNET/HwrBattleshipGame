package hwr.oop.client.scene;

import hwr.oop.client.ClientMain;
import hwr.oop.client.drawable.BoundedTextBoxDrawable;
import hwr.oop.client.drawable.EmptySpaceDrawable;
import hwr.oop.client.drawable.TextBoxDrawable;
import hwr.oop.client.renderer.IRenderer;
import hwr.oop.client.util.LocationParser;
import hwr.oop.client.util.Vector2i;

public class MenuScene implements IScene {

	private final IRenderer renderer = ClientMain.getSingleton().getCurrentRenderer();

	private final TextBoxDrawable infoMessage = new TextBoxDrawable();
	private final BoundedTextBoxDrawable errorMessage = new BoundedTextBoxDrawable(1);

	@Override
	public void onTextInput(String line) {
		var matcher = LocationParser.PATTERN.matcher(line);

		if (matcher.matches()) {
			var size = new Vector2i(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
			if (size.getX() <= 50 && size.getY() <= 50 && size.getX() > 8 && size.getY() > 8) {
				ClientMain.getSingleton().setScene(new SingePlayerScene(size));
			} else {
				errorMessage.put("Spielfeld ist zu groß/zu klein! Bitte bleibe zwischen 9x9 und 50x50");
			}
		} else {
			errorMessage.put("Invalides Format! Versuche es nochmal!");
		}

		renderer.render();
	}

	@Override
	public void onSceneSet() {
		infoMessage.put("Willkommen bei Schiffeversenken!",
				"",
				"Bitte wähle deine Spielfeldgröße aus!",
				"",
				"Das Format beträgt:",
				"Width,Height",
				"",
				"Wir empfehlen eine Größe von 10x10 aufgrund der Wikipedia Spielregeln!"
		);
		renderer.draw(infoMessage);
		renderer.draw(new EmptySpaceDrawable());
		renderer.draw(new TextBoxDrawable("-------------------------"));
		renderer.draw(errorMessage);
	}
}
