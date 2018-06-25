package components;
import javafx.scene.shape.Ellipse;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.json.JSONObject;


public class ShapeEllipse extends Shapes{
    private double centreX ;
    private double centreY;
    private double radiusX;
    private double radiusY;
    private int id = 0;
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

    public ShapeEllipse(JSONObject jsonObject) {
        centreX = jsonObject.getDouble("centreX");
        centreY = jsonObject.getDouble("centreY");
        radiusX = jsonObject.getDouble("radiusX");
        radiusY = jsonObject.getDouble("radiusY");
        shape = new Ellipse(centreX,centreY, radiusX, radiusY);
        this.setSelected(false);
        setListeners();
    }

    @Override
    protected void setArea() {
        this.area = radiusX * radiusY;
    }

    @Override
    public JSONObject save() {
        log.debug("Save Ellipse");
        return new JSONObject()
            .put("shape", 1)
            .put("centreX", this.centreX)
            .put("centreY", this.centreY)
            .put("radiusX", this.radiusX)
            .put("radiusY", this.radiusY)
            .put("id", id);
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
