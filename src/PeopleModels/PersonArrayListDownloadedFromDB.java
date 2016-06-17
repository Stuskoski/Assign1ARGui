package PeopleModels;

import java.util.ArrayList;

/**
 * Created by r730819 on 6/15/2016.
 *
 * This ArrayList is created whenever the user
 * downloads the data from the DB to view.  This
 * list is used when sorting the data with texas
 * sort or when creating an email to send.
 */
public class PersonArrayListDownloadedFromDB {
    public static ArrayList<Person> downloadedArrayList = new ArrayList<>();
}
