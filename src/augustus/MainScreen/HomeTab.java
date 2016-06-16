package augustus.MainScreen;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Created by r730819 on 6/15/2016.
 */
public class HomeTab {
    public static Tab homeTab = new Tab();

    public static void createHomeTab(){
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
        homeTab.setText("Home");
        homeTab.setClosable(false); //Unable to close tab
        homeTab.setContent(borderPane);

        //Added graphics for looks
        titleLabel.setStyle("-fx-font-weight: bold");
    }
}
