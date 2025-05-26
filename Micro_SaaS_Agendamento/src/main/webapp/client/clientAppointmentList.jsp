<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<html>
<head>
<title>Lista de agendamentos</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap"
	rel="stylesheet">
<style>
body {
	font-family: 'Roboto', sans-serif;
	background: #f8f9fa;
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	padding: 20px;
}

.container {
	background: #ffffff;
	max-width: 900px;
	width: 100%;
	padding: 40px;
	border-radius: 20px;
	box-shadow: 0 0 25px rgba(0, 0, 0, 0.1);
}

h2 {
	text-align: center;
	font-weight: 600;
}

.form-label {
	font-weight: 500;
}

/* Centralizar cabeçalhos e células da tabela */
table th, table td {
	vertical-align: middle;
	text-align: center;
}

/* Cabeçalho com fundo escuro e texto branco */
.table thead {
	background-color: #343a40;
	color: white;
}

/* Hover na linha da tabela */
.table tbody tr:hover {
	background-color: #f1f1f1;
}

/* Aumentar largura do select para aproximar do botão */
.form-select {
	width: 100%;
	height: 42px;
}

/* Ajustar layout do filtro para aproximar select do botão */
.form-row-filter {
	display: flex;
	gap: 10px;
	align-items: flex-end;
	margin-bottom: 1.5rem;
}

/* Botões anterior e próximo em azul */
.btn-pagination {
	min-width: 100px;
}

.btn-pagination-anterior, .btn-pagination-proximo {
	background-color: #0d6efd; /* Azul Bootstrap */
	color: white;
	border: none;
}

.btn-pagination-anterior:hover, .btn-pagination-proximo:hover {
	background-color: #0b5ed7;
	color: white;
}

.btn-pagination-disabled {
	background-color: #6c757d;
	color: white;
	border: none;
	cursor: not-allowed;
}
</style>
</head>
<body>
	<div class="container">
		<h2 class="mb-5">Meus agendamentos</h2>

		<form method="get"
			action="${pageContext.request.contextPath}/ListClientAppointment"
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
							<td>${a.date}</td>
							<td>${a.time}</td>
							<td>${a.status}</td>
						</tr>
					</c:forEach>
					<c:if test="${empty sessionScope.appointment}">
						<tr>
							<td colspan="4" class="text-center text-muted">Nenhum
								agendamento encontrado.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>

		<div class="text-center mt-4">
			<c:choose>
				<c:when test="${page > 0}">
					<a class="btn btn-pagination btn-pagination-anterior me-2"
						href="${pageContext.request.contextPath}/ListClientAppointment?page=${page - 1}&status=${param.status}">Anterior</a>
				</c:when>
				<c:otherwise>
					<span class="btn btn-pagination btn-pagination-disabled me-2">Anterior</span>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${page < totalPages - 1}">
					<a class="btn btn-pagination btn-pagination-proximo"
						href="${pageContext.request.contextPath}/ListClientAppointment?page=${page + 1}&status=${param.status}">Próximo</a>
				</c:when>
				<c:otherwise>
					<span class="btn btn-pagination btn-pagination-disabled">Próximo</span>
				</c:otherwise>
			</c:choose>

		</div>

		<div class="text-center mt-5">
			<a href="${pageContext.request.contextPath}/HomeAluno"
				class="btn btn-danger">Voltar</a>
		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
