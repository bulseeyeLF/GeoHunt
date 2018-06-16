package components;

import core.Answer;
import javafx.geometry.Insets;

public class AnswerFrameSingle extends AnswerFrame {

    private Answer answer;
    public AnswerFrameSingle() {
        super();
        init();
    }
    @Override
    public void init() {
        this.setPrefHeight(125);
        this.setPadding(new Insets(10, 10, 10, 10));
    }

    public AnswerFrameSingle setAnswer(Answer answer) {
        this.answer = answer;
        this.getChildren().removeAll();
        this.getChildren().add(answer.getAnswerText());
        return this;
    }
}
