package components;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import lombok.Getter;
import lombok.Setter;

public abstract class Shapes{
    @Setter
    @Getter
    Shape shape ;

    protected Shapes(){
    }

    public abstract Point2D getCentre();

    public abstract Rectangle getRectangle();

    public boolean isFocused(){
        return shape.isFocused();
    }

}
