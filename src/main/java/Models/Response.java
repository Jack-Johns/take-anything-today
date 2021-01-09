package Models;

/* The Response class is used for the storage and parsing of all data stored about a response to a question on a
    listing. */
public class Response {
    // Attributes are all private and only accessible through getters due to the rules of encapsulation.
    /* Submitter doesn't need to be stored because you can get it from the listing that the associated question is on,
     only the original listing submitter can answer questions. */
    private int questionId;
    private String response;

    // Constructor for use when creating a new response and the id hasn't been created yet
    public Response(int questionId, String response){
        this.questionId = questionId;
        this.response = response;
    }

    //Getters for each attribute. This is the only way to access the attributes from outside of the class
    public int getQuestionId() {
        return questionId;
    }

    public String getResponse() {
        return response;
    }
}
