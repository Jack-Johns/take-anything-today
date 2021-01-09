package Controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteListingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Called when a Post request is sent to /delete_listing
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        response.setContentType("text/html");

        String listingId = request.getParameter("listingId");

        Boolean success = DatabaseController.deleteListing(Integer.parseInt(listingId));
        if(success){
            System.out.println("Listing successfully updated");
        }
        else{
            System.out.println("Listing update failed");
        }
        response.sendRedirect("/browse");
    }
}
