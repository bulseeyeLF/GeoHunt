package components;


import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LineShape extends MapShape {

    public LineShape(){
        super();
        shape=new Polygon();
    }

    public LineShape(double ...coordinates){
        super();
        shape=new Polygon(coordinates);
    }

    public LineShape (Point2D... points){
        super();
        double[] coords= new double[points.length*2];
        AtomicInteger i= new AtomicInteger();
        Arrays.stream(points).forEach(point2D->{
            coords[i.getAndIncrement()]=point2D.getX();
            coords[i.getAndIncrement()]=point2D.getY();
        });
        shape = new Polygon(coords);
    }

    @Override
    public Point2D getCentre(){

        return null;
    }
}
