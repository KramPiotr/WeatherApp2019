package uk.ac.cam.cl.dgk27.weather;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Weather {
    private double lon, lat;
    private String weather_main, weather_description;
    private double temp, pressure, humidity, temp_min, temp_max;
    private double visibility, wind_speed, wind_deg;
    private double clouds;
    private String city_name, datetime;
    private double timestamp;

    public Weather(double lon, double lat, String weather_main, String weather_description, double temp, double pressure, double humidity, double temp_min, double temp_max, double visibility, double wind_speed, double wind_deg, double clouds, String city_name, String datetime, double timestamp) {
        this.lon = lon;
        this.lat = lat;
        this.weather_main = weather_main;
        this.weather_description = weather_description;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.visibility = visibility;
        this.wind_speed = wind_speed;
        this.wind_deg = wind_deg;
        this.clouds = clouds;
        this.city_name = city_name;
        this.datetime = datetime;
        this.timestamp = timestamp;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public String getWeather_main() {
        return weather_main;
    }

    public String getWeather_description() {
        return weather_description;
    }

    public double getTemp() {
        return temp;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    /**
     * @return 0 if forecast
     */
    public double getVisibility() {
        return visibility;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public double getWind_deg() {
        return wind_deg;
    }

    public double getClouds() {
        return clouds;
    }

    /**
     * @return Returns an empty string if weather object is for current time (ie not forecast)
     */
    public String getDatetime() {
        return datetime;
    }

    public String getCity_name() {
        return city_name;
    }

    public double getTimestamp() {
        return timestamp;
    }

    /**
     * @return Returns the OpenWeatherMap-specified icon codes, account for night (6pm to 6am)
     */
    public String getIcon() {
        int hours = Integer.parseInt(new SimpleDateFormat("HH").format(new Date()));
        boolean dayTime = hours > 5 && hours < 18;
        String ret;

        switch (getWeather_main().toLowerCase()) {
            case "clear": ret = "01" + (dayTime ? "d" : "n"); break;
            case "clouds": ret = "02" + (dayTime ? "d" : "n"); break;
            case "drizzle": ret = "09" + (dayTime ? "d" : "n"); break;
            case "rain": ret = "10" + (dayTime ? "d" : "n"); break;
            case "thunderstorm": ret = "11" + (dayTime ? "d" : "n"); break;
            case "snow": ret = "13" + (dayTime ? "d" : "n"); break;
            case "mist": ret = "50" + (dayTime ? "d" : "n"); break;
            default: ret = "01" + (dayTime ? "d" : "n");
        }

        return ret;
    }
}
