package scenes.mainscreen;

import eu.hansolo.tilesfx.weather.DarkSky;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import settings.SwitchTemperature;
import skeletons.WeatherScene;
import tiles.HeaderTile;
import tiles.RealFeelTile;
import tiles.Tile;
import tiles.WindTile;
import uk.ac.cam.cl.dgk27.stateful.StateManager;
import uk.ac.cam.cl.dgk27.weather.RequestType;
import uk.ac.cam.cl.dgk27.weather.Weather;
import uk.ac.cam.cl.dgk27.weather.WeatherAPI;
import ycl62.IntDesign.GraphTile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends WeatherScene {

    private GridPane mainPanel;
    private List<Tile> tiles;
    protected Weather city;

    public MainScreen(String name) throws IOException {
        super(name);
        this.city = WeatherAPI.makeRequest(RequestType.Current, "Paris")[0];
        initialise();
    }

    @Override
    public double getTemperature() {
        return city.getTemp();
    }

    @Override
    public double getWindSpeed() {
        return city.getWind_speed();
    }

    @Override
    public double getRealTemperature() {
        return city.getTemp()-city.getWind_speed()*0.3;
    }

    @Override
    public String getLocation() {
        return city.getCity_name();
    }

    @Override
    public String getDate() {
        return city.getDatetime();
    }


    public MainScreen(String name, Weather city){
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

        scene = new Scene(mainPanel, StateManager.WIDTH, StateManager.HEIGHT);
    }

    @Override
    protected void enable() {

    }

    @Override
    protected void disable() {

    }

    @Override
    public void update() {

    }
}
