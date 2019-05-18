package settings;

import items.AutoSizeText;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import skeletons.Panel;
import settings.Settings;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import tiles.Tile;
import uk.ac.cam.cl.dgk27.stateful.State;

import java.text.NumberFormat;
import java.util.List;

import static javafx.geometry.Pos.CENTER_LEFT;

public class ColorTile extends Tile {
    AutoSizeText label;
    ColorPicker colorPicker;
    Circle circle;
    Button defaultButton;
    Color defaultColor;
    GridPane grid;

    @Override
    public void update() {
        label.setFill(Settings.getPrimary());
        circle.setStroke(Settings.getFadedPrimary());
        grid.setBackground(new Background(new BackgroundFill(Settings.getSecondary(),null,null)));
        grid.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 3;" + "-fx-border-insets: 0;"
                + "-fx-border-radius: 0;" + "-fx-border-color: "+ Settings.colorString(Settings.getTertiary())+";");
        defaultButton.setStyle("-fx-background-color: "+Settings.colorString(Settings.getPrimary())+";"+
                                "-fx-text-fill: "+Settings.colorString(Settings.getSecondary()));

    }

    public ColorTile(State parent, String label){
        super(parent);

        //Set prefered size of tile
        this.setPrefSize(600,100);

        //Set text in label and value
        this.label = new AutoSizeText(label,Settings.getPrimary());
        this.colorPicker = new ColorPicker();
        this.circle = new Circle(25);
        circle.setStrokeWidth(2);
        circle.setStroke(Settings.getFadedPrimary());
        //Position label and value
        grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setBackground(new Background(new BackgroundFill(Settings.getSecondary(),null,null)));
        grid.minHeight(150);
        grid.minWidth(300);

        this.label.setStyle("-fx-font-size: 35");
        grid.setAlignment(CENTER_LEFT);
        grid.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 3;" + "-fx-border-insets: 0;"
                + "-fx-border-radius: 0;" + "-fx-border-color: "+ Settings.colorString(Settings.getTertiary())+";");

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(40);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(20);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(10);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(30);
        defaultButton = new Button();
        defaultButton.setText("Set default");
        defaultButton.setStyle("-fx-background-color: "+Settings.colorString(Settings.getPrimary())+";");
        defaultButton.setFont(Font.font(Settings.getFontString(), FontWeight.BOLD, 15));
        grid.getColumnConstraints().addAll(column1, column2, column3, column4);
        grid.add(this.label, 0, 0);
        grid.add(defaultButton, 1, 0);
        grid.add(this.circle, 2, 0);
        grid.add(this.colorPicker, 3, 0);
        grid.setMinSize(this.getPrefWidth(),this.getPrefHeight());


        this.getChildren().addAll(grid);
    }

}
