package controller;

import dao.PortfolioDAO;
import dao.TeacherDAO;
import model.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/HomeAluno")
public class ClientHomeController extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String city = request.getParameter("city");
	    TeacherDAO dao = new TeacherDAO();
	    PortfolioDAO portfolioDAO = new PortfolioDAO();
	    List<Teacher> teachers = null;
	    
	    int page = 0;
	    int totalPages = 0;
	    
	    String pageStr = request.getParameter("page");
	    if (pageStr != null){
	    	page = Integer.parseInt(request.getParameter("page"));
	    }

	    
	    try {
	       
	        if (city != null && !city.isEmpty()) {
	        	teachers = dao.searchByCity(city, page);
	        	totalPages = dao.getPagesByCity(city);
	        } else {
	        	teachers = dao.listTeacher(page);
	        	totalPages = dao.getPages();
	        }
	        
	        for (Teacher teacher : teachers) {
	            List<String> imagens = portfolioDAO.searchImageByTeacher(teacher.getId());
	            teacher.setImagens(imagens);
	        }
	        
	        System.out.println("[DEBUG] Prestadores encontrados: " + teachers.size());
	        teachers.forEach(t -> System.out.println(t.getBusinessName()));
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("erro", "Falha para carregar os professores.");
	        request.getRequestDispatcher("/erro.jsp").forward(request, response);
	    }
	    
	    request.setAttribute("page", page);
	    request.setAttribute("totalPages", totalPages);
	    request.setAttribute("teachers", teachers);
	    request.setAttribute("selectedCity", city);
	    
	    request.getRequestDispatcher("/client/homeClient.jsp").forward(request, response);

	    
	}
	
}
