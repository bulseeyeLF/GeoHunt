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

import java.io.InputStream;
import java.util.HashMap;
import java.util.stream.Stream;

public class OptionMenu extends FlowPane {

    private static final Logger log = Logger.getLogger(OptionMenu.class);

    @Getter
    private OptionButton buttons[];
    private HashMap<KeyCode, OptionButton> possibleActions;
    private String name;
    private static String[] TEXT;
    private static String[] HINT_TEXT;
    private static Selection[] ACTIONS;
    private static KeyCode[] TRIGGER_KEYS;

    public OptionMenu(String name, OptionButton[] buttons, String[] text, String[] hint, Selection[] actions, KeyCode[] triggers) {
        possibleActions = new HashMap<>();
        this.name = name;
        this.buttons = buttons;
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

    public OptionMenu(Orientation orientation) {
        super(orientation);
    }

    public OptionMenu(double hgap, double vgap) {
        super(hgap, vgap);
    }

    public OptionMenu(Orientation orientation, double hgap, double vgap) {
        super(orientation, hgap, vgap);
    }

    public OptionMenu(Node... children) {
        super(children);
    }

    public OptionMenu(Orientation orientation, Node... children) {
        super(orientation, children);
    }

    public OptionMenu(double hgap, double vgap, Node... children) {
        super(hgap, vgap, children);
    }

    public OptionMenu(Orientation orientation, double hgap, double vgap, Node... children) {
        super(orientation, hgap, vgap, children);
    }
    private void init() {
        this.setPadding(new Insets(5, 0, 5, 0));
        this.setVgap(4);
        this.setHgap(4);
        this.setPrefWrapLength(170); // preferred width allows for two columns
        this.setStyle("-fx-background-color: DAE6F3;");
        log.debug("TEXT length" + TEXT.length);
        for (int i = 0; i < TEXT.length; i++) {
            buttons[i] = new OptionButton(
                TEXT[i],
                new ImageView(
                    new Image(
                        OptionMenu.class.getResourceAsStream(  "/"+this.name + "_button_" + i + ".png")
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