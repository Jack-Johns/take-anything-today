package Models.Listings;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/* The Listing model class is an abstract class that is extended by a wanted and offered listing, all attributes and
methods are inherited. This class cannot be instantiated itself. */
public abstract class Listing {
    /* Attributes are all protected so they can be inherited and used within the module freely. They are all common
    between WantedListing and OfferedListing. */
    protected int id;
    protected int submitterId;
    protected String name;
    protected String description;
    protected String expiryDate;
    protected String category;
    protected int listingTypeId;

    // Getters for each attribute. This is the only way to access the attributes from outside of the class.
    public int getId()
    {
        return id;
    }

    public int getSubmitterId() {
        return submitterId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCategory() {
        return category;
    }

    public int getListingTypeId() {
        return listingTypeId;
    }

    // Static method that returns the correct category id when provided with a category. Used in SQL queries.
    public static int getCategoryId(String category){
        Map<String, Integer> categoryMapping = new HashMap<String, Integer>() {{
            put("Household", 1);
            put("Hobbies", 2);
            put("Toys", 3);
            put("Electronics", 4);
            put("Garden", 5);
            put("Collectable", 13);
            put("Other", 14);
        }};
        return categoryMapping.get(category);
    }

    public static String getListingType(int listingTypeId){
        Map<Integer, String> mapping = new HashMap<Integer, String>() {{
            put(1, "Wanted");
            put(2, "Offered");
        }};
        return mapping.get(listingTypeId);
    }

    /* Static method used in the MyListings tab that converts the string date, converts it to LocalDate and checks
    whether it is expired. */
    public static Boolean isExpired(String expiryDate){
        LocalDate expiry = LocalDate.of(Integer.parseInt(expiryDate.substring(0,3)),Integer.parseInt(expiryDate.substring(4,5)), Integer.parseInt(expiryDate.substring(6)));
        return expiry.compareTo(LocalDate.now()) < 0;
    }

}
