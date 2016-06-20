package FileActions;

import Tabs.LoggingTab;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Date;

/**
 * Created by r730819 on 6/16/2016.
 *
 * Logger class used to write to an
 * external log, an internal log, and
 * the console.
 */
public class CustomLogger {
    private static Logger logger = (Logger) LogManager.getLogger();

    /**
     * Logs the date and msg to the console, file,
     * and the logging page.  This options gives color
     * options if needed
     * @param msg msg to be logged
     */
    public static void createLogMsgAndSave(String msg, String color){
        Date date = new Date();

        switch (color){
            case "red": {
                Label logLabel = new Label(date.toString() + ": " + msg);
                logLabel.setStyle("-fx-text-fill: darkred;");
                LoggingTab.loggingVbox.getChildren().add(logLabel);
                logger.info(date.toString() + ": " + msg);
                logger.error(date.toString() + ": " + msg);
                break;
            }
            case "green": {
                Label logLabel = new Label(date.toString() + ": " + msg);
                logLabel.setStyle("-fx-text-fill: green;");
                LoggingTab.loggingVbox.getChildren().add(logLabel);
                logger.info(date.toString() + ": " + msg);
                break;
            }
            default: {
                Label logLabel = new Label(date.toString() + ": " + msg);
                LoggingTab.loggingVbox.getChildren().add(logLabel);
                logger.info(date.toString() + ": " + msg);
                break;
            }
        }
    }

    /**
     * Logs but with no color so default is black
     * @param msg msg to be logged
     */
    public static void createLogMsgAndSave(String msg){
        Date date = new Date();

        LoggingTab.loggingVbox.getChildren().add(new Label(date.toString() + ": " + msg));

        logger.info(date.toString() + ": " + msg);
    }
}
