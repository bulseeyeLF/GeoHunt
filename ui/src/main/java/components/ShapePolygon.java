package components;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.ShapeUtils;

import java.util.ArrayList;

public class ShapePolygon extends ShapePolyline{

    public ShapePolygon(JSONObject jsonObject){
        super();
        //finish();
    }

    public ShapePolygon(double ...coordinates){
        super(coordinates);
    }

    @Override
    public JSONObject save() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0 ; i < coordinates.size() ;  i+=2){
            jsonArray.put((new JSONObject()).put("x",coordinates.get(i)).put("y", coordinates.get(i+1)));
        }
        return new JSONObject()
            .put("type", ShapeUtils.TYPE_POLYLINE)
            .put("coordinates", jsonArray)
            .put("id", id);
    }
}
