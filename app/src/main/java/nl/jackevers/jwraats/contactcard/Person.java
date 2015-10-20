package nl.jackevers.jwraats.contactcard;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwraats on 19/10/15.
 */
public class Person {
    public Boolean isMale;
    public String email, firstName, lastName, imageURL, thumbnailURL;

    public Person(String email, Boolean isMale, String firstName, String lastName, String imageURL, String thumbnailURL) {
        this.email = email;
        this.isMale = isMale;
        this.firstName = makeStringCapitalized(firstName);
        this.lastName = makeStringCapitalized(lastName);
        this.imageURL = imageURL;
        this.thumbnailURL = thumbnailURL;


    }

    private String makeStringCapitalized(String input) {
        return Character.toUpperCase(input.charAt(0)) + input.substring(1);
    }
}
