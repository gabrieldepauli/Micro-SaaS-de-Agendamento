package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/image/*")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String BASE_PATH_PORT = "C:\\uploads\\portfolio\\";
	private static final String BASE_PATH_PERFIL = "C:\\uploads\\perfis\\";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String imageName = request.getPathInfo();
		
		if (imageName == null || imageName.equals("/")) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nome da imagem não especificado.");
			
			return;
		}
		
		imageName = imageName.substring(1);
		File imageFile = new File(BASE_PATH_PORT, imageName);
		if (!imageFile.exists() || imageFile.isDirectory()) {
			imageFile = new File(BASE_PATH_PERFIL, imageName);
			
            if (!imageFile.exists() || imageFile.isDirectory()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Imagem não encontrada");
                
                return;
            }
		}
		
		String contentType = Files.probeContentType(imageFile.toPath());
		if (contentType == null) contentType = "application/octet-stream";
		response.setContentType(contentType);
		Files.copy(imageFile.toPath(), response.getOutputStream());
	}
}
