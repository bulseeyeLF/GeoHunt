package components;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;
import javafx.scene.shape.Shape;
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
    private static final Logger log = Logger.getLogger(Shapes.class);
    @Getter
    protected double area;

    protected Shapes() {
        this.area = 100;
    }

    public void setSelected(boolean selected) {
        if (selected){
            Glow glow = new Glow();
            glow.setLevel(500);
            glow.setInput(new DropShadow(13,Color.BLACK));
            this.setShapeLook(0.7,Color.DARKGRAY,Color.DARKGRAY,1,StrokeType.OUTSIDE,glow);
            this.selected = true;
        }
        else {
            this.setShapeLook(0.5,Color.GRAY,Color.BLACK,1,null,null);
            this.selected = false;
        }
    }

    protected void setListeners() {

        shape.addEventHandler(MouseEvent.ANY,
            new ShapesHandler(
                this::setEntered,
                this::setExited
        ));
    }

    public void setEntered(MouseEvent event){
        if (!isSelected()) {
            this.setShapeLook(0.6,Color.LIGHTGRAY,Color.BLACK,1,null,(new DropShadow(13,Color.LIGHTGRAY)));
            event.consume();
        }
    }

    public void setExited(MouseEvent event){
        if (!isSelected()) {
            this.setShapeLook(0.5,Color.GRAY,Color.BLACK,1,null,null);
            event.consume();
        }
    }


    private void setShapeLook(double opacity, Paint fill, Paint stroke, double strokeWidth, StrokeType strokeType, Effect effect){
        shape.setOpacity(opacity);
        shape.setFill(fill);
        shape.setStroke(stroke);
        shape.setStrokeWidth(strokeWidth);
        shape.setStrokeType(strokeType);
        shape.setEffect(effect);
    }

    protected abstract void setArea();
}
