CREATE DATABASE  IF NOT EXISTS `cash_register` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cash_register`;
-- MySQL dump 10.13  Distrib 5.6.17, for osx10.6 (i386)
--
-- Host: localhost    Database: cash_register
-- ------------------------------------------------------
-- Server version	5.6.23

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
-- Table structure for table `dollar`
--

DROP TABLE IF EXISTS `dollar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dollar` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Table key',
  `name` varchar(45) DEFAULT NULL COMMENT 'Name of dollar',
  `denomination` int(11) DEFAULT NULL COMMENT 'Dollar denomination',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dollar`
--

LOCK TABLES `dollar` WRITE;
/*!40000 ALTER TABLE `dollar` DISABLE KEYS */;
INSERT INTO `dollar` VALUES (1,'ONE',1),(2,'TWO',2),(3,'FIVE',5),(4,'TEN',10),(5,'TWENTY',20);
/*!40000 ALTER TABLE `dollar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `summary`
--

DROP TABLE IF EXISTS `summary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `summary` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Table key',
  `countOne` int(11) DEFAULT NULL COMMENT 'Total one dollar count',
  `countTwo` int(11) DEFAULT NULL COMMENT 'Total two dollar count',
  `countFive` int(11) DEFAULT NULL COMMENT 'Total five dollar count',
  `countTen` int(11) DEFAULT NULL COMMENT 'Total ten dollar count',
  `countTwenty` int(11) DEFAULT NULL COMMENT 'Total twenty dollar count',
  `valueOne` int(11) DEFAULT NULL COMMENT 'Total one dollar value',
  `valueTwo` int(11) DEFAULT NULL COMMENT 'Total two dollar value',
  `valueFive` int(11) DEFAULT NULL COMMENT 'Total five dollar value',
  `valueTen` int(11) DEFAULT NULL COMMENT 'Total ten dollar value',
  `valueTwenty` int(11) DEFAULT NULL COMMENT 'Total twenty dollar value',
  `valueSum` int(11) DEFAULT NULL COMMENT 'Sum of all values in current transaction',
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Creation date of the transaction',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `summary`
--

LOCK TABLES `summary` WRITE;
/*!40000 ALTER TABLE `summary` DISABLE KEYS */;
INSERT INTO `summary` VALUES (1,5,4,3,2,1,5,8,15,20,20,68,'2018-08-12 10:46:37');
/*!40000 ALTER TABLE `summary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Table key',
  `type` varchar(45) DEFAULT NULL COMMENT 'Type of transaction',
  `one` int(11) DEFAULT NULL COMMENT 'One dollar count in transaction',
  `two` int(11) DEFAULT NULL COMMENT 'Two dollar count in transaction',
  `five` int(11) DEFAULT NULL COMMENT 'Five dollar count in transaction',
  `ten` int(11) DEFAULT NULL COMMENT 'Ten dollar count in transaction',
  `twenty` int(11) DEFAULT NULL COMMENT 'Twenty dollar count in transaction',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Creation date of the transaction',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'init',5,4,3,2,1,'2018-08-12 11:32:47');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-12 22:55:01