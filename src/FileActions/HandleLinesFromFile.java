package FileActions;

import People.Person;

import java.util.ArrayList;

/**
 * Created by r730819 on 6/14/2016.
 *
 */
public class HandleLinesFromFile {

    /**
     *
     * @param line A string representing one line from the read customer file
     */
    public static void parseLines(String line, ArrayList<Person> personArray){

        //Variables
        String strippedLines;
        String personDetails[];

        //Replace all occurrences of " inside the received line and put it into new variable
        strippedLines = line.replaceAll("\"", "");

        //Split the string into an array based off of commas
        personDetails = strippedLines.split(",");

        createUserAndAddToArray(personDetails, personArray);

    }

    /**
     * Create a new person and add the details.
     * After the user is create go ahead and
     * add the user to the ArrayList that will
     * be used later.
     *
     * @param personDetails An array contains the 7 details of
     *                      the person to be uploaded
     *
     * @param personArray An ArrayList reference that contains
     *                    multiple users.
     */
    public static void createUserAndAddToArray(String personDetails[], ArrayList<Person> personArray){
        if(personDetails.length != 7){
            return;
            //todo write log
        }

        Person person = new Person();

        //The order matters here according to the text file specs
        person.lastName = personDetails[0];
        person.firstName = personDetails[1];
        person.emailAddr = personDetails[2];
        person.homeAddr = personDetails[3];
        person.city = personDetails[4];
        person.state = personDetails[5];
        person.zipCode = personDetails[6];

        personArray.add(person);
    }
}
