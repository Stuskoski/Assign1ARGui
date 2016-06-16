package CustomAlerts;

import FileActions.CustomLogger;
import javafx.scene.control.Alert;

/**
 * Created by r730819 on 6/16/2016.
 * Alerts that are shown whenever the user sends
 * an email or changes the timestamps after an email
 */
public class EmailAlerts {
    public void goodSend(){
        CustomLogger.createLogMsgAndSave("Email sent successfully");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Email sent and timestamps updated successfully");

        alert.show();
    }

    public void badSend(){
        CustomLogger.createLogMsgAndSave("Error sending Email.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Unable to send email");

        alert.show();
    }
}
