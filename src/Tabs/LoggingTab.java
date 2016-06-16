package Tabs;

import FileActions.CustomLogger;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by r730819 on 6/16/2016.
 */
public class LoggingTab {
    public static Tab loggingTab = new Tab();
    public static VBox loggingVbox = new VBox(3);

    public static void createLoggingTab(){
        BorderPane borderPane = new BorderPane();
        Label titleLabel = new Label("Logging Page");
        HBox titleHbox = new HBox(titleLabel);
        ScrollPane scrollPane = new ScrollPane();

        //titleHbox options
        titleHbox.setAlignment(Pos.CENTER);

        //BorderPane Options
        borderPane.setTop(titleHbox);
        borderPane.setCenter(scrollPane);

        //ScrollPane Options
        scrollPane.setContent(loggingVbox);
        scrollPane.setMaxHeight(355);

        //Logging vbox options
        loggingVbox.setAlignment(Pos.TOP_LEFT);

        //Tab Options
        loggingTab.setText("Logging");
        loggingTab.setClosable(false); //Unable to close tab
        loggingTab.setContent(borderPane);

        //Added graphics for looks
        titleLabel.setStyle("-fx-font-weight: bold");

       // CustomLogger.createLogMsgAndSave("Logging tab loaded");

    }
}
