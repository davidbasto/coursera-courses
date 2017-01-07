<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tópicos do Usuário</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<div class="container">		
		<c:if test="${not empty tituloTopicoInserido}"><div class="alert alert-success" role="alert">Tópico "${tituloTopicoInserido}" incluído com Sucesso </div></c:if>
		<div class="page-header">
			<h2>Lista de Tópicos</h2>
		</div>
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>Título</th>
					<th>Nome do Usuário</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${topicos}" var="topico">
					<tr>
						<td><a href="ExibeTopicoServlet?idTopico=${topico.id}"><c:out value="${topico.titulo}"/></a></td>
						<td><c:out value="${topico.usuario.nome}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="insereTopico.jsp" class="btn btn-primary active" role="button">Inserir Novo Tópico</a>
		<a href="RankingServlet" class="btn btn-default active" role="button">Ranking</a>
	</div>
</body>
</html>