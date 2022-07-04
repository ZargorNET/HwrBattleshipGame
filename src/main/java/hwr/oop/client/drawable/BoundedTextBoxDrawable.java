package hwr.oop.client.drawable;

import hwr.oop.client.util.CircularFifoQueue;
import java.util.Arrays;
import java.util.Collection;
import org.fusesource.jansi.Ansi;

public class BoundedTextBoxDrawable implements IDrawable {

	public final int lineHeight;
	private final Collection<String> history;

	public BoundedTextBoxDrawable(int lineHeight) {
		this.lineHeight = lineHeight;
		this.history = new CircularFifoQueue<>(this.lineHeight);
	}

	public BoundedTextBoxDrawable(int lineHeight, String... initial) {
		this.lineHeight = lineHeight;
		this.history = new CircularFifoQueue<>(this.lineHeight);
		this.history.addAll(Arrays.asList(initial));
	}

	public void put(String... lines) {
		history.addAll(Arrays.asList(lines));
	}

	public void clear() {
		this.history.clear();
	}

	@Override
	public void draw(Ansi out) {
		history.forEach(h -> out.a(h).newline());

	}
}
