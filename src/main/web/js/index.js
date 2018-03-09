function validationRegister() {
    if (document.form.surname.value == "") {
        alert("Пожалуйста, введите Вашу фамилию!");
        return false;
    } else if (document.form.name.value == "") {
        alert("Пожалуйста, введите Ваше имя!");
        return false;
    } else if (document.form.middleName.value == "") {
        alert("Пожалуйста, введите Ваше отчество!");
        return false;
    } else if (document.form.dateBirth.value == "") {
        alert("Пожалуйста, введите дату Вашего рождения!");
        return false;
    } else if (document.form.phone.value == "") {
        alert("Пожалуйста, введите Ваш номер телефона!");
        return false;
    } else if (document.form.email.value == "") {
        alert("Пожалуйста, введите Вашу email!");
        return false;
    }  else if (document.form.login.value == "") {
        alert("Пожалуйста, введите логин!");
        return false;
    } else if (document.form.password.value == "") {
        alert("Пожалуйста, введите пароль!");
        return false;
    } else if (document.form.password2.value == "") {
        alert("Пожалуйста, повторите введенный пароль!");
        return false;
    }

    if (!(/^[A-ZА-Я][a-zа-я]+$/.test(document.form.surname.value))) {
        alert("Фамилия должна содержать только буквы и начинаться с заглавной буквы!");
        return false;
    }

    if (!(/^[A-ZА-Я][a-zа-я]+$/.test(document.form.name.value))) {
        alert("Имя должно содержать только буквы и начинаться с заглавной буквы!");
        return false;
    }

    if (!(/^[A-ZА-Я][a-zа-я]+$/.test(document.form.middleName.value))) {
        alert("Отчество должно содержать только буквы и начинаться с заглавной буквы!");
        return false;
    }

    if (!(/^(\d{4})\-(0\d|1[012])\-([0-2]\d|3[01])$/.test(document.form.dateBirth.value))) {
        alert("Дата рождения введена некорректно!");
        return false;
    }

    var now = new Date();
    var other = new Date(document.form.dateBirth.value);
    var result = Math.floor((now.getTime() - other.getTime()) / 1000 / 60 / 60 / 24 / 30 / 12);

    if (120 < result || result < 7) {
        alert("Некорректный возраст/либо вам меньше 7 лет!");
        return false;
    }

    if (document.form.phone.value.length > 17) {
        alert("Превышение количества символов в номере телефона!");
        return false;
    } else if (!(/^\+|\d[\d\(\)\ -]{4,14}\d$/.test(document.form.phone.value))) {
        alert("Неправильный формат номера телефона!");
        return false;
    }

    if (!(/^[\w-\.]+@[\w-]+\.[a-z]{2,3}$/.test(document.form.email.value))) {
        alert("Некорректный email адрес!");
        return false;
    }

    if (document.form.login.value.length < 5) {
        alert("Имя пользователя должно содержать больше 5 символов!");
        return false;
    } else if (!(/^[A-Za-z][A-Za-z0-9_]+$/.test(document.form.login.value))) {
        alert("Имя пользователя должно содержать буквы латинского алфавита!");
        return false;
    }

    if (document.form.password.value.length < 5) {
        alert("Пароль должен содержать не менее 5 символов!");
        return false;
    } else if (!(/^(?=.*[a-z])(?=.*[A-Z]).{4,}$/.test(document.form.password.value))) {
        alert("Пароль должен содержать не менее одной буквы в каждом регистре и не менее одной цифры!")
        return false;
    }

    if (document.form.password.value != document.form.password2.value) {
        alert("Пароли не совпадают!");
        return false;
    }
}

