package components;

import core.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Getter;
import utils.Utils;

import java.io.File;
import java.util.ArrayList;

import static components.GameFrame.UTILS;

public class QuestionFrame extends BorderPane {
    private AnswerFrame currentFrame;
    private AnswerFrameMultiple mulipleFrame;
    private AnswerFrameSingle singleFrame;
    private AnswerFrameVisual visualFrame;
    @Getter
    protected Question question;
    protected TextField inputField;
    private BorderPane centerPane;

    QuestionFrame() {
       UTILS.setPercentageWidth(this, 30);
        //UTILS.setPercentageHeight(this, 80);
        inputField = new TextField();
        mulipleFrame = new AnswerFrameMultiple();
       // mulipleFrame.setPrefWidth(500);
        singleFrame = new AnswerFrameSingle();
        //singleFrame.setPrefHeight(100);
        visualFrame = new AnswerFrameVisual();
        this.setPadding(new Insets(100, 50, 100, 50));

    }

    public void setQuestion(Question question) {
        this.question = question;
        this.inputField = question.getQuestionField();
        if (question.getType() == 0) {
            currentFrame = singleFrame.setAnswer(((QuestionSingle) question).getAnswer());
        } else if (question.getType() == 1) {
            currentFrame = mulipleFrame.setAnswer(((QuestionMultiple) question).getAnswers());
        }
        else if (question.getType() == 2){
            currentFrame = visualFrame.setAnswer(((QuestionVisual) question).getAnswerShape());
        }

        this.setTop(inputField);
        //this.setRight(question.getTimerSpinner());
        Button addPictureButton = new Button();
        addPictureButton.setStyle("-fx-background-radius: 360px");
        addPictureButton.setGraphic(new ImageView(new Image("/buttons/add_picture_button.png")));
        addPictureButton.setOnAction(event -> addPicture());
        //this.setLeft(addPictureButton);
        centerPane = new BorderPane();
        centerPane.setMaxHeight(Utils.getInstance().getScreenHeight()*40/100);
        centerPane.setMaxWidth(Utils.getInstance().getScreenWidth()*20/100);
        centerPane.setLeft(addPictureButton);
        centerPane.setRight(question.getTimerSpinner());
        if (question.getQuestionPictureSource()!=null){
           setPicture(question.getQuestionPictureSource());
        }
        this.setCenter(centerPane);
        this.setBottom(currentFrame);
    }

    private void addPicture(){
        FileChooser pictureChooser = new FileChooser();
        pictureChooser.setTitle("Choose your picture");
        pictureChooser.getExtensionFilters().clear();
        pictureChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.png", "*.jpg", "*.gif", "*bmp")
        );
        File choosenFile = pictureChooser.showOpenDialog(new Stage());
        if (choosenFile!=null){
            setPicture(choosenFile.toURI().toString());
        }
    }

    private void setPicture (String resource){
        ImageView choosenImage = new ImageView(new Image(resource));
        question.setQuestionPictureSource(resource);
        choosenImage.setFitWidth((Utils.getInstance().getScreenWidth()*10/100));
        choosenImage.setFitHeight((Utils.getInstance().getScreenHeight()*30/100));

        centerPane.setBottom(choosenImage);
    }

}
