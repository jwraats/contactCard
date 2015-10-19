package nl.jackevers.jwraats.contactcard;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwraats on 19/10/15.
 */
public class Person {
    public Bitmap profileImage;
    public Boolean isMale;
    public String email, firstName, lastName, imageURL;

    public static ArrayList<Person> ITEMS = new ArrayList<Person>();

    public Person(String email, Boolean isMale, String firstName, String lastName, String imageURL){
        this.email = email;
        this.isMale = isMale;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageURL = imageURL;

    }
}
