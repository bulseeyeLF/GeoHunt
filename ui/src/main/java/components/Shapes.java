package components;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

public abstract class Shapes{
    @Setter
    @Getter
    protected Shape shape ;
    boolean selected;
    @Setter
    protected GraphicsContext shapeGraphicsContext;

    private static final Logger log = Logger.getLogger(Shapes.class);

    protected Shapes() {
    }
    public abstract Point2D getCentre();

    public abstract Rectangle getRectangle();

    public void setSelected(boolean selected){
       this.selected = selected;
    }
    protected boolean isSelected () {return shape.isFocused();}

    protected void drawShape(GraphicsContext graphicsContext, double ... params){
        shapeGraphicsContext = graphicsContext;
    }



}
