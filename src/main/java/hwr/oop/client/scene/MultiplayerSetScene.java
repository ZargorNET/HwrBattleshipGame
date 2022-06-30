package hwr.oop.client.scene;

import hwr.oop.client.ClientMain;
import hwr.oop.client.GameWorld;
import hwr.oop.client.drawable.BoundedTextBoxDrawable;
import hwr.oop.client.drawable.EmptySpaceDrawable;
import hwr.oop.client.drawable.GameWorldDrawable;
import hwr.oop.client.drawable.TextBoxDrawable;
import hwr.oop.client.renderer.IRenderer;
import hwr.oop.client.util.LocationOrientationParser;
import hwr.oop.client.util.LocationParser;
import hwr.oop.client.util.Vector2i;
import hwr.oop.client.util.Vector3i;

import java.util.ArrayList;
import java.util.Optional;

public class MultiplayerSetScene implements IScene {

    private final GameWorld gameWorld;
    private final IRenderer renderer = ClientMain.getSingleton().getCurrentRenderer();
    private final BoundedTextBoxDrawable eventLog = new BoundedTextBoxDrawable(10);
    ArrayList<Vector3i> shipPositionsP1 = new ArrayList<>();
    ArrayList<Vector3i> shipPositionsP2 = new ArrayList<>();
    Vector2i size;

    public MultiplayerSetScene(Vector2i size) {
        this.gameWorld = new GameWorld(size);
        this.size = size;
    }

    @Override
    public void onTextInput(String line) {
        var orientationOptional = LocationOrientationParser.parse(line);
        if (orientationOptional.isPresent()) {
            Vector3i location = orientationOptional.get();
            playerArrayForShipSetter(location);
        } else {
            eventLog.put("Invalides Koordinatenformat!");
        }

        renderer.render();
    }

    @Override
    public void onSceneSet() {
        renderer.draw(new EmptySpaceDrawable());
        renderer.draw(new TextBoxDrawable(
                "Setze deine Schiffe",
                "Format: X,Y,Richtung",
                "0=NORTH 1=EAST 2=SOUTH 3=WEST"
        ));
    }

    private void playerArrayForShipSetter(Vector3i location) {
        if (shipPositionsP1.size() <= 10)
            shipPositionsP1.add(location);
        else
            shipPositionsP2.add(location);
        if (shipPositionsP2.size() == 10)
            ClientMain.getSingleton().setScene(new MultiPlayerScene(size, shipPositionsP1, shipPositionsP2));
    }
}
