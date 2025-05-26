package controller.filter;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebFilter;

// Filtro para bloquear acesso para quem não está logado a certas páginas
//@WebFilter(urlPatterns = {"/client/*","/teacher/*"})
public class SessionFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		
		if(session != null && session.getAttribute("usuarioLogado") != null) {
			filterChain.doFilter(httpRequest, response);
		}	else {
			request.setAttribute("erro", "Você deve estar logado para acessar esta página.");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginServlet");
			
			dispatcher.forward(request, response);
		}
		
	}
	
}
