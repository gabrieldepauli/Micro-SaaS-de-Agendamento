<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home - Professor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="icon" href="assets/images/logo.jpg" type="image/x-icon">
</head>
<body>
	<div class="overlay"></div>
	
	<div class="container mt-5 shadow-lg rounded">
	    <div class="card bg-transparent border-0"> <div class="card-body text-center">
	            <h1 class="card-title mb-4">Menu do Professor</h1>
	            <hr class="my-4">
	
	            <div class="d-grid gap-3 col-md-8 mx-auto"> 
	            	<a href="${pageContext.request.contextPath}/teacher/registerDisponibilidade.jsp" class="btn btn-primary">Cadastrar Horários Disponíveis</a>
	                <a href="${pageContext.request.contextPath}/portfolio" class="btn btn-success">Adicionar Portfólio</a>
	                <a href="${pageContext.request.contextPath}/ListarAgendamentosServlet" class="btn btn-warning">Ver Agendamentos</a>
	                <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Logout</a>
	            </div>
	        </div>
	    </div>
	    
	    <p class="text-center text-muted mt-4 small">© 2025 EnsinaFacil - Todos os direitos reservados</p>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>