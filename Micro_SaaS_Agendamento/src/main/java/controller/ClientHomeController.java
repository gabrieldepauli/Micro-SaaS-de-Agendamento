package controller;

import dao.PortfolioDAO;
import dao.TeacherDAO;
import model.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/client/HomeAluno")
public class ClientHomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String city = request.getParameter("city");
	    String specialty = request.getParameter("specialty");
	    TeacherDAO dao = new TeacherDAO();
	    PortfolioDAO portfolioDAO = new PortfolioDAO();
	    List<Teacher> teachers = null;

	    int page = 0;
	    int totalPages = 0;

	    String pageStr = request.getParameter("page");
	    if (pageStr != null) {
	        page = Integer.parseInt(pageStr);
	    }

	    try {
	        if ((city != null && !city.isEmpty()) || (specialty != null && !specialty.isEmpty())) {
	            teachers = dao.searchByCityAndSpecialty(city, specialty, page);
	            totalPages = dao.getPagesByCityAndSpecialty(city, specialty);
	        } else {
	            teachers = dao.listTeacher(page);
	            totalPages = dao.getPages();
	        }

	        for (Teacher teacher : teachers) {
	            List<String> imagens = portfolioDAO.searchImageByTeacher(teacher.getId());
	            teacher.setImagens(imagens);
	        }

	        List<String> specialties = dao.listAllSpecialties();
	        request.setAttribute("specialties", specialties);

	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("mensagem", "Falha para carregar os professores.");
	        request.getRequestDispatcher("/erro.jsp").forward(request, response);
	        
	        return;
	    }

	    request.setAttribute("page", page);
	    request.setAttribute("totalPages", totalPages);
	    request.setAttribute("teachers", teachers);
	    request.setAttribute("selectedCity", city);
	    request.setAttribute("selectedSpecialty", specialty);

	    request.getRequestDispatcher("/client/homeClient.jsp").forward(request, response);
	}
}
