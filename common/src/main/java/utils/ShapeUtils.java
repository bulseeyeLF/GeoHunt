package utils;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import lombok.Getter;
import org.apache.log4j.Logger;

//singleton for constants and utils of shapes
public class ShapeUtils {
    private static final Logger log = Logger.getLogger(ShapeUtils.class);
    private static ShapeUtils instance;
    @Getter
    private final Effect EFFECT_SELECTED;
    @Getter
    private final Effect EFFECT_ENTERED;
    @Getter
    private final Effect EFFECT_DEFAULT;
    @Getter
    private final double OPACITY_SELECTED;
    @Getter
    private final double OPACITY_DEFAULT;
    @Getter
    private final double OPACITY_ENTERED;
    @Getter
    private final double STROKE_WIDTH_SELECTED;
    @Getter
    private final double STROKE_WIDTH_DEFAULT;
    @Getter
    private final double STROKE_WIDTH_ENTERED;
    @Getter
    private final Color FILL_SELECTED;
    @Getter
    private final Color FILL_DEFAULT;
    @Getter
    private final Color FILL_ENTERED;
    @Getter
    private final Color STROKE_SELECTED;
    @Getter
    private final Color STROKE_DEFAULT;
    @Getter
    private final Color STROKE_ENTERED;
    @Getter
    private final StrokeType STROKE_TYPE_DEFAULT;
    @Getter
    private final StrokeType STROKE_TYPE_INSIDE;

    public static final int TYPE_ELLIPSE = 1;
    public static final int TYPE_POLYLINE = 0;



    public static ShapeUtils getInstance() {
        if (instance == null) {
            instance = new ShapeUtils();
            log.info("Utils instance made");
        }
        return instance;
    }

    private ShapeUtils(){
        Glow glow = new Glow();
        glow.setLevel(650);
        glow.setInput(new DropShadow(9,Color.BLACK));
        EFFECT_SELECTED = glow;
        EFFECT_ENTERED = new DropShadow(13,Color.LIGHTGRAY);
        EFFECT_DEFAULT = null;
        OPACITY_SELECTED = 0.7;
        OPACITY_DEFAULT = 0.5;
        OPACITY_ENTERED= 0.6;
        STROKE_WIDTH_SELECTED = 0.5;
        STROKE_WIDTH_DEFAULT = 0.5;
        STROKE_WIDTH_ENTERED = 0.1;
        FILL_SELECTED = Color.DARKGRAY;
        FILL_DEFAULT = Color.GRAY;
        FILL_ENTERED = Color.LIGHTGRAY;
        STROKE_SELECTED = Color.DARKGRAY;
        STROKE_DEFAULT = Color.BLACK;
        STROKE_ENTERED = Color.BLACK;
        STROKE_TYPE_DEFAULT = StrokeType.CENTERED;
        STROKE_TYPE_INSIDE = StrokeType.INSIDE;
    }
}
