package components;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class ShapePolygon extends Shapes {
    @Getter
    @Setter
    private double [] coordinates ;
    double minX = Double.MAX_VALUE;
    double maxX = -Double.MAX_VALUE;
    double minY = Double.MAX_VALUE;
    double maxY = -Double.MAX_VALUE;

    private static Logger log = Logger.getLogger(ShapePolygon.class);

    public ShapePolygon(){
        super();
        coordinates = null;
        shape=new Polygon();
    }

    public ShapePolygon(double ...coordinates){
        super();
        this.coordinates = coordinates;
        setMinMax();
        shape=new Polygon(coordinates);
    }

    public void addPoint (double x, double y){
        if (this.coordinates == null){
            this.coordinates = new double[2];
        } else {
            double [] newCords = new double[this.coordinates.length + 2];
            AtomicInteger i = new AtomicInteger();
            Arrays.stream(this.coordinates).forEach(coord -> newCords[i.getAndIncrement()] = coord);
            this.coordinates = newCords;
        }
        this.coordinates[this.coordinates.length - 2] = x;
        this.coordinates[this.coordinates.length - 1] = y;

        setMinMax();
        shape = new Polygon(coordinates);
    }

    @Override
    public Point2D getCentre(){
        return new Point2D((maxX-minX)/2,(maxY-minY)/2);
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle((maxX-minX)/2, (maxY-minY)/2,maxX-minX, maxY-minY );
    }

    @Override
    protected void drawShape(GraphicsContext graphicsContext, double ... params) {
        super.drawShape(graphicsContext);
        graphicsContext.setStroke(Color.BLACK);
        double [] xCords = new double[coordinates.length/2];
        double [] yCords = new double[coordinates.length/2];

        for (int i = 0; i < coordinates.length; i+=2){
            xCords[i/2] = coordinates[i];
            yCords[i/2] = coordinates[i+1];
        }

        if (params.length == 3 || params.length ==0){
            graphicsContext.strokePolygon(xCords,yCords,xCords.length);

        } else {
            graphicsContext.strokePolyline(xCords, yCords, xCords.length);
        }
    }

    private void setMinMax(){
        for(int i = 0; i < coordinates.length; i += 2) {
            double x = coordinates[i];
            double y = coordinates[i + 1];
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }

    }
}
