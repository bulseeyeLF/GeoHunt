package components;

import core.Question;
import core.QuestionSingle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import lombok.*;
import org.apache.log4j.Logger;
import org.json.JSONObject;
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
    private ArrayList<Shapes> shapes; //TODO ovo moramo zajedno, ne znam kako si planirao da dohvatamo/upisujemo shapes u map fajl
    private Question selectedQuestion;

    private Utils utils;
    public LayoutEdit(OptionMenu menu, String backgroundPath) {
        super(menu);
        utils = Utils.getInstance();
        //this.setHeight(utils.getScreenHeight());
        //this.setWidth(utils.getScreenWidth());
        this.backgroundPath = backgroundPath;
        menu.setButtonSizes(80, 200);
        menu.setPadding(new Insets(5, 5, 200, 5));
        menu.setHgap(10);
        menu.setAlignment(Pos.BOTTOM_CENTER);
        menu.setPrefHeight(utils.getScreenHeight()*20/100);
        menu.setPrefWidth(utils.getScreenWidth());
        this.setBottom(menu);
        resetScreen();
    }
        //1. TODO dodaj novo pitanje i selektuj ga, za novi shape koji nacrtas
    public void addQuestion() {
        // ovo mora da se pozove na kraju saveShape-a
        // ovdje mozda moramo da gledamo koje pitanje zelimo da napravimo
        // ili po defaultu pravimo single pa ima toggle dugme, nisam jos siguran
        QuestionSingle newQuestion = new QuestionSingle(null);
        this.questions.add(newQuestion);
        this.selectedQuestion = newQuestion;
        this.currentFrame.setQuestion(newQuestion);
    };
        //2 .TODO selektuj vec postojece pitanje kad se selektuje objekat
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

    public void setBackgroundPath(String newPath) {
        this.backgroundPath = newPath;
        Image newMap = new Image("file:" + this.backgroundPath);
        this.anchorImageView = new AnchorImageView(newMap, this);
        ((AnchorImageView) mapAndQuestion.getLeft()).setMapImageView(newMap);
    }

    public void resetScreen() {
        questions = null;
        selectedQuestion = null;
        timer = 0L;
        mapAndQuestion = new BorderPane();
        mapAndQuestion.setPrefHeight(utils.getScreenHeight()*60/100);
        mapAndQuestion.setPrefWidth(utils.getScreenWidth());
        mapAndQuestion.setStyle("-fx-background-color: red;");
        mapAndQuestion.setRight(this.currentFrame);
        mapAndQuestion.setLeft(this.anchorImageView);
        this.anchorImageView = new AnchorImageView(new Image("file:" + backgroundPath), this);
        this.anchorImageView.getMapImageView().setFitHeight(utils.getScreenHeight()*80/100);
        this.anchorImageView.getMapImageView().setFitWidth(utils.getScreenWidth()*60/100);
        this.mapAndQuestion.setLeft(this.anchorImageView);
        this.currentFrame = new QuestionFrame();
        this.currentFrame.setPrefWidth(utils.getScreenWidth()*40/100);
        this.currentFrame.setPrefHeight(utils.getScreenHeight()*80/100);
        this.currentFrame.setStyle("-fx-background-color: yellow;");
        this.mapAndQuestion.setRight(currentFrame);
        this.setCenter(this.mapAndQuestion);
    }
}
