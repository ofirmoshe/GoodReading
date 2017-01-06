CREATE DATABASE  IF NOT EXISTS `i-book` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `i-book`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: i-book
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author_book`
--

DROP TABLE IF EXISTS `author_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author_book` (
  `AuthorID` int(11) NOT NULL,
  `BookID` int(11) NOT NULL,
  PRIMARY KEY (`AuthorID`,`BookID`),
  KEY `FKAuthor_Boo817531` (`AuthorID`),
  KEY `FKAuthor_Boo116471` (`BookID`),
  CONSTRAINT `FKAuthor_Boo116471` FOREIGN KEY (`BookID`) REFERENCES `book` (`ID`),
  CONSTRAINT `FKAuthor_Boo817531` FOREIGN KEY (`AuthorID`) REFERENCES `author` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author_book`
--

LOCK TABLES `author_book` WRITE;
/*!40000 ALTER TABLE `author_book` DISABLE KEYS */;
/*!40000 ALTER TABLE `author_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) DEFAULT NULL,
  `Language` varchar(255) DEFAULT NULL,
  `Summary` varchar(255) DEFAULT NULL,
  `Table_of_contents` varchar(255) DEFAULT NULL,
  `Image` varchar(255) DEFAULT NULL,
  `Pdf` varchar(255) DEFAULT NULL,
  `Doc` varchar(255) DEFAULT NULL,
  `Fb2` varchar(255) DEFAULT NULL,
  `Price` float NOT NULL,
  `Status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Fantastic Beasts','English',NULL,NULL,'https://images-na.ssl-images-amazon.com/images/I/61kS08bR-EL._SX311_BO1,204,203,200_.jpg',NULL,NULL,'',20,NULL),(2,'The Winter Over','English',NULL,NULL,'https://images-na.ssl-images-amazon.com/images/I/51G4yhDlZKL.jpg',NULL,NULL,NULL,18,NULL),(3,'The Fault in Our Stars','English',NULL,NULL,'https://images-na.ssl-images-amazon.com/images/I/51VlKD1aucL._SX312_BO1,204,203,200_.jpg',NULL,NULL,NULL,25,NULL),(4,'The Black Key','English',NULL,NULL,'https://images-na.ssl-images-amazon.com/images/I/51faryWZWkL._SX329_BO1,204,203,200_.jpg',NULL,NULL,NULL,14,NULL),(5,'Something Sweet','English',NULL,NULL,'https://images-na.ssl-images-amazon.com/images/I/51dL%2BVTqLmL._SX416_BO1,204,203,200_.jpg',NULL,NULL,NULL,12,NULL),(6,'A Dog\'s Purpose','English',NULL,NULL,'https://images-na.ssl-images-amazon.com/images/I/51q94MZ3BQL._SX330_BO1,204,203,200_.jpg',NULL,NULL,NULL,7,NULL),(7,'Small Great Things','English',NULL,NULL,'https://images-na.ssl-images-amazon.com/images/I/51MzOneN8rL._SX325_BO1,204,203,200_.jpg',NULL,NULL,NULL,10,NULL),(8,'Commonwealth','English',NULL,NULL,'https://images-na.ssl-images-amazon.com/images/I/51IZNKC9j%2BL._SX329_BO1,204,203,200_.jpg',NULL,NULL,NULL,13,NULL),(9,'Two By Two','English',NULL,NULL,'https://images-na.ssl-images-amazon.com/images/I/51SRNKysi0L._SX332_BO1,204,203,200_.jpg',NULL,NULL,NULL,20,NULL),(10,'Star Wars','English',NULL,NULL,'https://images-na.ssl-images-amazon.com/images/I/51MC3VIGAnL._SX327_BO1,204,203,200_.jpg',NULL,NULL,NULL,15,NULL),(11,'1984','English',NULL,NULL,'https://images-na.ssl-images-amazon.com/images/I/31besGg4t3L._SX311_BO1,204,203,200_.jpg',NULL,NULL,NULL,14,NULL);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_field`
--

DROP TABLE IF EXISTS `book_field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_field` (
  `BookID` int(11) NOT NULL,
  `FieldID` int(11) NOT NULL,
  PRIMARY KEY (`BookID`,`FieldID`),
  KEY `FKBook_Field706405` (`BookID`),
  KEY `FKBook_Field16477` (`FieldID`),
  CONSTRAINT `FKBook_Field16477` FOREIGN KEY (`FieldID`) REFERENCES `field` (`ID`),
  CONSTRAINT `FKBook_Field706405` FOREIGN KEY (`BookID`) REFERENCES `book` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_field`
--

LOCK TABLES `book_field` WRITE;
/*!40000 ALTER TABLE `book_field` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_subject`
--

DROP TABLE IF EXISTS `book_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_subject` (
  `BookID` int(11) NOT NULL,
  `SubjectID` int(11) NOT NULL,
  PRIMARY KEY (`BookID`,`SubjectID`),
  KEY `FKBook_Subje677660` (`BookID`),
  KEY `FKBook_Subje480032` (`SubjectID`),
  CONSTRAINT `FKBook_Subje480032` FOREIGN KEY (`SubjectID`) REFERENCES `subject` (`ID`),
  CONSTRAINT `FKBook_Subje677660` FOREIGN KEY (`BookID`) REFERENCES `book` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_subject`
--

LOCK TABLES `book_subject` WRITE;
/*!40000 ALTER TABLE `book_subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `field`
--

DROP TABLE IF EXISTS `field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `field` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SubjectID` int(11) NOT NULL,
  `Field` varchar(255) DEFAULT NULL,
  `FieldID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `field`
--

LOCK TABLES `field` WRITE;
/*!40000 ALTER TABLE `field` DISABLE KEYS */;
/*!40000 ALTER TABLE `field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generaluser`
--

DROP TABLE IF EXISTS `generaluser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `generaluser` (
  `ID` varchar(255) NOT NULL,
  `Fname` varchar(255) DEFAULT NULL,
  `Lname` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Em_num` int(11) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Dep` varchar(255) DEFAULT NULL,
  `Position` varchar(255) DEFAULT NULL,
  `PaymentInfo` varchar(255) DEFAULT NULL,
  `Status` varchar(255) DEFAULT 'offline',
  `Discriminator` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generaluser`
--

LOCK TABLES `generaluser` WRITE;
/*!40000 ALTER TABLE `generaluser` DISABLE KEYS */;
INSERT INTO `generaluser` VALUES ('1','Guy','Zion','123',NULL,NULL,NULL,NULL,NULL,NULL,'User'),('2','Ofir','Moshe','0101',NULL,NULL,NULL,NULL,NULL,NULL,'User'),('3','Noy','Machlev','666',NULL,NULL,NULL,NULL,NULL,NULL,'User'),('4','Ofir','Chava','1010',NULL,NULL,NULL,NULL,NULL,NULL,'User');
/*!40000 ALTER TABLE `generaluser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `keyword`
--

DROP TABLE IF EXISTS `keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `keyword` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Word` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keyword`
--

LOCK TABLES `keyword` WRITE;
/*!40000 ALTER TABLE `keyword` DISABLE KEYS */;
/*!40000 ALTER TABLE `keyword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `keyword_book`
--

DROP TABLE IF EXISTS `keyword_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `keyword_book` (
  `KeywordID` int(11) NOT NULL,
  `BookID` int(11) NOT NULL,
  PRIMARY KEY (`KeywordID`,`BookID`),
  KEY `FKKeyword_Bo115416` (`KeywordID`),
  KEY `FKKeyword_Bo670081` (`BookID`),
  CONSTRAINT `FKKeyword_Bo115416` FOREIGN KEY (`KeywordID`) REFERENCES `keyword` (`ID`),
  CONSTRAINT `FKKeyword_Bo670081` FOREIGN KEY (`BookID`) REFERENCES `book` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keyword_book`
--

LOCK TABLES `keyword_book` WRITE;
/*!40000 ALTER TABLE `keyword_book` DISABLE KEYS */;
/*!40000 ALTER TABLE `keyword_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membership`
--

DROP TABLE IF EXISTS `membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membership` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(255) DEFAULT NULL,
  `Price` float NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membership`
--

LOCK TABLES `membership` WRITE;
/*!40000 ALTER TABLE `membership` DISABLE KEYS */;
/*!40000 ALTER TABLE `membership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentrequest`
--

DROP TABLE IF EXISTS `paymentrequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymentrequest` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `GeneralUserID` varchar(255) NOT NULL,
  `Date` date DEFAULT NULL,
  `PaymentInfo` varchar(255) DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKPaymentReq760019` (`GeneralUserID`),
  CONSTRAINT `FKPaymentReq760019` FOREIGN KEY (`GeneralUserID`) REFERENCES `generaluser` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentrequest`
--

LOCK TABLES `paymentrequest` WRITE;
/*!40000 ALTER TABLE `paymentrequest` DISABLE KEYS */;
/*!40000 ALTER TABLE `paymentrequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Text` varchar(255) DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  `User_BookBookID` int(11) NOT NULL,
  `User_BookGeneralUserID` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKReview85949` (`User_BookBookID`,`User_BookGeneralUserID`),
  CONSTRAINT `FKReview85949` FOREIGN KEY (`User_BookBookID`, `User_BookGeneralUserID`) REFERENCES `user_book` (`BookID`, `GeneralUserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Sub` varchar(255) DEFAULT NULL,
  `FieldID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKSubject46610` (`FieldID`),
  CONSTRAINT `FKSubject46610` FOREIGN KEY (`FieldID`) REFERENCES `field` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_book`
--

DROP TABLE IF EXISTS `user_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_book` (
  `PDate` date DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  `BookID` int(11) NOT NULL,
  `GeneralUserID` varchar(255) NOT NULL,
  PRIMARY KEY (`BookID`,`GeneralUserID`),
  KEY `FKUser_Book569873` (`BookID`),
  KEY `FKUser_Book221107` (`GeneralUserID`),
  CONSTRAINT `FKUser_Book221107` FOREIGN KEY (`GeneralUserID`) REFERENCES `generaluser` (`ID`),
  CONSTRAINT `FKUser_Book569873` FOREIGN KEY (`BookID`) REFERENCES `book` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_book`
--

LOCK TABLES `user_book` WRITE;
/*!40000 ALTER TABLE `user_book` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_membership`
--

DROP TABLE IF EXISTS `user_membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_membership` (
  `S_date` date NOT NULL,
  `E_date` date DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  `MembershipID` int(11) NOT NULL,
  `GeneralUserID` varchar(255) NOT NULL,
  PRIMARY KEY (`S_date`,`MembershipID`,`GeneralUserID`),
  KEY `FKUser_Membe409156` (`MembershipID`),
  KEY `FKUser_Membe885132` (`GeneralUserID`),
  CONSTRAINT `FKUser_Membe409156` FOREIGN KEY (`MembershipID`) REFERENCES `membership` (`ID`),
  CONSTRAINT `FKUser_Membe885132` FOREIGN KEY (`GeneralUserID`) REFERENCES `generaluser` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_membership`
--

LOCK TABLES `user_membership` WRITE;
/*!40000 ALTER TABLE `user_membership` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_membership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `views_date`
--

DROP TABLE IF EXISTS `views_date`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `views_date` (
  `Date` date NOT NULL,
  `BookID` int(11) NOT NULL,
  `ViewCount` int(11) NOT NULL,
  PRIMARY KEY (`Date`,`BookID`),
  KEY `FKViews_Date742991` (`BookID`),
  CONSTRAINT `FKViews_Date742991` FOREIGN KEY (`BookID`) REFERENCES `book` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `views_date`
--

LOCK TABLES `views_date` WRITE;
/*!40000 ALTER TABLE `views_date` DISABLE KEYS */;
/*!40000 ALTER TABLE `views_date` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-06 15:25:05
