package Models.Listings;

import java.util.HashMap;
import java.util.Map;

/* The OfferedListing model class extends Listing and is used for the storage and parsing of any details for an offered
listing. */
public class OfferedListing extends Listing {
    // The condition attribute is exclusive to OfferedListing, hence it is private.
    private String condition;

    // Constructor for use when creating a new offered listing and the id hasn't been created yet.
    public OfferedListing(String name, String description, String expiryDate, String category, String condition, int submitterId){
        this.name = name;
        this.description = description;
        this.expiryDate = expiryDate;
        this.category = category;
        this.condition = condition;
        this.listingTypeId = 2;
        this.submitterId = submitterId;
    }

    // Constructor for use when fetching user data from the database and the id is known.
    public OfferedListing(int id, String name, String description, String expiryDate, String category, String condition, int submitterId){
        this.id = id;
        this.name = name;
        this.description = description;
        this.expiryDate = expiryDate;
        this.category = category;
        this.condition = condition;
        this.listingTypeId = 2;
        this.submitterId = submitterId;
    }

    // Public getter for the private attribute adheres to the rules of encapsulation.
    public String getCondition() {
        return condition;
    }

    // Static method that returns the correct condition id when provided with a condition. Used in SQL queries.
    public static int getConditionId(String condition){
        Map<String, Integer> conditionMapping = new HashMap<String, Integer>() {{
            put("Unused", 1);
            put("Used", 2);
            put("Damaged", 3);
            put("Scrap", 4);
        }};
        return conditionMapping.get(condition);
    }
}
