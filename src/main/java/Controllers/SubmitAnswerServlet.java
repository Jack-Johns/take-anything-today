package Controllers;

import Models.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubmitAnswerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Called when a Post request is sent to /submit_answer
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        response.setContentType("text/html");

        String userId = request.getParameter("userId");
        String questionId = request.getParameter("questionId");
        String answer = request.getParameter("newResponse");
        System.out.println("DEBUG: " + userId);
        System.out.println("DEBUG: " + questionId);
        System.out.println("DEBUG: " + answer);

        Boolean success = DatabaseController.createResponse(new Response(Integer.parseInt(questionId), answer));
        if(success){
            System.out.println("Listing successfully updated");
        }
        else{
            System.out.println("Listing update failed");
        }
        response.sendRedirect("/browse");
    }
}
