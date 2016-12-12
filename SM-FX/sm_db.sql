CREATE DATABASE  IF NOT EXISTS `sm_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sm_db`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: sm_db
-- ------------------------------------------------------
-- Server version	5.5.44

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
-- Table structure for table `general_log`
--

DROP TABLE IF EXISTS `general_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `general_log` (
  `event_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_host` mediumtext NOT NULL,
  `thread_id` bigint(21) unsigned NOT NULL,
  `server_id` int(10) unsigned NOT NULL,
  `command_type` varchar(64) NOT NULL,
  `argument` mediumtext NOT NULL
) ENGINE=CSV DEFAULT CHARSET=utf8 COMMENT='General log';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `general_log`
--

LOCK TABLES `general_log` WRITE;
/*!40000 ALTER TABLE `general_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `general_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nid` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `description` varchar(100) NOT NULL,
  `ui` varchar(45) NOT NULL,
  `added_user` varchar(45) DEFAULT NULL,
  `date_added` date NOT NULL,
  `date_resolved` date DEFAULT NULL,
  `is_resolved` int(11) NOT NULL DEFAULT '0',
  `resolved_user` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_notify_notify_idx` (`type`),
  CONSTRAINT `fk_notify_notify` FOREIGN KEY (`type`) REFERENCES `user_notification_type` (`type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `printer`
--

DROP TABLE IF EXISTS `printer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `printer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_printer_printer_type_idx` (`type`),
  CONSTRAINT `fk_printer_printer_type` FOREIGN KEY (`type`) REFERENCES `printer_type` (`type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `printer`
--

