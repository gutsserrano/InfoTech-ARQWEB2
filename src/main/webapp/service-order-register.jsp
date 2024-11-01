<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!doctype html>
<html lang="pt-BR">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link href="css/styles.css" rel="stylesheet">
<link href="css/errors.css" rel="stylesheet">
<title>InfoTech - Cadastro de Ordens de Serviço</title>
</head>
<body class="bg-dark text-white">
	<jsp:include page="navbar.jsp" />
	<div class="container">
		<div class="col-lg-6 offset-lg-3 col-sm-12">
			<c:if test="${result == 'customerNotFound'}">
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					CPF não encontrado. Tente novamente.
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
			</c:if>
			<c:if test="${result == 'notRegistered'}">
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					Falha ao cadastrar ordem de serviço. Por favor, tente novamente
					mais tarde.
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
			</c:if>

			<form action="serviceOrderRegister" method="post" id="serviceOrderForm">
			
				<c:choose>
					<c:when test="${serviceOrder == null}">
						<h1 class="text-center mt-3 mb-3">Cadastrar Ordem de serviço</h1>
					</c:when>
					<c:when test="${serviceOrder != null}">
						<h1 class="text-center mt-3 mb-3">Editar Ordem de serviço</h1>
					</c:when>
				</c:choose>
				
				<c:choose>
					<c:when test="${serviceOrder == null}">
						<input type="hidden" name="id" value="0">
					</c:when>
					<c:when test="${serviceOrder != null}">
						<input type="hidden" name="id" value="${serviceOrder.id}">
					</c:when>
				</c:choose>
				
				<C:choose>
					<C:when test="${fn:length(customers) > 0}">
						<div class="mb-2">
							<label for="cpfHidden">Selecionar cliente</label> 
							<select class="form-select" name="cpfHidden" id="cpfHidden" required="required">								
								<c:choose>
									<c:when test="${serviceOrder == null}">
										<option value="" selected>Selecione</option>
										<c:forEach var="customer" items="${customers}" varStatus="index">
											<option>${customer.name} - ${customer.cpf}</option>
										</c:forEach>
									</c:when>
									<c:when test="${serviceOrder != null}">
										<option value="${serviceOrder.customer.cpf}" selected>${serviceOrder.customer.name} - ${serviceOrder.customer.cpf}</option>
									</c:when>
								</c:choose>
							</select>
						</div>
					</C:when>
					
					<input type="hidden" id="cpf" name="cpf" />

					<div class="mb-2">
						<label for="description">Descrição*</label>
						<textarea name="description" id="description"
							class="form-control mb-3" required="required">${serviceOrder.description }</textarea>
						<span id="1"></span>
					</div>
					
					<div class="mb-2">
						<label for="price">Preço*</label> 
						<input type="number" step="0.01" name="price" id="price"
							class="form-control" required="required"
							value="">
					</div>
					
					<C:when test="${fn:length(paymentMethods) > 0}">
						<div class="mb-2">
							<label for="methodSelect">Selecionar Método de pagamento</label> 
							<select class="form-select" name="methodSelect" id="methodSelect" required="required">
								<c:choose>
									<c:when test="${serviceOrder == null}">
										<c:forEach var="method" items="${paymentMethods}" varStatus="index">
											<option>${method.id} - ${method.name}</option>
										</c:forEach>
									</c:when>
									<c:when test="${serviceOrder != null}">
										<option selected>${serviceOrder.paymentMethod.id} - ${serviceOrder.paymentMethod.name}</option>
									</c:when>
								</c:choose>
							</select>
						</div>
					</C:when>
					
					<input type="hidden" id="paymentMethod" name="paymentMethod" />
					
					<div class="mb-2">
						<label for="observation">Observação*</label>
						<textarea name="observation" id="observation"
							class="form-control mb-3">${serviceOrder.observation }</textarea>
					</div>

					<div class="mb-2">
						<button type="submit" id="saveButton" class="btn btn-primary">Salvar</button>
					</div>
				</C:choose>
			</form>
		</div>
	</div>

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<script src="js/service-order-register.js"></script>
</body>
</html>
