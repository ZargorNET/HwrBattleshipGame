package hwr.oop.client.drawable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.fusesource.jansi.Ansi;

public class TextBoxDrawable implements IDrawable {

	final Collection<String> history;

	public TextBoxDrawable(String... lines) {
		this.history = new ArrayList<>(Arrays.asList(lines));
	}

	public void put(String... lines) {
		this.history.addAll(Arrays.asList(lines));
	}

	public void remove(String line) {
		this.history.remove(line);
	}

	public void clear() {
		this.history.clear();
	}

	@Override
	public void draw(Ansi out) {
		history.forEach(h -> out.a(h).newline());
	}
}
