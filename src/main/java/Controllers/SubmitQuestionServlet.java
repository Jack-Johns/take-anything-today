package Controllers;

import Models.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubmitQuestionServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    // Called when a Post request is sent to /submit_question
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        response.setContentType("text/html");

        String userId = request.getParameter("userId");
        String listingId = request.getParameter("listingId");
        String question = request.getParameter("newQuestion");
        System.out.println("DEBUG: " + userId);
        System.out.println("DEBUG: " + listingId);
        System.out.println("DEBUG: " + question);

        Boolean success = DatabaseController.createQuestion(new Question(Integer.parseInt(userId), Integer.parseInt(listingId), question));
        if(success){
            System.out.println("Listing successfully updated");
        }
        else{
            System.out.println("Listing update failed");
        }
        response.sendRedirect("/browse");
    }
}
