package skeletons;

import eu.hansolo.tilesfx.weather.DarkSky;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
public class WeatherScene extends State {
    
    private final double WIDTH = 600, HEIGHT = 800;
    private GridPane mainPanel;
    private List<Tile> tiles;
    protected Weather city;
    
    public WeatherScene(String name) throws IOException{
        super(name);
        this.city = WeatherAPI.makeRequest(RequestType.Current, "Paris")[0];
        initialise();
    }
    
    public WeatherScene(String name, Weather city){
        super(name);
        this.city = city;
        initialise();
    }
    
    @Override
    protected void initialise(){
        tiles = new ArrayList<>();
        
        //TODO: Add new tiles here
        HeaderTile headerTile = new HeaderTile(this);
        tiles.add(headerTile);
        RealFeelTile realFeelTile = new RealFeelTile(this);
        tiles.add(realFeelTile);
        WindTile windTile = new WindTile(this);
        tiles.add(windTile);
        GraphTile graphTile = new GraphTile(this, "Paris");
        tiles.add(graphTile);
        
        mainPanel = new GridPane();
        mainPanel.addRow(0, headerTile);
        mainPanel.addRow(1, realFeelTile, windTile);
        mainPanel.addRow(2, new SwitchTemperature(this), new WindTile(this));
        mainPanel.addRow(3, new WindTile(this), new WindTile(this));
        mainPanel.addRow(4, graphTile);
        mainPanel.setAlignment(Pos.TOP_LEFT);
        mainPanel.setCenterShape(true);
        mainPanel.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        
        scene = new Scene(mainPanel, WIDTH, HEIGHT);
    }
    
    @Override
    protected void enable(){
    
    }
    
    @Override
    protected void disable(){
    
    }
    
    @Override
    public void update(){
        for(Tile t : tiles){
            t.update();
        }
    }
    
    
    public double getTemperature(){
        return city.getTemp();
    }
    
    public double getTemperature(int placeHolderForDate_Time){
        return 0;
    }
    
    public double getRealTemperature(){
        return city.getTemp() - Math.random() * (city.getTemp() - city.getTemp_min());
    }
    
    public double getRealTemperature(int placeHolderForDate_Time){
        return 0;
    }
    
    public String getLocation(){
        return "Placeholder City";
    }
    
    public String getDate(){
        return "00/00";
    }
    
    public DarkSky.ConditionAndIcon getWeatherCondition(){
        return null;
    }
}
