package Controllers;

import Models.Address;
import Models.Login;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Called when a Post request is sent to /create_account
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
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

        // Creates a new User using the previous values
        User newUser = new User(firstName, lastName, new Address(addressLine1, addressLine2, city, postcode), new Login(email, password));
        // Runs createUser() and redirects accordingly
        if(DatabaseController.createUser(newUser)){
            System.out.println("Account Created Successfully");
            response.sendRedirect("/Login.html");
        }
        else{
            System.out.println("Account Creation Failure");
            response.sendRedirect("/edit_create_account.jsp");
        }


    }
}
