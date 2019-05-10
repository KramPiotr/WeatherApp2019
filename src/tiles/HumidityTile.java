package tiles;

import Skeletons.Panel;
import Skeletons.Tile;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class HumidityTile extends Tile {

    public HumidityTile(Panel parent){
        super(parent);
        this.setBackground(new Background(new BackgroundFill(parent.getSecondaryColor(),null,null)));
    }
}
