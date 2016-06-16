package DatabaseActions;

import CustomAlerts.EmailAlerts;
import People.Person;
import People.PersonArrayListDownloadedFromDB;
import Tabs.SettingsTab;
import Tabs.ViewDatabaseTab;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;


/**
 * Created by r730819 on 6/15/2016.
 */
public class GetDataFromDatabaseWithEmail {

    /**
     * Attempts to make a database connection and if ok will
     * pull all the customers from the table.
     * Dynamically creates vbox's will all the users info
     * and adds it to the scroll pane
     * @param userScrollPane Reference to the scrollpane in the ViewDatabaseTab
     * @param selfCall Determines if the texas sort is calling or itself
     */
    public static void getAllUsersFromDatabaseAndAddToVbox(ScrollPane userScrollPane, boolean selfCall){
        Statement statement;
        String sqlStr;
        Connection connection;

        if(selfCall)
            connection = GetDatabaseConnection.getDB(SettingsTab.urlTextField.getText(), SettingsTab.userTextField.getText(),
                SettingsTab.passTextField.getText(), "refresh"); //false for prompt screen
        else
            connection = GetDatabaseConnection.getDB(SettingsTab.urlTextField.getText(), SettingsTab.userTextField.getText(),
                    SettingsTab.passTextField.getText(), "texasSort"); //false for prompt screen

        if(connection!=null) {

            //Clear persons array before reloading it just in case
            PersonArrayListDownloadedFromDB.arrayList.clear();

            try {
                statement = connection.createStatement();

                sqlStr = "SELECT * FROM customers;";

                ResultSet rs = statement.executeQuery(sqlStr);

                //Create vbox that holds all the vbox's created below
                VBox overallVbox = new VBox(20);
                overallVbox.setAlignment(Pos.CENTER);

                //Add texas title to vbox for display and color red for visibility
                Label customerTitle = new Label("=== Sequential Customer List ===");
                customerTitle.setStyle("-fx-text-fill: darkred;");
                overallVbox.getChildren().add(customerTitle);

                int counter = 1;

                while (rs.next()) {
                    //last_name, first_name, email_addr, home_addr, city, state, zip_code, time_stamp
                    //Retrieve by data by column names
                    String last_name = rs.getString("last_name");
                    String first_name = rs.getString("first_name");
                    String email_addr = rs.getString("email_addr");
                    String home_addr = rs.getString("home_addr");
                    String city = rs.getString("city");
                    String state = rs.getString("state");
                    String zip_code = rs.getString("zip_code");
                    String time_stamp = rs.getString("time_stamp");

                    //Create a new person and add to array list for later use
                    Person person = new Person();
                    person.lastName = last_name;
                    person.firstName = first_name;
                    person.emailAddr = email_addr;
                    person.homeAddr = home_addr;
                    person.city = city;
                    person.state = state;
                    person.zipCode = zip_code;
                    person.timeStamp = time_stamp;
                    person.customerNum = counter;

                    PersonArrayListDownloadedFromDB.arrayList.add(person);

                    //Create labels and add to the vbox, I can do this in another method but for now do it here...
                    Label customerNumLabel = new Label("Customer #" + counter);
                    Label nameLabel = new Label(last_name + " " + first_name);
                    Label emailLabel = new Label(email_addr);
                    Label homeAddrLabel = new Label(home_addr);
                    Label cityLabel = new Label(city + ", " + state + " " + zip_code);
                    Label timeStampLabel = new Label(time_stamp);

                    //custom email link function
                    emailLabel.setStyle("-fx-text-fill: blue; -fx-underline: true;");

                    //Ehh just for fun
                    emailLabel.setOnMouseClicked(event -> {
                        Desktop desktop;
                        if (Desktop.isDesktopSupported()
                                && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
                            URI mailto = null;
                            try {

                                mailto = new URI("mailto:" + email_addr + "?subject=Assignment1");
                                desktop.mail(mailto);
                            } catch (URISyntaxException | IOException e) {
                                e.printStackTrace();
                            }

                        } else {
                            //throw error
                        }
                    });

                    //Add some color to those email links
                    emailLabel.setOnMouseEntered(event -> emailLabel.setStyle("-fx-text-fill: black; -fx-underline: true;"));
                    emailLabel.setOnMouseExited(event -> emailLabel.setStyle("-fx-text-fill: blue; -fx-underline: true;"));

                    //Create vbox to add to scrollpane
                    VBox personDetailsVbox = new VBox(3);
                    personDetailsVbox.getChildren().addAll(customerNumLabel, nameLabel, emailLabel, homeAddrLabel,
                            cityLabel, timeStampLabel);

                    //Add a box around the vbox to see better
                    personDetailsVbox.setStyle("-fx-border-color: black");
                    personDetailsVbox.setAlignment(Pos.CENTER);

                    //Add to overall vbox
                    overallVbox.getChildren().add(personDetailsVbox);

                    counter++;//increment customer number
                }

                rs.close();

                //Add the vbox to the scroll pane
                userScrollPane.setContent(overallVbox);

                //If at least one person was pulled from db, unlock the email button
                if (!PersonArrayListDownloadedFromDB.arrayList.isEmpty()) {
                    ViewDatabaseTab.emailBtn.setDisable(false);
                    ViewDatabaseTab.emailLabel.setVisible(false);
                } else {
                    ViewDatabaseTab.emailBtn.setDisable(true);
                    ViewDatabaseTab.emailLabel.setVisible(true);
                }


            } catch (SQLException e) {
                e.printStackTrace();
                //todo add log error
            }
        }

    }

