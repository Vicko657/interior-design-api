-- MySQL dump 10.13  Distrib 9.3.0, for macos15.2 (arm64)
--
-- Host: localhost    Database: interiordesignplanner
-- ------------------------------------------------------
-- Server version	9.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (2,'3 View Lane, London, NW8 8PG','2025-08-21 19:17:18.054379','vicolu@gmail.com','Abroad at the start of November till December','07866970123','2025-09-07 05:16:54.486899','Victoria','Olusegun'),(3,'60 Belsize Avenue, London, N1 3EE','2025-08-26 13:26:09.211555','johnmoss@gmail.com','Prefers morning appointments','07853394758','2025-08-26 13:26:09.211555','John','Moss'),(4,'8 Welling Road, London, SE18 210','2025-08-31 21:09:00.054715','sophieday@gmail.com','Available for meetings on a Saturday','07945394123','2025-08-31 21:09:00.054715','Sophie','Day');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projects` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `budget` int DEFAULT NULL,
  `completed_at` datetime(6) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `meetingurl` varchar(255) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` enum('ACTIVE','ARCHIVED','CANCELLED','COMPLETED','ON_HOLD','PLANNING') DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `client_id` bigint DEFAULT NULL,
  `room_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKt82hrja1byvn1i28yp18cdgi0` (`room_id`),
  KEY `FKksdiyuily2f4ca2y53k07pmq` (`client_id`),
  CONSTRAINT `FK7qkvs5v3i9qxm2vtiis2mtfb4` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`),
  CONSTRAINT `FKksdiyuily2f4ca2y53k07pmq` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (1,4600,NULL,'2025-08-26 13:04:48.842315','Paint, lighting, soft furnishings','2026-01-30','https://meet.google.com/abc-defg-hij','Living Room Refresh','2025-08-24','ACTIVE','2025-09-07 16:17:22.733089',2,2),(4,15000,'2025-09-07 14:23:35.560616','2025-08-26 13:51:52.019533','Mordernising outdated bathroom, tiling, plumbing, storage','2026-05-01','https://meet.google.com/abc-defg-hij','Outdated Bathroom Revamp','2025-11-28','PLANNING','2025-09-07 16:20:41.817472',2,3),(5,8000,NULL,'2025-08-27 11:27:56.077425','Light and airy bathroom makeover with porcelain tiles and brushed-brass fittings','2026-08-15','https://meet.google.com/abc-defg-hij','Cozy Retreat Bathroom','2025-12-14','PLANNING','2025-08-31 22:56:06.396099',3,1),(6,25900,'2025-08-28 13:22:12.895549','2025-08-28 13:17:05.593734','Japandi style kitchen renovation','2025-08-15','https://meet.google.com/abc-defg-hij','Japandi Kitchen','2025-03-10','COMPLETED','2025-09-07 16:22:52.578105',3,6),(7,5000,NULL,'2025-08-31 21:14:14.875242','Mordernising a old bedroom, changing lighting, curtains, bed and wardrobe','2025-04-13','https://meet.google.com/abc-defg-hij','Mordernise Main Bedroom','2025-11-25','PLANNING','2025-09-07 16:18:05.338937',3,8),(8,25000,'2025-09-07 13:49:24.289326','2025-09-07 13:02:17.518613','Renovation of the main office space with modern furniture and lighting.','2025-03-01','https://zoom.us/office-renovation','Office Renovation','2025-01-01','COMPLETED','2025-09-07 16:18:38.747225',4,NULL);
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `changes` varchar(255) DEFAULT NULL,
  `checklist` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `height` double DEFAULT NULL,
  `length` double DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `width` double DEFAULT NULL,
  `project_id` bigint DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `room_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK9dhh2mdvwrfxe176tbqmnlt1u` (`project_id`),
  UNIQUE KEY `UK522j1g1kig0go3c1o8r8gvu7g` (`room_id`),
  CONSTRAINT `FK155ul04boadq2lvc7ulyeis0s` FOREIGN KEY (`room_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FK31elx4lfyydu29pa0jtnufge5` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,'Keeping the orignal sink','Order floor tiles from Wickes, https://www.wickes.co.uk/Wickes-Pinhay-Light-Grey-Matt-Porcelain-Wall+Floor-Tile---600-x-600mm/p/295993','2025-08-26 18:58:06.799825',5.4,4,'m','2025-08-27 11:34:11.819876',6.2,5,'BATHROOM',NULL),(2,'Switching light switches to silver','Builders to remove Old flooring, 2025-10-25','2025-08-27 11:19:58.556001',5.2,7,'m','2025-08-27 11:32:35.574958',6.4,1,'LIVING_ROOM',NULL),(3,'Switched to two sinks instead of one, 2025-08-27','Order shower screen, https://www.victorianplumbing.co.uk/premier-wetroom-screen-square-support-arm-various-sizes','2025-08-27 11:43:34.277007',5.6,5,'m','2025-08-27 11:47:34.548430',4.2,4,'BATHROOM',NULL),(6,'Boiling tap is changing to normal tap','Order the worktop from B&Q','2025-08-31 21:02:15.859879',5.4,9.5,'m','2025-08-31 21:02:15.859879',6.2,6,'KITCHEN',NULL),(8,'None','Order the bedroom from Dusk','2025-08-31 21:19:59.037119',5.4,6.5,'m','2025-08-31 21:19:59.037119',3.2,7,'BEDROOM',NULL);
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-07 23:17:32
