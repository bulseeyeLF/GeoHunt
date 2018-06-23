package components;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class ShapePolygon extends Shapes {
    @Getter
    @Setter
    private double [] coordinates ;

    private double minX = Double.MAX_VALUE;
    private double maxX = -Double.MAX_VALUE;
    private double minY = Double.MAX_VALUE;
    private double maxY = -Double.MAX_VALUE;

    private static Logger log = Logger.getLogger(ShapePolygon.class);

    public ShapePolygon(double ...coordinates){
        super();
        this.coordinates = coordinates;
        shape=new Polyline(coordinates);
        this.setSelected(false);
        shape.setFill(null);
        setListeners();
    }

    //kopirajuci konstruktor
    public ShapePolygon(ShapePolygon shapePolygon){
        super();
        this.coordinates = shapePolygon.getCoordinates();
        shape = new Polygon(coordinates);
        this.setSelected(true);
        setListeners();
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
        shape = new Polyline(coordinates);
        this.setSelected(false);
        shape.setFill(null);

    }
    @Override
    protected void setArea(){
        for(int i = 0; i < coordinates.length; i += 2) {
            double x = coordinates[i];
            double y = coordinates[i + 1];
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }
        double width = maxX - minX;
        double height = maxY - minY;
        this.area = width * height;
    }
}
