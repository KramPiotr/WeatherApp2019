package settings;

import uk.ac.cam.cl.dgk27.stateful.State;

public class SwitchTemperature extends SwitchTile{

    public SwitchTemperature(State parent) {
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
