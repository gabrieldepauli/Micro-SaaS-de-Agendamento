<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<html>
<head>
<title>Lista de agendamentos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/clientAppointmentList.css" rel="stylesheet">
<link rel="icon" href="${pageContext.request.contextPath}/assets/images/logo.jpg" type="image/x-icon">
</head>
<body>
	<div class="container">
		<h2 class="mb-5">Meus agendamentos</h2>

		<form method="get"
			action="${pageContext.request.contextPath}/client/ListClientAppointment"
			class="form-row-filter">
			<input type="hidden" name="id_cliente" value="${idCliente}" />
			<div style="flex: 1;">
				<label for="status" class="form-label">Filtrar pelo Status
					do agendamento:</label> <select name="status" id="status"
					class="form-select">
					<option value="">Todos</option>
					<option value="solicitado"
						${param.status == 'SOLICITADO' ? 'selected' : ''}>Solicitado</option>
					<option value="aceito"
						${param.status == 'ACEITO' ? 'selected' : ''}>Aceito</option>
					<option value="concluido"
						${param.status == 'CONCLUIDO' ? 'selected' : ''}>Concluído</option>
				</select>
			</div>
			<div>
				<button type="submit" class="btn btn-primary"
					style="height: 42px; margin-top: 1.7rem;">Filtrar</button>
			</div>
		</form>

		<div class="table-responsive">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>ID Professor</th>
						<th>Professor</th>
						<th>Data</th>
						<th>Horário</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="a" items="${sessionScope.appointment}">
						<tr>
							<td>${a.professorId}</td>
							<td>${a.professorName}</td>
							<td>${a.date}</td>
							<td>${a.time}</td>
							<td>
							    <c:choose>
							        <c:when test="${a.status == 'SOLICITADO'}">
							            <span class="badge bg-warning text-dark">Solicitado</span>
							        </c:when>
							        <c:when test="${a.status == 'ACEITO'}">
							            <span class="badge bg-success">Aceito</span>
							        </c:when>
							        <c:when test="${a.status == 'CONCLUIDO'}">
							            <span class="badge bg-secondary">Concluído</span>
							        </c:when>
							        <c:otherwise>
							            <span class="badge bg-light text-dark">${a.status}</span>
							        </c:otherwise>
							    </c:choose>
							</td>

						</tr>
					</c:forEach>
					<c:if test="${empty sessionScope.appointment}">
						<tr>
							<td colspan="4" class="text-center text-muted">Nenhum agendamento encontrado.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>

		<div class="text-center mt-4">
			<c:choose>
				<c:when test="${page > 0}">
					<a class="btn btn-pagination btn-pagination-anterior me-2"
						href="${pageContext.request.contextPath}/client/ListClientAppointment?page=${page - 1}&status=${param.status}">Anterior</a>
				</c:when>
				<c:otherwise>
					<span class="btn btn-pagination btn-pagination-disabled me-2">Anterior</span>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${page < totalPages - 1}">
					<a class="btn btn-pagination btn-pagination-proximo"
						href="${pageContext.request.contextPath}/client/ListClientAppointment?page=${page + 1}&status=${param.status}">Próximo</a>
				</c:when>
				<c:otherwise>
					<span class="btn btn-pagination btn-pagination-disabled">Próximo</span>
				</c:otherwise>
			</c:choose>
		</div>

		<div class="text-center mt-5">
			<a href="${pageContext.request.contextPath}/client/HomeAluno"
				class="btn btn-danger">Voltar</a>
		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
