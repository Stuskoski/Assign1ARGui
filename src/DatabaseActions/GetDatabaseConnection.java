package DatabaseActions;

import CustomAlerts.DatabaseAlerts;
import CustomAlerts.EmailAlerts;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class GetDatabaseConnection {

    /**
     * Created by r730819 on 6/14/2016.
     * This class creates a database connection
     *
     * Boolean uploadData determines if user wants to upload
     * data or just grab a connection for looking at the DB
     */
    public static Connection getDB(String url, String user, String password, String whoNeedsIt){
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
            alerts.badConnection(whoNeedsIt);
        }

        return conn1;

    }

    /**
     * Method that will attempt to get
     * the connection for the DB only for
     * the email method since an email and
     * a message is sent with it.
     *
     * @param url
     * @param user
     * @param password
     * @param whoNeedsIt
     * @param email
     * @param message
     * @return
     */
    public static Connection getDBForEmail(String url, String user, String password, String whoNeedsIt, String email, String message){
        Connection conn1 = null;
        DatabaseAlerts alerts = new DatabaseAlerts();
        EmailAlerts emailAlerts = new EmailAlerts();

        try {
            conn1 = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            //exception is thrown with conn1 == null
        }

        if (conn1 != null) {
            emailAlerts.goodSend();
        }else{
            alerts.badConnection(whoNeedsIt, email, message);
        }

        return conn1;

    }
}
