package components;

import com.sun.istack.Nullable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.TextAlignment;
import lombok.Getter;
import lombok.Setter;

public class OptionButton extends Button {
    @Getter
    @Setter
    private Selection action;
    @Getter
    private KeyCode triggerKey;

    public OptionButton(String text, Node graphic, Selection action, KeyCode trigger) {
        super(text, graphic);
        this.setStyle("-fx-alignment: CENTER;");
        this.action = action;
        this.triggerKey = trigger;
    }

    public void doAction() {
        action.execute();
    }

    public OptionButton setSize(double h, double w) {
        this.setPrefHeight(h);
        this.setPrefWidth(w);
        return this;
    }

}
