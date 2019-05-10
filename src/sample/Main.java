package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.ac.cam.cl.dgk27.interactive.RequestType;
import uk.ac.cam.cl.dgk27.interactive.Weather;
import uk.ac.cam.cl.dgk27.interactive.WeatherAPI;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample2.fxml"));
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        Weather[] hey = WeatherAPI.makeRequest(RequestType.Current, "London");
        Weather[] hey2 = WeatherAPI.makeRequest(RequestType.FiveDay, "London");
        System.out.println("Did it work?");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
