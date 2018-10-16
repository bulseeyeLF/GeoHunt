package components;

import core.Answer;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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
        VBox vBoxAnswers = new VBox(new Label("Correct answer: "),answer.getAnswerText());
        VBox vBoxPoints = new VBox(new Label ("Points"), answer.getPointsText());
        HBox hBox = new HBox(vBoxAnswers, vBoxPoints);
        hBox.setSpacing(150);
        this.getChildren().add(hBox);
        return this;
    }
}
