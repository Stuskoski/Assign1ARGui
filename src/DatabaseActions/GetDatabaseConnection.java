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
    public static Connection getDB(String url, String user, String password){
        Connection conn1 = null;
        DatabaseAlerts alerts = new DatabaseAlerts();

        try {
            conn1 = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            //exception is thrown with conn1 == null
        }

        if (conn1 != null) {
            alerts.goodConnection();
        }else{
            alerts.badConnection();
        }

        return conn1;

    }
}
