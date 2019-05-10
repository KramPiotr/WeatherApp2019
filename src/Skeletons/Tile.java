package Skeletons;

import javafx.scene.layout.Pane;

public class Tile extends Pane {
    //Actual graphical element, logic handled by its parent panel

    Panel parent;

    public Tile(Panel parent){
        this.parent=parent;
    }
}
