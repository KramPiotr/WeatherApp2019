package items;

import javafx.scene.paint.Color;
import skeletons.Settings;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AutoSizeText extends Text {
    private static double increment = 0.1;

    double size = 1000;
    double prefWidth;

    public AutoSizeText(String text, Color color) {
        super(text);
        this.setFill(color);
    }


    public double setTextWidth(double width) {
        this.prefWidth = width;
        resizeText();
        double ret = super.prefWidth(width);
        return ret;
    }

    //Fills out the available space in the preferred width
    private void resizeText() {
        //The setFont call calls resizeText again
        setFont(Font.font(Settings.getFontString(), size));

        while (getLayoutBounds().getWidth() < (1 - (2 * increment)) * prefWidth) {
            size = size + size * increment;
            setFont(Font.font(Settings.getFontString(), size));
        }
        while (getLayoutBounds().getWidth() > prefWidth) {
            size = size - size * increment;

            setFont(Font.font(Settings.getFontString(), size));
        }
    }
}
