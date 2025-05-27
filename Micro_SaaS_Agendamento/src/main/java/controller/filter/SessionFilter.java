package controller.filter;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = {"/client/*", "/teacher/*"})
public class SessionFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        boolean loggedIn = (session != null && session.getAttribute("usuarioLogado") != null);
        
        if (loggedIn) {
            chain.doFilter(request, response);
        } else {
            String loginPage = httpRequest.getContextPath() + "/login.jsp";
            
            httpResponse.sendRedirect(loginPage);
        }
    }
}
