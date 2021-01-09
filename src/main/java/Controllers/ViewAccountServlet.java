package Controllers;

import Models.Address;
import Models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Called when a Post request is sent to /view_account
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        String userId = request.getParameter("submitter");

        ResultSet accountData;
        User user;
        try{
            accountData = DatabaseController.getAccount(Integer.parseInt(userId));
            accountData.next();
            user = new User(Integer.parseInt(userId), accountData.getString("First_Name"), accountData.getString("Last_Name"), new Address(accountData.getString("Address_Line_1"),accountData.getString("Address_Line_2"), accountData.getString("City"), accountData.getString("Postcode")));
        } catch (SQLException e) {
            e.printStackTrace();
            accountData = null;
            user = null;
        }
        if(accountData != null){
            request.setAttribute("user_view", user);
            RequestDispatcher rd=request.getRequestDispatcher("/view_account.jsp");
            rd.forward(request, response);
        }
        else{
            response.sendRedirect("/Login.html");
        }
    }
}
