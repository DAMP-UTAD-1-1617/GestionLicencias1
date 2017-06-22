-- phpMyAdmin SQL Dump
-- version 4.4.12
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-06-2017 a las 09:01:19
-- Versión del servidor: 5.6.25
-- Versión de PHP: 5.6.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestion_licencias_v3`
--
CREATE DATABASE IF NOT EXISTS `gestion_licencias_v3` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `gestion_licencias_v3`;

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `cambioTitular`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `cambioTitular`(
	IN `p_fecha` VARCHAR(50),
	IN `p_licencia` INT

)
BEGIN

UPDATE titularidad
SET fecha_fin = p_fecha
WHERE solicitud = p_licencia
AND fecha_fin is NULL;

CALL getUltimoIdPersona(@ultimoId);

INSERT INTO titularidad(persona, solicitud, fecha_inicio) VALUES (@ultimoId,p_licencia,p_fecha);

END$$

DROP PROCEDURE IF EXISTS `crearUsuario`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `crearUsuario`(IN `p_dni` VARCHAR(10), IN `p_nombre` VARCHAR(100))
    NO SQL
BEGIN
	
    INSERT INTO login(dni,nombre)
    VALUES(p_dni, p_nombre);
    
END$$

DROP PROCEDURE IF EXISTS `dni`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `dni`(IN `p_dni` VARCHAR(10), OUT `p_rows` INT)
BEGIN
	SELECT COUNT(nombre) INTO p_rows
	FROM login
	WHERE dni = p_dni;
END$$

DROP PROCEDURE IF EXISTS `editarUsuario`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `editarUsuario`(IN `p_id` INT, IN `p_dni` VARCHAR(10), IN `p_nombre` VARCHAR(100))
    NO SQL
BEGIN

UPDATE login
SET dni = p_dni,
nombre = p_nombre
WHERE id = p_id;

END$$

DROP PROCEDURE IF EXISTS `eliminarUsuario`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `eliminarUsuario`(IN `p_id` INT)
    NO SQL
BEGIN

DELETE FROM login
WHERE id = p_id;


END$$

DROP PROCEDURE IF EXISTS `getIdRepresentante`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `getIdRepresentante`(IN `p_so` INT, OUT `id` INT, OUT `idRep` INT)
BEGIN

SELECT persona INTO id
FROM titularidad
WHERE solicitud = p_so;

SELECT representante INTO idRep
FROM persona
WHERE id_persona = id;

END$$

DROP PROCEDURE IF EXISTS `getLicencias`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `getLicencias`()
    NO SQL
BEGIN

    SELECT so.id_solicitud, DATE_FORMAT(so.fecha,'%d/%m/%Y'), so.estado,

    so.tipo, so.desc_actividad, so.emplazamiento, so.ref_catastral, so.tipo_suelo, so.cuota,
    so.fotocopia_dni, so.fotocopia_imp_actividades, so.fotografias, so.fotocopia_escritura, so.justificante_pago,
    so.memoria_actividad, so.planos, so.licencia_obra, so.certificado1, so.certificado2, so.cd, so.otras_autorizaciones, so.certificado_colegio_oficial, so.fecha_inicio
    FROM solicitud AS so
    WHERE so.estado <> 'Archivada';

END$$

DROP PROCEDURE IF EXISTS `getPersona`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `getPersona`(IN `p_so` INT(11), OUT `id` INT(11))
BEGIN

SELECT persona INTO id
FROM titularidad
WHERE solicitud = p_so;

END$$

DROP PROCEDURE IF EXISTS `getRepresentante`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `getRepresentante`(IN `p_persona` INT)
    NO SQL
BEGIN

SELECT re.id_representante, re.nombre, re.nif_nie, re.direccion, re.municipio, re.cp, re.tlf_fijo, re.tlf_movil, re.tlf_movil, re.fax, re.email
FROM representante AS re
JOIN persona AS per
ON per.representante = re.id_representante
WHERE per.id_persona = p_persona;

END$$

DROP PROCEDURE IF EXISTS `getTitulares`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `getTitulares`(
	IN `p_id` INT


)
    NO SQL
BEGIN

SELECT per.id_persona, per.nombre, per.cif, per.direccion,
per.municipio, per.cp, per.tlf_fijo, per.tlf_movil, per.fax,
per.email, per.tipo, per.estructura_propiedad, per.planos_distribucion, 
per.licencia_anterior, per.numero_expediente, per.primero, IFNULL(ti.fecha_fin, '') as fecha_fin
FROM persona as per
JOIN titularidad as ti
ON per.id_persona = ti.persona
WHERE ti.solicitud = p_id;

END$$

DROP PROCEDURE IF EXISTS `getUltimoIdPersona`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `getUltimoIdPersona`(OUT `id` INT)
    NO SQL
BEGIN

SELECT MAX(id_persona) INTO id
FROM persona;

END$$

DROP PROCEDURE IF EXISTS `getUltimoIdRepresentante`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `getUltimoIdRepresentante`(OUT `id` INT)
    NO SQL
BEGIN

SELECT MAX(id_representante) INTO id
FROM representante;

END$$

DROP PROCEDURE IF EXISTS `getUsuarios`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `getUsuarios`()
    NO SQL
BEGIN
	
    SELECT lo.id, lo.dni, lo.nombre
    FROM login AS lo;
    
END$$

DROP PROCEDURE IF EXISTS `insertarRepresentante`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `insertarRepresentante`(IN `rep_nombre` VARCHAR(100), IN `rep_nif` VARCHAR(10), IN `rep_direccion` VARCHAR(300), IN `rep_municipio` VARCHAR(100), IN `rep_cp` VARCHAR(10), IN `rep_fijo` INT, IN `rep_movil` INT, IN `rep_fax` INT, IN `rep_email` VARCHAR(100))
    NO SQL
BEGIN

INSERT INTO representante(nombre, nif_nie, direccion, municipio, cp, tlf_fijo, tlf_movil, fax, email)
VALUES (rep_nombre,rep_nif,rep_direccion,rep_municipio,rep_cp,rep_fijo,rep_movil,rep_fax,rep_email);

END$$

DROP PROCEDURE IF EXISTS `insertarSolicitud`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `insertarSolicitud`(IN `so_id` INT, IN `so_fecha` VARCHAR(10), IN `so_tipo` VARCHAR(100), IN `so_desc` VARCHAR(500), IN `so_emp` VARCHAR(200), IN `so_ref` VARCHAR(150), IN `so_suelo` VARCHAR(20), IN `so_cuota` FLOAT, IN `so_doc1` INT, IN `so_doc2` INT, IN `so_doc3` INT, IN `so_doc4` INT, IN `so_doc5` INT, IN `so_doc6` INT, IN `so_doc7` INT, IN `so_doc8` INT, IN `so_doc9` INT, IN `so_doc10` INT, IN `so_doc11` INT, IN `so_doc12` INT, IN `so_doc13` INT)
BEGIN

CALL getUltimoIdPersona(@ultimoId);

INSERT INTO solicitud(id_solicitud, fecha, tipo, desc_actividad, emplazamiento, ref_catastral, tipo_suelo, cuota, fotocopia_dni, fotocopia_imp_actividades, fotografias, fotocopia_escritura, justificante_pago, memoria_actividad, planos, licencia_obra, certificado1, certificado2, cd, otras_autorizaciones, certificado_colegio_oficial) 
VALUES (so_id,so_fecha,so_tipo,so_desc,so_emp,so_ref,so_suelo,so_cuota,so_doc1,so_doc2,so_doc3,so_doc4,so_doc5,so_doc6,so_doc7,so_doc8,so_doc9,so_doc10,so_doc11,so_doc12,so_doc13);

INSERT INTO titularidad(persona, solicitud, fecha_inicio) VALUES (@ultimoId,so_id,so_fecha);

END$$

DROP PROCEDURE IF EXISTS `insertarTitularConRep`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `insertarTitularConRep`(
	IN `ti_nombre` VARCHAR(100),
	IN `ti_cif` VARCHAR(10),
	IN `ti_direccion` VARCHAR(300),
	IN `ti_municipio` VARCHAR(100),
	IN `ti_cp` VARCHAR(10),
	IN `ti_fijo` INT,
	IN `ti_movil` INT,
	IN `ti_fax` INT,
	IN `ti_email` VARCHAR(100),
	IN `ti_tipo` VARCHAR(20),
	IN `ti_doc1` INT,
	IN `ti_doc2` INT,
	IN `ti_doc3` INT,
	IN `ti_doc4` INT,
	IN `ti_primero` INT


)
    NO SQL
BEGIN

CALL getUltimoIdRepresentante(@id);

INSERT INTO persona(nombre, cif, direccion, municipio, cp, tlf_fijo, tlf_movil, fax, email, tipo, representante, estructura_propiedad, planos_distribucion, licencia_anterior, numero_expediente, primero) 
VALUES (ti_nombre,ti_cif,ti_direccion,ti_municipio,ti_cp,ti_fijo,ti_movil,ti_fax,ti_email,ti_tipo,@id, ti_doc1, ti_doc2, ti_doc3, ti_doc4, ti_primero);

END$$

DROP PROCEDURE IF EXISTS `insertarTitularSinRep`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `insertarTitularSinRep`(
	IN `ti_nombre` VARCHAR(100),
	IN `ti_cif` VARCHAR(10),
	IN `ti_direccion` VARCHAR(300),
	IN `ti_municipio` VARCHAR(100),
	IN `ti_cp` VARCHAR(10),
	IN `ti_fijo` INT,
	IN `ti_movil` INT,
	IN `ti_fax` INT,
	IN `ti_email` VARCHAR(100),
	IN `ti_tipo` VARCHAR(20),
	IN `ti_doc1` INT,
	IN `ti_doc2` INT,
	IN `ti_doc3` INT,
	IN `ti_doc4` INT,
	IN `ti_primero` INT

)
    NO SQL
BEGIN

INSERT INTO persona(nombre, cif, direccion, municipio, cp, tlf_fijo, tlf_movil, fax, email, tipo, estructura_propiedad, planos_distribucion, licencia_anterior, numero_expediente, primero) 
VALUES (ti_nombre,ti_cif,ti_direccion,ti_municipio,ti_cp,ti_fijo,ti_movil,ti_fax,ti_email,ti_tipo,ti_doc1,ti_doc2,ti_doc3,ti_doc4,ti_primero);

END$$

DROP PROCEDURE IF EXISTS `login`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `login`(IN `p_dni` VARCHAR(10))
BEGIN
	CALL dni(p_dni, @countRows);
	IF @countRows > 0 THEN
		SELECT nombre
		FROM login
		WHERE dni = p_dni;
	ELSE
		SELECT "-1";
	END IF;
END$$

DROP PROCEDURE IF EXISTS `ultimoNumRegistro`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `ultimoNumRegistro`()
    NO SQL
BEGIN

SELECT (MAX(id_solicitud) + 1)
FROM solicitud;

END$$

DROP PROCEDURE IF EXISTS `updatePersona`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `updatePersona`(
	IN `ti_id` INT,
	IN `ti_nombre` VARCHAR(100),
	IN `ti_cif` VARCHAR(10),
	IN `ti_direccion` VARCHAR(300),
	IN `ti_municipio` VARCHAR(100),
	IN `ti_cp` VARCHAR(10),
	IN `ti_fijo` INT,
	IN `ti_movil` INT,
	IN `ti_fax` INT,
	IN `ti_email` VARCHAR(100),
	IN `ti_tipo` VARCHAR(20)
,
	IN `ti_doc1` INT,
	IN `ti_doc2` INT,
	IN `ti_doc3` INT,
	IN `ti_doc4` INT
)
BEGIN

UPDATE `persona` 
SET `nombre`=ti_nombre,
`cif`=ti_cif,
`direccion`=ti_direccion,
`municipio`=ti_municipio,
`cp`=ti_cp,
`tlf_fijo`=ti_fijo,
`tlf_movil`=ti_movil,
`fax`=ti_fax,
`email`=ti_email,
`tipo`=ti_tipo,
`estructura_propiedad`=ti_doc1,
`planos_distribucion`=ti_doc2,
`licencia_anterior`=ti_doc3,
`numero_expediente`=ti_doc4
WHERE id_persona = ti_id;

END$$

DROP PROCEDURE IF EXISTS `updateRepPersona`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `updateRepPersona`(
	IN `rep_id` INT
)
BEGIN

CALL getUltimoIdRepresentante(@id);

UPDATE persona
SET representante = @id
WHERE id_persona = rep_id;

END$$

DROP PROCEDURE IF EXISTS `updateRepresentante`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `updateRepresentante`(
	IN `rep_id` INT,
	IN `rep_nombre` VARCHAR(100),
	IN `rep_nif` VARCHAR(10),
	IN `rep_direccion` VARCHAR(300),
	IN `rep_municipio` VARCHAR(100),
	IN `rep_cp` VARCHAR(10),
	IN `rep_fijo` INT,
	IN `rep_movil` INT,
	IN `rep_fax` INT,
	IN `rep_email` VARCHAR(100)
)
BEGIN

UPDATE `representante` 
SET `nombre`=rep_nombre,
`nif_nie`=rep_nif,
`direccion`=rep_direccion,
`municipio`=rep_municipio,
`cp`=rep_cp,
`tlf_fijo`=rep_fijo,
`tlf_movil`=rep_movil,
`fax`=rep_fax,
`email`=rep_email 
WHERE id_representante = rep_id;

END$$

DROP PROCEDURE IF EXISTS `updateSolicitud`$$
CREATE DEFINER=`sa`@`localhost` PROCEDURE `updateSolicitud`(IN `so_id` INT, IN `so_fecha` VARCHAR(10), IN `so_tipo` VARCHAR(100), IN `so_desc` VARCHAR(500), IN `so_emp` VARCHAR(200), IN `so_ref` VARCHAR(150), IN `so_suelo` VARCHAR(20), IN `so_cuota` FLOAT, IN `so_estado` VARCHAR(20), IN `so_doc1` INT, IN `so_doc2` INT, IN `so_doc3` INT, IN `so_doc4` INT, IN `so_doc5` INT, IN `so_doc6` INT, IN `so_doc7` INT, IN `so_doc8` INT, IN `so_doc9` INT, IN `so_doc10` INT, IN `so_doc11` INT, IN `so_doc12` INT, IN `so_doc13` INT)
BEGIN

UPDATE `solicitud` 
SET `fecha`=so_fecha,
`tipo`=so_tipo,
`desc_actividad`=so_desc,
`emplazamiento`=so_emp,
`ref_catastral`=so_ref,
`tipo_suelo`=so_suelo,
`cuota`=so_cuota,
`estado`=so_estado,
`fotocopia_dni`=so_doc1,
`fotocopia_imp_actividades`=so_doc2,
`fotografias`=so_doc3,
`fotocopia_escritura`=so_doc4,
`justificante_pago`=so_doc5,
`memoria_actividad`=so_doc6,
`planos`=so_doc7,
`licencia_obra`=so_doc8,
`certificado1`=so_doc9,
`certificado2`=so_doc10,
`cd`=so_doc11,
`otras_autorizaciones`=so_doc12,
`certificado_colegio_oficial`=so_doc13 
WHERE id_solicitud = so_id;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE IF NOT EXISTS `login` (
  `id` int(11) NOT NULL,
  `dni` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `login`
--

INSERT INTO `login` (`id`, `dni`, `nombre`) VALUES
(1, '9999', 'Developer');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

DROP TABLE IF EXISTS `persona`;
CREATE TABLE IF NOT EXISTS `persona` (
  `id_persona` int(11) NOT NULL,
  `nombre` varchar(100) CHARACTER SET latin1 NOT NULL,
  `cif` varchar(15) CHARACTER SET latin1 NOT NULL,
  `direccion` varchar(250) CHARACTER SET latin1 NOT NULL,
  `municipio` varchar(50) CHARACTER SET latin1 NOT NULL,
  `cp` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `tlf_fijo` int(11) NOT NULL,
  `tlf_movil` int(11) NOT NULL,
  `fax` int(11) NOT NULL,
  `email` varchar(100) CHARACTER SET latin1 NOT NULL,
  `tipo` enum('Física','Jurídica') CHARACTER SET latin1 NOT NULL,
  `representante` int(11) DEFAULT NULL,
  `estructura_propiedad` tinyint(1) NOT NULL,
  `planos_distribucion` tinyint(1) NOT NULL,
  `licencia_anterior` tinyint(1) NOT NULL,
  `numero_expediente` tinyint(1) NOT NULL,
  `primero` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci ROW_FORMAT=COMPACT;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id_persona`, `nombre`, `cif`, `direccion`, `municipio`, `cp`, `tlf_fijo`, `tlf_movil`, `fax`, `email`, `tipo`, `representante`, `estructura_propiedad`, `planos_distribucion`, `licencia_anterior`, `numero_expediente`, `primero`) VALUES
