package Tabs;

import FileActions.CustomLogger;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import FileActions.CheckIfFileExistsAndHandleColor;
import augustus.MainScreen.MainScreenActionListeners;
import javafx.scene.layout.HBox;

/**
 * Created by r730819 on 6/15/2016.
 *
 * Upload data tab is the tab used by the
 * user to load a csv file into the database.
 *
 * The user is prompted by a gui to select a file
 * with a file chooser or type in a valid
 * file path for the file to be uploaded.
 *
 * There are file checks to see if the
 * file exists and is a valid file not
 * a directory.
 *
 * todo look into csv file checking
 */
public class UploadDataTab {
    public static TextField fileNameTextField; //static reference for filename to use across stages
    public static Tab uploadDataTab = new Tab();
    public static Button parseFile = new Button("Parse File");

    public static void createUploadDataTab(){
        //Variables
        GridPane gridPane = new GridPane();
        Label titleLabel = new Label("Upload Data");
        HBox titleHBox = new HBox(titleLabel);
        BorderPane borderPane = new BorderPane(gridPane, titleHBox, null, null, null);
        Button chooseFile = new Button("Choose File");
        fileNameTextField = new TextField();

        //Gridpane options/adding nodes
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(chooseFile,0,10); //col row
        gridPane.add(fileNameTextField, 0, 11);
        gridPane.add(parseFile, 0, 14);

        //Center the title with HBox alignment
        titleHBox.setAlignment(Pos.CENTER);

        //Textfield options
        fileNameTextField.setPromptText("Enter Valid File Path");
        fileNameTextField.setPrefWidth(300);

        //Create listeners
        MainScreenActionListeners.createTextFieldListener(parseFile);
        MainScreenActionListeners.createChooseFileListener(chooseFile, parseFile);
        MainScreenActionListeners.createParseFileBanListener(parseFile);

        //Initially set the button to disabled till a file check is passed
        parseFile.setDisable(true);

        //Add a tooltip to the submit button so the user knows what is does
        final Tooltip tooltip = new Tooltip();
        tooltip.setText("Parse file and add contents to Database");
        parseFile.setTooltip(tooltip);

        //Fire the text field listener just to set initial values to red
        CheckIfFileExistsAndHandleColor.changeTextColorAndBtn(fileNameTextField, parseFile);

        //TabPane Options
        uploadDataTab.setText("Upload Data");
        uploadDataTab.setClosable(false); //Unable to close tab

        uploadDataTab.setContent(borderPane);

        //Added graphics
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");

        CustomLogger.createLogMsgAndSave("Upload Data tab loaded");

    }
}
