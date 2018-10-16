package components;

import java.util.Arrays;

public class LayoutChoose extends LayoutBase{
    public LayoutChoose(OptionMenu menu) {
        super(menu);
        this.setStyle("-fx-background-image: url(/back.png); -fx-background-repeat:stretch;");
        menu.setStyle("-fx-background-color: white");
        Arrays.stream(menu.getButtons()).forEach( b-> b.setStyle("-fx-background-color: white"));
        menu.setButtonSizes(40,40);
        menu.getLogo().setFitWidth(300);
        menu.getLogo().setFitHeight(300);
        this.setCenter(menu.getLogo());
    }
}
