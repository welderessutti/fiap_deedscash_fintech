<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<%@ include file="header.jsp"%>
<link rel="stylesheet" href="resources/css/main-style.css">
</head>
<body class="bg-my-white-1">
	<c:set var="usuarioSessao" value="${sessionScope.usuarioSessao}"/>
	<c:set var="carteirasUsuario" value="${sessionScope.carteirasUsuario}"/>
	<c:set var="carteira" value="${carteirasUsuario[0]}"/>
	<c:set var="movimentacoesCarteiras" value="${sessionScope.movimentacoesCarteiras}"/>
	<c:set var="movimentacoes" value="${movimentacoesCarteiras[0]}"/>
	<div class="mobile-layout">
		<c:choose>
			<c:when test="${not empty msg}">
				<div class="alert alert-success">${msg}</div>
			</c:when>
			<c:when test="${not empty erro}">
				<div class="alert alert-danger">${erro}</div>
			</c:when>
		</c:choose>
		<header class="fixed-top bg-white p-4 rounded-bottom-4 shadow-sm">
			<section class="d-flex justify-content-between mb-3">
				<div class="d-flex align-items-center">
					<img class="me-2 profile-icon" src="resources/images/icons/profile.png"
						alt="Ícone de perfil">
					<p class="m-0 fw-medium">
						Olá, <br>${usuarioSessao.nomeUsuario}
					</p>
				</div>
				<div class="d-flex">
					<a href="#"> <img class="header-icons"
						src="resources/images/icons/settings.png" alt="Ícone de configurações">
					</a>
					<a class="ms-3" href="login"> <img class="header-icons"
						src="resources/images/icons/logout.png" alt="Ícone de sair">
					</a>
				</div>
			</section>
			<section
				class="d-flex justify-content-center align-items-center mb-3">
				<div class="invisible-div"></div>
				<div class="mx-2 text-center">
					<p class="m-0 lh-sm opacity-50">Seu saldo</p>
					<p class="m-0 lh-sm fs-1 fw-semibold">R$ ${carteira.valorSaldo}</p>
				</div>
				<button class="btn btn-link mt-3 p-0 border-0">
					<img class="hide-icon" src="resources/images/icons/hide.png"
						alt="Ícone para esconder valores">
				</button>
			</section>
			<section class="d-flex justify-content-evenly">
				<div class="container p-0 text-center">
					<p class="summary-size m-0 fw-medium">Despesas mês</p>
					<p class="m-0 fw-medium txt-my-red-1">R$ --</p>
				</div>
				<div class="container p-0 border-start border-end text-center">
					<p class="summary-size m-0 fw-medium">Investimentos</p>
					<p class="m-0 fw-medium txt-my-green-1">R$ --</p>
				</div>
				<div class="container p-0 text-center">
					<p class="summary-size m-0 fw-medium">Receitas mês</p>
					<p class="m-0 fw-medium txt-my-green-1">R$ --</p>
				</div>
			</section>
		</header>

		<main class="main-margin">
			<p class="m-0 opacity-50">Últimos lançamentos</p>
			<ul class="list-group bg-white px-3 shadow-sm">
				<c:if test="${not empty movimentacoes}">
					<c:forEach var="movimentacao" items="${movimentacoes}">
						<li class="expenses-border-bottom">
							<div class="d-flex justify-content-between py-3">
								<div class="d-flex align-items-center">
									<img class="me-3 expenses-icons"
										src="resources/images/icons/dollar.png" alt="Ícone de cifrão">
									<div>
										<p class="m-0 fw-medium lh-sm">${movimentacao.descricaoMovimentacao}</p>
										<p class="m-0 fw-medium lh-sm txt-my-green-1">+ R$ ${movimentacao.valorMovimentacao}</p>
									</div>
								</div>
								<div class="d-flex align-items-center">
									<p class="m-0 fw-light date-font-size">
										<fmt:formatDate value="${movimentacao.dataHoraMovimentacao}" pattern="dd/MM/yyyy"/>
									</p>
									<c:url value="movimentacao" var="link">
										<c:param name="acao" value="editarMovimentacao"/>
										<c:param name="idMovimentacao" value="${movimentacao.idMovimentacao}"/>
									</c:url>
									<a class="btn btn-link p-0 border-0" href="${link}">
										<img class="ms-4 plus-icon"
											src="resources/images/icons/add.png" alt="Ícone de editar">
									</a>
								</div>
							</div>
						</li>
					</c:forEach>
				</c:if>
			</ul>
		</main>

		<footer class="fixed-bottom">
			<nav class="navbar justify-content-evenly p-3 bg-white rounded-top-4">
				<c:url value="carteira" var="link">
					<c:param name="acao" value="cadastroMovimentacao"/>
				</c:url>
				<a href="${link}"> <img class="nav-icons"
					src="resources/images/icons/pie-chart.png"
					alt="Ícone de despesa financeira">
				</a> <a href="#"> <img class="nav-icons"
					src="resources/images/icons/target.png" alt="Ícone de objetivo financeiro">
				</a> <a href="#"> <img class="nav-icons"
					src="resources/images/icons/statistic.png" alt="Ícone de investimento">
				</a>
			</nav>
		</footer>
	</div>

	<div class="desktop-layout">
		<aside>
			<nav class="navbar flex-column justify-content-evenly p-3 bg-white rounded-end-4 nav-height">
				<c:url value="carteira" var="link">
					<c:param name="acao" value="cadastroMovimentacao"/>
				</c:url>
				<a href="${link}"> <img class="nav-icons"
					src="resources/images/icons/pie-chart.png"
					alt="Ícone de despesa financeira">
				</a> <a href="#"> <img class="nav-icons"
					src="resources/images/icons/target.png" alt="Ícone de objetivo financeiro">
				</a> <a href="#"> <img class="nav-icons"
					src="resources/images/icons/statistic.png" alt="Ícone de investimento">
				</a>
			</nav>
		</aside>

		<section class="container section-padding">
			<header
				class="d-flex justify-content-between align-items-center mb-4 p-4 bg-white rounded-4 shadow-sm">
				<div class="d-flex align-items-center">
					<img class="me-2 profile-icon" src="resources/images/icons/profile.png"
						alt="Ícone de perfil">
					<p class="m-0 fw-medium">
						Olá, <br>${usuarioSessao.nomeUsuario}
					</p>
				</div>

				<div class="d-flex">
					<a href="#"> <img class="header-icons"
						src="resources/images/icons/settings.png" alt="Ícone de configurações">
					</a>
					<a class="ms-3" href="login"> <img class="header-icons"
						src="resources/images/icons/logout.png" alt="Ícone de sair">
					</a>
				</div>
			</header>

			<main>
				<section class="d-flex justify-content-between mb-4">
					<div class="py-3 px-4 bg-white rounded-4 shadow-sm">
						<p class="m-0 opacity-50">Seu saldo</p>
						<div class="d-flex">
							<p class="m-0 me-2 fs-4 fw-semibold">R$ ${carteira.valorSaldo}</p>
							<button class="btn btn-link m-0 p-0 border-0">
								<img class="hide-icon" src="resources/images/icons/hide.png"
									alt="Ícone para esconder valores">
							</button>
						</div>
					</div>

					<div class="py-3 px-4 bg-white rounded-4 shadow-sm">
						<p class="m-0 opacity-50">Receitas mês</p>
						<p class="m-0 fs-4 fw-semibold txt-my-green-1">R$ --</p>
					</div>

					<div class="py-3 px-4 bg-white rounded-4 shadow-sm">
						<p class="m-0 opacity-50">Despesas mês</p>
						<p class="m-0 fs-4 fw-semibold txt-my-red-1">R$ --</p>
					</div>

					<div class="py-3 px-4 bg-white rounded-4 shadow-sm">
						<p class="m-0 opacity-50">Investimentos</p>
						<p class="m-0 fs-4 fw-semibold txt-my-green-1">R$ --</p>
					</div>
				</section>

				<section class="d-flex justify-content-between">
					<div class="me-4 chart-side">
						<p class="mb-2 opacity-50">Despesas por categoria</p>
						<div
							class="d-flex justify-content-center p-5 bg-white rounded-4 shadow-sm">
							<img class="expenses-chart" src="resources/images/icons/main-chart.png"
								alt="Gráfico de despesas">
						</div>
					</div>

					<div class="expenses-side">
						<p class="mb-2 opacity-50">Últimos lançamentos</p>
						<ul class="list-group rounded-4 bg-white px-3 shadow-sm">
							<c:if test="${not empty movimentacoes}">
								<c:forEach var="movimentacao" items="${movimentacoes}">
									<li class="expenses-border-bottom">
										<div class="d-flex justify-content-between py-3">
											<div class="d-flex align-items-center">
												<img class="me-3 expenses-icons"
													src="resources/images/icons/dollar.png"
													alt="Ícone de cifrão">
												<div>
													<p class="m-0 fw-medium lh-sm">${movimentacao.descricaoMovimentacao}</p>
													<p class="m-0 fw-medium lh-sm txt-my-green-1">+ R$ ${movimentacao.valorMovimentacao}</p>
												</div>
											</div>
											<div class="d-flex align-items-center">
												<p class="m-0 fw-light date-font-size">
													<fmt:formatDate value="${movimentacao.dataHoraMovimentacao}" pattern="dd/MM/yyyy"/>
												</p>
												<c:url value="movimentacao" var="link">
													<c:param name="acao" value="editarMovimentacao" />
													<c:param name="idMovimentacao" value="${movimentacao.idMovimentacao}"/>
												</c:url>
												<a class="btn btn-link p-0 border-0" href="${link}">
													<img class="ms-4 plus-icon"
														src="resources/images/icons/add.png"
														alt="Ícone de editar">
												</a>
											</div>
										</div>
									</li>
								</c:forEach>
							</c:if>
						</ul>
					</div>
				</section>
			</main>
		</section>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>