    /**
     * Similar to the function above but pulls them in sorted order
     * @param userScrollPane Reference to the scrollpane in the ViewDatabaseTab
     */
    public static void getAllSortedUsersFromDatabaseAndAddToVbox(ScrollPane userScrollPane){

        getAllUsersFromDatabaseAndAddToVbox(userScrollPane, false);//call the function above to load the PersonArrayListDownloaded.

        //If the array is not empty, proceed..else do nothing
        if(!PersonArrayListDownloadedFromDB.arrayList.isEmpty()) {

            //Create vbox that holds all the vbox's created below
            VBox overallVbox = new VBox(20);
            overallVbox.setAlignment(Pos.CENTER);

            ArrayList<Person> sortedArray;
            ArrayList<Person> tempArray;

            tempArray = new ArrayList<>(PersonArrayListDownloadedFromDB.arrayList); //copy over the array
            sortedArray = new ArrayList<>();

            //Add texas title to vbox for display and color red for visibility
            Label texasTitle = new Label("=== Texas Customers ===");
            texasTitle.setStyle("-fx-text-fill: darkred;");
            overallVbox.getChildren().add(texasTitle);

            //Add all the texas persons first
            for (Person person : tempArray) {
                if (person.state.toLowerCase().equals("tx")) {
                    sortedArray.add(person);
                }
            }


            //add the rest of the non texans
            for (Person person : tempArray) {
                if (!person.state.toLowerCase().equals("tx")) {
                    sortedArray.add(person);
                }
            }

            //Make the static array sorted
            PersonArrayListDownloadedFromDB.arrayList = new ArrayList<>(sortedArray);

            boolean firstNonTexan = true;
            for (Person person : sortedArray) {

                //Determine the correct position to put the next label
                if(!person.state.toLowerCase().equals("tx")){
                    if(firstNonTexan){
                        //Add non-texas title to vbox for display and color red for visibility
                        Label nonTexasTitle = new Label("=== Out of State Customers ===");
                        nonTexasTitle.setStyle("-fx-text-fill: darkred;");
                        overallVbox.getChildren().add(nonTexasTitle);
                        firstNonTexan = false;
                    }
                }

                //Create labels and add to the vbox, I can do this in another method but for now do it here...
                Label customerNumLabel = new Label("Customer #" + person.customerNum);
                Label nameLabel = new Label(person.lastName + " " + person.firstName);
                Label emailLabel = new Label(person.emailAddr);
                Label homeAddrLabel = new Label(person.homeAddr);
                Label cityLabel = new Label(person.city + ", " + person.state + " " + person.zipCode);
                Label timeStampLabel = new Label(person.timeStamp);

                //custom email link function
                emailLabel.setStyle("-fx-text-fill: blue; -fx-underline: true;");

                //Ehh just for fun add option to email people
                emailLabel.setOnMouseClicked(event -> {
                    Desktop desktop;
                    if (Desktop.isDesktopSupported()
                            && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
                        URI mailto = null;
                        try {

                            mailto = new URI("mailto:" + person.emailAddr + "?subject=Assignment1");
                            desktop.mail(mailto);
                        } catch (URISyntaxException | IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        //throw error
                    }
                });

                emailLabel.setOnMouseEntered(event -> emailLabel.setStyle("-fx-text-fill: black; -fx-underline: true;"));
                emailLabel.setOnMouseExited(event -> emailLabel.setStyle("-fx-text-fill: blue; -fx-underline: true;"));


                //Create vbox to add to scrollpane
                VBox personDetailsVbox = new VBox(3);
                personDetailsVbox.getChildren().addAll(customerNumLabel, nameLabel, emailLabel, homeAddrLabel,
                        cityLabel, timeStampLabel);

                //Add a box around the vbox to see better
                personDetailsVbox.setStyle("-fx-border-color: black");
                personDetailsVbox.setAlignment(Pos.CENTER);

                //Add to overall vbox
                overallVbox.getChildren().add(personDetailsVbox);
            }

            //Add the vbox to the scroll pane
            userScrollPane.setContent(overallVbox);
        }

    }

    /**
     * This function will open an alert box to ask where
     * to send the email to.  Upon confirmation will open
     * the default email app to send the email.
     */
    public static void sendListViaEmail(String toSortOrNotToSort){
        //getAllSortedUsersFromDatabaseAndAddToVbox(userScrollPane, false); ---No longer sort before, option to send unsorted

        TextInputDialog dialog = new TextInputDialog("user@domain.com");
        dialog.setTitle("Email Prompt");
        dialog.setHeaderText("Email Sorted List");
        dialog.setContentText("Enter email address to send list too");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();

        // Call method to send email from the result
        if (result.isPresent()){
            if(toSortOrNotToSort.equals("Send Unsorted Email"))
                createEmail(result.get(), false);
            else
                createEmail(result.get(), true);
        }
    }

    /**
     * Creates the email with it being sorted or unsorted
     * and then sends to the next method to be sent via Javax
     * @param email The email to send to
     * @param sortEmail Boolean whether to sort or not
     */
    private static void createEmail(String email, boolean sortEmail){
        //send a sorted email
        if(sortEmail){
            String emailMsg = "=== Texas Customers ===\n\n";
            boolean firstNonTexan = true;

            //Go through all the persons and create the email from it
            for (Person person : PersonArrayListDownloadedFromDB.arrayList) {
                String personString = "";

                //Determine when there are no more Texas Persons
                if(!person.state.toLowerCase().equals("tx")){
                    if(firstNonTexan){
                        personString = "=== Out of State Customers ===\n\n";
                        firstNonTexan = false;
                    }
                }

                //Create strings to be sent in the email
                personString += ("Customer #" + person.customerNum + "\n");
                personString += (person.lastName + " " + person.firstName + "\n");
                personString += (person.emailAddr + "\n");
                personString += (person.homeAddr + "\n");
                personString += (person.city + ", " + person.state + " " + person.zipCode + "\n\n\n");
                //personString += (person.timeStamp + "\n\n\n"); ---don't send timestamp for now

                //Add person text to overall email message
                emailMsg += personString;
            }

            sendJavaxEmail(email, emailMsg);

            //Update timestamps
            ModifyDatabaseMethods.updateTimeStampAfterEmail(email, emailMsg);


            //Send a unsorted email
        }else {
            String emailMsg = "=== Customer List ===\n\n";

            //Go through all the persons and create the email from it
            for (Person person : PersonArrayListDownloadedFromDB.arrayList) {
                String personString = "";

                //Create strings to be sent in the email
                personString += ("Customer #" + person.customerNum + "\n");
                personString += (person.lastName + " " + person.firstName + "\n");
                personString += (person.emailAddr + "\n");
                personString += (person.homeAddr + "\n");
                personString += (person.city + ", " + person.state + " " + person.zipCode + "\n\n\n");
                //personString += (person.timeStamp + "\n\n\n"); ---don't send timestamp for now

                //Add person text to overall email message
                emailMsg += personString;
            }

            sendJavaxEmail(email, emailMsg);

            //Update timestamps
            ModifyDatabaseMethods.updateTimeStampAfterEmail(email, emailMsg);

        }
    }

    private static void sendJavaxEmail(String toEmail, String message){

        // Sender's email ID needs to be mentioned
        String from = "Rutkoski.Augustus@heb.com";

        // Assuming you are sending email from localhost
        String host = "exchange.heb.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try{
            // Create a default MimeMessage object.
            MimeMessage mimeMessage = new MimeMessage(session);

            // Set From: header field of the header.
            mimeMessage.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            // Set Subject: header field
            mimeMessage.setSubject("Assignment 1 - Customer List");

            // Now set the actual message
            mimeMessage.setText(message);

            // Send message
            Transport.send(mimeMessage);

            //send good log
        }catch (MessagingException mex) {
            EmailAlerts alerts = new EmailAlerts();
            alerts.badSend();
            //send bad log
        }
    }
}
