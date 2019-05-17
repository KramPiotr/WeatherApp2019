package skeletons;

import eu.hansolo.tilesfx.weather.DarkSky;
import javafx.scene.Parent;
import javafx.scene.Scene;
import tiles.Tile;
import uk.ac.cam.cl.dgk27.stateful.State;

import java.util.List;

public abstract class Panel extends Parent {

    List<Tile> tiles;
    State scene;

    //TODO: Store layout of tiles in panel
    //TODO: Control logic for tiles
    public Panel(State scene){
        this.scene = scene;
    }

    //TODO
    public void update(){
        for (Tile t:tiles){
            t.update();
        }
    }
}
