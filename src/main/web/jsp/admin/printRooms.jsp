<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <meta http-equiv="CONTENT-TYPE" content="text/html"; charset="UTF-8"/>
    <title><fmt:message key="page.main" bundle="${rb}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/reg.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/jsp/header/header.jsp"></jsp:include>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer/footer.jsp"></jsp:include>

<a href="/jsp/admin/addRoom.jsp">Добавить номер</a></li>
<table class="table table-hover">
    <thead>
    <tr>
        <th>№ Номера</th>
        <th>Статус</th>
        <th>Тип</th>
        <th>Вместимость</th>
        <th>Цена</th>
        <th>Описание</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="room" items="${rooms}">
    <tr>
        <td>${room.roomNumber}</td>
        <td>${room.status}</td>
        <td>${room.typeRoom}</td>
        <td>${room.capacity}</td>
        <td>${room.price}</td>
        <td>${room.description}</td>
    </tr>
    </tbody>
    </c:forEach>

</table>
</body>
</html>
