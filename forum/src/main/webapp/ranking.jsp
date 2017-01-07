<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ranking</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h2>Ranking de Usuários</h2>
		</div>
		<div class="row">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Colocação</th>
						<th>Nome do Usuário</th>
						<th>Login</th>
						<th>Quantidade de Pontos</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${ranking}" var="usuario" varStatus="loop">
						<tr>
							<td><c:out value="${loop.count}"/></td>
							<td><c:out value="${usuario.nome}"/></td>
							<td><c:out value="${usuario.login}"/></td>
							<td><c:out value="${usuario.pontos}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row">
			<a href="TopicoServlet" class="btn btn-default active">Visualizar Tópicos</a>
		</div>
	</div>
</body>
</html>