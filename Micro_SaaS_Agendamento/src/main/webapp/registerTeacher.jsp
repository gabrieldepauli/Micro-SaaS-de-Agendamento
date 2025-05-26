<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cadastro de Professor</title>
<link rel="icon" href="assets/images/logo.jpg" type="image/x-icon">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">

<style>
body {
	background-color: #f8f9fa;
	padding: 20px;
}

.container {
	background-color: #fff;
	padding: 30px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.form-group {
	margin-bottom: 1.5rem;
}

.btn-primary {
	background-color: #007bff;
	border-color: #007bff;
}

.btn-primary:hover {
	background-color: #0056b3;
	border-color: #0056b3;
}

.btn-danger {
	background-color: #dc3545;
	border-color: #dc3545;
}

.btn-danger:hover {
	background-color: #c82333;
	border-color: #bd2130;
}
</style>
</head>
<body>

	<div class="container">
		<h2 class="text-center mb-4">Cadastro de Professor</h2>

		<form action="TeacherController" method="POST"
			enctype="multipart/form-data">
			<div class="form-group">
				<label for="businessName">Nome Fantasia</label> <input type="text"
					class="form-control" id="businessName" name="businessName" required>
			</div>

			<div class="form-group">
				<label for="fullName">Nome Completo</label> <input type="text"
					class="form-control" id="fullName" name="fullName" required>
			</div>

			<div class="form-group">
				<label for="profilePicture">Foto de Perfil / Logo (.jpg ou
					.jpeg))</label> <input type="file" class="form-control-file"
					id="profilePicture" name="profilePicture" accept="image/*" required>
			</div>

			<div class="form-group">
				<label for="specialty">Especialidade</label> <input type="text"
					class="form-control" id="specialty" name="specialty">
			</div>

			<div class="form-group">
				<label for="address">Cidade:</label> <select class="form-control"
					id="address" name="address" required>
					<option value="">Selecione a cidade</option>
					<option value="Araraquara">Araraquara</option>
					<option value="São Carlos">São Carlos</option>
					<option value="Ribeirão Preto">Ribeirão Preto</option>
				</select>
			</div>

			<div class="form-group">
				<label for="descricao">Descrição</label>
				<textarea class="form-control" id="descricao" name="descricao"
					rows="4" required></textarea>
			</div>

			<div
				class="d-flex flex-column justify-content-center align-items-center">
				<button type="submit" class="btn btn-primary mb-3"
					style="width: 200px;">Cadastrar</button>
				<a href="javascript:history.back()" class="btn btn-danger"
					style="width: 200px;">Voltar</a>
			</div>
		</form>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>
