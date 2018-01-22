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
<jsp:include page="${pageContext.request.contextPath}/jsp/footer/footer.jsp"></jsp:include>

<div class="row">
    <div class="col-md-offset-2 col-md-8 col-lg-offset-3 col-lg-6">
        <div class="well profile">
            <div class="col-sm-12">
                <div class="col-xs-12 col-sm-8">
                    <h2>Сергей Иванов</h2>
                    <p><strong>Обо мне: </strong> Веб-дизайнер. </p>
                    <p><strong>Хобби: </strong> Книги, природа, активный отдых, сайтостроение, дизайн, верстка </p>
                    <p><strong>Знания: </strong>
                        <span class="tags">HTML5</span>
                        <span class="tags">CSS3</span>
                        <span class="tags">jQuery</span>
                        <span class="tags">Bootstrap</span>
                    </p>
                </div>
            </div>
            <div class="col-xs-12 col-sm-4 text-center">
                <img src="${pageContext.request.contextPath}/resources/kate.jpg" class="rounded-circle"
                     alt="user" width=60%" height="40%">
            </div>
            <div class="col-xs-12 divider text-center">
                dddd
            </div>
        </div>
    </div>
</div>
</body>
</html>
