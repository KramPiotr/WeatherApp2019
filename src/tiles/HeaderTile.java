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
import skeletons.Panel;
import skeletons.Settings;
import skeletons.WeatherPanel;
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

    public HeaderTile(Panel parent) {
        super(parent);

        BorderPane pane = new BorderPane();
        pane.setBackground(new Background(new BackgroundFill(Settings.getSecondary(),null,null)));

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
        temperature = ((WeatherPanel) parent).getTemperature();
        city = "Placeholder";
        date = "-1/-1";
        cityLabel.setText(city);
        dateLabel.setText(date);
        cityLabel.resizeText();
        dateLabel.resizeText();

    }
}
