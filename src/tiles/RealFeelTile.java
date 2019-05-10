package tiles;

import items.AutoSizeText;
import skeletons.Panel;
import skeletons.Settings;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;

import java.text.NumberFormat;

import static javafx.geometry.Pos.CENTER_LEFT;

public class RealFeelTile extends Tile {
    AutoSizeText label;
    double realTemperature;
    AutoSizeText value;
    NumberFormat nf;

    @Override
    public void update() {
        realTemperature = parent.getRealTemperature();
    }

    public RealFeelTile(Panel parent){
        super(parent);
        update();

        //Create number format for temperature
        nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(1);
        nf.setMaximumFractionDigits(1);

        //Set prefered size of tile
        this.setPrefSize(300,150);

        //Set text in label and value
        label = new AutoSizeText("Real Feel:",Settings.getFadedPrimary());
        if(Settings.isCelcius()){
            value = new AutoSizeText(nf.format(realTemperature)+"°C",Settings.getPrimary());
        }
        else{
            value = new AutoSizeText(nf.format(realTemperature*9.0/5.0+32.0)+"°F",Settings.getPrimary());
        }

        //Position label and value
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);
        hbox.setBackground(new Background(new BackgroundFill(Settings.getSecondary(),null,null)));
        hbox.minHeight(150);
        hbox.minWidth(300);

        Line divider = new Line();
        divider.setStartX(0);
        divider.setEndX(0);
        divider.setStartY(0);
        divider.setEndY(150);
        divider.setStrokeWidth(5);
        divider.setStroke(Settings.getPrimary());



        label.setTextWidth(150);
        value.setTextWidth(150);
        hbox.setAlignment(CENTER_LEFT);

        //hbox.setBorder(new Border(new BorderStroke(Settings.getTertiary(),BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        hbox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 5;" + "-fx-border-insets: 0;"
                + "-fx-border-radius: 0;" + "-fx-border-color: "+ Settings.colorString(Settings.getTertiary())+";");
        hbox.getChildren().addAll(label,divider,value);
        hbox.setMinSize(this.getPrefWidth(),this.getPrefHeight());


        this.getChildren().addAll(hbox);
    }

}
