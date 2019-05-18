package settings;
import javafx.scene.paint.Color;
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
            parent.update();
        });
        defaultButton.setOnMouseClicked(e->{
            Settings.setPrimary(defaultColor);
            circle.setFill(defaultColor);
            colorPicker.setValue(defaultColor);
            parent.update();
        });
    }

 }
