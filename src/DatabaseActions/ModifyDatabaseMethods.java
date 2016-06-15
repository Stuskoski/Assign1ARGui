package DatabaseActions;

import FileActions.ReadFile;
import People.Person;
import People.PersonsArrayList;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by r730819 on 6/15/2016.
 */
public class ModifyDatabaseMethods {
    public static void updateTimeStampAfterEmail(Connection connection){

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

                sqlStr = "INSERT INTO customers(last_name, first_name, email_addr, home_addr, city, state, zip_code, time_stamp)" +
                         "VALUES ('"+ person.lastName + "','" + person.firstName + "','" + person.emailAddr + "','" +
                          person.homeAddr + "','" + person.city + "','" + person.state + "','" + person.zipCode +
                          "',now());";

                statement.executeUpdate(sqlStr);

            } catch (SQLException e) {
                e.printStackTrace();
                //todo add log error
            }
        }
    }
}
