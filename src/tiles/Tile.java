package tiles;

import javafx.scene.layout.Pane;
import uk.ac.cam.cl.dgk27.stateful.State;

public abstract class Tile extends Pane {
    //Actual graphical element, logic handled by its parent panel
    
    protected State parent;
    
    public abstract void update();
    
    public Tile(State parent){
        this.parent = parent;
    }
}
