package controller;

import dao.ClientDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Client;
import model.User;
import java.io.IOException;

@WebServlet("/ClientController")
public class ClientController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User usuarioLogado = (User) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null && "CLIENT".equals(usuarioLogado.getTipo())) {
            String name = request.getParameter("name");
            String cpf = request.getParameter("cpf");
            String adress = request.getParameter("adress");
            String phone = request.getParameter("phone");

            Client client = new Client();
            client.setId(usuarioLogado.getId());
            client.setName(name);
            client.setCpf(cpf);
            client.setAdress(adress);
            client.setNumber(phone);

            ClientDAO clientDAO = new ClientDAO();
            boolean cadastrado = clientDAO.cadastrarClienteComUsuario(usuarioLogado, client);

            if (cadastrado) {
                // Após inserir no banco, redireciona para página de sucesso
                response.sendRedirect("cadastroSucesso.jsp");
            } else {
                // Em caso de erro
                response.sendRedirect("erroCadastro.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
