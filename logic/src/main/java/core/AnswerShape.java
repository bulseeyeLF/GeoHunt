package core;

import javafx.scene.control.TextField;
import lombok.Getter;
import org.json.JSONObject;
import shape.ShapeEllipse;
import shape.ShapePolygon;
import shape.Shapes;


public class AnswerShape {

    @Getter
    Shapes answerShape;
    @Getter
    private TextField pointsText;

    public AnswerShape(JSONObject answerShapeJson, String points) {
        if (answerShapeJson!= null){
            int type = answerShapeJson.optInt("type");
            if (type == 0){
                answerShape = new ShapePolygon(answerShapeJson);
            } else if (type == 1) {
                answerShape = new ShapeEllipse(answerShapeJson);
            }
        }

        pointsText = new TextField(points);
        pointsText.setPrefSize(70,70);
    }
}
