package augustus.MainScreen;

import FileActions.CustomLogger;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

import java.io.File;

/**
 * Created by r730819 on 6/14/2016.
 * This function receives two references, one to a textfield
 * and the other to a button.  A file object will be created
 * from the textfield's text and checked if it exists.
 *
 * If the file exists, green is shown and the button is not
 * disabled.
 *
 * If the file does not exist, red is show and the button is
 * disabled until a valid file name is in the textfield.
 */
public class CheckIfFileExistsAndHandleColor {
    public static void changeTextColorAndBtn(TextField textField, Button parseFile){
        File check = new File(textField.getText());

        CustomLogger.createLogMsgAndSave("Checking for file: " + check.toString());

        if(check.exists() && check.isFile()){
            CustomLogger.createLogMsgAndSave(check.toString() + "exists");
            textField.setStyle("-fx-border-color: green;" +
                    "-fx-text-fill: darkgreen;");
            parseFile.setDisable(false);
        }else{
            CustomLogger.createLogMsgAndSave(check.toString() + " does not exist!", "red");
            textField.setStyle("-fx-border-color: red;" +
                    "-fx-text-fill: darkred;");
            parseFile.setDisable(true);
        }
    }
}
