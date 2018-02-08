<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<%@ taglib prefix="err" uri="/WEB-INF/tld/taglib.tld" %>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <meta http-equiv="CONTENT-TYPE" content="text/html" ; charset=utf-8/>
    <title><fmt:message key="label.singIn" bundle="${rb}"/></title>
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
    <div class="formSingUp">
        <div class="randompad">
            <p><fmt:message key="label.singIn" bundle="${rb}"/></p>
            <form action="${pageContext.request.contextPath}/Controller" method="POST" name="form"
                  onsubmit="return validationSingUp();">
                <input type="hidden" name="command" value="login"/>
                <label name="email"><fmt:message key="label.login" bundle="${rb}"/></label>
                <input type="text" name="login"/>
                <label name="password"><fmt:message key="label.password" bundle="${rb}"/></label>
                <input type="password" name="password"/><br/>
                <err:mtg messageError="${errorLoginPass}"/>
                <button type="submit" class="but"><fmt:message key="label.enter" bundle="${rb}"/></button>
                <div class="forgotPass">
                    <a href="${pageContext.request.contextPath}/Controller?command=load_page&page=/jsp/common/forgotPassword.jsp">
                        <fmt:message key="label.forgotPass" bundle="${rb}"/>
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer/footer.jsp"></jsp:include>
</body>
</html>
