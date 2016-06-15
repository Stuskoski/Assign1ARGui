package DatabaseActions;

import Settings.SettingsTab;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;

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

            Connection connection = GetDatabaseConnection.getDB(url.getText(), user.getText(), pass.getText());

            if(connection!=null){
                dbStage.close();
                ModifyDatabaseMethods.attemptUploadData(connection);
            }else{
                // todo - throw error
            }

        });
    }

    public static void createRefreshBtnListenerViewDBTab(Button refreshBtn, ScrollPane userScrollPane){
        refreshBtn.setOnAction(event -> GetDataFromDatabase.getAllUsersFromDatabaseAndAddToVbox(userScrollPane));
    }

    public static void createsortTxBtnListenerViewDBTab(Button sortTxBtn, ScrollPane userScrollPane){
        sortTxBtn.setOnAction(event -> GetDataFromDatabase.getAllSortedUsersFromDatabaseAndAddToVbox(userScrollPane));
    }

    public static void createEmailBtnListenerViewDBTab(Button emailBtn){
        emailBtn.setOnAction(event -> GetDataFromDatabase.sortUsersAndSendViaEmail());
    }
}
