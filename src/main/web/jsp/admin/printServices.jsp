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

<a href="/jsp/admin/addService.jsp">Добавить услугу</a></li>
<table class="table table-hover">
 <thead>
    <tr>
        <th>ID услуги</th>
        <th>Наименование услуги</th>
        <th>Цена</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="service" items="${services}">
    <tr>
        <td>${service.idService}</td>
        <td>${service.typeService}</td>
        <td>${service.price}</td>
        <td>
            <a href="/Controller?command=delete_service&id=${service.idService}">Удалить</a>
            <a href="/Controller?command=edit_service&id=${service.idService}">Редактировать</a>
        </td>
    </tr>
    </tbody>
    </c:forEach>

</table>
</body>
</html>
