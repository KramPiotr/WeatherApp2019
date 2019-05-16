package tiles;

import javafx.scene.paint.Color;
import skeletons.Panel;
import skeletons.Settings;

public class SwitchTemperature extends SwitchTile{

    public SwitchTemperature(Panel parent) {
        super(parent, "Temperature", "°C");
        tSwitch.setOnMouseClicked( e-> {
            if (tSwitch.isSelected()) {
                Settings.setCelcius(false);
                value.update("°F");
            }
            else{
                Settings.setCelcius(true);
                value.update("°C");
            }
        });
    }

}
