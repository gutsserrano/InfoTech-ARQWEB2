<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
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
<title>InfoTech - Página de Cadastro de Cliente</title>
</head>
<body class="bg-dark text-white">
	<jsp:include page="navbar.jsp" />
	<div class="container">
		<div class="col-lg-6 offset-lg-3 col-sm-12">
			<c:if test="${result == 'notRegistered'}">
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					CPF já cadastrado. Tente novamente.
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
			</c:if>
			
			<form action="customerRegister" method="post" id="form1">
				<h1 class="text-center mt-3 mb-3">Cadastre-se</h1>

				<div class="mb-2">
					<label for="name">Nome completo*</label> <input type="text"
						name="name" id="name" class="form-control mb-3" minlength="3"
						maxlength="50" required="required"> <span id="0"></span>
				</div>
				
				<div class="mb-2">
					<label for="cpf">CPF*</label> <input type="text" name="cpf"
						id="cpf" class="form-control mb-3" minlength="11"
						maxlength="14" required="required"> <span
						id="3"></span>
				</div>

				<div class="mb-2">
					<label for="email">E-mail*</label> <input type="email" name="email"
						id="email" class="form-control mb-3" required="required"> <span
						id="1"></span>
				</div>

				<div class="mb-2">
					<label for="phone">Celular com DDD*</label> <input type="text" name="phone"
						id="phone" class="form-control mb-3" minlength="11"
						maxlength="15" required="required"> <span
						id="2"></span>
				</div>

				<div class="mb-2">
					<button type="submit" class="btn btn-primary">Salvar</button>
				</div>
			</form>
		</div>
	</div>

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<script src="js/user-register.js"></script>
</body>
</html>
