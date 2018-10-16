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


public class GameApp extends App {
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

    private Scene mainScene;
    private File mapsFolder;
    private String defaultPath ;
    private FileChooser fileChooser ;
    private File currentlyOpenFile;

    private Group mainScreenRoot;
    private Group editScreenRoot;

    private LayoutMain mainScreen;
    private LayoutEdit editScreen;

    private  OptionMenu mainMenu;
    private  OptionMenu editMenu;
    private  OptionMenu addMenu;
    @Override
    protected void initRoots() {

    }

    @Override
    protected void initConstants() {

    }

    @Override
    protected void initActionsAndVariables() {

    }

    @Override
    protected void initMenus() {

    }

    @Override
    protected void initScreens() {

    }

    @Override
    protected void setScreenSize(Stage stage) {

    }

    @Override
    protected void setUpCurrentScreen() {

    }
}
