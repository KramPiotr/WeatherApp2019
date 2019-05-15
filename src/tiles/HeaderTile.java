package tiles;

import eu.hansolo.tilesfx.weather.DarkSky;
import eu.hansolo.tilesfx.weather.WeatherSymbol;
import skeletons.Panel;

public class HeaderTile extends Tile{
    double temperature;
    DarkSky.ConditionAndIcon weather_main;
    String weather_description;
    WeatherSymbol symbol;

    public HeaderTile(Panel parent) {
        super(parent);
        update();
    }

    @Override
    public void update() {
        temperature = parent.getTemperature();
        weather_main = parent.getWeatherCondition();


    }
}
