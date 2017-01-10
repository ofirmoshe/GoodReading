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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'JK Rowling'),(2,'Matthew Iden'),(3,'John Green'),(4,'Amy Ewing'),(5,'Miriam Pascal'),(6,'W. Bruce Cameron'),(7,'Jodi Picoult'),(8,'Ann Patchett'),(9,'Nicholas Sparks'),(10,'James Luceno'),(11,'George Orwell');
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
INSERT INTO `author_book` VALUES (1,1),(1,12),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11);
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
  `Summary` mediumtext,
  `Table_of_contents` mediumtext,
  `Image` varchar(255) DEFAULT NULL,
  `Pdf` varchar(255) DEFAULT NULL,
  `Doc` varchar(255) DEFAULT NULL,
  `Fb2` varchar(255) DEFAULT NULL,
  `Price` float NOT NULL,
  `Status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Fantastic Beasts','English','J.K. Rowlings screenwriting debut is captured in this exciting hardcover edition of the Fantastic Beasts and Where to Find Them screenplay.\nWhen Magizoologist Newt Scamander arrives in New York, he intends his stay to be just a brief stopover. However, when his magical case is misplaced and some of Newts fantastic beasts escape, it spells trouble for everyone…\nFantastic Beasts and Where to Find Them marks the screenwriting debut of J.K. Rowling, author of the beloved and internationally bestselling Harry Potter books. Featuring a cast of remarkable characters, this is epic, adventure-packed storytelling at its very best.\nWhether an existing fan or new to the wizarding world, this is a perfect addition to any readers bookshelf.',NULL,'https://images-na.ssl-images-amazon.com/images/I/61kS08bR-EL._SX311_BO1,204,203,200_.jpg',NULL,NULL,'',20,NULL),(2,'The Winter Over','English','Each winter the crew at the Shackleton South Pole Research Facility faces nine months of isolation, round-the-clock darkness, and one of the most extreme climates on the planet. For thirty-something mechanical engineer Cass Jennings, Antarctica offers an opportunity to finally escape the guilt of her troubled past and to rebuild her life.\n\nBut the death of a colleague triggers a series of mysterious incidents that push Cass and the rest of the forty-four-person crew to the limits of their sanity and endurance. Confined and cut off from the outside world, will they work together or turn against one another? As the tension escalates, Cass must find the strength to survive not only a punishing landscape but also an unrelenting menace determined to destroy the station—and everyone in it',NULL,'https://images-na.ssl-images-amazon.com/images/I/51G4yhDlZKL.jpg',NULL,NULL,NULL,18,NULL),(3,'The Fault in Our Stars','English','Despite the tumor-shrinking medical miracle that has bought her a few years, Hazel has never been anything but terminal, her final chapter inscribed upon diagnosis. But when a gorgeous plot twist named Augustus Waters suddenly appears at Cancer Kid Support Group, Hazels story is about to be completely rewritten.\n\nInsightful, bold, irreverent, and raw, The Fault in Our Stars is award-winning-author John Greens most ambitious and heartbreaking work yet, brilliantly exploring the funny, thrilling, and tragic business of being alive and in love. \n',NULL,'https://images-na.ssl-images-amazon.com/images/I/51VlKD1aucL._SX312_BO1,204,203,200_.jpg',NULL,NULL,NULL,25,NULL),(4,'The Black Key','English','The thrilling conclusion to the New York Times bestselling Lone City trilogy, which began with The Jewel, a book BCCB said- will have fans of Olivers Delirium, Cass The Selection, and DeStefanos Wither breathless.\n\nFor too long, Violet and the people of the outer circles of the Lone City have lived in service of the royalty of the Jewel. But now, the secret society known as the Black Key is preparing to seize power.\n\nWhile Violet knows she is at the center of this rebellion, she has a more personal stake in it—for her sister, Hazel, has been taken by the Duchess of the Lake. Now, after fighting so hard to escape the Jewel, Violet must do everything in her power to return not only to save Hazel, but the future of the Lone City.',NULL,'https://images-na.ssl-images-amazon.com/images/I/51faryWZWkL._SX329_BO1,204,203,200_.jpg',NULL,NULL,NULL,14,NULL),(5,'Something Sweet','English','Miriam is a master at taking familiar kosher ingredients and combining them into creative treats that look beautiful, taste amazing, and arent hard to create. And, with her infectious enthusiasm, she makes it so much fun!',NULL,'https://images-na.ssl-images-amazon.com/images/I/51dL%2BVTqLmL._SX416_BO1,204,203,200_.jpg',NULL,NULL,NULL,12,NULL),(6,'A Dogs Purpose','English','A Dogs Purpose is not only the emotional and hilarious story of a dogs many lives, but also a dogs-eye commentary on human relationships and the unbreakable bonds between man and mans best friend. This moving and beautifully crafted story teaches us that love never dies, that our true friends are always with us, and that every creature on earth is born with a purpose.',NULL,'https://images-na.ssl-images-amazon.com/images/I/51q94MZ3BQL._SX330_BO1,204,203,200_.jpg',NULL,NULL,NULL,7,NULL),(7,'Small Great Things','English','Ruth Jefferson is a labor and delivery nurse at a Connecticut hospital with more than twenty years experience. During her shift, Ruth begins a routine checkup on a newborn, only to be told a few minutes later that shes been reassigned to another patient. The parents are white supremacists and dont want Ruth, who is African American, to touch their child. The hospital complies with their request, but the next day, the baby goes into cardiac distress while Ruth is alone in the nursery. Does she obey orders or does she intervene?',NULL,'https://images-na.ssl-images-amazon.com/images/I/51MzOneN8rL._SX325_BO1,204,203,200_.jpg',NULL,NULL,NULL,10,NULL),(8,'Commonwealth','English','One Sunday afternoon in Southern California, Bert Cousins shows up at Franny Keatings christening party uninvited. Before evening falls, he has kissed Frannys mother, Beverly—thus setting in motion the dissolution of their marriages and the joining of two families.\n\nSpanning five decades, Commonwealth explores how this chance encounter reverberates through the lives of the four parents and six children involved. Spending summers together in Virginia, the Keating and Cousins children forge a lasting bond that is based on a shared disillusionment with their parents and the strange and genuine affection that grows up between them.\n\nWhen, in her twenties, Franny begins an affair with the legendary author Leon Posen and tells him about her family, the story of her siblings is no longer hers to control. Their childhood becomes the basis for his wildly successful book, ultimately forcing them to come to terms with their losses, their guilt, and the deeply loyal connection they feel for one another.',NULL,'https://images-na.ssl-images-amazon.com/images/I/51IZNKC9j%2BL._SX329_BO1,204,203,200_.jpg',NULL,NULL,NULL,13,NULL),(9,'Two By Two','English','At 32, Russell Green has it all: a stunning wife, a lovable six year-old daughter, a successful career as an advertising executive and an expansive home in Charlotte. He is living the dream, and his marriage to the bewitching Vivian is the center of that. But underneath the shiny surface of this perfect existence, fault lines are beginning to appear...and no one is more surprised than Russ when he finds every aspect of the life he took for granted turned upside down. In a matter of months, Russ finds himself without a job or wife, caring for his young daughter while struggling to adapt to a new and baffling reality. Throwing himself into the wilderness of single parenting, Russ embarks on a journey at once terrifying and rewarding-one that will test his abilities and his emotional resources beyond anything he ever imagined.',NULL,'https://images-na.ssl-images-amazon.com/images/I/51SRNKysi0L._SX332_BO1,204,203,200_.jpg',NULL,NULL,NULL,20,NULL),(10,'Star Wars','English','War is tearing the galaxy apart. For years the Republic and the Separatists have battled across the stars, each building more and more deadly technology in an attempt to win the war. As a member of Chancellor Palpatines top secret Death Star project, Orson Krennic is determined to develop a superweapon before their enemies can. And an old friend of Krennics, the brilliant scientist Galen Erso, could be the key.\n\nGalens energy-focused research has captured the attention of both Krennic and his foes, making the scientist a crucial pawn in the galactic conflict. But after Krennic rescues Galen, his wife, Lyra, and their young daughter, Jyn, from Separatist kidnappers, the Erso family is deeply in Krennics debt. Krennic then offers Galen an extraordinary opportunity: to continue his scientific studies with every resource put utterly at his disposal. While Galen and Lyra believe that his energy research will be used purely in altruistic ways, Krennic has other plans that will finally make the Death Star a reality. Trapped in their benefactors tightening grasp, the Ersos must untangle Krennics web of deception to save themselves and the galaxy itself.',NULL,'https://images-na.ssl-images-amazon.com/images/I/51MC3VIGAnL._SX327_BO1,204,203,200_.jpg',NULL,NULL,NULL,15,NULL),(11,'1984','English','1984 was George Orwells chilling prophecy about the future. And while 1984 has come and gone, Orwells narrative is timelier than ever. 1984 presents a startling and haunting vision of the world, so powerful that it is completely convincing from start to finish. No one can deny the power of this novel, its hold on the imaginations of multiple generations of readers, or the resiliency of its admonitions—a legacy that seems only to grow with the passage of time.',NULL,'https://images-na.ssl-images-amazon.com/images/I/31besGg4t3L._SX311_BO1,204,203,200_.jpg',NULL,NULL,NULL,14,NULL),(12,'Harry Potter','Russian','It was always difficult being Harry Potter and it isnt much easier now that he is an overworked employee of the Ministry of Magic, a husband and father of three school-age children.\n\nWhile Harry grapples with a past that refuses to stay where it belongs, his youngest son Albus must struggle with the weight of a family legacy he never wanted. As past and present fuse ominously, both father and son learn the uncomfortable truth: sometimes, darkness comes from unexpected places.',NULL,'https://images-na.ssl-images-amazon.com/images/I/518VhA3dH9L._SX329_BO1,204,203,200_.jpg',NULL,NULL,NULL,17.99,NULL);
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
INSERT INTO `book_field` VALUES (1,1),(1,15),(2,13),(2,15),(3,1),(4,1),(4,2),(5,8),(6,9),(7,1),(7,12),(7,14),(8,4),(8,10),(9,14),(10,1),(10,15),(11,1),(11,13),(12,1),(12,15);
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
INSERT INTO `book_subject` VALUES (1,1),(2,46),(2,55),(3,3),(4,4),(5,25),(6,32),(7,4),(7,42),(7,49),(8,10),(8,35),(9,53),(10,1),(10,56),(12,3);
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
  `Field` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `field`
--

LOCK TABLES `field` WRITE;
/*!40000 ALTER TABLE `field` DISABLE KEYS */;
INSERT INTO `field` VALUES (1,'Literature & Fiction'),(2,'Arts & Photography'),(3,'Biographies & Memoirs'),(4,'Business & Money'),(5,'Christian Books & Bibles'),(6,'Comics & Graphic Novels'),(7,'Computers & Technology'),(8,'Cookbooks, Food & Wine'),(9,'Crafts, Hobbies & Home'),(10,'Education & Teaching'),(11,'Engineering & Transportation'),(12,'Health, Fitness & Dieting'),(13,'History'),(14,'Humor & Entertainment'),(15,'Mystery, Thriller & Suspense'),(16,'Politics & Social Sciences');
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
  `Status` varchar(255) DEFAULT NULL,
  `Discriminator` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generaluser`
