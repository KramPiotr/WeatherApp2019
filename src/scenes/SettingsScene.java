package scenes;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SettingsScene extends Scene {
    public static class MyVBox extends VBox {
        public MyVBox() {
            //vbox.getChildren().addAll(button, label, new Slider(0,10,4));
            getChildren().add(new Label("Hello World"));
        }
    }

    public SettingsScene(){
        super(new MyVBox(), 1000, 600);
    }
}
