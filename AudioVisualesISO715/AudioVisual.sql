
-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-06-2020 a las 19:34:05
-- Versión del servidor: 5.7.21-log
-- Versión de PHP: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `audiovisualesdb`
--
DROP DATABASE IF EXISTS `audiovisualesdb`;
CREATE DATABASE IF NOT EXISTS `audiovisualesdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE `audiovisualesdb`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `idEmpleado` int(11) NOT NULL,
  `Nombre` varchar(150) COLLATE utf8_spanish2_ci NOT NULL,
  `Cedula` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `Tanda_Labor` varchar(45) COLLATE utf8_spanish2_ci NOT NULL DEFAULT 'Matutina',
  `FechaIngreso` date NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo`
--

CREATE TABLE `equipo` (
  `idEquipo` int(11) NOT NULL,
  `Descripcion` varchar(150) COLLATE utf8_spanish2_ci NOT NULL,
  `No_Serial` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `ServiceTag` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `idTipoEquipo` int(11) DEFAULT NULL,
  `idModelo` int(11) DEFAULT NULL,
  `idTecConexion` int(11) DEFAULT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marcas`
--

CREATE TABLE `marcas` (
  `Id` int(11) NOT NULL,
  `Descripcion` varchar(150) NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modelos`
--

CREATE TABLE `modelos` (
  `Id` int(11) NOT NULL,
  `IdMarca` int(11) NOT NULL,
  `Descripcion` varchar(150) NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rentadevolucion`
--

CREATE TABLE `rentadevolucion` (
  `No_Prestamo` int(11) NOT NULL,
  `idEmpleado` int(11) NOT NULL,
  `idEquipo` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `FechaPrestamo` date NOT NULL,
  `FechaDevolucion` date DEFAULT NULL,
  `Comentario` varchar(240) COLLATE utf8_spanish2_ci NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tecconexion`
--

CREATE TABLE `tecconexion` (
  `Id` int(11) NOT NULL,
  `Descripcion` varchar(150) NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoequipo`
--

CREATE TABLE `tipoequipo` (
  `Id` int(11) NOT NULL,
  `Descripcion` varchar(150) NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipopersona`
--

CREATE TABLE `tipopersona` (
  `idTipoPesona` int(11) NOT NULL,
  `Descripcion` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `tipopersona`
--

INSERT INTO `tipopersona` (`idTipoPesona`, `Descripcion`, `Estado`) VALUES
(1, 'Fisica', 1),
(2, 'Juridica', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipousuario`
--

CREATE TABLE `tipousuario` (
  `idTipoUsuario` int(11) NOT NULL,
  `Descripcion` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `tipousuario`
--

INSERT INTO `tipousuario` (`idTipoUsuario`, `Descripcion`, `Estado`) VALUES
(1, 'Profesor', 1),
(2, 'Estudiante', 1),
(3, 'Empleado', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL,
  `Nombre` varchar(150) COLLATE utf8_spanish2_ci NOT NULL,
  `Cedula` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `No_Carnet` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `idTipoUsuario` int(11) DEFAULT NULL,
  `idTipoPersona` int(11) DEFAULT NULL,
  `Estado` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`idEmpleado`),
  ADD UNIQUE KEY `Cedula_UNIQUE` (`Cedula`),
  ADD KEY `NombreEmpleado_Index` (`Nombre`);

--
-- Indices de la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD PRIMARY KEY (`idEquipo`),
  ADD UNIQUE KEY `idEquipo_UNIQUE` (`idEquipo`),
  ADD UNIQUE KEY `ServiceTag_UNIQUE` (`ServiceTag`),
  ADD KEY `EquipoToTipo_idx` (`idTipoEquipo`),
  ADD KEY `EquipoToModelo_idx` (`idModelo`),
  ADD KEY `EquipoToTecConexion_idx` (`idTecConexion`);

--
-- Indices de la tabla `marcas`
--
ALTER TABLE `marcas`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `MarcaDescripcion` (`Descripcion`);

--
-- Indices de la tabla `modelos`
--
ALTER TABLE `modelos`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `ModeloDescripcion` (`Descripcion`),
  ADD KEY `ModeloToMarca_idx` (`IdMarca`);

--
-- Indices de la tabla `rentadevolucion`
--
ALTER TABLE `rentadevolucion`
  ADD PRIMARY KEY (`No_Prestamo`),
  ADD KEY `ComentarioRenta` (`Comentario`),
  ADD KEY `FechaRenta` (`FechaPrestamo`),
  ADD KEY `FechaPrestamo` (`FechaDevolucion`),
  ADD KEY `EstadoRenta` (`Estado`),
  ADD KEY `RentaEmpleado_idx` (`idEmpleado`),
  ADD KEY `RentaEquipo_idx` (`idEquipo`),
  ADD KEY `RentaUsuario_idx` (`idUsuario`);

--
-- Indices de la tabla `tecconexion`
--
ALTER TABLE `tecconexion`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `TecnologiaConexionDescripcion` (`Descripcion`);

--
-- Indices de la tabla `tipoequipo`
--
ALTER TABLE `tipoequipo`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `TipoEquipoDescripcion` (`Descripcion`);

--
-- Indices de la tabla `tipopersona`
--
ALTER TABLE `tipopersona`
  ADD PRIMARY KEY (`idTipoPesona`),
  ADD UNIQUE KEY `Descripcion_UNIQUE` (`Descripcion`);

--
-- Indices de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  ADD PRIMARY KEY (`idTipoUsuario`),
  ADD UNIQUE KEY `Descripcion_UNIQUE` (`Descripcion`),
  ADD UNIQUE KEY `idTipoUsuario_UNIQUE` (`idTipoUsuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idusuario`),
  ADD UNIQUE KEY `idusuario_UNIQUE` (`idusuario`),
  ADD UNIQUE KEY `Cedula_UNIQUE` (`Cedula`),
  ADD UNIQUE KEY `No_Carnet_UNIQUE` (`No_Carnet`),
  ADD KEY `UsuarioToTipoUsuario_idx` (`idTipoUsuario`),
  ADD KEY `UsuarioToTipoPersona_idx` (`idTipoPersona`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `idEmpleado` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `equipo`
--
ALTER TABLE `equipo`
  MODIFY `idEquipo` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `marcas`
--
ALTER TABLE `marcas`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `modelos`
--
ALTER TABLE `modelos`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `rentadevolucion`
--
ALTER TABLE `rentadevolucion`
  MODIFY `No_Prestamo` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tecconexion`
--
ALTER TABLE `tecconexion`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tipoequipo`
--
ALTER TABLE `tipoequipo`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tipopersona`
--
ALTER TABLE `tipopersona`
  MODIFY `idTipoPesona` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  MODIFY `idTipoUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idusuario` int(11) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD CONSTRAINT `EquipoToModelo` FOREIGN KEY (`idModelo`) REFERENCES `modelos` (`Id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `EquipoToTecConexion` FOREIGN KEY (`idTecConexion`) REFERENCES `tecconexion` (`Id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `EquipoToTipo` FOREIGN KEY (`idTipoEquipo`) REFERENCES `tipoequipo` (`Id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `modelos`
--
ALTER TABLE `modelos`
  ADD CONSTRAINT `ModeloToMarca` FOREIGN KEY (`IdMarca`) REFERENCES `marcas` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `rentadevolucion`
--
ALTER TABLE `rentadevolucion`
  ADD CONSTRAINT `RentaEmpleado` FOREIGN KEY (`idEmpleado`) REFERENCES `empleado` (`idEmpleado`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `RentaEquipo` FOREIGN KEY (`idEquipo`) REFERENCES `equipo` (`idEquipo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `RentaUsuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idusuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `UsuarioToTipoPersona` FOREIGN KEY (`idTipoPersona`) REFERENCES `tipopersona` (`idTipoPesona`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `UsuarioToTipoUsuario` FOREIGN KEY (`idTipoUsuario`) REFERENCES `tipousuario` (`idTipoUsuario`) ON DELETE SET NULL ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
