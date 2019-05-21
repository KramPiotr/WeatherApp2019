package ycl62.IntDesign;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import settings.Settings;
import tiles.Tile;
import uk.ac.cam.cl.dgk27.weather.RequestType;
import uk.ac.cam.cl.dgk27.weather.Weather;
import uk.ac.cam.cl.dgk27.weather.WeatherAPI;
import util.AutoSizeText;

import java.io.IOException;
import java.text.NumberFormat;

public class RouteSingleTile extends Tile {
    private double WIDTH = 600, HEIGHT = 150.0;
    
    private Weather weather;
    
    private static int headerSize = 40;
    private double temperature;
    private String iconCode;
    private String city;
    private AutoSizeText cityLabel;
    private String date;
    private AutoSizeText dateLabel;
    private AutoSizeText temperatureLabel;
    private Image conditionImage;
    private ImageView conditionView;
    private NumberFormat nf;

    private HBox cityPaddingRight;
    
    private BorderPane pane;

    public RouteSingleTile(RouteScene parent, String cityName, String date) {
        super(parent);
        city = cityName;
        this.date = date;
        try{
            weather = WeatherAPI.makeRequest(RequestType.Current, cityName)[0];
        } catch(IOException e) {
            System.out.println("Location (" + cityName + ") not found in weather service.");
            weather = new Weather(0.1, 0.1, "Cloudy", "chance of meatballs", -3.14, 101325, 95.25, -3.142, -3.14, 1E-6, 66.6, 2 * Math.PI, 42.0, "Cambridge", "2012 AD", 123.321);
        }

        pane = new BorderPane();
        this.setPrefSize(WIDTH, HEIGHT);

        pane.setPrefSize(WIDTH, HEIGHT);

        pane.setBackground(new Background(new BackgroundFill(Settings.getSecondary(), null, null)));

        pane.setStyle("-fx-padding: 5;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 5;" + "-fx-border-insets: -1;"
                + "-fx-border-radius: 0;" + "-fx-border-color: " + Settings.colorString(Settings.getTertiary()) + ";");

        //Construct header bar

        HBox topBar = new HBox();
        pane.setTop(topBar);
        topBar.setPadding(new Insets(0,10,0,10));
        topBar.setSpacing(20);



        //Add city label
        cityLabel = new AutoSizeText(city, Settings.getPrimary());
        cityLabel.setSize(300, headerSize);
        cityLabel.setTextAlignment(TextAlignment.LEFT);
        cityPaddingRight = new HBox();
        cityPaddingRight.setMinWidth(150 - cityLabel.getLayoutBounds().getWidth() / 2);
        HBox.setHgrow(cityPaddingRight, Priority.ALWAYS);

        topBar.getChildren().addAll(cityLabel, cityPaddingRight);



        //Add date text
        dateLabel = new AutoSizeText(date, Settings.getPrimary());
        dateLabel.setSize(100, headerSize);
        dateLabel.setTextAlignment(TextAlignment.RIGHT);
        topBar.getChildren().add(dateLabel);


        //Create center box

        HBox center = new HBox();
        center.setSpacing(100);
        center.setPadding(new Insets(5));

        //Create icon
        iconCode = null;
        
        
        //String iconUrl = "http://openweathermap.org/img/w/" + iconCode + ".png";
        conditionImage = new Image("/icons/chance0.png");
        conditionView = new ImageView(conditionImage);
        conditionView.setFitWidth(150);
        conditionView.setViewport(new Rectangle2D(100, 100, 100, 100));
        conditionView.setPreserveRatio(true);
        center.getChildren().add(conditionView);


        //Create temperature display
        nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(1);
        nf.setMaximumFractionDigits(1);


        temperatureLabel = new AutoSizeText("Temp", Settings.getPrimary());
        temperatureLabel.setSize(200, 100);
        center.getChildren().add(temperatureLabel);
        center.setAlignment(Pos.CENTER_LEFT);


        pane.setCenter(center);

        update();
        this.getChildren().add(pane);

    }

    @Override
    public void update() {
        temperature = weather.getTemp();

        iconCode = weather.getIcon();
        String iconUrl = "icons/weatherIcons/" + iconCode + ".png";
        conditionImage = new Image(iconUrl);
        conditionView.setImage(conditionImage);
        conditionView.setViewport(new Rectangle2D(0, 0,
                conditionImage.getWidth(), conditionImage.getHeight()));
        conditionView.setPreserveRatio(true);
        conditionView.setFitWidth(100);


        cityLabel.setText(city);
        dateLabel.setText(date);

        if (Settings.isCelsius()) {
            temperatureLabel.setText(nf.format(temperature) + "°C");
        }
        else {
            temperatureLabel.setText(nf.format(temperature * 9.0 / 5.0 + 32.0) + "°F");
        }
        cityLabel.resizeText();
        cityPaddingRight.setMinWidth(150 - cityLabel.getLayoutBounds().getWidth() / 2);

        dateLabel.resizeText();
        temperatureLabel.resizeText();

        //Set colors:
        
        pane.setBackground(new Background(new BackgroundFill(Settings.getSecondary(), null, null)));
        temperatureLabel.setFill(Settings.getPrimary());
        cityLabel.setFill(Settings.getPrimary());
        dateLabel.setFill(Settings.getPrimary());
        pane.setStyle("-fx-padding: 5;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 5;" + "-fx-border-insets: 0;"
                + "-fx-border-radius: 0;" + "-fx-border-color: " + Settings.colorString(Settings.getTertiary()) + ";");

    }
}