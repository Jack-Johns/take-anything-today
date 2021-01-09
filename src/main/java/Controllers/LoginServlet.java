package Controllers;

import Models.Address;
import Models.Login;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Called when a Post request is sent to /login
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        response.setContentType("text/html");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Login loginProvided = new Login(email, password);
        ResultSet accountData;
        User currentUser;
        try{
            accountData = DatabaseController.verifyLogin(loginProvided);
            accountData.next();
            currentUser = new User(accountData.getInt("ID"), accountData.getString("First_Name"), accountData.getString("Last_Name"), new Address(accountData.getString("Address_Line_1"),accountData.getString("Address_Line_2"), accountData.getString("City"), accountData.getString("Postcode")), new Login(accountData.getString("Email"), accountData.getString("Password")));
        } catch (SQLException e) {
            e.printStackTrace();
            accountData = null;
            currentUser = null;
        }
        if(accountData != null){
            request.getSession().setAttribute("user", currentUser);
            response.sendRedirect("/account_details.jsp");
        }
        else{
            response.sendRedirect("/Login.html");
        }

    }
}
