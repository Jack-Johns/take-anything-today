package Models;

/* The Question model class is used for the storage and parsing of all details corresponding to a question on a listing */
public class Question {
    //Attributes are all private and only accessible through getters due to the rules of encapsulation.
    private int id;
    private int submitterId;
    private int listingId;
    private String question;

    // Constructor for use when creating a new question and the id hasn't been created by the database yet.
    public Question(int submitterId, int listingId, String question){
        this.submitterId = submitterId;
        this.listingId = listingId;
        this.question = question;
    }

    // Constructor for use when fetching a question from the database and the id is known.
    public Question(int id, int submitterId, int listingId, String question){
        this.id = id;
        this.submitterId = submitterId;
        this.listingId = listingId;
        this.question = question;
    }

    //Getters for each attribute. This is the only way to access the attributes from outside of the class
    public int getId(){ return id; }

    public int getSubmitterId() {
        return submitterId;
    }

    public int getListingId() {
        return listingId;
    }

    public String getQuestion() {
        return question;
    }
}
