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
		<div class="row">
			<div class="col-md-12"><h3>Novo Tópico</h3></div>
		</div>
		<div class="row">
			<form method="post" action="InsereTopicoServlet">
				
				<div class="form-group">
    				<label for="titulo">Título</label>
    				<input class="form-control" type="text" name="titulo" maxlength="100" >
				</div> 
				<div class="form-group">
    				<label for="conteudo">Conteúdo</label>
					<textarea class="form-control" maxlength="500" name="conteudo" required></textarea>
				</div> 
		        <button class="btn btn-md btn-primary active" type="submit" >Adicionar Tópico</button>
			
			</form>
		</div>
		
	</div>
</body>
</html>