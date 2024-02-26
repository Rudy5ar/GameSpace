<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Dobro dosli!</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/home.css">
</head>
<body>
    <div class="container">
        <h1>Dobro dosli u Gamespace!</h1>
        <div class="navigation">
            <h3><a href="/Gamespace/games/home">Sve igre</a></h3>
            <h3><a href="/Gamespace/games/showFavorites?userId=${currentUser.userId}">Vase omiljene igre</a></h3>
        </div>
        <div class="search">
            <form action="${pageContext.request.contextPath}/games/findGame" method="post">
                <input type="text" id="pretraga" name="pretraga" placeholder="Pretrazite igru">
                <button type="submit">Nadji</button>
            </form>
            <c:if test="${empty game and searched eq true}">Igra nije pronadjena</c:if>
        </div>
        <div class="add-game">
            <h3><a href="/Gamespace/pages/addGame.jsp">Dodajte igru</a></h3>
            <h3><a href="/Gamespace/izvestaj/getStudios">Pronadjite igrice po odredjenom studiju</a></h3>
            <sec:authorize access="hasRole('ADMIN')">
            	<h3><a href="/Gamespace/user/admin">Brisanje korisnika</a></h3>
            </sec:authorize>
        </div>
        <table class="game-table">
            <tr>
                <th>Title</th>
                <th>Genre</th>
                <th>Platform</th>
                <th>Studio</th>
                <th>Omiljena</th>
                <th>Detalji</th>
            </tr>
            <c:forEach var="game" items="${gameList}">
                <tr>
                    <td>${game.title}</td>
                    <td>${game.genre}</td>
                    <td>${game.platform}</td>
                    <td>
                        <c:forEach var="studio" items="${game.studios}">
                            ${studio.name}<br>
                        </c:forEach>
                    </td>
                    <c:set var="omiljena" value="false" />
                    <c:forEach var="userGame" items="${game.userGames}">
                        <c:if test="${userGame.user.userId eq currentUser.userId}">
                            <c:set var="omiljena" value="true" />
                        </c:if>
                    </c:forEach>
                    <td class="${omiljena ? 'favorite-true' : 'favorite-false'}"></td>
                    <td><a href="${pageContext.request.contextPath}/games/getGame?gameId=${game.gameId}">Prikazi</a></td>
                </tr>
            </c:forEach>
        </table>
        <a href="/Gamespace/logout">Odjavite se</a>
    </div>
</body>
</html>
