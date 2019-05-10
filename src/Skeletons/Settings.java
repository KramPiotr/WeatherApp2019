package Skeletons;

import javafx.scene.paint.Color;

public class Settings {
    private static Color primary = Color.CORAL;
    private static Color secondary = Color.MEDIUMVIOLETRED;
    private static Color tertiary = Color.WHEAT;
    private static boolean celcius = true;

    public static Color getPrimary() {
        return primary;
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

    public static boolean isCelcius() {
        return celcius;
    }

    public static void setCelcius(boolean celcius) {
        Settings.celcius = celcius;
    }
}
