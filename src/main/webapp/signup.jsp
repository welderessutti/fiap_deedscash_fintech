<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp"%>
<link rel="stylesheet" href="resources/css/signup-style.css">
</head>
<body>
	<main class="container p-5">
		<section class="d-flex mb-4 flex-column align-items-center">
			<a class="link-underline link-underline-opacity-0 mb-5 txt-my-blue-2 fw-bold fs-1 home-link" href="index.jsp">DeedsCash</a>

			<div class="d-flex justify-content-end align-items-end">
				<img class="profile-icon" src="resources/images/icons/profile.png"
					alt="Ícone de perfil"> <a class="d-block position-absolute"
					href="#"> <img class="plus-icon" src="resources/images/icons/plus.png"
					alt="Ícone de sinal de mais">
				</a>
			</div>
		</section>

		<form action="usuario" method="post">
			<c:choose>
				<c:when test="${not empty msg}">
					<div class="alert alert-success">${msg}</div>
				</c:when>
				<c:when test="${not empty erro}">
					<div class="alert alert-danger">${erro}</div>
				</c:when>
			</c:choose>
			<input type="hidden" name="acao" value="cadastrar">
			<div class="row mb-4">
				<div class="col">
					<label class="form-label m-0 txt-my-blue-3" for="id-nome">Nome</label>
					<input class="form-control border-my-blue-3" type="text"
						name="nome" id="id-nome" required>
				</div>

				<div class="col">
					<label class="form-label m-0 txt-my-blue-3" for="id_sobrenome">Sobrenome</label>
					<input class="form-control border-my-blue-3" type="text"
						name="sobrenome" id="id_sobrenome" required>
				</div>
			</div>

			<div class="row mb-4">
				<div class="col">
					<label class="form-label m-0 txt-my-blue-3" for="id-nascimento">Nascimento</label>
					<input class="form-control border-my-blue-3" type="date"
						name="nascimento" id="id-nascimento" required>
				</div>

				<div class="col">
					<label class="form-label m-0 txt-my-blue-3" for="id_genero">Gênero</label>
					<select class="form-select border-my-blue-3" name="genero"
						id="id_genero" required>
						<option selected disabled value="0">Selecione</option>
						<option value="Feminino">Feminino</option>
						<option value="Masculino">Masculino</option>
						<option value="Indefinido">Indefinido</option>
					</select>
				</div>
			</div>

			<div class="mb-4">
				<label class="form-label m-0 txt-my-blue-3" for="id-email">E-mail</label>
				<input class="form-control border-my-blue-3" type="email"
					name="email" id="id-email" required>
			</div>

			<div class="row mb-4">
				<div class="col">
					<label class="form-label m-0 txt-my-blue-3" for="id-senha">Senha</label>
					<input class="form-control border-my-blue-3" type="password"
						name="senha" id="id-senha" required minlength="8"
						maxlength="16">
				</div>

				<div class="col">
					<label class="form-label m-0 txt-my-blue-3"
						for="id-confirmar-senha">Confirmar senha</label> <input
						class="form-control border-my-blue-3" type="password"
						name="confirmar-senha" id="id-confirmar-senha" required
						minlength="8" maxlength="16">
				</div>
			</div>

			<div class="d-flex justify-content-center mb-3">
				<a class="txt-my-blue-3 terms-link" href="#">Termos de uso</a>
			</div>

			<div class="d-flex justify-content-center mb-4">
				<input class="form-check-input me-2" type="checkbox" name="termo"
					id="id-termo" required> <label
					class="form-check-label fw-medium agree-checkbox" for="id-termo">Eu
					li e concordo com os termos de uso</label>
			</div>

			<div class="d-flex justify-content-between">
				<input class="btn py-2 px-3 bg-my-grey-1 txt-my-blue-1 fw-semibold cancel-btn"
					type="reset" value="Cancelar">
				<input class="btn py-2 px-3 bg-my-blue-1 txt-my-blue-4 fw-semibold register-btn"
					type="submit" value="Cadastrar">
			</div>
		</form>
	</main>
	<%@ include file="footer.jsp"%>
</body>
</html>
