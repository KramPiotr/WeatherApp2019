package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.ac.cam.cl.dgk27.weather.RequestType;
import uk.ac.cam.cl.dgk27.weather.Weather;
import uk.ac.cam.cl.dgk27.weather.WeatherAPI;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        // State SettingsState = new SettingsState(); // extends State.java
        // State.addToMap(SettingsState, states); // to add to states map (to allow lookup by name)

        Parent root = FXMLLoader.load(getClass().getResource("sample2.fxml"));
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        Weather[] hey = WeatherAPI.makeRequest(RequestType.Current, "Cambridge");
        Weather[] hey2 = WeatherAPI.makeRequest(RequestType.FiveDay, "London");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
