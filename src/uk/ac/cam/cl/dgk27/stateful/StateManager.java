package uk.ac.cam.cl.dgk27.stateful;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import scenes.mainscreen.MainScreen;
import settings.SettingsScene;
import ycl62.IntDesign.DateRangeScene;
//import ycl62.IntDesign.RouteScene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StateManager extends Application {
    static Stage primary;
    public static final double WIDTH=612; //Javafx seems to include the windows border in this, the extra 12 pixels correct for that
    public static final double HEIGHT=900;
    
    /**
     * Stores all created states
     */
    private static Map<String, State> states = new HashMap<>();
    
    static boolean addToMap(State s){
        if(states.containsKey(s.name))
            return false;
        states.put(s.name, s);
        return true;
    }
    
    /**
     * @param name The name of the scene to switch to
     */
    public static void switchTo(String name) {
        System.out.println("Switching to " + name);
        State found = null;
        for (Map.Entry<String, State> e : states.entrySet()) {
            if (e.getKey().equals(name)) {
                found = e.getValue();
            } else if (e.getValue().enabled) {
                e.getValue().disable();
                e.getValue().enabled = false;
            }
        }

        if (found != null){
            primary.hide();
            primary.setScene(found.getScene());
            primary.show();
            found.enable();
            found.update();//Don't change this. It helps all the colors to be updated after the values in settings are changed
            found.enabled = true;
        }
    }
    
    public static State get(String name){
        return states.get(name);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        primary = primaryStage;
        primaryStage.setTitle("Weather App");
        primaryStage.setResizable(false);
        primaryStage.setHeight(HEIGHT);
        primaryStage.setWidth(WIDTH);
        //primaryStage.getIcons().add(new Image("file:foo.ico"));
        
        switchTo("Main");
    }
    
    public static void main(String[] args) throws IOException{
        //Broke something, couldn't fix it, so here.
        //https://stackoverflow.com/questions/14025718/javafx-toolkit-not-initialized-when-trying-to-play-an-mp3-file-through-mediap/38883432
        JFXPanel fxPanel = new JFXPanel();
        
        // TODO: Add states here
//        new YetAnotherWeatherScene("Weather"); // extends State.java
        new MainScreen("Main");
       // new RouteScene("Route");
        new YetAnotherSearch("Search", "Date");
        new YetAnotherSearch("SearchToMain", "Main");
        new DateRangeScene("Date");
        new SettingsScene("Settings");
        
        launch(args);
    }
}
