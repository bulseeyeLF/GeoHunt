package core;

import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class QuestionVisual extends Question
{

    private static final Logger log = Logger.getLogger(QuestionSingle.class);

    @Getter
    @Setter
    private AnswerShape answerShape;

    public QuestionVisual(JSONObject jsonQuestion) {
        super(jsonQuestion);
        if (jsonQuestion != null){
            answerShape = new AnswerShape(jsonQuestion.optJSONObject("answerShape"), jsonQuestion.optString("points"));
        }else {
            jsonQuestion = new JSONObject();
            questionField = new TextField(jsonQuestion.optString("questionText", ""));
            timer = jsonQuestion.optLong("timer", 0);
            type = jsonQuestion.optInt("type", 2);
            answerShape = new AnswerShape(null, null);
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
                    .put("answerShape",answerShape.getAnswerShape().save())
                    .put("points", answerShape.getPointsText().getText());
        } catch (JSONException e) {
            log.error("Error in save UserInput Question",e);
            return null;
        }
    }

    //TODO problemi: prolazak kroz pitanja bez kliktanja na povrsine za igru svakako treba implementirati
    //TODO problemi: obelezene na poseban nacin non-clickable povrsine koje su potencijalni odgovor na neko pitanje - game play
    //TODO problemi: ne moramo mozda da imamo non clickable povrsine zato sto je ovo apps.editor i u editoru ne moramo da krijemo odgovor
    ///TODO problemi: samo necemo imati polje za odgovor ali treba napraviti dobru strukturu da moze posle sa tim da se igra
    //???? problemi: U sustini samo u onom delu nakon sto smo napravili pitanje treba taj shape sacuvati kao objekat ili kao json +type u questionvisual koji ima polje answeshape


}
