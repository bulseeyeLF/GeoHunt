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

}
