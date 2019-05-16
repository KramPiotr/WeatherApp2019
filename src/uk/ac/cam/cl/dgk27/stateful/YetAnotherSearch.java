package uk.ac.cam.cl.dgk27.stateful;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import eu.hansolo.tilesfx.weather.DarkSky;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.io.IOException;

public class YetAnotherSearch extends State {
    public YetAnotherSearch(String name) {
        super(name);
    }

    @Override
    protected void initialise() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SimpleSearch.fxml"));
            scene = new Scene(root);

        } catch (IOException e) {
            System.out.println("File not found - YetAnotherSearch");
        }
    }

    @Override
    protected void enable() {
        ListView lv = (ListView) scene.lookup("#list"); // WARNING: Will only work after scene is shown.
        lv.setItems(FXCollections.<String>observableArrayList("Apple", "Banana", "Orange", "Mango"));

        TextField tf = (TextField) scene.lookup("#search");
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                lv.getItems().add(newValue);
                //lv.setItems();
            }
        }
        );
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
