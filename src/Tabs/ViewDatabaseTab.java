package Tabs;

import DatabaseActions.DatabaseActionListeners;
import FileActions.CustomLogger;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by r730819 on 6/15/2016.
 */
public class ViewDatabaseTab {
    public static Tab viewDatabaseTab = new Tab();
    public static Button emailBtn = new Button("Email");
    public static Label emailLabel = new Label("Load data into table to unlock email");
    public static Button refreshBtn = new Button("Load Data");
    public static Button sortTxBtn = new Button("Texas Sort");

    public static void createViewDatabaseTab(){
        //Many many UI items, each one sorta builds on another
        GridPane gridPane = new GridPane();
        Label title = new Label("View Database Entries");
        Label customerTitleLabel = new Label("Database Customers");
        HBox customerTitleHbox = new HBox(customerTitleLabel);
        BorderPane borderPane = new BorderPane();
        ScrollPane scrollPane = new ScrollPane();
        VBox buttonVbox = new VBox(25, refreshBtn, sortTxBtn, emailBtn, emailLabel);
        VBox scrollVbox = new VBox(5, customerTitleHbox, scrollPane);
        HBox midContHbox = new HBox(25, buttonVbox, scrollVbox); //25 is spacing
        HBox titleHbox = new HBox(title);

        //titleHbox options
        titleHbox.setAlignment(Pos.CENTER);
        customerTitleHbox.setAlignment(Pos.CENTER);


        //buttonVbox options
        buttonVbox.setAlignment(Pos.CENTER);
        refreshBtn.setPrefWidth(150);
        sortTxBtn.setPrefWidth(150);
        emailBtn.setPrefWidth(150);
        emailBtn.setDisable(true);

        //GridPane Options
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(midContHbox, 0, 5);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        //BorderPane Options
        borderPane.setTop(titleHbox);
        borderPane.setCenter(gridPane);

        //Scroll Pane Options
        scrollPane.setPrefHeight(275);
        scrollPane.setPrefWidth(210);
        scrollPane.setMaxHeight(275);

        //Create action listeners
        DatabaseActionListeners.createRefreshBtnListenerViewDBTab(refreshBtn, scrollPane, emailBtn);
        DatabaseActionListeners.createSortTxBtnListenerViewDBTab(sortTxBtn, scrollPane, emailBtn);
        DatabaseActionListeners.createEmailBtnListenerViewDBTab(emailBtn);


        //Tab Options
        viewDatabaseTab.setText("View Database");
        viewDatabaseTab.setClosable(false); //Unable to close tab
        viewDatabaseTab.setContent(borderPane);

        //Added graphics
        title.setStyle("-fx-font-weight: bold");
        emailLabel.setStyle("-fx-text-fill: darkred;");
        customerTitleLabel.setStyle("-fx-font-weight: bold");

       // CustomLogger.createLogMsgAndSave("View database tab loaded");

    }
}
