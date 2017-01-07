<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tópico</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h2>${topico.titulo}</h2>
		</div>
		<div class="row">
			<div class="col-md-12"><h3>Conteúdo do Tópico</h3></div>
		</div>
		<div class="row">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Nome do Usuário</th>
						<th>Texto</th>
					</tr>
				</thead>
				
				<tbody>
					<tr>
						<td><c:out value="${topico.usuario.nome}"/></a></td>
						<td><c:out value="${topico.conteudo}"/></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row">
			<div class="col-md-12"><h4>Comentários</h4></div>
		</div>
		<div class="row">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Nome do Usuário</th>
						<th>Comentário</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${topico.comentarios}" var="comentario">
						<tr>
							<td><c:out value="${comentario.usuario.nome}"/></a></td>
							<td><c:out value="${comentario.textoComentario}"/></td>
						</tr>
					</c:forEach>
				</tbody>
				
			</table>
		</div>
		<div class="row">
			<form method="post" action="ExibeTopicoServlet">
				
				<input type="hidden" name="idTopico" value="${topico.id}">
				<input type="hidden" name="novoComentario" value="SIM">
				
				<div class="form-groupd">
    				<label for="textoComentario">Novo Comentário</label>
					<textarea class="form-control" rows="5" maxlength="100" name="textoComentario" required></textarea>
				</div> 
		        <button class="btn btn-md btn-primary active" type="submit" >Adicionar Comentário</button>
			
			</form>
		</div>
		<div class="row">
			<a href="TopicoServlet" class="btn btn-default active" role="button">Visualizar Tópicos</a>
		</div>
		
	</div>
</body>
</html>