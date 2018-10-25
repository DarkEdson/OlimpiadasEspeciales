CREATE DATABASE  IF NOT EXISTS `OLIMPIADAS_ESPECIALES` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `OLIMPIADAS_ESPECIALES`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: OLIMPIADAS_ESPECIALES
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `ATLETA`
--

DROP TABLE IF EXISTS `ATLETA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ATLETA` (
  `ID_ATLETA` varchar(100) NOT NULL COMMENT 'Correlativo unico de identificacion de la transaccion',
  `NOMBRE_ATLETA` varchar(100) DEFAULT NULL COMMENT 'Almacena en un campo de tipo String nombre del atleta',
  `FECHA_NACIMIENTO` date DEFAULT NULL COMMENT 'Almacena en un campo de tipo date la fecha de nacimiento',
  `DPI` int DEFAULT NULL COMMENT 'Correlativo unico de identificacion',
  `DOMICILIO` varchar(100) DEFAULT NULL COMMENT 'Direccion de domicilio en string',
  `TELEFONO` int(15) DEFAULT NULL COMMENT 'Tipo int para almacenar el numero de telefono personal',
  `MOVIL` int(15) DEFAULT NULL COMMENT 'Tipo int para almacenar el numero de celular',
  `ID_DIAGNOSTICO` int(11) DEFAULT NULL COMMENT 'Correlativo unico de identificacion del diagnostico',
  `ID_SITIO_ENTRENAMIENTO` int(11) DEFAULT NULL COMMENT 'Correlativo unico de identificacion del sitio_entrenamiento',
  `ID_INTITUCION` int(11) DEFAULT NULL COMMENT 'Correlativo unico de identificacion del institucion',
  `ID_DEPARTAMENTO` int(11) NOT NULL COMMENT 'Correlativo unico de identificacion de la foranea',
  `COMENTARIOS` text COMMENT 'Almacena String con comentarios del usuario final del formulario',
  `ID_ESTADO` int(11) DEFAULT NULL COMMENT 'Codigo de estado de la transaccion',
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP  COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_ATLETA`),
  CONSTRAINT `atleta_ibfk_1` FOREIGN KEY (`ID_DIAGNOSTICO`) REFERENCES `DIAGNOSTICO` (`ID_DIAGNOSTICO`) ON UPDATE CASCADE,
  CONSTRAINT `atleta_ibfk_2` FOREIGN KEY (`ID_SITIO_ENTRENAMIENTO`) REFERENCES `SITIO_ENTRENAMIENTO` (`ID_SITIO_ENTRENAMIENTO`) ON UPDATE CASCADE,
  CONSTRAINT `atleta_ibfk_3` FOREIGN KEY (`ID_INTITUCION`) REFERENCES `INSTITUCION` (`ID_INTITUCION`) ON UPDATE CASCADE,
  CONSTRAINT `atleta_ibfk_4` FOREIGN KEY (`ID_DEPARTAMENTO`) REFERENCES `DEPARTAMENTO` (`ID_DEPARTAMENTO`) ON UPDATE CASCADE,
  CONSTRAINT `atleta_ibfk_5` FOREIGN KEY (`ID_USUARIO_INGRESO`) REFERENCES `USUARIOS` (`ID_USUARIO`) ON UPDATE CASCADE,
  CONSTRAINT `atleta_ibfk_6` FOREIGN KEY (`ID_USUARIO_ACTUALIZACION`) REFERENCES `USUARIOS` (`ID_USUARIO`) ON UPDATE CASCADE,
  CONSTRAINT `atleta_ibfk_7` FOREIGN KEY (`ID_ESTADO`) REFERENCES `ESTADO` (`ID_ESTADO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ATLETA`
--

LOCK TABLES `ATLETA` WRITE;
/*!40000 ALTER TABLE `ATLETA` DISABLE KEYS */;
INSERT INTO `ATLETA` VALUES ('1','Jose Recinos','2002-05-20',1987050210101,'5ta ave 7-90 zona 1',24953694,58964250,1,1,1,2,NULL,1,'2018-10-08 20:19:23',NULL,'0000-00-00 00:00:00',NULL)
,('2','Marco Jomes','2001-07-10',1987508510101,'4ta ave 8-08 zona 3',24896365,58963058,2,1,1,3,NULL,1,'2018-10-08 20:19:23',NULL,'0000-00-00 00:00:00',NULL)
,('3','Mildred Amir','2003-08-30',1987580910101,'6ta calle 5-09 zona 7',24793365,57805905,3,1,1,2,NULL,1,'2018-10-08 20:19:23',NULL,'0000-00-00 00:00:00',NULL);
/*!40000 ALTER TABLE `ATLETA` ENABLE KEYS */;
UNLOCK TABLES;




--
-- Table structure for table `DEPARTAMENTO`
--

DROP TABLE IF EXISTS `DEPARTAMENTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DEPARTAMENTO` (
  `ID_DEPARTAMENTO` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Correlativo unico de identificacion de la transaccion',
  `DEPARTAMENTO` varchar(100) DEFAULT NULL COMMENT 'Almacena el String con el nombre o descripcion de la tupla',
  `ID_ESTADO` int(11) DEFAULT NULL COMMENT 'Codigo de estado de la transaccion',
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_DEPARTAMENTO`),
  CONSTRAINT `departamento_ibfk_1` FOREIGN KEY (`ID_ESTADO`) REFERENCES `ESTADO` (`ID_ESTADO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DEPARTAMENTO`
--

LOCK TABLES `DEPARTAMENTO` WRITE;
/*!40000 ALTER TABLE `DEPARTAMENTO` DISABLE KEYS */;
INSERT INTO `DEPARTAMENTO` VALUES (1,'Guatemala',1,'2018-10-08 20:19:01',NULL,'0000-00-00 00:00:00',NULL),(2,'Peten',1,'2018-10-08 20:19:01',NULL,'0000-00-00 00:00:00',NULL),(3,'Zacapa',1,'2018-10-08 20:19:01',NULL,'0000-00-00 00:00:00',NULL);
/*!40000 ALTER TABLE `DEPARTAMENTO` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `ATLETA_DISCIPLINA`
--

DROP TABLE IF EXISTS `ATLETA_DISCIPLINA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ATLETA_DISCIPLINA` (
  `ID_ATLETA_DISCIPLINA` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Correlativo unico de identificacion de la transaccion',
  `ID_ATLETA` varchar(100) NOT NULL COMMENT 'Correlativo unico de identificacion del atleta',
  `ID_DISCIPLINA` varchar(100) NOT NULL COMMENT 'Correlativo unico de identificacion de la transaccion',
  `ID_PROGRAMA` int(11) DEFAULT NULL COMMENT 'Correlativo unico de identificacion del programa',
  `ID_ESTADO` int(11) DEFAULT NULL COMMENT 'Codigo de estado de la transaccion',
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_ATLETA_DISCIPLINA`),
  CONSTRAINT `atleta_disciplina_ibfk_1` FOREIGN KEY (`ID_ATLETA`) REFERENCES `ATLETA` (`ID_ATLETA`) ON UPDATE CASCADE,
  CONSTRAINT `atleta_disciplina_ibfk_2` FOREIGN KEY (`ID_DISCIPLINA`) REFERENCES `DISCIPLINAS` (`ID_DISCIPLINA`) ON UPDATE CASCADE,
  CONSTRAINT `atleta_disciplina_ibfk_3` FOREIGN KEY (`ID_PROGRAMA`) REFERENCES `PROGRAMAS` (`ID_PROGRAMA`) ON UPDATE CASCADE,
  CONSTRAINT `atleta_disciplina_ibfk_4` FOREIGN KEY (`ID_ESTADO`) REFERENCES `ESTADO` (`ID_ESTADO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ATLETA_DISCIPLINA`
--

LOCK TABLES `ATLETA_DISCIPLINA` WRITE;
/*!40000 ALTER TABLE `ATLETA_DISCIPLINA` DISABLE KEYS */;
/*!40000 ALTER TABLE `ATLETA_DISCIPLINA` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `DIAGNOSTICO`
--

DROP TABLE IF EXISTS `DIAGNOSTICO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DIAGNOSTICO` (
  `ID_DIAGNOSTICO` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Correlativo unico de identificacion de la transaccion',
  `DIAGNOSTICO` varchar(100) DEFAULT NULL COMMENT 'Almacena el String con el nombre o descripcion de la tupla',
  `ID_ESTADO` int(11) DEFAULT NULL COMMENT 'Codigo de estado de la transaccion',
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_DIAGNOSTICO`),
  CONSTRAINT `diagnostico_ibfk_1` FOREIGN KEY (`ID_ESTADO`) REFERENCES `ESTADO` (`ID_ESTADO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DIAGNOSTICO`
--

LOCK TABLES `DIAGNOSTICO` WRITE;
/*!40000 ALTER TABLE `DIAGNOSTICO` DISABLE KEYS */;
INSERT INTO `DIAGNOSTICO` VALUES (1,'OK',1,'2018-10-08 20:19:23',NULL,'0000-00-00 00:00:00',NULL),(2,'fail',1,'2018-10-08 20:19:23',NULL,'0000-00-00 00:00:00',NULL),(3,'dispar',1,'2018-10-08 20:19:23',NULL,'0000-00-00 00:00:00',NULL);
/*!40000 ALTER TABLE `DIAGNOSTICO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DISCIPLINAS`
--

DROP TABLE IF EXISTS `DISCIPLINAS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DISCIPLINAS` (
  `ID_DISCIPLINA` varchar(100) NOT NULL COMMENT 'Correlativo unico de identificacion de la transaccion',
  `DISCIPLINA` varchar(100) DEFAULT NULL COMMENT 'Almacena el String con el nombre o descripcion de la tupla',
  `ID_ESTADO` int(11) DEFAULT NULL COMMENT 'Codigo de estado de la transaccion',
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_DISCIPLINA`),
  CONSTRAINT `disciplinas_ibfk_1` FOREIGN KEY (`ID_ESTADO`) REFERENCES `ESTADO` (`ID_ESTADO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DISCIPLINAS`
--

LOCK TABLES `DISCIPLINAS` WRITE;
/*!40000 ALTER TABLE `DISCIPLINAS` DISABLE KEYS */;
INSERT INTO `DISCIPLINAS` VALUES ('ATL','ATLETISMO', 1,'2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL)
,('NAT','Natacion', 1,'2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL),
('TE','TENIS', 1,'2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL)
,('BO','BOLICHE', 1,'2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL),
('BOC','BOCHAS', 1,'2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL)
,('FUT','FUTBOL', 1,'2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL),
('GO','GOLF', 1,'2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL)
,('BAD','BADMINGTON', 1,'2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL),
('EQUI','EQUITACION', 1,'2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL)
,('GIM','GIMNACIA', 1,'2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL),
('AJ','ATLETA JOVEN', 1,'2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL)
,('FIT','FITNESS', 1,'2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL)
,('FAM','FAMILIAS', 1,'2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL);
/*!40000 ALTER TABLE `DISCIPLINAS` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `PROGRAMAS`
--

DROP TABLE IF EXISTS `PROGRAMAS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PROGRAMAS` (
  `ID_PROGRAMA` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Correlativo unico de identificacion de la Programas',
  `PROGRAMA` varchar(100) DEFAULT NULL COMMENT 'Almacena el String con el nombre o descripcion de la tupla',
  `ID_ESTADO` int(11) DEFAULT NULL COMMENT 'Codigo de estado de la transaccion',
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_PROGRAMA`),
  CONSTRAINT `programas_ibfk_1` FOREIGN KEY (`ID_ESTADO`) REFERENCES `ESTADO` (`ID_ESTADO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROGRAMAS`
--

LOCK TABLES `PROGRAMAS` WRITE;
/*!40000 ALTER TABLE `PROGRAMAS` DISABLE KEYS */;
INSERT INTO `PROGRAMAS` VALUES (1,'Atleta Lider', 1,'2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL),
(2,'Atleta Joven', 1,'2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL)
,(3,'Fitness', 1,'2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL),
(4,'Atleta Saludable', 1,'2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL)
,(5,'Foro de Familias', 1,'2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL)
,(6,'Compañero Unificado', 1,'2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL);
/*!40000 ALTER TABLE `PROGRAMAS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ESTADO`
--

DROP TABLE IF EXISTS `ESTADO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ESTADO` (
  `ID_ESTADO` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Correlativo unico de identificacion de la transaccion',
  `ESTADO` varchar(100) DEFAULT NULL COMMENT 'Descripcion de la tupla',
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_ESTADO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ESTADO`
--

LOCK TABLES `ESTADO` WRITE;
/*!40000 ALTER TABLE `ESTADO` DISABLE KEYS */;
INSERT INTO `ESTADO` VALUES (1,'ACTIVO','2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL),(2,'INACTIVO','2018-10-03 20:01:38',NULL,'2018-10-03 20:01:38',NULL);
/*!40000 ALTER TABLE `ESTADO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INSTITUCION`
--


DROP TABLE IF EXISTS `INSTITUCION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `INSTITUCION` (
  `ID_INTITUCION` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Correlativo unico de identificacion de la transaccion',
  `INSTITUCION` varchar(100) DEFAULT NULL COMMENT 'Almacenara un string con el nombre del cliente',
  `ID_ESTADO` int(11) DEFAULT NULL COMMENT 'Codigo de estado de la transaccion',
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_INTITUCION`),
  CONSTRAINT `institucion_ibfk_1` FOREIGN KEY (`ID_ESTADO`) REFERENCES `ESTADO` (`ID_ESTADO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INSTITUCION`
--

LOCK TABLES `INSTITUCION` WRITE;
/*!40000 ALTER TABLE `INSTITUCION` DISABLE KEYS */;
INSERT INTO `INSTITUCION` VALUES (1, 'Olimpiadas Especiales de Guatemala', 1, '2018-10-03 20:04:43',NULL,'2018-10-03 20:04:43',NULL);
/*!40000 ALTER TABLE `INSTITUCION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROLES`
--

DROP TABLE IF EXISTS `ROLES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ROLES` (
  `ID_ROL` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Correlativo unico de identificacion de la transaccion',
  `ROL` varchar(100) DEFAULT NULL COMMENT 'Almacena el String con el nombre o descripcion de la tupla',
  `ID_ESTADO` int(11) DEFAULT NULL COMMENT 'Codigo de estado de la transaccion',
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_ROL`),
  CONSTRAINT `roles_ibfk_1` FOREIGN KEY (`ID_ESTADO`) REFERENCES `ESTADO` (`ID_ESTADO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



LOCK TABLES `ROLES` WRITE;
/*!40000 ALTER TABLE `ROLES` DISABLE KEYS */;
INSERT INTO ROLES (ID_ROL, ROL, ID_ESTADO) VALUES(1, 'ADMINISTRADOR', 1);
INSERT INTO ROLES (ID_ROL, ROL, ID_ESTADO) VALUES(2, 'TUTOR', 1);
/*!40000 ALTER TABLE `ROLES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ROLES`
--

LOCK TABLES `ROLES` WRITE;
/*!40000 ALTER TABLE `ROLES` DISABLE KEYS */;
/*!40000 ALTER TABLE `ROLES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SITIO_ENTRENAMIENTO`
--

DROP TABLE IF EXISTS `SITIO_ENTRENAMIENTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SITIO_ENTRENAMIENTO` (
  `ID_SITIO_ENTRENAMIENTO` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Correlativo unico de identificacion de la transaccion',
  `SITIO_ENTRENAMIENTO` varchar(100) DEFAULT NULL COMMENT 'Almacena el String con el nombre o descripcion de la tupla',
  `ID_ESTADO` int(11) DEFAULT NULL COMMENT 'Codigo de estado de la transaccion',
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_SITIO_ENTRENAMIENTO`),
  CONSTRAINT `sitio_entrenamiento_ibfk_1` FOREIGN KEY (`ID_ESTADO`) REFERENCES `ESTADO` (`ID_ESTADO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SITIO_ENTRENAMIENTO`
--

LOCK TABLES `SITIO_ENTRENAMIENTO` WRITE;
/*!40000 ALTER TABLE `SITIO_ENTRENAMIENTO` DISABLE KEYS */;
INSERT INTO `SITIO_ENTRENAMIENTO` VALUES (1,'gimnacio',1,'2018-10-08 20:19:23',NULL,'0000-00-00 00:00:00',NULL),(2,'piscina',1,'2018-10-08 20:19:23',NULL,'0000-00-00 00:00:00',NULL),(3,'estadio',1,'2018-10-08 20:19:23',NULL,'0000-00-00 00:00:00',NULL);
/*!40000 ALTER TABLE `SITIO_ENTRENAMIENTO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TUTOR`
--

DROP TABLE IF EXISTS `TUTOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TUTOR` (
  `ID_TUTOR` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Correlativo unico de identificacion de la transaccion',
  `ID_ATLETA` varchar(100) DEFAULT NULL COMMENT 'Correlativo unico de identificacion del atleta',
  `NOMBRE` varchar(100) DEFAULT NULL COMMENT 'Almacena en un campo de tipo String nombre del nommbre_tutor',
  `FECHA_NACIMIENTO` date DEFAULT NULL COMMENT 'Almacena en un campo de tipo date la fecha de nacimiento',
  `DPI` int(30) DEFAULT NULL COMMENT 'Correlativo unico de identificacion',
  `DIRECCION_LABORAL` varchar(200) DEFAULT NULL COMMENT 'Direccion de domicilio en string',
  `TELEFONO` int(15) DEFAULT NULL COMMENT 'Tipo int para almacenar el numero de telefono personal',
  `MOVIL` int(15) DEFAULT NULL COMMENT 'Tipo int para almacenar el numero de celular',
  `ID_ESTADO` int(11) DEFAULT NULL COMMENT 'Codigo de estado de la transaccion',
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_TUTOR`),
  CONSTRAINT `tutor_ibfk_1` FOREIGN KEY (`ID_ATLETA`) REFERENCES `ATLETA` (`ID_ATLETA`) ON UPDATE CASCADE,
  CONSTRAINT `tutor_ibfk_2` FOREIGN KEY (`ID_USUARIO_INGRESO`) REFERENCES `USUARIOS` (`ID_USUARIO`) ON UPDATE CASCADE,
  CONSTRAINT `tutor_ibfk_3` FOREIGN KEY (`ID_USUARIO_ACTUALIZACION`) REFERENCES `USUARIOS` (`ID_USUARIO`) ON UPDATE CASCADE,
  CONSTRAINT `tutor_ibfk_4` FOREIGN KEY (`ID_ESTADO`) REFERENCES `ESTADO` (`ID_ESTADO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TUTOR`
--

LOCK TABLES `TUTOR` WRITE;
/*!40000 ALTER TABLE `TUTOR` DISABLE KEYS */;
INSERT INTO `TUTOR` VALUES (1,1,'Jose','1990-10-25',198590581,'5ta calle',24456678,54678943,1,'2018-10-08 20:19:23',NULL,'0000-00-00 00:00:00',NULL),(2,2,'Maria','1995-10-25',198684568,'3ra calle',24454890,54868796,1,'2018-10-08 20:19:23',NULL,'0000-00-00 00:00:00',NULL),(3,3,'Esmeralda','1993-10-25',1987632505,'2a avenida',24796432,86548520,1,'2018-10-08 20:19:23',NULL,'0000-00-00 00:00:00',NULL);
/*!40000 ALTER TABLE `TUTOR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIOS`
--

DROP TABLE IF EXISTS `USUARIOS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USUARIOS` (
  `ID_USUARIO` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Correlativo unico de identificacion de la transaccion',
  `ID_ROL` int(11) DEFAULT NULL COMMENT 'Correlativo unico de identificacion del rol',
  `USUARIO` varchar(100) DEFAULT NULL COMMENT 'No. unico de identificacion del sistema',
  `PASSWORD` varchar(250) DEFAULT NULL COMMENT 'Almacena en tipo de campo String el password encriptado',
  `ID_ESTADO` int(11) DEFAULT NULL COMMENT 'Codigo de estado de la transaccion',
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_USUARIO`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`ID_ROL`) REFERENCES `ROLES` (`ID_ROL`),
  CONSTRAINT `usuarios_ibfk_2` FOREIGN KEY (`ID_ESTADO`) REFERENCES `ESTADO` (`ID_ESTADO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `USUARIOS` WRITE;
/*!40000 ALTER TABLE `USUARIOS` DISABLE KEYS */;
INSERT INTO USUARIOS (ID_USUARIO, ID_ROL, USUARIO, PASSWORD, ID_ESTADO) VALUES(1, 1, 'AXEL', '70f4c687710d65276370d15781c4f48f', 1), 
(2, 1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 1), 
(3, 1, 'EDSON', 'b937384a573b94c4d7cc6004c496f919', 1);
/*!40000 ALTER TABLE `USUARIOS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `USUARIOS`
--

LOCK TABLES `USUARIOS` WRITE;
/*!40000 ALTER TABLE `USUARIOS` DISABLE KEYS */;
/*!40000 ALTER TABLE `USUARIOS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
