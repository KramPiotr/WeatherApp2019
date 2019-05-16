package settings;

import items.AutoSizeText;
import org.controlsfx.control.ToggleSwitch;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import tiles.Tile;
import uk.ac.cam.cl.dgk27.stateful.State;

import static javafx.geometry.Pos.CENTER_LEFT;

public class SwitchTile extends Tile {
    AutoSizeText label;
    AutoSizeText value;
    ToggleSwitch tSwitch;

    @Override
    public void update() {

    }

    public SwitchTile(State parent, String label, String defaultValue){
        super(parent);
        update();

        //Set prefered size of tile
        this.setPrefSize(600,100);

        //Set text in label and value
        this.label = new AutoSizeText(label,Settings.getPrimary());
        value = new AutoSizeText(defaultValue, Settings.getPrimary());
        this.tSwitch = new ToggleSwitch();

        //Position label and value
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setBackground(new Background(new BackgroundFill(Settings.getSecondary(),null,null)));
        grid.minHeight(150);
        grid.minWidth(300);

        this.label.setTextWidth(150);
        this.value.setTextHeight(50);
        grid.setAlignment(CENTER_LEFT);
        grid.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 5;" + "-fx-border-insets: 0;"
                + "-fx-border-radius: 0;" + "-fx-border-color: "+ Settings.colorString(Settings.getTertiary())+";");

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(80);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(10);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(10);
        grid.getColumnConstraints().addAll(column1, column2, column3);
        grid.add(this.label, 0, 0);
        grid.add(value, 1, 0);
        grid.add(tSwitch, 2, 0);
        grid.setMinSize(this.getPrefWidth(),this.getPrefHeight());


        this.getChildren().addAll(grid);
    }

}
