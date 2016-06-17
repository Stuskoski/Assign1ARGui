package FileActions;

import PeopleModels.Person;
import PeopleModels.PersonsArrayList;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by r730819 on 6/14/2016.
 * Handles the file passed from main
 * after main checks if the file exists
 * and is good to go
 */
public class ReadFile {
    /**
     * @param inFile File passed from main to be read and handled
     */
    public static void readAndCreateObjects(File inFile){

        CustomLogger.createLogMsgAndSave("Reading and handling file: " + inFile.toString());

        String line;
        ArrayList<Person> personArray = new ArrayList<>();

        try {
            // Create fileReader to read the inFile
            FileReader fileReader = new FileReader(inFile);

            //Wrap file reader with a bufferedReader to improve efficiency
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //Read line by line from the file and process each line
            while((line = bufferedReader.readLine()) != null) {
                HandleLinesFromFile.parseLines(line, personArray);
            }

            // Always close files.
            bufferedReader.close();


        } catch(IOException ex) {
            CustomLogger.createLogMsgAndSave("Error: Unable to read file " + inFile.toString());
        }

        PersonsArrayList.personsArray = new ArrayList<>(personArray);

    }
}
