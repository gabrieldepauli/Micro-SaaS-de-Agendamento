<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home - Professor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/assets/css/homeTeacher.css" rel="stylesheet">
	<link rel="icon" href="${pageContext.request.contextPath}/assets/images/logo.jpg" type="image/x-icon">
</head>
<body>

    <div class="overlay"></div>

    <div class="container mt-5">
        <div class="card shadow-lg p-4">
            <div class="card-body text-center">
                <h1 class="card-title mb-4">Portal do Professor</h1>
                <hr class="my-4">

                <div class="d-grid gap-3 col-md-8 mx-auto">
                    <a href="${pageContext.request.contextPath}/teacher/registerDisponibilidade.jsp" class="btn btn-primary">Cadastrar Horários Disponíveis</a>
                    <a href="${pageContext.request.contextPath}/teacher/portfolio" class="btn btn-success">Adicionar Portfólio</a>
                    <a href="${pageContext.request.contextPath}/teacher/ListTeacherAppointment" class="btn btn-warning text-white">Ver Agendamentos</a>
                    <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
