package augustus.MainScreen;

import DatabaseActions.UploadDataTab;
import DatabaseActions.ViewDatabaseTab;
import Settings.SettingsTab;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primaryStage;
    private static Tab homeTab;
    private static Tab importDataTab;
    private static Tab sendEmailTab;
    private static Tab viewDBTab;

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();
        VBox topContainer = new VBox(tabPane);  //Creates a container to hold all Menu Objects.

        //Set the overall layout
        borderPane.setTop(topContainer);

        //Set primary stage to a global to be reference
        Main.primaryStage = primaryStage;

        //Create the home tab and then add to the TabPane
        HomeTab.createHomeTab();
        tabPane.getTabs().add(HomeTab.homeTab);

        //Create the upload data tab and add to TabPane
        UploadDataTab.createUploadDataTab();
        tabPane.getTabs().add(UploadDataTab.uploadDataTab);

        //Create the read database tab and add to TabPane
        ViewDatabaseTab.createViewDatabaseTab();
        tabPane.getTabs().add(ViewDatabaseTab.viewDatabaseTab);

        //Create the settings tab and add to TabPane
        SettingsTab.createSettingsTab();
        tabPane.getTabs().add(SettingsTab.settingsTab);


        //Create the sql tab and add to TabPane

        //Stage options passed from main
        primaryStage.setTitle("Assignment 1");
        primaryStage.setScene(new Scene(borderPane, 475, 415));//width height

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
