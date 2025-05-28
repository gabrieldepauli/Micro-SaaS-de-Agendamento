package controller;

import dao.TeacherDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Teacher;
import model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@WebServlet("/TeacherController")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 5 * 1024 * 1024,
    maxRequestSize = 25 * 1024 * 1024
)
public class RegisterTeacherController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String tipo = request.getParameter("tipo");

        if (email != null && senha != null && "TEACHER".equals(tipo)) {

            String businessName = request.getParameter("businessName");
            String fullName = request.getParameter("fullName");
            String specialty = request.getParameter("specialty");
            String address = request.getParameter("address");
            String description = request.getParameter("descricao");

            Part filePart = request.getPart("profilePicture");
            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String fileNameLower = originalFileName.toLowerCase();

            if (!(fileNameLower.endsWith(".jpg") || fileNameLower.endsWith(".jpeg"))) {
                response.sendRedirect("erroFormatoArquivo.jsp");
                return;
            }

            String extension = fileNameLower.endsWith(".jpeg") ? ".jpeg" : ".jpg";
            String uniqueFileName = UUID.randomUUID().toString() + extension;

            String uploadPath = "C:" + File.separator + "uploads" + File.separator + "perfis";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            filePart.write(uploadPath + File.separator + uniqueFileName);
 
            String profilePicture = "image/" + uniqueFileName;

            User usuario = new User(email, senha, tipo);

            Teacher teacher = new Teacher();
            teacher.setBusinessName(businessName);
            teacher.setName(fullName);
            teacher.setProfilePicture(profilePicture);
            teacher.setSpecialty(specialty);
            teacher.setAddress(address);
            teacher.setDescription(description);

            TeacherDAO teacherDAO = new TeacherDAO();
            boolean cadastrado = teacherDAO.cadastrarProfessorComUsuario(usuario, teacher);

            if (cadastrado) {
                response.sendRedirect(request.getContextPath() + "/teacher/homeTeacher.jsp");
            } else {
            	request.setAttribute("mensagem", "Erro ao inserir professor!");
            	RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
            	dispatcher.forward(request, response);
            }

        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
