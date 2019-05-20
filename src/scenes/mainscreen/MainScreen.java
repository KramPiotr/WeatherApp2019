package scenes.mainscreen;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import settings.Settings;
import skeletons.WeatherScene;
import tiles.*;
import uk.ac.cam.cl.dgk27.stateful.StateManager;
import uk.ac.cam.cl.dgk27.weather.RequestType;
import uk.ac.cam.cl.dgk27.weather.Weather;
import uk.ac.cam.cl.dgk27.weather.WeatherAPI;
import util.Pollen;

import java.io.IOException;

public class MainScreen extends WeatherScene {
    Weather weather;

    WindTile windTile;
    HeaderTile headerTile;
    RealFeelTile realFeelTile;
    ChanceOfRainTile chanceOfRainTile;
    HighLowTile highLowTile;
    HumidityTile humidityTile;
    PollenTile pollenTile;

    public MainScreen(String name) throws IOException {
        super(name);
    }

    @Override
    protected void initialise() {
        try {
            weather = WeatherAPI.makeRequest(RequestType.Current,"London","GBR")[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
        VBox mainPanel = new VBox();
        HBox horizontal = new HBox();
        HBox horizontal1 = new HBox();
        HBox horizontal2 = new HBox();
        mainPanel.setSpacing(0);
        horizontal.setSpacing(0);
        horizontal.setSpacing(0);
        horizontal2.setSpacing(0);
        windTile = new WindTile(this);
        realFeelTile = new RealFeelTile(this);
        headerTile = new HeaderTile(this);
        chanceOfRainTile = new ChanceOfRainTile(this);
        highLowTile = new HighLowTile(this);
        humidityTile = new HumidityTile(this);
        pollenTile = new PollenTile(this);


        horizontal.getChildren().addAll(chanceOfRainTile,highLowTile);
        horizontal1.getChildren().addAll(realFeelTile,windTile);
        horizontal2.getChildren().addAll(humidityTile,pollenTile);

        mainPanel.getChildren().addAll(headerTile,horizontal,horizontal1,horizontal2);



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
        windTile.update();
        headerTile.update();
        realFeelTile.update();
        chanceOfRainTile.update();
        highLowTile.update();
        humidityTile.update();
        pollenTile.update();
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
    public Pollen getPollen() {
        return Pollen.getRandomPollen();
    }

    @Override
    public double getChanceOfRain() {
        return 30;
    }

    @Override
    public double getLow() {
        return weather.getTemp_min();
    }

    @Override
    public double getHigh() {
        return weather.getTemp_max();
    }

    @Override
    public String getCondition() {
        return weather.getIcon();
    }

    @Override
    public double getHumidity() {
        return weather.getHumidity();
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
        if(weather.getDatetime().equals("")){
            return "00/00";
        }
        return weather.getDatetime();
    }


}
