package tiles;

import javafx.scene.control.SplitPane;
import util.AutoSizeText;
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
    HBox hbox;
    Line divider;
    

    
    public WindTile(WeatherScene parent){
        super(parent);
        
        //Create number format for temperature
        nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(1);
        nf.setMaximumFractionDigits(1);
        
        //Set prefered size of tile
        this.setPrefSize(300, 125);


        
        //Set text in label and value
        label = new AutoSizeText("Wind Speed:", Settings.getFadedPrimary());

        if(Settings.isKilometers()){
            value = new AutoSizeText(nf.format(windSpeed)+"Km/h",Settings.getPrimary());
        } else {
            value = new AutoSizeText(nf.format(windSpeed)+"MPH",Settings.getPrimary());
        }
        
        //Position label and value
        hbox = new HBox();
        hbox.setPadding(new Insets(10, 0, 10, 10));
        hbox.setSpacing(10);
        hbox.setBackground(new Background(new BackgroundFill(Settings.getSecondary(), null, null)));

        
        divider = new Line();
        divider.setStartX(0);
        divider.setEndX(0);
        divider.setStartY(20);
        divider.setEndY(125);
        divider.setStrokeWidth(5);
        divider.setStroke(Settings.getFadedPrimary());
        
        
        value = new AutoSizeText("" + windSpeed, Settings.getPrimary());
        label.setTextWidth(150-15);
        value.setTextWidth(150-30);

        hbox.setAlignment(CENTER_LEFT);

        hbox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 5;" + "-fx-border-insets: 0;"
                + "-fx-border-radius: 0;" + "-fx-border-color: " + Settings.colorString(Settings.getTertiary()) + ";");
        hbox.getChildren().addAll(label, divider, value);
        
        hbox.setMinSize(this.getPrefWidth(), this.getPrefHeight());
        hbox.setPrefSize(300,125);
        
        
        this.getChildren().addAll(hbox);
        update();
        
    }

    @Override
    public void update(){
        windSpeed = ((WeatherScene) parent).getWindSpeed();
        if(Settings.isKilometers()){
            value.setText(nf.format(windSpeed)+"Km/h");
        } else {
            value.setText(nf.format(windSpeed)+"MPH");
        }
        value.resizeText();
        value.setFill(Settings.getPrimary());
        hbox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 5;" + "-fx-border-insets: 0;"
                + "-fx-border-radius: 0;" + "-fx-border-color: " + Settings.colorString(Settings.getTertiary()) + ";");
        divider.setStroke(Settings.getFadedPrimary());
        hbox.setBackground(new Background(new BackgroundFill(Settings.getSecondary(), null, null)));
        label.setFill(Settings.getFadedPrimary());
    }

}
