-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: dbfinanceiro
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `tblmovimentacao`
--

DROP TABLE IF EXISTS `tblmovimentacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblmovimentacao` (
  `idmovimentacao` int unsigned NOT NULL AUTO_INCREMENT,
  `codigofilial` varchar(1) DEFAULT NULL,
  `cnpjempresa` varchar(14) DEFAULT NULL,
  `idfuncionario` int DEFAULT NULL,
  `nomefuncionario` varchar(100) DEFAULT NULL,
  `cpffuncionario` varchar(11) DEFAULT NULL,
  `tipoparceiro` char(1) DEFAULT NULL,
  `contaDebito` varchar(6) DEFAULT NULL,
  `historico` varchar(60) DEFAULT NULL,
  `agencia` varchar(15) DEFAULT NULL,
  `agenciadv` varchar(2) DEFAULT NULL,
  `contacorrente` varchar(15) DEFAULT NULL,
  `contacorrentedv` varchar(2) DEFAULT NULL,
  `valor` decimal(10,2) DEFAULT NULL,
  `vencimento` date DEFAULT NULL,
  `competencia` date DEFAULT NULL,
  `nota` varchar(7) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `ultimousuario` varchar(15) DEFAULT NULL,
  `ultimaalteracao` datetime DEFAULT NULL,
  PRIMARY KEY (`idmovimentacao`),
  UNIQUE KEY `idmovimentacao_UNIQUE` (`idmovimentacao`)
) ENGINE=InnoDB AUTO_INCREMENT=3994 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-06 16:00:32
