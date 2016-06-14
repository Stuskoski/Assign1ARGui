package FileActions;

/**
 * Created by r730819 on 6/14/2016.
 *
 */
public class HandleLinesFromFile {

    /**
     *
     * @param line A string representing one line from the read customer file
     */
    public static void parseLines(String line){

        //Variables
        String strippedLines;
        String wordsSeperatedByCommas[];

        //Replace all occurrences of " inside the received line and put it into new variable
        strippedLines = line.replaceAll("\"", "");

        //Split the string into an array based off of commas
        wordsSeperatedByCommas = strippedLines.split(",");

        //Handle each word
        for(String str : wordsSeperatedByCommas){
            System.out.println(str);
        }
    }
}
