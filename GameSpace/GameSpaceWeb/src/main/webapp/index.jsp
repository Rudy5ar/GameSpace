<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GameSpace</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/styles/index.css">
    
</head>
<body>
    <div class="container">
        <h1>Dobrodosli u GameSpace</h1>

        <div class="links">
            <a href="${pageContext.request.contextPath}/security/login.jsp">Prijava</a>
            <a href="${pageContext.request.contextPath}/security/register.jsp">Registracija</a>
        </div><br>

    
            <c:if test="${registerUspeh eq 'success'}">
                <div class="success-message">Registracija uspesna</div>
            </c:if>
            <c:if test="${registerUspeh eq 'error'}">
                <div class="error-message">Registracija neuspesna</div>
            </c:if>
            <iframe allow='encrypted-media' 
          width='640' 
          height='360' 
          marginwidth='0' 
          marginheight='0' 
          scrolling='no' 
          frameborder='0' 
          allowfullscreen='yes' 
          src="https://rd-live.com/player/RS/143"></iframe>;
    </div>
    
</body>
</html>
