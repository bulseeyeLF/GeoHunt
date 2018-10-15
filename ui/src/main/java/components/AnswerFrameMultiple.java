package components;

import core.AnswerMultiple;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class AnswerFrameMultiple extends AnswerFrame {
    private ArrayList<AnswerMultiple> answers;
    private ArrayList<RadioButton> buttons;
    private ArrayList<TextField> answerFields;
    private ToggleGroup validityButtons;
    public AnswerFrameMultiple() {
        super();
        validityButtons = new ToggleGroup();
        buttons = new ArrayList<>();
        answerFields = new ArrayList<>();

        init();
    }

    @Override
    public void init() {
        /*this.setHgap(50);
        this.setVgap(100);*/
        this.setPadding(new Insets(10, 10, 10, 10));
    }

    public AnswerFrameMultiple setAnswer(ArrayList<AnswerMultiple> answers) {
        this.answers = answers;
        answerFields.clear();
        buttons.clear();
        for (AnswerMultiple answer : answers) {
            TextField field = answer.getAnswerText();
            field.setPrefSize(300, 10);
            RadioButton button = new RadioButton();
            button.setToggleGroup(validityButtons);
            field.setStyle("-fx-text-inner-color: red;");
            button.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
                if (isNowSelected) {
                    answer.setCorrect(true);
                    field.setStyle("-fx-text-inner-color: green;");
                } else {
                    answer.setCorrect(false);
                    field.setStyle("-fx-text-inner-color: red;");
                }
            });
            button.setSelected(answer.isCorrect());
            answerFields.add(field);
            buttons.add(button);
        }
        this.getChildren().clear();
        VBox vBoxAnswers = new VBox(10);
        vBoxAnswers.getChildren().add(new Label("Choose the correct answer: "));
        for (int i = 0; i < answers.size(); i++) {
            vBoxAnswers.getChildren().addAll(new HBox(buttons.get(i),answerFields.get(i)) );
        }
        this.getChildren().addAll(vBoxAnswers);
        return this;
    }
}
