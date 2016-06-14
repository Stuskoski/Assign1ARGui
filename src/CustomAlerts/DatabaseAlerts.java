package CustomAlerts;

import javafx.scene.control.Alert;

/**
 * Created by r730819 on 6/14/2016.
 */
public class DatabaseAlerts {
    public void badConnection(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Database Error");
        alert.setHeaderText("Unable to connect to database");
        alert.setContentText("Ensure all database credentials are correct:" +
                "Url1 = \"jdbc:mysql://localhost:3306/assign1_db\";\n" +
                "User = \"stus\";\n" +
                "Password = \"Ownage3255%\";");

        alert.showAndWait();
    }
    public void goodConnection(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Successful Database Connection");

        alert.showAndWait();
    }
}
