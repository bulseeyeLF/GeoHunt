package components.Shapes;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ShapesHandler implements EventHandler<MouseEvent> {
    private final EventHandler<MouseEvent>  onEnteredShapeEventHandler;
    private final EventHandler<MouseEvent>  onExitedShapeEventHandler;


    private boolean dragging = false;

    public ShapesHandler(EventHandler<MouseEvent> onEnteredShapeEventHandler, EventHandler<MouseEvent> onExitedShapeEventHandler){
        this.onEnteredShapeEventHandler = onEnteredShapeEventHandler;
        this.onExitedShapeEventHandler = onExitedShapeEventHandler;

    }

    @Override
    public void handle(MouseEvent event) {
         if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            this.onEnteredShapeEventHandler.handle(event);
        } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
            this.onExitedShapeEventHandler.handle(event);
        }
    }
}