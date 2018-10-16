package apps;

import components.LayoutBase;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import utils.Utils;

public abstract class App extends Application{

    private static final Logger log = Logger.getLogger(App.class);
    protected LayoutBase currentScreen;
    protected Stage primaryStage;
    @Override
    public void init (){
        try {
            super.init();
        } catch (Exception e) {
            log.error(e);
        }
        initConstants();
        initActionsAndVariables();
        initMenus();
        initRoots();
        initScreens();
    }

    protected abstract void initRoots();

    protected abstract void initConstants();
    protected abstract void initActionsAndVariables();
    protected abstract void initMenus();
    protected abstract void initScreens();

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        initShortcuts(stage);
        setUpCurrentScreen();
        initScreen(stage, "");
        stage.setResizable(false);
        stage.show();
        setScreenSize(stage);
    }

    protected abstract void setScreenSize(Stage stage);

    protected abstract void setUpCurrentScreen();

    protected void initShortcuts(Stage parent){
        parent.addEventHandler(KeyEvent.KEY_PRESSED, event -> currentScreen.getMenu().pressButon(event.getCode()));
    }

    protected void initScreen(Stage parent, String title){
        parent.setWidth(Utils.getInstance().getScreenWidth());
        parent.setHeight(Utils.getInstance().getScreenHeight());
        parent.setOnCloseRequest(event -> close());
        parent.setTitle(title);
    }
    protected void close() {
        Platform.exit();
        System.exit(0);
    }
}

