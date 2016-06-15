package People;

/**
 * A person object that is created
 * in FileActions.ReadFile.  All details
 * must be present for object to be created.
 *
 * These person objects are stored in a
 * static array list contained in
 * People.PersonsArrayList which
 * is populated in FileActions.ReadFile.
 *
 * Created by r730819 on 6/15/2016.
 */
public class Person {
    public String lastName;
    public String firstName;
    public String emailAddr;
    public String homeAddr;
    public String city;
    public String state;
    public String zipCode;
    public String timeStamp;
    public int customerNum;
}
