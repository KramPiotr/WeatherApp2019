package Skeletons;

import javafx.scene.Parent;
import javafx.scene.paint.Color;

import java.util.List;

public abstract class Panel extends Parent {
    APIGetter apiGetter;

    List<Tile> tiles;

    //TODO: Store layout of tiles in panel
    //TODO: Control logic for tiles

    public Panel(APIGetter apiGetter)
    {
        this.apiGetter = apiGetter;
    }

    public abstract float getTemperature();

    //TODO: Change from int for date/time
    public abstract float getTemperature(int placeHolderForDate_Time);

    public abstract float getRealTemperature();

    //TODO: Change from int for date/time
    public abstract float getRealTemperature(int placeHolderForDate_Time);


    //TODO
    public void update(){
        for (Tile t:tiles){
            t.update();
        }
    }
}
