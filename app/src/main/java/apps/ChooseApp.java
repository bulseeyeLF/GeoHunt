package apps;

import apps.App;
import apps.editor.EditorApp;
import components.LayoutChoose;
import components.LayoutMain;
import components.OptionMenu;
import components.Selection;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.log4j.Logger;


public class ChooseApp extends App {
    private Group chooserScreenRoot;
    private LayoutChoose chooserScreen;
    private OptionMenu chooserMenu;
    private String [] CHOOSER_MENU_TEXT;
    private String[] CHOOSER_MENU_HINT_TEXT;
    private Selection[] CHOOSER_MENU_ACTIONS;
    private KeyCode[] CHOOSER_MENU_TRIGGERS;
    private Scene chooserScene;


    private static final Logger log = Logger.getLogger(ChooseApp.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
    }
    @Override
    public void init(){
        super.init();
    }

    @Override
    protected void initShortcuts(Stage parent) {
        super.initShortcuts(parent);
    }

    @Override
    protected void setUpCurrentScreen() {
        chooserScene = new Scene(chooserScreenRoot);
        currentScreen = chooserScreen;
    }

    @Override
    protected void initScreen(Stage parent, String title) {
        super.initScreen(parent, "Editor");
    }

    @Override
    protected void setScreenSize(Stage stage) {
        chooserScreen.setPrefHeight(stage.getHeight());
        chooserScreen.setPrefWidth(stage.getWidth());
        stage.setScene(chooserScene);
    }


    @Override
    protected void initConstants() {
        CHOOSER_MENU_TEXT = new String[]{
                "Play game",
                "Open Editor",
                "Exit"
        };
        CHOOSER_MENU_HINT_TEXT = new String[]{
                "(P)lay new game or continue previous",
                "Open (E)ditor and edit map or create your own",
                "E(X)it"
        };
        CHOOSER_MENU_TRIGGERS = new KeyCode[]{
                KeyCode.P,
                KeyCode.E,
                KeyCode.X
        };
    }

    @Override
    protected void initMenus() {
        chooserMenu = new OptionMenu(
                "chooser",
                CHOOSER_MENU_TEXT,
                CHOOSER_MENU_HINT_TEXT,
                CHOOSER_MENU_ACTIONS,
                CHOOSER_MENU_TRIGGERS
        );
    }

    @Override
    protected void initActionsAndVariables() {

        CHOOSER_MENU_ACTIONS = new Selection[]{
                this::launchEditor,
                this::launchGame,
                this::close
        };
    }



    @Override
    protected void initRoots() {
        chooserScreenRoot = new Group();
    }


    private void launchGame() {
        //TODO launch Game
    }
    private void launchEditor(){
        EditorApp editorApp = new EditorApp();
        editorApp.init();
        try {
            editorApp.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Launch editor failed");
        }
    }


    @Override
    protected void initScreens() {
        chooserScreen = new LayoutChoose(chooserMenu);
        chooserScreenRoot.getChildren().add(chooserScreen);
    }
}

