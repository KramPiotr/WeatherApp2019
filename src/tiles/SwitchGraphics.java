package tiles;

import javafx.scene.paint.Color;
import skeletons.Panel;
import skeletons.Settings;

public class SwitchGraphics extends SwitchTile{

    String defaultSwitchValue = "123";
    String secondarySwitchValue = "GUI";

    public SwitchGraphics(Panel parent) {
        super(parent, "Graphics", "123");
        tSwitch.setOnMouseClicked( e-> {
            if (tSwitch.isSelected()) {
                Settings.setGraphical(true);
                value.update(secondarySwitchValue);
            }
            else{
                Settings.setGraphical(false);
                value.update(defaultSwitchValue);
            }
        });
    }

}
