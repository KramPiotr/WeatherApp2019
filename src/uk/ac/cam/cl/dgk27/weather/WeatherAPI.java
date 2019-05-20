package uk.ac.cam.cl.dgk27.weather;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

public class WeatherAPI {
    private final static String api_key = "d699ac01b2fa5c83c4e5286be12fa325"; // built-in
    private static  boolean metric = true;

    private static double safeGetDouble(JSONObject o, String s) {
        try {
            return o.getDouble(s);
        } catch(Exception e) {
            System.out.println("JSON missing elements");
            return 0d;
        }
    }

    private static Weather[] extractJSON(String url) throws IOException {
        // ccleve @ https://stackoverflow.com/questions/4328711/read-url-to-string-in-few-lines-of-java-code
        JSONObject obj = new JSONObject(new Scanner(new URL(url).openStream(), "UTF-8").useDelimiter("\\A").next());

        if (checkMultiDay(obj)) {
            int i = 0;
            JSONObject city = obj.getJSONObject("city");
            JSONObject coordObj = city.getJSONObject("coord");

            JSONArray list = obj.getJSONArray("list");
            Weather[] rtn = new Weather[list.length()];

            Iterator it = list.iterator();
            while (it.hasNext()) {
                JSONObject jo = (JSONObject) it.next();

                JSONObject weather = jo.getJSONArray("weather").getJSONObject(0);
                JSONObject main = jo.getJSONObject("main");
                JSONObject wind = jo.getJSONObject("wind");
                JSONObject clouds = jo.getJSONObject("clouds");
                double dt = jo.getDouble("dt");
                String datetime = jo.getString("dt_txt");

                rtn[i] = new Weather(
                        safeGetDouble(coordObj, "lon"), safeGetDouble(coordObj,"lat"), weather.getString("main"), weather.getString("description"),
                        safeGetDouble(main,"temp"), safeGetDouble(main,"pressure"), safeGetDouble(main,"humidity"), safeGetDouble(main,"temp_min"), safeGetDouble(main,"temp_max"),
                        0d, safeGetDouble(wind,"speed"), safeGetDouble(wind,"deg"), safeGetDouble(clouds,"all"), city.getString("name"), datetime, dt
                );
                i++;
            }
            return rtn;
        }

        // Single-day weather
        Weather w1;
        JSONObject coordObj = obj.getJSONObject("coord");
        JSONObject weather = obj.getJSONArray("weather").getJSONObject(0);
        JSONObject main = obj.getJSONObject("main");
        double vis = safeGetDouble(obj,"visibility");
        JSONObject wind = obj.getJSONObject("wind");
        JSONObject clouds = obj.getJSONObject("clouds");
        double dt = obj.getDouble("dt");
        String name = obj.getString("name");

        w1 = new Weather(
                safeGetDouble(coordObj, "lon"), safeGetDouble(coordObj,"lat"), weather.getString("main"), weather.getString("description"),
                safeGetDouble(main,"temp"), safeGetDouble(main,"pressure"), safeGetDouble(main,"humidity"), safeGetDouble(main,"temp_min"), safeGetDouble(main,"temp_max"),
                vis, safeGetDouble(wind,"speed"), safeGetDouble(wind,"deg"), safeGetDouble(clouds,"all"), name, "", dt
        );

        return new Weather[] {w1};
    }

    // hacky, but works
    private static boolean checkMultiDay(JSONObject obj) {
        try {
            obj.get("message");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void setMetric(boolean val) {
        metric = val;
    }

    /**
     * @param req
     * @param city_name
     * @return An array of weather objects. If request is for current time, returns array of size 1
     * @throws IOException
     */
    public static Weather[] makeRequest(RequestType req, String city_name ) throws IOException {
        return extractJSON("http://api.openweathermap.org/data/2.5/" + ((req == RequestType.Current) ? "weather" : "forecast") + "?q=" + city_name + "&units=" + (metric ? "metric" : "imperial") + "&APPID=" + api_key);
    }

    /**
     * @param req
     * @param city_id
     * @return An array of weather objects. If request is for current time, returns array of size 1
     * @throws IOException
     */
    public static Weather[] makeRequest(RequestType req, int city_id ) throws IOException {
        return extractJSON("http://api.openweathermap.org/data/2.5/" + ((req == RequestType.Current) ? "weather" : "forecast") + "?id=" + city_id + "&units=" + (metric ? "metric" : "imperial") + "&APPID=" + api_key);
    }

    /**
     * @param req
     * @param city_name
     * @param country_code provided in ISO 3166 country codes
     * @return An array of weather objects. If request is for current time, returns array of size 1
     * @throws IOException
     */
    public static Weather[] makeRequest(RequestType req, String city_name, String country_code ) throws IOException {
        return extractJSON("http://api.openweathermap.org/data/2.5/" + ((req == RequestType.Current) ? "weather" : "forecast") + "?q=" + city_name + "," + country_code + "&units=" + (metric ? "metric" : "imperial") + "&APPID=" + api_key);
    }

    /**
     * @param req
     * @param lat
     * @param lon
     * @return An array of weather objects. If request is for current time, returns array of size 1
     * @throws IOException
     */
    public static Weather[] makeRequest(RequestType req, double lat, double lon ) throws IOException {
        return extractJSON("http://api.openweathermap.org/data/2.5/" + ((req == RequestType.Current) ? "weather" : "forecast") + "?lat=" + lat + "&lon=" + lon + "&units=" + (metric ? "metric" : "imperial") + "&APPID=" + api_key);
    }

//    // Finds cities in specified bounding-box
//    public void makeRequest(RequestType req, double lon_left, double lat_bottom, double lon_right, double lat_top, int zoom ) {
//        extractJSON("api.openweathermap.org/data/2.5/box/city?bbox=" + lon_left + "," + lat_bottom + "," + lon_right + "," + lat_top + ","  + zoom + "&APPID=" + api_key);
//    }
//    // Finds cities in specified bounding-box
//    public void makeRequest(RequestType req, double lat, double lon, int num_cities ) {
//        extractJSON("api.openweathermap.org/data/2.5/find?lat=" + lat + "&lon=" + lon + "&" + num_cities + "&APPID=" + api_key);
//    }
}
