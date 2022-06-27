package hwr.oop.client.drawable;

import org.fusesource.jansi.Ansi;

public class EmptySpaceDrawable implements IDrawable {

	@Override
	public void draw(Ansi out) {
		out.newline();
	}
}
