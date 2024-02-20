<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<%@ include file="header.jsp"%>
<link rel="stylesheet" href="resources/css/index-style.css">
</head>
<body class="bg-my-blue-2">
	<main class="container p-5">
		<section class="vector-side">
			<img class="img-fluid vector" src="resources/images/vectors/login.jpg"
				alt="Vector-image">
		</section>

		<section class="login-side">
			<h1 class="mb-5 txt-my-blue-4 fw-bold font-size-logo text-center">DeedsCash</h1>
			<form action="login" method="post">
				<c:if test="${not empty erro}">
					<div class="alert alert-danger">${erro}</div>
				</c:if>
				<div class="mb-2">
					<label class="form-label txt-my-grey-1 fw-light" for="id-email">E-mail</label>
					<input class="form-control bg-input" type="email" name="email"
						id="id-email" required placeholder="seu@email.com.br">
				</div>

				<div class="mb-4">
					<label class="form-label txt-my-grey-1 fw-light" for="id-senha">Senha</label>
					<input class="form-control bg-input" type="password"
						name="senha" id="id-senha" required minlength="8"
						maxlength="16" placeholder="********">
				</div>

				<div class="d-grid">
					<input class="btn btn-lg mb-5 bg-my-blue-4 txt-my-blue-2 fs-3 fw-semibold rounded-pill sign-in"
						type="submit" value="Entrar">
				</div>
			</form>

			<section class="d-flex mb-5 flex-column align-items-center">
				<h2 class="mb-3 txt-my-grey-1 fs-6 fw-light">Entrar com</h2>

				<div class="d-flex column-gap-3">
					<a href="#"> <img class="social-media"
						src="resources/images/icons/facebook.png" alt="Logo do Facebook">
					</a> <a href="#"> <img class="social-media"
						src="resources/images/icons/google.png" alt="Logo do Google">
					</a> <a href="#"> <img class="social-media"
						src="resources/images/icons/apple.png" alt="Logo da Apple">
					</a>
				</div>
			</section>

			<section class="d-flex pt-5 justify-content-between">
				<a class="txt-my-blue-4 footer-links" href="signup.jsp">Cadastre-se</a>
				<a class="txt-my-blue-4 footer-links" href="#">Recuperar senha</a>
			</section>
		</section>
	</main>
	<%@ include file="footer.jsp"%>
</body>
</html>
