package skeletons;

import eu.hansolo.tilesfx.weather.DarkSky;

public class WeatherPanel extends Panel {

    public double getTemperature() {
        return 0;
    }

    public double getTemperature(int placeHolderForDate_Time) {
        return 0;
    }

    public double getRealTemperature() {
        return 0;
    }

    public double getRealTemperature(int placeHolderForDate_Time) {
        return 0;
    }

    public String getLocation(){
        return "Placeholder City";
    }

    public String getDate(){
        return "00/00";
    }

    public DarkSky.ConditionAndIcon getWeatherCondition() {
        return null;
    }
}
