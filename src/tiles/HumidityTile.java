package tiles;

import util.AutoSizeText;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import settings.Settings;
import skeletons.WeatherScene;

import java.text.NumberFormat;

import static javafx.geometry.Pos.CENTER_LEFT;

public class HumidityTile extends Tile{
    AutoSizeText label;
    double humidity;
    AutoSizeText value;
    NumberFormat nf;
    Line divider;
    HBox hbox;



    public HumidityTile(WeatherScene parent){
        super(parent);

        //Create number format for temperature
        nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(1);
        nf.setMaximumFractionDigits(1);

        //Set prefered size of tile
        this.setPrefSize(300, 125);

        humidity = parent.getHumidity();

        //Position label and value
        hbox = new HBox();
        hbox.setPadding(new Insets(10, 0, 10, 10));
        hbox.setSpacing(20);


        divider = new Line();
        divider.setStartX(0);
        divider.setEndX(0);
        divider.setStartY(20);
        divider.setEndY(125);
        divider.setStrokeWidth(5);

        value.setTextWidth(150-35);
        label.setTextWidth(150-35);

        hbox.setAlignment(CENTER_LEFT);


        hbox.setMinSize(this.getPrefWidth(), this.getPrefHeight());
        hbox.setPrefSize(300,125);


        this.getChildren().addAll(hbox);
        update();

    }

    @Override
    public void update(){
       humidity = ((WeatherScene) parent).getHumidity();

        value.setText(nf.format(humidity)+"°%");

        value.resizeText();

        hbox.getChildren().addAll(label, divider, value);

        hbox.setBackground(new Background(new BackgroundFill(Settings.getSecondary(), null, null)));

        hbox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 5;" + "-fx-border-insets: 0;"
                + "-fx-border-radius: 0;" + "-fx-border-color: " + Settings.colorString(Settings.getTertiary()) + ";");

        divider.setStroke(Settings.getFadedPrimary());

        label = new AutoSizeText("Humidity:", Settings.getFadedPrimary());
        value = new AutoSizeText(nf.format(humidity)+"°%",Settings.getPrimary());

    }
}
