package settings;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import settings.BackButton;
import settings.ColorPrimary;
import settings.ColorSecondary;
import settings.ColorTertiary;
import tiles.Tile;
import uk.ac.cam.cl.dgk27.stateful.State;

import java.util.List;

import static uk.ac.cam.cl.dgk27.stateful.StateManager.HEIGHT;
import static uk.ac.cam.cl.dgk27.stateful.StateManager.WIDTH;

public class SettingsScene extends State {
    List<Tile> tiles;

    protected void initialiseTiles(){
        tiles = List.of(new SwitchTemperature(this),
                new SwitchWind(this),
                new SwitchGraphics(this),
                new SwitchLocation(this),
                new ColorPrimary(this),
                new ColorSecondary(this),
                new ColorTertiary(this),
                new BackButton(this));
    }
    @Override
    protected void initialise() {
        VBox vBox = new VBox();
        initialiseTiles();
        vBox.getChildren().addAll(tiles);
        scene = new Scene(vBox, WIDTH, HEIGHT);
    }

    @Override
    protected void enable() {

    }

    @Override
    protected void disable() {

    }

    @Override
    public void update() {
        tiles.forEach(t->t.update());
    }

    public SettingsScene(String name){
        super(name);
        initialise();
    }
}
