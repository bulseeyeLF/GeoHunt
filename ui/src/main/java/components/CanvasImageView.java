/*
package components;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import org.apache.log4j.Logger;

import java.util.ArrayList;


public class CanvasImageView extends Canvas {
    @Getter
    //this field has overwritten setter!!!
    private ImageView mapImageView;

    private boolean dragged;
    final private GraphicsContext graphicsContext = this.getGraphicsContext2D();
    private static Logger log = Logger.getLogger(CanvasImageView.class);
    private static double WIDTH = 500;
    private static double HEIGHT = 500;
    private Image currentImage;
    private double x,y;
    Shapes currentShape;
    private double [] coords =null;
    ArrayList<Shapes> shapesOnCanvas;


    public CanvasImageView(Image img){
        super(WIDTH,HEIGHT);
        shapesOnCanvas = new ArrayList<>();
        currentImage = img;
        dragged = false;
        mapImageView = new ImageView(img);
        x=y=0;
        graphicsContext.drawImage(img, 0, 0, WIDTH,HEIGHT);

        this.addEventHandler(MouseEvent.ANY,
                new MyHandler(
                        this::MouseDragged,
                        this::MouseClicked,
                        this::MouseReleased,
                        this::MousePressed,
                        this::DoubleClick)
        );
    }

    private ArrayList<Shapes> loadShapes (){

        return  new ArrayList<>();
    }

    private void redrawShapes() {
        graphicsContext.clearRect(0,0,WIDTH,HEIGHT);
        graphicsContext.drawImage(currentImage, 0, 0, WIDTH,HEIGHT);
        shapesOnCanvas.forEach(shapes -> shapes.drawShape(graphicsContext));
        //TODO implement reading shapes from file and drawing them on canvas
    }

    private void saveShape(Shapes currentShape) {
        shapesOnCanvas.add(currentShape);
    }

    private void MousePressed(MouseEvent event) {
        x = event.getX();
        y = event.getY();
        event.consume();
    }

    public void MouseDragged(MouseEvent event) {
        double xPosition = event.getX();
        double yPosition = event.getY();
        redrawShapes();
        if (xPosition > WIDTH ) xPosition = WIDTH;
        if (yPosition > HEIGHT) yPosition = HEIGHT;
        if (xPosition < 0) xPosition = 0;
        if (yPosition < 0) yPosition = 0;
        currentShape= new ShapeEllipse(x,y, Math.sqrt(Math.pow(xPosition -x,2)), Math.sqrt(Math.pow(yPosition - y,2)));
        currentShape.drawShape(graphicsContext, xPosition,yPosition);
        ((ShapeEllipse)currentShape).setRadiusXposition(xPosition);
        ((ShapeEllipse)currentShape).setRadiusYposition(yPosition);
        dragged = true;
        event.consume();
    }



    public void MouseReleased(MouseEvent event){
        if (dragged){
            saveShape(currentShape);
            currentShape = null;
            //TODO implement saving the shape
        }
        dragged = false;
        event.consume();
    }

    public void MouseClicked(MouseEvent event) {
        //TODO implement selecting the shape
        double xPosition = event.getX();
        double yPosition = event.getY();
        redrawShapes();
        if (xPosition > WIDTH ) xPosition = WIDTH;
        if (yPosition > HEIGHT) yPosition = HEIGHT;
        if (xPosition < 0) xPosition = 0;
        if (yPosition < 0) yPosition = 0;
       if (currentShape == null){
           currentShape = new ShapePolygon(event.getX(),event.getY());
       }else {
           ((ShapePolygon) currentShape).addPoint(xPosition, yPosition);
       }
       currentShape.drawShape(graphicsContext, xPosition,yPosition);

        event.consume();
    }

    private void DoubleClick(MouseEvent event) {
        if (currentShape instanceof ShapePolygon){
            currentShape.drawShape(graphicsContext, event.getX(), event.getY(), 1);
            saveShape(currentShape);
            currentShape = null;
        }
        event.consume();
    }



    public void setMapImageView(Image imageView){
        graphicsContext.clearRect(0,0,WIDTH,HEIGHT);
        mapImageView.setImage(imageView);
        currentImage = imageView;
        graphicsContext.drawImage(imageView,0,0,WIDTH,HEIGHT);
    }

}
*/
