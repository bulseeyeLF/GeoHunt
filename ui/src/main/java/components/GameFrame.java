package components;

import core.Question;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import lombok.Data;
import utils.Utils;

import java.util.ArrayList;

@Data
public class GameFrame extends BorderPane {
    //globalno definisan UTILS za ceo module
    static Utils UTILS = Utils.getInstance() ;
    private ArrayList<Question> questions;
    private Image backgroundImage;
    private double WIDTH = UTILS.getScreenWidth() - UTILS.getScreenWidth()/5;
    private double HEIGHT = UTILS.getScreenHeight();
    private Long timer;
    private String backgroundPath;

    public void setBackground(Image background) {
        this.backgroundImage = background;
        ImageView map = new ImageView(this.backgroundImage);
        this.setCenter(map);
        map.setFitHeight(HEIGHT);
        map.setFitWidth(WIDTH);
    }
    //TODO za sta ti sluze ovi konstruktori silni?
    public GameFrame() {
    }
    public GameFrame(Node center) {
        super(center);
    }

    public GameFrame(Node center, Node top, Node right, Node bottom, Node left) {
        super(center, top, right, bottom, left);
    }
}
