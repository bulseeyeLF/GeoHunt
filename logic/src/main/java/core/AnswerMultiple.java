package core;

import javafx.scene.control.RadioButton;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

public class MultipleChoiceA extends Answer {

    private static final Logger log = Logger.getLogger(MultipleChoiceA.class);

    @Getter
    @Setter
    private boolean correct;

    MultipleChoiceA(String text, boolean correct) {
        super(text);
        this.correct = correct;
        log.debug("Multiple choice answer created");
    }

}
