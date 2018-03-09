<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <meta http-equiv="CONTENT-TYPE" content="text/html" ; charset="UTF-8"/>
    <title><fmt:message key="page.main" bundle="${rb}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/reg.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body class="account">
<jsp:include page="${pageContext.request.contextPath}/jsp/header/header.jsp"></jsp:include>
<div class="content">
    <div class="profile">
        <div class="row">
            <div class="col-sm-8">
                <div class="col-xs-8 col-sm-8">
                    <h2>${typeRoom.typeRoom}</h2>
                    <p class="profileLine"></p></br>
                    <p><strong><fmt:message key="label.capacity" bundle="${rb}"/>: </strong> ${typeRoom.capacity}
                        <span class="glyphicon glyphicon-user"></span>
                    </p>
                    <p><strong><fmt:message key="label.price" bundle="${rb}"/>: </strong> ${typeRoom.price}</p>
                    <p><strong><fmt:message key="label.description" bundle="${rb}"/>: </strong> ${typeRoom.description}
                    </p></br>
                </div>
            </div>
            <div class="col-xs-7 col-sm-4 text-center">
                <img id="roomImage" src="${pageContext.request.contextPath}/resources/rooms/${typeRoom.image}">
            </div>
        </div>
        <div class="col-xs-12 divider text-center">
            <c:if test="${sessionScope.role == 'user'}">
                <div class="addService roomReserv">
                    <a href="${pageContext.request.contextPath}/Controller?command=reservation_room&id=${typeRoom.typeRoom}">
                        <fmt:message key="label.reservation" bundle="${rb}"/>
                    </a>
                </div>
            </c:if>
            <c:if test="${sessionScope.role == null}">
                <div class="addService roomReserv">
                    <a href="${pageContext.request.contextPath}/Controller?command=load_page&page=/jsp/common/login.jsp">
                        <fmt:message key="label.reservation" bundle="${rb}"/>
                    </a>
                </div>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer/footer.jsp"></jsp:include>
</body>
</html>
