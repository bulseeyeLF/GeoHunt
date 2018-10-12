package components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import lombok.Getter;
import org.apache.log4j.Logger;

import static components.GameFrame.UTILS;

public abstract class LayoutBase extends BorderPane {
    @Getter
    OptionMenu menu;
    private static final Logger log = Logger.getLogger(LayoutBase.class);
    public LayoutBase(OptionMenu menu) {
        super();
        this.menu = menu;
        menu.setHgap(230);
        double topInsets = 10;
        double bottomInsets = 50;
        double buttonsHeight = UTILS.getScreenHeight()/10;
        menu.setPadding(new Insets(topInsets , 0, bottomInsets, 25));
        menu.setButtonSizes(buttonsHeight, UTILS.getScreenWidth()/10);
        menu.setAbsHeight(buttonsHeight+topInsets+bottomInsets+10);
        menu.setAlignment(Pos.CENTER);
        this.setHeight(UTILS.getScreenHeight());
       // this.setWidth(UTILS.getScreenWidth());
        this.setStyle("-fx-background-image: url(/main_menu_background.jpeg); -fx-background-repeat: stretch; -fx-opacity: 0.9");
        this.setBottom(menu);
    }
}
