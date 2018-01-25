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
            <input type="text" class="form-control" name="number" value="${room.roomNumber}" readonly>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Тип</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="type" value="${room.typeRoom}">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Класс апартамента</label>
        <label class="radio-inline">
            <c:if test="${requestScope.room.classApartment == 1}">
                <input type="radio" name="classApartment" value="1" checked> 1
                <input type="radio" name="classApartment" value="2"> 2
                <input type="radio" name="classApartment" value="3"> 3
                <input type="radio" name="classApartment" value="4"> 4
                <input type="radio" name="classApartment" value="5"> 5
            </c:if>
            <c:if test="${requestScope.room.classApartment == 2}">
                <input type="radio" name="classApartment" value="1"> 1
                <input type="radio" name="classApartment" value="2" checked> 2
                <input type="radio" name="classApartment" value="3"> 3
                <input type="radio" name="classApartment" value="4"> 4
                <input type="radio" name="classApartment" value="5"> 5
            </c:if>
            <c:if test="${requestScope.room.classApartment == 3}">
                <input type="radio" name="classApartment" value="1"> 1
                <input type="radio" name="classApartment" value="2"> 2
                <input type="radio" name="classApartment" value="3" checked> 3
                <input type="radio" name="classApartment" value="4"> 4
                <input type="radio" name="classApartment" value="5"> 5
            </c:if>
            <c:if test="${requestScope.room.classApartment == 4}">
                <input type="radio" name="classApartment" value="1"> 1
                <input type="radio" name="classApartment" value="2"> 2
                <input type="radio" name="classApartment" value="3"> 3
                <input type="radio" name="classApartment" value="4" checked> 4
                <input type="radio" name="classApartment" value="5"> 5
            </c:if>
            <c:if test="${requestScope.room.classApartment == 5}">
                <input type="radio" name="classApartment" value="1"> 1
                <input type="radio" name="classApartment" value="2"> 2
                <input type="radio" name="classApartment" value="3"> 3
                <input type="radio" name="classApartment" value="4"> 4
                <input type="radio" name="classApartment" value="5" checked> 5
            </c:if>
        </label>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Вместимость</label>
        <select class="span1" name="capacity">
            <c:if test="${requestScope.room.capacity == 1}">
                <option selected>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </c:if>
            <c:if test="${requestScope.room.capacity == 2}">
                <option>1</option>
                <option selected>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </c:if>
            <c:if test="${requestScope.room.capacity == 3}">
                <option>1</option>
                <option>2</option>
                <option selected>3</option>
                <option>4</option>
                <option>5</option>
            </c:if>
            <c:if test="${requestScope.room.capacity == 4}">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option selected>4</option>
                <option>5</option>
            </c:if>
            <c:if test="${requestScope.room.capacity == 5}">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option selected>5</option>
            </c:if>
        </select>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Цена</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="price" value="${room.price}">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Статус</label>
        <label class="radio-inline">
            <c:if test="${requestScope.room.status == 'свободен'}">
                <input type="radio" name="status" value="Свободен" checked> cвободен
                <input type="radio" name="status" value="Бронирован"> бронирован
            </c:if>
            <input type="radio" name="status" value="Свободен"> cвободен
            <input type="radio" name="status" value="Бронирован" checked> бронирован
        </label>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Описание</label>
        <div class="col-sm-10">
            <textarea type="text" class="form-control" name="description">${room.description}</textarea>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <input type="file" name="image" id="image">
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <input type="hidden" name="command" value="update_room"/>
            <button type="submit" name="edit" class="btn btn-success">Изменить</button>
        </div>
    </div>
</form>
</body>
</html>

