package DatabaseActions;

import People.Person;
import People.PersonArrayListDownloadedFromDB;
import People.PersonsArrayList;
import Settings.SettingsTab;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

/**
 * Created by r730819 on 6/15/2016.
 */
public class GetDataFromDatabase {

    /**
     * Attempts to make a database connection and if ok will
     * pull all the customers from the table.
     * Dynamically creates vbox's will all the users info
     * and adds it to the scroll pane
     * @param userScrollPane Reference to the scrollpane in the ViewDatabaseTab
     */
    public static void getAllUsersFromDatabaseAndAddToVbox(ScrollPane userScrollPane){
        Statement statement;
        String sqlStr;
        Connection connection;

        connection = GetDatabaseConnection.getDB(SettingsTab.urlTextField.getText(), SettingsTab.userTextField.getText(),
                SettingsTab.passTextField.getText());


        try {
            statement = connection.createStatement();

            sqlStr = "SELECT * FROM customers;";

            ResultSet rs = statement.executeQuery(sqlStr);

            //Create vbox that holds all the vbox's created below
            VBox overallVbox = new VBox(20);

            int counter = 1;

            while(rs.next()){
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
                Label nameLabel = new Label(last_name+" "+first_name);
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

                            mailto = new URI("mailto:"+email_addr+"?subject=Assignment1");
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

                //Add to overall vbox
                overallVbox.getChildren().add(personDetailsVbox);

                counter++;//increment customer number
            }

            rs.close();

            //Add the vbox to the scroll pane
            userScrollPane.setContent(overallVbox);

            //If at least one person was pulled from db, unlock the email button
            if(!PersonArrayListDownloadedFromDB.arrayList.isEmpty()){
                ViewDatabaseTab.emailBtn.setDisable(false);
                ViewDatabaseTab.emailLabel.setVisible(false);
            }



        } catch (SQLException e) {
            e.printStackTrace();
            //todo add log error
        }

    }

    /**
     * Similar to the function above but pulls them in sorted order
     * @param userScrollPane Reference to the scrollpane in the ViewDatabaseTab
     */
    public static void getAllSortedUsersFromDatabaseAndAddToVbox(ScrollPane userScrollPane){

    }

    /**
     * This function will open an alert box to ask where
     * to send the email to.  Upon confirmation will open
     * the default email app to send the email.
     */
    public static void sortUsersAndSendViaEmail(){
        TextInputDialog dialog = new TextInputDialog("user@domain.com");
        dialog.setTitle("Email Prompt");
        dialog.setHeaderText("Email Sorted List");
        dialog.setContentText("Enter email address to send list too");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();

        // Call method to send email from the result
        result.ifPresent(GetDataFromDatabase::openEmailClient);
    }

    private static void openEmailClient(String email){

    }
}
