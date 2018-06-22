package components;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Shadow;
import javafx.scene.paint.*;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

public abstract class Shapes {
    @Setter
    @Getter
    protected Shape shape;
    @Getter
    boolean selected;
    @Setter
    protected GraphicsContext shapeGraphicsContext;

    private static final Logger log = Logger.getLogger(Shapes.class);

    protected Shapes() {
    }

    public void setSelected(boolean selected) {
        if (selected){
            shape.setOpacity(0.7);
            Glow glow = new Glow();
            glow.setLevel(500);
            glow.setInput(new DropShadow(13,Color.BLACK));
            shape.setEffect(glow);
            shape.setStroke(Color.DARKGRAY);
            shape.setStrokeType(StrokeType.OUTSIDE);
            shape.setStrokeWidth(1);
            shape.setFill(Color.DARKGRAY);
            this.selected = true;
        }
        else {
            shape.setEffect(null);
            shape.setOpacity(0.5);
            shape.setStroke(Color.BLACK);
            shape.setFill(Color.GRAY);
            shape.setStrokeWidth(1);
            this.selected = false;
        }
    }

    protected void setListeners() {
        shape.setOnMouseEntered(event -> {
            if (!isSelected()) {
                shape.setOpacity(0.6);
                shape.setFill(Color.LIGHTGREY);
                shape.setEffect(new DropShadow(13,Color.LIGHTGRAY));
                log.debug("entered");
                event.consume();
            }
        });
        shape.setOnMouseExited(event -> {
            if (!isSelected()) {
                shape.setOpacity(0.5);
                shape.setEffect(null);
                shape.setFill(Color.GRAY);
                shape.setStroke(Color.BLACK);
                log.debug("exited");
                event.consume();
            }
        });
    }
}
