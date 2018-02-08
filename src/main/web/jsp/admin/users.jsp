<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <meta http-equiv="CONTENT-TYPE" content="text/html" ; charset="UTF-8"/>
    <title><fmt:message key="page.users" bundle="${rb}"/></title>
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
        <h1 class="serviceH1"><fmt:message key="page.users" bundle="${rb}"/></h1>
        <p class="line"></p>
        <c:choose>
            <c:when test="${not empty users}">
                <c:forEach var="user" items="${users}">
                    <div class="nomerView">
                        <div class="nomerPic">
                            <img src="${pageContext.request.contextPath}/resources/avatars/${user.avatar}"></div>
                        <div class="nomerText">
                            <h1> ${user.surname} ${user.name} ${user.middleName}</h1>
                            <p>${user.email}</p>
                            <p>${user.telephone}</p>
                            <c:if test="${sessionScope.role == 'admin'}">
                                <div class="adminEdit">
                                    <a href="${pageContext.request.contextPath}/Controller?command=edit_user&id=${user.idUser}">
                                        <img src="${pageContext.request.contextPath}/resources/edit.png" class="edit">
                                    </a>
                                    <a href="/Controller?command=delete_user&id=${user.idUser}">
                                        <img src="${pageContext.request.contextPath}/resources/delete.png"
                                             class="delete">
                                    </a>
                                    <br/><a class="a-order"
                                        href="${pageContext.request.contextPath}/Controller?command=show_user_orders&id=${user.idUser}"><fmt:message
                                        key="label.orders" bundle="${rb}"/></a>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p class="null-list"><fmt:message key="message.emptyData" bundle="${rb}"/></p>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer/footer.jsp"></jsp:include>
</body>
</html>
