package hwr.oop.client.drawable;

import hwr.oop.client.GameWorld;
import hwr.oop.client.Ship;
import hwr.oop.client.util.Orientation;
import hwr.oop.client.util.Vector2i;
import org.fusesource.jansi.Ansi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameWorldDrawableTest {

	final String noShipPrint = "  00 01 02 03 04 05 06 07 08 09 \n" +
			"00 .  .  .  .  .  .  .  .  .  .  \n" +
			"01 .  .  .  .  .  .  .  .  .  .  \n" +
			"02 .  .  .  .  .  .  .  .  .  .  \n" +
			"03 .  .  .  .  .  .  .  .  .  .  \n" +
			"04 .  .  .  .  .  .  .  .  .  .  \n" +
			"05 .  .  .  .  .  .  .  .  .  .  \n" +
			"06 .  .  .  .  .  .  .  .  .  .  \n" +
			"07 .  .  .  .  .  .  .  .  .  .  \n" +
			"08 .  .  .  .  .  .  .  .  .  .  \n" +
			"09 .  .  .  .  .  .  .  .  .  .  \n";

	final String oneHitPrint = "  00 01 02 03 04 05 06 07 08 09 \n" +
			"00 x  .  .  .  .  .  .  .  .  .  \n" +
			"01 .  .  .  .  .  .  .  .  .  .  \n" +
			"02 .  .  .  .  .  .  .  .  .  .  \n" +
			"03 .  .  .  .  .  .  .  .  .  .  \n" +
			"04 .  .  .  .  .  .  .  .  .  .  \n" +
			"05 .  .  .  .  .  .  .  .  .  .  \n" +
			"06 .  .  .  .  .  .  .  .  .  .  \n" +
			"07 .  .  .  .  .  .  .  .  .  .  \n" +
			"08 .  .  .  .  .  .  .  .  .  .  \n" +
			"09 .  .  .  .  .  .  .  .  .  .  \n";

	final String oneMissPrint = "  00 01 02 03 04 05 06 07 08 09 \n" +
			"00 o  .  .  .  .  .  .  .  .  .  \n" +
			"01 .  .  .  .  .  .  .  .  .  .  \n" +
			"02 .  .  .  .  .  .  .  .  .  .  \n" +
			"03 .  .  .  .  .  .  .  .  .  .  \n" +
			"04 .  .  .  .  .  .  .  .  .  .  \n" +
			"05 .  .  .  .  .  .  .  .  .  .  \n" +
			"06 .  .  .  .  .  .  .  .  .  .  \n" +
			"07 .  .  .  .  .  .  .  .  .  .  \n" +
			"08 .  .  .  .  .  .  .  .  .  .  \n" +
			"09 .  .  .  .  .  .  .  .  .  .  \n";

	final String drawAllShipsPrint = "  00 01 02 03 04 05 06 07 08 09 \n" +
			"00 x  x  .  .  .  .  .  .  .  .  \n" +
			"01 .  .  .  .  .  .  .  .  .  .  \n" +
			"02 .  .  .  .  .  .  .  .  .  .  \n" +
			"03 .  .  .  .  .  .  .  .  .  .  \n" +
			"04 .  .  .  .  .  .  .  .  .  .  \n" +
			"05 .  .  .  .  .  .  .  .  .  .  \n" +
			"06 .  .  .  .  .  .  .  .  .  .  \n" +
			"07 .  .  .  .  .  .  .  .  .  .  \n" +
			"08 .  .  .  .  .  .  .  .  .  .  \n" +
			"09 .  .  .  .  .  .  .  .  .  .  \n";

	GameWorld gameWorld;

	@BeforeEach
	void setUp() {
		gameWorld = new GameWorld(new Vector2i(10, 10));
		gameWorld.addShip(new Ship(new Vector2i(0, 0), 2, Orientation.EAST));
	}

	@Test
	void test_drawNoShips() {
		var ansi = Ansi.ansi();
		new GameWorldDrawable(gameWorld, false).draw(ansi);
		Assertions.assertEquals(noShipPrint, ansi.toString());
	}

	@Test
	void test_drawOneHit() {
		var ansi = Ansi.ansi();
		gameWorld.hit(new Vector2i(0, 0));
		new GameWorldDrawable(gameWorld, false).draw(ansi);
		Assertions.assertEquals(oneHitPrint, ansi.toString());
	}

	@Test
	void test_drawOneMiss() {
		var ansi = Ansi.ansi();
		gameWorld.getShips().clear();
		gameWorld.hit(new Vector2i(0, 0));
		new GameWorldDrawable(gameWorld, false).draw(ansi);
		Assertions.assertEquals(oneMissPrint, ansi.toString());
	}

	@Test
	void test_drawAllShips() {
		var ansi = Ansi.ansi();
		new GameWorldDrawable(gameWorld, true).draw(ansi);
		Assertions.assertEquals(drawAllShipsPrint, ansi.toString());
	}
}