package tiles;

import javafx.scene.paint.Color;
import skeletons.Panel;
import skeletons.Settings;

public class SwitchWind extends SwitchTile{

    String defaultSwitchValue = "km/h";
    String secondarySwitchValue = "mph";

    public SwitchWind(Panel parent) {
        super(parent, "Windspeed", "km/h");
        tSwitch.setOnMouseClicked( e-> {
            if (tSwitch.isSelected()) {
                Settings.setKilometers(true);
                value.update(secondarySwitchValue);
            }
            else{
                Settings.setKilometers(false);
                value.update(defaultSwitchValue);
            }
        });
    }

}
