package Tabs;

import FileActions.CustomLogger;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Created by r730819 on 6/16/2016.
 */
public class DatabaseActionsTab {
    public static Tab databaseActionsTab = new Tab();

    public static void createDatabaseActionsTab(){
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        Label titleLabel = new Label("Assignment 1 Landing Page");
        HBox titleHbox = new HBox(titleLabel);

        //titleHbox options
        titleHbox.setAlignment(Pos.CENTER);

        //BorderPane Options
        borderPane.setTop(titleHbox);
        borderPane.setCenter(gridPane);

        //GridPane Options
        gridPane.setAlignment(Pos.CENTER_LEFT);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        //Tab Options
        databaseActionsTab.setText("Database Actions");
        databaseActionsTab.setClosable(false); //Unable to close tab
        databaseActionsTab.setContent(borderPane);

        //Added graphics for looks
        titleLabel.setStyle("-fx-font-weight: bold");

        //CustomLogger.createLogMsgAndSave("Database tab loaded");

    }
}
