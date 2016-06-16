package DatabaseActions;

import Settings.SettingsTab;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by r730819 on 6/15/2016.
 */
public class DatabaseActionListeners {

    /**
     * This method will received a button reference and a stage
     * reference.  When the user confirms the database credentials
     * are correct, the listener will close the stage, attempt the
     * db connection and upload data into db.  A new window is
     * created and displays a success or fail message
     *
     * todo - correct this description with new implementation
     *
     * @param confirm reference to the confirm details button
     * @param dbStage reference to the window the db credentials is displayed in
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

    public static void createRefreshBtnListenerViewDBTab(Button refreshBtn, ScrollPane userScrollPane){
        refreshBtn.setOnAction(event -> GetDataFromDatabase.getAllUsersFromDatabaseAndAddToVbox(userScrollPane, true));
    }

    public static void createsortTxBtnListenerViewDBTab(Button sortTxBtn, ScrollPane userScrollPane){
        sortTxBtn.setOnAction(event -> GetDataFromDatabase.getAllSortedUsersFromDatabaseAndAddToVbox(userScrollPane));
    }

    public static void createEmailBtnListenerViewDBTab(Button emailBtn){
        emailBtn.setOnAction(event -> GetDataFromDatabase.sortUsersAndSendViaEmail());
    }
}
