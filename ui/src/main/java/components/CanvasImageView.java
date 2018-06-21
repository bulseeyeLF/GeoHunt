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
                        this::DragDetected,
                        this::MouseClicked,
                        this::MouseReleased
                )
        );
    }

    public void setMapImageView(Image imageView){
        graphicsContext.clearRect(0,0,WIDTH,HEIGHT);
        mapImageView.setImage(imageView);
        currentImage = imageView;
        graphicsContext.drawImage(imageView,0,0,WIDTH,HEIGHT);
    }

    public void DragDetected(MouseEvent event) {
        graphicsContext.clearRect(0,0,WIDTH,HEIGHT);
        draw();
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.strokeLine(x,y, event.getX(),event.getY());
        log.debug("Mouse dragged");
        dragged = true;

    }

    private void draw() {
        graphicsContext.drawImage(currentImage, 0, 0, WIDTH,HEIGHT);

    }

    public void MouseClicked(MouseEvent event) {
        x = event.getX();
        y = event.getY();
        log.debug("Mouse Clicked");
    }

    public void MouseReleased(MouseEvent event){
        if (dragged){
            log.debug("Mouse released");
        }
        dragged = false;
    }

}
