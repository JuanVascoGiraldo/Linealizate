-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bdalgebra
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bdalgebra` ;

-- -----------------------------------------------------
-- Schema bdalgebra
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bdalgebra` DEFAULT CHARACTER SET utf8 ;
USE `bdalgebra` ;

-- -----------------------------------------------------
-- Table `bdalgebra`.`MUsuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdalgebra`.`MUsuario` (
  `id_usu` INT NOT NULL AUTO_INCREMENT,
  `nom_usu` VARCHAR(60) NOT NULL,
  `boleta_usu` BIGINT(10) NOT NULL,
  `correo_usu` VARCHAR(60) NOT NULL,
  `contra_usu` VARCHAR(45) NOT NULL,
  `rol_usu` INT NOT NULL,
  PRIMARY KEY (`id_usu`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdalgebra`.`CTipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdalgebra`.`CTipo` (
  `id_tipo` INT NOT NULL AUTO_INCREMENT,
  `nom_tipo` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`id_tipo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdalgebra`.`MMaterial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdalgebra`.`MMaterial` (
  `id_material` INT NOT NULL AUTO_INCREMENT,
  `titulo_publi` VARCHAR(100) NOT NULL,
  `link_publi` VARCHAR(200) NOT NULL,
  `bibliografia_publi` VARCHAR(150) NOT NULL,
  `estado` INT NOT NULL,
  `id_tipo` INT NOT NULL,
  `id_usu` INT NOT NULL,
  PRIMARY KEY (`id_material`),
  INDEX `tip_idx` (`id_tipo` ASC) ,
  INDEX `usua_idx` (`id_usu` ASC) ,
  CONSTRAINT `tip`
    FOREIGN KEY (`id_tipo`)
    REFERENCES `bdalgebra`.`CTipo` (`id_tipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `usua`
    FOREIGN KEY (`id_usu`)
    REFERENCES `bdalgebra`.`MUsuario` (`id_usu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdalgebra`.`CUnidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdalgebra`.`CUnidad` (
  `id_unidad` INT NOT NULL AUTO_INCREMENT,
  `nom_unidad` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_unidad`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdalgebra`.`CTema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdalgebra`.`CTema` (
  `id_tema` INT NOT NULL AUTO_INCREMENT,
  `nom_tema` VARCHAR(100) NOT NULL,
  `id_unidad` INT NOT NULL,
  PRIMARY KEY (`id_tema`),
  INDEX `unidad_idx` (`id_unidad` ASC) ,
  CONSTRAINT `unidad`
    FOREIGN KEY (`id_unidad`)
    REFERENCES `bdalgebra`.`CUnidad` (`id_unidad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdalgebra`.`EMaterial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdalgebra`.`EMaterial` (
  `id_material` INT NOT NULL AUTO_INCREMENT,
  `id_publi` INT NOT NULL,
  `id_tipo` INT NOT NULL,
  `id_usu` INT NOT NULL,
  PRIMARY KEY (`id_material`),
  INDEX `publi_idx` (`id_publi` ASC),
  INDEX `tipo_idx` (`id_tipo` ASC) ,
  INDEX `usuario_idx` (`id_usu` ASC) ,
  CONSTRAINT `publi`
    FOREIGN KEY (`id_publi`)
    REFERENCES `bdalgebra`.`MMaterial` (`id_material`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `tipo`
    FOREIGN KEY (`id_tipo`)
    REFERENCES `bdalgebra`.`CTipo` (`id_tipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `usuario`
    FOREIGN KEY (`id_usu`)
    REFERENCES `bdalgebra`.`MUsuario` (`id_usu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdalgebra`.`MReporte`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdalgebra`.`MReporte` (
  `id_reporte` INT NOT NULL AUTO_INCREMENT,
  `fecha_reporte` VARCHAR(10) NOT NULL,
  `text_reporte` VARCHAR(150) NOT NULL,
  `usu_reporte` VARCHAR(60) NOT NULL,
  `id_material` INT NOT NULL,
  `estado` INT NOT NULL,
  PRIMARY KEY (`id_reporte`),
  INDEX `material_idx` (`id_material` ASC) ,
  CONSTRAINT `material`
    FOREIGN KEY (`id_material`)
    REFERENCES `bdalgebra`.`MMaterial` (`id_material`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdalgebra`.`ETemaMaterial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdalgebra`.`ETemaMaterial` (
  `id_tm` INT NOT NULL AUTO_INCREMENT,
  `id_material` INT NOT NULL,
  `id_tema` INT NOT NULL,
  PRIMARY KEY (`id_tm`),
  INDEX `tema_idx` (`id_tema` ASC) ,
  INDEX `material_idx` (`id_material` ASC) ,
  CONSTRAINT `tema`
    FOREIGN KEY (`id_tema`)
    REFERENCES `bdalgebra`.`CTema` (`id_tema`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `materiall`
    FOREIGN KEY (`id_material`)
    REFERENCES `bdalgebra`.`MMaterial` (`id_material`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `bdalgebra`.`MUsuario`
-- -----------------------------------------------------
START TRANSACTION;
USE `bdalgebra`;
INSERT INTO `bdalgebra`.`MUsuario` (`id_usu`, `nom_usu`, `boleta_usu`, `correo_usu`, `contra_usu`, `rol_usu`) VALUES (1, 'Maestra', 0000000000, 'juanlandia3000@gmail.com', 'bKqhj7jOzbYmtXat/fUwkA==', 1);
INSERT INTO `bdalgebra`.`MUsuario` (`id_usu`, `nom_usu`, `boleta_usu`, `correo_usu`, `contra_usu`, `rol_usu`) VALUES (2, 'Subir', 0000000001, 'vasco.giraldo.juan.esteban@gmail.com', 'bKqhj7jOzbYmtXat/fUwkA==', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `bdalgebra`.`CTipo`
-- -----------------------------------------------------
START TRANSACTION;
USE `bdalgebra`;
INSERT INTO `bdalgebra`.`CTipo` (`id_tipo`, `nom_tipo`) VALUES (1, 'Videos');
INSERT INTO `bdalgebra`.`CTipo` (`id_tipo`, `nom_tipo`) VALUES (2, 'Infografías');
INSERT INTO `bdalgebra`.`CTipo` (`id_tipo`, `nom_tipo`) VALUES (3, 'Ejemplos');
INSERT INTO `bdalgebra`.`CTipo` (`id_tipo`, `nom_tipo`) VALUES (4, 'Exámenes');
INSERT INTO `bdalgebra`.`CTipo` (`id_tipo`, `nom_tipo`) VALUES (5, 'Listas');

COMMIT;


-- -----------------------------------------------------
-- Data for table `bdalgebra`.`CUnidad`
-- -----------------------------------------------------
START TRANSACTION;
USE `bdalgebra`;
INSERT INTO `bdalgebra`.`CUnidad` (`id_unidad`, `nom_unidad`) VALUES (1, 'Unidad 1');
INSERT INTO `bdalgebra`.`CUnidad` (`id_unidad`, `nom_unidad`) VALUES (2, 'Unidad 2');
INSERT INTO `bdalgebra`.`CUnidad` (`id_unidad`, `nom_unidad`) VALUES (3, 'Unidad 3');
INSERT INTO `bdalgebra`.`CUnidad` (`id_unidad`, `nom_unidad`) VALUES (4, 'Unidad 4');

COMMIT;


-- -----------------------------------------------------
-- Data for table `bdalgebra`.`CTema`
-- -----------------------------------------------------
START TRANSACTION;
USE `bdalgebra`;
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (1, 'Eliminación de Gauss y de Gauss-Gordan con pivoteo', 1);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (2, 'Representación matricial de un sistema de ecuaciones', 1);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (3, 'Cálculo de determinantes', 1);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (4, 'Inversa de una Matriz', 1);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (5, 'La inversa de una matriz a través de su adjunta', 1);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (6, 'Solución de sistema de ecuaciones lineales usando la inversa de la matriz de coeficiente', 1);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (7, 'Espacios vectoriales de distintos géneros', 2);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (8, 'Subespacios vectoriales de distintos géneros', 2);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (9, 'Combinaciónes lineales', 2);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (10, 'Espacio generado', 2);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (11, 'Dependencia e independencia lineal', 2);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (12, 'Bases de un Espacio Vectorial', 2);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (13, 'Dimensión de un Espacio Vectorial', 2);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (14, 'Rango y Nulidad de una matriz', 2);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (15, 'Matriz Cambio de Base', 2);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (16, 'Proceso de ortonormalización de Gram-Schmid', 2);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (17, 'Propiedades de las Transformaciones Lineales', 3);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (18, 'Imagen y Kernel de una transformación lineal', 3);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (19, 'Isomorfismos', 3);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (20, 'Cálculo de vectores caracteristicos', 4);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (21, 'Diagonalización de matrices', 4);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (22, 'Matrices Simétricas y diagonalización ortogonal', 4);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (23, 'Formas Cuadráticas y secciones cónicas', 4);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (24, 'Aplicaciones a Ecuaciones diferenciales matriciales', 4);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (25, 'Representación matricial de una transformación lineal', 2);
INSERT INTO `bdalgebra`.`CTema` (`id_tema`, `nom_tema`, `id_unidad`) VALUES (26, 'Sistema de ecuaciones lineales homogéneas', 1);

COMMIT;

