package components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.log4j.Logger;

import static components.GameFrame.UTILS;

public class LayoutMain extends LayoutBase {
    private static final Logger log = Logger.getLogger(LayoutMain.class);
    public LayoutMain(OptionMenu menu) {
        super(menu);
        menu.getLogo().setFitWidth(300);
        menu.getLogo().setFitHeight(300);

        this.setHeight(UTILS.getScreenHeight());
        this.setWidth(UTILS.getScreenWidth());
        this.setStyle("-fx-background-image: url(/main_menu_background.jpeg); -fx-background-repeat: round; -fx-opacity: 0.9");
        this.setCenter(menu.getLogo());
    }
}
