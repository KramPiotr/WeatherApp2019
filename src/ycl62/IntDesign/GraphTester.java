package ycl62.IntDesign;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GraphTester extends Application {
    
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = new Group(new GraphTile(new ForecastPanel()));
        stage.setTitle("GraphTile Test");
        stage.setScene(new Scene(root, 600, 800));
        stage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
