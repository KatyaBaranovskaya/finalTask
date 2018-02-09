<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="err" uri="/WEB-INF/tld/taglib.tld" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <meta http-equiv="CONTENT-TYPE" content="text/html" ; charset="UTF-8"/>
    <title><fmt:message key="page.main" bundle="${rb}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/reg.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body class="account">
<jsp:include page="${pageContext.request.contextPath}/jsp/header/header.jsp"></jsp:include>
<div class="content">
    <div class="profile">
        <div class="row" var="user" items="${sessionScope.user}">
            <div class="col-sm-8">
                <div class="col-xs-8 col-sm-8">
                    <h2>${user.surname}</h2>
                    <h2>${user.name} ${user.middleName}</h2>
                    <p class="profileLine"></p></br>
                    <p><strong><fmt:message key="label.login" bundle="${rb}"/>: </strong> ${user.login} </p>
                    <p><strong><fmt:message key="label.email" bundle="${rb}"/>: </strong> ${user.email}</p>
                    <p><strong><fmt:message key="label.birthDate" bundle="${rb}"/>: </strong> ${user.dateBirth}</p>
                    <p><strong><fmt:message key="label.telephone" bundle="${rb}"/>: </strong> ${user.telephone}</p></br>
                </div>
            </div>
            <div class="col-xs-7 col-sm-4 text-center">
                <img src="${pageContext.request.contextPath}/resources/avatars/${user.avatar}" class="rounded-circle">
                <c:if test="${sessionScope.role == 'user'}">
                    <form action="${pageContext.request.contextPath}/Controller" method="POST">
                        <input type="hidden" name="command" value="change_avatar"/></br>
                        <span class="btn btn-default btn-file">
                        <input type="file" name="avatar" accept="image/*"><fmt:message key="label.browsePhoto"
                                                                                       bundle="${rb}"/>
                    </span>
                        <err:mtg messageError="${errorLoginPass}"/>
                        <button type="submit" name="changePhoto" class="btn btn-success">OK</button>
                    </form>
                </c:if>
            </div>
        </div>
        <c:if test="${sessionScope.role == 'user'}">
            <div class="col-xs-12 divider text-center">
                <a href="${pageContext.request.contextPath}/Controller?command=load_page&page=/jsp/user/changeAccount.jsp">
                    <fmt:message key="label.editAccountInfo" bundle="${rb}"/>
                </a>
                <a href="${pageContext.request.contextPath}/Controller?command=load_page&page=/jsp/user/changePassword.jsp">
                    <fmt:message key="label.changePassword" bundle="${rb}"/>
                </a>
                <a href="${pageContext.request.contextPath}/Controller?command=show_user_orders&id="><fmt:message
                        key="label.viewMyOrders" bundle="${rb}"/></a>
            </div>
        </c:if>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer/footer.jsp"></jsp:include>
</body>
</html>
