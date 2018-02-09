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
    <div class="printContent">
        <h1 class="serviceH1"><fmt:message key="label.rooms" bundle="${rb}"/></h1>
        <p class="line"></p>

        <c:if test="${sessionScope.role == 'admin'}">
            <div class="addService">
                <a href="${pageContext.request.contextPath}/Controller?command=load_page&page=/jsp/admin/addTypeRoom.jsp">
                    <fmt:message key="label.addTypeRoom" bundle="${rb}"/>
                </a>
                <a href="${pageContext.request.contextPath}/Controller?command=edit_rooms"><fmt:message
                        key="label.changeRoom" bundle="${rb}"/></a>
            </div>
        </c:if>
        <c:if test="${sessionScope.role == 'user'}">
            <form class="searchRoomTypes" action="${pageContext.request.contextPath}/Controller" method="POST"
                  name="form"
                  onsubmit="return validationSearch();">
                <div class="row">
                    <label class="col-sm-2 control-label"><fmt:message key="label.minPrice" bundle="${rb}"/>:</label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="min" placeholder="min">
                    </div>
                    <label class="col-sm-2 control-label"><fmt:message key="label.maxPrice" bundle="${rb}"/>:</label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="max" placeholder="max">
                    </div>
                    <div class="form-group">
                    </div>
                </div>
                <err:mtg messageError="${errorPrice}"/>
                <div>
                    <input type="hidden" name="command" value="find_rooms"/>
                    <br/>
                    <button type="submit" class="btn btn-success"><fmt:message key="label.find"
                                                                               bundle="${rb}"/></button>
                </div>
            </form>
        </c:if>
        <c:choose>
            <c:when test="${not empty roomTypes}">
                <c:forEach var="typeRoom" items="${roomTypes}">
                    <div class="nomerView">
                        <div class="nomerPic">
                            <img src="${pageContext.request.contextPath}/resources/${typeRoom.image}"></div>
                        <div class="nomerText">
                            <a href="${pageContext.request.contextPath}/Controller?command=show_type_room&id=${typeRoom.idType}">
                                <h1>${typeRoom.typeRoom}</h1>
                            </a>
                            <div class="roomFeature">
                                <p>${typeRoom.capacity} <span class="glyphicon glyphicon-user"></span></p>
                                <p>${typeRoom.price} BYN</p>
                            </div>
                            <c:if test="${sessionScope.role == 'admin'}">
                                <div class="adminEdit">
                                    <a href="${pageContext.request.contextPath}/Controller?command=edit_type_room&id=${typeRoom.idType}">
                                        <img src="${pageContext.request.contextPath}/resources/edit.png" class="edit">
                                    </a>
                                    <a href="${pageContext.request.contextPath}/Controller?command=delete_type_room&id=${typeRoom.idType}">
                                        <img src="${pageContext.request.contextPath}/resources/delete.png"
                                             class="delete">
                                    </a>
                                </div>
                            </c:if>
                            <c:if test="${sessionScope.role == 'user'}">
                                <div class="addService reserv">
                                    <a href="${pageContext.request.contextPath}/Controller?command=reservation_room&id=${typeRoom.typeRoom}">
                                        <fmt:message key="label.reservation" bundle="${rb}"/>
                                    </a>
                                </div>
                            </c:if>
                            <c:if test="${sessionScope.role == 'guest'}">
                                <div class="addService reserv">
                                    <a href="${pageContext.request.contextPath}/Controller?command=load_page&page=/jsp/common/login.jsp">
                                        <fmt:message key="label.reservation" bundle="${rb}"/>
                                    </a>
                                </div>
                            </c:if>

                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p class="null-list"><fmt:message key="message.emptyData" bundle="${rb}"/></p>
            </c:otherwise>
        </c:choose>

        <p class="line"></p>
        <ul class="pagination">
            <c:if test="${currentPage gt 1}">
                <li>
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/Controller?command=show_room_types&page=${currentPage - 1}">
                        &lArr;</a>
                </li>
            </c:if>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-link">${i}</li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/Controller?command=show_room_types&page=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage lt noOfPages}">
                <li>
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/Controller?command=show_room_types&page=${currentPage + 1}">&rArr;</a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer/footer.jsp"></jsp:include>
</body>
</html>
