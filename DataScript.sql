-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.5.4-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for audiovisualesdb
DROP DATABASE IF EXISTS `audiovisualesdb`;
CREATE DATABASE IF NOT EXISTS `audiovisualesdb` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci */;
USE `audiovisualesdb`;

-- Dumping structure for table audiovisualesdb.credencial
DROP TABLE IF EXISTS `credencial`;
CREATE TABLE IF NOT EXISTS `credencial` (
  `IdEmpleado` int(11) NOT NULL,
  `IdRol` int(11) NOT NULL,
  `Username` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `Password` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`IdEmpleado`),
  UNIQUE KEY `Id_UNIQUE` (`IdEmpleado`),
  UNIQUE KEY `Username_UNIQUE` (`Username`),
  KEY `RolCredencial_idx` (`IdRol`),
  CONSTRAINT `CredencialEmpleado` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`idEmpleado`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `RolCredencial` FOREIGN KEY (`IdRol`) REFERENCES `rol` (`idRol`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Dumping data for table audiovisualesdb.credencial: ~0 rows (approximately)
/*!40000 ALTER TABLE `credencial` DISABLE KEYS */;
INSERT INTO `credencial` (`IdEmpleado`, `IdRol`, `Username`, `Password`, `Estado`) VALUES
	(1, 1, 'Admin', 'Admin123', 1);
/*!40000 ALTER TABLE `credencial` ENABLE KEYS */;

-- Dumping structure for table audiovisualesdb.empleado
DROP TABLE IF EXISTS `empleado`;
CREATE TABLE IF NOT EXISTS `empleado` (
  `idEmpleado` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(150) COLLATE utf8_spanish2_ci NOT NULL,
  `Cedula` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `Tanda_Labor` varchar(45) COLLATE utf8_spanish2_ci NOT NULL DEFAULT 'Matutina',
  `FechaIngreso` date NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idEmpleado`),
  UNIQUE KEY `Cedula_UNIQUE` (`Cedula`),
  KEY `NombreEmpleado_Index` (`Nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Dumping data for table audiovisualesdb.empleado: ~2 rows (approximately)
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` (`idEmpleado`, `Nombre`, `Cedula`, `Tanda_Labor`, `FechaIngreso`, `Estado`) VALUES
	(1, 'Jesus Francisco Dicent Aguero', '402-1457460-6', 'Matutina', '2020-07-11', 1),
	(2, 'Juan Pablo Valdez', '402-2793508-3', 'Nocturna', '2020-05-11', 1);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;

-- Dumping structure for table audiovisualesdb.equipo
DROP TABLE IF EXISTS `equipo`;
CREATE TABLE IF NOT EXISTS `equipo` (
  `idEquipo` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(150) COLLATE utf8_spanish2_ci NOT NULL,
  `No_Serial` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `ServiceTag` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `idTipoEquipo` int(11) DEFAULT NULL,
  `idModelo` int(11) DEFAULT NULL,
  `idTecConexion` int(11) DEFAULT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 0,
  `Rentado` varchar(1) COLLATE utf8_spanish2_ci NOT NULL DEFAULT 'N',
  PRIMARY KEY (`idEquipo`),
  UNIQUE KEY `idEquipo_UNIQUE` (`idEquipo`),
  UNIQUE KEY `ServiceTag_UNIQUE` (`ServiceTag`),
  KEY `EquipoToTipo_idx` (`idTipoEquipo`),
  KEY `EquipoToModelo_idx` (`idModelo`),
  KEY `EquipoToTecConexion_idx` (`idTecConexion`),
  CONSTRAINT `EquipoToModelo` FOREIGN KEY (`idModelo`) REFERENCES `modelos` (`Id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `EquipoToTecConexion` FOREIGN KEY (`idTecConexion`) REFERENCES `tecconexion` (`Id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `EquipoToTipo` FOREIGN KEY (`idTipoEquipo`) REFERENCES `tipoequipo` (`Id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Dumping data for table audiovisualesdb.equipo: ~2 rows (approximately)
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` (`idEquipo`, `Descripcion`, `No_Serial`, `ServiceTag`, `idTipoEquipo`, `idModelo`, `idTecConexion`, `Estado`, `Rentado`) VALUES
	(1, 'Equipo 1', '0000000', '545454', 1, 1, 1, 1, 'N'),
	(2, 'Equipo 3', 'asdads', 'qwe212', 2, 2, 1, 1, 'N');
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;

-- Dumping structure for table audiovisualesdb.marcas
DROP TABLE IF EXISTS `marcas`;
CREATE TABLE IF NOT EXISTS `marcas` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(150) COLLATE utf8_spanish2_ci NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`Id`),
  KEY `MarcaDescripcion` (`Descripcion`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Dumping data for table audiovisualesdb.marcas: ~3 rows (approximately)
/*!40000 ALTER TABLE `marcas` DISABLE KEYS */;
INSERT INTO `marcas` (`Id`, `Descripcion`, `Estado`) VALUES
	(1, 'HP', 1),
	(2, 'Dell', 1),
	(3, 'Toshiba', 1);
/*!40000 ALTER TABLE `marcas` ENABLE KEYS */;

-- Dumping structure for table audiovisualesdb.modelos
DROP TABLE IF EXISTS `modelos`;
CREATE TABLE IF NOT EXISTS `modelos` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdMarca` int(11) NOT NULL,
  `Descripcion` varchar(150) COLLATE utf8_spanish2_ci NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`Id`),
  KEY `ModeloDescripcion` (`Descripcion`),
  KEY `ModeloToMarca_idx` (`IdMarca`),
  CONSTRAINT `ModeloToMarca` FOREIGN KEY (`IdMarca`) REFERENCES `marcas` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Dumping data for table audiovisualesdb.modelos: ~6 rows (approximately)
/*!40000 ALTER TABLE `modelos` DISABLE KEYS */;
INSERT INTO `modelos` (`Id`, `IdMarca`, `Descripcion`, `Estado`) VALUES
	(1, 1, 'Modelo 1', 1),
	(2, 1, 'Modelo 2', 1),
	(3, 2, 'Modelo 3', 1),
	(4, 2, 'Modelo 4', 1),
	(5, 3, 'Modelo 5', 1),
	(6, 3, 'Modelo 6', 1);
/*!40000 ALTER TABLE `modelos` ENABLE KEYS */;

-- Dumping structure for table audiovisualesdb.rentadevolucion
DROP TABLE IF EXISTS `rentadevolucion`;
CREATE TABLE IF NOT EXISTS `rentadevolucion` (
  `No_Prestamo` int(11) NOT NULL AUTO_INCREMENT,
  `idEmpleado` int(11) NOT NULL,
  `idEquipo` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `FechaPrestamo` date NOT NULL,
  `FechaDevolucion` date DEFAULT NULL,
  `Comentario` varchar(240) COLLATE utf8_spanish2_ci NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`No_Prestamo`),
  KEY `ComentarioRenta` (`Comentario`),
  KEY `FechaRenta` (`FechaPrestamo`),
  KEY `FechaPrestamo` (`FechaDevolucion`),
  KEY `EstadoRenta` (`Estado`),
  KEY `RentaEmpleado_idx` (`idEmpleado`),
  KEY `RentaEquipo_idx` (`idEquipo`),
  KEY `RentaUsuario_idx` (`idUsuario`),
  CONSTRAINT `RentaEmpleado` FOREIGN KEY (`idEmpleado`) REFERENCES `empleado` (`idEmpleado`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `RentaEquipo` FOREIGN KEY (`idEquipo`) REFERENCES `equipo` (`idEquipo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `RentaUsuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idusuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Dumping data for table audiovisualesdb.rentadevolucion: ~2 rows (approximately)
/*!40000 ALTER TABLE `rentadevolucion` DISABLE KEYS */;
INSERT INTO `rentadevolucion` (`No_Prestamo`, `idEmpleado`, `idEquipo`, `idUsuario`, `FechaPrestamo`, `FechaDevolucion`, `Comentario`, `Estado`) VALUES
	(1, 1, 1, 1, '2020-08-01', '2020-08-16', 'DD. Devuelto!!!!!. ./n Devuelto!!!!!', 0),
	(2, 1, 1, 1, '2020-08-16', '2020-08-16', 'Preuba Numero 2 de Renta. Devuelto!!!!!', 0);
/*!40000 ALTER TABLE `rentadevolucion` ENABLE KEYS */;

-- Dumping structure for table audiovisualesdb.rol
DROP TABLE IF EXISTS `rol`;
CREATE TABLE IF NOT EXISTS `rol` (
  `idRol` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `Descripcion` varchar(100) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`idRol`),
  UNIQUE KEY `idRol_UNIQUE` (`idRol`),
  UNIQUE KEY `Nombre_UNIQUE` (`Nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Dumping data for table audiovisualesdb.rol: ~2 rows (approximately)
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` (`idRol`, `Nombre`, `Descripcion`, `Estado`) VALUES
	(1, 'Administrador', 'Rol FULL Privilegios', 1),
	(2, 'Empleado Comun', 'Rol Basico para los empleados', 1);
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;

-- Dumping structure for table audiovisualesdb.tecconexion
DROP TABLE IF EXISTS `tecconexion`;
CREATE TABLE IF NOT EXISTS `tecconexion` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(150) COLLATE utf8_spanish2_ci NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`Id`),
  KEY `TecnologiaConexionDescripcion` (`Descripcion`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Dumping data for table audiovisualesdb.tecconexion: ~6 rows (approximately)
/*!40000 ALTER TABLE `tecconexion` DISABLE KEYS */;
INSERT INTO `tecconexion` (`Id`, `Descripcion`, `Estado`) VALUES
	(1, 'USB', 1),
	(2, 'PS2', 1),
	(3, 'Serial', 1),
	(4, 'Paralelo', 1),
	(5, 'Infrarrojo', 1),
	(6, 'Bluetooth', 1);
/*!40000 ALTER TABLE `tecconexion` ENABLE KEYS */;

-- Dumping structure for table audiovisualesdb.tipoequipo
DROP TABLE IF EXISTS `tipoequipo`;
CREATE TABLE IF NOT EXISTS `tipoequipo` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(150) COLLATE utf8_spanish2_ci NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`Id`),
  KEY `TipoEquipoDescripcion` (`Descripcion`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Dumping data for table audiovisualesdb.tipoequipo: ~3 rows (approximately)
/*!40000 ALTER TABLE `tipoequipo` DISABLE KEYS */;
INSERT INTO `tipoequipo` (`Id`, `Descripcion`, `Estado`) VALUES
	(1, 'Tipo 1', 1),
	(2, 'Tipo 2', 1),
	(3, 'Tipo 3 ', 1);
/*!40000 ALTER TABLE `tipoequipo` ENABLE KEYS */;

-- Dumping structure for table audiovisualesdb.tipopersona
DROP TABLE IF EXISTS `tipopersona`;
CREATE TABLE IF NOT EXISTS `tipopersona` (
  `idTipoPesona` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idTipoPesona`),
  UNIQUE KEY `Descripcion_UNIQUE` (`Descripcion`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Dumping data for table audiovisualesdb.tipopersona: ~2 rows (approximately)
/*!40000 ALTER TABLE `tipopersona` DISABLE KEYS */;
INSERT INTO `tipopersona` (`idTipoPesona`, `Descripcion`, `Estado`) VALUES
	(1, 'Fisica', 1),
	(2, 'Juridica', 1);
/*!40000 ALTER TABLE `tipopersona` ENABLE KEYS */;

-- Dumping structure for table audiovisualesdb.tipousuario
DROP TABLE IF EXISTS `tipousuario`;
CREATE TABLE IF NOT EXISTS `tipousuario` (
  `idTipoUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idTipoUsuario`),
  UNIQUE KEY `Descripcion_UNIQUE` (`Descripcion`),
  UNIQUE KEY `idTipoUsuario_UNIQUE` (`idTipoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Dumping data for table audiovisualesdb.tipousuario: ~3 rows (approximately)
/*!40000 ALTER TABLE `tipousuario` DISABLE KEYS */;
INSERT INTO `tipousuario` (`idTipoUsuario`, `Descripcion`, `Estado`) VALUES
	(1, 'Profesor', 1),
	(2, 'Estudiante', 1),
	(3, 'Empleado', 1);
/*!40000 ALTER TABLE `tipousuario` ENABLE KEYS */;

-- Dumping structure for table audiovisualesdb.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(150) COLLATE utf8_spanish2_ci NOT NULL,
  `Cedula` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `No_Carnet` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `idTipoUsuario` int(11) DEFAULT NULL,
  `idTipoPersona` int(11) DEFAULT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idusuario`),
  UNIQUE KEY `idusuario_UNIQUE` (`idusuario`),
  UNIQUE KEY `Cedula_UNIQUE` (`Cedula`),
  UNIQUE KEY `No_Carnet_UNIQUE` (`No_Carnet`),
  KEY `UsuarioToTipoUsuario_idx` (`idTipoUsuario`),
  KEY `UsuarioToTipoPersona_idx` (`idTipoPersona`),
  CONSTRAINT `UsuarioToTipoPersona` FOREIGN KEY (`idTipoPersona`) REFERENCES `tipopersona` (`idTipoPesona`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `UsuarioToTipoUsuario` FOREIGN KEY (`idTipoUsuario`) REFERENCES `tipousuario` (`idTipoUsuario`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- Dumping data for table audiovisualesdb.usuario: ~1 rows (approximately)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`idusuario`, `Nombre`, `Cedula`, `No_Carnet`, `idTipoUsuario`, `idTipoPersona`, `Estado`) VALUES
	(1, 'Jesus ', '402-1457460-6', '00000', 1, 1, 1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

-- Dumping structure for trigger audiovisualesdb.rentadevolucion_AFTER_INSERT
DROP TRIGGER IF EXISTS `rentadevolucion_AFTER_INSERT`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `audiovisualesdb`.`rentadevolucion_AFTER_INSERT` AFTER INSERT ON `rentadevolucion` FOR EACH ROW
BEGIN
 update equipo
 set Rentado = "S"
 where idEquipo = new.idEquipo;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
