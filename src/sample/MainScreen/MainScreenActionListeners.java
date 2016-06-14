package sample.MainScreen;

import DatabaseActions.GetDatabaseConnection;
import DatabaseActions.PromptForDatabaseCredentials;
import FileActions.ReadFile;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

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
     * @param fileNameTextField Text field from the main stage
     *                          that contains the file path name
     *
     * @param parseFile The button reference from the main stage
     *                  that is either disabled or enabled and allows
     *                  the user to proceed to parse the file or not.
     */
    public static void createTextFieldListener(TextField fileNameTextField, Button parseFile){
        fileNameTextField.setOnKeyReleased(event -> CheckIfFileExistsAndHandleColor.changeTextColorAndBtn(fileNameTextField, parseFile));
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
     * @param fileNameTextField The text field that will populate with the files name upon selection
     */
    public static void createChooseFileListener(Button chooseFile, TextField fileNameTextField,
    Button parseFile){
        chooseFile.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Customer Text File");

            File exportClearFile = fileChooser.showOpenDialog(Main.primaryStage);

            if (exportClearFile != null && exportClearFile.exists()) {
                fileNameTextField.setText(exportClearFile.toString());
                CheckIfFileExistsAndHandleColor.changeTextColorAndBtn(fileNameTextField, parseFile);
            }

        });

    }


    /**
     * When the user presses the button show a database credentials
     * screen and a confirmation screen so they can connect to the
     * right place.
     *
     * @param parseFile reference to button to start the parsing process
     * @param fileNameTextField text field that contains the file path string
     */
    public static void createParseFileBtnListner(Button parseFile, TextField fileNameTextField){
        parseFile.setOnAction(event -> {
            ReadFile.readAndHandle(new File(fileNameTextField.getText()));
            PromptForDatabaseCredentials.createScreen();
            //GetDatabaseConnection.getDB();
        });
    }
}
