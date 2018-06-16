package components;

import core.MultipleChoiceA;
import core.MultipleChoiceQ;
import core.Question;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import lombok.Data;

import java.util.ArrayList;

@Data
public class MultipleChoiceQFrame extends QuestionFrame {

    private ArrayList<MultipleChoiceA> answers;
    private FlowPane answerTextFieldsPane;
    private BorderPane answersChooserPane;
    private ToggleGroup validityButtons;
    private FlowPane validityButtonsPane;

    public MultipleChoiceQFrame() {
        super();
    }

    @Override
    public QuestionFrame setQuestion(Question question) {
        answers = ((MultipleChoiceQ)question).getAnswers();
        init();
        return this;
    }

    @Override
    public void init() {
        answersChooserPane = new BorderPane();
        answerTextFieldsPane = new FlowPane();
        validityButtonsPane = new FlowPane();
        validityButtons = new ToggleGroup();
        for (MultipleChoiceA answer : answers) {
            answer.getButton().setToggleGroup(validityButtons);
            if (answer.isCorrect()) {
                answer.getButton().setSelected(true);
                (answer.getAnswerTextField()).setStyle("-fx-text-inner-color: green;");
            } else {
                answer.getAnswerTextField().setStyle("-fx-text-inner-color: red;");
            }
            answerTextFieldsPane.getChildren().addAll(answer.getAnswerTextField(), answer.getButton());
        }
        answersChooserPane.setLeft(answerTextFieldsPane);
        answersChooserPane.setRight(validityButtonsPane);
        this.setBottom(answersChooserPane);
    }
}
