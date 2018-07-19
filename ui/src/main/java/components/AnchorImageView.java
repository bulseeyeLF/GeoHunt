package components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import org.apache.log4j.Logger;


import java.util.ArrayList;
import java.util.stream.Collectors;

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
    }
    private void saveShape(Shapes currentShape) {
        currentShape.shape.setOnMouseClicked(event -> shapeMouseSecondoryClicked(event,currentShape));
        currentShape.setArea();
        if (currentShape.getArea()>20) {
            //TODO smisli neki dobar kriterijum za "getArea" ne prihvatanje nacrtanog oblika :)
            shapesOnPane.add(currentShape);
            this.parent.addQuestion(currentlySelectedShape);
            shapesOnPane.forEach(shape -> {
                if (shape != currentlySelectedShape){
                    shape.setSelected(false);
                }
            });
        } else {
            currentShape.delete();
        }
    }

    private void shapeMouseSecondoryClicked (MouseEvent event, Shapes currentShape) {
        if (event.getButton() == MouseButton.SECONDARY) {
            currentlySelectedShape = currentShape;
            shapesOnPane.forEach(shape -> shape.setSelected(false));
            currentShape.setSelected(true);
            this.parent.setSelectedQuestion(shapesOnPane.indexOf(currentShape));
        }
        event.consume();
    }

    private void MousePressed(MouseEvent event) {
        double xPosition = setPositionLimitHelper(event.getX(), WIDTH, 6);
        double yPosition = setPositionLimitHelper(event.getY(), HEIGHT, 6);
        x = xPosition;
        y = yPosition;
        event.consume();
        log.debug("mouse pressed");
    }

    private void DoubleClick(MouseEvent event) {
        double xPosition = setPositionLimitHelper(event.getX(), WIDTH, 6);
        double yPosition = setPositionLimitHelper(event.getY(), HEIGHT, 6);
        ((ShapePolygon) currentShape).addPoint(xPosition, yPosition);
        currentShape.finish();
        currentlySelectedShape = currentShape;
        saveShape(currentShape);
        currentShape = null;

    }

    private void MouseReleased(MouseEvent event) {
        if (dragged) {
            double xPosition = setPositionLimitHelper(event.getX(), WIDTH, 6);
            double yPosition = setPositionLimitHelper(event.getY(), HEIGHT, 6);
            //**************************************************
            mouseReleasedPolygon(event, xPosition, yPosition);
            //**************************************************
            //mouseReleasedEllipse(event,xPosition,yPosition);
            currentShape.finish();
            currentlySelectedShape = currentShape;
            saveShape(currentShape);
            currentShape = null;
            dragged = false;
            log.debug("released after dragged");
        }
        event.consume();
    }

    private void mouseReleasedPolygon(MouseEvent event, double xpos, double ypos){
        if (currentShape instanceof ShapePolygon){
            ((ShapePolygon)currentShape).addPoint(xpos, ypos);
        }
    }
    private void mouseReleasedEllipse(MouseEvent event, double xpos, double ypos){
        if(currentShape instanceof ShapeEllipse){
        }
    }

    private void MouseClicked(MouseEvent event) {
        double xPosition = setPositionLimitHelper(event.getX(), WIDTH, 6);
        double yPosition = setPositionLimitHelper(event.getY(), HEIGHT, 6);
        handlePolygon(xPosition, yPosition);
        log.debug("moouse clicked");
    }

    private void MouseDragged(MouseEvent event) {
        double xPosition = event.getX();
        double yPosition = event.getY();
        xPosition = setPositionLimitHelper(xPosition, WIDTH, 6);
        yPosition = setPositionLimitHelper(yPosition, HEIGHT, 6);
        //***********************************************
        mouseDraggedPolygon(event,xPosition,yPosition);
        //***********************************************
        //mouseDraggedEllipse(event,xPosition,yPosition);
        dragged = true;
        event.consume();
    }

    private void mouseDraggedPolygon (MouseEvent event,double xpos, double ypos){
        handlePolygon(xpos, ypos);
    }

    private void handlePolygon(double xpos, double ypos) {
        //Ovo je u funkciji jer se potpuno ista stvar radi i za mousedragged i za mouseclicked
        //doduse nikad istovremeno, ali da bismo mogli u slucaju potrebe da se prebacimo na specifikaciju zadatka
        if (currentShape == null) {
            currentShape = new ShapePolygon(x, y);
            this.getChildren().add(currentShape.shape);
        } else if (currentShape instanceof ShapePolygon) {
            ((ShapePolygon) currentShape).addPoint(xpos, ypos);
        }
    }

    private void mouseDraggedEllipse (MouseEvent event,double xpos, double ypos){
        if (currentShape == null) {
            currentShape= new ShapeEllipse(
                (xpos + x) / 2,
                (ypos + y) / 2,
                Math.abs(xpos - x) / 2,
                Math.abs(ypos - y) / 2);
            this.getChildren().add(currentShape.shape);
        } else if (currentShape instanceof ShapeEllipse){
            ((ShapeEllipse)currentShape).setEllipse(
                (xpos + x) / 2,
                (ypos + y) / 2,
                Math.abs(xpos - x) / 2,
                Math.abs(ypos - y) / 2);
        }
    }


    public void setMapImageView(Image image){
        this.getChildren().clear();
        mapImageView.setImage(image);
        currentImage = image;
        this.getChildren().add(mapImageView);
    }

    public void setShapes(ArrayList<Shapes> shapes) {
        if (!shapes.isEmpty()){
            currentlySelectedShape = shapes.get(0);
            shapesOnPane = shapes;
            shapesOnPane.forEach(shape -> {
                if (shape != currentlySelectedShape){
                    shape.setSelected(false);
                } else {
                    shape.setSelected(true);
                }
                shape.getShape().setOnMouseClicked(event -> shapeMouseSecondoryClicked(event,shape));
            });
            this.getChildren()
                .addAll(shapesOnPane.stream()
                    .map(Shapes::getShape)
                    .collect(Collectors.toList())
                );
        } else {
            shapesOnPane = shapes;
        }
    }

    private double setPositionLimitHelper (double position,double LIMIT, double constant ){
        if (position > LIMIT - constant) position = LIMIT - constant;
        if (position < constant) position = constant;
        return position;
    }
}
