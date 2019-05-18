package settings;

import items.AutoSizeText;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.controlsfx.control.ToggleSwitch;
import settings.Settings;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import tiles.Tile;
import uk.ac.cam.cl.dgk27.stateful.State;
import uk.ac.cam.cl.dgk27.stateful.StateManager;

import java.text.NumberFormat;
import java.util.List;

import static javafx.geometry.Pos.CENTER_LEFT;

public class BackButton extends Tile {

    private Button button;

    @Override
    public void update() {
        button.setStyle("-fx-background-color: "+Settings.colorString(Settings.getPrimary())+";"+
                       "-fx-text-fill: "+Settings.colorString(Settings.getSecondary())+";"+
                        "-fx-padding: 10;" + "-fx-border-style: solid inside;"
                        + "-fx-border-width: 3;" + "-fx-border-insets: 0;"
                        + "-fx-border-radius: 0;" + "-fx-border-color: "+ Settings.colorString(Settings.getTertiary())+";"
                        + "-fx-focus-color: transparent;");
        button.setOnMouseClicked(e->{
            StateManager.switchTo("Main");
            button.setStyle("-fx-background-color: "+Settings.colorString(Settings.getFadedPrimary())+";");
        });
        button.setOnMousePressed(e->{
            button.setStyle("-fx-background-color: "+Settings.colorString(Settings.getFadedPrimary())+";");
        });
    }

    public BackButton(State parent){
        super(parent);
        button = new Button();
        update();
        button.setText("Back");
        button.setFont(Font.font(Settings.getFontString(), FontWeight.BOLD, 50));
        button.setPrefSize(600, 100);

        this.setPrefSize(600,100);
        this.getChildren().addAll(button);
    }

}
