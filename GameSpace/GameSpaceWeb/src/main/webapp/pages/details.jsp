<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Detalji igre</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/details.css">
</head>
<body>
    <div class="container">
        <h1>${game.title}</h1>

        Studio:
        <c:forEach var="studio" items="${game.studios}">
            ${studio.name} 
        </c:forEach>
        <br> Zanr: ${game.genre}
        <br> Platforma: ${game.platform}
        <br>
        <c:forEach var="userGame" items="${game.userGames}">
            <c:if test="${userGame.user.userId eq currentUser.userId}">
                <c:set var="omiljena" value="true" />
            </c:if>
        </c:forEach>
        Omiljena:
        <c:if test="${!omiljena}">
            <a href="/Gamespace/games/dodajOmiljenu?userId=${currentUser.userId}&gameId=${game.gameId}">Oznacite kao omiljena</a>
        </c:if>
        <c:if test="${omiljena}">
            <a href="/Gamespace/games/ukloniOmiljenu?userId=${currentUser.userId}&gameId=${game.gameId}">Oznacite kao neomiljena</a>
        </c:if>

        <h2>Komentari</h2>

        <table>
            <tr>
                <th>User</th>
                <th>Komentar</th>
            </tr>
            <c:forEach var="comment" items="${comments}">
                <tr>
                    <td>${comment.user.username}</td>
                    <td>${comment.commentText}</td>
                </tr>
            </c:forEach>
        </table><br>

        <form action="/Gamespace/comment/addComment" method="post">
            <input type="hidden" name="gameId" value="${game.gameId}">
            <input type="hidden" name="userId" value="${currentUser.userId}">
            <textarea name="commentText" rows="4" cols="50"></textarea>
            <button type="submit">Dodaj komentar</button>
        </form>

        <h4>
            <a href="/Gamespace/games/home">Pocetna stranica</a>
        </h4>
    </div>
</body>
</html>
