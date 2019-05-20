package tiles;

import util.AutoSizeText;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import settings.Settings;
import skeletons.WeatherScene;
import util.ImageColorer;

import java.text.NumberFormat;

import static javafx.geometry.Pos.CENTER_LEFT;

public class ChanceOfRainTile extends Tile {
    AutoSizeText label;
    double chanceOfRain;
    AutoSizeText value;
    Group conditionView;
    NumberFormat nf;
    Line divider;
    HBox hbox;

    @Override
    public void update() {
        chanceOfRain = ((WeatherScene) parent).getChanceOfRain();
        value.setText(nf.format(chanceOfRain)+"%");
        setWeatherIcon();
        hbox.getChildren().clear();
        if(Settings.isGraphical()){
            hbox.getChildren().addAll(label,divider,conditionView);
        }
        else{
            hbox.getChildren().addAll(label,divider,value);
        }
        label.setFill(Settings.getFadedPrimary());
        value.setFill(Settings.getPrimary());
        hbox.setBackground(new Background(new BackgroundFill(Settings.getSecondary(),null,null)));
        divider.setStroke(Settings.getFadedPrimary());
        hbox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 5;" + "-fx-border-insets: -1;"
                + "-fx-border-radius: 0;" + "-fx-border-color: "+ Settings.colorString(Settings.getTertiary())+";");
    }

    private void setWeatherIcon(){
        Image rainIcon;
        if(chanceOfRain<1){
            rainIcon = new Image("/icons/chance0.png");
        }
        else if(chanceOfRain<20){
            rainIcon = new Image("/icons/chance1.png");
        }
        else if(chanceOfRain<40){
            rainIcon = new Image("/icons/chance2.png");
        }
        else if(chanceOfRain<60){
            rainIcon = new Image("/icons/chance3.png");
        }
        else if(chanceOfRain<80){
            rainIcon = new Image("/icons/chance4.png");
        }
        else{
            rainIcon = new Image("/icons/chance5.png");
        }

        ImageView iconView = new ImageView(rainIcon);
        iconView.setPreserveRatio(true);
        iconView.setFitWidth(110);
        conditionView = ImageColorer.recolor(iconView,Settings.getPrimary());

    }

    public ChanceOfRainTile(WeatherScene parent){
        super(parent);

        //Create number format for temperature
        nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(1);
        nf.setMaximumFractionDigits(1);

        //Set prefered size of tile
        this.setPrefSize(300,125);
        this.setMinSize(300,125);


        //Set text in label and value
        label = new AutoSizeText("Chance of Rain:",Settings.getFadedPrimary());
        value = new AutoSizeText(chanceOfRain+"%",Settings.getPrimary());

        //Position label and value
        hbox = new HBox();
        hbox.setPrefSize(300,125);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);



        divider = new Line();
        divider.setStartX(0);
        divider.setEndX(0);
        divider.setStartY(20);
        divider.setEndY(125);
        divider.setStrokeWidth(5);

        label.setTextWidth(150-25);
        value.setTextWidth(120-40);

        hbox.setAlignment(CENTER_LEFT);


        //hbox.setBorder(new Border(new BorderStroke(Settings.getTertiary(),BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));

        chanceOfRain = ((WeatherScene) parent).getChanceOfRain();
        setWeatherIcon();

        hbox.setMinSize(this.getPrefWidth(),this.getPrefHeight());

        this.getChildren().addAll(hbox);
        update();
    }
}
