package components;

import core.Answer;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AnswerFrameSingle extends AnswerFrame {

    private Answer answer;
    public AnswerFrameSingle() {
        super();
        init();
    }
    @Override
    public void init() {
        /*this.setPrefHeight(125);*/
        this.setPadding(new Insets(10, 10, 10, 10));
    }

    public AnswerFrameSingle setAnswer(Answer answer) {
        this.answer = answer;
        this.getChildren().clear();
        this.getChildren().add(new VBox(new Label("Correct answer: "),answer.getAnswerText()));
        return this;
    }
}
