package tiles;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import skeletons.Panel;
import skeletons.Settings;

public class ColorPrimary extends ColorTile{

    Color defaultColor = Settings.getPrimary();
    public ColorPrimary(Panel parent) {
        super(parent, "Primary");
        colorPicker.setValue(defaultColor);
        circle.setFill(defaultColor);
        super.colorPicker.setOnAction(e->{
            Settings.setPrimary(colorPicker.getValue());
            circle.setFill(colorPicker.getValue());
        });
    }

 }
