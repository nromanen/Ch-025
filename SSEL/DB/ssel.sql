-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Окт 20 2014 г., 17:15
-- Версия сервера: 5.5.16
-- Версия PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `ssel`
--

-- --------------------------------------------------------

--
-- Структура таблицы `block`
--

CREATE TABLE IF NOT EXISTS `block` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `duration` int(11) NOT NULL,
  `block_order` int(11) NOT NULL,
  `subject` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_anes1o955tba2vqsrg1d9q183` (`subject`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Дамп данных таблицы `block`
--

INSERT INTO `block` (`id`, `duration`, `block_order`, `subject`) VALUES
(1, 7, 1, 1),
(2, 12, 2, 1),
(3, 7, 3, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Дамп данных таблицы `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Програмування c#'),
(2, 'Програмування java'),
(3, 'Програмування c++'),
(5, 'Програмування java'),
(6, 'Програмування c++'),
(7, 'Програмування c#');

-- --------------------------------------------------------

--
-- Структура таблицы `course_scheduler`
--

CREATE TABLE IF NOT EXISTS `course_scheduler` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start` datetime DEFAULT NULL,
  `subject` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_j4lk1djrkgvuqep6yaw2l4l6h` (`subject`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Дамп данных таблицы `course_scheduler`
--

INSERT INTO `course_scheduler` (`id`, `start`, `subject`) VALUES
(1, '2014-11-17 00:00:00', 1),
(2, '2014-11-18 00:00:00', 2),
(3, '2014-11-27 00:00:00', 3);

-- --------------------------------------------------------

--
-- Структура таблицы `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Дамп данных таблицы `role`
--

INSERT INTO `role` (`id`, `role`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER'),
(3, 'ROLE_TEACHER');

-- --------------------------------------------------------

--
-- Структура таблицы `student_group`
--

CREATE TABLE IF NOT EXISTS `student_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupNumber` int(11) NOT NULL,
  `progress` double NOT NULL,
  `rating` double NOT NULL,
  `course_scheduler` int(11) DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gw588ox2lmrqo4s6yv8mqqxqo` (`course_scheduler`),
  KEY `FK_aqujywxa0fcyctds5wlr276lw` (`user`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Дамп данных таблицы `student_group`
--

INSERT INTO `student_group` (`id`, `groupNumber`, `progress`, `rating`, `course_scheduler`, `user`) VALUES
(1, 101, 0, 0, 1, 1),
(2, 101, 0, 0, 1, 1),
(3, 101, 0, 0, 1, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `subject`
--

CREATE TABLE IF NOT EXISTS `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `category` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s0wiifn7aka22ujiwgbr0v1oo` (`category`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Дамп данных таблицы `subject`
--

INSERT INTO `subject` (`id`, `description`, `duration`, `name`, `category`) VALUES
(1, 'Програмування мовою java', 10, 'Java', 1),
(2, 'Програмування мовою c++', 10, 'C++', 2),
(3, 'Програмування мовою C#', 10, 'C#', 3);

-- --------------------------------------------------------

--
-- Структура таблицы `topic`
--

CREATE TABLE IF NOT EXISTS `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `topic_order` int(11) DEFAULT NULL,
  `block` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rcqdtbpt92yblb78klf6a7mms` (`block`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Дамп данных таблицы `topic`
--

INSERT INTO `topic` (`id`, `content`, `name`, `topic_order`, `block`) VALUES
(1, 'Якийсь контент', 'topic name 1', 1, 1),
(2, 'Якийсь контент', 'topic name 2', 2, 2),
(3, 'Якийсь контент', 'topic name 3', 3, 3),
(4, 'пваи', 'фаиваи', 4, 1),
(5, 'ваивип', 'виітата', 5, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blocked` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `expired` datetime NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `registration` datetime NOT NULL,
  `role` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dl7g53f7lpmorjc24kx74apx8` (`role`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`id`, `blocked`, `email`, `expired`, `firstName`, `lastName`, `password`, `registration`, `role`) VALUES
(1, b'0', 'roma@gmail.com', '2014-10-20 19:48:14', 'Roma', 'Homyshyn', '123456789', '2014-10-20 19:48:14', 2),
(2, b'0', 'andrew@gmail.com', '2014-10-20 19:48:14', 'Andrew', 'Shutka', '123456789', '2014-10-20 19:48:14', 2),
(3, b'0', 'valik@gmail.com', '2014-10-20 19:48:14', 'Valik', 'Hash', '123456789', '2014-10-20 19:48:14', 2);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `block`
--
ALTER TABLE `block`
  ADD CONSTRAINT `FK_anes1o955tba2vqsrg1d9q183` FOREIGN KEY (`subject`) REFERENCES `subject` (`id`);

--
-- Ограничения внешнего ключа таблицы `course_scheduler`
--
ALTER TABLE `course_scheduler`
  ADD CONSTRAINT `FK_j4lk1djrkgvuqep6yaw2l4l6h` FOREIGN KEY (`subject`) REFERENCES `subject` (`id`);

--
-- Ограничения внешнего ключа таблицы `student_group`
--
ALTER TABLE `student_group`
  ADD CONSTRAINT `FK_aqujywxa0fcyctds5wlr276lw` FOREIGN KEY (`user`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_gw588ox2lmrqo4s6yv8mqqxqo` FOREIGN KEY (`course_scheduler`) REFERENCES `course_scheduler` (`id`);

--
-- Ограничения внешнего ключа таблицы `subject`
--
ALTER TABLE `subject`
  ADD CONSTRAINT `FK_s0wiifn7aka22ujiwgbr0v1oo` FOREIGN KEY (`category`) REFERENCES `category` (`id`);

--
-- Ограничения внешнего ключа таблицы `topic`
--
ALTER TABLE `topic`
  ADD CONSTRAINT `FK_rcqdtbpt92yblb78klf6a7mms` FOREIGN KEY (`block`) REFERENCES `block` (`id`);

--
-- Ограничения внешнего ключа таблицы `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK_dl7g53f7lpmorjc24kx74apx8` FOREIGN KEY (`role`) REFERENCES `role` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
