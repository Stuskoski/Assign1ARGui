package DatabaseActions;

import CustomAlerts.DatabaseAlerts;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by r730819 on 6/14/2016.
 * This class creates a database connection
 */
public class GetDatabaseConnection {
    public static void getDB(String url, String user, String password){
        Connection conn1;
        DatabaseAlerts alerts = new DatabaseAlerts();

        try {
           // String url1 = "jdbc:mysql://localhost:3306/assign1_db";
            //String user = "stus";
            //String password = "Ownage3255%";

            conn1 = DriverManager.getConnection(url, user, password);
            if (conn1 != null) {
                alerts.goodConnection();
            }

        } catch (SQLException ex) {
            alerts.badConnection();
        }

        alerts.badConnection();
    }
}
