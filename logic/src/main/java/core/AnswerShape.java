package core;

import lombok.Getter;
import org.json.JSONObject;
import shape.ShapeEllipse;
import shape.ShapePolygon;
import shape.Shapes;

public class AnswerShape {

    @Getter
    Shapes answerShape;

    //TODO kako cemo da cuvamo answerShape????

    public AnswerShape(JSONObject answerShapeJson) {
        if (answerShapeJson!= null){
            int type = answerShapeJson.optInt("type");
            if (type == 0){
                answerShape = new ShapePolygon(answerShapeJson);
            } else if (type == 1) {
                answerShape = new ShapeEllipse(answerShapeJson);
            }
        }
    }
}
