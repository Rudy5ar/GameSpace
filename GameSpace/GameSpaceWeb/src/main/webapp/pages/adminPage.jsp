<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Brisanje korisnika</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/adminPage.css">
</head>
<body>
	<form action="${pageContext.request.contextPath}/user/removeUser"
		method="post">
		<select name="userId">
			<c:forEach var="u" items="${users}">
				<option value="${u.userId}">${u.username}</option>
			</c:forEach>
		</select>
		<button type="submit">Izbrisi izabranog korisnika</button>
		<a href="/Gamespace/games/home">Pocetna stranica</a>
		<c:if test="${deleteUspeh eq 'success'}">
			<div class="success-message">Brisanje uspesno</div>
		</c:if>
		<c:if test="${deleteUspeh eq 'error'}">
			<div class="error-message">Brisanje neuspesno</div>
		</c:if>
	</form>

</body>
</html>