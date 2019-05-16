package uk.ac.cam.cl.dgk27.stateful;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class StateManager extends Application {
    static Stage primary;

    /**
     * Stores all created states
     */
    private static Map<String, State> states = new HashMap<>();

    static boolean addToMap(State s) {
        if (states.containsKey(s.name))
            return false;
        states.put(s.name, s);
        return true;
    }

    /**
     * @param name The name of the scene to switch to
     */
    public static void switchTo(String name) {
            State found = null;
            for (Map.Entry<String, State> e : states.entrySet()) {
                System.out.println(e.getKey());
                if (e.getKey().equals(name)) {
                    found = e.getValue();
                    found.enable();
                } else
                    e.getValue().disable();
            }

            if (found != null){
                primary.setScene(found.getScene());
                primary.show();
            }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primary = primaryStage;
        primaryStage.setTitle("Weather App");
        primaryStage.setResizable(false);
        primaryStage.setHeight(1000d);
        primaryStage.setWidth(600d);
        //primaryStage.getIcons().add(new Image("file:foo.ico"));

        switchTo("Weather");
    }

    public static void main(String[] args) {
        // TODO: Add states here
        new YetAnotherWeatherScene("Weather");
        //new SettingsState("Settings"); // extends State.java

        launch(args);
    }
}
