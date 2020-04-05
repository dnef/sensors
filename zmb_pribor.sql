-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Апр 05 2020 г., 13:06
-- Версия сервера: 8.0.15
-- Версия PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `zmb_pribor`
--
CREATE DATABASE IF NOT EXISTS `zmb_pribor` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `zmb_pribor`;

-- --------------------------------------------------------

--
-- Структура таблицы `app_user`
--

DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `id` bigint(20) NOT NULL,
  `sso_id` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `patronymic` varchar(30) DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `app_user`
--

INSERT INTO `app_user` (`id`, `sso_id`, `password`, `first_name`, `last_name`, `patronymic`, `email`, `enabled`) VALUES
(1, 'Dima', '$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm', 'Нефедьев', 'Дмитрий', 'Викторович', 'dima@xyz.com', 1),
(9, 'testKIP', '$2a$10$LO8YpRoy5HaZgz0rFRATG.9an2l64wt2cCvUF1lVjYlAVs6XOTjuW', 'FirstName', 'LastName', 'Patronymic', 'test@email.ru', 1),
(10, 'testUSER', '$2a$10$aOmRSTLqXot.ttloJDkWseNTOgiG62jApzRM2RKeIDWfHctxbBaDa', 'first', 'last', '', 'test@email.ru', 1),
(12, 'Admin', '$2a$10$hdps4uWfD3M9dKmwfEZJW.MRKQCbBNSZ8ciOipWyYldq7EjBlDAEq', 'admin', 'admin', 'admin', 'admin@xyz.com', 1),
(13, 'User2', '$2a$10$Y/KjZ8lLRA9JruDc6w2eQeGF77WPBFflyC.XcL9pO9RZw.DGZjJa2', 'Фамилия', 'Имя', 'Отчество', 'usr@xyz.com', 0);

-- --------------------------------------------------------

--
-- Структура таблицы `app_user_user_profile`
--

