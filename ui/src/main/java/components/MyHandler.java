package components;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MyHandler implements EventHandler<MouseEvent> {

    private final EventHandler<MouseEvent> onDraggedEventHandler;

    private final EventHandler<MouseEvent> onClickedEventHandler;

    private final EventHandler<MouseEvent> onReleasedEventHandler;

    private final EventHandler<MouseEvent> onDoubleClickEvenHandler;

    private boolean dragging = false;


    public MyHandler(EventHandler<MouseEvent> onDraggedEventHandler,
                     EventHandler<MouseEvent> onClickedEventHandler,
                     EventHandler<MouseEvent> onReleasedEventHandler,
                     EventHandler<MouseEvent> onDoubleClickEvenHandler) {
        this.onDraggedEventHandler = onDraggedEventHandler;
        this.onClickedEventHandler = onClickedEventHandler;
        this.onReleasedEventHandler = onReleasedEventHandler;
        this.onDoubleClickEvenHandler = onDoubleClickEvenHandler;
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
                    onClickedEventHandler.handle(event);
                }
            }
        else if (event.getEventType() == MouseEvent.MOUSE_RELEASED){
                onReleasedEventHandler.handle(event);
        }

    }
}