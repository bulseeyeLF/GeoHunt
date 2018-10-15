package components;

import core.Answer;
import core.AnswerShape;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class AnswerFrameVisual extends AnswerFrame {
    private AnswerShape answer;
    public AnswerFrameVisual() {
        super();
        init();
    }
    @Override
    public void init() {
        this.setPadding(new Insets(10, 10, 10, 10));
    }

    public AnswerFrameVisual setAnswer(AnswerShape answer) {
        this.answer = answer;
        this.getChildren().clear();
        this.getChildren().add(new Label("Answer is one of the shapes on your map"));
        return this;
    }
}
