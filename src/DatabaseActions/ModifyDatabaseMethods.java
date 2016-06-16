package DatabaseActions;

import FileActions.ReadFile;
import People.Person;
import People.PersonsArrayList;
import Settings.SettingsTab;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class ModifyDatabaseMethods {

    /**
     * Created by r730819 on 6/15/2016.
     * This method will get a SQL connection
     * and simply update all the time stamps
     * of every customer is the
     *
     * @param email the email address
     * @param message the email message
     */
    public static void updateTimeStampAfterEmail(String email, String message){
        Statement statement;
        String sqlStr;

        Connection connection = GetDatabaseConnection.getDBForEmail(SettingsTab.urlTextField.getText(),
                SettingsTab.userTextField.getText(), SettingsTab.passTextField.getText(), "email", email, message);//null null

        if(connection!=null){
            try {
                statement = connection.createStatement();

                sqlStr = "UPDATE customers " +
                         "SET time_stamp = now()";

                statement.executeUpdate(sqlStr);

            } catch (SQLException e) {
                e.printStackTrace();
                //todo add log error
            }
        }

    }

    /**
     * Send file to a method that will
     * parse the file and create
     * Person objects.  The method returns
     * an Arraylist containing all the objects
     * created from the file.  Ensures all 7 details
     * are present for each user.
     */
    public static void attemptUploadData(Connection connection){
        Statement statement;
        String sqlStr;

        ReadFile.readAndCreateObjects(new File(UploadDataTab.fileNameTextField.getText()));


        for (Person person : PersonsArrayList.personsArray){
            try {
                statement = connection.createStatement();

                sqlStr = "INSERT INTO customers(last_name, first_name, email_addr, home_addr, city, state, zip_code)" +
                         "VALUES ('"+ person.lastName + "','" + person.firstName + "','" + person.emailAddr + "','" +
                          person.homeAddr + "','" + person.city + "','" + person.state + "','" + person.zipCode +
                          "');";

                statement.executeUpdate(sqlStr);

            } catch (SQLException e) {
                e.printStackTrace();
                //todo add log error
            }
        }
    }
}
