package tiles;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import skeletons.Panel;
import skeletons.Settings;

public class ColorSecondary extends ColorTile{

    Color defaultColor = Settings.getSecondary();
    public ColorSecondary(Panel parent) {
        super(parent, "Secondary");
        colorPicker.setValue(defaultColor);
        circle.setFill(defaultColor);
        super.colorPicker.setOnAction(e->{
            Settings.setSecondary(colorPicker.getValue());
            circle.setFill(colorPicker.getValue());
        });
    }

}
