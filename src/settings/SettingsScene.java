package settings;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import settings.BackButton;
import settings.ColorPrimary;
import settings.ColorSecondary;
import settings.ColorTertiary;
import uk.ac.cam.cl.dgk27.stateful.State;

public class SettingsScene extends State {
    private final double WIDTH = 600, HEIGHT = 800;
    @Override
    protected void initialise() {
        VBox vBox = new VBox();
        vBox.getChildren().addAll(new SwitchTemperature(this),
                new SwitchWind(this),
                new SwitchGraphics(this),
                new SwitchLocation(this),
                new ColorPrimary(this),
                new ColorSecondary(this),
                new ColorTertiary(this),
                new BackButton(this));
        scene = new Scene(vBox, WIDTH, HEIGHT);
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    @Override
    protected void enable() {

    }

    @Override
    protected void disable() {

    }

    @Override
    public void update() {

    }

    public SettingsScene(String name){
        super(name);
        initialise();
    }
}
