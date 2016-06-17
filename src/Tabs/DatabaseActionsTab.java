package Tabs;

import DatabaseActions.DatabaseActionListeners;
import FileActions.CustomLogger;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by r730819 on 6/16/2016.
 *
 * The database actions tab is used to
 * do all the work when creating a database
 * or the table.
 */
public class DatabaseActionsTab {
    //static references to be used throughout program
    public static Tab databaseActionsTab = new Tab();
    public static Button makeDBBtn = new Button("Make DB");
    public static Button clearDBBtn = new Button("Clear DB");
    public static Button deleteDBBtn = new Button("Delete DB");


    public static void createDatabaseActionsTab(){
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        Label titleLabel = new Label("Database Actions");
        HBox titleHBox = new HBox(titleLabel);
        VBox holdBtnsVBox = new VBox(10, makeDBBtn, clearDBBtn, deleteDBBtn);
        Label warningLabel = new Label("* Ensure Database Credentials In Setting Are Correct *");
        HBox warningHBox = new HBox(warningLabel);

        //HBox options
        titleHBox.setAlignment(Pos.CENTER);
        warningHBox.setAlignment(Pos.CENTER);

        //BorderPane Options
        borderPane.setTop(titleHBox);
        borderPane.setCenter(holdBtnsVBox);
        borderPane.setBottom(warningHBox);

        //Button options
        holdBtnsVBox.setAlignment(Pos.CENTER);
        makeDBBtn.setPrefWidth(125);
        clearDBBtn.setPrefWidth(125);
        deleteDBBtn.setPrefWidth(125);

        //GridPane Options
        gridPane.setAlignment(Pos.CENTER_LEFT);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        //Tab Options
        databaseActionsTab.setText("Database Actions");
        databaseActionsTab.setClosable(false); //Unable to close tab
        databaseActionsTab.setContent(borderPane);

        //Create action listeners
        DatabaseActionListeners.createMakeDBBtnListener(makeDBBtn);
        DatabaseActionListeners.createClearDBBtnListener(clearDBBtn);
        DatabaseActionListeners.createDeleteDBBtnListener(deleteDBBtn);

        //Added graphics for looks
        titleLabel.setStyle("-fx-font-weight: bold");
        warningLabel.setStyle("-fx-text-fill: darkred");

        CustomLogger.createLogMsgAndSave("Database tab loaded");

    }
}
