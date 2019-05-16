/*
package tiles;

import javafx.scene.paint.Color;
import skeletons.Panel;
import skeletons.Settings;

public class SwitchHumidity extends SwitchTile{

    String defaultSwitchValue = "123";
    String secondarySwitchValue = "GUI";

    public SwitchHumidity(Panel parent) {
        super(parent, "Humidity", "123");
        tSwitch.setTextFill(Color.ORANGE);
        tSwitch.setOnMouseClicked( e-> {
            if (tSwitch.isSelected()) {
                Settings.setCelcius(false);
                value.update(secondarySwitchValue);
            }
            else{
                Settings.setCelcius(true);
                value.update(defaultSwitchValue);
            }
        });
    }

}*/
