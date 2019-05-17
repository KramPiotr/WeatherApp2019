package uk.ac.cam.cl.dgk27.stateful;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import eu.hansolo.tilesfx.weather.DarkSky;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class YetAnotherWeatherScene extends State {
    Tile weatherTile;

    public YetAnotherWeatherScene(String name) {
        super(name);
    }

    @Override
    void initialise() {
        DarkSky darkSky = new DarkSky("418d19282d20c33a25a314a5af6ffb5a", DarkSky.Unit.CA, DarkSky.Language.ENGLISH, 51.911858D, 7.632815D);
        darkSky.update(51.911858D, 7.632815D);
        this.weatherTile = TileBuilder.create().skinType(Tile.SkinType.WEATHER).prefSize(150.0D, 150.0D).darkSky(darkSky).build();

        FlowGridPane pane = new FlowGridPane(1, 1, new Node[]{this.weatherTile});
        pane.setHgap(5.0D);
        pane.setVgap(5.0D);
        pane.setAlignment(Pos.CENTER);
        pane.setCenterShape(true);
        pane.setPadding(new Insets(5.0D));
        pane.setBackground(new Background(new BackgroundFill[]{new BackgroundFill(Color.web("#101214"), CornerRadii.EMPTY, Insets.EMPTY)}));

        PerspectiveCamera camera = new PerspectiveCamera();
        camera.setFieldOfView(10.0D);

        scene = new Scene(pane);
        scene.setCamera(camera);
    }

    @Override
    void enable() {
        System.out.println("I've just been enabled!");
    }

    @Override
    void disable() {
        System.out.println("I've just been disable!");
    }

    @Override
    public void update() {
        System.out.println("I've just been forced to update!");
    }
}
