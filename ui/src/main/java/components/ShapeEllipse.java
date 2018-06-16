package components;

import javafx.geometry.Point2D;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import lombok.Data;

@Data
public class ShapeEllipse extends Shapes{
    private double centreX;
    private double centreY;
    private double radiusX;
    private double radiusY;

    public ShapeEllipse(){
        super();
        shape = new Ellipse();
    }

    public ShapeEllipse(double x, double y, double rX, double rY){
        super();
        centreX = x;
        centreY = y;
        radiusX = rX;
        radiusY = rY;
        shape = new Ellipse(x,y,rX, rY);
    }

    public ShapeEllipse(Point2D centre, double rX, double rY){
        super();
        centreX = centre.getX();
        centreY = centre.getY();
        radiusX = rX;
        radiusY = rY;
        shape = new Ellipse(centre.getX(), centre.getY(), rX,rY);
    }

    @Override
    public Point2D getCentre() {
        return new Point2D(centreX,centreY);
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(centreX, centreX,radiusX, radiusY);
    }
}
