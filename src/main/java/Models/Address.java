package Models;

/* The Address model class is used for the storage and parsing of the user details corresponding to their address.
This has been separated from User as logically it is a separate entity. */
public class Address {
    //Attributes are all private and only accessible through getters due to the rules of encapsulation.
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postcode;

    // Constructor for use when instantiating an Address object and an addressLine2 is present.
    public Address(String addressLine1, String addressLine2, String city, String postcode){
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.postcode = postcode;
    }

    // Since addressLine2 is nullable, this constructor is used when it isn't needed.
    public Address(String addressLine1, String city, String postcode){
        this.addressLine1 = addressLine1;
        this.addressLine2 = "";
        this.city = city;
        this.postcode = postcode;
    }

    //Getters for each attribute. This is the only way to access the attributes from outside of the class
    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2(){
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }
}
