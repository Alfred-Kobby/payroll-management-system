-- MySQL dump 10.13  Distrib 5.6.23, for osx10.8 (x86_64)
--
-- Host: localhost    Database: payroll
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
-- Table structure for table `Bonus`
--

DROP TABLE IF EXISTS `Bonus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Bonus` (
  `employeeNo` int(10) NOT NULL,
  `bonusDate` date NOT NULL,
  `bonusAmount` decimal(10,2) NOT NULL,
  `bonusTypeNo` int(5) NOT NULL,
  PRIMARY KEY (`employeeNo`,`bonusDate`),
  KEY `bonus_bonusType` (`bonusTypeNo`),
  CONSTRAINT `bonus_bonusType` FOREIGN KEY (`bonusTypeNo`) REFERENCES `BonusType` (`bonusTypeNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bonus_employee` FOREIGN KEY (`employeeNo`) REFERENCES `Employee` (`employeeNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bonus`
--

LOCK TABLES `Bonus` WRITE;
/*!40000 ALTER TABLE `Bonus` DISABLE KEYS */;
INSERT INTO `Bonus` VALUES (100,'2023-12-01',100.00,100);
/*!40000 ALTER TABLE `Bonus` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `bonus_after_update` AFTER UPDATE ON `Bonus`
FOR EACH ROW
BEGIN
    INSERT INTO UserActivities (username, activityType, timestamp, details)
    VALUES (USER(), 'update', NOW(), CONCAT('Bonus Record with employeeNo = ', NEW.employeeNo, ' and bonusDate = ', NEW.bonusDate, ' is an updated record. OLD Data: bonusDate = ', OLD.bonusDate, ', bonusAmount = ', OLD.bonusAmount, ', bonusTypeNo = ', OLD.bonusTypeNo));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `BonusType`
--

DROP TABLE IF EXISTS `BonusType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BonusType` (
  `bonusTypeNo` int(5) NOT NULL,
  `bonusDescription` varchar(150) NOT NULL,
  PRIMARY KEY (`bonusTypeNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BonusType`
--

LOCK TABLES `BonusType` WRITE;
/*!40000 ALTER TABLE `BonusType` DISABLE KEYS */;
INSERT INTO `BonusType` VALUES (100,'First Quater Bonus'),(101,'Second Quater Bonus'),(103,'Third Quater Bonus'),(104,'Fourth Quater Bonus'),(105,'End of Year Bonus');
/*!40000 ALTER TABLE `BonusType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DeductType`
--

DROP TABLE IF EXISTS `DeductType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DeductType` (
  `deductTypeNo` int(5) NOT NULL,
  `deductDescription` varchar(150) NOT NULL,
  PRIMARY KEY (`deductTypeNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DeductType`
--

LOCK TABLES `DeductType` WRITE;
/*!40000 ALTER TABLE `DeductType` DISABLE KEYS */;
/*!40000 ALTER TABLE `DeductType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Deduction`
--

DROP TABLE IF EXISTS `Deduction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Deduction` (
  `employeeNo` int(10) NOT NULL,
  `deductDate` date NOT NULL,
  `deductAmount` decimal(10,2) NOT NULL,
  `deductTypeNo` int(5) NOT NULL,
  PRIMARY KEY (`employeeNo`,`deductDate`),
  KEY `deduction_deductionType` (`deductTypeNo`),
  CONSTRAINT `deduction_deductionType` FOREIGN KEY (`deductTypeNo`) REFERENCES `DeductType` (`deductTypeNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `deduction_employee` FOREIGN KEY (`employeeNo`) REFERENCES `Employee` (`employeeNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Deduction`
--

LOCK TABLES `Deduction` WRITE;
/*!40000 ALTER TABLE `Deduction` DISABLE KEYS */;
/*!40000 ALTER TABLE `Deduction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Employee` (
  `employeeNo` int(10) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `phoneNumber` varchar(13) NOT NULL,
  `department` enum('Marketing','Engineering','Security','Projects','Client Service','Human Resource','Research') NOT NULL,
  `position` enum('Finance Officer','Back-End Engineer','Front-End Engineer','Devops Engineer','QA Engineer','UI/UX Designer','Security Officer','Project Coordinator','Client Service Specialist','Administration','Research Engineer') NOT NULL,
  `salary` decimal(10,2) NOT NULL,
  `employmentDate` date NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`employeeNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (100,'Kofi','Manu','0244938832','Engineering','Back-End Engineer',2000.00,'2020-12-12','2023-12-08 13:56:29','2023-12-08 13:56:29'),(102,'Yaw','kuma','0200383213','Projects','Project Coordinator',4000.00,'2022-10-01','2023-12-11 18:27:38','2023-12-11 18:27:38'),(103,'Abena','Dede','0266938472','Client Service','Client Service Specialist',8000.00,'2021-09-02','2023-12-11 18:33:42','2023-12-11 18:33:42'),(104,'Araba','Nimo','02077261202','Human Resource','Administration',5000.12,'2022-09-09','2023-12-11 18:55:24','2023-12-11 18:55:24'),(106,'Sam','Kodi','02099388467','Security','Security Officer',20.00,'2023-08-01','2023-12-12 21:44:11','2023-12-12 21:44:11');
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `employee_after_insert` AFTER INSERT ON `Employee`
FOR EACH ROW
BEGIN
    INSERT INTO UserActivities (username, activityType, timestamp, details)
    VALUES (USER(), 'insert', NOW(), CONCAT('New Employee Record with employeeNo = ', NEW.employeeNo, ' inserted in employee table'));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Holiday`
--

DROP TABLE IF EXISTS `Holiday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Holiday` (
  `employeeNo` int(10) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  PRIMARY KEY (`employeeNo`,`startDate`),
  KEY `end_date_idx` (`endDate`) USING BTREE,
  CONSTRAINT `holiday_employee` FOREIGN KEY (`employeeNo`) REFERENCES `Employee` (`employeeNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Holiday`
--

LOCK TABLES `Holiday` WRITE;
/*!40000 ALTER TABLE `Holiday` DISABLE KEYS */;
/*!40000 ALTER TABLE `Holiday` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PayDetails`
--

DROP TABLE IF EXISTS `PayDetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PayDetails` (
  `employeeNo` int(10) NOT NULL,
  `startDate` date NOT NULL,
  `routingNumber` int(5) NOT NULL,
  `accountType` enum('Savings Account','Current Account') NOT NULL,
  `bankName` varchar(150) NOT NULL,
  `bankAddress` varchar(150) NOT NULL,
  `payTypeNo` int(5) NOT NULL,
  PRIMARY KEY (`employeeNo`,`startDate`),
  KEY `account_type_idx` (`accountType`) USING BTREE,
  KEY `payDetails_payType` (`payTypeNo`),
  CONSTRAINT `payDetail_employee` FOREIGN KEY (`employeeNo`) REFERENCES `Employee` (`employeeNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `payDetails_Employee` FOREIGN KEY (`employeeNo`) REFERENCES `Employee` (`employeeNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `payDetails_payTpe` FOREIGN KEY (`payTypeNo`) REFERENCES `PayType` (`payTypeNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `payDetails_payType` FOREIGN KEY (`payTypeNo`) REFERENCES `PayType` (`payTypeNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PayDetails`
--

LOCK TABLES `PayDetails` WRITE;
/*!40000 ALTER TABLE `PayDetails` DISABLE KEYS */;
INSERT INTO `PayDetails` VALUES (100,'2023-10-05',1002,'Savings Account','Access Bank','Accra',100);
/*!40000 ALTER TABLE `PayDetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PayHistory`
--

DROP TABLE IF EXISTS `PayHistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PayHistory` (
  `payNo` int(10) NOT NULL,
  `employeeNo` int(10) NOT NULL,
  `payDate` date NOT NULL,
  `checkNumber` varchar(15) NOT NULL,
  `payAmount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`payNo`),
  KEY `check_idx` (`checkNumber`) USING BTREE,
  KEY `payHistory_employee` (`employeeNo`),
  CONSTRAINT `payHistory_employee` FOREIGN KEY (`employeeNo`) REFERENCES `Employee` (`employeeNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PayHistory`
--

LOCK TABLES `PayHistory` WRITE;
/*!40000 ALTER TABLE `PayHistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `PayHistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PayType`
--

DROP TABLE IF EXISTS `PayType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PayType` (
  `payTypeNo` int(5) NOT NULL,
  `payTypeDescription` varchar(150) NOT NULL,
  PRIMARY KEY (`payTypeNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PayType`
--

LOCK TABLES `PayType` WRITE;
/*!40000 ALTER TABLE `PayType` DISABLE KEYS */;
INSERT INTO `PayType` VALUES (100,'Main Salary'),(101,'Salary Advanced'),(102,'Loan');
/*!40000 ALTER TABLE `PayType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SickLeave`
--

DROP TABLE IF EXISTS `SickLeave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SickLeave` (
  `employeeNo` int(10) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `reason` varchar(255) NOT NULL,
  PRIMARY KEY (`employeeNo`,`startDate`),
  KEY `endDate_idx` (`endDate`) USING BTREE,
  CONSTRAINT `sickLeave_employee` FOREIGN KEY (`employeeNo`) REFERENCES `Employee` (`employeeNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SickLeave`
--

LOCK TABLES `SickLeave` WRITE;
/*!40000 ALTER TABLE `SickLeave` DISABLE KEYS */;
INSERT INTO `SickLeave` VALUES (100,'2023-12-11','2023-12-12','For Testing'),(106,'2023-12-11','2023-12-12','Run away');
/*!40000 ALTER TABLE `SickLeave` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `sickLeave_before_delete` BEFORE DELETE ON `SickLeave`
FOR EACH ROW
BEGIN
    INSERT INTO UserActivities (username, activityType, timestamp, details)
    VALUES (USER(), 'delete', NOW(), CONCAT('Sick leave Record with details employeeNo = ', OLD.employeeNo, ', startDate = ', OLD.startDate, ', endDate = ', OLD.endDate, ', reason = ', OLD.reason, ', is being deleted'));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `UserActivities`
--

DROP TABLE IF EXISTS `UserActivities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserActivities` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `activityType` enum('insert','update','delete') NOT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `details` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserActivities`
--

LOCK TABLES `UserActivities` WRITE;
/*!40000 ALTER TABLE `UserActivities` DISABLE KEYS */;
INSERT INTO `UserActivities` VALUES (1,'root@localhost','insert','2023-12-11 18:55:24','New Employee Record with employeeNo = 104 inserted in employee table'),(2,'root@localhost','update','2023-12-11 19:03:17','Bonus Record with employeeNo = 100 and bonusDate = 2023-12-07 is an updated record. OLD Data: bonusDate = 2023-12-06, bonusAmount = 120.00, bonusTypeNo = 101'),(3,'root@localhost','delete','2023-12-11 19:09:07','Sick leave Record with details employeeNo = 100, startDate = 2023-12-01, endDate = 2023-12-03, reason = General check up, is being deleted'),(4,'root@localhost','delete','2023-12-11 19:09:07','Sick leave Record with details employeeNo = 100, startDate = 2023-12-11, endDate = 2023-12-12, reason = For testing, is being deleted'),(5,'root@localhost','delete','2023-12-11 19:11:50','Sick leave Record with details employeeNo = 100, startDate = 2023-12-04, endDate = 2023-12-05, reason = For General Check Up, is being deleted'),(6,'root@localhost','insert','2023-12-12 21:44:10','New Employee Record with employeeNo = 106 inserted in employee table'),(7,'root@localhost','delete','2023-12-12 21:50:37','Sick leave Record with details employeeNo = 106, startDate = 2023-12-04, endDate = 2023-12-05, reason = For check up, is being deleted');
/*!40000 ALTER TABLE `UserActivities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-11 13:05:41
