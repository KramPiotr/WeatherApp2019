package uk.ac.cam.cl.dgk27.stateful;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import util.JaroWinklerDistance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class YetAnotherSearch extends State {
    // For location finding
    class Location {
        int id;
        String name, country;
        double lon, lat;

        public Location(int id, String name, String country, double lon, double lat) {
            this.id = id;
            this.name = name;
            this.country = country;
            this.lon = lon;
            this.lat = lat;
        }
    }
    class CityIDPair {
        int id; String name;

        public CityIDPair(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
    private Map<Integer, Location> cities;
    private void loadFile(String file){
        cities = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/src/" + file))) {
            String line = br.readLine(); // header

            while ((line = br.readLine()) != null) {
                String[] b = line.split("\\|"); // id|name|country|lon|lat
                cities.put(Integer.parseInt(b[0]), new Location(Integer.parseInt(b[0]), b[1], b[2], Double.parseDouble(b[3]), Double.parseDouble(b[4])));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    //

    /**
     * @param s The string to search by
     * @return A list of the City(display name)-ID pairs, sorted in descending order according to the Jaro-Winkler distance (cut off at 0.75 and limited to 100)
     */
    private List<CityIDPair> searchCities(String s) {
        return cities.values().stream()
            .map(l -> new Object() {
                int id = l.id;
                Double jwd = JaroWinklerDistance.apply(s, l.name);
            })
            .filter(l -> l.jwd > 0.75)
            .sorted((a, b) -> (int)-Math.signum(a.jwd - b.jwd))
            .limit(100)
            .map(l -> {
                Location temp = cities.get(l.id);
                return new CityIDPair(l.id, temp.name + " | " + temp.country + " | " + String.format("%.2f° N %.2f° E", temp.lat, temp.lon));
            })
            .collect(Collectors.toList());
    }

    private boolean inited = false;
    private int selectedCityID = -1;

    public YetAnotherSearch(String name) {
        super(name);
        initialise();
    }

    /**
     * @return returns the last-selected city ID or -1 if no city has been selected
     */
    public int getSelected() {
        return selectedCityID;
    }

    @Override
    protected void initialise() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SimpleSearch.fxml"));
            scene = new Scene(root);
        } catch (IOException e) {
            System.out.println("File not found - YetAnotherSearch");
        }

        loadFile("cities.csv");
    }

    @Override
    protected void enable() {
        if (!inited) {
            ListView lv = (ListView) scene.lookup("#list"); // WARNING: Will only work after scene is shown.
            //lv.setItems(FXCollections.<String>observableArrayList("Apple", "Banana", "Orange", "Mango"));

            TextField tf = (TextField) scene.lookup("#search");
            tf.textProperty().addListener(new ChangeListener<String>() {
                  @Override
                  public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                      var items = lv.getItems();
                      items.clear();
                      if (newValue.compareTo("") != 0)
                          items.addAll(searchCities(newValue));
                  }
            });

            lv.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    var item = lv.getSelectionModel().getSelectedItem();
                    if (item != null) {
                        // TODO: implement going to routing (getSelected)
                        System.out.println("clicked on " + item);
                    }
                }
            });

            inited = true;
        }
    }



    @Override
    protected void disable() {
        System.out.println("I've just been disable!");
    }

    @Override
    public void update() {
        System.out.println("I've just been forced to update!");
    }
}