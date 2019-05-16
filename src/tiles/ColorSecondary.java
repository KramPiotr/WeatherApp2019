package tiles;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import skeletons.Panel;
import skeletons.Settings;
import uk.ac.cam.cl.dgk27.stateful.State;

public class ColorSecondary extends ColorTile{

    Color defaultColor = Settings.getSecondary();
    public ColorSecondary(State parent) {
        super(parent, "Secondary");
        super.defaultColor = defaultColor;
        colorPicker.setValue(defaultColor);
        circle.setFill(defaultColor);
        super.colorPicker.setOnAction(e->{
            Settings.setSecondary(colorPicker.getValue());
            circle.setFill(colorPicker.getValue());
        });
        defaultButton.setOnMouseClicked(e->{
            Settings.setSecondary(defaultColor);
            circle.setFill(defaultColor);
            colorPicker.setValue(defaultColor);
        });
    }

}
