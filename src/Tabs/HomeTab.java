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

        //Main messsage labels
        Label setDBCredLabel1 = new Label("1) Click");
        Label makeDBLabel1 = new Label("2) Click");
        Label loadDataLabel1 = new Label("3) Click");
        Label viewDBLabel1 = new Label("5) Click");
        Label setDBCredLabel2 = new Label("or go to the Settings tab to set up correct credentials");
        Label makeDBLabel2 = new Label("or go to the DatabaseActions tab to create database");
        Label loadDataLabel2 = new Label("or go to the Upload Data tab to select/type file to input data into database");
        Label viewDBLabel2 = new Label("or go to the View Database tab to view/email database entries");

        //Labels for the word 'here' so only that word can be clicked
        Label setDBHereLabel = new Label("here");
        Label makeDBHereLabel = new Label("here");
        Label loadDataHereLabel = new Label("here");
        Label viewDBHereLabel = new Label("here");

        //Add to hbox so they are together
        HBox setDBCredHBox = new HBox(3, setDBCredLabel1, setDBHereLabel, setDBCredLabel2);
        HBox makeDBHBox = new HBox(3, makeDBLabel1, makeDBHereLabel, makeDBLabel2);
        HBox loadDataHBox = new HBox(3, loadDataLabel1, loadDataHereLabel, loadDataLabel2);
        HBox viewDBHBox = new HBox(3, viewDBLabel1, viewDBHereLabel, viewDBLabel2);


        //titleHbox options
        titleHbox.setAlignment(Pos.CENTER);

        //BorderPane Options
        borderPane.setTop(titleHbox);
        borderPane.setCenter(gridPane);

        //GridPane Options
        gridPane.setAlignment(Pos.CENTER_LEFT);
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.add(setDBCredHBox, 0, 0);
        gridPane.add(makeDBHBox, 0, 1);
        gridPane.add(loadDataHBox, 0, 2);
        gridPane.add(viewDBHBox, 0, 3);

        //Tab Options
        homeTab.setText("Home");
        homeTab.setClosable(false); //Unable to close tab
        homeTab.setContent(borderPane);

        //Action listeners here instead of normal separated classes since help tab is possibly optional
        setDBHereLabel.setOnMouseClicked(event -> {
            SingleSelectionModel<Tab> selectionModel = Main.tabPane.getSelectionModel();
            selectionModel.select(SettingsTab.settingsTab);
        });
        makeDBHereLabel.setOnMouseClicked(event -> {
            SingleSelectionModel<Tab> selectionModel = Main.tabPane.getSelectionModel();
            selectionModel.select(DatabaseActionsTab.databaseActionsTab);
        });
        loadDataHereLabel.setOnMouseClicked(event -> {
            SingleSelectionModel<Tab> selectionModel = Main.tabPane.getSelectionModel();
            selectionModel.select(UploadDataTab.uploadDataTab);
        });
        viewDBHereLabel.setOnMouseClicked(event -> {
            SingleSelectionModel<Tab> selectionModel = Main.tabPane.getSelectionModel();
            selectionModel.select(ViewDatabaseTab.viewDatabaseTab);
        });

        //Action listeners to make the here label turn black on hover
        setDBHereLabel.setOnMouseEntered(event -> {
            setDBHereLabel.setStyle("-fx-font-size: 14; -fx-text-fill: deepskyblue; -fx-font-weight: bold;");
        });
        makeDBHereLabel.setOnMouseEntered(event -> {
            makeDBHereLabel.setStyle("-fx-font-size: 14; -fx-text-fill: deepskyblue; -fx-font-weight: bold;");
        });
        loadDataHereLabel.setOnMouseEntered(event -> {
            loadDataHereLabel.setStyle("-fx-font-size: 14; -fx-text-fill: deepskyblue; -fx-font-weight: bold;");
        });
        viewDBHereLabel.setOnMouseEntered(event -> {
            viewDBHereLabel.setStyle("-fx-font-size: 14; -fx-text-fill: deepskyblue; -fx-font-weight: bold;");
        });

        //Action listeners to make the here label turn back blue on moused exited
        setDBHereLabel.setOnMouseExited(event -> {
            setDBHereLabel.setStyle("-fx-font-size: 14; -fx-text-fill: blue; -fx-font-weight: bold;");
        });
        makeDBHereLabel.setOnMouseExited(event -> {
            makeDBHereLabel.setStyle("-fx-font-size: 14; -fx-text-fill: blue; -fx-font-weight: bold;");
        });
        loadDataHereLabel.setOnMouseExited(event -> {
            loadDataHereLabel.setStyle("-fx-font-size: 14; -fx-text-fill: blue; -fx-font-weight: bold;");
        });
        viewDBHereLabel.setOnMouseExited(event -> {
            viewDBHereLabel.setStyle("-fx-font-size: 14; -fx-text-fill: blue; -fx-font-weight: bold;");
        });

        //Added graphics for looks
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");

        setDBCredLabel1.setStyle("-fx-font-size: 14;");
        setDBCredLabel2.setStyle("-fx-font-size: 14;");
        setDBHereLabel.setStyle("-fx-font-size: 14; -fx-text-fill: blue; -fx-font-weight: bold;");

        makeDBLabel1.setStyle("-fx-font-size: 14;");
        makeDBLabel2.setStyle("-fx-font-size: 14;");
        makeDBHereLabel.setStyle("-fx-font-size: 14; -fx-text-fill: blue; -fx-font-weight: bold;");

        loadDataLabel1.setStyle("-fx-font-size: 14;");
        loadDataLabel2.setStyle("-fx-font-size: 14;");
        loadDataHereLabel.setStyle("-fx-font-size: 14; -fx-text-fill: blue; -fx-font-weight: bold;");

        viewDBLabel1.setStyle("-fx-font-size: 14;");
        viewDBLabel2.setStyle("-fx-font-size: 14;");
        viewDBHereLabel.setStyle("-fx-font-size: 14; -fx-text-fill: blue; -fx-font-weight: bold;");



        CustomLogger.createLogMsgAndSave("Home tab loaded");
    }
}
