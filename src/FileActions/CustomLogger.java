package FileActions;

import Tabs.LoggingTab;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Date;

/**
 * Created by r730819 on 6/16/2016.
 */
public class CustomLogger {
    private static Logger logger = (Logger) LogManager.getLogger();

    /**
     * Logs the date and msg to the console, file,
     * and the logging page.  This options gives color
     * options if needed
     * @param msg
     */
    public static void createLogMsgAndSave(String msg, String color){
        Date date = new Date();

        switch (color){
            case "red": {
                Label logLabel = new Label(date.toString() + ": " + msg);
                logLabel.setStyle("-fx-text-fill: darkred;");
                LoggingTab.loggingVbox.getChildren().add(logLabel);
                break;
            }
            case "blue": {
                Label logLabel = new Label(date.toString() + ": " + msg);
                logLabel.setStyle("-fx-text-fill: blue;");
                LoggingTab.loggingVbox.getChildren().add(logLabel);
                break;
            }
            case "yellow": {
                Label logLabel = new Label(date.toString() + ": " + msg);
                logLabel.setStyle("-fx-text-fill: yellow;");
                LoggingTab.loggingVbox.getChildren().add(logLabel);
                break;
            }
            default: {
                Label logLabel = new Label(date.toString() + ": " + msg);
                LoggingTab.loggingVbox.getChildren().add(logLabel);
                break;
            }
        }

        logger.info(date.toString() + ": " + msg);
    }

    /**
     * Logs but with no color so default is black
     * @param msg
     */
    public static void createLogMsgAndSave(String msg){
        Date date = new Date();

        LoggingTab.loggingVbox.getChildren().add(new Label(date.toString() + ": " + msg));

        logger.info(date.toString() + ": " + msg);
    }
}
