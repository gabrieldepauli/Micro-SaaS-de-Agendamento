package controller;

import dao.ClientDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Client;
import model.User;
import java.io.IOException;

@WebServlet("/ClientController")
public class RegisterClientController extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String tipo = request.getParameter("tipo");

        if (email != null && senha != null && "CLIENT".equals(tipo)) {
            String name = request.getParameter("full_name");
            String cpf = request.getParameter("cpf");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");

            User usuario = new User(email, senha, tipo);

            Client client = new Client();
            client.setName(name);
            client.setCpf(cpf);
            client.setAdress(address);
            client.setNumber(phone);

            ClientDAO clientDAO = new ClientDAO();
            boolean cadastrado = clientDAO.cadastrarClienteComUsuario(usuario, client);

            if (cadastrado) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            } else {
            	request.setAttribute("mensagem", "Erro ao inserir cliente!");
            	RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
            	dispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
