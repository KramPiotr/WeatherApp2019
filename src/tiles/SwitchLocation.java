package tiles;
import skeletons.Panel;
import skeletons.Settings;

public class SwitchLocation extends SwitchTile{

    String defaultSwitchValue = "On";
    String secondarySwitchValue = "Off";

    public SwitchLocation(Panel parent) {
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
