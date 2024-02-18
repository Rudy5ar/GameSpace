<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registracija korisnika</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/register.css">
</head>
<body>
	
	<div class="container">
        <h1>Registracija korisnika</h1>

        <form action="${pageContext.request.contextPath}/user/addUser" method="post">

            <h4>Unesite vase podatke</h4>
            <table>
                <tr>
                    <td>Email:</td>
                    <td><input type="text" id="email" name="email"></td>
                </tr>
                <tr>
                    <td>Korisnicko ime:</td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td>Lozinka:</td>
                    <td><input type="password" name="password"></td>
                </tr>
            </table>

            <button type="submit">Dodaj</button>
        </form>
        <h4><a href="/Gamespace/index.jsp">Pocetna stranica</a></h4>
    </div>
	
</body>
</html>