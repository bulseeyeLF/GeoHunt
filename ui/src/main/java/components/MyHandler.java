package components;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MyHandler implements EventHandler<MouseEvent> {

    private final EventHandler<MouseEvent> onDraggedEventHandler;

    private final EventHandler<MouseEvent> onClickedEventHandler;

    private final EventHandler<MouseEvent> onReleasedEventHandler;

    private boolean dragging = false;
    private boolean clicked = false;

    public MyHandler(EventHandler<MouseEvent> onDraggedEventHandler,
                     EventHandler<MouseEvent> onClickedEventHandler,
                     EventHandler<MouseEvent> onReleasedEventHandler) {
        this.onDraggedEventHandler = onDraggedEventHandler;
        this.onClickedEventHandler = onClickedEventHandler;
        this.onReleasedEventHandler = onReleasedEventHandler;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            dragging = false;
        }
        else if (event.getEventType() == MouseEvent.DRAG_DETECTED) {
            dragging = true;
        }
        else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            //maybe filter on dragging (== true)
            onDraggedEventHandler.handle(event);
        }
        else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            if (!dragging) {
                clicked = true;
                onClickedEventHandler.handle(event);
            }
        }
        else if (event.getEventType() == MouseEvent.MOUSE_RELEASED){
                onReleasedEventHandler.handle(event);
        }


    }
}