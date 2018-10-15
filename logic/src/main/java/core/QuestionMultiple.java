package core;

import javafx.scene.control.TextField;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuestionMultiple extends Question {

    private static final Logger log = Logger.getLogger(QuestionMultiple.class);

    @Getter
    private ArrayList<AnswerMultiple> answers ;

    public QuestionMultiple(JSONObject jsonQuestion) {
        super(jsonQuestion);
        answers = new ArrayList<>();
        if (jsonQuestion != null) {
            JSONArray answersJson = jsonQuestion.optJSONArray("answers");
            if (answersJson != null) {
                answersJson.forEach(i -> {
                    JSONObject oneAnswer = (JSONObject) i;
                    if (oneAnswer != null) {
                        answers.add(new AnswerMultiple(
                                oneAnswer.optString("text", ""),
                                oneAnswer.optBoolean("correct", false)
                        ));
                    } else {
                        answers.add(new AnswerMultiple(
                                "",
                                false
                        ));
                    }
                });
            } else {
                createEmptyAnswers();
            }
        } else {
            jsonQuestion = new JSONObject();
            questionField = new TextField(jsonQuestion.optString("questionText", ""));
            timer = jsonQuestion.optLong("timer", 0);
            type = jsonQuestion.optInt("type", 1);
            createEmptyAnswers();
        }
        log.debug("Multiple choice question created");
    }

    public void createEmptyAnswers(){
        for (int i = 0; i < 4; i++) {
            answers.add(new AnswerMultiple("", false));
        }
    }

    @Override
    public JSONObject save() {
        try {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            answers.forEach(a->{
                try {
                    jsonArray.put(new JSONObject()
                        .put("text", a.getAnswerText().getText())
                        .put("correct", a.isCorrect()));
                } catch (JSONException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            });
            jsonObject
                    .put("questionText", this.questionField.getText())
                    .put("timer", this.getTimerSpinner().getValue())
                    .put("type", this.type)
                    .put("answers", jsonArray)
                    .put("pictureSource",getQuestionPictureSource());
            log.debug("Save Multiple Question");
            return jsonObject;
        } catch (JSONException e) {
            log.error("Error in save Multiple Question",e);
            return null;
        }
    }
}
