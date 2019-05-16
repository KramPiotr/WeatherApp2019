package tiles;

import items.AutoSizeText;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Circle;
import skeletons.Panel;
import skeletons.Settings;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;

import java.text.NumberFormat;
import java.util.List;

import static javafx.geometry.Pos.CENTER_LEFT;

public class ColorTile extends Tile {
    AutoSizeText label;
    ColorPicker colorPicker;
    Circle circle;

    @Override
    public void update() {

    }

    public ColorTile(Panel parent, String label){
        super(parent);
        update();

        //Set prefered size of tile
        this.setPrefSize(600,100);

        //Set text in label and value
        this.label = new AutoSizeText(label,Settings.getPrimary());
        this.colorPicker = new ColorPicker();
        this.circle = new Circle(25);

        //Position label and value
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setBackground(new Background(new BackgroundFill(Settings.getSecondary(),null,null)));
        grid.minHeight(150);
        grid.minWidth(300);

        this.label.setTextWidth(150);
        grid.setAlignment(CENTER_LEFT);
        grid.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 5;" + "-fx-border-insets: 0;"
                + "-fx-border-radius: 0;" + "-fx-border-color: "+ Settings.colorString(Settings.getTertiary())+";");

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(60);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(10);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(30);
        grid.getColumnConstraints().addAll(column1, column2, column3);
        grid.add(this.label, 0, 0);
        grid.add(this.circle, 1, 0);
        grid.add(this.colorPicker, 2, 0);
        grid.setMinSize(this.getPrefWidth(),this.getPrefHeight());


        this.getChildren().addAll(grid);
    }

}
