<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<title>Cadastro de Cliente</title>
	<link rel="icon" href="${pageContext.request.contextPath}/assets/images/logo.jpg" type="image/x-icon">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/assets/css/registerClient.css" rel="stylesheet">
	<link rel="icon" href="${pageContext.request.contextPath}/assets/images/logo.jpg" type="image/x-icon">
</head>
<body>

<div class="container">

  <div class="logo-container">
    <img src="${pageContext.request.contextPath}/assets/images/logo_site.png" alt="Logo EnsinaFácil" />
  </div>

  <h2 class="text-center">Cadastro de Estudante</h2>

  <form action="ClientController" method="POST" novalidate>
    <input type="hidden" name="email" value="${param.email}" />
    <input type="hidden" name="senha" value="${param.senha}" />
    <input type="hidden" name="tipo" value="${param.tipo}" />

    <div class="mb-3">
      <label for="full_name" class="form-label">Nome:</label>
      <input type="text" class="form-control" id="full_name" name="full_name" required />
    </div>

    <div class="mb-3">
      <label for="cpf" class="form-label">CPF:</label>
      <input type="text" class="form-control" id="cpf" name="cpf" required />
    </div>

    <div class="mb-3">
      <label for="address" class="form-label">Cidade:</label>
      <select class="form-select" id="address" name="address" required>
        <option value="" disabled selected>Selecione a cidade</option>
        <option value="Araraquara">Araraquara</option>
        <option value="São Carlos">São Carlos</option>
        <option value="Ribeirão Preto">Ribeirão Preto</option>
      </select>
    </div>

    <div class="mb-3">
      <label for="phone" class="form-label">Número de Telefone:</label>
      <input
        type="tel"
        class="form-control"
        id="phone"
        name="phone"
        pattern="^\(\d{2}\) \d{5}-\d{4}$"
        placeholder="(XX) XXXXX-XXXX"
        required
      />
      <div class="form-text">Informe no formato (XX) XXXXX-XXXX.</div>
    </div>

    <div class="btn-group">
      <button type="submit" class="btn btn-primary">Cadastrar</button>
      <a href="javascript:history.back()" class="btn btn-danger text-center">Voltar</a>
    </div>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