--

LOCK TABLES `generaluser` WRITE;
/*!40000 ALTER TABLE `generaluser` DISABLE KEYS */;
INSERT INTO `generaluser` VALUES ('1','Guy','Zion','123',NULL,NULL,NULL,NULL,'123456','offline','User'),('2','Ofir','Moshe','0101',NULL,NULL,NULL,NULL,'234567','offline','User'),('3','Noy','Machlev','666',NULL,NULL,NULL,NULL,'345678','offline','User'),('4','Ofir','Chava','1010',NULL,NULL,NULL,NULL,'456789','offline','User');
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
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keyword`
--

LOCK TABLES `keyword` WRITE;
/*!40000 ALTER TABLE `keyword` DISABLE KEYS */;
INSERT INTO `keyword` VALUES (1,'Hazel'),(2,'Harry '),(3,'child'),(4,'series'),(5,'London'),(6,'Magic'),(7,'darkness'),(8,'wand'),(9,'Hermione'),(10,'Potter'),(11,'Ron'),(12,'Weasley'),(13,'Goerge'),(14,'Orwell'),(15,'future'),(16,'vision'),(17,'imagination'),(18,'Death'),(19,'Star '),(20,'war'),(21,'Krennic'),(22,'Galen'),(23,'galaxy'),(24,'Erso'),(25,'captured'),(26,'scientist'),(27,'Lyra'),(28,'Russell'),(29,'turned'),(30,'Green'),(31,'journey '),(32,'successful '),(33,'parent'),(34,'single'),(35,'wife'),(36,'daughter'),(37,'dream'),(38,'marriage'),(39,'California'),(40,'Bert '),(41,'Franny '),(42,'kiss'),(43,'Beverly'),(44,'heartbreak'),(45,'humor'),(46,'Virginia'),(47,'Summer'),(48,'crime'),(49,'Ruth '),(50,'Jefferson '),(51,'nurse '),(52,'hospital '),(53,'African '),(54,'sensation'),(55,'Kennedy '),(56,'trust'),(57,'dog'),(58,'purpose'),(59,'humans'),(60,'perspective '),(61,'love'),(62,'relationships '),(63,'unbreakable '),(64,'bonds '),(65,'friend'),(66,'food'),(67,'sweet'),(68,'kosher'),(69,'goods'),(70,'dessert'),(71,'secret '),(72,'society '),(73,'Black'),(74,'Key'),(75,'rebellion'),(76,'Violet '),(77,'Duchess '),(78,'Power'),(79,'royalty '),(80,'Jewel'),(81,'medical '),(82,'Cancer '),(83,'Augustus '),(84,'Waters '),(85,'fault'),(86,'tragic '),(87,'alive '),(88,'winter '),(89,'Shackleton '),(90,'Research '),(91,'crew '),(92,'engineer '),(93,'Antarctica '),(94,'climates '),(95,'extreme '),(96,'mechanical '),(97,'survive '),(98,'sanity '),(99,'fantastic'),(100,'beasts'),(101,'wizard'),(102,'Magizoologist '),(103,'Newt'),(104,'New York'),(105,'Scamander ');
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
INSERT INTO `keyword_book` VALUES (1,3),(1,4),(2,12),(3,3),(3,7),(3,9),(3,12),(4,12),(5,12),(6,1),(6,12),(7,1),(7,2),(7,12),(8,1),(8,12),(9,12),(10,12),(11,12),(12,12),(13,11),(14,11),(15,11),(16,11),(17,10),(17,11),(17,12),(18,2),(18,9),(18,10),(19,10),(20,10),(20,12),(21,10),(22,10),(23,10),(24,10),(25,1),(25,7),(25,10),(26,2),(26,10),(27,10),(28,9),(29,3),(29,9),(29,12),(30,3),(30,9),(31,6),(31,9),(32,8),(32,9),(33,3),(33,7),(33,8),(33,9),(34,9),(35,8),(35,9),(35,12),(36,3),(36,8),(36,9),(37,8),(37,9),(38,8),(38,9),(38,12),(39,8),(40,8),(41,8),(42,3),(42,8),(43,8),(44,3),(44,9),(45,6),(45,8),(46,8),(47,8),(48,7),(49,7),(50,7),(51,7),(52,3),(52,7),(53,7),(54,7),(55,7),(56,6),(56,7),(56,9),(57,6),(58,6),(59,6),(60,6),(61,3),(61,6),(61,7),(61,8),(61,9),(62,6),(63,6),(64,6),(65,6),(65,12),(66,5),(67,5),(68,5),(69,5),(70,5),(71,4),(72,4),(73,4),(74,4),(75,4),(76,4),(77,4),(78,1),(78,4),(78,10),(78,11),(78,12),(79,4),(80,4),(81,3),(82,3),(83,3),(84,3),(85,3),(86,3),(87,3),(88,2),(89,2),(90,2),(91,2),(91,12),(92,2),(93,2),(94,2),(95,2),(95,12),(96,2),(97,2),(97,12),(98,2),(99,1),(100,1),(101,1),(101,12),(102,1),(103,1),(104,1),(105,1);
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
  `Days` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membership`
