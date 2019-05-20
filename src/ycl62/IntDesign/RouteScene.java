package ycl62.IntDesign;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import settings.BackButton;
import settings.Settings;
import tiles.Tile;
import uk.ac.cam.cl.dgk27.stateful.State;
import uk.ac.cam.cl.dgk27.stateful.StateManager;
import uk.ac.cam.cl.dgk27.stateful.YetAnotherSearch;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class RouteScene extends State {
    
    private ArrayList<RouteCityTile> cities;
    private AddButtonTile addButton;
    private VBox vBox;
    private ScrollPane scrollPane;
    
    public RouteScene(String name){
        super(name);
        cities = new ArrayList<>();
        
        addButton = new AddButtonTile(this);
        addButton.setAction(e -> {
            StateManager.switchTo("Search");
        });
        
        vBox = new VBox();
        vBox.getChildren().addAll(cities);
        vBox.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, null, null)));
        scrollPane = new ScrollPane(vBox);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        VBox buttons = new VBox();
        buttons.getChildren().addAll(addButton, new BackButton(this));
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);

        BorderPane mainPanel = new BorderPane();
        mainPanel.setTop(scrollPane);
        mainPanel.setBottom(buttons);
        mainPanel.setCenterShape(true);
        mainPanel.setPadding(new Insets(10.0, 0, 30.0, 0));
        mainPanel.setBackground(new Background(new BackgroundFill(Settings.getSecondary(), null, null)));
        scene = new Scene(mainPanel);
    }
    
    public void addCity(){//String cityName, LocalDate start, LocalDate end){
        String cityName = "London"; //((YetAnotherSearch) StateManager.get("Search")).getSelected();
        LocalDate start = ((DateRangeScene) StateManager.get("Date")).getStart();
        LocalDate end = ((DateRangeScene) StateManager.get("Date")).getEnd();
        
        cities.add(new RouteCityTile(this, cityName, start, end));
        Collections.sort(cities);
        vBox.getChildren().clear();
        vBox.getChildren().addAll(cities);
        update();
        System.out.println(vBox.getChildren().size());
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
        for(Node node : scrollPane.getChildrenUnmodifiable()){
            System.out.println(node);
            if(node instanceof ScrollBar){
                ScrollBar scrollBar = (ScrollBar) node;
                scrollBar.getChildrenUnmodifiable().forEach(c -> {
                    if(c.toString().startsWith("Sc")){ //so that we filter everything apart from ScrollBarSkin
                        c.setStyle("-fx-background-color: " + Settings.colorString(Settings.getPrimary()));
                    } else {
                        c.setStyle("-fx-background-color: " + Settings.colorString(Settings.getTertiary()));
                    }
                });
            } else {
                node.setStyle("-fx-background-color: " + Settings.colorString(Settings.getSecondary()));
            }
        }
        
        addButton.update();
        cities.forEach(Tile::update);
    }
}
