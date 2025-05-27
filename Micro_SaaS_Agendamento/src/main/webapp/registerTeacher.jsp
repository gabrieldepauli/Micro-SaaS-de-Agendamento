<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Cadastro de Professor</title>
	<link rel="icon" href="assets/images/logo.jpg" type="image/x-icon">
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/assets/css/registerTeacher.css" rel="stylesheet">
	<link rel="icon" href="${pageContext.request.contextPath}/assets/images/logo.jpg" type="image/x-icon">
</head>
<body>

	<div class="container">

		<div class="logo-container">
			<img
				src="${pageContext.request.contextPath}/assets/images/logo_site.png"
				alt="Logo EnsinaFácil" />
		</div>

		<h2 class="text-center">Cadastro de Professor</h2>

		<form action="TeacherController" method="POST"
			enctype="multipart/form-data" novalidate>
			<input type="hidden" name="email" value="${param.email}" /> <input
				type="hidden" name="senha" value="${param.senha}" /> <input
				type="hidden" name="tipo" value="${param.tipo}" />

			<div class="mb-3">
				<label for="businessName" class="form-label">Nome Fantasia</label> <input
					type="text" class="form-control" id="businessName"
					name="businessName" required />
			</div>

			<div class="mb-3">
				<label for="fullName" class="form-label">Nome Completo</label> <input
					type="text" class="form-control" id="fullName" name="fullName"
					required />
			</div>

			<div class="mb-3">
				<label for="profilePicture" class="form-label">Foto de
					Perfil / Logo (.jpg ou .jpeg)</label> <input type="file"
					class="form-control" id="profilePicture" name="profilePicture"
					accept="image/jpeg,image/jpg" required />
			</div>

			<div class="mb-3">
				<label for="specialty" class="form-label">Especialidade</label> <input
					type="text" class="form-control" id="specialty" name="specialty" />
			</div>

			<div class="mb-3">
				<label for="address" class="form-label">Cidade:</label> <select
					class="form-select" id="address" name="address" required>
					<option value="" disabled selected>Selecione a cidade</option>
					<option value="Araraquara">Araraquara</option>
					<option value="São Carlos">São Carlos</option>
					<option value="Ribeirão Preto">Ribeirão Preto</option>
				</select>
			</div>

			<div class="mb-3">
				<label for="descricao" class="form-label">Descrição</label>
				<textarea class="form-control" id="descricao" name="descricao"
					rows="4" required></textarea>
			</div>

			<div class="btn-group">
				<button type="submit" class="btn btn-primary">Cadastrar</button>
				<a href="javascript:history.back()"
					class="btn btn-danger text-center">Voltar</a>
			</div>
		</form>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
