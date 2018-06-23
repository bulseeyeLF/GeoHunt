package components;

import javafx.scene.shape.Polyline;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import java.util.ArrayList;

public class ShapePolygon extends Shapes {
    @Getter
    @Setter
    private ArrayList<Double> coordinates;

    private double minX = Double.MAX_VALUE;
    private double maxX = -Double.MAX_VALUE;
    private double minY = Double.MAX_VALUE;
    private double maxY = -Double.MAX_VALUE;

    private static Logger log = Logger.getLogger(ShapePolygon.class);

    public ShapePolygon(double ...coordinates){
        super();

        this.coordinates = new ArrayList<>();
        shape=new Polyline(coordinates);
        for ( double d : coordinates){
            this.coordinates.add(d);
        }
        this.setSelected(false);
        //shape.setFill(null);
        setListeners();
        log.debug("Constructor finished");
    }

    public void finishPolygon(){
        ((Polyline)shape).getPoints().add(((Polyline)shape).getPoints().get(0));
        ((Polyline)shape).getPoints().add(((Polyline)shape).getPoints().get(1));
        this.setSelected(true);
    //    ((Polyline)shape).getPoints().stream().map(Object::toString).forEach(e -> log.debug("cord: " + e + " , "));
        log.debug("FinishPolygon finished");
    }

    public void addPoint (double x, double y){
        ((Polyline)shape).getPoints().add(x);
        ((Polyline)shape).getPoints().add(y);
        this.coordinates.add(x);
        this.coordinates.add(y);
        this.setSelected(false);
        //shape.setFill(null);
      //  ((Polyline)shape).getPoints().stream().map(Object::toString).forEach(e -> log.debug("cord: " + e + " , "));
        log.debug("addPoint finished");

    }
    @Override
    protected void setArea(){
        for(int i = 0; i < coordinates.size(); i += 2) {
            double x = coordinates.get(i);
            double y = coordinates.get(i+1);
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }
        double width = maxX - minX;
        double height = maxY - minY;
        this.area = width * height;
        log.debug("setArea finished");
    }
}
