package components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lombok.Getter;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AnchorImageView extends AnchorPane {
    @Getter
    //this field has overwritten setter!!!
    private ImageView mapImageView;

    private boolean dragged;
    private static Logger log = Logger.getLogger(CanvasImageView.class);
    private static double WIDTH = 500;
    private static double HEIGHT = 500;
    private Image currentImage;
    private double x,y;
    Shapes currentShape;
    private double [] coords =null;
    ArrayList<Shapes> shapesOnCanvas;

    public AnchorImageView(Image img){
        super();
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        shapesOnCanvas = new ArrayList<>();
        currentImage = img;
        dragged = false;
        mapImageView = new ImageView(img);
        x=y=0;
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
    }

    private void MousePressed(MouseEvent event) {
        x = event.getX();
        y = event.getY();
        event.consume();
    }

    private void MouseReleased(MouseEvent event) {
        if (dragged){
            saveShape(currentShape);
            currentShape = null;
            //TODO implement saving the shape
        }
        dragged = false;
        event.consume();
    }

    private void saveShape(Shapes currentShape) {
        shapesOnCanvas.add(currentShape);
    }

    private void MouseClicked(MouseEvent event) {
    }

    private void MouseDragged(MouseEvent event) {
        double xPosition = event.getX();
        double yPosition = event.getY();
        redrawShapes();
        if (xPosition > WIDTH ) xPosition = WIDTH;
        if (yPosition > HEIGHT) yPosition = HEIGHT;
        if (xPosition < 0) xPosition = 0;
        if (yPosition < 0) yPosition = 0;
       // currentShape= new ShapeEllipse(Math.min(xPosition,x),Math.min(yPosition,y), Math.sqrt(Math.pow((xPosition -x)/2,2)), Math.sqrt(Math.pow((yPosition - y)/2,2)));
         currentShape= new ShapeEllipse(Math.max(x,xPosition),Math.max(y, yPosition), Math.sqrt(Math.pow((xPosition -x)/2,2)), Math.sqrt(Math.pow((yPosition - y)/2,2)));

        currentShape.getShape().setFill(null);
        currentShape.getShape().setStroke(Color.BLACK);
        this.getChildren().add(currentShape.getShape());
        //currentShape.drawShape(xPosition,yPosition);
        ((ShapeEllipse)currentShape).setRadiusXposition(xPosition);
        ((ShapeEllipse)currentShape).setRadiusYposition(yPosition);
        dragged = true;
        event.consume();
    }

    private void redrawShapes() {
        this.getChildren().clear();
        mapImageView = new ImageView(currentImage);
        this.getChildren().add(mapImageView);
        this.getChildren().addAll(shapesOnCanvas.stream().map(Shapes::getShape).collect(Collectors.toList()));
    }

    public void setMapImageView(Image imageView){
        //graphicsContext.clearRect(0,0,WIDTH,HEIGHT);
        this.getChildren().clear();
        currentImage = imageView;
        mapImageView = new ImageView(currentImage);
        this.getChildren().add(mapImageView);
    }
}
