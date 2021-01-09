package Controllers;

import Models.Listings.Listing;
import Models.Listings.OfferedListing;
import Models.Listings.WantedListing;
import Models.Question;
import Models.Response;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListingViewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Called when a Post request is sent to /listing_view
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        response.setContentType("text/html");
        String listingId = request.getParameter("listingId");

        // Get the listing with the corresponding ID, all of its associated questions and their answers.
        ResultSet listingData;
        Listing listing = null;
        try{
            listingData = DatabaseController.getListing(listingId);
            while(listingData.next()){
                if(listingData.getString("Type").equals("Wanted")){
                    listing = new WantedListing(listingData.getInt("ID"), listingData.getString("Name"), listingData.getString("Description"), listingData.getString("Expiry_Date"), listingData.getString("Category"), listingData.getInt("User_ID"));
                }
                else{
                    listing = new OfferedListing(listingData.getInt("ID"), listingData.getString("Name"), listingData.getString("Description"), listingData.getString("Expiry_Date"), listingData.getString("Category"), listingData.getString("Condition_ID"), listingData.getInt("User_ID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("listing", listing);

        ResultSet questionData;
        List<Question> questionList = new ArrayList<Question>();
        try{
            questionData = DatabaseController.getQuestions(listingId);
            while(questionData.next()){
                questionList.add(new Question(questionData.getInt("ID"), questionData.getInt("User_ID"), Integer.parseInt(listingId), questionData.getString("Question")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("questionList", questionList);

        ResultSet responseData;
        List<Response> responseList = new ArrayList<Response>();
        try{
            responseData = DatabaseController.getResponses(listingId);
            while(responseData.next()){
                responseList.add(new Response(responseData.getInt("Question_ID"), responseData.getString("Response")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("responseList", responseList);
        RequestDispatcher rd=request.getRequestDispatcher("/listing_view.jsp");
        rd.forward(request, response);
    }
}
