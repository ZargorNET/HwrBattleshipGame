package hwr.oop.client.util;

import java.util.Optional;
import java.util.regex.Pattern;

public final class LocationOrientationParser {

	public static final Pattern PATTERN = Pattern.compile("^(\\d+),(\\d+),(\\d+)$");

	public static Optional<Vector3i> parse(String s) {
		var matcher = PATTERN.matcher(s);

		if (matcher.matches())
			return Optional.of(new Vector3i(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),Integer.parseInt(matcher.group(3))));
		else
			return Optional.empty();
	}

	private LocationOrientationParser() {
	}
}
