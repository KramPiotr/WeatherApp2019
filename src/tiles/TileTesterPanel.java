package tiles;

import skeletons.Panel;
import uk.ac.cam.cl.dgk27.interactive.RequestType;
import uk.ac.cam.cl.dgk27.interactive.Weather;
import uk.ac.cam.cl.dgk27.interactive.WeatherAPI;

import java.io.IOException;

public class TileTesterPanel extends Panel {
    Weather weather;

    {
        try {
            weather = WeatherAPI.makeRequest(RequestType.Current,"Cambridge","GBR")[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public String getWeatherCondition() {
        return weather.getWeather_main();
    }

    @Override
    public String getWeatherDescription() {
        return weather.getWeather_description();
    }
}
