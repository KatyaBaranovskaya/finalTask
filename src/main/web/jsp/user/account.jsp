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

<div class="row" var="client" items="${sessionScope.client}">
    <div class="col-md-offset-12 col-md-12 col-lg-offset-5 col-lg-9">
        <div class="well profile">
            <div class="col-sm-12">
                <div class="col-xs-12 col-sm-8">
                    <h2>${client.surname} ${client.name} ${client.middleName}</h2>
                    <p><strong>Логин: </strong> ${client.login} </p>
                    <p><strong>Email: </strong> ${client.email}</p>
                    <p><strong>Дата рождения: </strong> ${client.dateBirth}</p>
                    <p><strong>Телефон: </strong> ${client.telephone}</p>
                </div>
            </div>
            <div class="col-xs-12 col-sm-4 text-center">
                <img src="${pageContext.request.contextPath}/resources/avatars/${client.avatar}" class="rounded-circle"
                     alt="user" width=200px" height="200px">
                <form action="/Controller" method="POST">
                    <input type="hidden" name="command" value="change_avatar"/>
                    <input type="file" name="avatar" id="avatar">
                    <button type="submit" name="changePhoto" class="btn btn-success">Изменить фото</button>
                </form>
            </div>
            <div class="col-xs-12 divider text-center">
                <a href="/jsp/user/changeAccount.jsp">Изменить информацию о себе</a>
                <a href="/jsp/user/changePassword.jsp">Изменить пароль</a>
                <button type="submit" name="changePhoto" class="btn btn-success">Посмотреть мои заказы</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
