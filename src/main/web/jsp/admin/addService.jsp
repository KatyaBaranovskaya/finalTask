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
<body>

<jsp:include page="${pageContext.request.contextPath}/jsp/header/header.jsp"></jsp:include>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer/footer.jsp"></jsp:include>

<form action="/Controller" method="POST">
    <div class="form-group">
        <label class="col-sm-2 control-label">Hаименование услуги</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="typeService">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Цена</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="price">
        </div>
    </div>
    <input type="hidden" name="command" value="add_service"/>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" name="add" class="btn btn-success">Добавить</button>
        </div>
    </div>
</form>
</body>
</html>
