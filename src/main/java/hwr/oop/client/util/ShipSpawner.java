package hwr.oop.client.util;

import hwr.oop.client.GameWorld;
import hwr.oop.client.Ship;
import java.util.Arrays;
import java.util.Random;

public class ShipSpawner {

	private final GameWorld gameWorld;
	private final int[] shipsToGenerate;
	private final Random random = new Random();


	public ShipSpawner(GameWorld gameWorld, int[] shipsToGenerate) {
		this.gameWorld = gameWorld;
		this.shipsToGenerate = shipsToGenerate;
	}

	public void spawnShips() {

		for (int shipLength : shipsToGenerate) {
			var iterations = 0;
			Ship ship;
			do {
				// If we end up in an state which is not possible, try completely again
				if (iterations > 500) {
					spawnShips();
					return;
				}
				var loc = new Vector2i(
						randomInt(0, gameWorld.getSize().getX()),
						randomInt(0, gameWorld.getSize().getY()));
				var orientation = Orientation.values()[randomInt(0, Orientation.values().length)];

				ship = new Ship(loc, shipLength, orientation);
				iterations++;
			} while (!isLocationValid(ship));
			gameWorld.addShip(ship);
		}
	}

	private boolean isLocationValid(Ship ship) {
		for (Vector2i loc : ship.getLocations().keySet()) {
			if (loc.getX() < 0 || loc.getY() < 0 || loc.getX() >= gameWorld.getSize().getX() || loc.getY() >= gameWorld.getSize().getY())
				return false;
			if (!gameWorld.areLocationsFree(Arrays.asList(getAdjacentLocations(loc))))
				return false;
		}

		return true;
	}

	private Vector2i[] getAdjacentLocations(Vector2i loc) {
		var locs = new Vector2i[8];

		locs[0] = loc.add(-1, -1);
		locs[1] = loc.add(0, -1);
		locs[2] = loc.add(1, 1);
		locs[3] = loc.add(-1, 0);
		locs[4] = loc.add(1, 0);
		locs[5] = loc.add(-1, 1);
		locs[6] = loc.add(0, 1);
		locs[7] = loc.add(1, 1);

		return locs;
	}

	private int randomInt(int lower, int upperExclusive) {
		return random.nextInt(upperExclusive - lower) + lower;
	}
}
