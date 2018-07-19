package utils;

import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Region;
import javafx.stage.Screen;
import lombok.Getter;
import org.apache.log4j.Logger;

//singleton class containing the constants and utils of project

public class Utils {
    private static final Logger log = Logger.getLogger(Utils.class);
    private static Utils instance;
    @Getter
    private double screenHeight;
    @Getter
    private double screenWidth;

    private Utils() {
        refreshScreen();
    }

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
            log.info("Utils instance made");
        }
        return instance;
    }

    //match a number with optional '-' and decimal.
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public void refreshScreen() {
        Rectangle2D currentScreen = Screen.getPrimary().getVisualBounds();
        screenHeight = currentScreen.getHeight();
        screenWidth = currentScreen.getWidth();
    }

    public void setPercentageWidth(Region element, double percentage) {
        element.setPrefWidth(screenWidth * percentage / 100);
    }

    public void setPercentageHeight(Region element, double percentage) {
        element.setPrefHeight(screenHeight * percentage / 100);
    }

}
