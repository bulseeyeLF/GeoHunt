package components;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.json.JSONObject;

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
        log.debug("super constructor called");
    }

    public void setSelected(boolean selected) {
        if (selected){
            Glow glow = new Glow();
            glow.setLevel(650);
            glow.setInput(new DropShadow(9,Color.BLACK));
            this.setShapeLook(0.7,Color.DARKGRAY,Color.DARKGRAY,0.5,null,glow);
            this.selected = true;
            //log.debug( "setSelected on TRUE");
        }
        else {
            this.setShapeLook(0.5,Color.GRAY,Color.BLACK,0.5,null,null);
            this.selected = false;
            //log.debug("setSelected on FALSE");
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
            this.setShapeLook(0.6,Color.LIGHTGRAY,Color.BLACK,0.1,null,(new DropShadow(13,Color.LIGHTGRAY)));
            // log.debug("Entered");
            event.consume();
        }
    }

    public void setExited(MouseEvent event){
        if (!isSelected()) {
            //log.debug("Exited");
            this.setShapeLook(0.5,Color.GRAY,Color.BLACK,0.1,null,null);
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
        //log.debug("SetShapeLook finished");
    }

    protected abstract void setArea();

    public abstract JSONObject save();
}
