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

        <form class="reservationForm" action="${pageContext.request.contextPath}/Controller" method="POST" name="form"
              onsubmit="return validationOrder();">
            <div class="row">
                <div class="col-md-4 col-sm-4 col-xs-4">
                    <label class="col-sm-7 control-label"><fmt:message key="label.arrivalDate" bundle="${rb}"/>:</label>
                    <input class="form-control" type="date" name="arrival_date"
                           placeholder="<fmt:message key="label.date" bundle="${rb}" />"/>
                </div>
                <div class="col-md-4 col-sm-8 col-xs-4">
                    <label class="col-sm-9 control-label"><fmt:message key="label.departureDate"
                                                                       bundle="${rb}"/>:</label>
                    <input class="form-control" type="date" name="departure_date"
                           placeholder="<fmt:message key="label.date" bundle="${rb}" />"/>
                </div>
            </div>
            </br>
            <div class="form-group">
                <label class="col-md-4 control-label"><fmt:message key="label.noPeople" bundle="${rb}"/>:</label>
                <select id="noAdults" class="custom-select col-md-2" name="noAdults">
                    <option selected disabled><fmt:message key="label.adults" bundle="${rb}"/></option>
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                </select>
                <select id="noChildren" class="custom-select col-md-2" name="noChildren">
                    <option selected disabled><fmt:message key="label.children" bundle="${rb}"/></option>
                    <option>0</option>
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                </select>
            </div>
            <c:if test="${sessionScope.typeApartment == null}">
                <div class="form-group">
                    <label class="col-sm-5 control-label"><fmt:message key="label.typeRoom" bundle="${rb}"/>:</label>
                    <div class="col-sm-7">
                        <select class="custom-select col-md-4" name="typeApartment">
                            <c:forEach var="type" items="${types}">
                                <option>${type}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </c:if>
            <input type="hidden" name="typeApartment" value="${typeApartment}">

            <div class="form-group">
                <label class="col-sm-4 control-label"><fmt:message key="label.includeBreakfast"
                                                                   bundle="${rb}"/>:</label>
                <label class="radio-inline">
                    <input type="radio" name="breakfast" value="да"> <fmt:message key="label.yes" bundle="${rb}"/>
                    <input type="radio" name="breakfast" value="нет"> <fmt:message key="label.not" bundle="${rb}"/>
                </label>
            </div>
            <err:mtg messageError="${errorOrder}"/>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-5">
                    <input type="hidden" name="command" value="reservation"/></br>
                    <button type="submit" name="add" class="btn btn-success"><fmt:message key="label.add"
                                                                                          bundle="${rb}"/></button>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer/footer.jsp"></jsp:include>
</body>
</html>


