<nav class="navbar navbar-expand-lg navbar-light bg-primary">
	<div class="container-fluid">
		<a class="navbar-brand fw-bold" href="serviceOrderSearch">INFO TECH</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item dropdown">
		          <a class="nav-link dropdown-toggle fw-bold" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
		            Ordens de Serviço
		          </a>
		          <ul class="dropdown-menu bg-dark">
		            <li>
		            	<form action="customerSearch" method="post">
							<button type="submit" class="btn dropdown-item">
								<span class="text-primary">Nova Ordem de Serviço</span>
							</button>
						</form>
					</li>
		            <li><a class="btn dropdown-item" href="payment-method-register.jsp">
		            		<span class="text-primary">Cadastrar Método de Pagamento</span>
		            	</a>
	            	</li>
		          </ul>	
		        </li>
		       
			</ul>
			<span class="navbar-text">
		        <a class="btn" href="logout">
        			<img src="img/logout.svg" style="width: 30px; height: 30px;" alt="Sair">
    			</a>
	      	</span>
		</div>
	</div>
</nav>