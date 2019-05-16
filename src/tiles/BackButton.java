package tiles;

import items.AutoSizeText;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.controlsfx.control.ToggleSwitch;
import skeletons.Settings;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import uk.ac.cam.cl.dgk27.stateful.State;

import java.text.NumberFormat;
import java.util.List;

import static javafx.geometry.Pos.CENTER_LEFT;

public class BackButton extends Tile {

    private Button button;

    @Override
    public void update() {

    }

    public BackButton(State parent){
        super(parent);
        button = new Button();
        button.setText("Back");
        button.setStyle("-fx-background-color: "+Settings.colorString(Settings.getPrimary())+";"
                +"-fx-font:"+Settings.getFontString());
        button.setFont(Font.font(null, FontWeight.BOLD, 50));
        button.setPrefSize(600, 100);

        button.setOnMouseClicked(e->{
            //get back to the homepage
            button.setStyle("-fx-background-color: "+Settings.colorString(Settings.getFadedPrimary())+";");
        });
        update();

        //Set prefered size of tile
        this.setPrefSize(600,100);
        this.getChildren().addAll(button);
    }

}
