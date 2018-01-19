<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <meta http-equiv="CONTENT-TYPE" content="text/html" ; charset="UTF-8"/>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/reg.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>
<nav class="top-menu">
    <a class="navbar-logo" href=""><img width="60" height="60" src="${pageContext.request.contextPath}/resources/hotel.png"></a>
    <ul class="menu-main">

        <li><a href="/jsp/common/main.jsp"><fmt:message key="page.main" bundle="${rb}"/></a></li>

        <c:if test="${sessionScope.role == null || sessionScope.role == 'guest'}">
            <li><a href=""><fmt:message key="page.room" bundle="${rb}"/></a></li>
            <li><a href=""><fmt:message key="page.service" bundle="${rb}"/></a></li>
            <li><a href=""><fmt:message key="page.contact" bundle="${rb}"/></a></li>
            <li><a href="/jsp/common/login.jsp"><fmt:message key="page.authorization" bundle="${rb}"/></a></li>
            <li><a href="/jsp/common/registration.jsp"><fmt:message key="page.registration" bundle="${rb}"/></a></li>
        </c:if>

        <c:if test="${sessionScope.role == 'user'}">
            <li><a href=""><fmt:message key="page.room" bundle="${rb}"/></a></li>
            <li><a href=""><fmt:message key="page.service" bundle="${rb}"/></a></li>
            <li><a href=""><fmt:message key="page.contact" bundle="${rb}"/></a></li>
            <li><a href=""><fmt:message key="page.reservation" bundle="${rb}"/></a></li>
            <li><a href="/jsp/user/account.jsp"><fmt:message key="page.account" bundle="${rb}"/></a></li>
            <li>
                <form action="/Controller" method="POST">
                    <input type="hidden" name="command" value="logout"/>
                    <input type="submit" value="<fmt:message key="page.exit" bundle="${rb}"/>" class="button"/><br/>
                </form>
            </li>
        </c:if>

        <c:if test="${sessionScope.role == 'admin'}">
            <li>
                <form action="/Controller" method="POST">
                    <input type="hidden" name="command" value="print_room"/>
                    <input type="submit" value="<fmt:message key="page.room" bundle="${rb}"/>" class="btn btn-success">
                </form>
            </li>
            <li>
                <form action="/Controller" method="POST">
                    <input type="hidden" name="command" value="print_service"/>
                    <input type="submit" value="<fmt:message key="page.service" bundle="${rb}"/>" class="btn btn-success">
                </form>
            </li>
            <li><a href=""><fmt:message key="page.contact" bundle="${rb}"/></a></li>
            <li>
                <form action="/Controller" method="POST">
                    <input type="hidden" name="command" value="print_user"/>
                    <input type="submit" value="<fmt:message key="page.users" bundle="${rb}"/>" class="btn btn-success">
                </form>
            </li>
            <li>
                <form action="/Controller" method="POST">
                    <input type="hidden" name="command" value="logout"/>
                    <input type="submit" value="<fmt:message key="page.exit" bundle="${rb}"/>" class="button"/><br/>
                </form>
            </li>
        </c:if>

    </ul>
</nav>
</nav>
</body>
</html>