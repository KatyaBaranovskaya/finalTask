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
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/header/header.jsp"></jsp:include>
<div class="content">
    <div class="addContent">

        <h1 class="serviceH1"><fmt:message key="label.changePassword" bundle="${rb}"/></h1>
        <p class="line"></p>

        <form class="changeAccountForm" action="${pageContext.request.contextPath}/Controller" method="POST" var="user" items="${user}" name="form"
              onsubmit="return validationUserInfo();">
            <div class="form-group">
                <label class="col-sm-3 control-label"><fmt:message key="label.surname" bundle="${rb}"/>:</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" name="surname" value="${user.surname}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><fmt:message key="label.name" bundle="${rb}"/>:</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" name="name" value="${user.name}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><fmt:message key="label.middleName" bundle="${rb}"/>:</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" name="middleName" value="${user.middleName}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><fmt:message key="label.login" bundle="${rb}"/>:</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" name="login" value="${user.login}" readonly>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><fmt:message key="label.email" bundle="${rb}"/>:</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" name="email" value="${user.email}" readonly>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-6 control-label"><fmt:message key="label.birthDate" bundle="${rb}"/>:</label>
                <div class="col-sm-4">
                    <input class="form-control" type="text" name="dateBirth" value="${user.dateBirth}" placeholder="<fmt:message key="label.date" bundle="${rb}" />"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><fmt:message key="label.telephone" bundle="${rb}"/>:</label>
                <div class="col-sm-6">
                    <input type="tel" class="form-control" name="phone" value="${user.telephone}">
                </div>
            </div>
            <err:mtg messageError="${errorUserInfo}"/>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="command" value="change_account_information"/>
                    <input type="hidden" name="id" value="${user.idUser}"/>
                    </br><button type="submit" class="btn btn-success"><fmt:message key="label.change" bundle="${rb}"/></button>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer/footer.jsp"></jsp:include>
</body>
</html>