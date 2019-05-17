package tiles;

import items.AutoSizeText;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import settings.Settings;
import skeletons.WeatherScene;

import java.text.NumberFormat;

import static javafx.geometry.Pos.CENTER_LEFT;

public class WindTile extends Tile {
    AutoSizeText label;
    double windSpeed;
    AutoSizeText value;
    NumberFormat nf;
    

    
    public WindTile(WeatherScene parent){
        super(parent);
        
        //Create number format for temperature
        nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(1);
        nf.setMaximumFractionDigits(1);
        
        //Set prefered size of tile
        this.setPrefSize(295, 150);


        
        //Set text in label and value
        label = new AutoSizeText("Wind Speed:", Settings.getFadedPrimary());

        if(Settings.isKilometers()){
            value = new AutoSizeText(nf.format(windSpeed)+"Km/h",Settings.getPrimary());
        } else {
            value = new AutoSizeText(nf.format(windSpeed)+"MPH",Settings.getPrimary());
        }
        
        //Position label and value
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);
        hbox.setBackground(new Background(new BackgroundFill(Settings.getSecondary(), null, null)));

        
        Line divider = new Line();
        divider.setStartX(0);
        divider.setEndX(0);
        divider.setStartY(0);
        divider.setEndY(150);
        divider.setStrokeWidth(5);
        divider.setStroke(Settings.getPrimary());
        
        
        value = new AutoSizeText("" + windSpeed, Settings.getPrimary());
        label.setTextWidth(150-15);
        value.setTextWidth(150-30);

        hbox.setAlignment(CENTER_LEFT);

        hbox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 5;" + "-fx-border-insets: 0;"
                + "-fx-border-radius: 0;" + "-fx-border-color: " + Settings.colorString(Settings.getTertiary()) + ";");
        hbox.getChildren().addAll(label, divider, value);
        
        hbox.setMinSize(this.getPrefWidth(), this.getPrefHeight());
        
        
        this.getChildren().addAll(hbox);
        update();
        
    }

    @Override
    public void update(){
        windSpeed = ((WeatherScene) parent).getWindSpeed();
        value.resizeText();
    }

}
