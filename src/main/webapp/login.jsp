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
	<jsp:include page="loginNavbar.jsp" />
	<div class="container">
		<div class="col-lg-4 offset-lg-4 col-sm-12">
			<c:choose>
				<c:when test="${result == 'registered'}">
					<div class="alert alert-success alert-dismissible fade show"
						role="alert">
						Usuário cadastrado com sucesso. Faça o login.
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:when>
				<c:when test="${result == 'loginError'}">
					<div class="alert alert-danger alert-dismissible fade show"
						role="alert">
						E-mail e/ou senha inválidos.
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:when>
			</c:choose>

			<form action="login" method="post">
				<h1 class="text-center">Login</h1>

				<div class="mb-2">
					<label for="email">E-mail</label> <input type="email" name="email"
						id="email" class="form-control mb-3" required="required"> <span
						id="1"></span>
				</div>

				<div class="mb-2">
					<label for="email">Senha</label> <input type="password" name="password"
						id="password" class="form-control mb-3" required="required"> <span
						id="2"></span>
				</div>

				<div class="mb-3">
					<button type="submit" class="btn btn-primary">Login</button>
				</div>

				<div class="mb-3">
					<a href="customer-register.jsp" class="btn btn-secondary">Cadastrar</a>
				</div>

			</form>
		</div>
	</div>
	

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>