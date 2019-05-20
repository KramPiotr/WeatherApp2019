package scenes.mainscreen;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import skeletons.WeatherScene;
import tiles.*;
import uk.ac.cam.cl.dgk27.stateful.StateManager;
import uk.ac.cam.cl.dgk27.stateful.YetAnotherSearch;
import uk.ac.cam.cl.dgk27.weather.RequestType;
import uk.ac.cam.cl.dgk27.weather.Weather;
import uk.ac.cam.cl.dgk27.weather.WeatherAPI;
import util.Pollen;
import ycl62.IntDesign.GraphTile;

import java.io.IOException;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class MainScreen extends WeatherScene {
    Weather weather;

    WindTile windTile;
    HeaderTile headerTile;
    RealFeelTile realFeelTile;
    ChanceOfRainTile chanceOfRainTile;
    HighLowTile highLowTile;
    HumidityTile humidityTile;
    PollenTile pollenTile;
    GraphTile dailyTile;

    int daysInAdvance=0;

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
        dailyTile = new GraphTile(this,weather.getCity_name());

        horizontal.getChildren().addAll(chanceOfRainTile,highLowTile);
        horizontal1.getChildren().addAll(realFeelTile,windTile);
        horizontal2.getChildren().addAll(humidityTile,pollenTile);

        mainPanel.getChildren().addAll(headerTile,horizontal,horizontal1,horizontal2);

        scene = new Scene(mainPanel, StateManager.WIDTH, StateManager.HEIGHT);
    }

    @Override
    protected void enable() {
        int temp_id = ((YetAnotherSearch) StateManager.get("SearchToMain")).getSelected();
        if (temp_id != -1) { // ie a search has been called
            try {
                weather = WeatherAPI.makeRequest(RequestType.Current, temp_id)[0];
            } catch(IOException e) {

            }
        }
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
        return weather.getTemp()+(0.5-Math.random())*2*daysInAdvance;
    }

    @Override
    public double getWindSpeed() {
        return Math.max(0,weather.getWind_speed()+(0.5-Math.random())*4*daysInAdvance);
    }

    @Override
    public Pollen getPollen() {
        return Pollen.getRandomPollen();
    }

    @Override
    public double getChanceOfRain() {
        return Math.max(Math.min(100,weather.getClouds()+(0.5-Math.random())*daysInAdvance),0);
    }

    @Override
    public double getLow() {
        return weather.getTemp_min()+(0.5-Math.random())*daysInAdvance;
    }

    @Override
    public double getHigh() {
        return weather.getTemp_max()+(0.5-Math.random())*daysInAdvance;
    }

    @Override
    public String getCondition() {
        return weather.getIcon();
    }

    @Override
    public double getHumidity() {
        return weather.getHumidity()+(0.5-Math.random())*10*daysInAdvance;
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
            return LocalDate.now().getDayOfMonth() +"/"+String.format("%02d", (LocalDate.now().getMonthValue()));
        }
        return weather.getDatetime();
    }

    public void switchToDate(LocalDate date){
        this.daysInAdvance = Math.max(((int) DAYS.between(LocalDate.now(),date)),6);
    }


}
