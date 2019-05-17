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
import settings.Settings;
import skeletons.WeatherScene;
import tiles.Tile;
import uk.ac.cam.cl.dgk27.weather.RequestType;
import uk.ac.cam.cl.dgk27.weather.Weather;
import uk.ac.cam.cl.dgk27.weather.WeatherAPI;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Forecast graph tile. Shows the temperature highs and lows for 5-6 days starting today.
 */
public class GraphTile extends Tile {
    private final double WIDTH = 600.0, HEIGHT = 200.0;
    private eu.hansolo.tilesfx.Tile chart;
    private String cityName;
    private double[] data = {-273.15}; //Placeholder value
    private String[] timestamp = {"2012 AD"}; //Placeholder value
    
    public GraphTile(WeatherScene parent){
        super(parent);
        cityName = "Cambridge";
        update();
    }
    
    public GraphTile(WeatherScene parent, String cityName){
        super(parent);
        this.cityName = cityName;
        update();
    }
    
    @Override
    public void update(){
        try{
            Weather[] weather = WeatherAPI.makeRequest(RequestType.FiveDay, cityName);
            data = Arrays.stream(weather).mapToDouble(Weather::getTemp).toArray();
            timestamp = Arrays.stream(weather).map(Weather::getDatetime).toArray(String[]::new);
        } catch(IOException e) {
            e.printStackTrace();
            System.out.println();
            System.out.println("Location (" + cityName + ") not found in weather service.");
        }
        
        Series<Node, Double> lows = new Series();
        Series<Node, Double> highs = new Series();
        int offset;
        try{
            offset = LocalDateTime.parse(timestamp[0].replace(" ", "T")).getHour() / 3;
        } catch(DateTimeParseException e) {
            offset = 0;
        }
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
                .prefSize(WIDTH, HEIGHT)
                .title("Temp Highs/Lows")
                .chartType(ChartType.AREA)
                .smoothing(true)
                .dataPointsVisible(true)
                .snapToTicks(true)
                .textSize(TextSize.BIGGER)
                .decimals(1)
                .tilesFxSeries(
                        new TilesFXSeries(lows, Settings.getPrimary(), new LinearGradient(0.0, 0.0, 0.0, 1.0, true, CycleMethod.NO_CYCLE, new Stop(0.0, Settings.getPrimary()), new Stop(0.6, Color.TRANSPARENT))),
                        new TilesFXSeries(highs, Settings.getFadedPrimary(), new LinearGradient(0.0, 0.0, 0.0, 1.0, true, CycleMethod.NO_CYCLE, new Stop(0.0, Settings.getFadedPrimary()), new Stop(0.7, Color.TRANSPARENT))))
                .backgroundColor(Settings.getSecondary())
                .tooltipTimeout(1000.0)
                .build();
        
        this.getChildren().add(chart);
    }
}
