package components;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import lombok.Getter;
import org.apache.log4j.Logger;


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
    private double x=0,y =0;


    public CanvasImageView(Image img){
        super(WIDTH,HEIGHT);
        currentImage = img;
        dragged = false;
        mapImageView = new ImageView(img);
        graphicsContext.drawImage(img, 0, 0, WIDTH,HEIGHT);

        this.addEventHandler(MouseEvent.ANY,
                new MyHandler(
                        this::MouseDragged,
                        this::MouseClicked,
                        this::MouseReleased,
                        this::MousePressed)
        );
    }

    public void MouseDragged(MouseEvent event) {

        //TODO implement drawing circle instead of a line
        //TODO implement instanciating Circle
        graphicsContext.clearRect(0,0,WIDTH,HEIGHT);
        draw();
        ShapeEllipse shapeEllipse = new ShapeEllipse(x,y, Math.sqrt(Math.pow(event.getX() -x,2)), Math.sqrt(Math.pow(event.getY() - y,2)));
        shapeEllipse.drawShape(graphicsContext, event.getX(), event.getY());
        log.debug("Mouse dragged");
        dragged = true;

    }

    private void MousePressed(MouseEvent event) {
        log.debug("Mouse pressed");
        //TODO maybe initialization of circle?
        x = event.getX();
        y = event.getY();
    }

    private void draw() {
        graphicsContext.drawImage(currentImage, 0, 0, WIDTH,HEIGHT);
        //TODO implement reading shapes from file and drawing them on canvas

    }

    public void MouseClicked(MouseEvent event) {
        //TODO implement drawing polygon
        //TODO implement selecting the shape
        log.debug("Mouse Clicked");
    }

    public void MouseReleased(MouseEvent event){
        if (dragged){
            //TODO implement saving the shape
            log.debug("Mouse released");
        }
        dragged = false;
    }

    public void setMapImageView(Image imageView){
        graphicsContext.clearRect(0,0,WIDTH,HEIGHT);
        mapImageView.setImage(imageView);
        currentImage = imageView;
        graphicsContext.drawImage(imageView,0,0,WIDTH,HEIGHT);
    }

}
