package core;

import javafx.scene.control.TextArea;
import lombok.Data;
import org.apache.log4j.Logger;
import org.json.JSONObject;
@Data
public abstract class Question {

    private static final Logger log = Logger.getLogger(Question.class);

    protected Long timer;
    protected int type;
    protected TextArea questionTextArea;

    public Question(JSONObject jsonQuestion) {
        questionTextArea = new TextArea(
            jsonQuestion.optString("questionText", "")
        );
        timer = jsonQuestion.optLong("timer", 0);
        type = jsonQuestion.optInt("type", 0);
    }

    public abstract JSONObject save();
}
