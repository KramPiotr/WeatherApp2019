package ycl62.IntDesign;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.fonts.Fonts;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import skeletons.Settings;
import uk.ac.cam.cl.dgk27.stateful.State;

import java.util.ArrayList;
import java.util.Collections;

public class RouteScene extends State {
    
    private final double WIDTH = 600, HEIGHT = 800;
    private StackPane mainPanel;
    private ArrayList<Tile> cities;
    
    private EventHandler<MouseEvent> mouseEventHandler;
    private Label addButtonLabel;
    private double addButtonWidth = 200.0, addButtonHeight = 50.0;
    
    public RouteScene(String name, Tile... tiles){
        super(name);
        cities = new ArrayList<>();
        Collections.addAll(cities, tiles);
        init();
    }
    
    private void init(){
        mouseEventHandler = (e) -> {
            EventType TYPE = e.getEventType();
            Label SRC = (Label) e.getSource();
            if(MouseEvent.MOUSE_PRESSED == TYPE){
                if(SRC.equals(addButtonLabel)){
                    //addCity();
                    System.out.println("HERE");
                    addButtonLabel.setTextFill(Settings.getPrimary());
                    addButtonLabel.setBorder(new Border(new BorderStroke(Settings.getPrimary(), BorderStrokeStyle.SOLID, new CornerRadii(addButtonHeight), new BorderWidths(addButtonHeight * 0.05))));
                }
            } else if(MouseEvent.MOUSE_RELEASED == TYPE){
                if(SRC.equals(addButtonLabel)){
                    addButtonLabel.setTextFill(Settings.getFadedPrimary());
                    addButtonLabel.setBorder(new Border(new BorderStroke(Settings.getFadedPrimary(), BorderStrokeStyle.SOLID, new CornerRadii(addButtonHeight), new BorderWidths(addButtonHeight * 0.05))));
                }
            }
        };
        addButtonLabel = new Label("+");
        addButtonLabel.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEventHandler);
        addButtonLabel.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEventHandler);
        addButtonLabel.setTextFill(Settings.getFadedPrimary());
        addButtonLabel.setBorder(new Border(new BorderStroke(Settings.getFadedPrimary(), BorderStrokeStyle.SOLID, new CornerRadii(addButtonHeight), new BorderWidths(addButtonHeight * 0.05))));
        addButtonLabel.setAlignment(Pos.CENTER);
        addButtonLabel.setPickOnBounds(false);
        addButtonLabel.setPrefSize(addButtonWidth, addButtonHeight);
        addButtonLabel.setFont(Fonts.latoBold(addButtonHeight));
        addButtonLabel.setPadding(new Insets(-addButtonHeight, 0, -addButtonHeight, 0));
        
        update();
    }
    
    @Override
    protected void initialise(){
    
    }
    
    @Override
    protected void enable(){
    
    }
    
    @Override
    protected void disable(){
    
    }
    
    @Override
    public void update(){
        
        //resize
//        this.addButtonLabel.setPadding(new Insets(-0.05D * this.addButtonSize, 0.0D, 0.0D, 0.0D));
        
        mainPanel = new StackPane(addButtonLabel);
        mainPanel.setCenterShape(true);
        mainPanel.setPadding(new Insets(15.0));
        StackPane.setAlignment(addButtonLabel, Pos.BOTTOM_CENTER);
        mainPanel.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, null, null)));
        
        scene = new Scene(mainPanel, WIDTH, HEIGHT);
    }
    
    private void increment(){
//        this.addButtonLabel.setTextFill(this.tile.getActiveColor());
//        this.addButtonLabel.setBorder(new Border(new BorderStroke[]{new BorderStroke(this.tile.getActiveColor(), BorderStrokeStyle.SOLID, new CornerRadii(1024.0D), new BorderWidths(this.addButtonSize * 0.01D))}));
//        double newValue = Helper.clamp(this.minValue, this.maxValue, this.tile.getValue() + this.tile.getIncrement());
//        this.tile.setValue(newValue);
    }
}
