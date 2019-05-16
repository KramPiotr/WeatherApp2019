package tiles;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import skeletons.Panel;
import skeletons.Settings;
import uk.ac.cam.cl.dgk27.stateful.State;

public class ColorPrimary extends ColorTile{

    Color defaultColor = Settings.getPrimary();
    public ColorPrimary(State parent) {
        super(parent, "Primary");
        colorPicker.setValue(defaultColor);
        circle.setFill(defaultColor);
        super.colorPicker.setOnAction(e->{
            Settings.setPrimary(colorPicker.getValue());
            circle.setFill(colorPicker.getValue());
        });
        defaultButton.setOnMouseClicked(e->{
            Settings.setPrimary(defaultColor);
            circle.setFill(defaultColor);
            colorPicker.setValue(defaultColor);
        });
    }

 }
