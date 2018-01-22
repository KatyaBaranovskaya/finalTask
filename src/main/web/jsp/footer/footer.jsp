<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</head>
<body>
<footer class="footer">
    <div class="container">
        <p>HotelBooking</p>
    </div>
    <form action="/Controller" method="POST">
        <input type="hidden" name="command" value="change_locale">
        <input type="hidden" name="url" value="${pageContext.request.requestURI}">
        <div class="form-group footerBtn">
            <button class="btn btn-link" type="submit" name="locale" value="en">EN</button>
            <button class="btn btn-link" type="submit" name="locale" value="ru">RU</button>
        </div>
    </form>
</footer>
</body>
</html>


