package core;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.text.Font;
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
    private Button addPictureButton;
    private JSONObject jsonQuestion;
    @Setter
    @Getter
    private String questionPictureSource;
    @Getter
    Spinner timerSpinner;

    //UI labele
    protected Label questionLabel;
    protected Label pictureLabel;
    protected Label timerLabel;
    protected Label answerLabel;

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
        addPictureButton = new Button();
        questionLabel = new Label("Question:");
        questionLabel.setFont(new Font("Arial",12));
        pictureLabel = new Label("  Add \npicture");

        timerLabel = new Label("Set your time limitation for this question");
    }

    public abstract JSONObject save();
    //TODO dodati feature-e : labele za objasnjenja,broj poena koji donosi tacan odgovor na pitanje, pomoc takmicaaru HINT
}
