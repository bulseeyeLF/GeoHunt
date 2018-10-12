package components;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import java.util.HashMap;

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
    @Getter
    @Setter
    private double absHeight;
    @Getter
    private ImageView logo;


    public OptionMenu(String name, String[] text, String[] hint, Selection[] actions, KeyCode[] triggers) {
        this.setStyle("-fx-background-color:  #4d4d4d");
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
        for (int i = 0; i < TEXT.length; i++) {
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
            buttons[i].setStyle("-fx-background-radius: 250px; -fx-font-family: fantasy;");
            buttons[i].setSize(300,300);
            this.getChildren().add(buttons[i]);
        }
        logo = new ImageView (new Image(LayoutMain.class.getResourceAsStream("/logo.png")));
        logo.setFitWidth(100);
        logo.setFitHeight(100);
        this.getChildren().add(logo);
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
        for (OptionButton button: buttons) {
            if (button.isFocused()) {
                button.getAction().execute();
            }
        }
    }
}
