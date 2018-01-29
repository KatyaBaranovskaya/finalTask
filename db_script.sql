-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema hotel
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hotel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hotel` DEFAULT CHARACTER SET utf8 ;
USE `hotel` ;

-- -----------------------------------------------------
-- Table `hotel`.`rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`rooms` (
  `room_number` INT(11) NOT NULL COMMENT 'Поле содержит \"ID типа номера\".',
  `type_room` VARCHAR(45) NOT NULL COMMENT 'Поле содержит тип номера (люкс, полулюкс и тд).',
  `class_apartment` INT(11) NOT NULL,
  `capacity` INT(11) NOT NULL COMMENT 'Поле содержит вместимость номера, т.е. максимальное количество человек, которые могут проживать в номере.',
  `price` DECIMAL(18,10) NOT NULL COMMENT 'Поле содержит цену номера за сутки.',
  `status` VARCHAR(45) NOT NULL,
  `picture` VARCHAR(45) NULL DEFAULT NULL,
  `description` TEXT NOT NULL COMMENT 'В данном поле содержится описание номера.',
  PRIMARY KEY (`room_number`),
  INDEX `type_idx` (`type_room` ASC)  COMMENT 'Индекс, который состоит из типа номера, т.к по данному полю наиболее часто выполняется запрос на чтение.',
  INDEX `capacity_price_idx` (`capacity` ASC, `price` ASC)  COMMENT 'Индекс, который состоит из вместимости и цены номера, т.к по данным полям наиболее часто выполняется запрос на поиск.')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Данная таблица предназначена для хранения данных о номерах и их видах. Все поля данной таблицы являются не нулевыми.';


-- -----------------------------------------------------
-- Table `hotel`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`roles` (
  `id_role` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Поле содержит \"ID роли\".',
  `role_name` VARCHAR(45) NOT NULL COMMENT 'Поле содержит имя роли, а именно администратор или клиент.',
  PRIMARY KEY (`id_role`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COMMENT = 'Данная таблица содержит информацию о ролях пользователей системы( админестратор/клиента). Таблица содержит первичный ключ \"ID роли\". Все поля являются не нулевыми.';


-- -----------------------------------------------------
-- Table `hotel`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`users` (
  `id_user` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Поле содержит \"ID клиента\".',
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `login` VARCHAR(45) NOT NULL COMMENT 'Поле содержит логин пользователя.',
  `password` VARCHAR(45) NOT NULL COMMENT 'Поле содержит пароль пользователя.',
  `id_role` INT(11) UNSIGNED NOT NULL DEFAULT '2' COMMENT 'Поле является внешним ключом и содержит значение \"ID роли\". С помощью данного поля осуществляется связь с таблицей \"roles\", тем самым определяя является пользователь программы \"клиентом\" или \"администратором\". Если пользователь - администратор, то не все поля будут обязательны для заполнения.',
  `surname` VARCHAR(45) NOT NULL COMMENT 'Поле содержит фамилию клиента.',
  `name` VARCHAR(45) NOT NULL COMMENT 'Поле содержит имя клиента.',
  `middle_name` VARCHAR(45) NOT NULL COMMENT 'Поле содержит отчество клиента.',
  `date_birth` DATE NULL DEFAULT NULL COMMENT 'Поле содержит дату рождения клиента.',
  `telephone` VARCHAR(45) NULL DEFAULT NULL COMMENT 'Поле содержит телефон клиента.',
  `avatar` VARCHAR(45) NULL DEFAULT 'defaultAvatar.png',
  PRIMARY KEY (`id_user`),
  INDEX `surname_name_middlename_idx` (`surname` ASC, `name` ASC, `middle_name` ASC)  COMMENT 'Индекс состоит из нескольких полей(фамилия, имя, отчество), т.к по данным полям наиболее часто выполняется запрос на чтение.',
  INDEX `id_role_fk_idx` (`id_role` ASC),
  CONSTRAINT `id_role_fk`
    FOREIGN KEY (`id_role`)
    REFERENCES `hotel`.`roles` (`id_role`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8
COMMENT = 'Данная таблица предназначена для хранения персональных данных о всех клиентах. Таблица содержит первичный ключ - \"ID клиента\". Все поля данной таблицы являются не нулевыми.';


-- -----------------------------------------------------
-- Table `hotel`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`orders` (
  `id_order` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Поле содержит \"ID заказа\".',
  `id_user` INT(11) UNSIGNED NOT NULL COMMENT 'Поле содержит \"ID клиента\", который осуществил этот заказ.\nПоле является внешним ключом, предназначено для хранения значения первичного ключа таблицы \"clients\" с целью организации связи между этими таблицами.',
  `room_number` INT(11) NULL DEFAULT NULL COMMENT 'Поле содержит номер апартамента, который предоставлен клиенту.\nПоле является внешним ключом, предназначено для хранения значения первичного ключа таблицы \"rooms\" с целью организации связи между этими таблицами.',
  `arrival_date` DATE NOT NULL COMMENT 'Поле содержит дату заезда  клиента.',
  `departure_date` DATE NOT NULL COMMENT 'Поле содержит дату выезда  клиента.',
  `no_persons` INT(11) NOT NULL,
  `class_apartment` INT(11) NOT NULL,
  `price` DECIMAL(18,10) NULL DEFAULT NULL COMMENT 'Поле содержит цену за проживание.',
  `status` VARCHAR(45) NOT NULL DEFAULT 'принят',
  PRIMARY KEY (`id_order`),
  INDEX `id_client_fk_idx` (`id_user` ASC),
  INDEX `id_room_number_fk_idx` (`room_number` ASC),
  CONSTRAINT `id_room_number_fk`
    FOREIGN KEY (`room_number`)
    REFERENCES `hotel`.`rooms` (`room_number`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `id_user_fk`
    FOREIGN KEY (`id_user`)
    REFERENCES `hotel`.`users` (`id_user`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8
COMMENT = 'Данная таблица предназначена для хранения обработанных заявок клиентов, т.е. каждая заявка уже подтверждена администратором и имеет статус заказа. Таблица содержит первичный ключ -\"ID заказа\". Все поля данной таблицы являются не нулевыми.';


-- -----------------------------------------------------
-- Table `hotel`.`services`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`services` (
  `id_service` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Поле содержит \"ID предоставляемой услуги\".',
  `type_service` VARCHAR(45) NOT NULL COMMENT 'Поле содержит тип предоставляемой услуги( питание, массаж и тд).',
  `price` DECIMAL(18,10) NOT NULL COMMENT 'Поле содержит цену за одноразовое использование услуги.',
  PRIMARY KEY (`id_service`),
  INDEX `type_idx` (`type_service` ASC)  COMMENT 'Индекс, который включает в себя тип предоставляемых услуг в отеле. Используем индекс для этого поля, т.к по данному полю наиболее часто выполняется запрос на чтение.')
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8
COMMENT = 'Данная таблица предназначена для хранения всех услуг, которые предоставляются отелем. Таблица содержит первичный ключ - \"ID услуги\". Все поля данной таблицы являются не нулевыми.';


-- -----------------------------------------------------
-- Table `hotel`.`order_service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`order_service` (
  `id_order` INT(10) UNSIGNED NOT NULL,
  `id_service` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id_order`, `id_service`),
  INDEX `id_service_fk_idx` (`id_service` ASC),
  CONSTRAINT `id_order_fk`
    FOREIGN KEY (`id_order`)
    REFERENCES `hotel`.`orders` (`id_order`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `id_service_fk`
    FOREIGN KEY (`id_service`)
    REFERENCES `hotel`.`services` (`id_service`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
