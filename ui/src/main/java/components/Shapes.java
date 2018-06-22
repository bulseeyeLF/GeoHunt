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
            shape.setOpacity(0.6);
            Glow glow = new Glow();
            glow.setLevel(200);
            glow.setInput(new DropShadow(15,Color.BLACK));
            shape.setEffect(glow);
            shape.setStroke(Color.DARKGRAY);
            shape.setStrokeType(StrokeType.OUTSIDE);
            shape.setStrokeWidth(1);
            shape.setFill(Color.DARKGRAY);
            this.selected = true;
        }
        else {
            shape.setEffect(null);
            shape.setOpacity(0.4);
            shape.setStroke(Color.BLACK);
            shape.setFill(Color.DARKGRAY);
            shape.setStrokeWidth(1);
            this.selected = false;
        }
    }

    protected void setListeners() {
        shape.setOnMouseEntered(event -> {
            if (!isSelected()) {
                shape.setOpacity(0.5);
                shape.setFill(Color.DARKGRAY);
                shape.setStrokeDashOffset(0);
                shape.setStroke(Color.DARKGRAY);
                shape.setEffect(new DropShadow());
                log.debug("entered");
                event.consume();
            }
        });
        shape.setOnMouseExited(event -> {
            if (!isSelected()) {
                shape.setOpacity(0.4);
                shape.setEffect(null);
                shape.setStroke(Color.BLACK);
                log.debug("exited");
                event.consume();
            }
        });
    }
}