function validationSingUp() {
    if (document.form.login.value == "") {
        alert("Пожалуйста, введите Ваш логин!");
        return false;
    } else if (document.form.password.value == "") {
        alert("Пожалуйста, введите Ваш пароль!");
        return false;
    }

    if (document.form.login.value.length < 5) {
        alert("Имя пользователя должно содержать больше 5 символов!");
        return false;
    } else if (!(/^[A-Za-z][A-Za-z0-9_]+$/.test(document.form.login.value))) {
        alert("Имя пользователя должно содержать буквы латинского алфавита!");
        return false;
    }

    if (document.form.password.value.length < 5) {
        alert("Пароль должен содержать не менее 5 символов!");
        return false;
    } else if (!(/^(?=.*[a-z])(?=.*[A-Z]).{4,}$/.test(document.form.password.value))) {
        alert("Пароль должен содержать не менее одной буквы в каждом регистре и не менее одной цифры!")
        return false;
    }
}

function validationForgotPass() {
    if (document.form.login.value == "") {
        alert("Пожалуйста, введите Ваш логин!");
        return false;
    } else if (document.form.email.value == "") {
        alert("Пожалуйста, введите Ваш email!");
        return false;
    }

    if (document.form.login.value.length < 5) {
        alert("Имя пользователя должно содержать больше 5 символов!");
        return false;
    } else if (!(/^[A-Za-z][A-Za-z0-9_]+$/.test(document.form.login.value))) {
        alert("Имя пользователя должно содержать буквы латинского алфавита!");
        return false;
    }

    if (!(/^[\w-\.]+@[\w-]+\.[a-z]{2,3}$/.test(document.form.email.value))) {
        alert("Некорректный email адрес!");
        return false;
    }
}

function validationSearch() {
    if (document.form.min.value == "") {
        alert("Пожалуйста, введите минимальную цену!");
        return false;
    } else if (document.form.max.value == "") {
        alert("Пожалуйста, введите максимальную цену!");
        return false;
    }

    if (!(/[0-9]$/.test(document.form.min.value))) {
        alert("Цена должна содержать только число!");
        return false;
    }

    if (!(/[0-9]$/.test(document.form.max.value))) {
        alert("Цена должна содержать только число!");
        return false;
    }

    if (document.form.min.value > document.form.max.value) {
        alert("Неккоректные данные(минимальная цена больше максимальной)!");
        return false;
    }

    if (document.form.min.value == document.form.max.value) {
        alert("Неккоректные данные!Цены равны");
        return false;
    }
}

