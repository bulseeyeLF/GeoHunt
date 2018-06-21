package components;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MyHandler implements EventHandler<MouseEvent> {

    private final EventHandler<MouseEvent> onDraggedEventHandler;

    private final EventHandler<MouseEvent> onClickedEventHandler;

    private final EventHandler<MouseEvent> onReleasedEventHandler;

    private final EventHandler<MouseEvent> onPressedEventHandler;

    private final EventHandler<MouseEvent> onDoubleCLickEventHandler;


    private boolean dragging = false;


    public MyHandler(EventHandler<MouseEvent> onDraggedEventHandler,
                     EventHandler<MouseEvent> onClickedEventHandler,
                     EventHandler<MouseEvent> onReleasedEventHandler,
                     EventHandler<MouseEvent> onPressedEventHandler,
                     EventHandler<MouseEvent> onDoubleCLickEventHandler) {
        this.onDraggedEventHandler = onDraggedEventHandler;
        this.onClickedEventHandler = onClickedEventHandler;
        this.onReleasedEventHandler = onReleasedEventHandler;
        this.onPressedEventHandler = onPressedEventHandler;
        this.onDoubleCLickEventHandler = onDoubleCLickEventHandler;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            dragging = false;
            this.onPressedEventHandler.handle(event);
        } else if (event.getEventType() == MouseEvent.DRAG_DETECTED) {
            dragging = true;
        } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            onDraggedEventHandler.handle(event);
        } else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            if (!dragging) {
                if (event.getClickCount() == 2){
                    onDoubleCLickEventHandler.handle(event);
                } else {
                    onClickedEventHandler.handle(event);
                }
            }
        } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
            onReleasedEventHandler.handle(event);
        }
    }
}