package tiles;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TileTester extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = new Group(new TileTesterPanel());
        primaryStage.setTitle("Tile Tester");
        Scene scene = new Scene(root, 300, 275);
        primaryStage.setScene(scene);
        PerspectiveCamera camera = new PerspectiveCamera();
        camera.setFieldOfView(10.0D);
        scene.setCamera(camera);
        primaryStage.setTitle("TilesFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
