package hwr.oop.client.util;

import java.util.Optional;
import java.util.regex.Pattern;

public final class LocationOrientationParser {

	public static final Pattern PATTERN = Pattern.compile("^(\\d+),(\\d+),([NESWnesw])$");

	public static Optional<LocationOrientation> parse(String s) {
		var matcher = PATTERN.matcher(s);

		if (matcher.matches()) {
			Orientation orientation;
			switch (matcher.group(3).toLowerCase()) {
				case "n":
					orientation = Orientation.NORTH;
					break;
				case "e":
					orientation = Orientation.EAST;
					break;
				case "s":
					orientation = Orientation.SOUTH;
					break;
				case "w":
					orientation = Orientation.WEST;
					break;
				default:
					return Optional.empty();
			}
			return Optional.of(
					new LocationOrientation(
							new Vector2i(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))),
							orientation
					)
			);
		} else {
			return Optional.empty();
		}
	}

	private LocationOrientationParser() {
	}
}