--

LOCK TABLES `membership` WRITE;
/*!40000 ALTER TABLE `membership` DISABLE KEYS */;
INSERT INTO `membership` VALUES (1,'Monthly',24.99,30),(2,'Yearly',89.99,365);
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
  `MembershipID` int(11) DEFAULT NULL,
  `BookID` int(11) DEFAULT NULL,
  `GeneralUserID` varchar(255) NOT NULL,
  `Date` date DEFAULT NULL,
  `PaymentInfo` varchar(255) DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKPaymentReq760019` (`GeneralUserID`),
  KEY `FKPaymentReq551000` (`BookID`),
  KEY `FKPaymentReq235996` (`MembershipID`),
  CONSTRAINT `FKPaymentReq235996` FOREIGN KEY (`MembershipID`) REFERENCES `membership` (`ID`),
  CONSTRAINT `FKPaymentReq551000` FOREIGN KEY (`BookID`) REFERENCES `book` (`ID`),
  CONSTRAINT `FKPaymentReq760019` FOREIGN KEY (`GeneralUserID`) REFERENCES `generaluser` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentrequest`
--

LOCK TABLES `paymentrequest` WRITE;
/*!40000 ALTER TABLE `paymentrequest` DISABLE KEYS */;
INSERT INTO `paymentrequest` VALUES (59,NULL,2,'3','2017-01-10','345678','waiting');
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
  `User_BookGeneralUserID` varchar(255) NOT NULL,
  `User_BookBookID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKReview85949` (`User_BookBookID`,`User_BookGeneralUserID`),
  CONSTRAINT `FKReview85949` FOREIGN KEY (`User_BookBookID`, `User_BookGeneralUserID`) REFERENCES `user_book` (`BookID`, `GeneralUserID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (2,'I hate this book','approved','2',1),(3,'This book is wow','approved','3',1),(4,'I don\'t know how to read','approved','4',1),(8,'i freaking love this book','approved','1',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Action & Adventure',1),(2,'Ancient & Medieval Literature',1),(3,'Dramas & Plays',1),(4,'Erotica',1),(5,'Arts & Literature',2),(6,'Ethnic & National',2),(7,'Professionals & Academics',2),(8,'Accounting',4),(9,'Business Culture',4),(10,'Economics',4),(11,'Finance',4),(12,'Job Hunting & Careers',4),(13,'Action & Adventure Manga',6),(14,'Art of Comics and Manga',6),(15,'Comic Books',6),(16,'Romance Manga',6),(17,'Computer Science',7),(18,'Databases & Big Data',7),(19,'Networking & Cloud Computing',7),(20,'Programming Languages',7),(21,'Web Development & Design',7),(22,'Baking',8),(23,'Beverages & Wine',8),(24,'Italian Cooking',8),(25,'Desserts',8),(26,'Antiques & Collectibles',9),(27,'Coloring Books for Grown-Ups',9),(28,'Crafts & Hobbies',9),(29,'Gardening & Landscape Design',9),(30,'Home Improvement & Design',9),(31,'Needlecrafts & Textile Crafts',9),(32,'Pets & Animal Care',9),(33,'Sustainable Living',9),(34,'Weddings',9),(35,'Higher & Continuing Education',10),(36,'Schools & Teaching',10),(37,'Studying & Workbooks',10),(38,'Test Preparation',10),(39,'Aging',12),(40,'Alternative Medicine',12),(41,'Mental Health',12),(42,'Sexual Health',12),(43,'Teen Health',12),(44,'Africa',13),(45,'Americas',13),(46,'Arctic & Antarctica',13),(47,'Asia',13),(48,'Middle East',13),(49,'Humor',14),(50,'Coloring Books for Grown-Ups',14),(51,'Pop Culture',14),(52,'Sheet Music & Scores',14),(53,'Trivia & Fun Facts',14),(54,'Movies',14),(55,'Mystery',15),(56,'Thrillers & Suspense',15),(57,'Writing',15);
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
INSERT INTO `user_book` VALUES (NULL,NULL,1,'1'),(NULL,NULL,1,'2'),(NULL,NULL,1,'3'),(NULL,NULL,1,'4');
/*!40000 ALTER TABLE `user_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_membership`
--

DROP TABLE IF EXISTS `user_membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_membership` (
  `S_date` date DEFAULT NULL,
  `E_date` date DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  `MembershipID` int(11) NOT NULL,
  `GeneralUserID` varchar(255) NOT NULL,
  PRIMARY KEY (`MembershipID`,`GeneralUserID`),
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
  PRIMARY KEY (`Date`),
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

-- Dump completed on 2017-01-10 21:42:39
