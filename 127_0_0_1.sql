-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-10-2026 a las 19:34:26
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bst`
--
CREATE DATABASE IF NOT EXISTS `bst` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bst`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aerolineas`
--

CREATE TABLE `aerolineas` (
  `IDAEROLINEA` int(11) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cajas`
--

CREATE TABLE `cajas` (
  `IDCAJA` int(11) NOT NULL,
  `DESCRIPCION` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `IDCLIENTE` int(11) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL,
  `TEL` varchar(15) NOT NULL,
  `DOMICILIO` varchar(150) NOT NULL,
  `CIUDAD` varchar(150) NOT NULL,
  `ESTADO` varchar(50) NOT NULL,
  `EMAIL` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contratos`
--

CREATE TABLE `contratos` (
  `IDCONTRATO` int(11) NOT NULL,
  `IDCLIENTE` int(11) NOT NULL,
  `IDPAQUETE` int(11) NOT NULL,
  `FREG` varchar(15) NOT NULL,
  `VCLAVER` varchar(100) DEFAULT NULL,
  `HCLAVER` varchar(100) DEFAULT NULL,
  `TCLAVER` varchar(100) DEFAULT NULL,
  `TOCLAVER` varchar(100) DEFAULT NULL,
  `RACLAVER` varchar(100) DEFAULT NULL,
  `OCLAVER` varchar(100) DEFAULT NULL,
  `NVIAJEROS` int(11) NOT NULL,
  `NADULTOS` int(11) NOT NULL,
  `NNINOS` int(11) NOT NULL,
  `FLIMITE` varchar(15) NOT NULL,
  `VUELO` tinyint(1) NOT NULL,
  `HOTEL` tinyint(1) NOT NULL,
  `TRASLADO` tinyint(1) NOT NULL,
  `RAUTO` tinyint(1) NOT NULL,
  `TOUR` tinyint(1) NOT NULL,
  `OTROS` tinyint(1) NOT NULL,
  `PRECIOVENTA` float NOT NULL,
  `SALDO` float NOT NULL,
  `ESTATUS` varchar(15) NOT NULL,
  `IDCAJA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contratosd`
--

CREATE TABLE `contratosd` (
  `IDCONTRATOD` int(11) NOT NULL,
  `IDCONTRATO` int(11) NOT NULL,
  `COMENTARIO` varchar(1000) NOT NULL,
  `FECHA` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cortes`
--

CREATE TABLE `cortes` (
  `IDCORTE` int(11) NOT NULL,
  `MONTOINI` float NOT NULL,
  `FINI` varchar(15) DEFAULT NULL,
  `FFIN` varchar(15) DEFAULT NULL,
  `MONTOFIN` float DEFAULT NULL,
  `IDCAJA` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cortesd`
--

CREATE TABLE `cortesd` (
  `IDCORTED` int(11) NOT NULL,
  `IDCORTE` int(11) DEFAULT NULL,
  `IDPAGOI` int(11) DEFAULT NULL,
  `IDPAGOF` int(11) DEFAULT NULL,
  `IDENTRADAI` int(11) DEFAULT NULL,
  `IDENTRADAF` int(11) DEFAULT NULL,
  `IDSALIDAI` int(11) DEFAULT NULL,
  `IDSALIDAF` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `destinos`
--

CREATE TABLE `destinos` (
  `IDDESTINO` int(11) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalles`
--

CREATE TABLE `detalles` (
  `IDDETALLE` int(11) NOT NULL,
  `DIASPRE` int(11) NOT NULL,
  `CONCEPTO` varchar(150) NOT NULL,
  `SMINIMO` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `documentos`
--

CREATE TABLE `documentos` (
  `IDDOC` int(11) NOT NULL,
  `IDCONT` int(11) NOT NULL,
  `TIPO` varchar(30) NOT NULL,
  `RUTA` varchar(1000) NOT NULL,
  `COMENTARIO` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `edetalles`
--

CREATE TABLE `edetalles` (
  `IDEDETALLE` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `PINI` varchar(15) NOT NULL,
  `PFIN` varchar(15) NOT NULL,
  `VPAGADAS` tinyint(1) NOT NULL,
  `APAGADOS` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `IDEMPLEADO` int(11) NOT NULL,
  `NOMBRE` varchar(50) DEFAULT NULL,
  `APELLIDOS` varchar(50) DEFAULT NULL,
  `TEL` varchar(20) DEFAULT NULL,
  `SUELDO` float NOT NULL,
  `EMPRESA` varchar(60) DEFAULT NULL,
  `BONO` float NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `ESTADO` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entradas`
--

CREATE TABLE `entradas` (
  `IDENTRADA` int(11) NOT NULL,
  `DESCRIPCION` varchar(300) NOT NULL,
  `MONTO` float NOT NULL,
  `CATEGORIA` varchar(50) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `METODO` varchar(15) NOT NULL,
  `IDCAJA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hoteles`
--

CREATE TABLE `hoteles` (
  `IDHOTEL` int(11) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nominas`
--

CREATE TABLE `nominas` (
  `IDNOMINA` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `SEMANA` int(11) NOT NULL,
  `DIAS` int(11) NOT NULL,
  `NSUELDO` double NOT NULL,
  `BONO` double NOT NULL,
  `CONCEPTO` varchar(300) NOT NULL,
  `IDAHORRO` int(11) NOT NULL,
  `IDABONO` int(11) NOT NULL,
  `TOTAL` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `IDPAGO` int(11) NOT NULL,
  `IDCONTRATO` int(11) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `METODO` varchar(15) NOT NULL,
  `MONTO` float NOT NULL,
  `COMENTARIO` varchar(150) NOT NULL,
  `IDCAJA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagosp`
--

CREATE TABLE `pagosp` (
  `IDPAGOP` int(11) NOT NULL,
  `IDCONTRATO` int(11) NOT NULL,
  `IDPROVEEDOR` int(11) NOT NULL,
  `IDAEROLINEA` int(11) NOT NULL,
  `CATEGORIA` varchar(30) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `METODO` varchar(30) NOT NULL,
  `MONTO` float NOT NULL,
  `COMENTARIO` varchar(150) NOT NULL,
  `IDCAJA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paquetes`
--

CREATE TABLE `paquetes` (
  `IDPAQUETE` int(11) NOT NULL,
  `DESTINO` varchar(150) NOT NULL,
  `FSALIDA` varchar(15) NOT NULL,
  `FREGRESO` varchar(15) NOT NULL,
  `AEROLINEA` varchar(150) DEFAULT NULL,
  `VPROVEEDOR` varchar(150) DEFAULT NULL,
  `VCOSTO` float DEFAULT NULL,
  `VCLAVER` tinyint(1) DEFAULT NULL,
  `HOTEL` varchar(150) DEFAULT NULL,
  `HPROVEEDOR` varchar(150) DEFAULT NULL,
  `HCOSTO` float DEFAULT NULL,
  `HCLAVER` tinyint(1) DEFAULT NULL,
  `TPROVEEDOR` varchar(150) DEFAULT NULL,
  `TCONCEPTO` varchar(300) DEFAULT NULL,
  `TCOSTO` float DEFAULT NULL,
  `TCLAVER` tinyint(1) DEFAULT NULL,
  `TOPROVEEDOR` varchar(150) DEFAULT NULL,
  `TOCONCEPTO` varchar(300) DEFAULT NULL,
  `TOCOSTO` float DEFAULT NULL,
  `TOCLAVER` tinyint(1) DEFAULT NULL,
  `RAPROVEEDOR` varchar(150) DEFAULT NULL,
  `RACONCEPTO` varchar(300) DEFAULT NULL,
  `RACOSTO` float DEFAULT NULL,
  `RACLAVER` tinyint(1) DEFAULT NULL,
  `OPROVEEDOR` varchar(150) DEFAULT NULL,
  `OCONCEPTO` varchar(150) DEFAULT NULL,
  `OCOSTO` float DEFAULT NULL,
  `OCLAVER` tinyint(1) DEFAULT NULL,
  `COSTOINI` float NOT NULL,
  `COSTOFIN` float DEFAULT NULL,
  `PRECIOPUBLICO` float DEFAULT NULL,
  `DESCRIPCION` varchar(1000) DEFAULT NULL,
  `FLIMITE` varchar(15) DEFAULT NULL,
  `ESTATUS` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `IDPROVEEDOR` int(11) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL,
  `TEL` varchar(15) NOT NULL,
  `EMAIL` varchar(150) NOT NULL,
  `WEB` varchar(150) NOT NULL,
  `CATEGORIA` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salidas`
--

CREATE TABLE `salidas` (
  `IDSALIDA` int(11) NOT NULL,
  `DESCRIPCION` varchar(300) NOT NULL,
  `MONTO` float NOT NULL,
  `CATEGORIA` varchar(15) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `METODO` varchar(15) NOT NULL,
  `IDCAJA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `traslados`
--

CREATE TABLE `traslados` (
  `IDTRASLADO` int(11) NOT NULL,
  `NOMBRE` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `IDUSUARIO` int(11) NOT NULL,
  `NOMBRE` varchar(10) DEFAULT NULL,
  `PASSWORD` varchar(30) DEFAULT NULL,
  `PRIVILEGIOS` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vacaciones`
--

CREATE TABLE `vacaciones` (
  `IDVACACION` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FREG` varchar(15) NOT NULL,
  `FSALIDA` varchar(15) NOT NULL,
  `FREGRESO` varchar(15) NOT NULL,
  `DIAST` int(11) NOT NULL,
  `COMENTARIOS` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vacacionestab`
--

CREATE TABLE `vacacionestab` (
  `IDVACACIONTAB` int(11) NOT NULL,
  `DIAS` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viajeros`
--

CREATE TABLE `viajeros` (
  `IDVIAJERO` int(11) NOT NULL,
  `IDCONTRATO` int(11) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL,
  `CURP` varchar(30) NOT NULL,
  `FNACIMIENTO` varchar(15) NOT NULL,
  `EDAD` int(11) NOT NULL,
  `PASAPORTE` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `aerolineas`
--
ALTER TABLE `aerolineas`
  ADD PRIMARY KEY (`IDAEROLINEA`);

--
-- Indices de la tabla `cajas`
--
ALTER TABLE `cajas`
  ADD PRIMARY KEY (`IDCAJA`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`IDCLIENTE`);

--
-- Indices de la tabla `contratos`
--
ALTER TABLE `contratos`
  ADD PRIMARY KEY (`IDCONTRATO`);

--
-- Indices de la tabla `contratosd`
--
ALTER TABLE `contratosd`
  ADD PRIMARY KEY (`IDCONTRATOD`);

--
-- Indices de la tabla `cortes`
--
ALTER TABLE `cortes`
  ADD PRIMARY KEY (`IDCORTE`);

--
-- Indices de la tabla `cortesd`
--
ALTER TABLE `cortesd`
  ADD PRIMARY KEY (`IDCORTED`);

--
-- Indices de la tabla `destinos`
--
ALTER TABLE `destinos`
  ADD PRIMARY KEY (`IDDESTINO`);

--
-- Indices de la tabla `detalles`
--
ALTER TABLE `detalles`
  ADD PRIMARY KEY (`IDDETALLE`);

--
-- Indices de la tabla `documentos`
--
ALTER TABLE `documentos`
  ADD PRIMARY KEY (`IDDOC`);

--
-- Indices de la tabla `edetalles`
--
ALTER TABLE `edetalles`
  ADD PRIMARY KEY (`IDEDETALLE`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`IDEMPLEADO`);

--
-- Indices de la tabla `entradas`
--
ALTER TABLE `entradas`
  ADD PRIMARY KEY (`IDENTRADA`);

--
-- Indices de la tabla `hoteles`
--
ALTER TABLE `hoteles`
  ADD PRIMARY KEY (`IDHOTEL`);

--
-- Indices de la tabla `nominas`
--
ALTER TABLE `nominas`
  ADD PRIMARY KEY (`IDNOMINA`);

--
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD PRIMARY KEY (`IDPAGO`);

--
-- Indices de la tabla `pagosp`
--
ALTER TABLE `pagosp`
  ADD PRIMARY KEY (`IDPAGOP`);

--
-- Indices de la tabla `paquetes`
--
ALTER TABLE `paquetes`
  ADD PRIMARY KEY (`IDPAQUETE`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`IDPROVEEDOR`);

--
-- Indices de la tabla `salidas`
--
ALTER TABLE `salidas`
  ADD PRIMARY KEY (`IDSALIDA`);

--
-- Indices de la tabla `traslados`
--
ALTER TABLE `traslados`
  ADD PRIMARY KEY (`IDTRASLADO`);

--
-- Indices de la tabla `vacaciones`
--
ALTER TABLE `vacaciones`
  ADD PRIMARY KEY (`IDVACACION`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `aerolineas`
--
ALTER TABLE `aerolineas`
  MODIFY `IDAEROLINEA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cajas`
--
ALTER TABLE `cajas`
  MODIFY `IDCAJA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `IDCLIENTE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `contratos`
--
ALTER TABLE `contratos`
  MODIFY `IDCONTRATO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `contratosd`
--
ALTER TABLE `contratosd`
  MODIFY `IDCONTRATOD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cortesd`
--
ALTER TABLE `cortesd`
  MODIFY `IDCORTED` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `destinos`
--
ALTER TABLE `destinos`
  MODIFY `IDDESTINO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalles`
--
ALTER TABLE `detalles`
  MODIFY `IDDETALLE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `documentos`
--
ALTER TABLE `documentos`
  MODIFY `IDDOC` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `entradas`
--
ALTER TABLE `entradas`
  MODIFY `IDENTRADA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `hoteles`
--
ALTER TABLE `hoteles`
  MODIFY `IDHOTEL` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pagos`
--
ALTER TABLE `pagos`
  MODIFY `IDPAGO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pagosp`
--
ALTER TABLE `pagosp`
  MODIFY `IDPAGOP` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `paquetes`
--
ALTER TABLE `paquetes`
  MODIFY `IDPAQUETE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `IDPROVEEDOR` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `salidas`
--
ALTER TABLE `salidas`
  MODIFY `IDSALIDA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vacaciones`
--
ALTER TABLE `vacaciones`
  MODIFY `IDVACACION` int(11) NOT NULL AUTO_INCREMENT;
--
-- Base de datos: `bsta`
--
CREATE DATABASE IF NOT EXISTS `bsta` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bsta`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aerolineas`
--

CREATE TABLE `aerolineas` (
  `IDAEROLINEA` int(11) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cajas`
--

CREATE TABLE `cajas` (
  `IDCAJA` int(11) NOT NULL,
  `DESCRIPCION` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `IDCLIENTE` int(11) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL,
  `TEL` varchar(15) NOT NULL,
  `DOMICILIO` varchar(150) NOT NULL,
  `CIUDAD` varchar(150) NOT NULL,
  `ESTADO` varchar(50) NOT NULL,
  `EMAIL` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contratos`
--

CREATE TABLE `contratos` (
  `IDCONTRATO` int(11) NOT NULL,
  `IDCLIENTE` int(11) NOT NULL,
  `IDPAQUETE` int(11) NOT NULL,
  `FREG` varchar(15) NOT NULL,
  `VCLAVER` varchar(100) DEFAULT NULL,
  `HCLAVER` varchar(100) DEFAULT NULL,
  `TCLAVER` varchar(100) DEFAULT NULL,
  `TOCLAVER` varchar(100) DEFAULT NULL,
  `RACLAVER` varchar(100) DEFAULT NULL,
  `OCLAVER` varchar(100) DEFAULT NULL,
  `NVIAJEROS` int(11) NOT NULL,
  `NADULTOS` int(11) NOT NULL,
  `NNINOS` int(11) NOT NULL,
  `FLIMITE` varchar(15) NOT NULL,
  `VUELO` tinyint(1) NOT NULL,
  `HOTEL` tinyint(1) NOT NULL,
  `TRASLADO` tinyint(1) NOT NULL,
  `RAUTO` tinyint(1) NOT NULL,
  `TOUR` tinyint(1) NOT NULL,
  `OTROS` tinyint(1) NOT NULL,
  `PRECIOVENTA` float NOT NULL,
  `SALDO` float NOT NULL,
  `ESTATUS` varchar(15) NOT NULL,
  `IDCAJA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contratosd`
--

CREATE TABLE `contratosd` (
  `IDCONTRATOD` int(11) NOT NULL,
  `IDCONTRATO` int(11) NOT NULL,
  `COMENTARIO` varchar(1000) NOT NULL,
  `FECHA` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cortes`
--

CREATE TABLE `cortes` (
  `IDCORTE` int(11) NOT NULL,
  `MONTOINI` float NOT NULL,
  `FINI` varchar(15) DEFAULT NULL,
  `FFIN` varchar(15) DEFAULT NULL,
  `MONTOFIN` float DEFAULT NULL,
  `IDCAJA` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cortesd`
--

CREATE TABLE `cortesd` (
  `IDCORTED` int(11) NOT NULL,
  `IDCORTE` int(11) DEFAULT NULL,
  `IDPAGOI` int(11) DEFAULT NULL,
  `IDPAGOF` int(11) DEFAULT NULL,
  `IDENTRADAI` int(11) DEFAULT NULL,
  `IDENTRADAF` int(11) DEFAULT NULL,
  `IDSALIDAI` int(11) DEFAULT NULL,
  `IDSALIDAF` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `destinos`
--

CREATE TABLE `destinos` (
  `IDDESTINO` int(11) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalles`
--

CREATE TABLE `detalles` (
  `IDDETALLE` int(11) NOT NULL,
  `DIASPRE` int(11) NOT NULL,
  `CONCEPTO` varchar(150) NOT NULL,
  `SMINIMO` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `documentos`
--

CREATE TABLE `documentos` (
  `IDDOC` int(11) NOT NULL,
  `IDCONT` int(11) NOT NULL,
  `TIPO` varchar(30) NOT NULL,
  `RUTA` varchar(1000) NOT NULL,
  `COMENTARIO` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `edetalles`
--

CREATE TABLE `edetalles` (
  `IDEDETALLE` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `PINI` varchar(15) NOT NULL,
  `PFIN` varchar(15) NOT NULL,
  `VPAGADAS` tinyint(1) NOT NULL,
  `APAGADOS` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `IDEMPLEADO` int(11) NOT NULL,
  `NOMBRE` varchar(50) DEFAULT NULL,
  `APELLIDOS` varchar(50) DEFAULT NULL,
  `TEL` varchar(20) DEFAULT NULL,
  `SUELDO` float NOT NULL,
  `EMPRESA` varchar(60) DEFAULT NULL,
  `BONO` float NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `ESTADO` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entradas`
--

CREATE TABLE `entradas` (
  `IDENTRADA` int(11) NOT NULL,
  `DESCRIPCION` varchar(300) NOT NULL,
  `MONTO` float NOT NULL,
  `CATEGORIA` varchar(50) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `METODO` varchar(15) NOT NULL,
  `IDCAJA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hoteles`
--

CREATE TABLE `hoteles` (
  `IDHOTEL` int(11) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nominas`
--

CREATE TABLE `nominas` (
  `IDNOMINA` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `SEMANA` int(11) NOT NULL,
  `DIAS` int(11) NOT NULL,
  `NSUELDO` double NOT NULL,
  `BONO` double NOT NULL,
  `CONCEPTO` varchar(300) NOT NULL,
  `IDAHORRO` int(11) NOT NULL,
  `IDABONO` int(11) NOT NULL,
  `TOTAL` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `IDPAGO` int(11) NOT NULL,
  `IDCONTRATO` int(11) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `METODO` varchar(15) NOT NULL,
  `MONTO` float NOT NULL,
  `COMENTARIO` varchar(150) NOT NULL,
  `IDCAJA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagosp`
--

CREATE TABLE `pagosp` (
  `IDPAGOP` int(11) NOT NULL,
  `IDCONTRATO` int(11) NOT NULL,
  `IDPROVEEDOR` int(11) NOT NULL,
  `IDAEROLINEA` int(11) NOT NULL,
  `CATEGORIA` varchar(30) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `METODO` varchar(30) NOT NULL,
  `MONTO` float NOT NULL,
  `COMENTARIO` varchar(150) NOT NULL,
  `IDCAJA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paquetes`
--

CREATE TABLE `paquetes` (
  `IDPAQUETE` int(11) NOT NULL,
  `DESTINO` varchar(150) NOT NULL,
  `FSALIDA` varchar(15) NOT NULL,
  `FREGRESO` varchar(15) NOT NULL,
  `AEROLINEA` varchar(150) DEFAULT NULL,
  `VPROVEEDOR` varchar(150) DEFAULT NULL,
  `VCOSTO` float DEFAULT NULL,
  `VCLAVER` tinyint(1) DEFAULT NULL,
  `HOTEL` varchar(150) DEFAULT NULL,
  `HPROVEEDOR` varchar(150) DEFAULT NULL,
  `HCOSTO` float DEFAULT NULL,
  `HCLAVER` tinyint(1) DEFAULT NULL,
  `TPROVEEDOR` varchar(150) DEFAULT NULL,
  `TCONCEPTO` varchar(300) DEFAULT NULL,
  `TCOSTO` float DEFAULT NULL,
  `TCLAVER` tinyint(1) DEFAULT NULL,
  `TOPROVEEDOR` varchar(150) DEFAULT NULL,
  `TOCONCEPTO` varchar(300) DEFAULT NULL,
  `TOCOSTO` float DEFAULT NULL,
  `TOCLAVER` tinyint(1) DEFAULT NULL,
  `RAPROVEEDOR` varchar(150) DEFAULT NULL,
  `RACONCEPTO` varchar(300) DEFAULT NULL,
  `RACOSTO` float DEFAULT NULL,
  `RACLAVER` tinyint(1) DEFAULT NULL,
  `OPROVEEDOR` varchar(150) DEFAULT NULL,
  `OCONCEPTO` varchar(150) DEFAULT NULL,
  `OCOSTO` float DEFAULT NULL,
  `OCLAVER` tinyint(1) DEFAULT NULL,
  `COSTOINI` float NOT NULL,
  `COSTOFIN` float DEFAULT NULL,
  `PRECIOPUBLICO` float DEFAULT NULL,
  `DESCRIPCION` varchar(1000) DEFAULT NULL,
  `FLIMITE` varchar(15) DEFAULT NULL,
  `ESTATUS` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `privilegios`
--

CREATE TABLE `privilegios` (
  `IDPRIVILEGIO` int(11) NOT NULL,
  `GRUPO` varchar(15) NOT NULL,
  `ITEM` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `IDPROVEEDOR` int(11) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL,
  `TEL` varchar(15) NOT NULL,
  `EMAIL` varchar(150) NOT NULL,
  `WEB` varchar(150) NOT NULL,
  `CATEGORIA` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salidas`
--

CREATE TABLE `salidas` (
  `IDSALIDA` int(11) NOT NULL,
  `DESCRIPCION` varchar(1000) NOT NULL,
  `MONTO` float NOT NULL,
  `CATEGORIA` varchar(50) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `METODO` varchar(30) NOT NULL,
  `IDCAJA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `traslados`
--

CREATE TABLE `traslados` (
  `IDTRASLADO` int(11) NOT NULL,
  `NOMBRE` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `IDUSUARIO` int(11) NOT NULL,
  `NOMBRE` varchar(10) DEFAULT NULL,
  `PASSWORD` varchar(30) DEFAULT NULL,
  `PRIVILEGIOS` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vacaciones`
--

CREATE TABLE `vacaciones` (
  `IDVACACION` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FREG` varchar(15) NOT NULL,
  `FSALIDA` varchar(15) NOT NULL,
  `FREGRESO` varchar(15) NOT NULL,
  `DIAST` int(11) NOT NULL,
  `COMENTARIOS` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vacacionestab`
--

CREATE TABLE `vacacionestab` (
  `IDVACACIONTAB` int(11) NOT NULL,
  `DIAS` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viajeros`
--

CREATE TABLE `viajeros` (
  `IDVIAJERO` int(11) NOT NULL,
  `IDCONTRATO` int(11) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL,
  `CURP` varchar(30) NOT NULL,
  `FNACIMIENTO` varchar(15) NOT NULL,
  `EDAD` int(11) NOT NULL,
  `PASAPORTE` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `aerolineas`
--
ALTER TABLE `aerolineas`
  ADD PRIMARY KEY (`IDAEROLINEA`);

--
-- Indices de la tabla `cajas`
--
ALTER TABLE `cajas`
  ADD PRIMARY KEY (`IDCAJA`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`IDCLIENTE`);

--
-- Indices de la tabla `contratos`
--
ALTER TABLE `contratos`
  ADD PRIMARY KEY (`IDCONTRATO`);

--
-- Indices de la tabla `contratosd`
--
ALTER TABLE `contratosd`
  ADD PRIMARY KEY (`IDCONTRATOD`);

--
-- Indices de la tabla `cortes`
--
ALTER TABLE `cortes`
  ADD PRIMARY KEY (`IDCORTE`);

--
-- Indices de la tabla `cortesd`
--
ALTER TABLE `cortesd`
  ADD PRIMARY KEY (`IDCORTED`);

--
-- Indices de la tabla `destinos`
--
ALTER TABLE `destinos`
  ADD PRIMARY KEY (`IDDESTINO`);

--
-- Indices de la tabla `detalles`
--
ALTER TABLE `detalles`
  ADD PRIMARY KEY (`IDDETALLE`);

--
-- Indices de la tabla `documentos`
--
ALTER TABLE `documentos`
  ADD PRIMARY KEY (`IDDOC`);

--
-- Indices de la tabla `edetalles`
--
ALTER TABLE `edetalles`
  ADD PRIMARY KEY (`IDEDETALLE`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`IDEMPLEADO`);

--
-- Indices de la tabla `entradas`
--
ALTER TABLE `entradas`
  ADD PRIMARY KEY (`IDENTRADA`);

--
-- Indices de la tabla `hoteles`
--
ALTER TABLE `hoteles`
  ADD PRIMARY KEY (`IDHOTEL`);

--
-- Indices de la tabla `nominas`
--
ALTER TABLE `nominas`
  ADD PRIMARY KEY (`IDNOMINA`);

--
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD PRIMARY KEY (`IDPAGO`);

--
-- Indices de la tabla `pagosp`
--
ALTER TABLE `pagosp`
  ADD PRIMARY KEY (`IDPAGOP`);

--
-- Indices de la tabla `paquetes`
--
ALTER TABLE `paquetes`
  ADD PRIMARY KEY (`IDPAQUETE`);

--
-- Indices de la tabla `privilegios`
--
ALTER TABLE `privilegios`
  ADD PRIMARY KEY (`IDPRIVILEGIO`),
  ADD UNIQUE KEY `IDPRIVILEGIO` (`IDPRIVILEGIO`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`IDPROVEEDOR`);

--
-- Indices de la tabla `salidas`
--
ALTER TABLE `salidas`
  ADD PRIMARY KEY (`IDSALIDA`);

--
-- Indices de la tabla `traslados`
--
ALTER TABLE `traslados`
  ADD PRIMARY KEY (`IDTRASLADO`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`IDUSUARIO`);

--
-- Indices de la tabla `vacaciones`
--
ALTER TABLE `vacaciones`
  ADD PRIMARY KEY (`IDVACACION`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `aerolineas`
--
ALTER TABLE `aerolineas`
  MODIFY `IDAEROLINEA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cajas`
--
ALTER TABLE `cajas`
  MODIFY `IDCAJA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `IDCLIENTE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `contratos`
--
ALTER TABLE `contratos`
  MODIFY `IDCONTRATO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `contratosd`
--
ALTER TABLE `contratosd`
  MODIFY `IDCONTRATOD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cortesd`
--
ALTER TABLE `cortesd`
  MODIFY `IDCORTED` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `destinos`
--
ALTER TABLE `destinos`
  MODIFY `IDDESTINO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalles`
--
ALTER TABLE `detalles`
  MODIFY `IDDETALLE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `documentos`
--
ALTER TABLE `documentos`
  MODIFY `IDDOC` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `entradas`
--
ALTER TABLE `entradas`
  MODIFY `IDENTRADA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `hoteles`
--
ALTER TABLE `hoteles`
  MODIFY `IDHOTEL` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pagos`
--
ALTER TABLE `pagos`
  MODIFY `IDPAGO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pagosp`
--
ALTER TABLE `pagosp`
  MODIFY `IDPAGOP` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `paquetes`
--
ALTER TABLE `paquetes`
  MODIFY `IDPAQUETE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `privilegios`
--
ALTER TABLE `privilegios`
  MODIFY `IDPRIVILEGIO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `IDPROVEEDOR` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `salidas`
--
ALTER TABLE `salidas`
  MODIFY `IDSALIDA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `IDUSUARIO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vacaciones`
--
ALTER TABLE `vacaciones`
  MODIFY `IDVACACION` int(11) NOT NULL AUTO_INCREMENT;
--
-- Base de datos: `dhn`
--
CREATE DATABASE IF NOT EXISTS `dhn` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE `dhn`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `abonos`
--

CREATE TABLE `abonos` (
  `IDABONO` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `DESCRIPCION` varchar(150) NOT NULL,
  `MONTO` float NOT NULL,
  `PERIODO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ahorros`
--

CREATE TABLE `ahorros` (
  `IDAHORRO` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `MONTO` float NOT NULL,
  `PERIODO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `antenas`
--

CREATE TABLE `antenas` (
  `IDANTENA` int(11) NOT NULL,
  `MODELO` varchar(100) NOT NULL,
  `NOMBRE` varchar(100) DEFAULT NULL,
  `MARCA` varchar(45) DEFAULT NULL,
  `IP` varchar(30) DEFAULT NULL,
  `SITIO` varchar(100) DEFAULT NULL,
  `IPPADRE` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bitacora`
--

CREATE TABLE `bitacora` (
  `IDBITACORA` int(11) NOT NULL,
  `TABLA` varchar(30) NOT NULL,
  `REGISTRO` int(11) NOT NULL,
  `ACCION` varchar(30) NOT NULL,
  `IDUSER` int(11) NOT NULL,
  `COMENTARIOS` varchar(5000) NOT NULL,
  `REFERENCIA` varchar(5000) NOT NULL,
  `FECHA` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bonosd`
--

CREATE TABLE `bonosd` (
  `IDBONOD` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `IDPRESTACION` int(11) NOT NULL,
  `EMPRESA` varchar(30) NOT NULL,
  `MONTO` float NOT NULL,
  `FECHA` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cable`
--

CREATE TABLE `cable` (
  `IDCABLE` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `ENTREGADO` double NOT NULL,
  `USADO` double NOT NULL,
  `DEVUELTO` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cajas`
--

CREATE TABLE `cajas` (
  `IDCAJA` int(11) NOT NULL,
  `DESCRIPCION` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito`
--

CREATE TABLE `carrito` (
  `IDCARRITO` int(11) NOT NULL,
  `IDPENDIENTE` int(11) NOT NULL,
  `IDPRODUCTO` double NOT NULL,
  `CANTIDAD` int(11) NOT NULL,
  `TOTAL` double NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `ESTADO` varchar(30) NOT NULL,
  `FECHA` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `centrosp`
--

CREATE TABLE `centrosp` (
  `IDCENTROP` int(11) NOT NULL,
  `DESCRIPCION` varchar(100) NOT NULL,
  `UBICACION` varchar(100) NOT NULL,
  `ROL` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cf`
--

CREATE TABLE `cf` (
  `IDCF` int(11) NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `TIPO` varchar(15) NOT NULL,
  `MONTO` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `IDCLIENTE` int(11) NOT NULL,
  `NOMBRE` varchar(300) DEFAULT NULL,
  `TEL1` varchar(100) DEFAULT NULL,
  `TEL2` varchar(100) DEFAULT NULL,
  `TEL3` varchar(100) DEFAULT NULL,
  `IDENTIFICACION` varchar(30) DEFAULT NULL,
  `INE` varchar(1000) DEFAULT NULL,
  `CFE` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cobranza`
--

CREATE TABLE `cobranza` (
  `IDCOBRANZA` int(11) NOT NULL,
  `IDCONTRATO` int(11) NOT NULL,
  `COMENTARIOS` varchar(1000) DEFAULT NULL,
  `CATEGORIA` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contratos`
--

CREATE TABLE `contratos` (
  `IDCONTRATO` int(11) NOT NULL,
  `IDCLIENTE` int(11) DEFAULT NULL,
  `IDDIRECCION` int(11) DEFAULT NULL,
  `IDPAQUETE` int(11) DEFAULT NULL,
  `FREG` varchar(15) DEFAULT NULL,
  `FINS` varchar(15) DEFAULT NULL,
  `COMENTARIOS` varchar(1000) DEFAULT NULL,
  `ESTADO` varchar(15) DEFAULT NULL,
  `FCOR` varchar(15) DEFAULT NULL,
  `EQUIPO` varchar(15) DEFAULT NULL,
  `IP` varchar(30) DEFAULT NULL,
  `CONT` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cortes`
--

CREATE TABLE `cortes` (
  `IDCORTE` int(11) NOT NULL,
  `MONTOINI` float NOT NULL,
  `FINI` varchar(45) DEFAULT NULL,
  `FFIN` varchar(45) DEFAULT NULL,
  `MONTOFIN` float DEFAULT NULL,
  `IDCAJA` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cortesd`
--

CREATE TABLE `cortesd` (
  `IDCORTED` int(11) NOT NULL,
  `IDCORTE` int(11) DEFAULT NULL,
  `IDPAGOI` int(11) DEFAULT NULL,
  `IDPAGOF` int(11) DEFAULT NULL,
  `IDENTRADAI` int(11) DEFAULT NULL,
  `IDENTRADAF` int(11) DEFAULT NULL,
  `IDSALIDAI` int(11) DEFAULT NULL,
  `IDSALIDAF` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cortesg`
--

CREATE TABLE `cortesg` (
  `IDCORTEG` int(11) NOT NULL,
  `DHN` float NOT NULL,
  `EDH` float NOT NULL,
  `VALES` float NOT NULL,
  `CF` float NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `MONTOG` float NOT NULL,
  `MONTOR` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cotizaciones`
--

CREATE TABLE `cotizaciones` (
  `IDCOTIZACION` int(11) NOT NULL,
  `IDPENDIENTE` int(11) NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `CATEGORIA` varchar(50) NOT NULL,
  `COMENTARIOS` varchar(2000) NOT NULL,
  `DOC` varchar(500) NOT NULL,
  `ESTADO` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas`
--

CREATE TABLE `cuentas` (
  `IDCUENTA` int(11) NOT NULL,
  `BANCO` varchar(50) NOT NULL,
  `NCUENTA` varchar(100) NOT NULL,
  `TIPO` varchar(50) NOT NULL,
  `ALIAS` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cutservices`
--

CREATE TABLE `cutservices` (
  `FECHA` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalles`
--

CREATE TABLE `detalles` (
  `IDDETALLE` int(11) NOT NULL,
  `DIASPRE` int(11) NOT NULL,
  `CONCEPTO` varchar(150) NOT NULL,
  `SMINIMO` float NOT NULL,
  `TC` float NOT NULL,
  `IVA` float NOT NULL,
  `GP` double NOT NULL,
  `GI` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallesdisp`
--

CREATE TABLE `detallesdisp` (
  `IDD` int(11) NOT NULL,
  `TABLA` varchar(30) NOT NULL,
  `CATEGORIA` varchar(30) NOT NULL,
  `DESCRIPCION` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direcciones`
--

CREATE TABLE `direcciones` (
  `IDDIRECCION` int(11) NOT NULL,
  `IDCLIENTE` int(11) DEFAULT NULL,
  `CALLE` varchar(100) DEFAULT NULL,
  `NUMERO` varchar(50) DEFAULT NULL,
  `COLONIA` varchar(100) DEFAULT NULL,
  `LOCALIDAD` varchar(100) DEFAULT NULL,
  `LATITUD` varchar(2000) DEFAULT NULL,
  `LONGITUD` varchar(150) DEFAULT NULL,
  `REFERENCIA` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `econtactos`
--

CREATE TABLE `econtactos` (
  `IDECONTACTO` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `TEL` varchar(20) NOT NULL,
  `EMAIL` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `edetalles`
--

CREATE TABLE `edetalles` (
  `IDEDETALLE` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `PINI` varchar(15) NOT NULL,
  `PFIN` varchar(15) NOT NULL,
  `VPAGADAS` tinyint(1) NOT NULL,
  `APAGADOS` tinyint(1) NOT NULL,
  `PERIODOV` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `edocs`
--

CREATE TABLE `edocs` (
  `IDEDOC` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `NUMBER` varchar(30) NOT NULL,
  `DESCRIPCION` varchar(30) NOT NULL,
  `VIGENCIA` varchar(50) NOT NULL,
  `DOC` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `IDEMPLEADO` int(11) NOT NULL,
  `NOMBRE` varchar(50) DEFAULT NULL,
  `APELLIDOS` varchar(50) DEFAULT NULL,
  `TEL` varchar(20) DEFAULT NULL,
  `SUELDO` float NOT NULL,
  `EMPRESA` varchar(60) DEFAULT NULL,
  `BONO` float NOT NULL,
  `BONOP` float NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `ESTADO` varchar(30) DEFAULT NULL,
  `IDEPUESTO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entradas`
--

CREATE TABLE `entradas` (
  `IDENTRADA` int(11) NOT NULL,
  `DESCRIPCION` varchar(1000) DEFAULT NULL,
  `MONTO` float DEFAULT NULL,
  `CATEGORIA` varchar(30) DEFAULT NULL,
  `FECHA` varchar(45) DEFAULT NULL,
  `METODO` varchar(15) DEFAULT NULL,
  `IDCAJA` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entradascaja`
--

CREATE TABLE `entradascaja` (
  `IDENTRADACAJA` int(11) NOT NULL,
  `DESCRIPCION` varchar(300) NOT NULL,
  `MONTO` double NOT NULL,
  `EMPRESA` varchar(50) NOT NULL,
  `FECHA` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `epersonales`
--

CREATE TABLE `epersonales` (
  `IDEPERSONAL` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FNACIMIENTO` varchar(10) NOT NULL,
  `SEXO` varchar(10) NOT NULL,
  `EDOCIVIL` varchar(10) NOT NULL,
  `NACIONALIDAD` varchar(15) NOT NULL,
  `CURP` varchar(20) NOT NULL,
  `RFC` varchar(15) NOT NULL,
  `NSS` varchar(15) NOT NULL,
  `CALLE` varchar(150) NOT NULL,
  `NUMERO` varchar(15) NOT NULL,
  `COLONIA` varchar(150) NOT NULL,
  `LOCALIDAD` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `epuestos`
--

CREATE TABLE `epuestos` (
  `IDEPUESTO` int(11) NOT NULL,
  `DEPTO` varchar(50) NOT NULL,
  `PUESTO` varchar(50) NOT NULL,
  `DOC` varchar(1000) NOT NULL,
  `SUELDO` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `extras`
--

CREATE TABLE `extras` (
  `IDEXTRA` int(11) NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `DESCRIPCION` varchar(1000) NOT NULL,
  `TIPO` varchar(30) NOT NULL,
  `MONTO` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `finiquito`
--

CREATE TABLE `finiquito` (
  `IDFINIQUITO` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FINI` varchar(15) NOT NULL,
  `FFIN` varchar(15) NOT NULL,
  `MOTIVO` varchar(30) NOT NULL,
  `SUELDO` double NOT NULL,
  `SDIARIO` double NOT NULL,
  `DRESTANTES` int(11) NOT NULL,
  `SRESTANTE` double NOT NULL,
  `PRESTAMOS` double NOT NULL,
  `ABONOS` double NOT NULL,
  `AHORROS` double NOT NULL,
  `SALDO` double NOT NULL,
  `DVACA` int(11) NOT NULL,
  `MONTOVACA` double NOT NULL,
  `PRIMAVACA` double NOT NULL,
  `AGUINALDO` double NOT NULL,
  `MONTO` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `kits`
--

CREATE TABLE `kits` (
  `IDKIT` int(11) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL,
  `CATEGORIA` varchar(50) NOT NULL,
  `COSTO` float NOT NULL,
  `PRECIO` float NOT NULL,
  `FOTO` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `kitscategorias`
--

CREATE TABLE `kitscategorias` (
  `CATEGORIA` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `kitsd`
--

CREATE TABLE `kitsd` (
  `IDKITD` int(11) NOT NULL,
  `IDKIT` int(11) NOT NULL,
  `IDPRODUCTO` int(11) NOT NULL,
  `CANTIDAD` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `motivosr`
--

CREATE TABLE `motivosr` (
  `IDMOTIVOR` int(11) NOT NULL,
  `IDNOTA` int(11) NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `MOTIVOS` varchar(1500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movd`
--

CREATE TABLE `movd` (
  `IDMOVD` int(11) NOT NULL,
  `MOVIMIENTO` varchar(30) NOT NULL,
  `IDMOVIMIENTO` int(11) NOT NULL,
  `REF` varchar(1000) NOT NULL,
  `COTEJADO` tinyint(1) NOT NULL,
  `FAC` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nominas`
--

CREATE TABLE `nominas` (
  `IDNOMINA` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `SEMANA` int(11) NOT NULL,
  `ANO` int(11) NOT NULL,
  `DIAS` float NOT NULL,
  `NSUELDO` double NOT NULL,
  `BONO` double NOT NULL,
  `CONCEPTO` varchar(300) NOT NULL,
  `IDAHORRO` int(11) NOT NULL,
  `IDABONO` int(11) NOT NULL,
  `TOTAL` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nominasd`
--

CREATE TABLE `nominasd` (
  `IDNOMINAD` int(11) NOT NULL,
  `IDNOMINA` int(11) NOT NULL,
  `PBP` int(11) NOT NULL,
  `RTT` int(11) NOT NULL,
  `RFTT` int(11) NOT NULL,
  `RJ` int(11) NOT NULL,
  `F` int(11) NOT NULL,
  `FJ` int(11) NOT NULL,
  `PSGS` int(11) NOT NULL,
  `PCGS` int(11) NOT NULL,
  `FAS` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordenes`
--

CREATE TABLE `ordenes` (
  `IDORDEN` int(11) NOT NULL,
  `IDEMPLEADO` int(11) DEFAULT NULL,
  `IDCONTRATO` int(11) DEFAULT NULL,
  `FINI` varchar(15) DEFAULT NULL,
  `FFIN` varchar(15) DEFAULT NULL,
  `ESTADO` varchar(20) DEFAULT NULL,
  `COMENTARIOS` varchar(5000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordenesp`
--

CREATE TABLE `ordenesp` (
  `IDORDENP` int(11) NOT NULL,
  `IDEMPLEADO` int(11) DEFAULT NULL,
  `IDPENDIENTE` int(11) DEFAULT NULL,
  `FINI` varchar(15) DEFAULT NULL,
  `FFIN` varchar(15) DEFAULT NULL,
  `ESTADO` varchar(20) DEFAULT NULL,
  `COMENTARIOS` varchar(5000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `IDNOTA` int(11) NOT NULL,
  `IDCLIENTE` int(11) DEFAULT NULL,
  `IDCONTRATO` int(11) DEFAULT NULL,
  `FECHA` varchar(50) DEFAULT NULL,
  `METODO` varchar(100) DEFAULT NULL,
  `MONTO` float DEFAULT NULL,
  `DOC` varchar(1000) DEFAULT NULL,
  `REFERENCIA` varchar(199) DEFAULT NULL,
  `COMENTARIOS` varchar(2000) DEFAULT NULL,
  `IDCAJA` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagosprogramados`
--

CREATE TABLE `pagosprogramados` (
  `IDPAGOPROGRAMADO` int(11) NOT NULL,
  `IDPROVEEDOR` int(11) NOT NULL,
  `FPRO` varchar(15) NOT NULL,
  `MONTO` float NOT NULL,
  `CONCEPTO` varchar(150) NOT NULL,
  `RECURRENCIA` int(11) NOT NULL,
  `ESTADO` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagosproveedores`
--

CREATE TABLE `pagosproveedores` (
  `IDPAGOPROV` int(11) NOT NULL,
  `IDPROVEEDOR` int(11) NOT NULL,
  `FECHA` varchar(100) NOT NULL,
  `IDPENDIENTE` int(11) NOT NULL,
  `CARGO` float NOT NULL,
  `ABONO` float NOT NULL,
  `SALDO` float NOT NULL,
  `DESCRIPCION` varchar(500) NOT NULL,
  `ESTADO` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paquetes`
--

CREATE TABLE `paquetes` (
  `IDPAQUETE` int(11) NOT NULL,
  `COSTO` int(11) DEFAULT NULL,
  `DESCRIPCION` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pdocs`
--

CREATE TABLE `pdocs` (
  `IDPDOC` int(11) NOT NULL,
  `IDPENDIENTE` int(11) NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `DOC` varchar(1000) NOT NULL,
  `COMENTARIOS` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pendientes`
--

CREATE TABLE `pendientes` (
  `IDPENDIENTE` int(11) NOT NULL,
  `IDCLIENTE` int(11) DEFAULT NULL,
  `IDDIRECCION` int(11) DEFAULT NULL,
  `NPEDIDO` varchar(10) DEFAULT NULL,
  `NPEDIDOF` varchar(10) DEFAULT NULL,
  `CATEGORIA` varchar(50) DEFAULT NULL,
  `SUBCATEGORIA` varchar(50) DEFAULT NULL,
  `DESCRIPCION` varchar(5000) DEFAULT NULL,
  `COMENTARIOS` varchar(5000) DEFAULT NULL,
  `FREG` varchar(30) DEFAULT NULL,
  `FEST` varchar(30) DEFAULT NULL,
  `FFIN` varchar(30) DEFAULT NULL,
  `ESTADO` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `pendientes_abierto`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `pendientes_abierto` (
`IDPENDIENTE` int(11)
,`NOMBRE` varchar(300)
,`TEL1` varchar(100)
,`CALLE` varchar(100)
,`NUMERO` varchar(50)
,`COLONIA` varchar(100)
,`LOCALIDAD` varchar(100)
,`LATITUD` varchar(2000)
,`LONGITUD` varchar(150)
,`CATEGORIA` varchar(50)
,`SUBCATEGORIA` varchar(50)
,`DESCRIPCION` varchar(5000)
,`COMENTARIOS` varchar(5000)
,`FREG` varchar(30)
,`FEST` varchar(30)
,`FFIN` varchar(30)
,`NPEDIDO` varchar(10)
,`NPEDIDOF` varchar(10)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestaciones`
--

CREATE TABLE `prestaciones` (
  `IDPRESTACION` int(11) NOT NULL,
  `CONCEPTO` varchar(30) NOT NULL,
  `PERIODO` int(11) NOT NULL,
  `FINI` varchar(15) NOT NULL,
  `FFIN` varchar(15) NOT NULL,
  `ESTADO` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestacionesp`
--

CREATE TABLE `prestacionesp` (
  `IDPRESTACIONP` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `IDPRESTACION` int(11) NOT NULL,
  `FREG` varchar(15) NOT NULL,
  `MONTO` double NOT NULL,
  `PERIODO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamos`
--

CREATE TABLE `prestamos` (
  `IDPRESTAMO` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `DESCRIPCION` varchar(150) NOT NULL,
  `MONTO` float NOT NULL,
  `PERIODO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `privilegios`
--

CREATE TABLE `privilegios` (
  `IDPRIVILEGIO` int(11) NOT NULL,
  `GRUPO` varchar(15) NOT NULL,
  `ITEM` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `IDPRODUCTO` int(11) NOT NULL,
  `SICAR` varchar(50) NOT NULL,
  `CATEGORIA` varchar(50) NOT NULL,
  `DESCRIPCION` varchar(1000) NOT NULL,
  `COSTO` double NOT NULL,
  `MONEDA` varchar(5) NOT NULL,
  `IVA` tinyint(1) NOT NULL,
  `PP` double NOT NULL,
  `PI` double NOT NULL,
  `KIT` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productosd`
--

CREATE TABLE `productosd` (
  `CB` varchar(50) NOT NULL,
  `IDPRODUCTO` int(11) NOT NULL,
  `CLAVESAT` varchar(45) NOT NULL,
  `INVENTARIO` int(11) NOT NULL,
  `ESTADO` varchar(15) NOT NULL,
  `FOTO` varchar(150) NOT NULL,
  `MARCA` varchar(50) NOT NULL,
  `PROVEEDOR` varchar(100) NOT NULL,
  `SKU` varchar(50) NOT NULL,
  `CPP` tinyint(1) NOT NULL,
  `CPI` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `IDPROVEEDOR` int(11) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `TEL1` varchar(15) NOT NULL,
  `TEL2` varchar(15) NOT NULL,
  `REFERENCIA` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reportes`
--

CREATE TABLE `reportes` (
  `IDREPORTE` int(11) NOT NULL,
  `IDCONTRATO` int(11) DEFAULT NULL,
  `DESCRIPCION` varchar(1000) DEFAULT NULL,
  `FABIERTO` varchar(30) DEFAULT NULL,
  `FCERRADO` varchar(30) DEFAULT NULL,
  `ESTATUS` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salidas`
--

CREATE TABLE `salidas` (
  `IDSALIDA` int(11) NOT NULL,
  `DESCRIPCION` varchar(1000) DEFAULT NULL,
  `MONTO` float DEFAULT NULL,
  `CATEGORIA` varchar(30) DEFAULT NULL,
  `FECHA` varchar(45) DEFAULT NULL,
  `METODO` varchar(15) DEFAULT NULL,
  `IDCAJA` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salidascaja`
--

CREATE TABLE `salidascaja` (
  `IDSALIDACAJA` int(11) NOT NULL,
  `DESCRIPCION` varchar(300) NOT NULL,
  `MONTO` double NOT NULL,
  `EMPRESA` varchar(50) NOT NULL,
  `FECHA` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `IDUSUARIO` int(11) NOT NULL,
  `NOMBRE` varchar(10) DEFAULT NULL,
  `PASSWORD` varchar(100) DEFAULT NULL,
  `PRIVILEGIOS` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vacaciones`
--

CREATE TABLE `vacaciones` (
  `IDVACACION` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FREG` varchar(15) NOT NULL,
  `FSALIDA` varchar(15) NOT NULL,
  `FREGRESO` varchar(15) NOT NULL,
  `DIAST` int(11) NOT NULL,
  `COMENTARIOS` varchar(150) NOT NULL,
  `PERIODO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vacacionestab`
--

CREATE TABLE `vacacionestab` (
  `IDVACACIONTAB` int(11) NOT NULL,
  `DIAS` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vales`
--

CREATE TABLE `vales` (
  `IDVALE` int(11) NOT NULL,
  `FECHA` varchar(50) NOT NULL,
  `DESCRIPCION` varchar(500) NOT NULL,
  `TIPO` varchar(15) NOT NULL,
  `MONTO` float NOT NULL,
  `ESTADO` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculos`
--

CREATE TABLE `vehiculos` (
  `IDVEHICULO` int(11) NOT NULL,
  `MARCA` varchar(30) NOT NULL,
  `MODELO` varchar(30) NOT NULL,
  `NS` varchar(100) NOT NULL,
  `NMOTOR` varchar(50) NOT NULL,
  `PLACA` varchar(15) NOT NULL,
  `EMPRESA` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculosseg`
--

CREATE TABLE `vehiculosseg` (
  `IDVEHICULOSEG` int(11) NOT NULL,
  `IDVEHICULO` int(11) NOT NULL,
  `ASEGURADORA` varchar(30) NOT NULL,
  `NP` varchar(30) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `VIGENCIA` varchar(15) NOT NULL,
  `ARCHIVO` varchar(1000) NOT NULL,
  `PROPIETARIO` varchar(150) NOT NULL,
  `COSTO` float NOT NULL,
  `EMPRESA` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculosser`
--

CREATE TABLE `vehiculosser` (
  `IDVEHICULOSER` int(11) NOT NULL,
  `IDVEHICULO` int(11) NOT NULL,
  `TIPO` varchar(50) NOT NULL,
  `COMENTARIOS` varchar(1000) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `KM` int(11) NOT NULL,
  `EMPRESA` varchar(50) NOT NULL,
  `COSTO` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura para la vista `pendientes_abierto`
--
DROP TABLE IF EXISTS `pendientes_abierto`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `pendientes_abierto`  AS SELECT `pendientes`.`IDPENDIENTE` AS `IDPENDIENTE`, `clientes`.`NOMBRE` AS `NOMBRE`, `clientes`.`TEL1` AS `TEL1`, `direcciones`.`CALLE` AS `CALLE`, `direcciones`.`NUMERO` AS `NUMERO`, `direcciones`.`COLONIA` AS `COLONIA`, `direcciones`.`LOCALIDAD` AS `LOCALIDAD`, `direcciones`.`LATITUD` AS `LATITUD`, `direcciones`.`LONGITUD` AS `LONGITUD`, `pendientes`.`CATEGORIA` AS `CATEGORIA`, `pendientes`.`SUBCATEGORIA` AS `SUBCATEGORIA`, `pendientes`.`DESCRIPCION` AS `DESCRIPCION`, `pendientes`.`COMENTARIOS` AS `COMENTARIOS`, `pendientes`.`FREG` AS `FREG`, `pendientes`.`FEST` AS `FEST`, `pendientes`.`FFIN` AS `FFIN`, `pendientes`.`NPEDIDO` AS `NPEDIDO`, `pendientes`.`NPEDIDOF` AS `NPEDIDOF` FROM ((`pendientes` join `clientes` on(`pendientes`.`IDCLIENTE` = `clientes`.`IDCLIENTE`)) join `direcciones` on(`pendientes`.`IDDIRECCION` = `direcciones`.`IDDIRECCION`)) WHERE `pendientes`.`ESTADO` = 'ABIERTO' ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `abonos`
--
ALTER TABLE `abonos`
  ADD PRIMARY KEY (`IDABONO`),
  ADD KEY `IDEMPLEADO` (`IDEMPLEADO`);

--
-- Indices de la tabla `ahorros`
--
ALTER TABLE `ahorros`
  ADD PRIMARY KEY (`IDAHORRO`),
  ADD KEY `IDEMPLEADO` (`IDEMPLEADO`);

--
-- Indices de la tabla `antenas`
--
ALTER TABLE `antenas`
  ADD PRIMARY KEY (`IDANTENA`);

--
-- Indices de la tabla `bitacora`
--
ALTER TABLE `bitacora`
  ADD PRIMARY KEY (`IDBITACORA`);

--
-- Indices de la tabla `bonosd`
--
ALTER TABLE `bonosd`
  ADD PRIMARY KEY (`IDBONOD`);

--
-- Indices de la tabla `cable`
--
ALTER TABLE `cable`
  ADD PRIMARY KEY (`IDCABLE`);

--
-- Indices de la tabla `cajas`
--
ALTER TABLE `cajas`
  ADD PRIMARY KEY (`IDCAJA`);

--
-- Indices de la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD PRIMARY KEY (`IDCARRITO`);

--
-- Indices de la tabla `centrosp`
--
ALTER TABLE `centrosp`
  ADD PRIMARY KEY (`IDCENTROP`);

--
-- Indices de la tabla `cf`
--
ALTER TABLE `cf`
  ADD PRIMARY KEY (`IDCF`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`IDCLIENTE`),
  ADD UNIQUE KEY `IDCLIENTE` (`IDCLIENTE`),
  ADD KEY `IDCLIENTE_2` (`IDCLIENTE`);

--
-- Indices de la tabla `cobranza`
--
ALTER TABLE `cobranza`
  ADD PRIMARY KEY (`IDCOBRANZA`);

--
-- Indices de la tabla `contratos`
--
ALTER TABLE `contratos`
  ADD PRIMARY KEY (`IDCONTRATO`);

--
-- Indices de la tabla `cortes`
--
ALTER TABLE `cortes`
  ADD PRIMARY KEY (`IDCORTE`);

--
-- Indices de la tabla `cortesd`
--
ALTER TABLE `cortesd`
  ADD PRIMARY KEY (`IDCORTED`);

--
-- Indices de la tabla `cortesg`
--
ALTER TABLE `cortesg`
  ADD PRIMARY KEY (`IDCORTEG`);

--
-- Indices de la tabla `cotizaciones`
--
ALTER TABLE `cotizaciones`
  ADD PRIMARY KEY (`IDCOTIZACION`);

--
-- Indices de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  ADD PRIMARY KEY (`IDCUENTA`);

--
-- Indices de la tabla `cutservices`
--
ALTER TABLE `cutservices`
  ADD PRIMARY KEY (`FECHA`);

--
-- Indices de la tabla `detalles`
--
ALTER TABLE `detalles`
  ADD PRIMARY KEY (`IDDETALLE`);

--
-- Indices de la tabla `detallesdisp`
--
ALTER TABLE `detallesdisp`
  ADD PRIMARY KEY (`IDD`);

--
-- Indices de la tabla `direcciones`
--
ALTER TABLE `direcciones`
  ADD PRIMARY KEY (`IDDIRECCION`),
  ADD KEY `IDCLIENTE` (`IDCLIENTE`);

--
-- Indices de la tabla `econtactos`
--
ALTER TABLE `econtactos`
  ADD PRIMARY KEY (`IDECONTACTO`);

--
-- Indices de la tabla `edetalles`
--
ALTER TABLE `edetalles`
  ADD PRIMARY KEY (`IDEDETALLE`);

--
-- Indices de la tabla `edocs`
--
ALTER TABLE `edocs`
  ADD PRIMARY KEY (`IDEDOC`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`IDEMPLEADO`);

--
-- Indices de la tabla `entradas`
--
ALTER TABLE `entradas`
  ADD PRIMARY KEY (`IDENTRADA`);

--
-- Indices de la tabla `entradascaja`
--
ALTER TABLE `entradascaja`
  ADD PRIMARY KEY (`IDENTRADACAJA`);

--
-- Indices de la tabla `epuestos`
--
ALTER TABLE `epuestos`
  ADD PRIMARY KEY (`IDEPUESTO`);

--
-- Indices de la tabla `extras`
--
ALTER TABLE `extras`
  ADD PRIMARY KEY (`IDEXTRA`);

--
-- Indices de la tabla `finiquito`
--
ALTER TABLE `finiquito`
  ADD PRIMARY KEY (`IDFINIQUITO`);

--
-- Indices de la tabla `kits`
--
ALTER TABLE `kits`
  ADD PRIMARY KEY (`IDKIT`);

--
-- Indices de la tabla `kitsd`
--
ALTER TABLE `kitsd`
  ADD PRIMARY KEY (`IDKITD`);

--
-- Indices de la tabla `motivosr`
--
ALTER TABLE `motivosr`
  ADD PRIMARY KEY (`IDMOTIVOR`);

--
-- Indices de la tabla `movd`
--
ALTER TABLE `movd`
  ADD PRIMARY KEY (`IDMOVD`);

--
-- Indices de la tabla `nominas`
--
ALTER TABLE `nominas`
  ADD PRIMARY KEY (`IDNOMINA`),
  ADD KEY `IDEMPLEADO` (`IDEMPLEADO`);

--
-- Indices de la tabla `nominasd`
--
ALTER TABLE `nominasd`
  ADD PRIMARY KEY (`IDNOMINAD`);

--
-- Indices de la tabla `ordenes`
--
ALTER TABLE `ordenes`
  ADD PRIMARY KEY (`IDORDEN`);

--
-- Indices de la tabla `ordenesp`
--
ALTER TABLE `ordenesp`
  ADD PRIMARY KEY (`IDORDENP`),
  ADD KEY `IDPENDIENTE` (`IDPENDIENTE`),
  ADD KEY `IDEMPLEADO` (`IDEMPLEADO`);

--
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD PRIMARY KEY (`IDNOTA`);

--
-- Indices de la tabla `pagosprogramados`
--
ALTER TABLE `pagosprogramados`
  ADD PRIMARY KEY (`IDPAGOPROGRAMADO`);

--
-- Indices de la tabla `paquetes`
--
ALTER TABLE `paquetes`
  ADD PRIMARY KEY (`IDPAQUETE`);

--
-- Indices de la tabla `pdocs`
--
ALTER TABLE `pdocs`
  ADD PRIMARY KEY (`IDPDOC`);

--
-- Indices de la tabla `pendientes`
--
ALTER TABLE `pendientes`
  ADD PRIMARY KEY (`IDPENDIENTE`);

--
-- Indices de la tabla `prestaciones`
--
ALTER TABLE `prestaciones`
  ADD PRIMARY KEY (`IDPRESTACION`);

--
-- Indices de la tabla `prestacionesp`
--
ALTER TABLE `prestacionesp`
  ADD PRIMARY KEY (`IDPRESTACIONP`);

--
-- Indices de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD PRIMARY KEY (`IDPRESTAMO`),
  ADD KEY `IDEMPLEADO` (`IDEMPLEADO`);

--
-- Indices de la tabla `privilegios`
--
ALTER TABLE `privilegios`
  ADD PRIMARY KEY (`IDPRIVILEGIO`),
  ADD UNIQUE KEY `IDPRIVILEGIO` (`IDPRIVILEGIO`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`IDPRODUCTO`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`IDPROVEEDOR`);

--
-- Indices de la tabla `reportes`
--
ALTER TABLE `reportes`
  ADD PRIMARY KEY (`IDREPORTE`);

--
-- Indices de la tabla `salidas`
--
ALTER TABLE `salidas`
  ADD PRIMARY KEY (`IDSALIDA`);

--
-- Indices de la tabla `salidascaja`
--
ALTER TABLE `salidascaja`
  ADD UNIQUE KEY `IDSALIDACAJA` (`IDSALIDACAJA`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`IDUSUARIO`);

--
-- Indices de la tabla `vacaciones`
--
ALTER TABLE `vacaciones`
  ADD PRIMARY KEY (`IDVACACION`);

--
-- Indices de la tabla `vacacionestab`
--
ALTER TABLE `vacacionestab`
  ADD PRIMARY KEY (`IDVACACIONTAB`);

--
-- Indices de la tabla `vales`
--
ALTER TABLE `vales`
  ADD PRIMARY KEY (`IDVALE`);

--
-- Indices de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  ADD PRIMARY KEY (`IDVEHICULO`);

--
-- Indices de la tabla `vehiculosseg`
--
ALTER TABLE `vehiculosseg`
  ADD PRIMARY KEY (`IDVEHICULOSEG`);

--
-- Indices de la tabla `vehiculosser`
--
ALTER TABLE `vehiculosser`
  ADD PRIMARY KEY (`IDVEHICULOSER`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `abonos`
--
ALTER TABLE `abonos`
  MODIFY `IDABONO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `antenas`
--
ALTER TABLE `antenas`
  MODIFY `IDANTENA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `bitacora`
--
ALTER TABLE `bitacora`
  MODIFY `IDBITACORA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `bonosd`
--
ALTER TABLE `bonosd`
  MODIFY `IDBONOD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cable`
--
ALTER TABLE `cable`
  MODIFY `IDCABLE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cajas`
--
ALTER TABLE `cajas`
  MODIFY `IDCAJA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `carrito`
--
ALTER TABLE `carrito`
  MODIFY `IDCARRITO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `centrosp`
--
ALTER TABLE `centrosp`
  MODIFY `IDCENTROP` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cf`
--
ALTER TABLE `cf`
  MODIFY `IDCF` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cobranza`
--
ALTER TABLE `cobranza`
  MODIFY `IDCOBRANZA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cortesd`
--
ALTER TABLE `cortesd`
  MODIFY `IDCORTED` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cortesg`
--
ALTER TABLE `cortesg`
  MODIFY `IDCORTEG` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cotizaciones`
--
ALTER TABLE `cotizaciones`
  MODIFY `IDCOTIZACION` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  MODIFY `IDCUENTA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalles`
--
ALTER TABLE `detalles`
  MODIFY `IDDETALLE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detallesdisp`
--
ALTER TABLE `detallesdisp`
  MODIFY `IDD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `econtactos`
--
ALTER TABLE `econtactos`
  MODIFY `IDECONTACTO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `edocs`
--
ALTER TABLE `edocs`
  MODIFY `IDEDOC` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `entradascaja`
--
ALTER TABLE `entradascaja`
  MODIFY `IDENTRADACAJA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `epuestos`
--
ALTER TABLE `epuestos`
  MODIFY `IDEPUESTO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `extras`
--
ALTER TABLE `extras`
  MODIFY `IDEXTRA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `finiquito`
--
ALTER TABLE `finiquito`
  MODIFY `IDFINIQUITO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `kits`
--
ALTER TABLE `kits`
  MODIFY `IDKIT` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `kitsd`
--
ALTER TABLE `kitsd`
  MODIFY `IDKITD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `motivosr`
--
ALTER TABLE `motivosr`
  MODIFY `IDMOTIVOR` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `movd`
--
ALTER TABLE `movd`
  MODIFY `IDMOVD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `nominasd`
--
ALTER TABLE `nominasd`
  MODIFY `IDNOMINAD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pagosprogramados`
--
ALTER TABLE `pagosprogramados`
  MODIFY `IDPAGOPROGRAMADO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pdocs`
--
ALTER TABLE `pdocs`
  MODIFY `IDPDOC` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `prestaciones`
--
ALTER TABLE `prestaciones`
  MODIFY `IDPRESTACION` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `prestacionesp`
--
ALTER TABLE `prestacionesp`
  MODIFY `IDPRESTACIONP` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `privilegios`
--
ALTER TABLE `privilegios`
  MODIFY `IDPRIVILEGIO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `IDPRODUCTO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `IDPROVEEDOR` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `salidascaja`
--
ALTER TABLE `salidascaja`
  MODIFY `IDSALIDACAJA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `IDUSUARIO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vacaciones`
--
ALTER TABLE `vacaciones`
  MODIFY `IDVACACION` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vales`
--
ALTER TABLE `vales`
  MODIFY `IDVALE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  MODIFY `IDVEHICULO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vehiculosseg`
--
ALTER TABLE `vehiculosseg`
  MODIFY `IDVEHICULOSEG` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vehiculosser`
--
ALTER TABLE `vehiculosser`
  MODIFY `IDVEHICULOSER` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `abonos`
--
ALTER TABLE `abonos`
  ADD CONSTRAINT `abonos_ibfk_1` FOREIGN KEY (`IDEMPLEADO`) REFERENCES `empleados` (`IDEMPLEADO`);

--
-- Filtros para la tabla `ahorros`
--
ALTER TABLE `ahorros`
  ADD CONSTRAINT `ahorros_ibfk_1` FOREIGN KEY (`IDEMPLEADO`) REFERENCES `empleados` (`IDEMPLEADO`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `direcciones`
--
ALTER TABLE `direcciones`
  ADD CONSTRAINT `direcciones_ibfk_1` FOREIGN KEY (`IDCLIENTE`) REFERENCES `clientes` (`IDCLIENTE`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `nominas`
--
ALTER TABLE `nominas`
  ADD CONSTRAINT `nominas_ibfk_1` FOREIGN KEY (`IDEMPLEADO`) REFERENCES `empleados` (`IDEMPLEADO`);

--
-- Filtros para la tabla `ordenesp`
--
ALTER TABLE `ordenesp`
  ADD CONSTRAINT `ordenesp_ibfk_1` FOREIGN KEY (`IDPENDIENTE`) REFERENCES `pendientes` (`IDPENDIENTE`),
  ADD CONSTRAINT `ordenesp_ibfk_2` FOREIGN KEY (`IDEMPLEADO`) REFERENCES `empleados` (`IDEMPLEADO`);

--
-- Filtros para la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`IDEMPLEADO`) REFERENCES `empleados` (`IDEMPLEADO`);
--
-- Base de datos: `gventas`
--
CREATE DATABASE IF NOT EXISTS `gventas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `gventas`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gv_almacen`
--

CREATE TABLE `gv_almacen` (
  `ida` int(11) NOT NULL,
  `nombre` varchar(125) NOT NULL,
  `direccion` varchar(125) NOT NULL,
  `encargado` int(11) NOT NULL,
  `activo` int(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gv_almaceproduct`
--

CREATE TABLE `gv_almaceproduct` (
  `idpa` bigint(20) NOT NULL,
  `idalmacen` int(11) NOT NULL,
  `idproducto` bigint(20) NOT NULL,
  `cantidad_actual` int(11) NOT NULL,
  `cantidad_inicial` int(11) NOT NULL,
  `tipo_manipulacion` enum('VENTA','COMPRA','TRANSLADO','REGISTRO') NOT NULL,
  `fecha` date DEFAULT NULL,
  `activo` int(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='almacen producto';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gv_asignarauth`
--

CREATE TABLE `gv_asignarauth` (
  `nombreitem` varchar(64) NOT NULL,
  `iduser` varchar(64) NOT NULL,
  `bizrule` text DEFAULT NULL,
  `dato` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gv_authitem`
--

CREATE TABLE `gv_authitem` (
  `nombre` varchar(64) NOT NULL,
  `tipo` int(11) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `bizrule` text DEFAULT NULL,
  `dato` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gv_authitemchild`
--

CREATE TABLE `gv_authitemchild` (
  `padre` varchar(64) NOT NULL,
  `hijo` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gv_bitacora`
--

CREATE TABLE `gv_bitacora` (
  `idbitacora` bigint(20) NOT NULL,
  `idusuario` int(11) NOT NULL,
  `OS` varchar(80) DEFAULT NULL,
  `arquitectura` varchar(80) DEFAULT NULL,
  `verssion` varchar(50) DEFAULT NULL,
  `nomCuenta` varchar(200) DEFAULT NULL COMMENT 'nombre de usuaio en el sistema operativo',
  `ultima_actividad` bigint(20) NOT NULL,
  `fecha_actividad` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gv_clase`
--

CREATE TABLE `gv_clase` (
  `idclase` int(11) NOT NULL,
  `nombre` varchar(125) NOT NULL,
  `activo` int(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gv_cliente`
--

CREATE TABLE `gv_cliente` (
  `idc` bigint(20) NOT NULL,
  `ruc` varchar(35) DEFAULT NULL,
  `dni` varchar(30) DEFAULT NULL,
  `nombre` varchar(125) NOT NULL,
  `apellidos` varchar(125) NOT NULL,
  `tipo` enum('NATURAL','JURIDICA') DEFAULT NULL,
  `email` varchar(225) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `movil` varchar(15) DEFAULT NULL,
  `nextel` varchar(15) DEFAULT NULL,
  `fax` varchar(15) DEFAULT NULL,
  `sexo` enum('H','M') DEFAULT NULL,
  `fec_reg` bigint(20) NOT NULL,
  `activo` int(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gv_compra`
--

CREATE TABLE `gv_compra` (
  `idcompra` int(11) NOT NULL,
  `tipodoc` enum('FACTURA','BOLETA','GUIA','NOTA DE PEDIDO','DUA') DEFAULT NULL,
  `numdoc` varchar(20) DEFAULT NULL,
  `idproveedor` int(11) DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `idmoneda` int(11) DEFAULT NULL,
  `estado` enum('CANCELADO','PENDIENTE') DEFAULT NULL,
  `numitems` int(11) DEFAULT NULL,
  `observaciones` text DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `igv` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `activo` int(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gv_detallecompra`
--

CREATE TABLE `gv_detallecompra` (
  `iddetallec` int(11) NOT NULL,
  `idcompra` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `importe` double NOT NULL,
  `descuento` double DEFAULT NULL COMMENT 'porcentaje de descuento',
  `activo` int(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gv_encargadoalmacen`
--

CREATE TABLE `gv_encargadoalmacen` (
  `idEA` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  `idalmacen` int(11) NOT NULL,
  `tipocargo` enum('Lider','Empleado') NOT NULL,
  `activo` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gv_kardex`
--

CREATE TABLE `gv_kardex` (
  `idkardex` int(11) NOT NULL,
  `idProductos` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `documento` enum('APERTURA','VENTA','COMPRA','COMPRA_ACTUALIZACION','VENTA_ACTUALIZACION','PRODUCTO_ELIMINADO_COMPRA') DEFAULT NULL,
  `nro_documento` varchar(30) DEFAULT NULL,
  `entrada` int(11) DEFAULT NULL,
  `salida` int(11) DEFAULT NULL,
  `precio` decimal(11,2) DEFAULT NULL,
  `valor` decimal(11,2) DEFAULT NULL,
  `stock_actual` int(11) DEFAULT NULL,
  `valorTotal` decimal(11,2) DEFAULT NULL,
  `activo` int(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gv_marca`
--

CREATE TABLE `gv_marca` (
  `idmarca` int(11) NOT NULL,
  `nombre` varchar(125) NOT NULL,
  `activo` int(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gv_modelo`
--

CREATE TABLE `gv_modelo` (
  `idmodelo` int(11) NOT NULL,
  `nombre` varchar(125) NOT NULL,
  `activo` int(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gv_moneda`
--

CREATE TABLE `gv_moneda` (
  `idm` int(3) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `simbolo` varchar(3) NOT NULL,
  `activo` int(1) DEFAULT 1,
  `isdefault` int(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gv_producto`
--

CREATE TABLE `gv_producto` (
  `idproducto` bigint(20) NOT NULL,
  `cod_barras` varchar(50) NOT NULL,
  `codigo` varchar(50) NOT NULL,
  `cod_del_fabricante` varchar(30) DEFAULT NULL,
  `nombre` varchar(125) NOT NULL,
  `costo` decimal(10,3) NOT NULL,
  `precioalmayor` decimal(10,3) NOT NULL,
  `precioalmenor` decimal(10,3) NOT NULL,
  `utilidad` int(3) NOT NULL,
  `aplica_igv` tinyint(1) DEFAULT 1,
  `stock_min` int(11) NOT NULL,
  `tipo` enum('Producto Terminado','Componente','Insumo') NOT NULL,
  `idmoneda` int(11) NOT NULL,
  `imagen` longblob DEFAULT NULL,
  `idclase` int(11) DEFAULT NULL,
  `idmarca` int(11) DEFAULT NULL,
  `idmodelo` int(11) DEFAULT NULL,
  `ubicacion` varchar(10) DEFAULT NULL,
  `unidad_principal` enum('CAJA','MILLAR','UNIDAD','CARTUCHO','PAQUETE') DEFAULT NULL,
  `peso` float DEFAULT 0,
  `activo` int(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gv_proveedor`
--

CREATE TABLE `gv_proveedor` (
  `idproveedor` int(11) NOT NULL,
  `ruc` varchar(50) NOT NULL,
  `razon_social` varchar(150) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `ciudad` varchar(150) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `nextel` varchar(15) DEFAULT NULL,
  `movil` varchar(15) DEFAULT NULL,
  `fax` varchar(15) DEFAULT NULL,
  `cta_bancaria` varchar(30) DEFAULT NULL,
  `nom_contacto` varchar(125) DEFAULT NULL,
  `email` varchar(125) DEFAULT NULL,
  `rubro` varchar(125) DEFAULT NULL,
  `productos` text DEFAULT NULL,
  `activo` int(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gv_seriesproducto`
--

CREATE TABLE `gv_seriesproducto` (
  `idsp` bigint(20) NOT NULL,
  `idproducto` bigint(20) NOT NULL,
  `idcliente` bigint(20) DEFAULT NULL,
  `idproveedor` int(11) DEFAULT NULL,
  `num_serie` varchar(50) DEFAULT NULL,
  `vendido` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gv_usuario`
--

CREATE TABLE `gv_usuario` (
  `idu` int(11) NOT NULL,
  `dni` varchar(30) NOT NULL,
  `codigo` varchar(30) NOT NULL,
  `login` varchar(125) NOT NULL,
  `nombre` varchar(185) NOT NULL,
  `apellidos` varchar(185) NOT NULL,
  `foto` longblob DEFAULT NULL,
  `clave` varchar(125) NOT NULL,
  `salt` varchar(125) NOT NULL,
  `fec_nac` bigint(20) DEFAULT NULL,
  `fono` varchar(15) NOT NULL,
  `fono2` varchar(15) DEFAULT NULL,
  `celular` varchar(15) DEFAULT NULL,
  `nextel` varbinary(15) DEFAULT NULL,
  `email` varchar(125) DEFAULT NULL,
  `sexo` enum('H','M') NOT NULL,
  `tipo_cargo` enum('CAJA','COMPRAS','VENTAS','SERVICIO TECNICO','ALMACEN','MULTIPLE') NOT NULL,
  `descripcion` text DEFAULT NULL,
  `activo` tinyint(1) DEFAULT 1,
  `fec_registro` bigint(20) NOT NULL,
  `fec_baja` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `gv_almacen`
--
ALTER TABLE `gv_almacen`
  ADD PRIMARY KEY (`ida`);

--
-- Indices de la tabla `gv_almaceproduct`
--
ALTER TABLE `gv_almaceproduct`
  ADD PRIMARY KEY (`idpa`);

--
-- Indices de la tabla `gv_asignarauth`
--
ALTER TABLE `gv_asignarauth`
  ADD PRIMARY KEY (`nombreitem`,`iduser`);

--
-- Indices de la tabla `gv_authitem`
--
ALTER TABLE `gv_authitem`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `gv_authitemchild`
--
ALTER TABLE `gv_authitemchild`
  ADD PRIMARY KEY (`padre`,`hijo`),
  ADD KEY `hijo` (`hijo`);

--
-- Indices de la tabla `gv_bitacora`
--
ALTER TABLE `gv_bitacora`
  ADD PRIMARY KEY (`idbitacora`);

--
-- Indices de la tabla `gv_clase`
--
ALTER TABLE `gv_clase`
  ADD PRIMARY KEY (`idclase`);

--
-- Indices de la tabla `gv_cliente`
--
ALTER TABLE `gv_cliente`
  ADD PRIMARY KEY (`idc`);

--
-- Indices de la tabla `gv_compra`
--
ALTER TABLE `gv_compra`
  ADD PRIMARY KEY (`idcompra`);

--
-- Indices de la tabla `gv_detallecompra`
--
ALTER TABLE `gv_detallecompra`
  ADD PRIMARY KEY (`iddetallec`);

--
-- Indices de la tabla `gv_encargadoalmacen`
--
ALTER TABLE `gv_encargadoalmacen`
  ADD PRIMARY KEY (`idEA`);

--
-- Indices de la tabla `gv_kardex`
--
ALTER TABLE `gv_kardex`
  ADD PRIMARY KEY (`idkardex`);

--
-- Indices de la tabla `gv_marca`
--
ALTER TABLE `gv_marca`
  ADD PRIMARY KEY (`idmarca`);

--
-- Indices de la tabla `gv_modelo`
--
ALTER TABLE `gv_modelo`
  ADD PRIMARY KEY (`idmodelo`);

--
-- Indices de la tabla `gv_moneda`
--
ALTER TABLE `gv_moneda`
  ADD PRIMARY KEY (`idm`);

--
-- Indices de la tabla `gv_producto`
--
ALTER TABLE `gv_producto`
  ADD PRIMARY KEY (`idproducto`);

--
-- Indices de la tabla `gv_proveedor`
--
ALTER TABLE `gv_proveedor`
  ADD PRIMARY KEY (`idproveedor`);

--
-- Indices de la tabla `gv_seriesproducto`
--
ALTER TABLE `gv_seriesproducto`
  ADD PRIMARY KEY (`idsp`);

--
-- Indices de la tabla `gv_usuario`
--
ALTER TABLE `gv_usuario`
  ADD PRIMARY KEY (`idu`,`dni`,`codigo`,`login`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `gv_almacen`
--
ALTER TABLE `gv_almacen`
  MODIFY `ida` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `gv_almaceproduct`
--
ALTER TABLE `gv_almaceproduct`
  MODIFY `idpa` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `gv_bitacora`
--
ALTER TABLE `gv_bitacora`
  MODIFY `idbitacora` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `gv_clase`
--
ALTER TABLE `gv_clase`
  MODIFY `idclase` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `gv_cliente`
--
ALTER TABLE `gv_cliente`
  MODIFY `idc` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `gv_compra`
--
ALTER TABLE `gv_compra`
  MODIFY `idcompra` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `gv_detallecompra`
--
ALTER TABLE `gv_detallecompra`
  MODIFY `iddetallec` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `gv_encargadoalmacen`
--
ALTER TABLE `gv_encargadoalmacen`
  MODIFY `idEA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `gv_kardex`
--
ALTER TABLE `gv_kardex`
  MODIFY `idkardex` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `gv_marca`
--
ALTER TABLE `gv_marca`
  MODIFY `idmarca` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `gv_modelo`
--
ALTER TABLE `gv_modelo`
  MODIFY `idmodelo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `gv_moneda`
--
ALTER TABLE `gv_moneda`
  MODIFY `idm` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `gv_producto`
--
ALTER TABLE `gv_producto`
  MODIFY `idproducto` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `gv_proveedor`
--
ALTER TABLE `gv_proveedor`
  MODIFY `idproveedor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `gv_seriesproducto`
--
ALTER TABLE `gv_seriesproducto`
  MODIFY `idsp` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `gv_usuario`
--
ALTER TABLE `gv_usuario`
  MODIFY `idu` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `gv_asignarauth`
--
ALTER TABLE `gv_asignarauth`
  ADD CONSTRAINT `gv_asignarauth_ibfk_1` FOREIGN KEY (`nombreitem`) REFERENCES `gv_authitem` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `gv_authitemchild`
--
ALTER TABLE `gv_authitemchild`
  ADD CONSTRAINT `gv_authitemchild_ibfk_1` FOREIGN KEY (`padre`) REFERENCES `gv_authitem` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `gv_authitemchild_ibfk_2` FOREIGN KEY (`hijo`) REFERENCES `gv_authitem` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE;
--
-- Base de datos: `phpmyadmin`
--
CREATE DATABASE IF NOT EXISTS `phpmyadmin` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `phpmyadmin`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__bookmark`
--

CREATE TABLE `pma__bookmark` (
  `id` int(10) UNSIGNED NOT NULL,
  `dbase` varchar(255) NOT NULL DEFAULT '',
  `user` varchar(255) NOT NULL DEFAULT '',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `query` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Bookmarks';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__central_columns`
--

CREATE TABLE `pma__central_columns` (
  `db_name` varchar(64) NOT NULL,
  `col_name` varchar(64) NOT NULL,
  `col_type` varchar(64) NOT NULL,
  `col_length` text DEFAULT NULL,
  `col_collation` varchar(64) NOT NULL,
  `col_isNull` tinyint(1) NOT NULL,
  `col_extra` varchar(255) DEFAULT '',
  `col_default` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Central list of columns';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__column_info`
--

CREATE TABLE `pma__column_info` (
  `id` int(5) UNSIGNED NOT NULL,
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `table_name` varchar(64) NOT NULL DEFAULT '',
  `column_name` varchar(64) NOT NULL DEFAULT '',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `mimetype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `transformation` varchar(255) NOT NULL DEFAULT '',
  `transformation_options` varchar(255) NOT NULL DEFAULT '',
  `input_transformation` varchar(255) NOT NULL DEFAULT '',
  `input_transformation_options` varchar(255) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Column information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__designer_settings`
--

CREATE TABLE `pma__designer_settings` (
  `username` varchar(64) NOT NULL,
  `settings_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Settings related to Designer';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__export_templates`
--

CREATE TABLE `pma__export_templates` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) NOT NULL,
  `export_type` varchar(10) NOT NULL,
  `template_name` varchar(64) NOT NULL,
  `template_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved export templates';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__favorite`
--

CREATE TABLE `pma__favorite` (
  `username` varchar(64) NOT NULL,
  `tables` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Favorite tables';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__history`
--

CREATE TABLE `pma__history` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(64) NOT NULL DEFAULT '',
  `db` varchar(64) NOT NULL DEFAULT '',
  `table` varchar(64) NOT NULL DEFAULT '',
  `timevalue` timestamp NOT NULL DEFAULT current_timestamp(),
  `sqlquery` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='SQL history for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__navigationhiding`
--

CREATE TABLE `pma__navigationhiding` (
  `username` varchar(64) NOT NULL,
  `item_name` varchar(64) NOT NULL,
  `item_type` varchar(64) NOT NULL,
  `db_name` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Hidden items of navigation tree';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__pdf_pages`
--

CREATE TABLE `pma__pdf_pages` (
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `page_nr` int(10) UNSIGNED NOT NULL,
  `page_descr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PDF relation pages for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__recent`
--

CREATE TABLE `pma__recent` (
  `username` varchar(64) NOT NULL,
  `tables` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Recently accessed tables';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__relation`
--

CREATE TABLE `pma__relation` (
  `master_db` varchar(64) NOT NULL DEFAULT '',
  `master_table` varchar(64) NOT NULL DEFAULT '',
  `master_field` varchar(64) NOT NULL DEFAULT '',
  `foreign_db` varchar(64) NOT NULL DEFAULT '',
  `foreign_table` varchar(64) NOT NULL DEFAULT '',
  `foreign_field` varchar(64) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Relation table';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__savedsearches`
--

CREATE TABLE `pma__savedsearches` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) NOT NULL DEFAULT '',
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `search_name` varchar(64) NOT NULL DEFAULT '',
  `search_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved searches';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__table_coords`
--

CREATE TABLE `pma__table_coords` (
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `table_name` varchar(64) NOT NULL DEFAULT '',
  `pdf_page_number` int(11) NOT NULL DEFAULT 0,
  `x` float UNSIGNED NOT NULL DEFAULT 0,
  `y` float UNSIGNED NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for phpMyAdmin PDF output';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__table_info`
--

CREATE TABLE `pma__table_info` (
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `table_name` varchar(64) NOT NULL DEFAULT '',
  `display_field` varchar(64) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__table_uiprefs`
--

CREATE TABLE `pma__table_uiprefs` (
  `username` varchar(64) NOT NULL,
  `db_name` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL,
  `prefs` text NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Tables'' UI preferences';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__tracking`
--

CREATE TABLE `pma__tracking` (
  `db_name` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL,
  `version` int(10) UNSIGNED NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime NOT NULL,
  `schema_snapshot` text NOT NULL,
  `schema_sql` text DEFAULT NULL,
  `data_sql` longtext DEFAULT NULL,
  `tracking` set('UPDATE','REPLACE','INSERT','DELETE','TRUNCATE','CREATE DATABASE','ALTER DATABASE','DROP DATABASE','CREATE TABLE','ALTER TABLE','RENAME TABLE','DROP TABLE','CREATE INDEX','DROP INDEX','CREATE VIEW','ALTER VIEW','DROP VIEW') DEFAULT NULL,
  `tracking_active` int(1) UNSIGNED NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Database changes tracking for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__userconfig`
--

CREATE TABLE `pma__userconfig` (
  `username` varchar(64) NOT NULL,
  `timevalue` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `config_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User preferences storage for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__usergroups`
--

CREATE TABLE `pma__usergroups` (
  `usergroup` varchar(64) NOT NULL,
  `tab` varchar(64) NOT NULL,
  `allowed` enum('Y','N') NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User groups with configured menu items';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__users`
--

CREATE TABLE `pma__users` (
  `username` varchar(64) NOT NULL,
  `usergroup` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users and their assignments to user groups';

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pma__central_columns`
--
ALTER TABLE `pma__central_columns`
  ADD PRIMARY KEY (`db_name`,`col_name`);

--
-- Indices de la tabla `pma__column_info`
--
ALTER TABLE `pma__column_info`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `db_name` (`db_name`,`table_name`,`column_name`);

--
-- Indices de la tabla `pma__designer_settings`
--
ALTER TABLE `pma__designer_settings`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_user_type_template` (`username`,`export_type`,`template_name`);

--
-- Indices de la tabla `pma__favorite`
--
ALTER TABLE `pma__favorite`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `pma__history`
--
ALTER TABLE `pma__history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username` (`username`,`db`,`table`,`timevalue`);

--
-- Indices de la tabla `pma__navigationhiding`
--
ALTER TABLE `pma__navigationhiding`
  ADD PRIMARY KEY (`username`,`item_name`,`item_type`,`db_name`,`table_name`);

--
-- Indices de la tabla `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  ADD PRIMARY KEY (`page_nr`),
  ADD KEY `db_name` (`db_name`);

--
-- Indices de la tabla `pma__recent`
--
ALTER TABLE `pma__recent`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `pma__relation`
--
ALTER TABLE `pma__relation`
  ADD PRIMARY KEY (`master_db`,`master_table`,`master_field`),
  ADD KEY `foreign_field` (`foreign_db`,`foreign_table`);

--
-- Indices de la tabla `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_savedsearches_username_dbname` (`username`,`db_name`,`search_name`);

--
-- Indices de la tabla `pma__table_coords`
--
ALTER TABLE `pma__table_coords`
  ADD PRIMARY KEY (`db_name`,`table_name`,`pdf_page_number`);

--
-- Indices de la tabla `pma__table_info`
--
ALTER TABLE `pma__table_info`
  ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Indices de la tabla `pma__table_uiprefs`
--
ALTER TABLE `pma__table_uiprefs`
  ADD PRIMARY KEY (`username`,`db_name`,`table_name`);

--
-- Indices de la tabla `pma__tracking`
--
ALTER TABLE `pma__tracking`
  ADD PRIMARY KEY (`db_name`,`table_name`,`version`);

--
-- Indices de la tabla `pma__userconfig`
--
ALTER TABLE `pma__userconfig`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `pma__usergroups`
--
ALTER TABLE `pma__usergroups`
  ADD PRIMARY KEY (`usergroup`,`tab`,`allowed`);

--
-- Indices de la tabla `pma__users`
--
ALTER TABLE `pma__users`
  ADD PRIMARY KEY (`username`,`usergroup`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pma__column_info`
--
ALTER TABLE `pma__column_info`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pma__history`
--
ALTER TABLE `pma__history`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  MODIFY `page_nr` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- Base de datos: `punto_venta_java`
--
CREATE DATABASE IF NOT EXISTS `punto_venta_java` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `punto_venta_java`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `ID` int(11) NOT NULL,
  `DNI` int(20) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `TELEFONO` bigint(15) NOT NULL,
  `DIRECCION` varchar(100) NOT NULL,
  `RAZON_SOCIAL` varchar(100) NOT NULL,
  `FECHA` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `configuracion`
--

CREATE TABLE `configuracion` (
  `ID` int(11) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `RUT` bigint(20) DEFAULT NULL,
  `TELEFONO` bigint(20) DEFAULT NULL,
  `DIRECCION` varchar(100) NOT NULL,
  `RAZON_SOCIAL` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle`
--

CREATE TABLE `detalle` (
  `ID` int(11) NOT NULL,
  `CODIGO_PRODUCTO` varchar(30) DEFAULT NULL,
  `CANTIDAD` int(11) NOT NULL,
  `PRECIO` decimal(10,2) NOT NULL,
  `ID_VENTA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `ID` int(11) NOT NULL,
  `CODIGO` varchar(50) NOT NULL,
  `DESCRIPCION` varchar(255) NOT NULL,
  `PROVEEDOR` varchar(100) NOT NULL,
  `STOCK` int(11) NOT NULL,
  `PRECIO` decimal(10,2) NOT NULL,
  `FECHA` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `ID` int(20) NOT NULL,
  `RUT` bigint(20) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `TELEFONO` bigint(15) NOT NULL,
  `DIRECCION` varchar(100) NOT NULL,
  `RAZON_SOCIAL` varchar(100) NOT NULL,
  `FECHA` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `ID` int(11) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `CORREO` varchar(100) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `ROL` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `ID` int(11) NOT NULL,
  `CLIENTE` varchar(100) NOT NULL,
  `VENDEDOR` varchar(100) NOT NULL,
  `TOTAL` decimal(12,2) NOT NULL,
  `FECHA` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalle`
--
ALTER TABLE `detalle`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `ID` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- Base de datos: `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test`;
--
-- Base de datos: `test_dhn`
--
CREATE DATABASE IF NOT EXISTS `test_dhn` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE `test_dhn`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `abonos`
--

CREATE TABLE `abonos` (
  `IDABONO` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `DESCRIPCION` varchar(150) NOT NULL,
  `MONTO` float NOT NULL,
  `PERIODO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ahorros`
--

CREATE TABLE `ahorros` (
  `IDAHORRO` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `MONTO` float NOT NULL,
  `PERIODO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `antenas`
--

CREATE TABLE `antenas` (
  `IDANTENA` int(11) NOT NULL,
  `MODELO` varchar(100) NOT NULL,
  `NOMBRE` varchar(100) DEFAULT NULL,
  `MARCA` varchar(45) DEFAULT NULL,
  `IP` varchar(30) DEFAULT NULL,
  `SITIO` varchar(100) DEFAULT NULL,
  `IPPADRE` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bitacora`
--

CREATE TABLE `bitacora` (
  `IDBITACORA` int(11) NOT NULL,
  `TABLA` varchar(30) NOT NULL,
  `REGISTRO` int(11) NOT NULL,
  `ACCION` varchar(30) NOT NULL,
  `IDUSER` int(11) NOT NULL,
  `COMENTARIOS` varchar(5000) NOT NULL,
  `REFERENCIA` varchar(5000) NOT NULL,
  `FECHA` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bonosd`
--

CREATE TABLE `bonosd` (
  `IDBONOD` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `IDPRESTACION` int(11) NOT NULL,
  `EMPRESA` varchar(30) NOT NULL,
  `MONTO` float NOT NULL,
  `FECHA` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cable`
--

CREATE TABLE `cable` (
  `IDCABLE` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `ENTREGADO` double NOT NULL,
  `USADO` double NOT NULL,
  `DEVUELTO` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cajas`
--

CREATE TABLE `cajas` (
  `IDCAJA` int(11) NOT NULL,
  `DESCRIPCION` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito`
--

CREATE TABLE `carrito` (
  `IDCARRITO` int(11) NOT NULL,
  `IDPENDIENTE` int(11) NOT NULL,
  `IDPRODUCTO` double NOT NULL,
  `CANTIDAD` int(11) NOT NULL,
  `TOTAL` double NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `ESTADO` varchar(30) NOT NULL,
  `FECHA` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `centrosp`
--

CREATE TABLE `centrosp` (
  `IDCENTROP` int(11) NOT NULL,
  `DESCRIPCION` varchar(100) NOT NULL,
  `UBICACION` varchar(100) NOT NULL,
  `ROL` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cf`
--

CREATE TABLE `cf` (
  `IDCF` int(11) NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `TIPO` varchar(15) NOT NULL,
  `MONTO` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `IDCLIENTE` int(11) NOT NULL,
  `NOMBRE` varchar(300) DEFAULT NULL,
  `TEL1` varchar(100) DEFAULT NULL,
  `TEL2` varchar(100) DEFAULT NULL,
  `TEL3` varchar(100) DEFAULT NULL,
  `IDENTIFICACION` varchar(30) DEFAULT NULL,
  `INE` varchar(1000) DEFAULT NULL,
  `CFE` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cobranza`
--

CREATE TABLE `cobranza` (
  `IDCOBRANZA` int(11) NOT NULL,
  `IDCONTRATO` int(11) NOT NULL,
  `COMENTARIOS` varchar(1000) DEFAULT NULL,
  `CATEGORIA` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contratos`
--

CREATE TABLE `contratos` (
  `IDCONTRATO` int(11) NOT NULL,
  `IDCLIENTE` int(11) DEFAULT NULL,
  `IDDIRECCION` int(11) DEFAULT NULL,
  `IDPAQUETE` int(11) DEFAULT NULL,
  `FREG` varchar(15) DEFAULT NULL,
  `FINS` varchar(15) DEFAULT NULL,
  `COMENTARIOS` varchar(1000) DEFAULT NULL,
  `ESTADO` varchar(15) DEFAULT NULL,
  `FCOR` varchar(15) DEFAULT NULL,
  `EQUIPO` varchar(15) DEFAULT NULL,
  `IP` varchar(30) DEFAULT NULL,
  `CONT` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cortes`
--

CREATE TABLE `cortes` (
  `IDCORTE` int(11) NOT NULL,
  `MONTOINI` float NOT NULL,
  `FINI` varchar(45) DEFAULT NULL,
  `FFIN` varchar(45) DEFAULT NULL,
  `MONTOFIN` float DEFAULT NULL,
  `IDCAJA` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cortesd`
--

CREATE TABLE `cortesd` (
  `IDCORTED` int(11) NOT NULL,
  `IDCORTE` int(11) DEFAULT NULL,
  `IDPAGOI` int(11) DEFAULT NULL,
  `IDPAGOF` int(11) DEFAULT NULL,
  `IDENTRADAI` int(11) DEFAULT NULL,
  `IDENTRADAF` int(11) DEFAULT NULL,
  `IDSALIDAI` int(11) DEFAULT NULL,
  `IDSALIDAF` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cortesg`
--

CREATE TABLE `cortesg` (
  `IDCORTEG` int(11) NOT NULL,
  `DHN` float NOT NULL,
  `EDH` float NOT NULL,
  `VALES` float NOT NULL,
  `CF` float NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `MONTOG` float NOT NULL,
  `MONTOR` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cotizaciones`
--

CREATE TABLE `cotizaciones` (
  `IDCOTIZACION` int(11) NOT NULL,
  `IDPENDIENTE` int(11) NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `CATEGORIA` varchar(50) NOT NULL,
  `COMENTARIOS` varchar(2000) NOT NULL,
  `DOC` varchar(500) NOT NULL,
  `ESTADO` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas`
--

CREATE TABLE `cuentas` (
  `IDCUENTA` int(11) NOT NULL,
  `BANCO` varchar(50) NOT NULL,
  `NCUENTA` varchar(100) NOT NULL,
  `TIPO` varchar(50) NOT NULL,
  `ALIAS` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cutservices`
--

CREATE TABLE `cutservices` (
  `FECHA` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalles`
--

CREATE TABLE `detalles` (
  `IDDETALLE` int(11) NOT NULL,
  `DIASPRE` int(11) NOT NULL,
  `CONCEPTO` varchar(150) NOT NULL,
  `SMINIMO` float NOT NULL,
  `TC` float NOT NULL,
  `IVA` float NOT NULL,
  `GP` double NOT NULL,
  `GI` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallesdisp`
--

CREATE TABLE `detallesdisp` (
  `IDD` int(11) NOT NULL,
  `TABLA` varchar(30) NOT NULL,
  `CATEGORIA` varchar(30) NOT NULL,
  `DESCRIPCION` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direcciones`
--

CREATE TABLE `direcciones` (
  `IDDIRECCION` int(11) NOT NULL,
  `IDCLIENTE` int(11) DEFAULT NULL,
  `CALLE` varchar(100) DEFAULT NULL,
  `NUMERO` varchar(50) DEFAULT NULL,
  `COLONIA` varchar(100) DEFAULT NULL,
  `LOCALIDAD` varchar(100) DEFAULT NULL,
  `LATITUD` varchar(2000) DEFAULT NULL,
  `LONGITUD` varchar(150) DEFAULT NULL,
  `REFERENCIA` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `edetalles`
--

CREATE TABLE `edetalles` (
  `IDEDETALLE` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `PINI` varchar(15) NOT NULL,
  `PFIN` varchar(15) NOT NULL,
  `VPAGADAS` tinyint(1) NOT NULL,
  `APAGADOS` tinyint(1) NOT NULL,
  `PERIODOV` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `edocs`
--

CREATE TABLE `edocs` (
  `IDEDOC` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `NUMBER` varchar(30) NOT NULL,
  `DESCRIPCION` varchar(30) NOT NULL,
  `VIGENCIA` varchar(50) NOT NULL,
  `DOC` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `IDEMPLEADO` int(11) NOT NULL,
  `NOMBRE` varchar(50) DEFAULT NULL,
  `APELLIDOS` varchar(50) DEFAULT NULL,
  `TEL` varchar(20) DEFAULT NULL,
  `SUELDO` float NOT NULL,
  `EMPRESA` varchar(60) DEFAULT NULL,
  `BONO` float NOT NULL,
  `BONOP` float NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `ESTADO` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entradas`
--

CREATE TABLE `entradas` (
  `IDENTRADA` int(11) NOT NULL,
  `DESCRIPCION` varchar(1000) DEFAULT NULL,
  `MONTO` float DEFAULT NULL,
  `CATEGORIA` varchar(30) DEFAULT NULL,
  `FECHA` varchar(45) DEFAULT NULL,
  `METODO` varchar(15) DEFAULT NULL,
  `IDCAJA` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entradascaja`
--

CREATE TABLE `entradascaja` (
  `IDENTRADACAJA` int(11) NOT NULL,
  `DESCRIPCION` varchar(300) NOT NULL,
  `MONTO` double NOT NULL,
  `EMPRESA` varchar(50) NOT NULL,
  `FECHA` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `epuestos`
--

CREATE TABLE `epuestos` (
  `IDEPUESTO` int(11) NOT NULL,
  `DEPTO` varchar(50) NOT NULL,
  `PUESTO` varchar(50) NOT NULL,
  `DOC` varchar(1000) NOT NULL,
  `SUELDO` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `extras`
--

CREATE TABLE `extras` (
  `IDEXTRA` int(11) NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `DESCRIPCION` varchar(1000) NOT NULL,
  `TIPO` varchar(30) NOT NULL,
  `MONTO` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `finiquito`
--

CREATE TABLE `finiquito` (
  `IDFINIQUITO` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FINI` varchar(15) NOT NULL,
  `FFIN` varchar(15) NOT NULL,
  `MOTIVO` varchar(30) NOT NULL,
  `SUELDO` double NOT NULL,
  `SDIARIO` double NOT NULL,
  `DRESTANTES` int(11) NOT NULL,
  `SRESTANTE` double NOT NULL,
  `PRESTAMOS` double NOT NULL,
  `ABONOS` double NOT NULL,
  `AHORROS` double NOT NULL,
  `SALDO` double NOT NULL,
  `DVACA` int(11) NOT NULL,
  `MONTOVACA` double NOT NULL,
  `PRIMAVACA` double NOT NULL,
  `AGUINALDO` double NOT NULL,
  `MONTO` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `kits`
--

CREATE TABLE `kits` (
  `IDKIT` int(11) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL,
  `CATEGORIA` varchar(50) NOT NULL,
  `COSTO` float NOT NULL,
  `PRECIO` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `kitscategorias`
--

CREATE TABLE `kitscategorias` (
  `CATEGORIA` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `kitsd`
--

CREATE TABLE `kitsd` (
  `IDKITD` int(11) NOT NULL,
  `IDKIT` int(11) NOT NULL,
  `IDPRODUCTO` int(11) NOT NULL,
  `CANTIDAD` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `motivosr`
--

CREATE TABLE `motivosr` (
  `IDMOTIVOR` int(11) NOT NULL,
  `IDNOTA` int(11) NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `MOTIVOS` varchar(1500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movd`
--

CREATE TABLE `movd` (
  `IDMOVD` int(11) NOT NULL,
  `MOVIMIENTO` varchar(30) NOT NULL,
  `IDMOVIMIENTO` int(11) NOT NULL,
  `REF` varchar(1000) NOT NULL,
  `COTEJADO` tinyint(1) NOT NULL,
  `FAC` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nominas`
--

CREATE TABLE `nominas` (
  `IDNOMINA` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `SEMANA` int(11) NOT NULL,
  `ANO` int(11) NOT NULL,
  `DIAS` int(11) NOT NULL,
  `NSUELDO` double NOT NULL,
  `BONO` double NOT NULL,
  `CONCEPTO` varchar(300) NOT NULL,
  `IDAHORRO` int(11) NOT NULL,
  `IDABONO` int(11) NOT NULL,
  `TOTAL` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordenes`
--

CREATE TABLE `ordenes` (
  `IDORDEN` int(11) NOT NULL,
  `IDEMPLEADO` int(11) DEFAULT NULL,
  `IDCONTRATO` int(11) DEFAULT NULL,
  `FINI` varchar(15) DEFAULT NULL,
  `FFIN` varchar(15) DEFAULT NULL,
  `ESTADO` varchar(20) DEFAULT NULL,
  `COMENTARIOS` varchar(5000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordenesp`
--

CREATE TABLE `ordenesp` (
  `IDORDENP` int(11) NOT NULL,
  `IDEMPLEADO` int(11) DEFAULT NULL,
  `IDPENDIENTE` int(11) DEFAULT NULL,
  `FINI` varchar(15) DEFAULT NULL,
  `FFIN` varchar(15) DEFAULT NULL,
  `ESTADO` varchar(20) DEFAULT NULL,
  `COMENTARIOS` varchar(5000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `IDNOTA` int(11) NOT NULL,
  `IDCLIENTE` int(11) DEFAULT NULL,
  `IDCONTRATO` int(11) DEFAULT NULL,
  `FECHA` varchar(50) DEFAULT NULL,
  `METODO` varchar(100) DEFAULT NULL,
  `MONTO` float DEFAULT NULL,
  `DOC` varchar(1000) DEFAULT NULL,
  `REFERENCIA` varchar(199) DEFAULT NULL,
  `COMENTARIOS` varchar(2000) DEFAULT NULL,
  `IDCAJA` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagosdif`
--

CREATE TABLE `pagosdif` (
  `IDPAGODIF` int(11) NOT NULL,
  `IDNOTA` int(11) NOT NULL,
  `METODO` varchar(15) NOT NULL,
  `MONTO` float NOT NULL,
  `DOC` varchar(150) NOT NULL,
  `REF` varchar(150) NOT NULL,
  `ESTADO` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagosprogramados`
--

CREATE TABLE `pagosprogramados` (
  `IDPAGOPROGRAMADO` int(11) NOT NULL,
  `IDPROVEEDOR` int(11) NOT NULL,
  `FPRO` varchar(15) NOT NULL,
  `MONTO` float NOT NULL,
  `CONCEPTO` varchar(150) NOT NULL,
  `RECURRENCIA` int(11) NOT NULL,
  `ESTADO` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagosproveedores`
--

CREATE TABLE `pagosproveedores` (
  `IDPAGOPROVEEDOR` int(11) NOT NULL,
  `IDPAGOPROGRAMADO` int(11) NOT NULL,
  `FREALIZADO` varchar(15) NOT NULL,
  `MONTO` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paquetes`
--

CREATE TABLE `paquetes` (
  `IDPAQUETE` int(11) NOT NULL,
  `COSTO` int(11) DEFAULT NULL,
  `DESCRIPCION` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pdocs`
--

CREATE TABLE `pdocs` (
  `IDPDOC` int(11) NOT NULL,
  `IDPENDIENTE` int(11) NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `DOC` varchar(1000) NOT NULL,
  `COMENTARIOS` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pendientes`
--

CREATE TABLE `pendientes` (
  `IDPENDIENTE` int(11) NOT NULL,
  `IDCLIENTE` int(11) DEFAULT NULL,
  `IDDIRECCION` int(11) DEFAULT NULL,
  `NPEDIDO` varchar(10) DEFAULT NULL,
  `NPEDIDOF` varchar(10) DEFAULT NULL,
  `CATEGORIA` varchar(50) DEFAULT NULL,
  `SUBCATEGORIA` varchar(50) DEFAULT NULL,
  `DESCRIPCION` varchar(5000) DEFAULT NULL,
  `COMENTARIOS` varchar(5000) DEFAULT NULL,
  `FREG` varchar(30) DEFAULT NULL,
  `FEST` varchar(30) DEFAULT NULL,
  `FFIN` varchar(30) DEFAULT NULL,
  `ESTADO` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `pendientes_abierto`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `pendientes_abierto` (
`IDPENDIENTE` int(11)
,`NOMBRE` varchar(300)
,`TEL1` varchar(100)
,`CALLE` varchar(100)
,`NUMERO` varchar(50)
,`COLONIA` varchar(100)
,`LOCALIDAD` varchar(100)
,`LATITUD` varchar(2000)
,`LONGITUD` varchar(150)
,`CATEGORIA` varchar(50)
,`SUBCATEGORIA` varchar(50)
,`DESCRIPCION` varchar(5000)
,`COMENTARIOS` varchar(5000)
,`FREG` varchar(30)
,`FEST` varchar(30)
,`FFIN` varchar(30)
,`NPEDIDO` varchar(10)
,`NPEDIDOF` varchar(10)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestaciones`
--

CREATE TABLE `prestaciones` (
  `IDPRESTACION` int(11) NOT NULL,
  `CONCEPTO` varchar(30) NOT NULL,
  `PERIODO` int(11) NOT NULL,
  `FINI` varchar(15) NOT NULL,
  `FFIN` varchar(15) NOT NULL,
  `ESTADO` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestacionesp`
--

CREATE TABLE `prestacionesp` (
  `IDPRESTACIONP` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `IDPRESTACION` int(11) NOT NULL,
  `FREG` varchar(15) NOT NULL,
  `MONTO` double NOT NULL,
  `PERIODO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamos`
--

CREATE TABLE `prestamos` (
  `IDPRESTAMO` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `DESCRIPCION` varchar(150) NOT NULL,
  `MONTO` float NOT NULL,
  `PERIODO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `privilegios`
--

CREATE TABLE `privilegios` (
  `IDPRIVILEGIO` int(11) NOT NULL,
  `GRUPO` varchar(15) NOT NULL,
  `ITEM` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `IDPRODUCTO` int(11) NOT NULL,
  `SICAR` varchar(50) NOT NULL,
  `CATEGORIA` varchar(50) NOT NULL,
  `DESCRIPCION` varchar(300) NOT NULL,
  `COSTO` double NOT NULL,
  `MONEDA` varchar(5) NOT NULL,
  `IVA` tinyint(1) NOT NULL,
  `PP` double NOT NULL,
  `PI` double NOT NULL,
  `KIT` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productosd`
--

CREATE TABLE `productosd` (
  `CB` varchar(50) NOT NULL,
  `IDPRODUCTO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `IDPROVEEDOR` int(11) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `TEL1` varchar(15) NOT NULL,
  `TEL2` varchar(15) NOT NULL,
  `REFERENCIA` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reportes`
--

CREATE TABLE `reportes` (
  `IDREPORTE` int(11) NOT NULL,
  `IDCONTRATO` int(11) DEFAULT NULL,
  `DESCRIPCION` varchar(1000) DEFAULT NULL,
  `FABIERTO` varchar(30) DEFAULT NULL,
  `FCERRADO` varchar(30) DEFAULT NULL,
  `ESTATUS` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salidas`
--

CREATE TABLE `salidas` (
  `IDSALIDA` int(11) NOT NULL,
  `DESCRIPCION` varchar(1000) DEFAULT NULL,
  `MONTO` float DEFAULT NULL,
  `CATEGORIA` varchar(30) DEFAULT NULL,
  `FECHA` varchar(45) DEFAULT NULL,
  `METODO` varchar(15) DEFAULT NULL,
  `IDCAJA` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salidascaja`
--

CREATE TABLE `salidascaja` (
  `IDSALIDACAJA` int(11) NOT NULL,
  `DESCRIPCION` varchar(300) NOT NULL,
  `MONTO` double NOT NULL,
  `EMPRESA` varchar(50) NOT NULL,
  `FECHA` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `IDUSUARIO` int(11) NOT NULL,
  `NOMBRE` varchar(10) DEFAULT NULL,
  `PASSWORD` varchar(30) DEFAULT NULL,
  `PRIVILEGIOS` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vacaciones`
--

CREATE TABLE `vacaciones` (
  `IDVACACION` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FREG` varchar(15) NOT NULL,
  `FSALIDA` varchar(15) NOT NULL,
  `FREGRESO` varchar(15) NOT NULL,
  `DIAST` int(11) NOT NULL,
  `COMENTARIOS` varchar(150) NOT NULL,
  `PERIODO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vacacionestab`
--

CREATE TABLE `vacacionestab` (
  `IDVACACIONTAB` int(11) NOT NULL,
  `DIAS` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vales`
--

CREATE TABLE `vales` (
  `IDVALE` int(11) NOT NULL,
  `FECHA` varchar(50) NOT NULL,
  `DESCRIPCION` varchar(500) NOT NULL,
  `TIPO` varchar(15) NOT NULL,
  `MONTO` float NOT NULL,
  `ESTADO` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculos`
--

CREATE TABLE `vehiculos` (
  `IDVEHICULO` int(11) NOT NULL,
  `MARCA` varchar(30) NOT NULL,
  `MODELO` varchar(30) NOT NULL,
  `NS` varchar(100) NOT NULL,
  `NMOTOR` varchar(50) NOT NULL,
  `PLACA` varchar(15) NOT NULL,
  `EMPRESA` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculosseg`
--

CREATE TABLE `vehiculosseg` (
  `IDVEHICULOSEG` int(11) NOT NULL,
  `IDVEHICULO` int(11) NOT NULL,
  `ASEGURADORA` varchar(30) NOT NULL,
  `NP` varchar(30) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `VIGENCIA` varchar(15) NOT NULL,
  `ARCHIVO` varchar(1000) NOT NULL,
  `PROPIETARIO` varchar(150) NOT NULL,
  `COSTO` float NOT NULL,
  `EMPRESA` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculosser`
--

CREATE TABLE `vehiculosser` (
  `IDVEHICULOSER` int(11) NOT NULL,
  `IDVEHICULO` int(11) NOT NULL,
  `TIPO` varchar(50) NOT NULL,
  `COMENTARIOS` varchar(1000) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `KM` int(11) NOT NULL,
  `EMPRESA` varchar(50) NOT NULL,
  `COSTO` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura para la vista `pendientes_abierto`
--
DROP TABLE IF EXISTS `pendientes_abierto`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `pendientes_abierto`  AS SELECT `pendientes`.`IDPENDIENTE` AS `IDPENDIENTE`, `clientes`.`NOMBRE` AS `NOMBRE`, `clientes`.`TEL1` AS `TEL1`, `direcciones`.`CALLE` AS `CALLE`, `direcciones`.`NUMERO` AS `NUMERO`, `direcciones`.`COLONIA` AS `COLONIA`, `direcciones`.`LOCALIDAD` AS `LOCALIDAD`, `direcciones`.`LATITUD` AS `LATITUD`, `direcciones`.`LONGITUD` AS `LONGITUD`, `pendientes`.`CATEGORIA` AS `CATEGORIA`, `pendientes`.`SUBCATEGORIA` AS `SUBCATEGORIA`, `pendientes`.`DESCRIPCION` AS `DESCRIPCION`, `pendientes`.`COMENTARIOS` AS `COMENTARIOS`, `pendientes`.`FREG` AS `FREG`, `pendientes`.`FEST` AS `FEST`, `pendientes`.`FFIN` AS `FFIN`, `pendientes`.`NPEDIDO` AS `NPEDIDO`, `pendientes`.`NPEDIDOF` AS `NPEDIDOF` FROM ((`pendientes` join `clientes` on(`pendientes`.`IDCLIENTE` = `clientes`.`IDCLIENTE`)) join `direcciones` on(`pendientes`.`IDDIRECCION` = `direcciones`.`IDDIRECCION`)) WHERE `pendientes`.`ESTADO` = 'ABIERTO' ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `abonos`
--
ALTER TABLE `abonos`
  ADD PRIMARY KEY (`IDABONO`),
  ADD KEY `IDEMPLEADO` (`IDEMPLEADO`);

--
-- Indices de la tabla `ahorros`
--
ALTER TABLE `ahorros`
  ADD PRIMARY KEY (`IDAHORRO`),
  ADD KEY `IDEMPLEADO` (`IDEMPLEADO`);

--
-- Indices de la tabla `antenas`
--
ALTER TABLE `antenas`
  ADD PRIMARY KEY (`IDANTENA`);

--
-- Indices de la tabla `bitacora`
--
ALTER TABLE `bitacora`
  ADD PRIMARY KEY (`IDBITACORA`);

--
-- Indices de la tabla `bonosd`
--
ALTER TABLE `bonosd`
  ADD PRIMARY KEY (`IDBONOD`);

--
-- Indices de la tabla `cable`
--
ALTER TABLE `cable`
  ADD PRIMARY KEY (`IDCABLE`);

--
-- Indices de la tabla `cajas`
--
ALTER TABLE `cajas`
  ADD PRIMARY KEY (`IDCAJA`);

--
-- Indices de la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD PRIMARY KEY (`IDCARRITO`);

--
-- Indices de la tabla `centrosp`
--
ALTER TABLE `centrosp`
  ADD PRIMARY KEY (`IDCENTROP`);

--
-- Indices de la tabla `cf`
--
ALTER TABLE `cf`
  ADD PRIMARY KEY (`IDCF`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`IDCLIENTE`),
  ADD UNIQUE KEY `IDCLIENTE` (`IDCLIENTE`),
  ADD KEY `IDCLIENTE_2` (`IDCLIENTE`);

--
-- Indices de la tabla `cobranza`
--
ALTER TABLE `cobranza`
  ADD PRIMARY KEY (`IDCOBRANZA`);

--
-- Indices de la tabla `contratos`
--
ALTER TABLE `contratos`
  ADD PRIMARY KEY (`IDCONTRATO`);

--
-- Indices de la tabla `cortes`
--
ALTER TABLE `cortes`
  ADD PRIMARY KEY (`IDCORTE`);

--
-- Indices de la tabla `cortesd`
--
ALTER TABLE `cortesd`
  ADD PRIMARY KEY (`IDCORTED`);

--
-- Indices de la tabla `cortesg`
--
ALTER TABLE `cortesg`
  ADD PRIMARY KEY (`IDCORTEG`);

--
-- Indices de la tabla `cotizaciones`
--
ALTER TABLE `cotizaciones`
  ADD PRIMARY KEY (`IDCOTIZACION`);

--
-- Indices de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  ADD PRIMARY KEY (`IDCUENTA`);

--
-- Indices de la tabla `cutservices`
--
ALTER TABLE `cutservices`
  ADD PRIMARY KEY (`FECHA`);

--
-- Indices de la tabla `detalles`
--
ALTER TABLE `detalles`
  ADD PRIMARY KEY (`IDDETALLE`);

--
-- Indices de la tabla `detallesdisp`
--
ALTER TABLE `detallesdisp`
  ADD PRIMARY KEY (`IDD`);

--
-- Indices de la tabla `direcciones`
--
ALTER TABLE `direcciones`
  ADD PRIMARY KEY (`IDDIRECCION`),
  ADD KEY `IDCLIENTE` (`IDCLIENTE`);

--
-- Indices de la tabla `edetalles`
--
ALTER TABLE `edetalles`
  ADD PRIMARY KEY (`IDEDETALLE`);

--
-- Indices de la tabla `edocs`
--
ALTER TABLE `edocs`
  ADD PRIMARY KEY (`IDEDOC`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`IDEMPLEADO`);

--
-- Indices de la tabla `entradas`
--
ALTER TABLE `entradas`
  ADD PRIMARY KEY (`IDENTRADA`);

--
-- Indices de la tabla `entradascaja`
--
ALTER TABLE `entradascaja`
  ADD PRIMARY KEY (`IDENTRADACAJA`);

--
-- Indices de la tabla `epuestos`
--
ALTER TABLE `epuestos`
  ADD PRIMARY KEY (`IDEPUESTO`);

--
-- Indices de la tabla `extras`
--
ALTER TABLE `extras`
  ADD PRIMARY KEY (`IDEXTRA`);

--
-- Indices de la tabla `finiquito`
--
ALTER TABLE `finiquito`
  ADD PRIMARY KEY (`IDFINIQUITO`);

--
-- Indices de la tabla `kits`
--
ALTER TABLE `kits`
  ADD PRIMARY KEY (`IDKIT`);

--
-- Indices de la tabla `kitsd`
--
ALTER TABLE `kitsd`
  ADD PRIMARY KEY (`IDKITD`);

--
-- Indices de la tabla `motivosr`
--
ALTER TABLE `motivosr`
  ADD PRIMARY KEY (`IDMOTIVOR`);

--
-- Indices de la tabla `movd`
--
ALTER TABLE `movd`
  ADD PRIMARY KEY (`IDMOVD`);

--
-- Indices de la tabla `nominas`
--
ALTER TABLE `nominas`
  ADD PRIMARY KEY (`IDNOMINA`),
  ADD KEY `IDEMPLEADO` (`IDEMPLEADO`);

--
-- Indices de la tabla `ordenes`
--
ALTER TABLE `ordenes`
  ADD PRIMARY KEY (`IDORDEN`);

--
-- Indices de la tabla `ordenesp`
--
ALTER TABLE `ordenesp`
  ADD PRIMARY KEY (`IDORDENP`),
  ADD KEY `IDPENDIENTE` (`IDPENDIENTE`),
  ADD KEY `IDEMPLEADO` (`IDEMPLEADO`);

--
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD PRIMARY KEY (`IDNOTA`);

--
-- Indices de la tabla `pagosdif`
--
ALTER TABLE `pagosdif`
  ADD PRIMARY KEY (`IDPAGODIF`);

--
-- Indices de la tabla `pagosprogramados`
--
ALTER TABLE `pagosprogramados`
  ADD PRIMARY KEY (`IDPAGOPROGRAMADO`);

--
-- Indices de la tabla `paquetes`
--
ALTER TABLE `paquetes`
  ADD PRIMARY KEY (`IDPAQUETE`);

--
-- Indices de la tabla `pdocs`
--
ALTER TABLE `pdocs`
  ADD PRIMARY KEY (`IDPDOC`);

--
-- Indices de la tabla `pendientes`
--
ALTER TABLE `pendientes`
  ADD PRIMARY KEY (`IDPENDIENTE`);

--
-- Indices de la tabla `prestaciones`
--
ALTER TABLE `prestaciones`
  ADD PRIMARY KEY (`IDPRESTACION`);

--
-- Indices de la tabla `prestacionesp`
--
ALTER TABLE `prestacionesp`
  ADD PRIMARY KEY (`IDPRESTACIONP`);

--
-- Indices de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD PRIMARY KEY (`IDPRESTAMO`),
  ADD KEY `IDEMPLEADO` (`IDEMPLEADO`);

--
-- Indices de la tabla `privilegios`
--
ALTER TABLE `privilegios`
  ADD PRIMARY KEY (`IDPRIVILEGIO`),
  ADD UNIQUE KEY `IDPRIVILEGIO` (`IDPRIVILEGIO`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`IDPRODUCTO`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`IDPROVEEDOR`);

--
-- Indices de la tabla `reportes`
--
ALTER TABLE `reportes`
  ADD PRIMARY KEY (`IDREPORTE`);

--
-- Indices de la tabla `salidas`
--
ALTER TABLE `salidas`
  ADD PRIMARY KEY (`IDSALIDA`);

--
-- Indices de la tabla `salidascaja`
--
ALTER TABLE `salidascaja`
  ADD UNIQUE KEY `IDSALIDACAJA` (`IDSALIDACAJA`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`IDUSUARIO`);

--
-- Indices de la tabla `vacaciones`
--
ALTER TABLE `vacaciones`
  ADD PRIMARY KEY (`IDVACACION`);

--
-- Indices de la tabla `vales`
--
ALTER TABLE `vales`
  ADD PRIMARY KEY (`IDVALE`);

--
-- Indices de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  ADD PRIMARY KEY (`IDVEHICULO`);

--
-- Indices de la tabla `vehiculosseg`
--
ALTER TABLE `vehiculosseg`
  ADD PRIMARY KEY (`IDVEHICULOSEG`);

--
-- Indices de la tabla `vehiculosser`
--
ALTER TABLE `vehiculosser`
  ADD PRIMARY KEY (`IDVEHICULOSER`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `abonos`
--
ALTER TABLE `abonos`
  MODIFY `IDABONO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `antenas`
--
ALTER TABLE `antenas`
  MODIFY `IDANTENA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `bitacora`
--
ALTER TABLE `bitacora`
  MODIFY `IDBITACORA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `bonosd`
--
ALTER TABLE `bonosd`
  MODIFY `IDBONOD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cable`
--
ALTER TABLE `cable`
  MODIFY `IDCABLE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cajas`
--
ALTER TABLE `cajas`
  MODIFY `IDCAJA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `carrito`
--
ALTER TABLE `carrito`
  MODIFY `IDCARRITO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `centrosp`
--
ALTER TABLE `centrosp`
  MODIFY `IDCENTROP` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cf`
--
ALTER TABLE `cf`
  MODIFY `IDCF` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cobranza`
--
ALTER TABLE `cobranza`
  MODIFY `IDCOBRANZA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cortesd`
--
ALTER TABLE `cortesd`
  MODIFY `IDCORTED` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cortesg`
--
ALTER TABLE `cortesg`
  MODIFY `IDCORTEG` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cotizaciones`
--
ALTER TABLE `cotizaciones`
  MODIFY `IDCOTIZACION` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  MODIFY `IDCUENTA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalles`
--
ALTER TABLE `detalles`
  MODIFY `IDDETALLE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detallesdisp`
--
ALTER TABLE `detallesdisp`
  MODIFY `IDD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `edocs`
--
ALTER TABLE `edocs`
  MODIFY `IDEDOC` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `entradascaja`
--
ALTER TABLE `entradascaja`
  MODIFY `IDENTRADACAJA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `epuestos`
--
ALTER TABLE `epuestos`
  MODIFY `IDEPUESTO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `extras`
--
ALTER TABLE `extras`
  MODIFY `IDEXTRA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `finiquito`
--
ALTER TABLE `finiquito`
  MODIFY `IDFINIQUITO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `kits`
--
ALTER TABLE `kits`
  MODIFY `IDKIT` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `kitsd`
--
ALTER TABLE `kitsd`
  MODIFY `IDKITD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `motivosr`
--
ALTER TABLE `motivosr`
  MODIFY `IDMOTIVOR` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `movd`
--
ALTER TABLE `movd`
  MODIFY `IDMOVD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pagosdif`
--
ALTER TABLE `pagosdif`
  MODIFY `IDPAGODIF` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pagosprogramados`
--
ALTER TABLE `pagosprogramados`
  MODIFY `IDPAGOPROGRAMADO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pdocs`
--
ALTER TABLE `pdocs`
  MODIFY `IDPDOC` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `prestaciones`
--
ALTER TABLE `prestaciones`
  MODIFY `IDPRESTACION` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `prestacionesp`
--
ALTER TABLE `prestacionesp`
  MODIFY `IDPRESTACIONP` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `privilegios`
--
ALTER TABLE `privilegios`
  MODIFY `IDPRIVILEGIO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `IDPRODUCTO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `IDPROVEEDOR` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `salidascaja`
--
ALTER TABLE `salidascaja`
  MODIFY `IDSALIDACAJA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `IDUSUARIO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vacaciones`
--
ALTER TABLE `vacaciones`
  MODIFY `IDVACACION` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vales`
--
ALTER TABLE `vales`
  MODIFY `IDVALE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  MODIFY `IDVEHICULO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vehiculosseg`
--
ALTER TABLE `vehiculosseg`
  MODIFY `IDVEHICULOSEG` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vehiculosser`
--
ALTER TABLE `vehiculosser`
  MODIFY `IDVEHICULOSER` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `abonos`
--
ALTER TABLE `abonos`
  ADD CONSTRAINT `abonos_ibfk_1` FOREIGN KEY (`IDEMPLEADO`) REFERENCES `empleados` (`IDEMPLEADO`);

--
-- Filtros para la tabla `ahorros`
--
ALTER TABLE `ahorros`
  ADD CONSTRAINT `ahorros_ibfk_1` FOREIGN KEY (`IDEMPLEADO`) REFERENCES `empleados` (`IDEMPLEADO`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `direcciones`
--
ALTER TABLE `direcciones`
  ADD CONSTRAINT `direcciones_ibfk_1` FOREIGN KEY (`IDCLIENTE`) REFERENCES `clientes` (`IDCLIENTE`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `nominas`
--
ALTER TABLE `nominas`
  ADD CONSTRAINT `nominas_ibfk_1` FOREIGN KEY (`IDEMPLEADO`) REFERENCES `empleados` (`IDEMPLEADO`);

--
-- Filtros para la tabla `ordenesp`
--
ALTER TABLE `ordenesp`
  ADD CONSTRAINT `ordenesp_ibfk_1` FOREIGN KEY (`IDPENDIENTE`) REFERENCES `pendientes` (`IDPENDIENTE`),
  ADD CONSTRAINT `ordenesp_ibfk_2` FOREIGN KEY (`IDEMPLEADO`) REFERENCES `empleados` (`IDEMPLEADO`);

--
-- Filtros para la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`IDEMPLEADO`) REFERENCES `empleados` (`IDEMPLEADO`);
--
-- Base de datos: `test_mtell`
--
CREATE DATABASE IF NOT EXISTS `test_mtell` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci;
USE `test_mtell`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito`
--

CREATE TABLE `carrito` (
  `IDCARRITO` int(11) NOT NULL,
  `IDPEDIDO` int(11) NOT NULL,
  `IDPRODUCTO` int(11) NOT NULL,
  `CANTIDAD` int(11) NOT NULL,
  `COMENTARIOS` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `CATEGORIA` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `IDCLIENTE` int(11) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `TEL1` varchar(16) DEFAULT NULL,
  `TEL2` varchar(16) DEFAULT NULL,
  `TEL3` varchar(16) DEFAULT NULL,
  `EMAIL` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `componentes`
--

CREATE TABLE `componentes` (
  `IDCOMPONENTE` int(11) NOT NULL,
  `IDPRODUCTO` int(11) NOT NULL,
  `DESCRIPCION` varchar(150) NOT NULL,
  `CANTIDAD` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direcciones`
--

CREATE TABLE `direcciones` (
  `IDDIRECCION` int(11) NOT NULL,
  `IDCLIENTE` int(11) NOT NULL,
  `CALLE` varchar(100) DEFAULT NULL,
  `NUMERO` varchar(30) DEFAULT NULL,
  `COLONIA` varchar(100) DEFAULT NULL,
  `MUNICIPIO` varchar(100) DEFAULT NULL,
  `ESTADO` varchar(50) DEFAULT NULL,
  `REFERENCIA` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `IDPEDIDO` int(11) NOT NULL,
  `IDCLIENTE` int(11) NOT NULL,
  `IDDIRECCION` int(11) NOT NULL,
  `FREG` varchar(16) NOT NULL,
  `FFIN` varchar(16) NOT NULL,
  `ESTATUS` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `IDPRODUCTO` int(11) NOT NULL,
  `IDPROVEEDOR` int(11) NOT NULL,
  `CATEGORIA` varchar(50) NOT NULL,
  `DESCRIPCION` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `IDPROVEEDOR` int(11) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `TEL1` varchar(16) DEFAULT NULL,
  `TEL2` varchar(16) DEFAULT NULL,
  `TEL3` varchar(16) DEFAULT NULL,
  `EMAIL` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD PRIMARY KEY (`IDCARRITO`),
  ADD KEY `IDPEDIDO` (`IDPEDIDO`),
  ADD KEY `IDPRODUCTO` (`IDPRODUCTO`);

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`CATEGORIA`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`IDCLIENTE`);

--
-- Indices de la tabla `componentes`
--
ALTER TABLE `componentes`
  ADD PRIMARY KEY (`IDCOMPONENTE`),
  ADD KEY `IDPRODUCTO` (`IDPRODUCTO`);

--
-- Indices de la tabla `direcciones`
--
ALTER TABLE `direcciones`
  ADD PRIMARY KEY (`IDDIRECCION`),
  ADD UNIQUE KEY `IDDIRECCION` (`IDDIRECCION`),
  ADD KEY `IDCLIENTE` (`IDCLIENTE`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`IDPEDIDO`),
  ADD KEY `IDCLIENTE` (`IDCLIENTE`),
  ADD KEY `IDDIRECCION` (`IDDIRECCION`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`IDPRODUCTO`),
  ADD KEY `IDPROVEEDOR` (`IDPROVEEDOR`),
  ADD KEY `CATEGORIA` (`CATEGORIA`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`IDPROVEEDOR`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `carrito`
--
ALTER TABLE `carrito`
  MODIFY `IDCARRITO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `IDCLIENTE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `componentes`
--
ALTER TABLE `componentes`
  MODIFY `IDCOMPONENTE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `direcciones`
--
ALTER TABLE `direcciones`
  MODIFY `IDDIRECCION` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `IDPEDIDO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `IDPRODUCTO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `IDPROVEEDOR` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD CONSTRAINT `carrito_ibfk_1` FOREIGN KEY (`IDPEDIDO`) REFERENCES `pedidos` (`IDPEDIDO`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `carrito_ibfk_2` FOREIGN KEY (`IDPRODUCTO`) REFERENCES `productos` (`IDPRODUCTO`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `componentes`
--
ALTER TABLE `componentes`
  ADD CONSTRAINT `componentes_ibfk_1` FOREIGN KEY (`IDPRODUCTO`) REFERENCES `productos` (`IDPRODUCTO`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `direcciones`
--
ALTER TABLE `direcciones`
  ADD CONSTRAINT `direcciones_ibfk_1` FOREIGN KEY (`IDCLIENTE`) REFERENCES `clientes` (`IDCLIENTE`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`IDCLIENTE`) REFERENCES `clientes` (`IDCLIENTE`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pedidos_ibfk_2` FOREIGN KEY (`IDDIRECCION`) REFERENCES `direcciones` (`IDDIRECCION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`IDPROVEEDOR`) REFERENCES `proveedores` (`IDPROVEEDOR`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `productos_ibfk_2` FOREIGN KEY (`CATEGORIA`) REFERENCES `categorias` (`CATEGORIA`) ON DELETE CASCADE ON UPDATE CASCADE;
--
-- Base de datos: `traveleres`
--
CREATE DATABASE IF NOT EXISTS `traveleres` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE `traveleres`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aerolineas`
--

CREATE TABLE `aerolineas` (
  `IDAEROLINEA` int(11) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cajas`
--

CREATE TABLE `cajas` (
  `IDCAJA` int(11) NOT NULL,
  `DESCRIPCION` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `IDCLIENTE` int(11) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL,
  `TEL` varchar(15) NOT NULL,
  `DOMICILIO` varchar(150) NOT NULL,
  `CIUDAD` varchar(150) NOT NULL,
  `ESTADO` varchar(50) NOT NULL,
  `EMAIL` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contratos`
--

CREATE TABLE `contratos` (
  `IDCONTRATO` int(11) NOT NULL,
  `IDCLIENTE` int(11) NOT NULL,
  `IDPAQUETE` int(11) NOT NULL,
  `FREG` varchar(15) NOT NULL,
  `VCLAVER` varchar(100) DEFAULT NULL,
  `HCLAVER` varchar(100) DEFAULT NULL,
  `TCLAVER` varchar(100) DEFAULT NULL,
  `TOCLAVER` varchar(100) DEFAULT NULL,
  `RACLAVER` varchar(100) DEFAULT NULL,
  `OCLAVER` varchar(100) DEFAULT NULL,
  `NVIAJEROS` int(11) NOT NULL,
  `NADULTOS` int(11) NOT NULL,
  `NNINOS` int(11) NOT NULL,
  `FLIMITE` varchar(15) NOT NULL,
  `VUELO` tinyint(1) NOT NULL,
  `HOTEL` tinyint(1) NOT NULL,
  `TRASLADO` tinyint(1) NOT NULL,
  `RAUTO` tinyint(1) NOT NULL,
  `TOUR` tinyint(1) NOT NULL,
  `OTROS` tinyint(1) NOT NULL,
  `PRECIOVENTA` float NOT NULL,
  `SALDO` float NOT NULL,
  `ESTATUS` varchar(15) NOT NULL,
  `IDCAJA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contratosd`
--

CREATE TABLE `contratosd` (
  `IDCONTRATOD` int(11) NOT NULL,
  `IDCONTRATO` int(11) NOT NULL,
  `COMENTARIO` varchar(1000) NOT NULL,
  `FECHA` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cortes`
--

CREATE TABLE `cortes` (
  `IDCORTE` int(11) NOT NULL,
  `MONTOINI` float NOT NULL,
  `FINI` varchar(15) DEFAULT NULL,
  `FFIN` varchar(15) DEFAULT NULL,
  `MONTOFIN` float DEFAULT NULL,
  `IDCAJA` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cortesd`
--

CREATE TABLE `cortesd` (
  `IDCORTED` int(11) NOT NULL,
  `IDCORTE` int(11) DEFAULT NULL,
  `IDPAGOI` int(11) DEFAULT NULL,
  `IDPAGOF` int(11) DEFAULT NULL,
  `IDENTRADAI` int(11) DEFAULT NULL,
  `IDENTRADAF` int(11) DEFAULT NULL,
  `IDSALIDAI` int(11) DEFAULT NULL,
  `IDSALIDAF` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `destinos`
--

CREATE TABLE `destinos` (
  `IDDESTINO` int(11) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalles`
--

CREATE TABLE `detalles` (
  `IDDETALLE` int(11) NOT NULL,
  `DIASPRE` int(11) NOT NULL,
  `CONCEPTO` varchar(150) NOT NULL,
  `SMINIMO` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `documentos`
--

CREATE TABLE `documentos` (
  `IDDOC` int(11) NOT NULL,
  `IDCONT` int(11) NOT NULL,
  `TIPO` varchar(30) NOT NULL,
  `RUTA` varchar(1000) NOT NULL,
  `COMENTARIO` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `edetalles`
--

CREATE TABLE `edetalles` (
  `IDEDETALLE` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `PINI` varchar(15) NOT NULL,
  `PFIN` varchar(15) NOT NULL,
  `VPAGADAS` tinyint(1) NOT NULL,
  `APAGADOS` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `IDEMPLEADO` int(11) NOT NULL,
  `NOMBRE` varchar(50) DEFAULT NULL,
  `APELLIDOS` varchar(50) DEFAULT NULL,
  `TEL` varchar(20) DEFAULT NULL,
  `SUELDO` float NOT NULL,
  `EMPRESA` varchar(60) DEFAULT NULL,
  `BONO` float NOT NULL,
  `FECHA` varchar(30) NOT NULL,
  `ESTADO` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entradas`
--

CREATE TABLE `entradas` (
  `IDENTRADA` int(11) NOT NULL,
  `DESCRIPCION` varchar(300) NOT NULL,
  `MONTO` float NOT NULL,
  `CATEGORIA` varchar(50) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `METODO` varchar(15) NOT NULL,
  `IDCAJA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hoteles`
--

CREATE TABLE `hoteles` (
  `IDHOTEL` int(11) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nominas`
--

CREATE TABLE `nominas` (
  `IDNOMINA` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `SEMANA` int(11) NOT NULL,
  `DIAS` int(11) NOT NULL,
  `NSUELDO` double NOT NULL,
  `BONO` double NOT NULL,
  `CONCEPTO` varchar(300) NOT NULL,
  `IDAHORRO` int(11) NOT NULL,
  `IDABONO` int(11) NOT NULL,
  `TOTAL` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `IDPAGO` int(11) NOT NULL,
  `IDCONTRATO` int(11) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `METODO` varchar(15) NOT NULL,
  `MONTO` float NOT NULL,
  `COMENTARIO` varchar(150) NOT NULL,
  `IDCAJA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagosp`
--

CREATE TABLE `pagosp` (
  `IDPAGOP` int(11) NOT NULL,
  `IDCONTRATO` int(11) NOT NULL,
  `IDPROVEEDOR` int(11) NOT NULL,
  `IDAEROLINEA` int(11) NOT NULL,
  `CATEGORIA` varchar(30) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `METODO` varchar(30) NOT NULL,
  `MONTO` float NOT NULL,
  `COMENTARIO` varchar(150) NOT NULL,
  `IDCAJA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paquetes`
--

CREATE TABLE `paquetes` (
  `IDPAQUETE` int(11) NOT NULL,
  `DESTINO` varchar(150) NOT NULL,
  `FSALIDA` varchar(15) NOT NULL,
  `FREGRESO` varchar(15) NOT NULL,
  `AEROLINEA` varchar(150) DEFAULT NULL,
  `VPROVEEDOR` varchar(150) DEFAULT NULL,
  `VCOSTO` float DEFAULT NULL,
  `VCLAVER` tinyint(1) DEFAULT NULL,
  `HOTEL` varchar(150) DEFAULT NULL,
  `HPROVEEDOR` varchar(150) DEFAULT NULL,
  `HCOSTO` float DEFAULT NULL,
  `HCLAVER` tinyint(1) DEFAULT NULL,
  `TPROVEEDOR` varchar(150) DEFAULT NULL,
  `TCONCEPTO` varchar(300) DEFAULT NULL,
  `TCOSTO` float DEFAULT NULL,
  `TCLAVER` tinyint(1) DEFAULT NULL,
  `TOPROVEEDOR` varchar(150) DEFAULT NULL,
  `TOCONCEPTO` varchar(300) DEFAULT NULL,
  `TOCOSTO` float DEFAULT NULL,
  `TOCLAVER` tinyint(1) DEFAULT NULL,
  `RAPROVEEDOR` varchar(150) DEFAULT NULL,
  `RACONCEPTO` varchar(300) DEFAULT NULL,
  `RACOSTO` float DEFAULT NULL,
  `RACLAVER` tinyint(1) DEFAULT NULL,
  `OPROVEEDOR` varchar(150) DEFAULT NULL,
  `OCONCEPTO` varchar(150) DEFAULT NULL,
  `OCOSTO` float DEFAULT NULL,
  `OCLAVER` tinyint(1) DEFAULT NULL,
  `COSTOINI` float NOT NULL,
  `COSTOFIN` float DEFAULT NULL,
  `PRECIOPUBLICO` float DEFAULT NULL,
  `DESCRIPCION` varchar(1000) DEFAULT NULL,
  `FLIMITE` varchar(15) DEFAULT NULL,
  `ESTATUS` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `privilegios`
--

CREATE TABLE `privilegios` (
  `IDPRIVILEGIO` int(11) NOT NULL,
  `GRUPO` varchar(15) NOT NULL,
  `ITEM` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `IDPROVEEDOR` int(11) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL,
  `TEL` varchar(15) NOT NULL,
  `EMAIL` varchar(150) NOT NULL,
  `WEB` varchar(150) NOT NULL,
  `CATEGORIA` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salidas`
--

CREATE TABLE `salidas` (
  `IDSALIDA` int(11) NOT NULL,
  `DESCRIPCION` varchar(1000) NOT NULL,
  `MONTO` float NOT NULL,
  `CATEGORIA` varchar(50) NOT NULL,
  `FECHA` varchar(15) NOT NULL,
  `METODO` varchar(30) NOT NULL,
  `IDCAJA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `traslados`
--

CREATE TABLE `traslados` (
  `IDTRASLADO` int(11) NOT NULL,
  `NOMBRE` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `IDUSUARIO` int(11) NOT NULL,
  `NOMBRE` varchar(10) DEFAULT NULL,
  `PASSWORD` varchar(30) DEFAULT NULL,
  `PRIVILEGIOS` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vacaciones`
--

CREATE TABLE `vacaciones` (
  `IDVACACION` int(11) NOT NULL,
  `IDEMPLEADO` int(11) NOT NULL,
  `FREG` varchar(15) NOT NULL,
  `FSALIDA` varchar(15) NOT NULL,
  `FREGRESO` varchar(15) NOT NULL,
  `DIAST` int(11) NOT NULL,
  `COMENTARIOS` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vacacionestab`
--

CREATE TABLE `vacacionestab` (
  `IDVACACIONTAB` int(11) NOT NULL,
  `DIAS` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viajeros`
--

CREATE TABLE `viajeros` (
  `IDVIAJERO` int(11) NOT NULL,
  `IDCONTRATO` int(11) NOT NULL,
  `NOMBRE` varchar(150) NOT NULL,
  `CURP` varchar(30) NOT NULL,
  `FNACIMIENTO` varchar(15) NOT NULL,
  `EDAD` int(11) NOT NULL,
  `PASAPORTE` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `aerolineas`
--
ALTER TABLE `aerolineas`
  ADD PRIMARY KEY (`IDAEROLINEA`);

--
-- Indices de la tabla `cajas`
--
ALTER TABLE `cajas`
  ADD PRIMARY KEY (`IDCAJA`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`IDCLIENTE`);

--
-- Indices de la tabla `contratos`
--
ALTER TABLE `contratos`
  ADD PRIMARY KEY (`IDCONTRATO`);

--
-- Indices de la tabla `contratosd`
--
ALTER TABLE `contratosd`
  ADD PRIMARY KEY (`IDCONTRATOD`);

--
-- Indices de la tabla `cortes`
--
ALTER TABLE `cortes`
  ADD PRIMARY KEY (`IDCORTE`);

--
-- Indices de la tabla `cortesd`
--
ALTER TABLE `cortesd`
  ADD PRIMARY KEY (`IDCORTED`);

--
-- Indices de la tabla `destinos`
--
ALTER TABLE `destinos`
  ADD PRIMARY KEY (`IDDESTINO`);

--
-- Indices de la tabla `detalles`
--
ALTER TABLE `detalles`
  ADD PRIMARY KEY (`IDDETALLE`);

--
-- Indices de la tabla `documentos`
--
ALTER TABLE `documentos`
  ADD PRIMARY KEY (`IDDOC`);

--
-- Indices de la tabla `edetalles`
--
ALTER TABLE `edetalles`
  ADD PRIMARY KEY (`IDEDETALLE`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`IDEMPLEADO`);

--
-- Indices de la tabla `entradas`
--
ALTER TABLE `entradas`
  ADD PRIMARY KEY (`IDENTRADA`);

--
-- Indices de la tabla `hoteles`
--
ALTER TABLE `hoteles`
  ADD PRIMARY KEY (`IDHOTEL`);

--
-- Indices de la tabla `nominas`
--
ALTER TABLE `nominas`
  ADD PRIMARY KEY (`IDNOMINA`);

--
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD PRIMARY KEY (`IDPAGO`);

--
-- Indices de la tabla `pagosp`
--
ALTER TABLE `pagosp`
  ADD PRIMARY KEY (`IDPAGOP`);

--
-- Indices de la tabla `paquetes`
--
ALTER TABLE `paquetes`
  ADD PRIMARY KEY (`IDPAQUETE`);

--
-- Indices de la tabla `privilegios`
--
ALTER TABLE `privilegios`
  ADD PRIMARY KEY (`IDPRIVILEGIO`),
  ADD UNIQUE KEY `IDPRIVILEGIO` (`IDPRIVILEGIO`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`IDPROVEEDOR`);

--
-- Indices de la tabla `salidas`
--
ALTER TABLE `salidas`
  ADD PRIMARY KEY (`IDSALIDA`);

--
-- Indices de la tabla `traslados`
--
ALTER TABLE `traslados`
  ADD PRIMARY KEY (`IDTRASLADO`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`IDUSUARIO`);

--
-- Indices de la tabla `vacaciones`
--
ALTER TABLE `vacaciones`
  ADD PRIMARY KEY (`IDVACACION`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `aerolineas`
--
ALTER TABLE `aerolineas`
  MODIFY `IDAEROLINEA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cajas`
--
ALTER TABLE `cajas`
  MODIFY `IDCAJA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `IDCLIENTE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `contratos`
--
ALTER TABLE `contratos`
  MODIFY `IDCONTRATO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `contratosd`
--
ALTER TABLE `contratosd`
  MODIFY `IDCONTRATOD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cortesd`
--
ALTER TABLE `cortesd`
  MODIFY `IDCORTED` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `destinos`
--
ALTER TABLE `destinos`
  MODIFY `IDDESTINO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalles`
--
ALTER TABLE `detalles`
  MODIFY `IDDETALLE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `documentos`
--
ALTER TABLE `documentos`
  MODIFY `IDDOC` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `entradas`
--
ALTER TABLE `entradas`
  MODIFY `IDENTRADA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `hoteles`
--
ALTER TABLE `hoteles`
  MODIFY `IDHOTEL` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pagos`
--
ALTER TABLE `pagos`
  MODIFY `IDPAGO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pagosp`
--
ALTER TABLE `pagosp`
  MODIFY `IDPAGOP` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `paquetes`
--
ALTER TABLE `paquetes`
  MODIFY `IDPAQUETE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `privilegios`
--
ALTER TABLE `privilegios`
  MODIFY `IDPRIVILEGIO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `IDPROVEEDOR` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `salidas`
--
ALTER TABLE `salidas`
  MODIFY `IDSALIDA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `IDUSUARIO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vacaciones`
--
ALTER TABLE `vacaciones`
  MODIFY `IDVACACION` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
