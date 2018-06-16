package components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.apache.log4j.Logger;

public class MapImageView extends ImageView {

    private static Logger log = Logger.getLogger(MapImageView.class);
    private boolean dragged;
    private boolean doubleclicked;

    public MapImageView(Image img){
        super(img);
       this.addEventHandler(MouseEvent.ANY,
               new MyHandler(
                       this::DragDetected,
                       this::MouseClicked,
                       this::MouseReleased,
                       this::DoubleClick)
       );
        dragged = false;
    }

    private void DoubleClick(MouseEvent event) {
        log.debug("Double click");
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
