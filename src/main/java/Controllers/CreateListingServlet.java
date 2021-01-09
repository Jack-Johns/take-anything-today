package Controllers;

import Models.Listings.WantedListing;
import Models.User;
import Models.Listings.OfferedListing;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CreateListingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Called when a Post request is sent to /create_listing
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        // Ensures that the user is logged in, if not they are forced to log in first
        if(request.getSession().getAttribute("user") == null){
            response.sendRedirect("/Login.html");
        }

        User currentUser = (User) request.getSession().getAttribute("user");
        response.setContentType("text/html");

        // Stores the value from each input field
        String listingType = request.getParameter("listing_type");
        String listingName = request.getParameter("listing_name");
        String category = request.getParameter("category");
        String condition = request.getParameter("condition");
        String expiryDate = request.getParameter("expiry_date");
        String description = request.getParameter("description");

        Boolean sqlResponse = false;
        if(listingType.equals("wanted")){
            WantedListing newListing = new WantedListing(listingName, description, expiryDate, category, currentUser.getId());
            sqlResponse = DatabaseController.createWantedListing(newListing);
        }
        else if (listingType.equals("offered")){
            OfferedListing newListing = new OfferedListing(listingName, description, expiryDate, category, condition, currentUser.getId());
            sqlResponse = DatabaseController.createOfferedListing(newListing);
        }
        if(sqlResponse){
            System.out.println("Listing Created Successfully");
            response.sendRedirect("/listing_view.jsp");
        }
        else{
            System.out.println("Listing Creation Failure");
            response.sendRedirect("/edit_create_listing.jsp");
        }
    }
}
