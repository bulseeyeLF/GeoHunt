package core;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Data;
import org.apache.log4j.Logger;
import org.json.JSONObject;
@Data
public abstract class Question {

    private static final Logger log = Logger.getLogger(Question.class);

    protected Long timer;
    protected int type;
    protected TextField questionField;

    public Question(JSONObject jsonQuestion) {
        questionField = new TextField(jsonQuestion.optString("questionText", ""));
        timer = jsonQuestion.optLong("timer", 0);
        type = jsonQuestion.optInt("type", 0);
    }

    public abstract JSONObject save();
}
