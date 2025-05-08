package controller;

import model.User;
import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UserRedirectServlet")
public class UserRedirectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String tipo = request.getParameter("tipo");

        User newUser = new User(email, senha, tipo);

        HttpSession session = request.getSession();
        session.setAttribute("usuarioLogado", newUser);

        if ("CLIENT".equals(tipo)) {
            response.sendRedirect("registerClient.jsp");
        } else if ("TEACHER".equals(tipo)) {
            response.sendRedirect("registerTeacher.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
