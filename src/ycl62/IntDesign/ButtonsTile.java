package ycl62.IntDesign;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import settings.Settings;
import tiles.Tile;
import uk.ac.cam.cl.dgk27.stateful.State;

public class ButtonsTile extends Tile {
    
    private Button addButton, backButton;
    private final double buttonWidth = 200.0, buttonHeight = 50.0;
    
    public ButtonsTile(State parent){
        super(parent);
        
        addButton = new Button("+");
        addButton.setPrefSize(buttonWidth, buttonHeight);
        addButton.setAlignment(Pos.CENTER);
        addButton.setFont(Font.font(Settings.getFontString(), FontWeight.BOLD, buttonHeight));
        addButton.setPadding(new Insets(-buttonHeight, 0, -buttonHeight, 0));
        addButton.setOnMousePressed(e -> addButton.setBackground(new Background(new BackgroundFill(Settings.getFadedPrimary(), null, null))));
        addButton.setOnMouseReleased(e -> update());
        
        backButton = new Button("Back");
        backButton.setPrefSize(buttonWidth, buttonHeight);
        backButton.setAlignment(Pos.CENTER);
        backButton.setFont(Font.font(Settings.getFontString(), FontWeight.BOLD, buttonHeight));
        backButton.setPadding(new Insets(-buttonHeight, 0, -buttonHeight, 0));
        backButton.setOnMousePressed(e -> addButton.setBackground(new Background(new BackgroundFill(Settings.getFadedPrimary(), null, null))));
        backButton.setOnMouseReleased(e -> update());
        
        GridPane gridPane = new GridPane();
        gridPane.addRow(0, backButton, addButton);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.maxHeight(buttonHeight);
        gridPane.setHgap(50);
//        System.out.println(buttonHeight + "; " + hBox.getHeight() + ", " + hBox.getWidth());
        this.setMaxSize(buttonWidth * 2 + 50, buttonHeight);
        this.getChildren().add(gridPane);
    }
    
    void setAddAction(EventHandler<ActionEvent> e){
        addButton.setOnAction(e);
    }
    
    void setBackAction(EventHandler<ActionEvent> e){
        backButton.setOnAction(e);
    }
    
    @Override
    public void update(){
        addButton.setTextFill(Settings.getSecondary());
        addButton.setBackground(new Background(new BackgroundFill(Settings.getPrimary(), null, null)));
        addButton.setBorder(new Border(new BorderStroke(Settings.getTertiary(), BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(3))));
        
        backButton.setTextFill(Settings.getSecondary());
        backButton.setBackground(new Background(new BackgroundFill(Settings.getPrimary(), null, null)));
        backButton.setBorder(new Border(new BorderStroke(Settings.getTertiary(), BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(3))));
    }
}
