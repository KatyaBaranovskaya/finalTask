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

        <h1 class="serviceH1"><fmt:message key="label.editTypeRoom" bundle="${rb}"/></h1>
        <p class="line"></p>

        <form class="addServiceForm" action="${pageContext.request.contextPath}/Controller" method="POST" name="form"
              onsubmit="return validationEditTypeRoom();">
            <div class="form-group">
                <label class="col-sm-2 control-label"><fmt:message key="label.typeRoom" bundle="${rb}"/>:</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" name="typeRoom" value="${typeRoom.typeRoom}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><fmt:message key="label.capacity" bundle="${rb}"/>:</label>
                <select class="custom-select col-md-2" name="capacity">
                    <c:if test="${typeRoom.capacity == 1}">
                        <option selected>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </c:if>
                    <c:if test="${typeRoom.capacity == 2}">
                        <option>1</option>
                        <option selected>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </c:if>
                    <c:if test="${typeRoom.capacity == 3}">
                        <option>1</option>
                        <option>2</option>
                        <option selected>3</option>
                        <option>4</option>
                        <option>5</option>
                    </c:if>
                    <c:if test="${typeRoom.capacity == 4}">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option selected>4</option>
                        <option>5</option>
                    </c:if>
                    <c:if test="${typeRoom.capacity == 5}">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option selected>5</option>
                    </c:if>
                </select>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label"><fmt:message key="label.price" bundle="${rb}"/>:</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="price" value="${typeRoom.price}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label"><fmt:message key="label.description" bundle="${rb}"/>:</label>
                <div class="col-sm-8">
                    <textarea type="text" class="form-control" name="description">${typeRoom.description}</textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-5">
                    <span class="btn btn-default btn-file">
                        <input type="hidden" name="oldImage" value="${typeRoom.image}"/>
                        <input type="file" name="image"><fmt:message key="label.browsePhoto" bundle="${rb}"/>
                    </span>
                </div>
            </div>
            <err:mtg messageError="${errorTypeRoom}"/>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="command" value="update_type_room"/>
                    </br><input type="hidden" name="idTypeRoom" value="${typeRoom.idType}"/>
                    <button type="submit" name="add" class="btn btn-success"><fmt:message key="label.change"
                                                                                          bundle="${rb}"/></button>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer/footer.jsp"></jsp:include>
</body>
</html>

