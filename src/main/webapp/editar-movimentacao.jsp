<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<%@ include file="header.jsp"%>
</head>
<body>
<div class="container">
	<h1>Edição de Movimentacao</h1>
	<form action="movimentacao" method="post">
		<input type="hidden" value="editar" name="acao">
		<input type="hidden" value="${movimentacao.idMovimentacao}" name="idMovimentacao">
        <input type="hidden" value="${movimentacao.carteira.idCarteira}" name="idCarteira">
		<div class="form-group">
			<label for="id-tipo">Tipo Movimentação</label>
			<input type="text" name="tipoMovimentacao" id="id-tipo" class="form-control" value="${movimentacao.tipoMovimentacao}" >
		</div>
		<div class="form-group">
			<label for="id-data-hora">Data Hora</label>
			<input type="date" name="dataHoraMovimentacao" id="id-data-hora" class="form-control" value="${movimentacao.dataHoraMovimentacao}">
		</div>
		<div class="form-group">
			<label for="id-valor">Valor</label>
			<input type="text" name="valorMovimentacao" id="id-valor" class="form-control" value="${movimentacao.valorMovimentacao}">
		</div>
		<div class="form-group">
			<label for="id-descricao">Descrição</label>
			<input type="text" name="descricaoMovimentacao" id="id-descricao" class="form-control" value="${movimentacao.descricaoMovimentacao}">
		</div>
		<input type="submit" value="Salvar" class="btn btn-primary">
		<a href="main.jsp" class="btn btn-danger">Cancelar</a>
	</form>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>
