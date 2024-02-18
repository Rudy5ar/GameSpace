<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/login.css">
   
</head>
<body>
    <div class="container">
        <c:if test="${param.error != null}">
            <p class="error-message">Uneti podaci nisu tačni.</p>
        </c:if>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <table>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td>Lozinka:</td>
                    <td><input type="password" name="password"></td>
                </tr>
            </table>
            <button type="submit">Prijavi se</button>
        </form>
        <h4><a href="/Gamespace/index.jsp">Početna stranica</a></h4>
    </div>
</body>
</html>
