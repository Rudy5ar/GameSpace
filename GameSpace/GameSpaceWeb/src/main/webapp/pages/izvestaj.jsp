<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Izvestaj o studijima</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/izvestaj.css">

</head>
<body>
	<form action="/Gamespace/izvestaj/createReport" method="post">
		<table>
			<tr>
				<td>Studio:</td>
				<td><select name="studio">
						<c:forEach items="${studios}" var="s">
							<option value="${s.studioId}">${s.name}</option>
						</c:forEach>
				</select></td>
				<td><input type="submit" value="Generisi izvestaj" /></td>
			</tr>
		</table>
		<a href="/Gamespace/games/home">Pocetna stranica</a>
	</form>
	
</body>
</html>