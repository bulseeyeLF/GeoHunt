package core;

import javafx.scene.control.TextField;
import lombok.Getter;

public class Answer {
    @Getter
    protected TextField answerText;

    public Answer(String text) {
        answerText = new TextField(text);
    }
}
