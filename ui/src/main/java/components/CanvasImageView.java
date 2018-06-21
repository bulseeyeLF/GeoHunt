package components;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private double x,y;
    Shapes currentShape;
    private double [] coords =null;


    public CanvasImageView(Image img){
        super(WIDTH,HEIGHT);
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



    //TODO drawing circle
    private void MousePressed(MouseEvent event) {
        log.debug("Mouse pressed");
        //TODO maybe initialization of circle?
        x = event.getX();
        y = event.getY();
    }

    public void MouseDragged(MouseEvent event) {
        //TODO implement instanciating Circle
        double xPosition = event.getX();
        double yPosition = event.getY();
        graphicsContext.clearRect(0,0,WIDTH,HEIGHT);
        redrawShapes();
        if (xPosition > WIDTH ) xPosition = WIDTH;
        if (yPosition > HEIGHT) yPosition = HEIGHT;
        if (xPosition < 0) xPosition = 0;
        if (yPosition < 0) yPosition = 0;
        currentShape= new ShapeEllipse(x,y, Math.sqrt(Math.pow(xPosition -x,2)), Math.sqrt(Math.pow(yPosition - y,2)));
        currentShape.drawShape(graphicsContext, xPosition,yPosition);
        log.debug("Mouse dragged");
        dragged = true;
    }

    private void redrawShapes() {
        graphicsContext.drawImage(currentImage, 0, 0, WIDTH,HEIGHT);
        //TODO implement reading shapes from file and drawing them on canvas
    }

    private void saveShape(Shapes currentShape) {
        //TODO implement func
    }

    public void MouseReleased(MouseEvent event){
        if (dragged){
            saveShape(currentShape);
            currentShape = null;
            //TODO implement saving the shape
            log.debug("Mouse released");
        }
        dragged = false;
    }

    public void MouseClicked(MouseEvent event) {
        //TODO implement selecting the shape
        double xPosition = event.getX();
        double yPosition = event.getY();

        graphicsContext.clearRect(0,0,WIDTH,HEIGHT);
        redrawShapes();
        if (xPosition > WIDTH ) xPosition = WIDTH;
        if (yPosition > HEIGHT) yPosition = HEIGHT;
        if (xPosition < 0) xPosition = 0;
        if (yPosition < 0) yPosition = 0;
       if (currentShape ==null){
           currentShape = new ShapePolygon(event.getX(),event.getY());
       }else {
           ((ShapePolygon) currentShape).addPoint(xPosition, yPosition);
       }
       currentShape.drawShape(graphicsContext, xPosition,yPosition);
       log.debug("Mouse Clicked");

    }

    private void DoubleClick(MouseEvent event) {
        log.debug("Double click");
        if (currentShape instanceof ShapePolygon){
            log.debug("current shape instance of shapepolygon");
            currentShape.drawShape(graphicsContext, event.getX(), event.getY(), 1);
            currentShape = null;
        }
    }



    public void setMapImageView(Image imageView){
        graphicsContext.clearRect(0,0,WIDTH,HEIGHT);
        mapImageView.setImage(imageView);
        currentImage = imageView;
        graphicsContext.drawImage(imageView,0,0,WIDTH,HEIGHT);
    }

}
