<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <meta charset="UTF-8"/>
    <title><fmt:message key="page.registration" bundle="${rb}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/reg.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/jsp/header/header.jsp"></jsp:include>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer/footer.jsp"></jsp:include>

<img class="svgClass" src="${pageContext.request.contextPath}/resources/reg.png" alt="hotel">
<div class="formholder">
    <div class="randompad">
        <p><fmt:message key="page.registration" bundle="${rb}"/></p>
        <form action="/Controller" method="POST" name="form" onsubmit="return validation();">
            <input type="hidden" name="command" value="registration"/>
            <input type="text" name="surname" placeholder="<fmt:message key="label.surname" bundle="${rb}" />"/>
            <input type="text" name="name" placeholder="<fmt:message key="label.name" bundle="${rb}" />"/>
            <input type="text" name="middleName" placeholder="<fmt:message key="label.middleName" bundle="${rb}" />"/>
            <label><fmt:message key="label.birthDate" bundle="${rb}"/></label>
            <input type="date" name="date"/>
            <input type="text" name="phone" placeholder="<fmt:message key="label.telephone" bundle="${rb}" />"/>
            <input id="coppy" type="email" name="mail" placeholder="<fmt:message key="label.email" bundle="${rb}" />"/>
            <input type="text" name="login" placeholder="<fmt:message key="label.login" bundle="${rb}" />"/>
            <input type="password" name="password" placeholder="<fmt:message key="label.password" bundle="${rb}" />"/>
            <input type="password" name="password2"
                   placeholder="<fmt:message key="label.confirmPassword" bundle="${rb}" />"/>
            <br/><input type="submit" value="<fmt:message key="label.register" bundle="${rb}" />"/>
        </form>
    </div>
</div>
<button type='button' id="butAdd" class="but" onclick="third('coppy',true)"><fmt:message key="label.add" bundle="${rb}" /></button>
<br/>
<button type='button' id="butDelete" class="but" onclick="fourth()"><fmt:message key="label.delete" bundle="${rb}" /></button>
<div id="mail" class="newMail"></div>
</body>
</html>
