<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Horários - Professor</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background: #f8f9fa;
        }
        .card-custom {
            background-color: #ffffff;
            border: 1px solid #dee2e6;
            padding: 2rem;
        }
        th, td {
            vertical-align: middle !important;
        }
    </style>
</head>
<body>

	<div class="container my-5">
	    <div class="card card-custom shadow-sm mx-auto" style="max-width: 1000px;">
	        <h3 class="text-center mb-4">Cadastrar Horários de Disponibilidade</h3>
	
	        <form action="${pageContext.request.contextPath}/RegisterDisponibilidade" method="post">
	            <div class="table-responsive">
	                <table class="table table-bordered text-center align-middle">
	                    <thead class="table-primary">
	                        <tr>
	                            <th>Dia</th>
	                            <th>Início</th>
	                            <th>Fim</th>
	                            <th>Descanso Início</th>
	                            <th>Descanso Fim</th>
	                            <th>Duração Serviço</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                        <c:forEach var="i" begin="1" end="7">
	                            <tr>
	                                <td>
	                                    <c:choose>
	                                        <c:when test="${i == 1}">Segunda</c:when>
	                                        <c:when test="${i == 2}">Terça</c:when>
	                                        <c:when test="${i == 3}">Quarta</c:when>
	                                        <c:when test="${i == 4}">Quinta</c:when>
	                                        <c:when test="${i == 5}">Sexta</c:when>
	                                        <c:when test="${i == 6}">Sábado</c:when>
	                                        <c:when test="${i == 7}">Domingo</c:when>
	                                    </c:choose>
	                                </td>
	                                <td><input type="time" name="inicio_${i}" class="form-control form-control-sm"></td>
	                                <td><input type="time" name="fim_${i}" class="form-control form-control-sm"></td>
	                                <td><input type="time" name="descanso_inicio_${i}" class="form-control form-control-sm"></td>
	                                <td><input type="time" name="descanso_fim_${i}" class="form-control form-control-sm"></td>
	                                <td><input type="time" name="tempo_servico_${i}" class="form-control form-control-sm"></td>
	                            </tr>
	                        </c:forEach>
	                    </tbody>
	                </table>
	            </div>
	
	            <input type="hidden" name="idPrestador" value="${idPrestador}" />
	
	            <div class="text-center mt-4">
	                <button type="submit" class="btn btn-primary px-4">Salvar Horários</button>
	            </div><br>
	        </form>
	
	        <c:if test="${not empty sessionScope.mensagem}">
	            <div class="alert alert-info text-center mt-4">
	                ${sessionScope.mensagem}
	            </div>
	            <c:remove var="mensagem" scope="session" />
	        </c:if>
	
	        <div class="text-center mt-2">
	            <a href="${pageContext.request.contextPath}/teacher/homeTeacher.jsp" class="btn btn-danger">Voltar para o Menu</a>
	        </div>
	    </div>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
