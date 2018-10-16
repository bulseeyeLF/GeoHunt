package core;

import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

public class AnswerMultiple extends Answer {

    private static final Logger log = Logger.getLogger(AnswerMultiple.class);

    @Getter
    @Setter
    private boolean correct;

    AnswerMultiple(String text, String points, boolean correct) {
        super(text, points);
        this.correct = correct;
        log.debug("Multiple choice answer created");
    }

}
