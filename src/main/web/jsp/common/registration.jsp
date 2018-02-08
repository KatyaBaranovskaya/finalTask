<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="err" uri="/WEB-INF/tld/taglib.tld" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <meta charset="UTF-8"/>
    <title><fmt:message key="page.registration" bundle="${rb}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/reg.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/header/header.jsp"></jsp:include>
<div class="content">
    <div class="formRegistr">
        <div class="randompad">
            <p><fmt:message key="page.registration" bundle="${rb}"/></p>
            <form action="${pageContext.request.contextPath}/Controller" method="POST" name="form"
                  onsubmit="return validationRegister();">
                <input type="hidden" name="command" value="registration"/>
                <input type="text" name="surname" placeholder="<fmt:message key="label.surname" bundle="${rb}" />"/>
                <input type="text" name="name" placeholder="<fmt:message key="label.name" bundle="${rb}" />"/>
                <input type="text" name="middleName"
                       placeholder="<fmt:message key="label.middleName" bundle="${rb}" />"/>
                <label><fmt:message key="label.birthDate" bundle="${rb}"/></label>
                <input type="text" name="dateBirth" placeholder="<fmt:message key="label.date" bundle="${rb}" />"/>
                <input type="text" name="phone" placeholder="<fmt:message key="label.telephone" bundle="${rb}" />"/>
                <input id="coppy" type="email" name="email"
                       placeholder="<fmt:message key="label.email" bundle="${rb}" />"/>
                <input type="text" name="login" placeholder="<fmt:message key="label.login" bundle="${rb}" />"/>
                <input type="password" name="password"
                       placeholder="<fmt:message key="label.password" bundle="${rb}" />"/>
                <input type="password" name="password2"
                       placeholder="<fmt:message key="label.confirmPassword" bundle="${rb}" />"/><br/>
                <err:mtg messageError="${ errorRegistration }"/>
                <input type="submit" class="but" value="<fmt:message key="label.register" bundle="${rb}" />"/>
            </form>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer/footer.jsp"></jsp:include>
</body>
</html>
