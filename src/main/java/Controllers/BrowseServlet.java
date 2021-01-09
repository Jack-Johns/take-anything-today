package Controllers;

import Models.Listings.Listing;
import Models.Listings.OfferedListing;
import Models.Listings.WantedListing;

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


public class BrowseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Called when a Get request is sent to /browse
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        response.setContentType("text/html");
        ResultSet listingsData;
        List<Listing> listingsList = new ArrayList<Listing>();

        // The result of getAllListings() is saved and iterated through, creating a new WantedListing or OfferedListing
        // with the correct details and adding it to listingsList
        try{
            listingsData = DatabaseController.getAllListings();
            while(listingsData.next()){
                if(listingsData.getString("Type").equals("Wanted")){
                    listingsList.add(new WantedListing(listingsData.getInt("ID"), listingsData.getString("Name"), listingsData.getString("Description"), listingsData.getString("Expiry_Date"), listingsData.getString("Category"), listingsData.getInt("User_ID")));
                }
                else{
                    listingsList.add(new OfferedListing(listingsData.getInt("ID"), listingsData.getString("Name"), listingsData.getString("Description"), listingsData.getString("Expiry_Date"), listingsData.getString("Category"), listingsData.getString("Condition_ID"), listingsData.getInt("User_ID")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("listingsList", listingsList);
        RequestDispatcher rd=request.getRequestDispatcher("/browse.jsp");
        rd.forward(request, response);
    }
}
