package Models.Listings;

/* The WantedListing model class extends Listing and is used for the storage and parsing of any details for an wanted
listing. */
public class WantedListing extends Listing {

    // Constructor for use when creating a new wanted listing and the id hasn't been created yet.
    public WantedListing(String name, String description, String expiryDate, String category, int submitterId) {
        this.name = name;
        this.description = description;
        this.expiryDate = expiryDate;
        this.category = category;
        this.listingTypeId = 1;
        this.submitterId = submitterId;
    }

    // Constructor for use when fetching user data from the database and the id is known.
    public WantedListing(int id, String name, String description, String expiryDate, String category, int submitterId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.expiryDate = expiryDate;
        this.category = category;
        this.listingTypeId = 1;
        this.submitterId = submitterId;
    }
}
