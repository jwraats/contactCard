package apps.jacks.contactcard;

/**
 * Created by jwraats on 16/10/15.
 */
public class Person {
    private boolean isMale;
    private String firstName;
    private String lastName;
    private String imageURL;

    public Person(boolean isMale, String firstName, String lastName, String imageURL){
        this.isMale = isMale;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageURL = imageURL;
    }

    public void setIsMale(boolean isMale){
        this.isMale = isMale;
    }

    public boolean getIsMale(){
        return this.isMale;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }

    public String getImageURL(){
        return this.imageURL;
    }

}
