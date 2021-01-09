package Controllers;

import Models.Address;
import Models.Login;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Called when a Post request is sent to /edit_account
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        // Ensures that the user is logged in, if not they are forced to log in first
        if(request.getSession().getAttribute("user") == null){
            response.sendRedirect("/Login.html");
        }
        response.setContentType("text/html");

        // Stores the value from each input field
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String addressLine1 = request.getParameter("address_line_1");
        String addressLine2 = request.getParameter("address_line_2");
        String city = request.getParameter("city");
        String postcode = request.getParameter("postcode");

        User userToUpdate = new User(firstName, lastName, new Address(addressLine1, addressLine2, city, postcode), new Login(email, password));
        Boolean success = DatabaseController.updateUser(userToUpdate);
        if(success){
            System.out.println("User successfully updated");
            request.getSession().setAttribute("user", userToUpdate);
            response.sendRedirect("/account_details.jsp");
        }
        else{
            System.out.println("User update failed");
            response.sendRedirect("/edit_account.jsp");
        }
    }
}
