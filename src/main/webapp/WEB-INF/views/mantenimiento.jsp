<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mantenimiento de peliculas</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

</head>
<body style="background-color: #B7FDF7;">
	<nav class="navbar navbar-light bg-light"
		style="background-color: #E7F5F4;"> <a class="navbar-brand"
		href="/index"> <img
		src="https://img2.freepng.es/20180404/gww/kisspng-cinema-clapperboard-film-computer-icons-clip-art-cine-5ac51166bd41a5.7114468315228644867752.jpg"
		width="45" height="20" alt="filmografia" style="padding-left: 10px">
		Filmografía
	</a> </nav>
	<br>

	<h2 align="center">Peliculas</h2>
	<br>

	<div align="center">
		<table class="table table-striped" style="text-align: center;">
			<tr>
				<td width=200px><b>Director</b></td>
				<td width=200px><b>Titulo</b></td>
				<td width=200px><b>Fecha</b></td>
				<td width=200px><b>URL Caratula</b></td>
				<td width=200px><b>Modificar</b></td>
				<td width=200px><b>Borrar</b></td>
			</tr>
			<c:forEach items="${listaPelis}" var="info">
				<tr>
					<td width=200px>${info.director}</td>
					<td width=200px>${info.titulo}</td>
					<td width=200px>${info.fecha}</td>
					<td width=200px>${info.url}</td>
					<td width=200px>
						<form action="/modificarPeli" method="POST">
							<input name="id" type="hidden" value="${info.id}"> <input
								name="director" type="hidden" value="${info.director}">
							<input name="titulo" type="hidden" value="${info.titulo}">
							<input name="fecha" type="hidden" value="${info.fecha}">
							<input name="url" type="hidden" value="${info.url}"> <input
								name="descripcion" type="hidden" value="${info.descripcion}">
							<p align="center">
								<input type="submit" class="btn btn-warning"
									value="Modificar Pelicula">
							</p>
						</form>
					</td>
					<td width=200px>
						<form action="/borrarPeli" method="POST">
							<input name="id" type="hidden" value="${info.id}">
							<p align="center">
								<input type="submit" class="btn btn-danger"
									value="Borrar Pelicula">
							</p>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br>
	<p align="center">
		<a href="/nuevaPeli"><input type="button" class="btn btn-success"
			value="Nueva Pelicula"></a>
	</p>
	<p align="center">
		<a href="/loginCorrecto"><input type="button"
			class="btn btn-primary" value="Volver atrás"></a>
	</p>

	<footer class="page-footer font-small blue"
		style="background-color: #E7F5F4; position: fixed; clear: both; bottom: 0; width: 100%">
	<div class="footer-copyright text-center py-3">
		© 2019 Copyright: <a href="/index"> Filmografia.com </a>
	</div>
	</footer>

</body>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
</html>