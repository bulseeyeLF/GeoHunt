package core;

import lombok.Getter;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MultipleChoiceQ extends Question {

    private static final Logger log = Logger.getLogger(MultipleChoiceQ.class);

    @Getter
    private ArrayList<MultipleChoiceA> answers ;

    public MultipleChoiceQ(JSONObject jsonQuestion) {
        super(jsonQuestion);
        answers = new ArrayList<>();
        JSONArray answersJson = jsonQuestion.optJSONArray("answers");
        if (answersJson != null) {
            for (int i = 0; i < 4; i++) {
                JSONObject oneAnswer = answersJson.optJSONObject(i);
                if (oneAnswer != null) {
                    answers.add(new MultipleChoiceA(
                        oneAnswer.optString("text", ""),
                        oneAnswer.optBoolean("correct", false)
                    ));
                } else {
                    answers.add(new MultipleChoiceA(
                        "",
                        false
                    ));
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                answers.add(new MultipleChoiceA("", false));
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
                    jsonArray.put(new JSONObject().put("text", a.getAnswerTextField().getText()).put("correct", a.isCorrect()));
                } catch (JSONException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            });
            jsonObject.put("questionText", this.questionTextArea.getText())
                    .put("timer", this.timer)
                    .put("type", this.type);
            jsonObject.put("answers",jsonArray);
            log.debug("Save Multiple Question");
            return jsonObject;
        } catch (JSONException e) {
            log.error("Error in save Multiple Question",e);
            return null;
        }
    }
}
