package tiles;

import javafx.scene.paint.Color;
import skeletons.Settings;
import uk.ac.cam.cl.dgk27.stateful.State;

public class SwitchWind extends SwitchTile{

    String defaultSwitchValue = "km/h";
    String secondarySwitchValue = "mph";

    public SwitchWind(State parent) {
        super(parent, "Wind speed", "km/h");
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
