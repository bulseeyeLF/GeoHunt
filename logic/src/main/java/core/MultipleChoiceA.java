package core;

import javafx.scene.control.RadioButton;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

public class MultipleChoiceA extends Answer {

    private static final Logger log = Logger.getLogger(MultipleChoiceA.class);

    @Getter
    private RadioButton button;
    @Getter
    @Setter
    private boolean correct;

    MultipleChoiceA(String text, boolean correct) {
        super(text);
        button = new RadioButton();
        button.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            if (isNowSelected) {
                setCorrect(true);
                answerTextField.setStyle("-fx-text-inner-color: green;");
            } else {
                setCorrect(false);
                answerTextField.setStyle("-fx-text-inner-color: red;");
            }
        });
        this.correct = correct;
        log.debug("Multiple choice answer created");
    }

}
