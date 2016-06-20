package CustomAlerts;

import DatabaseActions.PromptForDatabaseCredentialsScreen;
import FileActions.CustomLogger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.util.Duration;

/**
 * Created by r730819 on 6/14/2016.
 *
 * Custom alerts to be displayed after connections are
 * made/not made
 */
public class DatabaseAlerts {
    public void badConnection(String whoNeedsIt){
        CustomLogger.createLogMsgAndSave("Unable to connect to DB.  Opening Database Credentials Editor.", "red");
        PromptForDatabaseCredentialsScreen.createScreen(whoNeedsIt); //only if error

    }

    /**
     * Just a quick popup to show upload data was successful
     */
    public void uploadDataSuccess(){
        CustomLogger.createLogMsgAndSave("Upload data finished successfully");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Successful Data Upload");

        alert.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
           alert.close();
        }));
        timeline.play();
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
