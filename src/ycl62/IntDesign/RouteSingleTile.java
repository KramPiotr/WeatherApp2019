package ycl62.IntDesign;

import scenes.mainscreen.MainScreen;
import tiles.RealFeelTile;
import tiles.Tile;
import uk.ac.cam.cl.dgk27.stateful.State;

import java.io.IOException;

public class RouteSingleTile extends Tile {
    
    private RealFeelTile tile;
    
    public RouteSingleTile(State parent){
        super(parent);
        try{
            tile = new RealFeelTile(new MainScreen("Temp"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        this.getChildren().add(tile);
    }
    
    @Override
    public void update(){
        tile.update();
    }
}
