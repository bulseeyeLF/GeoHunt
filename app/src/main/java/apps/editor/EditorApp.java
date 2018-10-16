package apps.editor;

import apps.App;
import apps.ChooseApp;
import components.*;
import core.QuestionMultiple;
import core.Question;
import core.QuestionSingle;
import core.QuestionVisual;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import shape.ShapeEllipse;
import shape.ShapePolygon;
import shape.Shapes;
import utils.Utils;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;


public class EditorApp extends App {

    private static final Logger log = Logger.getLogger(EditorApp.class);

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
    public void init (){
        super.init();
    }

    @Override
    public void start(Stage editorStage) throws Exception {
        super.start(editorStage);
    }

    public void newMap() {
        //poziva se na add map
        currentlyOpenFile = null;
        editScreen.resetScreen();
        editScreen.setQuestions(new ArrayList<Question>());
        editScreen.setBackgroundPath(defaultPath);
        editScreen.setShapes (new ArrayList<Shapes>());
        mainScene.setRoot(editScreenRoot);
        currentScreen = editScreen;
    }

    public void editMap() {
        //poziva se na editmap
        fileChooser.setTitle("Open Map");
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Files", "*.map")
        );
        currentlyOpenFile = fileChooser.showOpenDialog(new Stage());
        if (currentlyOpenFile != null) {
            try {
                mapLoader(currentlyOpenFile.getCanonicalPath(), editScreen);
            } catch (IOException e) {
                log.error(e);
                e.printStackTrace();
            }
            mainScene.setRoot(editScreenRoot);
            currentScreen = editScreen;
        }
    }

    private void setGlobalTimer() {
        //na klik dugmeta
        Long currentTimer = editScreen.getTimer() / 1000;
        TextInputDialog dialog = new TextInputDialog(currentTimer.toString());
        dialog.setTitle("Timer");
        dialog.setHeaderText("Set global timer for this map");
        dialog.setContentText("Timer for current map in seconds:");
        Optional<String> result;
        while (true) {
            result = dialog.showAndWait();
            if (!result.isPresent()) {
                break;
            }
            if (Utils.isNumeric(result.get())) {
                editScreen.setTimer(Long.parseLong(result.get()) * 1000);
                break;
            } else {
                dialog.setContentText("Please input a number value:");
            }
        }
    }
    
    private void importMap() {
        //import novog backgrounda
        fileChooser.setTitle("Import background");
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Files", "*.png")
        );
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            editScreen.setBackgroundPath(file.getAbsolutePath());
        }
    }

    private void saveFile(String content, File file){
        //sacuvaj
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.flush();
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            log.error(e);
            e.printStackTrace();
        }
    }

    public void saveMap() {
        JSONObject jsonObject = new JSONObject();
        ArrayList<JSONObject> jsonObjectArrayListOfQuestions= new ArrayList<>();
        ArrayList<JSONObject> jsonObjectArrayListOfShapes = new ArrayList<>();

        if (currentlyOpenFile == null){
            fileChooser.setTitle("Save a new map");
            //fileChooser.setInitialDirectory(mapsFolder);
            fileChooser.getExtensionFilters().clear();
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.map")
            );
            currentlyOpenFile = fileChooser.showSaveDialog(new Stage());
        }
        if (currentlyOpenFile != null) {
            JSONArray jsonArray = new JSONArray();
            if (editScreen != null) {
                ArrayList<Question> questions = editScreen.getQuestions();
                if (questions != null) {
                    questions.stream().map(Question::save).forEach(jsonObjectArrayListOfQuestions::add);
                    jsonObjectArrayListOfQuestions.forEach(jsonArray::put);
                }
                ArrayList<Shapes> shapes = editScreen.getShapes();
                if (shapes != null){
                    log.debug("u editoru : " + shapes.size());
                    shapes.stream().map(Shapes::save).forEach(jsonObjectArrayListOfShapes::add);
                    jsonObjectArrayListOfShapes.forEach(jsonArray::put);
                }

            }
            try {
                jsonObject
                    .put("backgroundSource", editScreen.getBackgroundPath())
                    .put("globalTimer", editScreen.getTimer())
                    .put("questions", jsonObjectArrayListOfQuestions)
                    .put("shapes",jsonObjectArrayListOfShapes);

                saveFile(jsonObject.toString(4), currentlyOpenFile);
                currentlyOpenFile = null;
            } catch (JSONException e) {
                log.error(e);
                e.printStackTrace();
            }
        }
        currentlyOpenFile = null;
    }

    public void saveAndBackToMain(){
        saveMap();
        mainScene.setRoot(mainScreenRoot);
        currentScreen = mainScreen;
    }
    private void mapLoader(String input, LayoutEdit editScreen) throws IOException{
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            String myJsonFile = sb.toString();
            JSONObject jsonObjectMap = new JSONObject(myJsonFile);
            String backgroundPath = jsonObjectMap.optString("backgroundSource", "default.png");
            if (!backgroundPath.contains("/")) {
                URL resource = EditorApp.class.getResource("/maps/" + backgroundPath);
                try {
                    backgroundPath = Paths.get(resource.toURI()).toString();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
            editScreen.setBackgroundPath(backgroundPath);

            JSONArray jsonArrayOfQuestions = jsonObjectMap.getJSONArray("questions");

            ArrayList<Question> arrayListQuestion = new ArrayList<>();
            jsonArrayOfQuestions.forEach(i -> {
                int typeInt = ((JSONObject) i).optInt("type");
                if (typeInt == 0) {
                    arrayListQuestion.add(new QuestionSingle((JSONObject) i));
                } else if (typeInt == 1) {
                    arrayListQuestion.add(new QuestionMultiple((JSONObject) i));
                }
                else if (typeInt == 2) {
                    arrayListQuestion.add(new QuestionVisual((JSONObject)i));
                }
            });
            editScreen.setQuestions(arrayListQuestion);

            JSONArray jsonArrayOfShapes = jsonObjectMap.getJSONArray("shapes");

            ArrayList<Shapes> arrayListShapes = new ArrayList<>();
            jsonArrayOfShapes.forEach(i -> {
                int typeInt = ((JSONObject) i).optInt("type");
                if (typeInt == 0) {
                    arrayListShapes.add(new ShapePolygon((JSONObject)i));
                } else if (typeInt == 1) {
                    arrayListShapes.add(new ShapeEllipse((JSONObject)i));
                }
            });
            editScreen.setShapes(arrayListShapes);
            log.debug("array list of shapes from map: " + arrayListShapes.get(0));
            log.debug("shape setted in Editor apps.App");

        } catch (JSONException e) {
            log.error(e);
            e.printStackTrace();
        }
    }

    //init and start methods
    @Override
    protected void initShortcuts(Stage parent) {
       super.initShortcuts(parent);
    }

    @Override
    protected void setUpCurrentScreen() {
        mainScene = new Scene(mainScreenRoot);
        currentScreen = mainScreen;
    }

    @Override
    protected void initScreen(Stage parent, String title) {
        super.initScreen(parent,"Editor");
    }
    @Override
    protected void setScreenSize(Stage stage) {
        mainScreen.setPrefHeight(stage.getHeight());
        mainScreen.setPrefWidth(stage.getWidth());
        stage.setScene(mainScene);
    }

    @Override
    protected void initConstants() {
        //TODO constants for EDITOR
        MAIN_MENU_TEXT = new String[]{
                "New Map",
                "Edit Map",
                "Exit"
        };

        MAIN_MENU_HINT_TEXT = new String[]{
                "Create a (N)ew map",
                "(E)dit existing map",
                "E(X)it"
        };

        MAIN_MENU_TRIGGERS = new KeyCode[]{
                KeyCode.N,
                KeyCode.E,
                KeyCode.X
        };

        EDIT_MENU_TEXT = new String[]{
                "Add Question",
                "Set Global Timer",
                "Import background",
                "Save and Back"
        };

        EDIT_MENU_HINT_TEXT = new String[]{
                "(A)dd more one question",
                "Set the (T)imer",
                "(I)mport new map file",
                "Save and Go (B)ack"
        };

        EDIT_MENU_TRIGGERS = new KeyCode[]{
                KeyCode.A,
                KeyCode.T,
                KeyCode.I,
                KeyCode.B
        };

        ADD_MENU_TEXT = new String[]{
                "Add basic",
                "Add next-lvl",
                "Delete",
                "Save and Back"
        };

        ADD_MENU_HINT_TEXT = new String[]{
                "(A)dd another question",
                "Add another (M)ulti-choice question",
                "(D)elete current question",
                "(S)ave and back"
        };

        ADD_MENU_TRIGGERS = new KeyCode[]{
                KeyCode.A,
                KeyCode.M,
                KeyCode.D,
                KeyCode.S
        };
    }
    @Override
    protected void initMenus(){
        mainMenu = new OptionMenu(
                "main",
                MAIN_MENU_TEXT,
                MAIN_MENU_HINT_TEXT,
                MAIN_MENU_ACTIONS,
                MAIN_MENU_TRIGGERS
        );
        
        editMenu = new OptionMenu(
            "edit",
            EDIT_MENU_TEXT,
            EDIT_MENU_HINT_TEXT,
            EDIT_MENU_ACTIONS,
            EDIT_MENU_TRIGGERS
        );
        
        addMenu = new OptionMenu(
            "add",
            ADD_MENU_TEXT,
            ADD_MENU_HINT_TEXT,
            ADD_MENU_ACTIONS,
            ADD_MENU_TRIGGERS
        );
    }

    @Override
    protected void initActionsAndVariables() {
        ADD_MENU_ACTIONS = new Selection[]{
            () -> {}, //this::addUserInputQuestion,
            () -> {}, //this::addMultipleChoiceQuestion,
            () -> {}, //this::deleteQuestion,
            () -> {}, //this::saveAndBackToEdit
        };
        
        MAIN_MENU_ACTIONS = new Selection[]{
            this::newMap,
            this::editMap,
            this::close
        };
        
        EDIT_MENU_ACTIONS = new Selection[]{
                ()->{},
            this::setGlobalTimer,
            this::importMap,
            this::saveAndBackToMain
        };

        URL resource = EditorApp.class.getResource("/maps/default.jpg");
        try {
            defaultPath = Paths.get(resource.toURI()).toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        fileChooser = new FileChooser();
        currentlyOpenFile = null;
        //mapsFolder = new File(EditorApp.class.getResource("/maps/").getFile());
    }

    @Override
    protected void initRoots() {
        mainScreenRoot = new Group();
        editScreenRoot = new Group();
    }
    @Override
    protected void initScreens() {
        mainScreen = new LayoutMain(mainMenu);
        mainScreenRoot.getChildren().add(mainScreen);
        editScreen = new LayoutEdit(editMenu, defaultPath);
        editScreenRoot.getChildren().add(editScreen);
    }
    @Override
    protected void close (){
        ChooseApp chooseApp = new ChooseApp();
        chooseApp.init();
        try {
            chooseApp.start(primaryStage);
        } catch (Exception e) {
            log.error("Coulndt return to choose menu");
            e.printStackTrace();
        }
    }
}
