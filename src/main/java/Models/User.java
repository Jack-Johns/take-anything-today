package Models;

/* The User model class is used for the storage and transfer of all details corresponding to the account of a user. */
public class User {
    //Attributes are all private and only accessible through getters due to the rules of encapsulation.
    // Address and Login are both other model classes.
    private int id;
    private String firstName;
    private String lastName;
    private Address address;
    private Login login;

    // Constructor for use when creating a new user and the id hasn't been created yet.
    public User(String firstName, String lastName, Address address, Login login){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.login = login;
    }

    // Constructor for use when fetching user data from the database and the id is known.
    public User(int id, String firstName, String lastName, Address address, Login login){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.login = login;
    }

    // Constructor for use when viewing another user's profile and the login should not be accessed.
    public User(int id, String firstName, String lastName, Address address){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.login = null;
    }

    public User(){
        this.id = 0;
    }

    // Getters for each attribute. This is the only way to access the attributes from outside of the class.
    public int getId()
    {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public Login getLogin() {
        return login;
    }

}
