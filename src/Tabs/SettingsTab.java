package Tabs;

import FileActions.CustomLogger;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by r730819 on 6/15/2016.
 *
 * Settings tab for the user.  This
 * tab holds the credentials and the
 * URL connection string for the database.
 *
 * Every time a connection is attempted, the
 * credentials are pulled from the 3 TextFields.
 * If the program is unable to make a connection
 * the program prompts the user to edit the
 * connection parameters.
 */
public class SettingsTab {
    public static Tab settingsTab = new Tab();
    public static TextField urlTextField = new TextField("jdbc:mysql://localhost:3306/assign1_db_augustus");
    public static TextField userTextField = new TextField();
    public static TextField passTextField = new TextField();
    public static PasswordField passPasswordField = new PasswordField();


    public static void createSettingsTab(){
        GridPane gridPane = new GridPane();
        Label titleLabel = new Label("Database Credentials");
        Label urlLabel = new Label("URL:");
        Label userLabel = new Label("User:");
        Label passLabel = new Label("Password:");
        Label warningLabel = new Label("* URL Prefilled With Script Database *");
        HBox titleHbox = new HBox(titleLabel);
        BorderPane borderPane = new BorderPane();
        VBox passwordVBox = new VBox(passTextField, passPasswordField);
        CheckBox showPassChkBox = new CheckBox("Show Password");


        //titleHBox options
        titleHbox.setAlignment(Pos.CENTER);

        //BorderPane options
        borderPane.setTop(titleHbox);
        borderPane.setCenter(gridPane);

        //Text/Pass Field options
        urlTextField.setPromptText("URL Connection String");
        urlTextField.setPrefWidth(250);
        urlTextField.setTooltip(new Tooltip("Usage: 'jdbc:mysql://[HOST]:[PORT]/[Database Name]'"));

        userTextField.setPromptText("Enter User");
        userTextField.setPrefWidth(250);

        passTextField.setPromptText("Enter Password");
        passTextField.setPrefWidth(250);
        passTextField.setManaged(false);
        passTextField.setVisible(false);

        passPasswordField.setPromptText("Enter Password");
        passPasswordField.setPrefWidth(250);




        //GridPane options
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(urlLabel, 0, 2); //col row
        gridPane.add(urlTextField, 2, 2);
        gridPane.add(userLabel, 0, 3);
        gridPane.add(userTextField, 2, 3);
        gridPane.add(passLabel, 0, 4);
        gridPane.add(passwordVBox, 2, 4);
        gridPane.add(showPassChkBox, 2, 5);
        gridPane.add(warningLabel, 0, 7, 6, 1);


        //Tab options
        settingsTab.setContent(borderPane);
        settingsTab.setText("Settings");
        settingsTab.setClosable(false); //Unable to close tab

        //bind the managed and visibile properties of the fields with the checkbox
        passTextField.managedProperty().bind(showPassChkBox.selectedProperty());
        passTextField.visibleProperty().bind(showPassChkBox.selectedProperty());
        passPasswordField.managedProperty().bind(showPassChkBox.selectedProperty().not());
        passPasswordField.visibleProperty().bind(showPassChkBox.selectedProperty().not());

        // Bind the TextField and PasswordField to have the same characters
        passTextField.textProperty().bindBidirectional(passPasswordField.textProperty());


        //Added graphics just for looks
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
        urlLabel.setStyle("-fx-font-weight: bold");
        userLabel.setStyle("-fx-font-weight: bold");
        passLabel.setStyle("-fx-font-weight: bold");
        warningLabel.setStyle("-fx-text-fill: darkred");

        CustomLogger.createLogMsgAndSave("Settings tab loaded");

    }
}
