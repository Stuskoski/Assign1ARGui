package PeopleModels;

import java.util.ArrayList;

/**
 * Created by r730819 on 6/15/2016.
 *
 * An ArrayList originally created after
 * the user imports a file.  A person
 * object is created for each line in the
 * file and added to this array list.
 *
 * When uploading the data to the DB, a loop
 * through this array is used.
 */
public class PersonsArrayList {
    public static ArrayList<Person> personsArray = new ArrayList<>();
}
