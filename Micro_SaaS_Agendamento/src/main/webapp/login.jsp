<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Login - Sistema de Agendamento</title>
	    <!-- Bootstrap CSS -->
	    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
	    <!-- Custom CSS -->
	    <style>
	        body {
	            background-color: #f4f7fc;
	            font-family: Arial, sans-serif;
	        }
	        .login-container {
	            max-width: 400px;
	            margin: 50px auto;
	            padding: 20px;
	            background-color: #fff;
	            border-radius: 8px;
	            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
	        }
	        .login-container h2 {
	            text-align: center;
	            margin-bottom: 30px;
	        }
	        .error-message {
	            color: red;
	            text-align: center;
	            margin-bottom: 15px;
	        }
	    </style>
	</head>
	<body>
	
	    <div class="login-container">
	        <h2>Login</h2>
	        
	        <form action="LoginServlet" method="post">
	            <div class="form-group">
	                <label for="email">Email</label>
	                <input type="email" class="form-control" id="email" name="email" required placeholder="Digite seu email">
	            </div>
	            <div class="form-group">
	                <label for="senha">Senha</label>
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
	
	            <button type="submit" class="btn btn-primary btn-block">Entrar</button>
	        </form>
	        
	        <div class="text-center mt-3">
	            <a href="registerUser.jsp">Não tem uma conta? Cadastre-se aqui.</a>
	        </div><br>
	        
	        <div class="text-center mt-3">
	        	<a href="home.jsp" class="btn btn-danger" style="width: 200px;">Voltar para tela inicial</a>
	        </div>
	    </div>
	
	    <!-- Bootstrap JS -->
	    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>
