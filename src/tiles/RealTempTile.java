package tiles;

import Skeletons.Panel;
import Skeletons.Settings;
import Skeletons.Tile;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.text.NumberFormat;

public class RealTempTile extends Tile {
    Text label;
    float realTemperature;
    Text value;
    NumberFormat nf;

    @Override
    public void update() {
        this.realTemperature = parent.getRealTemperature();
    }

    public RealTempTile(Panel parent){
        super(parent);
        update();

        //Create number format for temperature
        nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(1);
        nf.setMaximumFractionDigits(1);

        //Set prefered size of tile
        this.setPrefSize(300,150);

        //Set text in label and value
        label = new Text("Humidity:");
        if(Settings.isCelcius()){
            value = new Text(nf.format(realTemperature)+"°C");
        }
        else{
            value = new Text(nf.format(realTemperature*9.0/5.0+32.0)+"°F");
        }

        //Position label and value
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);
        hbox.setBackground(new Background(new BackgroundFill(Settings.getSecondary(),null,null)));

        Line divider = new Line();
        divider.setStartX(200);
        divider.setEndX(200);
        divider.setStartY(0);
        divider.setEndY(150);


        label.prefWidth(200);
        value.prefWidth(100);
        label.prefHeight(150);
        hbox.getChildren().addAll(label,value,divider);

        this.getChildren().add(hbox);
    }

}
