package tiles;
import skeletons.Settings;
import uk.ac.cam.cl.dgk27.stateful.State;

public class SwitchLocation extends SwitchTile{

    String defaultSwitchValue = "On";
    String secondarySwitchValue = "Off";

    public SwitchLocation(State parent) {
        super(parent, "Location", "On");
        tSwitch.setSelected(true);
        tSwitch.setOnMouseClicked( e-> {
            if (tSwitch.isSelected()) {
                Settings.setLocation(true);
                value.update(defaultSwitchValue);
            }
            else{
                Settings.setLocation(false);
                value.update(secondarySwitchValue);
            }
        });
    }

}
