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
					locations.put(previous, true);
					previous = previous.add(0, -1);
					break;
				case EAST:
					locations.put(previous, true);
					previous = previous.add(1, 0);
					break;
				case SOUTH:
					locations.put(previous, true);
					previous = previous.add(0, 1);
					break;
				case WEST:
					locations.put(previous, true);
					previous = previous.add(-1, 0);
					break;
			}
		}
	}

	public Optional<Boolean> isAlive(Vector2i location) {
		return Optional.ofNullable(this.locations.get(location));
	}

	public boolean hasSunk() {
		return this.locations.values().stream().noneMatch(b -> b);
	}

	public int getSize() {
		return this.locations.size();
	}

	public boolean setAlive(Vector2i location, boolean alive) {
		if (!this.locations.containsKey(location))
			return false;
		this.locations.put(location, alive);
		return true;
	}
}
