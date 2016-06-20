package DatabaseActions;

import Tabs.SettingsTab;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by r730819 on 6/14/2016.
 *
 * Class that will create a screen to prompt
 * the user that the program was unable
 * to get a database connection.  The screen
 * prompts the user to input the correct credentials.
 *
 *
 */
public class PromptForDatabaseCredentialsScreen {
    public static Stage dbCredsStage = new Stage(); //prevents multiple windows from popping up without reference to close

    /**
     *
     * @param whoNeedsTheCreds String of who needs the prompt, used to determine which action listener for confirm button will be created
     */
    public static void createScreen(String whoNeedsTheCreds){
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 350, 225); //width height
        Label errorLabel = new Label("Error: Unable to make MySQL connection!");
        Label urlLabel = new Label("URL:");
        Label userLabel = new Label("User:");
        Label passLabel = new Label("Password:");
        Label warningLabel = new Label("* URL Prefilled With Script Database *");
        TextField urlTextField = new TextField(SettingsTab.urlTextField.getText());
        TextField userTextField = new TextField(SettingsTab.userTextField.getText());
        TextField passTextField = new TextField(SettingsTab.passTextField.getText());
        Button confirmDetailsBtn = new Button("Confirm");
        HBox confirmDetailsHbox = new HBox(confirmDetailsBtn);

        //set hbox to center to center confirm button
        confirmDetailsHbox.setAlignment(Pos.CENTER);

        //textfield options
        urlTextField.setPromptText("URL Connection String");
        urlTextField.setTooltip(new Tooltip("Usage: 'jdbc:mysql://[HOST]:[PORT]/[Database Name]'"));
        userTextField.setPromptText("Enter User");
        passTextField.setPromptText("Enter Password");


        //Gridpane options
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        gridPane.add(errorLabel, 0, 0, 4, 1);
        gridPane.add(urlLabel, 0, 2); //col row
        gridPane.add(urlTextField, 2, 2);
        gridPane.add(userLabel, 0, 3);
        gridPane.add(userTextField, 2, 3);
        gridPane.add(passLabel, 0, 4);
        gridPane.add(passTextField, 2, 4);
        gridPane.add(confirmDetailsHbox, 0, 9, 4, 1); //col row col_span row_span
        gridPane.add(warningLabel, 0, 10, 4, 1);

        //Create action listeners
        switch (whoNeedsTheCreds){
            case "uploadData":
                DatabaseActionListeners.createConfirmBtnListenerUploadData(confirmDetailsBtn, dbCredsStage, urlTextField,
                        userTextField, passTextField);
                break;
            case "texasSort":
                DatabaseActionListeners.createConfirmBtnListenerGetConnectionForTexasSort(confirmDetailsBtn, dbCredsStage, urlTextField,
                        userTextField, passTextField);
                break;
            case "refresh":
                DatabaseActionListeners.createConfirmBtnListenerGetConnectionForRefresh(confirmDetailsBtn, dbCredsStage, urlTextField,
                        userTextField, passTextField);
                break;
            case "email":
                DatabaseActionListeners.createConfirmBtnListenerGetConnectionForEmail(confirmDetailsBtn, dbCredsStage, urlTextField,
                        userTextField, passTextField);
                break;
        }

        //Stage options
        dbCredsStage.setTitle("Confirm DB Credentials");

        //Added graphics just for looks
        urlLabel.setStyle("-fx-font-weight: bold");
        userLabel.setStyle("-fx-font-weight: bold");
        passLabel.setStyle("-fx-font-weight: bold");
        warningLabel.setStyle("-fx-text-fill: darkred");
        errorLabel.setStyle("-fx-text-fill: darkred; -fx-font-weight: bold;"); //todo add blinking with timeline if have extra time?

        dbCredsStage.setScene(scene);

        dbCredsStage.show();
    }
}
