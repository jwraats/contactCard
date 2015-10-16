package apps.jacks.contactcard;

/**
 * Created by jwraats on 16/10/15.
 */
public class Person {
    private String email; //Is our amazing alternative ID
    private boolean isMale;
    private String firstName;
    private String lastName;
    private String imageURL;

    public Person(String email, boolean isMale, String firstName, String lastName, String imageURL){
        this.email = email;
        this.isMale = isMale;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageURL = imageURL;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setIsMale(boolean isMale){
        this.isMale = isMale;
    }

    public boolean getIsMale(){
        return this.isMale;
    }

    public void setFirstName(String firstName){
        this.firstName = makeStringCapitalized(firstName);
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setLastName(String lastName){
        this.lastName = makeStringCapitalized(lastName);
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

    @Override
    public String toString() {
        return this.getFirstName()+" "+this.getLastName();
    }

    private String makeStringCapitalized(String input){
        return Character.toUpperCase(input.charAt(0)) + input.substring(1);
    }

}
