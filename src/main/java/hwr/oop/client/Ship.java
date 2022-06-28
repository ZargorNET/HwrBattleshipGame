package hwr.oop.client;

import hwr.oop.client.util.Orientation;
import hwr.oop.client.util.Vector2i;
import java.util.HashMap;
import java.util.Optional;
import lombok.Getter;

public class Ship {

	// Location, isAlive
	@Getter
	private final HashMap<Vector2i, Boolean> locations = new HashMap<>();

	public Ship(Vector2i location, int size, Orientation orientation) {
		var previous = location;

		for (int i = 0; i < size; i++) {
			switch (orientation) {
				case NORTH:
					previous = previous.add(0, -1);
					locations.put(previous, true);
					break;
				case EAST:
					previous = previous.add(1, 0);
					locations.put(previous, true);
					break;
				case SOUTH:
					previous = previous.add(0, 1);
					locations.put(previous, true);
					break;
				case WEST:
					previous = previous.add(-1, 0);
					locations.put(previous, true);
					break;
			}
		}
	}

	public Optional<Boolean> isAlive(Vector2i location) {
		return Optional.ofNullable(this.locations.get(location));
	}

	public boolean setAlive(Vector2i location, boolean alive) {
		if (!this.locations.containsKey(location))
			return false;
		this.locations.put(location, alive);
		return true;
	}
}
