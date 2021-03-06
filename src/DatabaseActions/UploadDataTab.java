package DatabaseActions;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import sample.MainScreen.CheckIfFileExistsAndHandleColor;
import sample.MainScreen.MainScreenActionListeners;

/**
 * Created by r730819 on 6/15/2016.
 */
public class UploadDataTab {
    public static TextField fileNameTextField; //static reference for filename to use across stages
    public static Tab uploadDataTab = new Tab();

    public static void createUploadDataTab(){
        //Variables
        GridPane gridPane = new GridPane();
        Label header = new Label("Upload Data");
        Button chooseFile = new Button("Choose File");
        Button parseFile = new Button("Parse File");
        fileNameTextField = new TextField();

        //Gridpane options/adding nodes
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(header,0,3);//col row
        gridPane.add(chooseFile,0,10);
        gridPane.add(fileNameTextField, 0, 11);
        gridPane.add(parseFile, 0, 14);

        //Textfield options
        fileNameTextField.setPromptText("Enter Valid File Path");

        //Create listeners
        MainScreenActionListeners.createTextFieldListener(parseFile);
        MainScreenActionListeners.createChooseFileListener(chooseFile, parseFile);
        MainScreenActionListeners.createParseFileBtnListner(parseFile);

        //Initially set the button to disabled till a file check is passed
        parseFile.setDisable(true);

        //Added graphics
        header.setStyle("-fx-font-size: 24;");

        //Add a tooltip to the submit button so the user knows what is does
        final Tooltip tooltip = new Tooltip();
        tooltip.setText("Parse file and add contents to Database");
        parseFile.setTooltip(tooltip);

        //Fire the text field listener just to set intial values to red
        CheckIfFileExistsAndHandleColor.changeTextColorAndBtn(fileNameTextField, parseFile);

        //TabPane Options
        uploadDataTab.setText("Upload Data");
        uploadDataTab.setClosable(false); //Unable to close tab

        uploadDataTab.setContent(gridPane);

    }
}