(1, 'Alberto Jacinto', '12345678B', 'Rojas', 'Madrid', '29130', 952202122, 655467865, 952202122, 'alberto@utad.com', 'Jurídica', 3, 0, 0, 0, 0, 1),
(2, 'Zireael Cosmetics', 'N0032484H', 'Calle Granadina', 'Barcelona', '27896', 902118132, 722356476, 902118132, 'support@zireael.com', 'Jurídica', 1, 0, 0, 0, 0, 1),
(3, 'Iván Gálvez', '12346578B', 'Calle Cercedilla', 'Getafe', '27054', 918765435, 765897101, 918765435, 'Ivan@utad.com', 'Física', NULL, 0, 0, 0, 0, 1),
(4, 'Pablo Montiel', '12346579P', 'Calle Pináculo', 'Villaverde', '27043', 922890708, 647197465, 922890708, 'lonewar@hotmail.com', 'Física', NULL, 0, 0, 0, 0, 1),
(5, 'Alejandro Martín', '1234678A', 'Calle Liencres', 'Torrijos', '27032', 911432567, 645789007, 911432567, 'skaynx@outlook.es', 'Física', NULL, 0, 0, 0, 0, 1),
(6, 'Hyemin', 'Q0999094F', 'Palo Alto', 'Madrid', '28097', 902657897, 766897006, 902657897, 'beautybl@naver.com', 'Jurídica', 2, 0, 0, 0, 0, 1),
(7, 'Javier Aranda', '45623368Q', 'Av América', 'Málaga', '29017', 951789546, 666708709, 951789546, 'siruvls@gmail.com', 'Física', NULL, 0, 0, 0, 0, 1),
(8, 'José Luis', '43923874S', 'Av Europa', 'Málaga', '29015', 952404152, 644887902, 952404152, 'JL92@gmail.com', 'Física', NULL, 0, 0, 0, 0, 1),
(9, '1', '1', '1', '1', '1', 1, 1, 1, '1', 'Jurídica', 4, 1, 1, 0, 0, 0),
(10, '3', '3', '3', '3', '3', 3, 3, 3, '3', 'Física', NULL, 1, 1, 1, 1, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `representante`
--

DROP TABLE IF EXISTS `representante`;
CREATE TABLE IF NOT EXISTS `representante` (
  `id_representante` int(11) NOT NULL,
  `nombre` varchar(100) CHARACTER SET latin1 NOT NULL,
  `nif_nie` varchar(15) CHARACTER SET latin1 NOT NULL,
  `direccion` varchar(250) CHARACTER SET latin1 NOT NULL,
  `municipio` varchar(100) CHARACTER SET latin1 NOT NULL,
  `cp` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `tlf_fijo` int(11) NOT NULL,
  `tlf_movil` int(11) NOT NULL,
  `fax` int(11) NOT NULL,
  `email` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci ROW_FORMAT=COMPACT;

--
-- Volcado de datos para la tabla `representante`
--

INSERT INTO `representante` (`id_representante`, `nombre`, `nif_nie`, `direccion`, `municipio`, `cp`, `tlf_fijo`, `tlf_movil`, `fax`, `email`) VALUES
(1, 'Noemí Martín', '12346578A', 'Calle Lichi', 'Villata', '28030', 911115678, 722356476, 911115678, 'mitsuki@gmail.com'),
(2, 'Carolina Pineda', '38269239E', 'Calle Villanueva Baja', 'Girona', '27045', 902456789, 655005609, 902456789, 'carrol@live.outlook.com'),
(3, 'Ivan Galvez', '565456', 'Holis', 'Villata', '28021', 54654, 46546, 495665, 'jiji@hola.com'),
(4, '2', '2', '2', '2', '2', 2, 2, 2, '2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud`
--

DROP TABLE IF EXISTS `solicitud`;
CREATE TABLE IF NOT EXISTS `solicitud` (
  `id_solicitud` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `tipo` varchar(100) CHARACTER SET latin1 NOT NULL,
  `desc_actividad` varchar(500) CHARACTER SET latin1 NOT NULL,
  `emplazamiento` varchar(200) CHARACTER SET latin1 NOT NULL,
  `ref_catastral` varchar(150) CHARACTER SET latin1 NOT NULL,
  `tipo_suelo` enum('Urbano','Rústico') CHARACTER SET latin1 NOT NULL,
  `fecha_inicio` date NOT NULL,
  `cuota` float NOT NULL,
  `estado` enum('En proceso','Aceptada','Rechazada','Archivada') CHARACTER SET latin1 NOT NULL,
  `fotocopia_dni` tinyint(1) NOT NULL,
  `fotocopia_imp_actividades` tinyint(1) NOT NULL,
  `fotografias` tinyint(1) NOT NULL,
  `fotocopia_escritura` tinyint(1) NOT NULL,
  `justificante_pago` tinyint(1) NOT NULL,
  `memoria_actividad` tinyint(1) NOT NULL,
  `planos` tinyint(1) NOT NULL,
  `licencia_obra` tinyint(1) NOT NULL,
  `certificado1` tinyint(1) NOT NULL,
  `certificado2` tinyint(1) NOT NULL,
  `cd` tinyint(1) NOT NULL,
  `otras_autorizaciones` tinyint(1) NOT NULL,
  `certificado_colegio_oficial` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci ROW_FORMAT=COMPACT;

--
-- Volcado de datos para la tabla `solicitud`
--

INSERT INTO `solicitud` (`id_solicitud`, `fecha`, `tipo`, `desc_actividad`, `emplazamiento`, `ref_catastral`, `tipo_suelo`, `fecha_inicio`, `cuota`, `estado`, `fotocopia_dni`, `fotocopia_imp_actividades`, `fotografias`, `fotocopia_escritura`, `justificante_pago`, `memoria_actividad`, `planos`, `licencia_obra`, `certificado1`, `certificado2`, `cd`, `otras_autorizaciones`, `certificado_colegio_oficial`) VALUES
(1, '2017-04-06', 'Ocio', 'Concierto', 'Madrid', 'IksnMKen', 'Urbano', '0000-00-00', 200, 'En proceso', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
(2, '2017-06-01', 'Indoor', 'Showreel', 'Madrid', 'sKPkeNm', 'Urbano', '0000-00-00', 220, 'Aceptada', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
(3, '2017-05-02', 'Ocio', 'Casino', 'Interior', 'PKneRtm', 'Urbano', '0000-00-00', 1200, 'En proceso', 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0),
(4, '2017-05-31', 'Outdoor', 'Exposición pintura', 'Málaga', 'KVneRtk', 'Rústico', '0000-00-00', 1212, 'En proceso', 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0),
(5, '2017-05-27', 'Ocio', 'Batalla de gallos', 'Madrid', 'UyNHem', 'Urbano', '0000-00-00', 2023, 'Rechazada', 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(6, '2017-05-30', 'Indoor', 'Exposición', 'Casa de campo', 'KNoPe', 'Rústico', '0000-00-00', 987, 'Rechazada', 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1),
(7, '2017-05-22', 'Outdoor', 'Parkour training', 'Pabellón Azul', 'nSJemL', 'Urbano', '0000-00-00', 220, 'En proceso', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
(8, '2017-05-27', 'Asamblea', 'Reunión AA', 'Edificio Negro', 'mJKyNo', 'Urbano', '0000-00-00', 465, 'En proceso', 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `titularidad`
--

DROP TABLE IF EXISTS `titularidad`;
CREATE TABLE IF NOT EXISTS `titularidad` (
  `persona` int(11) NOT NULL,
  `solicitud` int(11) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci ROW_FORMAT=COMPACT;

--
-- Volcado de datos para la tabla `titularidad`
--

INSERT INTO `titularidad` (`persona`, `solicitud`, `fecha_inicio`, `fecha_fin`) VALUES
(1, 1, '2017-05-31', NULL),
(2, 2, '2017-06-01', NULL),
(3, 3, '2017-05-02', NULL),
(4, 4, '2017-05-31', NULL),
(5, 5, '2017-05-27', NULL),
(6, 6, '2017-05-30', NULL),
(7, 7, '2017-05-22', NULL),
(8, 8, '2017-05-27', '2017-06-19'),
(9, 8, '2017-06-19', '2017-06-21'),
(10, 8, '2017-06-21', NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id_persona`),
  ADD KEY `representante` (`representante`);

--
-- Indices de la tabla `representante`
--
ALTER TABLE `representante`
  ADD PRIMARY KEY (`id_representante`);

--
-- Indices de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD PRIMARY KEY (`id_solicitud`);

--
-- Indices de la tabla `titularidad`
--
ALTER TABLE `titularidad`
  ADD PRIMARY KEY (`persona`,`solicitud`),
  ADD KEY `persona` (`persona`),
  ADD KEY `actividad` (`solicitud`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id_persona` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `representante`
--
ALTER TABLE `representante`
  MODIFY `id_representante` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  MODIFY `id_solicitud` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `persona`
--
ALTER TABLE `persona`
  ADD CONSTRAINT `persona_ibfk_1` FOREIGN KEY (`representante`) REFERENCES `representante` (`id_representante`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `titularidad`
--
ALTER TABLE `titularidad`
  ADD CONSTRAINT `titularidad_ibfk_1` FOREIGN KEY (`persona`) REFERENCES `persona` (`id_persona`),
  ADD CONSTRAINT `titularidad_ibfk_2` FOREIGN KEY (`solicitud`) REFERENCES `solicitud` (`id_solicitud`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
