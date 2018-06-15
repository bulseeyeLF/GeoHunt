package components;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;
import lombok.Getter;
import lombok.Setter;

public abstract class MapShape {
    @Setter
    @Getter
    Shape shape ;

    protected MapShape(){
    }

    public abstract Point2D getCentre();

}
