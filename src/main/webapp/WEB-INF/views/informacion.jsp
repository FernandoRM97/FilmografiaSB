<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Información</title>

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

	<h1 style="text-align: center">${titulo}</h1>

	<c:forEach items="${peli}" var="info">
		<div style="text-align: center">
			<img src="${info.url}" alt="Portada película" width="500"
				height="auto">
		</div>
		<hr>
		<p align="center">Director:</p>
		<p align="center">${info.director}</p>
		<hr>
		<p align="center">Año de estreno:</p>
		<p align="center">${info.fecha}</p>
		<hr>
		<p align="center">Descripcion</p>
		<p align="center">${info.descripcion}</p>
		<hr>

		<form action="/evaluar" method="POST">
			<input name="id" type="hidden" value="${info.id}">

			<p align="center">Evaluar Pelicula</p>

			<div style="text-align: center">
				<input type="radio" id="rating-1" name="rating" value="1" /> <label
					for="rating-1">1</label> <input type="radio" id="rating-2"
					name="rating" value="2" /> <label for="rating-2">2</label> <input
					type="radio" id="rating-3" name="rating" value="3" /> <label
					for="rating-3">3</label> <input type="radio" id="rating-4"
					name="rating" value="4" /> <label for="rating-4">4</label> <input
					type="radio" id="rating-5" name="rating" value="5" /> <label
					for="rating-5">5</label> <input type="radio" id="rating-6"
					name="rating" value="6" /> <label for="rating-6">6</label> <input
					type="radio" id="rating-7" name="rating" value="7" /> <label
					for="rating-7">7</label> <input type="radio" id="rating-8"
					name="rating" value="8" /> <label for="rating-8">8</label> <input
					type="radio" id="rating-9" name="rating" value="9" /> <label
					for="rating-9">9</label> <input type="radio" id="rating-10"
					name="rating" value="10" checked /> <label for="rating-10">10</label>
			</div>

			<div style="text-align: center">
				<input type="submit" value="Evaluar" class="btn btn-warning">
			</div>

		</form>

	</c:forEach>

	<br>
	<p align="center">
		<a href="/peliculas"><input type="button" class="btn btn-primary"
			value="Inicio"></a>
	</p>
	<br>
	<br>

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