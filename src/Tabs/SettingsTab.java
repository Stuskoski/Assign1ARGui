package Tabs;

import FileActions.CustomLogger;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Created by r730819 on 6/15/2016.
 */
public class SettingsTab {
    public static Tab settingsTab = new Tab();
    public static TextField urlTextField = new TextField("jdbc:mysql://localhost:3306/assign1_db_augustus");
    public static TextField userTextField = new TextField("augustus");
    public static TextField passTextField = new TextField("mypass123");


    public static void createSettingsTab(){
        GridPane gridPane = new GridPane();
        Label titleLabel = new Label("Database Credentials");
        Label urlLabel = new Label("URL:");
        Label userLabel = new Label("User:");
        Label passLabel = new Label("Password:");
        Label warningLabel = new Label("* Values Prefilled With Script Credentials *");
        HBox titleHbox = new HBox(titleLabel);
        BorderPane borderPane = new BorderPane();

        //titleHbox options
        titleHbox.setAlignment(Pos.CENTER);

        //Borderpane options
        borderPane.setTop(titleHbox);
        borderPane.setCenter(gridPane);

        //textfield options
        urlTextField.setPromptText("URL Connection String");
        userTextField.setPromptText("Enter User");
        passTextField.setPromptText("Enter Password");


        //Gridpane options
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(urlLabel, 0, 2); //col row
        gridPane.add(urlTextField, 2, 2);
        gridPane.add(userLabel, 0, 3);
        gridPane.add(userTextField, 2, 3);
        gridPane.add(passLabel, 0, 4);
        gridPane.add(passTextField, 2, 4);
        gridPane.add(warningLabel, 0, 7, 6, 1);


        //Tab options
        settingsTab.setContent(borderPane);
        settingsTab.setText("Settings");
        settingsTab.setClosable(false); //Unable to close tab

        //Added graphics just for looks
        urlLabel.setStyle("-fx-font-weight: bold");
        userLabel.setStyle("-fx-font-weight: bold");
        passLabel.setStyle("-fx-font-weight: bold");
        warningLabel.setStyle("-fx-text-fill: darkred");
        titleLabel.setStyle("-fx-font-weight: bold");

        //CustomLogger.createLogMsgAndSave("Settings tab loaded");

    }
}
