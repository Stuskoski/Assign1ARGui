package DatabaseActions;

import CustomAlerts.DatabaseAlerts;
import CustomAlerts.EmailAlerts;
import FileActions.CustomLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class GetDatabaseConnection {

    /**
     * Created by r730819 on 6/14/2016.
     * This class creates a database connection
     *
     * Boolean uploadData determines if user wants to upload
     * data or just grab a connection for looking at the DB
     */
    public static Connection getDB(String url, String user, String password, String whoNeedsIt){
        CustomLogger.createLogMsgAndSave("Attempting database connection");
        Connection conn1 = null;
        DatabaseAlerts alerts = new DatabaseAlerts();

        try {
            conn1 = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            //exception is thrown with conn1 == null
        }

        if (conn1 != null) {
            CustomLogger.createLogMsgAndSave("Connection established");
            //alerts.goodConnection(); leave out for now
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
     * @param url Url connection string
     * @param user User name for DB
     * @param password Password for DB
     * @param whoNeedsIt Who needs the database connection.  Used for custom handling.
     * @param email Email to sent to just in case for email method
     * @param message Message to be sent just in case for email method
     * @return
     */
    public static Connection getDBForEmail(String url, String user, String password, String whoNeedsIt, String email, String message){
        CustomLogger.createLogMsgAndSave("Attempting database connection");
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
