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
        <label class="col-sm-2 control-label">Номер</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="number">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Статус</label>
        <label class="radio-inline">
            <input type="radio" name="status" id="radio1" value="Свободен"> cвободен
        </label>
        <label class="radio-inline">
            <input type="radio" name="status" id="radio2" value="Бронирован"> бронирован
        </label>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Тип</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="type">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Вместимость</label>
        <select class="span1" name="capacity">
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option>
        </select>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Цена</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="price">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Описание</label>
        <div class="col-sm-10">
            <textarea type="text" class="form-control" name="description"></textarea>
        </div>
    </div>
    <input type="hidden" name="command" value="add_room"/>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" name="add" class="btn btn-success">Добавить</button>
        </div>
    </div>
</form>
</body>
</html>


<%--
<div class="form-group">
    <label class="col-sm-2 control-label">Тип</label>
    <select class="span1" name="type">
        <option>SNGL</option>
        <option>DBL</option>
        <option>TWIN</option>
        <option>TRPL</option>
        <option>Family Room</option>
        <option>Studio</option>
        <option>King Suite</option>
        <option>Honeymoon Room</option>
        <option>Senior Suite</option>
        <option>3 ADL</option>
    </select>
</div>--%>
