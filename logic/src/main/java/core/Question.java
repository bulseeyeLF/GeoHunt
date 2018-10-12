package core;

import javafx.geometry.Insets;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.json.JSONObject;
@Data
public abstract class Question {

    private static final Logger log = Logger.getLogger(Question.class);

    protected Long timer;
    protected int type;
    protected TextField questionField;
    private JSONObject jsonQuestion;
    @Setter
    @Getter
    private String questionPictureSource;
    @Getter
    Spinner timerSpinner;

    public Question(JSONObject jsonQuestion) {
        if (jsonQuestion == null) {
            jsonQuestion = new JSONObject();
            this.jsonQuestion = jsonQuestion;
        }
        questionField = new TextField(jsonQuestion.optString("questionText", ""));
        timer = jsonQuestion.optLong("timer", 0);
        type = jsonQuestion.optInt("type", 0);
        questionPictureSource = jsonQuestion.optString("pictureSource",null);
        timerSpinner = new Spinner(0, 1000, timer);
    }

    public abstract JSONObject save();
}
