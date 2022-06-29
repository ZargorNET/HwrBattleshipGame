package hwr.oop.client.util;

import java.util.Optional;
import java.util.regex.Pattern;

public final class LocationParser {

	public static final Pattern PATTERN = Pattern.compile("^(\\d+),(\\d+)$");

	public static Optional<Vector2i> parse(String s) {
		var matcher = PATTERN.matcher(s);

		if (matcher.matches())
			return Optional.of(new Vector2i(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))));
		else
			return Optional.empty();
	}

	private LocationParser() {
	}
}
