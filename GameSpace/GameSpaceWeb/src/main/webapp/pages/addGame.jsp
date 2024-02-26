<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Dodavanje igre</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/addGame.css">
</head>
<body>
<div class="container">
    <h1>Unesite podatke nove igre</h1>
    <form action="${pageContext.request.contextPath}/games/addGame" method="post">
        Title: <input type="text" id="title" name="title" required>
        Genre: <input type="text" id="genre" name="genre" required>
        Platform: <input type="text" id="platform" name="platform" required>
        Studios:
        <input type="text" id="studio1" name="studio1" placeholder="Studio 1">
        <input type="text" id="studio2" name="studio2" placeholder="Studio 2">

        <button type="submit">Dodaj igru</button>
    </form>
    <c:if test="${!empty message}">${message}</c:if>
    
    <h4>
            <a href="/Gamespace/games/home">Pocetna stranica</a>
    </h4>
</div>
</body>
</html>
