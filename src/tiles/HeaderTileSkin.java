//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package tiles;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.fonts.Fonts;
import eu.hansolo.tilesfx.skins.TileSkin;
import eu.hansolo.tilesfx.tools.Helper;
import eu.hansolo.tilesfx.weather.DarkSky;
import eu.hansolo.tilesfx.weather.DataPoint;
import eu.hansolo.tilesfx.weather.WeatherSymbol;
import eu.hansolo.tilesfx.weather.DarkSky.ConditionAndIcon;
import eu.hansolo.tilesfx.weather.DarkSky.Unit;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HeaderTileSkin extends TileSkin {
    private static final DateTimeFormatter TF = DateTimeFormatter.ofPattern("HH:mm");
    private Text titleText;
    private Text valueText;
    private Text unitText;
    private WeatherSymbol weatherSymbol;
    private Text text;
    private HBox searchBox;
    private DarkSky darkSky;

    public HeaderTileSkin(Tile TILE) {
        super(TILE);
    }



    protected void initGraphics() {
        super.initGraphics();
        this.darkSky = this.tile.getDarkSky();

        this.titleText = new Text(this.tile.getTitle());
        this.titleText.setFill(this.tile.getTitleColor());
        Helper.enableNode(this.titleText, !this.tile.getTitle().isEmpty());
        this.valueText = new Text();
        this.unitText = new Text(this.darkSky.getUnit().temperatureUnitString);
        this.text = new Text("");
        this.text.setFill(this.tile.getTextColor());


        Image search = new Image(getClass().getResourceAsStream("search.png"));
        this.searchBox = new HBox(new Button(""));
        this.searchBox.setAlignment(Pos.TOP_CENTER);

        this.weatherSymbol = new WeatherSymbol(ConditionAndIcon.NONE, 250.0D, this.tile.getForegroundColor());

        this.getPane().getChildren().addAll(new Node[]{this.titleText, this.valueText, this.unitText, this.weatherSymbol, this.text, this.sunriseBox, this.sunsetBox});
    }

    protected void registerListeners() {
        super.registerListeners();
    }

    protected void handleEvents(String EVENT_TYPE) {
        super.handleEvents(EVENT_TYPE);
        if ("VISIBILITY".equals(EVENT_TYPE)) {
            Helper.enableNode(this.titleText, !this.tile.getTitle().isEmpty());
            Helper.enableNode(this.valueText, this.tile.isValueVisible());
            Helper.enableNode(this.unitText, !this.tile.getUnit().isEmpty());
        }

    }

    protected void resizeDynamicText() {
        double maxWidth = this.width - this.size * 0.1D;
        double fontSize = this.size * this.textSize.factor;
        this.titleText.setFont(Fonts.latoRegular(fontSize));
        if (this.titleText.getLayoutBounds().getWidth() > maxWidth) {
            Helper.adjustTextSize(this.titleText, maxWidth, fontSize);
        }

        this.titleText.relocate(this.size * 0.05D, this.size * 0.05D);
        maxWidth = this.unitText.isVisible() ? this.width - this.size * 0.275D : this.width - this.size * 0.1D;
        fontSize = this.size * 0.24D;
        this.valueText.setFont(Fonts.latoRegular(fontSize));
        if (this.valueText.getLayoutBounds().getWidth() > maxWidth) {
            Helper.adjustTextSize(this.valueText, maxWidth, fontSize);
        }

        if (this.unitText.isVisible()) {
            this.valueText.relocate(this.width - this.size * 0.075D - this.valueText.getLayoutBounds().getWidth() - this.unitText.getLayoutBounds().getWidth(), this.size * 0.15D);
        } else {
            this.valueText.relocate(this.width - this.size * 0.05D - this.valueText.getLayoutBounds().getWidth(), this.size * 0.15D);
        }

        maxWidth = this.width - this.size * 0.1D;
        fontSize = this.size * this.textSize.factor;
        this.text.setFont(Fonts.latoRegular(fontSize));
        if (this.text.getLayoutBounds().getWidth() > maxWidth) {
            Helper.adjustTextSize(this.text, maxWidth, fontSize);
        }

        this.text.setX(this.size * 0.05D);
        this.text.setY(this.height - this.size * 0.05D);
        Helper.fitNodeWidth(this.text, maxWidth);
        maxWidth = this.width - this.size * 0.705D;
        fontSize = this.size * 0.06D;
        this.sunriseText.setFont(Fonts.latoRegular(fontSize));
        if (this.sunriseText.getLayoutBounds().getWidth() > maxWidth) {
            Helper.adjustTextSize(this.sunriseText, maxWidth, fontSize);
        }

        this.sunsetText.setFont(Fonts.latoRegular(fontSize));
        if (this.sunsetText.getLayoutBounds().getWidth() > maxWidth) {
            Helper.adjustTextSize(this.sunsetText, maxWidth, fontSize);
        }

    }

    protected void resizeStaticText() {
        double maxWidth = this.width - this.size * 0.1D;
        double fontSize = this.size * this.textSize.factor;
        boolean customFontEnabled = this.tile.isCustomFontEnabled();
        Font customFont = this.tile.getCustomFont();
        Font font = customFontEnabled && customFont != null ? Font.font(customFont.getFamily(), fontSize) : Fonts.latoRegular(fontSize);
        this.titleText.setFont(font);
        if (this.titleText.getLayoutBounds().getWidth() > maxWidth) {
            Helper.adjustTextSize(this.titleText, maxWidth, fontSize);
        }

        switch(this.tile.getTitleAlignment()) {
            case LEFT:
            default:
                this.titleText.relocate(this.size * 0.05D, this.size * 0.05D);
                break;
            case CENTER:
                this.titleText.relocate((this.width - this.titleText.getLayoutBounds().getWidth()) * 0.5D, this.size * 0.05D);
                break;
            case RIGHT:
                this.titleText.relocate(this.width - this.size * 0.05D - this.titleText.getLayoutBounds().getWidth(), this.size * 0.05D);
        }

        maxWidth = this.width - this.size * 0.275D;
        fontSize = this.size * 0.12D;
        this.unitText.setFont(Fonts.latoRegular(fontSize));
        if (this.unitText.getLayoutBounds().getWidth() > maxWidth) {
            Helper.adjustTextSize(this.unitText, maxWidth, fontSize);
        }

        this.unitText.relocate(this.width - this.size * 0.05D - this.unitText.getLayoutBounds().getWidth(), this.size * 0.27D);
    }

    protected void resize() {
        super.resize();
        this.weatherSymbol.setPrefSize(this.width * 0.5D, this.height - this.size * 0.4D);
        this.weatherSymbol.relocate(this.contentBounds.getX(), (this.height - this.weatherSymbol.getPrefHeight()) * 0.5D);
        this.sunriseBox.setPrefSize(this.width - this.size * 0.1D, this.size * 0.09D);
        this.sunriseBox.setSpacing(this.size * 0.025D);
        this.sunriseBox.relocate(this.contentBounds.getX(), this.height * 0.6075D + this.size * 0.0D);
        this.sunsetBox.setPrefSize(this.width - this.size * 0.1D, this.size * 0.09D);
        this.sunsetBox.setSpacing(this.size * 0.025D);
        this.sunsetBox.relocate(this.contentBounds.getX(), this.height * 0.725D + this.size * 0.0D);
        this.sunriseSymbol.setPrefSize(this.size * 0.1D, this.size * 0.1D);
        this.sunsetSymbol.setPrefSize(this.size * 0.1D, this.size * 0.1D);
        this.redraw();
    }

    protected void redraw() {
        super.redraw();
        this.titleText.setText(this.tile.getTitle());
        this.resizeStaticText();
        DataPoint today = this.darkSky.getToday();
        Unit unit = this.darkSky.getUnit();
        this.valueText.setText(String.format(Locale.US, "%.0f", today.getTemperature()));
        this.unitText.setText(unit.temperatureUnitString);
        this.weatherSymbol.setCondition(today.getCondition());
        this.text.setText(Helper.normalize(today.getSummary()));
        this.sunriseText.setText(TF.format(today.getSunriseTime()));
        this.sunsetText.setText(TF.format(today.getSunsetTime()));
        this.resizeDynamicText();
        this.titleText.setFill(this.tile.getTitleColor());
        this.valueText.setFill(this.tile.getValueColor());
        this.unitText.setFill(this.tile.getUnitColor());
        this.text.setFill(this.tile.getTextColor());
        this.weatherSymbol.setSymbolColor(this.tile.getForegroundColor());
        this.sunriseSymbol.setSymbolColor(this.tile.getForegroundColor());
        this.sunsetSymbol.setSymbolColor(this.tile.getForegroundColor());
    }
}
