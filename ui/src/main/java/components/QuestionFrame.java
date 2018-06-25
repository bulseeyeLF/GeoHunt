package components;

import core.Answer;
import core.Question;
import core.QuestionMultiple;
import core.QuestionSingle;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import lombok.Getter;

import java.util.ArrayList;

import static components.GameFrame.UTILS;

public class QuestionFrame extends BorderPane {
    private AnswerFrame currentFrame;
    private AnswerFrameMultiple mulipleFrame;
    private AnswerFrameSingle singleFrame;
    @Getter
    protected Question question;
    protected TextField inputField;

    QuestionFrame() {
        UTILS.setPercentageWidth(this, 40);
        UTILS.setPercentageHeight(this, 80);
        inputField = new TextField();
        mulipleFrame = new AnswerFrameMultiple();
        mulipleFrame.setPrefWidth(500);
        singleFrame = new AnswerFrameSingle();
        singleFrame.setPrefHeight(100);
        this.setPadding(new Insets(100, 50, 100, 50));

    }

    public void setQuestion(Question question) {
        this.question = question;
        this.inputField = question.getQuestionField();
        if (question.getType() == 0) {
            currentFrame = singleFrame.setAnswer(((QuestionSingle) question).getAnswer());
        } else {
            currentFrame = mulipleFrame.setAnswer(((QuestionMultiple) question).getAnswers());
        }

        this.setTop(inputField);
        this.setBottom(currentFrame);
    }

}
