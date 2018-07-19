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
import utils.ShapeUtils;

public abstract class Shapes {
    private static final Logger log = Logger.getLogger(Shapes.class);
    private static final ShapeUtils shapeUtils = ShapeUtils.getInstance();
    @Setter
    @Getter
    protected Shape shape;
    @Getter
    boolean selected;
    @Getter
    protected double area;
    protected Shapes() {
        this.area = 100;
        log.debug("super constructor called");
    }

    public void setSelected(boolean selected) {
        if (selected){
            this.setShapeLook(
                shapeUtils.getOPACITY_SELECTED(),
                shapeUtils.getFILL_SELECTED(),
                shapeUtils.getSTROKE_SELECTED(),
                shapeUtils.getSTROKE_WIDTH_SELECTED(),
                shapeUtils.getSTROKE_TYPE_DEFAULT(),
                shapeUtils.getEFFECT_SELECTED());
            this.selected = true;
            //log.debug( "setSelected on TRUE");
        }
        else {
            this.setShapeLook(
                shapeUtils.getOPACITY_DEFAULT(),
                shapeUtils.getFILL_DEFAULT(),
                shapeUtils.getSTROKE_DEFAULT(),
                shapeUtils.getSTROKE_WIDTH_DEFAULT(),
                shapeUtils.getSTROKE_TYPE_INSIDE(), //TODO check why is only here this stroke type, what is this anyway
                shapeUtils.getEFFECT_DEFAULT());
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
            this.setShapeLook(
                shapeUtils.getOPACITY_ENTERED(),
                shapeUtils.getFILL_ENTERED(),
                shapeUtils.getSTROKE_ENTERED(),
                shapeUtils.getSTROKE_WIDTH_ENTERED(),
                shapeUtils.getSTROKE_TYPE_DEFAULT(),
                shapeUtils.getEFFECT_ENTERED());
            // log.debug("Entered");
            event.consume();
        }
    }

    public void setExited(MouseEvent event){
        if (!isSelected()) {
            //log.debug("Exited");
            this.setShapeLook(
                shapeUtils.getOPACITY_DEFAULT(),
                shapeUtils.getFILL_DEFAULT(),
                shapeUtils.getSTROKE_DEFAULT(),
                shapeUtils.getSTROKE_WIDTH_DEFAULT(),
                shapeUtils.getSTROKE_TYPE_INSIDE(),
                shapeUtils.getEFFECT_DEFAULT());
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

    public abstract void finish();

    public  abstract void delete();

    public abstract JSONObject save();
}