CREATE DATABASE  IF NOT EXISTS `ssel` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ssel`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: ssel
-- ------------------------------------------------------
-- Server version	5.5.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `block`
--

DROP TABLE IF EXISTS `block`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `block` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end_time` date NOT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `block_order` int(11) NOT NULL,
  `start_time` date NOT NULL,
  `id_subject` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_omq2djcq006ony43amegc9fsw` (`id_subject`),
  CONSTRAINT `FK_omq2djcq006ony43amegc9fsw` FOREIGN KEY (`id_subject`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `block`
--

LOCK TABLES `block` WRITE;
/*!40000 ALTER TABLE `block` DISABLE KEYS */;
INSERT INTO `block` VALUES (6,'2014-08-07','\0','Some module2',0,'2013-12-22',5),(15,'2014-01-31','\0','Some module2',2,'2014-01-01',5),(16,'2014-01-31','\0','Some module3',3,'2014-01-01',5),(17,'2014-01-31','\0','Some module4',4,'2014-01-01',5),(18,'2014-01-31','\0','Some module',5,'2014-01-01',11),(19,'2014-01-31','\0','Some module6',6,'2014-01-01',11),(20,'2014-01-31','\0','Some module7',7,'2014-01-01',9),(21,'2014-01-31','\0','Some module8',8,'2014-01-01',9),(22,'2014-01-31','\0','Some module9',9,'2014-01-01',8),(23,'2014-01-31','\0','Some module10',10,'2014-01-01',8),(25,'2014-12-12','\0','Some module2',24,'2013-12-22',5);
/*!40000 ALTER TABLE `block` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `BlockOrderTrigger` BEFORE INSERT ON `block` FOR EACH ROW BEGIN
SET new.block_order = (select max(id) from block)+1;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deleted` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `name_index_cat` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'\0','Programming'),(2,'\0','QC'),(3,'\0','Mathematics'),(4,'\0','Foreign languages');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_scheduler`
--

DROP TABLE IF EXISTS `course_scheduler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_scheduler` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end` datetime NOT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `start` datetime NOT NULL,
  `id_subject` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fqne590oury2qgrfkytjyteq` (`id_subject`),
  CONSTRAINT `FK_fqne590oury2qgrfkytjyteq` FOREIGN KEY (`id_subject`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_scheduler`
--

