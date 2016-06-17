package DatabaseActions;

import FileActions.CustomLogger;
import FileActions.ReadFile;
import PeopleModels.Person;
import PeopleModels.PersonsArrayList;
import Tabs.HomeTab;
import Tabs.SettingsTab;
import Tabs.UploadDataTab;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
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
            CustomLogger.createLogMsgAndSave("Attempting to update email timestamps");
            try {
                statement = connection.createStatement();

                sqlStr = "UPDATE customers " +
                         "SET time_stamp = now()";

                statement.executeUpdate(sqlStr);

            } catch (SQLException e) {
                CustomLogger.createLogMsgAndSave("Unable to update email timestamps", "red");
            }
        }

    }

    /**
     * Send file to a method that will
     * parse the file and create
     * Person objects.  The method returns
     * an ArrayList containing all the objects
     * created from the file.  Ensures all 7 details
     * are present for each user.
     */
    public static void attemptUploadData(Connection connection){
        if(connection!=null){
            CustomLogger.createLogMsgAndSave("Attempting to upload data from file to database");

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
                    System.out.println(e.toString());
                    CustomLogger.createLogMsgAndSave("Error inserting data", "red");
                }
            }
        }
    }


    /**
     * Combined 3 methods into one that takes a string
     * as an action to either make, clear(truncate), or
     * delete(drop) the database.
     *
     * @param action Received from DatabaseActions.DatabaseActionListeners
     *               and is either "make", "clear", "delete" to specify
     *               which action to take.
     */
    public static void makeClearDeleteDB(String action){
        File mySqlPath;
        Process process = null;

        CustomLogger.createLogMsgAndSave("Attempting to alter database");

        //Find the MySQL path. Restricted to windows only currently
        mySqlPath = findMySQLExec();

        if(mySqlPath!=null){
            try {
                switch (action){
                    case "make":
                        URL url = ModifyDatabaseMethods.class.getClassLoader().getResource("assign1_db_augustus_customers.sql");
                        String urlString;
                        String find = "C:";
                        if(url!=null){
                            urlString = url.getFile();
                            urlString = urlString.substring(urlString.indexOf(find));

                            urlString = urlString.replaceAll("!", "");
                            urlString = urlString.replaceAll("/", "\\\\");


                           // urlString = urlString.replaceAll("Assign1ARGui", "Assign1ARGui.jar");

                            CustomLogger.createLogMsgAndSave("Pulling resource: " + urlString);

                           // Files.move(new File(urlString).toPath(), new File(urlString+"../").toPath());


                            CustomLogger.createLogMsgAndSave("Creating database(assign1_db_augustus)");
                            process = Runtime.getRuntime().exec(  new String [] {mySqlPath.toString(), "-u", SettingsTab.userTextField.getText(),
                                    "-p" + SettingsTab.passTextField.getText(), "-e", "source " + urlString} );
                        }
                        break;
                    case "clear":
                        CustomLogger.createLogMsgAndSave("Truncating database table (customers)");
                        process = Runtime.getRuntime().exec(  new String [] {mySqlPath.toString(), "-u", SettingsTab.userTextField.getText(),
                                "-p" + SettingsTab.passTextField.getText(), "-e", "TRUNCATE `assign1_db_augustus`.`customers`;"} );
                        break;
                    case "delete":
                        CustomLogger.createLogMsgAndSave("Delete database (assign1_db_augustus)");
                        process = Runtime.getRuntime().exec(  new String [] {mySqlPath.toString(), "-u", SettingsTab.userTextField.getText(),
                                "-p" + SettingsTab.passTextField.getText(), "-e", "DROP DATABASE `assign1_db_augustus`;"} );
                        break;
                }

                if(process !=null){
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                        while(in.readLine()!=null)
                            System.out.println(in.readLine());
                    } catch (IOException e) {
                        CustomLogger.createLogMsgAndSave("Unable to locate MySQL executable", "red");
                    }

                    CustomLogger.createLogMsgAndSave("Database altered successfully");
                }

            } catch (IOException e) {
                CustomLogger.createLogMsgAndSave("Unable to execute " + mySqlPath.toString(), "red");
            }
        }
    }

    /**
     * Searches in program files for the correct
     * extension to the mysql executable.
     *
     * @return the executable mysql file
     */
    private static File findMySQLExec(){
        File retFile = null;
        Process process = null;

        CustomLogger.createLogMsgAndSave("Looking for mysql.exe in Program Files");

        //windows cmd to search for the mysql executable. todo - consider handling other OS
        try {
            process = Runtime.getRuntime().exec("where /R \"C:\\Program Files\" mysql.exe");
        } catch (IOException e) {
            CustomLogger.createLogMsgAndSave("Unable to locate MySQL executable", "red");
        }

        //Create a new file based off the path returned and return that file
        if(process!=null){
            try (BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                retFile = new File(in.readLine());
                CustomLogger.createLogMsgAndSave("MySQL executable found at " + retFile.toString());
            } catch (IOException e) {
                CustomLogger.createLogMsgAndSave("Unable to locate MySQL executable", "red");
            }
        }
        return retFile;
    }
}
