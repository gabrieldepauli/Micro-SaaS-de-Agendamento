<%@page import="model.Teacher"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="pt-br">
<head>
	<meta charset="UTF-8" />
	<title>Portal do Estudante</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/assets/css/homeClient.css" rel="stylesheet">
	<link rel="icon" href="${pageContext.request.contextPath}/assets/images/logo.jpg" type="image/x-icon">
</head>
<body>

	<nav class="navbar bg-light" style="height: 80px;">
		<div class="container d-flex justify-content-center align-items-center" style="height: 100%;">
			<a class="navbar-brand" href="#" style="height: 100%; display: flex; align-items: center;">
				<img src="${pageContext.request.contextPath}/assets/images/logo_site.png"
				     alt="Logo do Site"
				     style="height: 60px; width: auto; display: block;">
			</a>
		</div>
	</nav>

	<div class="hero-banner text-center py-4 text-white mb-4" style="background: linear-gradient(90deg, #0d6efd, #6610f2); border-radius: 0 0 12px 12px; box-shadow: 0 4px 10px rgba(0,0,0,0.1);">
	    <h1 class="display-6 fw-bold">Bem-vindo ao Portal do Estudante</h1>
	    <p class="lead mb-0">Agende com facilidade com os melhores profissionais</p>
	</div>

	<div class="container mt-4">
		<div class="d-flex justify-content-center gap-3 mb-4">
			<a href="${pageContext.request.contextPath}/client/ListClientAppointment"
				class="btn btn-primary shadow-sm rounded-pill"> Meus Agendamentos </a>
		    <a href="${pageContext.request.contextPath}/logout"
				class="btn btn-danger shadow-sm rounded-pill"> Logout </a>
		</div>

		<hr />

		<form method="get" action="${pageContext.request.contextPath}/client/HomeAluno" class="row g-3 justify-content-center align-items-end mb-4">
		    <div class="col-auto">
		        <label for="city" class="form-label">Filtrar por cidade:</label>
		        <select name="city" id="city" class="form-select">
		            <option value="">Todas cidades</option>
		            <option value="Araraquara" ${selectedCity == 'Araraquara' ? 'selected' : ''}>Araraquara</option>
		            <option value="São Carlos" ${selectedCity == 'São Carlos' ? 'selected' : ''}>São Carlos</option>
		            <option value="Ribeirão Preto" ${selectedCity == 'Ribeirão Preto' ? 'selected' : ''}>Ribeirão Preto</option>
		        </select>
		    </div>
		    <div class="col-auto">
			    <label for="specialty" class="form-label">Filtrar por especialidade:</label>
			    <select name="specialty" id="specialty" class="form-select">
			        <option value="">Todas especialidades</option>
			        <c:forEach var="spec" items="${specialties}">
			            <option value="${spec}" ${selectedSpecialty == spec ? 'selected' : ''}>${spec}</option>
			        </c:forEach>
			    </select>
			</div>
		    <div class="col-auto">
		        <button type="submit" class="btn btn-primary">Filtrar</button>
		    </div>
		</form>

		<hr />

		<c:if test="${empty teachers}">
			<div class="alert alert-warning text-center" role="alert">
				Nenhum professor encontrado.
			</div>
		</c:if>

		<div class="row mt-3">
			<c:forEach var="t" items="${teachers}">
				<div class="col-md-4 mb-4">
					<div class="card shadow-sm" data-bs-toggle="modal"
						data-bs-target="#modal${t.id}" role="button" tabindex="0"
						aria-label="Abrir portfólio de ${t.businessName}">
						<c:choose>
							<c:when test="${not empty t.profilePicture}">
								<img src="/Micro_SaaS_Agendamento/${t.profilePicture}"
								     alt="Foto do prestador"
								     class="card-img-top rounded-top"
								     style="height: 220px; object-fit: cover; transition: transform 0.3s ease-in-out;">
							</c:when>
							<c:otherwise>
								<img src="https://via.placeholder.com/300x200?text=Sem+Foto"
								     alt="Foto padrão"
								     class="card-img-top rounded-top"
								     style="height: 220px; object-fit: cover; transition: transform 0.3s ease-in-out;">
							</c:otherwise>
						</c:choose>
						<div class="card-body">
							<h5 class="card-title">${t.businessName}</h5>
							<p class="card-text mb-1"><strong>Cidade:</strong> ${t.address}</p>
							<p class="card-text"><strong>Descrição:</strong> ${t.description}</p>
						</div>
					</div>
				</div>

				<div class="modal fade" id="modal${t.id}" tabindex="-1"
					aria-labelledby="modalLabel${t.id}" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered modal-lg">
						<div class="modal-content rounded-4 shadow">
							<div class="modal-header border-0">
								<h5 class="modal-title" id="modalLabel${t.id}">Portfólio do ${t.businessName}</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
							</div>
							<div class="modal-body">
								<c:choose>
									<c:when test="${not empty t.imagens}">
										<div id="carousel${t.id}" class="carousel slide" data-bs-ride="carousel">
											<div class="carousel-inner rounded-3">
												<c:forEach var="img" items="${t.imagens}" varStatus="status">
													<div class="carousel-item ${status.index == 0 ? 'active' : ''}">
														<img src="/Micro_SaaS_Agendamento/${img}" alt="Imagem do portfólio" class="d-block w-100" />
													</div>
												</c:forEach>
											</div>
											<button class="carousel-control-prev" type="button" data-bs-target="#carousel${t.id}" data-bs-slide="prev">
												<span class="carousel-control-prev-icon" aria-hidden="true"></span>
												<span class="visually-hidden">Anterior</span>
											</button>
											<button class="carousel-control-next" type="button" data-bs-target="#carousel${t.id}" data-bs-slide="next">
												<span class="carousel-control-next-icon" aria-hidden="true"></span>
												<span class="visually-hidden">Próximo</span>
											</button>
										</div>
									</c:when>
									<c:otherwise>
										<p class="text-muted fst-italic">Portfólio do professor vazio.</p>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="modal-footer border-0 d-flex justify-content-between">
								<form method="post" action="${pageContext.request.contextPath}/client/appointment.jsp" class="m-0">
									<input type="hidden" name="teacher_id" value="${t.id}" />
									<button type="submit" class="btn btn-success btn-lg px-4">Agendar</button>
								</form>
								<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Fechar</button>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

		<div class="d-flex justify-content-center gap-3 mt-4">
			<c:choose>
				<c:when test="${page > 0}">
					<a class="btn btn-outline-secondary btn-pagination"
						href="${pageContext.request.contextPath}/client/HomeAluno?page=${page - 1}&cidade=${selectedCity}">Anterior</a>
				</c:when>
				<c:otherwise>
					<span class="btn btn-pagination btn-pagination-disabled me-2">Anterior</span>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${page < totalPages - 1}">
					<a class="btn btn-outline-secondary btn-pagination"
						href="${pageContext.request.contextPath}/client/HomeAluno?page=${page + 1}&cidade=${selectedCity}">Próximo</a>
				</c:when>
				<c:otherwise>
					<span class="btn btn-pagination btn-pagination-disabled me-2">Próximo</span>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>