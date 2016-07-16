-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: cfcmms
-- ------------------------------------------------------
-- Server version	5.7.9

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
-- Table structure for table `City`
--

DROP TABLE IF EXISTS `City`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `City` (
  `tf_cityId` varchar(4) NOT NULL,
  `tf_inputdate` datetime DEFAULT NULL,
  `tf_inputmen` varchar(10) DEFAULT NULL,
  `tf_shLocked` bit(1) DEFAULT NULL,
  `tf_shNowCount` int(11) DEFAULT NULL,
  `tf_shResult` varchar(10) DEFAULT NULL,
  `tf_shResultDate` datetime DEFAULT NULL,
  `tf_shResultExplain` varchar(255) DEFAULT NULL,
  `tf_shResultName` varchar(10) DEFAULT NULL,
  `tf_shdate1` datetime DEFAULT NULL,
  `tf_shdate2` datetime DEFAULT NULL,
  `tf_shdate3` datetime DEFAULT NULL,
  `tf_shdate4` datetime DEFAULT NULL,
  `tf_shdate5` datetime DEFAULT NULL,
  `tf_shdate6` datetime DEFAULT NULL,
  `tf_shexplain1` varchar(255) DEFAULT NULL,
  `tf_shexplain2` varchar(255) DEFAULT NULL,
  `tf_shexplain3` varchar(255) DEFAULT NULL,
  `tf_shexplain4` varchar(255) DEFAULT NULL,
  `tf_shexplain5` varchar(255) DEFAULT NULL,
  `tf_shexplain6` varchar(255) DEFAULT NULL,
  `tf_shname1` varchar(10) DEFAULT NULL,
  `tf_shname2` varchar(10) DEFAULT NULL,
  `tf_shname3` varchar(10) DEFAULT NULL,
  `tf_shname4` varchar(10) DEFAULT NULL,
  `tf_shname5` varchar(10) DEFAULT NULL,
  `tf_shname6` varchar(10) DEFAULT NULL,
  `tf_shresult1` varchar(20) DEFAULT NULL,
  `tf_shresult2` varchar(20) DEFAULT NULL,
  `tf_shresult3` varchar(20) DEFAULT NULL,
  `tf_shresult4` varchar(20) DEFAULT NULL,
  `tf_shresult5` varchar(20) DEFAULT NULL,
  `tf_shresult6` varchar(20) DEFAULT NULL,
  `tf_money` double DEFAULT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_postNumber` varchar(6) DEFAULT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_telHead` varchar(6) DEFAULT NULL,
  `tf_provinceId` varchar(10) NOT NULL,
  PRIMARY KEY (`tf_cityId`),
  UNIQUE KEY `tf_name` (`tf_name`),
  KEY `FK200D8B8E131488` (`tf_provinceId`),
  CONSTRAINT `FK_City_Province` FOREIGN KEY (`tf_provinceId`) REFERENCES `Province` (`tf_provinceId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `City`
--

LOCK TABLES `City` WRITE;
/*!40000 ALTER TABLE `City` DISABLE KEYS */;
INSERT INTO `City` VALUES ('0301',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5668,'石家庄市',NULL,NULL,NULL,'03'),('0302',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6500,'唐山市',NULL,NULL,NULL,'03'),('0303',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2300,'秦皇岛市',NULL,NULL,NULL,'03'),('0304',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1237,'邯郸市',NULL,NULL,NULL,'03'),('0305',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4327,'邢台市',NULL,NULL,NULL,'03'),('0306',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6530,'承德市',NULL,NULL,NULL,'03'),('0307',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4809,'沧州市',NULL,NULL,NULL,'03'),('0308',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2580,'保定市',NULL,NULL,NULL,'03'),('0309',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1854,'廊坊市',NULL,NULL,NULL,'03'),('0310',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6421,'衡水市',NULL,NULL,NULL,'03'),('0311',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,7234,'张家口市',NULL,NULL,NULL,'03'),('0701',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10000,'南京市',NULL,NULL,NULL,'07'),('0702',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,12523,'无锡市',NULL,NULL,NULL,'07'),('0703',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,9156.45,'徐州市',NULL,NULL,NULL,'07'),('0704',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,8897,'常州市',NULL,NULL,NULL,'07'),('0705',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,30612,'苏州市',NULL,NULL,NULL,'07'),('0706',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5600,'南通市',NULL,NULL,NULL,'07'),('0707',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2423,'淮安市',NULL,NULL,NULL,'07'),('0708',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3043,'盐城市',NULL,NULL,NULL,'07'),('0709',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6156,'扬州市',NULL,NULL,NULL,'07'),('0710',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2653,'镇江市',NULL,NULL,NULL,'07'),('0711',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1726,'泰州市',NULL,NULL,NULL,'07'),('0712',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1232,'宿迁市',NULL,NULL,NULL,'07'),('0713',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3167,'连云港市',NULL,NULL,NULL,'07'),('1001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,32123,'杭州市',NULL,NULL,NULL,'10'),('1002',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6743,'宁波市',NULL,NULL,NULL,'10'),('1003',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5321,'温州市',NULL,NULL,NULL,'10'),('1004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,12354,'瑞安市',NULL,NULL,NULL,'10'),('1005',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4325,'嘉兴市',NULL,NULL,NULL,'10'),('1006',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4567,'湖州市',NULL,NULL,NULL,'10'),('1007',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6789,'绍兴市',NULL,NULL,NULL,'10'),('1008',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,7890,'金华市',NULL,NULL,NULL,'10'),('1009',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5276,'衢州市',NULL,NULL,NULL,'10'),('1010',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,23456,'舟山市',NULL,NULL,NULL,'10'),('1011',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4321,'台州市',NULL,NULL,NULL,'10'),('1012',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,7300,'丽水市',NULL,NULL,NULL,'10'),('1201',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'青岛市',NULL,NULL,NULL,'06');
/*!40000 ALTER TABLE `City` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Customer` (
  `tf_customerId` int(11) NOT NULL,
  `tf_address` varchar(50) DEFAULT NULL,
  `tf_linkman` varchar(10) DEFAULT NULL,
  `tf_linkmanTel` varchar(20) DEFAULT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_remark` varchar(20) DEFAULT NULL,
  `tf_taxId` varchar(20) DEFAULT NULL,
  `tf_cityId` varchar(4) NOT NULL,
  `tf_rateId` varchar(2) NOT NULL,
  `tf_tradeId` varchar(6) NOT NULL,
  `tf_otherCityId` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`tf_customerId`),
  KEY `FK27FBE3FE954E3A68` (`tf_rateId`),
  KEY `FK27FBE3FE7C1FDCBE` (`tf_cityId`),
  KEY `FK27FBE3FE9A5F4CF6` (`tf_tradeId`),
  KEY `newkdadfasdf_idx` (`tf_otherCityId`),
  CONSTRAINT `FK27FBE3FE7C1FDCBE` FOREIGN KEY (`tf_cityId`) REFERENCES `City` (`tf_cityId`),
  CONSTRAINT `FK27FBE3FE954E3A68` FOREIGN KEY (`tf_rateId`) REFERENCES `Rate` (`tf_rateId`),
  CONSTRAINT `FK27FBE3FE9A5F4CF6` FOREIGN KEY (`tf_tradeId`) REFERENCES `Trade` (`tf_tradeId`),
  CONSTRAINT `newkdadfasdf` FOREIGN KEY (`tf_otherCityId`) REFERENCES `City` (`tf_cityId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (1,NULL,NULL,NULL,'无锡市太乙公司',NULL,NULL,'0702','20','1224','0306'),(2,NULL,NULL,NULL,'无锡市太湖公司分公司',NULL,NULL,'0702','20','1221','0307'),(3,NULL,NULL,NULL,'无锡市太湖汽车制造公司',NULL,NULL,'0704','20','1222','0704'),(4,NULL,NULL,NULL,'无锡市第一设备制造公司',NULL,NULL,'0702','10','1222','0705'),(5,NULL,NULL,NULL,'苏州市虎丘贸易公司',NULL,NULL,'0705','10','1502','0301'),(6,NULL,NULL,NULL,'苏州市捷运达贸易公司',NULL,NULL,'0705','10','1502',NULL),(7,NULL,NULL,NULL,'苏州市配件制造公司贸易公司',NULL,NULL,'0705','30','1224',NULL),(8,NULL,NULL,NULL,'苏州市第二配件制造公司贸易公司',NULL,NULL,'0705','30','1224',NULL),(9,NULL,NULL,NULL,'承德市避暑山庄有限公司',NULL,NULL,'0306','30','1502',NULL),(10,NULL,NULL,NULL,'承德市三保制造有限公司',NULL,NULL,'0306','20','1222',NULL),(11,NULL,NULL,NULL,'承德市华好有限有限公司',NULL,NULL,'0306','20','1501',NULL),(12,NULL,NULL,NULL,'承德市三友贸易有限公司',NULL,NULL,'0306','20','1502',NULL),(13,NULL,NULL,NULL,'承德市石家庄有限公司',NULL,NULL,'0301','20','1501',NULL),(14,NULL,NULL,NULL,'承德市承上启下有限公司',NULL,NULL,'0301','20','1222',NULL),(15,NULL,NULL,NULL,'承德市保关有限公司',NULL,NULL,'0301','10','1222',NULL),(16,NULL,NULL,NULL,'新增的无锡市单位',NULL,NULL,'0702','20','1223',NULL);
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IncomeDetail`
--

DROP TABLE IF EXISTS `IncomeDetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IncomeDetail` (
  `tf_incomeDetailId` int(11) NOT NULL,
  `tf_date` datetime NOT NULL,
  `tf_imcomePrice` double NOT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_ordersId` int(11) NOT NULL,
  PRIMARY KEY (`tf_incomeDetailId`),
  KEY `FK8184577AA6D049F2` (`tf_ordersId`),
  CONSTRAINT `FK8184577AA6D049F2` FOREIGN KEY (`tf_ordersId`) REFERENCES `Orders` (`tf_ordersId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IncomeDetail`
--

LOCK TABLES `IncomeDetail` WRITE;
/*!40000 ALTER TABLE `IncomeDetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `IncomeDetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Orders` (
  `tf_ordersId` int(11) NOT NULL,
  `tf_date` datetime NOT NULL,
  `tf_finished` bit(1) NOT NULL,
  `tf_ordersNumber` varchar(20) NOT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_year` varchar(5) DEFAULT NULL,
  `tf_customerId` int(11) NOT NULL,
  `tf_salesmanId` int(11) NOT NULL,
  `tf_storageId` int(11) DEFAULT NULL,
  `tf_fromCityId` varchar(4) DEFAULT NULL,
  `tf_toCityId` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`tf_ordersId`),
  KEY `FK8D444F05820B4A64` (`tf_customerId`),
  KEY `FK8D444F0596C83044` (`tf_salesmanId`),
  KEY `FK8D444F0586CECCA4` (`tf_storageId`),
  KEY `FK8D444F0599610E68` (`tf_fromCityId`),
  KEY `FK8D444F051C1AFBB9` (`tf_toCityId`),
  CONSTRAINT `FK8D444F051C1AFBB9` FOREIGN KEY (`tf_toCityId`) REFERENCES `City` (`tf_cityId`),
  CONSTRAINT `FK8D444F05820B4A64` FOREIGN KEY (`tf_customerId`) REFERENCES `Customer` (`tf_customerId`),
  CONSTRAINT `FK8D444F0586CECCA4` FOREIGN KEY (`tf_storageId`) REFERENCES `Storage` (`tf_storageId`),
  CONSTRAINT `FK8D444F0596C83044` FOREIGN KEY (`tf_salesmanId`) REFERENCES `Salesman` (`tf_salesmanId`),
  CONSTRAINT `FK8D444F0599610E68` FOREIGN KEY (`tf_fromCityId`) REFERENCES `City` (`tf_cityId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
INSERT INTO `Orders` VALUES (1,'2016-01-12 00:00:00',0x00,'order9016-01-12',NULL,NULL,2,1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrdersDetail`
--

DROP TABLE IF EXISTS `OrdersDetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrdersDetail` (
  `tf_ordersDetailId` int(11) NOT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_number` int(11) NOT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_subtotalPrice` double DEFAULT NULL,
  `tf_unitPrice` double NOT NULL,
  `tf_ordersId` int(11) NOT NULL,
  `tf_productId` int(11) NOT NULL,
  PRIMARY KEY (`tf_ordersDetailId`),
  KEY `FK18DB3BB64151148C` (`tf_productId`),
  KEY `FK18DB3BB6A6D049F2` (`tf_ordersId`),
  CONSTRAINT `FK18DB3BB64151148C` FOREIGN KEY (`tf_productId`) REFERENCES `Product` (`tf_productId`),
  CONSTRAINT `FK18DB3BB6A6D049F2` FOREIGN KEY (`tf_ordersId`) REFERENCES `Orders` (`tf_ordersId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrdersDetail`
--

LOCK TABLES `OrdersDetail` WRITE;
/*!40000 ALTER TABLE `OrdersDetail` DISABLE KEYS */;
INSERT INTO `OrdersDetail` VALUES (1,'40寸电视机若干',10,NULL,NULL,12222,1,1);
/*!40000 ALTER TABLE `OrdersDetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product` (
  `tf_productId` int(11) NOT NULL,
  `tf_costPrice` double DEFAULT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_origin` varchar(20) DEFAULT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_unitPrice` double DEFAULT NULL,
  `tf_productClassId` varchar(6) NOT NULL,
  `tf_cityId` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`tf_productId`),
  KEY `FK50C664CFD2D2E91A` (`tf_productClassId`),
  KEY `FK50C664CF7C1FDCBE` (`tf_cityId`),
  CONSTRAINT `FK50C664CF7C1FDCBE` FOREIGN KEY (`tf_cityId`) REFERENCES `City` (`tf_cityId`),
  CONSTRAINT `FK50C664CFD2D2E91A` FOREIGN KEY (`tf_productClassId`) REFERENCES `ProductClass` (`tf_productClassId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
INSERT INTO `Product` VALUES (1,0,'40寸电视机','广州',NULL,0,'10',NULL),(2,0,'50寸电视机','广州',NULL,0,'10',NULL),(3,0,'餐贴纸','无锡',NULL,0,'20',NULL),(4,0,'餐贴纸','无锡',NULL,0,'20',NULL);
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProductClass`
--

DROP TABLE IF EXISTS `ProductClass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProductClass` (
  `tf_productClassId` varchar(6) NOT NULL,
  `tf_name` varchar(50) NOT NULL,
  PRIMARY KEY (`tf_productClassId`),
  UNIQUE KEY `tf_name` (`tf_name`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProductClass`
--

LOCK TABLES `ProductClass` WRITE;
/*!40000 ALTER TABLE `ProductClass` DISABLE KEYS */;
INSERT INTO `ProductClass` VALUES ('10','电器'),('30','化妆品'),('20','日用品');
/*!40000 ALTER TABLE `ProductClass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Province`
--

DROP TABLE IF EXISTS `Province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Province` (
  `tf_provinceId` varchar(10) NOT NULL,
  `tf_inputdate` datetime NOT NULL,
  `tf_inputmen` varchar(10) NOT NULL,
  `tf_GDP` double DEFAULT NULL,
  `tf_area` int(11) DEFAULT NULL,
  `tf_createDate` datetime DEFAULT NULL,
  `tf_district` varchar(50) DEFAULT NULL,
  `tf_isMunicipality` bit(1) DEFAULT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_numberOfPeople` int(11) DEFAULT NULL,
  `tf_percent` decimal(10,4) DEFAULT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_shortname` varchar(10) NOT NULL,
  `tf_orderId` int(11) DEFAULT NULL,
  `tf_auditingDate` datetime DEFAULT NULL,
  `tf_auditingName` varchar(10) DEFAULT NULL,
  `tf_auditingRemark` varchar(100) DEFAULT NULL,
  `tf_auditinged` bit(1) DEFAULT NULL,
  `tf_map` longblob,
  PRIMARY KEY (`tf_provinceId`),
  UNIQUE KEY `IX_Province_tf_name` (`tf_name`),
  UNIQUE KEY `IX_Province_tf_shortname` (`tf_shortname`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Province`
--

LOCK TABLES `Province` WRITE;
/*!40000 ALTER TABLE `Province` DISABLE KEYS */;
INSERT INTO `Province` VALUES ('01','2015-10-01 16:06:51','管理员',8879,16800,'2010-01-01 00:00:00','华北地区',0x00,'北京市',1423,0.1400,NULL,'京',10,'2015-10-12 00:00:00','管理员','asdfefdssefd',0x01,NULL),('02','2015-10-01 16:06:51','管理员',5014,11300,'2010-01-02 00:00:00','华北地区',0x00,'天津市',1007,0.1020,NULL,'津',20,NULL,NULL,'批量自动审核',0x00,NULL),('03','2015-10-01 16:06:51','管理员',13388,187700,'2010-01-02 00:00:00','华北地区',0x00,'河北省',6735,0.6734,NULL,'冀',30,NULL,NULL,'批量自动审核',0x00,NULL),('04','2015-10-01 16:06:51','管理员',5465,156300,'2010-04-01 00:00:00','华北地区',0x00,'山西省',3294,0.3300,'增值税专用发票','晋',40,NULL,NULL,NULL,0x00,NULL),('05','2015-10-01 16:06:51','管理员',6140,1183000,'2015-11-16 00:00:00','华北地区',0x01,'内蒙古自治区',2368,0.2400,NULL,'蒙',50,NULL,NULL,NULL,NULL,NULL),('06','2015-10-01 16:06:51','管理员',25326,153800,'2014-06-01 00:00:00','华东地区',0x00,'山东省',9076,0.9100,NULL,'鲁',60,NULL,NULL,NULL,NULL,NULL),('07','2015-10-01 16:06:51','管理员',24738,102600,'2010-07-01 00:00:00','华东地区',0x00,'江苏省',7386,0.7402,NULL,'苏',70,NULL,NULL,NULL,NULL,NULL),('08','2015-10-01 16:06:51','管理员',6906,139700,'2010-08-01 00:00:00','华东地区',0x00,'安徽省',6337,0.6300,NULL,'皖',80,NULL,NULL,NULL,NULL,NULL),('09','2015-10-01 16:06:51','管理员',11658,6300,'2010-09-01 00:00:00','华东地区',0x00,'上海市',1622,0.1600,NULL,'沪、申',90,NULL,NULL,NULL,NULL,NULL),('10','2015-10-01 16:06:51','管理员',17633,102000,'2010-10-01 00:00:00','华东地区',0x00,'浙江省',4650,0.4600,NULL,'浙',100,NULL,NULL,NULL,0x00,NULL),('11','2015-10-01 16:06:51','管理员',8440,121300,'2010-11-01 00:00:00','华东地区',0x00,'福建省',3469,0.3500,NULL,'闽',110,NULL,NULL,NULL,NULL,NULL),('12','2015-10-01 16:06:51','管理员',7081,454800,'2010-12-01 00:00:00','东北地区',0x00,'黑龙江省',3812,0.3800,NULL,'黑',120,NULL,NULL,NULL,NULL,NULL),('13','2015-10-01 16:06:51','管理员',4693,187400,'2011-01-01 00:00:00','东北地区',0x00,'吉林省',2699,0.2700,NULL,'吉',130,NULL,NULL,NULL,NULL,NULL),('14','2015-10-01 16:06:51','管理员',10418,155900,'2011-02-01 00:00:00','东北地区',0x00,'辽宁省',4203,0.4200,NULL,'辽',140,NULL,NULL,NULL,NULL,NULL),('15','2015-10-01 16:06:51','管理员',14234,167000,'2011-03-01 00:00:00','华中地区',0x00,'河南省',9768,0.9800,NULL,'豫',150,NULL,NULL,NULL,NULL,NULL),('16','2015-10-01 16:06:51','管理员',8451,185900,'2011-04-01 00:00:00','华中地区',0x00,'湖北省',5988,0.6000,NULL,'鄂',160,NULL,NULL,NULL,NULL,NULL),('17','2015-10-01 16:06:51','管理员',8366,211800,'2011-05-01 00:00:00','华中地区',0x00,'湖南省',6629,0.6600,NULL,'湘',170,NULL,NULL,NULL,NULL,NULL),('18','2015-10-01 16:06:51','管理员',5323,167000,'2011-06-01 00:00:00','华中地区',0x00,'江西省',4222,0.4200,NULL,'赣',180,NULL,NULL,NULL,NULL,NULL),('19','2015-10-01 16:06:51','管理员',29863,180000,'2011-07-01 00:00:00','华南地区',0x00,'广东省',7859,0.7900,NULL,'粤',190,NULL,NULL,NULL,NULL,NULL),('20','2015-10-01 16:06:51','管理员',5386,236000,'2011-08-01 00:00:00','华南地区',0x01,'广西壮族自治区',4822,0.4800,NULL,'桂',200,NULL,NULL,NULL,NULL,NULL),('21','2015-10-01 16:06:51','管理员',1121,34000,'2011-09-01 00:00:00','华南地区',0x00,'海南省',803,0.0800,NULL,'琼',210,NULL,NULL,NULL,NULL,NULL),('22','2015-10-01 16:06:51','管理员',333,1100,'2011-10-01 00:00:00','台港澳地区',0x01,'香港特别行政区',686,0.0700,NULL,'港',220,NULL,NULL,NULL,NULL,NULL),('23','2015-10-01 16:06:51','管理员',333,250,'2011-11-01 00:00:00','台港澳地区',0x01,'澳门特别行政区',44,0.0000,NULL,'澳',230,NULL,NULL,NULL,NULL,NULL),('24','2015-10-01 16:06:51','管理员',333,36000,'2011-12-01 00:00:00','台港澳地区',0x00,'台湾省',227,0.0200,NULL,'台',240,NULL,NULL,NULL,NULL,NULL),('25','2015-10-01 16:06:51','管理员',9657,481400,'2012-01-01 00:00:00','西南地区',0x00,'四川省',8673,0.8700,NULL,'川、蜀',250,NULL,NULL,NULL,NULL,NULL),('26','2015-10-01 16:06:51','管理员',4260,383300,'2012-02-01 00:00:00','西南地区',0x00,'云南省',4333,0.4300,NULL,'云、滇',260,NULL,NULL,NULL,NULL,NULL),('27','2015-10-01 16:06:51','管理员',2543,176000,'2012-03-01 00:00:00','西南地区',0x00,'贵州省',3837,0.3800,NULL,'贵、黔',270,NULL,NULL,NULL,NULL,NULL),('28','2015-10-01 16:06:51','管理员',3938,82300,'2012-04-01 00:00:00','西南地区',0x00,'重庆市',3107,0.3100,NULL,'渝',280,NULL,NULL,NULL,NULL,NULL),('29','2015-10-01 16:06:51','管理员',326,1228000,'2012-05-01 00:00:00','西南地区',0x01,'西藏藏族自治区',267,0.0300,NULL,'藏',290,NULL,NULL,NULL,NULL,NULL),('30','2015-10-01 16:06:51','管理员',3305,1660000,'2012-06-01 00:00:00','西北地区',0x01,'新疆维吾尔自治区',1906,0.1900,NULL,'新',300,NULL,NULL,NULL,NULL,NULL),('31','2015-10-01 16:06:51','管理员',2494,454400,'2012-07-01 00:00:00','西北地区',0x00,'甘肃省',3813,0.3800,NULL,'甘、陇',310,NULL,NULL,NULL,NULL,NULL),('32','2015-10-01 16:06:51','管理员',706,722300,'2012-08-01 00:00:00','西北地区',0x00,'青海省',529,0.0500,NULL,'青',320,NULL,NULL,NULL,NULL,NULL),('33','2015-10-01 16:06:51','管理员',4806,205600,'2012-09-01 00:00:00','西北地区',0x00,'陕西省',3647,0.3600,NULL,'陕、秦',330,NULL,NULL,NULL,NULL,NULL),('34','2015-10-01 16:06:51','管理员',769,66400,'2012-10-01 00:00:00','西北地区',0x01,'宁夏回族自治区',527,0.0500,NULL,'宁',340,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `Province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Rate`
--

DROP TABLE IF EXISTS `Rate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rate` (
  `tf_rateId` varchar(2) NOT NULL,
  `tf_name` varchar(50) NOT NULL,
  PRIMARY KEY (`tf_rateId`),
  UNIQUE KEY `tf_name` (`tf_name`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Rate`
--

LOCK TABLES `Rate` WRITE;
/*!40000 ALTER TABLE `Rate` DISABLE KEYS */;
INSERT INTO `Rate` VALUES ('10','金牌客户'),('90','拉黑客户'),('40','普通用户'),('30','铜牌客户'),('20','银牌客户');
/*!40000 ALTER TABLE `Rate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Salesman`
--

DROP TABLE IF EXISTS `Salesman`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Salesman` (
  `tf_salesmanId` int(11) NOT NULL,
  `tf_age` int(11) DEFAULT NULL,
  `tf_birthday` datetime NOT NULL,
  `tf_eMail` varchar(50) DEFAULT NULL,
  `tf_name` varchar(10) NOT NULL,
  `tf_phonenumber` varchar(20) DEFAULT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_sex` varchar(2) NOT NULL,
  `tf_telnumber` varchar(20) DEFAULT NULL,
  `tf_departmentId` varchar(10) NOT NULL,
  PRIMARY KEY (`tf_salesmanId`),
  KEY `FK774E94EEFD8A767C` (`tf_departmentId`),
  CONSTRAINT `FK774E94EEFD8A767C` FOREIGN KEY (`tf_departmentId`) REFERENCES `_Department` (`tf_departmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Salesman`
--

LOCK TABLES `Salesman` WRITE;
/*!40000 ALTER TABLE `Salesman` DISABLE KEYS */;
INSERT INTO `Salesman` VALUES (1,NULL,'1970-01-12 00:00:00',NULL,'业务员甲',NULL,NULL,'男',NULL,'005001');
/*!40000 ALTER TABLE `Salesman` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Storage`
--

DROP TABLE IF EXISTS `Storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Storage` (
  `tf_storageId` int(11) NOT NULL,
  `tf_address` varchar(50) DEFAULT NULL,
  `tf_linkman` varchar(10) DEFAULT NULL,
  `tf_linkmanTel` varchar(20) DEFAULT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_remark` varchar(20) DEFAULT NULL,
  `tf_cityId` varchar(4) NOT NULL,
  PRIMARY KEY (`tf_storageId`),
  KEY `FKF2E8DA5B7C1FDCBE` (`tf_cityId`),
  CONSTRAINT `FKF2E8DA5B7C1FDCBE` FOREIGN KEY (`tf_cityId`) REFERENCES `City` (`tf_cityId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Storage`
--

LOCK TABLES `Storage` WRITE;
/*!40000 ALTER TABLE `Storage` DISABLE KEYS */;
INSERT INTO `Storage` VALUES (1,NULL,NULL,NULL,'唐山仓库',NULL,'0302'),(2,NULL,NULL,NULL,'苏州仓库',NULL,'0705'),(3,NULL,NULL,NULL,'台州仓库',NULL,'1011');
/*!40000 ALTER TABLE `Storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Trade`
--

DROP TABLE IF EXISTS `Trade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Trade` (
  `tf_tradeId` varchar(6) NOT NULL,
  `tf_name` varchar(50) NOT NULL,
  PRIMARY KEY (`tf_tradeId`),
  UNIQUE KEY `tf_name` (`tf_name`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Trade`
--

LOCK TABLES `Trade` WRITE;
/*!40000 ALTER TABLE `Trade` DISABLE KEYS */;
INSERT INTO `Trade` VALUES ('1001','A01:农业'),('1002','A02:林业'),('1003','A03:畜牧业'),('1004','A04:渔业'),('1005','A05:农、林、牧、渔服务业'),('10','A:农、林、牧、渔业'),('1101','B06:煤炭开采和洗选业'),('1102','B07:石油和天然气开采业'),('1103','B08:黑色金属矿采选业'),('1104','B09:有色金属矿采选业'),('1105','B10:非金属矿采选业'),('1106','B11:开采辅助活动'),('1107','B12:其他采矿业'),('11','B:采矿业'),('1201','C13:农副食品加工业'),('1202','C14:食品制造业'),('1203','C15:酒、饮料和精制茶制造业'),('1204','C16:烟草制品业'),('1205','C17:纺织业'),('1206','C18:纺织服装、服饰业'),('1207','C19:皮革、毛皮、羽毛及其制品和制鞋业'),('1208','C20:木材加工和木、竹、藤、棕、草制品业'),('1209','C21:家具制造业'),('1210','C22:造纸和纸制品业'),('1211','C23:印刷和记录媒介复制业'),('1212','C24:文教、工美、体育和娱乐用品制造业'),('1213','C25:石油加工、炼焦和核燃料加工业'),('1214','C26:化学原料和化学制品制造业'),('1215','C27:医药制造业'),('1216','C28:化学纤维制造业'),('1217','C29:橡胶和塑料制品业'),('1218','C30:非金属矿物制品业'),('1219','C31:黑色金属冶炼和压延加工业'),('1220','C32:有色金属冶炼和压延加工业'),('1221','C33:金属制品业'),('1222','C34:通用设备制造业'),('1223','C35:专用设备制造业'),('1224','C36:汽车制造业'),('1225','C37:铁路、船舶、航空航天和其他运输设备制造业'),('1226','C38:电气机械和器材制造业'),('1227','C39:计算机、通信和其他电子设备制造业'),('1228','C40:仪器仪表制造业'),('1229','C41:其他制造业'),('1230','C42:废弃资源综合利用业'),('1231','C43:金属制品、机械和设备修理业'),('12','C:制造业'),('1301','D44:电力、热力生产和供应业'),('1302','D45:燃气生产和供应业'),('1303','D46:水的生产和供应业'),('13','D:电力、热力、燃气及水生产和供应业'),('1401','E47:房屋建筑业'),('1402','E48:土木工程建筑业'),('1403','E49:建筑安装业'),('1404','E50:建筑装饰和其他建筑业'),('14','E:建筑业'),('1501','F51:批发业'),('1502','F52:零售业'),('15','F:批发和零售业'),('1601','G53:铁路运输业'),('1602','G54:道路运输业'),('1603','G55:水上运输业'),('1604','G56:航空运输业'),('1605','G57:管道运输业'),('1606','G58:装卸搬运和运输代理业'),('1607','G59:仓储业'),('1608','G60:邮政业'),('16','G:交通运输、仓储和邮政业'),('1701','H61:住宿业'),('1702','H62:餐饮业'),('17','H:住宿和餐饮业'),('1801','I63:电信、广播电视和卫星传输服务'),('1802','I64:互联网和相关服务'),('1803','I65:软件和信息技术服务业'),('18','I:信息传输、软件和信息技术服务业'),('1901','J66:货币金融服务'),('1902','J67:资本市场服务'),('1903','J68:保险业'),('1904','J69:其他金融业'),('19','J:金融业'),('2001','K70:房地产业'),('20','K:房地产业'),('2101','L71:租赁业'),('2102','L72:商务服务业'),('21','L:租赁和商务服务业'),('2201','M73:研究和试验发展'),('2202','M74:专业技术服务业'),('2203','M75:科技推广和应用服务业'),('22','M:科学研究和技术服务业'),('2301','N76:水利管理业'),('2302','N77:生态保护和环境治理业'),('2303','N78:公共设施管理业'),('23','N:水利、环境和公共设施管理业'),('2401','O79:居民服务业'),('2402','O80:机动车、电子产品和日用产品修理业'),('2403','O81:其他服务业'),('24','O:居民服务、修理和其他服务业'),('2501','P82:教育'),('25','P:教育'),('2601','Q83:卫生'),('2602','Q84:社会工作'),('26','Q:卫生和社会工作'),('2701','R85:新闻和出版业'),('2702','R86:广播、电视、电影和影视录音制作业'),('2703','R87:文化艺术业'),('2704','R88:体育'),('2705','R89:娱乐业'),('27','R:文化、体育和娱乐业'),('2801','S90:中国共产党机关'),('2802','S91:国家机构'),('2803','S92:人民政协、民主党派'),('2804','S93:社会保障'),('2805','S94:群众团体、社会团体和其他成员组织'),('2806','S95:基层群众自治组织'),('28','S:公共管理、社会保障和社会组织'),('2901','T96:国际组织'),('29','T:国际组织');
/*!40000 ALTER TABLE `Trade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_AdditionFileType`
--

DROP TABLE IF EXISTS `_AdditionFileType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_AdditionFileType` (
  `tf_additionFileTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `tf_name` varchar(50) NOT NULL,
  `tf_remark` mediumtext,
  PRIMARY KEY (`tf_additionFileTypeId`),
  UNIQUE KEY `tf_name_UNIQUE` (`tf_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_AdditionFileType`
--

LOCK TABLES `_AdditionFileType` WRITE;
/*!40000 ALTER TABLE `_AdditionFileType` DISABLE KEYS */;
INSERT INTO `_AdditionFileType` VALUES (1,'图像文件',NULL),(2,'影印文件',NULL),(3,'现场照片',NULL),(4,'文档文件',NULL);
/*!40000 ALTER TABLE `_AdditionFileType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_AllDepartment`
--

DROP TABLE IF EXISTS `_AllDepartment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_AllDepartment` (
  `tf_allDepartmentId` varchar(10) NOT NULL,
  `tf_name` varchar(50) DEFAULT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tf_allDepartmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_AllDepartment`
--

LOCK TABLES `_AllDepartment` WRITE;
/*!40000 ALTER TABLE `_AllDepartment` DISABLE KEYS */;
/*!40000 ALTER TABLE `_AllDepartment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_Attachment`
--

DROP TABLE IF EXISTS `_Attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_Attachment` (
  `tf_attachmentId` int(11) NOT NULL,
  `tf_inputdate` datetime NOT NULL,
  `tf_inputmen` varchar(10) NOT NULL,
  `tf_archiveNumber` varchar(50) DEFAULT NULL,
  `tf_fileCompressed` bit(1) DEFAULT NULL,
  `tf_filelastupdate` datetime DEFAULT NULL,
  `tf_filename` varchar(250) DEFAULT NULL,
  `tf_filesize` int(11) DEFAULT NULL,
  `tf_imgheight` int(11) DEFAULT NULL,
  `tf_imgwidth` int(11) DEFAULT NULL,
  `tf_keeper` varchar(50) DEFAULT NULL,
  `tf_moduleId` varchar(255) NOT NULL,
  `tf_moduleIdvalue` int(11) NOT NULL,
  `tf_name` varchar(100) NOT NULL,
  `tf_order` int(11) DEFAULT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_fileTypeId` int(11) DEFAULT NULL,
  `tf_fieldId` int(11) DEFAULT NULL,
  `tf_reduceModeId` int(11) DEFAULT NULL,
  `tf_typeId` varchar(10) NOT NULL,
  `tf_imagePreview` longblob,
  `tf_filedata` longblob,
  `tf_pdfdata` longblob,
  PRIMARY KEY (`tf_attachmentId`),
  KEY `FK8ubkfste27qkb016jrm54luoq` (`tf_reduceModeId`),
  KEY `FKnx6rgm1n1ygisdwhrf9n8nvon` (`tf_fieldId`),
  KEY `FK1tdayqds2bw03qt1lyvruenvv` (`tf_typeId`),
  KEY `FKdxxpanu7m2y1tcowr5c1ai3s0` (`tf_fileTypeId`),
  CONSTRAINT `FK1tdayqds2bw03qt1lyvruenvv` FOREIGN KEY (`tf_typeId`) REFERENCES `_AttachmentType` (`tf_typeId`),
  CONSTRAINT `FK8ubkfste27qkb016jrm54luoq` FOREIGN KEY (`tf_reduceModeId`) REFERENCES `_AttachmentReduceMode` (`tf_reduceModeId`),
  CONSTRAINT `FK__Attachment__ModuleField` FOREIGN KEY (`tf_fieldId`) REFERENCES `_ModuleField` (`tf_fieldId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FKdxxpanu7m2y1tcowr5c1ai3s0` FOREIGN KEY (`tf_fileTypeId`) REFERENCES `_AttachmentFileType` (`tf_fileTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_Attachment`
--

LOCK TABLES `_Attachment` WRITE;
/*!40000 ALTER TABLE `_Attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `_Attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_AttachmentFileType`
--

DROP TABLE IF EXISTS `_AttachmentFileType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_AttachmentFileType` (
  `tf_fileTypeId` int(11) NOT NULL,
  `blobfield` longblob,
  `tf_name` varchar(50) NOT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tf_fileTypeId`),
  UNIQUE KEY `UK_q65podteubw12b3yhsua0qc2k` (`tf_name`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_AttachmentFileType`
--

LOCK TABLES `_AttachmentFileType` WRITE;
/*!40000 ALTER TABLE `_AttachmentFileType` DISABLE KEYS */;
INSERT INTO `_AttachmentFileType` VALUES (1,NULL,'图像文件',NULL),(2,NULL,'影印文件',NULL),(3,NULL,'现场照片',NULL),(4,NULL,'文档文件',NULL);
/*!40000 ALTER TABLE `_AttachmentFileType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_AttachmentReduceMode`
--

DROP TABLE IF EXISTS `_AttachmentReduceMode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_AttachmentReduceMode` (
  `tf_reduceModeId` int(11) NOT NULL,
  `tf_maxValue` int(11) DEFAULT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_recudeTo` int(11) DEFAULT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `aaa` varchar(45) DEFAULT NULL,
  `abc` bit(1) DEFAULT NULL,
  PRIMARY KEY (`tf_reduceModeId`),
  UNIQUE KEY `UK_k4knwycsm1hg4asn2xk4yc5bx` (`tf_name`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_AttachmentReduceMode`
--

LOCK TABLES `_AttachmentReduceMode` WRITE;
/*!40000 ALTER TABLE `_AttachmentReduceMode` DISABLE KEYS */;
INSERT INTO `_AttachmentReduceMode` VALUES (10,NULL,'不压缩',NULL,NULL,NULL,NULL),(20,800,'长或宽最大800px',NULL,NULL,NULL,NULL),(30,1024,'长或宽最大1024px',NULL,NULL,NULL,NULL),(40,2048,'长或宽最大2048px',NULL,NULL,NULL,NULL),(50,NULL,'缩小至原来的一半',2,NULL,NULL,NULL),(60,NULL,'缩小至四分之一',4,NULL,NULL,NULL),(70,NULL,'缩小至八分之一',8,NULL,NULL,NULL);
/*!40000 ALTER TABLE `_AttachmentReduceMode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_AttachmentType`
--

DROP TABLE IF EXISTS `_AttachmentType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_AttachmentType` (
  `tf_typeId` varchar(10) NOT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tf_typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_AttachmentType`
--

LOCK TABLES `_AttachmentType` WRITE;
/*!40000 ALTER TABLE `_AttachmentType` DISABLE KEYS */;
INSERT INTO `_AttachmentType` VALUES ('01','合同附件',NULL),('0101','合同正文附件',NULL),('0102','合同图片附件',NULL),('0103','合同备案表',NULL),('02','前期资料',NULL),('0201','立项、能评、环评批文',NULL),('0202','土地证明文件',NULL),('0203','规划手续',NULL),('0204','工程审图批文、消防批文',NULL),('0205','工程质检、安监手续',NULL),('0206','工程施工许可证',NULL),('03','招投标',NULL),('0301','招标文件',NULL),('0302','中标通知书',NULL),('0303','招标内部审批',NULL),('04','现场管理',NULL),('0401','现场管理文件',NULL),('10','审批附件',NULL),('20','单位附件',NULL),('2001','建设单位附件',NULL),('200101','营业执照、机构代码、资质证书',NULL),('2002','施工单位附件',NULL);
/*!40000 ALTER TABLE `_AttachmentType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_DataFilterRole`
--

DROP TABLE IF EXISTS `_DataFilterRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_DataFilterRole` (
  `tf_filterRoleId` int(11) NOT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tf_filterRoleId`),
  UNIQUE KEY `UK_2av42qnn1syqu8340yrccnifd` (`tf_name`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_DataFilterRole`
--

LOCK TABLES `_DataFilterRole` WRITE;
/*!40000 ALTER TABLE `_DataFilterRole` DISABLE KEYS */;
INSERT INTO `_DataFilterRole` VALUES (1,'江沂沪省份限定',NULL);
/*!40000 ALTER TABLE `_DataFilterRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_DataFilterRoleDetail`
--

DROP TABLE IF EXISTS `_DataFilterRoleDetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_DataFilterRoleDetail` (
  `tf_filtRoleDetailId` int(11) NOT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_recordIds` varchar(255) DEFAULT NULL,
  `tf_recordNames` varchar(255) DEFAULT NULL,
  `tf_filterRoleId` int(11) NOT NULL,
  `tf_moduleId` varchar(10) NOT NULL,
  PRIMARY KEY (`tf_filtRoleDetailId`),
  UNIQUE KEY `UK_f7wgovy4q37rnc6wekvvw54ls` (`tf_name`),
  KEY `FK652epj8kb372oki0710b4d2gv` (`tf_filterRoleId`),
  KEY `FKg4myohafl26mwy5bdx83j31fu` (`tf_moduleId`),
  CONSTRAINT `FK652epj8kb372oki0710b4d2gv` FOREIGN KEY (`tf_filterRoleId`) REFERENCES `_DataFilterRole` (`tf_filterRoleId`),
  CONSTRAINT `FKg4myohafl26mwy5bdx83j31fu` FOREIGN KEY (`tf_moduleId`) REFERENCES `_Module` (`tf_moduleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_DataFilterRoleDetail`
--

LOCK TABLES `_DataFilterRoleDetail` WRITE;
/*!40000 ALTER TABLE `_DataFilterRoleDetail` DISABLE KEYS */;
INSERT INTO `_DataFilterRoleDetail` VALUES (3,'江沂沪省份限定','07,09,10','江苏省<br/>上海市<br/>浙江省',1,'7010');
/*!40000 ALTER TABLE `_DataFilterRoleDetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_Department`
--

DROP TABLE IF EXISTS `_Department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_Department` (
  `tf_departmentId` varchar(10) NOT NULL,
  `tf_effect` varchar(255) DEFAULT NULL,
  `tf_fzr` varchar(10) DEFAULT NULL,
  `tf_isOperAll` bit(1) NOT NULL,
  `tf_isOperThisLevel` bit(1) NOT NULL,
  `tf_isPayDepa` bit(1) NOT NULL,
  `tf_iscw` bit(1) NOT NULL,
  `tf_isjs` bit(1) NOT NULL,
  `tf_issj` bit(1) NOT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_preview` varchar(255) DEFAULT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_shortname` varchar(50) DEFAULT NULL,
  `tf_departmentClassId` varchar(10) DEFAULT NULL,
  `tf_scopeId` int(11) DEFAULT NULL,
  `tf_isManage` bit(1) NOT NULL,
  PRIMARY KEY (`tf_departmentId`),
  KEY `FK3wjfkulg761vw1hv966j9satg` (`tf_departmentClassId`),
  KEY `FK5wenms11avee4vbj7uwni7gq7` (`tf_scopeId`),
  CONSTRAINT `FK3wjfkulg761vw1hv966j9satg` FOREIGN KEY (`tf_departmentClassId`) REFERENCES `_DepartmentClass` (`tf_departmentClassId`),
  CONSTRAINT `FK5wenms11avee4vbj7uwni7gq7` FOREIGN KEY (`tf_scopeId`) REFERENCES `_DepartmentScope` (`tf_scopeId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_Department`
--

LOCK TABLES `_Department` WRITE;
/*!40000 ALTER TABLE `_Department` DISABLE KEYS */;
INSERT INTO `_Department` VALUES ('00',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'管理部门',NULL,NULL,NULL,'10',10,0x00),('0002',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'党政条线',NULL,NULL,NULL,NULL,11,0x00),('000210',NULL,NULL,0x01,0x00,0x00,0x00,0x00,0x00,'党政办',NULL,NULL,NULL,NULL,13,0x01),('000220',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'文化站',NULL,NULL,NULL,NULL,NULL,0x00),('000230',NULL,NULL,0x01,0x01,0x01,0x01,0x01,0x01,'asdfasf',NULL,NULL,NULL,NULL,NULL,0x00),('0004',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'资产条线',NULL,NULL,NULL,NULL,NULL,0x00),('000401',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'资产办',NULL,NULL,NULL,NULL,NULL,0x01),('000405',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'供销社',NULL,NULL,NULL,NULL,NULL,0x00),('000410',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'某一供销社',NULL,NULL,NULL,NULL,NULL,0x00),('0006',NULL,NULL,0x01,0x00,0x00,0x00,0x00,0x00,'镇财政所',NULL,NULL,NULL,NULL,NULL,0x00),('0030',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'建管条线',NULL,NULL,NULL,NULL,NULL,0x00),('003001',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'建管所',NULL,NULL,NULL,NULL,NULL,0x01),('003005',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'公用事业所',NULL,NULL,NULL,NULL,NULL,0x00),('003010',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'服务业发展办',NULL,NULL,NULL,NULL,NULL,0x00),('003015',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'城镇建设办',NULL,NULL,NULL,NULL,NULL,0x00),('003020',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'综管办',NULL,NULL,NULL,NULL,NULL,0x00),('003025',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'某一办事处',NULL,NULL,NULL,NULL,NULL,0x00),('003030',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'某二办事处',NULL,NULL,NULL,NULL,NULL,0x00),('003035',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'国土分局',NULL,NULL,NULL,NULL,NULL,0x00),('003040',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'一体化公司',NULL,NULL,NULL,NULL,NULL,0x00),('003045',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'征收办',NULL,NULL,NULL,NULL,NULL,0x00),('003050',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'水利站',NULL,NULL,NULL,NULL,NULL,0x00),('003055',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'农服中心',NULL,NULL,NULL,NULL,NULL,0x00),('0040',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'司法条线',NULL,NULL,NULL,NULL,NULL,0x00),('004001',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'司法所 ',NULL,NULL,NULL,NULL,NULL,0x01),('004005',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'综治办(新市民事务中心)',NULL,NULL,NULL,NULL,NULL,0x00),('004010',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'某一派出所',NULL,NULL,NULL,NULL,NULL,0x00),('004015',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'某一山派出所',NULL,NULL,NULL,NULL,NULL,0x00),('004020',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'某一行派出所',NULL,NULL,NULL,NULL,NULL,0x00),('004025',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'交警中队',NULL,NULL,NULL,NULL,NULL,0x00),('004030',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'消防中队',NULL,NULL,NULL,NULL,NULL,0x00),('004035',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'城管中队',NULL,NULL,NULL,NULL,NULL,0x00),('004040',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'某一法庭',NULL,NULL,NULL,NULL,NULL,0x00),('0045',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'民政条线',NULL,NULL,NULL,NULL,NULL,0x00),('004501',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'民政办',NULL,NULL,NULL,NULL,NULL,0x01),('004505',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'某一敬老院',NULL,NULL,NULL,NULL,NULL,0x00),('004510',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'王市社区',NULL,NULL,NULL,NULL,NULL,0x00),('004515',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'新城社区',NULL,NULL,NULL,NULL,NULL,0x00),('004520',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'某一山社区',NULL,NULL,NULL,NULL,NULL,0x00),('004525',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'某一行社区',NULL,NULL,NULL,NULL,NULL,0x00),('0050',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'公卫中心(教卫文体)',NULL,NULL,NULL,NULL,NULL,0x00),('005001',NULL,NULL,0x00,0x00,0x00,0x00,0x00,0x00,'公卫中心',NULL,NULL,NULL,NULL,NULL,0x01);
/*!40000 ALTER TABLE `_Department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_DepartmentClass`
--

DROP TABLE IF EXISTS `_DepartmentClass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_DepartmentClass` (
  `tf_departmentClassId` varchar(10) NOT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tf_departmentClassId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_DepartmentClass`
--

LOCK TABLES `_DepartmentClass` WRITE;
/*!40000 ALTER TABLE `_DepartmentClass` DISABLE KEYS */;
INSERT INTO `_DepartmentClass` VALUES ('10','未设定',NULL);
/*!40000 ALTER TABLE `_DepartmentClass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_DepartmentScope`
--

DROP TABLE IF EXISTS `_DepartmentScope`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_DepartmentScope` (
  `tf_scopeId` int(11) NOT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_scopeName` varchar(50) NOT NULL,
  PRIMARY KEY (`tf_scopeId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_DepartmentScope`
--

LOCK TABLES `_DepartmentScope` WRITE;
/*!40000 ALTER TABLE `_DepartmentScope` DISABLE KEYS */;
INSERT INTO `_DepartmentScope` VALUES (10,NULL,'未分类'),(11,'aaabbbcccdddeeefffsssss','市本级'),(13,NULL,'学校');
/*!40000 ALTER TABLE `_DepartmentScope` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_FieldHiddenRole`
--

DROP TABLE IF EXISTS `_FieldHiddenRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_FieldHiddenRole` (
  `tf_fieldRoleId` varchar(10) NOT NULL,
  `tf_isEnable` bit(1) NOT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tf_fieldRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_FieldHiddenRole`
--

LOCK TABLES `_FieldHiddenRole` WRITE;
/*!40000 ALTER TABLE `_FieldHiddenRole` DISABLE KEYS */;
/*!40000 ALTER TABLE `_FieldHiddenRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_FieldHiddenRoleDetail`
--

DROP TABLE IF EXISTS `_FieldHiddenRoleDetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_FieldHiddenRoleDetail` (
  `tf_fieldRoleDetailId` int(11) NOT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_fieldRoleId` varchar(10) NOT NULL,
  `tf_fieldId` int(11) NOT NULL,
  PRIMARY KEY (`tf_fieldRoleDetailId`),
  KEY `FKq3jsfksvl4rhk5on2cxlqwj6l` (`tf_fieldRoleId`),
  KEY `FKowynaopdvms3s8sk3jgn1up42` (`tf_fieldId`),
  CONSTRAINT `FKowynaopdvms3s8sk3jgn1up42` FOREIGN KEY (`tf_fieldId`) REFERENCES `_ModuleField` (`tf_fieldId`),
  CONSTRAINT `FKq3jsfksvl4rhk5on2cxlqwj6l` FOREIGN KEY (`tf_fieldRoleId`) REFERENCES `_FieldHiddenRole` (`tf_fieldRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_FieldHiddenRoleDetail`
--

LOCK TABLES `_FieldHiddenRoleDetail` WRITE;
/*!40000 ALTER TABLE `_FieldHiddenRoleDetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `_FieldHiddenRoleDetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_FieldReadonlyRole`
--

DROP TABLE IF EXISTS `_FieldReadonlyRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_FieldReadonlyRole` (
  `tf_fieldRoleId` varchar(10) NOT NULL,
  `tf_isEnable` bit(1) NOT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tf_fieldRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_FieldReadonlyRole`
--

LOCK TABLES `_FieldReadonlyRole` WRITE;
/*!40000 ALTER TABLE `_FieldReadonlyRole` DISABLE KEYS */;
/*!40000 ALTER TABLE `_FieldReadonlyRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_FieldReadonlyRoleDetail`
--

DROP TABLE IF EXISTS `_FieldReadonlyRoleDetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_FieldReadonlyRoleDetail` (
  `tf_fieldRoleDetailId` int(11) NOT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_fieldRoleId` varchar(10) NOT NULL,
  `tf_fieldId` int(11) NOT NULL,
  PRIMARY KEY (`tf_fieldRoleDetailId`),
  KEY `FKricd5h534chlpqtwsxmu9iw5e` (`tf_fieldRoleId`),
  KEY `FKmj802s1dw99984o470e0t3vse` (`tf_fieldId`),
  CONSTRAINT `FKmj802s1dw99984o470e0t3vse` FOREIGN KEY (`tf_fieldId`) REFERENCES `_ModuleField` (`tf_fieldId`),
  CONSTRAINT `FKricd5h534chlpqtwsxmu9iw5e` FOREIGN KEY (`tf_fieldRoleId`) REFERENCES `_FieldReadonlyRole` (`tf_fieldRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_FieldReadonlyRoleDetail`
--

LOCK TABLES `_FieldReadonlyRoleDetail` WRITE;
/*!40000 ALTER TABLE `_FieldReadonlyRoleDetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `_FieldReadonlyRoleDetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_FieldRole`
--

DROP TABLE IF EXISTS `_FieldRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_FieldRole` (
  `tf_fieldRoleId` varchar(10) NOT NULL,
  `tf_isEnable` bit(1) NOT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tf_fieldRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_FieldRole`
--

LOCK TABLES `_FieldRole` WRITE;
/*!40000 ALTER TABLE `_FieldRole` DISABLE KEYS */;
/*!40000 ALTER TABLE `_FieldRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_FieldRoleDetail`
--

DROP TABLE IF EXISTS `_FieldRoleDetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_FieldRoleDetail` (
  `tf_fieldRoleDetailId` int(11) NOT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_fieldRoleId` varchar(10) NOT NULL,
  `tf_fieldId` int(11) NOT NULL,
  PRIMARY KEY (`tf_fieldRoleDetailId`),
  KEY `FK9fh7gfothqpi6le5wxvcrtlm9` (`tf_fieldRoleId`),
  KEY `FKok1tqvicnqgqidb8pwlc4do9w` (`tf_fieldId`),
  CONSTRAINT `FK9fh7gfothqpi6le5wxvcrtlm9` FOREIGN KEY (`tf_fieldRoleId`) REFERENCES `_FieldRole` (`tf_fieldRoleId`),
  CONSTRAINT `FKok1tqvicnqgqidb8pwlc4do9w` FOREIGN KEY (`tf_fieldId`) REFERENCES `_ModuleField` (`tf_fieldId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_FieldRoleDetail`
--

LOCK TABLES `_FieldRoleDetail` WRITE;
/*!40000 ALTER TABLE `_FieldRoleDetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `_FieldRoleDetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_Menu`
--

DROP TABLE IF EXISTS `_Menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_Menu` (
  `tf_id` int(11) NOT NULL,
  `tf_pid` int(11) DEFAULT NULL,
  `tf_title` varchar(50) DEFAULT NULL,
  `tf_displayTitle` varchar(50) DEFAULT NULL,
  `tf_orderId` int(11) DEFAULT NULL,
  `tf_expand` bit(1) DEFAULT NULL,
  `tf_iconUrl` varchar(50) DEFAULT NULL,
  `tf_iconCls` varchar(50) DEFAULT NULL,
  `tf_parameter` varchar(255) DEFAULT NULL,
  `tf_moduleId` varchar(10) DEFAULT NULL,
  `tf_parentFilter` varchar(255) DEFAULT NULL,
  `tf_reportGroupId` varchar(10) DEFAULT NULL,
  `tf_reportId` int(11) DEFAULT NULL,
  `tf_windowName` varchar(90) DEFAULT NULL,
  `tf_execStatument` varchar(90) DEFAULT NULL,
  `tf_xtype` varchar(50) DEFAULT NULL,
  `tf_functionName` varchar(90) DEFAULT NULL,
  `tf_glyph` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`tf_id`),
  KEY `FKao8lnd9g37k18dey3rwtpqh66` (`tf_moduleId`),
  KEY `FKrt9mdxf1vv1g4vaetd8c8e6jj` (`tf_reportId`),
  KEY `FKdj8qxy4np4v9jmq50s43o9rc8` (`tf_reportGroupId`),
  KEY `FK1gpanm1id9uxtss802iy9bbdw` (`tf_pid`),
  CONSTRAINT `FK1gpanm1id9uxtss802iy9bbdw` FOREIGN KEY (`tf_pid`) REFERENCES `_Menu` (`tf_id`),
  CONSTRAINT `FKao8lnd9g37k18dey3rwtpqh66` FOREIGN KEY (`tf_moduleId`) REFERENCES `_Module` (`tf_moduleId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKdj8qxy4np4v9jmq50s43o9rc8` FOREIGN KEY (`tf_reportGroupId`) REFERENCES `_ReportGroup` (`tf_reportGroupId`),
  CONSTRAINT `FKrt9mdxf1vv1g4vaetd8c8e6jj` FOREIGN KEY (`tf_reportId`) REFERENCES `_Report` (`tf_reportId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_Menu`
--

LOCK TABLES `_Menu` WRITE;
/*!40000 ALTER TABLE `_Menu` DISABLE KEYS */;
INSERT INTO `_Menu` VALUES (2,NULL,'系统设置',NULL,98,NULL,'images/button/about.png',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,NULL,'模块管理',NULL,99,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,NULL,'编码管理',NULL,97,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(115,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'990130',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(200,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(210,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9005',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(220,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(230,2,'-',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(240,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9010',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(250,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9011',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(260,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9030',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(270,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9035',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(280,2,'-',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(290,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9038',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(300,2,'用户记录筛选设置明细',NULL,NULL,NULL,NULL,NULL,NULL,'903810',NULL,NULL,NULL,NULL,' ',NULL,NULL,NULL),(310,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9039',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(320,2,'-',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(330,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9040',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(340,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9041',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(350,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9042',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(360,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9043',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(370,2,'-',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(380,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9045',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(390,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9046',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(400,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9091',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(410,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9092',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(412,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(415,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9052',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(510,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'990101',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(520,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'990102',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(530,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'990103',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(540,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'990110',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(550,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'990115',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(560,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'990120',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(570,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'990130',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(580,3,'Grid列表模块',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(590,580,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'990201',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(600,580,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'990202',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(610,580,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'990203',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(620,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'990204',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(630,3,'Form表单模块',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(640,630,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'990301',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(650,630,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'990302',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(660,630,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'990303',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(670,3,'明细列表模块',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(680,670,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'990401',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(690,670,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'990402',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(700,3,'打印模块',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(710,700,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9941',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(720,700,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9942',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(730,700,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9943',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(800,3,'导入导出模块',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(810,800,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9935',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(820,800,'编码设置',NULL,NULL,NULL,NULL,NULL,NULL,'9937',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(910,4,'字段附加列表属性',NULL,1,NULL,NULL,NULL,NULL,'8090',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(920,4,'日期数值分组',NULL,3,NULL,NULL,NULL,NULL,'8091',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(930,4,'日期数值分组明细',NULL,2,NULL,NULL,NULL,NULL,'8092',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(940,4,'附件属性管理',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(950,940,'附件类型',NULL,1,NULL,NULL,NULL,NULL,'9502',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(960,940,'附件文件类型',NULL,3,NULL,NULL,NULL,NULL,'9503',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(970,940,'图片压缩模式',NULL,2,NULL,NULL,NULL,NULL,'9504',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(980,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'9050',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(982,985,'客户单位',NULL,1,0x00,NULL,NULL,NULL,'6010',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(983,985,'省份',NULL,2,0x00,NULL,NULL,NULL,'7010',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(984,985,'市',NULL,3,0x00,NULL,NULL,NULL,'7012',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(985,NULL,'业务系统',NULL,10,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(986,985,'项目合同台帐',NULL,0,0x00,NULL,NULL,NULL,NULL,NULL,'10',NULL,NULL,NULL,NULL,NULL,NULL),(987,985,'行业',NULL,4,0x00,NULL,NULL,NULL,'7016',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(988,985,'业务员',NULL,0,0x00,NULL,NULL,NULL,'6020',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(989,985,'商品类别',NULL,0,0x00,NULL,NULL,NULL,'7018',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(990,985,'商品仓库',NULL,0,0x00,NULL,NULL,NULL,'7020',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(991,985,'商品',NULL,0,0x00,NULL,NULL,NULL,'6030',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(992,985,'订单',NULL,0,0x00,NULL,NULL,NULL,'6040',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(993,985,'订单明细',NULL,0,0x00,NULL,NULL,NULL,'6050',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `_Menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_MenuGroup`
--

DROP TABLE IF EXISTS `_MenuGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_MenuGroup` (
  `tf_menuGroupId` varchar(10) NOT NULL,
  `tf_description` varchar(50) DEFAULT NULL,
  `tf_expand` bit(1) DEFAULT NULL,
  `tf_iconURL` varchar(50) DEFAULT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_title` varchar(50) NOT NULL,
  PRIMARY KEY (`tf_menuGroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_MenuGroup`
--

LOCK TABLES `_MenuGroup` WRITE;
/*!40000 ALTER TABLE `_MenuGroup` DISABLE KEYS */;
INSERT INTO `_MenuGroup` VALUES ('10','dddf',0x00,NULL,NULL,'aaaa'),('20',NULL,0x01,NULL,NULL,'asdfasd');
/*!40000 ALTER TABLE `_MenuGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_MenuModule`
--

DROP TABLE IF EXISTS `_MenuModule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_MenuModule` (
  `tf_menuModuleId` int(11) NOT NULL,
  `tf_addSeparator` bit(1) DEFAULT NULL,
  `tf_execStatument` varchar(90) DEFAULT NULL,
  `tf_functionName` varchar(90) DEFAULT NULL,
  `tf_orderId` int(11) NOT NULL,
  `tf_parentFilter` varchar(255) DEFAULT NULL,
  `tf_title` varchar(50) DEFAULT NULL,
  `tf_windowName` varchar(90) DEFAULT NULL,
  `tf_menuGroupId` varchar(10) NOT NULL,
  `tf_moduleId` varchar(10) DEFAULT NULL,
  `tf_reportId` int(11) DEFAULT NULL,
  `tf_reportGroupId` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`tf_menuModuleId`),
  KEY `FKncdffbsw94wbhmm0bcoqhbw3e` (`tf_menuGroupId`),
  KEY `FKbk1uk3lp36209pxswrsy3t4am` (`tf_moduleId`),
  KEY `FKcrud3i6ut95geia4yveasd9ww` (`tf_reportId`),
  KEY `FK8bhv97f32u3qvtbg01nyq3bqg` (`tf_reportGroupId`),
  CONSTRAINT `FK8bhv97f32u3qvtbg01nyq3bqg` FOREIGN KEY (`tf_reportGroupId`) REFERENCES `_ReportGroup` (`tf_reportGroupId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKbk1uk3lp36209pxswrsy3t4am` FOREIGN KEY (`tf_moduleId`) REFERENCES `_Module` (`tf_moduleId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKcrud3i6ut95geia4yveasd9ww` FOREIGN KEY (`tf_reportId`) REFERENCES `_Report` (`tf_reportId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKncdffbsw94wbhmm0bcoqhbw3e` FOREIGN KEY (`tf_menuGroupId`) REFERENCES `_MenuGroup` (`tf_menuGroupId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_MenuModule`
--

LOCK TABLES `_MenuModule` WRITE;
/*!40000 ALTER TABLE `_MenuModule` DISABLE KEYS */;
/*!40000 ALTER TABLE `_MenuModule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_Module`
--

DROP TABLE IF EXISTS `_Module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_Module` (
  `tf_moduleId` varchar(10) NOT NULL,
  `tf_title` varchar(50) NOT NULL,
  `tf_allowEditExcel` bit(1) NOT NULL,
  `tf_allowInsertExcel` bit(1) NOT NULL,
  `tf_canLimit` bit(1) NOT NULL,
  `tf_codeField` varchar(50) DEFAULT NULL,
  `tf_codeLevel` varchar(50) DEFAULT NULL,
  `tf_dateField` varchar(50) DEFAULT NULL,
  `tf_defaultOrderField` varchar(255) DEFAULT NULL,
  `tf_description` varchar(50) DEFAULT NULL,
  `tf_englishname` varchar(20) DEFAULT NULL,
  `tf_fileField` varchar(50) DEFAULT NULL,
  `tf_glyph` varchar(10) DEFAULT NULL,
  `tf_hasApprove` bit(1) NOT NULL,
  `tf_hasAuditing` bit(1) NOT NULL,
  `tf_hasBrowse` bit(1) NOT NULL,
  `tf_hasChart` bit(1) NOT NULL,
  `tf_hasDelete` bit(1) NOT NULL,
  `tf_hasEdit` bit(1) NOT NULL,
  `tf_hasExec` bit(1) NOT NULL,
  `tf_hasInsert` bit(1) NOT NULL,
  `tf_hasPayment` bit(1) NOT NULL,
  `tf_homePageTag` varchar(50) DEFAULT NULL,
  `tf_iconUrl` varchar(50) DEFAULT NULL,
  `tf_isEnable` bit(1) NOT NULL,
  `tf_isInlineOper` bit(1) NOT NULL,
  `tf_isNotNean` bit(1) DEFAULT NULL,
  `tf_isOnlyUsedSearch` bit(1) DEFAULT NULL,
  `tf_isSystem` bit(1) NOT NULL,
  `tf_isTreeModel` bit(1) DEFAULT NULL,
  `tf_linkedModule` varchar(200) DEFAULT NULL,
  `tf_moduleName` varchar(50) NOT NULL,
  `tf_monthField` varchar(50) DEFAULT NULL,
  `tf_nameFields` varchar(50) NOT NULL,
  `tf_orderField` varchar(50) DEFAULT NULL,
  `tf_orderFieldControlModule` varchar(50) DEFAULT NULL,
  `tf_parentKey` varchar(50) DEFAULT NULL,
  `tf_pidField` varchar(50) DEFAULT NULL,
  `tf_primaryKey` varchar(50) NOT NULL,
  `tf_remark` mediumtext,
  `tf_requestMapping` varchar(50) NOT NULL,
  `tf_searchCondOrder` int(11) DEFAULT NULL,
  `tf_seasonField` varchar(50) DEFAULT NULL,
  `tf_shortname` varchar(20) DEFAULT NULL,
  `tf_tableName` varchar(50) DEFAULT NULL,
  `tf_titleTpl` varchar(200) DEFAULT NULL,
  `tf_yearField` varchar(50) DEFAULT NULL,
  `tf_moduleGroupId` varchar(10) DEFAULT NULL,
  `tf_hasAttachment` bit(1) NOT NULL,
  `tf_iconCls` varchar(50) DEFAULT NULL,
  `tf_iconFile` longblob,
  `tf_hasRecordIcon` bit(1) DEFAULT NULL,
  PRIMARY KEY (`tf_moduleId`),
  KEY `FKmedrofcf7b7x52ue2n506cil1` (`tf_moduleGroupId`),
  CONSTRAINT `FK__Module__ModuleGroup` FOREIGN KEY (`tf_moduleGroupId`) REFERENCES `_ModuleGroup` (`tf_moduleGroupId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_Module`
--

LOCK TABLES `_Module` WRITE;
/*!40000 ALTER TABLE `_Module` DISABLE KEYS */;
INSERT INTO `_Module` VALUES ('6010','客户单位',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,0x00,NULL,0x00,NULL,NULL,'Customer',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_customerId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'70',0x00,'fa fa-twitter',NULL,NULL),('6020','业务员',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,0x00,NULL,0x00,NULL,NULL,'Salesman',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_salesmanId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'70',0x00,NULL,NULL,NULL),('6030','商品',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,0x00,NULL,0x00,NULL,NULL,'Product',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_productId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'70',0x00,NULL,NULL,NULL),('6040','订单',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,0x00,NULL,0x00,NULL,NULL,'Orders',NULL,'tf_ordersNumber',NULL,NULL,NULL,NULL,'tf_ordersId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'70',0x00,NULL,NULL,NULL),('6050','订单明细',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,0x00,NULL,0x00,NULL,NULL,'OrdersDetail',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_ordersDetailId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'70',0x00,NULL,NULL,NULL),('7010','省份',0x00,0x01,0x00,NULL,NULL,'tf_createDate',NULL,NULL,NULL,'tf_map',NULL,0x00,0x01,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,0x00,NULL,0x00,NULL,NULL,'Province',NULL,'tf_name','tf_orderId',NULL,NULL,NULL,'tf_provinceId',NULL,'undefined',10,NULL,NULL,NULL,NULL,NULL,'70',0x01,'fa fa-university',NULL,NULL),('7012','市',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,0x00,NULL,0x00,NULL,NULL,'City',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_cityId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'70',0x00,'fa fa-group',NULL,NULL),('7014','客户等级',0x00,0x00,0x00,NULL,NULL,NULL,'tf_rateId',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,0x00,NULL,0x00,NULL,NULL,'Rate',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_rateId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'70',0x00,'fa fa-thumbs-up',NULL,NULL),('7016','行业',0x00,0x00,0x00,NULL,'2,2',NULL,'tf_tradeId',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,0x00,NULL,0x00,NULL,NULL,'Trade',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_tradeId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'70',0x00,NULL,NULL,NULL),('7018','商品类别',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,0x00,NULL,0x00,NULL,NULL,'ProductClass',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_productClassId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'70',0x00,NULL,NULL,NULL),('7020','商品仓库',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,0x00,NULL,0x00,NULL,NULL,'Storage',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_storageId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'70',0x00,NULL,NULL,NULL),('8090','字段附加列表属性',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_PropertyType',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_propertyTypeId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'80',0x00,NULL,NULL,NULL),('8091','日期数值分组',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_NumberGroup',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_numberGroupId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'80',0x00,NULL,NULL,NULL),('8092','日期数值分组明细',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_NumberGroupDetail',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_numberGroupDetailId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'80',0x00,NULL,NULL,NULL),('9000','用户及服务单位',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x00,0x01,0x00,0x00,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_Systemset',NULL,'tf_userdwmc',NULL,NULL,NULL,NULL,'tf_systemsetId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9001','附加参数设置',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x00,0x01,0x00,0x00,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_SystemsetAddition',NULL,'undefined',NULL,NULL,NULL,NULL,'tf_systemsetattachmentId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9005','运行参数设置',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x00,0x01,0x00,0x00,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_Systeminfo',NULL,'tf_systemName',NULL,NULL,NULL,NULL,'tf_systeminfoId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9010','部门类别',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_DepartmentScope',NULL,'tf_scopeName',NULL,NULL,NULL,NULL,'tf_scopeId',NULL,'undefined',11,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9011','部门',0x00,0x00,0x00,NULL,'2,2,2,2,2',NULL,'tf_departmentId',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,'images/module/_Department.png',0x01,0x00,NULL,NULL,0x00,0x01,NULL,'_Department',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_departmentId',NULL,'undefined',10,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('901110','可视部门',0x00,0x00,0x00,NULL,'2,2,2,2,2',NULL,'tf_allDepartmentId',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x00,0x00,0x00,0x00,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_AllDepartment',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_allDepartmentId','是部门的一个视图，为了加给一些不需要部门权限的模块使用','undefined',NULL,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('901120','管理部门',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0xf026',0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ManageDepartment',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_mDepartmentId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9030','职务',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,'images/button/edit.png',0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_Position',NULL,'tf_positionName',NULL,NULL,NULL,NULL,'tf_positionId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9035','用户',0x00,0x00,0x00,NULL,NULL,NULL,'_t9011.tf_departmentId , tf_loginName',NULL,NULL,'tf_signPhoto',NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x01,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_User',NULL,'tf_userName',NULL,NULL,NULL,NULL,'tf_userId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9038','用户记录筛选角色',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_DataFilterRole',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_filterRoleId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('903810','用户记录筛选设置明细',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_DataFilterRoleDetail',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_filtRoleDetailId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9039','用户记录筛选角色分配',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_UserDataFilterRole',NULL,'undefined',NULL,NULL,NULL,NULL,'tf_userDataFilterRoleId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9040','用户操作角色分组',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_RoleGroup',NULL,'tf_title',NULL,NULL,NULL,NULL,'tf_roleGroupId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9041','用户操作角色',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x01,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_Role',NULL,'tf_roleName',NULL,NULL,NULL,NULL,'tf_roleId',NULL,'undefined',1,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9042','用户操作角色分配',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_UserRole',NULL,'undefined',NULL,NULL,NULL,NULL,'tf_userRoleId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9043','用户附加部门',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_UserAdditionDepartment',NULL,'undefined',NULL,NULL,NULL,NULL,'tf_additionDepaId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9045','模块审批设置',0x00,0x00,0x00,NULL,NULL,NULL,'_t990102.tf_moduleId,tf_order',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ModuleApprove',NULL,'tf_departmentName',NULL,NULL,NULL,NULL,'tf_approveId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9046','模块审批人员',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,'_ModuleApprove','_ModuleApproveUser',NULL,'tf_User',NULL,NULL,NULL,NULL,'tf_approveUserId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9050','综合查询分组',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ReportGroup',NULL,'tf_title',NULL,NULL,NULL,NULL,'tf_reportGroupId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9052','综合查询',0x00,0x00,0x00,NULL,NULL,NULL,'_t9050.tf_reportGroupid , tf_orderId',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_Report',NULL,'tf_title','tf_orderId',NULL,NULL,NULL,'tf_reportId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9055','综合查询图表方案',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ReportChart',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_chartId',NULL,'undefined',NULL,NULL,'查询图表',NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9090','数据备份',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_Systembackup',NULL,'tf_userName',NULL,NULL,NULL,NULL,'tf_systembackupId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9091','操作日志',0x00,0x00,0x00,NULL,NULL,NULL,'tf_date DESC',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x00,0x00,0x00,0x00,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_SystemOperateLog',NULL,'tf_recordname',NULL,NULL,NULL,NULL,'tf_systemlogId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9092','登录日志',0x00,0x00,0x00,NULL,NULL,NULL,'tf_loginDate DESC',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x00,0x00,0x00,0x00,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_SystemLoginLog',NULL,'tf_userName',NULL,NULL,NULL,NULL,'tf_systemLogiglogId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'90',0x00,NULL,NULL,NULL),('9401','文件资料分类',0x00,0x00,0x00,NULL,'2,2,2,2,2',NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ArticleClass',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_articleClassId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'94',0x00,NULL,NULL,NULL),('9405','文件资料',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_Article',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_articleId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'94',0x01,NULL,NULL,NULL),('9502','附件类型',0x00,0x00,0x00,NULL,'2,2,2,2',NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x01,NULL,NULL,0x00,NULL,NULL,'_AttachmentType',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_typeId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'95',0x00,NULL,NULL,NULL),('9503','附件文件类型',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x01,NULL,NULL,0x00,NULL,NULL,'_AttachmentFileType',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_fileTypeId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'95',0x00,NULL,NULL,NULL),('9504','图片压缩模式',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_AttachmentReduceMode',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_reduceModeId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'95',0x00,NULL,NULL,NULL),('9505','附件',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x00,0x00,0x00,0x00,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_Attachment',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_attachmentId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'95',0x00,NULL,NULL,NULL),('9506','附件对应字段',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,0x00,NULL,0x00,NULL,NULL,'_AttachmentOnField',NULL,'tf_title',NULL,NULL,NULL,NULL,'tf_fieldId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'95',0x00,NULL,NULL,NULL),('990101','模块分组',0x00,0x00,0x00,NULL,'2,2,2',NULL,'tf_moduleGroupId',NULL,NULL,'tf_iconFile',NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,0x00,NULL,'_ModuleGroup',NULL,'tf_title',NULL,NULL,NULL,NULL,'tf_moduleGroupId',NULL,'_modulegroup',NULL,NULL,NULL,NULL,NULL,NULL,'9901',0x00,'fa fa-object-group',NULL,0x01),('990102','系统模块',0x00,0x00,0x00,NULL,NULL,NULL,'_t990101.tf_moduleGroupId,tf_moduleId',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x00,0x01,0x01,0x00,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_Module',NULL,'tf_title','tf_orderId',NULL,NULL,NULL,'tf_moduleId',NULL,'_module',NULL,NULL,NULL,NULL,NULL,NULL,'9901',0x00,NULL,NULL,0x01),('990103','模块字段',0x00,0x00,0x00,NULL,NULL,NULL,'_t990102.tf_moduleId,tf_fieldOrder',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x01,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ModuleField',NULL,'tf_title',NULL,NULL,NULL,NULL,'tf_fieldId',NULL,'_modulefield',NULL,NULL,NULL,NULL,NULL,NULL,'9901',0x00,NULL,NULL,NULL),('990110','模块字段平衡关系',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ModuleFieldConstraint',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_id',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'9901',0x00,NULL,NULL,NULL),('990115','子模块按钮方案',0x00,0x00,0x00,NULL,NULL,NULL,'_t990102.tf_moduleId,tf_order',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ModuleSubToolbar',NULL,'tf_subModuleTitle','tf_order',NULL,NULL,NULL,'tf_Id',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'9901',0x00,NULL,NULL,NULL),('990120','模块附加功能',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ModuleAdditionFunction',NULL,'tf_title',NULL,NULL,NULL,NULL,'tf_moduleAdditionFunctionId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'9901',0x00,NULL,NULL,NULL),('990130','菜单',0x00,0x00,0x00,NULL,NULL,NULL,'tf_orderId',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_Menu',NULL,'tf_title',NULL,NULL,'tf_pid',NULL,'tf_id',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'9901',0x00,NULL,NULL,NULL),('990201','模块列表方案',0x00,0x00,0x00,NULL,NULL,NULL,'_t990102.tf_moduleId,tf_SchemeOrder',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ModuleGridScheme',NULL,'tf_schemeName','tf_schemeOrder',NULL,NULL,NULL,'tf_gridSchemeId',NULL,'_modulegridscheme',NULL,NULL,'列表方案',NULL,NULL,NULL,'9902',0x00,NULL,NULL,NULL),('990202','模块列表字段分组',0x00,0x00,0x00,NULL,NULL,NULL,'_t990201.tf_gridSchemeId,cast(tf_gridGroupOrder as char(12))',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x01,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ModuleGridSchemeGroup',NULL,'tf_gridGroupName','tf_gridGroupOrder',NULL,NULL,NULL,'tf_gridGroupId',NULL,'_modulegridschemegroup',NULL,NULL,'列表字段分组',NULL,NULL,NULL,'9902',0x00,NULL,NULL,NULL),('990203','模块列表字段',0x00,0x00,0x00,NULL,NULL,NULL,'_t990202.tf_gridGroupId,tf_gridFieldOrder',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x00,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ModuleGridSchemeGroupField',NULL,'tf_ModuleField','tf_gridFieldOrder',NULL,NULL,NULL,'tf_gridFieldId',NULL,'_modulegridschemegroupfield',NULL,NULL,'列表字段',NULL,NULL,NULL,'9902',0x00,NULL,NULL,NULL),('990204','模块导航字段',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ModuleGridNavigate',NULL,'tf_text',NULL,NULL,NULL,NULL,'tf_navigatesetId',NULL,'undefined',NULL,NULL,'导航字段',NULL,NULL,NULL,'9902',0x00,NULL,NULL,NULL),('990301','模块Form方案',0x00,0x00,0x00,NULL,NULL,NULL,'_t990102.tf_moduleId,tf_SchemeOrder',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ModuleFormScheme',NULL,'tf_schemeName','tf_SchemeOrder',NULL,NULL,NULL,'tf_formSchemeId',NULL,'_moduleformscheme',NULL,NULL,'Form方案',NULL,NULL,NULL,'9903',0x00,NULL,NULL,NULL),('990302','模块Form字段分组',0x00,0x00,0x00,NULL,NULL,NULL,'_t990301.tf_formSchemeId, cast(tf_formGroupOrder as char(12))',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x01,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ModuleFormSchemeGroup',NULL,'tf_formGroupName','tf_formGroupOrder',NULL,NULL,NULL,'tf_formGroupId',NULL,'_moduleformschemegroup',NULL,NULL,'Form字段分组',NULL,NULL,NULL,'9903',0x00,NULL,NULL,NULL),('990303','模块Form字段',0x00,0x00,0x00,NULL,NULL,NULL,'_t990302.tf_formGroupId,tf_formFieldOrder',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x00,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ModuleFormSchemeGroupField',NULL,'tf_ModuleField','tf_formFieldOrder',NULL,NULL,NULL,'tf_formFieldId',NULL,'_moduleformschemegroupfield',NULL,NULL,'Form字段',NULL,NULL,NULL,'9903',0x00,NULL,NULL,NULL),('990401','模块明细显示分组',0x00,0x00,0x00,NULL,NULL,NULL,'_t990102.tf_moduleId,tf_order',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ModuleDetailScheme',NULL,'tf_schemeName','tf_order',NULL,NULL,NULL,'tf_detailId',NULL,'undefined',NULL,NULL,'明细显示分组',NULL,NULL,NULL,'9904',0x00,NULL,NULL,NULL),('990402','模块明细显示字段',0x00,0x00,0x00,NULL,NULL,NULL,'_t9912.tf_detailId,tf_order',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x00,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ModuleDetailSchemeField',NULL,'tf_ModuleField','tf_order',NULL,NULL,NULL,'tf_detailFieldId',NULL,'undefined',NULL,NULL,'明细字段',NULL,NULL,NULL,'9904',0x00,NULL,NULL,NULL),('990501','模块图表方案',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ModuleChart',NULL,'tf_name','tf_order',NULL,NULL,NULL,'tf_chartId',NULL,'undefined',NULL,NULL,'图表方案',NULL,NULL,NULL,'9905',0x00,NULL,NULL,NULL),('9920','菜单分组',0x00,0x00,0x00,NULL,NULL,NULL,'tf_menuGroupId',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_MenuGroup',NULL,'tf_title',NULL,NULL,NULL,NULL,'tf_menuGroupId',NULL,'_menugroup',NULL,NULL,NULL,NULL,NULL,NULL,'99',0x00,NULL,NULL,NULL),('9921','系统菜单',0x00,0x00,0x00,NULL,NULL,NULL,'_t9920.tf_menuGroupId , tf_orderId',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_MenuModule',NULL,'tf_MenuGroup','tf_orderId',NULL,NULL,NULL,'tf_menuModuleId',NULL,'_menumodule',NULL,NULL,NULL,NULL,NULL,NULL,'99',0x00,NULL,NULL,NULL),('9935','模块Excel报表',0x00,0x00,0x00,NULL,NULL,NULL,'_t990102.tf_moduleId , tf_order',NULL,NULL,'tf_filedata',NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ModuleExcelReport',NULL,'tf_name','tf_order',NULL,NULL,NULL,'tf_id',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'9907',0x00,NULL,NULL,NULL),('9937','模块Excel单记录导入',0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,'tf_filedata',NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_ModuleExcelRecordAdd',NULL,'tf_name','tf_order',NULL,NULL,NULL,'tf_id',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'9907',0x00,NULL,NULL,NULL),('9941','记录打印方案',0x00,0x00,0x00,NULL,NULL,NULL,'_t990102.tf_moduleId,tf_schemeOrder',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_PrintScheme',NULL,'tf_schemeName',NULL,NULL,NULL,NULL,'tf_printSchemeId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'9906',0x00,NULL,NULL,NULL),('9942','记录打印方案分组',0x00,0x00,0x00,NULL,NULL,NULL,'_t9941.tf_printSchemeId,tf_printGroupOrder',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_PrintSchemeGroup',NULL,'tf_printGroupName',NULL,NULL,NULL,NULL,'tf_printSchemeGroupId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'9906',0x00,NULL,NULL,NULL),('9943','记录打印方案分组字段',0x00,0x00,0x00,NULL,NULL,NULL,'tf_order',NULL,NULL,NULL,NULL,0x00,0x00,0x01,0x00,0x01,0x01,0x00,0x01,0x00,NULL,NULL,0x01,0x00,NULL,NULL,0x00,NULL,NULL,'_PrintSchemeGroupCell',NULL,'tf_name',NULL,NULL,NULL,NULL,'tf_cellId',NULL,'undefined',NULL,NULL,NULL,NULL,NULL,NULL,'9906',0x00,NULL,NULL,NULL);
/*!40000 ALTER TABLE `_Module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ModuleAdditionField`
--

DROP TABLE IF EXISTS `_ModuleAdditionField`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ModuleAdditionField` (
  `tf_moduleadditionfieldId` int(11) NOT NULL,
  `tf_aggregate` varchar(20) NOT NULL,
  `tf_fieldId` int(11) NOT NULL,
  `tf_moduleId` varchar(10) NOT NULL,
  PRIMARY KEY (`tf_moduleadditionfieldId`),
  KEY `FKlul2rwgfo6ofuk8xlrc33x905` (`tf_moduleId`),
  CONSTRAINT `FKlul2rwgfo6ofuk8xlrc33x905` FOREIGN KEY (`tf_moduleId`) REFERENCES `_Module` (`tf_moduleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ModuleAdditionField`
--

LOCK TABLES `_ModuleAdditionField` WRITE;
/*!40000 ALTER TABLE `_ModuleAdditionField` DISABLE KEYS */;
INSERT INTO `_ModuleAdditionField` VALUES (38,'count',1311095418,'990102'),(39,'count',99210010,'9920'),(40,'count',99040010,'990102'),(41,'count',99070010,'990102'),(43,'count',99210010,'990102'),(48,'count',99050010,'990201'),(49,'count',99060010,'990201'),(50,'count',99020010,'990101'),(74,'count',99060010,'990202'),(75,'count',90350010,'9030'),(76,'count',90410010,'9040'),(77,'count',94050010,'9401'),(82,'normal',99020040,'9046'),(83,'count',90460010,'9045'),(84,'count',80920010,'8091'),(101,'normal',99020040,'990402'),(102,'count',99120010,'990401'),(103,'normal',99020040,'990202'),(118,'normal',90110020,'9042'),(119,'normal',90110010,'9035'),(127,'normal',421165418,'9043'),(128,'count',90430010,'9035'),(129,'normal',90350040,'9046'),(130,'normal',90110010,'9046'),(131,'normal',90110020,'9046'),(132,'count',70120010,'7010'),(133,'sum',70120060,'7010'),(134,'count',60100010,'7010');
/*!40000 ALTER TABLE `_ModuleAdditionField` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ModuleAdditionFunction`
--

DROP TABLE IF EXISTS `_ModuleAdditionFunction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ModuleAdditionFunction` (
  `tf_moduleAdditionFunctionId` int(11) NOT NULL,
  `tf_additionName` varchar(50) DEFAULT NULL,
  `tf_description` varchar(50) DEFAULT NULL,
  `tf_hasEnable` bit(1) DEFAULT NULL,
  `tf_icon` varchar(50) DEFAULT NULL,
  `tf_menuName` varchar(20) DEFAULT NULL,
  `tf_needRecord` bit(1) DEFAULT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_showWindow` bit(1) DEFAULT NULL,
  `tf_title` varchar(20) NOT NULL,
  `tf_moduleId` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`tf_moduleAdditionFunctionId`),
  KEY `FKltdk2m4a4uiv74ubwsinc82t7` (`tf_moduleId`),
  CONSTRAINT `FKltdk2m4a4uiv74ubwsinc82t7` FOREIGN KEY (`tf_moduleId`) REFERENCES `_Module` (`tf_moduleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ModuleAdditionFunction`
--

LOCK TABLES `_ModuleAdditionFunction` WRITE;
/*!40000 ALTER TABLE `_ModuleAdditionFunction` DISABLE KEYS */;
INSERT INTO `_ModuleAdditionFunction` VALUES (900001,'RestartTomcat','重新启动java服务器',0x01,NULL,'操作',0x00,NULL,0x00,'重启服务','9000'),(900002,'ExecuteSQL','Sql Server 服务器中执行客户端的指令',0x00,NULL,'操作',0x00,NULL,0x01,'执行SQL','9000'),(900003,'UpdateServerDate','将服务器上的时间更新为本机的时间',0x01,NULL,'操作',0x00,NULL,0x00,'更新服务器时间','9000'),(903501,'UserSetRole','为当前选中的用户设置角色权限。',0x01,NULL,NULL,0x01,NULL,0x01,'角色设置','9035'),(903502,'UserSetFieldReadonlyRole',NULL,0x00,'setfieldrole.png',NULL,0x01,NULL,0x01,'只读字段角色','9035'),(903503,'UserSetFieldHiddenRole','',0x00,'setfieldrole.png',NULL,0x01,NULL,0x01,'隐藏字段角色','9035'),(903504,'UserPopedom','显示用户的所有角色权限的叠加',0x01,'userPopedom.png',NULL,0x01,NULL,0x01,'用户权限','9035'),(903505,'ResetPassword','为当前选中的用户重置密码为缺省值。',0x01,'resetpassword.png',NULL,NULL,NULL,NULL,'重置密码','9035'),(904101,'RoleSetPopedom','为当前选中的角色设置权限。',0x01,NULL,NULL,0x01,NULL,0x01,'权限设置','9041'),(909001,'DownloadBackupFile','下载备份文件至本地。',0x01,'download.png',NULL,0x01,NULL,0x00,'下载','9090'),(909101,'DownloadUploadedFile','下载以前上传过的新增和修改的Excel文件',0x01,'download.png',NULL,0x01,NULL,0x00,'下载','9091'),(950501,'DownloadAdditionFile','下载当前选中记录的附件文件。',0x01,'download.png',NULL,NULL,NULL,NULL,'下载','9505'),(990201,'SetAdditionFields','为模块设置附加字段，包括父模块的字段和子模块的聚合字段。',0x01,'SetAdditionFields.png','',0x01,'',0x01,'附加字段','990102'),(990202,'AddModule','根据录入的模块名称导入到模块定义和字段定义，并生成grid和form的方案。',0x01,NULL,NULL,NULL,NULL,NULL,'导入模块','990102'),(990301,'RefreshFields','根据系统类的定义刷新当前模块的字段。(必须选择一个模块或是有父模块)',0x01,'refresh.png',NULL,NULL,NULL,NULL,'刷新字段','990103'),(990501,'SetGridGroupFields','为当前选中的Grid字段组设置字段。',0x01,NULL,NULL,0x01,NULL,0x01,'设置字段','990202'),(990801,'SetFormGroupFields','为当前选中的Form字段组设置字段。',0x01,NULL,NULL,0x01,NULL,0x01,'设置字段','990302'),(991001,'RefreshNavigateModule','根据模块的定义，加入此模块的所有父模块的导航信息',0x01,NULL,NULL,0x00,NULL,0x00,'刷新导航模块','990204'),(991101,'SetDetailGroupFields','为当前选中的明细显示分组设置字段。',0x01,NULL,NULL,0x01,NULL,0x01,'设置字段','990401'),(90381010,'SetDataFilterRoleDetail','为当前选中的角色设置筛选记录',0x01,NULL,NULL,0x01,NULL,0x01,'设置筛选条件','903810'),(90381011,'SetProvinceArea','设置省份的地图',0x01,NULL,NULL,0x01,NULL,0x00,'设置地图','7010');
/*!40000 ALTER TABLE `_ModuleAdditionFunction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ModuleApprove`
--

DROP TABLE IF EXISTS `_ModuleApprove`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ModuleApprove` (
  `tf_approveId` int(11) NOT NULL,
  `tf_allowFinished` bit(1) DEFAULT NULL,
  `tf_departmentName` varchar(50) NOT NULL,
  `tf_level` int(11) NOT NULL,
  `tf_order` int(11) NOT NULL,
  `tf_moduleId` varchar(10) NOT NULL,
  PRIMARY KEY (`tf_approveId`),
  KEY `FK3p7funslpksvlqdcros50wegd` (`tf_moduleId`),
  CONSTRAINT `FK3p7funslpksvlqdcros50wegd` FOREIGN KEY (`tf_moduleId`) REFERENCES `_Module` (`tf_moduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ModuleApprove`
--

LOCK TABLES `_ModuleApprove` WRITE;
/*!40000 ALTER TABLE `_ModuleApprove` DISABLE KEYS */;
INSERT INTO `_ModuleApprove` VALUES (1,0x00,'部门意见',1,1,'7012'),(2,0x00,'主管领导意见',2,2,'7012');
/*!40000 ALTER TABLE `_ModuleApprove` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ModuleApproveUser`
--

DROP TABLE IF EXISTS `_ModuleApproveUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ModuleApproveUser` (
  `tf_approveUserId` int(11) NOT NULL,
  `tf_approveId` int(11) NOT NULL,
  `tf_userId` int(11) NOT NULL,
  PRIMARY KEY (`tf_approveUserId`),
  KEY `FKds9y0hag9q2s3027rdoltenq8` (`tf_approveId`),
  KEY `FKi86y0vpwibmlsoip1hua4xm0r` (`tf_userId`),
  CONSTRAINT `FKds9y0hag9q2s3027rdoltenq8` FOREIGN KEY (`tf_approveId`) REFERENCES `_ModuleApprove` (`tf_approveId`),
  CONSTRAINT `FKi86y0vpwibmlsoip1hua4xm0r` FOREIGN KEY (`tf_userId`) REFERENCES `_User` (`tf_userId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ModuleApproveUser`
--

LOCK TABLES `_ModuleApproveUser` WRITE;
/*!40000 ALTER TABLE `_ModuleApproveUser` DISABLE KEYS */;
INSERT INTO `_ModuleApproveUser` VALUES (1,1,2),(2,2,1);
/*!40000 ALTER TABLE `_ModuleApproveUser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ModuleChart`
--

DROP TABLE IF EXISTS `_ModuleChart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ModuleChart` (
  `tf_chartId` int(11) NOT NULL,
  `tf_inputdate` datetime NOT NULL,
  `tf_inputmen` varchar(10) NOT NULL,
  `tf_categoryField` varchar(50) NOT NULL,
  `tf_chartType` varchar(50) NOT NULL,
  `tf_colorScheme` varchar(50) NOT NULL,
  `tf_isAnimate` bit(1) DEFAULT NULL,
  `tf_isGridLine` bit(1) DEFAULT NULL,
  `tf_isShowDetailNumber` bit(1) DEFAULT NULL,
  `tf_isShowTips` bit(1) DEFAULT NULL,
  `tf_isSystemScheme` bit(1) DEFAULT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_numericFields` varchar(255) NOT NULL,
  `tf_order` int(11) NOT NULL,
  `tf_otherSetting` varchar(255) DEFAULT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_title` varchar(50) DEFAULT NULL,
  `tf_moduleId` varchar(10) NOT NULL,
  PRIMARY KEY (`tf_chartId`),
  KEY `FK2jcn06vg4hmscicmd6c0tkwd5` (`tf_moduleId`),
  CONSTRAINT `FK2jcn06vg4hmscicmd6c0tkwd5` FOREIGN KEY (`tf_moduleId`) REFERENCES `_Module` (`tf_moduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ModuleChart`
--

LOCK TABLES `_ModuleChart` WRITE;
/*!40000 ALTER TABLE `_ModuleChart` DISABLE KEYS */;
/*!40000 ALTER TABLE `_ModuleChart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ModuleDetailScheme`
--

DROP TABLE IF EXISTS `_ModuleDetailScheme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ModuleDetailScheme` (
  `tf_detailId` int(11) NOT NULL,
  `tf_isSystemScheme` bit(1) DEFAULT NULL,
  `tf_order` int(11) NOT NULL,
  `tf_schemeName` varchar(50) NOT NULL,
  `tf_moduleId` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`tf_detailId`),
  KEY `FKao33huv1qdpgh6nqrvoo7r740` (`tf_moduleId`),
  CONSTRAINT `FKao33huv1qdpgh6nqrvoo7r740` FOREIGN KEY (`tf_moduleId`) REFERENCES `_Module` (`tf_moduleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ModuleDetailScheme`
--

LOCK TABLES `_ModuleDetailScheme` WRITE;
/*!40000 ALTER TABLE `_ModuleDetailScheme` DISABLE KEYS */;
INSERT INTO `_ModuleDetailScheme` VALUES (2,0x01,10,'菜单分组','9920'),(6,0x01,10,'明细显示分组','990401'),(7,0x01,10,'明细显示字段','990402'),(8,0x01,10,'系统菜单','9921'),(9,0x01,10,'模块分组','990101'),(10,0x01,10,'系统模块','990102'),(11,0x01,10,'模块字段','990103'),(12,0x01,10,'模块列表方案','990201'),(13,0x01,10,'模块列表字段分组','990202'),(14,0x01,10,'模块列表字段','990203'),(15,0x00,10,'模块导航字段','990204'),(16,0x01,10,'模块Form方案','990301'),(17,0x01,10,'模块Form字段分组','990302'),(18,0x01,10,'模块Form字段','990303'),(19,0x01,10,'记录打印方案','9941'),(20,0x01,10,'记录打印方案分组','9942'),(21,0x01,10,'记录打印方案分组字段','9943'),(22,0x01,10,'用户及服务单位','9000'),(23,0x01,10,'附加参数设置','9001'),(24,0x01,10,'运行参数设置','9005'),(25,0x01,10,'部门权限','9010'),(26,0x01,10,'部门','9011'),(27,0x01,10,'职务','9030'),(28,0x01,10,'用户','9035'),(29,0x01,10,'用户角色分组','9040'),(30,0x01,10,'用户角色','9041'),(31,0x01,10,'模块审批设置','9045'),(32,0x01,10,'模块审批人员','9046'),(33,0x01,10,'数据备份','9090'),(34,0x01,10,'操作日志','9091'),(35,0x01,10,'登录日志','9092'),(36,0x01,10,'文件资料分类','9401'),(37,0x01,10,'文件资料','9405'),(38,0x01,10,'附件类型','9502'),(39,0x01,10,'附件文件类型','9503'),(40,0x01,10,'图片压缩模式','9504'),(41,0x01,10,'附件','9505'),(51,0x01,10,'附加列表属性','8090'),(52,0x01,10,'日期数值分组','8091'),(53,0x01,10,'日期数值分组明细','8092'),(55,0x00,10,'省份明细','7010');
/*!40000 ALTER TABLE `_ModuleDetailScheme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ModuleDetailSchemeField`
--

DROP TABLE IF EXISTS `_ModuleDetailSchemeField`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ModuleDetailSchemeField` (
  `tf_detailFieldId` int(11) NOT NULL,
  `tf_action` varchar(50) DEFAULT NULL,
  `tf_aggregate` varchar(20) DEFAULT NULL,
  `tf_fieldId` int(11) DEFAULT NULL,
  `tf_order` int(11) NOT NULL,
  `tf_otherSetting` varchar(50) DEFAULT NULL,
  `tf_detailId` int(11) DEFAULT NULL,
  PRIMARY KEY (`tf_detailFieldId`),
  KEY `FKaq4rx4bwad37p9cfcm2n4bj0y` (`tf_detailId`),
  KEY `FKdhdd7smihnuveyy5prop2kvwh` (`tf_fieldId`),
  CONSTRAINT `FKaq4rx4bwad37p9cfcm2n4bj0y` FOREIGN KEY (`tf_detailId`) REFERENCES `_ModuleDetailScheme` (`tf_detailId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKdhdd7smihnuveyy5prop2kvwh` FOREIGN KEY (`tf_fieldId`) REFERENCES `_ModuleField` (`tf_fieldId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ModuleDetailSchemeField`
--

LOCK TABLES `_ModuleDetailSchemeField` WRITE;
/*!40000 ALTER TABLE `_ModuleDetailSchemeField` DISABLE KEYS */;
INSERT INTO `_ModuleDetailSchemeField` VALUES (49,NULL,NULL,99200010,10,NULL,2),(50,NULL,NULL,99200020,20,NULL,2),(51,NULL,NULL,99200050,60,NULL,2),(52,NULL,NULL,99200040,50,NULL,2),(53,NULL,NULL,99200060,70,NULL,2),(54,NULL,NULL,99200030,40,NULL,2),(55,NULL,NULL,99210010,30,NULL,2),(117,NULL,NULL,99110020,10,NULL,6),(118,NULL,NULL,99110030,20,NULL,6),(119,NULL,NULL,99110040,30,NULL,6),(120,NULL,NULL,99110050,40,NULL,6),(121,NULL,NULL,99120010,50,NULL,6),(122,NULL,NULL,99120020,10,NULL,7),(123,NULL,NULL,99120030,20,NULL,7),(124,NULL,NULL,99120040,30,NULL,7),(125,NULL,NULL,99120050,40,NULL,7),(126,NULL,NULL,99120060,50,NULL,7),(127,NULL,NULL,99020040,5,NULL,7),(128,NULL,NULL,99210020,30,NULL,8),(129,NULL,NULL,99210030,10,NULL,8),(130,NULL,NULL,99210040,20,NULL,8),(131,NULL,NULL,99210050,40,NULL,8),(132,NULL,NULL,99210160,50,NULL,8),(133,NULL,NULL,99010010,10,NULL,9),(134,NULL,NULL,99010020,20,NULL,9),(135,NULL,NULL,99010030,40,NULL,9),(136,NULL,NULL,99010040,50,NULL,9),(137,NULL,NULL,99010050,60,NULL,9),(138,NULL,NULL,99020010,30,NULL,9),(139,NULL,NULL,99020010,10,NULL,10),(140,NULL,NULL,99020020,20,NULL,10),(141,NULL,NULL,99020030,30,NULL,10),(142,NULL,NULL,99020040,40,NULL,10),(143,NULL,NULL,99020050,50,NULL,10),(144,NULL,NULL,99020060,60,NULL,10),(145,NULL,NULL,99020070,70,NULL,10),(146,NULL,NULL,99020080,80,NULL,10),(147,NULL,NULL,99020090,90,NULL,10),(148,NULL,NULL,99020100,100,NULL,10),(149,NULL,NULL,99020110,110,NULL,10),(150,NULL,NULL,99020120,120,NULL,10),(151,NULL,NULL,99020130,130,NULL,10),(152,NULL,NULL,99020140,140,NULL,10),(153,NULL,NULL,99020150,150,NULL,10),(154,NULL,NULL,99020155,160,NULL,10),(155,NULL,NULL,99020160,170,NULL,10),(156,NULL,NULL,99020170,180,NULL,10),(157,NULL,NULL,99020180,190,NULL,10),(158,NULL,NULL,99020190,200,NULL,10),(159,NULL,NULL,99020200,210,NULL,10),(160,NULL,NULL,99020210,220,NULL,10),(161,NULL,NULL,99020220,230,NULL,10),(162,NULL,NULL,99020230,240,NULL,10),(163,NULL,NULL,99020240,250,NULL,10),(164,NULL,NULL,99020250,260,NULL,10),(165,NULL,NULL,99020260,270,NULL,10),(166,NULL,NULL,99020800,280,NULL,10),(168,NULL,NULL,99040010,300,NULL,10),(169,NULL,NULL,99070010,310,NULL,10),(170,NULL,NULL,99100010,320,NULL,10),(171,NULL,NULL,99210010,330,NULL,10),(172,NULL,NULL,99300010,340,NULL,10),(173,NULL,NULL,99060010,350,NULL,10),(174,NULL,NULL,99090010,360,NULL,10),(175,NULL,NULL,99050010,370,NULL,10),(176,NULL,NULL,99080010,380,NULL,10),(201,NULL,NULL,99040020,10,NULL,12),(202,NULL,NULL,99040030,20,NULL,12),(203,NULL,NULL,99040040,30,NULL,12),(204,NULL,NULL,99040050,40,NULL,12),(205,NULL,NULL,99040060,50,NULL,12),(206,NULL,NULL,99040070,60,NULL,12),(207,NULL,NULL,99040080,70,NULL,12),(208,NULL,NULL,99040090,80,NULL,12),(209,NULL,NULL,99050010,90,NULL,12),(210,NULL,NULL,99060010,100,NULL,12),(211,NULL,NULL,99050020,10,NULL,13),(212,NULL,NULL,99050030,20,NULL,13),(213,NULL,NULL,99050040,30,NULL,13),(214,NULL,NULL,99050050,40,NULL,13),(215,NULL,NULL,99050060,50,NULL,13),(216,NULL,NULL,99060010,60,NULL,13),(217,NULL,NULL,99020040,5,NULL,13),(218,NULL,NULL,99060020,10,NULL,14),(219,NULL,NULL,99060030,20,NULL,14),(220,NULL,NULL,99060040,30,NULL,14),(221,NULL,NULL,99060050,40,NULL,14),(222,NULL,NULL,99060060,50,NULL,14),(223,NULL,NULL,99060070,60,NULL,14),(224,NULL,NULL,99060080,70,NULL,14),(225,NULL,NULL,99100020,10,NULL,15),(226,NULL,NULL,99100030,20,NULL,15),(227,NULL,NULL,99100040,30,NULL,15),(228,NULL,NULL,99100050,40,NULL,15),(229,NULL,NULL,99100060,50,NULL,15),(230,NULL,NULL,99100070,60,NULL,15),(231,NULL,NULL,99100080,70,NULL,15),(232,NULL,NULL,99100090,80,NULL,15),(233,NULL,NULL,99100100,90,NULL,15),(235,NULL,NULL,99070020,10,NULL,16),(236,NULL,NULL,99070030,20,NULL,16),(237,NULL,NULL,99070040,30,NULL,16),(238,NULL,NULL,99070050,40,NULL,16),(239,NULL,NULL,99070060,50,NULL,16),(240,NULL,NULL,99070070,60,NULL,16),(241,NULL,NULL,99070080,70,NULL,16),(242,NULL,NULL,99070090,80,NULL,16),(243,NULL,NULL,99070100,90,NULL,16),(244,NULL,NULL,99080020,10,NULL,17),(245,NULL,NULL,99080030,20,NULL,17),(246,NULL,NULL,99080040,30,NULL,17),(247,NULL,NULL,99080050,40,NULL,17),(248,NULL,NULL,99080060,50,NULL,17),(249,NULL,NULL,99080070,60,NULL,17),(250,NULL,NULL,99080080,70,NULL,17),(251,NULL,NULL,99080090,80,NULL,17),(252,NULL,NULL,99080100,90,NULL,17),(253,NULL,NULL,99080110,100,NULL,17),(254,NULL,NULL,99080120,110,NULL,17),(255,NULL,NULL,99090020,10,NULL,18),(256,NULL,NULL,99090030,20,NULL,18),(257,NULL,NULL,99090040,30,NULL,18),(258,NULL,NULL,99090050,40,NULL,18),(259,NULL,NULL,99090060,50,NULL,18),(260,NULL,NULL,99090070,60,NULL,18),(261,NULL,NULL,99090080,70,NULL,18),(262,NULL,NULL,99410020,10,NULL,19),(263,NULL,NULL,99410030,20,NULL,19),(264,NULL,NULL,99410040,30,NULL,19),(265,NULL,NULL,99410050,40,NULL,19),(266,NULL,NULL,99410060,50,NULL,19),(267,NULL,NULL,99410070,60,NULL,19),(268,NULL,NULL,99410080,70,NULL,19),(269,NULL,NULL,99410090,80,NULL,19),(270,NULL,NULL,99420020,10,NULL,20),(271,NULL,NULL,99420030,20,NULL,20),(272,NULL,NULL,99420040,30,NULL,20),(273,NULL,NULL,99420050,40,NULL,20),(274,NULL,NULL,99420060,50,NULL,20),(275,NULL,NULL,99420070,60,NULL,20),(276,NULL,NULL,99420080,70,NULL,20),(277,NULL,NULL,99420090,80,NULL,20),(278,NULL,NULL,99420100,90,NULL,20),(279,NULL,NULL,99420190,100,NULL,20),(280,NULL,NULL,99430020,10,NULL,21),(281,NULL,NULL,99430030,20,NULL,21),(282,NULL,NULL,99430040,30,NULL,21),(283,NULL,NULL,99430050,40,NULL,21),(284,NULL,NULL,99430060,50,NULL,21),(285,NULL,NULL,99430070,60,NULL,21),(286,NULL,NULL,99430080,70,NULL,21),(287,NULL,NULL,99430090,80,NULL,21),(288,NULL,NULL,99430100,90,NULL,21),(289,NULL,NULL,99430110,100,NULL,21),(290,NULL,NULL,99430120,110,NULL,21),(291,NULL,NULL,99430130,120,NULL,21),(292,NULL,NULL,99430190,130,NULL,21),(293,NULL,NULL,90000020,10,NULL,22),(294,NULL,NULL,90000030,20,NULL,22),(295,NULL,NULL,90000040,30,NULL,22),(296,NULL,NULL,90000050,40,NULL,22),(297,NULL,NULL,90000060,50,NULL,22),(298,NULL,NULL,90000070,60,NULL,22),(299,NULL,NULL,90000090,70,NULL,22),(300,NULL,NULL,90000100,80,NULL,22),(301,NULL,NULL,90000110,90,NULL,22),(302,NULL,NULL,90000120,100,NULL,22),(303,NULL,NULL,90000130,110,NULL,22),(304,NULL,NULL,90000140,120,NULL,22),(305,NULL,NULL,90000150,130,NULL,22),(306,NULL,NULL,90000160,140,NULL,22),(308,NULL,NULL,90010110,20,NULL,23),(309,NULL,NULL,90010200,30,NULL,23),(310,NULL,NULL,90010210,40,NULL,23),(311,NULL,NULL,90010220,50,NULL,23),(312,NULL,NULL,90010230,60,NULL,23),(313,NULL,NULL,90010240,70,NULL,23),(314,NULL,NULL,90010250,80,NULL,23),(315,NULL,NULL,90010260,90,NULL,23),(316,NULL,NULL,90010270,100,NULL,23),(317,NULL,NULL,90010280,110,NULL,23),(318,NULL,NULL,90010290,120,NULL,23),(319,NULL,NULL,90010100,10,NULL,23),(320,NULL,NULL,90050020,10,NULL,24),(321,NULL,NULL,90050030,20,NULL,24),(322,NULL,NULL,90050040,30,NULL,24),(323,NULL,NULL,90050050,40,NULL,24),(324,NULL,NULL,90050060,50,NULL,24),(325,NULL,NULL,90050100,60,NULL,24),(326,NULL,NULL,90050110,70,NULL,24),(327,NULL,NULL,90050120,80,NULL,24),(328,NULL,NULL,90050130,90,NULL,24),(329,NULL,NULL,90050140,100,NULL,24),(330,NULL,NULL,90050150,110,NULL,24),(331,NULL,NULL,90050160,120,NULL,24),(332,NULL,NULL,90050170,130,NULL,24),(333,NULL,NULL,90050180,140,NULL,24),(334,NULL,NULL,90050190,150,NULL,24),(335,NULL,NULL,90050300,160,NULL,24),(336,NULL,NULL,90100020,10,NULL,25),(337,NULL,NULL,90100030,20,NULL,25),(338,NULL,NULL,90110010,10,NULL,26),(339,NULL,NULL,90110020,20,NULL,26),(340,NULL,NULL,90110030,30,NULL,26),(341,NULL,NULL,90110040,40,NULL,26),(342,NULL,NULL,90110050,50,NULL,26),(343,NULL,NULL,90110060,60,NULL,26),(344,NULL,NULL,90110070,70,NULL,26),(345,NULL,NULL,90110080,80,NULL,26),(346,NULL,NULL,90110090,90,NULL,26),(347,NULL,NULL,90110100,100,NULL,26),(348,NULL,NULL,90110110,110,NULL,26),(349,NULL,NULL,90110120,120,NULL,26),(350,NULL,NULL,90110130,130,NULL,26),(351,NULL,NULL,90300010,10,NULL,27),(352,NULL,NULL,90300020,20,NULL,27),(353,NULL,NULL,90300030,30,NULL,27),(354,NULL,NULL,90300040,40,NULL,27),(355,NULL,NULL,90300050,50,NULL,27),(356,NULL,NULL,90350010,60,NULL,27),(357,NULL,NULL,90350020,10,NULL,28),(358,NULL,NULL,90350030,20,NULL,28),(359,NULL,NULL,90350040,30,NULL,28),(360,NULL,NULL,90350050,40,NULL,28),(361,NULL,NULL,90350060,50,NULL,28),(362,NULL,NULL,90350070,60,NULL,28),(363,NULL,NULL,90350080,70,NULL,28),(364,NULL,NULL,90350090,80,NULL,28),(365,NULL,NULL,90350100,90,NULL,28),(366,NULL,NULL,90350110,100,NULL,28),(367,NULL,NULL,90350210,110,NULL,28),(368,NULL,NULL,90350220,120,NULL,28),(369,NULL,NULL,90350230,130,NULL,28),(370,NULL,NULL,90350240,140,NULL,28),(371,NULL,NULL,90400010,10,NULL,29),(372,NULL,NULL,90400020,20,NULL,29),(373,NULL,NULL,90400030,30,NULL,29),(374,NULL,NULL,90410010,40,NULL,29),(375,NULL,NULL,90410010,10,NULL,30),(376,NULL,NULL,90410020,20,NULL,30),(377,NULL,NULL,90410030,30,NULL,30),(378,NULL,NULL,90410040,40,NULL,30),(379,NULL,NULL,90410050,50,NULL,30),(380,NULL,NULL,90450020,10,NULL,31),(381,NULL,NULL,90450030,20,NULL,31),(382,NULL,NULL,90450040,30,NULL,31),(383,NULL,NULL,90450050,40,NULL,31),(384,NULL,NULL,90450060,50,NULL,31),(385,NULL,NULL,90460010,60,NULL,31),(386,NULL,NULL,90460020,20,NULL,32),(387,NULL,NULL,90460030,30,NULL,32),(388,NULL,NULL,99020040,10,NULL,32),(389,NULL,NULL,90900020,10,NULL,33),(390,NULL,NULL,90900030,20,NULL,33),(391,NULL,NULL,90900040,30,NULL,33),(392,NULL,NULL,90900050,40,NULL,33),(393,NULL,NULL,90900060,50,NULL,33),(394,NULL,NULL,90900070,60,NULL,33),(395,NULL,NULL,90900080,70,NULL,33),(396,NULL,NULL,90900900,80,NULL,33),(397,NULL,NULL,90900910,90,NULL,33),(398,NULL,NULL,90910020,10,NULL,34),(399,NULL,NULL,90910030,20,NULL,34),(400,NULL,NULL,90910040,30,NULL,34),(401,NULL,NULL,90910050,40,NULL,34),(402,NULL,NULL,90910060,50,NULL,34),(403,NULL,NULL,90910070,60,NULL,34),(404,NULL,NULL,90910080,70,NULL,34),(405,NULL,NULL,90910090,80,NULL,34),(406,NULL,NULL,90910100,90,NULL,34),(407,NULL,NULL,90910200,100,NULL,34),(409,NULL,NULL,90920030,20,NULL,35),(410,NULL,NULL,90920040,30,NULL,35),(411,NULL,NULL,90920050,40,NULL,35),(412,NULL,NULL,90920060,50,NULL,35),(413,NULL,NULL,90920070,60,NULL,35),(414,NULL,NULL,90920080,70,NULL,35),(415,NULL,NULL,94010010,10,NULL,36),(416,NULL,NULL,94010020,20,NULL,36),(417,NULL,NULL,94010030,30,NULL,36),(418,NULL,NULL,94050010,40,NULL,36),(419,NULL,NULL,94050020,10,NULL,37),(420,NULL,NULL,94050030,20,NULL,37),(421,NULL,NULL,94050040,30,NULL,37),(422,NULL,NULL,94050050,40,NULL,37),(423,NULL,NULL,94050060,50,NULL,37),(424,NULL,NULL,94050070,60,NULL,37),(425,NULL,NULL,94050080,70,NULL,37),(426,NULL,NULL,94050090,80,NULL,37),(427,NULL,NULL,94050100,90,NULL,37),(428,NULL,NULL,94050200,100,NULL,37),(429,NULL,NULL,94059000,110,NULL,37),(430,NULL,NULL,94059010,120,NULL,37),(431,NULL,NULL,95020010,10,NULL,38),(432,NULL,NULL,95020020,20,NULL,38),(433,NULL,NULL,95020030,30,NULL,38),(435,NULL,NULL,95030020,20,NULL,39),(436,NULL,NULL,95030030,30,NULL,39),(437,NULL,NULL,95040020,10,NULL,40),(438,NULL,NULL,95040030,20,NULL,40),(439,NULL,NULL,95040040,30,NULL,40),(440,NULL,NULL,95040090,40,NULL,40),(441,NULL,NULL,95050020,10,NULL,41),(442,NULL,NULL,95050030,20,NULL,41),(443,NULL,NULL,95050040,30,NULL,41),(444,NULL,NULL,95050050,40,NULL,41),(445,NULL,NULL,95050060,50,NULL,41),(446,NULL,NULL,95050070,60,NULL,41),(447,NULL,NULL,95050080,70,NULL,41),(448,NULL,NULL,95050090,80,NULL,41),(449,NULL,NULL,95050100,90,NULL,41),(450,NULL,NULL,95050110,100,NULL,41),(451,NULL,NULL,95050120,110,NULL,41),(452,NULL,NULL,95050130,120,NULL,41),(453,NULL,NULL,95050140,130,NULL,41),(454,NULL,NULL,95050150,140,NULL,41),(455,NULL,NULL,95050160,150,NULL,41),(456,NULL,NULL,95050170,160,NULL,41),(457,NULL,NULL,95059000,170,NULL,41),(458,NULL,NULL,95059010,180,NULL,41),(521,NULL,NULL,80900020,10,NULL,51),(522,NULL,NULL,80900030,20,NULL,51),(523,NULL,NULL,80900040,30,NULL,51),(524,NULL,NULL,80900050,40,NULL,51),(525,NULL,NULL,80900060,50,NULL,51),(526,NULL,NULL,80910020,10,NULL,52),(527,NULL,NULL,80910030,20,NULL,52),(528,NULL,NULL,80920010,30,NULL,52),(529,NULL,NULL,80920020,10,NULL,53),(530,NULL,NULL,80920030,20,NULL,53),(531,NULL,NULL,80920040,30,NULL,53),(532,NULL,NULL,80920050,40,NULL,53),(533,NULL,NULL,80920060,50,NULL,53),(534,NULL,NULL,80920070,60,NULL,53),(549,NULL,NULL,70100010,10,NULL,55),(550,NULL,NULL,70100020,20,NULL,55),(551,NULL,NULL,70100030,30,NULL,55),(552,NULL,NULL,70100040,40,NULL,55),(553,NULL,NULL,70100050,50,NULL,55),(554,NULL,NULL,70100060,60,NULL,55),(555,NULL,NULL,70100070,70,NULL,55),(556,NULL,NULL,70100080,80,NULL,55),(557,NULL,NULL,70100090,90,NULL,55),(558,NULL,NULL,70100100,100,NULL,55),(559,NULL,NULL,70100110,110,NULL,55),(560,NULL,NULL,70100120,120,NULL,55),(561,NULL,NULL,70100130,130,NULL,55),(562,NULL,NULL,70100140,140,NULL,55);
/*!40000 ALTER TABLE `_ModuleDetailSchemeField` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ModuleExcelRecordAdd`
--

DROP TABLE IF EXISTS `_ModuleExcelRecordAdd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ModuleExcelRecordAdd` (
  `tf_id` int(11) NOT NULL,
  `tf_inputdate` datetime NOT NULL,
  `tf_inputmen` varchar(10) NOT NULL,
  `tf_author` varchar(10) DEFAULT NULL,
  `tf_filedata` longblob,
  `tf_filename` varchar(99) DEFAULT NULL,
  `tf_filesize` int(11) DEFAULT NULL,
  `tf_isEnable` bit(1) DEFAULT NULL,
  `tf_isSelectMonth` bit(1) DEFAULT NULL,
  `tf_isSelectRecord` bit(1) DEFAULT NULL,
  `tf_isSelectSeason` bit(1) DEFAULT NULL,
  `tf_isSelectYear` bit(1) DEFAULT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_order` int(11) NOT NULL,
  `tf_otherSetting` varchar(255) DEFAULT NULL,
  `tf_relation` varchar(255) DEFAULT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_type` varchar(50) DEFAULT NULL,
  `tf_uploadDate` datetime DEFAULT NULL,
  `tf_moduleId` varchar(10) NOT NULL,
  PRIMARY KEY (`tf_id`),
  KEY `FKb3swh5uglk24nnl4paix3msr4` (`tf_moduleId`),
  CONSTRAINT `FKb3swh5uglk24nnl4paix3msr4` FOREIGN KEY (`tf_moduleId`) REFERENCES `_Module` (`tf_moduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ModuleExcelRecordAdd`
--

LOCK TABLES `_ModuleExcelRecordAdd` WRITE;
/*!40000 ALTER TABLE `_ModuleExcelRecordAdd` DISABLE KEYS */;
/*!40000 ALTER TABLE `_ModuleExcelRecordAdd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ModuleExcelReport`
--

DROP TABLE IF EXISTS `_ModuleExcelReport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ModuleExcelReport` (
  `tf_id` int(11) NOT NULL,
  `tf_inputdate` datetime NOT NULL,
  `tf_inputmen` varchar(10) NOT NULL,
  `tf_author` varchar(10) DEFAULT NULL,
  `tf_filedata` longblob,
  `tf_filename` varchar(99) DEFAULT NULL,
  `tf_filesize` int(11) DEFAULT NULL,
  `tf_isEnable` bit(1) DEFAULT NULL,
  `tf_isSelectMonth` bit(1) DEFAULT NULL,
  `tf_isSelectRecord` bit(1) DEFAULT NULL,
  `tf_isSelectSeason` bit(1) DEFAULT NULL,
  `tf_isSelectYear` bit(1) DEFAULT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_order` int(11) NOT NULL,
  `tf_otherSetting` varchar(255) DEFAULT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_type` varchar(50) DEFAULT NULL,
  `tf_uploadDate` datetime DEFAULT NULL,
  `tf_moduleId` varchar(10) NOT NULL,
  PRIMARY KEY (`tf_id`),
  KEY `FK7o7g98blau57orgydrfa83dci` (`tf_moduleId`),
  CONSTRAINT `FK7o7g98blau57orgydrfa83dci` FOREIGN KEY (`tf_moduleId`) REFERENCES `_Module` (`tf_moduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ModuleExcelReport`
--

LOCK TABLES `_ModuleExcelReport` WRITE;
/*!40000 ALTER TABLE `_ModuleExcelReport` DISABLE KEYS */;
/*!40000 ALTER TABLE `_ModuleExcelReport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ModuleField`
--

DROP TABLE IF EXISTS `_ModuleField`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ModuleField` (
  `tf_fieldId` int(11) NOT NULL,
  `tf_moduleId` varchar(10) NOT NULL,
  `tf_fieldOrder` int(11) DEFAULT NULL,
  `tf_title` varchar(50) NOT NULL,
  `tf_fieldName` varchar(50) NOT NULL,
  `tf_fieldType` varchar(50) NOT NULL,
  `tf_fieldLen` int(11) DEFAULT NULL,
  `tf_digitsLen` int(11) DEFAULT NULL,
  `tf_fieldGroup` varchar(50) NOT NULL,
  `tf_fieldRelation` varchar(20) DEFAULT NULL,
  `tf_joinTable` varchar(50) DEFAULT NULL,
  `tf_DBfieldName` varchar(50) DEFAULT NULL,
  `tf_DBformula` mediumtext,
  `tf_isDisable` bit(1) DEFAULT NULL,
  `tf_isHidden` bit(1) DEFAULT NULL,
  `tf_allowNew` bit(1) DEFAULT NULL,
  `tf_allowEdit` bit(1) DEFAULT NULL,
  `tf_allowGroup` bit(1) DEFAULT NULL,
  `tf_allowSummary` bit(1) DEFAULT NULL,
  `tf_allowAggregate` bit(1) DEFAULT NULL,
  `tf_showNavigatorTree` bit(1) DEFAULT NULL,
  `tf_newNeedSelected` bit(1) DEFAULT NULL,
  `tf_allowInsertExcel` bit(1) DEFAULT NULL,
  `tf_allowEditExcel` bit(1) DEFAULT NULL,
  `tf_isChartCategory` bit(1) DEFAULT NULL,
  `tf_isChartNumeric` bit(1) DEFAULT NULL,
  `tf_haveAttachment` bit(1) DEFAULT NULL,
  `tf_isMonetary` bit(1) DEFAULT NULL,
  `tf_isRequired` bit(1) DEFAULT NULL,
  `tf_maxValue` int(11) DEFAULT NULL,
  `tf_minValue` int(11) DEFAULT NULL,
  `tf_regexValue` mediumtext,
  `tf_vtype` varchar(50) DEFAULT NULL,
  `tf_jsValue` mediumtext,
  `tf_unitText` varchar(50) DEFAULT NULL,
  `tf_defaultValue` varchar(50) DEFAULT NULL,
  `tf_propertyTypeId` int(11) DEFAULT NULL,
  `tf_propertyValue` mediumtext,
  `tf_divisor` varchar(50) DEFAULT NULL,
  `tf_denominator` varchar(50) DEFAULT NULL,
  `tf_tooltipTpl` mediumtext,
  `tf_otherSetting` mediumtext,
  `tf_remark` mediumtext,
  `tf_modelSet` mediumtext,
  `tf_gridColumnSet` mediumtext,
  `tf_formFieldSet` mediumtext,
  `tf_reportSet` mediumtext,
  `tf_isUserDefine` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`tf_fieldId`),
  KEY `FKaxnt98p7mtfrorga3kt51s0fd` (`tf_moduleId`),
  KEY `FK4co3gm10u2l2h8m4v555kr16t` (`tf_propertyTypeId`),
  CONSTRAINT `FK4co3gm10u2l2h8m4v555kr16t` FOREIGN KEY (`tf_propertyTypeId`) REFERENCES `_PropertyType` (`tf_propertyTypeId`),
  CONSTRAINT `FKaxnt98p7mtfrorga3kt51s0fd` FOREIGN KEY (`tf_moduleId`) REFERENCES `_Module` (`tf_moduleId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ModuleField`
--

LOCK TABLES `_ModuleField` WRITE;
/*!40000 ALTER TABLE `_ModuleField` DISABLE KEYS */;
INSERT INTO `_ModuleField` VALUES (111,'9041',200,'用户列表','tf_Users','Set<_User>',0,NULL,'默认组','ManyToMany','_UserRole',NULL,NULL,0x00,0x00,0x01,0x01,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60100010,'6010',10,'序号','tf_customerId','Integer',NULL,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x01,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60100020,'6010',20,'市','tf_City','City',NULL,NULL,'基本信息','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60100030,'6010',30,'行业','tf_Trade','Trade',NULL,NULL,'基本信息','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60100040,'6010',40,'等级','tf_Rate','Rate',NULL,NULL,'基本信息','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60100050,'6010',50,'客户名称','tf_name','String',50,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'请输入该单位公章上的全称',NULL,NULL,NULL,NULL,NULL),(60100060,'6010',60,'单位地址','tf_address','String',50,NULL,'附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60100070,'6010',70,'联系人','tf_linkman','String',10,NULL,'附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60100080,'6010',80,'联系电话','tf_linkmanTel','String',20,NULL,'附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60100090,'6010',90,'税号','tf_taxId','String',20,NULL,'附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60100100,'6010',190,'备注','tf_remark','String',20,NULL,'附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60200010,'6020',10,'序号','tf_salesmanId','Integer',NULL,NULL,'用户基本信息',NULL,NULL,'',NULL,NULL,0x01,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60200020,'6020',20,'部门','tf_Department','_Department',NULL,NULL,'用户基本信息','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60200030,'6020',30,'姓名','tf_name','String',10,NULL,'用户基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60200040,'6020',40,'性别','tf_sex','String',2,NULL,'用户附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60200050,'6020',50,'出生日期','tf_birthday','Date',NULL,NULL,'用户附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60200060,'6020',60,'年龄','tf_age','Integer',NULL,NULL,'用户附加信息',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60200070,'6020',70,'联系电话','tf_telnumber','String',20,NULL,'用户附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60200080,'6020',80,'手机号码','tf_phonenumber','String',20,NULL,'用户附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60200090,'6020',90,'电子邮件','tf_eMail','String',50,NULL,'用户附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60200100,'6020',190,'备注','tf_remark','String',NULL,NULL,'用户附加信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60300010,'6030',10,'序号','tf_productId','Integer',NULL,NULL,'商品基本信息',NULL,NULL,'',NULL,NULL,0x01,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60300020,'6030',20,'商品类别','tf_ProductClass','ProductClass',NULL,NULL,'商品基本信息','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60300030,'6030',30,'商品名称','tf_name','String',50,NULL,'商品基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60300040,'6030',40,'产地','tf_origin','String',20,NULL,'商品基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60300050,'6030',50,'销售单价','tf_unitPrice','Double',NULL,NULL,'商品附加信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60300060,'6030',60,'成本单价','tf_costPrice','Double',NULL,NULL,'商品附加信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60300070,'6030',190,'备注','tf_remark','String',NULL,NULL,'商品附加信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60400010,'6040',10,'序号','tf_ordersId','Integer',NULL,NULL,'订单信息',NULL,NULL,'',NULL,NULL,0x01,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60400020,'6040',20,'客户单位','tf_Customer','Customer',NULL,NULL,'订单信息','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60400030,'6040',30,'业务员','tf_Salesman','Salesman',NULL,NULL,'订单信息','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60400040,'6040',40,'订单号','tf_ordersNumber','String',20,NULL,'订单信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60400050,'6040',50,'订单日期','tf_date','Date',NULL,NULL,'订单信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60400060,'6040',55,'订单年份','tf_year','String',5,NULL,'订单信息',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60400070,'6040',60,'是否完成','tf_finished','Boolean',NULL,NULL,'订单信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60400080,'6040',190,'备注','tf_remark','String',NULL,NULL,'商品附加信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60400090,'6040',35,'商品仓库','tf_Storage','Storage',NULL,NULL,'订单信息','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60500010,'6050',10,'序号','tf_ordersDetailId','Integer',NULL,NULL,'订单明细信息',NULL,NULL,'',NULL,NULL,0x01,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60500020,'6050',20,'订单','tf_Orders','Orders',NULL,NULL,'订单明细信息','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60500030,'6050',30,'商品','tf_Product','Product',NULL,NULL,'订单明细信息','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60500040,'6050',40,'明细描述','tf_name','String',50,NULL,'订单明细信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60500050,'6050',50,'数量','tf_number','Integer',NULL,NULL,'订单明细信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60500060,'6050',60,'单价','tf_unitPrice','Double',NULL,NULL,'订单明细信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60500070,'6050',70,'金额','tf_subtotalPrice','Double',NULL,NULL,'订单明细信息',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60500080,'6050',190,'备注','tf_remark','String',NULL,NULL,'订单明细信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70100010,'7010',10,'编码','tf_provinceId','String',10,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70100020,'7010',20,'名称','tf_name','String',50,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70100030,'7010',30,'简称','tf_shortname','String',10,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70100040,'7010',40,'所属区域','tf_district','String',50,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,0x01,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,'华东地区',NULL,NULL,NULL,NULL,NULL,'formfield:{comboThisField:true},field : {searchCondOrder :41 }',NULL,NULL,NULL,NULL,NULL,NULL),(70100050,'7010',50,'面积','tf_area','Integer',NULL,NULL,'附加信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'平方公里',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70100060,'7010',60,'人口数量','tf_numberOfPeople','Integer',NULL,NULL,'附加信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'万',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70100070,'7010',70,'GPD','tf_GDP','Double',NULL,NULL,'附加信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,'亿',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70100080,'7010',70,'人均GPD','tf_averageGDP','Double',NULL,NULL,'附加信息',NULL,NULL,'','   tf_GDP /   tf_numberOfPeople',NULL,0x00,0x00,0x00,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,'万元/人',NULL,NULL,NULL,'tf_GDP','tf_numberOfPeople','GDP：{tf_GDP}亿/人口数量：{tf_numberOfPeople}万人',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70100090,'7010',80,'百分比','tf_percent','Percent',NULL,NULL,'附加信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70100100,'7010',90,'是否自治区','tf_isMunicipality','Boolean',NULL,NULL,'附加信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70100101,'7010',101,'距今天数','tf_days','Integer',0,NULL,'附加信息',NULL,NULL,NULL,' (datediff(now(),   tf_createDate))',0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70100110,'7010',100,'附加日期','tf_createDate','Date',NULL,NULL,'附加信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70100120,'7010',190,'备注','tf_remark','String',NULL,NULL,'附加信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70100130,'7010',9000,'录入人员','tf_inputmen','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70100140,'7010',9010,'录入日期','tf_inputdate','Date',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70100150,'7010',110,'顺序号','tf_orderId','Integer',NULL,NULL,'附加信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70100160,'7010',2010,'审核人员','tf_auditingName','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70100170,'7010',2020,'审核日期','tf_auditingDate','Date',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70100180,'7010',2030,'审核备注','tf_auditingRemark','String',100,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70100190,'7010',2040,'审核','tf_auditinged','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70100200,'7010',110,'地图','tf_map','Image',NULL,NULL,'附加信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120010,'7012',10,'编码','tf_cityId','String',4,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120020,'7012',20,'省份','tf_Province','Province',NULL,NULL,'基本信息','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120030,'7012',30,'名称','tf_name','String',50,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120040,'7012',40,'邮政编码','tf_postNumber','String',6,NULL,'附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120050,'7012',50,'电话区号','tf_telHead','String',6,NULL,'附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120060,'7012',70,'金额属性','tf_money','Double',NULL,NULL,'附加信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,'只',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120070,'7012',190,'备注','tf_remark','String',NULL,NULL,'附加信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120080,'7012',1000,'已审批级数','tf_shNowCount','Integer',NULL,NULL,'审批结果',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120090,'7012',1001,'审批结果','tf_shResult','String',10,NULL,'审批结果',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120100,'7012',1002,'审批日期','tf_shResultDate','Date',NULL,NULL,'审批结果',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120110,'7012',1003,'审批人员','tf_shResultName','String',10,NULL,'审批结果',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120120,'7012',1004,'审批意见','tf_shResultExplain','String',NULL,NULL,'审批结果',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120130,'7012',1009,'锁定','tf_shLocked','Boolean',NULL,NULL,'审批结果',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120140,'7012',1110,'审批人员1','tf_shname1','String',10,NULL,'审批1',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120150,'7012',1120,'审批日期1','tf_shdate1','Date',NULL,NULL,'审批1',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120160,'7012',1130,'审批结果1','tf_shresult1','String',20,NULL,'审批1',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120170,'7012',1140,'审批意见1','tf_shexplain1','String',NULL,NULL,'审批1',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120180,'7012',1210,'审批人员2','tf_shname2','String',10,NULL,'审批2',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120190,'7012',1220,'审批日期2','tf_shdate2','Date',NULL,NULL,'审批2',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120200,'7012',1230,'审批结果2','tf_shresult2','String',20,NULL,'审批2',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120210,'7012',1240,'审批意见2','tf_shexplain2','String',NULL,NULL,'审批2',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120220,'7012',1310,'审批人员3','tf_shname3','String',10,NULL,'审批3',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120230,'7012',1320,'审批日期3','tf_shdate3','Date',NULL,NULL,'审批3',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120240,'7012',1330,'审批结果3','tf_shresult3','String',20,NULL,'审批3',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120250,'7012',1340,'审批意见3','tf_shexplain3','String',NULL,NULL,'审批3',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120260,'7012',1410,'审批人员4','tf_shname4','String',10,NULL,'审批4',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120270,'7012',1420,'审批日期4','tf_shdate4','Date',NULL,NULL,'审批4',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120280,'7012',1430,'审批结果4','tf_shresult4','String',20,NULL,'审批4',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120290,'7012',1440,'审批意见4','tf_shexplain4','String',NULL,NULL,'审批4',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120300,'7012',1510,'审批人员5','tf_shname5','String',10,NULL,'审批5',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120310,'7012',1520,'审批日期5','tf_shdate5','Date',NULL,NULL,'审批5',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120320,'7012',1530,'审批结果5','tf_shresult5','String',20,NULL,'审批5',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120330,'7012',1540,'审批意见5','tf_shexplain5','String',NULL,NULL,'审批5',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120340,'7012',1610,'审批人员6','tf_shname6','String',10,NULL,'审批6',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120350,'7012',1620,'审批日期6','tf_shdate6','Date',NULL,NULL,'审批6',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120360,'7012',1630,'审批结果6','tf_shresult6','String',20,NULL,'审批6',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120370,'7012',1640,'审批意见6','tf_shexplain6','String',NULL,NULL,'审批6',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120380,'7012',9000,'录入人员','tf_inputmen','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70120390,'7012',9010,'录入日期','tf_inputdate','Date',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70140010,'7014',10,'编码','tf_rateId','String',2,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70140020,'7014',20,'等级名称','tf_name','String',50,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70160010,'7016',10,'行业编码','tf_tradeId','String',6,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70160020,'7016',20,'行业名称','tf_name','String',50,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70180010,'7018',10,'编码','tf_productClassId','String',6,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70180020,'7018',20,'产品类别名称','tf_name','String',50,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70200010,'7020',10,'序号','tf_storageId','Integer',NULL,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x01,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70200030,'7020',30,'仓库名称','tf_name','String',50,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70200040,'7020',40,'地址','tf_address','String',50,NULL,'附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70200050,'7020',50,'联系人','tf_linkman','String',10,NULL,'附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70200060,'7020',60,'联系电话','tf_linkmanTel','String',20,NULL,'附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70200070,'7020',190,'备注','tf_remark','String',20,NULL,'附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80900010,'8090',NULL,'ID号','tf_propertyTypeId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80900020,'8090',NULL,'属性名称','tf_name','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80900030,'8090',NULL,'可录入','tf_canInput','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'选中表示，此combo可以输入列表中没有的值',NULL,NULL,NULL,NULL,NULL),(80900040,'8090',NULL,'可多选','tf_canmultSelected','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'选中表示，此combo可以多选',NULL,NULL,NULL,NULL,NULL),(80900050,'8090',NULL,'属性值','tf_value','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80900060,'8090',NULL,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80910010,'8091',10,'ID号','tf_numberGroupId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80910020,'8091',20,'分组名称','tf_name','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80910030,'8091',30,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80920010,'8092',10,'ID号','tf_numberGroupDetailId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80920020,'8092',20,'数值分组','tf_NumberGroup','_NumberGroup',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x00,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80920030,'8092',30,'序号','tf_order','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80920040,'8092',40,'条件描述','tf_name','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80920050,'8092',50,'字段条件A','tf_condition1','String',99,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thisfield 表示此字段',NULL,NULL,NULL,NULL,NULL),(80920060,'8092',60,'字段条件B','tf_condition2','String',99,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thisfield 表示此字段',NULL,NULL,NULL,NULL,NULL),(80920070,'8092',90,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90000010,'9000',10,'tf_systemsetId','tf_systemsetId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90000020,'9000',20,'单位名称','tf_userdwmc','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90000030,'9000',30,'单位地址','tf_userAddress','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90000040,'9000',40,'单位性质','tf_userType','String',20,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90000050,'9000',50,'联系人','tf_userLinkmen','String',20,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90000060,'9000',60,'联系电话','tf_userTelnumber','String',20,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90000070,'9000',70,'开始使用日期','tf_userStartdate','Date',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90000090,'9000',90,'用户备注','tf_userRemark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90000100,'9000',210,'服务单位名称','tf_serviceDepartment','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90000110,'9000',220,'服务人员','tf_serviceMen','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90000120,'9000',230,'服务电话','tf_serviceTelnumber','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90000130,'9000',240,'服务传真','tf_serviceFaxnumber','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90000140,'9000',250,'服务电子邮件','tf_serviceEmail','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90000150,'9000',260,'服务主页','tf_serviceHomepage','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90000160,'9000',270,'服务人员QQ','tf_serviceQQ','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90010010,'9001',10,'序号','tf_systemsetAdditionId','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90010100,'9001',100,'忘记密码提示','tf_userforgetPassword','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90010110,'9001',110,'备份路径','tf_backupfiledirs','String',250,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'系统自动备份的数据路径，可以设置多个用;号隔开',NULL,NULL,NULL,NULL,NULL),(90010200,'9001',200,'请款金额控制','tf_paymentMethod','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,8,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90010210,'9001',210,'预付','tf_perBeforehand','Percent',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90010220,'9001',220,'主体过半','tf_perWorkhalf','Percent',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90010230,'9001',230,'竣工后','tf_perFinish','Percent',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90010240,'9001',240,'审计后','tf_perAfterAudit','Percent',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90010250,'9001',250,'竣工半年后','tf_perFinishHalfYear','Percent',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90010260,'9001',260,'竣工一年后','tf_perFinishOneYear','Percent',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90010270,'9001',270,'竣工二年后','tf_perFinishTwoYear','Percent',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90010280,'9001',280,'竣工三年后','tf_perFinishThreeYear','Percent',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90010290,'9001',290,'竣工四年后','tf_perFinishFourYear','Percent',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90010300,'9001',20,'审批后才能继续','tf_continueWithAudit','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90050010,'9005',10,'序号','tf_systeminfoId','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90050020,'9005',20,'系统名称','tf_systemName','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90050030,'9005',30,'版本号','tf_systemVersion','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90050040,'9005',40,'附加信息','tf_systemAddition','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90050050,'9005',50,'版权所有','tf_copyrightOwner','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90050060,'9005',60,'版权信息','tf_copyrightInfo','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90050100,'9005',100,'单点登录','tf_allowExternalLogin','Boolean',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90050110,'9005',110,'2周内自动登录','tf_allowautoLoginInTwoWeeks','Boolean',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90050120,'9005',120,'更改初始密码','tf_needReplaceInitialPassword','Boolean',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'是否需要在第一次登录的时候修改初始密码',NULL,NULL,NULL,NULL,NULL),(90050130,'9005',130,'需要验证码','tf_needIdentifingCode','Boolean',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90050140,'9005',140,'总是需要验证码','tf_alwaysNeedIdentifingCode','Boolean',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'是否总是需要验证码，如果是false,则登录时输错一次密码后才显示验证码',NULL,NULL,NULL,NULL,NULL),(90050150,'9005',150,'最大登录用户数','tf_maxusers','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90050160,'9005',160,'超时时间分种','tf_sessionTimeoutMinute','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90050170,'9005',170,'附件最大兆数','tf_additionFileMaxMB','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90050180,'9005',180,'数据备份文件名','tf_backupfilename','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90050190,'9005',190,'图像压缩模式','tf_AttachmentReduceMode','_AttachmentReduceMode',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90050300,'9005',300,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90050320,'9005',210,'可预览文件后缀','tf_previewExts','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'附件中可以在网页中预览的文件后缀名',NULL,NULL,NULL,NULL,NULL),(90100010,'9010',10,'编号','tf_scopeId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90100020,'9010',20,'权限名称','tf_scopeName','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90100030,'9010',30,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90110010,'9011',10,'部门编号','tf_departmentId','String',10,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90110020,'9011',20,'部门名称','tf_name','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90110030,'9011',30,'部门简称','tf_shortname','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90110040,'9011',40,'部门类别','tf_Scope','_DepartmentScope',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,0x01,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90110050,'9011',50,'操作所有','tf_isOperAll','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90110060,'9011',60,'本级','tf_isOperThisLevel','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90110070,'9011',70,'付款部门','tf_isPayDepa','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90110080,'9011',80,'建设','tf_isjs','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90110090,'9011',90,'财务','tf_iscw','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90110100,'9011',100,'审计','tf_issj','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90110110,'9011',110,'职责范围','tf_effect','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90110120,'9011',120,'管理权限','tf_preview','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90110130,'9011',130,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90110140,'9011',130,'负责人','tf_fzr','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90300010,'9030',10,'编号','tf_positionId','String',10,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90300020,'9030',20,'职务名称','tf_positionName','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90300030,'9030',30,'主要职能','tf_effect','String',100,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90300040,'9030',40,'附加设置','tf_preview','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90300050,'9030',50,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90350010,'9035',10,'序号','tf_userId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90350020,'9035',20,'部门','tf_Department','_Department',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'field:{ allowParentValue : 1 } ','allowParentValue 可以选择非末级的值',NULL,NULL,NULL,NULL,NULL),(90350030,'9035',30,'用户姓名','tf_userName','String',20,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90350040,'9035',40,'登录名','tf_loginName','String',20,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'用户登录本系统时使用的名称',NULL,NULL,NULL,NULL,NULL),(90350050,'9035',50,'职务','tf_Position','_Position',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90350060,'9035',60,'允许登录','tf_allowLogin','Boolean',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'是否允许此用户登录系统，如未选中则不许登录',NULL,NULL,NULL,NULL,NULL),(90350070,'9035',70,'办公室','tf_office','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90350080,'9035',80,'联系电话','tf_telnumber','String',20,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90350090,'9035',90,'手机号码','tf_phonenumber','String',20,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90350100,'9035',100,'电子邮件','tf_eMail','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90350110,'9035',110,'QQ号','tf_qq','String',20,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90350210,'9035',210,'最后登录日期','tf_lastLoginDate','Datetime',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90350220,'9035',220,'登录次数','tf_loginTimes','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90350230,'9035',230,'附加设置','tf_favoriteSet','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90350240,'9035',240,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90350250,'9035',120,'可发送短信','tf_isSendMessage','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'是否可对此人发送短信提醒',NULL,NULL,NULL,NULL,NULL),(90350260,'9035',200,'权限列表','tf_Roles','Set<_Role>',NULL,NULL,'默认组','ManyToMany','_UserRole',NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90380010,'9038',10,'序号','tf_filterRoleId','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90380020,'9038',20,'记录筛选角色名称','tf_name','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90380030,'9038',90,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90390010,'9039',10,'序号','tf_userDataFilterRoleId','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90390020,'9039',20,'用户','tf_User','_User',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90390030,'9039',30,'记录筛选角色','tf_DataFilterRole','_DataFilterRole',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90400010,'9040',10,'分组编码','tf_roleGroupId','String',10,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90400020,'9040',20,'角色分组名称','tf_title','String',20,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90400030,'9040',30,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90410010,'9041',10,'角色编码','tf_roleId','String',10,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90410020,'9041',20,'角色名称','tf_roleName','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90410030,'9041',30,'用户角色分组','tf_RoleGroup','_RoleGroup',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90410040,'9041',40,'角色可用','tf_isEnable','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90410050,'9041',50,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90420010,'9042',10,'序号','tf_userRoleId','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90420020,'9042',20,'用户角色','tf_Role','_Role',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90420030,'9042',30,'用户','tf_User','_User',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90430010,'9043',10,'序号','tf_additionDepaId','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90430020,'9043',20,'可管理部门','tf_AllDepartment','_AllDepartment',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'field:{ allowParentValue : 1 }',NULL,NULL,NULL,NULL,NULL,NULL),(90430030,'9043',30,'用户','tf_User','_User',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90450010,'9045',10,'ID号','tf_approveId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90450020,'9045',20,'所属模块','tf_Module','_Module',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x00,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90450030,'9045',30,'顺序号','tf_order','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90450040,'9045',40,'审批部门名称','tf_departmentName','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90450050,'9045',50,'审批次序','tf_level','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'审批次序相同的可以多级同时审批。',NULL,NULL,NULL,NULL,NULL),(90450060,'9045',60,'可以终止审批','tf_allowFinished','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'可以审批的同时终止审批流程，即最终决定通过或不通过审批。',NULL,NULL,NULL,NULL,NULL),(90460010,'9046',10,'ID号','tf_approveUserId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90460020,'9046',20,'所属审批部门','tf_ModuleApprove','_ModuleApprove',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x00,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90460030,'9046',30,'用户','tf_User','_User',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90500010,'9050',10,'顺序号','tf_reportGroupId','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90500020,'9050',20,'分组名称','tf_title','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90500030,'9050',30,'分隔下一条','tf_addSeparator','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'在树状菜单下默认是否展开',NULL,NULL,NULL,NULL,NULL),(90500040,'9050',40,'图标文件名','tf_iconURL','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'图标放置于/images/module/目录下',NULL,NULL,NULL,NULL,NULL),(90500050,'9050',50,'分组描述','tf_description','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90500060,'9050',60,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90520010,'9052',10,'ID号','tf_reportId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90520020,'9052',20,'顺序号','tf_orderId','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'按顺序号显示在报表菜单中',NULL,NULL,NULL,NULL,NULL),(90520030,'9052',30,'综合查询分组','tf_ReportGroup','_ReportGroup',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90520040,'9052',40,'综合查询名称','tf_title','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90520050,'9052',50,'类型','tf_type','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90520060,'9052',60,'图标文件名','tf_iconURL','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'图标放置于/images/module/目录下',NULL,NULL,NULL,NULL,NULL),(90520070,'9052',70,'系统方案','tf_isSystem','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90520080,'9052',80,'分组定义','tf_groupDefine','String',99,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90520090,'9052',90,'分组显示明细','tf_isShowDetail','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90520100,'9052',800,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90520110,'9052',9000,'录入人员','tf_inputmen','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90520120,'9052',9010,'录入日期','tf_inputdate','Date',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90550010,'9055',10,'ID号','tf_chartId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90550020,'9055',20,'综合查询','tf_Report','_Report',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90550030,'9055',30,'顺序号','tf_order','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90550040,'9055',40,'图表方案名称','tf_name','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90550050,'9055',50,'系统方案','tf_isSystemScheme','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90550060,'9055',60,'图表类型','tf_chartType','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90550070,'9055',70,'配色方案','tf_colorScheme','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90550080,'9055',80,'项目内容','tf_categoryField','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90550090,'9055',90,'数值内容','tf_numericFields','String',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90550100,'9055',100,'显示数值','tf_isShowDetailNumber','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90550110,'9055',110,'显示提示信息','tf_isShowTips','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90550120,'9055',120,'动画展示','tf_isAnimate','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90550130,'9055',130,'定位线','tf_isGridLine','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90550140,'9055',140,'标题','tf_title','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90550150,'9055',190,'附加设置','tf_otherSetting','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90550160,'9055',200,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90550170,'9055',9000,'录入人员','tf_inputmen','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90550180,'9055',9010,'录入日期','tf_inputdate','Date',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90900010,'9090',10,'序号','tf_systembackupId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90900020,'9090',20,'备份日期','tf_backupdate','Date',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90900030,'9090',30,'发起备份IP地址','tf_ipaddress','String',24,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90900040,'9090',40,'备份人员','tf_userName','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90900050,'9090',50,'备份文件名','tf_backupfilename','String',250,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90900060,'9090',60,'已下载','tf_isupload','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90900070,'9090',70,'备份结果','tf_result','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90900080,'9090',80,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90900900,'9090',900,'录入人员','tf_inputmen','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90900910,'9090',910,'录入日期','tf_inputdate','Date',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90910010,'9091',10,'序号','tf_systemlogId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90910020,'9091',20,'操作时间','tf_date','Date',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90910030,'9091',30,'操作ip地址','tf_ipaddress','String',24,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90910040,'9091',40,'用户序号','tf_userId','Integer',24,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90910050,'9091',50,'用户名称','tf_userName','String',24,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90910060,'9091',60,'操作类型','tf_do','String',20,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90910070,'9091',70,'模块序号','tf_moduleId','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90910080,'9091',80,'模块名称','tf_moduleTitle','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x00,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90910090,'9091',90,'记录主键值','tf_recordkey','String',20,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90910100,'9091',100,'记录名称值','tf_recordname','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90910200,'9091',200,'备注','tf_remark','String',0,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90920010,'9092',10,'序号','tf_systemLogiglogId','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90920020,'9092',20,'用户Id号','tf_userId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90920030,'9092',30,'用户登录名','tf_loginName','String',20,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90920040,'9092',40,'用户名','tf_userName','String',20,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90920050,'9092',50,'登录时间','tf_loginDate','Date',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90920060,'9092',60,'注销时间','tf_logoutDate','Date',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90920070,'9092',70,'登录ip地址','tf_ipaddress','String',30,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90920080,'9092',80,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94010010,'9401',10,'编码','tf_articleClassId','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94010020,'9401',20,'分类名称','tf_name','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94010030,'9401',30,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94050010,'9405',10,'序号','tf_articleId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94050020,'9405',20,'编码','tf_code','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94050030,'9405',30,'文件资料名称','tf_name','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94050040,'9405',40,'资料分类','tf_ArticleClass','_ArticleClass',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,0x01,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94050050,'9405',50,'发布单位','tf_publishCompany','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94050060,'9405',60,'发布时间','tf_publishDate','Date',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94050070,'9405',70,'制订人','tf_draftsman','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94050080,'9405',80,'版本号','tf_version','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94050090,'9405',90,'有效期','tf_validity','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94050100,'9405',100,'资料状态','tf_state','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94050200,'9405',200,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94059000,'9405',9000,'录入人员','tf_inputmen','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94059010,'9405',9010,'录入日期','tf_inputdate','Date',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95020010,'9502',10,'编码','tf_typeId','String',10,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95020020,'9502',20,'附件类型名称','tf_name','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95020030,'9502',30,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95030010,'9503',10,'ID号','tf_fileTypeId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95030020,'9503',20,'文件类型名称','tf_name','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95030030,'9503',30,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95040010,'9504',10,'序号','tf_reduceModeId','Integer',2,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95040020,'9504',20,'图片压缩模式','tf_name','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95040030,'9504',30,'长宽最大像素','tf_maxValue','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95040040,'9504',40,'缩小比例','tf_recudeTo','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95040090,'9504',90,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95050010,'9505',10,'ID号','tf_attachmentId','String',10,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95050020,'9505',20,'模块Id','tf_moduleId','String',10,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95050030,'9505',30,'记录主键值','tf_moduleIdvalue','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95050040,'9505',40,'序号','tf_order','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95050050,'9505',50,'附件类型','tf_AttachmentType','_AttachmentType',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95050060,'9505',60,'附件名称','tf_name','String',100,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95050070,'9505',70,'保管人','tf_keeper','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,0x00,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95050080,'9505',80,'档案号','tf_archiveNumber','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95050090,'9505',90,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95050100,'9505',100,'文件名','tf_filename','String',100,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95050110,'9505',110,'文件类型','tf_AttachmentFileType','_AttachmentFileType',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95050120,'9505',120,'文件大小','tf_filesize','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'field:{behindText:\'字节\'}',NULL,NULL,NULL,NULL,NULL,NULL),(95050130,'9505',130,'上传日期','tf_filelastupdate','Date',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95050140,'9505',140,'文件压缩保存','tf_fileCompressed','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95050150,'9505',150,'图像压缩模式','tf_AttachmentReduceMode','_AttachmentReduceMode',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95050160,'9505',160,'图像高度','tf_imgheight','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'field:{behindText:\'像素\'}',NULL,NULL,NULL,NULL,NULL,NULL),(95050170,'9505',170,'图像宽度','tf_imgwidth','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'field:{behindText:\'像素\'}',NULL,NULL,NULL,NULL,NULL,NULL),(95059000,'9505',9000,'录入人员','tf_inputmen','String',10,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95059010,'9505',9010,'录入日期','tf_inputdate','Date',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95059020,'9505',55,'附件对应字段','tf_AttachmentOnField','_AttachmentOnField',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95060010,'9506',10,'ID号','tf_fieldId','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95060020,'9506',20,'字段内容','tf_title','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95060030,'9506',30,'字段名','tf_fieldName','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99010010,'990101',10,'模块分组序号','tf_moduleGroupId','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99010020,'990101',20,'模块分组名称','tf_title','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99010030,'990101',40,'描述','tf_description','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99010040,'990101',60,'图标地址','tf_iconUrl','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99010050,'990101',190,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99010060,'990101',30,'系统组','tf_isSystemGroup','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99010070,'990101',50,'图标Cls','tf_iconCls','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99010080,'990101',70,'图标文件','tf_iconFile','Image',0,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99020010,'990102',10,'模块ID号','tf_moduleId','String',10,NULL,'基本信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'模块的id号,为一个6位数字,用来代表一个唯一的模块,在生成查询语句的时候，用 _t{id} 来表示此表的别名',NULL,NULL,NULL,NULL,NULL),(99020020,'990102',20,'模块分组','tf_ModuleGroup','_ModuleGroup',NULL,NULL,'基本信息','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,0x01,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'定义模块属于哪个组',NULL,NULL,NULL,NULL,NULL),(99020030,'990102',30,'模块标识','tf_moduleName','String',50,NULL,'基本信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'模块的唯一标识符，默认是该类的类名，在将模块属性导入到数据库中时自动使用类名。 （整个系统中不允许有相同的hibernate bean类名，哪怕是在不同的package中也不能有相同的类名。）',NULL,NULL,NULL,NULL,NULL),(99020040,'990102',40,'模块名称','tf_title','String',50,NULL,'基本信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'此模块的具体名称描述，用来显示在window，panel中的title部分',NULL,NULL,NULL,NULL,NULL),(99020050,'990102',50,'模块简称','tf_shortname','String',20,NULL,'基本信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'此模块的中文简称。在有些地方，比如说模块的按钮，tab中显示模块名称的时候，可以使用简称来减少字符长度',NULL,NULL,NULL,NULL,NULL),(99020060,'990102',60,'英文简称','tf_englishname','String',20,NULL,'基本信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'模块的英文简称，一般为几个字母，如果该模块有编码字段，可以以这几个英文字母开头加上时间序号自动生成一个编码',NULL,NULL,NULL,NULL,NULL),(99020070,'990102',70,'表名','tf_tableName','String',50,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'如果在数据库中使用的表或视图和此bean的名称不一致，需要设置tableName',NULL,NULL,NULL,NULL,NULL),(99020080,'990102',80,'主键','tf_primaryKey','String',50,NULL,'基本信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'模块的主键定义，自动从 bean 中获取。主键可以是整型，字符型，也可以增量型、UUID等类型。(主键不能是复合主键)',NULL,NULL,NULL,NULL,NULL),(99020090,'990102',90,'显示标志字段','tf_nameFields','String',50,NULL,'属性字段信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,' 模块的显示标志字段，自动从 bean 中获取。该字段的值可以用来描述该记录。主要用在删除时会显示此字段的值来确认删除。',NULL,NULL,NULL,NULL,NULL),(99020100,'990102',100,'模块描述','tf_description','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'模块的具体描术信息',NULL,NULL,NULL,NULL,NULL),(99020110,'990102',110,'请求地址','tf_requestMapping','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99020120,'990102',120,'图标地址','tf_iconUrl','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'模块的图标地址，如果未指定，则会查找images/module/{tf_moduleNam}.png文件，如果有的话就用此作为图标地址。',NULL,NULL,NULL,NULL,NULL),(99020130,'990102',130,'默认排序字段','tf_defaultOrderField','String',250,NULL,'属性字段信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'模块加载时的默认的排序字段，可以设置多级排序',NULL,NULL,NULL,NULL,NULL),(99020140,'990102',140,'行操作','tf_isInlineOper','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'新增及修改操作都行内完成',NULL,NULL,NULL,NULL,NULL),(99020150,'990102',150,'编码级次','tf_codeLevel','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'如果设置了编码级次，则主键的长度必须是此级次中的，并且必须有父级编码存在',NULL,NULL,NULL,NULL,NULL),(99020155,'990102',155,'联动模块','tf_linkedModule','String',200,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'在本模块的数据增删改后，打开的联动模块都要刷新数据。',NULL,NULL,NULL,NULL,NULL),(99020160,'990102',160,'可用','tf_isEnable','Boolean',NULL,NULL,'权限信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99020170,'990102',170,'可浏览','tf_hasBrowse','Boolean',NULL,NULL,'权限信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99020180,'990102',180,'可增加','tf_hasInsert','Boolean',NULL,NULL,'权限信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99020190,'990102',190,'可修改','tf_hasEdit','Boolean',NULL,NULL,'权限信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99020200,'990102',200,'可删除','tf_hasDelete','Boolean',NULL,NULL,'权限信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99020210,'990102',210,'可执行','tf_hasExec','Boolean',NULL,NULL,'权限信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99020220,'990102',220,'可审核','tf_hasAuditing','Boolean',NULL,NULL,'权限信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99020230,'990102',230,'可审批','tf_hasApprove','Boolean',NULL,NULL,'权限信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99020240,'990102',240,'可支付','tf_hasPayment','Boolean',NULL,NULL,'权限信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99020250,'990102',250,'有附件','tf_hasAttachment','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99020260,'990102',260,'可权限设置','tf_canLimit','Boolean',NULL,NULL,'权限信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'用户可对此模块设置权限，选定的才可以浏览与操作',NULL,NULL,NULL,NULL,NULL),(99020800,'990102',800,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99020810,'990102',92,'日期字段','tf_dateField','String',50,NULL,'属性字段信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'此模块的主要日期字段，比如说合同的签订日期，订单的销售日期等等。这个日期主要用在综合查询中，在当前模块是基准模块的前提下，设定了日期条件后会直接关联到这个日期字段上面',NULL,NULL,NULL,NULL,NULL),(99020820,'990102',270,'Excel导入','tf_allowInsertExcel','Boolean',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'是否可以通过Excel导入新增记录',NULL,NULL,NULL,NULL,NULL),(99020830,'990102',95,'季度字段','tf_seasonField','String',50,NULL,'属性字段信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99020840,'990102',94,'月度字段','tf_monthField','String',50,NULL,'属性字段信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99020850,'990102',96,'主页上顺序','tf_homePageTag','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99020860,'990102',280,'Excel修改','tf_allowEditExcel','Boolean',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'是否可以导出的Excel修改后再导入',NULL,NULL,NULL,NULL,NULL),(99020870,'990102',93,'年度字段','tf_yearfield','String',50,NULL,'属性字段信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99020880,'990102',91,'编码字段','tf_codeField','String',50,NULL,'属性字段信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'该模块的编码字段，例如对于合同来说就是：合同编号这个字段。',NULL,NULL,NULL,NULL,NULL),(99020890,'990102',82,'记录标题tpl','tf_titleTpl','String',99,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'当一个字段不能描述该记录时，使用titleTpl来进行描述。',NULL,NULL,NULL,NULL,NULL),(99020900,'990102',96,'文件字段','tf_fileField','String',50,NULL,'属性字段信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'此记录的可以存放文件信息的一个字段，对于模块来说，有专门的附件模块来处理上传的文件附件。这个功能可以用来做一些简单的文件操作，比如放置操作员的签名照片，可以在打印的时候打印此签名。有了这个字段以后，会在toolbar中加入相应的操作，来上传、下载、清除此文件字段中的内容。',NULL,NULL,NULL,NULL,NULL),(99020910,'990102',290,'可图表','tf_hasChart','Boolean',NULL,NULL,'权限信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'此模块是否具有简单的图表展示功能',NULL,NULL,NULL,NULL,NULL),(99020920,'990102',300,'系统模块','tf_isSystem','Boolean',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'如果是系统模块，用户没有浏览权限，就不把模块定义发送到前端',NULL,NULL,NULL,NULL,NULL),(99020930,'990102',310,'查询条件顺序号','tf_searchCondOrder','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'此模块放在综合查询的条件选择栏中的顺序',NULL,NULL,NULL,NULL,NULL),(99020940,'990102',97,'顺序号字段','tf_orderField','String',50,NULL,'属性字段信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'此模块中的记录的顺序号的字段，在对于顺序敏感的模块中需要使用，比如说菜单的顺序号用来排列各个顺序位置。',NULL,NULL,NULL,NULL,NULL),(99020950,'990102',85,'父键','tf_parentKey','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99020960,'990102',98,'顺序字段限定模块','tf_orderFieldControlModule','String',50,NULL,'属性字段信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'如果有orderField(),那么可以设置此字段为某个父模块的moduleName,用来表示在这个模块的导航，或者父模块的限定之下才可以在grid中拖动列的位置来修改 orderField的值。',NULL,NULL,NULL,NULL,NULL),(99020970,'990102',125,'图标字体','tf_glyph','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'模块的图标字体值，这是一个夭量的图标图形。',NULL,NULL,NULL,NULL,NULL),(99020980,'990102',157,'树形表','tf_isTreeModel','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'此模块是否是树形结构，如果是的话，需要设置tf_pidField',NULL,NULL,NULL,NULL,NULL),(99021000,'990102',292,'只用于查询','tf_isOnlyUsedSearch','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99021010,'990102',294,'无实体bean','tf_isNotNean','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99021020,'990102',121,'图标iconCls','tf_iconCls','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99021030,'990102',122,'图标文件','tf_iconFile','Blob',50,NULL,'附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99021040,'990102',252,'记录有图标','tf_hasRecordIcon','Boolean',NULL,NULL,'附加信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99040010,'990201',10,'ID号','tf_gridSchemeId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99040020,'990201',20,'模块','tf_Module','_Module',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,NULL,0x00,0x00,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99040030,'990201',30,'顺序号','tf_schemeOrder','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99040040,'990201',40,'方案名称','tf_schemeName','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99040050,'990201',50,'系统方案','tf_isSystemScheme','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99040060,'990201',60,'可编辑','tf_isAllowEditInGrid','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99040070,'990201',70,'双击操作','tf_dblClickAction','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99040080,'990201',80,'排序字段','tf_defaultSort','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99040090,'990201',90,'附加设置','tf_otherSetting','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99050010,'990202',10,'ID号','tf_gridGroupId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99050020,'990202',20,'模块列表方案','tf_ModuleGridScheme','_ModuleGridScheme',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,NULL,0x00,0x00,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99050030,'990202',30,'顺序号','tf_gridGroupOrder','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99050040,'990202',40,'分组名称','tf_gridGroupName','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99050050,'990202',50,'表头分组','tf_isShowHeaderSpans','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99050060,'990202',90,'其他设置','tf_otherSetting','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99050070,'990202',60,'锁定','tf_isLocked','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99060010,'990203',10,'ID号','tf_gridFieldId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99060020,'990203',20,'模块列表字段分组','tf_ModuleGridSchemeGroup','_ModuleGridSchemeGroup',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,NULL,0x00,0x00,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99060030,'990203',30,'顺序号','tf_gridFieldOrder','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99060040,'990203',40,'模块字段','tf_ModuleField','_ModuleField',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,NULL,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99060050,'990203',50,'列宽度','tf_columnWidth','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99060060,'990203',60,'锁定列','tf_isLocked','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99060070,'990203',70,'附加类型','tf_additionType','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99060080,'990203',80,'附加设置','tf_otherSetting','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99060090,'990203',45,'聚合类型','tf_aggregate','String',20,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99060100,'990203',90,'隐藏列','tf_ishidden','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99060110,'990203',100,'Excel不导出','tf_notExportExcel','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99070010,'990301',10,'ID号','tf_formSchemeId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99070020,'990301',20,'模块','tf_Module','_Module',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,NULL,0x00,0x00,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99070030,'990301',30,'顺序号','tf_schemeOrder','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99070040,'990301',40,'方案名称','tf_schemeName','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99070050,'990301',50,'系统方案','tf_isSystemScheme','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99070060,'990301',60,'窗口高','tf_windowHeight','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99070070,'990301',70,'窗口宽','tf_windowWidth','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99070080,'990301',80,'分栏数','tf_numCols','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99070090,'990301',90,'Form类型','tf_displayMode','String',20,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,16,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99070100,'990301',100,'附加设置','tf_otherSetting','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99080010,'990302',10,'ID号','tf_formGroupId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99080020,'990302',20,'模块Form方案','tf_ModuleFormScheme','_ModuleFormScheme',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99080030,'990302',30,'顺序号','tf_formGroupOrder','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99080040,'990302',40,'分组名称','tf_formGroupName','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99080050,'990302',50,'显示方式','tf_displayMode','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99080060,'990302',60,'分栏数','tf_numCols','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99080070,'990302',70,'审核组','tf_auditingGroup','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99080080,'990302',80,'审批组','tf_approveGroup','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99080090,'990302',90,'可折叠','tf_collapsible','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99080100,'990302',100,'默认折叠','tf_collapsed','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99080110,'990302',110,'子模块名称','tf_subModuleName','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'设置此项，则在此组中显示该子模块',NULL,NULL,NULL,NULL,NULL),(99080120,'990302',120,'其他设置','tf_otherSetting','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99080130,'990302',55,'表格Table','tf_isTable','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99080140,'990302',56,'每列宽度','tf_widths','String',100,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'设置每列的宽度，可以为像素或者百分比。以逗号分隔',NULL,NULL,NULL,NULL,NULL),(99080150,'990302',57,'分离label','tf_isSeparateLabel','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'将label和text放在二个单元格里',NULL,NULL,NULL,NULL,NULL),(99090010,'990303',10,'ID号','tf_formFieldId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99090020,'990303',20,'模块字段','tf_ModuleField','_ModuleField',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,NULL,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99090030,'990303',30,'模块Form字段分组','tf_ModuleFormSchemeGroup','_ModuleFormSchemeGroup',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,NULL,0x00,0x00,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99090040,'990303',40,'顺序号','tf_formFieldOrder','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99090050,'990303',50,'宽度','tf_width','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99090060,'990303',60,'栏数','tf_colspan','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99090070,'990303',70,'结束行','tf_isEndRow','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99090080,'990303',80,'附加设置','tf_otherSetting','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,NULL,0x00,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99090090,'990303',45,'聚合类型','tf_aggregate','String',20,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99090100,'990303',61,'行数','tf_rowspan','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99100010,'990204',10,'ID号','tf_navigatesetId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99100020,'990204',20,'模块','tf_Module','_Module',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x00,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99100030,'990204',30,'顺序号','tf_order','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99100040,'990204',40,'导航字段名','tf_fields','String',200,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'各字段之间用“;”号分隔',NULL,NULL,NULL,NULL,NULL),(99100050,'990204',50,'导航字段说明','tf_text','String',200,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99100060,'990204',60,'导航模式','tf_NumberGroup','_NumberGroup',0,NULL,'默认组','ManyToOne',NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99100070,'990204',70,'附加类型','tf_type','String',200,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99100080,'990204',80,'层级','tf_cascading','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'默认树是层级显示还是并排显示',NULL,NULL,NULL,NULL,NULL),(99100090,'990204',90,'倒序','tf_reverseOrder','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'显示的树是否倒序排列',NULL,NULL,NULL,NULL,NULL),(99100100,'990204',100,'可用','tf_enabled','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99110010,'990401',10,'ID号','tf_detailId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99110020,'990401',20,'系统模块','tf_Module','_Module',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99110030,'990401',30,'顺序号','tf_order','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99110040,'990401',40,'明细方案名称','tf_schemeName','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99110050,'990401',50,'系统方案','tf_isSystemScheme','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99120010,'990402',10,'ID号','tf_detailFieldId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99120020,'990402',20,'模块显示方案','tf_ModuleDetailScheme','_ModuleDetailScheme',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x00,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99120030,'990402',30,'顺序号','tf_order','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99120040,'990402',50,'模块字段','tf_ModuleField','_ModuleField',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x00,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99120050,'990402',60,'操作类型','tf_action','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99120060,'990402',70,'附加设置','tf_otherSetting','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99120070,'990402',45,'聚合类型','tf_aggregate','String',20,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99150010,'990501',10,'ID号','tf_chartId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99150020,'990501',20,'所属模块','tf_Module','_Module',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x00,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99150030,'990501',30,'顺序号','tf_order','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99150040,'990501',40,'图表方案名称','tf_name','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99150050,'990501',50,'系统方案','tf_isSystemScheme','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99150060,'990501',60,'图表类型','tf_chartType','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99150070,'990501',70,'配色方案','tf_colorScheme','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99150080,'990501',80,'项目内容','tf_categoryField','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99150090,'990501',90,'数值内容','tf_numericFields','String',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99150100,'990501',100,'显示数值','tf_isShowDetailNumber','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99150110,'990501',110,'显示提示信息','tf_isShowTips','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99150120,'990501',120,'动画展示','tf_isAnimate','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99150130,'990501',130,'定位线','tf_isGridLine','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99150140,'990501',140,'标题','tf_title','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99150150,'990501',190,'附加设置','tf_otherSetting','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99150160,'990501',200,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99150170,'990501',9000,'录入人员','tf_inputmen','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99150180,'990501',9010,'录入日期','tf_inputdate','Date',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99200010,'9920',10,'顺序号','tf_menuGroupId','String',10,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'formfield : {regex : /^[0-9]{2}$/,  regexText : \'请输入2位数字编码 !\'}  ','菜单分组按顺序号显示在菜单条上',NULL,NULL,NULL,NULL,NULL),(99200020,'9920',20,'分组名称','tf_title','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99200030,'9920',30,'展开','tf_expand','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'在树状菜单下默认是否展开',NULL,NULL,NULL,NULL,NULL),(99200040,'9920',40,'图标文件名','tf_iconURL','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'图标放置于/images/module/目录下',NULL,NULL,NULL,NULL,NULL),(99200050,'9920',50,'分组描述','tf_description','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99200060,'9920',60,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99210010,'9921',10,'ID号','tf_menuModuleId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99210020,'9921',20,'顺序号','tf_orderId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'按顺序号显示在菜单中',NULL,NULL,NULL,NULL,NULL),(99210030,'9921',30,'菜单分组','tf_MenuGroup','_MenuGroup',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,0x01,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99210040,'9921',40,'系统模块','tf_Module','_Module',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x00,0x00,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99210050,'9921',50,'父菜单标题','tf_parentMenu','String',20,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,0x01,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99210160,'9921',60,'分隔下一条','tf_addSeparator','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99210170,'9921',70,'菜单标题','tf_title','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99210180,'9921',80,'父模块约束设置','tf_parentFilter','String',0,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99300010,'990120',NULL,'序号','tf_moduleAdditionFunctionId','Integer',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99300020,'990120',NULL,'模块','tf_Module','_Module',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x00,0x01,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99300030,'990120',NULL,'功能名称','tf_title','String',20,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99300040,'990120',NULL,'功能Id号','tf_additionName','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99300050,'990120',NULL,'功能说明','tf_description','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99300060,'990120',NULL,'可用','tf_hasEnable','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99300070,'990120',NULL,'菜单名称','tf_menuName','String',20,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99300080,'990120',NULL,'图标名称','tf_icon','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99300090,'990120',NULL,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99300100,'990120',NULL,'需选中记录','tf_needRecord','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99300110,'990120',NULL,'有窗口操作','tf_showWindow','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99350010,'9935',10,'ID号','tf_id','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99350020,'9935',20,'所属模块','tf_Module','_Module',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x00,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99350030,'9935',30,'顺序号','tf_order','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99350040,'9935',40,'报表名称','tf_name','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99350050,'9935',50,'报表类型','tf_type','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99350060,'9935',60,'可用','tf_isEnable','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99350070,'9935',70,'选择年度','tf_isSelectYear','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99350080,'9935',80,'选择季度','tf_isSelectSeason','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99350090,'9935',90,'选择月份','tf_isSelectMonth','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99350100,'9935',100,'选择记录','tf_isSelectRecord','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99350110,'9935',180,'文件名','tf_filename','String',99,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99350120,'9935',190,'文件大小','tf_filesize','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99350130,'9935',200,'文件上传者','tf_author','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99350140,'9935',210,'上传时间','tf_uploadDate','Date',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99350150,'9935',220,'其他设置','tf_otherSetting','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99350160,'9935',800,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99350170,'9935',9000,'录入人员','tf_inputmen','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99350180,'9935',9010,'录入日期','tf_inputdate','Date',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99370010,'9937',10,'ID号','tf_id','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99370020,'9937',20,'所属模块','tf_Module','_Module',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x00,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99370030,'9937',30,'顺序号','tf_order','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99370040,'9937',40,'名称','tf_name','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99370050,'9937',50,'导入类型','tf_type','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99370060,'9937',60,'可用','tf_isEnable','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99370070,'9937',70,'选择年度','tf_isSelectYear','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99370080,'9937',80,'选择季度','tf_isSelectSeason','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99370090,'9937',90,'选择月份','tf_isSelectMonth','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99370100,'9937',100,'选择记录','tf_isSelectRecord','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99370110,'9937',180,'文件名','tf_filename','String',99,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99370120,'9937',190,'文件大小','tf_filesize','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99370130,'9937',200,'文件上传者','tf_author','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99370140,'9937',210,'上传时间','tf_uploadDate','Date',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99370150,'9937',220,'对应关系设置','tf_relation','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99370160,'9937',230,'其他设置','tf_otherSetting','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99370170,'9937',800,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99370180,'9937',9000,'录入人员','tf_inputmen','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99370190,'9937',9010,'录入日期','tf_inputdate','Date',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99410010,'9941',10,'ID号','tf_printSchemeId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99410020,'9941',20,'模块','tf_Module','_Module',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x00,0x00,0x01,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99410030,'9941',30,'顺序号','tf_schemeOrder','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99410040,'9941',40,'方案名称','tf_schemeName','String',50,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99410050,'9941',50,'系统方案','tf_isSystemScheme','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99410060,'9941',60,'子方案','tf_isSubScheme','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99410070,'9941',70,'创建人员','tf_createMen','String',20,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99410080,'9941',80,'创建日期','tf_createDate','Date',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99410090,'9941',90,'附加设置','tf_otherSetting','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99420010,'9942',10,'ID号','tf_printSchemeGroupId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99420020,'9942',20,'记录打印方案','tf_PrintScheme','_PrintScheme',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x00,0x00,0x01,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99420030,'9942',30,'分组顺序号','tf_printGroupOrder','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99420040,'9942',40,'分组名称','tf_printGroupName','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99420050,'9942',50,'本组宽度','tf_groupWidth','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99420060,'9942',60,'列数','tf_numCols','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99420070,'9942',70,'列宽','tf_widths','String',80,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'以逗号分隔各个列宽',NULL,NULL,NULL,NULL,NULL),(99420080,'9942',80,'边线宽度','tf_border','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99420090,'9942',90,'cellpadding','tf_cellpadding','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99420100,'9942',100,'css设置','tf_cssStyle','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99420190,'9942',190,'附加设置','tf_otherSetting','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99430010,'9943',10,'ID号','tf_cellId','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99430020,'9943',20,'记录打印方案分组','tf_PrintSchemeGroup','_PrintSchemeGroup',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x00,0x00,0x01,NULL,NULL,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99430030,'9943',30,'顺序号','tf_order','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99430040,'9943',40,'单元格名称','tf_name','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99430050,'9943',50,'打印内容','tf_printText','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99430060,'9943',60,'行高','tf_height','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99430070,'9943',70,'列宽','tf_width','String',10,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99430080,'9943',80,'合并列数','tf_colspan','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99430090,'9943',90,'合并行数','tf_rowspan','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99430100,'9943',100,'左右对齐方式','tf_align','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,12,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99430110,'9943',110,'上下对齐方式','tf_valign','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,13,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99430120,'9943',120,'css设置','tf_cssStyle','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99430130,'9943',130,'结束行','tf_isEndrow','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99430190,'9943',190,'附加设置','tf_otherSetting','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99430200,'9943',140,'禁用','tf_disabled','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(421165418,'901110',10,'部门编号','tf_allDepartmentId','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(421165428,'901110',20,'部门名称','tf_name','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(421165438,'901110',200,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(421265418,'901120',10,'管理部门编号','tf_mDepartmentId','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(421265428,'901120',20,'管理部门名称','tf_name','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(448165418,'903810',10,'序号','tf_filtRoleDetailId','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x01,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(448165428,'903810',20,'用户记录筛选角色','tf_DataFilterRole','_DataFilterRole',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(448165438,'903810',30,'所属模块','tf_Module','_Module',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(448165448,'903810',40,'筛选设置说明','tf_name','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(448165458,'903810',50,'记录主键集合','tf_recordIds','String',0,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(448165468,'903810',60,'记录名称集合','tf_recordNames','String',0,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095418,'990103',10,'ID号','tf_fieldId','Integer',NULL,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095428,'990103',20,'所属模块','tf_Module','_Module',NULL,NULL,'基本信息','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x00,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095438,'990103',30,'顺序号','tf_fieldOrder','Integer',NULL,NULL,'基本信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095448,'990103',40,'字段内容','tf_title','String',50,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095458,'990103',50,'字段名','tf_fieldName','String',50,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095468,'990103',60,'类型','tf_fieldType','String',50,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095478,'990103',70,'长度','tf_fieldLen','Integer',NULL,NULL,'基本信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095488,'990103',80,'小数位数','tf_digitsLen','Integer',NULL,NULL,'基本信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095498,'990103',90,'字段分组','tf_fieldGroup','String',50,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095508,'990103',100,'关联类型','tf_fieldRelation','String',20,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095518,'990103',110,'表字段实名','tf_DBfieldName','String',50,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'数据表中的实际字段名',NULL,NULL,NULL,NULL,NULL),(1311095528,'990103',120,'字段公式','tf_DBformula','String',NULL,NULL,'基本信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'公式字段的具体内容',NULL,NULL,NULL,NULL,NULL),(1311095538,'990103',130,'禁用','tf_isDisable','Boolean',NULL,NULL,'字段权限',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095548,'990103',140,'隐藏','tf_isHidden','Boolean',NULL,NULL,'字段权限',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095558,'990103',150,'可新增','tf_allowNew','Boolean',NULL,NULL,'字段权限',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095568,'990103',160,'可修改','tf_allowEdit','Boolean',NULL,NULL,'字段权限',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095578,'990103',190,'可分组','tf_allowGroup','Boolean',NULL,NULL,'设置信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095588,'990103',200,'可小计','tf_allowSummary','Boolean',NULL,NULL,'设置信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095598,'990103',210,'聚合','tf_allowAggregate','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095608,'990103',220,'可导航','tf_showNavigatorTree','Boolean',NULL,NULL,'设置信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'选中才可以在导航树中显示',NULL,NULL,NULL,NULL,NULL),(1311095618,'990103',230,'新增选中','tf_newNeedSelected','Boolean',NULL,NULL,'设置信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'在新增一条记录时，是否必须在导航树中选择此字段的值',NULL,NULL,NULL,NULL,NULL),(1311095628,'990103',240,'Excel导入','tf_allowInsertExcel','Boolean',NULL,NULL,'设置信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Excel导入新增时加入此字段可新增',NULL,NULL,NULL,NULL,NULL),(1311095638,'990103',250,'Excel修改','tf_allowEditExcel','Boolean',NULL,NULL,'设置信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Excel修改后再导入时此字段可更新',NULL,NULL,NULL,NULL,NULL),(1311095648,'990103',260,'图表项目','tf_isChartCategory','Boolean',NULL,NULL,'设置信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'此字段可以作为图表分析中的一个项目',NULL,NULL,NULL,NULL,NULL),(1311095658,'990103',270,'图表数据','tf_isChartNumeric','Boolean',NULL,NULL,'设置信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'此字段可以作为图表分析中的一个数据',NULL,NULL,NULL,NULL,NULL),(1311095668,'990103',280,'字段附件','tf_haveAttachment','Boolean',NULL,NULL,'设置信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'可以增加针对此字段的附件',NULL,NULL,NULL,NULL,NULL),(1311095678,'990103',290,'选择数值单位','tf_isMonetary','Boolean',NULL,NULL,'设置信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'可以选择千,万,百万,亿为单位',NULL,NULL,NULL,NULL,NULL),(1311095688,'990103',300,'必填','tf_isRequired','Boolean',NULL,NULL,'单字段验证',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095698,'990103',310,'最大值','tf_maxValue','Integer',NULL,NULL,'单字段验证',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095708,'990103',320,'最小值','tf_minValue','Integer',NULL,NULL,'单字段验证',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095718,'990103',330,'正则验正表达式','tf_regexValue','String',NULL,NULL,'单字段验证',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095728,'990103',340,'vtype类型','tf_vtype','String',50,NULL,'单字段验证',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095738,'990103',350,'js验证代码','tf_jsValue','String',NULL,NULL,'单字段验证',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095748,'990103',400,'计量单位','tf_unitText','String',50,NULL,'附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095758,'990103',410,'缺省值','tf_defaultValue','String',50,NULL,'附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095768,'990103',420,'字段列表属性','tf_PropertyType','_PropertyType',NULL,NULL,'附加信息','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095778,'990103',430,'字段列表值','tf_propertyValue','String',NULL,NULL,'附加信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095788,'990103',440,'百分比分子','tf_divisor','String',50,NULL,'附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095798,'990103',450,'百分比分母','tf_denominator','String',50,NULL,'附加信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095808,'990103',460,'提示信息定义','tf_tooltipTpl','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'column中显示在此字段值上的提示信息,是tpl表达式',NULL,NULL,NULL,NULL,NULL),(1311095818,'990103',470,'其他设置','tf_otherSetting','String',NULL,NULL,'附加信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095828,'990103',990,'备注','tf_remark','String',NULL,NULL,'附加信息',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095838,'990103',800,'model设置语句','tf_modelSet','String',NULL,NULL,'关联设置',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095848,'990103',810,'grid字段设置','tf_gridColumnSet','String',NULL,NULL,'关联设置',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095858,'990103',820,'form字段设置','tf_formFieldSet','String',NULL,NULL,'关联设置',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095868,'990103',830,'report字段设置','tf_reportSet','String',NULL,NULL,'关联设置',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1311095878,'990103',102,'关联表名','tf_joinTable','String',50,NULL,'基本信息',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ManyToMany中的关联表名',NULL,NULL,NULL,NULL,NULL),(1313165418,'990110',10,'ID号','tf_id','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1313165428,'990110',20,'所属模块','tf_Module','_Module',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1313165438,'990110',30,'顺序号','tf_order','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1313165448,'990110',40,'平衡关系描述','tf_name','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1313165458,'990110',50,'是否可用','tf_isEnable','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1313165468,'990110',60,'平衡关系级别','tf_Level','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1313165478,'990110',70,'平衡关系表达式','tf_express','String',250,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1313165488,'990110',80,'错误提示信息','tf_errorMessage','String',250,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1313165498,'990110',90,'显示信息字段','tf_errorMessageField','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'错误信息绑定的字段',NULL,NULL,NULL,NULL,NULL),(1313165508,'990110',200,'备注','tf_remark','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1313165518,'990110',9000,'录入人员','tf_inputmen','String',10,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1313165528,'990110',9010,'录入日期','tf_inputdate','Date',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1313265418,'990115',10,'ID号','tf_Id','Integer',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1313265428,'990115',20,'所属模块','tf_Module','_Module',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1313265438,'990115',30,'顺序号','tf_order','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1313265448,'990115',40,'子模块标识','tf_subMoudleName','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1313265458,'990115',60,'窗口中打开','tf_openInWindow','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1313265468,'990115',50,'子模块名称','tf_subModuleTitle','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x00,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1313265478,'990115',70,'更多菜单下','tf_inSubMenu','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1331365418,'990130',10,'ID号','tf_id','String',10,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1331365428,'990130',20,'父节点号','tf_pid','String',10,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x01,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1331365438,'990130',30,'菜单内容','tf_title','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1331365448,'990130',40,'显示内容','tf_displayTitle','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1331365458,'990130',50,'图标文件名','tf_iconUrl','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'图标放置于/images/module/目录下',NULL,NULL,NULL,NULL,NULL),(1331365478,'990130',70,'顺序号','tf_orderId','Integer',NULL,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'按顺序号显示在菜单中',NULL,NULL,NULL,NULL,NULL),(1331365488,'990130',80,'系统模块','tf_Module','_Module',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1331365498,'990130',90,'查询分组','tf_ReportGroup','_ReportGroup',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1331365508,'990130',100,'查询','tf_Report','_Report',NULL,NULL,'默认组','ManyToOne',NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1331365518,'990130',110,'函数名称','tf_functionName','String',90,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1331365528,'990130',120,'窗口名称','tf_windowName','String',90,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1331365538,'990130',130,'执行语句','tf_execStatument','String',90,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1331365548,'990130',140,'附加参数','tf_parameter','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1331365558,'990130',150,'父模块约束值','tf_parentFilter','String',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1331365568,'990130',160,'默认展开','tf_expand','Boolean',NULL,NULL,'默认组',NULL,NULL,NULL,NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1331365578,'990130',60,'图标样式','tf_iconCls','String',50,NULL,'默认组',NULL,NULL,'',NULL,NULL,0x00,0x01,0x01,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'图标的iconCls值',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `_ModuleField` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ModuleFieldConstraint`
--

DROP TABLE IF EXISTS `_ModuleFieldConstraint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ModuleFieldConstraint` (
  `tf_id` int(11) NOT NULL,
  `tf_inputdate` datetime NOT NULL,
  `tf_inputmen` varchar(10) NOT NULL,
  `tf_Level` varchar(10) DEFAULT NULL,
  `tf_errorMessage` varchar(250) DEFAULT NULL,
  `tf_errorMessageField` varchar(50) DEFAULT NULL,
  `tf_express` varchar(250) NOT NULL,
  `tf_isEnable` bit(1) DEFAULT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_order` int(11) NOT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_moduleId` varchar(10) NOT NULL,
  PRIMARY KEY (`tf_id`),
  KEY `FK6q8tqyqwx82a021fub71xc8ij` (`tf_moduleId`),
  CONSTRAINT `FK6q8tqyqwx82a021fub71xc8ij` FOREIGN KEY (`tf_moduleId`) REFERENCES `_Module` (`tf_moduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ModuleFieldConstraint`
--

LOCK TABLES `_ModuleFieldConstraint` WRITE;
/*!40000 ALTER TABLE `_ModuleFieldConstraint` DISABLE KEYS */;
/*!40000 ALTER TABLE `_ModuleFieldConstraint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ModuleFormScheme`
--

DROP TABLE IF EXISTS `_ModuleFormScheme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ModuleFormScheme` (
  `tf_formSchemeId` int(11) NOT NULL,
  `tf_displayMode` varchar(255) DEFAULT NULL,
  `tf_isSystemScheme` bit(1) DEFAULT NULL,
  `tf_numCols` int(11) DEFAULT NULL,
  `tf_otherSetting` varchar(255) DEFAULT NULL,
  `tf_schemeName` varchar(50) NOT NULL,
  `tf_schemeOrder` int(11) NOT NULL,
  `tf_windowHeight` int(11) DEFAULT NULL,
  `tf_windowWidth` int(11) DEFAULT NULL,
  `tf_moduleId` varchar(10) NOT NULL,
  PRIMARY KEY (`tf_formSchemeId`),
  KEY `FKj8x3ey0lth9kh39b8cxslxu5e` (`tf_moduleId`),
  CONSTRAINT `FKsd0day3ipeh2b4nwcgfu9` FOREIGN KEY (`tf_moduleId`) REFERENCES `_Module` (`tf_moduleId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ModuleFormScheme`
--

LOCK TABLES `_ModuleFormScheme` WRITE;
/*!40000 ALTER TABLE `_ModuleFormScheme` DISABLE KEYS */;
INSERT INTO `_ModuleFormScheme` VALUES (1,'tab',NULL,1,NULL,'菜单分组',1,-1,400,'9920'),(2,'tab',NULL,1,NULL,'系统菜单',1,-1,500,'9921'),(3,'tab',NULL,1,NULL,'新增的Form方案',1,-1,400,'990101'),(4,'accordion',NULL,2,NULL,'新增的Form方案',1,-1,600,'990102'),(5,'accordion',NULL,2,NULL,'模块字段Form方案',1,-1,900,'990103'),(6,NULL,NULL,1,NULL,'新增的Form方案',1,-1,450,'990301'),(7,'tab',NULL,2,NULL,'新增的Form方案',1,-1,500,'990302'),(8,NULL,NULL,2,NULL,'新增的Form方案',1,-1,500,'990303'),(9,'tab',NULL,1,NULL,'模块列表Form方案',1,-1,500,'990201'),(10,'tab',NULL,1,NULL,'新增的Form方案',1,-1,400,'990202'),(11,NULL,NULL,1,NULL,'新增的Form方案',1,-1,450,'990203'),(12,NULL,NULL,1,NULL,'附件类型Form',1,-1,400,'9502'),(14,NULL,NULL,1,NULL,'附件文件类型Form',1,-1,400,'9503'),(15,'accordion',NULL,2,NULL,'附件Form',1,-1,620,'9505'),(16,'none',NULL,1,NULL,'部门权限Form',1,-1,400,'9010'),(17,'accordion',NULL,1,NULL,'部门设置Form',1,-1,500,'9011'),(18,'tab',0x01,1,NULL,'字段列表属性Form',1,-1,600,'8090'),(19,'accordion',NULL,2,NULL,'系统参数设置Form',1,-1,500,'9000'),(20,NULL,0x01,1,NULL,'职务Form',1,-1,400,'9030'),(21,NULL,NULL,2,NULL,'用户管理Form',1,-1,500,'9035'),(22,'none',NULL,1,NULL,'用户角色分组Form',1,-1,400,'9040'),(23,'none',NULL,1,NULL,'用户角色Form',1,-1,400,'9041'),(25,NULL,NULL,2,NULL,'模块附加功能Form',1,-1,500,'990120'),(26,NULL,0x01,1,NULL,'资料分类Form',1,-1,400,'9401'),(27,NULL,NULL,NULL,NULL,'文件资料Form',1,-1,500,'9405'),(28,NULL,NULL,2,NULL,'数据备份Form',1,-1,500,'9090'),(38,NULL,NULL,2,NULL,'运行参数设置Form',1,-1,600,'9005'),(39,NULL,NULL,2,NULL,'操作日志Form',1,-1,480,'9091'),(40,NULL,NULL,2,NULL,'模块导航字段Form',1,-1,550,'990204'),(41,NULL,NULL,1,NULL,'模块审批设置Form',1,-1,450,'9045'),(42,NULL,NULL,1,NULL,'模块审批人员Form',1,-1,450,'9046'),(43,NULL,NULL,1,NULL,'数值分组Form',1,-1,450,'8091'),(44,NULL,NULL,1,NULL,'数值分组明细Form',1,-1,450,'8092'),(46,NULL,NULL,2,NULL,'登录日志Form',1,-1,550,'9092'),(47,NULL,NULL,1,NULL,'图片压缩模式Form',1,-1,450,'9504'),(67,NULL,NULL,2,NULL,'附加参数设置Form',1,-1,600,'9001'),(78,NULL,NULL,2,NULL,'记录打印方案Form',1,-1,600,'9941'),(79,NULL,NULL,2,NULL,'记录打印方案分组Form',1,-1,600,'9942'),(80,NULL,NULL,2,NULL,'记录打印方案分组字段Form',1,-1,600,'9943'),(81,NULL,NULL,1,NULL,'模块明细显示分组Form',1,-1,450,'990401'),(82,NULL,NULL,1,NULL,'模块明细显示字段Form',1,-1,500,'990402'),(83,NULL,NULL,2,NULL,'模块Excel报表Form',1,-1,600,'9935'),(84,NULL,NULL,2,NULL,'模块Excel单记录导入Form',1,-1,600,'9937'),(85,NULL,NULL,2,NULL,'模块图表方案Form',1,-1,600,'990501'),(86,NULL,NULL,2,NULL,'模块字段平衡关系Form',1,-1,600,'990110'),(87,NULL,NULL,1,NULL,'综合查询分组Form',1,-1,500,'9050'),(88,NULL,NULL,2,NULL,'综合查询Form',1,-1,600,'9052'),(96,NULL,NULL,2,NULL,'综合查询图表方案Form',1,-1,600,'9055'),(98,NULL,NULL,1,NULL,'子模块按钮方案Form',1,-1,450,'990115'),(104,NULL,NULL,1,NULL,'用户角色分配Form',1,-1,400,'9042'),(114,NULL,NULL,2,NULL,'管理部门Form',1,-1,600,'901110'),(115,NULL,NULL,1,NULL,'用户附加部门Form',1,-1,400,'9043'),(116,NULL,NULL,1,NULL,'用户记录筛选角色Form',1,-1,400,'9038'),(117,NULL,NULL,1,NULL,'用户记录筛选设置明细Form',1,-1,600,'903810'),(118,NULL,NULL,1,NULL,'用户记录筛选角色分配Form',1,-1,400,'9039'),(119,NULL,NULL,2,NULL,'管理部门Form',1,-1,600,'901120'),(120,NULL,NULL,2,NULL,'菜单Form',1,-1,700,'990130'),(121,NULL,NULL,2,NULL,'省份Form',1,-1,900,'7010'),(122,NULL,NULL,2,NULL,'附件对应字段Form',1,-1,600,'9506'),(123,NULL,NULL,2,NULL,'市Form',1,-1,600,'7012'),(124,NULL,NULL,2,NULL,'行业Form',1,-1,600,'7016'),(125,NULL,NULL,2,NULL,'客户等级Form',1,-1,600,'7014'),(126,NULL,NULL,2,NULL,'客户单位Form',1,-1,600,'6010'),(127,NULL,NULL,2,NULL,'商品类别Form',1,-1,600,'7018'),(128,NULL,NULL,2,NULL,'商品Form',1,-1,600,'6030'),(129,NULL,NULL,2,NULL,'业务员Form',1,-1,600,'6020'),(130,NULL,NULL,2,NULL,'订单Form',1,-1,600,'6040'),(131,NULL,NULL,2,NULL,'订单明细Form',1,-1,600,'6050'),(133,NULL,NULL,2,NULL,'商品仓库Form',1,-1,600,'7020');
/*!40000 ALTER TABLE `_ModuleFormScheme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ModuleFormSchemeGroup`
--

DROP TABLE IF EXISTS `_ModuleFormSchemeGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ModuleFormSchemeGroup` (
  `tf_formGroupId` int(11) NOT NULL,
  `tf_approveGroup` bit(1) DEFAULT NULL,
  `tf_auditingGroup` bit(1) DEFAULT NULL,
  `tf_collapsed` bit(1) DEFAULT NULL,
  `tf_collapsible` bit(1) DEFAULT NULL,
  `tf_displayMode` varchar(50) DEFAULT NULL,
  `tf_formGroupName` varchar(50) NOT NULL,
  `tf_formGroupOrder` int(11) NOT NULL,
  `tf_numCols` int(11) DEFAULT NULL,
  `tf_otherSetting` varchar(255) DEFAULT NULL,
  `tf_subModuleName` varchar(50) DEFAULT NULL,
  `tf_formSchemeId` int(11) NOT NULL,
  `tf_isTable` bit(1) DEFAULT NULL,
  `tf_widths` varchar(100) DEFAULT NULL,
  `tf_isSeparateLabel` bit(1) DEFAULT NULL,
  PRIMARY KEY (`tf_formGroupId`),
  KEY `FKg8hdgor36hx0x4px7lfxbdcev` (`tf_formSchemeId`),
  CONSTRAINT `FKg8hdgor36hx0x4px7lfxbdcev` FOREIGN KEY (`tf_formSchemeId`) REFERENCES `_ModuleFormScheme` (`tf_formSchemeId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ModuleFormSchemeGroup`
--

LOCK TABLES `_ModuleFormSchemeGroup` WRITE;
/*!40000 ALTER TABLE `_ModuleFormSchemeGroup` DISABLE KEYS */;
INSERT INTO `_ModuleFormSchemeGroup` VALUES (1,NULL,NULL,NULL,NULL,NULL,'菜单分组',1,1,NULL,NULL,1,NULL,NULL,NULL),(2,NULL,NULL,NULL,NULL,'accordion','所有字段',1,NULL,NULL,NULL,2,NULL,NULL,NULL),(3,NULL,NULL,NULL,NULL,NULL,'所有字段',1,NULL,NULL,NULL,3,NULL,NULL,NULL),(4,NULL,NULL,NULL,NULL,'tab','基本信息',10,NULL,NULL,NULL,4,NULL,NULL,NULL),(5,NULL,NULL,0x00,0x00,NULL,'基本信息',10,3,NULL,NULL,5,NULL,NULL,NULL),(6,NULL,NULL,NULL,NULL,NULL,'所有字段',1,NULL,NULL,NULL,6,NULL,NULL,NULL),(7,NULL,NULL,NULL,NULL,NULL,'所有字段',1,NULL,NULL,NULL,7,NULL,NULL,NULL),(8,NULL,NULL,NULL,NULL,NULL,'所有字段',1,2,NULL,NULL,8,NULL,NULL,NULL),(9,NULL,NULL,0x00,NULL,NULL,'所有字段',1,NULL,NULL,NULL,9,0x01,NULL,NULL),(10,NULL,NULL,0x00,NULL,NULL,'所有字段',1,NULL,NULL,NULL,10,NULL,NULL,NULL),(11,NULL,NULL,NULL,NULL,NULL,'所有字段',1,NULL,NULL,NULL,11,NULL,NULL,NULL),(12,NULL,NULL,NULL,NULL,NULL,'附件类型',1,NULL,NULL,NULL,12,NULL,NULL,NULL),(14,NULL,NULL,NULL,NULL,NULL,'附件文件类型',1,NULL,NULL,NULL,14,NULL,NULL,NULL),(15,NULL,NULL,NULL,NULL,NULL,'基本信息',10,NULL,NULL,NULL,15,NULL,NULL,NULL),(16,NULL,NULL,NULL,NULL,NULL,'部门权限',1,NULL,NULL,NULL,16,NULL,NULL,NULL),(17,NULL,NULL,NULL,NULL,NULL,'部门设置',10,NULL,NULL,NULL,17,NULL,NULL,NULL),(18,NULL,NULL,NULL,NULL,'accordion','字段列表属性',1,NULL,NULL,NULL,18,NULL,NULL,NULL),(20,NULL,NULL,0x00,0x00,'tab','附加信息',20,NULL,NULL,NULL,4,NULL,NULL,NULL),(22,NULL,NULL,0x00,0x00,'tab','权限信息',30,NULL,NULL,NULL,4,NULL,NULL,NULL),(23,NULL,NULL,0x00,0x00,NULL,'字段权限',20,4,NULL,NULL,5,NULL,NULL,NULL),(24,NULL,NULL,0x00,0x00,NULL,'文件信息',20,NULL,NULL,NULL,15,NULL,NULL,NULL),(25,NULL,NULL,0x00,0x00,NULL,'权限设置',20,3,NULL,NULL,17,NULL,NULL,NULL),(26,NULL,NULL,0x00,0x00,'tab','其他设置',30,NULL,NULL,NULL,17,NULL,NULL,NULL),(27,NULL,NULL,NULL,NULL,'tab','使用单位情况',10,NULL,NULL,NULL,19,NULL,NULL,NULL),(28,NULL,NULL,NULL,NULL,NULL,'职务',1,NULL,NULL,NULL,20,NULL,NULL,NULL),(29,NULL,NULL,NULL,NULL,NULL,'用户管理',1,NULL,NULL,NULL,21,NULL,NULL,NULL),(30,NULL,NULL,0x00,0x00,'tab','服务单位情况',20,NULL,NULL,NULL,19,NULL,NULL,NULL),(31,NULL,NULL,NULL,NULL,NULL,'用户角色分组',1,NULL,NULL,NULL,22,NULL,NULL,NULL),(32,NULL,NULL,NULL,NULL,NULL,'用户角色',1,NULL,NULL,NULL,23,NULL,NULL,NULL),(34,NULL,NULL,NULL,NULL,NULL,'模块附加功能',1,NULL,NULL,NULL,25,NULL,NULL,NULL),(36,NULL,NULL,NULL,NULL,'none','资料分类',1,1,NULL,NULL,26,NULL,NULL,NULL),(37,NULL,NULL,NULL,NULL,NULL,'文件资料',1,NULL,NULL,NULL,27,NULL,NULL,NULL),(39,NULL,NULL,NULL,NULL,NULL,'数据备份',1,NULL,NULL,NULL,28,NULL,NULL,NULL),(54,NULL,NULL,NULL,NULL,NULL,'运行参数设置',1,2,NULL,NULL,38,NULL,NULL,NULL),(55,NULL,NULL,NULL,NULL,NULL,'操作日志',1,NULL,NULL,NULL,39,NULL,NULL,NULL),(56,NULL,NULL,NULL,NULL,NULL,'模块导航字段',1,NULL,NULL,NULL,40,NULL,NULL,NULL),(57,NULL,NULL,NULL,NULL,NULL,'模块审批设置',1,NULL,NULL,NULL,41,NULL,NULL,NULL),(58,NULL,NULL,NULL,NULL,NULL,'模块审批人员',1,NULL,NULL,NULL,42,NULL,NULL,NULL),(59,NULL,NULL,NULL,NULL,NULL,'数值分组',1,NULL,NULL,NULL,43,NULL,NULL,NULL),(60,NULL,NULL,NULL,NULL,NULL,'数值分组明细',1,NULL,NULL,NULL,44,NULL,NULL,NULL),(70,NULL,NULL,NULL,NULL,NULL,'登录日志',1,NULL,NULL,NULL,46,NULL,NULL,NULL),(71,NULL,NULL,NULL,NULL,NULL,'图片压缩模式',1,NULL,NULL,NULL,47,NULL,NULL,NULL),(111,NULL,NULL,NULL,NULL,NULL,'附加参数设置',10,NULL,NULL,NULL,67,NULL,NULL,NULL),(118,0x00,0x00,0x00,0x00,NULL,'合同默认付款比例',20,0,NULL,NULL,67,NULL,NULL,NULL),(130,0x00,0x00,0x00,0x01,'none','菜单明细',2,0,NULL,'_MenuModule',1,NULL,NULL,NULL),(131,NULL,NULL,NULL,NULL,NULL,'记录打印方案',1,NULL,NULL,NULL,78,NULL,NULL,NULL),(132,NULL,NULL,NULL,NULL,NULL,'记录打印方案分组',1,NULL,NULL,NULL,79,NULL,NULL,NULL),(133,NULL,NULL,NULL,NULL,NULL,'记录打印方案分组字段',1,NULL,NULL,NULL,80,NULL,NULL,NULL),(134,NULL,NULL,NULL,NULL,NULL,'模块明细显示分组',1,NULL,NULL,NULL,81,NULL,NULL,NULL),(135,NULL,NULL,NULL,NULL,NULL,'模块明细显示字段',1,NULL,NULL,NULL,82,NULL,NULL,NULL),(137,NULL,NULL,NULL,NULL,NULL,'模块Excel报表',1,NULL,NULL,NULL,83,NULL,NULL,NULL),(138,NULL,NULL,NULL,NULL,NULL,'模块Excel单记录导入',1,NULL,NULL,NULL,84,NULL,NULL,NULL),(139,NULL,NULL,NULL,NULL,NULL,'模块图表方案',1,NULL,NULL,NULL,85,NULL,NULL,NULL),(140,NULL,NULL,NULL,NULL,NULL,'模块字段平衡关系',1,NULL,NULL,NULL,86,NULL,NULL,NULL),(141,NULL,NULL,NULL,NULL,NULL,'综合查询分组',1,NULL,NULL,NULL,87,NULL,NULL,NULL),(142,NULL,NULL,NULL,NULL,NULL,'综合查询',1,NULL,NULL,NULL,88,NULL,NULL,NULL),(167,NULL,NULL,NULL,NULL,NULL,'综合查询图表方案',1,NULL,NULL,NULL,96,NULL,NULL,NULL),(170,NULL,NULL,NULL,NULL,NULL,'子模块按钮方案',1,NULL,NULL,NULL,98,NULL,NULL,NULL),(176,NULL,NULL,NULL,NULL,NULL,'用户角色分配',1,NULL,NULL,NULL,104,NULL,NULL,NULL),(221,NULL,NULL,NULL,NULL,NULL,'管理部门',1,NULL,NULL,NULL,114,NULL,NULL,NULL),(222,NULL,NULL,NULL,NULL,NULL,'用户附加部门',1,NULL,NULL,NULL,115,NULL,NULL,NULL),(223,NULL,NULL,NULL,NULL,NULL,'用户记录筛选角色',1,NULL,NULL,NULL,116,NULL,NULL,NULL),(224,NULL,NULL,NULL,NULL,NULL,'用户记录筛选设置明细',1,NULL,NULL,NULL,117,NULL,NULL,NULL),(225,NULL,NULL,NULL,NULL,NULL,'用户记录筛选角色分配',1,NULL,NULL,NULL,118,NULL,NULL,NULL),(227,NULL,NULL,NULL,NULL,NULL,'管理部门',1,NULL,NULL,NULL,119,NULL,NULL,NULL),(228,NULL,NULL,NULL,NULL,NULL,'菜单',1,NULL,NULL,NULL,120,NULL,NULL,NULL),(229,NULL,NULL,NULL,NULL,NULL,'省份',1,3,NULL,NULL,121,0x01,'20%,30%,20%,30%,50px,100px',0x01),(230,NULL,NULL,NULL,NULL,NULL,'附件对应字段',1,NULL,NULL,NULL,122,NULL,NULL,NULL),(231,0x00,0x01,0x00,0x01,NULL,'省份',2,2,NULL,NULL,121,0x01,'30%,50%,20%',0x01),(232,NULL,NULL,NULL,NULL,NULL,'市',1,NULL,NULL,NULL,123,NULL,NULL,NULL),(233,0x01,NULL,NULL,0x01,NULL,'第1级审批',11,3,NULL,NULL,123,NULL,NULL,NULL),(234,0x01,NULL,NULL,0x01,NULL,'第2级审批',12,3,NULL,NULL,123,NULL,NULL,NULL),(235,0x01,NULL,NULL,0x01,NULL,'第3级审批',13,3,NULL,NULL,123,NULL,NULL,NULL),(236,0x01,NULL,NULL,0x01,NULL,'第4级审批',14,3,NULL,NULL,123,NULL,NULL,NULL),(237,0x01,NULL,NULL,0x01,NULL,'第5级审批',15,3,NULL,NULL,123,NULL,NULL,NULL),(238,0x01,NULL,NULL,0x01,NULL,'第6级审批',16,3,NULL,NULL,123,NULL,NULL,NULL),(239,0x00,0x00,0x00,0x00,NULL,'设置信息',30,4,NULL,NULL,5,0x00,NULL,0x00),(240,0x00,0x00,0x00,0x00,NULL,'单字段验证',40,4,NULL,NULL,5,0x00,NULL,0x00),(241,0x00,0x00,0x00,0x00,'tab','附加信息',50,4,NULL,NULL,5,0x00,NULL,0x00),(242,0x00,0x00,0x00,0x00,'none','关联设置',60,1,NULL,NULL,5,0x00,NULL,0x00),(243,NULL,NULL,NULL,NULL,NULL,'行业',1,NULL,NULL,NULL,124,NULL,NULL,NULL),(244,NULL,NULL,NULL,NULL,NULL,'客户等级',1,NULL,NULL,NULL,125,NULL,NULL,NULL),(245,NULL,NULL,NULL,NULL,NULL,'客户单位',1,NULL,NULL,NULL,126,NULL,NULL,NULL),(246,NULL,NULL,NULL,NULL,NULL,'商品类别',1,NULL,NULL,NULL,127,NULL,NULL,NULL),(247,NULL,NULL,NULL,NULL,NULL,'商品',1,NULL,NULL,NULL,128,NULL,NULL,NULL),(248,NULL,NULL,NULL,NULL,NULL,'业务员',1,NULL,NULL,NULL,129,NULL,NULL,NULL),(249,NULL,NULL,NULL,NULL,NULL,'订单',1,NULL,NULL,NULL,130,NULL,NULL,NULL),(250,NULL,NULL,NULL,NULL,NULL,'订单明细',1,NULL,NULL,NULL,131,NULL,NULL,NULL),(252,NULL,NULL,NULL,NULL,NULL,'商品仓库',1,NULL,NULL,NULL,133,NULL,NULL,NULL);
/*!40000 ALTER TABLE `_ModuleFormSchemeGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ModuleFormSchemeGroupField`
--

DROP TABLE IF EXISTS `_ModuleFormSchemeGroupField`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ModuleFormSchemeGroupField` (
  `tf_formFieldId` int(11) NOT NULL,
  `tf_aggregate` varchar(20) DEFAULT NULL,
  `tf_colspan` int(11) DEFAULT NULL,
  `tf_fieldId` int(11) DEFAULT NULL,
  `tf_formFieldOrder` int(11) NOT NULL,
  `tf_isEndRow` bit(1) DEFAULT NULL,
  `tf_otherSetting` varchar(255) DEFAULT NULL,
  `tf_width` int(11) DEFAULT NULL,
  `tf_formGroupId` int(11) NOT NULL,
  `tf_rowspan` int(11) DEFAULT NULL,
  PRIMARY KEY (`tf_formFieldId`),
  KEY `FKpnlu3dx5n0ljq0pfefn5gfedo` (`tf_fieldId`),
  KEY `FKj5eaph8vsqnc8kgfkl20c16ii` (`tf_formGroupId`),
  CONSTRAINT `FKj5eaph8vsqnc8kgfkl20c16ii` FOREIGN KEY (`tf_formGroupId`) REFERENCES `_ModuleFormSchemeGroup` (`tf_formGroupId`) ON DELETE CASCADE,
  CONSTRAINT `FKpnlu3dx5n0ljq0pfefn5gfedo` FOREIGN KEY (`tf_fieldId`) REFERENCES `_ModuleField` (`tf_fieldId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ModuleFormSchemeGroupField`
--

LOCK TABLES `_ModuleFormSchemeGroupField` WRITE;
/*!40000 ALTER TABLE `_ModuleFormSchemeGroupField` DISABLE KEYS */;
INSERT INTO `_ModuleFormSchemeGroupField` VALUES (10,NULL,NULL,99210010,10,0x01,NULL,NULL,2,NULL),(11,NULL,NULL,99210030,20,NULL,NULL,-1,2,NULL),(13,NULL,NULL,99210020,40,NULL,NULL,40,2,NULL),(14,NULL,NULL,99210040,30,0x01,NULL,-1,2,NULL),(15,NULL,NULL,99010050,60,NULL,NULL,-1,3,NULL),(16,NULL,NULL,99010030,40,NULL,NULL,-1,3,NULL),(17,NULL,NULL,99010020,20,NULL,NULL,-1,3,NULL),(18,NULL,NULL,99010010,10,NULL,NULL,0,3,NULL),(19,NULL,NULL,99010040,50,NULL,NULL,-1,3,NULL),(28,NULL,NULL,99020010,10,NULL,NULL,60,4,NULL),(29,NULL,2,99020030,35,0x01,NULL,-1,4,NULL),(30,NULL,NULL,99020020,20,NULL,NULL,-1,4,NULL),(31,NULL,NULL,99020050,40,NULL,NULL,-1,4,NULL),(33,NULL,2,99020040,30,0x01,NULL,-1,4,NULL),(37,NULL,NULL,99020060,60,0x01,NULL,-1,4,NULL),(58,NULL,NULL,99070060,120,NULL,NULL,NULL,6,NULL),(59,NULL,NULL,99070070,130,NULL,NULL,NULL,6,NULL),(60,NULL,NULL,99070040,20,NULL,NULL,NULL,6,NULL),(61,NULL,NULL,99070080,50,NULL,NULL,NULL,6,NULL),(62,NULL,NULL,99070100,160,NULL,NULL,-1,6,NULL),(63,NULL,NULL,99070020,10,NULL,NULL,NULL,6,NULL),(64,NULL,NULL,99070030,30,NULL,NULL,NULL,6,NULL),(65,NULL,NULL,99070050,90,NULL,NULL,NULL,6,NULL),(66,NULL,NULL,99070090,100,NULL,NULL,NULL,6,NULL),(68,NULL,2,99080040,20,NULL,NULL,-1,7,NULL),(69,NULL,2,99080020,10,NULL,NULL,-1,7,NULL),(70,NULL,2,99080120,140,NULL,NULL,-1,7,NULL),(71,NULL,NULL,99080030,30,NULL,NULL,NULL,7,NULL),(72,NULL,NULL,99080050,90,NULL,NULL,NULL,7,NULL),(79,NULL,2,99090080,90,NULL,NULL,-1,8,NULL),(81,NULL,NULL,99090070,70,NULL,NULL,NULL,8,NULL),(85,NULL,NULL,99090050,40,NULL,NULL,NULL,8,NULL),(86,NULL,NULL,99090060,50,NULL,NULL,NULL,8,NULL),(87,NULL,2,99090030,20,NULL,NULL,-1,8,NULL),(88,NULL,2,99090020,10,0x01,NULL,-1,8,NULL),(89,NULL,NULL,99090040,30,NULL,NULL,NULL,8,NULL),(94,NULL,NULL,99040040,20,NULL,NULL,NULL,9,NULL),(95,NULL,NULL,99040090,40,NULL,NULL,-1,9,NULL),(96,NULL,NULL,99040060,50,NULL,NULL,NULL,9,NULL),(97,NULL,NULL,99040020,10,NULL,NULL,NULL,9,NULL),(98,NULL,NULL,99040080,70,NULL,NULL,NULL,9,NULL),(99,NULL,NULL,99040070,80,NULL,NULL,NULL,9,NULL),(100,NULL,NULL,99040030,30,NULL,NULL,NULL,9,NULL),(101,NULL,NULL,99040050,100,NULL,NULL,NULL,9,NULL),(103,NULL,NULL,99050040,20,NULL,NULL,NULL,10,NULL),(104,NULL,NULL,99050020,10,NULL,NULL,NULL,10,NULL),(105,NULL,NULL,99050060,90,NULL,NULL,-1,10,NULL),(106,NULL,NULL,99050030,30,NULL,NULL,NULL,10,NULL),(108,NULL,NULL,99060070,80,NULL,NULL,-1,11,NULL),(109,NULL,NULL,99060080,70,NULL,NULL,-1,11,NULL),(112,NULL,NULL,99060050,50,NULL,NULL,NULL,11,NULL),(113,NULL,NULL,99060020,20,NULL,NULL,NULL,11,NULL),(114,NULL,NULL,99060040,10,NULL,NULL,NULL,11,NULL),(115,NULL,NULL,99060030,30,NULL,NULL,NULL,11,NULL),(116,NULL,NULL,99060060,40,NULL,NULL,NULL,11,NULL),(118,NULL,NULL,95020030,30,NULL,NULL,-1,12,NULL),(120,NULL,NULL,95020020,20,NULL,NULL,-1,12,NULL),(124,NULL,NULL,95030010,10,NULL,NULL,NULL,14,NULL),(125,NULL,NULL,95030030,20,NULL,NULL,-1,14,NULL),(126,NULL,NULL,95030020,15,NULL,NULL,-1,14,NULL),(128,NULL,2,95050090,70,0x01,NULL,-1,15,NULL),(129,NULL,NULL,95050050,20,NULL,NULL,-1,15,NULL),(130,NULL,2,95050060,30,0x01,NULL,-1,15,NULL),(140,NULL,NULL,95050040,10,NULL,NULL,NULL,15,NULL),(141,NULL,NULL,90100030,50,NULL,NULL,-1,16,NULL),(143,NULL,NULL,90100020,30,NULL,NULL,-1,16,NULL),(148,NULL,NULL,90110010,10,NULL,NULL,80,17,NULL),(149,NULL,NULL,90110030,60,NULL,NULL,NULL,17,NULL),(150,NULL,NULL,90110020,40,NULL,NULL,-1,17,NULL),(158,NULL,NULL,90110040,150,NULL,NULL,NULL,17,NULL),(159,NULL,NULL,99210160,60,NULL,NULL,NULL,2,NULL),(163,NULL,NULL,99200010,10,0x00,NULL,40,1,NULL),(164,NULL,NULL,99200020,20,NULL,NULL,-1,1,NULL),(165,NULL,NULL,99200050,30,NULL,NULL,-1,1,NULL),(166,NULL,NULL,99200040,40,NULL,NULL,-1,1,NULL),(167,NULL,NULL,99200060,50,NULL,NULL,-1,1,NULL),(174,NULL,NULL,80900060,80,NULL,NULL,-1,18,NULL),(175,NULL,NULL,80900040,50,NULL,NULL,NULL,18,NULL),(176,NULL,NULL,80900030,40,NULL,NULL,NULL,18,NULL),(177,NULL,NULL,80900020,20,NULL,NULL,-1,18,NULL),(178,NULL,NULL,80900050,60,NULL,NULL,-1,18,NULL),(180,NULL,0,99020080,10,0x00,NULL,0,20,NULL),(181,NULL,0,99020090,20,0x01,NULL,0,20,NULL),(185,NULL,NULL,99020250,110,0x00,NULL,NULL,20,NULL),(186,NULL,2,99020130,160,NULL,NULL,-1,20,NULL),(187,NULL,NULL,99020160,10,0x00,NULL,NULL,22,NULL),(188,NULL,NULL,99020170,20,NULL,NULL,NULL,22,NULL),(189,NULL,NULL,99020180,30,NULL,NULL,NULL,22,NULL),(190,NULL,NULL,99020190,40,NULL,NULL,NULL,22,NULL),(191,NULL,NULL,99020200,50,NULL,NULL,NULL,22,NULL),(192,NULL,NULL,99020220,60,NULL,NULL,NULL,22,NULL),(193,NULL,NULL,99020230,70,NULL,NULL,NULL,22,NULL),(207,NULL,NULL,95059000,80,NULL,NULL,NULL,15,NULL),(208,NULL,NULL,95059010,90,NULL,NULL,NULL,15,NULL),(209,NULL,2,95050100,10,0x01,NULL,-1,24,NULL),(211,NULL,NULL,95050110,30,NULL,NULL,-1,24,NULL),(212,NULL,NULL,95050120,40,NULL,NULL,NULL,24,NULL),(213,NULL,NULL,95050130,50,0x01,NULL,NULL,24,NULL),(214,NULL,NULL,95050170,60,NULL,NULL,NULL,24,NULL),(215,NULL,NULL,95050160,70,NULL,NULL,NULL,24,NULL),(216,NULL,NULL,90110050,10,NULL,NULL,NULL,25,NULL),(217,NULL,NULL,90110060,20,NULL,NULL,NULL,25,NULL),(218,NULL,NULL,90110070,30,NULL,NULL,NULL,25,NULL),(219,NULL,NULL,90110080,40,NULL,NULL,NULL,25,NULL),(220,NULL,NULL,90110090,50,NULL,NULL,NULL,25,NULL),(221,NULL,NULL,90110100,60,NULL,NULL,NULL,25,NULL),(222,NULL,NULL,90110110,10,NULL,NULL,-1,26,NULL),(223,NULL,NULL,90110120,20,NULL,NULL,-1,26,NULL),(224,NULL,NULL,90110130,30,NULL,NULL,-1,26,NULL),(226,NULL,2,90000030,20,0x01,NULL,-1,27,NULL),(227,NULL,2,90000020,10,0x01,NULL,-1,27,NULL),(228,NULL,NULL,90000040,40,NULL,NULL,-1,27,NULL),(236,NULL,NULL,90000070,50,NULL,NULL,NULL,27,NULL),(237,NULL,NULL,90000060,130,NULL,NULL,NULL,27,NULL),(238,NULL,NULL,90000050,140,NULL,NULL,-1,27,NULL),(240,NULL,2,90000090,160,NULL,NULL,-1,27,NULL),(241,NULL,1,99020150,140,0x00,NULL,-1,20,NULL),(242,NULL,NULL,99020260,90,NULL,NULL,NULL,22,NULL),(243,NULL,NULL,90300050,50,NULL,NULL,-1,28,NULL),(244,NULL,NULL,90300010,10,NULL,NULL,50,28,NULL),(245,NULL,NULL,90300040,40,NULL,NULL,-1,28,NULL),(246,NULL,NULL,90300020,20,NULL,NULL,-1,28,NULL),(247,NULL,NULL,90300030,30,NULL,NULL,-1,28,NULL),(250,NULL,NULL,90350070,40,NULL,NULL,-1,29,NULL),(251,NULL,2,90350240,240,0x01,NULL,-1,29,NULL),(252,NULL,2,90350020,25,NULL,NULL,-1,29,NULL),(254,NULL,NULL,90350040,20,NULL,NULL,-1,29,NULL),(255,NULL,NULL,90350100,80,NULL,NULL,-1,29,NULL),(256,NULL,2,90350230,220,0x01,NULL,-1,29,NULL),(257,NULL,NULL,90350080,50,NULL,NULL,-1,29,NULL),(258,NULL,NULL,90350090,110,NULL,NULL,-1,29,NULL),(261,NULL,NULL,90350030,10,NULL,NULL,-1,29,NULL),(262,NULL,NULL,90350060,32,NULL,NULL,-1,29,NULL),(263,NULL,NULL,90350050,28,NULL,NULL,-1,29,NULL),(265,NULL,2,90000100,10,0x01,NULL,-1,30,NULL),(266,NULL,NULL,90000110,20,0x01,NULL,-1,30,NULL),(267,NULL,2,90000120,30,0x01,NULL,-1,30,NULL),(268,NULL,2,90000130,40,0x01,NULL,-1,30,NULL),(269,NULL,2,90000140,55,0x01,NULL,-1,30,NULL),(270,NULL,3,90000150,60,NULL,NULL,-1,30,NULL),(271,NULL,1,90000160,50,0x01,NULL,-1,30,NULL),(272,NULL,NULL,90350110,180,0x01,NULL,-1,29,NULL),(273,NULL,NULL,90400030,30,NULL,NULL,-1,31,NULL),(274,NULL,NULL,90400010,10,NULL,NULL,40,31,NULL),(275,NULL,NULL,90400020,20,NULL,NULL,-1,31,NULL),(276,NULL,NULL,90410050,90,NULL,NULL,-1,32,NULL),(277,NULL,NULL,90410010,20,NULL,NULL,60,32,NULL),(278,NULL,NULL,90410040,40,NULL,NULL,NULL,32,NULL),(279,NULL,NULL,90410020,30,NULL,NULL,-1,32,NULL),(280,NULL,NULL,90410030,10,NULL,NULL,NULL,32,NULL),(293,NULL,2,99300090,200,NULL,NULL,-1,34,NULL),(294,NULL,NULL,99300070,190,0x01,NULL,-1,34,NULL),(295,NULL,2,99300040,30,NULL,NULL,-1,34,NULL),(296,NULL,2,99300030,40,0x01,NULL,-1,34,NULL),(297,NULL,2,99300050,50,0x01,NULL,-1,34,NULL),(298,NULL,NULL,99300060,70,NULL,NULL,NULL,34,NULL),(299,NULL,NULL,99300020,20,NULL,NULL,-1,34,NULL),(300,NULL,NULL,99300080,60,0x01,NULL,-1,34,NULL),(301,NULL,NULL,99300100,90,NULL,NULL,NULL,34,NULL),(302,NULL,NULL,99300010,10,NULL,NULL,60,34,NULL),(303,NULL,NULL,99300110,110,NULL,NULL,NULL,34,NULL),(304,NULL,NULL,95050010,100,NULL,NULL,NULL,15,NULL),(305,NULL,NULL,95050020,110,NULL,NULL,NULL,15,NULL),(306,NULL,NULL,95050030,120,NULL,NULL,NULL,15,NULL),(307,NULL,2,99020100,70,0x01,NULL,-1,4,NULL),(308,NULL,2,99020800,80,NULL,NULL,-1,4,NULL),(309,NULL,NULL,99080060,40,NULL,NULL,NULL,7,NULL),(310,NULL,NULL,90350210,250,NULL,NULL,-1,29,NULL),(311,NULL,NULL,90350220,260,NULL,NULL,NULL,29,NULL),(316,NULL,NULL,99050050,40,NULL,NULL,NULL,10,NULL),(317,NULL,NULL,99210050,50,NULL,NULL,NULL,2,NULL),(318,NULL,NULL,99200030,25,NULL,NULL,NULL,1,NULL),(319,NULL,NULL,95050070,50,NULL,NULL,-1,15,NULL),(320,NULL,NULL,95050080,60,NULL,NULL,-1,15,NULL),(321,NULL,NULL,99080070,50,NULL,NULL,NULL,7,NULL),(322,NULL,NULL,99080080,60,NULL,NULL,NULL,7,NULL),(326,NULL,NULL,94010030,30,NULL,NULL,-1,36,NULL),(327,NULL,NULL,94010010,10,NULL,NULL,100,36,NULL),(328,NULL,NULL,94010020,20,NULL,NULL,-1,36,NULL),(329,NULL,NULL,94050080,80,NULL,NULL,NULL,37,NULL),(330,NULL,2,94050200,110,NULL,NULL,-1,37,NULL),(331,NULL,2,94050020,20,NULL,NULL,-1,37,NULL),(332,NULL,2,94050050,50,NULL,NULL,-1,37,NULL),(333,NULL,NULL,94050060,60,NULL,NULL,NULL,37,NULL),(334,NULL,1,94059000,210,NULL,NULL,NULL,37,NULL),(335,NULL,1,94059010,220,NULL,NULL,NULL,37,NULL),(336,NULL,2,94050030,30,NULL,NULL,-1,37,NULL),(338,NULL,NULL,94050090,90,NULL,NULL,NULL,37,NULL),(339,NULL,NULL,94050070,70,NULL,NULL,NULL,37,NULL),(340,NULL,2,94050040,40,NULL,NULL,-1,37,NULL),(341,NULL,2,94050100,45,NULL,NULL,-1,37,NULL),(343,NULL,NULL,99080090,70,NULL,NULL,NULL,7,NULL),(344,NULL,NULL,99080100,80,NULL,NULL,NULL,7,NULL),(347,NULL,2,90900070,70,NULL,NULL,-1,39,NULL),(348,NULL,NULL,90900040,40,0x00,NULL,NULL,39,NULL),(349,NULL,NULL,90900020,20,NULL,NULL,NULL,39,NULL),(350,NULL,2,90900050,50,NULL,NULL,-1,39,NULL),(351,NULL,2,90900080,80,NULL,NULL,-1,39,NULL),(352,NULL,NULL,90900030,30,NULL,NULL,-1,39,NULL),(353,NULL,NULL,90900900,900,NULL,NULL,NULL,39,NULL),(354,NULL,NULL,90900910,910,NULL,NULL,NULL,39,NULL),(356,NULL,0,90900060,45,NULL,NULL,0,39,NULL),(391,NULL,NULL,95020010,10,NULL,NULL,NULL,12,NULL),(555,NULL,NULL,90050110,110,NULL,NULL,NULL,54,NULL),(556,NULL,NULL,90050030,30,NULL,NULL,-1,54,NULL),(557,NULL,2,90050050,50,NULL,NULL,-1,54,NULL),(558,NULL,2,90050060,60,NULL,NULL,-1,54,NULL),(559,NULL,2,90050300,300,NULL,NULL,-1,54,NULL),(560,NULL,NULL,90050160,160,NULL,NULL,NULL,54,NULL),(561,NULL,NULL,90050100,110,NULL,NULL,NULL,54,NULL),(562,NULL,NULL,90050040,40,NULL,NULL,-1,54,NULL),(563,NULL,NULL,90050170,170,0x00,NULL,NULL,54,NULL),(564,NULL,NULL,90050120,120,0x01,NULL,NULL,54,NULL),(565,NULL,NULL,90050180,190,0x01,NULL,NULL,54,NULL),(566,NULL,2,90050020,20,NULL,NULL,-1,54,NULL),(567,NULL,NULL,90050130,130,NULL,NULL,NULL,54,NULL),(569,NULL,NULL,90050140,140,NULL,NULL,NULL,54,NULL),(570,NULL,NULL,90050150,150,NULL,NULL,NULL,54,NULL),(571,NULL,2,90910200,100,NULL,NULL,-1,55,NULL),(572,NULL,NULL,90910030,30,NULL,NULL,NULL,55,NULL),(573,NULL,NULL,90910060,60,NULL,NULL,NULL,55,NULL),(574,NULL,NULL,90910020,20,NULL,NULL,NULL,55,NULL),(575,NULL,2,90910100,90,NULL,NULL,-1,55,NULL),(579,NULL,NULL,90910050,50,NULL,NULL,NULL,55,NULL),(581,NULL,2,90910080,70,NULL,NULL,-1,55,NULL),(583,NULL,NULL,99100080,80,NULL,NULL,NULL,56,NULL),(584,NULL,2,99100060,60,NULL,NULL,-1,56,NULL),(585,NULL,2,99100040,40,NULL,NULL,-1,56,NULL),(586,NULL,2,99100050,50,NULL,NULL,-1,56,NULL),(587,NULL,2,99100070,70,NULL,NULL,-1,56,NULL),(588,NULL,NULL,99100100,100,NULL,NULL,NULL,56,NULL),(589,NULL,NULL,99100020,20,NULL,NULL,NULL,56,NULL),(590,NULL,NULL,99100030,30,NULL,NULL,NULL,56,NULL),(591,NULL,NULL,99100090,90,NULL,NULL,NULL,56,NULL),(592,NULL,NULL,90450010,10,NULL,NULL,NULL,57,NULL),(593,NULL,NULL,90450060,60,NULL,NULL,NULL,57,NULL),(594,NULL,NULL,90450040,40,NULL,NULL,NULL,57,NULL),(595,NULL,NULL,90450050,50,NULL,NULL,NULL,57,NULL),(596,NULL,NULL,90450030,30,NULL,NULL,NULL,57,NULL),(597,NULL,NULL,90450020,20,NULL,NULL,NULL,57,NULL),(598,NULL,NULL,90460010,10,NULL,NULL,NULL,58,NULL),(599,NULL,NULL,90460020,20,NULL,NULL,NULL,58,NULL),(600,NULL,NULL,90460030,30,NULL,NULL,NULL,58,NULL),(601,NULL,NULL,80910010,10,NULL,NULL,NULL,59,NULL),(602,NULL,NULL,80910030,30,NULL,NULL,NULL,59,NULL),(603,NULL,NULL,80910020,20,NULL,NULL,NULL,59,NULL),(604,NULL,NULL,80920010,10,NULL,NULL,NULL,60,NULL),(605,NULL,NULL,80920070,90,NULL,NULL,NULL,60,NULL),(606,NULL,NULL,80920050,50,NULL,NULL,NULL,60,NULL),(607,NULL,NULL,80920020,20,NULL,NULL,NULL,60,NULL),(608,NULL,NULL,80920040,40,NULL,NULL,NULL,60,NULL),(609,NULL,NULL,80920060,60,NULL,NULL,NULL,60,NULL),(610,NULL,NULL,80920030,30,NULL,NULL,NULL,60,NULL),(660,NULL,NULL,90920080,80,NULL,NULL,NULL,70,NULL),(661,NULL,NULL,90920070,70,0x01,NULL,NULL,70,NULL),(662,NULL,NULL,90920050,50,NULL,NULL,NULL,70,NULL),(665,NULL,NULL,90920030,30,NULL,NULL,NULL,70,NULL),(666,NULL,NULL,90920040,40,NULL,NULL,NULL,70,NULL),(667,NULL,NULL,90920060,60,NULL,NULL,NULL,70,NULL),(668,NULL,NULL,95040010,10,NULL,NULL,NULL,71,NULL),(669,NULL,NULL,95040020,20,NULL,NULL,-1,71,NULL),(670,NULL,NULL,95040030,30,NULL,NULL,NULL,71,NULL),(671,NULL,NULL,95040040,40,NULL,NULL,NULL,71,NULL),(672,NULL,NULL,95040090,90,NULL,NULL,-1,71,NULL),(673,NULL,NULL,95050150,35,NULL,NULL,-1,24,NULL),(674,NULL,NULL,90050190,180,NULL,NULL,NULL,54,NULL),(1056,NULL,NULL,90010200,30,NULL,NULL,-1,111,NULL),(1078,NULL,NULL,99020240,80,NULL,NULL,NULL,22,NULL),(1079,NULL,2,99020155,170,NULL,NULL,-1,20,NULL),(1111,NULL,NULL,90010210,10,NULL,NULL,NULL,118,NULL),(1112,NULL,NULL,90010220,20,NULL,NULL,NULL,118,NULL),(1113,NULL,NULL,90010230,30,NULL,NULL,NULL,118,NULL),(1114,NULL,NULL,90010240,40,NULL,NULL,NULL,118,NULL),(1115,NULL,NULL,90010250,50,NULL,NULL,NULL,118,NULL),(1116,NULL,NULL,90010260,60,NULL,NULL,NULL,118,NULL),(1117,NULL,NULL,90010270,70,NULL,NULL,NULL,118,NULL),(1118,NULL,NULL,90010280,80,NULL,NULL,NULL,118,NULL),(1119,NULL,NULL,90010290,90,NULL,NULL,NULL,118,NULL),(1223,NULL,1,99080110,100,NULL,NULL,-1,7,NULL),(1224,NULL,NULL,99410010,10,NULL,NULL,NULL,131,NULL),(1225,NULL,NULL,99410070,70,NULL,NULL,NULL,131,NULL),(1226,NULL,NULL,99410080,80,NULL,NULL,NULL,131,NULL),(1227,NULL,2,99410040,40,NULL,NULL,-1,131,NULL),(1228,NULL,2,99410090,90,NULL,NULL,-1,131,NULL),(1229,NULL,NULL,99410020,20,NULL,NULL,-1,131,NULL),(1230,NULL,NULL,99410030,30,NULL,NULL,NULL,131,NULL),(1231,NULL,NULL,99410050,50,NULL,NULL,NULL,131,NULL),(1232,NULL,NULL,99410060,60,NULL,NULL,NULL,131,NULL),(1233,NULL,NULL,99420090,90,NULL,NULL,NULL,132,NULL),(1234,NULL,NULL,99420100,100,0x01,NULL,NULL,132,NULL),(1235,NULL,NULL,99420010,10,NULL,NULL,NULL,132,NULL),(1236,NULL,NULL,99420050,50,NULL,NULL,NULL,132,NULL),(1237,NULL,NULL,99420080,60,NULL,NULL,NULL,132,NULL),(1238,NULL,2,99420040,40,NULL,NULL,-1,132,NULL),(1239,NULL,NULL,99420030,30,NULL,NULL,NULL,132,NULL),(1240,NULL,NULL,99420190,190,NULL,NULL,NULL,132,NULL),(1241,NULL,NULL,99420020,20,NULL,NULL,-1,132,NULL),(1242,NULL,NULL,99420070,80,NULL,NULL,NULL,132,NULL),(1243,NULL,NULL,99420060,70,NULL,NULL,NULL,132,NULL),(1244,NULL,NULL,99430120,100,NULL,NULL,NULL,133,NULL),(1245,NULL,NULL,99430010,10,NULL,NULL,NULL,133,NULL),(1246,NULL,2,99430050,45,NULL,NULL,-1,133,NULL),(1247,NULL,2,99430040,40,NULL,NULL,-1,133,NULL),(1248,NULL,2,99430190,190,NULL,NULL,-1,133,NULL),(1249,NULL,NULL,99430080,60,NULL,NULL,NULL,133,NULL),(1250,NULL,NULL,99430090,65,NULL,NULL,NULL,133,NULL),(1251,NULL,NULL,99430020,20,NULL,NULL,-1,133,NULL),(1252,NULL,NULL,99430130,120,NULL,NULL,NULL,133,NULL),(1253,NULL,NULL,99430110,75,NULL,NULL,NULL,133,NULL),(1254,NULL,NULL,99430030,30,NULL,NULL,NULL,133,NULL),(1255,NULL,NULL,99430060,50,NULL,NULL,NULL,133,NULL),(1256,NULL,NULL,99430100,70,NULL,NULL,NULL,133,NULL),(1257,NULL,NULL,99430070,55,NULL,NULL,NULL,133,NULL),(1261,NULL,NULL,99110010,10,NULL,NULL,NULL,134,NULL),(1262,NULL,NULL,99110040,40,NULL,NULL,NULL,134,NULL),(1263,NULL,NULL,99110020,20,NULL,NULL,NULL,134,NULL),(1264,NULL,NULL,99110030,30,NULL,NULL,NULL,134,NULL),(1265,NULL,NULL,99110050,50,NULL,NULL,NULL,134,NULL),(1266,NULL,NULL,99120010,10,NULL,NULL,NULL,135,NULL),(1267,NULL,NULL,99120050,50,NULL,NULL,NULL,135,NULL),(1268,NULL,NULL,99120060,70,NULL,NULL,NULL,135,NULL),(1269,NULL,NULL,99120020,20,NULL,NULL,NULL,135,NULL),(1270,NULL,NULL,99120040,40,NULL,NULL,NULL,135,NULL),(1271,NULL,NULL,99120030,30,NULL,NULL,NULL,135,NULL),(1272,NULL,2,90010100,10,NULL,NULL,-1,111,NULL),(1273,NULL,2,90010110,20,NULL,NULL,-1,111,NULL),(1274,NULL,NULL,99020850,130,0x01,NULL,NULL,20,NULL),(1275,NULL,NULL,99020820,100,NULL,NULL,NULL,22,NULL),(1276,NULL,NULL,99020860,110,NULL,NULL,NULL,22,NULL),(1305,NULL,NULL,99350010,10,NULL,NULL,NULL,137,NULL),(1306,NULL,NULL,99350050,50,NULL,NULL,NULL,137,NULL),(1307,NULL,2,99350040,40,NULL,NULL,-1,137,NULL),(1308,NULL,2,99350160,800,NULL,NULL,-1,137,NULL),(1309,NULL,NULL,99350060,60,0x01,NULL,NULL,137,NULL),(1310,NULL,NULL,99350170,9000,NULL,NULL,NULL,137,NULL),(1311,NULL,NULL,99350180,9010,NULL,NULL,NULL,137,NULL),(1312,NULL,2,99350150,220,NULL,NULL,-1,137,NULL),(1313,NULL,NULL,99350140,210,NULL,NULL,NULL,137,NULL),(1314,NULL,NULL,99350030,45,NULL,NULL,NULL,137,NULL),(1315,NULL,2,99350020,20,NULL,NULL,-1,137,NULL),(1316,NULL,NULL,99350120,190,0x01,NULL,NULL,137,NULL),(1317,NULL,2,99350110,180,NULL,NULL,-1,137,NULL),(1318,NULL,NULL,99350130,200,NULL,NULL,NULL,137,NULL),(1319,NULL,NULL,99350100,100,NULL,NULL,NULL,137,NULL),(1320,NULL,NULL,99350080,80,NULL,NULL,NULL,137,NULL),(1321,NULL,NULL,99350070,70,NULL,NULL,NULL,137,NULL),(1322,NULL,NULL,99350090,90,NULL,NULL,NULL,137,NULL),(1323,NULL,NULL,99370010,10,NULL,NULL,NULL,138,NULL),(1324,NULL,2,99370170,800,NULL,NULL,-1,138,NULL),(1325,NULL,NULL,99370050,35,NULL,NULL,NULL,138,NULL),(1326,NULL,2,99370150,220,NULL,NULL,-1,138,NULL),(1327,NULL,NULL,99370060,60,0x01,NULL,NULL,138,NULL),(1328,NULL,NULL,99370180,9000,NULL,NULL,NULL,138,NULL),(1329,NULL,NULL,99370190,9010,NULL,NULL,NULL,138,NULL),(1330,NULL,2,99370040,40,NULL,NULL,-1,138,NULL),(1331,NULL,2,99370160,230,NULL,NULL,-1,138,NULL),(1332,NULL,NULL,99370140,210,NULL,NULL,NULL,138,NULL),(1333,NULL,NULL,99370030,30,NULL,NULL,NULL,138,NULL),(1334,NULL,2,99370020,20,NULL,NULL,-1,138,NULL),(1335,NULL,NULL,99370120,190,NULL,NULL,NULL,138,NULL),(1336,NULL,NULL,99370110,180,NULL,NULL,NULL,138,NULL),(1337,NULL,NULL,99370130,200,NULL,NULL,NULL,138,NULL),(1342,NULL,NULL,99150010,10,NULL,NULL,NULL,139,NULL),(1343,NULL,2,99150160,200,NULL,NULL,-1,139,NULL),(1344,NULL,2,99150140,40,NULL,NULL,-1,139,NULL),(1345,NULL,NULL,99150130,130,NULL,NULL,NULL,139,NULL),(1346,NULL,NULL,99150120,120,NULL,NULL,NULL,139,NULL),(1347,NULL,2,99150150,190,NULL,NULL,-1,139,NULL),(1348,NULL,NULL,99150170,9000,NULL,NULL,NULL,139,NULL),(1349,NULL,NULL,99150180,9010,NULL,NULL,NULL,139,NULL),(1350,NULL,NULL,99150070,70,NULL,NULL,NULL,139,NULL),(1351,NULL,2,99150090,90,NULL,NULL,-1,139,NULL),(1352,NULL,NULL,99150030,45,NULL,NULL,NULL,139,NULL),(1353,NULL,2,99150020,20,NULL,NULL,-1,139,NULL),(1354,NULL,2,99150040,30,NULL,NULL,-1,139,NULL),(1355,NULL,NULL,99150060,60,NULL,NULL,NULL,139,NULL),(1356,NULL,NULL,99150050,50,NULL,NULL,NULL,139,NULL),(1357,NULL,NULL,99150100,100,NULL,NULL,NULL,139,NULL),(1358,NULL,NULL,99150110,110,NULL,NULL,NULL,139,NULL),(1359,NULL,2,99150080,80,NULL,NULL,-1,139,NULL),(1360,NULL,NULL,1313165418,10,NULL,NULL,NULL,140,NULL),(1361,NULL,2,1313165508,200,NULL,NULL,-1,140,NULL),(1362,NULL,2,1313165488,80,NULL,NULL,-1,140,NULL),(1363,NULL,NULL,1313165518,9000,NULL,NULL,NULL,140,NULL),(1364,NULL,NULL,1313165528,9010,NULL,NULL,NULL,140,NULL),(1365,NULL,2,1313165478,70,NULL,NULL,-1,140,NULL),(1366,NULL,NULL,1313165468,60,NULL,NULL,NULL,140,NULL),(1367,NULL,2,1313165448,50,NULL,NULL,-1,140,NULL),(1368,NULL,NULL,1313165458,40,NULL,NULL,NULL,140,NULL),(1369,NULL,NULL,1313165438,30,NULL,NULL,NULL,140,NULL),(1370,NULL,2,1313165428,20,NULL,NULL,-1,140,NULL),(1371,NULL,NULL,1313165498,65,NULL,NULL,NULL,140,NULL),(1372,NULL,2,99020890,30,NULL,NULL,-1,20,NULL),(1373,NULL,NULL,99020880,40,NULL,NULL,NULL,20,NULL),(1374,NULL,NULL,99020810,60,NULL,NULL,NULL,20,NULL),(1375,NULL,NULL,99020870,70,NULL,NULL,NULL,20,NULL),(1376,NULL,NULL,99020840,80,NULL,NULL,NULL,20,NULL),(1377,NULL,NULL,99020830,90,NULL,NULL,NULL,20,NULL),(1378,NULL,NULL,99020900,100,NULL,NULL,NULL,20,NULL),(1379,NULL,NULL,99020910,120,NULL,NULL,NULL,22,NULL),(1385,NULL,NULL,99430200,130,0x01,NULL,NULL,133,NULL),(1391,NULL,NULL,99020930,120,NULL,NULL,NULL,20,NULL),(1393,NULL,NULL,99210170,70,NULL,NULL,NULL,2,NULL),(1394,NULL,NULL,99210180,80,NULL,NULL,NULL,2,NULL),(1395,NULL,NULL,90500060,60,NULL,NULL,0,141,NULL),(1396,NULL,NULL,90500050,50,NULL,NULL,NULL,141,NULL),(1397,NULL,NULL,90500020,20,NULL,NULL,NULL,141,NULL),(1398,NULL,NULL,90500010,10,NULL,NULL,NULL,141,NULL),(1399,NULL,NULL,90500040,40,NULL,NULL,NULL,141,NULL),(1400,NULL,NULL,90500030,30,NULL,NULL,NULL,141,NULL),(1401,NULL,NULL,90520010,10,NULL,NULL,NULL,142,NULL),(1402,NULL,2,90520100,800,NULL,NULL,NULL,142,NULL),(1403,NULL,NULL,90520080,80,NULL,NULL,NULL,142,NULL),(1404,NULL,NULL,90520090,90,NULL,NULL,NULL,142,NULL),(1405,NULL,2,90520050,50,NULL,NULL,NULL,142,NULL),(1406,NULL,NULL,90520110,9000,NULL,NULL,NULL,142,NULL),(1407,NULL,NULL,90520120,9010,NULL,NULL,NULL,142,NULL),(1408,NULL,NULL,90520020,20,0x00,NULL,NULL,142,NULL),(1409,NULL,NULL,90520060,60,NULL,NULL,NULL,142,NULL),(1410,NULL,NULL,90520070,70,NULL,NULL,NULL,142,NULL),(1411,NULL,0,90520030,30,NULL,NULL,-1,142,NULL),(1412,NULL,2,90520040,40,NULL,NULL,NULL,142,NULL),(1685,NULL,2,90050320,200,NULL,NULL,-1,54,NULL),(1743,NULL,NULL,90550010,10,NULL,NULL,NULL,167,NULL),(1744,NULL,NULL,90550160,200,NULL,NULL,NULL,167,NULL),(1745,NULL,NULL,90550140,140,NULL,NULL,NULL,167,NULL),(1746,NULL,NULL,90550130,130,NULL,NULL,NULL,167,NULL),(1747,NULL,NULL,90550120,120,NULL,NULL,NULL,167,NULL),(1748,NULL,NULL,90550150,190,NULL,NULL,NULL,167,NULL),(1749,NULL,NULL,90550170,9000,NULL,NULL,NULL,167,NULL),(1750,NULL,NULL,90550180,9010,NULL,NULL,NULL,167,NULL),(1751,NULL,NULL,90550070,70,NULL,NULL,NULL,167,NULL),(1752,NULL,NULL,90550090,90,NULL,NULL,NULL,167,NULL),(1753,NULL,NULL,90550030,30,NULL,NULL,NULL,167,NULL),(1754,NULL,NULL,90550040,40,NULL,NULL,NULL,167,NULL),(1755,NULL,NULL,90550060,60,NULL,NULL,NULL,167,NULL),(1756,NULL,NULL,90550050,50,NULL,NULL,NULL,167,NULL),(1757,NULL,NULL,90550100,100,NULL,NULL,NULL,167,NULL),(1758,NULL,NULL,90550110,110,NULL,NULL,NULL,167,NULL),(1759,NULL,NULL,90550080,80,NULL,NULL,NULL,167,NULL),(1760,NULL,NULL,90550020,20,NULL,NULL,NULL,167,NULL),(1843,NULL,NULL,1313265418,10,NULL,NULL,NULL,170,NULL),(1844,NULL,NULL,1313265458,60,NULL,NULL,NULL,170,NULL),(1845,NULL,NULL,1313265438,30,NULL,NULL,NULL,170,NULL),(1846,NULL,NULL,1313265428,20,NULL,NULL,NULL,170,NULL),(1847,NULL,NULL,1313265448,40,NULL,NULL,NULL,170,NULL),(1848,NULL,NULL,1313265468,50,NULL,NULL,NULL,170,NULL),(1852,NULL,NULL,1313265478,70,NULL,NULL,NULL,170,NULL),(1853,NULL,NULL,99050070,50,NULL,NULL,NULL,10,NULL),(1858,NULL,NULL,99020940,50,NULL,NULL,NULL,20,NULL),(1909,NULL,NULL,90420030,30,NULL,NULL,NULL,176,NULL),(1910,NULL,NULL,90420020,20,NULL,NULL,NULL,176,NULL),(2208,NULL,NULL,421165438,200,NULL,NULL,NULL,221,NULL),(2209,NULL,NULL,421165418,10,NULL,NULL,NULL,221,NULL),(2210,NULL,NULL,421165428,20,NULL,NULL,NULL,221,NULL),(2211,NULL,NULL,90430020,30,NULL,NULL,-1,222,NULL),(2212,NULL,NULL,90430010,10,NULL,NULL,NULL,222,NULL),(2213,NULL,NULL,90430030,20,NULL,NULL,NULL,222,NULL),(2214,NULL,NULL,90380030,90,NULL,NULL,NULL,223,NULL),(2215,NULL,NULL,90380020,20,NULL,NULL,NULL,223,NULL),(2216,NULL,NULL,90380010,10,NULL,NULL,NULL,223,NULL),(2217,NULL,NULL,448165468,60,NULL,NULL,-1,224,NULL),(2219,NULL,NULL,448165448,40,NULL,NULL,-1,224,NULL),(2220,NULL,NULL,448165438,30,NULL,NULL,-1,224,NULL),(2221,NULL,NULL,448165418,10,NULL,NULL,NULL,224,NULL),(2222,NULL,NULL,448165428,20,NULL,NULL,-1,224,NULL),(2223,NULL,NULL,90390030,30,NULL,NULL,-1,225,NULL),(2224,NULL,NULL,90390010,10,NULL,NULL,NULL,225,NULL),(2225,NULL,NULL,90390020,20,NULL,NULL,-1,225,NULL),(2226,NULL,NULL,90010300,40,NULL,NULL,NULL,111,NULL),(2237,NULL,NULL,90110140,160,NULL,NULL,NULL,17,NULL),(2241,NULL,NULL,421265418,10,NULL,NULL,NULL,227,NULL),(2242,NULL,NULL,421265428,20,NULL,NULL,NULL,227,NULL),(2245,NULL,NULL,1331365418,10,NULL,NULL,NULL,228,NULL),(2246,NULL,NULL,1331365438,30,NULL,NULL,-1,228,NULL),(2247,NULL,NULL,1331365508,110,NULL,NULL,-1,228,NULL),(2248,NULL,NULL,1331365498,100,NULL,NULL,-1,228,NULL),(2249,NULL,2,1331365528,130,NULL,NULL,-1,228,NULL),(2250,NULL,NULL,1331365428,20,NULL,NULL,NULL,228,NULL),(2251,NULL,2,1331365558,160,NULL,NULL,-1,228,NULL),(2252,NULL,2,1331365548,150,NULL,NULL,-1,228,NULL),(2253,NULL,2,1331365518,120,NULL,NULL,-1,228,NULL),(2254,NULL,NULL,1331365568,80,NULL,NULL,NULL,228,NULL),(2255,NULL,NULL,1331365478,70,NULL,NULL,NULL,228,NULL),(2256,NULL,NULL,1331365458,60,NULL,NULL,-1,228,NULL),(2257,NULL,NULL,1331365488,90,NULL,NULL,-1,228,NULL),(2258,NULL,NULL,1331365448,40,NULL,NULL,-1,228,NULL),(2259,NULL,2,1331365538,140,NULL,NULL,-1,228,NULL),(2274,NULL,NULL,99060090,60,NULL,NULL,NULL,11,NULL),(2275,NULL,NULL,99090090,80,NULL,NULL,NULL,8,NULL),(2276,NULL,NULL,99120070,60,NULL,NULL,NULL,135,NULL),(2277,NULL,NULL,99021020,180,NULL,NULL,NULL,20,NULL),(2291,NULL,NULL,70100010,10,NULL,NULL,NULL,229,NULL),(2292,NULL,NULL,70100020,20,NULL,NULL,-1,229,1),(2293,NULL,2,70100030,40,NULL,NULL,NULL,229,NULL),(2294,NULL,NULL,70100040,50,NULL,NULL,NULL,229,NULL),(2295,NULL,NULL,70100050,60,NULL,NULL,NULL,229,NULL),(2296,NULL,NULL,70100060,70,NULL,NULL,NULL,229,NULL),(2297,NULL,NULL,70100070,90,NULL,NULL,NULL,229,NULL),(2298,NULL,NULL,70100080,80,NULL,NULL,NULL,229,NULL),(2299,NULL,NULL,70100090,100,NULL,NULL,NULL,229,NULL),(2300,NULL,NULL,70100100,110,NULL,NULL,NULL,229,NULL),(2301,NULL,2,70100110,120,NULL,NULL,0,229,NULL),(2302,NULL,3,70100120,130,NULL,NULL,-1,229,NULL),(2303,NULL,NULL,70100130,140,NULL,NULL,NULL,229,NULL),(2304,NULL,NULL,70100140,150,NULL,NULL,NULL,229,NULL),(2305,NULL,NULL,95060010,10,NULL,NULL,NULL,230,NULL),(2306,NULL,NULL,95060020,20,NULL,NULL,NULL,230,NULL),(2307,NULL,NULL,95060030,30,NULL,NULL,NULL,230,NULL),(2308,NULL,NULL,95059020,40,NULL,NULL,-1,15,NULL),(2309,NULL,NULL,99010060,30,NULL,NULL,NULL,3,NULL),(2310,NULL,NULL,99010070,70,NULL,NULL,NULL,3,NULL),(2311,NULL,NULL,99020120,190,NULL,NULL,NULL,20,NULL),(2312,NULL,NULL,70100160,10,NULL,NULL,NULL,231,NULL),(2313,NULL,NULL,70100170,20,NULL,NULL,NULL,231,NULL),(2314,NULL,2,70100180,30,NULL,NULL,-1,231,NULL),(2315,NULL,NULL,99080130,110,0x01,NULL,NULL,7,NULL),(2316,NULL,NULL,99090100,60,NULL,NULL,NULL,8,NULL),(2318,NULL,2,99080140,130,NULL,NULL,-1,7,NULL),(2319,NULL,NULL,99080150,120,NULL,NULL,NULL,7,NULL),(2320,NULL,NULL,99010080,80,NULL,NULL,16,3,NULL),(2321,NULL,NULL,70120010,10,NULL,NULL,NULL,232,NULL),(2322,NULL,NULL,70120020,20,NULL,NULL,NULL,232,NULL),(2323,NULL,NULL,70120030,30,NULL,NULL,NULL,232,NULL),(2324,NULL,NULL,70120040,40,NULL,NULL,NULL,232,NULL),(2325,NULL,NULL,70120050,50,NULL,NULL,NULL,232,NULL),(2326,NULL,NULL,70120060,70,NULL,NULL,NULL,232,NULL),(2327,NULL,NULL,70120070,190,NULL,NULL,NULL,232,NULL),(2328,NULL,NULL,70120380,9000,NULL,NULL,NULL,232,NULL),(2329,NULL,NULL,70120390,9010,NULL,NULL,NULL,232,NULL),(2330,NULL,NULL,70120140,1110,NULL,NULL,NULL,233,NULL),(2331,NULL,NULL,70120150,1120,NULL,NULL,NULL,233,NULL),(2332,NULL,NULL,70120160,1130,NULL,NULL,NULL,233,NULL),(2333,NULL,NULL,70120170,1140,NULL,NULL,NULL,233,NULL),(2334,NULL,NULL,70120180,1210,NULL,NULL,NULL,234,NULL),(2335,NULL,NULL,70120190,1220,NULL,NULL,NULL,234,NULL),(2336,NULL,NULL,70120200,1230,NULL,NULL,NULL,234,NULL),(2337,NULL,NULL,70120210,1240,NULL,NULL,NULL,234,NULL),(2338,NULL,NULL,70120220,1310,NULL,NULL,NULL,235,NULL),(2339,NULL,NULL,70120230,1320,NULL,NULL,NULL,235,NULL),(2340,NULL,NULL,70120240,1330,NULL,NULL,NULL,235,NULL),(2341,NULL,NULL,70120250,1340,NULL,NULL,NULL,235,NULL),(2342,NULL,NULL,70120260,1410,NULL,NULL,NULL,236,NULL),(2343,NULL,NULL,70120270,1420,NULL,NULL,NULL,236,NULL),(2344,NULL,NULL,70120280,1430,NULL,NULL,NULL,236,NULL),(2345,NULL,NULL,70120290,1440,NULL,NULL,NULL,236,NULL),(2346,NULL,NULL,70120300,1510,NULL,NULL,NULL,237,NULL),(2347,NULL,NULL,70120310,1520,NULL,NULL,NULL,237,NULL),(2348,NULL,NULL,70120320,1530,NULL,NULL,NULL,237,NULL),(2349,NULL,NULL,70120330,1540,NULL,NULL,NULL,237,NULL),(2350,NULL,NULL,70120340,1610,NULL,NULL,NULL,238,NULL),(2351,NULL,NULL,70120350,1620,NULL,NULL,NULL,238,NULL),(2352,NULL,NULL,70120360,1630,NULL,NULL,NULL,238,NULL),(2353,NULL,NULL,70120370,1640,NULL,NULL,NULL,238,NULL),(2354,NULL,NULL,70100200,30,NULL,NULL,NULL,229,5),(2355,NULL,NULL,99020980,150,NULL,NULL,NULL,20,NULL),(2357,NULL,NULL,99021040,200,NULL,NULL,NULL,20,NULL),(2358,NULL,NULL,1311095418,10,NULL,NULL,NULL,5,NULL),(2359,NULL,NULL,1311095428,20,NULL,NULL,-1,5,NULL),(2360,NULL,NULL,1311095438,30,NULL,NULL,NULL,5,NULL),(2361,NULL,NULL,1311095448,40,NULL,NULL,-1,5,NULL),(2362,NULL,NULL,1311095458,50,NULL,NULL,-1,5,NULL),(2363,NULL,NULL,1311095468,60,NULL,NULL,-1,5,NULL),(2364,NULL,NULL,1311095478,70,NULL,NULL,NULL,5,NULL),(2365,NULL,NULL,1311095488,80,NULL,NULL,NULL,5,NULL),(2366,NULL,NULL,1311095498,90,NULL,NULL,-1,5,NULL),(2367,NULL,NULL,1311095508,100,NULL,NULL,-1,5,NULL),(2368,NULL,NULL,1311095518,120,NULL,NULL,-1,5,NULL),(2369,NULL,NULL,1311095528,130,NULL,NULL,-1,5,NULL),(2370,NULL,NULL,1311095538,10,NULL,NULL,NULL,23,NULL),(2371,NULL,NULL,1311095548,20,NULL,NULL,NULL,23,NULL),(2372,NULL,NULL,1311095558,30,NULL,NULL,NULL,23,NULL),(2373,NULL,NULL,1311095568,40,NULL,NULL,NULL,23,NULL),(2374,NULL,NULL,1311095578,10,NULL,NULL,NULL,239,NULL),(2375,NULL,NULL,1311095588,20,NULL,NULL,NULL,239,NULL),(2376,NULL,NULL,1311095598,30,NULL,NULL,NULL,239,NULL),(2377,NULL,NULL,1311095608,40,NULL,NULL,NULL,239,NULL),(2378,NULL,NULL,1311095618,50,NULL,NULL,NULL,239,NULL),(2379,NULL,NULL,1311095628,60,NULL,NULL,NULL,239,NULL),(2380,NULL,NULL,1311095638,70,NULL,NULL,NULL,239,NULL),(2381,NULL,NULL,1311095648,80,NULL,NULL,NULL,239,NULL),(2382,NULL,NULL,1311095658,90,NULL,NULL,NULL,239,NULL),(2383,NULL,NULL,1311095668,100,NULL,NULL,NULL,239,NULL),(2384,NULL,2,1311095678,110,NULL,NULL,NULL,239,NULL),(2385,NULL,NULL,1311095688,10,NULL,NULL,NULL,240,NULL),(2386,NULL,NULL,1311095698,20,NULL,NULL,NULL,240,NULL),(2387,NULL,NULL,1311095708,30,NULL,NULL,NULL,240,NULL),(2388,NULL,2,1311095718,50,NULL,NULL,NULL,240,NULL),(2389,NULL,NULL,1311095728,40,NULL,NULL,NULL,240,NULL),(2390,NULL,2,1311095738,60,NULL,NULL,NULL,240,NULL),(2391,NULL,NULL,1311095748,10,NULL,NULL,-1,241,NULL),(2392,NULL,NULL,1311095758,20,NULL,NULL,-1,241,NULL),(2393,NULL,2,1311095768,50,NULL,NULL,-1,241,NULL),(2394,NULL,2,1311095778,60,NULL,NULL,-1,241,NULL),(2395,NULL,NULL,1311095788,30,NULL,NULL,-1,241,NULL),(2396,NULL,NULL,1311095798,40,NULL,NULL,-1,241,NULL),(2397,NULL,2,1311095808,70,NULL,NULL,-1,241,NULL),(2398,NULL,2,1311095818,80,NULL,NULL,-1,241,NULL),(2399,NULL,4,1311095828,90,NULL,NULL,-1,241,NULL),(2400,NULL,NULL,1311095838,10,NULL,NULL,NULL,242,NULL),(2401,NULL,NULL,1311095848,20,NULL,NULL,NULL,242,NULL),(2402,NULL,NULL,1311095858,30,NULL,NULL,NULL,242,NULL),(2403,NULL,NULL,1311095868,40,NULL,NULL,NULL,242,NULL),(2404,NULL,NULL,70160010,10,NULL,NULL,NULL,243,NULL),(2405,NULL,NULL,70160020,20,NULL,NULL,NULL,243,NULL),(2406,NULL,NULL,70140010,10,NULL,NULL,NULL,244,NULL),(2407,NULL,NULL,70140020,20,NULL,NULL,NULL,244,NULL),(2408,NULL,NULL,60100010,10,NULL,NULL,NULL,245,NULL),(2409,NULL,NULL,60100020,20,NULL,NULL,NULL,245,NULL),(2410,NULL,NULL,60100030,30,NULL,NULL,NULL,245,NULL),(2411,NULL,NULL,60100040,40,NULL,NULL,NULL,245,NULL),(2412,NULL,NULL,60100050,50,NULL,NULL,NULL,245,NULL),(2413,NULL,NULL,60100060,60,NULL,NULL,NULL,245,NULL),(2414,NULL,NULL,60100070,70,NULL,NULL,NULL,245,NULL),(2415,NULL,NULL,60100080,80,NULL,NULL,NULL,245,NULL),(2416,NULL,NULL,60100090,90,NULL,NULL,NULL,245,NULL),(2417,NULL,NULL,60100100,190,NULL,NULL,NULL,245,NULL),(2418,NULL,NULL,1331365578,50,NULL,NULL,-1,228,NULL),(2419,NULL,NULL,1311095878,110,NULL,NULL,NULL,5,NULL),(2420,NULL,NULL,70180010,10,NULL,NULL,NULL,246,NULL),(2421,NULL,NULL,70180020,20,NULL,NULL,NULL,246,NULL),(2422,NULL,NULL,60300010,10,NULL,NULL,NULL,247,NULL),(2423,NULL,NULL,60300020,20,NULL,NULL,NULL,247,NULL),(2424,NULL,NULL,60300030,30,NULL,NULL,NULL,247,NULL),(2425,NULL,NULL,60300040,40,NULL,NULL,NULL,247,NULL),(2426,NULL,NULL,60300050,50,NULL,NULL,NULL,247,NULL),(2427,NULL,NULL,60300060,60,NULL,NULL,NULL,247,NULL),(2428,NULL,NULL,60300070,190,NULL,NULL,NULL,247,NULL),(2429,NULL,NULL,60200010,10,NULL,NULL,NULL,248,NULL),(2430,NULL,NULL,60200020,20,NULL,NULL,NULL,248,NULL),(2431,NULL,NULL,60200030,30,NULL,NULL,NULL,248,NULL),(2432,NULL,NULL,60200040,40,NULL,NULL,NULL,248,NULL),(2433,NULL,NULL,60200050,50,NULL,NULL,NULL,248,NULL),(2434,NULL,NULL,60200060,60,NULL,NULL,NULL,248,NULL),(2435,NULL,NULL,60200070,70,NULL,NULL,NULL,248,NULL),(2436,NULL,NULL,60200080,80,NULL,NULL,NULL,248,NULL),(2437,NULL,NULL,60200090,90,NULL,NULL,NULL,248,NULL),(2438,NULL,NULL,60200100,190,NULL,NULL,NULL,248,NULL),(2439,NULL,NULL,60400010,10,NULL,NULL,NULL,249,NULL),(2440,NULL,NULL,60400020,20,NULL,NULL,NULL,249,NULL),(2441,NULL,NULL,60400030,30,NULL,NULL,NULL,249,NULL),(2442,NULL,NULL,60400040,40,NULL,NULL,NULL,249,NULL),(2443,NULL,NULL,60400050,50,NULL,NULL,NULL,249,NULL),(2444,NULL,NULL,60400060,55,NULL,NULL,NULL,249,NULL),(2445,NULL,NULL,60400070,60,NULL,NULL,NULL,249,NULL),(2446,NULL,NULL,60400080,190,NULL,NULL,NULL,249,NULL),(2447,NULL,NULL,60500010,10,NULL,NULL,NULL,250,NULL),(2448,NULL,NULL,60500020,20,NULL,NULL,NULL,250,NULL),(2449,NULL,NULL,60500030,30,NULL,NULL,NULL,250,NULL),(2450,NULL,NULL,60500040,40,NULL,NULL,NULL,250,NULL),(2451,NULL,NULL,60500050,50,NULL,NULL,NULL,250,NULL),(2452,NULL,NULL,60500060,60,NULL,NULL,NULL,250,NULL),(2453,NULL,NULL,60500070,70,NULL,NULL,NULL,250,NULL),(2454,NULL,NULL,60500080,190,NULL,NULL,NULL,250,NULL),(2460,NULL,NULL,70200010,10,NULL,NULL,NULL,252,NULL),(2462,NULL,NULL,70200030,30,NULL,NULL,NULL,252,NULL),(2463,NULL,NULL,70200040,40,NULL,NULL,NULL,252,NULL),(2464,NULL,NULL,70200050,50,NULL,NULL,NULL,252,NULL),(2465,NULL,NULL,70200060,60,NULL,NULL,NULL,252,NULL),(2466,NULL,NULL,70200070,190,NULL,NULL,NULL,252,NULL);
/*!40000 ALTER TABLE `_ModuleFormSchemeGroupField` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ModuleGridNavigate`
--

DROP TABLE IF EXISTS `_ModuleGridNavigate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ModuleGridNavigate` (
  `tf_navigatesetId` int(11) NOT NULL,
  `tf_cascading` bit(1) DEFAULT NULL,
  `tf_enabled` bit(1) DEFAULT NULL,
  `tf_fields` varchar(200) NOT NULL,
  `tf_order` int(11) NOT NULL,
  `tf_reverseOrder` bit(1) DEFAULT NULL,
  `tf_text` varchar(200) NOT NULL,
  `tf_type` varchar(200) DEFAULT NULL,
  `tf_moduleId` varchar(10) NOT NULL,
  `tf_numberGroupId` int(11) DEFAULT NULL,
  PRIMARY KEY (`tf_navigatesetId`),
  KEY `FKpjs2flyeliqgjpymbl54d5mad` (`tf_moduleId`),
  KEY `FKcseu7es0797q6d913kusdbqks` (`tf_numberGroupId`),
  CONSTRAINT `FKcseu7es0797q6d913kusdbqks` FOREIGN KEY (`tf_numberGroupId`) REFERENCES `_NumberGroup` (`tf_numberGroupId`),
  CONSTRAINT `FKpjs2flyeliqgjpymbl54d5mad` FOREIGN KEY (`tf_moduleId`) REFERENCES `_Module` (`tf_moduleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ModuleGridNavigate`
--

LOCK TABLES `_ModuleGridNavigate` WRITE;
/*!40000 ALTER TABLE `_ModuleGridNavigate` DISABLE KEYS */;
INSERT INTO `_ModuleGridNavigate` VALUES (14,0x00,0x01,'tf_date',1,0x01,'操作日期',NULL,'9091',4),(69,0x01,0x01,'_RoleGroup--_Role',20,NULL,'用户角色分组-用户角色',NULL,'9042',NULL),(70,0x01,0x01,'_Department--_User',10,NULL,'部门-用户',NULL,'9042',NULL),(71,0x01,0x01,'_Department',1,NULL,'部门',NULL,'9035',NULL),(119,0x01,0x01,'_AllDepartment',20,NULL,'管理部门',NULL,'9043',NULL),(120,0x01,0x01,'_Department--_User',10,NULL,'部门-用户',NULL,'9043',NULL),(121,0x01,0x01,'_Department--_User',1,NULL,'部门-用户',NULL,'9039',NULL),(123,0x01,0x01,'_DataFilterRole',3,NULL,'用户记录筛选角色',NULL,'9039',NULL),(124,0x00,0x01,'tf_district',10,0x00,'所属区域',NULL,'7010',NULL),(125,0x00,0x01,'tf_createDate',20,0x01,'日期',NULL,'7010',2),(126,0x00,0x01,'tf_area',30,0x00,'面积',NULL,'7010',10),(127,0x00,0x01,'tf_name',1,NULL,'名称',NULL,'9505',NULL);
/*!40000 ALTER TABLE `_ModuleGridNavigate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ModuleGridScheme`
--

DROP TABLE IF EXISTS `_ModuleGridScheme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ModuleGridScheme` (
  `tf_gridSchemeId` int(11) NOT NULL,
  `tf_dblClickAction` varchar(255) DEFAULT NULL,
  `tf_defaultSort` varchar(255) DEFAULT NULL,
  `tf_isAllowEditInGrid` bit(1) DEFAULT NULL,
  `tf_isSystemScheme` bit(1) DEFAULT NULL,
  `tf_otherSetting` varchar(255) DEFAULT NULL,
  `tf_schemeName` varchar(50) NOT NULL,
  `tf_schemeOrder` int(11) NOT NULL,
  `tf_moduleId` varchar(10) NOT NULL,
  PRIMARY KEY (`tf_gridSchemeId`),
  KEY `FKsd0day3ipeh2b4h7rmnwcgfu9` (`tf_moduleId`),
  CONSTRAINT `FK__ModuleGridScheme__Module` FOREIGN KEY (`tf_moduleId`) REFERENCES `_Module` (`tf_moduleId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ModuleGridScheme`
--

LOCK TABLES `_ModuleGridScheme` WRITE;
/*!40000 ALTER TABLE `_ModuleGridScheme` DISABLE KEYS */;
INSERT INTO `_ModuleGridScheme` VALUES (1,NULL,NULL,NULL,0x01,NULL,'菜单分组',1,'9920'),(2,NULL,NULL,NULL,NULL,NULL,'系统菜单',3,'9921'),(3,NULL,NULL,NULL,NULL,NULL,'模块分组',1,'990101'),(4,NULL,NULL,NULL,NULL,NULL,'主要内容',10,'990102'),(5,NULL,NULL,NULL,0x01,NULL,'主要内容',10,'990103'),(6,NULL,NULL,0x01,NULL,NULL,'新增的列表方案',1,'990301'),(7,NULL,NULL,NULL,NULL,NULL,'新增的列表方案',1,'990302'),(8,NULL,NULL,NULL,NULL,NULL,'新增的列表方案',1,'990303'),(9,NULL,NULL,NULL,NULL,NULL,'模块列表方案',1,'990201'),(10,NULL,NULL,NULL,NULL,NULL,'新增的列表方案',1,'990202'),(11,NULL,NULL,NULL,NULL,NULL,'新增的列表方案',1,'990203'),(14,NULL,NULL,NULL,NULL,NULL,'附件类型列表',1,'9502'),(16,NULL,NULL,NULL,NULL,NULL,'附件文件类型列表',1,'9503'),(17,NULL,NULL,NULL,NULL,NULL,'主要信息',1,'9505'),(20,NULL,NULL,NULL,NULL,NULL,'部门权限列表',1,'9010'),(21,NULL,NULL,NULL,NULL,NULL,'部门设置列表',1,'9011'),(22,NULL,NULL,NULL,NULL,NULL,'所有信息',2,'9505'),(23,NULL,NULL,NULL,NULL,NULL,'字段列表属性列表',10,'8090'),(24,NULL,NULL,0x00,0x01,NULL,'所有内容',30,'990102'),(25,NULL,NULL,0x00,0x01,NULL,'所有内容',20,'990103'),(26,NULL,NULL,NULL,NULL,NULL,'系统参数设置列表',1,'9000'),(27,NULL,NULL,NULL,0x01,NULL,'职务列表',1,'9030'),(28,NULL,NULL,NULL,NULL,NULL,'主要信息',10,'9035'),(29,NULL,NULL,0x00,0x00,NULL,'所有信息',20,'9035'),(30,NULL,NULL,NULL,NULL,NULL,'用户角色分组列表',1,'9040'),(31,NULL,NULL,NULL,NULL,NULL,'用户角色列表',1,'9041'),(33,NULL,NULL,NULL,NULL,NULL,'模块附加功能列表',1,'990120'),(34,NULL,NULL,NULL,NULL,NULL,'资料分类列表',1,'9401'),(35,NULL,NULL,NULL,NULL,NULL,'文件资料列表',1,'9405'),(36,NULL,NULL,NULL,NULL,NULL,'数据备份列表',1,'9090'),(48,NULL,NULL,NULL,NULL,NULL,'运行参数设置列表',1,'9005'),(49,NULL,NULL,NULL,NULL,NULL,'操作日志列表',1,'9091'),(50,NULL,NULL,NULL,NULL,NULL,'模块导航字段列表',1,'990204'),(51,NULL,NULL,NULL,NULL,NULL,'模块审批设置列表',1,'9045'),(52,NULL,NULL,NULL,NULL,NULL,'模块审批人员列表',1,'9046'),(53,NULL,NULL,NULL,NULL,NULL,'数值分组列表',10,'8091'),(54,NULL,NULL,NULL,NULL,NULL,'数值分组明细列表',10,'8092'),(56,NULL,NULL,NULL,NULL,NULL,'登录日志列表',1,'9092'),(57,NULL,NULL,NULL,NULL,NULL,'图片压缩模式列表',1,'9504'),(81,NULL,NULL,NULL,NULL,NULL,'附加参数设置列表',1,'9001'),(94,NULL,NULL,NULL,NULL,NULL,'记录打印方案列表',1,'9941'),(95,NULL,NULL,NULL,NULL,NULL,'记录打印方案分组列表',1,'9942'),(96,NULL,NULL,NULL,NULL,NULL,'记录打印方案分组字段列表',1,'9943'),(97,NULL,NULL,NULL,NULL,NULL,'模块明细显示分组列表',1,'990401'),(98,NULL,NULL,NULL,NULL,NULL,'模块明细显示字段列表',1,'990402'),(99,NULL,NULL,0x00,0x00,NULL,'附加内容',20,'990102'),(100,NULL,NULL,0x00,0x01,NULL,'子模块信息',40,'990102'),(101,NULL,NULL,NULL,NULL,NULL,'模块Excel报表列表',1,'9935'),(102,NULL,NULL,NULL,NULL,NULL,'模块Excel单记录导入列表',1,'9937'),(103,NULL,NULL,NULL,NULL,NULL,'模块图表方案列表',1,'990501'),(104,NULL,NULL,NULL,NULL,NULL,'模块字段平衡关系列表',1,'990110'),(105,NULL,NULL,NULL,NULL,NULL,'综合查询分组列表',1,'9050'),(106,NULL,NULL,NULL,NULL,NULL,'综合查询列表',1,'9052'),(115,NULL,NULL,NULL,NULL,NULL,'综合查询图表方案列表',1,'9055'),(118,NULL,NULL,NULL,NULL,NULL,'子模块按钮方案列表',1,'990115'),(123,NULL,NULL,NULL,NULL,NULL,'用户角色分配列表',1,'9042'),(134,NULL,NULL,NULL,NULL,NULL,'管理部门列表',1,'901110'),(135,NULL,NULL,NULL,NULL,NULL,'用户附加部门列表',1,'9043'),(136,NULL,NULL,NULL,NULL,NULL,'用户记录筛选角色列表',1,'9038'),(137,NULL,NULL,NULL,NULL,NULL,'用户记录筛选设置明细列表',1,'903810'),(138,NULL,NULL,NULL,NULL,NULL,'用户记录筛选角色分配列表',1,'9039'),(139,NULL,NULL,NULL,NULL,NULL,'管理部门列表',1,'901120'),(140,NULL,NULL,NULL,NULL,NULL,'菜单列表',1,'990130'),(141,NULL,NULL,NULL,NULL,NULL,'省份所有信息',20,'7010'),(142,NULL,NULL,NULL,NULL,NULL,'附件对应字段列表',1,'9506'),(144,NULL,NULL,NULL,NULL,NULL,'市列表',1,'7012'),(145,NULL,NULL,NULL,NULL,NULL,'行业列表',1,'7016'),(146,NULL,NULL,NULL,NULL,NULL,'客户等级列表',1,'7014'),(147,NULL,NULL,NULL,NULL,NULL,'客户单位列表',1,'6010'),(148,NULL,NULL,NULL,NULL,NULL,'商品类别列表',1,'7018'),(149,NULL,NULL,NULL,NULL,NULL,'商品列表',1,'6030'),(150,NULL,NULL,NULL,NULL,NULL,'业务员列表',1,'6020'),(151,NULL,NULL,NULL,NULL,NULL,'订单列表',1,'6040'),(152,NULL,NULL,NULL,NULL,NULL,'订单明细列表',1,'6050'),(154,NULL,NULL,NULL,NULL,NULL,'商品仓库列表',1,'7020');
/*!40000 ALTER TABLE `_ModuleGridScheme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ModuleGridSchemeGroup`
--

DROP TABLE IF EXISTS `_ModuleGridSchemeGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ModuleGridSchemeGroup` (
  `tf_gridGroupId` int(11) NOT NULL,
  `tf_gridGroupName` varchar(50) NOT NULL,
  `tf_gridGroupOrder` int(11) NOT NULL,
  `tf_isLocked` bit(1) DEFAULT NULL,
  `tf_isShowHeaderSpans` bit(1) DEFAULT NULL,
  `tf_otherSetting` varchar(255) DEFAULT NULL,
  `tf_gridSchemeId` int(11) NOT NULL,
  PRIMARY KEY (`tf_gridGroupId`),
  KEY `FKp1gvtp0sdt40alhkx7w6bi0cq` (`tf_gridSchemeId`),
  CONSTRAINT `FK__ModuleGridSchemeGroup__ModuleGridScheme` FOREIGN KEY (`tf_gridSchemeId`) REFERENCES `_ModuleGridScheme` (`tf_gridSchemeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ModuleGridSchemeGroup`
--

LOCK TABLES `_ModuleGridSchemeGroup` WRITE;
/*!40000 ALTER TABLE `_ModuleGridSchemeGroup` DISABLE KEYS */;
INSERT INTO `_ModuleGridSchemeGroup` VALUES (1,'菜单分组',1,NULL,NULL,NULL,1),(2,'所有字段',1,NULL,NULL,NULL,2),(3,'所有字段',1,NULL,NULL,NULL,3),(4,'系统模块',1,NULL,NULL,NULL,4),(5,'主要内容',1,NULL,NULL,NULL,5),(6,'所有字段',1,NULL,NULL,NULL,6),(7,'所有字段',1,NULL,NULL,NULL,7),(8,'所有字段',1,NULL,NULL,NULL,8),(9,'所有字段',1,NULL,NULL,NULL,9),(10,'所有字段',1,NULL,NULL,NULL,10),(11,'所有字段',1,NULL,NULL,NULL,11),(16,'附件类型',1,NULL,NULL,NULL,14),(18,'附件文件类型',1,NULL,NULL,NULL,16),(19,'附件信息',1,NULL,NULL,NULL,17),(21,'部门权限',1,NULL,NULL,NULL,20),(22,'部门设置',10,NULL,NULL,NULL,21),(24,'附件信息',1,NULL,NULL,NULL,22),(25,'文件信息',2,NULL,NULL,NULL,22),(26,'字段列表属性',1,NULL,NULL,NULL,23),(27,'主要内容',10,NULL,0x01,NULL,24),(28,'附加信息',20,NULL,0x01,NULL,24),(29,'模块基本权限',30,NULL,0x01,NULL,24),(30,'基本信息',10,NULL,0x01,NULL,25),(31,'字段权限',20,NULL,0x01,NULL,25),(32,'权限设置',20,NULL,0x01,NULL,21),(33,'其他设置',30,NULL,NULL,NULL,21),(34,'使用单位情况',10,NULL,0x01,NULL,26),(35,'职务',1,NULL,NULL,NULL,27),(36,'用户管理',1,NULL,NULL,NULL,28),(37,'服务单位情况',20,NULL,0x01,NULL,26),(38,'主要信息',10,NULL,NULL,NULL,29),(39,'附加信息',20,NULL,NULL,NULL,29),(40,'用户角色分组',1,NULL,NULL,NULL,30),(41,'用户角色',1,NULL,NULL,NULL,31),(43,'模块附加功能',1,NULL,NULL,NULL,33),(44,'资料分类',1,NULL,NULL,NULL,34),(45,'文件资料',1,NULL,NULL,NULL,35),(46,'数据备份',1,NULL,NULL,NULL,36),(64,'运行参数设置',1,NULL,NULL,NULL,48),(65,'操作日志',1,NULL,NULL,NULL,49),(66,'模块导航字段',1,NULL,NULL,NULL,50),(67,'模块审批设置',1,NULL,NULL,NULL,51),(68,'模块审批人员',1,NULL,NULL,NULL,52),(69,'数值分组',1,NULL,NULL,NULL,53),(70,'数值分组明细',1,NULL,NULL,NULL,54),(80,'登录日志',1,NULL,NULL,NULL,56),(81,'图片压缩模式',1,NULL,NULL,NULL,57),(123,'附加参数设置',10,NULL,NULL,NULL,81),(133,'合同默认付款比例',20,NULL,0x00,NULL,81),(144,'记录打印方案',1,NULL,NULL,NULL,94),(145,'记录打印方案分组',1,NULL,NULL,NULL,95),(146,'记录打印方案分组字段',1,NULL,NULL,NULL,96),(147,'模块明细显示分组',1,NULL,NULL,NULL,97),(148,'模块明细显示字段',1,NULL,NULL,NULL,98),(149,'模块基本情况',10,NULL,0x01,NULL,99),(150,'模块权限情况',20,NULL,0x01,NULL,99),(151,'基本信息',10,NULL,0x01,NULL,100),(152,'子模块信息',20,NULL,0x01,NULL,100),(153,'模块Excel报表',1,NULL,NULL,NULL,101),(154,'模块Excel单记录导入',1,NULL,NULL,NULL,102),(155,'主要内容',1,NULL,NULL,NULL,103),(156,'模块字段平衡关系',1,NULL,NULL,NULL,104),(157,'综合查询分组',1,NULL,NULL,NULL,105),(158,'综合查询',1,NULL,NULL,NULL,106),(180,'综合查询图表方案',1,NULL,NULL,NULL,115),(185,'子模块按钮方案',1,NULL,NULL,NULL,118),(204,'用户角色分配',1,NULL,NULL,NULL,123),(223,'管理部门',1,NULL,NULL,NULL,134),(224,'用户附加部门',1,NULL,NULL,NULL,135),(225,'用户记录筛选角色',1,NULL,NULL,NULL,136),(226,'用户记录筛选设置明细',1,NULL,NULL,NULL,137),(227,'用户记录筛选角色分配',1,NULL,NULL,NULL,138),(228,'管理部门',1,NULL,NULL,NULL,139),(229,'菜单',1,NULL,NULL,NULL,140),(230,'省份',10,0x00,0x01,NULL,141),(231,'附件对应字段',1,NULL,NULL,NULL,142),(233,'市',1,NULL,NULL,NULL,144),(234,'省份附加信息',20,0x00,0x01,NULL,141),(235,'设置信息',30,0x00,0x01,NULL,25),(236,'单字段验证',40,0x00,0x01,NULL,25),(237,'附加信息',50,0x00,0x01,NULL,25),(238,'关联设置',60,0x00,0x01,NULL,25),(239,'附加信息2',2010,0x00,0x00,NULL,141),(240,'省份2',1010,0x00,0x01,NULL,141),(241,'省份1',1005,0x00,0x01,NULL,141),(242,'省份11',100510,0x00,0x01,NULL,141),(243,'省份12',100520,0x00,0x01,NULL,141),(244,'附加信息1',2005,0x00,0x01,NULL,141),(245,'行业',1,NULL,NULL,NULL,145),(246,'客户等级',1,NULL,NULL,NULL,146),(247,'客户单位',1,NULL,NULL,NULL,147),(248,'商品类别',1,NULL,NULL,NULL,148),(249,'商品',1,NULL,NULL,NULL,149),(250,'业务员',1,NULL,NULL,NULL,150),(251,'订单',1,NULL,NULL,NULL,151),(252,'订单明细',1,NULL,NULL,NULL,152),(254,'商品仓库',1,NULL,NULL,NULL,154);
/*!40000 ALTER TABLE `_ModuleGridSchemeGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ModuleGridSchemeGroupField`
--

DROP TABLE IF EXISTS `_ModuleGridSchemeGroupField`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ModuleGridSchemeGroupField` (
  `tf_gridFieldId` int(11) NOT NULL,
  `tf_additionType` varchar(255) DEFAULT NULL,
  `tf_aggregate` varchar(20) DEFAULT NULL,
  `tf_columnWidth` int(11) DEFAULT NULL,
  `tf_fieldId` int(11) DEFAULT NULL,
  `tf_gridFieldOrder` int(11) NOT NULL,
  `tf_isLocked` bit(1) DEFAULT NULL,
  `tf_ishidden` bit(1) DEFAULT NULL,
  `tf_notExportExcel` bit(1) DEFAULT NULL,
  `tf_otherSetting` varchar(255) DEFAULT NULL,
  `tf_gridGroupId` int(11) NOT NULL,
  PRIMARY KEY (`tf_gridFieldId`),
  KEY `FKb51seqwj2vi80v3ydsr15agt` (`tf_fieldId`),
  KEY `FKnusx9dbqvgf50ea395663qp40` (`tf_gridGroupId`),
  CONSTRAINT `FKb51seqwj2vi80v3ydsr15agt` FOREIGN KEY (`tf_fieldId`) REFERENCES `_ModuleField` (`tf_fieldId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKnusx9dbqvgf50ea395663qp40` FOREIGN KEY (`tf_gridGroupId`) REFERENCES `_ModuleGridSchemeGroup` (`tf_gridGroupId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ModuleGridSchemeGroupField`
--

LOCK TABLES `_ModuleGridSchemeGroupField` WRITE;
/*!40000 ALTER TABLE `_ModuleGridSchemeGroupField` DISABLE KEYS */;
INSERT INTO `_ModuleGridSchemeGroupField` VALUES (58,NULL,NULL,75,99070060,120,NULL,NULL,NULL,NULL,6),(59,NULL,NULL,75,99070070,130,NULL,NULL,NULL,NULL,6),(60,NULL,NULL,185,99070040,20,NULL,NULL,NULL,NULL,6),(61,NULL,NULL,75,99070080,50,NULL,NULL,NULL,NULL,6),(62,NULL,NULL,175,99070100,160,NULL,NULL,NULL,NULL,6),(63,NULL,NULL,190,99070020,10,NULL,NULL,NULL,NULL,6),(64,NULL,NULL,75,99070030,30,NULL,NULL,NULL,NULL,6),(65,NULL,NULL,75,99070050,40,NULL,NULL,NULL,NULL,6),(66,NULL,NULL,90,99070090,50,NULL,NULL,NULL,NULL,6),(68,NULL,NULL,150,99080040,20,NULL,NULL,NULL,NULL,7),(69,NULL,NULL,165,99080020,10,NULL,NULL,NULL,NULL,7),(70,NULL,NULL,-1,99080120,140,NULL,NULL,NULL,NULL,7),(71,NULL,NULL,75,99080030,30,NULL,NULL,NULL,NULL,7),(72,NULL,NULL,90,99080050,120,NULL,NULL,NULL,NULL,7),(79,NULL,NULL,-1,99090080,80,NULL,NULL,NULL,NULL,8),(81,NULL,NULL,90,99090070,60,NULL,NULL,NULL,NULL,8),(85,NULL,NULL,65,99090050,40,NULL,NULL,NULL,NULL,8),(86,NULL,NULL,60,99090060,50,NULL,NULL,NULL,NULL,8),(87,NULL,NULL,160,99090030,10,NULL,NULL,NULL,NULL,8),(88,NULL,NULL,160,99090020,20,NULL,NULL,NULL,NULL,8),(89,NULL,NULL,80,99090040,30,NULL,NULL,NULL,NULL,8),(94,NULL,NULL,155,99040040,20,NULL,NULL,NULL,NULL,9),(95,NULL,NULL,75,99040090,40,NULL,NULL,NULL,NULL,9),(96,NULL,NULL,60,99040060,50,NULL,NULL,NULL,NULL,9),(97,NULL,NULL,125,99040020,10,NULL,NULL,NULL,NULL,9),(98,NULL,NULL,75,99040080,70,NULL,NULL,NULL,NULL,9),(99,NULL,NULL,75,99040070,80,NULL,NULL,NULL,NULL,9),(100,NULL,NULL,80,99040030,30,NULL,NULL,NULL,NULL,9),(101,NULL,NULL,75,99040050,100,NULL,NULL,NULL,NULL,9),(103,NULL,NULL,225,99050040,30,NULL,NULL,NULL,NULL,10),(104,NULL,NULL,135,99050020,20,NULL,NULL,NULL,NULL,10),(105,NULL,NULL,NULL,99050060,70,NULL,NULL,NULL,NULL,10),(106,NULL,NULL,75,99050030,40,NULL,NULL,NULL,NULL,10),(108,NULL,NULL,NULL,99060070,80,NULL,NULL,NULL,NULL,11),(109,NULL,NULL,NULL,99060080,90,NULL,NULL,NULL,NULL,11),(112,NULL,NULL,80,99060050,70,NULL,NULL,NULL,NULL,11),(113,NULL,NULL,140,99060020,20,NULL,NULL,NULL,NULL,11),(114,NULL,NULL,110,99060040,10,NULL,NULL,NULL,NULL,11),(115,NULL,NULL,70,99060030,30,NULL,NULL,NULL,NULL,11),(116,NULL,NULL,70,99060060,60,NULL,NULL,NULL,NULL,11),(131,NULL,NULL,80,99210020,30,NULL,NULL,NULL,NULL,2),(138,NULL,NULL,110,99210010,30,NULL,NULL,NULL,NULL,1),(187,NULL,NULL,NULL,99020020,10,NULL,NULL,NULL,NULL,4),(188,NULL,NULL,230,99020030,30,NULL,NULL,NULL,NULL,4),(189,NULL,NULL,180,99020040,20,NULL,NULL,NULL,NULL,4),(190,NULL,NULL,125,99020050,50,NULL,NULL,NULL,NULL,4),(207,NULL,NULL,NULL,99210160,50,NULL,NULL,NULL,NULL,2),(216,NULL,NULL,105,99010010,10,NULL,NULL,NULL,NULL,3),(217,NULL,NULL,150,99010020,20,NULL,NULL,NULL,NULL,3),(218,NULL,NULL,-1,99010030,50,NULL,NULL,NULL,NULL,3),(219,NULL,NULL,-1,99010040,70,NULL,NULL,NULL,NULL,3),(220,NULL,NULL,-1,99010050,80,NULL,NULL,NULL,NULL,3),(225,NULL,NULL,65,99200010,10,NULL,NULL,NULL,NULL,1),(226,NULL,NULL,105,99200020,20,NULL,NULL,NULL,NULL,1),(227,NULL,NULL,-1,99200050,60,NULL,NULL,NULL,NULL,1),(228,NULL,NULL,-1,99200040,50,NULL,NULL,NULL,NULL,1),(229,NULL,NULL,-1,99200060,70,NULL,NULL,NULL,NULL,1),(234,NULL,NULL,-1,95020030,30,NULL,NULL,NULL,NULL,16),(236,NULL,NULL,200,95020020,20,NULL,NULL,NULL,NULL,16),(239,NULL,NULL,-1,95030030,20,NULL,NULL,NULL,NULL,18),(240,NULL,NULL,200,95030020,10,NULL,NULL,NULL,NULL,18),(243,NULL,NULL,140,95050050,10,NULL,NULL,NULL,NULL,19),(244,NULL,NULL,-1,95050060,30,NULL,NULL,NULL,NULL,19),(262,NULL,NULL,-1,90100030,30,NULL,NULL,NULL,NULL,21),(264,NULL,NULL,195,90100020,10,NULL,NULL,NULL,NULL,21),(269,NULL,NULL,NULL,90110010,20,NULL,NULL,NULL,NULL,22),(270,NULL,NULL,NULL,90110030,30,NULL,NULL,NULL,NULL,22),(271,NULL,NULL,205,90110020,10,NULL,NULL,NULL,NULL,22),(279,NULL,NULL,NULL,90110040,40,NULL,NULL,NULL,NULL,22),(297,NULL,NULL,100,95050050,20,NULL,NULL,NULL,NULL,24),(298,NULL,NULL,50,95050040,10,NULL,NULL,NULL,NULL,24),(299,NULL,NULL,200,95050060,30,NULL,NULL,NULL,NULL,24),(300,NULL,NULL,150,95050090,40,NULL,NULL,NULL,NULL,24),(301,NULL,NULL,90,95059000,50,NULL,NULL,NULL,NULL,24),(302,NULL,NULL,90,95059010,60,NULL,NULL,NULL,NULL,24),(303,NULL,NULL,250,95050100,10,NULL,NULL,NULL,NULL,25),(305,NULL,NULL,100,95050110,30,NULL,NULL,NULL,NULL,25),(306,NULL,NULL,100,95050120,40,NULL,NULL,NULL,NULL,25),(307,NULL,NULL,100,95050130,50,NULL,NULL,NULL,NULL,25),(308,NULL,NULL,80,95050170,60,NULL,NULL,NULL,NULL,25),(309,NULL,NULL,80,95050160,70,NULL,NULL,NULL,NULL,25),(318,NULL,NULL,NULL,80900060,90,NULL,NULL,NULL,NULL,26),(319,NULL,NULL,80,80900040,30,NULL,NULL,NULL,NULL,26),(320,NULL,NULL,75,80900030,20,NULL,NULL,NULL,NULL,26),(321,NULL,NULL,160,80900020,10,NULL,NULL,NULL,NULL,26),(322,NULL,NULL,-1,80900050,40,NULL,NULL,NULL,NULL,26),(324,NULL,NULL,155,99210030,10,NULL,NULL,NULL,NULL,2),(325,NULL,NULL,150,99210040,20,NULL,NULL,NULL,NULL,2),(340,NULL,NULL,NULL,99020020,20,NULL,NULL,NULL,NULL,27),(341,NULL,NULL,190,99020030,40,NULL,NULL,NULL,NULL,27),(342,NULL,NULL,150,99020040,30,NULL,NULL,NULL,NULL,27),(343,NULL,NULL,75,99020050,50,NULL,NULL,NULL,NULL,27),(344,NULL,NULL,70,99020060,60,NULL,NULL,NULL,NULL,27),(345,NULL,NULL,150,99020080,10,NULL,NULL,NULL,NULL,28),(346,NULL,NULL,150,99020090,20,NULL,NULL,NULL,NULL,28),(354,NULL,NULL,NULL,99020800,70,NULL,NULL,NULL,NULL,28),(357,NULL,NULL,150,99020130,40,NULL,NULL,NULL,NULL,28),(358,NULL,NULL,65,99020160,10,NULL,NULL,NULL,NULL,29),(359,NULL,NULL,65,99020170,20,NULL,NULL,NULL,NULL,29),(360,NULL,NULL,65,99020180,30,NULL,NULL,NULL,NULL,29),(361,NULL,NULL,65,99020190,40,NULL,NULL,NULL,NULL,29),(362,NULL,NULL,65,99020200,50,NULL,NULL,NULL,NULL,29),(363,NULL,NULL,65,99020220,60,NULL,NULL,NULL,NULL,29),(364,NULL,NULL,65,99020230,70,NULL,NULL,NULL,NULL,29),(384,NULL,NULL,60,95050040,5,NULL,NULL,NULL,NULL,19),(385,NULL,NULL,150,99020120,50,NULL,NULL,NULL,NULL,28),(386,NULL,NULL,NULL,90110050,10,NULL,NULL,NULL,NULL,32),(387,NULL,NULL,NULL,90110060,20,NULL,NULL,NULL,NULL,32),(388,NULL,NULL,NULL,90110070,30,NULL,NULL,NULL,NULL,32),(389,NULL,NULL,NULL,90110080,40,NULL,NULL,NULL,NULL,32),(390,NULL,NULL,NULL,90110090,50,NULL,NULL,NULL,NULL,32),(391,NULL,NULL,NULL,90110100,60,NULL,NULL,NULL,NULL,32),(392,NULL,NULL,NULL,90110110,10,NULL,NULL,NULL,NULL,33),(393,NULL,NULL,NULL,90110120,20,NULL,NULL,NULL,NULL,33),(394,NULL,NULL,NULL,90110130,30,NULL,NULL,NULL,NULL,33),(396,NULL,NULL,140,90000030,20,NULL,NULL,NULL,NULL,34),(397,NULL,NULL,160,90000020,10,NULL,NULL,NULL,NULL,34),(406,NULL,NULL,115,90000070,30,NULL,NULL,NULL,NULL,34),(407,NULL,NULL,95,90000060,50,NULL,NULL,NULL,NULL,34),(408,NULL,NULL,65,90000050,40,NULL,NULL,NULL,NULL,34),(411,NULL,NULL,NULL,99020150,60,NULL,NULL,NULL,NULL,28),(412,NULL,NULL,90,99020260,90,NULL,NULL,NULL,NULL,29),(413,NULL,NULL,80,99020010,5,NULL,NULL,NULL,NULL,4),(414,NULL,NULL,80,99020010,10,NULL,NULL,NULL,NULL,27),(415,NULL,NULL,-1,90300050,50,NULL,NULL,NULL,NULL,35),(416,NULL,NULL,60,90300010,10,NULL,NULL,NULL,NULL,35),(417,NULL,NULL,-1,90300040,40,NULL,NULL,NULL,NULL,35),(418,NULL,NULL,150,90300020,20,NULL,NULL,NULL,NULL,35),(419,NULL,NULL,-1,90300030,30,NULL,NULL,NULL,NULL,35),(422,NULL,NULL,NULL,90350070,70,NULL,NULL,NULL,NULL,36),(424,NULL,NULL,170,90350020,20,NULL,NULL,NULL,NULL,36),(426,NULL,NULL,NULL,90350040,50,NULL,NULL,NULL,NULL,36),(430,NULL,NULL,NULL,90350090,80,NULL,NULL,NULL,NULL,36),(432,NULL,NULL,NULL,90350030,40,NULL,NULL,NULL,NULL,36),(433,NULL,NULL,100,90350060,60,NULL,NULL,NULL,NULL,36),(434,NULL,NULL,NULL,90350050,30,NULL,NULL,NULL,NULL,36),(437,NULL,NULL,210,90000100,10,NULL,NULL,NULL,NULL,37),(438,NULL,NULL,85,90000110,20,NULL,NULL,NULL,NULL,37),(439,NULL,NULL,135,90000120,30,NULL,NULL,NULL,NULL,37),(441,NULL,NULL,160,90000140,50,NULL,NULL,NULL,NULL,37),(443,NULL,NULL,95,90000160,40,NULL,NULL,NULL,NULL,37),(444,NULL,NULL,NULL,90350040,20,NULL,NULL,NULL,NULL,38),(445,NULL,NULL,NULL,90350060,30,NULL,NULL,NULL,NULL,38),(446,NULL,NULL,NULL,90350030,10,NULL,NULL,NULL,NULL,38),(447,NULL,NULL,NULL,90350020,5,NULL,NULL,NULL,NULL,38),(448,NULL,NULL,NULL,90350050,8,NULL,NULL,NULL,NULL,38),(449,NULL,NULL,NULL,90350070,60,NULL,NULL,NULL,NULL,38),(450,NULL,NULL,NULL,90350090,70,NULL,NULL,NULL,NULL,38),(451,NULL,NULL,NULL,90350080,10,NULL,NULL,NULL,NULL,39),(452,NULL,NULL,NULL,90350100,20,NULL,NULL,NULL,NULL,39),(453,NULL,NULL,NULL,90350110,30,NULL,NULL,NULL,NULL,39),(454,NULL,NULL,155,90350210,40,NULL,NULL,NULL,NULL,39),(455,NULL,NULL,NULL,90350220,50,NULL,NULL,NULL,NULL,39),(456,NULL,NULL,NULL,90350230,60,NULL,NULL,NULL,NULL,39),(457,NULL,NULL,NULL,90350240,70,NULL,NULL,NULL,NULL,39),(458,NULL,NULL,-1,90400030,40,NULL,NULL,NULL,NULL,40),(459,NULL,NULL,80,90400010,10,NULL,NULL,NULL,NULL,40),(460,NULL,NULL,150,90400020,20,NULL,NULL,NULL,NULL,40),(461,NULL,NULL,-1,90410050,90,NULL,NULL,NULL,NULL,41),(462,NULL,NULL,90,90410010,10,NULL,NULL,NULL,NULL,41),(463,NULL,NULL,NULL,90410040,30,NULL,NULL,NULL,NULL,41),(464,NULL,NULL,275,90410020,20,NULL,NULL,NULL,NULL,41),(465,NULL,NULL,150,90410030,5,NULL,NULL,NULL,NULL,41),(479,NULL,NULL,NULL,99300070,190,NULL,NULL,NULL,NULL,43),(480,NULL,NULL,200,99300040,30,NULL,NULL,NULL,NULL,43),(481,NULL,NULL,125,99300030,40,NULL,NULL,NULL,NULL,43),(482,NULL,NULL,250,99300050,50,NULL,NULL,NULL,NULL,43),(483,NULL,NULL,60,99300060,60,NULL,NULL,NULL,NULL,43),(484,NULL,NULL,150,99300020,10,NULL,NULL,NULL,NULL,43),(485,NULL,NULL,120,99300080,80,NULL,NULL,NULL,NULL,43),(486,NULL,NULL,NULL,99300100,90,NULL,NULL,NULL,NULL,43),(487,NULL,NULL,80,99300010,5,NULL,NULL,NULL,NULL,43),(488,NULL,NULL,NULL,99300110,110,NULL,NULL,NULL,NULL,43),(489,NULL,NULL,NULL,99300090,200,NULL,NULL,NULL,NULL,43),(495,NULL,NULL,125,99060010,80,NULL,NULL,NULL,NULL,10),(496,NULL,NULL,NULL,90350010,35,NULL,NULL,NULL,NULL,35),(497,NULL,NULL,80,99080060,35,NULL,NULL,NULL,NULL,7),(498,NULL,NULL,NULL,90410010,30,NULL,NULL,NULL,NULL,40),(499,NULL,NULL,-1,95050100,40,NULL,NULL,NULL,NULL,19),(500,NULL,NULL,100,95050110,15,NULL,NULL,NULL,NULL,19),(501,NULL,NULL,NULL,95050130,50,NULL,NULL,NULL,NULL,19),(506,NULL,NULL,75,99050050,50,NULL,NULL,NULL,NULL,10),(508,NULL,NULL,NULL,99020100,70,NULL,NULL,NULL,NULL,27),(509,NULL,NULL,115,99210050,40,NULL,NULL,NULL,NULL,2),(510,NULL,NULL,50,99200030,40,NULL,NULL,NULL,NULL,1),(511,NULL,NULL,105,95050070,45,NULL,NULL,NULL,NULL,19),(512,NULL,NULL,105,95050080,46,NULL,NULL,NULL,NULL,19),(513,NULL,NULL,105,95050070,35,NULL,NULL,NULL,NULL,24),(514,NULL,NULL,105,95050080,36,NULL,NULL,NULL,NULL,24),(518,NULL,NULL,80,99080070,70,NULL,NULL,NULL,NULL,7),(519,NULL,NULL,80,99080080,80,NULL,NULL,NULL,NULL,7),(520,NULL,NULL,-1,94010030,40,NULL,NULL,NULL,NULL,44),(521,NULL,NULL,100,94010010,10,NULL,NULL,NULL,NULL,44),(522,NULL,NULL,200,94010020,20,NULL,NULL,NULL,NULL,44),(523,NULL,NULL,NULL,94050080,80,NULL,NULL,NULL,NULL,45),(524,NULL,NULL,NULL,94050200,100,NULL,NULL,NULL,NULL,45),(525,NULL,NULL,145,94050020,10,NULL,NULL,NULL,NULL,45),(526,NULL,NULL,160,94050050,50,NULL,NULL,NULL,NULL,45),(527,NULL,NULL,NULL,94050060,60,NULL,NULL,NULL,NULL,45),(530,NULL,NULL,220,94050030,20,NULL,NULL,NULL,NULL,45),(531,NULL,NULL,NULL,94050090,90,NULL,NULL,NULL,NULL,45),(532,NULL,NULL,110,94050070,70,NULL,NULL,NULL,NULL,45),(533,NULL,NULL,125,94050040,30,NULL,NULL,NULL,NULL,45),(534,NULL,NULL,95,94050100,40,NULL,NULL,NULL,NULL,45),(535,NULL,NULL,80,99080090,90,NULL,NULL,NULL,NULL,7),(536,NULL,NULL,80,99080100,100,NULL,NULL,NULL,NULL,7),(537,NULL,NULL,NULL,94050010,30,NULL,NULL,NULL,NULL,44),(538,NULL,NULL,240,90900070,70,NULL,NULL,NULL,NULL,46),(539,NULL,NULL,100,90900040,40,NULL,NULL,NULL,NULL,46),(540,NULL,NULL,95,90900020,20,NULL,NULL,NULL,NULL,46),(541,NULL,NULL,260,90900050,50,NULL,NULL,NULL,NULL,46),(542,NULL,NULL,-1,90900080,80,NULL,NULL,NULL,NULL,46),(543,NULL,NULL,145,90900030,30,NULL,NULL,NULL,NULL,46),(547,NULL,NULL,80,90900060,60,NULL,NULL,NULL,NULL,46),(591,NULL,NULL,NULL,95020010,10,NULL,NULL,NULL,NULL,16),(765,NULL,NULL,NULL,90050110,60,NULL,NULL,NULL,NULL,64),(766,NULL,NULL,135,90050030,20,NULL,NULL,NULL,NULL,64),(767,NULL,NULL,130,90050050,40,NULL,NULL,NULL,NULL,64),(768,NULL,NULL,190,90050060,50,NULL,NULL,NULL,NULL,64),(769,NULL,NULL,NULL,90050300,160,NULL,NULL,NULL,NULL,64),(770,NULL,NULL,115,90050160,120,NULL,NULL,NULL,NULL,64),(771,NULL,NULL,NULL,90050100,70,NULL,NULL,NULL,NULL,64),(772,NULL,NULL,NULL,90050040,30,NULL,NULL,NULL,NULL,64),(773,NULL,NULL,115,90050170,130,NULL,NULL,NULL,NULL,64),(774,NULL,NULL,NULL,90050120,80,NULL,NULL,NULL,NULL,64),(775,NULL,NULL,130,90050180,150,NULL,NULL,NULL,NULL,64),(776,NULL,NULL,205,90050020,10,NULL,NULL,NULL,NULL,64),(777,NULL,NULL,NULL,90050130,90,NULL,NULL,NULL,NULL,64),(779,NULL,NULL,125,90050140,100,NULL,NULL,NULL,NULL,64),(780,NULL,NULL,130,90050150,110,NULL,NULL,NULL,NULL,64),(782,NULL,NULL,-1,90910200,80,NULL,NULL,NULL,NULL,65),(783,NULL,NULL,105,90910030,20,NULL,NULL,NULL,NULL,65),(784,NULL,NULL,80,90910060,40,NULL,NULL,NULL,NULL,65),(785,NULL,NULL,95,90910020,10,NULL,NULL,NULL,NULL,65),(786,NULL,NULL,300,90910100,70,NULL,NULL,NULL,NULL,65),(790,NULL,NULL,105,90910050,30,NULL,NULL,NULL,NULL,65),(791,NULL,NULL,135,90910080,60,NULL,NULL,NULL,NULL,65),(793,NULL,NULL,55,99100080,70,NULL,NULL,NULL,NULL,66),(794,NULL,NULL,NULL,99100060,50,NULL,NULL,NULL,NULL,66),(795,NULL,NULL,290,99100040,30,NULL,NULL,NULL,NULL,66),(796,NULL,NULL,260,99100050,40,NULL,NULL,NULL,NULL,66),(797,NULL,NULL,NULL,99100070,60,NULL,NULL,NULL,NULL,66),(798,NULL,NULL,55,99100100,90,NULL,NULL,NULL,NULL,66),(799,NULL,NULL,185,99100020,10,NULL,NULL,NULL,NULL,66),(800,NULL,NULL,65,99100030,20,NULL,NULL,NULL,NULL,66),(811,NULL,NULL,55,99100090,80,NULL,NULL,NULL,NULL,66),(812,NULL,NULL,110,90450060,60,NULL,NULL,NULL,NULL,67),(813,NULL,NULL,135,90450040,40,NULL,NULL,NULL,NULL,67),(814,NULL,NULL,90,90450050,50,NULL,NULL,NULL,NULL,67),(815,NULL,NULL,65,90450030,30,NULL,NULL,NULL,NULL,67),(816,NULL,NULL,130,90450020,20,NULL,NULL,NULL,NULL,67),(817,NULL,NULL,155,90460020,20,NULL,NULL,NULL,NULL,68),(818,NULL,NULL,130,90460030,50,NULL,NULL,NULL,NULL,68),(819,NULL,NULL,165,99020040,10,NULL,NULL,NULL,NULL,68),(820,NULL,NULL,130,90460010,70,NULL,NULL,NULL,NULL,67),(822,NULL,NULL,-1,80910030,30,NULL,NULL,NULL,NULL,69),(823,NULL,NULL,200,80910020,10,NULL,NULL,NULL,NULL,69),(824,NULL,NULL,-1,80920070,90,NULL,NULL,NULL,NULL,70),(825,NULL,NULL,180,80920050,50,NULL,NULL,NULL,NULL,70),(826,NULL,NULL,150,80920020,20,NULL,NULL,NULL,NULL,70),(827,NULL,NULL,215,80920040,40,NULL,NULL,NULL,NULL,70),(828,NULL,NULL,195,80920060,60,NULL,NULL,NULL,NULL,70),(829,NULL,NULL,60,80920030,30,NULL,NULL,NULL,NULL,70),(861,NULL,NULL,170,80920010,20,NULL,NULL,NULL,NULL,69),(878,NULL,NULL,-1,90920080,80,NULL,NULL,NULL,NULL,80),(879,NULL,NULL,120,90920070,70,NULL,NULL,NULL,NULL,80),(880,NULL,NULL,NULL,90920050,50,NULL,NULL,NULL,NULL,80),(883,NULL,NULL,NULL,90920030,30,NULL,NULL,NULL,NULL,80),(884,NULL,NULL,110,90920040,40,NULL,NULL,NULL,NULL,80),(885,NULL,NULL,NULL,90920060,60,NULL,NULL,NULL,NULL,80),(886,NULL,NULL,65,95040010,10,NULL,NULL,NULL,NULL,81),(887,NULL,NULL,185,95040020,20,NULL,NULL,NULL,NULL,81),(888,NULL,NULL,NULL,95040030,30,NULL,NULL,NULL,NULL,81),(889,NULL,NULL,NULL,95040040,40,NULL,NULL,NULL,NULL,81),(890,NULL,NULL,225,95040090,90,NULL,NULL,NULL,NULL,81),(891,NULL,NULL,155,90050190,140,NULL,NULL,NULL,NULL,64),(1238,NULL,NULL,165,90010200,20,NULL,NULL,NULL,NULL,123),(1267,NULL,NULL,65,99020240,80,NULL,NULL,NULL,NULL,29),(1316,NULL,NULL,85,90010210,10,NULL,NULL,NULL,NULL,133),(1317,NULL,NULL,NULL,90010220,20,NULL,NULL,NULL,NULL,133),(1318,NULL,NULL,90,90010230,30,NULL,NULL,NULL,NULL,133),(1319,NULL,NULL,90,90010240,40,NULL,NULL,NULL,NULL,133),(1320,NULL,NULL,NULL,90010250,50,NULL,NULL,NULL,NULL,133),(1321,NULL,NULL,NULL,90010260,60,NULL,NULL,NULL,NULL,133),(1322,NULL,NULL,NULL,90010270,70,NULL,NULL,NULL,NULL,133),(1323,NULL,NULL,NULL,90010280,80,NULL,NULL,NULL,NULL,133),(1324,NULL,NULL,NULL,90010290,90,NULL,NULL,NULL,NULL,133),(1431,NULL,NULL,NULL,99080110,150,NULL,NULL,NULL,NULL,7),(1433,NULL,NULL,NULL,99410070,70,NULL,NULL,NULL,NULL,144),(1434,NULL,NULL,NULL,99410080,80,NULL,NULL,NULL,NULL,144),(1435,NULL,NULL,210,99410040,40,NULL,NULL,NULL,NULL,144),(1436,NULL,NULL,110,99410090,90,NULL,NULL,NULL,NULL,144),(1437,NULL,NULL,NULL,99410020,20,NULL,NULL,NULL,NULL,144),(1438,NULL,NULL,85,99410030,30,NULL,NULL,NULL,NULL,144),(1439,NULL,NULL,NULL,99410050,50,NULL,NULL,NULL,NULL,144),(1440,NULL,NULL,85,99410060,60,NULL,NULL,NULL,NULL,144),(1441,NULL,NULL,NULL,99420090,90,NULL,NULL,NULL,NULL,145),(1442,NULL,NULL,NULL,99420100,100,NULL,NULL,NULL,NULL,145),(1444,NULL,NULL,NULL,99420050,50,NULL,NULL,NULL,NULL,145),(1445,NULL,NULL,NULL,99420080,80,NULL,NULL,NULL,NULL,145),(1446,NULL,NULL,NULL,99420040,40,NULL,NULL,NULL,NULL,145),(1447,NULL,NULL,95,99420030,30,NULL,NULL,NULL,NULL,145),(1448,NULL,NULL,NULL,99420190,190,NULL,NULL,NULL,NULL,145),(1449,NULL,NULL,125,99420020,20,NULL,NULL,NULL,NULL,145),(1450,NULL,NULL,190,99420070,70,NULL,NULL,NULL,NULL,145),(1451,NULL,NULL,70,99420060,60,NULL,NULL,NULL,NULL,145),(1452,NULL,NULL,NULL,99430120,120,NULL,NULL,NULL,NULL,146),(1454,NULL,NULL,155,99430050,40,NULL,NULL,NULL,NULL,146),(1455,NULL,NULL,190,99430040,30,NULL,NULL,NULL,NULL,146),(1456,NULL,NULL,NULL,99430190,140,NULL,NULL,NULL,NULL,146),(1457,NULL,NULL,90,99430080,80,NULL,NULL,NULL,NULL,146),(1458,NULL,NULL,80,99430090,90,NULL,NULL,NULL,NULL,146),(1459,NULL,NULL,125,99430020,10,NULL,NULL,NULL,NULL,146),(1460,NULL,NULL,70,99430130,130,NULL,NULL,NULL,NULL,146),(1461,NULL,NULL,NULL,99430110,100,NULL,NULL,NULL,NULL,146),(1462,NULL,NULL,80,99430030,20,NULL,NULL,NULL,NULL,146),(1463,NULL,NULL,70,99430060,60,NULL,NULL,NULL,NULL,146),(1464,NULL,NULL,NULL,99430100,110,NULL,NULL,NULL,NULL,146),(1465,NULL,NULL,60,99430070,70,NULL,NULL,NULL,NULL,146),(1468,NULL,NULL,190,99110040,40,NULL,NULL,NULL,NULL,147),(1469,NULL,NULL,175,99110020,20,NULL,NULL,NULL,NULL,147),(1470,NULL,NULL,75,99110030,30,NULL,NULL,NULL,NULL,147),(1471,NULL,NULL,NULL,99110050,50,NULL,NULL,NULL,NULL,147),(1472,NULL,NULL,145,99120050,50,NULL,NULL,NULL,NULL,148),(1473,NULL,NULL,165,99120060,60,NULL,NULL,NULL,NULL,148),(1474,NULL,NULL,145,99120020,20,NULL,NULL,NULL,NULL,148),(1475,NULL,NULL,130,99120040,40,NULL,NULL,NULL,NULL,148),(1476,NULL,NULL,75,99120030,30,NULL,NULL,NULL,NULL,148),(1477,NULL,NULL,135,99020040,10,NULL,NULL,NULL,NULL,148),(1478,NULL,NULL,125,99120010,60,NULL,NULL,NULL,NULL,147),(1479,NULL,NULL,80,99020010,10,NULL,NULL,NULL,NULL,149),(1480,NULL,NULL,NULL,99020020,20,NULL,NULL,NULL,NULL,149),(1481,NULL,NULL,180,99020030,30,NULL,NULL,NULL,NULL,149),(1482,NULL,NULL,140,99020040,40,NULL,NULL,NULL,NULL,149),(1483,NULL,NULL,NULL,99020050,50,NULL,NULL,NULL,NULL,149),(1484,NULL,NULL,55,99020160,10,NULL,NULL,NULL,NULL,150),(1485,NULL,NULL,70,99020170,20,NULL,NULL,NULL,NULL,150),(1486,NULL,NULL,65,99020180,30,NULL,NULL,NULL,NULL,150),(1487,NULL,NULL,65,99020190,40,NULL,NULL,NULL,NULL,150),(1488,NULL,NULL,65,99020200,50,NULL,NULL,NULL,NULL,150),(1489,NULL,NULL,60,99020210,60,NULL,NULL,NULL,NULL,150),(1490,NULL,NULL,65,99020220,70,NULL,NULL,NULL,NULL,150),(1491,NULL,NULL,70,99020230,80,NULL,NULL,NULL,NULL,150),(1492,NULL,NULL,65,99020240,90,NULL,NULL,NULL,NULL,150),(1493,NULL,NULL,65,99020250,100,NULL,NULL,NULL,NULL,150),(1494,NULL,NULL,100,99020260,110,NULL,NULL,NULL,NULL,150),(1495,NULL,NULL,NULL,99020155,80,NULL,NULL,NULL,NULL,28),(1496,NULL,NULL,NULL,99020210,100,NULL,NULL,NULL,NULL,29),(1497,NULL,NULL,NULL,99020250,110,NULL,NULL,NULL,NULL,29),(1498,NULL,NULL,NULL,99020010,10,NULL,NULL,NULL,NULL,151),(1499,NULL,NULL,NULL,99020020,20,NULL,NULL,NULL,NULL,151),(1500,NULL,NULL,220,99020030,30,NULL,NULL,NULL,NULL,151),(1501,NULL,NULL,145,99020040,40,NULL,NULL,NULL,NULL,151),(1513,NULL,NULL,NULL,99040010,120,NULL,NULL,NULL,NULL,152),(1514,NULL,NULL,NULL,99070010,130,NULL,NULL,NULL,NULL,152),(1515,NULL,NULL,NULL,99210010,140,NULL,NULL,NULL,NULL,152),(1516,NULL,NULL,140,99020040,10,NULL,NULL,NULL,NULL,10),(1518,NULL,NULL,NULL,90010110,30,NULL,NULL,NULL,NULL,123),(1519,NULL,NULL,NULL,90010100,10,NULL,NULL,NULL,NULL,123),(1522,NULL,NULL,75,99350050,50,NULL,NULL,NULL,NULL,153),(1523,NULL,NULL,200,99350040,40,NULL,NULL,NULL,NULL,153),(1525,NULL,NULL,55,99350060,60,NULL,NULL,NULL,NULL,153),(1530,NULL,NULL,65,99350030,30,NULL,NULL,NULL,NULL,153),(1531,NULL,NULL,205,99350020,20,NULL,NULL,NULL,NULL,153),(1533,NULL,NULL,NULL,99350110,180,NULL,NULL,NULL,NULL,153),(1535,NULL,NULL,75,99350100,100,NULL,NULL,NULL,NULL,153),(1536,NULL,NULL,70,99350080,80,NULL,NULL,NULL,NULL,153),(1537,NULL,NULL,75,99350070,70,NULL,NULL,NULL,NULL,153),(1538,NULL,NULL,75,99350090,90,NULL,NULL,NULL,NULL,153),(1542,NULL,NULL,50,99370060,40,NULL,NULL,NULL,NULL,154),(1545,NULL,NULL,280,99370040,30,NULL,NULL,NULL,NULL,154),(1548,NULL,NULL,65,99370030,20,NULL,NULL,NULL,NULL,154),(1549,NULL,NULL,195,99370020,10,NULL,NULL,NULL,NULL,154),(1551,NULL,NULL,310,99370110,50,NULL,NULL,NULL,NULL,154),(1558,NULL,NULL,335,99150140,140,NULL,NULL,NULL,NULL,155),(1566,NULL,NULL,65,99150030,30,NULL,NULL,NULL,NULL,155),(1567,NULL,NULL,240,99150020,20,NULL,NULL,NULL,NULL,155),(1568,NULL,NULL,295,99150040,40,NULL,NULL,NULL,NULL,155),(1570,NULL,NULL,90,99150050,50,NULL,NULL,NULL,NULL,155),(1575,NULL,NULL,185,1313165488,80,NULL,NULL,NULL,NULL,156),(1578,NULL,NULL,195,1313165478,70,NULL,NULL,NULL,NULL,156),(1579,NULL,NULL,135,1313165468,60,NULL,NULL,NULL,NULL,156),(1580,NULL,NULL,160,1313165448,40,NULL,NULL,NULL,NULL,156),(1581,NULL,NULL,90,1313165458,50,NULL,NULL,NULL,NULL,156),(1582,NULL,NULL,90,1313165438,30,NULL,NULL,NULL,NULL,156),(1583,NULL,NULL,140,1313165428,20,NULL,NULL,NULL,NULL,156),(1584,NULL,NULL,150,1313165498,90,NULL,NULL,NULL,NULL,156),(1585,NULL,NULL,55,99430200,50,NULL,NULL,NULL,NULL,146),(1586,NULL,NULL,NULL,99210170,60,NULL,NULL,NULL,NULL,2),(1587,NULL,NULL,NULL,99210180,70,NULL,NULL,NULL,NULL,2),(1588,NULL,NULL,-1,90500060,60,NULL,NULL,NULL,NULL,157),(1589,NULL,NULL,80,90500050,50,NULL,NULL,NULL,NULL,157),(1590,NULL,NULL,210,90500020,20,NULL,NULL,NULL,NULL,157),(1591,NULL,NULL,65,90500010,10,NULL,NULL,NULL,NULL,157),(1592,NULL,NULL,185,90500040,40,NULL,NULL,NULL,NULL,157),(1593,NULL,NULL,95,90500030,30,NULL,NULL,NULL,NULL,157),(1594,NULL,NULL,50,90520100,90,NULL,NULL,NULL,NULL,158),(1595,NULL,NULL,80,90520080,70,NULL,NULL,NULL,NULL,158),(1596,NULL,NULL,90,90520090,80,NULL,NULL,NULL,NULL,158),(1597,NULL,NULL,75,90520050,40,NULL,NULL,NULL,NULL,158),(1598,NULL,NULL,80,90520110,100,NULL,NULL,NULL,NULL,158),(1599,NULL,NULL,85,90520120,110,NULL,NULL,NULL,NULL,158),(1600,NULL,NULL,65,90520020,20,NULL,NULL,NULL,NULL,158),(1601,NULL,NULL,95,90520060,60,NULL,NULL,NULL,NULL,158),(1602,NULL,NULL,80,90520070,50,NULL,NULL,NULL,NULL,158),(1603,NULL,NULL,105,90520030,10,NULL,NULL,NULL,NULL,158),(1604,NULL,NULL,200,90520040,30,NULL,NULL,NULL,NULL,158),(1851,NULL,NULL,NULL,90550160,200,NULL,NULL,NULL,NULL,180),(1852,NULL,NULL,NULL,90550140,140,NULL,NULL,NULL,NULL,180),(1853,NULL,NULL,NULL,90550130,130,NULL,NULL,NULL,NULL,180),(1854,NULL,NULL,NULL,90550120,120,NULL,NULL,NULL,NULL,180),(1855,NULL,NULL,NULL,90550150,190,NULL,NULL,NULL,NULL,180),(1856,NULL,NULL,NULL,90550170,9000,NULL,NULL,NULL,NULL,180),(1857,NULL,NULL,NULL,90550180,9010,NULL,NULL,NULL,NULL,180),(1858,NULL,NULL,NULL,90550070,70,NULL,NULL,NULL,NULL,180),(1859,NULL,NULL,NULL,90550090,90,NULL,NULL,NULL,NULL,180),(1860,NULL,NULL,NULL,90550030,30,NULL,NULL,NULL,NULL,180),(1861,NULL,NULL,NULL,90550040,40,NULL,NULL,NULL,NULL,180),(1862,NULL,NULL,NULL,90550060,60,NULL,NULL,NULL,NULL,180),(1863,NULL,NULL,NULL,90550050,50,NULL,NULL,NULL,NULL,180),(1864,NULL,NULL,NULL,90550100,100,NULL,NULL,NULL,NULL,180),(1865,NULL,NULL,NULL,90550110,110,NULL,NULL,NULL,NULL,180),(1866,NULL,NULL,NULL,90550080,80,NULL,NULL,NULL,NULL,180),(1867,NULL,NULL,NULL,90550020,20,NULL,NULL,NULL,NULL,180),(1903,NULL,NULL,NULL,1313265458,50,NULL,NULL,NULL,NULL,185),(1904,NULL,NULL,65,1313265438,20,NULL,NULL,NULL,NULL,185),(1905,NULL,NULL,175,1313265428,10,NULL,NULL,NULL,NULL,185),(1906,NULL,NULL,210,1313265448,40,NULL,NULL,NULL,NULL,185),(1907,NULL,NULL,200,1313265468,30,NULL,NULL,NULL,NULL,185),(1908,NULL,NULL,NULL,1313265478,60,NULL,NULL,NULL,NULL,185),(1909,NULL,NULL,70,99050070,60,NULL,NULL,NULL,NULL,10),(1974,NULL,NULL,NULL,99020820,120,NULL,NULL,NULL,NULL,150),(1975,NULL,NULL,NULL,99020860,130,NULL,NULL,NULL,NULL,150),(1976,NULL,NULL,NULL,99020910,140,NULL,NULL,NULL,NULL,150),(1977,NULL,NULL,NULL,99020820,120,NULL,NULL,NULL,NULL,29),(1978,NULL,NULL,NULL,99020860,130,NULL,NULL,NULL,NULL,29),(1979,NULL,NULL,NULL,99020910,140,NULL,NULL,NULL,NULL,29),(1980,NULL,NULL,NULL,99020920,150,NULL,NULL,NULL,NULL,29),(2027,NULL,NULL,175,90420030,30,NULL,NULL,NULL,NULL,204),(2028,NULL,NULL,195,90420020,10,NULL,NULL,NULL,NULL,204),(2029,NULL,NULL,190,90110020,20,NULL,NULL,NULL,NULL,204),(2030,NULL,NULL,105,90110010,10,NULL,NULL,NULL,NULL,36),(2200,NULL,NULL,NULL,421165438,200,NULL,NULL,NULL,NULL,223),(2201,NULL,NULL,NULL,421165418,10,NULL,NULL,NULL,NULL,223),(2202,NULL,NULL,NULL,421165428,20,NULL,NULL,NULL,NULL,223),(2203,NULL,NULL,245,90430020,30,NULL,NULL,NULL,NULL,224),(2204,NULL,NULL,NULL,90430010,10,NULL,NULL,NULL,NULL,224),(2205,NULL,NULL,NULL,90430030,10,NULL,NULL,NULL,NULL,224),(2206,NULL,NULL,110,421165418,20,NULL,NULL,NULL,NULL,224),(2207,NULL,NULL,155,90430010,90,NULL,NULL,NULL,NULL,36),(2208,NULL,NULL,145,90430010,80,NULL,NULL,NULL,NULL,38),(2209,NULL,NULL,265,90380030,90,NULL,NULL,NULL,NULL,225),(2210,NULL,NULL,235,90380020,20,NULL,NULL,NULL,NULL,225),(2211,NULL,NULL,NULL,90380010,10,NULL,NULL,NULL,NULL,225),(2212,NULL,NULL,355,448165468,60,NULL,NULL,NULL,NULL,226),(2214,NULL,NULL,285,448165448,40,NULL,NULL,NULL,NULL,226),(2215,NULL,NULL,150,448165438,30,NULL,NULL,NULL,NULL,226),(2216,NULL,NULL,NULL,448165418,10,NULL,NULL,NULL,NULL,226),(2217,NULL,NULL,165,448165428,20,NULL,NULL,NULL,NULL,226),(2218,NULL,NULL,325,90390030,30,NULL,NULL,NULL,NULL,227),(2219,NULL,NULL,NULL,90390010,10,NULL,NULL,NULL,NULL,227),(2220,NULL,NULL,135,90390020,20,NULL,NULL,NULL,NULL,227),(2221,NULL,NULL,150,90010300,40,NULL,NULL,NULL,NULL,123),(2222,NULL,NULL,NULL,90350040,60,NULL,NULL,NULL,NULL,68),(2223,NULL,NULL,NULL,90110010,30,NULL,NULL,NULL,NULL,68),(2224,NULL,NULL,NULL,90110020,40,NULL,NULL,NULL,NULL,68),(2225,NULL,NULL,NULL,90110140,50,NULL,NULL,NULL,NULL,22),(2226,NULL,NULL,NULL,421265418,10,NULL,NULL,NULL,NULL,228),(2227,NULL,NULL,NULL,421265428,20,NULL,NULL,NULL,NULL,228),(2229,NULL,NULL,NULL,1331365438,10,NULL,NULL,NULL,NULL,229),(2230,NULL,NULL,NULL,1331365508,80,NULL,NULL,NULL,NULL,229),(2231,NULL,NULL,NULL,1331365498,70,NULL,NULL,NULL,NULL,229),(2232,NULL,NULL,NULL,1331365528,100,NULL,NULL,NULL,NULL,229),(2233,NULL,NULL,NULL,1331365558,130,NULL,NULL,NULL,NULL,229),(2234,NULL,NULL,NULL,1331365548,120,NULL,NULL,NULL,NULL,229),(2235,NULL,NULL,NULL,1331365518,90,NULL,NULL,NULL,NULL,229),(2236,NULL,NULL,NULL,1331365568,140,NULL,NULL,NULL,NULL,229),(2237,NULL,NULL,NULL,1331365478,50,NULL,NULL,NULL,NULL,229),(2238,NULL,NULL,NULL,1331365458,40,NULL,NULL,NULL,NULL,229),(2239,NULL,NULL,NULL,1331365488,60,NULL,NULL,NULL,NULL,229),(2240,NULL,NULL,NULL,1331365448,20,NULL,NULL,NULL,NULL,229),(2241,NULL,NULL,NULL,1331365538,110,NULL,NULL,NULL,NULL,229),(2267,NULL,NULL,NULL,99060090,100,NULL,NULL,NULL,NULL,11),(2268,NULL,NULL,NULL,99090090,90,NULL,NULL,NULL,NULL,8),(2269,NULL,NULL,NULL,99120070,70,NULL,NULL,NULL,NULL,148),(2287,NULL,NULL,NULL,95060010,10,NULL,NULL,NULL,NULL,231),(2288,NULL,NULL,NULL,95060020,20,NULL,NULL,NULL,NULL,231),(2289,NULL,NULL,NULL,95060030,30,NULL,NULL,NULL,NULL,231),(2290,NULL,NULL,NULL,99010060,30,NULL,NULL,NULL,NULL,3),(2292,NULL,'count',NULL,99020010,40,NULL,NULL,NULL,NULL,3),(2293,NULL,NULL,-1,99010070,60,NULL,NULL,NULL,NULL,3),(2296,NULL,NULL,NULL,99080130,160,NULL,NULL,NULL,NULL,7),(2297,NULL,NULL,NULL,99080140,170,NULL,NULL,NULL,NULL,7),(2298,NULL,NULL,NULL,99080150,180,NULL,NULL,NULL,NULL,7),(2299,NULL,NULL,NULL,99010080,90,NULL,NULL,NULL,NULL,3),(2304,NULL,NULL,NULL,70120010,10,NULL,NULL,NULL,NULL,233),(2305,NULL,NULL,NULL,70120020,20,NULL,NULL,NULL,NULL,233),(2306,NULL,NULL,NULL,70120030,30,NULL,NULL,NULL,NULL,233),(2307,NULL,NULL,NULL,70120040,40,NULL,NULL,NULL,NULL,233),(2308,NULL,NULL,NULL,70120050,50,NULL,NULL,NULL,NULL,233),(2309,NULL,NULL,NULL,70120060,70,NULL,NULL,NULL,NULL,233),(2310,NULL,NULL,NULL,70120070,190,NULL,NULL,NULL,NULL,233),(2311,NULL,NULL,NULL,70120380,9000,NULL,NULL,NULL,NULL,233),(2312,NULL,NULL,NULL,70120390,9010,NULL,NULL,NULL,NULL,233),(2322,NULL,NULL,NULL,1311095428,10,NULL,NULL,NULL,NULL,5),(2323,NULL,NULL,NULL,1311095438,20,NULL,NULL,NULL,NULL,5),(2324,NULL,NULL,NULL,1311095448,30,NULL,NULL,NULL,NULL,5),(2325,NULL,NULL,NULL,1311095458,40,NULL,NULL,NULL,NULL,5),(2326,NULL,NULL,NULL,1311095468,50,NULL,NULL,NULL,NULL,5),(2327,NULL,NULL,NULL,1311095478,60,NULL,NULL,NULL,NULL,5),(2328,NULL,NULL,NULL,1311095498,70,NULL,NULL,NULL,NULL,5),(2329,NULL,NULL,NULL,1311095508,80,NULL,NULL,NULL,NULL,5),(2330,NULL,NULL,NULL,1311095428,10,NULL,NULL,NULL,NULL,30),(2331,NULL,NULL,NULL,1311095438,20,NULL,NULL,NULL,NULL,30),(2332,NULL,NULL,NULL,1311095448,30,NULL,NULL,NULL,NULL,30),(2333,NULL,NULL,NULL,1311095458,40,NULL,NULL,NULL,NULL,30),(2334,NULL,NULL,NULL,1311095468,50,NULL,NULL,NULL,NULL,30),(2335,NULL,NULL,NULL,1311095478,60,NULL,NULL,NULL,NULL,30),(2336,NULL,NULL,NULL,1311095488,70,NULL,NULL,NULL,NULL,30),(2337,NULL,NULL,NULL,1311095498,80,NULL,NULL,NULL,NULL,30),(2338,NULL,NULL,NULL,1311095508,90,NULL,NULL,NULL,NULL,30),(2339,NULL,NULL,NULL,1311095518,100,NULL,NULL,NULL,NULL,30),(2340,NULL,NULL,NULL,1311095528,110,NULL,NULL,NULL,NULL,30),(2341,NULL,NULL,NULL,1311095538,10,NULL,NULL,NULL,NULL,31),(2342,NULL,NULL,NULL,1311095548,20,NULL,NULL,NULL,NULL,31),(2343,NULL,NULL,NULL,1311095558,30,NULL,NULL,NULL,NULL,31),(2344,NULL,NULL,NULL,1311095568,40,NULL,NULL,NULL,NULL,31),(2345,NULL,NULL,NULL,1311095578,10,NULL,NULL,NULL,NULL,235),(2346,NULL,NULL,NULL,1311095588,20,NULL,NULL,NULL,NULL,235),(2347,NULL,NULL,NULL,1311095598,30,NULL,NULL,NULL,NULL,235),(2348,NULL,NULL,NULL,1311095608,40,NULL,NULL,NULL,NULL,235),(2349,NULL,NULL,NULL,1311095618,50,NULL,NULL,NULL,NULL,235),(2350,NULL,NULL,NULL,1311095628,60,NULL,NULL,NULL,NULL,235),(2351,NULL,NULL,NULL,1311095638,70,NULL,NULL,NULL,NULL,235),(2352,NULL,NULL,NULL,1311095648,80,NULL,NULL,NULL,NULL,235),(2353,NULL,NULL,NULL,1311095658,90,NULL,NULL,NULL,NULL,235),(2354,NULL,NULL,NULL,1311095668,100,NULL,NULL,NULL,NULL,235),(2355,NULL,NULL,NULL,1311095678,110,NULL,NULL,NULL,NULL,235),(2356,NULL,NULL,NULL,1311095688,10,NULL,NULL,NULL,NULL,236),(2357,NULL,NULL,NULL,1311095698,20,NULL,NULL,NULL,NULL,236),(2358,NULL,NULL,NULL,1311095708,30,NULL,NULL,NULL,NULL,236),(2359,NULL,NULL,NULL,1311095718,40,NULL,NULL,NULL,NULL,236),(2360,NULL,NULL,NULL,1311095728,50,NULL,NULL,NULL,NULL,236),(2361,NULL,NULL,NULL,1311095738,60,NULL,NULL,NULL,NULL,236),(2362,NULL,NULL,NULL,1311095748,10,NULL,NULL,NULL,NULL,237),(2363,NULL,NULL,NULL,1311095758,20,NULL,NULL,NULL,NULL,237),(2364,NULL,NULL,NULL,1311095768,30,NULL,NULL,NULL,NULL,237),(2365,NULL,NULL,NULL,1311095778,40,NULL,NULL,NULL,NULL,237),(2366,NULL,NULL,NULL,1311095788,50,NULL,NULL,NULL,NULL,237),(2367,NULL,NULL,NULL,1311095798,60,NULL,NULL,NULL,NULL,237),(2368,NULL,NULL,NULL,1311095808,70,NULL,NULL,NULL,NULL,237),(2369,NULL,NULL,NULL,1311095818,80,NULL,NULL,NULL,NULL,237),(2370,NULL,NULL,NULL,1311095828,90,NULL,NULL,NULL,NULL,237),(2371,NULL,NULL,NULL,1311095838,10,NULL,NULL,NULL,NULL,238),(2372,NULL,NULL,NULL,1311095848,20,NULL,NULL,NULL,NULL,238),(2373,NULL,NULL,NULL,1311095858,30,NULL,NULL,NULL,NULL,238),(2374,NULL,NULL,NULL,1311095868,40,NULL,NULL,NULL,NULL,238),(2375,NULL,NULL,NULL,70100040,10,NULL,NULL,NULL,NULL,240),(2376,NULL,NULL,NULL,70100050,20,NULL,NULL,NULL,NULL,240),(2377,NULL,NULL,NULL,70100060,30,NULL,NULL,NULL,NULL,240),(2378,NULL,NULL,NULL,70100101,10,NULL,NULL,NULL,NULL,239),(2379,NULL,NULL,NULL,70100200,20,NULL,NULL,NULL,NULL,239),(2380,NULL,NULL,NULL,70100120,30,NULL,NULL,NULL,NULL,239),(2385,NULL,NULL,NULL,70100010,10,NULL,NULL,NULL,NULL,242),(2386,NULL,NULL,NULL,70100020,20,NULL,NULL,NULL,NULL,242),(2387,NULL,NULL,NULL,70100030,10,NULL,NULL,NULL,NULL,243),(2388,NULL,NULL,NULL,70100100,20,NULL,NULL,NULL,NULL,243),(2389,NULL,NULL,NULL,70100070,10,NULL,NULL,NULL,NULL,244),(2390,NULL,NULL,NULL,70100080,20,NULL,NULL,NULL,NULL,244),(2391,NULL,NULL,NULL,70100110,30,NULL,NULL,NULL,NULL,244),(2392,NULL,'count',NULL,70120010,30,NULL,NULL,NULL,NULL,243),(2393,NULL,'sum',NULL,70120060,50,NULL,NULL,NULL,NULL,243),(2394,NULL,NULL,NULL,70160010,10,NULL,NULL,NULL,NULL,245),(2395,NULL,NULL,NULL,70160020,20,NULL,NULL,NULL,NULL,245),(2396,NULL,NULL,NULL,70140010,10,NULL,NULL,NULL,NULL,246),(2397,NULL,NULL,NULL,70140020,20,NULL,NULL,NULL,NULL,246),(2398,NULL,NULL,NULL,60100020,20,NULL,NULL,NULL,NULL,247),(2399,NULL,NULL,NULL,60100030,30,NULL,NULL,NULL,NULL,247),(2400,NULL,NULL,NULL,60100040,40,NULL,NULL,NULL,NULL,247),(2401,NULL,NULL,NULL,60100050,50,NULL,NULL,NULL,NULL,247),(2402,NULL,NULL,NULL,60100060,60,NULL,NULL,NULL,NULL,247),(2403,NULL,NULL,NULL,60100070,70,NULL,NULL,NULL,NULL,247),(2404,NULL,NULL,NULL,60100080,80,NULL,NULL,NULL,NULL,247),(2405,NULL,NULL,NULL,60100090,90,NULL,NULL,NULL,NULL,247),(2406,NULL,NULL,NULL,60100100,190,NULL,NULL,NULL,NULL,247),(2407,NULL,'count',NULL,60100010,40,NULL,NULL,NULL,NULL,243),(2408,NULL,NULL,NULL,1331365578,30,NULL,NULL,NULL,NULL,229),(2409,NULL,NULL,NULL,90350260,100,NULL,NULL,NULL,NULL,36),(2410,NULL,NULL,NULL,111,100,NULL,NULL,NULL,NULL,41),(2411,NULL,NULL,NULL,70180010,10,NULL,NULL,NULL,NULL,248),(2412,NULL,NULL,NULL,70180020,20,NULL,NULL,NULL,NULL,248),(2413,NULL,NULL,NULL,60300020,20,NULL,NULL,NULL,NULL,249),(2414,NULL,NULL,NULL,60300030,30,NULL,NULL,NULL,NULL,249),(2415,NULL,NULL,NULL,60300040,40,NULL,NULL,NULL,NULL,249),(2416,NULL,NULL,NULL,60300050,50,NULL,NULL,NULL,NULL,249),(2417,NULL,NULL,NULL,60300060,60,NULL,NULL,NULL,NULL,249),(2418,NULL,NULL,NULL,60300070,190,NULL,NULL,NULL,NULL,249),(2419,NULL,NULL,NULL,60200020,20,NULL,NULL,NULL,NULL,250),(2420,NULL,NULL,NULL,60200030,30,NULL,NULL,NULL,NULL,250),(2421,NULL,NULL,NULL,60200040,40,NULL,NULL,NULL,NULL,250),(2422,NULL,NULL,NULL,60200050,50,NULL,NULL,NULL,NULL,250),(2423,NULL,NULL,NULL,60200060,60,NULL,NULL,NULL,NULL,250),(2424,NULL,NULL,NULL,60200070,70,NULL,NULL,NULL,NULL,250),(2425,NULL,NULL,NULL,60200080,80,NULL,NULL,NULL,NULL,250),(2426,NULL,NULL,NULL,60200090,90,NULL,NULL,NULL,NULL,250),(2427,NULL,NULL,NULL,60200100,190,NULL,NULL,NULL,NULL,250),(2428,NULL,NULL,NULL,60400020,20,NULL,NULL,NULL,NULL,251),(2429,NULL,NULL,NULL,60400030,30,NULL,NULL,NULL,NULL,251),(2430,NULL,NULL,NULL,60400040,40,NULL,NULL,NULL,NULL,251),(2431,NULL,NULL,NULL,60400050,50,NULL,NULL,NULL,NULL,251),(2432,NULL,NULL,NULL,60400060,55,NULL,NULL,NULL,NULL,251),(2433,NULL,NULL,NULL,60400070,60,NULL,NULL,NULL,NULL,251),(2434,NULL,NULL,NULL,60400080,190,NULL,NULL,NULL,NULL,251),(2435,NULL,NULL,NULL,60500020,20,NULL,NULL,NULL,NULL,252),(2436,NULL,NULL,NULL,60500030,30,NULL,NULL,NULL,NULL,252),(2437,NULL,NULL,NULL,60500040,40,NULL,NULL,NULL,NULL,252),(2438,NULL,NULL,NULL,60500050,50,NULL,NULL,NULL,NULL,252),(2439,NULL,NULL,NULL,60500060,60,NULL,NULL,NULL,NULL,252),(2440,NULL,NULL,NULL,60500070,70,NULL,NULL,NULL,NULL,252),(2441,NULL,NULL,NULL,60500080,190,NULL,NULL,NULL,NULL,252),(2447,NULL,NULL,NULL,70200030,30,NULL,NULL,NULL,NULL,254),(2448,NULL,NULL,NULL,70200040,40,NULL,NULL,NULL,NULL,254),(2449,NULL,NULL,NULL,70200050,50,NULL,NULL,NULL,NULL,254),(2450,NULL,NULL,NULL,70200060,60,NULL,NULL,NULL,NULL,254),(2451,NULL,NULL,NULL,70200070,190,NULL,NULL,NULL,NULL,254);
/*!40000 ALTER TABLE `_ModuleGridSchemeGroupField` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ModuleGroup`
--

DROP TABLE IF EXISTS `_ModuleGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ModuleGroup` (
  `tf_moduleGroupId` varchar(10) NOT NULL,
  `tf_pid` varchar(10) DEFAULT NULL,
  `tf_title` varchar(50) NOT NULL,
  `tf_description` varchar(50) DEFAULT NULL,
  `tf_isSystemGroup` bit(1) DEFAULT NULL,
  `tf_iconCls` varchar(50) DEFAULT NULL,
  `tf_iconUrl` varchar(50) DEFAULT NULL,
  `tf_iconFile` mediumblob,
  `tf_remark` mediumtext,
  PRIMARY KEY (`tf_moduleGroupId`),
  UNIQUE KEY `IX__ModuleGroup_tf_title` (`tf_title`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ModuleGroup`
--

LOCK TABLES `_ModuleGroup` WRITE;
/*!40000 ALTER TABLE `_ModuleGroup` DISABLE KEYS */;
INSERT INTO `_ModuleGroup` VALUES ('70',NULL,'销售系统',NULL,NULL,NULL,NULL,NULL,'布'),('80',NULL,'编码设置',NULL,NULL,NULL,'images/button/new.png',NULL,NULL),('90',NULL,'系统设置',NULL,NULL,NULL,'images/button/delete.png',NULL,NULL),('94',NULL,'文件资料汇编',NULL,NULL,'fa fa-retweet',NULL,NULL,' '),('95',NULL,'附件管理',NULL,NULL,'fa fa-film',NULL,NULL,NULL),('99',NULL,'系统构建模块',NULL,NULL,'fa fa-plus',NULL,NULL,'ui'),('9901',NULL,'基础模块',NULL,0x01,'fa fa-home',NULL,NULL,NULL),('9902',NULL,'Grid列表模块',NULL,0x01,'fa fa-life-ring',NULL,NULL,NULL),('9903',NULL,'Form表单模块',NULL,0x01,NULL,NULL,NULL,NULL),('9904',NULL,'明细列表模块',NULL,0x01,NULL,NULL,0x89504E470D0A1A0A0000000D49484452000000100000001008060000001FF3FF610000000467414D410000AFC837058AE90000001974455874536F6674776172650041646F626520496D616765526561647971C9653C000001DA4944415438CB9593BB6B53511CC7F37F3848290E4E1D1D5D7514412C0EA58388937793D26ACDFB611E7A6362621E0836D6EA05C526AD0951636E5E62341062326509816C19842078A51F7F57840A7D241D0EE7C5E7FBFB9EEF39C70258A6B5743AAD2493C96E229130E2F1B8F1FFDE5438954A29024E86C321E3F1986834CA890404EE0E060346A311F97C9E70387C3281582C6698D55BAD16AAAA96CDB5603088DFEFC7E7F3195305C4F2EF7EBF4FB55A25140A7D0A0402BAA669F47A3DDC6EF7F1029148E48258DEEB743A341A0D8AC52285428172B94CA552C1E974760F875FDD5427991B2DB16CD4EB759ACD26665FABD5D0759D52A984C3E198D86C36E520BC755D65D70AD9357E6CDEA2DD6E93CBE5F07ABD7BA665A96AD8EDF6AED56A550E86B869C277E1DB167C7D019AC2F8C9121E8FE7A7CBE59A3FCCEDFE24B3ACB2B326B0801F1FC0FBFBD0780A1BCBFC729FFB7C544EFFE02595EC2A349FC387206C8BD03B275413905E04EBD997470B3CBBA6B2BD227046AA06E0AD8C77ECA0C7217915D6CF68C7DD9485D465F8B20145B1FCE636E4EE091C13F80ADC99D7A6BD130B918B5097B3EE4AD5EC3A941F435C4457E75ECFF2D12C3C3CDF3483FA0B961E41EC12AC9C9E09DE0FD1B9F05D8212CB73029F9A1936DB1FDE91F8E9EED704970000000049454E44AE426082,NULL),('9905',NULL,'图表模块',NULL,0x01,NULL,NULL,0x89504E470D0A1A0A0000000D49484452000000100000001008060000001FF3FF610000000467414D410000B18F0BFC61050000016349444154384F8D93A17284301086E91BF451E81B4456525749DF00894456A60E7791B8469E44E21A591959B9B26EBBFF42B81CCD759A990C4C867CFBED9F70E7E7C8D53EA822AA2A667E7B797AE82EEB7FBC01900F37CD5C3FB67C3A7FD87F0282EE6F27A34F00E625B079EE3846E23944F68BCC39B0F3814FEF07B03FAF8034C267E4A61DD4A2ED6DD92E87B80DD0BAD5A01B4927AABBC9AF8045629169373B6D31419C9FF72AC84367A60CA3DC0EAD0180A91981AABD2F563797024D90E86B0E63A5395D006ED3949D080A83C681E9B593D0D640F1312044AC4F7CF70B60FDC0A54093B21148FC22F6BD18C8695C03BE790D6D0B946CC7D437CCB28E70A16C1A5913053DCE630BA00DD6A9723EB021575E0DEEF5347683FCB6A540519D5AA3568D15834D193014B1925B199002454B522D554FCA767472C90EC77865B001A8336250CB09C8AD1CCC6DE5E3CF02350487FB006D54465B37954B8074CB4ACFD2DFF903DEAB420C992EC5260000000049454E44AE426082,NULL),('9906',NULL,'打印模块',NULL,0x01,NULL,NULL,NULL,NULL),('9907',NULL,'导出、导入模块',NULL,0x01,NULL,NULL,NULL,NULL),('9921',NULL,'辅助模块',NULL,0x01,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `_ModuleGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ModuleSubToolbar`
--

DROP TABLE IF EXISTS `_ModuleSubToolbar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ModuleSubToolbar` (
  `tf_Id` int(11) NOT NULL,
  `tf_inSubMenu` bit(1) DEFAULT NULL,
  `tf_openInWindow` bit(1) DEFAULT NULL,
  `tf_order` int(11) NOT NULL,
  `tf_subModuleTitle` varchar(50) NOT NULL,
  `tf_subMoudleName` varchar(50) NOT NULL,
  `tf_moduleId` varchar(10) NOT NULL,
  PRIMARY KEY (`tf_Id`),
  KEY `FKs29kgb426r3fovml6pxx1a29v` (`tf_moduleId`),
  CONSTRAINT `FKs29kgb426r3fovml6pxx1a29v` FOREIGN KEY (`tf_moduleId`) REFERENCES `_Module` (`tf_moduleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ModuleSubToolbar`
--

LOCK TABLES `_ModuleSubToolbar` WRITE;
/*!40000 ALTER TABLE `_ModuleSubToolbar` DISABLE KEYS */;
INSERT INTO `_ModuleSubToolbar` VALUES (7,0x00,0x00,10,'系统菜单','_MenuModule','9920'),(11,0x00,0x00,10,'模块列表字段分组','_ModuleGridSchemeGroup','990201'),(12,0x00,0x00,10,'模块列表字段','_ModuleGridSchemeGroupField','990202'),(13,0x00,0x01,10,'模块Form字段分组','_ModuleFormSchemeGroup','990301'),(14,0x00,0x01,10,'模块Form字段','_ModuleFormSchemeGroupField','990302'),(15,0x00,0x00,10,'模块字段','_ModuleField','990102'),(22,0x00,0x01,10,'模块明细显示字段','_ModuleDetailSchemeField','990401'),(24,0x00,0x01,10,'用户','_User','9041'),(25,0x00,0x00,1,'记录打印方案分组','_PrintSchemeGroup','9941'),(26,0x00,0x00,1,'记录打印方案分组字段','_PrintSchemeGroupCell','9942'),(28,0x00,0x00,10,'模块审批人员','_ModuleApproveUser','9045'),(31,0x00,0x01,10,'用户附加部门','_UserAdditionDepartment','9035');
/*!40000 ALTER TABLE `_ModuleSubToolbar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_NumberGroup`
--

DROP TABLE IF EXISTS `_NumberGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_NumberGroup` (
  `tf_numberGroupId` int(11) NOT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tf_numberGroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_NumberGroup`
--

LOCK TABLES `_NumberGroup` WRITE;
/*!40000 ALTER TABLE `_NumberGroup` DISABLE KEYS */;
INSERT INTO `_NumberGroup` VALUES (0,'无',NULL),(1,'年',NULL),(2,'年月',NULL),(3,'年季',NULL),(4,'年月日',NULL),(10,'按金额大小分组1',NULL),(11,'按金额大小分组2',NULL),(12,'比例',NULL);
/*!40000 ALTER TABLE `_NumberGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_NumberGroupDetail`
--

DROP TABLE IF EXISTS `_NumberGroupDetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_NumberGroupDetail` (
  `tf_numberGroupDetailId` int(11) NOT NULL,
  `tf_condition1` varchar(50) DEFAULT NULL,
  `tf_condition2` varchar(50) DEFAULT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_order` int(11) NOT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_numberGroupId` int(11) NOT NULL,
  PRIMARY KEY (`tf_numberGroupDetailId`),
  KEY `FK7apc3pbun61srkqxl5uinqhbs` (`tf_numberGroupId`),
  CONSTRAINT `FK7apc3pbun61srkqxl5uinqhbs` FOREIGN KEY (`tf_numberGroupId`) REFERENCES `_NumberGroup` (`tf_numberGroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_NumberGroupDetail`
--

LOCK TABLES `_NumberGroupDetail` WRITE;
/*!40000 ALTER TABLE `_NumberGroupDetail` DISABLE KEYS */;
INSERT INTO `_NumberGroupDetail` VALUES (1,NULL,'<1000000','小于100万',1,NULL,10),(2,'>=1000000','<10000000','大于等于100万小于1000万',2,NULL,10),(3,NULL,'<0','负数',1,NULL,11),(5,'=0',NULL,'零',2,NULL,11),(6,'>0',NULL,'正数',3,NULL,11),(7,'>=10000000','<100000000','大于等于1000万小于1亿',3,NULL,10),(8,'>100000000',NULL,'大于等于1亿',4,NULL,10),(9,NULL,'<.20','小于20％',1,NULL,12),(10,'>=.20','<.50','20％至50％',2,NULL,12),(11,'>=.50','<.80','50％至80％',3,NULL,12),(12,'>=.80','<1','80％以上不到100％',4,NULL,12),(13,'>=1',NULL,'100％',5,NULL,12);
/*!40000 ALTER TABLE `_NumberGroupDetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_Popedom`
--

DROP TABLE IF EXISTS `_Popedom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_Popedom` (
  `tf_popedomId` int(11) NOT NULL,
  `tf_allowApprove` int(11) DEFAULT NULL,
  `tf_allowAuditing` int(11) DEFAULT NULL,
  `tf_allowBrowse` int(11) DEFAULT NULL,
  `tf_allowDelete` int(11) DEFAULT NULL,
  `tf_allowEdit` int(11) DEFAULT NULL,
  `tf_allowEditDirect` int(11) DEFAULT NULL,
  `tf_allowExec` int(11) DEFAULT NULL,
  `tf_allowInsert` int(11) DEFAULT NULL,
  `tf_allowPayment` int(11) DEFAULT NULL,
  `tf_moduleId` varchar(255) DEFAULT NULL,
  `tf_roleId` varchar(255) DEFAULT NULL,
  `tf_attachmentBrowse` int(11) DEFAULT NULL,
  `tf_attachmentDelete` int(11) DEFAULT NULL,
  `tf_attachmentEdit` int(11) DEFAULT NULL,
  `tf_attachmentInsert` int(11) DEFAULT NULL,
  PRIMARY KEY (`tf_popedomId`),
  KEY `kkkdfs_idx` (`tf_moduleId`),
  KEY `FK__Popedom__Role_idx` (`tf_roleId`),
  CONSTRAINT `FK__Popedom__Module` FOREIGN KEY (`tf_moduleId`) REFERENCES `_Module` (`tf_moduleId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK__Popedom__Role` FOREIGN KEY (`tf_roleId`) REFERENCES `_Role` (`tf_roleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_Popedom`
--

LOCK TABLES `_Popedom` WRITE;
/*!40000 ALTER TABLE `_Popedom` DISABLE KEYS */;
INSERT INTO `_Popedom` VALUES (8659,0,0,0,0,0,0,0,0,0,'8090','1010',0,0,0,0),(8660,0,0,0,0,0,0,0,0,0,'8091','1010',0,0,0,0),(8661,0,0,0,0,0,0,0,0,0,'8092','1010',0,0,0,0),(8662,0,0,0,0,0,0,0,0,0,'9000','1010',0,0,0,0),(8663,0,0,0,0,0,0,0,0,0,'9001','1010',0,0,0,0),(8664,0,0,0,0,0,0,0,0,0,'9005','1010',0,0,0,0),(8665,0,0,0,0,0,0,0,0,0,'9010','1010',0,0,0,0),(8666,0,0,0,0,0,0,0,0,0,'9011','1010',0,0,0,0),(8667,0,0,0,0,0,0,0,0,0,'9030','1010',0,0,0,0),(8668,0,0,0,0,0,0,0,0,0,'9035','1010',0,0,0,0),(8669,0,0,0,0,0,0,0,0,0,'9040','1010',0,0,0,0),(8670,0,0,0,0,0,0,0,0,0,'9041','1010',0,0,0,0),(8671,0,0,0,0,0,0,0,0,0,'9042','1010',0,0,0,0),(8672,0,0,0,0,0,0,0,0,0,'9045','1010',0,0,0,0),(8673,0,0,0,0,0,0,0,0,0,'9046','1010',0,0,0,0),(8674,0,0,0,0,0,0,0,0,0,'9050','1010',0,0,0,0),(8675,0,0,0,0,0,0,0,0,0,'9052','1010',0,0,0,0),(8676,0,0,0,0,0,0,0,0,0,'9055','1010',0,0,0,0),(8677,0,0,0,0,0,0,0,0,0,'9090','1010',0,0,0,0),(8678,0,0,0,0,0,0,0,0,0,'9091','1010',0,0,0,0),(8679,0,0,0,0,0,0,0,0,0,'9092','1010',0,0,0,0),(8680,0,0,0,0,0,0,0,0,0,'9401','1010',0,0,0,0),(8681,0,0,0,0,0,0,0,0,0,'9405','1010',0,0,0,0),(8682,0,0,0,0,0,0,0,0,0,'9502','1010',0,0,0,0),(8683,0,0,0,0,0,0,0,0,0,'9503','1010',0,0,0,0),(8684,0,0,0,0,0,0,0,0,0,'9504','1010',0,0,0,0),(8685,0,0,0,0,0,0,0,0,0,'9505','1010',0,0,0,0),(8686,0,0,0,0,0,0,0,0,0,'990101','1010',0,0,0,0),(8687,0,0,0,0,0,0,0,0,0,'990102','1010',0,0,0,0),(8688,0,0,0,0,0,0,0,0,0,'990103','1010',0,0,0,0),(8689,0,0,0,0,0,0,0,0,0,'990110','1010',0,0,0,0),(8690,0,0,0,0,0,0,0,0,0,'990115','1010',0,0,0,0),(8691,0,0,0,0,0,0,0,0,0,'990201','1010',0,0,0,0),(8692,0,0,0,0,0,0,0,0,0,'990202','1010',0,0,0,0),(8693,0,0,0,0,0,0,0,0,0,'990203','1010',0,0,0,0),(8694,0,0,0,0,0,0,0,0,0,'990301','1010',0,0,0,0),(8695,0,0,0,0,0,0,0,0,0,'990302','1010',0,0,0,0),(8696,0,0,0,0,0,0,0,0,0,'990303','1010',0,0,0,0),(8697,0,0,0,0,0,0,0,0,0,'990204','1010',0,0,0,0),(8698,0,0,0,0,0,0,0,0,0,'990401','1010',0,0,0,0),(8699,0,0,0,0,0,0,0,0,0,'990402','1010',0,0,0,0),(8700,0,0,0,0,0,0,0,0,0,'990501','1010',0,0,0,0),(8701,0,0,0,0,0,0,0,0,0,'9920','1010',0,0,0,0),(8702,0,0,0,0,0,0,0,0,0,'9921','1010',0,0,0,0),(8703,0,0,0,0,0,0,0,0,0,'990120','1010',0,0,0,0),(8704,0,0,0,0,0,0,0,0,0,'9935','1010',0,0,0,0),(8705,0,0,0,0,0,0,0,0,0,'9937','1010',0,0,0,0),(8706,0,0,0,0,0,0,0,0,0,'9941','1010',0,0,0,0),(8707,0,0,0,0,0,0,0,0,0,'9942','1010',0,0,0,0),(8708,0,0,0,0,0,0,0,0,0,'9943','1010',0,0,0,0),(9365,0,1,1,1,1,0,0,1,0,'7010','0005',1,1,1,1),(9366,1,0,1,1,1,0,0,1,0,'7012','0005',0,0,0,0),(9367,0,0,0,0,0,0,0,0,0,'8090','0005',0,0,0,0),(9368,0,0,0,0,0,0,0,0,0,'8091','0005',0,0,0,0),(9369,0,0,0,0,0,0,0,0,0,'8092','0005',0,0,0,0),(9370,0,0,1,0,1,0,0,0,0,'9000','0005',0,0,0,0),(9371,0,0,0,0,0,0,0,0,0,'9001','0005',0,0,0,0),(9372,0,0,0,0,0,0,0,0,0,'9005','0005',0,0,0,0),(9373,0,0,1,1,1,0,0,1,0,'9010','0005',0,0,0,0),(9374,0,0,1,1,1,0,0,1,0,'9011','0005',0,0,0,0),(9375,0,0,0,0,0,0,0,0,0,'901110','0005',0,0,0,0),(9376,0,0,0,0,0,0,0,0,0,'901120','0005',0,0,0,0),(9377,0,0,1,1,1,0,0,1,0,'9030','0005',0,0,0,0),(9378,0,0,1,1,1,0,0,1,0,'9035','0005',0,0,0,0),(9379,0,0,1,1,1,0,0,1,0,'9038','0005',0,0,0,0),(9380,0,0,1,1,1,0,0,1,0,'903810','0005',0,0,0,0),(9381,0,0,1,1,1,0,0,1,0,'9039','0005',0,0,0,0),(9382,0,0,1,1,1,0,0,1,0,'9040','0005',0,0,0,0),(9383,0,0,1,1,1,0,0,1,0,'9041','0005',0,0,0,0),(9384,0,0,1,1,1,0,0,1,0,'9042','0005',0,0,0,0),(9385,0,0,1,1,1,0,0,1,0,'9043','0005',0,0,0,0),(9386,0,0,1,1,1,0,0,1,0,'9045','0005',0,0,0,0),(9387,0,0,1,1,1,0,0,1,0,'9046','0005',0,0,0,0),(9388,0,0,0,0,0,0,0,0,0,'9050','0005',0,0,0,0),(9389,0,0,0,0,0,0,0,0,0,'9052','0005',0,0,0,0),(9390,0,0,0,0,0,0,0,0,0,'9055','0005',0,0,0,0),(9391,0,0,1,1,1,0,0,1,0,'9090','0005',0,0,0,0),(9392,0,0,0,0,0,0,0,0,0,'9091','0005',0,0,0,0),(9393,0,0,0,0,0,0,0,0,0,'9092','0005',0,0,0,0),(9394,0,0,0,0,0,0,0,0,0,'9401','0005',0,0,0,0),(9395,0,0,0,0,0,0,0,0,0,'9405','0005',0,0,0,0),(9396,0,0,0,0,0,0,0,0,0,'9502','0005',0,0,0,0),(9397,0,0,0,0,0,0,0,0,0,'9503','0005',0,0,0,0),(9398,0,0,0,0,0,0,0,0,0,'9504','0005',0,0,0,0),(9399,0,0,0,0,0,0,0,0,0,'9505','0005',0,0,0,0),(9400,0,0,0,0,0,0,0,0,0,'9506','0005',0,0,0,0),(9401,0,0,0,0,0,0,0,0,0,'9920','0005',0,0,0,0),(9402,0,0,0,0,0,0,0,0,0,'9921','0005',0,0,0,0),(9403,0,0,0,0,0,0,0,0,0,'990101','0005',0,0,0,0),(9404,0,0,0,0,0,0,0,0,0,'990102','0005',0,0,0,0),(9405,0,0,0,0,0,0,0,0,0,'990103','0005',0,0,0,0),(9406,0,0,0,0,0,0,0,0,0,'990110','0005',0,0,0,0),(9407,0,0,0,0,0,0,0,0,0,'990115','0005',0,0,0,0),(9408,0,0,0,0,0,0,0,0,0,'990120','0005',0,0,0,0),(9409,0,0,0,0,0,0,0,0,0,'990130','0005',0,0,0,0),(9410,0,0,0,0,0,0,0,0,0,'990201','0005',0,0,0,0),(9411,0,0,0,0,0,0,0,0,0,'990202','0005',0,0,0,0),(9412,0,0,0,0,0,0,0,0,0,'990203','0005',0,0,0,0),(9413,0,0,0,0,0,0,0,0,0,'990204','0005',0,0,0,0),(9414,0,0,0,0,0,0,0,0,0,'990301','0005',0,0,0,0),(9415,0,0,0,0,0,0,0,0,0,'990302','0005',0,0,0,0),(9416,0,0,0,0,0,0,0,0,0,'990303','0005',0,0,0,0),(9417,0,0,0,0,0,0,0,0,0,'990401','0005',0,0,0,0),(9418,0,0,0,0,0,0,0,0,0,'990402','0005',0,0,0,0),(9419,0,0,0,0,0,0,0,0,0,'990501','0005',0,0,0,0),(9420,0,0,0,0,0,0,0,0,0,'9941','0005',0,0,0,0),(9421,0,0,0,0,0,0,0,0,0,'9942','0005',0,0,0,0),(9422,0,0,0,0,0,0,0,0,0,'9943','0005',0,0,0,0),(9423,0,0,0,0,0,0,0,0,0,'9935','0005',0,0,0,0),(9424,0,0,0,0,0,0,0,0,0,'9937','0005',0,0,0,0),(9565,0,0,1,1,1,0,0,1,0,'6010','0000',0,0,0,0),(9566,0,0,1,1,1,0,0,1,0,'6020','0000',0,0,0,0),(9567,0,0,1,1,1,0,0,1,0,'6030','0000',0,0,0,0),(9568,0,0,1,1,1,0,0,1,0,'6040','0000',0,0,0,0),(9569,0,0,1,1,1,0,0,1,0,'6050','0000',0,0,0,0),(9571,0,1,1,1,1,0,0,1,0,'7010','0000',1,1,1,1),(9572,1,0,1,1,1,0,0,1,0,'7012','0000',0,0,0,0),(9573,0,0,1,1,1,0,0,1,0,'7014','0000',0,0,0,0),(9574,0,0,1,1,1,0,0,1,0,'7016','0000',0,0,0,0),(9575,0,0,1,1,1,0,0,1,0,'7018','0000',0,0,0,0),(9576,0,0,1,1,1,0,0,1,0,'7020','0000',0,0,0,0),(9577,0,0,1,1,1,0,0,1,0,'8090','0000',0,0,0,0),(9578,0,0,1,1,1,0,0,1,0,'8091','0000',0,0,0,0),(9579,0,0,1,1,1,0,0,1,0,'8092','0000',0,0,0,0),(9580,0,0,1,0,1,0,0,0,0,'9000','0000',0,0,0,0),(9581,0,0,1,0,1,0,0,0,0,'9001','0000',0,0,0,0),(9582,0,0,1,0,1,0,0,0,0,'9005','0000',0,0,0,0),(9583,0,0,1,1,1,0,0,1,0,'9010','0000',0,0,0,0),(9584,0,0,1,1,1,0,0,1,0,'9011','0000',0,0,0,0),(9585,0,0,0,0,0,0,0,0,0,'901110','0000',0,0,0,0),(9586,0,0,0,0,0,0,0,0,0,'901120','0000',0,0,0,0),(9587,0,0,1,1,1,0,0,1,0,'9030','0000',0,0,0,0),(9588,0,0,1,1,1,0,0,1,0,'9035','0000',0,0,0,0),(9589,0,0,1,1,1,0,0,1,0,'9038','0000',0,0,0,0),(9590,0,0,1,1,1,0,0,1,0,'903810','0000',0,0,0,0),(9591,0,0,1,1,1,0,0,1,0,'9039','0000',0,0,0,0),(9592,0,0,1,1,1,0,0,1,0,'9040','0000',0,0,0,0),(9593,0,0,1,1,1,0,0,1,0,'9041','0000',0,0,0,0),(9594,0,0,1,1,1,0,0,1,0,'9042','0000',0,0,0,0),(9595,0,0,1,1,1,0,0,1,0,'9043','0000',0,0,0,0),(9596,0,0,1,1,1,0,0,1,0,'9045','0000',0,0,0,0),(9597,0,0,1,1,1,0,0,1,0,'9046','0000',0,0,0,0),(9598,0,0,1,1,1,0,0,1,0,'9050','0000',0,0,0,0),(9599,0,0,1,1,1,0,0,1,0,'9052','0000',0,0,0,0),(9600,0,0,1,1,1,0,0,1,0,'9055','0000',0,0,0,0),(9601,0,0,1,1,1,0,0,1,0,'9090','0000',0,0,0,0),(9602,0,0,1,0,0,0,0,0,0,'9091','0000',0,0,0,0),(9603,0,0,1,0,0,0,0,0,0,'9092','0000',0,0,0,0),(9604,0,0,0,0,0,0,0,0,0,'9401','0000',0,0,0,0),(9605,0,0,0,0,0,0,0,0,0,'9405','0000',0,0,0,0),(9606,0,0,1,1,1,0,0,1,0,'9502','0000',0,0,0,0),(9607,0,0,1,1,1,0,0,1,0,'9503','0000',0,0,0,0),(9608,0,0,1,1,1,0,0,1,0,'9504','0000',0,0,0,0),(9609,0,0,1,0,0,0,0,0,0,'9505','0000',0,0,0,0),(9610,0,0,0,0,0,0,0,0,0,'9506','0000',0,0,0,0),(9611,0,0,1,1,1,0,0,1,0,'9920','0000',0,0,0,0),(9612,0,0,1,1,1,0,0,1,0,'9921','0000',0,0,0,0),(9613,0,0,1,1,1,0,0,1,0,'990101','0000',0,0,0,0),(9614,0,0,1,0,1,0,0,0,0,'990102','0000',0,0,0,0),(9615,0,0,1,1,1,0,0,1,0,'990103','0000',0,0,0,0),(9616,0,0,1,1,1,0,0,1,0,'990110','0000',0,0,0,0),(9617,0,0,1,1,1,0,0,1,0,'990115','0000',0,0,0,0),(9618,0,0,1,1,1,0,0,1,0,'990120','0000',0,0,0,0),(9619,0,0,1,1,1,0,0,1,0,'990130','0000',0,0,0,0),(9620,0,0,1,1,1,0,0,1,0,'990201','0000',0,0,0,0),(9621,0,0,1,1,1,0,0,1,0,'990202','0000',0,0,0,0),(9622,0,0,1,1,1,0,0,0,0,'990203','0000',0,0,0,0),(9623,0,0,1,1,1,0,0,1,0,'990204','0000',0,0,0,0),(9624,0,0,1,1,1,0,0,1,0,'990301','0000',0,0,0,0),(9625,0,0,1,1,1,0,0,1,0,'990302','0000',0,0,0,0),(9626,0,0,1,1,1,0,0,0,0,'990303','0000',0,0,0,0),(9627,0,0,1,1,1,0,0,1,0,'990401','0000',0,0,0,0),(9628,0,0,1,1,1,0,0,0,0,'990402','0000',0,0,0,0),(9629,0,0,1,1,1,0,0,1,0,'990501','0000',0,0,0,0),(9630,0,0,1,1,1,0,0,1,0,'9941','0000',0,0,0,0),(9631,0,0,1,1,1,0,0,1,0,'9942','0000',0,0,0,0),(9632,0,0,1,1,1,0,0,1,0,'9943','0000',0,0,0,0),(9633,0,0,1,1,1,0,0,1,0,'9935','0000',0,0,0,0),(9634,0,0,1,1,1,0,0,1,0,'9937','0000',0,0,0,0),(9635,0,0,1,1,1,0,0,1,0,'6010','0010',0,0,0,0),(9636,0,0,1,1,1,0,0,1,0,'6020','0010',0,0,0,0),(9637,0,0,1,1,1,0,0,1,0,'6030','0010',0,0,0,0),(9638,0,0,1,1,1,0,0,1,0,'6040','0010',0,0,0,0),(9639,0,0,1,1,1,0,0,1,0,'6050','0010',0,0,0,0),(9641,0,1,1,1,1,0,0,1,0,'7010','0010',1,1,1,1),(9642,1,0,1,1,1,0,0,1,0,'7012','0010',0,0,0,0),(9643,0,0,1,1,1,0,0,1,0,'7014','0010',0,0,0,0),(9644,0,0,1,1,1,0,0,1,0,'7016','0010',0,0,0,0),(9645,0,0,1,1,1,0,0,1,0,'7018','0010',0,0,0,0),(9646,0,0,1,1,1,0,0,1,0,'7020','0010',0,0,0,0),(9647,0,0,0,0,0,0,0,0,0,'8090','0010',0,0,0,0),(9648,0,0,0,0,0,0,0,0,0,'8091','0010',0,0,0,0),(9649,0,0,0,0,0,0,0,0,0,'8092','0010',0,0,0,0),(9650,0,0,1,0,0,0,0,0,0,'9000','0010',0,0,0,0),(9651,0,0,0,0,0,0,0,0,0,'9001','0010',0,0,0,0),(9652,0,0,0,0,0,0,0,0,0,'9005','0010',0,0,0,0),(9653,0,0,1,0,0,0,0,0,0,'9010','0010',0,0,0,0),(9654,0,0,1,0,0,0,0,0,0,'9011','0010',0,0,0,0),(9655,0,0,0,0,0,0,0,0,0,'901110','0010',0,0,0,0),(9656,0,0,0,0,0,0,0,0,0,'901120','0010',0,0,0,0),(9657,0,0,1,0,0,0,0,0,0,'9030','0010',0,0,0,0),(9658,0,0,1,0,0,0,0,0,0,'9035','0010',0,0,0,0),(9659,0,0,0,0,0,0,0,0,0,'9038','0010',0,0,0,0),(9660,0,0,0,0,0,0,0,0,0,'903810','0010',0,0,0,0),(9661,0,0,0,0,0,0,0,0,0,'9039','0010',0,0,0,0),(9662,0,0,0,0,0,0,0,0,0,'9040','0010',0,0,0,0),(9663,0,0,0,0,0,0,0,0,0,'9041','0010',0,0,0,0),(9664,0,0,0,0,0,0,0,0,0,'9042','0010',0,0,0,0),(9665,0,0,0,0,0,0,0,0,0,'9043','0010',0,0,0,0),(9666,0,0,0,0,0,0,0,0,0,'9045','0010',0,0,0,0),(9667,0,0,0,0,0,0,0,0,0,'9046','0010',0,0,0,0),(9668,0,0,0,0,0,0,0,0,0,'9050','0010',0,0,0,0),(9669,0,0,0,0,0,0,0,0,0,'9052','0010',0,0,0,0),(9670,0,0,0,0,0,0,0,0,0,'9055','0010',0,0,0,0),(9671,0,0,0,0,0,0,0,0,0,'9090','0010',0,0,0,0),(9672,0,0,1,0,0,0,0,0,0,'9091','0010',0,0,0,0),(9673,0,0,1,0,0,0,0,0,0,'9092','0010',0,0,0,0),(9674,0,0,0,0,0,0,0,0,0,'9401','0010',0,0,0,0),(9675,0,0,0,0,0,0,0,0,0,'9405','0010',0,0,0,0),(9676,0,0,0,0,0,0,0,0,0,'9502','0010',0,0,0,0),(9677,0,0,0,0,0,0,0,0,0,'9503','0010',0,0,0,0),(9678,0,0,0,0,0,0,0,0,0,'9504','0010',0,0,0,0),(9679,0,0,1,0,0,0,0,0,0,'9505','0010',0,0,0,0),(9680,0,0,0,0,0,0,0,0,0,'9506','0010',0,0,0,0),(9681,0,0,0,0,0,0,0,0,0,'9920','0010',0,0,0,0),(9682,0,0,0,0,0,0,0,0,0,'9921','0010',0,0,0,0),(9683,0,0,0,0,0,0,0,0,0,'990101','0010',0,0,0,0),(9684,0,0,0,0,0,0,0,0,0,'990102','0010',0,0,0,0),(9685,0,0,0,0,0,0,0,0,0,'990103','0010',0,0,0,0),(9686,0,0,0,0,0,0,0,0,0,'990110','0010',0,0,0,0),(9687,0,0,0,0,0,0,0,0,0,'990115','0010',0,0,0,0),(9688,0,0,0,0,0,0,0,0,0,'990120','0010',0,0,0,0),(9689,0,0,0,0,0,0,0,0,0,'990130','0010',0,0,0,0),(9690,0,0,0,0,0,0,0,0,0,'990201','0010',0,0,0,0),(9691,0,0,0,0,0,0,0,0,0,'990202','0010',0,0,0,0),(9692,0,0,0,0,0,0,0,0,0,'990203','0010',0,0,0,0),(9693,0,0,0,0,0,0,0,0,0,'990204','0010',0,0,0,0),(9694,0,0,0,0,0,0,0,0,0,'990301','0010',0,0,0,0),(9695,0,0,0,0,0,0,0,0,0,'990302','0010',0,0,0,0),(9696,0,0,0,0,0,0,0,0,0,'990303','0010',0,0,0,0),(9697,0,0,0,0,0,0,0,0,0,'990401','0010',0,0,0,0),(9698,0,0,0,0,0,0,0,0,0,'990402','0010',0,0,0,0),(9699,0,0,0,0,0,0,0,0,0,'990501','0010',0,0,0,0),(9700,0,0,0,0,0,0,0,0,0,'9941','0010',0,0,0,0),(9701,0,0,0,0,0,0,0,0,0,'9942','0010',0,0,0,0),(9702,0,0,0,0,0,0,0,0,0,'9943','0010',0,0,0,0),(9703,0,0,0,0,0,0,0,0,0,'9935','0010',0,0,0,0),(9704,0,0,0,0,0,0,0,0,0,'9937','0010',0,0,0,0);
/*!40000 ALTER TABLE `_Popedom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_Position`
--

DROP TABLE IF EXISTS `_Position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_Position` (
  `tf_positionId` varchar(10) NOT NULL,
  `tf_effect` varchar(100) DEFAULT NULL,
  `tf_positionName` varchar(50) NOT NULL,
  `tf_preview` varchar(255) DEFAULT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tf_positionId`),
  UNIQUE KEY `UK_rmxnlki1d4pcqji1aaqc5nwao` (`tf_positionName`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_Position`
--

LOCK TABLES `_Position` WRITE;
/*!40000 ALTER TABLE `_Position` DISABLE KEYS */;
INSERT INTO `_Position` VALUES ('00','负责系统设置，操作权限的分配','管理员',NULL,NULL),('10',NULL,'主要领导',NULL,NULL),('20',NULL,'分管领导',NULL,NULL),('30',NULL,'部门领导',NULL,NULL),('35',NULL,'科室领导',NULL,NULL),('40',NULL,'部门助理',NULL,NULL),('50',NULL,'操作员',NULL,NULL),('90','其他未定义的职务','其他',NULL,NULL);
/*!40000 ALTER TABLE `_Position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_PrintScheme`
--

DROP TABLE IF EXISTS `_PrintScheme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_PrintScheme` (
  `tf_printSchemeId` int(11) NOT NULL,
  `tf_createDate` datetime DEFAULT NULL,
  `tf_createMen` varchar(20) DEFAULT NULL,
  `tf_isSubScheme` bit(1) DEFAULT NULL,
  `tf_isSystemScheme` bit(1) DEFAULT NULL,
  `tf_otherSetting` varchar(255) DEFAULT NULL,
  `tf_schemeName` varchar(50) NOT NULL,
  `tf_schemeOrder` int(11) NOT NULL,
  `tf_moduleId` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`tf_printSchemeId`),
  KEY `FKvw2cu3fdokeumb9si4rq4oja` (`tf_moduleId`),
  CONSTRAINT `FKvw2cu3fdokeumb9si4rq4oja` FOREIGN KEY (`tf_moduleId`) REFERENCES `_Module` (`tf_moduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_PrintScheme`
--

LOCK TABLES `_PrintScheme` WRITE;
/*!40000 ALTER TABLE `_PrintScheme` DISABLE KEYS */;
/*!40000 ALTER TABLE `_PrintScheme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_PrintSchemeGroup`
--

DROP TABLE IF EXISTS `_PrintSchemeGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_PrintSchemeGroup` (
  `tf_printSchemeGroupId` int(11) NOT NULL,
  `tf_border` int(11) DEFAULT NULL,
  `tf_cellpadding` int(11) DEFAULT NULL,
  `tf_cssStyle` varchar(50) DEFAULT NULL,
  `tf_groupWidth` int(11) DEFAULT NULL,
  `tf_numCols` int(11) DEFAULT NULL,
  `tf_otherSetting` varchar(255) DEFAULT NULL,
  `tf_printGroupName` varchar(50) DEFAULT NULL,
  `tf_printGroupOrder` int(11) DEFAULT NULL,
  `tf_widths` varchar(80) DEFAULT NULL,
  `tf_printSchemeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`tf_printSchemeGroupId`),
  KEY `FKtq6i0651aqto12dajtehliv3f` (`tf_printSchemeId`),
  CONSTRAINT `FKtq6i0651aqto12dajtehliv3f` FOREIGN KEY (`tf_printSchemeId`) REFERENCES `_PrintScheme` (`tf_printSchemeId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_PrintSchemeGroup`
--

LOCK TABLES `_PrintSchemeGroup` WRITE;
/*!40000 ALTER TABLE `_PrintSchemeGroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `_PrintSchemeGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_PrintSchemeGroupCell`
--

DROP TABLE IF EXISTS `_PrintSchemeGroupCell`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_PrintSchemeGroupCell` (
  `tf_cellId` int(11) NOT NULL,
  `tf_align` varchar(255) DEFAULT NULL,
  `tf_colspan` int(11) DEFAULT NULL,
  `tf_cssStyle` varchar(50) DEFAULT NULL,
  `tf_disabled` bit(1) DEFAULT NULL,
  `tf_height` int(11) DEFAULT NULL,
  `tf_isEndrow` bit(1) DEFAULT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_order` int(11) DEFAULT NULL,
  `tf_otherSetting` varchar(255) DEFAULT NULL,
  `tf_printText` varchar(255) DEFAULT NULL,
  `tf_rowspan` int(11) DEFAULT NULL,
  `tf_valign` varchar(255) DEFAULT NULL,
  `tf_width` varchar(10) DEFAULT NULL,
  `tf_printSchemeGroupId` int(11) DEFAULT NULL,
  PRIMARY KEY (`tf_cellId`),
  KEY `FK74on1tj4tg3v6xnx7ietalppv` (`tf_printSchemeGroupId`),
  CONSTRAINT `FK74on1tj4tg3v6xnx7ietalppv` FOREIGN KEY (`tf_printSchemeGroupId`) REFERENCES `_PrintSchemeGroup` (`tf_printSchemeGroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_PrintSchemeGroupCell`
--

LOCK TABLES `_PrintSchemeGroupCell` WRITE;
/*!40000 ALTER TABLE `_PrintSchemeGroupCell` DISABLE KEYS */;
/*!40000 ALTER TABLE `_PrintSchemeGroupCell` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_PropertyType`
--

DROP TABLE IF EXISTS `_PropertyType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_PropertyType` (
  `tf_propertyTypeId` int(11) NOT NULL,
  `tf_canInput` bit(1) DEFAULT NULL,
  `tf_canmultSelected` bit(1) DEFAULT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_value` varchar(255) NOT NULL,
  PRIMARY KEY (`tf_propertyTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_PropertyType`
--

LOCK TABLES `_PropertyType` WRITE;
/*!40000 ALTER TABLE `_PropertyType` DISABLE KEYS */;
INSERT INTO `_PropertyType` VALUES (0,0x00,0x00,'空值','空值','空值'),(1,0x00,0x00,'FormFieldSet类型',NULL,'none,tab,accordion,intabpanel,intab'),(5,0x00,0x00,'审批结果',NULL,'同意,不同意'),(6,0x00,0x00,'发票类型',NULL,'增值税专用发票,工业企业产品销售发票,商业零售统一发票,建筑安装企业统一发票,税务机关代开统一发票,服务业通用发票,其他'),(7,0x00,0x00,'合同状态',NULL,'未审核,已审核,正在执行,完成过半,已竣工,已验收,已决算,已审计,已完成,存档'),(8,0x00,0x00,'请款金额控制',NULL,'10-不得超出结算金额,20-超过过程监控金额提醒,30-不得超过过程监控金额,40-超过付款计划提醒,50-不得超过付款计划'),(9,0x01,0x00,'请款类型',NULL,'工程进度款,工程预付款,完工工程支付款,工程尾款'),(12,0x00,0x00,'左右对齐方式',NULL,'left,middle,right'),(13,0x00,0x00,'上下对齐方式',NULL,'top,middle,bottom'),(14,0x00,0x00,'发包方式',NULL,'公开招标,邀请招标,直接发包,增资项目,合同结算'),(15,0x01,0x00,'付款原因',NULL,'预付款,提交方案设计文本七天内,施工图纸审查通过后七天内,项目竣工验收通过,工程审计结束且出具审计报告后,竣工一年后,竣工二年后'),(16,0x00,0x00,'Form类型',NULL,'display,new,edit'),(17,0x01,0x01,'资金来源',NULL,'镇级预算,区级预算,市级预算,镇区级预算,市区级预算,村级预算,市镇村预算,其他'),(18,0x00,0x00,'招标方式',NULL,'公开招标,邀请招标,直接发包,增资项目,合同结算'),(19,0x01,0x00,'招标组织方式',NULL,'镇招投标中心,管理区领导小组,市招投标中心'),(20,0x00,0x00,'项目计划状态',NULL,'尚未执行,正在执行,已完成');
/*!40000 ALTER TABLE `_PropertyType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_Report`
--

DROP TABLE IF EXISTS `_Report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_Report` (
  `tf_reportId` int(11) NOT NULL,
  `tf_inputdate` datetime NOT NULL,
  `tf_inputmen` varchar(10) NOT NULL,
  `tf_baseModuleName` varchar(50) NOT NULL,
  `tf_groupDefine` varchar(255) DEFAULT NULL,
  `tf_iconURL` varchar(50) DEFAULT NULL,
  `tf_isShowDetail` bit(1) DEFAULT NULL,
  `tf_isShowTotal` bit(1) DEFAULT NULL,
  `tf_isSystem` bit(1) DEFAULT NULL,
  `tf_orderId` int(11) NOT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_title` varchar(50) DEFAULT NULL,
  `tf_type` varchar(50) DEFAULT NULL,
  `tf_reportGroupId` varchar(10) NOT NULL,
  PRIMARY KEY (`tf_reportId`),
  KEY `FK1vpje9w5rxd17fpbwtgm4rve5` (`tf_reportGroupId`),
  CONSTRAINT `FK__Report__ReportGroup` FOREIGN KEY (`tf_reportGroupId`) REFERENCES `_ReportGroup` (`tf_reportGroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_Report`
--

LOCK TABLES `_Report` WRITE;
/*!40000 ALTER TABLE `_Report` DISABLE KEYS */;
INSERT INTO `_Report` VALUES (2,'2015-10-01 16:17:33','管理员','Province','[]',NULL,0x00,NULL,NULL,20,NULL,'省份1',NULL,'10'),(3,'2015-11-08 13:23:11','管理员','City','[]',NULL,0x00,0x00,NULL,30,NULL,'市',NULL,'10');
/*!40000 ALTER TABLE `_Report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ReportChart`
--

DROP TABLE IF EXISTS `_ReportChart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ReportChart` (
  `tf_chartId` int(11) NOT NULL,
  `tf_inputdate` datetime NOT NULL,
  `tf_inputmen` varchar(10) NOT NULL,
  `tf_categoryField` varchar(50) NOT NULL,
  `tf_chartType` varchar(50) NOT NULL,
  `tf_colorScheme` varchar(50) NOT NULL,
  `tf_isAnimate` bit(1) DEFAULT NULL,
  `tf_isGridLine` bit(1) DEFAULT NULL,
  `tf_isShowDetailNumber` bit(1) DEFAULT NULL,
  `tf_isShowTips` bit(1) DEFAULT NULL,
  `tf_isSystemScheme` bit(1) DEFAULT NULL,
  `tf_name` varchar(50) NOT NULL,
  `tf_numericFields` varchar(255) NOT NULL,
  `tf_order` int(11) NOT NULL,
  `tf_otherSetting` varchar(255) DEFAULT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_title` varchar(50) DEFAULT NULL,
  `tf_reportId` int(11) NOT NULL,
  PRIMARY KEY (`tf_chartId`),
  KEY `FKmaytpkasfxpe6ke3bb9nne9fs` (`tf_reportId`),
  CONSTRAINT `FKmaytpkasfxpe6ke3bb9nne9fs` FOREIGN KEY (`tf_reportId`) REFERENCES `_Report` (`tf_reportId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ReportChart`
--

LOCK TABLES `_ReportChart` WRITE;
/*!40000 ALTER TABLE `_ReportChart` DISABLE KEYS */;
/*!40000 ALTER TABLE `_ReportChart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ReportField`
--

DROP TABLE IF EXISTS `_ReportField`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ReportField` (
  `tf_reportFieldId` int(11) NOT NULL,
  `tf_aggregate` varchar(50) DEFAULT NULL,
  `tf_condition` varchar(255) DEFAULT NULL,
  `tf_isHidden` bit(1) DEFAULT NULL,
  `tf_orderId` int(11) NOT NULL,
  `tf_otherSetting` varchar(255) DEFAULT NULL,
  `tf_fieldId` int(11) NOT NULL,
  `tf_reportFieldGroupId` int(11) NOT NULL,
  PRIMARY KEY (`tf_reportFieldId`),
  KEY `FKaqnf28vk5qldscntt44li9u0c` (`tf_fieldId`),
  KEY `FKmxtc9oegc8o9esju1kmktwojv` (`tf_reportFieldGroupId`),
  CONSTRAINT `FKaqnf28vk5qldscntt44li9u0c` FOREIGN KEY (`tf_fieldId`) REFERENCES `_ModuleField` (`tf_fieldId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKmxtc9oegc8o9esju1kmktwojv` FOREIGN KEY (`tf_reportFieldGroupId`) REFERENCES `_ReportFieldGroup` (`tf_reportFieldGroupId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ReportField`
--

LOCK TABLES `_ReportField` WRITE;
/*!40000 ALTER TABLE `_ReportField` DISABLE KEYS */;
INSERT INTO `_ReportField` VALUES (1,NULL,NULL,NULL,10,NULL,70100010,3),(2,NULL,NULL,NULL,20,NULL,70100020,3),(3,NULL,NULL,NULL,30,NULL,70100030,3),(4,NULL,NULL,NULL,40,NULL,70100040,3),(5,NULL,NULL,NULL,10,NULL,70100050,4),(6,NULL,NULL,NULL,20,NULL,70100060,4),(7,NULL,NULL,NULL,30,NULL,70100070,4),(8,NULL,NULL,NULL,40,NULL,70100080,4),(9,NULL,NULL,NULL,50,NULL,70100090,4),(10,NULL,NULL,NULL,60,NULL,70100100,4),(11,NULL,NULL,NULL,70,NULL,70100110,4),(12,NULL,NULL,NULL,80,NULL,70100120,4),(13,NULL,NULL,NULL,10,NULL,70120010,5),(14,NULL,NULL,NULL,20,NULL,70120020,5),(15,NULL,NULL,NULL,30,NULL,70120030,5),(16,NULL,NULL,NULL,10,NULL,70120040,6),(17,NULL,NULL,NULL,20,NULL,70120050,6),(18,NULL,NULL,NULL,30,NULL,70120060,6),(19,NULL,NULL,NULL,40,NULL,70120070,6);
/*!40000 ALTER TABLE `_ReportField` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ReportFieldGroup`
--

DROP TABLE IF EXISTS `_ReportFieldGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ReportFieldGroup` (
  `tf_reportFieldGroupId` int(11) NOT NULL,
  `tf_groupName` varchar(50) NOT NULL,
  `tf_isShowHeaderSpans` bit(1) DEFAULT NULL,
  `tf_orderId` int(11) NOT NULL,
  `tf_otherSetting` varchar(255) DEFAULT NULL,
  `tf_reportId` int(11) NOT NULL,
  PRIMARY KEY (`tf_reportFieldGroupId`),
  KEY `FKrlayknhb6rqjtllx4mmwpox8` (`tf_reportId`),
  CONSTRAINT `FKrlayknhb6rqjtllx4mmwpox8` FOREIGN KEY (`tf_reportId`) REFERENCES `_Report` (`tf_reportId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ReportFieldGroup`
--

LOCK TABLES `_ReportFieldGroup` WRITE;
/*!40000 ALTER TABLE `_ReportFieldGroup` DISABLE KEYS */;
INSERT INTO `_ReportFieldGroup` VALUES (3,'基本信息',NULL,10,NULL,2),(4,'附加信息',NULL,20,NULL,2),(5,'基本信息',NULL,10,NULL,3),(6,'附加信息',NULL,20,NULL,3);
/*!40000 ALTER TABLE `_ReportFieldGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_ReportGroup`
--

DROP TABLE IF EXISTS `_ReportGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_ReportGroup` (
  `tf_reportGroupId` varchar(10) NOT NULL,
  `tf_addSeparator` bit(1) DEFAULT NULL,
  `tf_description` varchar(50) DEFAULT NULL,
  `tf_iconURL` varchar(50) DEFAULT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_title` varchar(50) NOT NULL,
  PRIMARY KEY (`tf_reportGroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_ReportGroup`
--

LOCK TABLES `_ReportGroup` WRITE;
/*!40000 ALTER TABLE `_ReportGroup` DISABLE KEYS */;
INSERT INTO `_ReportGroup` VALUES ('10',0x00,NULL,'images/module/_Report.png',NULL,'项目合同台帐');
/*!40000 ALTER TABLE `_ReportGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_Role`
--

DROP TABLE IF EXISTS `_Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_Role` (
  `tf_roleId` varchar(10) NOT NULL,
  `tf_isEnable` bit(1) NOT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_roleName` varchar(50) NOT NULL,
  `tf_roleGroupId` varchar(10) NOT NULL,
  PRIMARY KEY (`tf_roleId`),
  KEY `FK2mwl35kcb5fmwkr5ydopd43uy` (`tf_roleGroupId`),
  CONSTRAINT `FK2mwl35kcb5fmwkr5ydopd43uy` FOREIGN KEY (`tf_roleGroupId`) REFERENCES `_RoleGroup` (`tf_roleGroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_Role`
--

LOCK TABLES `_Role` WRITE;
/*!40000 ALTER TABLE `_Role` DISABLE KEYS */;
INSERT INTO `_Role` VALUES ('0000',0x01,NULL,'管理员','00'),('0005',0x01,NULL,'市级管理员','05'),('0010',0x01,NULL,'查询角色','08'),('1000',0x01,NULL,'合同操作角色','10'),('1010',0x01,NULL,'业务系统管理角色','10');
/*!40000 ALTER TABLE `_Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_RoleGroup`
--

DROP TABLE IF EXISTS `_RoleGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_RoleGroup` (
  `tf_roleGroupId` varchar(10) NOT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_title` varchar(20) NOT NULL,
  PRIMARY KEY (`tf_roleGroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_RoleGroup`
--

LOCK TABLES `_RoleGroup` WRITE;
/*!40000 ALTER TABLE `_RoleGroup` DISABLE KEYS */;
INSERT INTO `_RoleGroup` VALUES ('00',NULL,'管理员'),('05',NULL,'市级管理'),('08',NULL,'查询'),('09',NULL,'工程管理'),('10',NULL,'合同管理'),('20',NULL,'资金管理'),('30',NULL,'审计部门'),('60',NULL,'审批角色'),('90',NULL,'其他');
/*!40000 ALTER TABLE `_RoleGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_RoleModuleAddition`
--

DROP TABLE IF EXISTS `_RoleModuleAddition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_RoleModuleAddition` (
  `tf_roleModuleAdditionId` int(11) NOT NULL,
  `tf_moduleAdditionFunctionId` int(11) NOT NULL,
  `tf_roleId` varchar(10) NOT NULL,
  PRIMARY KEY (`tf_roleModuleAdditionId`),
  KEY `FKpx1g9uvooj53sr3uchmwbnqhp` (`tf_moduleAdditionFunctionId`),
  KEY `FK54x4u3o44fngfb6fjhwg9uhb1` (`tf_roleId`),
  CONSTRAINT `FK54x4u3o44fngfb6fjhwg9uhb1` FOREIGN KEY (`tf_roleId`) REFERENCES `_Role` (`tf_roleId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKpx1g9uvooj53sr3uchmwbnqhp` FOREIGN KEY (`tf_moduleAdditionFunctionId`) REFERENCES `_ModuleAdditionFunction` (`tf_moduleAdditionFunctionId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_RoleModuleAddition`
--

LOCK TABLES `_RoleModuleAddition` WRITE;
/*!40000 ALTER TABLE `_RoleModuleAddition` DISABLE KEYS */;
INSERT INTO `_RoleModuleAddition` VALUES (506,90381011,'0005'),(507,900001,'0005'),(508,900003,'0005'),(509,903501,'0005'),(510,903504,'0005'),(511,903505,'0005'),(512,90381010,'0005'),(513,904101,'0005'),(514,909001,'0005'),(536,90381011,'0000'),(537,900001,'0000'),(538,900003,'0000'),(539,903501,'0000'),(540,903504,'0000'),(541,903505,'0000'),(542,90381010,'0000'),(543,904101,'0000'),(544,909001,'0000'),(545,909101,'0000'),(546,950501,'0000'),(547,990201,'0000'),(548,990202,'0000'),(549,990301,'0000'),(550,990501,'0000'),(551,991001,'0000'),(552,990801,'0000'),(553,991101,'0000'),(554,90381011,'0010'),(555,909101,'0010'),(556,950501,'0010');
/*!40000 ALTER TABLE `_RoleModuleAddition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_SystemLoginLog`
--

DROP TABLE IF EXISTS `_SystemLoginLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_SystemLoginLog` (
  `tf_systemLogiglogId` int(11) NOT NULL,
  `tf_ipaddress` varchar(255) DEFAULT NULL,
  `tf_loginDate` datetime DEFAULT NULL,
  `tf_loginName` varchar(255) DEFAULT NULL,
  `tf_logoutDate` datetime DEFAULT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_userId` int(11) DEFAULT NULL,
  `tf_userName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tf_systemLogiglogId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_SystemLoginLog`
--

LOCK TABLES `_SystemLoginLog` WRITE;
/*!40000 ALTER TABLE `_SystemLoginLog` DISABLE KEYS */;
INSERT INTO `_SystemLoginLog` VALUES (1,'0:0:0:0:0:0:0:1','2016-01-11 14:32:37','admin','2016-01-11 20:22:23','正常登录,超时退出',1,'管理员'),(2,'0:0:0:0:0:0:0:1','2016-01-11 20:45:57','admin',NULL,'正常登录',1,'管理员'),(3,'0:0:0:0:0:0:0:1','2016-01-11 21:41:34','admin',NULL,'正常登录',1,'管理员'),(4,'0:0:0:0:0:0:0:1','2016-01-12 08:24:31','admin','2016-01-12 16:15:38','正常登录,正常退出',1,'管理员'),(5,'0:0:0:0:0:0:0:1','2016-01-12 16:16:08','admin','2016-01-12 21:43:27','正常登录,超时退出',1,'管理员'),(6,'0:0:0:0:0:0:0:1','2016-01-13 08:37:42','admin',NULL,'正常登录',1,'管理员'),(7,'0:0:0:0:0:0:0:1','2016-01-13 11:02:55','admin',NULL,'正常登录',1,'管理员');
/*!40000 ALTER TABLE `_SystemLoginLog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_SystemOperateLog`
--

DROP TABLE IF EXISTS `_SystemOperateLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_SystemOperateLog` (
  `tf_systemlogId` int(11) NOT NULL,
  `tf_date` datetime NOT NULL,
  `tf_do` varchar(20) NOT NULL,
  `tf_filedata` longblob,
  `tf_hasfiledata` bit(1) DEFAULT NULL,
  `tf_ipaddress` varchar(24) NOT NULL,
  `tf_moduleId` varchar(10) NOT NULL,
  `tf_moduleTitle` varchar(50) NOT NULL,
  `tf_recordkey` varchar(20) NOT NULL,
  `tf_recordname` varchar(50) DEFAULT NULL,
  `tf_remark` mediumtext,
  `tf_userId` int(11) NOT NULL,
  `tf_userName` varchar(10) NOT NULL,
  PRIMARY KEY (`tf_systemlogId`),
  KEY `FK1uhr8eo02jb7bmemmib4xl39c` (`tf_userId`),
  CONSTRAINT `FK1uhr8eo02jb7bmemmib4xl39c` FOREIGN KEY (`tf_userId`) REFERENCES `_User` (`tf_userId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_SystemOperateLog`
--

LOCK TABLES `_SystemOperateLog` WRITE;
/*!40000 ALTER TABLE `_SystemOperateLog` DISABLE KEYS */;
INSERT INTO `_SystemOperateLog` VALUES (1,'2016-01-10 20:40:21','删除',NULL,NULL,'0:0:0:0:0:0:0:1','990103','模块字段','60300080','产地市',NULL,1,'管理员'),(2,'2016-01-10 20:40:34','修改',NULL,NULL,'0:0:0:0:0:0:0:1','990103','模块字段','60400100','始发地市','-{\"tf_fieldOrder\":36,\"tf_fieldId\":60400100}',1,'管理员'),(3,'2016-01-10 20:40:37','修改',NULL,NULL,'0:0:0:0:0:0:0:1','990103','模块字段','60400110','目的地市','-{\"tf_fieldOrder\":37,\"tf_fieldId\":60400110}',1,'管理员'),(4,'2016-01-11 16:55:46','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1717','','-{\"_t9041___tf_roleId\":\"1010\",\"_t9035___tf_userId\":\"1\",\"tf_userRoleId\":\"_UserRole-1\"}',1,'管理员'),(5,'2016-01-11 21:02:17','删除',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1716','',NULL,1,'管理员'),(6,'2016-01-11 21:05:06','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1718','','-{\"_t9041___tf_roleId\":\"1000\",\"_t9035___tf_userId\":\"2\"}',1,'管理员'),(7,'2016-01-11 21:05:06','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1719','','-{\"_t9041___tf_roleId\":\"1010\",\"_t9035___tf_userId\":\"2\"}',1,'管理员'),(8,'2016-01-11 21:05:21','删除',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1712','',NULL,1,'管理员'),(9,'2016-01-11 21:05:21','删除',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1713','',NULL,1,'管理员'),(10,'2016-01-11 21:05:21','删除',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1714','',NULL,1,'管理员'),(11,'2016-01-11 21:05:21','删除',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1718','',NULL,1,'管理员'),(12,'2016-01-11 21:05:21','删除',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1719','',NULL,1,'管理员'),(13,'2016-01-11 21:47:31','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1718','','-{\"_t9041___tf_roleId\":\"0005\",\"_t9035___tf_userId\":\"2\"}',1,'管理员'),(14,'2016-01-11 21:47:31','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1719','','-{\"_t9041___tf_roleId\":\"0010\",\"_t9035___tf_userId\":\"2\"}',1,'管理员'),(15,'2016-01-11 21:47:42','删除',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1718','',NULL,1,'管理员'),(16,'2016-01-11 21:47:42','删除',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1719','',NULL,1,'管理员'),(17,'2016-01-11 21:47:52','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1720','','-{\"_t9041___tf_roleId\":\"0000\",\"_t9035___tf_userId\":\"2\"}',1,'管理员'),(18,'2016-01-11 21:47:52','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1721','','-{\"_t9041___tf_roleId\":\"0005\",\"_t9035___tf_userId\":\"2\"}',1,'管理员'),(19,'2016-01-11 21:47:52','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1722','','-{\"_t9041___tf_roleId\":\"0010\",\"_t9035___tf_userId\":\"2\"}',1,'管理员'),(20,'2016-01-11 21:47:52','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1723','','-{\"_t9041___tf_roleId\":\"1010\",\"_t9035___tf_userId\":\"2\"}',1,'管理员'),(21,'2016-01-11 21:51:55','删除',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1723','',NULL,1,'管理员'),(22,'2016-01-11 21:52:32','删除',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1722','',NULL,1,'管理员'),(23,'2016-01-11 21:53:06','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1724','','-{\"_t9041___tf_roleId\":\"0010\",\"_t9035___tf_userId\":\"2\"}',1,'管理员'),(24,'2016-01-11 21:53:17','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1725','','-{\"_t9041___tf_roleId\":\"1000\",\"_t9035___tf_userId\":\"2\"}',1,'管理员'),(25,'2016-01-11 21:53:17','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1726','','-{\"_t9041___tf_roleId\":\"1010\",\"_t9035___tf_userId\":\"2\"}',1,'管理员'),(26,'2016-01-11 21:54:45','删除',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1726','',NULL,1,'管理员'),(27,'2016-01-11 21:55:09','删除',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1720','',NULL,1,'管理员'),(28,'2016-01-11 21:56:11','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1727','','-{\"_t9041___tf_roleId\":\"0000\",\"_t9035___tf_userId\":\"2\"}',1,'管理员'),(29,'2016-01-11 21:56:22','删除',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1727','',NULL,1,'管理员'),(30,'2016-01-11 21:56:27','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1728','','-{\"_t9041___tf_roleId\":\"0000\",\"_t9035___tf_userId\":\"2\"}',1,'管理员'),(31,'2016-01-11 21:58:58','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9035','用户','3','蒋锋','-{\"tf_userName\":\"蒋锋\",\"tf_loginName\":\"jf\",\"_t9011___tf_departmentId\":\"00\",\"_t9030___tf_positionId\":\"10\",\"tf_allowLogin\":true,\"tf_office\":null,\"tf_telnumber\":null,\"tf_eMail\":null,\"tf_phonenumber\":null,\"tf_qq\":null,\"tf_favoriteSet\":null,\"tf_remark\":null,\"tf_lastLoginDate\":null,\"tf_loginTimes\":0,\"tf_userId\":\"_User-1\",\"tf_isSendMessage\":false,\"tf_Roles\":null}',1,'管理员'),(32,'2016-01-11 21:59:12','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9035','用户','4','徐红','-{\"tf_userName\":\"徐红\",\"tf_loginName\":\"xh\",\"_t9011___tf_departmentId\":\"0050\",\"_t9030___tf_positionId\":\"20\",\"tf_allowLogin\":true,\"tf_office\":null,\"tf_telnumber\":null,\"tf_eMail\":null,\"tf_phonenumber\":null,\"tf_qq\":null,\"tf_favoriteSet\":null,\"tf_remark\":null,\"tf_lastLoginDate\":null,\"tf_loginTimes\":0,\"tf_userId\":\"_User-2\",\"tf_isSendMessage\":false,\"tf_Roles\":null}',1,'管理员'),(33,'2016-01-11 21:59:21','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1729','','-{\"_t9041___tf_roleId\":\"0000\",\"_t9035___tf_userId\":\"3\"}',1,'管理员'),(34,'2016-01-11 21:59:21','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1730','','-{\"_t9041___tf_roleId\":\"0005\",\"_t9035___tf_userId\":\"3\"}',1,'管理员'),(35,'2016-01-11 21:59:24','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1731','','-{\"_t9041___tf_roleId\":\"1000\",\"_t9035___tf_userId\":\"4\"}',1,'管理员'),(36,'2016-01-11 22:00:14','删除',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1731','',NULL,1,'管理员'),(37,'2016-01-11 22:00:22','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1732','','-{\"_t9041___tf_roleId\":\"0000\",\"_t9035___tf_userId\":\"4\"}',1,'管理员'),(38,'2016-01-11 22:00:22','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1733','','-{\"_t9041___tf_roleId\":\"0005\",\"_t9035___tf_userId\":\"4\"}',1,'管理员'),(39,'2016-01-11 22:00:26','删除',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1732','',NULL,1,'管理员'),(40,'2016-01-11 22:13:39','删除',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1725','',NULL,1,'管理员'),(41,'2016-01-11 22:14:19','新增',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1734','','-{\"_t9041___tf_roleId\":\"1000\",\"_t9035___tf_userId\":\"2\"}',1,'管理员'),(42,'2016-01-11 22:29:08','删除',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1724','',NULL,1,'管理员'),(43,'2016-01-11 22:29:08','删除',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1728','',NULL,1,'管理员'),(44,'2016-01-11 22:34:25','删除',NULL,NULL,'0:0:0:0:0:0:0:1','9042','用户操作角色分配','1733','',NULL,1,'管理员'),(45,'2016-01-12 14:02:44','新增',NULL,NULL,'0:0:0:0:0:0:0:1','990130','菜单','988','业务员','-{\"tf_title\":null,\"tf_displayTitle\":null,\"tf_iconCls\":null,\"tf_iconUrl\":null,\"tf_orderId\":0,\"_t990102___tf_moduleId\":\"6020\",\"_t9050___tf_reportGroupId\":null,\"_t9052___tf_reportId\":null,\"tf_functionName\":null,\"tf_windowName\":null,\"tf_execStatument\":null,\"tf_parameter\":null,\"tf_parentFilter\":null,\"tf_id\":\"app.model._Menu-1\",\"tf_pid\":null,\"parentId\":null,\"leaf\":false,\"tf_expand\":false}',1,'管理员'),(46,'2016-01-12 14:02:55','新增',NULL,NULL,'0:0:0:0:0:0:0:1','990130','菜单','989','商品类别','-{\"tf_title\":null,\"tf_displayTitle\":null,\"tf_iconCls\":null,\"tf_iconUrl\":null,\"tf_orderId\":0,\"_t990102___tf_moduleId\":\"7018\",\"_t9050___tf_reportGroupId\":null,\"_t9052___tf_reportId\":null,\"tf_functionName\":null,\"tf_windowName\":null,\"tf_execStatument\":null,\"tf_parameter\":null,\"tf_parentFilter\":null,\"tf_id\":\"app.model._Menu-2\",\"tf_pid\":null,\"parentId\":null,\"leaf\":false,\"tf_expand\":false}',1,'管理员'),(47,'2016-01-12 14:03:04','新增',NULL,NULL,'0:0:0:0:0:0:0:1','990130','菜单','990','商品仓库','-{\"tf_title\":null,\"tf_displayTitle\":null,\"tf_iconCls\":null,\"tf_iconUrl\":null,\"tf_orderId\":0,\"_t990102___tf_moduleId\":\"7020\",\"_t9050___tf_reportGroupId\":null,\"_t9052___tf_reportId\":null,\"tf_functionName\":null,\"tf_windowName\":null,\"tf_execStatument\":null,\"tf_parameter\":null,\"tf_parentFilter\":null,\"tf_id\":\"app.model._Menu-3\",\"tf_pid\":null,\"parentId\":null,\"leaf\":false,\"tf_expand\":false}',1,'管理员'),(48,'2016-01-12 14:03:13','新增',NULL,NULL,'0:0:0:0:0:0:0:1','990130','菜单','991','商品','-{\"tf_title\":null,\"tf_displayTitle\":null,\"tf_iconCls\":null,\"tf_iconUrl\":null,\"tf_orderId\":0,\"_t990102___tf_moduleId\":\"6030\",\"_t9050___tf_reportGroupId\":null,\"_t9052___tf_reportId\":null,\"tf_functionName\":null,\"tf_windowName\":null,\"tf_execStatument\":null,\"tf_parameter\":null,\"tf_parentFilter\":null,\"tf_id\":\"app.model._Menu-4\",\"tf_pid\":null,\"parentId\":null,\"leaf\":false,\"tf_expand\":false}',1,'管理员'),(49,'2016-01-12 14:03:20','新增',NULL,NULL,'0:0:0:0:0:0:0:1','990130','菜单','992','订单','-{\"tf_title\":null,\"tf_displayTitle\":null,\"tf_iconCls\":null,\"tf_iconUrl\":null,\"tf_orderId\":0,\"_t990102___tf_moduleId\":\"6040\",\"_t9050___tf_reportGroupId\":null,\"_t9052___tf_reportId\":null,\"tf_functionName\":null,\"tf_windowName\":null,\"tf_execStatument\":null,\"tf_parameter\":null,\"tf_parentFilter\":null,\"tf_id\":\"app.model._Menu-5\",\"tf_pid\":null,\"parentId\":null,\"leaf\":false,\"tf_expand\":false}',1,'管理员'),(50,'2016-01-12 14:03:25','新增',NULL,NULL,'0:0:0:0:0:0:0:1','990130','菜单','993','订单明细','-{\"tf_title\":null,\"tf_displayTitle\":null,\"tf_iconCls\":null,\"tf_iconUrl\":null,\"tf_orderId\":0,\"_t990102___tf_moduleId\":\"6050\",\"_t9050___tf_reportGroupId\":null,\"_t9052___tf_reportId\":null,\"tf_functionName\":null,\"tf_windowName\":null,\"tf_execStatument\":null,\"tf_parameter\":null,\"tf_parentFilter\":null,\"tf_id\":\"app.model._Menu-6\",\"tf_pid\":null,\"parentId\":null,\"leaf\":false,\"tf_expand\":false}',1,'管理员'),(51,'2016-01-12 14:03:30','新增',NULL,NULL,'0:0:0:0:0:0:0:1','990130','菜单','994','收款明细','-{\"tf_title\":null,\"tf_displayTitle\":null,\"tf_iconCls\":null,\"tf_iconUrl\":null,\"tf_orderId\":0,\"_t990102___tf_moduleId\":\"6060\",\"_t9050___tf_reportGroupId\":null,\"_t9052___tf_reportId\":null,\"tf_functionName\":null,\"tf_windowName\":null,\"tf_execStatument\":null,\"tf_parameter\":null,\"tf_parentFilter\":null,\"tf_id\":\"app.model._Menu-7\",\"tf_pid\":null,\"parentId\":null,\"leaf\":false,\"tf_expand\":false}',1,'管理员'),(52,'2016-01-12 14:03:38','修改',NULL,NULL,'0:0:0:0:0:0:0:1','990130','菜单','988','业务员','-{\"parentId\":\"root\",\"tf_pid\":\"985\",\"tf_id\":\"988\"}',1,'管理员'),(53,'2016-01-12 14:03:46','修改',NULL,NULL,'0:0:0:0:0:0:0:1','990130','菜单','989','商品类别','-{\"parentId\":\"root\",\"tf_pid\":\"985\",\"tf_id\":\"989\"}',1,'管理员'),(54,'2016-01-12 14:03:53','修改',NULL,NULL,'0:0:0:0:0:0:0:1','990130','菜单','990','商品仓库','-{\"parentId\":\"root\",\"tf_pid\":\"985\",\"tf_id\":\"990\"}',1,'管理员'),(55,'2016-01-12 14:03:58','修改',NULL,NULL,'0:0:0:0:0:0:0:1','990130','菜单','991','商品','-{\"parentId\":\"root\",\"tf_pid\":\"985\",\"tf_id\":\"991\"}',1,'管理员'),(56,'2016-01-12 14:04:03','修改',NULL,NULL,'0:0:0:0:0:0:0:1','990130','菜单','992','订单','-{\"parentId\":\"root\",\"tf_pid\":\"985\",\"tf_id\":\"992\"}',1,'管理员'),(57,'2016-01-12 14:04:06','修改',NULL,NULL,'0:0:0:0:0:0:0:1','990130','菜单','993','订单明细','-{\"parentId\":\"root\",\"tf_pid\":\"985\",\"tf_id\":\"993\"}',1,'管理员'),(58,'2016-01-12 14:04:10','修改',NULL,NULL,'0:0:0:0:0:0:0:1','990130','菜单','994','收款明细','-{\"parentId\":\"root\",\"tf_pid\":\"985\",\"tf_id\":\"994\"}',1,'管理员'),(59,'2016-01-12 14:05:07','新增',NULL,NULL,'0:0:0:0:0:0:0:1','6020','业务员','1','业务员甲','-{\"_t9011___tf_departmentId\":\"005001\",\"tf_name\":\"业务员甲\",\"tf_sex\":\"男\",\"tf_birthday\":\"1970-01-12\",\"tf_age\":0,\"tf_telnumber\":null,\"tf_phonenumber\":null,\"tf_eMail\":null,\"tf_remark\":null,\"tf_salesmanId\":\"Salesman-1\"}',1,'管理员'),(60,'2016-01-12 14:05:22','新增',NULL,NULL,'0:0:0:0:0:0:0:1','7018','商品类别','10','电器','-{\"tf_productClassId\":\"10\",\"tf_name\":\"电器\"}',1,'管理员'),(61,'2016-01-12 14:05:29','新增',NULL,NULL,'0:0:0:0:0:0:0:1','7018','商品类别','20','日用品','-{\"tf_productClassId\":\"20\",\"tf_name\":\"日用品\"}',1,'管理员'),(62,'2016-01-12 14:05:37','新增',NULL,NULL,'0:0:0:0:0:0:0:1','7018','商品类别','30','化妆品','-{\"tf_productClassId\":\"30\",\"tf_name\":\"化妆品\"}',1,'管理员'),(63,'2016-01-12 14:05:50','新增',NULL,NULL,'0:0:0:0:0:0:0:1','7020','商品仓库','1','唐山仓库','-{\"_t7012___tf_cityId\":\"0302\",\"tf_name\":\"唐山仓库\",\"tf_address\":null,\"tf_linkman\":null,\"tf_linkmanTel\":null,\"tf_remark\":null,\"tf_storageId\":\"Storage-1\"}',1,'管理员'),(64,'2016-01-12 14:05:59','新增',NULL,NULL,'0:0:0:0:0:0:0:1','7020','商品仓库','2','苏州仓库','-{\"_t7012___tf_cityId\":\"0705\",\"tf_name\":\"苏州仓库\",\"tf_address\":null,\"tf_linkman\":null,\"tf_linkmanTel\":null,\"tf_remark\":null,\"tf_storageId\":\"Storage-2\"}',1,'管理员'),(65,'2016-01-12 14:06:15','新增',NULL,NULL,'0:0:0:0:0:0:0:1','7020','商品仓库','3','台州仓库','-{\"_t7012___tf_cityId\":\"1011\",\"tf_name\":\"台州仓库\",\"tf_address\":null,\"tf_linkman\":null,\"tf_linkmanTel\":null,\"tf_remark\":null,\"tf_storageId\":\"Storage-3\"}',1,'管理员'),(66,'2016-01-12 14:09:16','新增',NULL,NULL,'0:0:0:0:0:0:0:1','6030','商品','1','40寸电视机','-{\"_t7018___tf_productClassId\":\"10\",\"tf_name\":\"40寸电视机\",\"tf_origin\":\"广州\",\"tf_unitPrice\":0,\"tf_costPrice\":0,\"tf_remark\":null,\"tf_productId\":\"Product-3\"}',1,'管理员'),(67,'2016-01-12 14:09:25','新增',NULL,NULL,'0:0:0:0:0:0:0:1','6030','商品','2','50寸电视机','-{\"_t7018___tf_productClassId\":\"10\",\"tf_name\":\"50寸电视机\",\"tf_origin\":\"广州\",\"tf_unitPrice\":0,\"tf_costPrice\":0,\"tf_remark\":null,\"tf_productId\":\"Product-4\"}',1,'管理员'),(68,'2016-01-12 14:09:56','新增',NULL,NULL,'0:0:0:0:0:0:0:1','6030','商品','3','餐贴纸','-{\"_t7018___tf_productClassId\":\"20\",\"tf_name\":\"餐贴纸\",\"tf_origin\":\"无锡\",\"tf_unitPrice\":0,\"tf_costPrice\":0,\"tf_remark\":null,\"tf_productId\":\"Product-5\"}',1,'管理员'),(69,'2016-01-12 14:09:59','新增',NULL,NULL,'0:0:0:0:0:0:0:1','6030','商品','4','餐贴纸','-{\"_t7018___tf_productClassId\":\"20\",\"tf_name\":\"餐贴纸\",\"tf_origin\":\"无锡\",\"tf_unitPrice\":0,\"tf_costPrice\":0,\"tf_remark\":null,\"tf_productId\":\"Product-6\"}',1,'管理员'),(70,'2016-01-12 14:10:49','新增',NULL,NULL,'0:0:0:0:0:0:0:1','6040','订单','1','order9016-01-12','-{\"_t6010___tf_customerId\":\"2\",\"_t6020___tf_salesmanId\":\"1\",\"tf_ordersNumber\":\"order9016-01-12\",\"tf_date\":\"2016-01-12\",\"tf_year\":null,\"tf_remark\":null,\"tf_ordersId\":\"Orders-1\",\"_t7020___tf_storageId\":null,\"_t7012___tf_cityId\":null,\"tf_finished\":false}',1,'管理员'),(71,'2016-01-12 14:11:27','新增',NULL,NULL,'0:0:0:0:0:0:0:1','6050','订单明细','1','40寸电视机若干','-{\"_t6040___tf_ordersId\":\"1\",\"_t6030___tf_productId\":\"1\",\"tf_name\":\"40寸电视机若干\",\"tf_number\":10,\"tf_unitPrice\":12222,\"tf_subtotalPrice\":0,\"tf_remark\":null,\"tf_ordersDetailId\":\"OrdersDetail-1\"}',1,'管理员'),(72,'2016-01-12 14:12:55','删除',NULL,NULL,'0:0:0:0:0:0:0:1','990103','模块字段','60400100','始发地市',NULL,1,'管理员'),(73,'2016-01-12 14:12:55','删除',NULL,NULL,'0:0:0:0:0:0:0:1','990103','模块字段','60400110','目的地市',NULL,1,'管理员'),(74,'2016-01-12 15:39:46','删除',NULL,NULL,'0:0:0:0:0:0:0:1','990103','模块字段','70200020','市',NULL,1,'管理员'),(75,'2016-01-13 11:04:48','取消审核',NULL,NULL,'0:0:0:0:0:0:0:1','7010','省份','02','天津市','-{\"tf_averageGDP\":4.98,\"tf_auditingName\":null,\"tf_auditingDate\":null,\"tf_provinceId\":\"02\"}',1,'管理员'),(76,'2016-01-13 11:04:56','修改',NULL,NULL,'0:0:0:0:0:0:0:1','7010','省份','02','天津市','-{\"tf_map\":\"\",\"tf_provinceId\":\"02\"}',1,'管理员');
/*!40000 ALTER TABLE `_SystemOperateLog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_Systembackup`
--

DROP TABLE IF EXISTS `_Systembackup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_Systembackup` (
  `tf_systembackupId` int(11) NOT NULL,
  `tf_inputdate` datetime NOT NULL,
  `tf_inputmen` varchar(10) NOT NULL,
  `tf_backupdate` datetime NOT NULL,
  `tf_backupfilename` varchar(250) DEFAULT NULL,
  `tf_ipaddress` varchar(24) NOT NULL,
  `tf_isupload` bit(1) DEFAULT NULL,
  `tf_otherfiles` varchar(255) DEFAULT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_result` varchar(255) DEFAULT NULL,
  `tf_userName` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`tf_systembackupId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_Systembackup`
--

LOCK TABLES `_Systembackup` WRITE;
/*!40000 ALTER TABLE `_Systembackup` DISABLE KEYS */;
INSERT INTO `_Systembackup` VALUES (7,'2015-12-12 11:46:43','系统','2015-12-12 11:46:43','/\\dddd2015-12-12.dat','127.0.0.1',NULL,NULL,'自动备份','数据备份失败,请与服务单位联系','自动备份'),(8,'2016-01-08 18:20:54','系统','2016-01-08 18:20:54','/\\dddd2016-01-08.dat','127.0.0.1',NULL,NULL,'自动备份','数据备份失败,请与服务单位联系','自动备份'),(9,'2016-01-11 14:11:36','系统','2016-01-11 14:11:36','/\\dddd2016-01-11.dat','127.0.0.1',NULL,NULL,'自动备份','数据备份失败,请与服务单位联系','自动备份');
/*!40000 ALTER TABLE `_Systembackup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_Systeminfo`
--

DROP TABLE IF EXISTS `_Systeminfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_Systeminfo` (
  `tf_systeminfoId` int(11) NOT NULL,
  `tf_additionFileMaxMB` int(11) NOT NULL,
  `tf_allowExternalLogin` bit(1) NOT NULL,
  `tf_allowToPDF` varchar(255) DEFAULT NULL,
  `tf_allowautoLoginInTwoWeeks` bit(1) NOT NULL,
  `tf_alwaysNeedIdentifingCode` bit(1) NOT NULL,
  `tf_attachmentDataBaseName` varchar(255) DEFAULT NULL,
  `tf_attachmentSaveDir` varchar(255) DEFAULT NULL,
  `tf_attachmentSaveToFile` bit(1) DEFAULT NULL,
  `tf_backupfilename` varchar(50) NOT NULL,
  `tf_copyrightInfo` varchar(50) NOT NULL,
  `tf_copyrightOwner` varchar(50) NOT NULL,
  `tf_maxusers` int(11) NOT NULL,
  `tf_needIdentifingCode` bit(1) NOT NULL,
  `tf_needReplaceInitialPassword` bit(1) NOT NULL,
  `tf_previewExts` varchar(255) DEFAULT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_sessionTimeoutMinute` int(11) NOT NULL,
  `tf_systemAddition` varchar(50) DEFAULT NULL,
  `tf_systemVersion` varchar(50) NOT NULL,
  `tf_systemName` varchar(50) NOT NULL,
  `tf_reduceModeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`tf_systeminfoId`),
  KEY `FKkbi0r6vr37yr0559p2hj2m5b0` (`tf_reduceModeId`),
  CONSTRAINT `FKkbi0r6vr37yr0559p2hj2m5b0` FOREIGN KEY (`tf_reduceModeId`) REFERENCES `_AttachmentReduceMode` (`tf_reduceModeId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_Systeminfo`
--

LOCK TABLES `_Systeminfo` WRITE;
/*!40000 ALTER TABLE `_Systeminfo` DISABLE KEYS */;
INSERT INTO `_Systeminfo` VALUES (1,10000,0x00,'xls,doc',0x01,0x00,NULL,NULL,NULL,'dddd','jfok','jfok',200,0x01,0x00,'doc,xls,pdf',NULL,180,NULL,'6.0.0','常规功能和模块自定义系统 (cfcmms)',10);
/*!40000 ALTER TABLE `_Systeminfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_Systemset`
--

DROP TABLE IF EXISTS `_Systemset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_Systemset` (
  `tf_systemsetId` int(11) NOT NULL,
  `tf_userdwmc` varchar(50) NOT NULL COMMENT '单位名称',
  `tf_userAddress` varchar(50) DEFAULT NULL,
  `tf_userType` varchar(20) DEFAULT NULL,
  `tf_userLinkmen` varchar(20) DEFAULT NULL,
  `tf_userTelnumber` varchar(20) DEFAULT NULL,
  `tf_userStartdate` datetime DEFAULT NULL,
  `tf_userRemark` mediumtext,
  `tf_serviceDepartment` varchar(50) DEFAULT NULL,
  `tf_serviceMen` varchar(50) DEFAULT NULL,
  `tf_serviceTelnumber` varchar(50) DEFAULT NULL,
  `tf_serviceFaxnumber` varchar(50) DEFAULT NULL,
  `tf_serviceEmail` varchar(50) DEFAULT NULL,
  `tf_serviceHomepage` varchar(50) DEFAULT NULL,
  `tf_serviceQQ` varchar(50) DEFAULT NULL,
  `tf_serviceRemark` mediumtext COMMENT '服务单位备注',
  PRIMARY KEY (`tf_systemsetId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_Systemset`
--

LOCK TABLES `_Systemset` WRITE;
/*!40000 ALTER TABLE `_Systemset` DISABLE KEYS */;
INSERT INTO `_Systemset` VALUES (1,'无锡市熙旺软件技术有限公司','无锡市城南路',NULL,NULL,NULL,'2015-09-11 00:00:00',NULL,'无锡市熙旺软件技术有限公司','蒋锋','1320528023__',NULL,'jfok1972@qq.com',NULL,'qq群: 386100815',NULL);
/*!40000 ALTER TABLE `_Systemset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_SystemsetAddition`
--

DROP TABLE IF EXISTS `_SystemsetAddition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_SystemsetAddition` (
  `tf_systemsetAdditionId` int(11) NOT NULL,
  `tf_backupfiledirs` varchar(250) DEFAULT NULL,
  `tf_continueWithAudit` bit(1) DEFAULT NULL,
  `tf_userforgetPassword` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tf_systemsetAdditionId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_SystemsetAddition`
--

LOCK TABLES `_SystemsetAddition` WRITE;
/*!40000 ALTER TABLE `_SystemsetAddition` DISABLE KEYS */;
INSERT INTO `_SystemsetAddition` VALUES (1,'/',0x00,'忘了密码，去问管理员');
/*!40000 ALTER TABLE `_SystemsetAddition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_User`
--

DROP TABLE IF EXISTS `_User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_User` (
  `tf_userId` int(11) NOT NULL,
  `tf_allowLogin` bit(1) NOT NULL,
  `tf_eMail` varchar(50) DEFAULT NULL,
  `tf_favoriteSet` varchar(255) DEFAULT NULL,
  `tf_isSendMessage` bit(1) DEFAULT NULL,
  `tf_lastLoginDate` datetime DEFAULT NULL,
  `tf_loginName` varchar(20) NOT NULL,
  `tf_loginTimes` int(11) DEFAULT NULL,
  `tf_office` varchar(50) DEFAULT NULL,
  `tf_password` varchar(50) NOT NULL,
  `tf_phonenumber` varchar(20) DEFAULT NULL,
  `tf_qq` varchar(20) DEFAULT NULL,
  `tf_remark` varchar(255) DEFAULT NULL,
  `tf_signPhoto` longblob,
  `tf_telnumber` varchar(20) DEFAULT NULL,
  `tf_userName` varchar(20) NOT NULL,
  `tf_departmentId` varchar(10) NOT NULL,
  `tf_positionId` varchar(10) NOT NULL,
  PRIMARY KEY (`tf_userId`),
  KEY `FKky9goup64vtis2o2bynl9p3sm` (`tf_departmentId`),
  KEY `FKm5gbdgdc1tqpjbm910a0aib8s` (`tf_positionId`),
  CONSTRAINT `FKky9goup64vtis2o2bynl9p3sm` FOREIGN KEY (`tf_departmentId`) REFERENCES `_Department` (`tf_departmentId`),
  CONSTRAINT `FKm5gbdgdc1tqpjbm910a0aib8s` FOREIGN KEY (`tf_positionId`) REFERENCES `_Position` (`tf_positionId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_User`
--

LOCK TABLES `_User` WRITE;
/*!40000 ALTER TABLE `_User` DISABLE KEYS */;
INSERT INTO `_User` VALUES (1,0x01,NULL,NULL,NULL,'2016-01-13 11:02:53','admin',342,NULL,'djODkzsZNWXsTa18/StyQw==',NULL,NULL,NULL,NULL,NULL,'管理员','00','10'),(2,0x01,NULL,NULL,NULL,'2015-11-08 18:25:49','manager',55,NULL,'JSnLyUSWsL3BheBRsnqxwg==',NULL,NULL,NULL,NULL,NULL,'镇级管理员','00','50'),(3,0x01,NULL,NULL,0x00,NULL,'jf',0,NULL,'m5vVhE+yHb4vNGW5B39gIw==',NULL,NULL,NULL,NULL,NULL,'蒋锋','00','10'),(4,0x01,NULL,NULL,0x00,NULL,'xh',0,NULL,'yHfeSLP1D3AyO01KnVUUrw==',NULL,NULL,NULL,NULL,NULL,'徐红','0050','20');
/*!40000 ALTER TABLE `_User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_UserAdditionDepartment`
--

DROP TABLE IF EXISTS `_UserAdditionDepartment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_UserAdditionDepartment` (
  `tf_additionDepaId` int(11) NOT NULL,
  `tf_allDepartmentId` varchar(10) NOT NULL,
  `tf_userId` int(11) NOT NULL,
  PRIMARY KEY (`tf_additionDepaId`),
  KEY `FKdppe4m99dgxi37o9lfneht2k1` (`tf_allDepartmentId`),
  KEY `FKtma1364fwhfo8xjgu2aghe9fw` (`tf_userId`),
  CONSTRAINT `FKdppe4m99dgxi37o9lfneht2k1` FOREIGN KEY (`tf_allDepartmentId`) REFERENCES `_AllDepartment` (`tf_allDepartmentId`),
  CONSTRAINT `FKtma1364fwhfo8xjgu2aghe9fw` FOREIGN KEY (`tf_userId`) REFERENCES `_User` (`tf_userId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_UserAdditionDepartment`
--

LOCK TABLES `_UserAdditionDepartment` WRITE;
/*!40000 ALTER TABLE `_UserAdditionDepartment` DISABLE KEYS */;
/*!40000 ALTER TABLE `_UserAdditionDepartment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_UserDataFilterRole`
--

DROP TABLE IF EXISTS `_UserDataFilterRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_UserDataFilterRole` (
  `tf_userDataFilterRoleId` int(11) NOT NULL,
  `tf_filterRoleId` int(11) NOT NULL,
  `tf_userId` int(11) NOT NULL,
  PRIMARY KEY (`tf_userDataFilterRoleId`),
  KEY `FKbfdhv1sotmy6to2htbj6a25kn` (`tf_filterRoleId`),
  KEY `FKjjgd3oi8fetv11fxtp7vkcfro` (`tf_userId`),
  CONSTRAINT `FKbfdhv1sotmy6to2htbj6a25kn` FOREIGN KEY (`tf_filterRoleId`) REFERENCES `_DataFilterRole` (`tf_filterRoleId`),
  CONSTRAINT `FKjjgd3oi8fetv11fxtp7vkcfro` FOREIGN KEY (`tf_userId`) REFERENCES `_User` (`tf_userId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_UserDataFilterRole`
--

LOCK TABLES `_UserDataFilterRole` WRITE;
/*!40000 ALTER TABLE `_UserDataFilterRole` DISABLE KEYS */;
/*!40000 ALTER TABLE `_UserDataFilterRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_UserFieldHiddenRole`
--

DROP TABLE IF EXISTS `_UserFieldHiddenRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_UserFieldHiddenRole` (
  `tf_userFieldRoleId` int(11) NOT NULL,
  `tf_fieldRoleId` varchar(255) DEFAULT NULL,
  `tf_userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`tf_userFieldRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_UserFieldHiddenRole`
--

LOCK TABLES `_UserFieldHiddenRole` WRITE;
/*!40000 ALTER TABLE `_UserFieldHiddenRole` DISABLE KEYS */;
/*!40000 ALTER TABLE `_UserFieldHiddenRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_UserFieldReadonlyRole`
--

DROP TABLE IF EXISTS `_UserFieldReadonlyRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_UserFieldReadonlyRole` (
  `tf_userFieldRoleId` int(11) NOT NULL,
  `tf_fieldRoleId` varchar(255) DEFAULT NULL,
  `tf_userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`tf_userFieldRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_UserFieldReadonlyRole`
--

LOCK TABLES `_UserFieldReadonlyRole` WRITE;
/*!40000 ALTER TABLE `_UserFieldReadonlyRole` DISABLE KEYS */;
/*!40000 ALTER TABLE `_UserFieldReadonlyRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_UserFieldRole`
--

DROP TABLE IF EXISTS `_UserFieldRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_UserFieldRole` (
  `tf_userFieldRoleId` int(11) NOT NULL,
  `tf_fieldRoleId` varchar(255) DEFAULT NULL,
  `tf_userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`tf_userFieldRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_UserFieldRole`
--

LOCK TABLES `_UserFieldRole` WRITE;
/*!40000 ALTER TABLE `_UserFieldRole` DISABLE KEYS */;
/*!40000 ALTER TABLE `_UserFieldRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_UserRole`
--

DROP TABLE IF EXISTS `_UserRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_UserRole` (
  `tf_userRoleId` int(11) NOT NULL,
  `tf_roleId` varchar(10) NOT NULL,
  `tf_userId` int(11) NOT NULL,
  PRIMARY KEY (`tf_userRoleId`),
  UNIQUE KEY `index4` (`tf_roleId`,`tf_userId`),
  KEY `FKdwr53oxamuxerlmnlolun0i6n` (`tf_roleId`),
  KEY `FKfkc046xm568j763smxsqkoy8x` (`tf_userId`),
  CONSTRAINT `FKdwr53oxamuxerlmnlolun0i6n` FOREIGN KEY (`tf_roleId`) REFERENCES `_Role` (`tf_roleId`),
  CONSTRAINT `FKfkc046xm568j763smxsqkoy8x` FOREIGN KEY (`tf_userId`) REFERENCES `_User` (`tf_userId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_UserRole`
--

LOCK TABLES `_UserRole` WRITE;
/*!40000 ALTER TABLE `_UserRole` DISABLE KEYS */;
INSERT INTO `_UserRole` VALUES (1700,'0000',1),(1729,'0000',3),(1701,'0005',1),(1721,'0005',2),(1730,'0005',3),(1734,'1000',2),(1717,'1010',1);
/*!40000 ALTER TABLE `_UserRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `_attachmentonfield`
--

DROP TABLE IF EXISTS `_attachmentonfield`;
/*!50001 DROP VIEW IF EXISTS `_attachmentonfield`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `_attachmentonfield` AS SELECT 
 1 AS `tf_fieldId`,
 1 AS `tf_title`,
 1 AS `tf_fieldName`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `role_additionfunction`
--

DROP TABLE IF EXISTS `role_additionfunction`;
/*!50001 DROP VIEW IF EXISTS `role_additionfunction`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `role_additionfunction` AS SELECT 
 1 AS `tf_userId`,
 1 AS `tf_moduleId`,
 1 AS `tf_moduleAdditionFunctionId`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `role_fielddetail`
--

DROP TABLE IF EXISTS `role_fielddetail`;
/*!50001 DROP VIEW IF EXISTS `role_fielddetail`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `role_fielddetail` AS SELECT 
 1 AS `tf_userId`,
 1 AS `tf_fieldId`,
 1 AS `tf_moduleId`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `role_fieldhiddendetail`
--

DROP TABLE IF EXISTS `role_fieldhiddendetail`;
/*!50001 DROP VIEW IF EXISTS `role_fieldhiddendetail`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `role_fieldhiddendetail` AS SELECT 
 1 AS `tf_userId`,
 1 AS `tf_fieldId`,
 1 AS `tf_moduleId`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `role_fieldreadonlydetail`
--

DROP TABLE IF EXISTS `role_fieldreadonlydetail`;
/*!50001 DROP VIEW IF EXISTS `role_fieldreadonlydetail`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `role_fieldreadonlydetail` AS SELECT 
 1 AS `tf_userId`,
 1 AS `tf_fieldId`,
 1 AS `tf_moduleId`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `role_operdetail`
--

DROP TABLE IF EXISTS `role_operdetail`;
/*!50001 DROP VIEW IF EXISTS `role_operdetail`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `role_operdetail` AS SELECT 
 1 AS `tf_userId`,
 1 AS `tf_moduleId`,
 1 AS `tf_allowBrowse`,
 1 AS `tf_allowInsert`,
 1 AS `tf_allowEdit`,
 1 AS `tf_allowDelete`,
 1 AS `tf_allowExec`,
 1 AS `tf_allowAuditing`,
 1 AS `tf_allowApprove`,
 1 AS `tf_allowEditDirect`,
 1 AS `tf_allowPayment`,
 1 AS `tf_attachmentBrowse`,
 1 AS `tf_attachmentInsert`,
 1 AS `tf_attachmentEdit`,
 1 AS `tf_attachmentDelete`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `role_operdetail_temp`
--

DROP TABLE IF EXISTS `role_operdetail_temp`;
/*!50001 DROP VIEW IF EXISTS `role_operdetail_temp`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `role_operdetail_temp` AS SELECT 
 1 AS `tf_userId`,
 1 AS `tf_moduleId`,
 1 AS `tf_allowBrowse`,
 1 AS `tf_allowInsert`,
 1 AS `tf_allowEdit`,
 1 AS `tf_allowDelete`,
 1 AS `tf_allowExec`,
 1 AS `tf_allowAuditing`,
 1 AS `tf_allowApprove`,
 1 AS `tf_allowEditDirect`,
 1 AS `tf_allowPayment`,
 1 AS `tf_attachmentBrowse`,
 1 AS `tf_attachmentInsert`,
 1 AS `tf_attachmentEdit`,
 1 AS `tf_attachmentDelete`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `_attachmentonfield`
--

/*!50001 DROP VIEW IF EXISTS `_attachmentonfield`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `_attachmentonfield` AS select `_modulefield`.`tf_fieldId` AS `tf_fieldId`,`_modulefield`.`tf_title` AS `tf_title`,`_modulefield`.`tf_fieldName` AS `tf_fieldName` from `_modulefield` where (`_modulefield`.`tf_haveAttachment` = 1) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `role_additionfunction`
--

/*!50001 DROP VIEW IF EXISTS `role_additionfunction`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `role_additionfunction` AS select distinct `_user`.`tf_userId` AS `tf_userId`,`_moduleadditionfunction`.`tf_moduleId` AS `tf_moduleId`,`_rolemoduleaddition`.`tf_moduleAdditionFunctionId` AS `tf_moduleAdditionFunctionId` from ((((`_userrole` join `_user` on((`_userrole`.`tf_userId` = `_user`.`tf_userId`))) join `_role` on((`_userrole`.`tf_roleId` = `_role`.`tf_roleId`))) join `_rolemoduleaddition` on((`_role`.`tf_roleId` = `_rolemoduleaddition`.`tf_roleId`))) join `_moduleadditionfunction` on((`_rolemoduleaddition`.`tf_moduleAdditionFunctionId` = `_moduleadditionfunction`.`tf_moduleAdditionFunctionId`))) where (`_role`.`tf_isEnable` = 1) order by `_user`.`tf_userId`,`_rolemoduleaddition`.`tf_moduleAdditionFunctionId` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `role_fielddetail`
--

/*!50001 DROP VIEW IF EXISTS `role_fielddetail`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `role_fielddetail` AS select distinct `_user`.`tf_userId` AS `tf_userId`,`_fieldroledetail`.`tf_fieldId` AS `tf_fieldId`,`_modulefield`.`tf_moduleId` AS `tf_moduleId` from ((((`_user` join `_userfieldrole` on((`_user`.`tf_userId` = `_userfieldrole`.`tf_userId`))) join `_fieldrole` on((`_userfieldrole`.`tf_fieldRoleId` = `_fieldrole`.`tf_fieldRoleId`))) join `_fieldroledetail` on((`_fieldrole`.`tf_fieldRoleId` = `_fieldroledetail`.`tf_fieldRoleId`))) join `_modulefield` on((`_fieldroledetail`.`tf_fieldId` = `_modulefield`.`tf_fieldId`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `role_fieldhiddendetail`
--

/*!50001 DROP VIEW IF EXISTS `role_fieldhiddendetail`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `role_fieldhiddendetail` AS select distinct `_user`.`tf_userId` AS `tf_userId`,`_fieldhiddenroledetail`.`tf_fieldId` AS `tf_fieldId`,`_modulefield`.`tf_moduleId` AS `tf_moduleId` from ((((`_user` join `_userfieldhiddenrole` on((`_user`.`tf_userId` = `_userfieldhiddenrole`.`tf_userId`))) join `_fieldhiddenrole` on((`_userfieldhiddenrole`.`tf_fieldRoleId` = `_fieldhiddenrole`.`tf_fieldRoleId`))) join `_fieldhiddenroledetail` on((`_fieldhiddenrole`.`tf_fieldRoleId` = `_fieldhiddenroledetail`.`tf_fieldRoleId`))) join `_modulefield` on((`_fieldhiddenroledetail`.`tf_fieldId` = `_modulefield`.`tf_fieldId`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `role_fieldreadonlydetail`
--

/*!50001 DROP VIEW IF EXISTS `role_fieldreadonlydetail`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `role_fieldreadonlydetail` AS select distinct `_user`.`tf_userId` AS `tf_userId`,`_fieldreadonlyroledetail`.`tf_fieldId` AS `tf_fieldId`,`_modulefield`.`tf_moduleId` AS `tf_moduleId` from ((((`_user` join `_userfieldreadonlyrole` on((`_user`.`tf_userId` = `_userfieldreadonlyrole`.`tf_userId`))) join `_fieldreadonlyrole` on((`_userfieldreadonlyrole`.`tf_fieldRoleId` = `_fieldreadonlyrole`.`tf_fieldRoleId`))) join `_fieldreadonlyroledetail` on((`_fieldreadonlyrole`.`tf_fieldRoleId` = `_fieldreadonlyroledetail`.`tf_fieldRoleId`))) join `_modulefield` on((`_fieldreadonlyroledetail`.`tf_fieldId` = `_modulefield`.`tf_fieldId`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `role_operdetail`
--

/*!50001 DROP VIEW IF EXISTS `role_operdetail`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `role_operdetail` AS select `role_operdetail_temp`.`tf_userId` AS `tf_userId`,`role_operdetail_temp`.`tf_moduleId` AS `tf_moduleId`,sum(`role_operdetail_temp`.`tf_allowBrowse`) AS `tf_allowBrowse`,sum(`role_operdetail_temp`.`tf_allowInsert`) AS `tf_allowInsert`,sum(`role_operdetail_temp`.`tf_allowEdit`) AS `tf_allowEdit`,sum(`role_operdetail_temp`.`tf_allowDelete`) AS `tf_allowDelete`,sum(`role_operdetail_temp`.`tf_allowExec`) AS `tf_allowExec`,sum(`role_operdetail_temp`.`tf_allowAuditing`) AS `tf_allowAuditing`,sum(`role_operdetail_temp`.`tf_allowApprove`) AS `tf_allowApprove`,sum(`role_operdetail_temp`.`tf_allowEditDirect`) AS `tf_allowEditDirect`,sum(`role_operdetail_temp`.`tf_allowPayment`) AS `tf_allowPayment`,sum(`role_operdetail_temp`.`tf_attachmentBrowse`) AS `tf_attachmentBrowse`,sum(`role_operdetail_temp`.`tf_attachmentInsert`) AS `tf_attachmentInsert`,sum(`role_operdetail_temp`.`tf_attachmentEdit`) AS `tf_attachmentEdit`,sum(`role_operdetail_temp`.`tf_attachmentDelete`) AS `tf_attachmentDelete` from `role_operdetail_temp` group by `role_operdetail_temp`.`tf_userId`,`role_operdetail_temp`.`tf_moduleId` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `role_operdetail_temp`
--

/*!50001 DROP VIEW IF EXISTS `role_operdetail_temp`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `role_operdetail_temp` AS select `_userrole`.`tf_userId` AS `tf_userId`,`_popedom`.`tf_moduleId` AS `tf_moduleId`,((select ifnull(`_module`.`tf_hasBrowse`,0) AS `Expr1` from `_module` where (`_module`.`tf_moduleId` = `_popedom`.`tf_moduleId`)) * `_popedom`.`tf_allowBrowse`) AS `tf_allowBrowse`,((select ifnull(`_Module_2`.`tf_hasInsert`,0) AS `Expr1` from `_module` `_Module_2` where (`_Module_2`.`tf_moduleId` = `_popedom`.`tf_moduleId`)) * `_popedom`.`tf_allowInsert`) AS `tf_allowInsert`,((select ifnull(`_Module_2`.`tf_hasEdit`,0) AS `Expr1` from `_module` `_Module_2` where (`_Module_2`.`tf_moduleId` = `_popedom`.`tf_moduleId`)) * `_popedom`.`tf_allowEdit`) AS `tf_allowEdit`,((select ifnull(`_Module_2`.`tf_hasDelete`,0) AS `Expr1` from `_module` `_Module_2` where (`_Module_2`.`tf_moduleId` = `_popedom`.`tf_moduleId`)) * `_popedom`.`tf_allowDelete`) AS `tf_allowDelete`,((select ifnull(`_Module_2`.`tf_hasExec`,0) AS `Expr1` from `_module` `_Module_2` where (`_Module_2`.`tf_moduleId` = `_popedom`.`tf_moduleId`)) * `_popedom`.`tf_allowExec`) AS `tf_allowExec`,((select ifnull(`_Module_2`.`tf_hasAuditing`,0) AS `Expr1` from `_module` `_Module_2` where (`_Module_2`.`tf_moduleId` = `_popedom`.`tf_moduleId`)) * `_popedom`.`tf_allowAuditing`) AS `tf_allowAuditing`,((select ifnull(`_Module_2`.`tf_hasApprove`,0) AS `Expr1` from `_module` `_Module_2` where (`_Module_2`.`tf_moduleId` = `_popedom`.`tf_moduleId`)) * `_popedom`.`tf_allowApprove`) AS `tf_allowApprove`,((select ifnull((`_Module_2`.`tf_hasApprove` | `_Module_2`.`tf_hasAuditing`),0) AS `Expr1` from `_module` `_Module_2` where (`_Module_2`.`tf_moduleId` = `_popedom`.`tf_moduleId`)) * `_popedom`.`tf_allowEditDirect`) AS `tf_allowEditDirect`,((select ifnull(`_Module_2`.`tf_hasPayment`,0) AS `Expr1` from `_module` `_Module_2` where (`_Module_2`.`tf_moduleId` = `_popedom`.`tf_moduleId`)) * `_popedom`.`tf_allowPayment`) AS `tf_allowPayment`,((select ifnull(`_Module_2`.`tf_hasAttachment`,0) AS `Expr1` from `_module` `_Module_2` where (`_Module_2`.`tf_moduleId` = `_popedom`.`tf_moduleId`)) * `_popedom`.`tf_attachmentBrowse`) AS `tf_attachmentBrowse`,((select ifnull(`_Module_2`.`tf_hasAttachment`,0) AS `Expr1` from `_module` `_Module_2` where (`_Module_2`.`tf_moduleId` = `_popedom`.`tf_moduleId`)) * `_popedom`.`tf_attachmentInsert`) AS `tf_attachmentInsert`,((select ifnull(`_Module_2`.`tf_hasAttachment`,0) AS `Expr1` from `_module` `_Module_2` where (`_Module_2`.`tf_moduleId` = `_popedom`.`tf_moduleId`)) * `_popedom`.`tf_attachmentEdit`) AS `tf_attachmentEdit`,((select ifnull(`_Module_2`.`tf_hasAttachment`,0) AS `Expr1` from `_module` `_Module_2` where (`_Module_2`.`tf_moduleId` = `_popedom`.`tf_moduleId`)) * `_popedom`.`tf_attachmentDelete`) AS `tf_attachmentDelete` from (((`_role` join `_popedom` on((`_role`.`tf_roleId` = `_popedom`.`tf_roleId`))) join `_userrole` on((`_role`.`tf_roleId` = `_userrole`.`tf_roleId`))) join `_module` `_Module_1` on((`_popedom`.`tf_moduleId` = `_Module_1`.`tf_moduleId`))) where ((`_Module_1`.`tf_isEnable` = 1) and (`_role`.`tf_isEnable` = 1)) order by `_userrole`.`tf_userId`,`_popedom`.`tf_moduleId` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-13 11:11:48
