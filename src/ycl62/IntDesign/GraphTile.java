package ycl62.IntDesign;

import eu.hansolo.tilesfx.Tile.*;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.TilesFXSeries;
import javafx.scene.Node;
import javafx.scene.chart.XYChart.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import settings.Settings;
import skeletons.WeatherScene;
import tiles.Tile;
import uk.ac.cam.cl.dgk27.stateful.State;
import uk.ac.cam.cl.dgk27.weather.RequestType;
import uk.ac.cam.cl.dgk27.weather.Weather;
import uk.ac.cam.cl.dgk27.weather.WeatherAPI;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Forecast graph tile. Shows the temperature highs and lows for 5-6 days starting today.
 */
public class GraphTile extends Tile {
    private double WIDTH = 588.0, HEIGHT = 200.0;
    private eu.hansolo.tilesfx.Tile chart;
    String cityName;
    
    Series<Node, Double> lows;
    Series<Node, Double> highs;
    String title;
    
    public GraphTile(State parent, String cityName){
        super(parent);
        this.cityName = cityName;
        title = "Temp Highs/Lows";
    }
    
    void getData(){
        double[] temps;
        String[] dates;
        try{
            Weather[] weather = WeatherAPI.makeRequest(RequestType.FiveDay, cityName);
            temps = Arrays.stream(weather).mapToDouble(Weather::getTemp).toArray();
            dates = Arrays.stream(weather).map(Weather::getDatetime).toArray(String[]::new);
            Arrays.stream(weather).forEach(w -> System.out.println(w.getDatetime() + ": " + w.getTemp() + " || " + w.getHumidity() + " || " + w.getTemp_max()));
        } catch(IOException e) {
            e.printStackTrace();
            System.out.println();
            System.out.println("Location (" + cityName + ") not found in weather service.");
            temps = new double[]{-273.15}; //Placeholder value
            dates = new String[]{"2012AD"}; //Placeholder value
        }
        
        int offset;
        try{
            offset = LocalDateTime.parse(dates[0].replace(" ", "T")).getHour() / 3;
        } catch(DateTimeParseException e) {
            offset = 0;
        }
        //System.out.println(temps.length);
        //System.out.println(offset);
        
        //Obtain temps and day-of-week
        int N;
        for(int i = 0; i < temps.length; i += N){
            double low = temps[i];
            double high = temps[i];
            for(N = 1; (i + N + offset) % 8 != 0 && i + N < temps.length; N++){
                low = Math.min(temps[i + N], low);
                high = Math.max(temps[i + N], high);
            }
            
            LocalDateTime d = LocalDateTime.parse(dates[i].replace(" ", "T"));
            lows.getData().add(new Data(d.getDayOfWeek().name().substring(0, 3), low));
            highs.getData().add(new Data(d.getDayOfWeek().name().substring(0, 3), high));
            //System.out.println(i + ": " + low + " -- " + high + " || " + d.getDayOfWeek().name().substring(0, 3));
        }
    }
    
    @Override
    public void update(){
        lows = new Series<>();
        highs = new Series<>();
        getData();
        chart = TileBuilder.create()
                .chartType(ChartType.AREA)
                .skinType(SkinType.SMOOTHED_CHART)
                .prefSize(WIDTH, HEIGHT)
                .title(title)
                .tilesFxSeries(
                        new TilesFXSeries(lows, Settings.getPrimary(), new LinearGradient(0.0, 0.0, 0.0, 1.0, true, CycleMethod.NO_CYCLE, new Stop(0.0, Settings.getPrimary()), new Stop(0.6, Color.TRANSPARENT))),
                        new TilesFXSeries(highs, Settings.getFadedPrimary(), new LinearGradient(0.0, 0.0, 0.0, 1.0, true, CycleMethod.NO_CYCLE, new Stop(0.0, Settings.getFadedPrimary()), new Stop(0.7, Color.TRANSPARENT))))
                .backgroundColor(Settings.getSecondary())
                .foregroundBaseColor(Settings.getFadedPrimary())
                .smoothing(true)
                .dataPointsVisible(true)
                .snapToTicks(true)
                .textSize(TextSize.BIGGER)
                .decimals(1)
                .tooltipTimeout(1000.0)
                .build();

//        CategoryAxis xAxis = new CategoryAxis();
//        NumberAxis yAxis = new NumberAxis(5,30,5);
//        AreaChart chart2 = new AreaChart(xAxis, yAxis);
//        chart2.getData().addAll(lows, highs);
//        chart2.setPrefSize(WIDTH, HEIGHT);
//        chart2.setStyle("-fx-text-fill: "+Settings.colorString(Settings.getPrimary()));
        
        FlowPane pane = new FlowPane(chart);
        pane.setStyle("-fx-padding: -1;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 5;" + "-fx-border-color: " + Settings.colorString(Settings.getTertiary()) + ";");
        this.getChildren().add(pane);
    }
    
    public void setWidth(double width){
        WIDTH = width;
    }
}
