<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Login</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="shortcut icon" href="../resources/reg.png" type="image/x-icon">
    <script  src="../js/index.js"></script>
</head>
<body>
<img class="svgClass" src="../resources/reg.png" alt="hotel">
<div class="formholder">
    <div class="randompad">
        <p>Регистрация</p>
        <form  action="/Controller" method="POST" name="form" onsubmit="return validation();">
            <input type="hidden" name="command" value="registration" />
            <input type="text" name="surname" placeholder="Фамилия"/>
            <input type="text" name="name" placeholder="Имя" />
            <input type="text" name="middleName" placeholder="Отчество" />
            <label>Дата рождения</label>
            <input type="date" name="date"/>
            <input type="text" name="phone" placeholder="Телефон"/>
            <input id="coppy" type="email" name="mail" placeholder="Email"/>
            <input type="text" name="login" placeholder="Логин"/>
            <input type="password" name="password"  placeholder="Пароль"/>
            <input type="password" name="password2" placeholder="Повторите пароль"/>
            <br/><input type="submit" value="Зарегистрироваться" />
        </form>
    </div>
</div>
<button type='button' id="butAdd" class="but" onclick="third('coppy',true)">Добавить</button><br/>
<button type='button' id="butDelete" class="but" onclick="fourth()">Удалить</button>
<div id="mail" class="newMail"></div>
</body>
</html>
