package Skeletons;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;

public abstract class Tile extends Pane {
    //Actual graphical element, logic handled by its parent panel

    protected Panel parent;

    public abstract void update();

    public Tile(Panel parent){
        this.parent=parent;
    }
}
