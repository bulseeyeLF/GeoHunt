package utils;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import org.apache.log4j.Logger;

public class Utils {
    private static final Logger log = Logger.getLogger(Utils.class);

    private Utils(){
        currentScreen =Screen.getPrimary().getVisualBounds();
    }

    private static Utils instance;

    public static Utils getInstance(){
        if (instance==null){
            instance=new Utils();
            log.info("Utils instance made");
        }
        return instance;
    }

    private Rectangle2D currentScreen ;

    public double getScreenWidth() {
        return currentScreen.getWidth();
    }

    public double getScreenHeight() {
        return currentScreen.getHeight();
    }
}
