package DatabaseActions;

import FileActions.CustomLogger;
import Tabs.DatabaseActionsTab;
import Tabs.SettingsTab;
import Tabs.UploadDataTab;
import Tabs.ViewDatabaseTab;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DatabaseActionListeners {

    /**
     * This method receives 2 references, a confirm button
     * and the stage of the new popup.  An action listener
     * is created for the button specifically for uploading
     * data confirmation.  There are methods below for the
     * other actions in the program.  Since the textfields
     * between the 2 screens are bound, the credentials are
     * automatically saved.
     *
     * When the user clicks confirm, a fire event for
     * the specific action, Uploading data in this case,
     * is fired.
     *
     * @param confirm reference to the confirm details button
     * @param dbStage reference to the window the db credentials is displayed in
     */
    public static void createConfirmBtnListenerUploadData(Button confirm, Stage dbStage){
        confirm.setOnAction(event -> {
            CustomLogger.createLogMsgAndSave("Database credentials saved.  Changes reflected in settings tab.");

            //Close the prompt, a new one will be created if connection fails again
            dbStage.close();

            //fire buttons after the new info is saved
            UploadDataTab.parseFile.fire();
            CustomLogger.createLogMsgAndSave("Attempting to re-upload data");
        });
    }

    /**
     * similar to one above just for changing credentials for grabbing sorted data
     */
    public static void createConfirmBtnListenerGetConnectionForTexasSort(Button confirm, Stage dbStage){
        confirm.setOnAction(event -> {
            CustomLogger.createLogMsgAndSave("Database credentials saved.  Changes reflected in settings tab.");

            dbStage.close();

            //fire buttons after the new info is saved
            ViewDatabaseTab.sortTxBtn.fire();
            CustomLogger.createLogMsgAndSave("Attempting to retrieve unsorted data again");
        });
    }

    /**
     * similar to one above just for changing credentials for grabbing unsorted data
     */
    public static void createConfirmBtnListenerGetConnectionForRefresh(Button confirm, Stage dbStage){
        confirm.setOnAction(event -> {
            CustomLogger.createLogMsgAndSave("Database credentials saved.  Changes reflected in settings tab.");

            dbStage.close();

            //fire buttons after the new info is saved
            ViewDatabaseTab.refreshBtn.fire();

            CustomLogger.createLogMsgAndSave("Attempting to retrieve sorted data again");
        });
    }

    /**
     * Similar to the methods above but this is for changing
     * database credentials for emailing
     */
    public static void createConfirmBtnListenerGetConnectionForEmail(Button confirm, Stage dbStage){
        confirm.setOnAction(event -> {
            CustomLogger.createLogMsgAndSave("Database credentials saved.  Changes reflected in settings tab.");

            dbStage.close();

            //fire buttons after the new info is saved
            ModifyDatabaseMethods.updateTimeStampAfterEmail();
            CustomLogger.createLogMsgAndSave("Attempting to update timestamps again");
        });
    }

    /**
     * Creates an action listener for the Load Data button on
     * the View Database Tab.  Changes the text for email button.
     */
    public static void createRefreshBtnListenerViewDBTab(Button loadDataBtn, ScrollPane userScrollPane, Button emailBtn){
        loadDataBtn.setOnAction(event -> {
            emailBtn.setText("Send Unsorted Email");
            GetDataFromDatabaseWithEmail.getAllUsersFromDatabaseAndAddToVBox(userScrollPane, true);
        });
    }

    /**
     * Creates an action listener for the Texas Sort button on
     * the View Database Tab.  Changes the text for email button.
     */
    public static void createSortTxBtnListenerViewDBTab(Button sortTxBtn, ScrollPane userScrollPane, Button emailBtn){
        sortTxBtn.setOnAction(event -> {
            emailBtn.setText("Send Sorted Email");
            GetDataFromDatabaseWithEmail.getAllSortedUsersFromDatabaseAndAddToVbox(userScrollPane);
        });
    }

    /**
     * Creates an action listener for the Email button on
     * the View Database Tab.
     */
    public static void createEmailBtnListenerViewDBTab(Button emailBtn){
        emailBtn.setOnAction(event -> GetDataFromDatabaseWithEmail.sendListViaEmail(emailBtn.getText()));
    }


    /**
     * Start Action Listeners for the Database Actions
     * Tab buttons
     */
    public static void createMakeDBBtnListener(Button makeDB){
        makeDB.setOnAction(event -> ModifyDatabaseMethods.makeClearDeleteDB("make"));
    }
    public static void createClearDBBtnListener(Button clearDB){
        clearDB.setOnAction(event -> ModifyDatabaseMethods.makeClearDeleteDB("clear"));
    }
    public static void createDeleteDBBtnListener(Button deleteDB){
        deleteDB.setOnAction(event -> ModifyDatabaseMethods.makeClearDeleteDB("delete"));
    }

    //Disable the database action buttons if any of the settings fields are empty since unable to check credentials with cmd executables
    public static void createDatabaseActionTabListener(Tab tab){
        tab.setOnSelectionChanged(event -> {


            if(SettingsTab.urlTextField.getText().equals("") || SettingsTab.userTextField.getText().equals("") ||
                    SettingsTab.passTextField.getText().equals("")){
                        DatabaseActionsTab.makeDBBtn.setDisable(true);
                        DatabaseActionsTab.clearDBBtn.setDisable(true);
                        DatabaseActionsTab.deleteDBBtn.setDisable(true);
                        DatabaseActionsTab.buttonsDisabledLabel.setVisible(true);
                        DatabaseActionsTab.buttonsDisabledLabel.setManaged(true);
                        CustomLogger.createLogMsgAndSave("Database Actions Disabled", "red"); //todo log msg comes out twice since enter/exit is an event
            }

            if(!SettingsTab.urlTextField.getText().equals("") && !SettingsTab.userTextField.getText().equals("") &&
                    !SettingsTab.passTextField.getText().equals("")){
                        DatabaseActionsTab.makeDBBtn.setDisable(false);
                        DatabaseActionsTab.clearDBBtn.setDisable(false);
                        DatabaseActionsTab.deleteDBBtn.setDisable(false);
                        DatabaseActionsTab.buttonsDisabledLabel.setVisible(false);
                        DatabaseActionsTab.buttonsDisabledLabel.setManaged(false);
                        CustomLogger.createLogMsgAndSave("Database Actions Enabled");
            }
        });
    }
    // End action listeners for DatabaseActionsTab

}
