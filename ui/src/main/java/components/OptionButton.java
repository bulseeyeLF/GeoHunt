package components;

import com.sun.istack.Nullable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import lombok.Getter;
import lombok.Setter;

public class OptionButton extends Button {
    @Getter
    @Setter
    private Selection action;
    @Getter
    private KeyCode triggerKey;
    //TODO i sta je sa ova 4 konstruktora :D
    public OptionButton() {
    }
    public OptionButton(String text) {
        super(text);
    }

    public OptionButton(String text, Node graphic, Selection action, @Nullable KeyCode trigger) {
        super(text, graphic);
        this.action = action;
        this.triggerKey = trigger;
        //this.setHeight(70);
        //this.setWidth(200);
        //this.setPrefWidth(this.getWidth());
        //this.setPrefHeight(this.getHeight());
        this.setPrefHeight(50);
        this.setPrefWidth(200);
    }

    public OptionButton(Image backgroundImage) {
        super();
        this.setGraphic(new ImageView(backgroundImage));

        //this.setMaxWidth(100);
        //this.setPrefWidth(100);
        //this.setMinWidth(100);
    }
    public void doAction() {
        action.execute();
    }

}
