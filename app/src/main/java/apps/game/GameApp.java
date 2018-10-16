package apps.game;

import apps.App;
import components.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import utils.Utils;

import java.io.File;

public abstract class GameApp extends App {
    private  String[] MAIN_MENU_TEXT ;
    private  String[] MAIN_MENU_HINT_TEXT;
    private  KeyCode[] MAIN_MENU_TRIGGERS ;
    private  Selection[] MAIN_MENU_ACTIONS ;
    private  String[] EDIT_MENU_TEXT ;
    private  String[] EDIT_MENU_HINT_TEXT ;
    private  KeyCode[] EDIT_MENU_TRIGGERS;
    private  Selection[] EDIT_MENU_ACTIONS ;
    private  String[] ADD_MENU_TEXT ;
    private  String[] ADD_MENU_HINT_TEXT ;
    private  Selection[] ADD_MENU_ACTIONS;
    private  KeyCode[] ADD_MENU_TRIGGERS;

    static Utils UTILS = Utils.getInstance() ;
    private Scene mainScene;
    private File mapsFolder;
    private String defaultPath ;
    private FileChooser fileChooser ;
    private File currentlyOpenFile;

    private Group mainScreenRoot;
    private Group editScreenRoot;

    private LayoutMain mainScreen;
    private LayoutEdit editScreen;

    private LayoutBase currentScreen;

    private OptionMenu mainMenu;
    private  OptionMenu editMenu;
    private  OptionMenu addMenu;

    private static final Logger log = Logger.getLogger(GameApp.class);

    public static void main(String[] argv) {
        launch(argv);
    }
/*    @Override
    public void init (){
        try {
            super.init();
        } catch (Exception e) {
            log.error(e);
        }
        initConstants();
        initActionsAndVariables();
        initMenus();
        mainScreenRoot = new Group();
        editScreenRoot = new Group();
        initScreens();
    }

    @Override
    public void start(Stage editorStage) {
        initShortcuts(editorStage);
        mainScene = new Scene(mainScreenRoot);
        currentScreen = mainScreen;
        initScreen(editorStage);
        editorStage.setResizable(false);
        editorStage.show();
        mainScreen.setPrefHeight(editorStage.getHeight());
        mainScreen.setPrefWidth(editorStage.getWidth());
        editorStage.setScene(mainScene);
    }*/
}
