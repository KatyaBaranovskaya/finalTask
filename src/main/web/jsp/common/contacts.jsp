<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <meta http-equiv="CONTENT-TYPE" content="text/html" ; charset="UTF-8"/>
    <title><fmt:message key="page.main" bundle="${rb}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/contacts.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/reg.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/header/header.jsp"></jsp:include>
<div class="content">
    <div class="bg">
        <p class="txt"><fmt:message key="label.contacts" bundle="${rb}"/></p>
        <div class="block2">
        </div>
        <div class="block">
            <img class="img" src="${pageContext.request.contextPath}/resources/hotelSign.png">
            <div class="contactsInf">
                <p><fmt:message key="label.telephoneNum" bundle="${rb}"/></p>
                <p>8-029-969-91-34</p>
                <p class="line"></p>
                <p><fmt:message key="label.address" bundle="${rb}"/></p>
                <p><fmt:message key="label.timeWork" bundle="${rb}"/></p>
                <p><fmt:message key="label.mail" bundle="${rb}"/></p>
            </div>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer/footer.jsp"></jsp:include>
</body>
</html>
