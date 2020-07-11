-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema audiovisualesdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `audiovisualesdb` ;

-- -----------------------------------------------------
-- Schema audiovisualesdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `audiovisualesdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci ;
USE `audiovisualesdb` ;

-- -----------------------------------------------------
-- Table `audiovisualesdb`.`empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiovisualesdb`.`empleado` (
  `idEmpleado` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(150) NOT NULL,
  `Cedula` VARCHAR(15) NOT NULL,
  `Tanda_Labor` VARCHAR(45) NOT NULL DEFAULT 'Matutina',
  `FechaIngreso` DATE NOT NULL,
  `Estado` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idEmpleado`),
  UNIQUE INDEX `Cedula_UNIQUE` (`Cedula` ASC) VISIBLE,
  INDEX `NombreEmpleado_Index` (`Nombre` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `audiovisualesdb`.`marcas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiovisualesdb`.`marcas` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(150) NOT NULL,
  `Estado` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`Id`),
  INDEX `MarcaDescripcion` (`Descripcion` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `audiovisualesdb`.`modelos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiovisualesdb`.`modelos` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `IdMarca` INT(11) NOT NULL,
  `Descripcion` VARCHAR(150) NOT NULL,
  `Estado` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`Id`),
  INDEX `ModeloDescripcion` (`Descripcion` ASC) VISIBLE,
  INDEX `ModeloToMarca_idx` (`IdMarca` ASC) VISIBLE,
  CONSTRAINT `ModeloToMarca`
    FOREIGN KEY (`IdMarca`)
    REFERENCES `audiovisualesdb`.`marcas` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `audiovisualesdb`.`tecconexion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiovisualesdb`.`tecconexion` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(150) NOT NULL,
  `Estado` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`Id`),
  INDEX `TecnologiaConexionDescripcion` (`Descripcion` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `audiovisualesdb`.`tipoequipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiovisualesdb`.`tipoequipo` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(150) NOT NULL,
  `Estado` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`Id`),
  INDEX `TipoEquipoDescripcion` (`Descripcion` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `audiovisualesdb`.`equipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiovisualesdb`.`equipo` (
  `idEquipo` INT(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(150) NOT NULL,
  `No_Serial` VARCHAR(45) NOT NULL,
  `ServiceTag` VARCHAR(45) NOT NULL,
  `idTipoEquipo` INT(11) NULL DEFAULT NULL,
  `idModelo` INT(11) NULL DEFAULT NULL,
  `idTecConexion` INT(11) NULL DEFAULT NULL,
  `Estado` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idEquipo`),
  UNIQUE INDEX `idEquipo_UNIQUE` (`idEquipo` ASC) VISIBLE,
  UNIQUE INDEX `ServiceTag_UNIQUE` (`ServiceTag` ASC) VISIBLE,
  INDEX `EquipoToTipo_idx` (`idTipoEquipo` ASC) VISIBLE,
  INDEX `EquipoToModelo_idx` (`idModelo` ASC) VISIBLE,
  INDEX `EquipoToTecConexion_idx` (`idTecConexion` ASC) VISIBLE,
  CONSTRAINT `EquipoToModelo`
    FOREIGN KEY (`idModelo`)
    REFERENCES `audiovisualesdb`.`modelos` (`Id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `EquipoToTecConexion`
    FOREIGN KEY (`idTecConexion`)
    REFERENCES `audiovisualesdb`.`tecconexion` (`Id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `EquipoToTipo`
    FOREIGN KEY (`idTipoEquipo`)
    REFERENCES `audiovisualesdb`.`tipoequipo` (`Id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `audiovisualesdb`.`tipopersona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiovisualesdb`.`tipopersona` (
  `idTipoPesona` INT(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(45) NOT NULL,
  `Estado` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idTipoPesona`),
  UNIQUE INDEX `Descripcion_UNIQUE` (`Descripcion` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `audiovisualesdb`.`tipousuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiovisualesdb`.`tipousuario` (
  `idTipoUsuario` INT(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(45) NOT NULL,
  `Estado` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idTipoUsuario`),
  UNIQUE INDEX `Descripcion_UNIQUE` (`Descripcion` ASC) VISIBLE,
  UNIQUE INDEX `idTipoUsuario_UNIQUE` (`idTipoUsuario` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `audiovisualesdb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiovisualesdb`.`usuario` (
  `idusuario` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(150) NOT NULL,
  `Cedula` VARCHAR(15) NOT NULL,
  `No_Carnet` VARCHAR(10) NOT NULL,
  `idTipoUsuario` INT(11) NULL DEFAULT NULL,
  `idTipoPersona` INT(11) NULL DEFAULT NULL,
  `Estado` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idusuario`),
  UNIQUE INDEX `idusuario_UNIQUE` (`idusuario` ASC) VISIBLE,
  UNIQUE INDEX `Cedula_UNIQUE` (`Cedula` ASC) VISIBLE,
  UNIQUE INDEX `No_Carnet_UNIQUE` (`No_Carnet` ASC) VISIBLE,
  INDEX `UsuarioToTipoUsuario_idx` (`idTipoUsuario` ASC) VISIBLE,
  INDEX `UsuarioToTipoPersona_idx` (`idTipoPersona` ASC) VISIBLE,
  CONSTRAINT `UsuarioToTipoPersona`
    FOREIGN KEY (`idTipoPersona`)
    REFERENCES `audiovisualesdb`.`tipopersona` (`idTipoPesona`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `UsuarioToTipoUsuario`
    FOREIGN KEY (`idTipoUsuario`)
    REFERENCES `audiovisualesdb`.`tipousuario` (`idTipoUsuario`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `audiovisualesdb`.`rentadevolucion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiovisualesdb`.`rentadevolucion` (
  `No_Prestamo` INT(11) NOT NULL AUTO_INCREMENT,
  `idEmpleado` INT(11) NOT NULL,
  `idEquipo` INT(11) NOT NULL,
  `idUsuario` INT(11) NOT NULL,
  `FechaPrestamo` DATE NOT NULL,
  `FechaDevolucion` DATE NULL DEFAULT NULL,
  `Comentario` VARCHAR(240) NOT NULL,
  `Estado` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`No_Prestamo`),
  INDEX `ComentarioRenta` (`Comentario` ASC) VISIBLE,
  INDEX `FechaRenta` (`FechaPrestamo` ASC) VISIBLE,
  INDEX `FechaPrestamo` (`FechaDevolucion` ASC) VISIBLE,
  INDEX `EstadoRenta` (`Estado` ASC) VISIBLE,
  INDEX `RentaEmpleado_idx` (`idEmpleado` ASC) VISIBLE,
  INDEX `RentaEquipo_idx` (`idEquipo` ASC) VISIBLE,
  INDEX `RentaUsuario_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `RentaEmpleado`
    FOREIGN KEY (`idEmpleado`)
    REFERENCES `audiovisualesdb`.`empleado` (`idEmpleado`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `RentaEquipo`
    FOREIGN KEY (`idEquipo`)
    REFERENCES `audiovisualesdb`.`equipo` (`idEquipo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `RentaUsuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `audiovisualesdb`.`usuario` (`idusuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;


-- -----------------------------------------------------
-- Table `audiovisualesdb`.`Rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiovisualesdb`.`Rol` (
  `idRol` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Descripcion` VARCHAR(100) NULL,
  `Estado` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`idRol`),
  UNIQUE INDEX `idRol_UNIQUE` (`idRol` ASC) VISIBLE,
  UNIQUE INDEX `Nombre_UNIQUE` (`Nombre` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `audiovisualesdb`.`Credencial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `audiovisualesdb`.`Credencial` (
  `IdEmpleado` INT NOT NULL,
  `IdRol` INT NOT NULL,
  `Username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Estado` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`IdEmpleado`),
  UNIQUE INDEX `Id_UNIQUE` (`IdEmpleado` ASC) VISIBLE,
  UNIQUE INDEX `Username_UNIQUE` (`Username` ASC) VISIBLE,
  INDEX `RolCredencial_idx` (`IdRol` ASC) VISIBLE,
  CONSTRAINT `CredencialEmpleado`
    FOREIGN KEY (`IdEmpleado`)
    REFERENCES `audiovisualesdb`.`empleado` (`idEmpleado`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `RolCredencial`
    FOREIGN KEY (`IdRol`)
    REFERENCES `audiovisualesdb`.`Rol` (`idRol`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
