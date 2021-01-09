package Controllers;

import Models.Listings.OfferedListing;
import Models.Listings.WantedListing;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditListingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Called when a Post request is sent to /edit_listing
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        if(request.getSession().getAttribute("user") == null){
            response.sendRedirect("/Login.html");
        }
        response.setContentType("text/html");

        // Stores the value from each input field
        User currentUser = (User) request.getSession().getAttribute("user");
        String id = request.getParameter("id");
        String type = request.getParameter("listing_type");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        String expiryDate = request.getParameter("expiry_date");
        Boolean success = false;

        if(type.equals("1")){
            WantedListing newListing = new WantedListing(Integer.parseInt(id), name, description, expiryDate, category, currentUser.getId());
            success = DatabaseController.updateWantedListing(newListing);
        }
        else if(type.equals("2")){
            String condition = request.getParameter("condition");
            OfferedListing newListing = new OfferedListing(Integer.parseInt(id), name, description, expiryDate, category, condition, currentUser.getId());
            success = DatabaseController.updateOfferedListing(newListing);
        }

        if(success){
            System.out.println("Listing successfully updated");
        }
        else{
            System.out.println("Listing update failed");
        }
        response.sendRedirect("/browse");
    }
}
