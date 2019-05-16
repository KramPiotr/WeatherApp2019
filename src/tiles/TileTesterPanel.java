package tiles;

import eu.hansolo.tilesfx.weather.DarkSky;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import skeletons.Panel;
import skeletons.Settings;
import skeletons.WeatherPanel;
import uk.ac.cam.cl.dgk27.weather.RequestType;
import uk.ac.cam.cl.dgk27.weather.Weather;
import uk.ac.cam.cl.dgk27.weather.WeatherAPI;

import java.io.IOException;

public class TileTesterPanel extends WeatherPanel {
    Weather weather;

    public TileTesterPanel()
    {
        try {
            weather = WeatherAPI.makeRequest(RequestType.Current,"London","GBR")[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
        VBox tileBox = new VBox();
        tileBox.setBackground(new Background(new BackgroundFill(Settings.getTertiary(),null,null)));
        tileBox.getChildren().addAll(new HeaderTile(this),new RealFeelTile(this));

        this.getChildren().add(tileBox);
    }

    @Override
    public double getTemperature() {
        return weather.getTemp();
    }

    @Override
    public double getTemperature(int placeHolderForDate_Time) {
        return 0;
    }

    @Override
    public double getRealTemperature() {
        //TODO Stop fudging this
        return weather.getTemp()-Math.random()*(weather.getTemp()-weather.getTemp_min());
    }

    @Override
    public double getRealTemperature(int placeHolderForDate_Time) {
        return 0;
    }

    @Override
    public DarkSky.ConditionAndIcon getWeatherCondition() {
        return null;
    }

}
