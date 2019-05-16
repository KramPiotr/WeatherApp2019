package tiles;

import items.AutoSizeText;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import skeletons.Settings;
import skeletons.WeatherScene;
import util.ImageColorer;

public class HeaderTile extends Tile{
    private static int headerSize = 20;
    private double temperature;
    private String iconCode = "09d";
    private String city;
    private AutoSizeText cityLabel;
    private Image conditionImage;
    private ImageView conditionView;

    Button menuButton;
    Button searchButton;

    public HeaderTile(WeatherScene parent) {
        super(parent);

        BorderPane pane = new BorderPane();

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

        topBar.getChildren().add(menuButton);


        //Add city label
        cityLabel = new AutoSizeText("Placeholder",Settings.getPrimary());
        cityLabel.setSize(400,20);
        topBar.getChildren().add(cityLabel);

        //Add search button
        searchButton = new Button();





        String iconUrl = "http://openweathermap.org/img/w/" + iconCode + ".png";
        conditionImage = new Image(iconUrl);
        conditionView = new ImageView(conditionImage);
        conditionView.setPreserveRatio(true);
        conditionView.setFitWidth(100);




        this.getChildren().add(pane);
        update();
    }

    @Override
    public void update() {
        temperature = ((WeatherScene) parent).getTemperature();


    }
}
