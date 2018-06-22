package components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class AnchorImageView extends AnchorPane {
    @Getter
    //this field has overwritten setter!!!
    private ImageView mapImageView;

    private boolean dragged;
    private static Logger log = Logger.getLogger(AnchorImageView.class);
    private static double WIDTH = 600;
    private static double HEIGHT = 600;
    private Image currentImage;
    private double x,y;
    Shapes currentShape;
    private double [] coords =null;
    ArrayList<Shapes> shapesOnCanvas;
    Shapes currentlySelectedShape;

    public AnchorImageView(Image img){
        super();
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        shapesOnCanvas = new ArrayList<>();
        currentImage = img;
        dragged = false;
        mapImageView = new ImageView(img);
        x=y=0;
        currentlySelectedShape = null;

        this.addEventHandler(MouseEvent.ANY,
                new MyHandler(
                        this::MouseDragged,
                        this::MouseClicked,
                        this::MouseReleased,
                        this::MousePressed,
                        this::DoubleClick)
        );
    }

    private void DoubleClick(MouseEvent event) {
        if (currentShape instanceof ShapePolygon){
            currentShape = new ShapePolygon((ShapePolygon)currentShape);
            this.getChildren().add(currentShape.shape);
            currentlySelectedShape = currentShape;
            saveShape(currentShape);
            currentShape = null;
        }
        log.debug("double click");
        event.consume();
    }

    private void MousePressed(MouseEvent event) {
        x = event.getX();
        y = event.getY();
        log.debug("pressed");
        event.consume();

    }

    private void MouseReleased(MouseEvent event) {
        log.debug("dragged bool ??? : " +dragged);
        if (dragged){
            currentlySelectedShape = currentShape;
            saveShape(currentShape);
            log.debug("Released");
            currentShape = null;
        }
        dragged = false;
        event.consume();
    }

    private void saveShape(Shapes currentShape) {
        currentShape.setSelected(true);
        currentlySelectedShape = currentShape;
        currentShape.shape.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                currentlySelectedShape = currentShape;
                shapesOnCanvas.forEach(shape -> shape.setSelected(false));
                currentShape.setSelected(true);
                redrawShapes(currentShape);
                log.debug("Selected ");
            }
            event.consume();
        });
        shapesOnCanvas.add(currentShape);
        redrawShapes(currentlySelectedShape);
    }

    private void MouseClicked(MouseEvent event) {
       /* AtomicBoolean contains = new AtomicBoolean(false);
        shapesOnCanvas.stream()
                .map(Shapes::getShape)
                .forEach(shape ->
                        contains.set(contains.get() || shape.contains(event.getX(), event.getY()))
                );*/
        log.debug("currently selected shape : " + currentlySelectedShape.getShape().toString());
            double xPosition = event.getX();
            double yPosition = event.getY();
            redrawShapes(currentlySelectedShape);
            if (xPosition > WIDTH) xPosition = WIDTH;
            if (yPosition > HEIGHT) yPosition = HEIGHT;
            if (xPosition < 0) xPosition = 0;
            if (yPosition < 0) yPosition = 0;
            if (currentShape == null) {
                currentShape = new ShapePolygon(event.getX(), event.getY());
            } else {
                ((ShapePolygon) currentShape).addPoint(xPosition, yPosition);
            }
            this.getChildren().add(currentShape.getShape());
        log.debug("Clicked");
        event.consume();
    }

    private void MouseDragged(MouseEvent event) {
        AtomicBoolean contains = new AtomicBoolean(false);
        /*shapesOnCanvas.stream()
                .map(Shapes::getShape)
                .forEach(shape ->
                        contains.set(contains.get() || shape.contains(event.getX(), event.getY()))
                );*/
            double xPosition = event.getX();
            double yPosition = event.getY();
            redrawShapes(currentlySelectedShape);
            if (xPosition> WIDTH ) xPosition = WIDTH ;
            if (yPosition> HEIGHT) yPosition = HEIGHT;
            if (xPosition < 0) xPosition = 0 ;
            if (yPosition < 0) yPosition = 0 ;
            currentShape= new ShapeEllipse((xPosition + x) / 2, (yPosition + y) / 2, Math.abs((xPosition - x) / 2), Math.abs(yPosition - y) / 2);
            this.getChildren().add(currentShape.getShape());
            //currentShape.drawShape(xPosition,yPosition);
            ((ShapeEllipse)currentShape).setRadiusXposition(xPosition);
            ((ShapeEllipse)currentShape).setRadiusYposition(yPosition);
            dragged = true;
            log.debug("dragged");
        event.consume();
    }

    private void redrawShapes(Shapes selectedShape) {
        this.getChildren().clear();
        mapImageView.setImage(currentImage);
        this.getChildren().add(mapImageView);
        shapesOnCanvas.forEach(shape -> {
            if (shape != selectedShape) shape.setSelected(false);
            else shape.setSelected(true);
        });
        this.getChildren().addAll(shapesOnCanvas.stream().map(Shapes::getShape).collect(Collectors.toList()));
    }

    public void setMapImageView(Image image){
        //graphicsContext.clearRect(0,0,WIDTH,HEIGHT);
        this.getChildren().clear();
        mapImageView.setImage(image);
        currentImage = image;
        this.getChildren().add(mapImageView);
    }
}
