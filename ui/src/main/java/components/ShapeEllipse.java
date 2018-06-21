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
import sun.jvm.hotspot.code.ObjectValue;

@Data
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
        shape.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                log.debug("mouse entered");
            }
        });

    }

    public ShapeEllipse(double x, double y, double rX, double rY){
        super();
        centreX = x;
        centreY = y;
        radiusX = rX;
        radiusY = rY;
        shape = new Ellipse(x,y,rX, rY);
        shape.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                log.debug("Entered???");
                event.consume();
            }
        });

    }

    @Override
    public Point2D getCentre() {
        return new Point2D(centreX,centreY);
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(centreX, centreX,radiusX, radiusY);
    }

    @Override
    protected void drawShape(GraphicsContext graphicsContext, double ... params) {
        super.drawShape(graphicsContext);
        if (params.length != 0) {
            radiusXposition = params[0];
            radiusYposition = params[1];
        }
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.strokeOval(Math.min(centreX, radiusXposition), Math.min(centreY,radiusYposition), radiusX, radiusY);
    }


}
