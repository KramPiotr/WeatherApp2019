package settings;

import uk.ac.cam.cl.dgk27.stateful.State;

public class SwitchGraphics extends SwitchTile{

    String defaultSwitchValue = "123";
    String secondarySwitchValue = "GUI";

    public SwitchGraphics(State parent) {
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
