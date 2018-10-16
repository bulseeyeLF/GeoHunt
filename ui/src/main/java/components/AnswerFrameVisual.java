package components;

import core.Answer;
import core.AnswerShape;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
        VBox vBoxPoints = new VBox(new Label ("Points"), answer.getPointsText());
        HBox hBox = new HBox(new Label("Answer is one of the \nshapes on your map"), vBoxPoints);
        hBox.setSpacing(210);
        this.getChildren().add(hBox);
        return this;
    }
}
