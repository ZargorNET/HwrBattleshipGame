package hwr.oop.client;

import hwr.oop.client.util.Tuple;
import hwr.oop.client.util.Vector2i;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import lombok.Getter;

public class GameWorld {

	@Getter
	private Tuple<Integer, Integer> size;

	private final HashSet<Ship> ships = new HashSet<>();

	// Location, was a successful hit
	@Getter
	private final HashMap<Vector2i, Boolean> hits = new HashMap<>();


	public GameWorld(Tuple<Integer, Integer> size) {
		this.size = size;
	}

	/**
	 * Make a hit!
	 *
	 * @param location The target location
	 *
	 * @return True if you hit a ship
	 */
	public boolean hit(Vector2i location) {
		var success = this.setAliveAt(location, false);
		this.hits.put(location, success);

		return success;
	}

	public Optional<Boolean> getHit(Vector2i location) {
		return Optional.ofNullable(this.hits.get(location));
	}

	public boolean addShip(Ship ship) {
		if (!areLocationsFree(ship.getLocations().keySet()))
			return false;

		return this.ships.add(ship);
	}

	public boolean removeShip(Ship ship) {
		return this.ships.remove(ship);
	}

	public boolean setAliveAt(Vector2i location, boolean alive) {
		return this.ships.stream().anyMatch(ship -> ship.setAlive(location, alive));
	}

	public boolean isLocationFree(Vector2i location) {
		return this.ships.stream().noneMatch(ship -> ship.isAlive(location).isPresent());
	}

	public boolean areLocationsFree(Collection<Vector2i> locations) {
		return locations.stream().allMatch(this::isLocationFree);
	}
}
