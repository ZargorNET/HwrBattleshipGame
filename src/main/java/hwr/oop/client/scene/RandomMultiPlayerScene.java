package hwr.oop.client.scene;

import hwr.oop.client.ClientMain;
import hwr.oop.client.GameWorld;
import hwr.oop.client.Ship;
import hwr.oop.client.drawable.BoundedTextBoxDrawable;
import hwr.oop.client.drawable.EmptySpaceDrawable;
import hwr.oop.client.drawable.GameWorldDrawable;
import hwr.oop.client.drawable.TextBoxDrawable;
import hwr.oop.client.renderer.IRenderer;
import hwr.oop.client.util.LocationParser;
import hwr.oop.client.util.ShipSpawner;
import hwr.oop.client.util.Vector2i;

public class RandomMultiPlayerScene implements IScene{

    private final GameWorld gameWorldPlayer1;
    private final GameWorld gameWorldPlayer2;
    private final IRenderer renderer = ClientMain.getSingleton().getCurrentRenderer();
    private final BoundedTextBoxDrawable eventLog = new BoundedTextBoxDrawable(10);
    private boolean Player1Turn;

    public RandomMultiPlayerScene(Vector2i size) {
        this.gameWorldPlayer1 = new GameWorld(size);
        this.gameWorldPlayer2 = new GameWorld(size);
    }

    @Override
    public void onTextInput(String line) {
        var locationOptional = LocationParser.parse(line);
        if (locationOptional.isPresent()) {
            var location = locationOptional.get();
            onLocation(location);
        } else {
            eventLog.put("Invalides Koordinatenformat!");
        }
        renderer.render();
    }

    @Override
    public void onSceneSet() {

        renderer.draw(new TextBoxDrawable("Ratefeld Spieler 1"));
        renderer.draw(new GameWorldDrawable(gameWorldPlayer1));
        renderer.draw(new TextBoxDrawable("Ratefeld Spieler 2"));
        renderer.draw(new GameWorldDrawable(gameWorldPlayer2));
        renderer.draw(new EmptySpaceDrawable());
        renderer.draw(new TextBoxDrawable("-------------------------"));
        renderer.draw(eventLog);
        renderer.draw(new TextBoxDrawable("-------------------------"));
        renderer.draw(new TextBoxDrawable(
                "Versenke alle gegnerischen Schiffe!",
                "Schießformat: X,Y"
        ));
        new ShipSpawner(gameWorldPlayer1, new int[]{5, 4, 4, 3, 3, 3, 2, 2, 2, 2}).spawnShips();
        new ShipSpawner(gameWorldPlayer2, new int[]{5, 4, 4, 3, 3, 3, 2, 2, 2, 2}).spawnShips();
    }

    private void onLocation(Vector2i location) {
        var gameWorldSize = gameWorldPlayer1.getSize();
        if (location.getX() >= gameWorldSize.getX() || location.getX() < 0 || location.getY() >= gameWorldSize.getY() || location.getY() < 0) {
            eventLog.put("Invalide Koordinaten!");
            return;
        }
        GameWorld gameWorld;
        this.Player1Turn ^= true;       //look wich players turn it is
        if(this.Player1Turn){
            gameWorld = gameWorldPlayer1;
            eventLog.put("Spieler 1:");
        }
        else{
            gameWorld = gameWorldPlayer2;
            eventLog.put("Spieler 2:");
        }

        var shipOptional = gameWorld.hit(location);
        if (shipOptional.isEmpty()) {
            eventLog.put(String.format("Wasser: (%d, %d)", location.getX(), location.getY()));
            return;
        }

        var ship = shipOptional.get();
        if (ship.hasSunk())
            eventLog.put("Versenkt! -- Du bist weiter dran");

        else
            eventLog.put(String.format("Treffer: (%d, %d) -- Du bist weiter dran", location.getX(), location.getY()));

        this.Player1Turn ^= true;


        if (gameWorld.getShips().stream().allMatch(Ship::hasSunk)) {
            ClientMain.getSingleton().setScene(new EndScene(gameWorld));
        }

    }
}
