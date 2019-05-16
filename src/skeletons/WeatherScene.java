package skeletons;

import eu.hansolo.tilesfx.weather.DarkSky;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import settings.SwitchTemperature;
import tiles.*;
import uk.ac.cam.cl.dgk27.stateful.State;
import uk.ac.cam.cl.dgk27.weather.RequestType;
import uk.ac.cam.cl.dgk27.weather.Weather;
import uk.ac.cam.cl.dgk27.weather.WeatherAPI;
import ycl62.IntDesign.GraphTile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main screen layout with weather boxes and forecast graph.
 */

public abstract class WeatherScene extends State {
    
    public WeatherScene(String name){
        super(name);
    }

    public abstract double getTemperature();

    public abstract double getWindSpeed();

    public abstract double getRealTemperature();

    public abstract String getLocation();
    
    public abstract String getDate();
}
