package util;

import javafx.scene.paint.Color;
import settings.Settings;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AutoSizeText extends Text {
    private static double increment = 0.01;

    double size = 1000;
    double prefWidth = -1;
    double prefHeight = -1;

    public AutoSizeText(String text, Color color) {
        super(text);
        this.setFill(color);
    }

    public void update(String newText) {
        super.setText(newText);
    }

    public void setSize(double width, double height) {
        this.prefWidth = width;
        this.prefHeight = height;
        this.minWidth(width);
        this.minHeight(height);
        resizeText();
    }

    public void setTextWidth(double width) {
        this.prefWidth = width;
        this.minWidth(width);
        resizeText();
    }

    public void setTextHeight(double height) {
        this.prefHeight = height;
        this.minHeight(height);
        resizeText();
    }

    //Fills out the available space in the preferred width
    public void resizeText() {
        //The setFont call calls resizeText again
        setFont(Font.font(Settings.getFontString(), size));

        if (prefWidth == -1 && prefHeight == -1) return;

        //Increases size
        while ((prefWidth == -1 || getLayoutBounds().getWidth() < (1 - (2 * increment)) * prefWidth)
                && (prefHeight == -1 || getLayoutBounds().getHeight() < (1 - (2 * increment)) * prefHeight)) {
            size = size + size * increment;

            setFont(Font.font(Settings.getFontString(), size));
        }
        //Reduces size
        while ((prefWidth != -1 && getLayoutBounds().getWidth() > prefWidth)
                || (prefHeight != -1 && getLayoutBounds().getHeight() > prefHeight)) {
            size = size - size * increment;

            setFont(Font.font(Settings.getFontString(), size));
        }
    }
}
