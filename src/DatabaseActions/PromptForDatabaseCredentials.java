package DatabaseActions;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by r730819 on 6/14/2016.
 */
public class PromptForDatabaseCredentials {
    public static void createScreen(){
        Stage dbCredsStage = new Stage();
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 300, 300);
        Label urlLabel = new Label("URL:");
        Label userLabel = new Label("url:");
        Label passLabel = new Label("user:");
        TextField urlTextField = new TextField();
        TextField userTextField = new TextField();
        TextField passTextField = new TextField();

        //Gridpane options
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        //Stage options
        dbCredsStage.setTitle("Confirm DB Credentials");

        //Prefill the credentials with the values from SQL Script

        // String url1 = "jdbc:mysql://localhost:3306/assign1_db_augustus";
        //String user = "augustus";
        //String user = "mypass123";



        dbCredsStage.setScene(scene);
    }
}
