CREATE DATABASE  IF NOT EXISTS `zmb_pribor` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `zmb_pribor`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: zmb_pribor
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sso_id` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `patronymic` varchar(30) DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sso_id` (`sso_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` VALUES (1,'Dima','$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm','Нефедьев','Дмитрий','Викторович','dima@xyz.com',1);
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_user_user_profile`
--

DROP TABLE IF EXISTS `app_user_user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_user_user_profile` (
  `user_id` bigint NOT NULL,
  `user_profile_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`user_profile_id`),
  KEY `FK_USER_PROFILE` (`user_profile_id`),
  CONSTRAINT `FK_APP_USER` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK_USER_PROFILE` FOREIGN KEY (`user_profile_id`) REFERENCES `user_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user_user_profile`
--

LOCK TABLES `app_user_user_profile` WRITE;
/*!40000 ALTER TABLE `app_user_user_profile` DISABLE KEYS */;
INSERT INTO `app_user_user_profile` VALUES (1,2);
/*!40000 ALTER TABLE `app_user_user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `archive`
--

DROP TABLE IF EXISTS `archive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `archive` (
  `id_archive` int NOT NULL AUTO_INCREMENT,
  `location_id` int NOT NULL,
  `sensor_id` int NOT NULL,
  `install_date` datetime NOT NULL,
  `note` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_archive`),
  UNIQUE KEY `id_archive_UNIQUE` (`id_archive`),
  KEY `fk_archive_sensor_idx` (`sensor_id`),
  KEY `fk_archive_location_idx` (`location_id`),
  CONSTRAINT `fk_archive_location` FOREIGN KEY (`location_id`) REFERENCES `location` (`id_location`),
  CONSTRAINT `fk_archive_sensor` FOREIGN KEY (`sensor_id`) REFERENCES `sensor` (`id_sensor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archive`
--

LOCK TABLES `archive` WRITE;
/*!40000 ALTER TABLE `archive` DISABLE KEYS */;
/*!40000 ALTER TABLE `archive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id_country` int NOT NULL AUTO_INCREMENT,
  `country_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_country`),
  UNIQUE KEY `country_name_UNIQUE` (`country_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disposal`
--

DROP TABLE IF EXISTS `disposal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `disposal` (
  `id_disposal` int NOT NULL AUTO_INCREMENT,
  `type_sensor` varchar(45) NOT NULL,
  `model_sensor` varchar(45) NOT NULL,
  `version_sensor` varchar(45) NOT NULL,
  `number_sensor` varchar(45) NOT NULL,
  `inventory_number_sensor` varchar(45) DEFAULT NULL,
  `date_archive` datetime NOT NULL,
  `date_disposal` datetime NOT NULL,
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_disposal`),
  UNIQUE KEY `id_disposal_UNIQUE` (`id_disposal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disposal`
--

LOCK TABLES `disposal` WRITE;
/*!40000 ALTER TABLE `disposal` DISABLE KEYS */;
/*!40000 ALTER TABLE `disposal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `firm`
--

DROP TABLE IF EXISTS `firm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `firm` (
  `id_firm` int NOT NULL AUTO_INCREMENT,
  `name_firm` varchar(45) NOT NULL,
  PRIMARY KEY (`id_firm`),
  UNIQUE KEY `name_firm_UNIQUE` (`name_firm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `firm`
--

LOCK TABLES `firm` WRITE;
/*!40000 ALTER TABLE `firm` DISABLE KEYS */;
/*!40000 ALTER TABLE `firm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `id_location` int NOT NULL AUTO_INCREMENT,
  `name_loc` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ser_number` varchar(50) DEFAULT NULL,
  `inv_number` varchar(50) DEFAULT NULL,
  `work_loc` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_location`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'Склад ГТЭС КИП',NULL,NULL,1);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `location_BEFORE_DELETE id1` BEFORE DELETE ON `location` FOR EACH ROW BEGIN
IF OLD.id_location = 1 THEN
SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'NO DELENE STOCK';
END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `location_install`
--

DROP TABLE IF EXISTS `location_install`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location_install` (
  `id_location_install` int NOT NULL AUTO_INCREMENT,
  `location_id` int NOT NULL,
  `sensor_id` int NOT NULL,
  PRIMARY KEY (`id_location_install`),
  UNIQUE KEY `id_location_install_UNIQUE` (`id_location_install`),
  UNIQUE KEY `sensor_id` (`sensor_id`),
  KEY `fk_location_install_sensor_idx` (`sensor_id`),
  KEY `fk_location_install_location_idx` (`location_id`),
  CONSTRAINT `fk_location_install_location` FOREIGN KEY (`location_id`) REFERENCES `location` (`id_location`),
  CONSTRAINT `fk_location_install_sensor` FOREIGN KEY (`sensor_id`) REFERENCES `sensor` (`id_sensor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_install`
--

LOCK TABLES `location_install` WRITE;
/*!40000 ALTER TABLE `location_install` DISABLE KEYS */;
/*!40000 ALTER TABLE `location_install` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model_sensor`
--

DROP TABLE IF EXISTS `model_sensor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model_sensor` (
  `id_model` int NOT NULL AUTO_INCREMENT,
  `model_name` varchar(45) NOT NULL,
  `model_version` varchar(45) DEFAULT NULL,
  `typesens_id` int NOT NULL,
  PRIMARY KEY (`id_model`),
  KEY `fk_model_typesens_idx` (`typesens_id`),
  CONSTRAINT `fk_model_typesens` FOREIGN KEY (`typesens_id`) REFERENCES `typesens` (`id_typesens`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model_sensor`
--

LOCK TABLES `model_sensor` WRITE;
/*!40000 ALTER TABLE `model_sensor` DISABLE KEYS */;
/*!40000 ALTER TABLE `model_sensor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sensor`
--

DROP TABLE IF EXISTS `sensor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sensor` (
  `id_sensor` int NOT NULL AUTO_INCREMENT,
  `model_id` int NOT NULL,
  `sensor_numb` varchar(45) NOT NULL,
  `inventory_numb` varchar(45) DEFAULT NULL,
  `unit_id` int NOT NULL,
  `range_min` int DEFAULT NULL,
  `range_max` int DEFAULT NULL,
  `country_id` int NOT NULL,
  `firm_id` int NOT NULL,
  `date_manufacture` date NOT NULL,
  `date_verification` date NOT NULL,
  `interval_verification` int NOT NULL,
  `verification` tinyint(1) NOT NULL DEFAULT '0',
  `calibration` tinyint(1) NOT NULL DEFAULT '0',
  `passport` tinyint(1) NOT NULL,
  `location_id` int DEFAULT NULL,
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_sensor`),
  UNIQUE KEY `sensor_numb_UNIQUE` (`sensor_numb`),
  KEY `fk_sensor_country_idx` (`country_id`),
  KEY `fk_sensor_model_idx` (`model_id`),
  KEY `fk_sensor_firm_idx` (`firm_id`),
  KEY `fk_sensor_unit_idx` (`unit_id`),
  KEY `fk_sensor_location_idx` (`location_id`),
  CONSTRAINT `fk_sensor_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id_country`),
  CONSTRAINT `fk_sensor_firm` FOREIGN KEY (`firm_id`) REFERENCES `firm` (`id_firm`),
  CONSTRAINT `fk_sensor_location` FOREIGN KEY (`location_id`) REFERENCES `location` (`id_location`),
  CONSTRAINT `fk_sensor_model` FOREIGN KEY (`model_id`) REFERENCES `model_sensor` (`id_model`),
  CONSTRAINT `fk_sensor_unit` FOREIGN KEY (`unit_id`) REFERENCES `unit` (`id_unit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sensor`
--

LOCK TABLES `sensor` WRITE;
/*!40000 ALTER TABLE `sensor` DISABLE KEYS */;
/*!40000 ALTER TABLE `sensor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typesens`
--

DROP TABLE IF EXISTS `typesens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `typesens` (
  `id_typesens` int NOT NULL AUTO_INCREMENT,
  `name_type` varchar(45) NOT NULL,
  PRIMARY KEY (`id_typesens`),
  UNIQUE KEY `name_type_UNIQUE` (`name_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typesens`
--

LOCK TABLES `typesens` WRITE;
/*!40000 ALTER TABLE `typesens` DISABLE KEYS */;
/*!40000 ALTER TABLE `typesens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unit` (
  `id_unit` int NOT NULL AUTO_INCREMENT,
  `unit_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_unit`),
  UNIQUE KEY `unit_name_UNIQUE` (`unit_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_profile` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` VALUES (2,'ADMIN'),(4,'DBA'),(3,'KIP'),(1,'USER');
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'zmb_pribor'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-20  9:48:26
