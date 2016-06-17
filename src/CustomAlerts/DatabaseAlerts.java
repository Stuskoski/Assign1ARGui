package CustomAlerts;

import DatabaseActions.PromptForDatabaseCredentialsScreen;
import FileActions.CustomLogger;
import javafx.scene.control.Alert;

/**
 * Created by r730819 on 6/14/2016.
 *
 * Custom alerts to be displayed after connections are
 * made/not made
 */
public class DatabaseAlerts {
    public void badConnection(String whoNeedsIt){
        CustomLogger.createLogMsgAndSave("Unable to connect to DB.  Opening Database Credentials Editor.", "red");
        PromptForDatabaseCredentialsScreen.createScreen(whoNeedsIt, null, null); //only if error

    }

    /**
     * Bad connection message strictly for email method
     * @param whoNeedsIt String identifier on who needs the prompt for DB credentials screen
     * @param email the email
     * @param message the message to be sent
     */
    public void badConnection(String whoNeedsIt, String email, String message){
        CustomLogger.createLogMsgAndSave("Unable to open DB for changing timestamps.  Opening Database Credentials Editor.", "red");
        PromptForDatabaseCredentialsScreen.createScreen(whoNeedsIt, email, message); //only if error
    }

    /**
     * disabled for now. The popup became annoying ...
     */
    public void goodConnection(){
        CustomLogger.createLogMsgAndSave("Good Connection!");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Successful Database Connection");

        alert.show();
    }
}