DROP TABLE IF EXISTS `app_user_user_profile`;
CREATE TABLE `app_user_user_profile` (
  `user_id` bigint(20) NOT NULL,
  `user_profile_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `app_user_user_profile`
--

INSERT INTO `app_user_user_profile` (`user_id`, `user_profile_id`) VALUES
(10, 1),
(13, 1),
(1, 2),
(12, 2),
(9, 3);

-- --------------------------------------------------------

--
-- Структура таблицы `archive`
--

DROP TABLE IF EXISTS `archive`;
CREATE TABLE `archive` (
  `id_archive` int(11) NOT NULL,
  `location_id` int(11) NOT NULL,
  `sensor_id` int(11) NOT NULL,
  `install_date` datetime NOT NULL,
  `note` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `archive`
--

INSERT INTO `archive` (`id_archive`, `location_id`, `sensor_id`, `install_date`, `note`) VALUES
(3, 1, 17, '2019-06-30 14:18:18', NULL),
(4, 8, 17, '2019-06-30 14:19:04', NULL),
(5, 1, 17, '2019-06-30 14:19:40', NULL),
(6, 7, 17, '2019-06-30 14:19:40', NULL),
(7, 1, 3, '2019-06-30 15:05:17', NULL),
(8, 1, 27, '2019-08-08 17:50:19', 'снят'),
(9, 3, 27, '2019-08-08 17:50:19', 'установлен'),
(10, 1, 27, '2019-08-08 17:51:29', 'снят'),
(11, 2, 27, '2019-08-08 17:51:29', 'установлен'),
(12, 1, 27, '2019-08-08 18:29:23', 'перемещен с установки на склад'),
(13, 4, 3, '2019-08-08 18:40:40', 'установлен'),
(14, 1, 3, '2019-08-08 18:42:23', 'перемещен с установки на склад'),
(15, 1, 26, '2019-08-08 18:54:56', 'перемещен с установки на склад'),
(16, 4, 26, '2019-08-08 18:54:56', 'установлен'),
(17, 1, 26, '2019-08-08 18:57:00', 'перемещен с установки на склад'),
(18, 4, 27, '2019-08-08 19:05:00', 'установлен'),
(19, 5, 3, '2019-08-08 21:52:29', 'установлен'),
(20, 1, 3, '2019-08-08 21:55:03', 'перемещен с установки на склад'),
(22, 1, 1, '2019-08-08 23:24:35', 'перемещен с установки на склад'),
(23, 4, 1, '2019-08-08 23:24:35', 'установлен'),
(40, 5, 31, '2019-08-09 20:09:05', 'установлен'),
(42, 1, 2, '2019-08-17 12:31:57', 'перемещен с установки на склад'),
(43, 3, 2, '2019-08-17 12:31:58', 'установлен'),
(46, 6, 17, '2019-08-17 12:42:40', 'установлен'),
(47, 1, 2, '2019-08-26 14:08:20', 'Перемещен с установки на склад'),
(48, 2, 26, '2019-08-26 14:09:12', 'установлен'),
(49, 2, 3, '2019-08-26 14:09:26', 'установлен'),
(50, 1, 3, '2019-08-26 14:31:04', 'Перемещен с установки на склад'),
(51, 2, 3, '2019-08-26 14:34:55', 'установлен'),
(52, 1, 3, '2019-08-26 14:35:05', 'Перемещен с установки на склад'),
(53, 2, 3, '2019-08-26 15:29:57', 'установлен'),
(54, 1, 3, '2019-08-26 15:48:00', 'Перемещен с установки на склад'),
(55, 2, 3, '2019-08-26 15:48:14', 'установлен'),
(56, 1, 3, '2019-08-26 16:17:50', 'Перемещен с установки на склад'),
(57, 2, 3, '2019-08-26 16:18:02', 'установлен'),
(58, 1, 3, '2019-08-28 08:42:06', 'Перемещен с GTU-1 на склад'),
(64, 1, 3, '2020-02-09 21:40:52', 'Перемещен с GTU-1 на склад: Dima'),
(65, 6, 3, '2020-02-09 22:17:15', 'установлен: Dima'),
(66, 1, 1, '2020-02-11 17:28:15', 'установлен: Dima'),
(67, 1, 1, '2020-02-11 17:30:10', 'установлен: Dima'),
(68, 1, 2, '2020-02-11 17:41:55', 'перемещен с установки GTU-2 на склад: Dima'),
(69, 3, 3, '2020-02-11 17:41:56', 'установлен: Dima'),
(70, 1, 1, '2020-02-11 17:53:12', 'установлен: Dima'),
(71, 1, 3, '2020-02-11 21:57:53', 'установлен: Dima'),
(72, 1, 3, '2020-02-11 22:01:04', 'установлен: Dima'),
(73, 1, 3, '2020-02-11 22:05:52', 'установлен: Dima'),
(74, 1, 3, '2020-02-11 22:12:16', 'установлен: Dima'),
(75, 3, 3, '2020-02-11 22:20:56', 'установлен: Dima'),
(77, 1, 26, '2020-02-12 10:40:51', 'перемещен с установки GTU-5 на склад: Dima'),
(78, 1, 26, '2020-02-12 10:40:51', 'установлен: Dima'),
(79, 5, 26, '2020-02-12 11:23:53', 'установлен: Dima'),
(80, 1, 3, '2020-02-12 11:24:25', 'перемещен с установки GTU-2 на склад: Dima'),
(81, 5, 3, '2020-02-12 11:24:25', 'установлен: Dima'),
(82, 1, 17, '2020-02-12 11:44:29', 'перемещен с установки GTU-4 на склад: Dima'),
(83, 1, 17, '2020-02-12 11:44:33', 'установлен: Dima'),
(84, 1, 3, '2020-02-12 11:45:09', 'перемещен с установки GTU-4 на склад: Dima'),
(85, 1, 16, '2020-02-12 11:45:09', 'перемещен с установки GTU-4 на склад: Dima'),
(86, 4, 3, '2020-02-12 11:45:10', 'установлен: Dima'),
(87, 4, 17, '2020-02-12 14:38:03', 'установлен: Dima'),
(88, 1, 17, '2020-02-12 15:35:57', 'перемещен с установки GTU-3 на склад: Dima'),
(89, 7, 17, '2020-02-12 15:35:58', 'установлен: Dima'),
(90, 1, 31, '2020-02-12 15:53:02', 'Перемещен с GTU-8 на склад: Dima'),
(91, 1, 31, '2020-02-12 15:53:08', 'Утилизирован(перемещен со склада): Dima');

-- --------------------------------------------------------

--
-- Структура таблицы `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `authorities`
--

INSERT INTO `authorities` (`username`, `authority`) VALUES
('admin', 'ROLE_ADMIN'),
('kip', 'ROLE_KIP'),
('user', 'ROLE_USER');

-- --------------------------------------------------------

--
-- Структура таблицы `country`
--

DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `id_country` int(11) NOT NULL,
  `country_name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `country`
--

INSERT INTO `country` (`id_country`, `country_name`) VALUES
(17, 'China'),
(16, 'Russian Federation'),
(18, 'USA');

-- --------------------------------------------------------

--
-- Структура таблицы `disposal`
--

DROP TABLE IF EXISTS `disposal`;
CREATE TABLE `disposal` (
  `id_disposal` int(11) NOT NULL,
  `type_sensor` varchar(45) NOT NULL,
  `model_sensor` varchar(45) NOT NULL,
  `version_sensor` varchar(45) NOT NULL,
  `number_sensor` varchar(45) NOT NULL,
  `inventory_number_sensor` varchar(45) DEFAULT NULL,
  `date_archive` datetime NOT NULL,
  `date_disposal` datetime NOT NULL,
  `note` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `disposal`
--

INSERT INTO `disposal` (`id_disposal`, `type_sensor`, `model_sensor`, `version_sensor`, `number_sensor`, `inventory_number_sensor`, `date_archive`, `date_disposal`, `note`) VALUES
(29, 'Датчик температуры', 'ДТС тест', '125', '161514', '', '2020-03-02 22:08:33', '2020-03-02 22:08:48', 'Перемещен с GTU-1 на склад: Dima Удаление пользователем: Dima'),
(30, 'Датчик температуры', 'ДТС тест', '125', '161514', '', '2020-03-02 22:08:40', '2020-03-02 22:08:48', 'Утилизирован(перемещен со склада): Dima Удаление пользователем: Dima');

-- --------------------------------------------------------

--
-- Структура таблицы `firm`
--

DROP TABLE IF EXISTS `firm`;
CREATE TABLE `firm` (
  `id_firm` int(11) NOT NULL,
  `name_firm` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `firm`
--

INSERT INTO `firm` (`id_firm`, `name_firm`) VALUES
(1, 'Danfoss'),
(9, 'Dell'),
(8, 'Endress+Hauser AG'),
(7, 'АО \"ИПФ \"СибНА\" '),
(6, 'АО ПГ Метран');

-- --------------------------------------------------------

--
-- Структура таблицы `location`
--

DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `id_location` int(11) NOT NULL,
  `name_loc` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ser_number` varchar(50) DEFAULT NULL,
  `inv_number` varchar(50) DEFAULT NULL,
  `work_loc` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `location`
--

INSERT INTO `location` (`id_location`, `name_loc`, `ser_number`, `inv_number`, `work_loc`) VALUES
(1, 'Склад ГТЭС КИП', NULL, NULL, 1),
(2, 'GTU-1', '11111', '11111', 1),
(3, 'GTU-2', NULL, NULL, 0),
(4, 'GTU-3', NULL, NULL, 0),
(5, 'GTU-4', NULL, NULL, 0),
(6, 'GTU-5', NULL, NULL, 0),
(7, 'GTU-8', NULL, NULL, 1),
(8, 'GTU-6', NULL, NULL, 1),
(9, 'GTU-7', NULL, NULL, 0),
(10, 'ДКС-1', NULL, NULL, 1);

--
-- Триггеры `location`
--
DROP TRIGGER IF EXISTS `location_BEFORE_DELETE id1`;
DELIMITER $$
CREATE TRIGGER `location_BEFORE_DELETE id1` BEFORE DELETE ON `location` FOR EACH ROW BEGIN
IF OLD.id_location = 1 THEN
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'NO DELENE STOCK';
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Структура таблицы `location_install`
--

DROP TABLE IF EXISTS `location_install`;
CREATE TABLE `location_install` (
  `id_location_install` int(11) NOT NULL,
  `location_id` int(11) NOT NULL,
  `sensor_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `location_install`
--

INSERT INTO `location_install` (`id_location_install`, `location_id`, `sensor_id`) VALUES
(1, 4, 1),
(12, 2, 26),
(13, 1, 2),
(14, 4, 27),
(15, 1, 3),
(21, 5, 31),
(23, 6, 17);

-- --------------------------------------------------------

--
-- Структура таблицы `model_sensor`
--

DROP TABLE IF EXISTS `model_sensor`;
CREATE TABLE `model_sensor` (
  `id_model` int(11) NOT NULL,
  `model_name` varchar(45) NOT NULL,
  `model_version` varchar(45) DEFAULT NULL,
  `typesens_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `model_sensor`
--

INSERT INTO `model_sensor` (`id_model`, `model_name`, `model_version`, `typesens_id`) VALUES
(1, 'ДТС тест', '125', 7),
(4, 'MBS3100', '060G1100', 8),
(5, 'Тест', 'ver', 9);

-- --------------------------------------------------------

--
-- Структура таблицы `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `sensor`
--

DROP TABLE IF EXISTS `sensor`;
CREATE TABLE `sensor` (
  `id_sensor` int(11) NOT NULL,
  `model_id` int(11) NOT NULL,
  `sensor_numb` varchar(45) NOT NULL,
  `inventory_numb` varchar(45) DEFAULT NULL,
  `unit_id` int(11) NOT NULL,
  `range_min` int(11) DEFAULT NULL,
  `range_max` int(11) DEFAULT NULL,
  `country_id` int(11) NOT NULL,
  `firm_id` int(11) NOT NULL,
  `date_manufacture` date NOT NULL,
  `date_verification` date NOT NULL,
  `interval_verification` int(11) NOT NULL,
  `verification` tinyint(1) NOT NULL DEFAULT '0',
  `calibration` tinyint(1) NOT NULL DEFAULT '0',
  `passport` tinyint(1) NOT NULL,
  `location_id` int(11) DEFAULT NULL,
  `note` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `sensor`
--

INSERT INTO `sensor` (`id_sensor`, `model_id`, `sensor_numb`, `inventory_numb`, `unit_id`, `range_min`, `range_max`, `country_id`, `firm_id`, `date_manufacture`, `date_verification`, `interval_verification`, `verification`, `calibration`, `passport`, `location_id`, `note`) VALUES
(1, 4, '3454567', '1', 1, 0, 25, 17, 1, '2019-06-11', '2019-06-19', 12, 1, 1, 0, 2, 'Test'),
(2, 1, '3423141231423421', '2', 4, 0, 80, 16, 7, '2016-09-06', '2016-09-06', 12, 0, 1, 1, 3, NULL),
(3, 1, '666', '3', 1, 23, 50, 16, 7, '2019-05-30', '2019-06-14', 48, 1, 1, 1, 4, NULL),
(16, 5, '1245780567', '4', 4, 0, 29, 18, 7, '2017-06-15', '2017-08-24', 12, 0, 1, 1, 6, NULL),
(17, 5, '56', NULL, 4, 6, 7, 16, 7, '2019-06-13', '2019-06-20', 12, 1, 1, 1, 7, NULL),
(26, 4, '4235', '', 1, 0, 6, 16, 7, '2019-06-11', '2019-06-18', 12, 1, 1, 1, 5, NULL),
(27, 4, '7809', '', 1, 0, 6, 16, 7, '2019-06-11', '2019-06-18', 12, 1, 0, 1, 8, NULL),
(28, 5, 'test1', '', 1, 0, 400, 17, 7, '2019-06-25', '2019-07-04', 12, 0, 0, 1, 9, NULL),
(31, 5, '444444', '444444', 3, 0, 100, 16, 6, '2019-06-10', '2019-08-01', 12, 1, 0, 1, 1, NULL),
(40, 4, '444445', '444445', 1, 0, 25, 17, 1, '2019-12-01', '2020-02-12', 12, 1, 0, 1, 2, NULL),
(42, 1, '5369', '', 5, 0, 50, 16, 6, '2020-01-01', '2020-03-01', 12, 1, 0, 1, 1, 'test');

-- --------------------------------------------------------

--
-- Структура таблицы `typesens`
--

DROP TABLE IF EXISTS `typesens`;
CREATE TABLE `typesens` (
  `id_typesens` int(11) NOT NULL,
  `name_type` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `typesens`
--

INSERT INTO `typesens` (`id_typesens`, `name_type`) VALUES
(8, 'Датчик давления'),
(7, 'Датчик температуры'),
(9, 'Тест');

-- --------------------------------------------------------

--
-- Структура таблицы `unit`
--

DROP TABLE IF EXISTS `unit`;
CREATE TABLE `unit` (
  `id_unit` int(11) NOT NULL,
  `unit_name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `unit`
--

INSERT INTO `unit` (`id_unit`, `unit_name`) VALUES
(1, 'Bar'),
(4, 'Pa'),
(3, 'Psi'),
(5, 'Цельсий');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `enabled` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
('admin', '$2a$10$hbxecwitQQ.dDT4JOFzQAulNySFwEpaFLw38jda6Td.Y/cOiRzDFu', 1),
('kip', '$2a$10$BcBgN1EKzWjoYLXVWS9y5uQFb8g98r5LnTaD895KiQwUZ.ZiHK/NG', 0),
('user', '$2a$10$5xiA/C9V5ZWaKItaqHO3TuUTc7MKkytfTBKtu1EeL0Z7OnW2nmIGS', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile` (
  `id` bigint(20) NOT NULL,
  `type` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user_profile`
--

INSERT INTO `user_profile` (`id`, `type`) VALUES
(2, 'ADMIN'),
(3, 'KIP'),
(1, 'USER');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `app_user`
--
ALTER TABLE `app_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `sso_id` (`sso_id`);

--
-- Индексы таблицы `app_user_user_profile`
--
ALTER TABLE `app_user_user_profile`
  ADD PRIMARY KEY (`user_id`,`user_profile_id`),
  ADD KEY `FK_USER_PROFILE` (`user_profile_id`);

--
-- Индексы таблицы `archive`
--
ALTER TABLE `archive`
  ADD PRIMARY KEY (`id_archive`),
  ADD UNIQUE KEY `id_archive_UNIQUE` (`id_archive`),
  ADD KEY `fk_archive_sensor_idx` (`sensor_id`),
  ADD KEY `fk_archive_location_idx` (`location_id`);

--
-- Индексы таблицы `authorities`
--
ALTER TABLE `authorities`
  ADD UNIQUE KEY `ix_auth_username` (`username`,`authority`) USING BTREE;

--
-- Индексы таблицы `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`id_country`),
  ADD UNIQUE KEY `country_name_UNIQUE` (`country_name`);

--
-- Индексы таблицы `disposal`
--
ALTER TABLE `disposal`
  ADD PRIMARY KEY (`id_disposal`),
  ADD UNIQUE KEY `id_disposal_UNIQUE` (`id_disposal`);

--
-- Индексы таблицы `firm`
--
ALTER TABLE `firm`
  ADD PRIMARY KEY (`id_firm`),
  ADD UNIQUE KEY `name_firm_UNIQUE` (`name_firm`);

--
-- Индексы таблицы `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id_location`);

--
-- Индексы таблицы `location_install`
--
ALTER TABLE `location_install`
  ADD PRIMARY KEY (`id_location_install`),
  ADD UNIQUE KEY `id_location_install_UNIQUE` (`id_location_install`),
  ADD UNIQUE KEY `sensor_id` (`sensor_id`),
  ADD KEY `fk_location_install_sensor_idx` (`sensor_id`),
  ADD KEY `fk_location_install_location_idx` (`location_id`);

--
-- Индексы таблицы `model_sensor`
--
ALTER TABLE `model_sensor`
  ADD PRIMARY KEY (`id_model`),
  ADD KEY `fk_model_typesens_idx` (`typesens_id`);

--
-- Индексы таблицы `persistent_logins`
--
ALTER TABLE `persistent_logins`
  ADD PRIMARY KEY (`series`);

--
-- Индексы таблицы `sensor`
--
ALTER TABLE `sensor`
  ADD PRIMARY KEY (`id_sensor`),
  ADD UNIQUE KEY `sensor_numb_UNIQUE` (`sensor_numb`),
  ADD KEY `fk_sensor_country_idx` (`country_id`),
  ADD KEY `fk_sensor_model_idx` (`model_id`),
  ADD KEY `fk_sensor_firm_idx` (`firm_id`),
  ADD KEY `fk_sensor_unit_idx` (`unit_id`),
  ADD KEY `fk_sensor_location_idx` (`location_id`);

--
-- Индексы таблицы `typesens`
--
ALTER TABLE `typesens`
  ADD PRIMARY KEY (`id_typesens`),
  ADD UNIQUE KEY `name_type_UNIQUE` (`name_type`);

--
-- Индексы таблицы `unit`
--
ALTER TABLE `unit`
  ADD PRIMARY KEY (`id_unit`),
  ADD UNIQUE KEY `unit_name_UNIQUE` (`unit_name`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- Индексы таблицы `user_profile`
--
ALTER TABLE `user_profile`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `type` (`type`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `app_user`
--
ALTER TABLE `app_user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT для таблицы `archive`
--
ALTER TABLE `archive`
  MODIFY `id_archive` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=96;

--
-- AUTO_INCREMENT для таблицы `country`
--
ALTER TABLE `country`
  MODIFY `id_country` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT для таблицы `disposal`
--
ALTER TABLE `disposal`
  MODIFY `id_disposal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT для таблицы `firm`
--
ALTER TABLE `firm`
  MODIFY `id_firm` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT для таблицы `location`
--
ALTER TABLE `location`
  MODIFY `id_location` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT для таблицы `location_install`
--
ALTER TABLE `location_install`
  MODIFY `id_location_install` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT для таблицы `model_sensor`
--
ALTER TABLE `model_sensor`
  MODIFY `id_model` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `sensor`
--
ALTER TABLE `sensor`
  MODIFY `id_sensor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT для таблицы `typesens`
--
ALTER TABLE `typesens`
  MODIFY `id_typesens` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT для таблицы `unit`
--
ALTER TABLE `unit`
  MODIFY `id_unit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT для таблицы `user_profile`
--
ALTER TABLE `user_profile`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `app_user_user_profile`
--
ALTER TABLE `app_user_user_profile`
  ADD CONSTRAINT `FK_APP_USER` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`),
  ADD CONSTRAINT `FK_USER_PROFILE` FOREIGN KEY (`user_profile_id`) REFERENCES `user_profile` (`id`);

--
-- Ограничения внешнего ключа таблицы `archive`
--
ALTER TABLE `archive`
  ADD CONSTRAINT `fk_archive_location` FOREIGN KEY (`location_id`) REFERENCES `location` (`id_location`),
  ADD CONSTRAINT `fk_archive_sensor` FOREIGN KEY (`sensor_id`) REFERENCES `sensor` (`id_sensor`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `location_install`
--
ALTER TABLE `location_install`
  ADD CONSTRAINT `fk_location_install_location` FOREIGN KEY (`location_id`) REFERENCES `location` (`id_location`),
  ADD CONSTRAINT `fk_location_install_sensor` FOREIGN KEY (`sensor_id`) REFERENCES `sensor` (`id_sensor`);

--
-- Ограничения внешнего ключа таблицы `model_sensor`
--
ALTER TABLE `model_sensor`
  ADD CONSTRAINT `fk_model_typesens` FOREIGN KEY (`typesens_id`) REFERENCES `typesens` (`id_typesens`);

--
-- Ограничения внешнего ключа таблицы `sensor`
--
ALTER TABLE `sensor`
  ADD CONSTRAINT `fk_sensor_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id_country`),
  ADD CONSTRAINT `fk_sensor_firm` FOREIGN KEY (`firm_id`) REFERENCES `firm` (`id_firm`),
  ADD CONSTRAINT `fk_sensor_location` FOREIGN KEY (`location_id`) REFERENCES `location` (`id_location`),
  ADD CONSTRAINT `fk_sensor_model` FOREIGN KEY (`model_id`) REFERENCES `model_sensor` (`id_model`),
  ADD CONSTRAINT `fk_sensor_unit` FOREIGN KEY (`unit_id`) REFERENCES `unit` (`id_unit`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
