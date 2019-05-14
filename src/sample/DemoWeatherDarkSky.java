package sample;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import eu.hansolo.tilesfx.weather.DarkSky;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DemoWeatherDarkSky extends Application {
    Tile weatherTile;

    public void init(){
        DarkSky darkSky = new DarkSky("418d19282d20c33a25a314a5af6ffb5a", DarkSky.Unit.CA, DarkSky.Language.ENGLISH, 51.911858D, 7.632815D);
        darkSky.update(51.911858D, 7.632815D);
        this.weatherTile = TileBuilder.create().skinType(Tile.SkinType.WEATHER).prefSize(150.0D, 150.0D).darkSky(darkSky).build();
    }

    public void start(Stage stage) {
        long start = System.currentTimeMillis();
        FlowGridPane pane = new FlowGridPane(8, 5, new Node[]{this.weatherTile});
        pane.setHgap(5.0D);
        pane.setVgap(5.0D);
        pane.setAlignment(Pos.CENTER);
        pane.setCenterShape(true);
        pane.setPadding(new Insets(5.0D));
        pane.setBackground(new Background(new BackgroundFill[]{new BackgroundFill(Color.web("#101214"), CornerRadii.EMPTY, Insets.EMPTY)}));
        PerspectiveCamera camera = new PerspectiveCamera();
        camera.setFieldOfView(10.0D);
        Scene scene = new Scene(pane);
        scene.setCamera(camera);
        stage.setTitle("TilesFX");
        stage.setScene(scene);
        stage.show();
    }

    public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
