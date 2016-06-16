package CustomAlerts;

import DatabaseActions.PromptForDatabaseCredentialsScreen;
import javafx.scene.control.Alert;

/**
 * Created by r730819 on 6/14/2016.
 */
public class DatabaseAlerts {
    public void badConnection(String whoNeedsIt){

        PromptForDatabaseCredentialsScreen.createScreen(whoNeedsIt, null, null); //only if error

    }

    /**
     * Bad connection message strictly for email method
     * @param whoNeedsIt String identifier on who needs the prompt for DB credentials screen
     * @param email the email
     * @param message the message to be sent
     */
    public void badConnection(String whoNeedsIt, String email, String message){
        PromptForDatabaseCredentialsScreen.createScreen(whoNeedsIt, email, message); //only if error
    }

    public void goodConnection(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Successful Database Connection");

        alert.show();
    }
}
