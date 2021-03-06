<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="err" uri="/WEB-INF/tld/taglib.tld" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <meta http-equiv="CONTENT-TYPE" content="text/html" ; charset="UTF-8"/>
    <title><fmt:message key="page.reservation" bundle="${rb}"/></title>
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
        <h1 class="serviceH1"><fmt:message key="page.reservation" bundle="${rb}"/></h1>
        <p class="line"></p>
        <form class="ediOrderForm" action="/Controller" method="POST" name="form"
              onsubmit="return validationOrder();">
            <div class="form-group">
                <label class="col-sm-6 control-label"><fmt:message key="label.roomNumber" bundle="${rb}"/>:</label>
                <div class="col-sm-7">
                    <select class="custom-select col-md-5" name="roomNumber">
                        <c:forEach var="number" items="${numbers}">
                            <option>${number}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-6 control-label"><fmt:message key="label.arrivalDate" bundle="${rb}"/>:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" name="arrival_date" value="${order.arrivalDate}"
                           placeholder="<fmt:message key="label.date" bundle="${rb}"/> "/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-6 control-label"><fmt:message key="label.departureDate" bundle="${rb}"/>:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" name="departure_date" value="${order.departureDate}"
                           placeholder="<fmt:message key="label.date" bundle="${rb}" />"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-7 control-label"><fmt:message key="label.adults" bundle="${rb}"/>:</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="noAdults" value="${order.noAdults}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-7 control-label"><fmt:message key="label.children" bundle="${rb}"/>:</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="noChildren" value="${order.noChildren}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-7 control-label"><fmt:message key="label.typeRoom" bundle="${rb}"/>:</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" name="typeApartment" value="${order.typeApartment}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><fmt:message key="label.includeBreakfast" bundle="${rb}"/></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="breakfast" value="${order.breakfast}">
                </div>
            </div>
            <err:mtg messageError="${errorOrder}"/>
            <div class="col-sm-offset-2 col-sm-10">
                <input type="hidden" name="command" value="calculate_price"/>
                <button type="submit" name="edit" class="btn btn-success"><fmt:message key="label.count"
                                                                                       bundle="${rb}"/></button>
            </div>

        </form>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer/footer.jsp"></jsp:include>
</body>
</html>

