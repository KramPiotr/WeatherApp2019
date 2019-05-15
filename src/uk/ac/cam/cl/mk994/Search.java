package uk.ac.cam.cl.mk994;

import eu.hansolo.tilesfx.weather.DarkSky;
import skeletons.Panel;
import uk.ac.cam.cl.dgk27.weather.RequestType;
import uk.ac.cam.cl.dgk27.weather.Weather;
import uk.ac.cam.cl.dgk27.weather.WeatherAPI;

import java.io.IOException;

public class Search extends Panel {

    private String location;
    private Weather[] weather;

    public Search(String l) throws IOException {
        location = l;
        weather = WeatherAPI.makeRequest(RequestType.Current, location);
    }

    @Override
    public double getTemperature() {
        return weather[0].getTemp();
    }

    @Override
    public double getTemperature(int placeHolderForDate_Time) {
        return 0;
    }

    @Override
    public double getRealTemperature() {
        return 0;
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
