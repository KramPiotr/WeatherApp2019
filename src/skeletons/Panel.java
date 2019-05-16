package skeletons;

import eu.hansolo.tilesfx.weather.DarkSky;
import javafx.scene.Parent;
import tiles.Tile;

import java.util.List;

public abstract class Panel extends Parent {

    List<Tile> tiles;

    //TODO: Store layout of tiles in panel
    //TODO: Control logic for tiles

    //TODO
    public void update(){
        for (Tile t:tiles){
            t.update();
        }
    }
}
