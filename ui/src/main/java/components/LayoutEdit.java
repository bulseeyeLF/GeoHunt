package components;

import core.Question;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import lombok.*;
import org.apache.log4j.Logger;
import utils.Utils;

import java.util.ArrayList;



public class LayoutEdit extends LayoutBase {

    private static Logger log = Logger.getLogger(LayoutEdit.class);

    @Getter
    private String backgroundPath;
    @Setter
    private MapImageView mapImageView;

    @Getter
    @Setter
    private Long timer;

    private BorderPane mapAndQuestion;
    private QuestionFrame currentFrame;
    @Getter
    private ArrayList<Question> questions;
    private Question selectedQuestion;
    private ArrayList<Shapes> shapes;
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

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
        this.selectedQuestion = questions.get(0);
        this.currentFrame.setQuestion(this.questions.get(0));
    }

    public void setBackgroundPath(String backgroundPath) {
        this.backgroundPath = backgroundPath;
        this.mapImageView = new MapImageView(new Image(backgroundPath));
    }

    public void resetScreen() {

        questions = null;
        selectedQuestion = null;

        mapAndQuestion = new BorderPane();
        mapAndQuestion.setPrefHeight(utils.getScreenHeight()*60/100);
        mapAndQuestion.setPrefWidth(utils.getScreenWidth());
        mapAndQuestion.setStyle("-fx-background-color: red;");
        mapAndQuestion.setRight(this.currentFrame);
        mapAndQuestion.setLeft(mapImageView);
        this.mapImageView = new MapImageView(new Image(backgroundPath));
        this.mapImageView.setFitHeight(utils.getScreenHeight()*80/100);
        this.mapImageView.setFitWidth(utils.getScreenWidth()*60/100);
        this.mapAndQuestion.setLeft(this.mapImageView);
        this.currentFrame = new QuestionFrame();
        this.currentFrame.setPrefWidth(utils.getScreenWidth()*40/100);
        this.currentFrame.setPrefHeight(utils.getScreenHeight()*80/100);
        this.currentFrame.setStyle("-fx-background-color: yellow;");
        this.mapAndQuestion.setRight(currentFrame);
        this.setCenter(this.mapAndQuestion);
    }
}
