CREATE DATABASE  IF NOT EXISTS `olimpiadas_especiales` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `olimpiadas_especiales`;
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
  `ID_ATLETA` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Correlativo unico de identificacion de la transaccion',
  `NOMBRE_ATLETA` varchar(100) DEFAULT NULL COMMENT 'Almacena en un campo de tipo String nombre del atleta',
  `FECHA_NACIMIENTO` date DEFAULT NULL COMMENT 'Almacena en un campo de tipo date la fecha de nacimiento',
  `ID_DEPARTAMENTO` int(11) DEFAULT NULL COMMENT 'Correlativo unico de identificacion del departamento',
  `DPI` int(30) DEFAULT NULL COMMENT 'Correlativo unico de identificacion',
  `DOMICILIO` varchar(100) DEFAULT NULL COMMENT 'Direccion de domicilio en string',
  `TELEFONO` int(15) DEFAULT NULL COMMENT 'Tipo int para almacenar el numero de telefono personal',
  `MOVIL` int(15) DEFAULT NULL COMMENT 'Tipo int para almacenar el numero de celular',
  `ID_DIAGNOSTICO` int(11) DEFAULT NULL COMMENT 'Correlativo unico de identificacion del diagnostico',
  `ID_TUTOR1` int(11) DEFAULT NULL COMMENT 'Correlativo unico de identificacion del tutor',
  `ID_TUTOR2` int(11) DEFAULT NULL COMMENT 'Correlativo unico de identificacion del tutor',
  `ID_SITIO_ENTRENAMIENTO` int(11) DEFAULT NULL COMMENT 'Correlativo unico de identificacion del sitio_entrenamiento',
  `COMENTARIOS` text COMMENT 'Almacena String con comentarios del usuario final del formulario',
  `ID_ESTADO` int(11) DEFAULT NULL COMMENT 'Codigo de estado de la transaccion',
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_ATLETA`),
  KEY `ID_DEPARTAMENTO` (`ID_DEPARTAMENTO`),
  KEY `ID_DIAGNOSTICO` (`ID_DIAGNOSTICO`),
  KEY `ID_TUTOR1` (`ID_TUTOR1`),
  KEY `ID_TUTOR2` (`ID_TUTOR2`),
  KEY `ID_SITIO_ENTRENAMIENTO` (`ID_SITIO_ENTRENAMIENTO`),
  KEY `ID_USUARIO_INGRESO` (`ID_USUARIO_INGRESO`),
  KEY `ID_USUARIO_ACTUALIZACION` (`ID_USUARIO_ACTUALIZACION`),
  KEY `ID_ESTADO` (`ID_ESTADO`),
  CONSTRAINT `atleta_ibfk_1` FOREIGN KEY (`ID_DEPARTAMENTO`) REFERENCES `DEPARTAMENTO` (`ID_DEPARTAMENTO`) ON UPDATE CASCADE,
  CONSTRAINT `atleta_ibfk_2` FOREIGN KEY (`ID_DIAGNOSTICO`) REFERENCES `DIAGNOSTICO` (`ID_DIAGNOSTICO`) ON UPDATE CASCADE,
  CONSTRAINT `atleta_ibfk_3` FOREIGN KEY (`ID_TUTOR1`) REFERENCES `TUTOR` (`ID_TUTOR`) ON UPDATE CASCADE,
  CONSTRAINT `atleta_ibfk_4` FOREIGN KEY (`ID_TUTOR2`) REFERENCES `TUTOR` (`ID_TUTOR`) ON UPDATE CASCADE,
  CONSTRAINT `atleta_ibfk_5` FOREIGN KEY (`ID_SITIO_ENTRENAMIENTO`) REFERENCES `SITIO_ENTRENAMIENTO` (`ID_SITIO_ENTRENAMIENTO`) ON UPDATE CASCADE,
  CONSTRAINT `atleta_ibfk_6` FOREIGN KEY (`ID_USUARIO_INGRESO`) REFERENCES `USUARIOS` (`ID_USUARIO`) ON UPDATE CASCADE,
  CONSTRAINT `atleta_ibfk_7` FOREIGN KEY (`ID_USUARIO_ACTUALIZACION`) REFERENCES `USUARIOS` (`ID_USUARIO`) ON UPDATE CASCADE,
  CONSTRAINT `atleta_ibfk_8` FOREIGN KEY (`ID_ESTADO`) REFERENCES `ESTADO` (`ID_ESTADO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ATLETA`
--

LOCK TABLES `ATLETA` WRITE;
/*!40000 ALTER TABLE `ATLETA` DISABLE KEYS */;
/*!40000 ALTER TABLE `ATLETA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ATLETA_DISCIPLINA`
--

DROP TABLE IF EXISTS `ATLETA_DISCIPLINA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ATLETA_DISCIPLINA` (
  `ID_ATLETA_DISCIPLINA` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Correlativo unico de identificacion de la transaccion',
  `ID_ATLETA` int(11) DEFAULT NULL COMMENT 'Correlativo unico de identificacion del atleta',
  `ID_DISCIPLINA` int(11) NOT NULL COMMENT 'Correlativo unico de identificacion del disciplina',
  `ID_INTITUCION` int(11) DEFAULT NULL COMMENT 'Correlativo unico de identificacion del institucion',
  `ID_ESTADO` int(11) DEFAULT NULL COMMENT 'Codigo de estado de la transaccion',
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_ATLETA_DISCIPLINA`),
  KEY `ID_ATLETA` (`ID_ATLETA`),
  KEY `ID_INTITUCION` (`ID_INTITUCION`),
  KEY `ID_ESTADO` (`ID_ESTADO`),
  CONSTRAINT `atleta_disciplina_ibfk_1` FOREIGN KEY (`ID_ATLETA`) REFERENCES `ATLETA` (`ID_ATLETA`) ON UPDATE CASCADE,
  CONSTRAINT `atleta_disciplina_ibfk_2` FOREIGN KEY (`ID_DISCIPLINA`) REFERENCES `DISCIPLINAS` (`ID_DISCIPLINA`) ON UPDATE CASCADE,
  CONSTRAINT `atleta_disciplina_ibfk_3` FOREIGN KEY (`ID_INTITUCION`) REFERENCES `INSTITUCION` (`ID_INTITUCION`) ON UPDATE CASCADE,
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
-- Table structure for table `DEPARTAMENTO`
--

