<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<%@ include file="header.jsp"%>
</head>
<body>
<div class="container">
	<h1>Cadastro de Movimentação</h1>
	<form action="movimentacao" method="post">
		<input type="hidden" value="cadastrarMovimentacao" name="acao">
		<input type="hidden" value="${carteira.idCarteira}" name="idCarteira">
		<input type="hidden" value="${carteira.valorSaldo}" name="valorSaldo">
		<div class="form-group">
			<label for="id-tipo">Tipo Movimentação</label>
			<select class="form-select border-my-blue-3" name="tipoMovimentacao"
					id="id-tipo" required>
					<option selected disabled value="0">Selecione</option>
					<option value="Despesa">Despesa</option>
					<option value="Receita">Receita</option>
			</select>
			</div>
		<div class="form-group">
			<label for="id-data-hora">Data Hora</label>
			<input type="date" name="dataHoraMovimentacao" id="id-data-hora" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-valor">Valor</label>
			<input type="text" name="valorMovimentacao" id="id-valor" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-descricao">Descrição</label>
			<input type="text" name="descricaoMovimentacao" id="id-descricao" class="form-control">
		</div>
		<input type="submit" value="Salvar" class="btn btn-primary">
		<a href="main.jsp" class="btn btn-danger">Cancelar</a>
	</form>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>
