package scenes.tiletester;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.stage.Stage;
import skeletons.WeatherScene;
import uk.ac.cam.cl.dgk27.stateful.State;
import uk.ac.cam.cl.dgk27.stateful.StateManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class TileTester extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Tile Tester");

        //TileTesterState state = new TileTesterState("Tile tester");

        primaryStage.setTitle("Tile tester");
        primaryStage.setResizable(false);
        primaryStage.setHeight(StateManager.HEIGHT);
        primaryStage.setWidth(StateManager.WIDTH);

       // primaryStage.setScene(state.getScene());

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
