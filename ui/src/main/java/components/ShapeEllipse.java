package components;

import javafx.beans.property.ObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import lombok.Data;
import lombok.Setter;
import org.apache.log4j.Logger;


public class ShapeEllipse extends Shapes{
    private double centreX;
    private double centreY;
    private double radiusX;
    private double radiusY;
    @Setter
    private double radiusXposition;
    @Setter
    private double radiusYposition;

    private static Logger log = Logger.getLogger(ShapeEllipse.class);

    public ShapeEllipse(){
        super();
        shape = new Ellipse();
        this.setSelected(false);
        setListeners();

    }

    public ShapeEllipse(double x, double y, double rX, double rY){
        super();
        centreX = x;
        centreY = y;
        radiusX = rX;
        radiusY = rY;
        shape = new Ellipse(x,y,rX, rY);
        this.setSelected(false);
        setListeners();

    }


}
