<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/home.css">
<title>InfoTech - Página Principal</title>
</head>
<body class="bg-dark text-white">
	<jsp:include page="navbar.jsp" />
	<div class="container">
		<div class="center">
			<h1 class="text-center mt-3">Buscar Ordens Serviços</h1>
			<div class="d-flex justify-content-center">
				<form action="serviceOrderSearch" method="post" class="w-50">
					<div class="mb-2">
						<label for="cpf" class="form-label">CPF</label> <input type="text"
							name="cpf" id="cpf" class="form-control" minlength="11"
							maxlength="14" placeholder="ex: 12345678900">
					</div>
					<p class="text-left">Obs: deixe em branco para buscar todos os
						serviços</p>
					<div class="mt-4 mb-3 w-25">
						<button type="submit" class="btn btn-primary btn-sm">Buscar</button>
					</div>
				</form>
			</div>
			<c:choose>
				<c:when test="${fn:length(serviceOrders) > 0}">
					<table
						class="table table-dark table-bordered table-striped table-hover table-responsive">
						<thead>
							<tr>
								<th scope="col" class="p-3">#</th>
								<th scope="col" class="p-3">Serviço</th>
								<th scope="col" class="p-3">Cliente</th>
								<th scope="col" class="p-3">Preço</th>
								<th scope="col" class="p-3">Data de Emissão</th>
								<th scope="col" class="p-3">Data de Finalização</th>
								<th scope="col" class="p-3">Mais informações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="serviceOrder" items="${serviceOrders}"
								varStatus="index">
								<tr>
									<td class="p-3">${serviceOrder.id}</td>
									<td class="p-3">
									    <c:choose>
									        <c:when test="${fn:length(serviceOrder.description) > 45}">
									            <span class="short-description">
									                ${fn:substring(serviceOrder.description, 0, 45)}...
									            </span>
									            <a type="text/html" class="link-underline-light" data-bs-toggle="modal" data-bs-target="#completeDescriptionModal"
													data-bs-text="${serviceOrder.description}"> Ler Mais</a>
												</td>
									        </c:when>
									        <c:otherwise>
									            <span class="short-description">
									                ${serviceOrder.description}
									            </span>
									        </c:otherwise>
									    </c:choose>
									</td>
									<td class="p-3">
									<a type="text/html" class="link-underline-light" data-bs-toggle="modal" data-bs-target="#customerInfoModal"
										data-bs-id="${serviceOrder.id}" 
								       data-bs-name="${serviceOrder.name}" 
								       data-bs-cpf="${serviceOrder.cpf}" 
								       data-bs-phone="${serviceOrder.phone}" 
								       data-bs-email="${serviceOrder.email}">${serviceOrder.name}</a>
									</td>
									<td class="p-3">R$ ${serviceOrder.price}</td>
									<td class="p-3">${serviceOrder.issueDate}</td>
									<td class="p-3">${serviceOrder.endDate}</td>
									<td class="p-3"><span data-bs-toggle="tooltip"
										data-bs-placement="top" title="Infos"> <a type="button"
										class="btn" data-bs-toggle="modal" data-bs-target="#serviceOrderInfoModal"
										data-bs-status="${serviceOrder.status}"
										data-bs-observation="${serviceOrder.observation}"
										data-bs-paymentMethodName="${serviceOrder.paymentMethodName}"> 
								       <img src="img/info.svg" alt="Info">
										</a>
									</span></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<p class="text-center">Nenhuma ordem de serviço cadastrada.</p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="modal" tabindex="-1" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Exclusão</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>Tem certeza que deseja excluir a atividade?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancelar</button>
					<button type="button" id="delete" class="btn btn-danger">Excluir</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal" id="customerInfoModal">
		<div class="modal-dialog">
			<div class="modal-content bg-dark">
				<div class="modal-header">
					<h5 class="modal-title">Informações do cliente</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
					<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-bs-dismiss="modal">OK</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal" id="serviceOrderInfoModal">
		<div class="modal-dialog">
			<div class="modal-content bg-dark">
				<div class="modal-header">
					<h5 class="modal-title">Informações da Ordem de Serviço</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
					<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-bs-dismiss="modal">OK</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal" id="completeDescriptionModal">
		<div class="modal-dialog">
			<div class="modal-content bg-dark">
				<div class="modal-header">
					<h5 class="modal-title">Descrição</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
					<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-bs-dismiss="modal">OK</button>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<script type="text/javascript" src="js/home.js"></script>
</body>
</html>