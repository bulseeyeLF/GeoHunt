package components;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.EventListener;


public class MapImageView extends ImageView implements EventHandler {

    public MapImageView(Image img){
        super(img);
    }

    @Override
    public void handle(Event event) {

    }
}
