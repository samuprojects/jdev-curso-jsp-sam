<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	
	
	<title>Curso JSP</title>
	
<style type="text/css">

form{
 position: absolute;
 top: 40%;
 left: 33%;
 right: 33%;
}

h5{
 position: absolute;
 top: 30%;
 left: 33%;
 right: 33%;
}

.msg{
 position: absolute;
 top: 56%;
 left: 33%;
 right: 33%;
 font-size: 15px;
 color: red;
}
</style>
	
</head>
<body>

<h5>Bem vindo ao curso de JSP</h5>


<form action="ServletLogin" method="post" class="row g-3">

<input type="hidden" value="<%= request.getParameter("url") %>" name="url">

<div class="col-md-6">
	<label class="form-label">Login</label>
	<input class="form-control" name="login" type="text">
</div>

<div class="col-md-6">
	<label class="form-label">Senha</label>
	<input class="form-control" name="senha" type="password">
</div>		
		
<input type="submit" value="Acessar" class="btn btn-primary">
			

	</form>
	
	<h5 class="msg">${msg}</h5>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>