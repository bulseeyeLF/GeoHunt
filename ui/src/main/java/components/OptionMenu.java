package components;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import lombok.Getter;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class OptionMenu extends FlowPane {

    private static final Logger log = Logger.getLogger(OptionMenu.class);

    @Getter
    private OptionButton buttons[];
    private String name;
    private static String[] TEXT;
    private static String[] HINT_TEXT;
    private static Selection[] ACTIONS;
    private static KeyCode[] TRIGGER_KEYS;
    private HashMap<KeyCode, OptionButton> possibleActions;


    public OptionMenu(String name, String[] text, String[] hint, Selection[] actions, KeyCode[] triggers) {
        possibleActions = new HashMap<>();
        this.name = name;
        this.buttons = new OptionButton[text.length];
        TEXT = text;
        HINT_TEXT = hint;
        ACTIONS = actions;
        TRIGGER_KEYS = triggers;
        init();
        for (OptionButton button : buttons) {
            possibleActions.put(button.getTriggerKey(), button);
        }
        possibleActions.put(KeyCode.ENTER, new OptionButton(null, null, this::selectSelectedButton, null));
    }

    private void init() {
        log.debug("TEXT length" + TEXT.length);
        for (int i = 0; i < TEXT.length; i++) {
            log.debug("/buttons/" + this.name + "_button_" + i + ".png");
            buttons[i] = new OptionButton(
                TEXT[i],
                new ImageView(
                    new Image(
                        OptionMenu.class.getResourceAsStream(  "/buttons/" + this.name + "_button_" + i + ".png")
                    )
                ),
                ACTIONS[i],
                TRIGGER_KEYS[i]
            );
            buttons[i].setTooltip(new Tooltip(HINT_TEXT[i]));
            buttons[i].setOnMouseClicked(event -> ((OptionButton)event.getSource()).getAction().execute());
            this.getChildren().add(buttons[i]);
        }
        log.debug("init finished");
    }

    public void setButtonSizes(double h, double w) {
        for (OptionButton button: buttons) {
            button.setSize(h, w);
        }
    }
    public void pressButon(KeyCode code) {
        possibleActions.getOrDefault(code, new OptionButton(null, null, ()->{}, null)).doAction();
        log.debug("Button pressed");
    }
    public void selectSelectedButton() {
        for (OptionButton button:buttons
             ) {
            if (button.isFocused()) {
                button.getAction().execute();
            }
        }
    }
}
