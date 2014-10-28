/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : ssel

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2014-10-24 12:10:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `block`
-- ----------------------------
DROP TABLE IF EXISTS `block`;
CREATE TABLE `block` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end_time` date NOT NULL,
  `name` varchar(255) NOT NULL,
  `block_order` int(11) NOT NULL,
  `start_time` date NOT NULL,
  `id_subject` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_omq2djcq006ony43amegc9fsw` (`id_subject`),
  CONSTRAINT `FK_omq2djcq006ony43amegc9fsw` FOREIGN KEY (`id_subject`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of block
-- ----------------------------
INSERT INTO `block` VALUES ('1', '2014-12-28', 'Java core', '1', '2014-10-01', '1');
INSERT INTO `block` VALUES ('2', '2014-12-28', 'Java EE', '2', '2014-10-01', '1');
INSERT INTO `block` VALUES ('3', '2014-12-28', 'Servlet API', '3', '2014-10-01', '1');
INSERT INTO `block` VALUES ('4', '2014-12-28', 'Hibernate', '4', '2014-10-01', '1');
INSERT INTO `block` VALUES ('5', '2014-12-28', 'Spring MVC', '5', '2014-10-01', '1');
INSERT INTO `block` VALUES ('6', '2014-12-31', 'Some module', '6', '2014-01-01', '1');
INSERT INTO `block` VALUES ('7', '2014-12-31', 'modulef', '1', '2014-01-01', '5');

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'Programming');
INSERT INTO `category` VALUES ('2', 'QC');
INSERT INTO `category` VALUES ('3', 'Mathematics');
INSERT INTO `category` VALUES ('4', 'Foreign languages');

-- ----------------------------
-- Table structure for `course_scheduler`
-- ----------------------------
DROP TABLE IF EXISTS `course_scheduler`;
CREATE TABLE `course_scheduler` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end` date NOT NULL,
  `start` date NOT NULL,
  `id_subject` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fqne590oury2qgrfkytjyteq` (`id_subject`),
  CONSTRAINT `FK_fqne590oury2qgrfkytjyteq` FOREIGN KEY (`id_subject`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_scheduler
-- ----------------------------
INSERT INTO `course_scheduler` VALUES ('1', '2014-12-30', '2014-09-30', '1');
INSERT INTO `course_scheduler` VALUES ('3', '2014-09-30', '2013-01-01', '5');
INSERT INTO `course_scheduler` VALUES ('4', '2014-11-01', '2014-10-03', '8');
INSERT INTO `course_scheduler` VALUES ('5', '2014-10-01', '2014-11-30', '9');

-- ----------------------------
-- Table structure for `logging`
-- ----------------------------
DROP TABLE IF EXISTS `logging`;
CREATE TABLE `logging` (
  `eventDate` datetime DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `logger` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `exception` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logging
-- ----------------------------
INSERT INTO `logging` VALUES ('2014-10-24 11:19:53', 'WARN', 'org.springframework.web.servlet.PageNotFound', 'No mapping found for HTTP request with URI [/SSEL/index] in DispatcherServlet with name \'appServlet\'', '');
INSERT INTO `logging` VALUES ('2014-10-24 11:20:09', 'WARN', 'org.springframework.web.servlet.PageNotFound', 'No mapping found for HTTP request with URI [/SSEL/subscribe_error] in DispatcherServlet with name \'appServlet\'', '');
INSERT INTO `logging` VALUES ('2014-10-24 11:22:07', 'WARN', 'org.springframework.web.servlet.PageNotFound', 'No mapping found for HTTP request with URI [/SSEL/subscribe_error] in DispatcherServlet with name \'appServlet\'', '');
INSERT INTO `logging` VALUES ('2014-10-24 11:53:05', 'WARN', 'org.springframework.web.servlet.PageNotFound', 'No mapping found for HTTP request with URI [/SSEL/subscribe_error] in DispatcherServlet with name \'appServlet\'', '');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ADMIN');
INSERT INTO `role` VALUES ('2', 'STUDENT');
INSERT INTO `role` VALUES ('3', 'TEACHER');

-- ----------------------------
-- Table structure for `student_group`
-- ----------------------------
DROP TABLE IF EXISTS `student_group`;
CREATE TABLE `student_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupNumber` int(11) NOT NULL,
  `progress` double NOT NULL,
  `rating` double NOT NULL,
  `id_course_scheduler` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qwrow5v9nx2q2mvhp60069x0` (`id_course_scheduler`),
  KEY `FK_efnecvqpn68w3gfiqlyg2c137` (`id_user`),
  CONSTRAINT `FK_efnecvqpn68w3gfiqlyg2c137` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_qwrow5v9nx2q2mvhp60069x0` FOREIGN KEY (`id_course_scheduler`) REFERENCES `course_scheduler` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_group
-- ----------------------------
INSERT INTO `student_group` VALUES ('2', '100', '0', '0', '4', '1');
INSERT INTO `student_group` VALUES ('3', '101', '0', '0', '3', '1');
INSERT INTO `student_group` VALUES ('4', '102', '75', '100', '3', '1');
INSERT INTO `student_group` VALUES ('5', '103', '0', '0', '5', '1');

-- ----------------------------
-- Table structure for `subject`
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `id_category` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1kftfvbujtv6y8isj6bw2s6i4` (`id_category`),
  CONSTRAINT `FK_1kftfvbujtv6y8isj6bw2s6i4` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES ('1', 'Java EE', 'Java EE', '1');
INSERT INTO `subject` VALUES ('5', 'QC', 'Quality control', '2');
INSERT INTO `subject` VALUES ('8', 'English', 'English', '4');
INSERT INTO `subject` VALUES ('9', 'Mathematics', 'Mathematics', '3');

-- ----------------------------
-- Table structure for `topic`
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alive` bit(1) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `topic_order` int(11) DEFAULT NULL,
  `id_block` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_o5t8ufxkwh91qx5vxdi05h3wc` (`id_block`),
  CONSTRAINT `FK_o5t8ufxkwh91qx5vxdi05h3wc` FOREIGN KEY (`id_block`) REFERENCES `block` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('1', '', 'Some content\r\n', 'Java core', '4', '1');
INSERT INTO `topic` VALUES ('2', '', 'Some content', 'Lection 1', '2', '1');
INSERT INTO `topic` VALUES ('3', '', 'Some content', 'Lection 2', '3', '1');
INSERT INTO `topic` VALUES ('5', '', 'Some content', 'Lection 3', '1', '1');
INSERT INTO `topic` VALUES ('6', '', 'Some content', 'Lection 4', '1', '3');
INSERT INTO `topic` VALUES ('8', '', 'Some content', 'why is QC?', '1', '7');
INSERT INTO `topic` VALUES ('9', '', 'Some content', 'Lection 5', '2', '7');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
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
  KEY `FK_dl7g53f7lpmorjc24kx74apx8` (`role`),
  CONSTRAINT `FK_dl7g53f7lpmorjc24kx74apx8` FOREIGN KEY (`role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '', 'student@gmail.com', '2014-10-24 11:00:35', 'Lars', 'Hammet', '1234', '2014-10-24 11:00:35', '2');
INSERT INTO `user` VALUES ('2', '', 'teacher@mail.ru', '2014-10-24 11:01:18', 'Kirk', 'Ulrich', '1234', '2014-10-24 11:01:18', '3');
