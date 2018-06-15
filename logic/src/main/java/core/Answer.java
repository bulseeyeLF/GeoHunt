package core;

import javafx.scene.control.TextField;
import lombok.Getter;

public class Answer {
    @Getter
    protected TextField answerTextField;

    public Answer(String text) {
        this.answerTextField = new TextField(text);}
}
