package ycl62.IntDesign;

import eu.hansolo.tilesfx.Tile.*;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;
import items.AutoSizeText;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import skeletons.Settings;
import tiles.Tile;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GraphTile extends Tile {
    private eu.hansolo.tilesfx.Tile lowPlot;
    private eu.hansolo.tilesfx.Tile highPlot;
    private final double prefWidth = 600.0, prefHeight = 150;
    
    public GraphTile(ForecastPanel parent){
        super(parent);
        
        //TODO: shift to update below, merge/fix panel implementations
        double[] data = parent.get5DayTemp();
        String[] timestamp = parent.get5DayTimestamp();
        ArrayList<ChartData> lowPoints = new ArrayList<>();
        ArrayList<ChartData> highPoints = new ArrayList<>();
        ArrayList<AutoSizeText> tickLabels = new ArrayList<>();
        int offset = LocalDateTime.parse(timestamp[0].replace(" ", "T")).getHour() / 3;
        //System.out.println(data.length);
        //System.out.println(offset);
        int N;
        
        //Obtain temps and day-of-week
        for(int i = 0; i < data.length; i += N){
            double low = data[i];
            double high = data[i];
            for(N = 1; (i + N + offset) % 8 != 0 && i + N < data.length; N++){
                low = Math.min(data[i + N], low);
                high = Math.max(data[i + N], high);
            }
            
            lowPoints.add(new ChartData(low));
            highPoints.add(new ChartData(high));
            
            LocalDateTime d = LocalDateTime.parse(timestamp[i].replace(" ", "T"));
            tickLabels.add(new AutoSizeText(d.getDayOfWeek().name().substring(0, 3), Settings.getPrimary()));
            //System.out.println(i + ": " + low + " -- " + high + " || " + d.getDayOfWeek().name().substring(0, 3));
        }
        
        highPlot = TileBuilder.create()
                .skinType(SkinType.SMOOTH_AREA_CHART)
                .valueVisible(false)
                .dataPointsVisible(true)
                .snapToTicks(true)
                .decimals(1)
                .chartData(highPoints)
                .barColor(Settings.getFadedPrimary())
                .backgroundColor(Color.TRANSPARENT)
                .animated(false)
                .build();
        VBox plot1 = new VBox();
        Rectangle clip = new Rectangle(0, prefHeight * 0.1, prefWidth, prefHeight * 0.5);
        plot1.setClip(clip);
        plot1.setPadding(new Insets(-prefHeight * 0.3, 10.0, prefHeight * 0.3, 10.0));
        plot1.getChildren().addAll(highPlot);
        
        lowPlot = TileBuilder.create()
                .skinType(SkinType.SMOOTH_AREA_CHART)
                .valueVisible(false)
                .dataPointsVisible(true)
                .snapToTicks(true)
                .decimals(1)
                .chartData(lowPoints)
                .barColor(Settings.getPrimary())
                .backgroundColor(Color.TRANSPARENT)
                .animated(false)
                .build();
        VBox plot2 = new VBox();
        clip = new Rectangle(0, prefHeight * 0.4, prefWidth, prefHeight);
        plot2.setClip(clip);
        plot2.setPadding(new Insets(prefHeight * 0.1, 10.0, -prefHeight * 0.1, 10.0));
        plot2.getChildren().addAll(lowPlot);
        
        HBox axisBox = new HBox();
        axisBox.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT.interpolate(Settings.getSecondary(), 0.5), null, null)));
        axisBox.setAlignment(Pos.CENTER);
        for(int i = 0; i < tickLabels.size(); i++){
            if(i != 0){
                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);
                axisBox.getChildren().add(spacer);
            }
            Node tick = tickLabels.get(i);
            axisBox.getChildren().add(tick);
        }
        
        //Stack plots together
        StackPane chart = new StackPane(plot1, plot2);
        chart.maxHeight(prefHeight);
        
        VBox mainBox = new VBox();
        mainBox.setPadding(new Insets(0, 10.0, 5.0, 10.0));
        mainBox.setPrefSize(prefWidth, prefHeight);
        mainBox.setBackground(new Background(new BackgroundFill(Settings.getSecondary(), null, null)));
        mainBox.setAlignment(Pos.TOP_CENTER);
        mainBox.getChildren().addAll(chart, axisBox);
        this.getChildren().add(mainBox);
        //TODO: shift to update below
        
        update();
    }
    
    @Override
    public void update(){
    
    }
}
