package core;

import lombok.Getter;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuestionMultiple extends Question {

    private static final Logger log = Logger.getLogger(QuestionMultiple.class);
    private static final int NUMBER_OF_ANSWERS = 4;

    @Getter
    private ArrayList<AnswerMultiple> answers ;

    public QuestionMultiple(JSONObject jsonQuestion) {
        super(jsonQuestion);
        answers = new ArrayList<>();
        JSONArray answersJson = jsonQuestion.optJSONArray("answers");
        if (answersJson != null) {
            answersJson.forEach(i->{
                JSONObject oneAnswer = (JSONObject)i;
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
            for (int i = 0; i < NUMBER_OF_ANSWERS; i++) {
                answers.add(new AnswerMultiple("", false));
            }
        }
        log.debug("Multiple choice question created");
    }

    @Override
    public JSONObject save() {
        try {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            answers.forEach(a->{
                try {
                    jsonArray
                        .put(new JSONObject()
                            .put("text", a.getAnswerText().getText())
                            .put("correct", a.isCorrect()));
                } catch (JSONException e) {
                    log.error("Error with saving json",e);
                }
            });
            jsonObject
                .put("questionText", this.questionField.getText())
                .put("timer", this.timer)
                .put("type", this.type)
                .put("answers", jsonArray);
            log.debug("Save Multiple Question");
            return jsonObject;
        } catch (JSONException e) {
            log.error("Error in save Multiple Question",e);
            return null;
        }
    }
}
