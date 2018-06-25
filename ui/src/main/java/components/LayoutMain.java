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
        menu.setHgap(50);
        menu.setPadding(new Insets(5, 5, 100, 5));
        log.debug(UTILS.getScreenHeight());
        menu.setButtonSizes(UTILS.getScreenHeight()/10, UTILS.getScreenWidth()/9);
        log.debug(menu.getWidth());
        log.debug(menu.getWidth());
        menu.setAlignment(Pos.CENTER);
        ImageView logo = new ImageView (new Image(LayoutMain.class.getResourceAsStream("/logo.png")));
        logo.setFitWidth(UTILS.getScreenWidth()/5);
        logo.setFitHeight(UTILS.getScreenHeight()/5);

        this.setHeight(UTILS.getScreenHeight());
        this.setWidth(UTILS.getScreenWidth());
        this.setStyle("-fx-background-image: url(/main_menu_background.jpg); -fx-background-repeat: stretch;");
        this.setCenter(logo);
        this.setBottom(menu);
    }
}
