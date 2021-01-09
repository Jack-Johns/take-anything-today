package Controllers;

import Models.Listings.OfferedListing;
import Models.Listings.WantedListing;
import Models.Login;
import Models.Question;
import Models.Response;
import Models.User;

import java.sql.ResultSet;

public class DatabaseController {

    // SQL UPDATE, INSERT and DELETE statements
    public static Boolean createUser(User user){
        String query = "Use TakeAnythingToday\n" +
                "Insert into [User](First_Name, Last_Name, Address_Line_1, Address_Line_2, City, Postcode)\n" +
                "Values ('"+ user.getFirstName() +"', '"+ user.getLastName() +"', '"+ user.getAddress().getAddressLine1() +"', '"+ user.getAddress().getAddressLine2() +"', '"+ user.getAddress().getCity() +"', '"+ user.getAddress().getPostcode() +"')\n" +
                "Insert into [Login]([User_ID], Email, [Password])\n" +
                "Values (SCOPE_IDENTITY(), '"+ user.getLogin().getEmail() +"', '"+ user.getLogin().getPassword() +"');";
        return DatabaseConnection.executeUpdate(query);
    }

    public static Boolean updateUser(User user){
        String query = "Use TakeAnythingToday\n" +
                "Update [User] Set First_Name = '"+ user.getFirstName() +"', Last_Name = '"+ user.getLastName() +"', Address_Line_1 = '"+ user.getAddress().getAddressLine1() +"', Address_Line_2 = '"+ user.getAddress().getAddressLine2() +"', City = '"+ user.getAddress().getCity() +"', Postcode = '"+ user.getAddress().getPostcode() +"'\n" +
                "Where ID = "+ user.getId()+";\n" +
                "Update [Login] Set Email = '"+ user.getLogin().getEmail() +"', [Password] = '"+ user.getLogin().getPassword() +"'\n" +
                "Where User_ID = "+ user.getId()+";";
        return DatabaseConnection.executeUpdate(query);
    }

    public static Boolean createWantedListing(WantedListing listing){
        String query = "Use TakeAnythingToday\n" +
                "Insert into Listing([User_ID], Category_ID, Listing_Type_ID, [Name], [Description], [Expiry_Date])\n" +
                "Values ("+ listing.getSubmitterId() +", "+ listing.getCategoryId(listing.getCategory()) +", "+ listing.getListingTypeId() +", '"+ listing.getName() +"', '"+ listing.getDescription() +"', '"+ listing.getExpiryDate() +"');";
        return DatabaseConnection.executeUpdate(query);
    }

    public static Boolean createOfferedListing(OfferedListing listing){
        String query = "Use TakeAnythingToday\n" +
                "Insert into Listing([User_ID], Category_ID, Condition_ID, Listing_Type_ID, [Name], [Description], [Expiry_Date])\n" +
                "Values ("+ listing.getSubmitterId() +", "+ listing.getCategoryId(listing.getCategory()) +", "+ listing.getConditionId(listing.getCondition()) +", "+ listing.getListingTypeId() +", '"+ listing.getName() +"', '"+ listing.getDescription() +"', '"+ listing.getExpiryDate() +"');";
        return DatabaseConnection.executeUpdate(query);
    }

    public static Boolean createQuestion(Question question){
        String query = "Use TakeAnythingToday\n" +
                "Insert into Question([User_ID], Listing_ID, Question)\n" +
                "Values ("+ question.getSubmitterId() +", "+ question.getListingId() +", '"+ question.getQuestion() +"')";
        return DatabaseConnection.executeUpdate(query);
    }

    public static Boolean createResponse(Response response){
        String query = "Use TakeAnythingToday\n" +
                "Insert into Response(Question_ID, Response)\n" +
                "Values ("+ response.getQuestionId() +", '"+ response.getResponse() +"')";
        return DatabaseConnection.executeUpdate(query);
    }

    public static Boolean updateWantedListing(WantedListing listing){
        String query = "Use TakeAnythingToday\n" +
                "Update Listing Set Category_ID = "+ listing.getCategoryId(listing.getCategory()) +", [Name] = '"+ listing.getName() +"', [Description] = '"+ listing.getDescription() +"', [Expiry_Date] = '"+ listing.getExpiryDate() +"'\n" +
                "Where ID = "+ listing.getId() +";";
        return DatabaseConnection.executeUpdate(query);
    }

    public static Boolean updateOfferedListing(OfferedListing listing){
        String query = "Use TakeAnythingToday\n" +
                "Update Listing Set Category_ID = "+ listing.getCategoryId(listing.getCategory()) +", Condition_ID = "+ listing.getConditionId(listing.getCondition()) +", [Name] = '"+ listing.getName() +"', [Description] = '"+ listing.getDescription() +"', [Expiry_Date] = '"+ listing.getExpiryDate() +"'\n" +
                "Where ID = "+ listing.getId() +";";
        return DatabaseConnection.executeUpdate(query);
    }

