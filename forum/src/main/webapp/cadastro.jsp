<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro</title>
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
	<form method="post" action="CadastroServlet" class="form-signin">
		<h2 class="form-signin-heading">Preencha os dados para Cadastrar</h2>
        <label for="login" class="sr-only">Login</label>
        <input type="text" id="login" name="login" class="form-control" placeholder="Login" required autofocus>
        <label for="nome" class="sr-only">Nome</label>
        <input type="text" id="nome" name="nome" class="form-control" placeholder="Nome" required autofocus>
        <label for="email" class="sr-only">Email</label>
        <input type="email" id="email" name=""email"" class="form-control" placeholder="E-Mail" required autofocus>
        <label for="senha" class="sr-only">Password</label>
        <input type="password" id="senha" name="senha" class="form-control" placeholder="Senha" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Cadastrar</button>
	</form>
</div>
</html>