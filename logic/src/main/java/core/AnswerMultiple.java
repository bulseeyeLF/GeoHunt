package core;

import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

public class AnswerMultiple extends Answer {

    private static final Logger log = Logger.getLogger(AnswerMultiple.class);

    @Getter
    @Setter
    private boolean correct;

    AnswerMultiple(String text, boolean correct) {
        super(text);
        this.correct = correct;
        log.debug("Multiple choice answer created");
    }

}
