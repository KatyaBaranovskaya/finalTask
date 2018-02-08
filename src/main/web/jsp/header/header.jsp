<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
<header>
    <nav class="top-menu">
        <a class="navbar-logo" href=""><img src="${pageContext.request.contextPath}/resources/logo.png"></a>
        <ul class="menu-main">

            <li><a href="${pageContext.request.contextPath}/Controller?command=load_page&page=/jsp/common/main.jsp"><fmt:message key="page.main"
                                                                                              bundle="${rb}"/></a></li>

            <c:if test="${sessionScope.role == 'guest'}">
                <li><a href="${pageContext.request.contextPath}/Controller?command=show_room_types"><fmt:message
                        key="page.rooms" bundle="${rb}"/></a></li>
                <li><a href="${pageContext.request.contextPath}/Controller?command=show_services"><fmt:message
                        key="page.services" bundle="${rb}"/></a></li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=load_page&page=/jsp/common/contacts.jsp">
                        <fmt:message key="page.contacts" bundle="${rb}"/>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=load_page&page=/jsp/common/login.jsp">
                        <fmt:message key="page.authorization" bundle="${rb}"/>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=load_page&page=/jsp/common/registration.jsp">
                        <fmt:message key="page.registration" bundle="${rb}"/>
                    </a>
                </li>
            </c:if>

            <c:if test="${sessionScope.role == 'user'}">
                <li><a href="${pageContext.request.contextPath}/Controller?command=show_room_types"><fmt:message
                        key="page.rooms" bundle="${rb}"/></a></li>
                <li><a href="${pageContext.request.contextPath}/Controller?command=show_services"><fmt:message
                        key="page.services" bundle="${rb}"/></a></li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=load_page&page=/jsp/common/contacts.jsp">
                        <fmt:message key="page.contacts" bundle="${rb}"/>
                    </a>
                </li>
                <li><a href="${pageContext.request.contextPath}/Controller?command=load_reservation_page"><fmt:message
                        key="page.reservation" bundle="${rb}"/></a></li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=load_page&page=/jsp/user/account.jsp">
                        <fmt:message key="page.account" bundle="${rb}"/>
                    </a>
                </li>
                <li><a href="${pageContext.request.contextPath}/Controller?command=logout"><fmt:message key="page.exit"
                                                                                                        bundle="${rb}"/></a>
                </li>
            </c:if>

            <c:if test="${sessionScope.role == 'admin'}">
                <li><a href="${pageContext.request.contextPath}/Controller?command=show_room_types"><fmt:message
                        key="page.rooms" bundle="${rb}"/></a></li>
                <li><a href="${pageContext.request.contextPath}/Controller?command=show_services"><fmt:message
                        key="page.services" bundle="${rb}"/></a></li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=load_page&page=/jsp/common/contacts.jsp">
                        <fmt:message key="page.contacts" bundle="${rb}"/>
                    </a>
                </li>
                <li><a href="${pageContext.request.contextPath}/Controller?command=show_applications"><fmt:message
                        key="page.applications" bundle="${rb}"/></a></li>
                <li><a href="${pageContext.request.contextPath}/Controller?command=show_users"><fmt:message
                        key="page.users" bundle="${rb}"/></a></li>
                <li><a href="${pageContext.request.contextPath}/Controller?command=logout"><fmt:message key="page.exit"
                                                                                                        bundle="${rb}"/></a>
                </li>
            </c:if>

        </ul>
    </nav>
    <div class="locale">
        <form action="${pageContext.request.contextPath}/Controller" method="POST">
            <input type="hidden" name="command" value="change_locale">
            <input type="hidden" name="url" value="${pageContext.request.requestURI}">
            <div class="form-group localeBtn">
                <button class="btn btn-link" type="submit" name="locale" value="en_US">EN</button>
                <button class="btn btn-link" type="submit" name="locale" value="ru_RU">RU</button>
            </div>
        </form>
    </div>
</header>
</body>
</html>