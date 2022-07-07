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
import hwr.oop.client.util.Vector2i;

public class MultiPlayerScene implements IScene {

	private final GameWorld gameWorldPlayer1;
	private final GameWorld gameWorldPlayer2;
	private final IRenderer renderer = ClientMain.getSingleton().getCurrentRenderer();
	private final BoundedTextBoxDrawable eventLog = new BoundedTextBoxDrawable(10);
	private boolean isPlayer1Turn;

	public MultiPlayerScene(GameWorld gameWorldPlayer1, GameWorld gameWorldPlayer2) {
		this.gameWorldPlayer1 = gameWorldPlayer1;
		this.gameWorldPlayer2 = gameWorldPlayer2;
	}

	@Override
	public void onTextInput(String line) {
		var locationOptional = LocationParser.parse(line);
		if (locationOptional.isPresent()) {
			var location = locationOptional.get();
			onLocation(location);
		} else {
			eventLog.put("Invalides Koordinatenformat!");
		}
		renderer.render();
	}

	@Override
	public void onSceneSet() {

		renderer.draw(new TextBoxDrawable("Ratefeld Spieler 1"));
		renderer.draw(new GameWorldDrawable(gameWorldPlayer2));
		renderer.draw(new TextBoxDrawable("Ratefeld Spieler 2"));
		renderer.draw(new GameWorldDrawable(gameWorldPlayer1));
		renderer.draw(new EmptySpaceDrawable());
		renderer.draw(new TextBoxDrawable("-------------------------"));
		renderer.draw(eventLog);
		renderer.draw(new TextBoxDrawable("-------------------------"));
		renderer.draw(new TextBoxDrawable(
				"Versenke alle gegnerischen Schiffe!",
				"Schießformat: X,Y"
		));

		eventLog.put("Es startet: Spieler 1");
	}

	private void onLocation(Vector2i location) {
		GameWorld gameWorld;
		if (this.isPlayer1Turn)
			gameWorld = gameWorldPlayer1;
		else
			gameWorld = gameWorldPlayer2;

		var gameWorldSize = gameWorld.getSize();
		if (location.getX() >= gameWorldSize.getX() || location.getX() < 0 || location.getY() >= gameWorldSize.getY() || location.getY() < 0) {
			eventLog.put("Invalide Koordinaten!");
			return;
		}

		var shipOptional = gameWorld.hit(location);
		if (shipOptional.isEmpty()) {
			eventLog.put(String.format("Wasser: (%d, %d)", location.getX(), location.getY()));
		} else {
			var ship = shipOptional.get();
			if (ship.hasSunk())
				eventLog.put("Versenkt! -- Du bist weiter dran");
			else
				eventLog.put(String.format("Treffer: (%d, %d) -- Du bist weiter dran", location.getX(), location.getY()));

			if (gameWorld.getShips().stream().allMatch(Ship::hasSunk)) {
				ClientMain.getSingleton().setScene(new EndScene(gameWorld));
				return;
			}
		}

		this.isPlayer1Turn = !this.isPlayer1Turn;
		this.eventLog.put(String.format("Spieler %d, du bist dran!", this.isPlayer1Turn ? 1 : 0));
	}
}
