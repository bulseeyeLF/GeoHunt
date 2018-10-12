package components.Shapes;

import components.LayoutEdit;
import components.MyHandler;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import org.apache.log4j.Logger;
import utils.Utils;


import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class AnchorImageView extends AnchorPane {
    @Getter
    //this field has overwritten setter!!!
    private ImageView mapImageView;

    private boolean dragged;
    private static Logger log = Logger.getLogger(AnchorImageView.class);
    private static double WIDTH = Utils.getInstance().getScreenWidth()*70/100;
    private double HEIGHT = Utils.getInstance().getScreenHeight();
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
        HEIGHT = HEIGHT - parent.getMenu().getAbsHeight();
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

    private void shapeMouseSecondoryClicked (MouseEvent event, Shapes currentShape) {
        log.debug("shapes on pane: " + shapesOnPane.size());
        if (event.getButton() == MouseButton.SECONDARY) {
            currentlySelectedShape = currentShape;
            shapesOnPane.forEach(shape -> shape.setSelected(false));
            currentShape.setSelected(true);
            log.debug("index of selected shape: "+shapesOnPane.indexOf(currentShape));
            //TODO na ovoj liniji imamo povezzanost izmedju shape-a i pitanja!!!
            this.parent.setSelectedQuestion(shapesOnPane.indexOf(currentShape));
            //redrawShapes(currentShape);
            log.debug("secondory click");
        }
        event.consume();
    }

    private void saveShape(Shapes currentShape) {
        currentShape.shape.setOnMouseClicked(event -> shapeMouseSecondoryClicked(event,currentShape));
        log.debug("current shape  " + currentShape);
        currentShape.setArea();
        log.debug("AREA: "+currentShape.getArea());
        if (currentShape.getArea()>20) {
            log.debug("Area bigger thaan 10");
            shapesOnPane.add(currentShape);
            //TODO popup za pitanje da li zelis multiple ili single question
            ChoiceDialog<String> choiceDialogQuestion = new ChoiceDialog<>("Single Question",
                    "Single Question", "Multiple Question", "Visual Question");
            choiceDialogQuestion.setTitle("Create new question");
            choiceDialogQuestion.setHeaderText("Choose type of question");
            choiceDialogQuestion.setGraphic(new ImageView("/logo.png"));
            choiceDialogQuestion.showAndWait();
            String questionType = choiceDialogQuestion.getSelectedItem();
            this.parent.addQuestion(currentlySelectedShape,questionType);

            shapesOnPane.forEach(shape -> {
                if (shape != currentlySelectedShape) shape.setSelected(false);
            });
        }
        else {
            currentShape.delete();
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

    private void DoubleClick(MouseEvent event) {
        double xPosition = event.getX();
        double yPosition = event.getY();
        xPosition = setPositionLimitHelper(xPosition, WIDTH, 6);
        yPosition = setPositionLimitHelper(yPosition, HEIGHT, 6);
        ((ShapePolygon) currentShape).addPoint(xPosition, yPosition);
        currentShape.finish();
        currentlySelectedShape = currentShape;
        saveShape(currentShape);
        currentShape = null;

    }

    private void MouseReleased(MouseEvent event) {
        if (dragged) {
            double xPosition = event.getX();
            double yPosition = event.getY();
            xPosition = setPositionLimitHelper(xPosition, WIDTH, 6);
            yPosition = setPositionLimitHelper(yPosition, HEIGHT, 6);
            //mouseReleasedPolygon(event, xPosition, yPosition);
            mouseReleasedEllipse(event,xPosition,yPosition);
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
        double xPosition = event.getX();
        double yPosition = event.getY();
        //redrawShapes(currentlySelectedShape);
        xPosition = setPositionLimitHelper(xPosition, WIDTH, 6);
        yPosition = setPositionLimitHelper(yPosition, HEIGHT, 6);
        if (currentShape == null) {
            currentShape = new ShapePolygon(x, y);
            this.getChildren().add(currentShape.shape);
        } else if (currentShape instanceof ShapePolygon){
            ((ShapePolygon) currentShape).addPoint(xPosition, yPosition);
        }
        log.debug("moouse clicked");
    }

    private void MouseDragged(MouseEvent event) {
        double xPosition = event.getX();
        double yPosition = event.getY();
        //redrawShapes(currentlySelectedShape);
        xPosition = setPositionLimitHelper(xPosition, WIDTH, 6);
        yPosition = setPositionLimitHelper(yPosition, HEIGHT, 6);
        //mouseDraggedPolygon(event,xPosition,yPosition);
        mouseDraggedEllipse(event,xPosition,yPosition);
        dragged = true;
        event.consume();

    }

    private void mouseDraggedPolygon (MouseEvent event,double xpos, double ypos){

        if (currentShape == null) {
            log.debug("Current shape je null");
            currentShape = new ShapePolygon(x, y);
            this.getChildren().add(currentShape.shape);
            log.debug("Velicina pane-a ??? " +this.getChildren().size());
        } else if (currentShape instanceof ShapePolygon) {
            ((ShapePolygon) currentShape).addPoint(xpos, ypos);
        }
    }
    private void mouseDraggedEllipse (MouseEvent event,double xpos, double ypos){
        if (currentShape == null) {
            currentShape= new ShapeEllipse(
                (xpos + x) / 2,
                (ypos + y) / 2,
                 Math.abs((xpos - x) / 2),
                Math.abs(ypos - y) / 2);
            log.debug("created ellipse");
            this.getChildren().add(currentShape.shape);
            log.debug("getChildren :  " + this.getChildren().size());
            /*((ShapeEllipse)currentShape).setRadiusXposition(xpos);
            ((ShapeEllipse)currentShape).setRadiusYposition(ypos);*/
        } else if (currentShape instanceof ShapeEllipse){
            log.debug("u elsu");
            ((ShapeEllipse)currentShape).setEllipse(
                (xpos + x) / 2,
                (ypos + y) / 2,
                 Math.abs((xpos - x) / 2),
                Math.abs(ypos - y) / 2);
        }
    }


    public void setMapImageView(Image image){
        //graphicsContext.clearRect(0,0,WIDTH,HEIGHT);
        this.getChildren().clear();
        mapImageView.setImage(image);

        currentImage = image;
        this.getChildren().add(mapImageView);
        log.debug("shapes on pane: " + shapesOnPane.size());
    }

    public void setShapes(ArrayList<Shapes> shapes) {
        log.debug("shapes on pane: " + shapesOnPane.size());
        if (!shapes.isEmpty()){
            currentlySelectedShape = shapes.get(0);
            shapesOnPane = shapes;
            log.debug("shapes on pane size :  "+shapesOnPane.size());
            shapesOnPane.forEach(shape -> {
                if (shape != currentlySelectedShape) shape.setSelected(false);
                else shape.setSelected(true);
                shape.getShape().setOnMouseClicked(event -> shapeMouseSecondoryClicked(event,shape));
            });
            this.getChildren().addAll(shapesOnPane.stream().map(Shapes::getShape).collect(Collectors.toList()));
            log.debug("Size of children : "+ this.getChildren().size());
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
