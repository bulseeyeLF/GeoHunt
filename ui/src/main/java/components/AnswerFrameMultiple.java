package components;

import core.AnswerMultiple;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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
        this.setHgap(50);
        this.setVgap(100);
        this.setPadding(new Insets(10, 10, 10, 10));
    }

    public AnswerFrameMultiple setAnswer(ArrayList<AnswerMultiple> answers) {
        this.answers = answers;
        answerFields.clear();
        buttons.clear();
        for (AnswerMultiple answer : answers) {
            TextField field = answer.getAnswerText();
            field.setPrefSize(100, 50);
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
        for (int i = 0; i < answers.size(); i++) {
            this.getChildren().addAll(answerFields.get(i), buttons.get(i));
        }
        return this;
    }
}
