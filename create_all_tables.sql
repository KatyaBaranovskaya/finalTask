CREATE TABLE `roles` (
  `id_role` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Поле содержит "ID роли".',
  `role_name` varchar(45) NOT NULL COMMENT 'Поле содержит имя роли, а именно администратор или клиент.',
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Данная таблица содержит информацию о ролях пользователей системы( админестратор/клиента). Таблица содержит первичный ключ "ID роли". Все поля являются не нулевыми.';

CREATE TABLE `clients` (
  `id_client` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Поле содержит "ID клиента".',
  `email` varchar(45) DEFAULT NULL,
  `login` varchar(45) NOT NULL COMMENT 'Поле содержит логин пользователя.',
  `password` varchar(45) NOT NULL COMMENT 'Поле содержит пароль пользователя.',
  `id_role` int(11) unsigned NOT NULL COMMENT 'Поле является внешним ключом и содержит значение "ID роли". С помощью данного поля осуществляется связь с таблицей "roles", тем самым определяя является пользователь программы "клиентом" или "администратором". Если пользователь - администратор, то не все поля будут обязательны для заполнения.',
  `surname` varchar(45) NOT NULL COMMENT 'Поле содержит фамилию клиента.',
  `name` varchar(45) NOT NULL COMMENT 'Поле содержит имя клиента.',
  `middle_name` varchar(45) NOT NULL COMMENT 'Поле содержит отчество клиента.',
  `date_birth` date DEFAULT NULL COMMENT 'Поле содержит дату рождения клиента.',
  `passport` varchar(45) DEFAULT NULL COMMENT 'Поле содержит паспорт (серия и номер)  клиента.',
  `telephone` varchar(45) DEFAULT NULL COMMENT 'Поле содержит телефон клиента.',
  PRIMARY KEY (`id_client`),
  KEY `surname_name_middlename_idx` (`surname`,`name`,`middle_name`) COMMENT 'Индекс состоит из нескольких полей(фамилия, имя, отчество), т.к по данным полям наиболее часто выполняется запрос на чтение.',
  KEY `passport_idx` (`passport`) COMMENT 'Используем данный индекс, т.к. существует необходимость поиска по данному полю.',
  KEY `id_role_fk_idx` (`id_role`),
  CONSTRAINT `id_role_fk` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='Данная таблица предназначена для хранения персональных данных о всех клиентах. Таблица содержит первичный ключ - "ID клиента". Все поля данной таблицы являются не нулевыми.';

CREATE TABLE `types_room` (
  `id_type` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Поле содержит "ID типа номера".',
  `type_room` varchar(45) NOT NULL COMMENT 'Поле содержит тип номера (люкс, полулюкс и тд).',
  `capacity` int(11) NOT NULL COMMENT 'Поле содержит вместимость номера, т.е. максимальное количество человек, которые могут проживать в номере.',
  `price` decimal(18,10) NOT NULL COMMENT 'Поле содержит цену номера за сутки.',
  `picture` varchar(45) DEFAULT NULL,
  `description` text NOT NULL COMMENT 'В данном поле содержится описание номера.',
  PRIMARY KEY (`id_type`),
  KEY `type_idx` (`type_room`) COMMENT 'Индекс, который состоит из типа номера, т.к по данному полю наиболее часто выполняется запрос на чтение.',
  KEY `capacity_price_idx` (`capacity`,`price`) COMMENT 'Индекс, который состоит из вместимости и цены номера, т.к по данным полям наиболее часто выполняется запрос на поиск.'
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='Данная таблица предназначена для хранения данных о видах номеров отеля. Таблица содержит первичный ключ, который хранит в себе "ID типа  номера". Все поля данной таблицы являются не нулевыми.';

CREATE TABLE `rooms` (
  `room_number` int(11) NOT NULL COMMENT 'Поле содержит номер апартамента отеля.',
  `id_type` int(11) unsigned NOT NULL COMMENT 'Поле содержит "ID типа номера".\nПоле является внешним ключом, предназначено для хранения значения первичного ключа таблицы "types_room" с целью организации связи между этими таблицами.',
  `status` varchar(45) NOT NULL COMMENT 'Поле содержит статус номера, т.е. забронирован или свободен.',
  PRIMARY KEY (`room_number`),
  KEY `status_idx` (`status`) COMMENT 'Индекс, который состоит из статуса номера, т.к по данному полю наиболее часто выполняется запрос свободен номер или нет.',
  KEY `id_type_fk_idx` (`id_type`),
  CONSTRAINT `id_type_fk` FOREIGN KEY (`id_type`) REFERENCES `types_room` (`id_type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Данная таблица предназначена для хранения данных о номерах отеля. Таблица содержит первичный ключ, который хранит в себе номер апартамента, а также поля тип номера и его статус. Все поля данной таблицы являются не нулевыми.';

CREATE TABLE `orders` (
  `id_order` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Поле содержит "ID заказа".',
  `id_client` int(11) unsigned NOT NULL COMMENT 'Поле содержит "ID клиента", который осуществил этот заказ.\nПоле является внешним ключом, предназначено для хранения значения первичного ключа таблицы "clients" с целью организации связи между этими таблицами.',
  `room_number` int(11) NOT NULL COMMENT 'Поле содержит номер апартамента, который предоставлен клиенту.\nПоле является внешним ключом, предназначено для хранения значения первичного ключа таблицы "rooms" с целью организации связи между этими таблицами.',
  `arrival_date` date NOT NULL COMMENT 'Поле содержит дату заезда  клиента.',
  `departure_date` date NOT NULL COMMENT 'Поле содержит дату выезда  клиента.',
  `price` decimal(18,10) NOT NULL COMMENT 'Поле содержит цену за проживание.',
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id_order`),
  KEY `room_number_fk_idx` (`room_number`),
  KEY `id_client_fk_idx` (`id_client`),
  CONSTRAINT `id_client_fk` FOREIGN KEY (`id_client`) REFERENCES `clients` (`id_client`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `room_number_fk` FOREIGN KEY (`room_number`) REFERENCES `rooms` (`room_number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='Данная таблица предназначена для хранения обработанных заявок клиентов, т.е. каждая заявка уже подтверждена администратором и имеет статус заказа. Таблица содержит первичный ключ -"ID заказа". Все поля данной таблицы являются не нулевыми.';

CREATE TABLE `services` (
  `id_service` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Поле содержит "ID предоставляемой услуги".',
  `type_service` varchar(45) NOT NULL COMMENT 'Поле содержит тип предоставляемой услуги( питание, массаж и тд).',
  `price` decimal(18,10) NOT NULL COMMENT 'Поле содержит цену за одноразовое использование услуги.',
  PRIMARY KEY (`id_service`),
  KEY `type_idx` (`type_service`) COMMENT 'Индекс, который включает в себя тип предоставляемых услуг в отеле. Используем индекс для этого поля, т.к по данному полю наиболее часто выполняется запрос на чтение.'
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='Данная таблица предназначена для хранения всех услуг, которые предоставляются отелем. Таблица содержит первичный ключ - "ID услуги". Все поля данной таблицы являются не нулевыми.';

CREATE TABLE `order_service` (
  `id_order` int(10) unsigned NOT NULL,
  `id_service` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_order`,`id_service`),
  KEY `id_service_fk_idx` (`id_service`),
  CONSTRAINT `id_order_fk` FOREIGN KEY (`id_order`) REFERENCES `orders` (`id_order`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_service_fk` FOREIGN KEY (`id_service`) REFERENCES `services` (`id_service`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
