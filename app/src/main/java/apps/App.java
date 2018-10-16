package apps;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public abstract class App extends Application{

    private static final Logger log = Logger.getLogger(App.class);

    public static void main(String[] argv) {
        launch(argv);
    }
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
        initShortcuts(stage);
        setUpCurrentScreen(stage);
        initScreen(stage);
        stage.setResizable(false);
        stage.show();
        setScreenSize(stage);
    }

    protected abstract void setScreenSize(Stage stage);

    protected abstract void setUpCurrentScreen(Stage stage);

    protected abstract void initShortcuts(Stage editorStage);
    protected abstract void initScreen(Stage editorStage);
}

