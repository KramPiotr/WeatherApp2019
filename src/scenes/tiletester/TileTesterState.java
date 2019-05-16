package scenes.tiletester;

import eu.hansolo.tilesfx.weather.DarkSky;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import skeletons.WeatherScene;
import tiles.HeaderTile;
import tiles.RealFeelTile;
import tiles.WindTile;
import uk.ac.cam.cl.dgk27.stateful.StateManager;
import uk.ac.cam.cl.dgk27.weather.RequestType;
import uk.ac.cam.cl.dgk27.weather.Weather;
import uk.ac.cam.cl.dgk27.weather.WeatherAPI;

import java.io.IOException;

public class TileTesterState extends WeatherScene {
    Weather weather;

    WindTile windTile;
    HeaderTile headerTile;
    RealFeelTile realFeelTile;

    public TileTesterState(String name) throws IOException {
        super(name);
        try {
            weather = WeatherAPI.makeRequest(RequestType.Current,"London","GBR")[0];
        } catch (IOException e) {
            e.printStackTrace();
        }

       initialise();

    }

    @Override
    protected void initialise() {
        VBox mainPanel = new VBox();
        HBox horizontal = new HBox();
        windTile = new WindTile(this);
        realFeelTile = new RealFeelTile(this);
        headerTile = new HeaderTile(this);

        horizontal.getChildren().addAll(realFeelTile,windTile);
        mainPanel.getChildren().addAll(headerTile,horizontal);



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

    @Override
    public double getTemperature() {
        return weather.getTemp();
    }

    @Override
    public double getWindSpeed() {
        return weather.getWind_speed();
    }


    @Override
    public double getRealTemperature() {
        return weather.getTemp()-Math.random()*(weather.getTemp()-weather.getTemp_min());
    }

    @Override
    public String getLocation() {
        return weather.getCity_name();
    }

    @Override
    public String getDate() {
        return weather.getDatetime();
    }


}
