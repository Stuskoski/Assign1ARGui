package Tabs;

import FileActions.CustomLogger;
import augustus.MainScreen.Main;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
/**
 * Created by r730819 on 6/15/2016.
 * Home tab or the help landing page.
 * This is the first tab the user
 * sees when the program opens.
 *
 * It is basically a readme but in
 * the gui instead of a seperate
 * text file
 */
public class HomeTab {
    public static Tab homeTab = new Tab();

    /**
     * TabPane Action Selectors are added here
     * for simplicity since the help page is
     * possibly going to be optional
     */
    public static void createHomeTab(){
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        Label titleLabel = new Label("Assignment 1 Landing Page");
        HBox titleHbox = new HBox(titleLabel);

        titleHbox.setOnMouseClicked(event -> {
            SingleSelectionModel<Tab> selectionModel = Main.tabPane.getSelectionModel();
            selectionModel.select(SettingsTab.settingsTab);
        });

        Label setDBCredLabel = new Label("1) Click here or go to the Settings tab to set up correct credentials");
        Label makeDBLabel = new Label("2) Click here or go to the DatabaseActions tab to create database");
        Label loadDataLabel = new Label("3) Click here or go to the Upload Data tab to select/type file to input data into database.");
        Label viewDBLabel = new Label("5) Click here or go to the View Database tab to view/email database entries.");

        //titleHbox options
        titleHbox.setAlignment(Pos.CENTER);

        //BorderPane Options
        borderPane.setTop(titleHbox);
        borderPane.setCenter(gridPane);

        //GridPane Options
        gridPane.setAlignment(Pos.CENTER_LEFT);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(setDBCredLabel, 0, 0);

        //Tab Options
        homeTab.setText("Home");
        homeTab.setClosable(false); //Unable to close tab
        homeTab.setContent(borderPane);

        //Added graphics for looks
        titleLabel.setStyle("-fx-font-weight: bold");

        CustomLogger.createLogMsgAndSave("Home tab loaded");
    }
}
