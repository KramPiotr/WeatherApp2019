
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



        //Set text in label and value
        label = new AutoSizeText("Humidity:", Settings.getFadedPrimary());
        humidity = parent.getHumidity();
        value = new AutoSizeText(nf.format(humidity)+"°%",Settings.getPrimary());




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

        hbox.getChildren().addAll(label, divider, value);

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

        label.setFill(Settings.getFadedPrimary());
        value.setFill(Settings.getPrimary());

        hbox.setBackground(new Background(new BackgroundFill(Settings.getSecondary(), null, null)));

        divider.setStroke(Settings.getFadedPrimary());

        hbox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 5;" + "-fx-border-insets: -1;"
                + "-fx-border-radius: 0;" + "-fx-border-color: " + Settings.colorString(Settings.getTertiary()) + ";");

    }
}