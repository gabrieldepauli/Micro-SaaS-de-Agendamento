<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (session != null) {
        model.User usuarioLogado = (model.User) session.getAttribute("usuarioLogado");
        if (usuarioLogado != null) {
            String tipo = usuarioLogado.getTipo();
            if ("CLIENT".equals(tipo)) {
                response.sendRedirect(request.getContextPath() + "/client/HomeAluno");
                return;
            } else if ("TEACHER".equals(tipo)) {
                response.sendRedirect(request.getContextPath() + "/teacher/homeTeacher.jsp");
                return;
            } else {
                response.sendRedirect(request.getContextPath() + "/home.jsp");
                return;
            }
        }
    }
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login - Sistema de Agendamento</title>
  <link rel="icon" href="assets/images/logo.jpg" type="image/x-icon">
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/css/login.css" rel="stylesheet">
  <link rel="icon" href="${pageContext.request.contextPath}/assets/images/logo.jpg" type="image/x-icon">
</head>
<body>

  <div class="login-container">
    <img src="assets/images/logo_site.png" alt="Logo do Sistema">
    <h2>Login</h2>

    <form action="LoginServlet" method="post">
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" class="form-control" id="email" name="email" required placeholder="Digite seu email">
      </div>

      <div class="form-group">
        <label for="senha">Senha:</label>
        <input type="password" class="form-control" id="senha" name="senha" required placeholder="Digite sua senha">
      </div>

      <% 
        String error = request.getParameter("error");
        if ("1".equals(error)) {
          out.println("<div class='error-message'>Email ou senha incorretos.</div>");
        } else if ("2".equals(error)) {
          out.println("<div class='error-message'>Erro ao conectar ao banco de dados.</div>");
        }
      %>

      <button type="submit" class="btn btn-primary">Entrar</button>
    </form>

    <div class="text-center mt-3">
      <a href="registerUser.jsp">Não tem uma conta? Cadastre-se aqui.</a>
    </div>

    <div class="text-center mt-3">
      <a href="home.jsp" class="btn btn-danger">Voltar para tela inicial</a>
    </div>
  </div>

  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
