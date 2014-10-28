CREATE DATABASE  IF NOT EXISTS `ssel` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ssel`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: ssel
-- ------------------------------------------------------
-- Server version	5.5.16

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
  `name` varchar(255) NOT NULL,
  `block_order` int(11) NOT NULL,
  `start_time` date NOT NULL,
  `id_subject` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_omq2djcq006ony43amegc9fsw` (`id_subject`),
  CONSTRAINT `FK_omq2djcq006ony43amegc9fsw` FOREIGN KEY (`id_subject`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `block`
--

LOCK TABLES `block` WRITE;
/*!40000 ALTER TABLE `block` DISABLE KEYS */;
INSERT INTO `block` VALUES (1,'2014-12-28','Java core',1,'2014-10-01',1),(2,'2014-12-28','Java EE',2,'2014-10-01',1),(3,'2014-12-28','Servlet API',3,'2014-10-01',1),(4,'2014-12-28','Hibernate',4,'2014-10-01',1),(5,'2014-12-28','Spring MVC',5,'2014-10-01',1),(6,'2014-12-31','Some module',6,'2014-01-01',1),(7,'2014-12-31','modulef',1,'2014-01-01',5);
/*!40000 ALTER TABLE `block` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Programming'),(2,'QC'),(3,'Mathematics'),(4,'Foreign languages');
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
  `end` date NOT NULL,
  `start` date NOT NULL,
  `id_subject` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fqne590oury2qgrfkytjyteq` (`id_subject`),
  CONSTRAINT `FK_fqne590oury2qgrfkytjyteq` FOREIGN KEY (`id_subject`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_scheduler`
--

LOCK TABLES `course_scheduler` WRITE;
/*!40000 ALTER TABLE `course_scheduler` DISABLE KEYS */;
INSERT INTO `course_scheduler` VALUES (1,'2014-12-30','2014-09-30',1),(3,'2014-09-30','2013-01-01',5),(4,'2014-11-01','2014-10-03',8),(5,'2014-10-01','2014-11-30',9);
/*!40000 ALTER TABLE `course_scheduler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_group`
--

LOCK TABLES `student_group` WRITE;
/*!40000 ALTER TABLE `student_group` DISABLE KEYS */;
INSERT INTO `student_group` VALUES (2,100,0,0,4,1),(3,101,0,0,3,1),(4,102,75,100,3,1),(5,103,0,0,5,1);
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
  `name` varchar(255) NOT NULL,
  `id_category` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1kftfvbujtv6y8isj6bw2s6i4` (`id_category`),
  KEY `FK_m0hq9p5ouxkaq56mc335e0fak` (`id_user`),
  CONSTRAINT `FK_1kftfvbujtv6y8isj6bw2s6i4` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`),
  CONSTRAINT `FK_m0hq9p5ouxkaq56mc335e0fak` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Java EE','Java EE',1,1),(5,'QC','Quality control',2,1),(8,'English','English',4,1),(9,'Mathematics','Mathematics',3,1);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (1,'\0','Some content\r\n','Java core',4,1),(2,'','Some content','Lection 1',2,1),(3,'\0','Some content','Lection 2',3,1),(5,'','Some content','Lection 3',1,1),(6,'','Some content','Lection 4',1,3),(8,'','Some content','why is QC?',1,7),(9,'\0','Some content','Lection 5',2,7);
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

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
  `lastName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `registration` datetime NOT NULL,
  `role` int(11) NOT NULL,
  `verificationkey` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dl7g53f7lpmorjc24kx74apx8` (`role`),
  CONSTRAINT `FK_dl7g53f7lpmorjc24kx74apx8` FOREIGN KEY (`role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'\0','student@gmail.com','2014-10-24 11:00:35','Lars','Hammet','1234','2014-10-24 11:00:35',2,NULL),(2,'\0','teacher@mail.ru','2014-10-24 11:01:18','Kirk','Ulrich','1234','2014-10-24 11:01:18',3,NULL),(6,'','ivan.khotynskyi@gmail.com','2014-10-27 19:39:20','Ivan','Khotynskyi','$2a$10$TT2FoBggqBjuEmtqaJrRgudlNTabyN8JTHYTxFifGhh0OgwhvAppu','2014-10-27 19:39:20',2,'$2a$10$J6ThawiiePRYibn7w.6a1.77l4V8WMOxQwH7FmKLaJyz0v1MF8H7u');
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

-- Dump completed on 2014-10-28 13:44:03
