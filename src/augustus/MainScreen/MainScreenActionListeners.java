package augustus.MainScreen;

import DatabaseActions.GetDatabaseConnection;
import DatabaseActions.ModifyDatabaseMethods;
import FileActions.CustomLogger;
import Tabs.UploadDataTab;
import Tabs.SettingsTab;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;
import java.sql.Connection;

/**
 * Created by r730819 on 6/14/2016.
 * This class contains all the event listeners
 * for the main landing stage.
 */
public class MainScreenActionListeners {
    /**
     * Create a listener that will check if the file
     * exists every time the user enters a character.
     *
     *
     * @param parseFile The button reference from the main stage
     *                  that is either disabled or enabled and allows
     *                  the user to proceed to parse the file or not.
     */
    public static void createTextFieldListener(Button parseFile){
        UploadDataTab.fileNameTextField.setOnKeyReleased(event ->
                CheckIfFileExistsAndHandleColor.changeTextColorAndBtn(UploadDataTab.fileNameTextField, parseFile));
    }

    /**
     * This listener will wait for a file chooser button
     * to be pushed.  Upon executing a file chooser will
     * open to give the user the option to select a file.
     *
     * Once the user selects the file, the listener above
     * will fire and check if it exists which is should
     * obviously.  Fires mainly for the green text to
     * let the user know file is good to go.
     *
     * @param chooseFile Button to enable the user to select a file
     */
    public static void createChooseFileListener(Button chooseFile, Button parseFile){
        chooseFile.setOnAction(event -> {
            CustomLogger.createLogMsgAndSave("File Chooser Opened");

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Customer Text File");

            File exportClearFile = fileChooser.showOpenDialog(Main.primaryStage);

            if (exportClearFile != null && exportClearFile.exists()) {
                CustomLogger.createLogMsgAndSave(exportClearFile.toString() + " chosen");
                UploadDataTab.fileNameTextField.setText(exportClearFile.toString());
                CheckIfFileExistsAndHandleColor.changeTextColorAndBtn(UploadDataTab.fileNameTextField, parseFile);
            }

        });

    }


    /**
     * This button is in charge of parsing a file
     * and then sending the contents to the database.
     *
     * The button is initially disabled but as soon as
     * a valid file is inputted or chosen the button is
     * enabled.
     *
     * If no DB connection can be established a prompt
     * will say so
     *
     * todo - fix this description
     *
     * @param parseFile reference to button to start the parsing process
     */
    public static void createParseFileBanListener(Button parseFile){
        parseFile.setOnAction(event -> {
            //ReadFile.readAndCreateObjects(new File(Main.fileNameTextField.getText()));
            Connection connection = GetDatabaseConnection.getDB(SettingsTab.urlTextField.getText(),
                    SettingsTab.userTextField.getText(), SettingsTab.passTextField.getText(), "uploadData"); //true for prompt screen

            if(connection!=null)
                ModifyDatabaseMethods.attemptUploadData(connection);
            //GetDatabaseConnection.getDB();
        });
    }
}
