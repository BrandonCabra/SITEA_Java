-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 09-12-2025 a las 00:26:07
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sitea4`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `acciones`
--

CREATE TABLE `acciones` (
  `ID_ACCIONES` int(11) NOT NULL,
  `NOMBRE_ACCION` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `acciones`
--

INSERT INTO `acciones` (`ID_ACCIONES`, `NOMBRE_ACCION`) VALUES
(1, 'CREAR'),
(2, 'LEER'),
(3, 'MODIFICAR'),
(4, 'ELIMINAR'),
(5, 'GESTIONAR'),
(6, 'RECOMENDAR'),
(7, 'INGRESAR');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad_clase`
--

CREATE TABLE `actividad_clase` (
  `ID_ACTIVIDAD_CLASE` int(11) NOT NULL,
  `PIAR_CODIGO_PIAR` int(11) NOT NULL,
  `PERIODO_ACTIVIDAD_CLASE` varchar(45) DEFAULT NULL,
  `FECHA_ACTIVIDAD_CLASE` date NOT NULL,
  `DUA_ACTIVIDAD_CLASE` varchar(200) DEFAULT NULL,
  `DESCRIPCION_ACTIVIDAD_CLASE` varchar(500) NOT NULL,
  `DIARIO_PEDAGOGICO` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `area`
--

CREATE TABLE `area` (
  `ID_AREA` int(11) NOT NULL,
  `NOMBRE_AREA` varchar(45) NOT NULL,
  `DESCRIPCION` varchar(200) DEFAULT NULL,
  `PLAN_AREA` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `area`
--

INSERT INTO `area` (`ID_AREA`, `NOMBRE_AREA`, `DESCRIPCION`, `PLAN_AREA`) VALUES
(1, 'CIENCIAS_NATURALES_EDUCACION_AMBIENTAL', NULL, NULL),
(2, 'CIENCIAS_SOCIALES', NULL, NULL),
(3, 'HUMANIDADES_LENGUAJE', NULL, NULL),
(4, 'MATEMATICAS', NULL, NULL),
(5, 'EDUCACION_ARTISTICA_CULTURAL', NULL, NULL),
(6, 'EDUCACION_RELIGIOSA', NULL, NULL),
(7, 'EDUCACION_FISICA_DEPORTIVA', NULL, NULL),
(8, 'TECNOLOGIA_INFORMATICA', NULL, NULL),
(9, 'EDUCACION_ETICA', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `audit_estudiante`
--

CREATE TABLE `audit_estudiante` (
  `audit_id` bigint(20) NOT NULL,
  `estudiante_id` bigint(20) DEFAULT NULL,
  `operation_type` char(1) NOT NULL,
  `old_estado_registro` varchar(20) DEFAULT NULL,
  `new_estado_registro` varchar(20) DEFAULT NULL,
  `old_expediente_id` varchar(30) DEFAULT NULL,
  `new_expediente_id` varchar(30) DEFAULT NULL,
  `changed_by` int(11) DEFAULT NULL,
  `changed_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `boletin_academico`
--

CREATE TABLE `boletin_academico` (
  `ID_BOLETIN_ACADEMICO` int(11) NOT NULL,
  `FECHA_BOLETIN` date DEFAULT NULL,
  `PERIODO_BOLETIN` varchar(45) NOT NULL,
  `OBSERVACION_PROF` varchar(45) NOT NULL,
  `ESTADO_BOLETIN` varchar(45) DEFAULT NULL,
  `PROMEDIO` varchar(45) DEFAULT NULL,
  `RECOMENDACIONES` varchar(45) DEFAULT NULL,
  `NOVEDADES_REPORTES_ID_NOVEDADES_REPORTES` int(11) DEFAULT NULL,
  `ESTUDIANTE_ID_ESTUDIANTE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `caracterizacion`
--

CREATE TABLE `caracterizacion` (
  `ID_CARACTERIZACION` int(11) NOT NULL,
  `CODIGO_CARACTERIZACION` varchar(100) NOT NULL,
  `ESTUDIANTE_ID_ESTUDIANTE` int(11) NOT NULL,
  `CONTEXTO_ACADEMICO` varchar(200) NOT NULL,
  `CONTEXTO_FAMILIAR` varchar(200) NOT NULL,
  `CONTEXTO_ESCOLAR` varchar(200) NOT NULL,
  `DIAGNOSTICO` varchar(255) NOT NULL,
  `VALORACION_PEDAGOGICA` varchar(300) NOT NULL,
  `BARRA_DE_APRENDIZAJE` varchar(200) NOT NULL,
  `RECOMENDACIONES` varchar(500) NOT NULL,
  `CORRESPONSABILIDAD` varchar(255) NOT NULL,
  `RECOMENDACIONES_IA` longtext DEFAULT NULL,
  `expediente_caracterizacion` varchar(30) DEFAULT NULL COMMENT 'Expediente único formato CHAR-TEA-YYYY-####',
  `estado_caracterizacion` varchar(20) DEFAULT 'INICIADA' COMMENT 'Estados: INICIADA, EN_PROCESO, COMPLETADA, ARCHIVADA',
  `fecha_inicio` timestamp NOT NULL DEFAULT current_timestamp() COMMENT 'Fecha de inicio del proceso',
  `FECHA_FINALIZACION` datetime DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() COMMENT 'Fecha de creación del registro',
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Fecha de última actualización',
  `created_by` int(11) DEFAULT NULL COMMENT 'ID del usuario que creó el registro',
  `updated_by` int(11) DEFAULT NULL COMMENT 'ID del usuario que actualizó el registro'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `caracterizacion`
--

INSERT INTO `caracterizacion` (`ID_CARACTERIZACION`, `CODIGO_CARACTERIZACION`, `ESTUDIANTE_ID_ESTUDIANTE`, `CONTEXTO_ACADEMICO`, `CONTEXTO_FAMILIAR`, `CONTEXTO_ESCOLAR`, `DIAGNOSTICO`, `VALORACION_PEDAGOGICA`, `BARRA_DE_APRENDIZAJE`, `RECOMENDACIONES`, `CORRESPONSABILIDAD`, `RECOMENDACIONES_IA`, `expediente_caracterizacion`, `estado_caracterizacion`, `fecha_inicio`, `FECHA_FINALIZACION`, `created_at`, `updated_at`, `created_by`, `updated_by`) VALUES
(2, '20251145328856', 1, 'BAJO NIVEL', 'APOYO FAMILIAR', 'DIFICULTAD LECTURA', 'DISLEXIA', 'REALIZAR PIAR', 'CONTEXTUAL Y SOCIAL', 'APOYO VISUALES', 'OPORTUNA', NULL, NULL, 'INICIADA', '2025-12-02 04:59:47', NULL, '2025-12-02 04:59:47', '2025-12-02 04:59:47', NULL, NULL),
(3, '987654321-2025', 21, 'estudiante con asignaturas perdidas', 'poco apoyo familiar', 'sufre de bulling', 'Dislexia', 'necesita fortalecer procesos de diferenciacion de b y d', 'contextual', 'profesores reforzar tareas y tener flexibilidad en la escritura', 'padres firman', NULL, NULL, 'EN_PROCESO', '2025-12-02 05:43:47', NULL, '2025-12-02 05:43:47', '2025-12-02 05:43:47', NULL, NULL),
(4, '1053450788_2025', 2, 'presenta dificultades para llegar a la institución, además de presentar riesgo de deserción escolar', 'Poco apoyo, familia disfuncional', 'presenta dificultades en las áreas de matemáticas y lenguaje', 'se detecta además inicios de TDAH y depresión', 'realizar flexibilizacion en las tareas', 'contextual\r\neconomicas', 'dar apoyo grupal y fortalecer relaciones con los padres', 'estan dispuestos a firmar y apoyar', NULL, NULL, 'EN_PROCESO', '2025-12-02 20:16:53', NULL, '2025-12-02 20:16:53', '2025-12-02 20:16:53', NULL, NULL),
(5, 'CHAR-TEA-2025-0033', 10, 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', NULL, 'CHAR-TEA-2025-0033', 'INICIADA', '2025-12-05 00:40:43', NULL, '2025-12-05 00:40:43', '2025-12-05 00:40:43', NULL, NULL),
(6, 'CHAR-TEA-2025-0034', 3, 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', NULL, 'CHAR-TEA-2025-0034', 'INICIADA', '2025-12-05 01:50:03', NULL, '2025-12-05 01:50:03', '2025-12-05 01:50:03', NULL, NULL),
(7, 'CHAR-TEA-2025-0035', 4, 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', NULL, 'CHAR-TEA-2025-0035', 'INICIADA', '2025-12-05 04:20:58', NULL, '2025-12-05 04:20:58', '2025-12-05 04:20:58', NULL, NULL),
(8, 'CHAR-TEA-2025-0036', 4, 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', NULL, 'CHAR-TEA-2025-0036', 'INICIADA', '2025-12-05 04:25:00', NULL, '2025-12-05 04:25:00', '2025-12-05 04:25:00', NULL, NULL),
(9, 'CHAR-TEA-2025-0037', 5, 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', NULL, 'CHAR-TEA-2025-0037', 'INICIADA', '2025-12-05 04:38:24', NULL, '2025-12-05 04:38:24', '2025-12-05 04:38:24', NULL, NULL),
(10, 'CHAR-TEA-2025-0038', 15, 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', NULL, 'CHAR-TEA-2025-0038', 'INICIADA', '2025-12-05 04:49:35', NULL, '2025-12-05 04:49:35', '2025-12-05 04:49:35', NULL, NULL),
(11, 'CHAR-TEA-2025-0039', 6, 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', NULL, 'CHAR-TEA-2025-0039', 'INICIADA', '2025-12-05 05:04:15', NULL, '2025-12-05 05:04:15', '2025-12-05 05:04:15', NULL, NULL),
(12, 'CHAR-TEA-2025-0040', 8, 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', NULL, 'CHAR-TEA-2025-0040', 'INICIADA', '2025-12-05 05:50:11', NULL, '2025-12-05 05:50:11', '2025-12-05 05:50:11', NULL, NULL),
(13, 'CHAR-TEA-2025-0041', 8, 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', NULL, 'CHAR-TEA-2025-0041', 'INICIADA', '2025-12-05 06:05:18', NULL, '2025-12-05 06:05:18', '2025-12-05 06:05:18', NULL, NULL),
(14, 'CHAR-TEA-2025-0042', 9, 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', NULL, 'CHAR-TEA-2025-0042', 'INICIADA', '2025-12-05 06:17:55', NULL, '2025-12-05 06:17:55', '2025-12-05 06:17:55', NULL, NULL),
(17, 'CHAR-TEA-2025-0045', 14, 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', NULL, 'CHAR-TEA-2025-0045', 'INICIADA', '2025-12-05 09:48:29', NULL, '2025-12-05 09:48:29', '2025-12-05 09:48:29', NULL, NULL),
(18, 'CHAR-TEA-2025-0046', 13, 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', NULL, 'CHAR-TEA-2025-0046', 'INICIADA', '2025-12-06 03:48:01', NULL, '2025-12-06 03:48:01', '2025-12-06 03:48:01', NULL, NULL),
(19, 'CHAR-TEA-2025-0047', 16, 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', NULL, 'CHAR-TEA-2025-0047', 'INICIADA', '2025-12-06 16:28:02', NULL, '2025-12-06 16:28:02', '2025-12-06 16:28:02', NULL, NULL),
(20, 'CHAR-TEA-2025-0048', 17, 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', NULL, 'CHAR-TEA-2025-0048', 'INICIADA', '2025-12-07 17:16:17', NULL, '2025-12-07 17:16:17', '2025-12-07 17:16:17', NULL, NULL),
(21, 'CHAR-TEA-2025-0049', 19, 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', NULL, 'CHAR-TEA-2025-0049', 'INICIADA', '2025-12-08 06:38:03', NULL, '2025-12-08 06:38:03', '2025-12-08 06:38:03', NULL, NULL),
(22, 'CHAR-TEA-2025-0050', 25, 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', NULL, 'CHAR-TEA-2025-0050', 'INICIADA', '2025-12-08 22:57:51', NULL, '2025-12-08 22:57:51', '2025-12-08 22:57:51', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contexto_escolar`
--

CREATE TABLE `contexto_escolar` (
  `id_contexto_escolar` int(11) NOT NULL,
  `id_caracterizacion` int(11) NOT NULL,
  `infraestructura` text DEFAULT NULL,
  `accesibilidad` text DEFAULT NULL,
  `recursos` text DEFAULT NULL,
  `ambiente` text DEFAULT NULL,
  `observaciones_docentes` text DEFAULT NULL,
  `barreras_aprendizaje` text DEFAULT NULL,
  `recomendaciones_institucionales` text DEFAULT NULL,
  `otras_notas` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `contexto_escolar`
--

INSERT INTO `contexto_escolar` (`id_contexto_escolar`, `id_caracterizacion`, `infraestructura`, `accesibilidad`, `recursos`, `ambiente`, `observaciones_docentes`, `barreras_aprendizaje`, `recomendaciones_institucionales`, `otras_notas`, `created_at`, `updated_at`, `created_by`, `updated_by`) VALUES
(1, 2, 'DIFICULTAD LECTURA', NULL, NULL, NULL, 'REALIZAR PIAR', 'CONTEXTUAL Y SOCIAL', 'APOYO VISUALES', NULL, '2025-12-02 04:59:47', '2025-12-02 04:59:47', NULL, NULL),
(2, 3, 'sufre de bulling', NULL, NULL, NULL, 'necesita fortalecer procesos de diferenciacion de b y d', 'contextual', 'profesores reforzar tareas y tener flexibilidad en la escritura', NULL, '2025-12-02 05:43:47', '2025-12-02 05:43:47', NULL, NULL),
(3, 4, 'presenta dificultades en las áreas de matemáticas y lenguaje', NULL, NULL, NULL, 'realizar flexibilizacion en las tareas', 'contextual\r\neconomicas', 'dar apoyo grupal y fortalecer relaciones con los padres', NULL, '2025-12-02 20:16:53', '2025-12-02 20:16:53', NULL, NULL),
(4, 5, 'PENDIENTE', NULL, NULL, NULL, 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', NULL, '2025-12-05 00:40:43', '2025-12-05 00:40:43', NULL, NULL),
(5, 6, 'PENDIENTE', NULL, NULL, NULL, 'PENDIENTE', 'PENDIENTE', 'PENDIENTE', NULL, '2025-12-05 01:50:03', '2025-12-05 01:50:03', NULL, NULL),
(6, 11, 'sin espacios verdes', 'espacios sin accesibilidad y lugares peligrosos', 'los salones estan dotados', NULL, 'solo un docente de apoyo', 'contextual', 'realizar geocercas para lugares peligrosos', NULL, '2025-12-05 05:05:32', '2025-12-05 05:05:32', NULL, NULL),
(7, 12, 'COMPLETA', 'CUMPLE CON ESTANDARES', 'TIENE MATERIALES ADAPTABLES AMBIENTE APROPIADO', 'presentes para cada área', 'CADA SALON CUENTA CON DOCENTE DE APOYO', 'CULTURALES', 'MANTENER CONDICIONES', NULL, '2025-12-05 06:02:00', '2025-12-05 06:02:00', NULL, NULL),
(8, 13, 'COMPLETA', 'CUMPLE CON ESTANDARES', 'TIENE MATERIALES ADAPTABLES AMBIENTE APROPIADO', 'presentes para cada área', 'CADA SALON CUENTA CON DOCENTE DE APOYO', 'CULTURALES', 'MANTENER CONDICIONES', NULL, '2025-12-05 06:05:25', '2025-12-05 06:05:25', NULL, NULL),
(9, 14, 'correcta', 'poca', 'basicos', 'no aplica', 'falta apoyo', 'contextual', 'mejorar', NULL, '2025-12-05 06:18:34', '2025-12-05 06:18:34', NULL, NULL),
(11, 17, 'aceptable', 'inclusiva', 'apropiada', 'pocos', 'bien apropiada', 'contextual', 'flexibilizacion', NULL, '2025-12-05 09:48:43', '2025-12-05 09:48:43', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contexto_familiar`
--

CREATE TABLE `contexto_familiar` (
  `ID_CONTEXTO_FAMILIAR` int(11) NOT NULL,
  `CARACTERIZACION_ID` int(11) NOT NULL,
  `ACUDIENTE_NOMBRE` varchar(120) DEFAULT NULL,
  `ACUDIENTE_DOCUMENTO` varchar(20) DEFAULT NULL,
  `ACUDIENTE_TELEFONO` varchar(20) DEFAULT NULL,
  `ACUDIENTE_EMAIL` varchar(120) DEFAULT NULL,
  `ACUDIENTE_PARENTESCO` varchar(45) DEFAULT NULL,
  `MADRE_NOMBRE` varchar(120) DEFAULT NULL,
  `MADRE_DOCUMENTO` varchar(20) DEFAULT NULL,
  `MADRE_TELEFONO` varchar(20) DEFAULT NULL,
  `MADRE_EMAIL` varchar(120) DEFAULT NULL,
  `MADRE_OCUPACION` varchar(150) DEFAULT NULL,
  `MADRE_ESCOLARIDAD` varchar(20) DEFAULT NULL,
  `PADRE_NOMBRE` varchar(120) DEFAULT NULL,
  `PADRE_DOCUMENTO` varchar(20) DEFAULT NULL,
  `PADRE_TELEFONO` varchar(20) DEFAULT NULL,
  `PADRE_EMAIL` varchar(120) DEFAULT NULL,
  `PADRE_OCUPACION` varchar(150) DEFAULT NULL,
  `PADRE_ESCOLARIDAD` varchar(20) DEFAULT NULL,
  `OTROS_FAMILIARES` longtext DEFAULT NULL,
  `RELACIONES_FAMILIARES` longtext DEFAULT NULL,
  `COMUNICACION_FAMILIAR` longtext DEFAULT NULL,
  `TIPO_VIVIENDA` varchar(45) DEFAULT NULL,
  `TENENCIA_VIVIENDA` varchar(45) DEFAULT NULL,
  `CONDICIONES_VIVIENDA` longtext DEFAULT NULL,
  `SITUACION_ECONOMICA` longtext DEFAULT NULL,
  `OBSERVACIONES_FAMILIA` longtext DEFAULT NULL,
  `CREATED_AT` timestamp NOT NULL DEFAULT current_timestamp(),
  `UPDATED_AT` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `CREATED_BY` int(11) DEFAULT NULL,
  `UPDATED_BY` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `contexto_familiar`
--

INSERT INTO `contexto_familiar` (`ID_CONTEXTO_FAMILIAR`, `CARACTERIZACION_ID`, `ACUDIENTE_NOMBRE`, `ACUDIENTE_DOCUMENTO`, `ACUDIENTE_TELEFONO`, `ACUDIENTE_EMAIL`, `ACUDIENTE_PARENTESCO`, `MADRE_NOMBRE`, `MADRE_DOCUMENTO`, `MADRE_TELEFONO`, `MADRE_EMAIL`, `MADRE_OCUPACION`, `MADRE_ESCOLARIDAD`, `PADRE_NOMBRE`, `PADRE_DOCUMENTO`, `PADRE_TELEFONO`, `PADRE_EMAIL`, `PADRE_OCUPACION`, `PADRE_ESCOLARIDAD`, `OTROS_FAMILIARES`, `RELACIONES_FAMILIARES`, `COMUNICACION_FAMILIAR`, `TIPO_VIVIENDA`, `TENENCIA_VIVIENDA`, `CONDICIONES_VIVIENDA`, `SITUACION_ECONOMICA`, `OBSERVACIONES_FAMILIA`, `CREATED_AT`, `UPDATED_AT`, `CREATED_BY`, `UPDATED_BY`) VALUES
(1, 11, 'CLARA MARTINEZ', '1052395000', '3158102820', 'bjcabrab@gmail.com', 'Padre', 'CLARA MARTINEZ', '1052395000', '3158102820', 'bjcabrab@gmail.com', 'ama de casa', 'Técnica', 'Rafael Duarte', '1052395100', '3158102820', 'sitea.edu@gmail.com', 'independiente', 'Técnica', 'abuelitos', 'presenta disfunción familiar', 'con la mamá quien es la que esta pendiente de la estudiante', 'Apartamento', 'Arrendada', 'con lo necesario', 'media baja', 'en la casa ayuda en el negocio del papá', '2025-12-05 05:21:59', '2025-12-05 05:21:59', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso`
--

CREATE TABLE `curso` (
  `ID_CURSO` int(11) NOT NULL,
  `CODIGO_CURSO` int(11) NOT NULL,
  `CANTIDAD_ESTUDIANTE` int(11) DEFAULT NULL,
  `GRADO_ID_GRADO` int(11) DEFAULT NULL,
  `ID_INSTITUCION_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `curso`
--

INSERT INTO `curso` (`ID_CURSO`, `CODIGO_CURSO`, `CANTIDAD_ESTUDIANTE`, `GRADO_ID_GRADO`, `ID_INSTITUCION_ID`) VALUES
(1, 1, 25, 1, 1),
(2, 2, 25, 1, 1),
(3, 3, 25, 1, 1),
(4, 4, 25, 1, 1),
(5, 101, 25, 2, 1),
(6, 102, 30, 2, 1),
(7, 103, 30, 2, 1),
(8, 104, 30, 2, 1),
(9, 201, 30, 3, 1),
(10, 202, 30, 3, 1),
(11, 203, 30, 3, 1),
(12, 204, 30, 3, 1),
(13, 301, 30, 4, 1),
(14, 302, 30, 4, 1),
(15, 303, 30, 4, 1),
(16, 304, 30, 4, 1),
(17, 401, 30, 5, 1),
(18, 402, 30, 5, 1),
(19, 403, 30, 5, 1),
(20, 404, 30, 5, 1),
(21, 501, 30, 6, 1),
(22, 502, 30, 6, 1),
(23, 503, 30, 6, 1),
(24, 504, 30, 6, 1),
(25, 601, 30, 7, 1),
(26, 602, 30, 7, 1),
(27, 603, 30, 7, 1),
(28, 604, 30, 7, 1),
(29, 701, 30, 8, 1),
(30, 702, 30, 8, 1),
(31, 703, 30, 8, 1),
(32, 704, 30, 8, 1),
(33, 801, 30, 9, 1),
(34, 802, 30, 9, 1),
(35, 803, 30, 9, 1),
(36, 804, 30, 9, 1),
(37, 901, 30, 10, 1),
(38, 902, 30, 10, 1),
(39, 903, 30, 10, 1),
(40, 904, 30, 10, 1),
(41, 1001, 30, 11, 1),
(42, 1002, 30, 11, 1),
(43, 1003, 30, 11, 1),
(44, 1004, 30, 11, 1),
(45, 1101, 30, 12, 1),
(46, 1102, 30, 12, 1),
(47, 1103, 30, 12, 1),
(48, 1104, 30, 12, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dba`
--

CREATE TABLE `dba` (
  `ID_DBA` int(11) NOT NULL,
  `AREA_DBA` int(11) NOT NULL,
  `GRADO` int(11) DEFAULT NULL,
  `PERIODO` int(11) DEFAULT NULL,
  `OBJETIVO_ENUNCIADO` varchar(400) DEFAULT NULL,
  `EVIDENCIA` varchar(400) DEFAULT NULL,
  `EJEMPLO` varchar(400) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `dba`
--

INSERT INTO `dba` (`ID_DBA`, `AREA_DBA`, `GRADO`, `PERIODO`, `OBJETIVO_ENUNCIADO`, `EVIDENCIA`, `EJEMPLO`) VALUES
(1, 1, 2, 1, 'Comprende que los sentidos le permiten percibir algunas características de los objetos que nos rodean (temperatura, sabor, sonidos, olor, color, texturas y formas)', 'Describe colores, olores, texturas; usa lupa para observar.', 'Observa una hoja con lupa y la dibuja.'),
(2, 1, 2, 2, 'Comprende que existe una gran variedad de materiales y que éstos se utilizan para distintos fines, según sus características (longitud, dureza, flexibilidad, permeabilidad al agua, solubilidad, ductilidad, maleabilidad, color, sabor, textura)', 'Clasifica materiales por propiedades sensoriales.', 'Elige goma para un borrador por su textura.'),
(3, 1, 2, 3, 'Comprende que los seres vivos (plantas y animales) tienen características comunes (se alimentan, respiran, tienen un ciclo de vida, responden al entorno) y los diferencia de los objetos inertes.', NULL, NULL),
(4, 1, 2, 4, 'Comprende que su cuerpo experimenta constantes cambios a lo largo del tiempo y reconoce a partir de su comparación que tiene características similares y diferentes a las de sus padres y compañeros.', NULL, NULL),
(5, 1, 3, NULL, 'Comprende que una acción mecánica (fuerza) puede producir distintas deformaciones en un objeto, y que este resiste a las fuerzas de diferente modo, de acuerdo con el material del que está hecho. ', 'Compara deformaciones en papel, plastilina y madera.', 'Plastilina se deforma fácilmente, la madera no.'),
(6, 1, 3, NULL, 'Comprende los estados de la materia.', 'Clasifica sólido, líquido y gas; reconoce el aire.', 'Infla un globo y muestra que el aire ocupa espacio.'),
(7, 1, 4, NULL, 'Comprende cómo se propaga la luz en distintos materiales.', 'Clasifica materiales por su interacción con la luz.', 'Usa papel translúcido para mostrar paso parcial de luz.'),
(8, 1, 4, NULL, 'Reconoce que los seres vivos se relacionan con su entorno.', 'Identifica relaciones en ecosistemas.', 'Dibuja cadena alimentaria en un jardín escolar.'),
(9, 1, 5, NULL, 'Entiende cómo las fuerzas afectan el movimiento.', NULL, NULL),
(10, 1, 5, NULL, 'Comprende los órganos de los sentidos y su cuidado.', NULL, NULL),
(11, 1, 6, NULL, 'Comprende funcionamiento básico de circuitos eléctricos.', NULL, NULL),
(12, 1, 6, NULL, 'Comprende el ciclo del agua y su importancia.', NULL, NULL),
(13, 1, 7, NULL, 'Comprende cómo temperatura y presión afectan materiales.', NULL, NULL),
(14, 1, 7, NULL, 'Comprende las funciones vitales en seres vivos.', NULL, NULL),
(15, 1, 8, NULL, 'Comprende transformación de energía en sistemas mecánicos.', NULL, NULL),
(16, 1, 8, NULL, 'Comprende formación de sustancias a partir de elementos.', NULL, NULL),
(17, 1, 9, NULL, 'Comprende el funcionamiento de máquinas térmicas.', NULL, NULL),
(18, 1, 9, NULL, 'Comprende que las reacciones químicas forman nuevas sustancias.', NULL, NULL),
(19, 1, 10, NULL, 'Comprende el movimiento y lo representa gráficamente.', NULL, NULL),
(20, 1, 10, NULL, 'Comprende propiedades ácido-base y sus aplicaciones.', NULL, NULL),
(21, 1, 11, NULL, 'Comprende las leyes de Newton del movimiento.', NULL, NULL),
(22, 1, 11, NULL, 'Comprende los tipos de reacciones químicas inorgánicas.', NULL, NULL),
(23, 1, 12, NULL, 'Comprende evolución y diversidad a partir de la genética.', NULL, NULL),
(24, 1, 12, NULL, 'Comprende el rol del ADN y su expresión en proteínas.', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dimension_valoracion`
--

CREATE TABLE `dimension_valoracion` (
  `ID_DIMENSION` int(11) NOT NULL COMMENT 'ID único de la dimensión',
  `CARACTERIZACION_ID` int(11) NOT NULL COMMENT 'ID de la caracterizacion asociada',
  `NOMBRE_DIMENSION` varchar(100) NOT NULL COMMENT 'Nombre de la dimensión según MEN',
  `DESCRIPCION` text DEFAULT NULL COMMENT 'Descripción detallada de la valoración',
  `FORTALEZAS` text DEFAULT NULL COMMENT 'Fortalezas identificadas en esta dimensión',
  `AREAS_APOYO` text DEFAULT NULL COMMENT 'Áreas que requieren apoyo',
  `PUNTUACION` int(11) DEFAULT NULL COMMENT 'Puntuación de la valoración (escala 1-5)',
  `ESTADO` varchar(20) DEFAULT 'PENDIENTE' COMMENT 'Estados: PENDIENTE, EN_PROCESO, COMPLETADA',
  `FECHA_VALORACION` timestamp NULL DEFAULT NULL COMMENT 'Fecha de valoración',
  `CREATED_AT` timestamp NOT NULL DEFAULT current_timestamp() COMMENT 'Fecha de creación',
  `UPDATED_AT` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Fecha de actualización'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Tabla para gestionar las 8 dimensiones de valoración según MEN';

--
-- Volcado de datos para la tabla `dimension_valoracion`
--

INSERT INTO `dimension_valoracion` (`ID_DIMENSION`, `CARACTERIZACION_ID`, `NOMBRE_DIMENSION`, `DESCRIPCION`, `FORTALEZAS`, `AREAS_APOYO`, `PUNTUACION`, `ESTADO`, `FECHA_VALORACION`, `CREATED_AT`, `UPDATED_AT`) VALUES
(1, 5, 'Contexto y vida familiar', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 00:40:43', '2025-12-05 00:40:43'),
(2, 5, 'Habilidades intelectuales', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 00:40:43', '2025-12-05 00:40:43'),
(3, 5, 'Bienestar emocional', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 00:40:43', '2025-12-05 00:40:43'),
(4, 5, 'Conducta adaptativa y desarrollo personal', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 00:40:43', '2025-12-05 00:40:43'),
(5, 5, 'Salud y bienestar físico', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 00:40:43', '2025-12-05 00:40:43'),
(6, 5, 'Participación e inclusión social', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 00:40:43', '2025-12-05 00:40:43'),
(7, 5, 'Control del propio entorno', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 00:40:43', '2025-12-05 00:40:43'),
(8, 5, 'Dimensión pedagógica', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 00:40:43', '2025-12-05 00:40:43'),
(9, 6, 'Contexto y vida familiar', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 01:50:03', '2025-12-05 01:50:03'),
(10, 6, 'Habilidades intelectuales', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 01:50:03', '2025-12-05 01:50:03'),
(11, 6, 'Bienestar emocional', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 01:50:03', '2025-12-05 01:50:03'),
(12, 6, 'Conducta adaptativa y desarrollo personal', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 01:50:03', '2025-12-05 01:50:03'),
(13, 6, 'Salud y bienestar físico', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 01:50:03', '2025-12-05 01:50:03'),
(14, 6, 'Participación e inclusión social', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 01:50:03', '2025-12-05 01:50:03'),
(15, 6, 'Control del propio entorno', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 01:50:03', '2025-12-05 01:50:03'),
(16, 6, 'Dimensión pedagógica', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 01:50:03', '2025-12-05 01:50:03'),
(17, 7, 'Contexto y vida familiar', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:20:58', '2025-12-05 04:20:58'),
(18, 7, 'Habilidades intelectuales', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:20:58', '2025-12-05 04:20:58'),
(19, 7, 'Bienestar emocional', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:20:58', '2025-12-05 04:20:58'),
(20, 7, 'Conducta adaptativa y desarrollo personal', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:20:58', '2025-12-05 04:20:58'),
(21, 7, 'Salud y bienestar físico', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:20:58', '2025-12-05 04:20:58'),
(22, 7, 'Participación e inclusión social', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:20:58', '2025-12-05 04:20:58'),
(23, 7, 'Control del propio entorno', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:20:58', '2025-12-05 04:20:58'),
(24, 7, 'Dimensión pedagógica', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:20:59', '2025-12-05 04:20:59'),
(25, 8, 'Contexto y vida familiar', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:25:00', '2025-12-05 04:25:00'),
(26, 8, 'Habilidades intelectuales', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:25:00', '2025-12-05 04:25:00'),
(27, 8, 'Bienestar emocional', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:25:00', '2025-12-05 04:25:00'),
(28, 8, 'Conducta adaptativa y desarrollo personal', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:25:00', '2025-12-05 04:25:00'),
(29, 8, 'Salud y bienestar físico', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:25:00', '2025-12-05 04:25:00'),
(30, 8, 'Participación e inclusión social', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:25:00', '2025-12-05 04:25:00'),
(31, 8, 'Control del propio entorno', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:25:00', '2025-12-05 04:25:00'),
(32, 8, 'Dimensión pedagógica', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:25:00', '2025-12-05 04:25:00'),
(33, 9, 'Contexto y vida familiar', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:38:24', '2025-12-05 04:38:24'),
(34, 9, 'Habilidades intelectuales', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:38:24', '2025-12-05 04:38:24'),
(35, 9, 'Bienestar emocional', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:38:24', '2025-12-05 04:38:24'),
(36, 9, 'Conducta adaptativa y desarrollo personal', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:38:24', '2025-12-05 04:38:24'),
(37, 9, 'Salud y bienestar físico', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:38:24', '2025-12-05 04:38:24'),
(38, 9, 'Participación e inclusión social', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:38:24', '2025-12-05 04:38:24'),
(39, 9, 'Control del propio entorno', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:38:24', '2025-12-05 04:38:24'),
(40, 9, 'Dimensión pedagógica', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:38:24', '2025-12-05 04:38:24'),
(41, 10, 'Contexto y vida familiar', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:49:35', '2025-12-05 04:49:35'),
(42, 10, 'Habilidades intelectuales', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:49:35', '2025-12-05 04:49:35'),
(43, 10, 'Bienestar emocional', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:49:35', '2025-12-05 04:49:35'),
(44, 10, 'Conducta adaptativa y desarrollo personal', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:49:35', '2025-12-05 04:49:35'),
(45, 10, 'Salud y bienestar físico', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:49:35', '2025-12-05 04:49:35'),
(46, 10, 'Participación e inclusión social', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:49:35', '2025-12-05 04:49:35'),
(47, 10, 'Control del propio entorno', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:49:35', '2025-12-05 04:49:35'),
(48, 10, 'Dimensión pedagógica', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 04:49:35', '2025-12-05 04:49:35'),
(49, 11, 'Contexto y vida familiar', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 05:04:15', '2025-12-05 05:04:15'),
(50, 11, 'Habilidades intelectuales', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 05:04:15', '2025-12-05 05:04:15'),
(51, 11, 'Bienestar emocional', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 05:04:15', '2025-12-05 05:04:15'),
(52, 11, 'Conducta adaptativa y desarrollo personal', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 05:04:15', '2025-12-05 05:04:15'),
(53, 11, 'Salud y bienestar físico', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 05:04:15', '2025-12-05 05:04:15'),
(54, 11, 'Participación e inclusión social', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 05:04:15', '2025-12-05 05:04:15'),
(55, 11, 'Control del propio entorno', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 05:04:15', '2025-12-05 05:04:15'),
(56, 11, 'Dimensión pedagógica', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 05:04:15', '2025-12-05 05:04:15'),
(57, 12, 'Contexto y vida familiar', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 05:50:11', '2025-12-05 05:50:11'),
(58, 12, 'Habilidades intelectuales', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 05:50:11', '2025-12-05 05:50:11'),
(59, 12, 'Bienestar emocional', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 05:50:11', '2025-12-05 05:50:11'),
(60, 12, 'Conducta adaptativa y desarrollo personal', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 05:50:11', '2025-12-05 05:50:11'),
(61, 12, 'Salud y bienestar físico', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 05:50:11', '2025-12-05 05:50:11'),
(62, 12, 'Participación e inclusión social', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 05:50:11', '2025-12-05 05:50:11'),
(63, 12, 'Control del propio entorno', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 05:50:11', '2025-12-05 05:50:11'),
(64, 12, 'Dimensión pedagógica', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 05:50:11', '2025-12-05 05:50:11'),
(65, 13, 'Contexto y vida familiar', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 06:05:18', '2025-12-05 06:05:18'),
(66, 13, 'Habilidades intelectuales', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 06:05:18', '2025-12-05 06:05:18'),
(67, 13, 'Bienestar emocional', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 06:05:18', '2025-12-05 06:05:18'),
(68, 13, 'Conducta adaptativa y desarrollo personal', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 06:05:18', '2025-12-05 06:05:18'),
(69, 13, 'Salud y bienestar físico', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 06:05:18', '2025-12-05 06:05:18'),
(70, 13, 'Participación e inclusión social', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 06:05:18', '2025-12-05 06:05:18'),
(71, 13, 'Control del propio entorno', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 06:05:18', '2025-12-05 06:05:18'),
(72, 13, 'Dimensión pedagógica', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 06:05:18', '2025-12-05 06:05:18'),
(73, 14, 'Contexto y vida familiar', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 06:17:55', '2025-12-05 06:17:55'),
(74, 14, 'Habilidades intelectuales', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 06:17:55', '2025-12-05 06:17:55'),
(75, 14, 'Bienestar emocional', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 06:17:55', '2025-12-05 06:17:55'),
(76, 14, 'Conducta adaptativa y desarrollo personal', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 06:17:55', '2025-12-05 06:17:55'),
(77, 14, 'Salud y bienestar físico', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 06:17:55', '2025-12-05 06:17:55'),
(78, 14, 'Participación e inclusión social', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 06:17:55', '2025-12-05 06:17:55'),
(79, 14, 'Control del propio entorno', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 06:17:55', '2025-12-05 06:17:55'),
(80, 14, 'Dimensión pedagógica', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 06:17:55', '2025-12-05 06:17:55'),
(97, 17, 'Contexto y vida familiar', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 09:48:29', '2025-12-05 09:48:29'),
(98, 17, 'Habilidades intelectuales', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 09:48:29', '2025-12-05 09:48:29'),
(99, 17, 'Bienestar emocional', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 09:48:29', '2025-12-05 09:48:29'),
(100, 17, 'Conducta adaptativa y desarrollo personal', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 09:48:29', '2025-12-05 09:48:29'),
(101, 17, 'Salud y bienestar físico', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 09:48:29', '2025-12-05 09:48:29'),
(102, 17, 'Participación e inclusión social', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 09:48:29', '2025-12-05 09:48:29'),
(103, 17, 'Control del propio entorno', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 09:48:29', '2025-12-05 09:48:29'),
(104, 17, 'Dimensión pedagógica', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-05 09:48:29', '2025-12-05 09:48:29'),
(105, 18, 'Contexto y vida familiar', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-06 03:48:01', '2025-12-06 03:48:01'),
(106, 18, 'Habilidades intelectuales', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-06 03:48:01', '2025-12-06 03:48:01'),
(107, 18, 'Bienestar emocional', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-06 03:48:01', '2025-12-06 03:48:01'),
(108, 18, 'Conducta adaptativa y desarrollo personal', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-06 03:48:01', '2025-12-06 03:48:01'),
(109, 18, 'Salud y bienestar físico', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-06 03:48:01', '2025-12-06 03:48:01'),
(110, 18, 'Participación e inclusión social', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-06 03:48:01', '2025-12-06 03:48:01'),
(111, 18, 'Control del propio entorno', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-06 03:48:01', '2025-12-06 03:48:01'),
(112, 18, 'Dimensión pedagógica', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-06 03:48:01', '2025-12-06 03:48:01'),
(113, 19, 'Contexto y vida familiar', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-06 16:28:02', '2025-12-06 16:28:02'),
(114, 19, 'Habilidades intelectuales', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-06 16:28:02', '2025-12-06 16:28:02'),
(115, 19, 'Bienestar emocional', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-06 16:28:02', '2025-12-06 16:28:02'),
(116, 19, 'Conducta adaptativa y desarrollo personal', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-06 16:28:02', '2025-12-06 16:28:02'),
(117, 19, 'Salud y bienestar físico', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-06 16:28:02', '2025-12-06 16:28:02'),
(118, 19, 'Participación e inclusión social', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-06 16:28:02', '2025-12-06 16:28:02'),
(119, 19, 'Control del propio entorno', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-06 16:28:02', '2025-12-06 16:28:02'),
(120, 19, 'Dimensión pedagógica', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-06 16:28:02', '2025-12-06 16:28:02'),
(121, 20, 'Contexto y vida familiar', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-07 17:16:17', '2025-12-07 17:16:17'),
(122, 20, 'Habilidades intelectuales', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-07 17:16:17', '2025-12-07 17:16:17'),
(123, 20, 'Bienestar emocional', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-07 17:16:17', '2025-12-07 17:16:17'),
(124, 20, 'Conducta adaptativa y desarrollo personal', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-07 17:16:17', '2025-12-07 17:16:17'),
(125, 20, 'Salud y bienestar físico', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-07 17:16:17', '2025-12-07 17:16:17'),
(126, 20, 'Participación e inclusión social', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-07 17:16:17', '2025-12-07 17:16:17'),
(127, 20, 'Control del propio entorno', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-07 17:16:17', '2025-12-07 17:16:17'),
(128, 20, 'Dimensión pedagógica', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-07 17:16:17', '2025-12-07 17:16:17'),
(129, 21, 'Contexto y vida familiar', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-08 06:38:03', '2025-12-08 06:38:03'),
(130, 21, 'Habilidades intelectuales', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-08 06:38:03', '2025-12-08 06:38:03'),
(131, 21, 'Bienestar emocional', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-08 06:38:03', '2025-12-08 06:38:03'),
(132, 21, 'Conducta adaptativa y desarrollo personal', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-08 06:38:03', '2025-12-08 06:38:03'),
(133, 21, 'Salud y bienestar físico', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-08 06:38:03', '2025-12-08 06:38:03'),
(134, 21, 'Participación e inclusión social', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-08 06:38:03', '2025-12-08 06:38:03'),
(135, 21, 'Control del propio entorno', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-08 06:38:03', '2025-12-08 06:38:03'),
(136, 21, 'Dimensión pedagógica', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-08 06:38:03', '2025-12-08 06:38:03'),
(137, 22, 'Contexto y vida familiar', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-08 22:57:51', '2025-12-08 22:57:51'),
(138, 22, 'Habilidades intelectuales', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-08 22:57:51', '2025-12-08 22:57:51'),
(139, 22, 'Bienestar emocional', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-08 22:57:51', '2025-12-08 22:57:51'),
(140, 22, 'Conducta adaptativa y desarrollo personal', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-08 22:57:51', '2025-12-08 22:57:51'),
(141, 22, 'Salud y bienestar físico', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-08 22:57:51', '2025-12-08 22:57:51'),
(142, 22, 'Participación e inclusión social', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-08 22:57:51', '2025-12-08 22:57:51'),
(143, 22, 'Control del propio entorno', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-08 22:57:51', '2025-12-08 22:57:51'),
(144, 22, 'Dimensión pedagógica', NULL, NULL, NULL, NULL, 'PENDIENTE', NULL, '2025-12-08 22:57:51', '2025-12-08 22:57:51');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dim_bienestar_emocional`
--

CREATE TABLE `dim_bienestar_emocional` (
  `ID_DIM_EMOCIONAL` int(11) NOT NULL,
  `CARACTERIZACION_ID` int(11) NOT NULL,
  `IDENTIFICA_EMOCIONES` int(1) DEFAULT 0,
  `EXPRESA_EMOCIONES` int(1) DEFAULT 0,
  `REGULA_IMPULSOS` int(1) DEFAULT 0,
  `AUTOESTIMA` int(1) DEFAULT 0,
  `MANEJO_ESTRES` int(1) DEFAULT 0,
  `EMPATIA` int(1) DEFAULT 0,
  `ESCUCHA_ACTIVA` int(1) DEFAULT 0,
  `RESUELVE_CONFLICTOS` int(1) DEFAULT 0,
  `TRABAJO_EQUIPO` int(1) DEFAULT 0,
  `SIGNOS_ALARMA` text DEFAULT NULL,
  `OBSERVACIONES_PSICO` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dim_conducta_adaptativa`
--

CREATE TABLE `dim_conducta_adaptativa` (
  `ID_DIM_ADAPTATIVA` int(11) NOT NULL,
  `CARACTERIZACION_ID` int(11) NOT NULL,
  `ALIMENTACION` int(1) DEFAULT 0,
  `HIGIENE` int(1) DEFAULT 0,
  `VESTIDO` int(1) DEFAULT 0,
  `SEGURIDAD` int(1) DEFAULT 0,
  `COMUNICACION` int(1) DEFAULT 0,
  `SEGUIMIENTO_INSTRUCCIONES` int(1) DEFAULT 0,
  `MANEJO_DINERO_TIEMPO` int(1) DEFAULT 0,
  `LECTURA_FUNCIONAL` int(1) DEFAULT 0,
  `INTERACCION_PARES` int(1) DEFAULT 0,
  `RESPETO_NORMAS` int(1) DEFAULT 0,
  `MANEJO_CAMBIOS` int(1) DEFAULT 0,
  `AUTOCONTROL` int(1) DEFAULT 0,
  `OBSERVACIONES` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dim_control_entorno`
--

CREATE TABLE `dim_control_entorno` (
  `ID_DIM_CONTROL` int(11) NOT NULL,
  `CARACTERIZACION_ID` int(11) NOT NULL,
  `RECONOCE_INTERESES` int(1) DEFAULT 0,
  `TIENE_METAS` int(1) DEFAULT 0,
  `PLANES_REFLEJAN_DESEOS` int(1) DEFAULT 0,
  `IDENTIFICA_FORTALEZAS` int(1) DEFAULT 0,
  `SEGURIDAD_DECISIONES` int(1) DEFAULT 0,
  `DECIDE_LUGARES` int(1) DEFAULT 0,
  `REQUIERE_POCO_APOYO` int(1) DEFAULT 0,
  `CONSCIENTE_CONSECUENCIAS` int(1) DEFAULT 0,
  `LIBERTAD_EXPRESION` int(1) DEFAULT 0,
  `FAMILIA_RESPETA_DECISIONES` int(1) DEFAULT 0,
  `CONOCE_DERECHOS` int(1) DEFAULT 0,
  `ENTORNO_ANIMA_INDEPENDENCIA` int(1) DEFAULT 0,
  `OBSERVACIONES` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dim_habilidades_intelectuales`
--

CREATE TABLE `dim_habilidades_intelectuales` (
  `ID_DIM_INTELECTUAL` int(11) NOT NULL,
  `CARACTERIZACION_ID` int(11) NOT NULL,
  `ATENCION` text DEFAULT NULL,
  `MEMORIA` text DEFAULT NULL,
  `SENSOPERCEPCION` text DEFAULT NULL,
  `RAZONAMIENTO` text DEFAULT NULL,
  `MOTIVACION` text DEFAULT NULL,
  `LECTURA` text DEFAULT NULL,
  `ESCRITURA` text DEFAULT NULL,
  `MATEMATICAS` text DEFAULT NULL,
  `ESTILO_APRENDIZAJE_DOMINANTE` varchar(50) DEFAULT NULL,
  `INTELIGENCIA_MULTIPLE_DOMINANTE` varchar(100) DEFAULT NULL,
  `OBSERVACIONES` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dim_participacion_social`
--

CREATE TABLE `dim_participacion_social` (
  `ID_DIM_PARTICIPACION` int(11) NOT NULL,
  `CARACTERIZACION_ID` int(11) NOT NULL,
  `ACEPTACION_COMPANEROS` int(1) DEFAULT 0,
  `GRUPO_AMIGOS` int(1) DEFAULT 0,
  `PARTICIPACION_DEBATES` int(1) DEFAULT 0,
  `PEDIR_AYUDA` int(1) DEFAULT 0,
  `RESPETO_DIVERSIDAD` int(1) DEFAULT 0,
  `ACTIVIDADES_EXTRACURRICULARES` int(1) DEFAULT 0,
  `OPORTUNIDADES_PARTICIPACION` int(1) DEFAULT 0,
  `ACCESIBILIDAD_INSTALACIONES` int(1) DEFAULT 0,
  `RECURSOS_COMUNITARIOS` int(1) DEFAULT 0,
  `VALORACION_OPINION` int(1) DEFAULT 0,
  `PROMOCION_INCLUSION` int(1) DEFAULT 0,
  `RUTA_ATENCION_EXCLUSION` int(1) DEFAULT 0,
  `SEGURIDAD_ESCOLAR` int(1) DEFAULT 0,
  `ASERTIVIDAD` int(1) DEFAULT 0,
  `EMPATIA` int(1) DEFAULT 0,
  `OBSERVACIONES` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dim_pedagogica`
--

CREATE TABLE `dim_pedagogica` (
  `ID_DIM_PEDAGOGICA` int(11) NOT NULL,
  `CARACTERIZACION_ID` int(11) NOT NULL,
  `FACTORES_MOTIVACION` text DEFAULT NULL,
  `EXPERIENCIAS_DESTACADAS` text DEFAULT NULL,
  `SABERES_CONECTAN` int(1) DEFAULT 0,
  `INTERES_TEMAS` int(1) DEFAULT 0,
  `PROFESORES_CONSIDERAN_INTERESES` int(1) DEFAULT 0,
  `OPORTUNIDAD_ELEGIR` int(1) DEFAULT 0,
  `ACTIVIDADES_APRENDE_MEJOR` int(1) DEFAULT 0,
  `AMBIENTE_AULA_AYUDA` int(1) DEFAULT 0,
  `METODOS_ADAPTAN` int(1) DEFAULT 0,
  `MOTIVACION_APRENDER` int(1) DEFAULT 0,
  `METAS_CLARAS` int(1) DEFAULT 0,
  `AYUDA_IDENTIFICAR_FORTALEZAS` int(1) DEFAULT 0,
  `CAPAZ_ALCANZAR_METAS` int(1) DEFAULT 0,
  `CONSCIENTE_HABILIDADES` int(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dim_salud_fisica`
--

CREATE TABLE `dim_salud_fisica` (
  `ID_DIM_SALUD` int(11) NOT NULL,
  `CARACTERIZACION_ID` int(11) NOT NULL,
  `AFILIADO_SISTEMA_SALUD` tinyint(1) DEFAULT 0,
  `NOMBRE_EPS_ARS` varchar(100) DEFAULT NULL,
  `REGIMEN_SALUD` varchar(45) DEFAULT NULL,
  `LUGAR_ATENCION_URGENCIAS` varchar(150) DEFAULT NULL,
  `TIENE_DIAGNOSTICO_MEDICO` tinyint(1) DEFAULT 0,
  `DETALLE_DIAGNOSTICO` text DEFAULT NULL,
  `TOMA_MEDICAMENTOS` tinyint(1) DEFAULT 0,
  `DETALLE_MEDICAMENTOS_DOSIS` text DEFAULT NULL,
  `TIENE_APOYOS_TERAPEUTICOS` tinyint(1) DEFAULT 0,
  `DETALLE_APOYOS_EXTERNOS` text DEFAULT NULL,
  `ALERGIAS_CONOCIDAS` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entidades`
--

CREATE TABLE `entidades` (
  `ID_ENTIDAD` int(11) NOT NULL,
  `NOMBRE_ENTIDAD` varchar(100) NOT NULL,
  `MODULO` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `entidades`
--

INSERT INTO `entidades` (`ID_ENTIDAD`, `NOMBRE_ENTIDAD`, `MODULO`) VALUES
(1, 'ESTUDIANTE', 'MODULO_GESTION_ESTUDIANTIL'),
(2, 'BOLETIN_ACADEMICO', 'MODULO_GESTION_ESTUDIANTIL'),
(3, 'CARACTERIZACION', 'MODULO_CARACTERIZACION'),
(4, 'TRASTORNO', 'MODULO_CARACTERIZACION'),
(5, 'PIAR', 'MODULO_PIAR'),
(6, 'ESTRATEGIAS_EDUCATIVAS', 'MODULO_PIAR'),
(7, 'ACTIVIDAD_CLASE', 'MODULO_PIAR'),
(8, 'EVALUACION', 'MODULO_PIAR'),
(9, 'NOVEDADES_REPORTES', 'MODULO_PSICOORIENTADOR'),
(10, 'PROTOCOLOS_RUTAS', 'MODULO_PSICOORIENTADOR'),
(11, 'MATERIA', 'MODULO_PROFESOR'),
(12, 'AREA', 'MODULO_PROFESOR'),
(13, 'DBA', 'MODULO_PIAR'),
(14, 'GRADO', 'MODULO_PROFESOR'),
(15, 'CURSO', 'MODULO_PROFESOR'),
(16, 'USUARIOS', 'MODULO_USUARIO'),
(17, 'ROL', 'MODULO_USUARIO'),
(18, 'PERMISOS', 'MODULO_USUARIO'),
(19, 'INSTITUCION', 'MODULO_INSTITUCIONAL'),
(20, 'MATRICULA', 'MODULU_INSTITUCIONAL'),
(21, 'REPRESENTANTE_LEGAL', 'MODULO_INSTITUCIONAL'),
(22, 'GESTION_ESTUDIANTIL', 'MODULO_GESTION_ESTUDIANTIL'),
(23, 'DASHBOARD', 'PANEL'),
(24, 'PERFIL', 'MODULO_USUARIO'),
(25, 'INCIDENCIAS', 'ADMIN'),
(26, 'DOCENTE', 'MODULO_PROFESOR'),
(27, 'PSICOPEDAGOGICO', 'MODULO_PSICOORIENTADOR'),
(28, 'FAMILIA', 'MODULO_PADRES'),
(29, 'MODULOS', 'ADMIN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estrategias_educativas`
--

CREATE TABLE `estrategias_educativas` (
  `ID_ESTRATEGIAS_EDUCATIVAS` int(11) NOT NULL,
  `CODIGO_ESTRATEGIAS_EDUCATIVAS` varchar(45) NOT NULL,
  `TIPO_ESTRATEGIAS_EDUCATIVAS` varchar(45) NOT NULL,
  `DESCRIPCION_ESTRATEGIA` varchar(300) NOT NULL,
  `HERRAMIENTAS_ESTRATEGIA` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiante`
--

CREATE TABLE `estudiante` (
  `ID_ESTUDIANTE` int(11) NOT NULL,
  `TIPO_DOCUMENTO_ID_TIPO_DOCUMENTO` int(11) NOT NULL,
  `NUMERO_DOCUMENTO_ESTUDIANTE` varchar(20) NOT NULL,
  `PRIMER_NOMBRE_ESTUDIANTE` varchar(45) NOT NULL,
  `SEGUNDO_NOMBRE_ESTUDIANTE` varchar(45) DEFAULT NULL,
  `PRIMER_APELLIDO_ESTUDIANTE` varchar(45) NOT NULL,
  `SEGUNDO_APELLIDO_ESTUDIANTE` varchar(45) DEFAULT NULL,
  `CURSO_ID_CURSO` int(11) NOT NULL,
  `FECHA_NACIMIENTO` date NOT NULL,
  `TELEFONO_ESTUDIANTE` varchar(20) DEFAULT NULL,
  `DIRECCION_ESTUDIANTE` varchar(45) NOT NULL,
  `CORREO_INSTITUCIONAL_ESTUDIANTE` varchar(45) NOT NULL,
  `FOTOGRAFIA_ESTUDIANTE` varchar(300) DEFAULT NULL,
  `alerta` varchar(1000) DEFAULT NULL,
  `expediente_id` varchar(30) DEFAULT NULL,
  `diagnostico_certificado` tinyint(1) DEFAULT 0,
  `tipo_tea` varchar(30) DEFAULT NULL,
  `fecha_diagnostico` date DEFAULT NULL,
  `profesional_diagnostico` varchar(150) DEFAULT NULL,
  `observaciones_diagnostico` text DEFAULT NULL,
  `contexto_observacion` text DEFAULT NULL,
  `acudiente_principal` varchar(120) DEFAULT NULL,
  `relacion_acudiente` varchar(20) DEFAULT NULL,
  `telefono_alternativo` varchar(15) DEFAULT NULL,
  `correo_contacto` varchar(120) DEFAULT NULL,
  `institucion_procedencia` varchar(150) DEFAULT NULL,
  `estado_registro` varchar(20) DEFAULT 'TEMPORAL',
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp(),
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estudiante`
--

INSERT INTO `estudiante` (`ID_ESTUDIANTE`, `TIPO_DOCUMENTO_ID_TIPO_DOCUMENTO`, `NUMERO_DOCUMENTO_ESTUDIANTE`, `PRIMER_NOMBRE_ESTUDIANTE`, `SEGUNDO_NOMBRE_ESTUDIANTE`, `PRIMER_APELLIDO_ESTUDIANTE`, `SEGUNDO_APELLIDO_ESTUDIANTE`, `CURSO_ID_CURSO`, `FECHA_NACIMIENTO`, `TELEFONO_ESTUDIANTE`, `DIRECCION_ESTUDIANTE`, `CORREO_INSTITUCIONAL_ESTUDIANTE`, `FOTOGRAFIA_ESTUDIANTE`, `alerta`, `expediente_id`, `diagnostico_certificado`, `tipo_tea`, `fecha_diagnostico`, `profesional_diagnostico`, `observaciones_diagnostico`, `contexto_observacion`, `acudiente_principal`, `relacion_acudiente`, `telefono_alternativo`, `correo_contacto`, `institucion_procedencia`, `estado_registro`, `fecha_registro`, `created_at`, `updated_at`, `created_by`, `updated_by`) VALUES
(1, 5, '1145328856', 'THOMAS', 'ANDRES', 'ALVAREZ', 'ORTEGA', 8, '2018-03-03', '3205895789', 'LA PAZ', 'thomasalvarez@itirr.edu.co', '', '123456789', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'TEMPORAL', '2025-09-25 17:20:39', '2025-09-25 17:20:39', '2025-09-25 17:20:39', NULL, NULL),
(2, 5, '1053450788', 'VALERY', 'SOFIA', 'BARRIOS', 'FONSECA', 12, '2017-12-13', '3178961213', '7 DE AGOSTO', 'valerybarrios@itirr.edu.co', NULL, '123456789', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'TEMPORAL', '2025-09-25 17:20:39', '2025-09-25 17:20:39', '2025-09-25 17:20:39', NULL, NULL),
(3, 4, '1052411785', 'DILAN', 'MAURICIO', 'GONZALEZ', 'CARREÑO', 14, '2015-06-07', '3001245745', 'CHAPINERO', 'dilangonzalez@itirr.edu.co', NULL, '1052978129', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'TEMPORAL', '2025-09-25 17:20:39', '2025-09-25 17:20:39', '2025-09-25 17:20:39', NULL, NULL),
(4, 4, '1053447463', 'JUAN', 'MANUEL', 'CORREDOR', 'SIERRA', 25, '2012-02-28', '3015878232', 'CENTRO', 'juanmsierra@itirr.edu.co', NULL, '1078624226', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'TEMPORAL', '2025-09-25 17:20:39', '2025-09-25 17:20:39', '2025-09-25 17:20:39', NULL, NULL),
(5, 4, '1145225850', 'LOREN', 'SOFIA', 'LINEROS', 'GOMEZ', 39, '2010-10-29', '3134567825', 'SAN JOSE', 'lorengomez@itirr.edu.co', NULL, '1052398478', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'TEMPORAL', '2025-09-25 17:20:39', '2025-09-25 17:20:39', '2025-09-25 17:20:39', NULL, NULL),
(6, 4, '1052398782', 'MARIA', 'CAMILA', 'DUARTE', 'HERNANDEZ', 23, '2020-02-01', '3207894512', 'LA PAZ', 'mariaduarte@itirr.edu.co', '', '1234567810', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'TEMPORAL', '2025-09-25 17:20:39', '2025-09-25 17:20:39', '2025-09-25 17:20:39', NULL, NULL),
(8, 4, '1234668752', 'JUAN', 'CARLOS', 'PERALTA', 'RAMIREZ', 16, '2017-07-04', '3158405762', 'barrios unidos', 'juancarlosperalta@gmail.com', NULL, NULL, 'EXP-TEA-2025-0001', 1, 'DISCALCULIA', '2023-01-30', 'EMILIANO TORRES', 'Confunde escritura y conteo de numeros', '', 'MARIA RAMIREZ', 'MADRE', '3114587985', 'mariaramirez@gmail.com', 'la florida', 'CONFIRMADO', '2025-09-26 07:19:06', '2025-09-26 07:19:06', '2025-09-26 07:19:06', 1, NULL),
(9, 4, '7854226942', 'FELIPE', '', 'CAMARGO', 'CASTAÑEDA', 8, '2019-11-04', '3178945296', 'san fernando', 'felipecamargo@itirr.edu.co', NULL, NULL, 'TEMP-TEA-2025-0001', 0, 'OTRO', NULL, '', 'Posible TDAH', 'CLASE', 'Cristian Camargo', 'PADRE', '3205487925', 'cristianca@gmail.com', 'jorge eliecer gaitan ', 'TEMPORAL', '2025-09-26 15:26:59', '2025-09-26 15:26:59', '2025-09-26 15:26:59', 1, NULL),
(10, 4, '1234568890', 'santiago', 'fernando', 'niño', 'garcia', 7, '2020-11-04', '3158741587', 'las lajas', 'santinino@itirr.edu.co', '', '12345782001', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'TEMPORAL', '2025-09-30 10:32:18', '2025-09-30 10:32:18', '2025-09-30 10:32:18', NULL, NULL),
(13, 4, '105239515', 'brandon', 'jahir', 'cabra', 'bula', 29, '2009-11-20', '3187941218', 'bjcabrajsf', 'bjcbarab@gmial.com', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ACTIVO', '2025-10-01 04:42:35', '2025-10-01 04:42:35', '2025-10-01 04:42:35', NULL, NULL),
(14, 4, '105239878514', 'RICARDO', 'JORGE', 'RUIZ', '', 2, '2021-01-14', '3158210445', '7AGOS', 'RICAR@GMAIL.COM', '', NULL, NULL, 1, 'DISLEXIA', '2000-11-23', 'JOSE', 'TEA', NULL, NULL, NULL, NULL, NULL, NULL, 'ACTIVO', '2025-10-01 06:02:36', '2025-10-01 06:02:36', '2025-10-01 06:02:36', NULL, NULL),
(15, 4, '1052395178', 'JORGE', '', 'PIRAGUA', '', 48, '2011-02-26', '135487456456', 'espinal', 'jorpi@gmal.com', '', 'Dificultades para leer palabras simples; Escritura ilegible o con muchos errores; Problemas de atención y concentración; Confunde letras o números similares; Dificultad para expresarse oralmente; OTRA: tda', NULL, 0, NULL, NULL, NULL, NULL, 'clase', NULL, NULL, NULL, NULL, NULL, 'ACTIVO', '2025-10-01 06:05:40', '2025-10-01 06:05:40', '2025-10-01 06:20:47', NULL, NULL),
(16, 5, '12378954625', 'MARIA', '', 'LOPEZ', 'QUIROGA', 5, '2020-09-07', '123454899815', 'AMERICAS', 'LOPE@GMAIL.COM', '', NULL, NULL, 1, 'tdah', '2024-02-10', 'brandon c', '', NULL, NULL, NULL, NULL, NULL, NULL, 'ACTIVO', '2025-10-01 06:18:51', '2025-10-01 06:18:51', '2025-10-01 06:18:51', NULL, NULL),
(17, 4, '105239514567', 'LUISA', 'FERANDA', 'RICO', '', 22, '2018-03-31', '3215648542', 'LAS LAJAS VIS', 'LUISM@GMAIL.COM', '', NULL, NULL, 1, 'MIXTO', '2019-05-19', 'PATRICIA R', '', NULL, 'PATRICIA f', 'MADRE', '3215782564', 'PATO@GMAIL.COM', 'SAN FERNANDO', 'ACTIVO', '2025-10-01 08:12:46', '2025-10-01 08:12:46', '2025-10-01 08:12:46', NULL, NULL),
(19, 4, '1356851681153', 'sdfasdf', 'sdfas', 'asdfasdfas', 'sdfasdfas', 16, '2025-08-05', '114577537', 'dfsadfsd', 'sdfasdf@asdfads.com', '', NULL, NULL, 1, 'TDA', '2025-09-30', 'SDAFASDF', '', NULL, 'DSAFASDFA', 'PADRE', '32552148228', 'SDFASFC@SDFSADF.COM', 'DFSGAD', 'ACTIVO', '2025-10-01 15:56:45', '2025-10-01 15:56:45', '2025-10-01 15:56:45', NULL, NULL),
(20, 4, '1235151025', 'sdfaasdf', 'sdfsdaf', 'asdfasdf', '', 7, '2020-08-10', '315885245', 'las lajas', 'bjcabrab@gmail.com', '', NULL, NULL, 1, 'Dislexia', '2024-02-07', 'juan', '', NULL, 'maria', 'MADRE', '352858225', 'bjcabrab@gmail.com', 'jorge eliecer gaitan', 'ACTIVO', '2025-10-01 23:52:11', '2025-10-01 23:52:11', '2025-10-01 23:52:11', NULL, NULL),
(21, 4, '9876543210', 'JUAN', 'PABLO', 'MURILLO', 'GARCIA', 11, '2018-06-12', '3158102820', 'la paz', 'juanmurillo@itirr.edu.co', '', NULL, NULL, 1, 'Dislexia', '2024-06-03', 'RICARDO ORTIZ', 'Apoyos con las letras b y d', NULL, 'JUAN MURILLO', 'PADRE', '3158102820', 'bjcabrab@gmail.com', 'san antonio norte', 'ACTIVO', '2025-12-02 05:39:06', '2025-12-02 05:39:06', '2025-12-02 05:39:06', NULL, NULL),
(23, 4, '9874563214', 'Juliana', 'Maria', 'Rios', 'Cardenas', 16, '2018-11-13', '3158662563', 'las lajas', 'julianamrios@itirr.edu.co', '', NULL, 'EXP-TEA-2025-0002', 1, 'disgrafia', '2025-12-01', 'Jorge Cabrera', '', NULL, 'Esperanza Cardenas', 'MADRE', '31578825452', 'bjcabrab@gmail.com', 'rafael reyes', 'ACTIVO', '2025-12-02 09:11:29', '2025-12-02 09:11:29', '2025-12-02 09:11:29', NULL, NULL),
(24, 4, '1053954785', 'DAVID', '', 'LÓPEZ', 'LÓPEZ', 24, '2015-05-04', '3154275212', 'las lajas', 'lopedavid@itirr.edu.co', '', NULL, 'EXP-TEA-2025-0003', 1, 'TDAH', '2025-02-04', 'JORGE CABRERA', 'Hiperactividad detonada por el estres', NULL, 'jorge López', 'PADRE', '3158102820', 'bjcabrab@gmail.com', 'itirr', 'ACTIVO', '2025-12-02 09:40:48', '2025-12-02 09:40:48', '2025-12-02 09:40:48', NULL, NULL),
(25, 4, '152354684651', 'Carlos ', 'andres', 'Pereira', '', 42, '2010-05-07', '31581025852', 'las lajas', 'carlospereira@gmail.com', '', 'Dificultades para leer palabras simples; Problemas con operaciones matemáticas básicas; Dificultad para seguir instrucciones secuenciales; Confunde letras o números similares; Lentitud excesiva en tareas académicas; Olvida instrucciones o pierde materiales frecuentemente', 'TEMP-TEA-2025-0004', 0, NULL, NULL, NULL, NULL, '', 'Andres Pereira', 'PADRE', '3158524165', 'bjcabrab@gmail.com', '', 'ACTIVO', '2025-12-02 10:25:23', '2025-12-02 10:25:23', '2025-12-02 10:25:23', NULL, NULL),
(26, 4, '1245879865', 'JULIANA', '', 'SANCHEZ', 'SALAZAR', 46, '2008-08-07', '3158102820', 'las lajas', 'bjcabrab@gmail.com', '', NULL, 'EXP-TEA-2025-0005', 1, 'TDA', '2020-02-09', 'RICARDO ORTIZ', '', NULL, 'RAFAEL SANCHEZ', 'PADRE', '3158102820', 'bjcabrab@gmail.com', '', 'ACTIVO', '2025-12-02 18:02:28', '2025-12-02 18:02:28', '2025-12-02 18:02:28', NULL, NULL),
(27, 4, '12378965487', 'DAYRON', '', 'MORENO', '', 42, '2009-11-24', '3158102820', 'las lajas', 'bjcabrab@gmail.com', '', 'Dificultades para leer palabras simples; Problemas con operaciones matemáticas básicas; Escritura ilegible o con muchos errores; Dificultad para seguir instrucciones secuenciales; Problemas de atención y concentración; Evita actividades que requieren lectura; Confunde letras o números similares', 'TEMP-TEA-2025-0006', 0, NULL, NULL, NULL, NULL, '', 'Julian Moreno', 'PADRE', '3158102820', 'bjcabrab@gmail.com', 'Alemania solidaria', 'ACTIVO', '2025-12-02 18:35:47', '2025-12-02 18:35:47', '2025-12-02 18:35:47', NULL, NULL),
(28, 4, '1025887844', 'Luis', '', 'Fernandez', '', 48, '2008-07-06', '3158102820', 'las lajas', 'bjcabrab@gmail.com', '', NULL, 'EXP-TEA-2025-0007', 1, 'Dislexia', '2022-07-06', 'RICARDO ORTIZ', '', NULL, 'Vicente Fernandez', 'PADRE', '3158102820', 'bjcabrab@gmail.com', '', 'ACTIVO', '2025-12-02 19:07:11', '2025-12-02 19:07:11', '2025-12-02 19:07:11', NULL, NULL),
(29, 4, '2126578459', 'Lautaro', '', 'Del Campo', '', 11, '2016-03-27', '3157895421', 'las lajas', 'bjcabrab@gmail.com', '', 'Escritura ilegible o con muchos errores; Problemas de atención y concentración; Dificultad para expresarse oralmente; Lentitud excesiva en tareas académicas', 'TEMP-TEA-2025-0008', 0, NULL, NULL, NULL, NULL, '', 'Carlos Del Campo', 'PADRE', '3158102820', 'bjcabrab@gmail.com', '', 'ACTIVO', '2025-12-02 19:31:09', '2025-12-02 19:31:09', '2025-12-02 19:31:09', NULL, NULL),
(30, 4, '6848879865', 'Lennin', '', 'Marx', '', 20, '2010-04-14', '3158102820', 'las lajas', 'bjcabrab@gmail.com', '', NULL, 'EXP-TEA-2025-0009', 1, 'TDAH', '2020-02-14', 'RICARDO ORTIZ', '', NULL, 'Carlos Marx', 'PADRE', '3158102820', 'bjcabrab@gmail.com', '', 'ACTIVO', '2025-12-02 19:35:13', '2025-12-02 19:35:13', '2025-12-02 19:35:13', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiante_contacto`
--

CREATE TABLE `estudiante_contacto` (
  `estudiante_contacto_id` bigint(20) NOT NULL,
  `estudiante_id` int(11) NOT NULL,
  `nombre` varchar(120) NOT NULL,
  `relacion` varchar(20) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `email` varchar(120) DEFAULT NULL,
  `autorizado_legal` tinyint(1) DEFAULT 0,
  `preferencia_contacto` varchar(10) DEFAULT 'EMAIL',
  `orden_prioridad` smallint(6) DEFAULT 1,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiante_has_trastorno`
--

CREATE TABLE `estudiante_has_trastorno` (
  `ID_ESTUDIANTE_has_TRASTORNO` int(11) NOT NULL,
  `ESTUDIANTE_ID_ESTUDIANTE` int(11) NOT NULL,
  `TRASTORNO_ID_TRASTORNO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiante_senal_alerta`
--

CREATE TABLE `estudiante_senal_alerta` (
  `id_senal_alerta` bigint(20) NOT NULL,
  `estudiante_id` int(11) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `contexto_observacion` varchar(1000) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `otra_senal` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evaluacion`
--

CREATE TABLE `evaluacion` (
  `ID_EVALUACION` int(11) NOT NULL,
  `CUALITATIVA_EVALUACION` varchar(45) NOT NULL,
  `CUANTITATIVA_EVALUACION` varchar(45) NOT NULL,
  `CRITERIOS EVALUACION` varchar(45) NOT NULL,
  `EVALUACION_ESTRATEGIA` varchar(45) NOT NULL,
  `BOLETIN_ACADEMICO_ID_BOLETIN_ACADEMICO` int(11) NOT NULL,
  `PIAR_SEGUIMIENTO_EVALUATIVO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `expediente_counters`
--

CREATE TABLE `expediente_counters` (
  `year` int(11) NOT NULL,
  `last_counter` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `expediente_counters`
--

INSERT INTO `expediente_counters` (`year`, `last_counter`) VALUES
(2025, 50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fecha_registro`
--

CREATE TABLE `fecha_registro` (
  `ID_FECHA_REGISTRO` int(11) NOT NULL,
  `CREACION_REGISTRO` date DEFAULT NULL,
  `ACTUALIZACION_REGISTRO` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grado`
--

CREATE TABLE `grado` (
  `ID_GRADO` int(11) NOT NULL,
  `NIVEL_GRADO` varchar(45) NOT NULL,
  `CICLO_GRADO` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `grado`
--

INSERT INTO `grado` (`ID_GRADO`, `NIVEL_GRADO`, `CICLO_GRADO`) VALUES
(1, 'PREJARDIN', 'PREJARDIN'),
(2, 'PRIMERO', 'BASICA_PRI'),
(3, 'SEGUNDO', 'BASICA_PRI'),
(4, 'TERCERO', 'BASICA_PRI'),
(5, 'CUARTO', 'BASICA_PRI'),
(6, 'QUINTO', 'BASICA_PRI'),
(7, 'SEXTO', 'BASICA_SEC'),
(8, 'SEPTIMO', 'BASICA_SEC'),
(9, 'OCTAVO', 'BASICA_SEC'),
(10, 'NOVENO', 'BASICA_SEC'),
(11, 'DECIMO', 'EDUCACION_'),
(12, 'ONCE', 'EDUCACION_');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grado_materia`
--

CREATE TABLE `grado_materia` (
  `ID_GRADO_MATERIA` int(11) NOT NULL,
  `GRADO_ID_GRADO` int(11) NOT NULL,
  `MATERIA_ID_MATERIA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `grado_materia`
--

INSERT INTO `grado_materia` (`ID_GRADO_MATERIA`, `GRADO_ID_GRADO`, `MATERIA_ID_MATERIA`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 2, 1),
(6, 2, 2),
(7, 2, 3),
(8, 2, 4),
(9, 3, 1),
(10, 3, 2),
(11, 3, 3),
(12, 3, 4),
(13, 4, 1),
(14, 4, 2),
(15, 4, 3),
(16, 4, 4),
(17, 5, 1),
(18, 5, 2),
(19, 5, 3),
(20, 5, 4),
(21, 6, 1),
(22, 6, 2),
(23, 6, 3),
(24, 6, 4),
(25, 7, 1),
(26, 7, 2),
(27, 7, 3),
(28, 7, 4),
(29, 8, 1),
(30, 8, 2),
(31, 8, 3),
(32, 8, 4),
(33, 9, 1),
(34, 9, 2),
(35, 9, 3),
(36, 9, 4),
(37, 10, 1),
(38, 10, 2),
(39, 10, 3),
(40, 10, 4),
(41, 11, 1),
(42, 11, 2),
(43, 11, 3),
(44, 11, 4),
(45, 12, 1),
(46, 12, 2),
(47, 12, 3),
(48, 12, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_caracterizacion`
--

CREATE TABLE `historial_caracterizacion` (
  `ID_HISTORIAL` int(11) NOT NULL COMMENT 'ID único del historial',
  `CARACTERIZACION_ID` int(11) NOT NULL COMMENT 'ID de la caracterización',
  `ACCION` varchar(50) NOT NULL COMMENT 'Tipo de acción realizada',
  `DESCRIPCION` text DEFAULT NULL COMMENT 'Descripción de la acción',
  `USUARIO_ID` int(11) DEFAULT NULL COMMENT 'ID del usuario que realizó la acción',
  `FECHA_ACCION` timestamp NOT NULL DEFAULT current_timestamp() COMMENT 'Fecha de la acción',
  `DATOS_ANTERIORES` text DEFAULT NULL COMMENT 'Datos antes del cambio (JSON)',
  `DATOS_NUEVOS` text DEFAULT NULL COMMENT 'Datos después del cambio (JSON)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Tabla para auditoría y seguimiento de cambios en caracterizaciones';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `institucion`
--

CREATE TABLE `institucion` (
  `ID_INSTITUCION` int(11) NOT NULL,
  `NOMBRE_INSTITUCION` varchar(150) NOT NULL,
  `NIT_INSTITUCION` varchar(45) NOT NULL,
  `CORREO_INSTITUCION` varchar(45) NOT NULL,
  `TELEFONO_INSTITUCION` varchar(45) NOT NULL,
  `DIRECCION_INSTITUCION` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `institucion`
--

INSERT INTO `institucion` (`ID_INSTITUCION`, `NOMBRE_INSTITUCION`, `NIT_INSTITUCION`, `CORREO_INSTITUCION`, `TELEFONO_INSTITUCION`, `DIRECCION_INSTITUCION`) VALUES
(1, 'INSTITUTO TÉCNICO INDUSTRIAL RAFAEL REYES', '123456789', 'itirr@itirr.edu.co', '5607640', 'Barrio La Paz');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materia`
--

CREATE TABLE `materia` (
  `ID_MATERIA` int(11) NOT NULL,
  `CODIGO_MATERIA` varchar(45) NOT NULL,
  `NOMBRE_MATERIA` varchar(45) NOT NULL,
  `DESCRIPCION_MATERIA` varchar(250) NOT NULL,
  `PLAN_MATERIA` varchar(500) NOT NULL,
  `AREA_ID_AREA` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `materia`
--

INSERT INTO `materia` (`ID_MATERIA`, `CODIGO_MATERIA`, `NOMBRE_MATERIA`, `DESCRIPCION_MATERIA`, `PLAN_MATERIA`, `AREA_ID_AREA`) VALUES
(1, 'CN', 'CIENCIAS_NATURALES', 'Materia para basica primaria', 'PDF', 1),
(2, 'SOCIALES', 'SOCIALES', '', '', 2),
(3, 'LEN', 'LENGUAJE', '', '', 3),
(4, 'MAT', 'MATEMATICAS', '', '', 4),
(5, 'BIO', 'BIOLOGIA', '', '', 1),
(6, 'QUI', 'QUIMICA', '', '', 1),
(7, 'FIS', 'FISICA', '', '', 1),
(8, 'POL', 'POLITICA', '', '', 2),
(9, 'GEO', 'GEOGRAFIA', '', '', 2),
(10, 'ESP', 'ESPAÑOL', '', '', 3),
(11, 'ING', 'INGLES', '', '', 3),
(12, 'ALG', 'ALGEBRA', '', '', 4),
(13, 'ART', 'ARTISTICA', '', '', 5),
(14, 'EF', 'EDUCACION_FISICA', '', '', 7),
(15, 'TEC', 'TECNOLOGIA', '', '', 8),
(16, 'ETI', 'ETICA', '', '', 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matricula`
--

CREATE TABLE `matricula` (
  `ID_MATRICULA` int(11) NOT NULL,
  `CODIGO_MATRICULA` varchar(45) NOT NULL,
  `VALOR_MATRICULA` decimal(10,2) NOT NULL,
  `FECHA_MATRICULA` date NOT NULL,
  `JORNADA` enum('MAÑANA','TARDE','UNICA') NOT NULL,
  `INSTITUCION_ID_INSTITUCION` int(11) NOT NULL,
  `ESTUDIANTE_ID_ESTUDIANTE` int(11) NOT NULL,
  `FECHA_REGISTRO_ID_FECHA_REGISTRO` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `matricula`
--

INSERT INTO `matricula` (`ID_MATRICULA`, `CODIGO_MATRICULA`, `VALOR_MATRICULA`, `FECHA_MATRICULA`, `JORNADA`, `INSTITUCION_ID_INSTITUCION`, `ESTUDIANTE_ID_ESTUDIANTE`, `FECHA_REGISTRO_ID_FECHA_REGISTRO`) VALUES
(2, '20251145328856', 200000.00, '2025-01-13', 'MAÑANA', 1, 1, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `novedades_reportes`
--

CREATE TABLE `novedades_reportes` (
  `ID_NOVEDADES_REPORTES` int(11) NOT NULL,
  `FECHA_NOVEDADES_REPORTES` date NOT NULL,
  `NUMERO_DOCUMENTO_ESTUDIANTE` varchar(45) NOT NULL,
  `ACCIONES_NOVEDADES_REPORTES` varchar(100) DEFAULT NULL,
  `TIPO_NOVEDADES_REPORTES` enum('Académica','Conductual','Médica','Psicológica','NuevoEstudianteTEA','Otra') NOT NULL,
  `DESCRIPCION_NOVEDADES_REPORTES` varchar(200) DEFAULT NULL,
  `ESTADO_NOVEDADES_REPORTES` enum('Para reportar','Pendiente','En proceso','Resuelto') NOT NULL DEFAULT 'Pendiente',
  `PRIORIDAD` enum('Alta','Media','Baja') NOT NULL,
  `USUARIOS_ID_USUARIO` int(11) NOT NULL,
  `ESTUDIANTE_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `novedades_reportes`
--

INSERT INTO `novedades_reportes` (`ID_NOVEDADES_REPORTES`, `FECHA_NOVEDADES_REPORTES`, `NUMERO_DOCUMENTO_ESTUDIANTE`, `ACCIONES_NOVEDADES_REPORTES`, `TIPO_NOVEDADES_REPORTES`, `DESCRIPCION_NOVEDADES_REPORTES`, `ESTADO_NOVEDADES_REPORTES`, `PRIORIDAD`, `USUARIOS_ID_USUARIO`, `ESTUDIANTE_ID`) VALUES
(1, '2025-05-05', '1145328856', 'ENFERMEDAD', 'Médica', 'NO SE PRESENTA AL COLEGIO', 'En proceso', 'Baja', 9, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `observacion_sistematica`
--

CREATE TABLE `observacion_sistematica` (
  `ID_OBSERVACION` int(11) NOT NULL COMMENT 'ID único de la observación',
  `CARACTERIZACION_ID` int(11) NOT NULL COMMENT 'ID de la caracterización asociada',
  `FECHA_OBSERVACION` timestamp NOT NULL DEFAULT current_timestamp() COMMENT 'Fecha y hora de la observación',
  `ENTORNO` varchar(50) NOT NULL COMMENT 'Entorno: AULA, RECREO, HOGAR, EXTRACURRICULAR',
  `DESCRIPCION` text DEFAULT NULL COMMENT 'Descripción detallada de la observación',
  `CONTEXTO` text DEFAULT NULL COMMENT 'Contexto en el que se realizó la observación',
  `EVIDENCIAS` varchar(300) DEFAULT NULL COMMENT 'Ruta a archivos adjuntos o evidencias',
  `OBSERVADOR` varchar(100) DEFAULT NULL COMMENT 'Nombre del observador',
  `CREATED_AT` timestamp NOT NULL DEFAULT current_timestamp() COMMENT 'Fecha de creación del registro'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Tabla para gestionar observaciones sistemáticas del estudiante en diferentes entornos';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `periodo`
--

CREATE TABLE `periodo` (
  `ID_PERIODO` int(11) NOT NULL,
  `NUMERO_PERIODO` int(11) NOT NULL,
  `FECHA_INICIO` date DEFAULT NULL,
  `FECHA_CIERRA` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `periodo`
--

INSERT INTO `periodo` (`ID_PERIODO`, `NUMERO_PERIODO`, `FECHA_INICIO`, `FECHA_CIERRA`) VALUES
(1, 1, NULL, NULL),
(2, 2, NULL, NULL),
(3, 3, NULL, NULL),
(4, 4, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos`
--

CREATE TABLE `permisos` (
  `ID_PERMISO` int(11) NOT NULL,
  `ENTIDAD_ID_ENTIDAD` int(11) DEFAULT NULL,
  `ACCION_ID_ACCION` int(11) DEFAULT NULL,
  `DESCRIPCION` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `permisos`
--

INSERT INTO `permisos` (`ID_PERMISO`, `ENTIDAD_ID_ENTIDAD`, `ACCION_ID_ACCION`, `DESCRIPCION`) VALUES
(2, 3, 2, 'LEER_CARACTERIZACION'),
(3, 3, 3, 'MODIFICAR_CARACTERIZACION'),
(4, 3, 4, 'ELIMINAR_CARACTERIZACION'),
(5, 3, 5, 'GESTIONAR_CARACTERIZACION'),
(6, 3, 6, 'RECOMENDAR_CARACTERIZACION'),
(8, 5, 2, 'LEER_PIAR'),
(9, 5, 3, 'MODIFICAR_PIAR'),
(10, 5, 4, 'ELIMINAR_PIAR'),
(11, 5, 5, 'GESTIONAR_PIAR'),
(12, 5, 6, 'RECOMENDAR_PIAR'),
(13, 16, 1, 'CREAR_USUARIOS'),
(14, 16, 2, 'LEER_USUARIOS'),
(15, 16, 3, 'MODIFICAR_USUARIOS'),
(16, 16, 4, 'ELIMINAR_USUARIOS'),
(17, 16, 5, 'GESTIONAR_USUARIOS'),
(18, 16, 6, 'RECOMENDAR_USUARIOS'),
(32, 1, 1, 'CREAR_ESTUDIANTE'),
(33, 1, 2, 'LEER_ESTUDIANTE'),
(34, 1, 3, 'MODIFICAR_ESTUDIANTE'),
(35, 1, 4, 'ELIMINAR_ESTUDIANTE'),
(36, 1, 5, 'GESTIONAR_ESTUDIANTE'),
(37, 1, 6, 'RECOMENDAR_ESTUDIANTE'),
(38, 6, 1, 'CREAR_ESTRATEGIAS_EDUCATIVAS'),
(39, 6, 2, 'LEER_ESTRATEGIAS_EDUCATIVAS'),
(40, 6, 3, 'MODIFICAR_ESTRATEGIAS_EDUCATIVAS'),
(41, 6, 4, 'ELIMINAR_ESTRATEGIAS_EDUCATIVAS'),
(42, 6, 5, 'GESTIONAR_ESTRATEGIAS_EDUCATIVAS'),
(43, 6, 6, 'RECOMENDAR_ESTRATEGIAS_EDUCATIVAS'),
(44, 10, 1, 'CREAR_PROTOCOLOS_RUTAS'),
(45, 10, 2, 'LEER_PROTOCOLOS_RUTAS'),
(46, 10, 3, 'MODIFICAR_PROTOCOLOS_RUTAS'),
(47, 10, 4, 'ELIMINAR_PROTOCOLOS_RUTAS'),
(48, 10, 5, 'GESTIONAR_PROTOCOLOS_RUTAS'),
(49, 10, 6, 'RECOMENDAR PROTOCOLOS_RUTAS'),
(63, 19, 5, 'GESTIONAR_INSTITUCION'),
(64, 21, 5, 'GESTIONAR_REPRESENTANTE_LEGAL'),
(65, 3, 7, 'caracterizacion'),
(66, 5, 7, 'Piar'),
(67, 6, 7, 'estrategiasEducativas'),
(68, 19, 7, 'institucional'),
(69, 9, 7, 'reportesNovedades'),
(70, 10, 7, 'protocolosRutas'),
(71, 22, 7, 'gestionEstudiantil'),
(72, 23, 7, 'dashboard'),
(73, 24, 7, 'perfil'),
(74, 25, 7, 'incidencias'),
(75, 26, 7, 'gestionDocente'),
(76, 27, 7, 'gestionPsicopedagogica'),
(77, 28, 7, 'gestionFamilia'),
(78, 29, 7, 'gestionModulos'),
(79, 2, 7, 'boletinEstudiantil');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `piar`
--

CREATE TABLE `piar` (
  `ID_PIAR` int(11) NOT NULL,
  `ESTUDIANTE_ID_ESTUDIANTE` int(11) NOT NULL,
  `CODIGO_PIAR` varchar(45) NOT NULL,
  `PERIODO` varchar(45) NOT NULL,
  `AREA_ID_AREA` int(11) DEFAULT NULL,
  `DBA_ID_DBA` int(11) DEFAULT NULL,
  `BARRA_DE_APRENDIZAJE` varchar(200) NOT NULL,
  `FLEXIBILIZACION` varchar(45) NOT NULL,
  `EVALUACION` varchar(200) NOT NULL,
  `SEGUIMIENTO_EVALUATIVO` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `piar`
--

INSERT INTO `piar` (`ID_PIAR`, `ESTUDIANTE_ID_ESTUDIANTE`, `CODIGO_PIAR`, `PERIODO`, `AREA_ID_AREA`, `DBA_ID_DBA`, `BARRA_DE_APRENDIZAJE`, `FLEXIBILIZACION`, `EVALUACION`, `SEGUIMIENTO_EVALUATIVO`) VALUES
(5, 1, '202511031145328856', 'PRIMER', NULL, 1, 'CONTEXTUAL', 'IDENTIFICA LOS SENTIDOS', 'DESCRIBE SENSACIONES A PARTIR DEL USOS DE LOS SENTRIDOS', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `piar_has_estrategias_educativas`
--

CREATE TABLE `piar_has_estrategias_educativas` (
  `CODIGO_PIAR_has_ESTRATEGIAS_EDUCATIVAS` int(11) NOT NULL,
  `PIAR_ID_PIAR` int(11) NOT NULL,
  `ESTRATEGIAS_EDUCATIVAS_ID_ESTRATEGIAS_EDUCATIVAS` int(11) NOT NULL,
  `EVALUACIÓN_ESTRATEGIA` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `protocolos_rutas`
--

CREATE TABLE `protocolos_rutas` (
  `ID_PROTOCOLOS_RUTAS` int(11) NOT NULL,
  `NOMBRE_PROTOCOLOS_RUTAS` varchar(45) NOT NULL,
  `DESCRIPCION_PROTOCOLOS_RUTAS` text NOT NULL,
  `TIPO_PROTOCOLOS_RUTAS` enum('Emergencia','Seguimiento','Psicológico','Otro') NOT NULL,
  `PASOS_PROTOCOLOS_RUTAS` text NOT NULL,
  `RESPONSABLE_PROTOCOLOS_RUTAS` varchar(100) NOT NULL,
  `ESTADO_PROTOCOLOS_RUTAS` enum('Activo','En proceso','Cerrrado') NOT NULL,
  `NOVEDADES_REPORTES_ID_NOVEDADES_REPORTES` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `representante_legal`
--

CREATE TABLE `representante_legal` (
  `ID_REPRESENTANTE_LEGAL` int(11) NOT NULL,
  `NOMBRE_REPRESENTANTE_LEGAL` varchar(45) NOT NULL,
  `DOCUMENTO_REPRESENTANTE_LEGAL` varchar(20) NOT NULL,
  `TELEFONO_REPRESENTANTE_LEGAL` varchar(45) NOT NULL,
  `CORREO_REPRESENTANTE_LEGAL` varchar(45) NOT NULL,
  `INSTITUCION_ID_INSTITUCION` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `representante_legal`
--

INSERT INTO `representante_legal` (`ID_REPRESENTANTE_LEGAL`, `NOMBRE_REPRESENTANTE_LEGAL`, `DOCUMENTO_REPRESENTANTE_LEGAL`, `TELEFONO_REPRESENTANTE_LEGAL`, `CORREO_REPRESENTANTE_LEGAL`, `INSTITUCION_ID_INSTITUCION`) VALUES
(1, 'JOSELIN CAMARGO', '7224398', '3157894625', 'josecamargo@itirr.edu.co', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reunion_socializacion`
--

CREATE TABLE `reunion_socializacion` (
  `ID_REUNION` int(11) NOT NULL COMMENT 'ID único de la reunión',
  `CARACTERIZACION_ID` int(11) NOT NULL COMMENT 'ID de la caracterización asociada',
  `TIPO_REUNION` varchar(50) NOT NULL COMMENT 'Tipo: INICIAL, SEGUIMIENTO, CIERRE',
  `FECHA_PROGRAMADA` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Fecha programada de la reunión',
  `FECHA_REALIZADA` timestamp NULL DEFAULT NULL COMMENT 'Fecha real de realización',
  `PARTICIPANTES` text DEFAULT NULL COMMENT 'Lista de participantes',
  `ACUERDOS` text DEFAULT NULL COMMENT 'Acuerdos y compromisos establecidos',
  `OBSERVACIONES` text DEFAULT NULL COMMENT 'Observaciones adicionales',
  `ESTADO` varchar(20) DEFAULT 'PROGRAMADA' COMMENT 'Estados: PROGRAMADA, REALIZADA, CANCELADA',
  `ACTA_URL` varchar(300) DEFAULT NULL COMMENT 'Ruta al acta de la reunión',
  `CREATED_AT` timestamp NOT NULL DEFAULT current_timestamp() COMMENT 'Fecha de creación',
  `UPDATED_AT` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT 'Fecha de actualización'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Tabla para gestionar reuniones de socialización con familia y equipo docente';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `ID_ROL` int(11) NOT NULL,
  `NOMBRE_ROL` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`ID_ROL`, `NOMBRE_ROL`) VALUES
(1, 'PROFESOR'),
(2, 'PSICOORIENTADOR'),
(3, 'PADRE DE FAMILIA'),
(4, 'ESTUDIANTE'),
(5, 'ADMINISTRADOR');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol_permiso`
--

CREATE TABLE `rol_permiso` (
  `ID_ROL_PERMISO` int(11) NOT NULL,
  `ROL_ID_ROL` int(11) NOT NULL,
  `PERMISO_ID_PERMISO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol_permiso`
--

INSERT INTO `rol_permiso` (`ID_ROL_PERMISO`, `ROL_ID_ROL`, `PERMISO_ID_PERMISO`) VALUES
(1, 5, 74),
(2, 5, 68),
(3, 5, 71),
(4, 5, 70),
(5, 5, 69),
(6, 5, 67),
(7, 5, 66),
(8, 5, 78),
(9, 5, 65),
(10, 5, 73),
(11, 5, 72),
(12, 1, 72),
(44, 1, 65),
(45, 1, 66),
(46, 1, 73),
(47, 1, 67),
(48, 1, 69),
(50, 1, 70),
(52, 1, 71),
(53, 1, 75),
(54, 2, 72),
(56, 2, 73),
(57, 2, 65),
(58, 2, 66),
(59, 2, 67),
(60, 2, 69),
(61, 2, 70),
(62, 2, 71),
(63, 2, 76),
(64, 3, 73),
(65, 3, 65),
(66, 3, 66),
(67, 3, 67),
(68, 3, 69),
(69, 3, 70),
(70, 3, 79),
(76, 3, 77);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_documento`
--

CREATE TABLE `tipo_documento` (
  `ID_TIPO_DOCUMENTO` int(11) NOT NULL,
  `NOMBRE_TIPO_DOCUMENTO` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo_documento`
--

INSERT INTO `tipo_documento` (`ID_TIPO_DOCUMENTO`, `NOMBRE_TIPO_DOCUMENTO`) VALUES
(1, 'CEDULA'),
(2, 'CEDULA EXTRANJERIA'),
(3, 'PASAPORTE'),
(4, 'TARJETA IDENTIDAD'),
(5, 'REGISTRO CIVIL'),
(6, 'OTRO'),
(7, 'NO APORTA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trastorno`
--

CREATE TABLE `trastorno` (
  `ID_TRASTORNO` int(11) NOT NULL,
  `NOMBRE_TRASTORNO` varchar(100) NOT NULL,
  `DESCRIPCIÓN_TRASTORNO` varchar(45) NOT NULL,
  `DIAGNOSTICO_MEDICO_TRASTORNO` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `trastorno`
--

INSERT INTO `trastorno` (`ID_TRASTORNO`, `NOMBRE_TRASTORNO`, `DESCRIPCIÓN_TRASTORNO`, `DIAGNOSTICO_MEDICO_TRASTORNO`) VALUES
(1, 'DISLEXIA', 'dificultad en la lectura.', 'DISLEXIA'),
(2, 'DISGRAFÍA', 'dificultad en la escritura.', NULL),
(3, 'DISCALCULIA', 'dificultad en las matemáticas.', NULL),
(4, 'TDAH', 'combinación de problemas persistentes, tales ', NULL),
(5, 'TDA', 'dificultades de la persona para sostener su a', NULL),
(6, 'MULTIPLE', 'dos o más TEA', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarioprof_has_materia`
--

CREATE TABLE `usuarioprof_has_materia` (
  `ID_USUARIOPROF_MATERIA` int(11) NOT NULL,
  `USUARIOS_ID_USUARIO` int(11) NOT NULL,
  `USUARIOS_ROL_ID_ROL` int(11) NOT NULL,
  `MATERIA_ID_MATERIA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarioprof_has_materia`
--

INSERT INTO `usuarioprof_has_materia` (`ID_USUARIOPROF_MATERIA`, `USUARIOS_ID_USUARIO`, `USUARIOS_ROL_ID_ROL`, `MATERIA_ID_MATERIA`) VALUES
(3, 1, 1, 1),
(4, 7, 1, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `ID_USUARIO` int(11) NOT NULL,
  `NUMERO_DOCUMENTO` varchar(20) NOT NULL,
  `PRIMER_NOMBRE` varchar(45) NOT NULL,
  `PRIMER_APELLIDO` varchar(45) NOT NULL,
  `TELEFONO_USUARIO` varchar(45) DEFAULT NULL,
  `DIRECCION_USUARIO` varchar(100) NOT NULL,
  `CORREO_USUARIO` varchar(45) DEFAULT NULL,
  `CORREO_INSTITUCIONAL` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `ESTATUS` enum('ACTIVO','DESACTIVADO') DEFAULT NULL,
  `TIPO_DOCUMENTO_ID_TIPO_DOCUMENTO` int(11) NOT NULL,
  `FECHA_REGISTRO_ID_FECHA_REGISTRO` date DEFAULT NULL,
  `ROL_ID_ROL` int(11) DEFAULT NULL,
  `FOTO_PERFIL` varchar(555) DEFAULT NULL COMMENT 'Ruta de la imagen de perfil del usuario'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`ID_USUARIO`, `NUMERO_DOCUMENTO`, `PRIMER_NOMBRE`, `PRIMER_APELLIDO`, `TELEFONO_USUARIO`, `DIRECCION_USUARIO`, `CORREO_USUARIO`, `CORREO_INSTITUCIONAL`, `PASSWORD`, `ESTATUS`, `TIPO_DOCUMENTO_ID_TIPO_DOCUMENTO`, `FECHA_REGISTRO_ID_FECHA_REGISTRO`, `ROL_ID_ROL`, `FOTO_PERFIL`) VALUES
(1, '1052395145', 'BRANDON JAHIR', 'CABRA BULLA', NULL, '', NULL, NULL, '$2a$10$WWnsreg/QmyulhPNMnV2eOl6FLt/kRU8Mvwj/SPg.p9YySv7dDc.m', 'ACTIVO', 1, NULL, NULL, 'resources/images/perfiles/profesor_default.jpg'),
(3, '12345678911', 'SAMUEL', 'LOPEZ', NULL, '', NULL, NULL, '$2y$10$ACqTrIb/6QJLk.D5rwVpLegsmZcUF2YAEc5.m.PgDodmJRzm7J14m', 'ACTIVO', 1, NULL, NULL, NULL),
(4, '1052395144', 'Raul', 'Garcia', NULL, '', NULL, NULL, '$2a$10$n9j7.R8wDjO6KzXo5HuDD.34bQ7hKOQT/Lltp6hcKiueqc8nlFome', 'ACTIVO', 1, NULL, NULL, NULL),
(5, '1052395146', 'ANDREA', 'GARCIA', NULL, '', NULL, NULL, '$2a$10$QdAw/574FrIaJET.x34Kze/h2o7JZ4o3lZSWnyUEucW7xa4MV.x8C', 'ACTIVO', 1, NULL, NULL, NULL),
(6, '1032460451', 'FREDDY', 'CASTRO', NULL, '', NULL, NULL, '$2a$10$fmrpRVqJTJnx1mDC5kimk.xFZBMD2l59lcE6sS0Cq4XbtJsWsjntO', 'ACTIVO', 1, NULL, NULL, 'resources/images/perfiles/admin_default.png'),
(7, '1018477769', 'SEBASTIAN', 'MORENO', NULL, '', NULL, NULL, '$2a$10$/2.dYR3GP4HcG75vQdZVcu/yRMSlz9VmuKaECuAjTKmMmAw2Zq36O', 'ACTIVO', 1, NULL, NULL, NULL),
(9, '123456789', 'JOSE', 'DELGADO', '3157895175', 'CHAPINERO', 'jdelgado@gmail.com', NULL, '$2a$10$X.6osM6f7UAuR9NKJ7HGAe.DwJ6jhp/ptO6zT9IOKXCJMwtbPmSRC', 'ACTIVO', 1, '2025-01-13', NULL, NULL),
(17, 'PADRE1764702431147', 'Vicente', 'Fernandez', '3158102820', 'las lajas', 'bjcabrab@gmail.com', NULL, 'ThHt/2RteiF/I2VTdcqxIA==:fPRhqRcaeHjEWlxwlOJqiUOguNmucAYAgDoF+LXZhc4=', 'ACTIVO', 1, '2025-12-02', 3, NULL),
(18, 'PADRE1764704113433', 'Carlos', 'Marx', '3158102820', 'las lajas', 'bjcabrab@gmail.com', NULL, 'kZGzdBHSM27ePwvbqa8wZw==:OIx1IjjHXyDEhKFL8AvKRnX3luOTTm4fdIHBnXexQTs=', 'ACTIVO', 1, '2025-12-02', 3, NULL),
(19, '1052395000', 'CLARA', 'MARTINEZ', NULL, 'NO_ESPECIFICADO', 'bjcabrab@gmail.com', NULL, '$2a$10$gbps0ljMVQdCh2BFEiOthOylsuVIvLSUvvffAXOlHxUyVh943r40u', 'ACTIVO', 1, '2025-12-05', 4, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_rol`
--

CREATE TABLE `usuario_rol` (
  `ID_USUARIO_ROL` int(11) NOT NULL,
  `USUARIO_ID_USUARIO` int(11) NOT NULL,
  `ROL_ID_ROL` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario_rol`
--

INSERT INTO `usuario_rol` (`ID_USUARIO_ROL`, `USUARIO_ID_USUARIO`, `ROL_ID_ROL`) VALUES
(1, 1, 1),
(2, 5, 2),
(3, 6, 5),
(4, 7, 1),
(5, 9, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valoracion_instrumento`
--

CREATE TABLE `valoracion_instrumento` (
  `ID_VALORACION_INSTRUMENTO` int(11) NOT NULL,
  `CARACTERIZACION_ID` int(11) DEFAULT NULL,
  `DIMENSION` varchar(50) NOT NULL,
  `ESTUDIANTE_IDENTIFICACION` varchar(100) DEFAULT NULL,
  `FECHA_VALORACION` timestamp NULL DEFAULT NULL,
  `PUNTUACION_TOTAL` int(11) DEFAULT NULL,
  `OBSERVACIONES` text DEFAULT NULL,
  `RECOMENDACIONES` text DEFAULT NULL,
  `CREATED_AT` timestamp NOT NULL DEFAULT current_timestamp(),
  `UPDATED_AT` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valoracion_respuesta`
--

CREATE TABLE `valoracion_respuesta` (
  `ID_RESPUESTA` int(11) NOT NULL,
  `ID_VALORACION_INSTRUMENTO` int(11) NOT NULL,
  `PREGUNTA_KEY` varchar(100) NOT NULL,
  `VALOR` int(11) NOT NULL,
  `COMENTARIO` text DEFAULT NULL,
  `CREATED_AT` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `v_dashboard_dimensiones`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `v_dashboard_dimensiones` (
`ID_CARACTERIZACION` int(11)
,`EXPEDIENTE_CARACTERIZACION` varchar(30)
,`NOMBRE_DIMENSION` varchar(100)
,`ESTADO` varchar(20)
,`PUNTUACION` int(11)
,`FECHA_VALORACION` timestamp
,`PORCENTAJE_AVANCE` int(3)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `v_resumen_caracterizaciones`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `v_resumen_caracterizaciones` (
`ID_CARACTERIZACION` int(11)
,`EXPEDIENTE_CARACTERIZACION` varchar(30)
,`CODIGO_CARACTERIZACION` varchar(100)
,`ESTADO_CARACTERIZACION` varchar(20)
,`FECHA_INICIO` timestamp
,`FECHA_FINALIZACION` datetime
,`NUMERO_DOCUMENTO_ESTUDIANTE` varchar(20)
,`NOMBRE_COMPLETO` varchar(183)
,`TIPO_TEA` varchar(30)
,`DIAGNOSTICO_CERTIFICADO` tinyint(1)
,`DIMENSIONES_COMPLETADAS` bigint(21)
,`TOTAL_DIMENSIONES` bigint(21)
,`TOTAL_OBSERVACIONES` bigint(21)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `v_dashboard_dimensiones`
--
DROP TABLE IF EXISTS `v_dashboard_dimensiones`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_dashboard_dimensiones`  AS SELECT `c`.`ID_CARACTERIZACION` AS `ID_CARACTERIZACION`, `c`.`expediente_caracterizacion` AS `EXPEDIENTE_CARACTERIZACION`, `dv`.`NOMBRE_DIMENSION` AS `NOMBRE_DIMENSION`, `dv`.`ESTADO` AS `ESTADO`, `dv`.`PUNTUACION` AS `PUNTUACION`, `dv`.`FECHA_VALORACION` AS `FECHA_VALORACION`, CASE WHEN `dv`.`ESTADO` = 'COMPLETADA' THEN 100 WHEN `dv`.`ESTADO` = 'EN_PROCESO' THEN 50 ELSE 0 END AS `PORCENTAJE_AVANCE` FROM (`caracterizacion` `c` left join `dimension_valoracion` `dv` on(`c`.`ID_CARACTERIZACION` = `dv`.`CARACTERIZACION_ID`)) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `v_resumen_caracterizaciones`
--
DROP TABLE IF EXISTS `v_resumen_caracterizaciones`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_resumen_caracterizaciones`  AS SELECT `c`.`ID_CARACTERIZACION` AS `ID_CARACTERIZACION`, `c`.`expediente_caracterizacion` AS `EXPEDIENTE_CARACTERIZACION`, `c`.`CODIGO_CARACTERIZACION` AS `CODIGO_CARACTERIZACION`, `c`.`estado_caracterizacion` AS `ESTADO_CARACTERIZACION`, `c`.`fecha_inicio` AS `FECHA_INICIO`, `c`.`FECHA_FINALIZACION` AS `FECHA_FINALIZACION`, `e`.`NUMERO_DOCUMENTO_ESTUDIANTE` AS `NUMERO_DOCUMENTO_ESTUDIANTE`, concat(`e`.`PRIMER_NOMBRE_ESTUDIANTE`,' ',ifnull(`e`.`SEGUNDO_NOMBRE_ESTUDIANTE`,''),' ',`e`.`PRIMER_APELLIDO_ESTUDIANTE`,' ',ifnull(`e`.`SEGUNDO_APELLIDO_ESTUDIANTE`,'')) AS `NOMBRE_COMPLETO`, `e`.`tipo_tea` AS `TIPO_TEA`, `e`.`diagnostico_certificado` AS `DIAGNOSTICO_CERTIFICADO`, (select count(0) from `dimension_valoracion` `dv` where `dv`.`CARACTERIZACION_ID` = `c`.`ID_CARACTERIZACION` and `dv`.`ESTADO` = 'COMPLETADA') AS `DIMENSIONES_COMPLETADAS`, (select count(0) from `dimension_valoracion` `dv` where `dv`.`CARACTERIZACION_ID` = `c`.`ID_CARACTERIZACION`) AS `TOTAL_DIMENSIONES`, (select count(0) from `observacion_sistematica` `os` where `os`.`CARACTERIZACION_ID` = `c`.`ID_CARACTERIZACION`) AS `TOTAL_OBSERVACIONES` FROM (`caracterizacion` `c` join `estudiante` `e` on(`c`.`ESTUDIANTE_ID_ESTUDIANTE` = `e`.`ID_ESTUDIANTE`)) ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `acciones`
--
ALTER TABLE `acciones`
  ADD PRIMARY KEY (`ID_ACCIONES`);

--
-- Indices de la tabla `actividad_clase`
--
ALTER TABLE `actividad_clase`
  ADD PRIMARY KEY (`ID_ACTIVIDAD_CLASE`),
  ADD KEY `PIAR_CODIGO_PIAR` (`PIAR_CODIGO_PIAR`);

--
-- Indices de la tabla `area`
--
ALTER TABLE `area`
  ADD PRIMARY KEY (`ID_AREA`);

--
-- Indices de la tabla `audit_estudiante`
--
ALTER TABLE `audit_estudiante`
  ADD PRIMARY KEY (`audit_id`);

--
-- Indices de la tabla `boletin_academico`
--
ALTER TABLE `boletin_academico`
  ADD PRIMARY KEY (`ID_BOLETIN_ACADEMICO`),
  ADD KEY `NOVEDADES_REPORTES_ID_NOVEDADES_REPORTES` (`NOVEDADES_REPORTES_ID_NOVEDADES_REPORTES`),
  ADD KEY `ESTUDIANTE_ID_ESTUDIANTE` (`ESTUDIANTE_ID_ESTUDIANTE`);

--
-- Indices de la tabla `caracterizacion`
--
ALTER TABLE `caracterizacion`
  ADD PRIMARY KEY (`ID_CARACTERIZACION`),
  ADD UNIQUE KEY `expediente_caracterizacion` (`expediente_caracterizacion`),
  ADD KEY `caracterizacion_ibfk_1` (`ESTUDIANTE_ID_ESTUDIANTE`),
  ADD KEY `idx_caracterizacion_expediente` (`expediente_caracterizacion`),
  ADD KEY `idx_caracterizacion_estado` (`estado_caracterizacion`),
  ADD KEY `idx_caracterizacion_estudiante` (`ESTUDIANTE_ID_ESTUDIANTE`);

--
-- Indices de la tabla `contexto_escolar`
--
ALTER TABLE `contexto_escolar`
  ADD PRIMARY KEY (`id_contexto_escolar`),
  ADD UNIQUE KEY `uk_contexto_por_caracterizacion` (`id_caracterizacion`),
  ADD KEY `fk_contexto_caracterizacion_idx` (`id_caracterizacion`);

--
-- Indices de la tabla `contexto_familiar`
--
ALTER TABLE `contexto_familiar`
  ADD PRIMARY KEY (`ID_CONTEXTO_FAMILIAR`),
  ADD UNIQUE KEY `UC_CARACTERIZACION_FAMILIA` (`CARACTERIZACION_ID`),
  ADD KEY `FK_CONTEXTO_FAMILIA_CREATEDBY` (`CREATED_BY`),
  ADD KEY `FK_CONTEXTO_FAMILIA_UPDATEDBY` (`UPDATED_BY`),
  ADD KEY `IDX_CONTEXTO_FAMILIA_CARACTERIZACION` (`CARACTERIZACION_ID`),
  ADD KEY `IDX_CONTEXTO_FAMILIA_ACUDIENTE_EMAIL` (`ACUDIENTE_EMAIL`),
  ADD KEY `IDX_CONTEXTO_FAMILIA_CREATED_AT` (`CREATED_AT`);

--
-- Indices de la tabla `curso`
--
ALTER TABLE `curso`
  ADD PRIMARY KEY (`ID_CURSO`),
  ADD KEY `GRADO_ID_GRADO` (`GRADO_ID_GRADO`),
  ADD KEY `ID_INSTITUCION_ID` (`ID_INSTITUCION_ID`);

--
-- Indices de la tabla `dba`
--
ALTER TABLE `dba`
  ADD PRIMARY KEY (`ID_DBA`),
  ADD KEY `AREA_DBA` (`AREA_DBA`),
  ADD KEY `GRADO` (`GRADO`),
  ADD KEY `PERIODO` (`PERIODO`);

--
-- Indices de la tabla `dimension_valoracion`
--
ALTER TABLE `dimension_valoracion`
  ADD PRIMARY KEY (`ID_DIMENSION`),
  ADD KEY `idx_dimension_caracterizacion` (`CARACTERIZACION_ID`),
  ADD KEY `idx_dimension_estado` (`ESTADO`),
  ADD KEY `idx_dimension_nombre` (`NOMBRE_DIMENSION`);

--
-- Indices de la tabla `dim_bienestar_emocional`
--
ALTER TABLE `dim_bienestar_emocional`
  ADD PRIMARY KEY (`ID_DIM_EMOCIONAL`),
  ADD KEY `fk_dim_emo_car_idx` (`CARACTERIZACION_ID`);

--
-- Indices de la tabla `dim_conducta_adaptativa`
--
ALTER TABLE `dim_conducta_adaptativa`
  ADD PRIMARY KEY (`ID_DIM_ADAPTATIVA`),
  ADD KEY `fk_dim_adap_car_idx` (`CARACTERIZACION_ID`);

--
-- Indices de la tabla `dim_control_entorno`
--
ALTER TABLE `dim_control_entorno`
  ADD PRIMARY KEY (`ID_DIM_CONTROL`),
  ADD KEY `fk_dim_ctrl_car_idx` (`CARACTERIZACION_ID`);

--
-- Indices de la tabla `dim_habilidades_intelectuales`
--
ALTER TABLE `dim_habilidades_intelectuales`
  ADD PRIMARY KEY (`ID_DIM_INTELECTUAL`),
  ADD KEY `fk_dim_inte_car_idx` (`CARACTERIZACION_ID`);

--
-- Indices de la tabla `dim_participacion_social`
--
ALTER TABLE `dim_participacion_social`
  ADD PRIMARY KEY (`ID_DIM_PARTICIPACION`),
  ADD KEY `fk_dim_part_car_idx` (`CARACTERIZACION_ID`);

--
-- Indices de la tabla `dim_pedagogica`
--
ALTER TABLE `dim_pedagogica`
  ADD PRIMARY KEY (`ID_DIM_PEDAGOGICA`),
  ADD KEY `fk_dim_peda_car_idx` (`CARACTERIZACION_ID`);

--
-- Indices de la tabla `dim_salud_fisica`
--
ALTER TABLE `dim_salud_fisica`
  ADD PRIMARY KEY (`ID_DIM_SALUD`),
  ADD KEY `fk_dim_salud_car_idx` (`CARACTERIZACION_ID`);

--
-- Indices de la tabla `entidades`
--
ALTER TABLE `entidades`
  ADD PRIMARY KEY (`ID_ENTIDAD`);

--
-- Indices de la tabla `estrategias_educativas`
--
ALTER TABLE `estrategias_educativas`
  ADD PRIMARY KEY (`ID_ESTRATEGIAS_EDUCATIVAS`);

--
-- Indices de la tabla `estudiante`
--
ALTER TABLE `estudiante`
  ADD PRIMARY KEY (`ID_ESTUDIANTE`),
  ADD UNIQUE KEY `uq_est_doc` (`TIPO_DOCUMENTO_ID_TIPO_DOCUMENTO`,`NUMERO_DOCUMENTO_ESTUDIANTE`),
  ADD UNIQUE KEY `expediente_id` (`expediente_id`),
  ADD KEY `TIPO_DOCUMENTO_ID_TIPO_DOCUMENTO` (`TIPO_DOCUMENTO_ID_TIPO_DOCUMENTO`),
  ADD KEY `CURSO_ID_CURSO` (`CURSO_ID_CURSO`);

--
-- Indices de la tabla `estudiante_contacto`
--
ALTER TABLE `estudiante_contacto`
  ADD PRIMARY KEY (`estudiante_contacto_id`),
  ADD KEY `estudiante_id` (`estudiante_id`);

--
-- Indices de la tabla `estudiante_has_trastorno`
--
ALTER TABLE `estudiante_has_trastorno`
  ADD PRIMARY KEY (`ID_ESTUDIANTE_has_TRASTORNO`),
  ADD KEY `ESTUDIANTE_ID_ESTUDIANTE` (`ESTUDIANTE_ID_ESTUDIANTE`),
  ADD KEY `TRASTORNO_ID_TRASTORNO` (`TRASTORNO_ID_TRASTORNO`);

--
-- Indices de la tabla `estudiante_senal_alerta`
--
ALTER TABLE `estudiante_senal_alerta`
  ADD PRIMARY KEY (`id_senal_alerta`),
  ADD KEY `estudiante_id` (`estudiante_id`);

--
-- Indices de la tabla `evaluacion`
--
ALTER TABLE `evaluacion`
  ADD PRIMARY KEY (`ID_EVALUACION`),
  ADD KEY `BOLETIN_ACADEMICO_ID_BOLETIN_ACADEMICO` (`BOLETIN_ACADEMICO_ID_BOLETIN_ACADEMICO`),
  ADD KEY `PIAR_SEGUIMIENTO_EVALUATIVO` (`PIAR_SEGUIMIENTO_EVALUATIVO`);

--
-- Indices de la tabla `expediente_counters`
--
ALTER TABLE `expediente_counters`
  ADD PRIMARY KEY (`year`);

--
-- Indices de la tabla `fecha_registro`
--
ALTER TABLE `fecha_registro`
  ADD PRIMARY KEY (`ID_FECHA_REGISTRO`);

--
-- Indices de la tabla `grado`
--
ALTER TABLE `grado`
  ADD PRIMARY KEY (`ID_GRADO`);

--
-- Indices de la tabla `grado_materia`
--
ALTER TABLE `grado_materia`
  ADD PRIMARY KEY (`ID_GRADO_MATERIA`),
  ADD KEY `GRADO_ID_GRADO` (`GRADO_ID_GRADO`),
  ADD KEY `MATERIA_ID_MATERIA` (`MATERIA_ID_MATERIA`);

--
-- Indices de la tabla `historial_caracterizacion`
--
ALTER TABLE `historial_caracterizacion`
  ADD PRIMARY KEY (`ID_HISTORIAL`),
  ADD KEY `idx_historial_caracterizacion` (`CARACTERIZACION_ID`),
  ADD KEY `idx_historial_fecha` (`FECHA_ACCION`),
  ADD KEY `idx_historial_usuario` (`USUARIO_ID`);

--
-- Indices de la tabla `institucion`
--
ALTER TABLE `institucion`
  ADD PRIMARY KEY (`ID_INSTITUCION`);

--
-- Indices de la tabla `materia`
--
ALTER TABLE `materia`
  ADD PRIMARY KEY (`ID_MATERIA`),
  ADD KEY `AREA_ID_AREA` (`AREA_ID_AREA`);

--
-- Indices de la tabla `matricula`
--
ALTER TABLE `matricula`
  ADD PRIMARY KEY (`ID_MATRICULA`),
  ADD KEY `INSTITUCION_ID_INSTITUCION` (`INSTITUCION_ID_INSTITUCION`),
  ADD KEY `FECHA_REGISTRO_ID_FECHA_REGISTRO` (`FECHA_REGISTRO_ID_FECHA_REGISTRO`),
  ADD KEY `ESTUDIANTE_ID_ESTUDIANTE` (`ESTUDIANTE_ID_ESTUDIANTE`);

--
-- Indices de la tabla `novedades_reportes`
--
ALTER TABLE `novedades_reportes`
  ADD PRIMARY KEY (`ID_NOVEDADES_REPORTES`),
  ADD KEY `USUARIOS_ID_USUARIO` (`USUARIOS_ID_USUARIO`),
  ADD KEY `fk_novedades_estudiante` (`ESTUDIANTE_ID`);

--
-- Indices de la tabla `observacion_sistematica`
--
ALTER TABLE `observacion_sistematica`
  ADD PRIMARY KEY (`ID_OBSERVACION`),
  ADD KEY `idx_observacion_caracterizacion` (`CARACTERIZACION_ID`),
  ADD KEY `idx_observacion_fecha` (`FECHA_OBSERVACION`),
  ADD KEY `idx_observacion_entorno` (`ENTORNO`);

--
-- Indices de la tabla `periodo`
--
ALTER TABLE `periodo`
  ADD PRIMARY KEY (`ID_PERIODO`);

--
-- Indices de la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD PRIMARY KEY (`ID_PERMISO`),
  ADD KEY `ACCION_ID_ACCION` (`ACCION_ID_ACCION`),
  ADD KEY `ENTIDAD_ID_ENTIDAD` (`ENTIDAD_ID_ENTIDAD`);

--
-- Indices de la tabla `piar`
--
ALTER TABLE `piar`
  ADD PRIMARY KEY (`ID_PIAR`),
  ADD KEY `ESTUDIANTE_ID_ESTUDIANTE` (`ESTUDIANTE_ID_ESTUDIANTE`),
  ADD KEY `SEGUIMIENTO_EVALUATIVO` (`SEGUIMIENTO_EVALUATIVO`),
  ADD KEY `AREA_ID_AREA` (`AREA_ID_AREA`),
  ADD KEY `DBA_ID_DBA` (`DBA_ID_DBA`);

--
-- Indices de la tabla `piar_has_estrategias_educativas`
--
ALTER TABLE `piar_has_estrategias_educativas`
  ADD PRIMARY KEY (`CODIGO_PIAR_has_ESTRATEGIAS_EDUCATIVAS`),
  ADD KEY `PIAR_ID_PIAR` (`PIAR_ID_PIAR`),
  ADD KEY `ESTRATEGIAS_EDUCATIVAS_ID_ESTRATEGIAS_EDUCATIVAS` (`ESTRATEGIAS_EDUCATIVAS_ID_ESTRATEGIAS_EDUCATIVAS`);

--
-- Indices de la tabla `protocolos_rutas`
--
ALTER TABLE `protocolos_rutas`
  ADD PRIMARY KEY (`ID_PROTOCOLOS_RUTAS`),
  ADD KEY `NOVEDADES_REPORTES_ID_NOVEDADES_REPORTES` (`NOVEDADES_REPORTES_ID_NOVEDADES_REPORTES`);

--
-- Indices de la tabla `representante_legal`
--
ALTER TABLE `representante_legal`
  ADD PRIMARY KEY (`ID_REPRESENTANTE_LEGAL`),
  ADD KEY `FK_REPRESENTANTE_INSTITUCION` (`INSTITUCION_ID_INSTITUCION`);

--
-- Indices de la tabla `reunion_socializacion`
--
ALTER TABLE `reunion_socializacion`
  ADD PRIMARY KEY (`ID_REUNION`),
  ADD KEY `idx_reunion_caracterizacion` (`CARACTERIZACION_ID`),
  ADD KEY `idx_reunion_fecha` (`FECHA_PROGRAMADA`),
  ADD KEY `idx_reunion_estado` (`ESTADO`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`ID_ROL`);

--
-- Indices de la tabla `rol_permiso`
--
ALTER TABLE `rol_permiso`
  ADD PRIMARY KEY (`ID_ROL_PERMISO`),
  ADD KEY `rol_id` (`ROL_ID_ROL`),
  ADD KEY `permiso_id` (`PERMISO_ID_PERMISO`);

--
-- Indices de la tabla `tipo_documento`
--
ALTER TABLE `tipo_documento`
  ADD PRIMARY KEY (`ID_TIPO_DOCUMENTO`);

--
-- Indices de la tabla `trastorno`
--
ALTER TABLE `trastorno`
  ADD PRIMARY KEY (`ID_TRASTORNO`);

--
-- Indices de la tabla `usuarioprof_has_materia`
--
ALTER TABLE `usuarioprof_has_materia`
  ADD PRIMARY KEY (`ID_USUARIOPROF_MATERIA`),
  ADD KEY `MATERIA_ID_MATERIA` (`MATERIA_ID_MATERIA`),
  ADD KEY `USUARIOS_ID_USUARIO` (`USUARIOS_ID_USUARIO`),
  ADD KEY `usuarioprof_has_materia_ibfk_3` (`USUARIOS_ROL_ID_ROL`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`ID_USUARIO`),
  ADD KEY `TIPO_DOCUMENTO_ID_TIPO_DOCUMENTO` (`TIPO_DOCUMENTO_ID_TIPO_DOCUMENTO`),
  ADD KEY `FECHA_REGISTRO_ID_FECHA_REGISTRO` (`FECHA_REGISTRO_ID_FECHA_REGISTRO`),
  ADD KEY `fk_usuarios_rol_final` (`ROL_ID_ROL`);

--
-- Indices de la tabla `usuario_rol`
--
ALTER TABLE `usuario_rol`
  ADD PRIMARY KEY (`ID_USUARIO_ROL`),
  ADD KEY `usuario_id` (`USUARIO_ID_USUARIO`),
  ADD KEY `rol_id` (`ROL_ID_ROL`);

--
-- Indices de la tabla `valoracion_instrumento`
--
ALTER TABLE `valoracion_instrumento`
  ADD PRIMARY KEY (`ID_VALORACION_INSTRUMENTO`),
  ADD KEY `fk_valoracion_caracterizacion` (`CARACTERIZACION_ID`),
  ADD KEY `idx_valoracion_dim` (`DIMENSION`),
  ADD KEY `idx_valoracion_estudiante` (`ESTUDIANTE_IDENTIFICACION`);

--
-- Indices de la tabla `valoracion_respuesta`
--
ALTER TABLE `valoracion_respuesta`
  ADD PRIMARY KEY (`ID_RESPUESTA`),
  ADD KEY `idx_respuesta_valoracion` (`ID_VALORACION_INSTRUMENTO`),
  ADD KEY `idx_respuesta_pregunta` (`PREGUNTA_KEY`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `acciones`
--
ALTER TABLE `acciones`
  MODIFY `ID_ACCIONES` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `actividad_clase`
--
ALTER TABLE `actividad_clase`
  MODIFY `ID_ACTIVIDAD_CLASE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `area`
--
ALTER TABLE `area`
  MODIFY `ID_AREA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `audit_estudiante`
--
ALTER TABLE `audit_estudiante`
  MODIFY `audit_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `boletin_academico`
--
ALTER TABLE `boletin_academico`
  MODIFY `ID_BOLETIN_ACADEMICO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `caracterizacion`
--
ALTER TABLE `caracterizacion`
  MODIFY `ID_CARACTERIZACION` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `contexto_escolar`
--
ALTER TABLE `contexto_escolar`
  MODIFY `id_contexto_escolar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `contexto_familiar`
--
ALTER TABLE `contexto_familiar`
  MODIFY `ID_CONTEXTO_FAMILIAR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `curso`
--
ALTER TABLE `curso`
  MODIFY `ID_CURSO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT de la tabla `dba`
--
ALTER TABLE `dba`
  MODIFY `ID_DBA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `dimension_valoracion`
--
ALTER TABLE `dimension_valoracion`
  MODIFY `ID_DIMENSION` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID único de la dimensión', AUTO_INCREMENT=145;

--
-- AUTO_INCREMENT de la tabla `dim_bienestar_emocional`
--
ALTER TABLE `dim_bienestar_emocional`
  MODIFY `ID_DIM_EMOCIONAL` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `dim_conducta_adaptativa`
--
ALTER TABLE `dim_conducta_adaptativa`
  MODIFY `ID_DIM_ADAPTATIVA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `dim_control_entorno`
--
ALTER TABLE `dim_control_entorno`
  MODIFY `ID_DIM_CONTROL` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `dim_habilidades_intelectuales`
--
ALTER TABLE `dim_habilidades_intelectuales`
  MODIFY `ID_DIM_INTELECTUAL` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `dim_participacion_social`
--
ALTER TABLE `dim_participacion_social`
  MODIFY `ID_DIM_PARTICIPACION` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `dim_pedagogica`
--
ALTER TABLE `dim_pedagogica`
  MODIFY `ID_DIM_PEDAGOGICA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `dim_salud_fisica`
--
ALTER TABLE `dim_salud_fisica`
  MODIFY `ID_DIM_SALUD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `entidades`
--
ALTER TABLE `entidades`
  MODIFY `ID_ENTIDAD` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `estrategias_educativas`
--
ALTER TABLE `estrategias_educativas`
  MODIFY `ID_ESTRATEGIAS_EDUCATIVAS` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `estudiante`
--
ALTER TABLE `estudiante`
  MODIFY `ID_ESTUDIANTE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `estudiante_contacto`
--
ALTER TABLE `estudiante_contacto`
  MODIFY `estudiante_contacto_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `estudiante_has_trastorno`
--
ALTER TABLE `estudiante_has_trastorno`
  MODIFY `ID_ESTUDIANTE_has_TRASTORNO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `estudiante_senal_alerta`
--
ALTER TABLE `estudiante_senal_alerta`
  MODIFY `id_senal_alerta` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `evaluacion`
--
ALTER TABLE `evaluacion`
  MODIFY `ID_EVALUACION` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `fecha_registro`
--
ALTER TABLE `fecha_registro`
  MODIFY `ID_FECHA_REGISTRO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `grado`
--
ALTER TABLE `grado`
  MODIFY `ID_GRADO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `grado_materia`
--
ALTER TABLE `grado_materia`
  MODIFY `ID_GRADO_MATERIA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT de la tabla `historial_caracterizacion`
--
ALTER TABLE `historial_caracterizacion`
  MODIFY `ID_HISTORIAL` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID único del historial';

--
-- AUTO_INCREMENT de la tabla `institucion`
--
ALTER TABLE `institucion`
  MODIFY `ID_INSTITUCION` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `materia`
--
ALTER TABLE `materia`
  MODIFY `ID_MATERIA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `matricula`
--
ALTER TABLE `matricula`
  MODIFY `ID_MATRICULA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `novedades_reportes`
--
ALTER TABLE `novedades_reportes`
  MODIFY `ID_NOVEDADES_REPORTES` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `observacion_sistematica`
--
ALTER TABLE `observacion_sistematica`
  MODIFY `ID_OBSERVACION` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID único de la observación';

--
-- AUTO_INCREMENT de la tabla `periodo`
--
ALTER TABLE `periodo`
  MODIFY `ID_PERIODO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `permisos`
--
ALTER TABLE `permisos`
  MODIFY `ID_PERMISO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;

--
-- AUTO_INCREMENT de la tabla `piar`
--
ALTER TABLE `piar`
  MODIFY `ID_PIAR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `piar_has_estrategias_educativas`
--
ALTER TABLE `piar_has_estrategias_educativas`
  MODIFY `CODIGO_PIAR_has_ESTRATEGIAS_EDUCATIVAS` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `protocolos_rutas`
--
ALTER TABLE `protocolos_rutas`
  MODIFY `ID_PROTOCOLOS_RUTAS` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `representante_legal`
--
ALTER TABLE `representante_legal`
  MODIFY `ID_REPRESENTANTE_LEGAL` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `reunion_socializacion`
--
ALTER TABLE `reunion_socializacion`
  MODIFY `ID_REUNION` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID único de la reunión';

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `ID_ROL` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `rol_permiso`
--
ALTER TABLE `rol_permiso`
  MODIFY `ID_ROL_PERMISO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=77;

--
-- AUTO_INCREMENT de la tabla `tipo_documento`
--
ALTER TABLE `tipo_documento`
  MODIFY `ID_TIPO_DOCUMENTO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `trastorno`
--
ALTER TABLE `trastorno`
  MODIFY `ID_TRASTORNO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `usuarioprof_has_materia`
--
ALTER TABLE `usuarioprof_has_materia`
  MODIFY `ID_USUARIOPROF_MATERIA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `ID_USUARIO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `usuario_rol`
--
ALTER TABLE `usuario_rol`
  MODIFY `ID_USUARIO_ROL` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `valoracion_instrumento`
--
ALTER TABLE `valoracion_instrumento`
  MODIFY `ID_VALORACION_INSTRUMENTO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `valoracion_respuesta`
--
ALTER TABLE `valoracion_respuesta`
  MODIFY `ID_RESPUESTA` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `actividad_clase`
--
ALTER TABLE `actividad_clase`
  ADD CONSTRAINT `actividad_clase_ibfk_1` FOREIGN KEY (`PIAR_CODIGO_PIAR`) REFERENCES `piar` (`ID_PIAR`);

--
-- Filtros para la tabla `boletin_academico`
--
ALTER TABLE `boletin_academico`
  ADD CONSTRAINT `boletin_academico_ibfk_1` FOREIGN KEY (`NOVEDADES_REPORTES_ID_NOVEDADES_REPORTES`) REFERENCES `novedades_reportes` (`ID_NOVEDADES_REPORTES`),
  ADD CONSTRAINT `boletin_academico_ibfk_2` FOREIGN KEY (`ESTUDIANTE_ID_ESTUDIANTE`) REFERENCES `estudiante` (`ID_ESTUDIANTE`);

--
-- Filtros para la tabla `caracterizacion`
--
ALTER TABLE `caracterizacion`
  ADD CONSTRAINT `caracterizacion_ibfk_1` FOREIGN KEY (`ESTUDIANTE_ID_ESTUDIANTE`) REFERENCES `estudiante` (`ID_ESTUDIANTE`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `contexto_escolar`
--
ALTER TABLE `contexto_escolar`
  ADD CONSTRAINT `fk_contexto_caracterizacion` FOREIGN KEY (`id_caracterizacion`) REFERENCES `caracterizacion` (`ID_CARACTERIZACION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `contexto_familiar`
--
ALTER TABLE `contexto_familiar`
  ADD CONSTRAINT `FK_CONTEXTO_FAMILIA_CARACTERIZACION` FOREIGN KEY (`CARACTERIZACION_ID`) REFERENCES `caracterizacion` (`ID_CARACTERIZACION`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_CONTEXTO_FAMILIA_CREATEDBY` FOREIGN KEY (`CREATED_BY`) REFERENCES `usuarios` (`ID_USUARIO`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_CONTEXTO_FAMILIA_UPDATEDBY` FOREIGN KEY (`UPDATED_BY`) REFERENCES `usuarios` (`ID_USUARIO`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `curso`
--
ALTER TABLE `curso`
  ADD CONSTRAINT `curso_ibfk_1` FOREIGN KEY (`GRADO_ID_GRADO`) REFERENCES `grado` (`ID_GRADO`),
  ADD CONSTRAINT `curso_ibfk_2` FOREIGN KEY (`ID_INSTITUCION_ID`) REFERENCES `institucion` (`ID_INSTITUCION`);

--
-- Filtros para la tabla `dba`
--
ALTER TABLE `dba`
  ADD CONSTRAINT `dba_ibfk_1` FOREIGN KEY (`AREA_DBA`) REFERENCES `area` (`ID_AREA`),
  ADD CONSTRAINT `dba_ibfk_2` FOREIGN KEY (`GRADO`) REFERENCES `grado` (`ID_GRADO`),
  ADD CONSTRAINT `dba_ibfk_3` FOREIGN KEY (`PERIODO`) REFERENCES `periodo` (`ID_PERIODO`);

--
-- Filtros para la tabla `dimension_valoracion`
--
ALTER TABLE `dimension_valoracion`
  ADD CONSTRAINT `fk_dimension_caracterizacion` FOREIGN KEY (`CARACTERIZACION_ID`) REFERENCES `caracterizacion` (`ID_CARACTERIZACION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `dim_bienestar_emocional`
--
ALTER TABLE `dim_bienestar_emocional`
  ADD CONSTRAINT `fk_dim_emo_car` FOREIGN KEY (`CARACTERIZACION_ID`) REFERENCES `caracterizacion` (`ID_CARACTERIZACION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `dim_conducta_adaptativa`
--
ALTER TABLE `dim_conducta_adaptativa`
  ADD CONSTRAINT `fk_dim_adap_car` FOREIGN KEY (`CARACTERIZACION_ID`) REFERENCES `caracterizacion` (`ID_CARACTERIZACION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `dim_control_entorno`
--
ALTER TABLE `dim_control_entorno`
  ADD CONSTRAINT `fk_dim_ctrl_car` FOREIGN KEY (`CARACTERIZACION_ID`) REFERENCES `caracterizacion` (`ID_CARACTERIZACION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `dim_habilidades_intelectuales`
--
ALTER TABLE `dim_habilidades_intelectuales`
  ADD CONSTRAINT `fk_dim_inte_car` FOREIGN KEY (`CARACTERIZACION_ID`) REFERENCES `caracterizacion` (`ID_CARACTERIZACION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `dim_participacion_social`
--
ALTER TABLE `dim_participacion_social`
  ADD CONSTRAINT `fk_dim_part_car` FOREIGN KEY (`CARACTERIZACION_ID`) REFERENCES `caracterizacion` (`ID_CARACTERIZACION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `dim_pedagogica`
--
ALTER TABLE `dim_pedagogica`
  ADD CONSTRAINT `fk_dim_peda_car` FOREIGN KEY (`CARACTERIZACION_ID`) REFERENCES `caracterizacion` (`ID_CARACTERIZACION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `dim_salud_fisica`
--
ALTER TABLE `dim_salud_fisica`
  ADD CONSTRAINT `fk_dim_salud_car` FOREIGN KEY (`CARACTERIZACION_ID`) REFERENCES `caracterizacion` (`ID_CARACTERIZACION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `estudiante`
--
ALTER TABLE `estudiante`
  ADD CONSTRAINT `FK_ESTUDIANTE_CURSO` FOREIGN KEY (`CURSO_ID_CURSO`) REFERENCES `curso` (`ID_CURSO`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_ESTUDIANTE_TIPO_DOCUMENTO_ESTUDIANTE` FOREIGN KEY (`TIPO_DOCUMENTO_ID_TIPO_DOCUMENTO`) REFERENCES `tipo_documento` (`ID_TIPO_DOCUMENTO`);

--
-- Filtros para la tabla `estudiante_contacto`
--
ALTER TABLE `estudiante_contacto`
  ADD CONSTRAINT `estudiante_contacto_ibfk_1` FOREIGN KEY (`estudiante_id`) REFERENCES `estudiante` (`ID_ESTUDIANTE`) ON DELETE CASCADE;

--
-- Filtros para la tabla `estudiante_has_trastorno`
--
ALTER TABLE `estudiante_has_trastorno`
  ADD CONSTRAINT `estudiante_has_trastorno_ibfk_1` FOREIGN KEY (`ESTUDIANTE_ID_ESTUDIANTE`) REFERENCES `estudiante` (`ID_ESTUDIANTE`),
  ADD CONSTRAINT `estudiante_has_trastorno_ibfk_2` FOREIGN KEY (`TRASTORNO_ID_TRASTORNO`) REFERENCES `trastorno` (`ID_TRASTORNO`);

--
-- Filtros para la tabla `estudiante_senal_alerta`
--
ALTER TABLE `estudiante_senal_alerta`
  ADD CONSTRAINT `estudiante_senal_alerta_ibfk_1` FOREIGN KEY (`estudiante_id`) REFERENCES `estudiante` (`ID_ESTUDIANTE`) ON DELETE CASCADE;

--
-- Filtros para la tabla `evaluacion`
--
ALTER TABLE `evaluacion`
  ADD CONSTRAINT `evaluacion_ibfk_1` FOREIGN KEY (`PIAR_SEGUIMIENTO_EVALUATIVO`) REFERENCES `piar` (`SEGUIMIENTO_EVALUATIVO`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `evaluacion_ibfk_2` FOREIGN KEY (`BOLETIN_ACADEMICO_ID_BOLETIN_ACADEMICO`) REFERENCES `boletin_academico` (`ID_BOLETIN_ACADEMICO`);

--
-- Filtros para la tabla `grado_materia`
--
ALTER TABLE `grado_materia`
  ADD CONSTRAINT `grado_materia_ibfk_1` FOREIGN KEY (`GRADO_ID_GRADO`) REFERENCES `grado` (`ID_GRADO`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `grado_materia_ibfk_2` FOREIGN KEY (`MATERIA_ID_MATERIA`) REFERENCES `materia` (`ID_MATERIA`);

--
-- Filtros para la tabla `historial_caracterizacion`
--
ALTER TABLE `historial_caracterizacion`
  ADD CONSTRAINT `fk_historial_caracterizacion` FOREIGN KEY (`CARACTERIZACION_ID`) REFERENCES `caracterizacion` (`ID_CARACTERIZACION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `materia`
--
ALTER TABLE `materia`
  ADD CONSTRAINT `materia_ibfk_1` FOREIGN KEY (`AREA_ID_AREA`) REFERENCES `area` (`ID_AREA`);

--
-- Filtros para la tabla `matricula`
--
ALTER TABLE `matricula`
  ADD CONSTRAINT `FK_MATRICULA_FECHA` FOREIGN KEY (`FECHA_REGISTRO_ID_FECHA_REGISTRO`) REFERENCES `fecha_registro` (`ID_FECHA_REGISTRO`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `matricula_ibfk_1` FOREIGN KEY (`INSTITUCION_ID_INSTITUCION`) REFERENCES `institucion` (`ID_INSTITUCION`),
  ADD CONSTRAINT `matricula_ibfk_2` FOREIGN KEY (`ESTUDIANTE_ID_ESTUDIANTE`) REFERENCES `estudiante` (`ID_ESTUDIANTE`);

--
-- Filtros para la tabla `novedades_reportes`
--
ALTER TABLE `novedades_reportes`
  ADD CONSTRAINT `NOVEDADES_REPORTES_ibfk_1` FOREIGN KEY (`USUARIOS_ID_USUARIO`) REFERENCES `usuarios` (`ID_USUARIO`),
  ADD CONSTRAINT `fk_novedades_estudiante` FOREIGN KEY (`ESTUDIANTE_ID`) REFERENCES `estudiante` (`ID_ESTUDIANTE`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `observacion_sistematica`
--
ALTER TABLE `observacion_sistematica`
  ADD CONSTRAINT `fk_observacion_caracterizacion` FOREIGN KEY (`CARACTERIZACION_ID`) REFERENCES `caracterizacion` (`ID_CARACTERIZACION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD CONSTRAINT `permisos_ibfk_1` FOREIGN KEY (`ACCION_ID_ACCION`) REFERENCES `acciones` (`ID_ACCIONES`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `permisos_ibfk_2` FOREIGN KEY (`ENTIDAD_ID_ENTIDAD`) REFERENCES `entidades` (`ID_ENTIDAD`);

--
-- Filtros para la tabla `piar`
--
ALTER TABLE `piar`
  ADD CONSTRAINT `piar_ibfk_1` FOREIGN KEY (`ESTUDIANTE_ID_ESTUDIANTE`) REFERENCES `estudiante` (`ID_ESTUDIANTE`) ON DELETE CASCADE,
  ADD CONSTRAINT `piar_ibfk_2` FOREIGN KEY (`DBA_ID_DBA`) REFERENCES `dba` (`ID_DBA`);

--
-- Filtros para la tabla `piar_has_estrategias_educativas`
--
ALTER TABLE `piar_has_estrategias_educativas`
  ADD CONSTRAINT `piar_has_estrategias_educativas_ibfk_1` FOREIGN KEY (`PIAR_ID_PIAR`) REFERENCES `piar` (`ID_PIAR`),
  ADD CONSTRAINT `piar_has_estrategias_educativas_ibfk_2` FOREIGN KEY (`ESTRATEGIAS_EDUCATIVAS_ID_ESTRATEGIAS_EDUCATIVAS`) REFERENCES `estrategias_educativas` (`ID_ESTRATEGIAS_EDUCATIVAS`);

--
-- Filtros para la tabla `protocolos_rutas`
--
ALTER TABLE `protocolos_rutas`
  ADD CONSTRAINT `protocolos_rutas_ibfk_1` FOREIGN KEY (`NOVEDADES_REPORTES_ID_NOVEDADES_REPORTES`) REFERENCES `novedades_reportes` (`ID_NOVEDADES_REPORTES`);

--
-- Filtros para la tabla `representante_legal`
--
ALTER TABLE `representante_legal`
  ADD CONSTRAINT `FK_REPRESENTANTE_INSTITUCION` FOREIGN KEY (`INSTITUCION_ID_INSTITUCION`) REFERENCES `institucion` (`ID_INSTITUCION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `reunion_socializacion`
--
ALTER TABLE `reunion_socializacion`
  ADD CONSTRAINT `fk_reunion_caracterizacion` FOREIGN KEY (`CARACTERIZACION_ID`) REFERENCES `caracterizacion` (`ID_CARACTERIZACION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `rol_permiso`
--
ALTER TABLE `rol_permiso`
  ADD CONSTRAINT `rol_permiso_ibfk_1` FOREIGN KEY (`ROL_ID_ROL`) REFERENCES `rol` (`ID_ROL`),
  ADD CONSTRAINT `rol_permiso_ibfk_2` FOREIGN KEY (`PERMISO_ID_PERMISO`) REFERENCES `permisos` (`ID_PERMISO`);

--
-- Filtros para la tabla `usuarioprof_has_materia`
--
ALTER TABLE `usuarioprof_has_materia`
  ADD CONSTRAINT `usuarioprof_has_materia_ibfk_1` FOREIGN KEY (`MATERIA_ID_MATERIA`) REFERENCES `materia` (`ID_MATERIA`),
  ADD CONSTRAINT `usuarioprof_has_materia_ibfk_2` FOREIGN KEY (`USUARIOS_ID_USUARIO`) REFERENCES `usuarios` (`ID_USUARIO`),
  ADD CONSTRAINT `usuarioprof_has_materia_ibfk_3` FOREIGN KEY (`USUARIOS_ROL_ID_ROL`) REFERENCES `rol` (`ID_ROL`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `FK_TIPO_DOCUMENTO_USUARIO` FOREIGN KEY (`TIPO_DOCUMENTO_ID_TIPO_DOCUMENTO`) REFERENCES `tipo_documento` (`ID_TIPO_DOCUMENTO`),
  ADD CONSTRAINT `fk_usuarios_rol_final` FOREIGN KEY (`ROL_ID_ROL`) REFERENCES `rol` (`ID_ROL`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_usuarios_rol_principal` FOREIGN KEY (`ROL_ID_ROL`) REFERENCES `rol` (`ID_ROL`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario_rol`
--
ALTER TABLE `usuario_rol`
  ADD CONSTRAINT `usuario_rol_ibfk_1` FOREIGN KEY (`USUARIO_ID_USUARIO`) REFERENCES `usuarios` (`ID_USUARIO`),
  ADD CONSTRAINT `usuario_rol_ibfk_2` FOREIGN KEY (`ROL_ID_ROL`) REFERENCES `rol` (`ID_ROL`);

--
-- Filtros para la tabla `valoracion_instrumento`
--
ALTER TABLE `valoracion_instrumento`
  ADD CONSTRAINT `fk_valoracion_caracterizacion` FOREIGN KEY (`CARACTERIZACION_ID`) REFERENCES `caracterizacion` (`ID_CARACTERIZACION`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `valoracion_respuesta`
--
ALTER TABLE `valoracion_respuesta`
  ADD CONSTRAINT `fk_respuesta_valoracion` FOREIGN KEY (`ID_VALORACION_INSTRUMENTO`) REFERENCES `valoracion_instrumento` (`ID_VALORACION_INSTRUMENTO`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
