package augustus.MainScreen;

import FileActions.CustomLogger;
import Tabs.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

/**
 * The main method of the program
 * to get everything started.  Sets
 * the dimensions for the program,
 * creates the tabs and adds them to
 * the tab pane.  Static reference
 * to the stage and TabPane. 
 */
public class Main extends Application {
    public static Stage primaryStage;
    public static TabPane tabPane;

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane borderPane = new BorderPane();
        tabPane = new TabPane();
        VBox topContainer = new VBox(tabPane);  //Creates a container to hold all Menu Objects.

        //Set the overall layout
        borderPane.setTop(topContainer);

        //Set primary stage to a global to be reference
        Main.primaryStage = primaryStage;

        //Create the logging tab and add to TabPane
        LoggingTab.createLoggingTab();
        CustomLogger.createLogMsgAndSave("Assign1ARGui started ... creating tabs");

        //Create the home tab and then add to the TabPane
        HomeTab.createHomeTab();
        tabPane.getTabs().add(HomeTab.homeTab);

        //Create the settings tab and add to TabPane
        SettingsTab.createSettingsTab();
        tabPane.getTabs().add(SettingsTab.settingsTab);

        //Create the database actions tab and add to TabPane
        DatabaseActionsTab.createDatabaseActionsTab();
        tabPane.getTabs().add(DatabaseActionsTab.databaseActionsTab);

        //Create the upload data tab and add to TabPane
        UploadDataTab.createUploadDataTab();
        tabPane.getTabs().add(UploadDataTab.uploadDataTab);

        //Create the read database tab and add to TabPane
        ViewDatabaseTab.createViewDatabaseTab();
        tabPane.getTabs().add(ViewDatabaseTab.viewDatabaseTab);

        //Add logging tab here for positioning
        tabPane.getTabs().add(LoggingTab.loggingTab);




        //remove temp files if closed
        primaryStage.setOnCloseRequest(event -> {
            File temp = new File("temp-augustus.sql");
            if(temp.exists()){
                temp.delete();//delete status ignored since it is done on close
            }
        });

        //Stage options passed from main
        primaryStage.setTitle("Assignment 1");
        primaryStage.setScene(new Scene(borderPane, 550, 415));//width height

        primaryStage.show();
}


    public static void main(String[] args) {
        launch(args);
    }
}
