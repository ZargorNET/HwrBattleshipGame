package hwr.oop.client.renderer;

import hwr.oop.client.ClientMain;
import hwr.oop.client.drawable.IDrawable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;
import lombok.Getter;

import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleRenderer implements IRenderer {

	@Getter
	private final LinkedList<IDrawable> drawables = new LinkedList<>();
	private boolean running = false;

	@Override
	public void start() {
		running = true;
		this.render();
		this.startScanner();
	}

	@Override
	public void stop() {
		running = false;
	}

	@Override
	public boolean isStarted() {
		return this.running;
	}

	public void draw(Collection<IDrawable> drawables) {
		this.drawables.addAll(drawables);
	}

	public void draw(IDrawable drawable) {
		this.drawables.add(drawable);
	}

	public void remove(IDrawable drawable) {
		this.drawables.remove(drawable);
	}

	public void removeAll() {
		this.drawables.clear();
	}

	public void render() {
		var ansi = ansi();

		ansi.eraseScreen();
		drawables.forEach(d -> d.draw(ansi));

		System.out.println(ansi.reset());
	}

	private void startScanner() {
		var scanner = new Scanner(System.in);
		while (this.running) {
			var nextLine = scanner.nextLine();
			if (ClientMain.getSingleton().getCurrentScene() != null)
				ClientMain.getSingleton().getCurrentScene().onTextInput(nextLine);
		}
	}
}
