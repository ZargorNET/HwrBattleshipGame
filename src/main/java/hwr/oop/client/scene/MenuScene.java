package hwr.oop.client.scene;

import hwr.oop.client.ClientMain;
import hwr.oop.client.drawable.BoundedTextBoxDrawable;
import hwr.oop.client.drawable.EmptySpaceDrawable;
import hwr.oop.client.drawable.TextBoxDrawable;
import hwr.oop.client.renderer.IRenderer;
import hwr.oop.client.util.Tuple;
import java.util.Optional;
import java.util.regex.Pattern;

public class MenuScene implements IScene {

	private final IRenderer renderer = ClientMain.getSingleton().getCurrentRenderer();

	private final TextBoxDrawable infoMessage = new TextBoxDrawable();
	private final BoundedTextBoxDrawable errorMessage = new BoundedTextBoxDrawable(1);

	private final Pattern sizePattern = Pattern.compile("^(\\d+),(\\d+)$");
	private Optional<Tuple<Integer, Integer>> size = Optional.empty();

	@Override
	public void onTextInput(String line) {
		if (size.isEmpty()) {
			var matcher = sizePattern.matcher(line);

			if (matcher.matches()) {
				size = Optional.of(new Tuple<>(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2))));
				infoMessage.clear();
				infoMessage.put(
						"Perfekt!",
						"",
						"Bitte wähle nun deinen Spielmodus aus!",
						"",
						"E: Einzelspieler",
						"M: Mehrspieler"
				);
				errorMessage.clear();
			} else {
				errorMessage.put("Invalides Format! Versuche es nochmal!");
			}
		} else {
			if (line.equalsIgnoreCase("E")) {

			} else if (line.equalsIgnoreCase("M")) {
				ClientMain.getSingleton().setScene(new MultiplayerPlayScene(size.get()));
			} else {
				errorMessage.put("Invalider Spielmodus! Versuche es nochmal!");
			}
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
				"Wir empfehlen eine Größe von maximal 10x10 aufgrund der Anzeige!"
		);
		renderer.draw(infoMessage);
		renderer.draw(new EmptySpaceDrawable());
		renderer.draw(errorMessage);
	}

	@Override
	public void onSceneClose() {

	}
}
