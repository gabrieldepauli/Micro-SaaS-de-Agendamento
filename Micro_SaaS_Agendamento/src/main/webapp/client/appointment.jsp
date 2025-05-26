<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.*"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
int teacher_id = Integer.parseInt(request.getParameter("teacher_id"));
request.setAttribute("professor_id", teacher_id);

List<String> availableTimes = (List<String>) request.getAttribute("availableTimes");
String selectedDate = (String) request.getAttribute("selectedDate");

String formattedDate = "";
if (selectedDate != null) {
	try {
		LocalDate date = LocalDate.parse(selectedDate);
		formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	} catch (Exception e) {
		formattedDate = selectedDate;
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agendar horário</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap"
	rel="stylesheet">
<style>
body {
	background-color: #f2f6fc;
	font-family: 'Roboto', sans-serif;
}

.card-center {
	max-width: 600px;
	margin: 50px auto;
	background-color: #ffffff;
	padding: 30px;
	border-radius: 15px;
	box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.1);
}

h2, h4 {
	text-align: center;
	color: #2c3e50;
}

.btn-primary {
	background-color: #2980b9;
	border-color: #2980b9;
}

.btn-success {
	background-color: #27ae60;
	border-color: #27ae60;
}

.btn-danger {
	width: 100%;
}

hr {
	margin: 30px 0;
}
</style>
</head>
<body>

	<nav class="navbar bg-light" style="height: 80px;">
		<div
			class="container d-flex justify-content-center align-items-center"
			style="height: 100%;">
			<a class="navbar-brand" href="#"
				style="height: 100%; display: flex; align-items: center;"> <img
				src="${pageContext.request.contextPath}/assets/images/logo_site.png"
				alt="Logo do Site"
				style="height: 60px; width: auto; display: block;">
			</a>
		</div>
	</nav>

	<div class="card-center">
		<h2 class="mb-4">Agendar Horário</h2>

		<!-- MENSAGEM CONDICIONAL -->
		<c:if test="${not empty sessionScope.mensagem}">
			<div class="alert alert-info text-center">${sessionScope.mensagem}</div>
			<c:remove var="mensagem" scope="session"/>
		</c:if>

		<form method="get" action="/Micro_SaaS_Agendamento/ShowAvailability"
			class="mb-4">
			<input type="hidden" name="teacher_id" value="<%=teacher_id%>" />

			<div class="mb-3">
				<label for="data_agendamento" class="form-label">Escolha um dia:</label>
				<%
				String today = LocalDate.now().toString(); // yyyy-MM-dd
				%>

				<input type="date" name="data_agendamento" class="form-control"
					min="<%=today%>" required />
			</div>

			<button type="submit" class="btn btn-primary w-100">Ver
				Horários Disponíveis</button>
		</form>

		<%
		if (availableTimes != null && !availableTimes.isEmpty()) {
		%>

		<h4 class="mb-3">Horários disponíveis para <%=formattedDate%>:</h4>

		<form method="post" action="/Micro_SaaS_Agendamento/RegisterAppointment" class="mb-4">
			<input type="hidden" name="teacher_id" value="<%=teacher_id%>" />
			<input type="hidden" name="data_agendamento" value="<%=selectedDate%>" />

			<div class="mb-3">
				<label for="hora_agendamento" class="form-label">Selecione um horário:</label>
				<select name="appointment_time" class="form-select" required>
					<%
					for (String hora : availableTimes) {
					%>
					<option value="<%=hora%>"><%=hora%></option>
					<%
					}
					%>
				</select>
			</div>

			<button type="submit" class="btn btn-success w-100">Realizar Agendamento</button>
		</form>

		<%
		} else if (selectedDate != null) {
		%>
		<div class="alert alert-warning text-center">Não existe horário disponível para o dia selecionado.</div>
		<%
		}
		%>

		<hr />
		<a href="${pageContext.request.contextPath}/HomeAluno" class="btn btn-danger">Voltar</a>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
