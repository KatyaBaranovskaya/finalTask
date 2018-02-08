<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <title><fmt:message key="label.errorPage" bundle="${rb}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</head>
<body class="body-error">
<div class="error-content">
    <div class="error-text">
        <h1>Status code: ${pageContext.errorData.statusCode} </h1>
        <p><b>Request from:</b> ${pageContext.errorData.requestURI} is failed </p>
        <p><b>Servlet name:</b> ${pageContext.errorData.servletName}</p>
        <p><b>Message from exception:</b> ${pageContext.exception.message}</p>
    </div>
    <a class="error-a" href="${pageContext.request.contextPath}/Controller?command=load_page&page=/jsp/common/main.jsp"><span
            class="glyphicon glyphicon-home"></span></a>
    <img class="error-img" src="${pageContext.request.contextPath}/resources/404.jpg">
</div>
</body>
</html>


