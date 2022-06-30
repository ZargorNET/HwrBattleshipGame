package hwr.oop.client.util;

import lombok.Data;

import java.util.Objects;

@Data
public class Vector3i {

	private int x, y, z;

	public Vector3i(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3i add(int x, int y, int z) {
		return new Vector3i(this.x + x, this.y + y, this.z +z);
	}

	public static Vector3i zero() {
		return new Vector3i(0, 0, 0);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Vector3i)) return false;
		Vector3i vector3i = (Vector3i) o;
		return x == vector3i.x && y == vector3i.y && z == vector3i.z;
	}

	public int getZ(){
		return z;
	}

	public Vector2i getsVector2i(){
		return new Vector2i(x,y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}
}
