<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <meta http-equiv="CONTENT-TYPE" content="text/html" ; charset="UTF-8"/>
    <title><fmt:message key="page.reservation" bundle="${rb}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/reg.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/header/header.jsp"></jsp:include>
<div class="content">
    <div class="addContent">
        <h1 class="serviceH1"><fmt:message key="page.reservation" bundle="${rb}"/></h1>
        <p class="line"></p>
        <form action="/Controller" method="POST">
            <div class="nomerView">
                <div class="nomerText">
                    <h1><fmt:message key="label.arrivalDate" bundle="${rb}"/>: ${order.arrivalDate}</h1>
                    <h1><fmt:message key="label.departureDate" bundle="${rb}"/>: ${order.departureDate}</h1>
                    <h1><fmt:message key="label.price" bundle="${rb}"/>: ${order.price}</h1>
                </div>
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="command" value="issue_order"/>
                    <button type="submit" name="edit" class="btn btn-success"><fmt:message key="label.issue" bundle="${rb}"/></button><br/>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer/footer.jsp"></jsp:include>
</body>
</html>
