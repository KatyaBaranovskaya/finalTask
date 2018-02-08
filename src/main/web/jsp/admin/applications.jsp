<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <meta http-equiv="CONTENT-TYPE" content="text/html" ; charset="UTF-8"/>
    <title><fmt:message key="page.applications" bundle="${rb}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/reg.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/header/header.jsp"></jsp:include>
<div class="content">
    <div class="printContent">
        <h1 class="serviceH1"><fmt:message key="page.applications" bundle="${rb}"/></h1>
        <p class="line"></p>
        <a href="${pageContext.request.contextPath}/Controller?command=show_executed_applications">
            Оформленные заявки
        </a>
        <table class="table table-hover">
            <thead>
            <tr>
                <th><fmt:message key="label.fullName" bundle="${rb}"/></th>
                <th><fmt:message key="label.adults" bundle="${rb}"/></th>
                <th><fmt:message key="label.children" bundle="${rb}"/></th>
                <th><fmt:message key="label.arrivalDate" bundle="${rb}"/></th>
                <th><fmt:message key="label.departureDate" bundle="${rb}"/></th>
                <th></th>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.user.surname} ${order.user.name} ${order.user.middleName}</td>
                <td>${order.noAdults}</td>
                <td>${order.noChildren}</td>
                <td>${order.arrivalDate}</td>
                <td>${order.departureDate}</td>
                <td>
                    <a href="/Controller?command=edit_order&id=${order.idOrder}"><fmt:message key="label.issue"
                                                                                              bundle="${rb}"/></a
                    <br/><a href="/Controller?command=reject_order&id=${order.idOrder}">Отклонить</a>
                </td>
            </tr>
            </tbody>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer/footer.jsp"></jsp:include>
</body>
</html>


