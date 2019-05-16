package items;

import javafx.scene.paint.Color;
import skeletons.Settings;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AutoSizeText extends Text {
    private static double increment = 0.1;

    double size = 1000;
    double prefWidth=-1;
    double prefHeight=-1;

    public AutoSizeText(String text, Color color) {
        super(text);
        this.setFill(color);
    }

    public void setSize(double width,double height){
        this.prefWidth = width;
        this.prefHeight = height;
        super.prefHeight(height);
        super.prefWidth(width);
        resizeText();
    }

    public double setTextWidth(double width) {
        this.prefWidth = width;
        resizeText();
        double ret = super.prefWidth(width);
        return ret;
    }

    public double setTextHeight(double height){
        this.prefHeight = height;
        resizeText();
        double ret = super.prefHeight(height);
        return ret;
    }

    //Fills out the available space in the preferred width
    public void resizeText() {
        //The setFont call calls resizeText again
        setFont(Font.font(Settings.getFontString(), size));

        if(prefWidth == -1 && prefHeight ==-1) return;

        //Increases size
        while ((prefWidth ==-1 || getLayoutBounds().getWidth() < (1 - (2 * increment)) * prefWidth)
                && (prefHeight==-1 || getLayoutBounds().getHeight() < (1 - (2 * increment)) * prefHeight)) {
            size = size + size * increment;
            setFont(Font.font(Settings.getFontString(), size));
        }
        //Reduces size
        while ((prefWidth !=-1 && getLayoutBounds().getWidth() > prefWidth)
        || (prefHeight !=-1 && getLayoutBounds().getWidth() > prefHeight)) {
            size = size - size * increment;

            setFont(Font.font(Settings.getFontString(), size));
        }
    }
}
