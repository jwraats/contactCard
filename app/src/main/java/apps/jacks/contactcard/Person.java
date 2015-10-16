package apps.jacks.contactcard;

/**
 * Created by jwraats on 16/10/15.
 */
public class Person {
    private boolean isMale;
    private string firstName;
    private string lastName;
    private string imageURL;

    public Person(boolean isMale, string firstName, string lastName, string imageURL){
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

    public void setFirstName(string firstName){
        this.firstName = firstName;
    }

    public string getFirstName(){
        return this.firstName;
    }

    public void setLastName(string lastName){
        this.lastName = lastName;
    }

    public string getLastName(){
        return this.lastName;
    }

    public void setImageURL(string imageURL){
        this.imageURL = imageURL;
    }

    public string getImageURL(){
        return this.imageURL;
    }

}
