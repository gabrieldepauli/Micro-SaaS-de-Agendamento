package controller;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/UserRedirectServlet")
public class UserRedirectController extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String tipo = request.getParameter("tipo");

        if ("CLIENT".equals(tipo)) {
            response.sendRedirect("registerClient.jsp?email=" + email + "&senha=" + senha + "&tipo=" + tipo);
        } else if ("TEACHER".equals(tipo)) {
            response.sendRedirect("registerTeacher.jsp?email=" + email + "&senha=" + senha + "&tipo=" + tipo);
        } else {
            response.sendRedirect("error.jsp");
        }
    }
    
}
