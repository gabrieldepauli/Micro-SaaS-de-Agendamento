<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Agenda de serviços</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/assets/css/teacherAppointmentList.css" rel="stylesheet">
	<link rel="icon" href="${pageContext.request.contextPath}/assets/images/logo.jpg" type="image/x-icon">
</head>
<body>

<div class="overlay"></div>

<div class="container mt-5 shadow-lg rounded">
    <h2 class="mb-5">Meus agendamentos</h2>

		<form method="get"
			action="${pageContext.request.contextPath}/teacher/ListTeacherAppointment"
			class="form-row-filter">
			<input type="hidden" name="id_cliente" value="${professorId}" />
			<div style="flex: 1;">
				<label for="status" class="form-label">Filtrar pelo Status
					do agendamento:</label> <select name="status" id="status"
					class="form-select">
					<option value="" ${empty sessionScope.selectedStatus ? 'selected' : ''}>Todos</option>
					<option value="solicitado" ${sessionScope.selectedStatus == 'solicitado' ? 'selected' : ''}>Solicitado</option>
					<option value="aceito" ${sessionScope.selectedStatus == 'aceito' ? 'selected' : ''}>Aceito</option>
					<option value="concluido" ${sessionScope.selectedStatus == 'concluido' ? 'selected' : ''}>Concluído</option>
				</select>
			</div>
			<div>
				<button type="submit" class="btn btn-primary"
					style="height: 42px; margin-top: 1.7rem;">Filtrar</button>
			</div>
		</form>

    <div class="table-responsive">
        <table class="table table-bordered table-hover">
            <thead class="table-dark"> <tr>
                    <th>ID Aluno</th>
                    <th>Aluno</th>
                    <th>Data</th>
                    <th>Horário</th>
                    <th>Status</th>
                    <th colspan="2">Ações</th> </tr>
            </thead>
            <tbody>
                <c:forEach var="a" items="${sessionScope.appointment}">
                    <tr>
                        <td>${a.clientId}</td> 
                        <td>${a.clientName}</td> 
                        <td>${a.date}</td>
                        <td>${a.time}</td>
                        <td>${a.status}</td>
                        <td>
                            <c:choose>
                                <c:when test="${a.status == 'SOLICITADO'}">
                                    <form method="post" action="${pageContext.request.contextPath}/teacher/AtualizarStatusServlet" class="d-inline">
                                        <input type="hidden" name="id_agendamento" value="${a.id}" />
                                        <input type="hidden" name="novo_status" value="aceito" />
                                        <button type="submit" class="btn btn-success btn-sm">Aceitar</button>
                                    </form>
                                </c:when>
                                <c:when test="${a.status == 'ACEITO'}">
                                    <form method="post" action="${pageContext.request.contextPath}/teacher/AtualizarStatusServlet" class="d-inline">
                                        <input type="hidden" name="id_agendamento" value="${a.id}" />
                                        <input type="hidden" name="novo_status" value="concluido" />
                                        <button type="submit" class="btn btn-warning btn-sm">Concluir</button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <span class="text-muted">-</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}/teacher/LogController" class="d-inline">
                                <input type="hidden" name="appointment_Id" value="${a.id}" />
                                <button type="submit" class="btn btn-info btn-sm">Ver Log</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty sessionScope.appointment}">
                    <tr>
                        <td colspan="6" class="text-center text-muted">Nenhum agendamento encontrado.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
    
    <div class="text-center mt-4">
			<c:choose>
				<c:when test="${page > 0}">
					<a class="btn btn-pagination btn-pagination-anterior me-2"
						href="${pageContext.request.contextPath}/teacher/ListTeacherAppointment?page=${page - 1}&status=${param.status}">Anterior</a>
				</c:when>
				<c:otherwise>
					<span class="btn btn-pagination btn-pagination-disabled me-2">Anterior</span>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${page < totalPages - 1}">
					<a class="btn btn-pagination btn-pagination-proximo"
						href="${pageContext.request.contextPath}/teacher/ListTeacherAppointment?page=${page + 1}&status=${param.status}">Próximo</a>
				</c:when>
				<c:otherwise>
					<span class="btn btn-pagination btn-pagination-disabled">Próximo</span>
				</c:otherwise>
			</c:choose>
		</div>

		<div class="text-center mt-5">
			<a href="${pageContext.request.contextPath}/teacher/homeTeacher.jsp" class="btn btn-danger">Voltar</a>
		</div>
</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>