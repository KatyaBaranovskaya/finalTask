function validation() {
    // if (document.form.surname.value == "") {
    //     alert("Пожалуйста, введите Вашу фамилию!");
    //     return false;
    // } else if (document.form.name.value == "") {
    //     alert("Пожалуйста, введите Ваше имя!");
    //     return false;
    // } else if (document.form.middleName.value == "") {
    //     alert("Пожалуйста, введите Ваше отчество!");
    //     return false;
    // } else if (document.form.date.value == "") {
    //     alert("Пожалуйста, введите дату Вашего рождения!");
    //     return false;
    // } else if (document.form.mail.value == "") {
    //     alert("Пожалуйста, введите Вашу email!");
    //     return false;
    // } else if (document.form.phone.value == "") {
    //     alert("Пожалуйста, введите Ваш номер телефона!");
    //     return false;
    // } else if (document.form.login.value == "") {
    //     alert("Пожалуйста, введите логин!");
    //     return false;
    // } else if (document.form.password.value == "") {
    //     alert("Пожалуйста, введите пароль!");
    //     return false;
    // } else if (document.form.password2.value == "") {
    //     alert("Пожалуйста, повторите введенный пароль!");
    //     return false;
    // }
    //
    // if (!(/^[A-ZА-Я][a-zа-я]+$/.test(document.form.surname.value))) {
    //     alert("Фамилия должна содержать только буквы и начинаться с заглавной буквы!");
    //     return false;
    // }
    //
    // if (!(/^[A-ZА-Я][a-zа-я]+$/.test(document.form.name.value))) {
    //     alert("Имя должно содержать только буквы и начинаться с заглавной буквы!");
    //     return false;
    // }
    //
    // if (!(/^[A-ZА-Я][a-zа-я]+$/.test(document.form.middleName.value))) {
    //     alert("Отчество должно содержать только буквы и начинаться с заглавной буквы!");
    //     return false;
    // }
    //
    // var now = new Date();
    // var other = new Date(document.form.date.value);
    // var result = Math.floor((now.getTime()-other.getTime())/1000/60/60/24/30/12);
    //
    // if (120 < result || result < 7) {
    //     alert("Некорректный возраст/либо вам меньше 7 лет!");
    //     return false;
    // }
    //
    // if (!(/^[\w-\.]+@[\w-]+\.[a-z]{2,3}$/.test(document.form.mail.value))) {
    //     alert("Некорректный email адрес!");
    //     return false;
    // }
    //
    // if (document.form.phone.value.length > 17 ) {
    //     alert("Превышение количества символов в номере телефона!");
    //     return false;
    // } else if (!(/^\+|\d[\d\(\)\ -]{4,14}\d$/.test(document.form.phone.value))) {
    //     alert("Неправильный формат номера телефона!");
    //     return false;
    // }
    //
    // if (document.form.login.value.length < 5) {
    //     alert("Имя пользователя должно содержать больше 5 символов!");
    //     return false;
    // } else if (!(/^[A-Za-z][A-Za-z0-9_]+$/.test(document.form.login.value))) {
    //     alert("Имя пользователя должно содержать буквы латинского алфавита!");
    //     return false;
    // }
    //
    // if (document.form.password.value.length < 7) {
    //     alert("Пароль должен содержать не менее 7 символов!");
    //     return false;
    // } else if (!(/^(?=.*[a-z])(?=.*[A-Z]).{4,}$/.test(document.form.password.value))) {
    //     alert("Пароль должен содержать не менее одной буквы в каждом регистре и не менее одной цифры!")
    //     return false;
    // }
    //
    // if (document.form.password.value != document.form.password2.value) {
    //     alert("Пароли не совпадают!");
    //     return false;
    // }

}

var arr = new Array();
var flag = 0;

function third(nodeId, deep) {
    if(flag == 0) {
        var toClone = document.getElementById(nodeId);
        var clonedNode = toClone.cloneNode(deep);
        arr.push(clonedNode);
        document.getElementById("mail").appendChild(clonedNode);
    }
    flag = 1;
};

function fourth() {
    flag = 0;
    var removableNode = arr.pop();
    document.getElementById("mail").removeChild(removableNode);
};
