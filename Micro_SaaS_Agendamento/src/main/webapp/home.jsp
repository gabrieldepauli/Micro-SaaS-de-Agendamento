<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <title>Página Inicial</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/css/home.css" rel="stylesheet">
  <link rel="icon" href="${pageContext.request.contextPath}/assets/images/logo.jpg" type="image/x-icon">
</head>
<body>

  <nav class="navbar navbar-expand-lg navbar-custom">
    <div class="container">
      <a class="navbar-brand" href="home.jsp">
        <img src="${pageContext.request.contextPath}/assets/images/logo_site.png" alt="Logo EnsinaFácil" />
        EnsinaFácil
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
        <span class="navbar-toggler-icon" style="filter: invert(1);"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto align-items-center">
          <li class="nav-item">
            <a class="nav-link btn-register" href="registerUser.jsp">Cadastre-se</a>
          </li>
          <li class="nav-item">
            <a class="nav-link btn-login" href="login.jsp">Login</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <main class="container">
    <h1>Bem-vindo ao MicroSaaS de Agendamento de Aulas Particulares!</h1>
  </main>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
