package sample.MainScreen;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Variables
        GridPane gridPane = new GridPane();
        Label header = new Label("Assignment 1");
        Button chooseFile = new Button("Choose File");
        Button parseFile = new Button("Parse File");
        TextField fileNameTextField = new TextField();

        //Set primary stage to a global to be reference
        Main.primaryStage = primaryStage;

        //Gridpane options/adding nodes
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(header,0,3);//col row
        gridPane.add(chooseFile,0,10);
        gridPane.add(fileNameTextField, 0, 11);
        gridPane.add(parseFile, 0, 14);

        //Textfield options
        fileNameTextField.setPromptText("Enter Valid File Path");

        //Create listeners
        MainScreenActionListeners.createTextFieldListener(fileNameTextField, parseFile);
        MainScreenActionListeners.createChooseFileListener(chooseFile, fileNameTextField, parseFile);
        MainScreenActionListeners.createParseFileBtnListner(parseFile, fileNameTextField);

        //Initially set the button to disabled till a file check is passed
        parseFile.setDisable(true);

        //Added graphics
        header.setStyle("-fx-font-size: 24;");

        //Add a tooltip to the submit button so the user knows what is does
        final Tooltip tooltip = new Tooltip();
        tooltip.setText("Parse file and add contents to Database");
        parseFile.setTooltip(tooltip);

        //Stage options passed from main
        primaryStage.setTitle("Assignment 1");
        primaryStage.setScene(new Scene(gridPane, 300, 275));

        primaryStage.show();

        //Fire the text field listener just to set intial values to red
        CheckIfFileExistsAndHandleColor.changeTextColorAndBtn(fileNameTextField, parseFile);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
