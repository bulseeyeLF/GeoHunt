package components;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import lombok.Getter;
import lombok.Setter;

public abstract class Shapes{
    @Setter
    @Getter
    Shape shape ;
    boolean selected;

    protected Shapes(){
    }

    public abstract Point2D getCentre();

    public abstract Rectangle getRectangle();

    public void setSelected(boolean selected){
       this.selected = selected;
    }

    protected abstract void drawShape(GraphicsContext graphicsContext, double ... params);

}