    public static Boolean deleteListing(int id){
        String query = "Use TakeAnythingToday\n" +
                "Delete From Response Where Question_ID = \n" +
                "\t(Select Question.ID From Question Inner join Listing on Question.Listing_ID = Listing.ID\n" +
                "\tWhere Listing.ID = "+ id +")\n" +
                "Delete From Question Where Listing_ID = "+ id +"\n" +
                "Delete From Listing Where ID = "+ id +";";
        return DatabaseConnection.executeUpdate(query);
    }

    public static Boolean deleteAccount(int id){
        String query = "Use TakeAnythingToday\n" +
                "Delete From Response Where Question_ID = \n" +
                "\t(Select Question.ID From Question Inner join Listing on Question.Listing_ID = Listing.ID\n" +
                "\tWhere Listing.[User_ID] = "+ id +")\n" +
                "Delete From Question Where ID = \n" +
                "\t(Select Question.ID From Question Inner join Listing on Question.Listing_ID = Listing.ID\n" +
                "\tWhere Listing.[User_ID] = "+ id +")\n" +
                "Delete From Listing Where [User_ID] = "+ id +"\n" +
                "Delete From [Login] Where [User_ID] = "+ id +"\n" +
                "Delete From [User] Where ID = "+ id +";";
        return DatabaseConnection.executeUpdate(query);
    }

    //SQL SELECT queries -----------------------------------------------------------------------------------------------

    public static ResultSet verifyLogin(Login login){
        String query = "Use TakeAnythingToday\n" +
                "Select [User].ID, [User].First_Name, [User].Last_Name, [User].Address_Line_1, [User].Address_Line_2, [User].City, [User].Postcode, [Login].Email, [Login].[Password]\n" +
                "From [User] inner join [Login] On [User].ID = [Login].[User_ID]\n" +
                "Where [Login].Email = '"+ login.getEmail() +"' And [Login].[Password] = '"+ login.getPassword() +"'";
        return DatabaseConnection.executeQuery(query);
    }

    public static ResultSet getAccount(int id){
        String query = "Use TakeAnythingToday\n" +
                "Select [User].First_Name, [User].Last_Name, [User].Address_Line_1, [User].Address_Line_2, [User].City, [User].Postcode, [Login].Email\n" +
                "From [User] Inner join [Login] on [User].ID = [Login].[User_ID]\n" +
                "Where [User].ID = "+ id +";";
        return DatabaseConnection.executeQuery(query);
    }

    public static ResultSet getAllListings(){
        String query = "Use TakeAnythingToday\n" +
                "Select Listing.ID, Listing.[User_ID], Category.Category, Condition.Condition, Listing_Type.[Type], Listing.[Name], Listing.[Description], Listing.[Expiry_Date]\n" +
                "From Listing inner join Category On Listing.Category_ID = Category.ID\n" +
                "inner join Condition on Listing.Condition_ID = Condition.ID\n" +
                "inner join Listing_Type On Listing.Listing_Type_ID = Listing_Type.ID\n" +
                "Where Listing.[Expiry_Date] > Getdate()";
        return DatabaseConnection.executeQuery(query);
    }

    public static ResultSet getUsersListings(int id){
        String query = "Use TakeAnythingToday\n" +
                "Select Listing.ID, Listing.[User_ID], Category.Category, Condition.Condition, Listing_Type.[Type], Listing.[Name], Listing.[Description], Listing.[Expiry_Date]\n" +
                "From Listing inner join Category On Listing.Category_ID = Category.ID\n" +
                "inner join Condition on Listing.Condition_ID = Condition.ID\n" +
                "inner join Listing_Type On Listing.Listing_Type_ID = Listing_Type.ID\n" +
                "And Listing.[User_ID] = "+ id +";";
        return DatabaseConnection.executeQuery(query);
    }

    public static ResultSet getListing(String id){
        String query = "Use TakeAnythingToday\n" +
                "Select Listing.ID, Listing.[User_ID], Category.Category, Condition.Condition, Listing_Type.[Type], Listing.[Name], Listing.[Description], Listing.[Expiry_Date]\n" +
                "From Listing inner join Category On Listing.Category_ID = Category.ID\n" +
                "inner join Condition on Listing.Condition_ID = Condition.ID\n" +
                "inner join Listing_Type On Listing.Listing_Type_ID = Listing_Type.ID\n" +
                "Where Listing.ID = "+ id +";";
        return DatabaseConnection.executeQuery(query);
    }

    public static ResultSet getQuestions(String id){
        String query = "Use TakeAnythingToday\n" +
                "Select Question.ID, Question.[User_ID], Question.Question\n" +
                "From Question\n" +
                "Where Question.Listing_ID = "+ id +";";
        return DatabaseConnection.executeQuery(query);
    }

    public static ResultSet getResponses(String id){
        String query = "Use TakeAnythingToday\n" +
                "Select Response.Question_ID, Response.Response\n" +
                "From Response Inner join Question on Response.Question_ID = Question.ID\n" +
                "Where Question.Listing_ID = "+ id +";";
        return DatabaseConnection.executeQuery(query);
    }
}
