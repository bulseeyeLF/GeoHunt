package core;

import lombok.Getter;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class QuestionSingle extends Question {

    private static final Logger log = Logger.getLogger(QuestionSingle.class);

    @Getter
    private Answer answer;

    public QuestionSingle(JSONObject jsonQuestion) {
        super(jsonQuestion);
        if (jsonQuestion != null) {
            answer = new Answer(jsonQuestion.optString("text", ""));
        } else {
            answer = new Answer(null);
        }
    }

    @Override
    public JSONObject save() {
        try {
            log.debug("Save in QuestionSingle");
            return new JSONObject()
                .put("questionText", this.questionField.getText())
                .put("timer", this.timer)
                .put("text", answer.getAnswerText().getText())
                .put("type", this.getType());
        } catch (JSONException e) {
            log.error("Error in save UserInput Question",e);
            return null;
        }
    }

}
