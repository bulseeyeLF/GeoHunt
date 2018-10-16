package components;

import core.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import lombok.*;
import org.apache.log4j.Logger;
import shape.Shapes;
import utils.Utils;

import java.util.ArrayList;


public class LayoutEdit extends LayoutBase {

    private static Logger log = Logger.getLogger(LayoutEdit.class);
    @Getter
    private String backgroundPath;
    @Setter
    private AnchorImageView anchorImageView;

    @Getter
    @Setter
    private Long timer;

    private BorderPane mapAndQuestion;
    private QuestionFrame currentFrame;
    @Getter
    private ArrayList<Question> questions;
    @Getter
    private ArrayList<Shapes> shapes;
    private Question selectedQuestion;
    
    public LayoutEdit(OptionMenu menu, String backgroundPath) {
        super(menu);
        this.backgroundPath = backgroundPath;
        //log.debug("shapes size : " + shapes.size());
        resetScreen();
    }
        //1. dodaj novo pitanje i selektuj ga, za novi shape koji nacrtas
    public void addQuestion(Shapes shape, String questionType) {
        // ovo mora da se pozove na kraju saveShape-a
        // ovdje mozda moramo da gledamo koje pitanje zelimo da napravimo
        // ili po defaultu pravimo single pa ima toggle dugme, nisam jos siguran
        Question newQuestion;
        if (questionType.equals("Multiple Question")) {
           newQuestion = new QuestionMultiple(null);
        }
        else if (questionType.equals("Single Question")){
            newQuestion = new QuestionSingle(null);
        }
        else if (questionType.equals("Visual Question")) {
            newQuestion = new QuestionVisual(null);
            // ovde cuvamo shape kao answer
            ((QuestionVisual)newQuestion).setAnswerShape(new AnswerShape(shape.save(), "0"));
        }
        else {
            newQuestion = null; // oporavak od greske
            log.error("new question nije nijedan tip wtf?");
        }

        this.questions.add(newQuestion);
        this.selectedQuestion = newQuestion;
        this.currentFrame.setQuestion(newQuestion);
        //this.shapes.add(shape);
        log.debug("Koliko puta se ovo ispisuje :D i kolika je velicina shape-a" + this.shapes.size());
    }
        //2  selektuj vec postojece pitanje kad se selektuje objekat
    public void setSelectedQuestion(int i) {
        this.selectedQuestion = this.questions.get(i);
        // zivo me interesuje hoce li da radi bez ovoga
        // ako stavim u konstruktoru this.currentFrame.setQuestion(this.selectedQuestion)
        // i onda samo mijenjam selectedQuestion
        //u listeneru za select?
        this.currentFrame.setQuestion(this.selectedQuestion);
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
        if (!questions.isEmpty()) {
            this.selectedQuestion = questions.get(0);
            this.currentFrame.setQuestion(this.questions.get(0));
        }
    }

    public void setShapes(ArrayList<Shapes> shapes) {
        this.shapes = shapes;
        ((AnchorImageView) mapAndQuestion.getLeft()).setShapes(shapes);

        log.debug("shape setted - shapes size  : " + shapes.size());
    }



    public void setBackgroundPath(String newPath) {
        this.backgroundPath = newPath;
        Image newMap = new Image("file:" + this.backgroundPath);
        this.anchorImageView = makeNewAncorImageView(newMap, this);
        ((AnchorImageView) mapAndQuestion.getLeft()).setMapImageView(newMap);
        log.debug("Image View setted");
    }

    public AnchorImageView makeNewAncorImageView (Image img, LayoutEdit parent){
        AnchorImageView helpAnchorImageView = new AnchorImageView(img, parent);
        helpAnchorImageView.getMapImageView().setFitWidth(Utils.getInstance().getScreenWidth()*70/100);
        helpAnchorImageView.getMapImageView().setFitHeight(Utils.getInstance().getScreenHeight()-getMenu().getAbsHeight());
        return helpAnchorImageView;
    }
    public void resetScreen() {
        questions = null;
        shapes = null;
        selectedQuestion = null;
        timer = 0L;
        mapAndQuestion = new BorderPane();
        mapAndQuestion.setPrefHeight(Utils.getInstance().getScreenHeight()-this.getMenu().getAbsHeight());
        mapAndQuestion.setPrefWidth(Utils.getInstance().getScreenWidth());
        mapAndQuestion.setStyle("-fx-background-color: lightgray;");
        mapAndQuestion.setRight(this.currentFrame);
        mapAndQuestion.setLeft(this.anchorImageView);
        this.anchorImageView = makeNewAncorImageView(new Image("file:" + backgroundPath), this);
        //this.anchorImageView.getMapImageView().setFitHeight(UTILS.getScreenHeight()*80/100);
        //this.anchorImageView.getMapImageView().setFitWidth(UTILS.getScreenWidth()*60/100);
        //this.anchorImageView.setMaxHeight(UTILS.getScreenHeight()*80/100);
        //this.anchorImageView.setMaxWidth(UTILS.getScreenWidth()*60/100);
        this.mapAndQuestion.setLeft(this.anchorImageView);
        //log.debug("Here is size : " + this.shapes.size());

        this.currentFrame = new QuestionFrame();
        //this.currentFrame.setPrefWidth(UTILS.getScreenWidth()*40/100);
        //this.currentFrame.setPrefHeight(UTILS.getScreenHeight()*80/100);
        //this.currentFrame.setMinWidth(UTILS.getScreenWidth()*40/100);
        this.currentFrame.setStyle("-fx-background-color:  #cc7a00 ;");
        this.mapAndQuestion.setRight(currentFrame);
        this.setCenter(this.mapAndQuestion);
    }
}
