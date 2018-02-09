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

        <h1 class="serviceH1"><fmt:message key="label.addService" bundle="${rb}"/></h1>
        <p class="line"></p>

        <form class="addServiceForm" action="${pageContext.request.contextPath}/Controller" method="POST"
              name="form" onsubmit="return validationService();">
            <div class="form-group">
                <label class="col-sm-2 control-label"><fmt:message key="label.serviceName" bundle="${rb}"/>:</label>
                <div class="col-sm-7">
                    <input type="text" class="form-control" name="typeService">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label"><fmt:message key="label.description" bundle="${rb}"/>:</label>
                <div class="col-sm-8">
                    <textarea type="text" class="form-control" name="description"></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-5">
                    <span class="btn btn-default btn-file">
                        <input type="file" name="image"><fmt:message key="label.browsePhoto" bundle="${rb}"/>
                    </span>
                </div>
            </div>
            <err:mtg messageError="${errorService}"/>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-10">
                    <input type="hidden" name="command" value="add_service"/>
                    </br>
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
