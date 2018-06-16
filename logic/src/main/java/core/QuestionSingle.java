package core;

import lombok.Getter;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class UserInputQ extends Question {

    private static final Logger log = Logger.getLogger(UserInputQ.class);

    @Getter
    private Answer answer;

    public UserInputQ(JSONObject jsonQuestion) {
        super(jsonQuestion);
        answer = new Answer(jsonQuestion.optString("text", ""));
    }

    @Override
    public JSONObject save() {
        try {
            log.debug("Save in UserInputQ");
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
