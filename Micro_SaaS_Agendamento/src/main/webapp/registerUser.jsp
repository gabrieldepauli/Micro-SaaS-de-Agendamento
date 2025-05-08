<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Usuário</title>
    <link rel="icon" href="assets/images/logo.jpg" type="image/x-icon">
</head>
<body>
    <h2>Cadastro de Usuário</h2>
    <form action="UserRedirectServlet" method="post">
        <label>Email:</label><br>
        <input type="email" name="email" required><br><br>

        <label>Senha:</label><br>
        <input type="password" name="senha" required><br><br>

        <label>Tipo de Usuário:</label><br>
        <select name="tipo" required>
            <option value="CLIENT">Cliente (Aluno)</option>
            <option value="TEACHER">Professor</option>
        </select><br><br>

        <button type="submit">Cadastrar</button>
    </form>
</body>
</html>
