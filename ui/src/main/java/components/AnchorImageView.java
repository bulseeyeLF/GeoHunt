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
    private Shapes currentShape;
    private double [] coords =null;
    @Getter
    private ArrayList<Shapes> shapesOnPane;
    private Shapes currentlySelectedShape;

    public AnchorImageView(Image img){
        super();
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        shapesOnPane = new ArrayList<>();
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

    private void saveShape(Shapes currentShape) {
       currentShape.setArea();
        if (currentShape.getArea()>10) {
            currentShape.setSelected(true);
            currentlySelectedShape = currentShape;
            currentShape.shape.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY) {
                    currentlySelectedShape = currentShape;
                    shapesOnPane.forEach(shape -> shape.setSelected(false));
                    currentShape.setSelected(true);
                    redrawShapes(currentShape);
                    log.debug("secondory click");
                }
                event.consume();
            });
            shapesOnPane.add(currentShape);
            redrawShapes(currentlySelectedShape);
            log.debug("Saved");
        }
    }

    private void redrawShapes(Shapes selectedShape) {
        this.getChildren().clear();
        mapImageView.setImage(currentImage);
        this.getChildren().add(mapImageView);
        shapesOnPane.forEach(shape -> {
            if (shape != selectedShape) shape.setSelected(false);
            else shape.setSelected(true);
        });
        this.getChildren().addAll(shapesOnPane.stream().map(Shapes::getShape).collect(Collectors.toList()));

        log.debug("Redrawed");
    }

    private void DoubleClick(MouseEvent event) {
    }

    private void MousePressed(MouseEvent event) {
        x = event.getX();
        y = event.getY();
        event.consume();
    }

    private void MouseReleased(MouseEvent event) {
       if (dragged) {
           if (currentShape instanceof ShapePolygon) {
               currentShape = new ShapePolygon((ShapePolygon) currentShape);
               this.getChildren().add(currentShape.shape);
               saveShape(currentShape);
               currentShape = null;
               log.debug("released");
           }
       }
        dragged = false;
        event.consume();
    }

    private void MouseClicked(MouseEvent event) {
    }

    private void MouseDragged(MouseEvent event) {
        AtomicBoolean contains = new AtomicBoolean(false);
            double xPosition = event.getX();
            double yPosition = event.getY();
            redrawShapes(currentlySelectedShape);
            if (xPosition > currentImage.getWidth()) xPosition = currentImage.getWidth();
            if (yPosition > currentImage.getHeight()) yPosition = currentImage.getHeight();
            if (xPosition < 0) xPosition = 0;
            if (yPosition < 0) yPosition = 0;
            if (currentShape == null) {
                currentShape = new ShapePolygon(event.getX(), event.getY());
            } else {
                ((ShapePolygon) currentShape).addPoint(xPosition, yPosition);
            }
            this.getChildren().add(currentShape.getShape());
            log.debug("dragged");
            dragged = true;
    }

    public void setMapImageView(Image image){
        //graphicsContext.clearRect(0,0,WIDTH,HEIGHT);
        this.getChildren().clear();
        mapImageView.setImage(image);
        currentImage = image;
        this.getChildren().add(mapImageView);
    }
}
