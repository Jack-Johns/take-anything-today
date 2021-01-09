package Models;

/* The Login model class is used for the storage and parsing of the login details of a user, they have been separated
from the User class as this allows the user details to be fetched without these sensitive details, improving security */
public class Login {
    //Attributes are all private and only accessible through getters due to the rules of encapsulation.
    private String email;
    private String password;

    // Constructor for use when instantiating a new object of type Login, usually within the instatiation of a User.
    public Login(String email, String password){
        this.email = email;
        this.password = password;
    }

    //Getters for each attribute. This is the only way to access the attributes from outside of the class
    // Usually the password wouldn't be accessible through a public getter, this would be changed in the next sprint.
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
