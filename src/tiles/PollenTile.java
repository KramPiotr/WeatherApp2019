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
import util.Pollen;

import static javafx.geometry.Pos.CENTER_LEFT;

public class PollenTile extends Tile {
    AutoSizeText label;
    Pollen pollen;
    AutoSizeText value;
    Group conditionView;
    Line divider;
    HBox hbox;

    @Override
    public void update() {
        pollen = ((WeatherScene) parent).getPollen();
        value.setText(pollen.toString());
        hbox.getChildren().clear();
        setPollenIcon();
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
        value.resizeText();
    }


    private void setPollenIcon(){
        Image pollenLevel;

        switch (pollen){
            case LOW:
                pollenLevel = new Image("/icons/pollenL.png");
                break;
            case MEDIUM:
                pollenLevel = new Image("/icons/pollenM.png");
                break;
            case HIGH:
                pollenLevel = new Image("/icons/pollenH.png");
                break;
            default:
                pollenLevel = new Image("/icons/pollenN.png");
        }

        ImageView iconView = new ImageView(pollenLevel);
        iconView.setPreserveRatio(true);
        iconView.setFitWidth(110);
        conditionView = ImageColorer.recolor(iconView,Settings.getPrimary());
    }
    public PollenTile(WeatherScene parent){
        super(parent);


        //Set prefered size of tile
        this.setPrefSize(300,125);
        this.setMinSize(300,125);


        pollen = ((WeatherScene) parent).getPollen();

        //Set text in label and value
        label = new AutoSizeText("Pollen Levels:",Settings.getFadedPrimary());
        value = new AutoSizeText(pollen.toString(),Settings.getPrimary());

        //Position label and value
        hbox = new HBox();
        hbox.setPrefSize(300,125);
        hbox.setPadding(new Insets(10, 0, 10, 10));
        hbox.setSpacing(10);



        divider = new Line();
        divider.setStartX(0);
        divider.setEndX(0);
        divider.setStartY(20);
        divider.setEndY(125);
        divider.setStrokeWidth(5);

        label.setTextWidth(150-15);
        value.setTextWidth(150-40);

        hbox.setAlignment(CENTER_LEFT);


        setPollenIcon();

        hbox.setMinSize(this.getPrefWidth(),this.getPrefHeight());

        this.getChildren().addAll(hbox);
        update();
    }
}
