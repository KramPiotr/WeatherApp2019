package tiles;

import util.AutoSizeText;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import settings.Settings;
import skeletons.WeatherScene;

import java.text.NumberFormat;

import static javafx.geometry.Pos.CENTER_LEFT;

public class HighLowTile extends Tile{
    AutoSizeText label;
    double high;
    double low;
    AutoSizeText lowValue;
    AutoSizeText highValue;
    NumberFormat nf;



    public HighLowTile(WeatherScene parent){
        super(parent);

        //Create number format for temperature
        nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(1);
        nf.setMaximumFractionDigits(1);

        //Set prefered size of tile
        this.setPrefSize(300, 125);



        //Set text in label and value
        label = new AutoSizeText("Low/High:", Settings.getFadedPrimary());
        low = parent.getLow();
        high = parent.getHigh();

        if(Settings.isCelsius()){
            lowValue = new AutoSizeText(nf.format(low)+"°C",Settings.getPrimary());
            highValue = new AutoSizeText(nf.format(high)+"°C",Settings.getPrimary());
        }
        else{
            lowValue = new AutoSizeText(nf.format(low*9.0/5.0+32.0)+"°F",Settings.getPrimary());
            highValue = new AutoSizeText(nf.format(high*9.0/5.0+32.0)+"°F",Settings.getPrimary());

        }

        lowValue.setSize(110,40);
        highValue.setSize(100,40);

        //Position label and value
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 0, 10, 10));
        hbox.setSpacing(10);
        hbox.setBackground(new Background(new BackgroundFill(Settings.getSecondary(), null, null)));


        Line divider = new Line();
        divider.setStartX(0);
        divider.setEndX(0);
        divider.setStartY(20);
        divider.setEndY(125);
        divider.setStrokeWidth(5);
        divider.setStroke(Settings.getFadedPrimary());

        label.setTextWidth(150-15);

        hbox.setAlignment(CENTER_LEFT);

        hbox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 5;" + "-fx-border-insets: 0;"
                + "-fx-border-radius: 0;" + "-fx-border-color: " + Settings.colorString(Settings.getTertiary()) + ";");

        VBox values = new VBox();

        Line horizLine = new Line();
        horizLine.setStartX(10);
        horizLine.setEndX(110);
        horizLine.setStartY(0);
        horizLine.setEndY(0);
        horizLine.setStrokeWidth(3);
        horizLine.setStroke(Settings.getFadedPrimary());

        values.getChildren().addAll(lowValue,horizLine,highValue);

        hbox.getChildren().addAll(label, divider, values);

        hbox.setMinSize(this.getPrefWidth(), this.getPrefHeight());
        hbox.setPrefSize(300,125);


        this.getChildren().addAll(hbox);
        update();

    }

    @Override
    public void update(){
        low = ((WeatherScene) parent).getLow();
        high = ((WeatherScene) parent).getHigh();
        if(Settings.isCelsius()){
            lowValue.setText(nf.format(low)+"°C");
            highValue.setText(nf.format(high)+"°C");
        }
        else{
            lowValue.setText(nf.format(low*9.0/5.0+32.0)+"°F");
            highValue.setText(nf.format(high*9.0/5.0+32.0)+"°F");
        }
        lowValue.resizeText();
        highValue.resizeText();
    }
}
