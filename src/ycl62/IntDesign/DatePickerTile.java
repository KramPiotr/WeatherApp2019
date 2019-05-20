package ycl62.IntDesign;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import settings.Settings;
import tiles.Tile;
import uk.ac.cam.cl.dgk27.stateful.State;
import util.AutoSizeText;

import java.time.LocalDate;

public class DatePickerTile extends Tile {
    private DatePicker picker;
    private AutoSizeText label;
    private GridPane gridPane;
    
    public DatePickerTile(State parent, String label, LocalDate defaultValue){
        super(parent);
        
        this.label = new AutoSizeText(label, Settings.getPrimary());
        this.label.setFont(Font.font(Settings.getFontString(), 35));
        picker = new DatePicker(defaultValue);
        picker.setScaleX(1.5);
        picker.setScaleY(1.5);
        
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(15.0);
        gridPane.setPrefSize(300, 550);
        gridPane.add(this.label, 0, 0);
        gridPane.add(picker, 0, 1);
        this.getChildren().add(gridPane);
    }
    
    @Override
    public void update(){
        label.setFill(Settings.getPrimary());
        gridPane.setBackground(new Background(new BackgroundFill(Settings.getSecondary(), null, null)));
    }
    
    public LocalDate getDate(){
        return picker.getValue();
    }
}
