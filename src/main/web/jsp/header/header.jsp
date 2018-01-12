<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <meta http-equiv="CONTENT-TYPE" content="text/html" ; charset="UTF-8"/>
    <title><fmt:message key="label.singIn" bundle="${rb}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/reg.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>
<nav class="top-menu">
    <a class="navbar-logo" href=""><img width="60" height="60" src="/resources/hotel.png"></a>
    <ul class="menu-main">

        <li><a href="">Главная</a></li>
        <li><a href="">Номера и цены</a></li>
        <li><a href="">Услуги</a></li>
        <li><a href="">Контакты</a></li>

        <c:if test="${sessionScope.role == null}">
            <li><a href="/jsp/common/login.jsp">Авторизация</a></li>
            <li><a href="/jsp/common/registration.jsp">Регистрация</a></li>
        </c:if>

        <c:if test="${sessionScope.role == 'user'}">
            <li><a href="">Бронирование</a></li>
            <li><a href="">Личный кабинет</a></li>
            <li>
                <form action="/Controller" method="POST">
                    <input type="hidden" name="command" value="logout"/>
                    <input type="submit" value="Выход" class="button"/><br/>
                </form>
            </li>
        </c:if>

        <c:if test="${sessionScope.role == 'admin'}">
            <li><a href="">Личный кабинет</a></li>
            <li>
                <form action="/Controller" method="POST">
                    <input type="hidden" name="command" value="logout"/>
                    <input type="submit" value="Выход" class="button"/><br/>
                </form>
            </li>
        </c:if>

    </ul>
</nav>
</nav>
</body>
</html>