DROP TABLE IF EXISTS `DEPARTAMENTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DEPARTAMENTO` (
  `ID_DEPARTAMENTO` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Correlativo unico de identificacion de la transaccion',
  `DEPARTAMENTO` varchar(100) DEFAULT NULL COMMENT 'Almacena el String con el nombre o descripcion de la tupla',
  `ID_ESTADO` int(11) DEFAULT NULL COMMENT 'Codigo de estado de la transaccion',
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_DEPARTAMENTO`),
  KEY `ID_ESTADO` (`ID_ESTADO`),
  CONSTRAINT `departamento_ibfk_1` FOREIGN KEY (`ID_ESTADO`) REFERENCES `ESTADO` (`ID_ESTADO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DEPARTAMENTO`
--

LOCK TABLES `DEPARTAMENTO` WRITE;
/*!40000 ALTER TABLE `DEPARTAMENTO` DISABLE KEYS */;
/*!40000 ALTER TABLE `DEPARTAMENTO` ENABLE KEYS */;
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
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_DIAGNOSTICO`),
  KEY `ID_ESTADO` (`ID_ESTADO`),
  CONSTRAINT `diagnostico_ibfk_1` FOREIGN KEY (`ID_ESTADO`) REFERENCES `ESTADO` (`ID_ESTADO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DIAGNOSTICO`
--

LOCK TABLES `DIAGNOSTICO` WRITE;
/*!40000 ALTER TABLE `DIAGNOSTICO` DISABLE KEYS */;
/*!40000 ALTER TABLE `DIAGNOSTICO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DISCIPLINAS`
--

DROP TABLE IF EXISTS `DISCIPLINAS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DISCIPLINAS` (
  `ID_DISCIPLINA` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Correlativo unico de identificacion de la transaccion',
  `NOMBRE_DISCIPLINA` varchar(100) DEFAULT NULL COMMENT 'Almacena el String con el nombre o descripcion de la tupla',
  `ID_ESTADO` int(11) DEFAULT NULL COMMENT 'Codigo de estado de la transaccion',
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_DISCIPLINA`),
  KEY `ID_ESTADO` (`ID_ESTADO`),
  CONSTRAINT `disciplinas_ibfk_1` FOREIGN KEY (`ID_ESTADO`) REFERENCES `ESTADO` (`ID_ESTADO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DISCIPLINAS`
--

LOCK TABLES `DISCIPLINAS` WRITE;
/*!40000 ALTER TABLE `DISCIPLINAS` DISABLE KEYS */;
/*!40000 ALTER TABLE `DISCIPLINAS` ENABLE KEYS */;
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
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Fecha en la que se genero el registro',
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
  `NOMBRE_INSTITUCION` varchar(100) DEFAULT NULL COMMENT 'Almacenara un string con el nombre del cliente',
  `ID_ESTADO` int(11) DEFAULT NULL COMMENT 'Codigo de estado de la transaccion',
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_INTITUCION`),
  KEY `ID_ESTADO` (`ID_ESTADO`),
  CONSTRAINT `institucion_ibfk_1` FOREIGN KEY (`ID_ESTADO`) REFERENCES `ESTADO` (`ID_ESTADO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INSTITUCION`
--

LOCK TABLES `INSTITUCION` WRITE;
/*!40000 ALTER TABLE `INSTITUCION` DISABLE KEYS */;
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
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_ROL`),
  KEY `ID_ESTADO` (`ID_ESTADO`),
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
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_SITIO_ENTRENAMIENTO`),
  KEY `ID_ESTADO` (`ID_ESTADO`),
  CONSTRAINT `sitio_entrenamiento_ibfk_1` FOREIGN KEY (`ID_ESTADO`) REFERENCES `ESTADO` (`ID_ESTADO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SITIO_ENTRENAMIENTO`
--

LOCK TABLES `SITIO_ENTRENAMIENTO` WRITE;
/*!40000 ALTER TABLE `SITIO_ENTRENAMIENTO` DISABLE KEYS */;
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
  `NOMBRE_TUTOR` varchar(100) DEFAULT NULL COMMENT 'Almacena en un campo de tipo String nombre del nommbre_tutor',
  `FECHA_NACIMIENTO` date DEFAULT NULL COMMENT 'Almacena en un campo de tipo date la fecha de nacimiento',
  `DPI` int(30) DEFAULT NULL COMMENT 'Correlativo unico de identificacion',
  `DIRECCION_LABORAL` varchar(200) DEFAULT NULL COMMENT 'Direccion de domicilio en string',
  `TELEFONO` int(15) DEFAULT NULL COMMENT 'Tipo int para almacenar el numero de telefono personal',
  `MOVIL` int(15) DEFAULT NULL COMMENT 'Tipo int para almacenar el numero de celular',
  `ID_ESTADO` int(11) DEFAULT NULL COMMENT 'Codigo de estado de la transaccion',
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_TUTOR`),
  KEY `ID_USUARIO_INGRESO` (`ID_USUARIO_INGRESO`),
  KEY `ID_USUARIO_ACTUALIZACION` (`ID_USUARIO_ACTUALIZACION`),
  KEY `ID_ESTADO` (`ID_ESTADO`),
  CONSTRAINT `tutor_ibfk_1` FOREIGN KEY (`ID_USUARIO_INGRESO`) REFERENCES `USUARIOS` (`ID_USUARIO`) ON UPDATE CASCADE,
  CONSTRAINT `tutor_ibfk_2` FOREIGN KEY (`ID_USUARIO_ACTUALIZACION`) REFERENCES `USUARIOS` (`ID_USUARIO`) ON UPDATE CASCADE,
  CONSTRAINT `tutor_ibfk_3` FOREIGN KEY (`ID_ESTADO`) REFERENCES `ESTADO` (`ID_ESTADO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TUTOR`
--

LOCK TABLES `TUTOR` WRITE;
/*!40000 ALTER TABLE `TUTOR` DISABLE KEYS */;
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
  `FECHA_INGRESO` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_INGRESO` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  `FECHA_ACTUALIZACION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Fecha en la que se genero el registro',
  `ID_USUARIO_ACTUALIZACION` int(11) DEFAULT NULL COMMENT 'Usuario quien ingreso la tupla',
  PRIMARY KEY (`ID_USUARIO`),
  KEY `ID_ROL` (`ID_ROL`),
  KEY `ID_ESTADO` (`ID_ESTADO`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`ID_ROL`) REFERENCES `ROLES` (`ID_ROL`),
  CONSTRAINT `usuarios_ibfk_2` FOREIGN KEY (`ID_ESTADO`) REFERENCES `ESTADO` (`ID_ESTADO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `USUARIOS` WRITE;
/*!40000 ALTER TABLE `USUARIOS` DISABLE KEYS */;
INSERT INTO USUARIOS (ID_USUARIO, ID_ROL, USUARIO, PASSWORD, ID_ESTADO) VALUES(1, 1, 'AXEL', '70f4c687710d65276370d15781c4f48f', 1);
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

-- Dump completed on 2018-10-03 14:07:35
