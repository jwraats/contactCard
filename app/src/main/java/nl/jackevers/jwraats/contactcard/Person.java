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

    public Person(String email, Boolean isMale, String firstName, String lastName, String imageURL) {
        this.email = email;
        this.isMale = isMale;
        this.firstName = makeStringCapitalized(firstName);
        this.lastName = makeStringCapitalized(lastName);
        this.imageURL = imageURL;

    }

    private String makeStringCapitalized(String input) {
        return Character.toUpperCase(input.charAt(0)) + input.substring(1);
    }
}
