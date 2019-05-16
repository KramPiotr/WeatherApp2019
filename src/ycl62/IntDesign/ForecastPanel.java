package ycl62.IntDesign;

import eu.hansolo.tilesfx.weather.DarkSky;
import skeletons.Panel;
import skeletons.WeatherPanel;
import uk.ac.cam.cl.dgk27.weather.RequestType;
import uk.ac.cam.cl.dgk27.weather.Weather;
import uk.ac.cam.cl.dgk27.weather.WeatherAPI;

import java.io.IOException;
import java.util.Arrays;

public class ForecastPanel extends WeatherPanel {
    
    private Weather[] weather;
    private String city = "Cambridge";
    
    public ForecastPanel(/*City c*/){
        try{
            weather = WeatherAPI.makeRequest(RequestType.FiveDay, city);
        } catch(IOException e) {
            e.printStackTrace();
            weather = new Weather[1];
            weather[0] = new Weather(0.1, 0.1, "Cloudy", "chance of meatballs", 40.0, 101325, 95.25, 65.1, 70.1, 2.71828, 3.1415, -Math.PI, 5.0, "Cambridge", "2012 AD", 123.321);
        }
    }
    
    public double[] get5DayTemp(){
        return Arrays.stream(weather).mapToDouble(Weather::getTemp).toArray();
    }
    
    public String[] get5DayTimestamp(){
        return Arrays.stream(weather).map(Weather::getDatetime).toArray(String[]::new);
    }
    
    public String getCity(){
        return city;
    }
    
    @Override
    public double getTemperature(){
        return 0;
    }
    
    @Override
    public double getTemperature(int placeHolderForDate_Time){
        return 0;
    }
    
    @Override
    public double getRealTemperature(){
        return 0;
    }
    
    @Override
    public double getRealTemperature(int placeHolderForDate_Time){
        return 0;
    }
    
    @Override
    public DarkSky.ConditionAndIcon getWeatherCondition(){
        return null;
    }
}
