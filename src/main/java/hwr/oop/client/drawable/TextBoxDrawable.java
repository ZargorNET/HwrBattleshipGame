package hwr.oop.client.drawable;

import java.util.ArrayList;
import java.util.Collection;
import org.fusesource.jansi.Ansi;

public class TextBoxDrawable implements IDrawable {

	private final Collection<String> history = new ArrayList<>();

	public void insert(String line) {
		this.history.add(line);
	}

	public void remove(String line) {
		this.history.remove(line);
	}

	@Override
	public void draw(Ansi out) {
		history.forEach(h -> out.a(h).newline());
	}
}
