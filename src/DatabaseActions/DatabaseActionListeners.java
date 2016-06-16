package DatabaseActions;

import Settings.SettingsTab;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DatabaseActionListeners {

    /**
     * This method will received a button reference, a stage
     * reference, and 3 references to TextFields.  When the user
     * confirms the database credentials are correct, the method
     * will change the Credentials on the settings page and
     * re-fire the correct button to attempt the connection again.
     * A pass/fail message is displayed after the connection.
     *
     *
     * @param confirm reference to the confirm details button
     * @param dbStage reference to the window the db credentials is displayed in
     * @param url TextField in which the text is pulled for the url connection string
     * @param user TextField in which the text is pulled for the MySQL user name
     * @param pass TextField in which the text is pulled for the MySQL password
     */
    public static void createConfirmBtnListenerUploadData(Button confirm, Stage dbStage, TextField url, TextField user,
                                                          TextField pass){
        confirm.setOnAction(event -> {

            //Update the values in the settings page with the new correct ones
            SettingsTab.urlTextField.setText(url.getText());
            SettingsTab.userTextField.setText(user.getText());
            SettingsTab.passTextField.setText(pass.getText());

            //Close the prompt, a new one will be created if connection fails again
            dbStage.close();

            //fire buttons after the new info is saved
            UploadDataTab.parseFile.fire();


        });
    }

    /**
     * similar to one above just for different task
     */
    public static void createConfirmBtnListenerGetConnectionForTexasSort(Button confirm, Stage dbStage, TextField url, TextField user,
                                                                         TextField pass){
        confirm.setOnAction(event -> {

            //Update the values in the settings page with the new correct ones
            SettingsTab.urlTextField.setText(url.getText());
            SettingsTab.userTextField.setText(user.getText());
            SettingsTab.passTextField.setText(pass.getText());

            dbStage.close();

            //fire buttons after the new info is saved
            ViewDatabaseTab.sortTxBtn.fire();


        });
    }

    /**
     * similar to one above just for different task
     */
    public static void createConfirmBtnListenerGetConnectionForRefresh(Button confirm, Stage dbStage, TextField url, TextField user,
                                                                         TextField pass){
        confirm.setOnAction(event -> {

            //Update the values in the settings page with the new correct ones
            SettingsTab.urlTextField.setText(url.getText());
            SettingsTab.userTextField.setText(user.getText());
            SettingsTab.passTextField.setText(pass.getText());

            dbStage.close();

            //fire buttons after the new info is saved
            ViewDatabaseTab.refreshBtn.fire();


        });
    }

    public static void createConfirmBtnListenerGetConnectionForEmail(Button confirm, Stage dbStage, TextField url, TextField user,
                                                                     TextField pass, String email, String message){
        confirm.setOnAction(event -> {
            //Update the values in the settings page with the new correct ones
            SettingsTab.urlTextField.setText(url.getText());
            SettingsTab.userTextField.setText(user.getText());
            SettingsTab.passTextField.setText(pass.getText());

            dbStage.close();

            //fire buttons after the new info is saved
            ModifyDatabaseMethods.updateTimeStampAfterEmail(email, message);
        });
    }

    public static void createRefreshBtnListenerViewDBTab(Button refreshBtn, ScrollPane userScrollPane, Button emailBtn){
        refreshBtn.setOnAction(event -> {
            emailBtn.setText("Send Unsorted Email");
            GetDataFromDatabaseWithEmail.getAllUsersFromDatabaseAndAddToVbox(userScrollPane, true);
        });
    }

    public static void createSortTxBtnListenerViewDBTab(Button sortTxBtn, ScrollPane userScrollPane, Button emailBtn){
        sortTxBtn.setOnAction(event -> {
            emailBtn.setText("Send Sorted Email");
            GetDataFromDatabaseWithEmail.getAllSortedUsersFromDatabaseAndAddToVbox(userScrollPane);
        });
    }

    public static void createEmailBtnListenerViewDBTab(Button emailBtn){
        emailBtn.setOnAction(event -> GetDataFromDatabaseWithEmail.sendListViaEmail(emailBtn.getText()));
    }
}
