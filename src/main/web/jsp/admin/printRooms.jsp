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



<a href="/Controller?command=get_types">Добавить номер</a></li>
<table class="table table-hover">
    <thead>
    <tr>
        <th>№ Номера</th>
        <th>Тип</th>
        <th>Класс апартамента</th>
        <th>Вместимость</th>
        <th>Цена</th>
        <th>Статус</th>
        <th>Фото</th>
        <th>Описание</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="room" items="${rooms}">
    <tr>
        <td>${room.roomNumber}</td>
        <td>${room.typeRoom}</td>
        <td>${room.classApartment}</td>
        <td>${room.capacity}</td>
        <td>${room.price}</td>
        <td>${room.status}</td>
        <td class="printRoom" ><img src="/resources/${room.picture}" alt="${room.roomNumber}"></td>
        <td>${room.description}</td>

        <c:if test="${sessionScope.role == 'admin'}">
            <td>
                <a href="/Controller?command=delete_room&id=${room.roomNumber}">Удалить</a>
                <a href="/Controller?command=edit_room&id=${room.roomNumber}">Редактировать</a>
            </td>
        </c:if>
        <c:if test="${sessionScope.role == 'user'}">
            <td>
                <a href="/Controller?command=reservation_room&id=${room.roomNumber}">Забронировать</a>
            </td>
        </c:if>
    </tr>
    </tbody>
    </c:forEach>
</table>

<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="/Controller?command=print_room&page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

<c:if test="${currentPage lt noOfPages}">
    <td><a href="/Controller?command=print_room&page=${currentPage + 1}">Next</a></td>
</c:if>
</body>
</html>


