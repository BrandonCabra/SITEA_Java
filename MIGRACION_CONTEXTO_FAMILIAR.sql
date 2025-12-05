/**
 * SCRIPT DE MIGRACIÓN - CONTEXTO FAMILIAR
 * 
 * Este script crea la tabla contexto_familiar para almacenar información
 * del contexto familiar del estudiante durante el proceso de caracterización
 * 
 * Base de datos: MariaDB/MySQL
 * Compatible con: JPA/Hibernate
 */

-- ============================================================================
-- Crear tabla contexto_familiar
-- ============================================================================

CREATE TABLE IF NOT EXISTS `contexto_familiar` (
  `ID_CONTEXTO_FAMILIAR` INT NOT NULL AUTO_INCREMENT,
  
  -- Relación con caracterización
  `CARACTERIZACION_ID` INT NOT NULL,
  
  -- Información del acudiente principal
  `ACUDIENTE_NOMBRE` VARCHAR(120),
  `ACUDIENTE_DOCUMENTO` VARCHAR(20),
  `ACUDIENTE_TELEFONO` VARCHAR(20),
  `ACUDIENTE_EMAIL` VARCHAR(120),
  `ACUDIENTE_PARENTESCO` VARCHAR(45),
  
  -- Información de la madre
  `MADRE_NOMBRE` VARCHAR(120),
  `MADRE_DOCUMENTO` VARCHAR(20),
  `MADRE_TELEFONO` VARCHAR(20),
  `MADRE_EMAIL` VARCHAR(120),
  `MADRE_OCUPACION` VARCHAR(150),
  `MADRE_ESCOLARIDAD` VARCHAR(20),
  
  -- Información del padre
  `PADRE_NOMBRE` VARCHAR(120),
  `PADRE_DOCUMENTO` VARCHAR(20),
  `PADRE_TELEFONO` VARCHAR(20),
  `PADRE_EMAIL` VARCHAR(120),
  `PADRE_OCUPACION` VARCHAR(150),
  `PADRE_ESCOLARIDAD` VARCHAR(20),
  
  -- Composición familiar y relaciones
  `OTROS_FAMILIARES` LONGTEXT,
  `RELACIONES_FAMILIARES` LONGTEXT,
  `COMUNICACION_FAMILIAR` LONGTEXT,
  
  -- Vivienda y situación socioeconómica
  `TIPO_VIVIENDA` VARCHAR(45),
  `TENENCIA_VIVIENDA` VARCHAR(45),
  `CONDICIONES_VIVIENDA` LONGTEXT,
  `SITUACION_ECONOMICA` LONGTEXT,
  
  -- Observaciones adicionales
  `OBSERVACIONES_FAMILIA` LONGTEXT,
  
  -- Auditoría
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_AT` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` INT,
  `UPDATED_BY` INT,
  
  PRIMARY KEY (`ID_CONTEXTO_FAMILIAR`),
  UNIQUE KEY `UC_CARACTERIZACION_FAMILIA` (`CARACTERIZACION_ID`),
  
  CONSTRAINT `FK_CONTEXTO_FAMILIA_CARACTERIZACION` 
    FOREIGN KEY (`CARACTERIZACION_ID`) 
    REFERENCES `caracterizacion` (`ID_CARACTERIZACION`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  
  CONSTRAINT `FK_CONTEXTO_FAMILIA_CREATEDBY` 
    FOREIGN KEY (`CREATED_BY`) 
    REFERENCES `usuarios` (`ID_USUARIO`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  
  CONSTRAINT `FK_CONTEXTO_FAMILIA_UPDATEDBY` 
    FOREIGN KEY (`UPDATED_BY`) 
    REFERENCES `usuarios` (`ID_USUARIO`)
    ON DELETE SET NULL
    ON UPDATE CASCADE
    
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================================
-- Crear índices para búsquedas comunes
-- ============================================================================

CREATE INDEX `IDX_CONTEXTO_FAMILIA_CARACTERIZACION` 
  ON `contexto_familiar` (`CARACTERIZACION_ID`);

CREATE INDEX `IDX_CONTEXTO_FAMILIA_ACUDIENTE_EMAIL` 
  ON `contexto_familiar` (`ACUDIENTE_EMAIL`);

CREATE INDEX `IDX_CONTEXTO_FAMILIA_CREATED_AT` 
  ON `contexto_familiar` (`CREATED_AT`);

-- ============================================================================
-- Verificar que la tabla se creó correctamente
-- ============================================================================

-- Describir la estructura de la tabla
-- DESCRIBE contexto_familiar;

-- Contar registros
-- SELECT COUNT(*) as total_registros FROM contexto_familiar;

-- ============================================================================
-- Script completado
-- ============================================================================
-- Ejecutar este script en tu base de datos antes de usar la entidad
-- ContextoFamiliar en la aplicación
