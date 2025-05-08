package controller;

import dao.TeacherDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Teacher;
import model.User;
import java.io.IOException;

@WebServlet("/TeacherController")
public class TeacherController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User usuarioLogado = (User) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null && "TEACHER".equals(usuarioLogado.getTipo())) {

            String businessName = request.getParameter("businessName");
            String fullName = request.getParameter("fullName");
            String profilePicture = request.getParameter("profilePicture");
            String specialty = request.getParameter("specialty");
            String address = request.getParameter("address");
            String description = request.getParameter("description");

            Teacher teacher = new Teacher();
            teacher.setId(usuarioLogado.getId());
            teacher.setBusinessName(businessName);
            teacher.setName(fullName);
            teacher.setProfilePicture(profilePicture);
            teacher.setSpecialty(specialty);
            teacher.setAdress(address);
            teacher.setDescription(description);

            TeacherDAO teacherDAO = new TeacherDAO();
            boolean cadastrado = teacherDAO.cadastrarProfessorComUsuario(usuarioLogado, teacher);

            if (cadastrado) {
                response.sendRedirect("cadastroSucesso.jsp");
            } else {
                response.sendRedirect("erroCadastro.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
