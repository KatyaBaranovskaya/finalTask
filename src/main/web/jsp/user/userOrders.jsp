<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <meta http-equiv="CONTENT-TYPE" content="text/html" ; charset="UTF-8"/>
    <title><fmt:message key="page.account" bundle="${rb}"/></title>
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
        <h1 class="serviceH1"><fmt:message key="label.orders" bundle="${rb}"/></h1>
        <p class="line"></p>
        <table class="table table-hover">
            <c:choose>
                <c:when test="${not empty orders}">
                    <thead>
                    <tr>
                        <th><fmt:message key="label.roomNumber" bundle="${rb}"/></th>
                        <th><fmt:message key="label.adults" bundle="${rb}"/></th>
                        <th><fmt:message key="label.children" bundle="${rb}"/></th>
                        <th><fmt:message key="label.arrivalDate" bundle="${rb}"/></th>
                        <th><fmt:message key="label.departureDate" bundle="${rb}"/></th>
                        <th><fmt:message key="label.typeApartment" bundle="${rb}"/></th>
                        <th><fmt:message key="label.price" bundle="${rb}"/></th>
                        <th><fmt:message key="label.status" bundle="${rb}"/></th>
                        <th></th>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <c:choose>
                                <c:when test="${order.roomNumber == 0}">
                                    <td>-</td>
                                </c:when>
                                <c:otherwise>
                                    <td>${order.roomNumber}</td>
                                </c:otherwise>
                            </c:choose>
                            <td>${order.noAdults}</td>
                            <td>${order.noChildren}</td>
                            <td>${order.arrivalDate}</td>
                            <td>${order.departureDate}</td>
                            <td>${order.typeApartment}&#9733;</td>
                            <td>${order.price}</td>
                            <td>${order.status}</td>
                        </tr>
                        </tbody>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p class="null-list"><fmt:message key="message.emptyData" bundle="${rb}"/></p>
                </c:otherwise>
            </c:choose>
        </table>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer/footer.jsp"></jsp:include>
</body>
</html>
