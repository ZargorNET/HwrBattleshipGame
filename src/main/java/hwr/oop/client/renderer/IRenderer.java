package hwr.oop.client.renderer;

import hwr.oop.client.drawable.IDrawable;
import java.util.Collection;

public interface IRenderer {

	void start();

	void stop();

	void draw(Collection<IDrawable> drawables);

	void draw(IDrawable drawable);

	void remove(IDrawable drawable);

	void removeAll();

	void render();
}
