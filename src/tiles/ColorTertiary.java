package tiles;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import skeletons.Panel;
import skeletons.Settings;
import uk.ac.cam.cl.dgk27.stateful.State;

public class ColorTertiary extends ColorTile{

    Color defaultColor = Settings.getTertiary();
    public ColorTertiary(State parent) {
        super(parent, "Tertiary");
        colorPicker.setValue(defaultColor);
        circle.setFill(defaultColor);
        super.colorPicker.setOnAction(e->{
            Settings.setTertiary(colorPicker.getValue());
            circle.setFill(colorPicker.getValue());
        });
        defaultButton.setOnMouseClicked(e->{
            Settings.setTertiary(defaultColor);
            circle.setFill(defaultColor);
            colorPicker.setValue(defaultColor);
        });
    }

}
