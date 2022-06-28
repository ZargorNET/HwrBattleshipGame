package hwr.oop.client.drawable;

import hwr.oop.client.GameWorld;
import hwr.oop.client.util.Vector2i;
import org.fusesource.jansi.Ansi;

public class GameWorldDrawable implements IDrawable {

	public GameWorld gameWorld;

	public GameWorldDrawable(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}

	@Override
	public void draw(Ansi out) {
		out.a("  ");
		drawXCoordinates(out);
		// Draw world
		for (int y = 0; y < gameWorld.getSize().getY(); y++) {
			out.a(y + " ");
			for (int x = 0; x < gameWorld.getSize().getX(); x++) {
				var location = new Vector2i(x, y);

				var hit = gameWorld.getHit(location);
				char symbol = hit.map(h -> h ? 'x' : 'o').orElse('#');

				out.a(symbol + " ");
			}
			out.newline();
		}
	}

	private void drawXCoordinates(Ansi out) {
		for (int i = 0; i < gameWorld.getSize().getX(); i++) {
			out.a(i + " ");
		}
		out.newline();

	}
}
