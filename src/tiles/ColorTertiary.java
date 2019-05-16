package tiles;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import skeletons.Panel;
import skeletons.Settings;

public class ColorTertiary extends ColorTile{

    Color defaultColor = Settings.getTertiary();
    public ColorTertiary(Panel parent) {
        super(parent, "Tertiary");
        colorPicker.setValue(defaultColor);
        circle.setFill(defaultColor);
        super.colorPicker.setOnAction(e->{
            Settings.setTertiary(colorPicker.getValue());
            circle.setFill(colorPicker.getValue());
        });
    }

}
