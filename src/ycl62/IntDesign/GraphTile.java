package ycl62.IntDesign;

import eu.hansolo.tilesfx.Tile.*;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.TilesFXSeries;
import javafx.scene.Node;
import javafx.scene.chart.XYChart.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import skeletons.Settings;
import tiles.Tile;

import java.time.LocalDateTime;

public class GraphTile extends Tile {
    private eu.hansolo.tilesfx.Tile chart;
    private final double prefWidth = 600.0, prefHeight = 150.0;
    
    public GraphTile(ForecastPanel parent){
        super(parent);
        
        //TODO: shift to update below, merge/fix panel implementations
        double[] data = parent.get5DayTemp();
        String[] timestamp = parent.get5DayTimestamp();
        Series<Node, Double> lows = new Series();
        Series<Node, Double> highs = new Series();
        int offset = LocalDateTime.parse(timestamp[0].replace(" ", "T")).getHour() / 3;
        int N;
        //System.out.println(data.length);
        //System.out.println(offset);
        
        //Obtain temps and day-of-week
        for(int i = 0; i < data.length; i += N){
            double low = data[i];
            double high = data[i];
            for(N = 1; (i + N + offset) % 8 != 0 && i + N < data.length; N++){
                low = Math.min(data[i + N], low);
                high = Math.max(data[i + N], high);
            }
            
            LocalDateTime d = LocalDateTime.parse(timestamp[i].replace(" ", "T"));
            lows.getData().add(new Data(d.getDayOfWeek().name().substring(0, 3), low));
            highs.getData().add(new Data(d.getDayOfWeek().name().substring(0, 3), high));
            //System.out.println(i + ": " + low + " -- " + high + " || " + d.getDayOfWeek().name().substring(0, 3));
        }
        
        chart = TileBuilder.create()
                .skinType(SkinType.SMOOTHED_CHART)
                .prefSize(prefWidth, prefHeight)
                .title("Temp Highs/Lows")
                .chartType(ChartType.AREA)
                .smoothing(true)
                .dataPointsVisible(true)
                .snapToTicks(true)
                .textSize(TextSize.BIGGER)
                .decimals(1)
                .tilesFxSeries(
                        new TilesFXSeries(highs, Settings.getFadedPrimary(), new LinearGradient(0.0, 0.0, 0.0, 1.0, true, CycleMethod.NO_CYCLE, new Stop(0.0, Settings.getFadedPrimary()), new Stop(0.7, Color.TRANSPARENT))),
                        new TilesFXSeries(lows, Settings.getPrimary(), new LinearGradient(0.0, 0.0, 0.0, 1.0, true, CycleMethod.NO_CYCLE, new Stop(0.0, Settings.getPrimary()), new Stop(0.6, Color.TRANSPARENT))))
                .backgroundColor(Settings.getSecondary())
                .tooltipTimeout(1000.0)
                .build();
        
        this.getChildren().add(chart);
        //TODO: shift to update below
        
        update();
    }
    
    @Override
    public void update(){
    
    }
}
