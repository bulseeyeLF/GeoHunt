package components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import utils.Utils;

public class LayoutMain extends LayoutBase {

    public LayoutMain(OptionMenu menu) {
        super(menu);
        Utils utils = Utils.getInstance();
        menu.setVgap(5);
        menu.setHgap(5);
        menu.setPadding(new Insets(5, 5, 5, 5));
        menu.setButtonSizes(50, 100);
        utils.setPercentageWidth(menu, 100);
        utils.setPercentageHeight(menu, 30);
        menu.setAlignment(Pos.CENTER);
        this.setBottom(menu);

        Image logo = new Image(LayoutMain.class.getResourceAsStream("logo.png"));
    }
}