LOCK TABLES `course_scheduler` WRITE;
/*!40000 ALTER TABLE `course_scheduler` DISABLE KEYS */;
INSERT INTO `course_scheduler` VALUES (3,'2014-09-30 00:00:00','\0','2013-01-01 00:00:00',5),(4,'2014-12-31 00:00:00','\0','2014-10-03 00:00:00',8),(5,'2014-11-01 00:00:00','\0','2014-10-30 00:00:00',9),(6,'2015-10-31 00:00:00','\0','2014-01-16 00:00:00',11),(7,'2014-01-16 00:00:00','\0','2013-10-22 00:00:00',12),(8,'2015-02-16 00:00:00','\0','2014-10-22 00:00:00',13),(9,'2015-05-16 00:00:00','\0','2014-10-21 00:00:00',14),(10,'2015-10-16 00:00:00','\0','2014-09-06 00:00:00',15),(11,'2015-12-16 00:00:00','\0','2015-10-07 00:00:00',16),(12,'2016-01-16 00:00:00','\0','2014-10-12 00:00:00',17),(13,'2017-02-11 00:00:00','\0','2015-04-22 00:00:00',18),(14,'2015-01-24 00:00:00','\0','2014-10-13 00:00:00',19),(15,'2015-01-05 00:00:00','\0','2014-10-14 00:00:00',20),(16,'2015-01-03 00:00:00','\0','2014-10-19 00:00:00',21),(17,'2015-01-10 00:00:00','\0','2014-12-21 00:00:00',22),(18,'2015-01-08 00:00:00','\0','2014-11-04 00:00:00',23),(19,'2015-01-05 00:00:00','\0','2014-11-07 00:00:00',24),(20,'2015-01-05 00:00:00','\0','2014-11-07 00:00:00',25),(21,'2015-01-05 00:00:00','\0','2014-11-07 00:00:00',26),(22,'2015-01-05 00:00:00','\0','2014-11-07 00:00:00',27),(23,'2015-01-05 00:00:00','\0','2014-11-07 00:00:00',28),(24,'2015-01-05 00:00:00','\0','2014-11-07 00:00:00',29),(25,'2015-01-05 00:00:00','\0','2014-11-07 00:00:00',30);
/*!40000 ALTER TABLE `course_scheduler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `files` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contentType` varchar(255) DEFAULT NULL,
  `DATA` tinyblob NOT NULL,
  `dateCreated` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `newFilename` varchar(255) DEFAULT NULL,
  `size_` bigint(20) DEFAULT NULL,
  `id_topic` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9v972e2fpd838iqkcgnks7801` (`id_topic`),
  CONSTRAINT `FK_9v972e2fpd838iqkcgnks7801` FOREIGN KEY (`id_topic`) REFERENCES `topic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isActive` bit(1) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `id_course_scheduler` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_96demx41l6j5l1llgh6inp1w0` (`id_course_scheduler`),
  CONSTRAINT `FK_96demx41l6j5l1llgh6inp1w0` FOREIGN KEY (`id_course_scheduler`) REFERENCES `course_scheduler` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (3,'\0','\0',3),(4,'','\0',6),(5,'','\0',7),(6,'','\0',8),(7,'','\0',9),(8,'','\0',10),(9,'','\0',11),(10,'','\0',12),(11,'','\0',13),(12,'','\0',14),(13,'','\0',15),(14,'','\0',16),(15,'','\0',17),(16,'','\0',18),(17,'','\0',19),(18,'','\0',20),(19,'','\0',21),(20,'','\0',22),(21,'','\0',23),(22,'','\0',24),(23,'','\0',25);
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logging`
--

DROP TABLE IF EXISTS `logging`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logging` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eventDate` datetime DEFAULT NULL,
  `exception` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `logger` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logging`
--

LOCK TABLES `logging` WRITE;
/*!40000 ALTER TABLE `logging` DISABLE KEYS */;
INSERT INTO `logging` VALUES (1,'2014-11-13 00:22:00','','WARN','org.apache.tiles.request.locale.PostfixedApplicationResource','No supported matching language for locale \"ua\". Using jndi:/localhost/SSEL-demo/WEB-INF/tiles/templates_ua.xml as a non-localized resource path. see TILES-571'),(2,'2014-11-13 00:22:01','','WARN','org.apache.tiles.request.locale.PostfixedApplicationResource','No supported matching language for locale \"ua\". Using jndi:/localhost/SSEL-demo/WEB-INF/tiles/pages_ua.xml as a non-localized resource path. see TILES-571'),(3,'2014-11-13 00:22:44','','WARN','org.springframework.web.servlet.PageNotFound','No mapping found for HTTP request with URI [/SSEL-demo/error] in DispatcherServlet with name \'appServlet\''),(4,'2014-11-13 00:24:29','','WARN','org.springframework.web.servlet.PageNotFound','No mapping found for HTTP request with URI [/SSEL-demo/error] in DispatcherServlet with name \'appServlet\''),(5,'2014-11-13 00:42:29','','WARN','org.springframework.web.servlet.PageNotFound','No mapping found for HTTP request with URI [/SSEL-demo/error] in DispatcherServlet with name \'appServlet\''),(6,'2014-11-13 00:58:19','','WARN','org.springframework.web.servlet.PageNotFound','No mapping found for HTTP request with URI [/SSEL-demo/error] in DispatcherServlet with name \'appServlet\''),(7,'2014-11-13 01:04:32','','WARN','org.springframework.web.servlet.PageNotFound','No mapping found for HTTP request with URI [/SSEL-demo/error] in DispatcherServlet with name \'appServlet\''),(8,'2014-11-13 01:14:21','','WARN','org.springframework.web.servlet.PageNotFound','No mapping found for HTTP request with URI [/SSEL-demo/error] in DispatcherServlet with name \'appServlet\''),(9,'2014-11-13 01:32:58','','WARN','org.apache.tiles.request.locale.PostfixedApplicationResource','No supported matching language for locale \"ua\". Using jndi:/localhost/SSEL-demo/WEB-INF/tiles/templates_ua.xml as a non-localized resource path. see TILES-571'),(10,'2014-11-13 01:32:58','','WARN','org.apache.tiles.request.locale.PostfixedApplicationResource','No supported matching language for locale \"ua\". Using jndi:/localhost/SSEL-demo/WEB-INF/tiles/pages_ua.xml as a non-localized resource path. see TILES-571'),(11,'2014-11-13 01:33:35','','WARN','org.springframework.web.servlet.PageNotFound','No mapping found for HTTP request with URI [/SSEL-demo/error] in DispatcherServlet with name \'appServlet\''),(12,'2014-11-13 01:33:44','','WARN','org.springframework.web.servlet.PageNotFound','No mapping found for HTTP request with URI [/SSEL-demo/error] in DispatcherServlet with name \'appServlet\''),(13,'2014-11-13 01:40:34','','WARN','org.apache.tiles.request.locale.PostfixedApplicationResource','No supported matching language for locale \"ua\". Using jndi:/localhost/SSEL-demo/WEB-INF/tiles/templates_ua.xml as a non-localized resource path. see TILES-571'),(14,'2014-11-13 01:40:34','','WARN','org.apache.tiles.request.locale.PostfixedApplicationResource','No supported matching language for locale \"ua\". Using jndi:/localhost/SSEL-demo/WEB-INF/tiles/pages_ua.xml as a non-localized resource path. see TILES-571'),(15,'2014-11-13 01:40:52','','WARN','org.springframework.web.servlet.PageNotFound','No mapping found for HTTP request with URI [/SSEL-demo/error] in DispatcherServlet with name \'appServlet\''),(16,'2014-11-13 01:58:12','','WARN','org.apache.tiles.request.locale.PostfixedApplicationResource','No supported matching language for locale \"ua\". Using jndi:/localhost/SSEL-demo/WEB-INF/tiles/templates_ua.xml as a non-localized resource path. see TILES-571'),(17,'2014-11-13 01:58:12','','WARN','org.apache.tiles.request.locale.PostfixedApplicationResource','No supported matching language for locale \"ua\". Using jndi:/localhost/SSEL-demo/WEB-INF/tiles/pages_ua.xml as a non-localized resource path. see TILES-571'),(18,'2014-11-13 02:00:01','','WARN','org.springframework.web.servlet.PageNotFound','No mapping found for HTTP request with URI [/SSEL-demo/error] in DispatcherServlet with name \'appServlet\''),(19,'2014-11-13 02:00:22','','WARN','org.springframework.web.servlet.PageNotFound','No mapping found for HTTP request with URI [/SSEL-demo/error] in DispatcherServlet with name \'appServlet\''),(20,'2014-11-13 02:02:42','','WARN','org.springframework.web.servlet.PageNotFound','No mapping found for HTTP request with URI [/SSEL-demo/error] in DispatcherServlet with name \'appServlet\''),(21,'2014-11-13 02:08:26','','WARN','org.apache.tiles.request.locale.PostfixedApplicationResource','No supported matching language for locale \"ua\". Using jndi:/localhost/SSEL-demo/WEB-INF/tiles/templates_ua.xml as a non-localized resource path. see TILES-571'),(22,'2014-11-13 02:08:26','','WARN','org.apache.tiles.request.locale.PostfixedApplicationResource','No supported matching language for locale \"ua\". Using jndi:/localhost/SSEL-demo/WEB-INF/tiles/pages_ua.xml as a non-localized resource path. see TILES-571'),(23,'2014-11-13 16:58:46','','WARN','org.apache.tiles.request.locale.PostfixedApplicationResource','No supported matching language for locale \"ua\". Using jndi:/localhost/SSEL-demo/WEB-INF/tiles/templates_ua.xml as a non-localized resource path. see TILES-571'),(24,'2014-11-13 16:58:49','','WARN','org.apache.tiles.request.locale.PostfixedApplicationResource','No supported matching language for locale \"ua\". Using jndi:/localhost/SSEL-demo/WEB-INF/tiles/pages_ua.xml as a non-localized resource path. see TILES-571'),(25,'2014-11-13 17:25:24','','WARN','org.apache.tiles.request.locale.PostfixedApplicationResource','No supported matching language for locale \"ua\". Using jndi:/localhost/SSEL-demo/WEB-INF/tiles/templates_ua.xml as a non-localized resource path. see TILES-571'),(26,'2014-11-13 17:25:25','','WARN','org.apache.tiles.request.locale.PostfixedApplicationResource','No supported matching language for locale \"ua\". Using jndi:/localhost/SSEL-demo/WEB-INF/tiles/pages_ua.xml as a non-localized resource path. see TILES-571');
/*!40000 ALTER TABLE `logging` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mark` double DEFAULT NULL,
  `id_block` int(11) DEFAULT NULL,
  `id_group` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qk86s4pe1wpwsjv8lrxpmsv92` (`id_block`),
  KEY `FK_cnjs788xf5bg57ff93gkfl3x3` (`id_group`),
  KEY `FK_jl72fljncdib2delovgq2qgxo` (`id_user`),
  CONSTRAINT `FK_jl72fljncdib2delovgq2qgxo` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_cnjs788xf5bg57ff93gkfl3x3` FOREIGN KEY (`id_group`) REFERENCES `groups` (`id`),
  CONSTRAINT `FK_qk86s4pe1wpwsjv8lrxpmsv92` FOREIGN KEY (`id_block`) REFERENCES `block` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` VALUES (3,75,6,3,7),(4,60,15,3,7),(5,90,16,3,7),(6,57,17,3,7);
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'STUDENT'),(3,'TEACHER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_group`
--

DROP TABLE IF EXISTS `student_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_group` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5cpd83ph40qvyvw8w28xlftma` (`id_group`),
  KEY `FK_efnecvqpn68w3gfiqlyg2c137` (`id_user`),
  CONSTRAINT `FK_efnecvqpn68w3gfiqlyg2c137` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_5cpd83ph40qvyvw8w28xlftma` FOREIGN KEY (`id_group`) REFERENCES `groups` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_group`
--

LOCK TABLES `student_group` WRITE;
/*!40000 ALTER TABLE `student_group` DISABLE KEYS */;
INSERT INTO `student_group` VALUES (1,11,7);
/*!40000 ALTER TABLE `student_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `id_category` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `name_index` (`name`,`description`),
  KEY `FK_1kftfvbujtv6y8isj6bw2s6i4` (`id_category`),
  KEY `FK_m0hq9p5ouxkaq56mc335e0fak` (`id_user`),
  CONSTRAINT `FK_m0hq9p5ouxkaq56mc335e0fak` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_1kftfvbujtv6y8isj6bw2s6i4` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (5,'QC','\0','Quality control',2,8),(8,'English','\0','English',4,8),(9,'Mathematics','\0','Mathematics',3,8),(11,'Java','\0','Java',1,8),(12,'JavaEE','\0','JavaEE',1,8),(13,'JavaFX','\0','JavaFX',1,8),(14,'JavaSE','\0','JavaSE',1,8),(15,'JavaME','\0','JavaME',1,8),(16,'Java Core','\0','Java Core',1,8),(17,'Java Script Base','\0','Java Script Base',1,8),(18,'Java Script Frameworks','\0','Java Script Frameworks',1,8),(19,'Java JUnit','\0','Java JUnit',1,8),(20,'Java Collections','\0','Java Collections',1,8),(21,'Java Collections 2','\0','Java Collections 2',1,8),(22,'Java Frameworks','\0','Java Frameworks',1,8),(23,'Java Frameworks 2','\0','Java Frameworks 2',1,8),(24,'Java Multithreading','\0','Java Multithreading',1,8),(25,'Java 8','\0','Java 8',1,8),(26,'Java Generics','\0','Java Generics',1,8),(27,'Java Script 2','\0','Java Script 2',1,8),(28,'Java : Spring','\0','Java : Spring',1,8),(29,'Java : Introduction','\0','Java : Introduction',1,8),(30,'Java : Progressive level','\0','Java : Progressive level',1,8);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_request`
--

DROP TABLE IF EXISTS `teacher_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `request_date` datetime DEFAULT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_iu1b4mnri3sryc4sd3okbkkov` (`id_user`),
  CONSTRAINT `FK_iu1b4mnri3sryc4sd3okbkkov` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_request`
--

LOCK TABLES `teacher_request` WRITE;
/*!40000 ALTER TABLE `teacher_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alive` bit(1) NOT NULL,
  `content` varchar(255) NOT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `topic_order` int(11) NOT NULL,
  `id_block` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_o5t8ufxkwh91qx5vxdi05h3wc` (`id_block`),
  CONSTRAINT `FK_o5t8ufxkwh91qx5vxdi05h3wc` FOREIGN KEY (`id_block`) REFERENCES `block` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (2,'\0','Some content','\0','Lection 1',12,6),(4,'','Some content','\0','Lection 2',11,6),(6,'','Some content','\0','Lection 3',5,6),(7,'','<p>Some content</p>\r\n','\0','Lection 4',6,6),(8,'','Some content','\0','Lection 5',4,6),(9,'','Some content','\0','Lection 68',3,6),(10,'','Some content','\0','Lection 7',7,20),(11,'','Some content','\0','Lection 8',8,21),(12,'','Some content','\0','Lection 9',9,22),(13,'','Some content','\0','Lection 10',10,23),(14,'','<p>Some content</p>\r\n','\0','Lection 111',77,6),(15,'','Some content','\0','Lection 12',12,15),(16,'','Some content','\0','Lection 13',13,16),(17,'','Some content','\0','Lection 14',14,17),(18,'','<p>Some content</p>\r\n','\0','Lection 150',15,18),(19,'','Some content','\0','Lection 16',16,19),(20,'','Some content','','Lection 17',17,20),(21,'','Some content','\0','Lection 18',18,21),(22,'','Some content','\0','Lection 19',19,22),(23,'','<p>Some content</p>\r\n','\0','Lection 6',1,6),(24,'','<p>Some content</p>\r\n','\0','Lection 6',20,6);
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `orderTrigger` BEFORE INSERT ON `topic` FOR EACH ROW BEGIN
SET new.topic_order = (select max(id) from topic)+1;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blocked` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `expired` datetime NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `image` longblob,
  `lastName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `registration` datetime NOT NULL,
  `verificationkey` varchar(255) DEFAULT NULL,
  `role` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  KEY `FK_dl7g53f7lpmorjc24kx74apx8` (`role`),
  CONSTRAINT `FK_dl7g53f7lpmorjc24kx74apx8` FOREIGN KEY (`role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (7,'\0','relyativus@gmail.com','2015-11-13 00:22:41','Anatoliy',NULL,'Vacaliuck','$2a$10$PAWNZlJTfppldYkUfQ29rOjGEzFMzl/oBNoluegDMAdckIwomUK9m','2014-11-13 00:22:41','$2a$10$eUWfxUaRGbskOwLdeOOiKeFLeahXWb5tVTQztNx2Mjqq5UvVt6zBu',2),(8,'\0','hash.cv@gmail.com','2015-11-13 00:24:28','Bidon',NULL,'Pomoev','$2a$10$g9Ed13ub5IQwCiYRM5vsauxxYBw9jIIMb4xxijIkWwsf6r/1694me','2014-11-13 00:24:28','$2a$10$eFk8QioFweJtG27iahREDuriTZkNnX1lgD9Z0iGGRr3PQrCBA3wpG',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-13 17:35:01
