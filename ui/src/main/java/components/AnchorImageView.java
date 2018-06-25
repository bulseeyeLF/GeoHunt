package components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import org.apache.log4j.Logger;


import java.util.ArrayList;

import static components.GameFrame.UTILS;

public class AnchorImageView extends AnchorPane {
    @Getter
    //this field has overwritten setter!!!
    private ImageView mapImageView;

    private boolean dragged;
    private static Logger log = Logger.getLogger(AnchorImageView.class);
    private static double WIDTH = UTILS.getScreenWidth()*60/100;
    private static double HEIGHT = UTILS.getScreenHeight()*80/100;
    private Image currentImage;
    private double x,y;
    private Shapes currentShape;
    private double [] coords =null;
    @Getter
    private ArrayList<Shapes> shapesOnPane;
    private Shapes currentlySelectedShape;
    private LayoutEdit parent;

    public AnchorImageView(Image img, LayoutEdit parent){
        super();
        this.parent = parent;
        this.setMaxWidth(WIDTH);
        this.setMaxHeight(HEIGHT);
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

        log.debug("anchorImageView constructor");
    }

    private void saveShape(Shapes currentShape) {
        currentShape.shape.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                currentlySelectedShape = currentShape;
                shapesOnPane.forEach(shape -> shape.setSelected(false));
                currentShape.setSelected(true);
                log.debug("index of selected shape: "+shapesOnPane.indexOf(currentShape));
                this.parent.setSelectedQuestion(shapesOnPane.indexOf(currentShape));
                //redrawShapes(currentShape);
                log.debug("secondory click");
            }
            event.consume();
        });


       currentShape.setArea();
        if (currentShape.getArea()>10) {
            currentlySelectedShape = currentShape;
            shapesOnPane.add(currentShape);
            this.parent.addQuestion(currentlySelectedShape);
            shapesOnPane.forEach(shape -> {
                if (shape != currentlySelectedShape) shape.setSelected(false);
            });
            //redrawShapes(currentlySelectedShape);
            log.debug("Saved");
        }
    }

   /* private void redrawShapes(Shapes selectedShape) {
        this.getChildren().clear();
        mapImageView.setImage(currentImage);
        this.getChildren().add(mapImageView);
        shapesOnPane.forEach(shape -> {
            if (shape != selectedShape) shape.setSelected(false);
            else shape.setSelected(true);
        });
        this.getChildren().addAll(shapesOnPane.stream().map(Shapes::getShape).collect(Collectors.toList()));

        log.debug("Redrawed");
    }*/

    private void DoubleClick(MouseEvent event) {
    }

    private void MousePressed(MouseEvent event) {
        double xPosition = event.getX();
        double yPosition = event.getY();
        xPosition = setPositionLimitHelper(xPosition, WIDTH, 6);
        yPosition = setPositionLimitHelper(yPosition, HEIGHT, 6);
        x = xPosition;
        y = yPosition;
        event.consume();
        log.debug("mouse pressed");
    }

    private void MouseReleased(MouseEvent event) {
       if (dragged) {
           double xPosition = event.getX();
           double yPosition = event.getY();
           //redrawShapes(currentlySelectedShape);
           xPosition = setPositionLimitHelper(xPosition, WIDTH, 6);
           yPosition = setPositionLimitHelper(yPosition, HEIGHT, 6);
           ((ShapePolygon) currentShape).addPoint(xPosition, yPosition);
           if (currentShape instanceof ShapePolygon) {
               ((ShapePolygon) currentShape).finishPolygon();
               saveShape(currentShape);
               currentShape = null;
               log.debug("released after dragged");
           }
       }
        dragged = false;
        event.consume();
    }

    private void MouseClicked(MouseEvent event) {
    }

    private void MouseDragged(MouseEvent event) {
        double xPosition = event.getX();
        double yPosition = event.getY();
        //redrawShapes(currentlySelectedShape);
        xPosition = setPositionLimitHelper(xPosition, WIDTH, 6);
        yPosition = setPositionLimitHelper(yPosition, HEIGHT, 6);
        if (currentShape == null) {
            currentShape = new ShapePolygon(x, y);
            this.getChildren().add(currentShape.getShape());
        } else {
            ((ShapePolygon) currentShape).addPoint(xPosition, yPosition);
        }
        dragged = true;
    }

    public void setMapImageView(Image image){
        //graphicsContext.clearRect(0,0,WIDTH,HEIGHT);
        this.getChildren().clear();
        mapImageView.setImage(image);
        currentImage = image;
        this.getChildren().add(mapImageView);
    }

    private double setPositionLimitHelper (double position,double LIMIT, double constant ){
        if (position > LIMIT - constant) position = LIMIT - constant;
        if (position < constant) position = constant;
        return position;
    }
}
