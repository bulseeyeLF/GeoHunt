package components;

import javafx.scene.layout.BorderPane;
import lombok.Getter;

public class LayoutBase extends BorderPane {
    @Getter
    OptionMenu menu;

    public LayoutBase(OptionMenu menu) {
        this.menu = menu;
    }
}
