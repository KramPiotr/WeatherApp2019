package skeletons;

import eu.hansolo.tilesfx.weather.DarkSky;
import javafx.scene.Parent;
import tiles.Tile;

import java.util.List;

public abstract class Panel extends Parent {

    List<Tile> tiles;

    //TODO: Store layout of tiles in panel
    //TODO: Control logic for tiles

    public abstract double getTemperature();

    //TODO: Change from int for date/time
    public abstract double getTemperature(int placeHolderForDate_Time);

    public abstract double getRealTemperature();

    //TODO: Change from int for date/time
    public abstract double getRealTemperature(int placeHolderForDate_Time);

    public abstract DarkSky.ConditionAndIcon getWeatherCondition();

    //TODO
    public void update(){
        for (Tile t:tiles){
            t.update();
        }
    }
}
