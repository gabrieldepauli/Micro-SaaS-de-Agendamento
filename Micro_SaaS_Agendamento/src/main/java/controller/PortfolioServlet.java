package controller;

import dao.PortfolioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@WebServlet("/portfolio")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 5 * 1024 * 1024,
    maxRequestSize = 25 * 1024 * 1024
)
public class PortfolioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User usuarioLogado = (User) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado == null || !"TEACHER".equals(usuarioLogado.getTipo())) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		Part filePart = request.getPart("image");
		if (filePart != null && filePart.getSize() > 0) {
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			String fileNameLower = fileName.toLowerCase();
			
			if (!(fileNameLower.endsWith(".jpg") || fileNameLower.endsWith(".jpeg") || fileNameLower.endsWith(".png"))) {
				response.sendRedirect("erroFormatoArquivo.jsp");
				return;
			}
			
			String uploadPath = "C:" + File.separator + "uploads" + File.separator + "portfolio";
			File uploadDir = new File(uploadPath);
			
			if (!uploadDir.exists()) uploadDir.mkdirs();
			
			String filePath = uploadPath + File.separator + fileName;
			filePart.write(filePath);
			
			String relativePath = "image/" + fileName;
			PortfolioDAO dao = new PortfolioDAO();
			dao.insertImage(usuarioLogado.getId(), relativePath);
		}
		
		response.sendRedirect(request.getContextPath() + "/portfolio");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User usuarioLogado = (User) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado == null || !"TEACHER".equals(usuarioLogado.getTipo())) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		PortfolioDAO dao = new PortfolioDAO();
		List<String> images = dao.searchImageByTeacher(usuarioLogado.getId());
		request.setAttribute("images", images);
		request.getRequestDispatcher("/teacher/portfolio.jsp").forward(request, response);
	}
}