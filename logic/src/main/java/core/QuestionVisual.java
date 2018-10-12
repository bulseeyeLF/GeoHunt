package core;

import lombok.Getter;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class QuestionVisual extends Question
{

    private static final Logger log = Logger.getLogger(QuestionSingle.class);

    @Getter
    private AnswerShape answerShape;

    public QuestionVisual(JSONObject jsonQuestion) {
        super(jsonQuestion);
        if (jsonQuestion != null) {
            answerShape = new AnswerShape(jsonQuestion.optJSONObject("answerShape"));
        } else {
            answerShape = new AnswerShape(new JSONObject());
        }
    }

    @Override
    public JSONObject save() {
        try {
            log.debug("Save in QuestionVisual");
            return new JSONObject()
                    .put("questionText", this.questionField.getText())
                    .put("timer", this.getTimerSpinner().getValue())
                    .put("type", this.getType())
                    .put("pictureSource",getQuestionPictureSource())
                    .put("answerShape",answerShape);
        } catch (JSONException e) {
            log.error("Error in save UserInput Question",e);
            return null;
        }
    }

}
