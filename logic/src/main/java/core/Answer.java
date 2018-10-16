package core;

import javafx.scene.control.TextField;
import lombok.Getter;

public class Answer {
    @Getter
    protected TextField answerText;
    @Getter
    protected TextField pointsText;
    //TODO dodati poene pored koliko nosi tacan odgovor
    public Answer(String text, String points) {
        answerText = new TextField(text);
        answerText.setPrefSize(200,20);
        pointsText = new TextField(points);
        pointsText.setPrefSize(70,70);
    }
}
