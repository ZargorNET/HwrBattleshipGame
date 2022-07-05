package hwr.oop.client.util;

import java.util.Objects;
import lombok.Data;

@Data
public class Vector2i {

	private int x;
	private int y;

	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Vector2i add(int x, int y) {
		return new Vector2i(this.x + x, this.y + y);
	}

	public static Vector2i zero() {
		return new Vector2i(0, 0);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Vector2i)) return false;
		Vector2i vector2i = (Vector2i) o;
		return x == vector2i.x && y == vector2i.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
