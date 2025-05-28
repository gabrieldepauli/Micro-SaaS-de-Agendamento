<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Cadastro de Usuário</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/assets/css/registerUser.css" rel="stylesheet">
	<link rel="icon" href="${pageContext.request.contextPath}/assets/images/logo.jpg" type="image/x-icon">
</head>
<body>

    <div class="container">

        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/assets/images/logo_site.png" alt="Logo EnsinaFácil" />
        </div>

        <h2 class="text-center">Cadastro de Usuário</h2>

        <form action="UserRedirectServlet" method="post">
            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required />
            </div>

            <div class="mb-3">
                <label for="senha" class="form-label">Senha:</label>
                <input type="password" class="form-control" id="senha" name="senha" required />
            </div>

            <div class="mb-3">
                <label for="tipo" class="form-label">Tipo de Usuário:</label>
                <select class="form-select" id="tipo" name="tipo" required>
                    <option value="" disabled selected>Selecione o tipo</option>
                    <option value="CLIENT">Cliente (Aluno)</option>
                    <option value="TEACHER">Professor</option>
                </select>
            </div>

            <div class="btn-group">
                <button type="submit" class="btn btn-primary">Cadastrar</button>
                <a href="home.jsp" class="btn btn-danger text-center">Voltar</a>
            </div>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
