package utils;

import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Region;
import javafx.stage.Screen;
import org.apache.log4j.Logger;

public class Utils {
    private static final Logger log = Logger.getLogger(Utils.class);
    private double height;
    private double width;
    private static Utils instance;

    private Utils(){
        refreshScreen();
    }

    public static Utils getInstance(){
        if (instance == null){
            instance = new Utils();
            log.info("Utils instance made");
        }
        return instance;
    }

    public void refreshScreen() {
        Rectangle2D currentScreen = Screen.getPrimary().getVisualBounds();
        height = currentScreen.getHeight();
        width = currentScreen.getWidth();
    }

    public double getScreenWidth() {
        return width;
    }

    public double getScreenHeight() {
        return height;
    }

    public void setPercentageWidth(Region element, double percentage) {
        element.setPrefWidth(width * 100 / percentage);
    }

    public void setPercentageHeight(Region element, double percentage) {
        element.setPrefHeight(height * 100 / percentage);
    }
}
