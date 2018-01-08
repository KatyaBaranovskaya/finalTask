<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <meta http-equiv="CONTENT-TYPE" content="text/html"; charset="UTF-8"/>
    <title>Login</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="shortcut icon" href="../resources/reg.png" type="image/x-icon">
    <script  src="../js/index.js"></script>
</head>
<body>
<div class="formholder" style="top: 180px; ">
    <div class="randompad">
        <p>Вход</p>
        <form action="/Controller" method="POST">
            <input type="hidden" name="command" value="login" />
            <label name="email">Логин</label>
            <input type="text" name="login"/>
            <label name="password">Пароль</label>
            <input type="password" name="password"/>
            <br/><input type="submit" value="Войти" />
        </form>
    </div>
</div>
</body>
</html>
