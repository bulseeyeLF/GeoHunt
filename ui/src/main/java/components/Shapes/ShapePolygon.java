package components.Shapes;

import javafx.scene.shape.Polyline;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShapePolygon extends Shapes {
    @Getter
    @Setter
    private ArrayList<Double> coordinates;

    private double minX = Double.MAX_VALUE;
    private double maxX = -Double.MAX_VALUE;
    private double minY = Double.MAX_VALUE;
    private double maxY = -Double.MAX_VALUE;
    private int id =0;

    private static Logger log = Logger.getLogger(ShapePolygon.class);

    public ShapePolygon(double ...coordinates){
        super();
        initPolygon(coordinates);
    }

    public ShapePolygon(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("coordinates");
        this.coordinates = new ArrayList<>();

        for (int i = 0; i < jsonArray.length() ; i ++){
            if (shape == null){
               initPolygon(jsonArray.getJSONObject(0).getDouble("x"), jsonArray.getJSONObject(0).getDouble("y"));
            }
            else {
                this.addPoint(jsonArray.getJSONObject(i).getDouble("x"),jsonArray.getJSONObject(i).getDouble("y"));
            }
        }

        finish();
    }

    private void initPolygon(double ... coords){
        this.coordinates = new ArrayList<>();
        shape=new Polyline(coords);
        for ( double d : coords){
            this.coordinates.add(d);
        }
        this.setSelected(false);
        //shape.setFill(null);
        setListeners();
    }

    public void addPoint (double x, double y){
        ((Polyline)shape).getPoints().add(x);
        ((Polyline)shape).getPoints().add(y);
        this.coordinates.add(x);
        this.coordinates.add(y);
        this.setSelected(false);
        //shape.setFill(null);
      //  ((Polyline)shape).getPoints().stream().map(Object::toString).forEach(e -> log.debug("cord: " + e + " , "));
        //log.debug("addPoint finished");
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
        //log.debug("setArea finished");
    }

    @Override
    public void finish() {
        ((Polyline)shape).getPoints().add(((Polyline)shape).getPoints().get(0));
        ((Polyline)shape).getPoints().add(((Polyline)shape).getPoints().get(1));
        this.setSelected(true);
        //    ((Polyline)shape).getPoints().stream().map(Object::toString).forEach(e -> log.debug("cord: " + e + " , "));
        log.debug("FinishPolygon finished");
    }

    @Override
    public void delete() {
        shape = new Polyline();
        this.setSelected(false);
    }

    @Override
    public JSONObject save() {
        log.debug("Save Polygon");
        JSONArray jsonArray = new JSONArray();
        for (int i = 0 ; i < coordinates.size() ;  i+=2){
            jsonArray.put((new JSONObject()).put("x",coordinates.get(i)).put("y", coordinates.get(i+1)));
        }
        return new JSONObject()
            .put("type", 0)
            .put("coordinates", jsonArray)
            .put("id", id);
    }
}
