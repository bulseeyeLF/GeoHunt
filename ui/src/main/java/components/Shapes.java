package components;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
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
            shape.setOpacity(0.5);
            shape.setStroke(Color.BLACK);
            shape.setStrokeDashOffset(5);
            shape.setFill(Color.RED);
            this.selected = true;
        }
        else {
            shape.setOpacity(0.1);
            shape.setStroke(Color.BLACK);
            shape.setFill(Color.BLACK);
            this.selected = false;
        }
    }

    protected void setListeners() {
        shape.setOnMouseEntered(event -> {
            if (!isSelected()) {
                shape.setOpacity(0.5);
                shape.setFill(Color.BLACK);
                shape.setStrokeDashOffset(0);
                shape.setStroke(Color.BLACK);
                log.debug("entered");
                event.consume();
            }
        });
        shape.setOnMouseExited(event -> {
            if (!isSelected()) {
                shape.setOpacity(0.1);
                log.debug("exited");
                event.consume();
            }
        });
    }
}
