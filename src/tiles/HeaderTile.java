package tiles;

import items.AutoSizeText;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import settings.Settings;
import skeletons.WeatherScene;
import util.ImageColorer;


public class HeaderTile extends Tile{
    private static int headerSize = 40;
    private double temperature;
    private String iconCode = "09d";
    private String city = "_placeholder_";
    private AutoSizeText cityLabel;
    private String date = "_pl_";
    private AutoSizeText dateLabel;
    private Image conditionImage;
    private ImageView conditionView;

    Button menuButton;
    Button searchButton;

    public HeaderTile(WeatherScene parent) {
        super(parent);

        BorderPane pane = new BorderPane();
        this.setPrefSize(590,250);
        pane.setPrefSize(590,250);

        pane.setBackground(new Background(new BackgroundFill(Settings.getSecondary(),null,null)));

        pane.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 5;" + "-fx-border-insets: 0;"
                + "-fx-border-radius: 0;" + "-fx-border-color: "+ Settings.colorString(Settings.getTertiary())+";");

        //Construct header bar

        HBox topBar = new HBox();
        pane.setTop(topBar);

        //Add menu button
        menuButton = new Button();

        Image menuIcon = new Image("/icons/hamburger.png");
        ImageView menuIconView = new ImageView(menuIcon);

        menuIconView.setPreserveRatio(true);
        menuIconView.setFitHeight(headerSize);

        menuButton.setGraphic(ImageColorer.recolor(menuIconView, Settings.getPrimary()));
        menuButton.setStyle("-fx-focus-color: transparent;");
        menuButton.setPadding(new Insets(0, 0, 0, 0));
        menuButton.setBackground(new Background(new BackgroundFill(Settings.getSecondary(),null,null)));

        topBar.getChildren().add(menuButton);


        //Add city label
        cityLabel = new AutoSizeText(city,Settings.getPrimary());
        cityLabel.setSize(300,headerSize);
        topBar.getChildren().add(cityLabel);

        //Add search button
        searchButton = new Button();

        ImageView searchImage = new ImageView(new Image("/icons/location.png"));
        searchImage.setPreserveRatio(true);
        searchImage.setFitHeight(headerSize);

        searchButton.setGraphic(ImageColorer.recolor(searchImage,Settings.getPrimary()));
        searchButton.setStyle("-fx-focus-color: transparent;");
        searchButton.setPadding(new Insets(0, 0, 0, 0));
        searchButton.setBackground(new Background(new BackgroundFill(Settings.getSecondary(),null,null)));
        topBar.getChildren().add(searchButton);

        //Add date text
        dateLabel = new AutoSizeText(date,Settings.getPrimary());
        dateLabel.setSize(50,headerSize);
        topBar.getChildren().add(dateLabel);

        //TODO:TEST
        topBar.getChildren().add(new Rectangle(headerSize,headerSize, Color.BLUEVIOLET));

        String iconUrl = "http://openweathermap.org/img/w/" + iconCode + ".png";
        conditionImage = new Image(iconUrl);
        conditionView = new ImageView(conditionImage);
        conditionView.setPreserveRatio(true);
        conditionView.setFitWidth(100);




        update();
        this.getChildren().add(pane);

    }

    @Override
    public void update() {
        temperature = ((WeatherScene) parent).getTemperature();
        city = "Placeholder";
        date = "-1/-1";
        cityLabel.setText(city);
        dateLabel.setText(date);
        cityLabel.resizeText();
        dateLabel.resizeText();

    }
}
