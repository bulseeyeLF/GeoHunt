package components;

import javafx.scene.layout.FlowPane;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public abstract class AnswerFrame extends FlowPane{
    @Getter
    @Setter
    protected TextField textFieldPoints;
    public AnswerFrame() {
        super();
    }
    public abstract void init();
}
