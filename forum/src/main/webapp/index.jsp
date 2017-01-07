<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Login</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-sacle=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container">
	<style>
		body {
		  padding-top: 40px;
		  padding-bottom: 40px;
		}
	</style>
	<form method="post" action="LoginServlet" class="form-signin">
		<c:if test="${not empty erro}"><div class="alert alert-danger" role="alert">${erro}</div></c:if>
		<c:if test="${not empty sucesso}"><div class="alert alert-success" role="alert">${sucesso}</div></c:if>
		<h2 class="form-signin-heading">Preencha abaixo:</h2>
        <label for="login" class="sr-only">Email</label>
        <input type="text" id="login" name="login" class="form-control" placeholder="Login" required autofocus>
        <label for="senha" class="sr-only">Password</label>
        <input type="password" id="senha" name="senha" class="form-control" placeholder="Senha" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
		<a href="cadastro.jsp" >Clique aqui para novo usuário</a>
	</form>
</div>
</body>
</html>