function validationService() {
    if (document.form.typeService.value == "") {
        alert("Пожалуйста, введите тип услуги!");
        return false;
    }  else if (document.form.description.value == "") {
        alert("Пожалуйста, введите описание услуги!");
        return false;
    }  else if (document.form.image.value == "") {
        alert("Пожалуйста, выберите файл!");
        return false;
    }

    if (!(/[A-ZА-Я0-9][a-zа-я0-9\s]+$/.test(document.form.typeService.value))) {
        alert("Название должно содержать только буквы и знаки пробела!");
        return false;
    }

    if (!(/[A-ZА-Я0-9][a-zа-я0-9\-\,\.\"\:\;\(\)\s]+$/.test(document.form.description.value))) {
        alert("Описание должно содержать только буквы и знаки пробела!");
        return false;
    }
}

function validationEditService() {
    if (document.form.typeService.value == "") {
        alert("Пожалуйста, введите тип услуги!");
        return false;
    }  else if (document.form.description.value == "") {
        alert("Пожалуйста, введите описание услуги!");
        return false;
    }

    if (!(/[A-ZА-Я0-9][a-zа-я0-9\s]+$/.test(document.form.typeService.value))) {
        alert("Название должно содержать только буквы и знаки пробела!");
        return false;
    }

    if (!(/[A-ZА-Я0-9][a-zа-я0-9\-\,\.\"\:\;\(\)\s]+$/.test(document.form.description.value))) {
        alert("Описание должно содержать только буквы и знаки пробела!");
        return false;
    }
}

function validationTypeRoom() {
    if (document.form.typeRoom.value == "") {
        alert("Пожалуйста, введите тип номера!");
        return false;
    } else if(document.form.capacity.selectedIndex==0){
        alert("Пожалуйста, выберите вместимость типа номера!");
        document.form.capacity.focus();
        return false;
    } else if (document.form.price.value == "") {
        alert("Пожалуйста, введите цену типа номера!");
        return false;
    } else if (document.form.description.value == "") {
        alert("Пожалуйста, введите описание типа номера!");
        return false;
    } else if (document.form.image.value == "") {
            alert("Пожалуйста, выберите файл!");
            return false;
    }

    if (!(/[\w\+\s]+$/.test(document.form.typeRoom.value))) {
        alert("Название типа должно содержать только буквы и знаки пробела!");
        return false;
    }

    if (!(/[0-9]$/.test(document.form.capacity.value))) {
        alert("Вместимость должна содержать только число!");
        return false;
    }

    if (!(/\d+(\.\d{0,})?$/.test(document.form.price.value))) {
        alert("Цена должна содержать только цифры и знак '.'!");
        return false;
    }

    if (!(/[\W|\w|\s]+$/.test(document.form.description.value))) {
        alert("Описание должно содержать только буквы и знаки пробела!");
        return false;
    }
}

function validationEditTypeRoom() {
    if (document.form.typeRoom.value == "") {
        alert("Пожалуйста, введите тип номера!");
        return false;
    }  else if (document.form.price.value == "") {
        alert("Пожалуйста, введите цену типа номера!");
        return false;
    } else if (document.form.description.value == "") {
        alert("Пожалуйста, введите описание типа номера!");
        return false;
    }

    if (!(/[\w\+\s]+$/.test(document.form.typeRoom.value))) {
        alert("Название типа должно содержать только буквы и знаки пробела!");
        return false;
    }

    if (!(/[0-9]$/.test(document.form.capacity.value))) {
        alert("Вместимость должна содержать только число!");
        return false;
    }

    if (!(/\d+(\.\d{0,})?$/.test(document.form.price.value))) {
        alert("Цена должна содержать только цифры и знак '.'!");
        return false;
    }

    if (!(/[A-ZА-Я0-9][a-zа-я0-9\-\,\.\"\:\;\(\)\s]+$/.test(document.form.description.value))) {
        alert("Описание должно содержать только буквы и знаки пробела!");
        return false;
    }
}


function validationUserInfo() {
    if (document.form.surname.value == "") {
        alert("Пожалуйста, введите Вашу фамилию!");
        return false;
    } else if (document.form.name.value == "") {
        alert("Пожалуйста, введите Ваше имя!");
        return false;
    } else if (document.form.middleName.value == "") {
        alert("Пожалуйста, введите Ваше отчество!");
        return false;
    } else if (document.form.dateBirth.value == "") {
        alert("Пожалуйста, введите дату Вашего рождения!");
        return false;
    } else if (document.form.phone.value == "") {
        alert("Пожалуйста, введите Ваш номер телефона!");
        return false;
    }

    if (!(/^[A-ZА-Я][a-zа-я]+$/.test(document.form.surname.value))) {
        alert("Фамилия должна содержать только буквы и начинаться с заглавной буквы!");
        return false;
    }

    if (!(/^[A-ZА-Я][a-zа-я]+$/.test(document.form.name.value))) {
        alert("Имя должно содержать только буквы и начинаться с заглавной буквы!");
        return false;
    }

    if (!(/^[A-ZА-Я][a-zа-я]+$/.test(document.form.middleName.value))) {
        alert("Отчество должно содержать только буквы и начинаться с заглавной буквы!");
        return false;
    }

    if (!(/^(\d{4})\-(0\d|1[012])\-([0-2]\d|3[01])$/.test(document.form.dateBirth.value))) {
        alert("Дата рождения введена некорректно!");
        return false;
    }

    var now = new Date();
    var other = new Date(document.form.dateBirth.value);
    var result = Math.floor((now.getTime() - other.getTime()) / 1000 / 60 / 60 / 24 / 30 / 12);

    if (120 < result || result < 7) {
        alert("Некорректный возраст/либо вам меньше 7 лет!");
        return false;
    }

    if (document.form.phone.value.length > 17) {
        alert("Превышение количества символов в номере телефона!");
        return false;
    } else if (!(/^\+|\d[\d\(\)\ -]{4,14}\d$/.test(document.form.phone.value))) {
        alert("Неправильный формат номера телефона!");
        return false;
    }
}

function validationRoom() {
    if (document.form.roomNumber.value == "") {
        alert("Пожалуйста, введите № номера!");
        return false;
    }

    if (!(/[0-9]+$/.test(document.form.roomNumber.value))) {
        alert("Некорректный № номера!Поле содержит только цифры");
        return false;
    }
}

function validationOrder() {
    if (document.form.arrival_date.value == "") {
        alert("Пожалуйста, введите дату прибытия!");
        return false;
    } else if (document.form.departure_date.value == "") {
        alert("Пожалуйста, введите дату отъезда!");
        return false;
    } else if(document.form.noAdults.selectedIndex==0){
        alert("Пожалуйста, выберите кол-во взрослых");
        document.form.noAdults.focus();
        return false;
    } else if(document.form.noChildren.selectedIndex==0){
        alert("Пожалуйста, выберите кол-во детей");
        document.form.noChildren.focus();
        return false;
    } else if (document.form.breakfast.value == "") {
        alert("Пожалуйста, выберите пункт завтрака!");
        return false;
    }

    if (!(/^(\d{4})\-(0\d|1[012])\-([0-2]\d|3[01])$/.test(document.form.arrival_date.value))) {
        alert("Дата прибытия введена некорректно!");
        return false;
    }

    if (!(/^(\d{4})\-(0\d|1[012])\-([0-2]\d|3[01])$/.test(document.form.departure_date.value))) {
        alert("Дата отъезда введена некорректно!");
        return false;
    }

    var now = new Date();
    var first = new Date(document.form.arrival_date.value);
    var second = new Date(document.form.departure_date.value);
    var result = Math.floor((second.getTime() - first.getTime()) / 1000 / 60 / 60 / 24 );

    if(now.getTime() >= first.getTime() || now.getTime() > second.getTime()){
        alert("Дата введена некорректно!");
        return false;
    }

    if ( result <= 0) {
        alert("Дата введена некорректно!Даты равны!");
        return false;
    }
}

function validationChangePass() {
    if (document.form.lastPassword.value == "") {
        alert("Пожалуйста, введите Ваш старый пароль!");
        return false;
    } else if (document.form.newPassword.value == "") {
        alert("Пожалуйста, введите Ваш новый пароль!");
        return false;
    } else if (document.form.repeatNewPassword.value == "") {
        alert("Пожалуйста, введите Ваш повторный пароль!");
        return false;
    }

    if (document.form.lastPassword.value.length < 5) {
        alert("Пароль должен содержать не менее 5 символов!");
        return false;
    } else if (!(/^(?=.*[a-z])(?=.*[A-Z]).{4,}$/.test(document.form.lastPassword.value))) {
        alert("Пароль должен содержать не менее одной буквы в каждом регистре и не менее одной цифры!")
        return false;
    }

    if (document.form.newPassword.value.length < 5) {
        alert("Пароль должен содержать не менее 5 символов!");
        return false;
    } else if (!(/^(?=.*[a-z])(?=.*[A-Z]).{4,}$/.test(document.form.newPassword.value))) {
        alert("Пароль должен содержать не менее одной буквы в каждом регистре и не менее одной цифры!")
        return false;
    }

    if (document.form.repeatNewPassword.value.length < 5) {
        alert("Пароль должен содержать не менее 5 символов!");
        return false;
    } else if (!(/^(?=.*[a-z])(?=.*[A-Z]).{4,}$/.test(document.form.repeatNewPassword.value))) {
        alert("Пароль должен содержать не менее одной буквы в каждом регистре и не менее одной цифры!")
        return false;
    }

    if (document.form.newPassword.value != document.form.repeatNewPassword.value) {
        alert("Пароли не совпадают!");
        return false;
    }
}

