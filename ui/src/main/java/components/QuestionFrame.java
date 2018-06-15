package components;

import core.Question;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import lombok.Getter;

public abstract class QuestionFrame extends BorderPane {
    @Getter
    protected Question question;
    private TextArea inputField;

    QuestionFrame() {
    }

    public String getInputFieldString() {
        return this.inputField.getText().toString();
    }
    public abstract QuestionFrame setQuestion(Question question);
    public abstract void init();
}
