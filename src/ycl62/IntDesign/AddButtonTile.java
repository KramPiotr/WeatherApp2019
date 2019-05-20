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

public class AddButtonTile extends Tile {
    
    private Button addButton;
    private final double addButtonWidth = 200.0, addButtonHeight = 50.0;
    
    public AddButtonTile(State parent){
        super(parent);
        
        addButton = new Button("+");
        addButton.setPrefSize(addButtonWidth, addButtonHeight);
        addButton.setAlignment(Pos.CENTER);
        addButton.setFont(Font.font(Settings.getFontString(), FontWeight.BOLD, addButtonHeight));
        addButton.setPadding(new Insets(-addButtonHeight, 0, -addButtonHeight, 0));
        addButton.setOnMousePressed(e -> addButton.setBackground(new Background(new BackgroundFill(Settings.getFadedPrimary(), null, null))));
        addButton.setOnMouseReleased(e -> update());
        
        this.setMaxSize(addButtonWidth, addButtonHeight);
        this.getChildren().add(addButton);
    }
    
    void setAction(EventHandler<ActionEvent> e){
        addButton.setOnAction(e);
    }
    
    @Override
    public void update(){
        addButton.setTextFill(Settings.getSecondary());
        addButton.setBackground(new Background(new BackgroundFill(Settings.getPrimary(), null, null)));
        addButton.setBorder(new Border(new BorderStroke(Settings.getTertiary(), BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(3))));
    }
}
