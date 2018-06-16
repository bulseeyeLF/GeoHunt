package components;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.paint.Color;
import org.apache.log4j.Logger;

public class MapImageView extends ImageView {

    private static Logger log = Logger.getLogger(MapImageView.class);
    private boolean dragged;
    private boolean released;

    public MapImageView(Image img){
        super(img);
       this.addEventHandler(MouseEvent.ANY, new MyHandler(this::DragDetected, this::MouseClicked, this::MouseReleased));
        dragged = false;
        released = false;
    }

    private void DragDetected(MouseEvent event) {
        log.debug("Mouse dragged");
        dragged = true;

    }

    private void MouseClicked(MouseEvent event) {
        log.debug("Mouse Clicked");
    }

    private void MouseReleased(MouseEvent event){
       if (dragged){
           log.debug("Mouse released");
       }
        dragged = false;
    }


}
