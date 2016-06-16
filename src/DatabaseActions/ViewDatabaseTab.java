package DatabaseActions;

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
    public static Button refreshBtn = new Button("Refresh");
    public static Button sortTxBtn = new Button("Texas Sort");

    public static void createViewDatabaseTab(){
        //Many many UI items, each one sorta builds on another
        GridPane gridPane = new GridPane();
        BorderPane borderPane = new BorderPane();
        VBox buttonVbox = new VBox(25, refreshBtn, sortTxBtn, emailBtn, emailLabel);
        ScrollPane scrollPane = new ScrollPane();
        HBox midContHbox = new HBox(25, buttonVbox, scrollPane); //25 is spacing
        Label title = new Label("View Database Entries");
        HBox titleHbox = new HBox(title);

        //titleHbox options
        titleHbox.setAlignment(Pos.CENTER);


        //buttonVbox options
        buttonVbox.setAlignment(Pos.CENTER);
        refreshBtn.setPrefWidth(75);
        sortTxBtn.setPrefWidth(75);
        emailBtn.setPrefWidth(75);
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
        scrollPane.setPrefHeight(250);
        scrollPane.setPrefWidth(150);

        //Create action listeners
        DatabaseActionListeners.createRefreshBtnListenerViewDBTab(refreshBtn, scrollPane);
        DatabaseActionListeners.createsortTxBtnListenerViewDBTab(sortTxBtn, scrollPane);
        DatabaseActionListeners.createEmailBtnListenerViewDBTab(emailBtn, scrollPane);


        //Tab Options
        viewDatabaseTab.setText("View Database");
        viewDatabaseTab.setClosable(false); //Unable to close tab
        viewDatabaseTab.setContent(borderPane);

        //Added graphics
        title.setStyle("-fx-font-weight: bold");
        emailLabel.setStyle("-fx-text-fill: darkred;");

    }
}
