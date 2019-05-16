package skeletons;

import javafx.scene.paint.Color;

public class Settings {
    private static Color primary = Color.ORANGE;
    private static Color fadedPrimary;
    private static double fadeConstant = 2;
    private static Color secondary = Color.BLACK;
    private static Color tertiary = Color.RED;
    private static boolean celsius = true;
    private static boolean kilometers = true;


    private static String font = "Gill Sans MT";

    public static Color getPrimary() {
        return primary;
    }
    public static Color getFadedPrimary(){
        //Set fadedPrimary as weighted average of primary and secondary colors
        if(fadedPrimary==null){
            double red = (primary.getRed()*fadeConstant+secondary.getRed())/(1+fadeConstant);
            double green = (primary.getGreen()*fadeConstant+secondary.getRed())/(1+fadeConstant);
            double blue = (primary.getBlue()*fadeConstant+secondary.getRed())/(1+fadeConstant);
            fadedPrimary = new Color(red,green,blue,1);

        }
        return fadedPrimary;
    }

    public static void setPrimary(Color primary) {
        Settings.primary = primary;
    }

    public static Color getSecondary() {
        return secondary;
    }

    public static void setSecondary(Color secondary) {
        Settings.secondary = secondary;
    }

    public static Color getTertiary() {
        return tertiary;
    }

    public static void setTertiary(Color tertiary) {
        Settings.tertiary = tertiary;
    }

    public static boolean isCelsius() {
        return celsius;
    }

    public static boolean isKilometers(){return kilometers;}

    public static void setCelcius(boolean celcius) {
        Settings.celsius = celcius;
    }

    public static String colorString(Color c){
        return "rgb("+(c.getRed()*255)+","+(c.getGreen()*255)+","+(c.getBlue()*255)+")";
    }

    public static String getFontString(){
        return font;
    }
}
