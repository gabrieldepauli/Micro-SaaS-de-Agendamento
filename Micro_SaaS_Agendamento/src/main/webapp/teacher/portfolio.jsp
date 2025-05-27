<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%
List<String> images = (List<String>) request.getAttribute("images");
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
	<meta charset="UTF-8">
	<title>Meu Portfólio</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/assets/css/portfolio.css" rel="stylesheet">
	<link rel="icon" href="${pageContext.request.contextPath}/assets/images/logo.jpg" type="image/x-icon">
</head>
<body>

	<div class="container card-portfolio shadow-lg">
		<div class="text-center mb-4">
			<h2>Meu Portfólio</h2>
			<hr class="my-4">
		</div>

		<form action="${pageContext.request.contextPath}/teacher/portfolio"
			method="post" enctype="multipart/form-data" class="mb-4">
			<div class="mb-3">
				<label for="image" class="form-label">Adicionar nova imagem:</label>
				<input type="file" name="image" id="image"
					class="form-control-file form-control" required>
			</div>
			<div class="d-flex gap-3">
				<button type="submit" class="btn btn-primary px-4">Enviar</button>
				<a href="${pageContext.request.contextPath}/teacher/homeTeacher.jsp"
					class="btn btn-danger px-4">Voltar para o Menu</a>
			</div>
		</form>

		<div class="gallery">
			<%
			if (images != null && !images.isEmpty()) {
				for (String img : images) {
			%>
			<img src="<%=request.getContextPath() + "/" + img%>"
				alt="Imagem do portfólio">
			<%
			}
			} else {
			%>
			<p class="text-center w-100">Nenhuma imagem cadastrada no
				portfólio.</p>
			<%
			}
			%>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>