package FileActions;

import java.io.*;

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
    public static void readAndHandle(File inFile){
        String line;

        try {
            // Create fileReader to read the inFile
            FileReader fileReader = new FileReader(inFile);

            //Wrap file reader with a bufferedReader to improve efficiency
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //Read line by line from the file and process each line
            while((line = bufferedReader.readLine()) != null) {
                HandleLinesFromFile.parseLines(line);
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(inFile.toString() + " was not found");
        }
        catch(IOException ex) {
            System.out.println("Unable to read " + inFile.toString());
        }
    }
}
