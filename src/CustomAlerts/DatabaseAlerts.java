package CustomAlerts;

import DatabaseActions.PromptForDatabaseCredentials;
import javafx.scene.control.Alert;

/**
 * Created by r730819 on 6/14/2016.
 */
public class DatabaseAlerts {
    public void badConnection(){

        PromptForDatabaseCredentials.createScreen(); //only if error

        /**
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Database Error");
        alert.setHeaderText("Unable to connect to database");
        alert.setContentText("Ensure all database credentials are correct:\n" +
                "Url1 = \"" + url + "\";\n" +
                "User = \"" + user + "\";\n" +
                "Password = \"" + pass + "\";");

        alert.showAndWait();
         **/
    }
    public void goodConnection(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Successful Database Connection");

        alert.show();
    }
}
