package tiles;

import Skeletons.APIGetter;
import Skeletons.Panel;

public class TileTesterPanel extends Panel {
    public TileTesterPanel(APIGetter apiGetter) {
        super(apiGetter);
    }

    @Override
    public float getTemperature() {
        return 0;
    }

    @Override
    public float getTemperature(int placeHolderForDate_Time) {
        return 0;
    }

    @Override
    public float getRealTemperature() {
        return 0;
    }

    @Override
    public float getRealTemperature(int placeHolderForDate_Time) {
        return 0;
    }
}
