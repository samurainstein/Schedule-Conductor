-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: scheduleconductor
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `first_level_divisions`
--

DROP TABLE IF EXISTS `first_level_divisions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `first_level_divisions` (
  `Division_ID` int NOT NULL AUTO_INCREMENT,
  `Division` varchar(50) DEFAULT NULL,
  `Country_ID` int DEFAULT NULL,
  PRIMARY KEY (`Division_ID`),
  KEY `fk_country_id_idx` (`Country_ID`),
  CONSTRAINT `fk_country_id` FOREIGN KEY (`Country_ID`) REFERENCES `countries` (`Country_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `first_level_divisions`
--

LOCK TABLES `first_level_divisions` WRITE;
/*!40000 ALTER TABLE `first_level_divisions` DISABLE KEYS */;
INSERT INTO `first_level_divisions` VALUES (1,'Alabama',1),(2,'Arizona',1),(3,'Arkansas',1),(4,'California',1),(5,'Colorado',1),(6,'Connecticut',1),(7,'Delaware',1),(8,'District of Columbia',1),(9,'Florida',1),(10,'Georgia',1),(11,'Idaho',1),(12,'Illinois',1),(13,'Indiana',1),(14,'Iowa',1),(15,'Kansas',1),(16,'Kentucky',1),(17,'Louisiana',1),(18,'Maine',1),(19,'Maryland',1),(20,'Massachusetts',1),(21,'Michigan',1),(22,'Minnesota',1),(23,'Mississippi',1),(24,'Missouri',1),(25,'Montana',1),(26,'Nebraska',1),(27,'Nevada',1),(28,'New Hampshire',1),(29,'New Jersey',1),(30,'New Mexico',1),(31,'New York',1),(32,'North Carolina',1),(33,'North Dakota',1),(34,'Ohio',1),(35,'Oklahoma',1),(36,'Oregon',1),(37,'Pennsylvania',1),(38,'Rhode Island',1),(39,'South Carolina',1),(40,'South Dakota',1),(41,'Tennessee',1),(42,'Texas',1),(43,'Utah',1),(44,'Vermont',1),(45,'Virginia',1),(46,'Washington',1),(47,'West Virginia',1),(48,'Wisconsin',1),(49,'Wyoming',1),(52,'Hawaii',1),(54,'Alaska',1),(60,'Northwest Territories',3),(61,'Alberta',3),(62,'British Columbia',3),(63,'Manitoba',3),(64,'New Brunswick',3),(65,'Nova Scotia',3),(66,'Prince Edward Island',3),(67,'Ontario',3),(68,'QuÃ©bec',3),(69,'Saskatchewan',3),(70,'Nunavut',3),(71,'Yukon',3),(72,'Newfoundland and Labrador',3),(101,'England',2),(102,'Wales',2),(103,'Scotland',2),(104,'Northern Ireland',2);
/*!40000 ALTER TABLE `first_level_divisions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-29 13:34:57
