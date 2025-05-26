package controller;

import dao.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("senha");

        UserDAO dao = new UserDAO();
        try {
            User user = dao.authenticate(email, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuarioLogado", user);

                if ("CLIENT".equals(user.getTipo())) {
                	response.sendRedirect(request.getContextPath() + "/HomeAluno");
                } else if ("TEACHER".equals(user.getTipo())) {
                    response.sendRedirect("/Micro_SaaS_Agendamento/teacher/homeTeacher.jsp");
                } else {
                    response.sendRedirect("home.jsp");
                }

            } else {
                response.sendRedirect("login.jsp?error=1");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            
            response.sendRedirect("login.jsp?error=2");
        }
    }
}