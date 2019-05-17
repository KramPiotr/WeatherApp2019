package uk.ac.cam.cl.mk994;

import eu.hansolo.tilesfx.weather.DarkSky;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import skeletons.Panel;
import uk.ac.cam.cl.dgk27.stateful.State;
import uk.ac.cam.cl.dgk27.weather.RequestType;
import uk.ac.cam.cl.dgk27.weather.Weather;
import uk.ac.cam.cl.dgk27.weather.WeatherAPI;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Search extends State {

    static final Path cities = Paths.get("resources/cityNames");

    private String c;
    private String city;
    private Weather[] weather;

    public Search(String name) {
        super(name);
        c = name;
    }

    @Override
    public void initialise() throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("sample2.fxml"));
        //scene = new Scene(root);

        //file with list of cities and other information about the cities
        File file = cities.toFile();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        //go through each line in cities file
        while ((st = br.readLine()) != null) {
            String[] parts = st.split(",");
            //there should only be 4 parts, checking in case a city has a comma in its name and so accidentally got split
            if (parts.length > 5) {
                for (int i=5; i<parts.length; i++) {
                    //combine the parts of the city name if it got split
                    parts[4] = parts[4] + "," + parts[i];
                }
            }
            //checking if the city searched is in the cities file
            if (parts[4].equals(c)) {
                city = c;
                break;
            }
        }

        weather = WeatherAPI.makeRequest(RequestType.Current, city);
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }

    @Override
    public void update() {

    }
}