LOCK TABLES `printer` WRITE;
/*!40000 ALTER TABLE `printer` DISABLE KEYS */;
INSERT INTO `printer` VALUES (1,'PRN0001','Microsoft XPS Document Writer','Banquet','Ultra fast Invisible printing');
/*!40000 ALTER TABLE `printer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `printer_report`
--

DROP TABLE IF EXISTS `printer_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `printer_report` (
  `pid` varchar(45) NOT NULL,
  `rid` varchar(45) NOT NULL,
  PRIMARY KEY (`pid`,`rid`),
  KEY `fk_pr_r_idx` (`rid`),
  CONSTRAINT `fk_pr_p` FOREIGN KEY (`pid`) REFERENCES `printer` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pr_r` FOREIGN KEY (`rid`) REFERENCES `report` (`rid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `printer_report`
--

LOCK TABLES `printer_report` WRITE;
/*!40000 ALTER TABLE `printer_report` DISABLE KEYS */;
INSERT INTO `printer_report` VALUES ('PRN0001','RPT0001');
/*!40000 ALTER TABLE `printer_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `printer_type`
--

DROP TABLE IF EXISTS `printer_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `printer_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`type`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `printer_type`
--

LOCK TABLES `printer_type` WRITE;
/*!40000 ALTER TABLE `printer_type` DISABLE KEYS */;
INSERT INTO `printer_type` VALUES (1,'Alacarte'),(2,'Banquet'),(3,'Room'),(4,'Stock');
/*!40000 ALTER TABLE `printer_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `url` varchar(200) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `is_Delete_privilege` int(11) DEFAULT '1',
  `is_Network` int(11) DEFAULT '1',
  PRIMARY KEY (`rid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_repot_report_type_idx` (`type`),
  CONSTRAINT `fk_repot_report_type_idx` FOREIGN KEY (`type`) REFERENCES `report_type` (`type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (1,'RPT0001','Profit And Loss Report','Finance','.//Reports//Profit&Loss.jasper',0,1,1),(32,'RPT0002','Stock Report','Stock','.//Reports//Stock.jasper',0,1,1);
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_reg`
--

DROP TABLE IF EXISTS `report_reg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_reg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `report_id` varchar(45) NOT NULL,
  `printer_id` varchar(45) NOT NULL,
  `status` int(11) DEFAULT '0',
  `user_id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `report_reg_fk1` (`report_id`),
  KEY `report_reg_fk2` (`printer_id`),
  KEY `report_reg_fk3` (`user_id`),
  CONSTRAINT `report_reg_fk1` FOREIGN KEY (`report_id`) REFERENCES `report` (`rid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `report_reg_fk2` FOREIGN KEY (`printer_id`) REFERENCES `printer` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `report_reg_fk3` FOREIGN KEY (`user_id`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_reg`
--

LOCK TABLES `report_reg` WRITE;
/*!40000 ALTER TABLE `report_reg` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_reg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_type`
--

DROP TABLE IF EXISTS `report_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`type`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_type`
--

LOCK TABLES `report_type` WRITE;
/*!40000 ALTER TABLE `report_type` DISABLE KEYS */;
INSERT INTO `report_type` VALUES (1,'Finance'),(2,'Stock');
/*!40000 ALTER TABLE `report_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scale`
--

DROP TABLE IF EXISTS `scale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scale` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `weight_scale_id` varchar(45) NOT NULL,
  `scale_id` varchar(45) NOT NULL,
  `size` varchar(45) DEFAULT NULL,
  `no` varchar(45) DEFAULT NULL,
  `t_no_shift` varchar(45) DEFAULT NULL,
  `net_weight` double DEFAULT NULL,
  `file_type` varchar(200) DEFAULT NULL,
  `gauge` varchar(200) DEFAULT NULL,
  `qty` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `gross_weight` double DEFAULT NULL,
  `time_stamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`weight_scale_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `scale_fk1_idx` (`scale_id`),
  CONSTRAINT `scale_fk1` FOREIGN KEY (`scale_id`) REFERENCES `scale_register` (`scale_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scale`
--

LOCK TABLES `scale` WRITE;
/*!40000 ALTER TABLE `scale` DISABLE KEYS */;
/*!40000 ALTER TABLE `scale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scale_register`
--

DROP TABLE IF EXISTS `scale_register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scale_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `scale_id` varchar(45) NOT NULL,
  `scale_name` varchar(45) DEFAULT NULL,
  `com_port` varchar(45) DEFAULT NULL,
  `board_rate` int(11) DEFAULT NULL,
  PRIMARY KEY (`scale_id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scale_register`
--

LOCK TABLES `scale_register` WRITE;
/*!40000 ALTER TABLE `scale_register` DISABLE KEYS */;
/*!40000 ALTER TABLE `scale_register` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `server_config`
--

DROP TABLE IF EXISTS `server_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `server_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(20) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `server_config`
--

LOCK TABLES `server_config` WRITE;
/*!40000 ALTER TABLE `server_config` DISABLE KEYS */;
INSERT INTO `server_config` VALUES (1,'127.0.0.1',1099);
/*!40000 ALTER TABLE `server_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slow_log`
--

DROP TABLE IF EXISTS `slow_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `slow_log` (
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_host` mediumtext NOT NULL,
  `query_time` time NOT NULL,
  `lock_time` time NOT NULL,
  `rows_sent` int(11) NOT NULL,
  `rows_examined` int(11) NOT NULL,
  `db` varchar(512) NOT NULL,
  `last_insert_id` int(11) NOT NULL,
  `insert_id` int(11) NOT NULL,
  `server_id` int(10) unsigned NOT NULL,
  `sql_text` mediumtext NOT NULL,
  `thread_id` bigint(21) unsigned NOT NULL
) ENGINE=CSV DEFAULT CHARSET=utf8 COMMENT='Slow log';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slow_log`
--

LOCK TABLES `slow_log` WRITE;
/*!40000 ALTER TABLE `slow_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `slow_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `EID` varchar(45) NOT NULL,
  `title` varchar(10) NOT NULL,
  `name` varchar(200) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `spec_code` varchar(45) DEFAULT NULL,
  `flag` varchar(45) DEFAULT NULL,
  `category_type` varchar(45) NOT NULL,
  `is_canceled` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`EID`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  KEY `usertype` (`flag`),
  KEY `categoryFK_idx` (`category_type`),
  KEY `user` (`flag`),
  KEY `user_fk2` (`category_type`),
  CONSTRAINT `user_fk1` FOREIGN KEY (`flag`) REFERENCES `user_type` (`type`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `user_fk2` FOREIGN KEY (`category_type`) REFERENCES `user_sub_type` (`type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (8,'EM0004','Mr.','Saiton','saiton','3CRO+GzZlkI=',NULL,'Manager','Administrator',0),(9,'EM0005','Mr.','admin','admin','3CRO+GzZlkI=',NULL,'Ultra User','Administrator',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_notification_type`
--

DROP TABLE IF EXISTS `user_notification_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_notification_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`type`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_notification_type`
--

LOCK TABLES `user_notification_type` WRITE;
/*!40000 ALTER TABLE `user_notification_type` DISABLE KEYS */;
INSERT INTO `user_notification_type` VALUES (8,'Stock');
/*!40000 ALTER TABLE `user_notification_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_notifications`
--

DROP TABLE IF EXISTS `user_notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_notifications` (
  `EID` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `show` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`EID`,`type`),
  KEY `fkusernotf2_idx` (`type`),
  CONSTRAINT `fkusernotf1` FOREIGN KEY (`EID`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fkusernotf2` FOREIGN KEY (`type`) REFERENCES `user_notification_type` (`type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_notifications`
--

LOCK TABLES `user_notifications` WRITE;
/*!40000 ALTER TABLE `user_notifications` DISABLE KEYS */;
INSERT INTO `user_notifications` VALUES ('EM0005','Stock',0);
/*!40000 ALTER TABLE `user_notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_permission_type`
--

DROP TABLE IF EXISTS `user_permission_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_permission_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`type`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_permission_type`
--

LOCK TABLES `user_permission_type` WRITE;
/*!40000 ALTER TABLE `user_permission_type` DISABLE KEYS */;
INSERT INTO `user_permission_type` VALUES (73,'User Registration'),(128,'Weight Scale'),(129,'Report Generator'),(130,'Report Registration'),(131,'Report Settings'),(132,'Printer Registration'),(133,'Scale Registration');
/*!40000 ALTER TABLE `user_permission_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_permissions`
--

DROP TABLE IF EXISTS `user_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_permissions` (
  `EID` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `allow_insert` int(11) NOT NULL DEFAULT '0',
  `allow_update` int(11) NOT NULL DEFAULT '0',
  `allow_delete` int(11) NOT NULL DEFAULT '0',
  `allow_view` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`EID`,`type`),
  KEY `fkuserperf2_idx` (`type`),
  CONSTRAINT `fkuserperf1` FOREIGN KEY (`EID`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fkuserperf2` FOREIGN KEY (`type`) REFERENCES `user_permission_type` (`type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_permissions`
--

LOCK TABLES `user_permissions` WRITE;
/*!40000 ALTER TABLE `user_permissions` DISABLE KEYS */;
INSERT INTO `user_permissions` VALUES ('EM0004','Printer Registration',1,1,1,1),('EM0004','Report Generator',1,1,1,1),('EM0004','Report Registration',1,1,1,1),('EM0004','Report Settings',1,1,1,1),('EM0004','Scale Registration',1,1,1,1),('EM0004','User Registration',1,1,1,1),('EM0004','Weight Scale',1,1,1,1),('EM0005','Printer Registration',1,1,1,1),('EM0005','Report Generator',1,1,1,1),('EM0005','Report Registration',1,1,1,1),('EM0005','Report Settings',1,1,1,1),('EM0005','Scale Registration',1,1,1,1),('EM0005','User Registration',1,1,1,1),('EM0005','Weight Scale',1,1,1,1);
/*!40000 ALTER TABLE `user_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_sub_type`
--

DROP TABLE IF EXISTS `user_sub_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_sub_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`type`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_sub_type`
--

LOCK TABLES `user_sub_type` WRITE;
/*!40000 ALTER TABLE `user_sub_type` DISABLE KEYS */;
INSERT INTO `user_sub_type` VALUES (1,'Stock'),(2,'Administrator'),(3,'Staff');
/*!40000 ALTER TABLE `user_sub_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`type`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` VALUES (1,'Ultra User'),(2,'Super User'),(3,'Accountant'),(4,'Manager'),(5,'Walker');
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-12 19:51:57
