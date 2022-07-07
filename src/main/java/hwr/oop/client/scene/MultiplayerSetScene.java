package hwr.oop.client.scene;

import hwr.oop.client.ClientMain;
import hwr.oop.client.GameWorld;
import hwr.oop.client.Ship;
import hwr.oop.client.drawable.BoundedTextBoxDrawable;
import hwr.oop.client.drawable.EmptySpaceDrawable;
import hwr.oop.client.drawable.GameWorldDrawable;
import hwr.oop.client.drawable.TextBoxDrawable;
import hwr.oop.client.renderer.IRenderer;
import hwr.oop.client.util.LocationOrientation;
import hwr.oop.client.util.LocationOrientationParser;
import hwr.oop.client.util.ShipSpawner;
import hwr.oop.client.util.Vector2i;
import java.util.Optional;

public class MultiplayerSetScene implements IScene {

	private final IRenderer renderer = ClientMain.getSingleton().getCurrentRenderer();
	private final BoundedTextBoxDrawable eventLog = new BoundedTextBoxDrawable(10);
	final GameWorld gameWorldPlayer1;
	final GameWorld gameWorldPlayer2;
	private final TextBoxDrawable playerText = new TextBoxDrawable("Es ist dran Spieler 1:");
	private final BoundedTextBoxDrawable shipLength = new BoundedTextBoxDrawable(1, "Schiffslänge: " + ClientMain.getSingleton().getShipsToGenerate()[0]);
	private final GameWorldDrawable gameWorldDrawable;

	private GameWorld currentGameWorld;

	public MultiplayerSetScene(Vector2i size) {
		this.gameWorldPlayer1 = new GameWorld(size);
		this.gameWorldPlayer2 = new GameWorld(size);
		this.gameWorldDrawable = new GameWorldDrawable(this.gameWorldPlayer1, true);
		this.currentGameWorld = this.gameWorldPlayer1;
	}

	@Override
	public void onTextInput(String line) {
		var gameWorld = this.currentGameWorld;
		var nextShip = getNextShipLength(gameWorld);
		this.gameWorldDrawable.setGameWorld(gameWorld);

		if (nextShip.isEmpty())
			throw new IllegalStateException("ship cannot be null");

		setShipDialogue(nextShip.get(), line, gameWorld);

		nextShip = getNextShipLength(gameWorld);

		if (nextShip.isEmpty()) {
			this.eventLog.clear();
			this.playerText.clear();
			this.playerText.put("Es ist dran Spieler 2:");

			this.currentGameWorld = this.gameWorldPlayer2;
			nextShip = getNextShipLength(this.currentGameWorld);
			this.gameWorldDrawable.setGameWorld(this.currentGameWorld);


			if (nextShip.isEmpty()) {
				ClientMain.getSingleton().setScene(new MultiPlayerScene(gameWorldPlayer1, gameWorldPlayer2));
				renderer.render();
				return;
			}
		}

		this.shipLength.put("Schiffslänge: " + nextShip.get());
		renderer.render();
	}

	@Override
	public void onSceneSet() {
		renderer.draw(this.gameWorldDrawable);
		renderer.draw(new TextBoxDrawable("-------------------------"));
		renderer.draw(new TextBoxDrawable(
				"Setze deine Schiffe",
				"Format: X,Y,Richtung",
				"Für die Richtung gebe ein: N(orth), E(ast), S(outh), W(est)"
		));
		renderer.draw(playerText);
		renderer.draw(shipLength);
		renderer.draw(new EmptySpaceDrawable());
		renderer.draw(new TextBoxDrawable("-------------------------"));
		renderer.draw(eventLog);
	}

	private void setShipDialogue(int nextShip, String line, GameWorld gameWorld) {
		var locOr = LocationOrientationParser.parse(line);
		if (locOr.isPresent()) {
			LocationOrientation location = locOr.get();
			var ship = new Ship(location.getLocation(), nextShip, location.getOrientation());
			if (new ShipSpawner(gameWorld).isLocationValid(ship)) {
				gameWorld.addShip(ship);
				eventLog.put("Erfolg!");
			} else {
				eventLog.put("Ungültige Schiffsposition!");
			}
		} else {
			eventLog.put("Invalides Koordinatenformat!");
		}
	}

	private Optional<Integer> getNextShipLength(GameWorld gameWorld) {
		var index = gameWorld.getShips().size();
		var arraySize = ClientMain.getSingleton().getShipsToGenerate().length;

		if (index >= arraySize)
			return Optional.empty();

		return Optional.of(ClientMain.getSingleton().getShipsToGenerate()[index]);
	}
}
