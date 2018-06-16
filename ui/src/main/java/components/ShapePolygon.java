package components;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class ShapePolygon extends Shapes {
    @Getter
    @Setter
    private double [] coordinates;
    double minX = Double.MAX_VALUE;
    double maxX = -Double.MAX_VALUE;
    double minY = Double.MAX_VALUE;
    double maxY = -Double.MAX_VALUE;

    public ShapePolygon(){
        super();
        shape=new Polygon();
    }

    public ShapePolygon(double ...coordinates){
        super();
        this.coordinates = coordinates;
        setMinMax();
        shape=new Polygon(coordinates);
    }

    public ShapePolygon(Point2D... points){
        super();
        double[] coords = new double[points.length*2];
        AtomicInteger i = new AtomicInteger();
        Arrays.stream(points).forEach(point2D -> {
            coords[i.getAndIncrement()] = point2D.getX();
            coords[i.getAndIncrement()] = point2D.getY();
        });
        this.coordinates = coords;
        setMinMax();
        shape = new Polygon(coords);
    }

    @Override
    public Point2D getCentre(){
        return new Point2D((maxX-minX)/2,(maxY-minY)/2);
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle((maxX-minX)/2, (maxY-minY)/2,maxX-minX, maxY-minY );
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
