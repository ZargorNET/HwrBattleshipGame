package hwr.oop.client;

import hwr.oop.client.util.Vector2i;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import lombok.Getter;

public class GameWorld {

	@Getter
	private Vector2i size;

	@Getter
	private final HashSet<Ship> ships = new HashSet<>();

	// Location, was a successful hit
	@Getter
	private final HashMap<Vector2i, Boolean> hits = new HashMap<>();


	public GameWorld(Vector2i size) {
		this.size = size;
	}

	/**
	 * Make a hit!
	 *
	 * @param location The target location
	 *
	 * @return Empty if nothing has been hit else the hit ship
	 */
	public Optional<Ship> hit(Vector2i location) {
		var success = this.setAliveAt(location, false);
		this.hits.put(location, success.isPresent());

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

	public void addAllShips(Collection<Ship> ships) {
		this.ships.addAll(ships);
	}

	public Optional<Ship> setAliveAt(Vector2i location, boolean alive) {
		var ship = getShipAt(location);
		if (ship.isEmpty())
			return Optional.empty();

		ship.get().setAlive(location, alive);
		return ship;
	}

	public Optional<Ship> getShipAt(Vector2i location) {
		return this.ships.stream().filter(ship -> ship.isAlive(location).isPresent()).findFirst();
	}

	public boolean areLocationsFree(Collection<Vector2i> locations) {
		return locations.stream().allMatch(l -> getShipAt(l).isEmpty());
	}
}
