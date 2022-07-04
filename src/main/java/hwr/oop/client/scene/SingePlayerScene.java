package hwr.oop.client.scene;

import hwr.oop.client.ClientMain;
import hwr.oop.client.GameWorld;
import hwr.oop.client.Ship;
import hwr.oop.client.drawable.BoundedTextBoxDrawable;
import hwr.oop.client.drawable.EmptySpaceDrawable;
import hwr.oop.client.drawable.GameWorldDrawable;
import hwr.oop.client.drawable.TextBoxDrawable;
import hwr.oop.client.renderer.IRenderer;
import hwr.oop.client.util.LocationParser;
import hwr.oop.client.util.ShipSpawner;
import hwr.oop.client.util.Vector2i;

public class SingePlayerScene implements IScene {

	private final GameWorld gameWorld;
	private final IRenderer renderer = ClientMain.getSingleton().getCurrentRenderer();
	private final BoundedTextBoxDrawable eventLog = new BoundedTextBoxDrawable(10);

	public SingePlayerScene(Vector2i size) {
		this.gameWorld = new GameWorld(size);
	}

	@Override
	public void onTextInput(String line) {
		if (line.equalsIgnoreCase("cheat")) {
			onCheat();
		} else {
			var locationOptional = LocationParser.parse(line);
			if (locationOptional.isPresent()) {
				var location = locationOptional.get();
				onLocation(location);
			} else {
				eventLog.put("Invalides Koordinatenformat!");
			}
		}
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

		new ShipSpawner(gameWorld).generateShips();
	}

	private void onCheat() {
		gameWorld.getShips().forEach(ship -> ship.getLocations().keySet().forEach(gameWorld::hit));
		eventLog.put("Aha! Wir haben einen Schummler!");
	}

	private void onLocation(Vector2i location) {
		var gameWorldSize = gameWorld.getSize();
		if (location.getX() >= gameWorldSize.getX() || location.getX() < 0 || location.getY() >= gameWorldSize.getY() || location.getY() < 0) {
			eventLog.put("Invalide Koordinaten!");
			return;
		}
		var shipOptional = gameWorld.hit(location);
		if (shipOptional.isEmpty()) {
			eventLog.put(String.format("Wasser: (%d, %d)", location.getX(), location.getY()));
			return;
		}

		var ship = shipOptional.get();
		if (ship.hasSunk())
			eventLog.put("Versenkt!");
		else
			eventLog.put(String.format("Treffer: (%d, %d)", location.getX(), location.getY()));


		if (gameWorld.getShips().stream().allMatch(Ship::hasSunk)) {
			ClientMain.getSingleton().setScene(new EndScene(gameWorld));
		}

	}
}
