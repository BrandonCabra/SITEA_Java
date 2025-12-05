-- Script SQL para actualizar el módulo de Caracterización
-- Sistema SITEA - Módulo de Caracterización Pedagógica y Social
-- Fecha: 2024

-- ============================================
-- 1. Actualizar tabla CARACTERIZACION
-- ============================================

-- ============================================
-- 0. Crear tabla expediente_counters (contador por año)
-- ============================================
CREATE TABLE IF NOT EXISTS expediente_counters (
    `year` INT NOT NULL PRIMARY KEY,
    last_counter INT NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- Agregar nuevos campos a la tabla caracterizacion
ALTER TABLE caracterizacion 
ADD COLUMN expediente_caracterizacion VARCHAR(30) UNIQUE COMMENT 'Expediente único formato CHAR-TEA-YYYY-####',
ADD COLUMN estado_caracterizacion VARCHAR(20) DEFAULT 'INICIADA' COMMENT 'Estados: INICIADA, EN_PROCESO, COMPLETADA, ARCHIVADA',
ADD COLUMN fecha_inicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de inicio del proceso',
ADD COLUMN fecha_finalizacion TIMESTAMP NULL COMMENT 'Fecha de finalización del proceso',
ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación del registro',
ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de última actualización',
ADD COLUMN created_by INT NULL COMMENT 'ID del usuario que creó el registro',
ADD COLUMN updated_by INT NULL COMMENT 'ID del usuario que actualizó el registro';

-- Crear índices para mejorar el rendimiento
CREATE INDEX idx_caracterizacion_expediente ON caracterizacion(expediente_caracterizacion);
CREATE INDEX idx_caracterizacion_estado ON caracterizacion(estado_caracterizacion);
CREATE INDEX idx_caracterizacion_estudiante ON caracterizacion(ESTUDIANTE_ID_ESTUDIANTE);

-- ============================================
-- 2. Crear tabla DIMENSION_VALORACION
-- ============================================

CREATE TABLE IF NOT EXISTS dimension_valoracion (
    ID_DIMENSION INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID único de la dimensión',
    CARACTERIZACION_ID INT NOT NULL COMMENT 'ID de la caracterizacion asociada',
    NOMBRE_DIMENSION VARCHAR(100) NOT NULL COMMENT 'Nombre de la dimensión según MEN',
    DESCRIPCION TEXT COMMENT 'Descripción detallada de la valoración',
    FORTALEZAS TEXT COMMENT 'Fortalezas identificadas en esta dimensión',
    AREAS_APOYO TEXT COMMENT 'Áreas que requieren apoyo',
    PUNTUACION INT COMMENT 'Puntuación de la valoración (escala 1-5)',
    ESTADO VARCHAR(20) DEFAULT 'PENDIENTE' COMMENT 'Estados: PENDIENTE, EN_PROCESO, COMPLETADA',
    FECHA_VALORACION TIMESTAMP NULL COMMENT 'Fecha de valoración',
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación',
    UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de actualización',
    
    CONSTRAINT fk_dimension_caracterizacion 
        FOREIGN KEY (CARACTERIZACION_ID) 
        REFERENCES caracterizacion(ID_CARACTERIZACION) 
        ON DELETE CASCADE ON UPDATE CASCADE,
    
    INDEX idx_dimension_caracterizacion (CARACTERIZACION_ID),
    INDEX idx_dimension_estado (ESTADO),
    INDEX idx_dimension_nombre (NOMBRE_DIMENSION)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Tabla para gestionar las 8 dimensiones de valoración según MEN';

-- ============================================
-- 3. Crear tabla OBSERVACION_SISTEMATICA
-- ============================================

CREATE TABLE IF NOT EXISTS observacion_sistematica (
    ID_OBSERVACION INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID único de la observación',
    CARACTERIZACION_ID INT NOT NULL COMMENT 'ID de la caracterización asociada',
    FECHA_OBSERVACION TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha y hora de la observación',
    ENTORNO VARCHAR(50) NOT NULL COMMENT 'Entorno: AULA, RECREO, HOGAR, EXTRACURRICULAR',
    DESCRIPCION TEXT COMMENT 'Descripción detallada de la observación',
    CONTEXTO TEXT COMMENT 'Contexto en el que se realizó la observación',
    EVIDENCIAS VARCHAR(300) COMMENT 'Ruta a archivos adjuntos o evidencias',
    OBSERVADOR VARCHAR(100) COMMENT 'Nombre del observador',
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación del registro',
    
    CONSTRAINT fk_observacion_caracterizacion 
        FOREIGN KEY (CARACTERIZACION_ID) 
        REFERENCES caracterizacion(ID_CARACTERIZACION) 
        ON DELETE CASCADE ON UPDATE CASCADE,
    
    INDEX idx_observacion_caracterizacion (CARACTERIZACION_ID),
    INDEX idx_observacion_fecha (FECHA_OBSERVACION),
    INDEX idx_observacion_entorno (ENTORNO)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Tabla para gestionar observaciones sistemáticas del estudiante en diferentes entornos';

-- ============================================
-- 4. Crear tabla REUNION_SOCIALIZACION
-- ============================================

CREATE TABLE IF NOT EXISTS reunion_socializacion (
    ID_REUNION INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID único de la reunión',
    CARACTERIZACION_ID INT NOT NULL COMMENT 'ID de la caracterización asociada',
    TIPO_REUNION VARCHAR(50) NOT NULL COMMENT 'Tipo: INICIAL, SEGUIMIENTO, CIERRE',
    FECHA_PROGRAMADA TIMESTAMP NOT NULL COMMENT 'Fecha programada de la reunión',
    FECHA_REALIZADA TIMESTAMP NULL COMMENT 'Fecha real de realización',
    PARTICIPANTES TEXT COMMENT 'Lista de participantes',
    ACUERDOS TEXT COMMENT 'Acuerdos y compromisos establecidos',
    OBSERVACIONES TEXT COMMENT 'Observaciones adicionales',
    ESTADO VARCHAR(20) DEFAULT 'PROGRAMADA' COMMENT 'Estados: PROGRAMADA, REALIZADA, CANCELADA',
    ACTA_URL VARCHAR(300) COMMENT 'Ruta al acta de la reunión',
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación',
    UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de actualización',
    
    CONSTRAINT fk_reunion_caracterizacion 
        FOREIGN KEY (CARACTERIZACION_ID) 
        REFERENCES caracterizacion(ID_CARACTERIZACION) 
        ON DELETE CASCADE ON UPDATE CASCADE,
    
    INDEX idx_reunion_caracterizacion (CARACTERIZACION_ID),
    INDEX idx_reunion_fecha (FECHA_PROGRAMADA),
    INDEX idx_reunion_estado (ESTADO)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Tabla para gestionar reuniones de socialización con familia y equipo docente';

-- ============================================
-- 5. Crear tabla HISTORIAL_CARACTERIZACION
-- ============================================

CREATE TABLE IF NOT EXISTS historial_caracterizacion (
    ID_HISTORIAL INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID único del historial',
    CARACTERIZACION_ID INT NOT NULL COMMENT 'ID de la caracterización',
    ACCION VARCHAR(50) NOT NULL COMMENT 'Tipo de acción realizada',
    DESCRIPCION TEXT COMMENT 'Descripción de la acción',
    USUARIO_ID INT COMMENT 'ID del usuario que realizó la acción',
    FECHA_ACCION TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de la acción',
    DATOS_ANTERIORES TEXT COMMENT 'Datos antes del cambio (JSON)',
    DATOS_NUEVOS TEXT COMMENT 'Datos después del cambio (JSON)',
    
    CONSTRAINT fk_historial_caracterizacion 
        FOREIGN KEY (CARACTERIZACION_ID) 
        REFERENCES caracterizacion(ID_CARACTERIZACION) 
        ON DELETE CASCADE ON UPDATE CASCADE,
    
    INDEX idx_historial_caracterizacion (CARACTERIZACION_ID),
    INDEX idx_historial_fecha (FECHA_ACCION),
    INDEX idx_historial_usuario (USUARIO_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Tabla para auditoría y seguimiento de cambios en caracterizaciones';

-- ============================================
-- 6. Insertar dimensiones iniciales (Opcional)
-- ============================================

-- Este script se puede ejecutar después de crear una caracterización
-- para inicializar automáticamente las 8 dimensiones del MEN

DELIMITER $$

CREATE PROCEDURE IF NOT EXISTS inicializar_dimensiones(IN p_caracterizacion_id INT)
BEGIN
    DECLARE v_dimensiones VARCHAR(500);
    DECLARE v_dimension VARCHAR(100);
    DECLARE v_pos INT;
    
    SET v_dimensiones = 'Contexto y vida familiar,Habilidades intelectuales,Bienestar emocional,Conducta adaptativa y desarrollo personal,Salud y bienestar físico,Participación e inclusión social,Control del propio entorno,Dimensión pedagógica';
    
    WHILE LENGTH(v_dimensiones) > 0 DO
        SET v_pos = LOCATE(',', v_dimensiones);
        
        IF v_pos > 0 THEN
            SET v_dimension = SUBSTRING(v_dimensiones, 1, v_pos - 1);
            SET v_dimensiones = SUBSTRING(v_dimensiones, v_pos + 1);
        ELSE
            SET v_dimension = v_dimensiones;
            SET v_dimensiones = '';
        END IF;
        
        INSERT INTO dimension_valoracion (CARACTERIZACION_ID, NOMBRE_DIMENSION, ESTADO, CREATED_AT, UPDATED_AT)
        VALUES (p_caracterizacion_id, v_dimension, 'PENDIENTE', NOW(), NOW());
    END WHILE;
END$$

DELIMITER ;

-- ============================================
-- 7. Crear vistas útiles
-- ============================================

-- Vista para resumen de caracterizaciones
CREATE OR REPLACE VIEW v_resumen_caracterizaciones AS
SELECT 
    c.ID_CARACTERIZACION,
    c.EXPEDIENTE_CARACTERIZACION,
    c.CODIGO_CARACTERIZACION,
    c.ESTADO_CARACTERIZACION,
    c.FECHA_INICIO,
    c.FECHA_FINALIZACION,
    e.NUMERO_DOCUMENTO_ESTUDIANTE,
    CONCAT(e.PRIMER_NOMBRE_ESTUDIANTE, ' ', 
           IFNULL(e.SEGUNDO_NOMBRE_ESTUDIANTE, ''), ' ',
           e.PRIMER_APELLIDO_ESTUDIANTE, ' ',
           IFNULL(e.SEGUNDO_APELLIDO_ESTUDIANTE, '')) AS NOMBRE_COMPLETO,
    e.TIPO_TEA,
    e.DIAGNOSTICO_CERTIFICADO,
    (SELECT COUNT(*) FROM dimension_valoracion dv 
     WHERE dv.CARACTERIZACION_ID = c.ID_CARACTERIZACION 
     AND dv.ESTADO = 'COMPLETADA') AS DIMENSIONES_COMPLETADAS,
    (SELECT COUNT(*) FROM dimension_valoracion dv 
     WHERE dv.CARACTERIZACION_ID = c.ID_CARACTERIZACION) AS TOTAL_DIMENSIONES,
    (SELECT COUNT(*) FROM observacion_sistematica os 
     WHERE os.CARACTERIZACION_ID = c.ID_CARACTERIZACION) AS TOTAL_OBSERVACIONES
FROM caracterizacion c
INNER JOIN estudiante e ON c.ESTUDIANTE_ID_ESTUDIANTE = e.ID_ESTUDIANTE;

-- Vista para dashboard de dimensiones
CREATE OR REPLACE VIEW v_dashboard_dimensiones AS
SELECT 
    c.ID_CARACTERIZACION,
    c.EXPEDIENTE_CARACTERIZACION,
    dv.NOMBRE_DIMENSION,
    dv.ESTADO,
    dv.PUNTUACION,
    dv.FECHA_VALORACION,
    CASE 
        WHEN dv.ESTADO = 'COMPLETADA' THEN 100
        WHEN dv.ESTADO = 'EN_PROCESO' THEN 50
        ELSE 0
    END AS PORCENTAJE_AVANCE
FROM caracterizacion c
LEFT JOIN dimension_valoracion dv ON c.ID_CARACTERIZACION = dv.CARACTERIZACION_ID;

-- ============================================
-- 8. Datos de ejemplo (Opcional - Solo para desarrollo)
-- ============================================

-- Comentar estas líneas en producción
/*
-- Actualizar expedientes existentes
UPDATE caracterizacion 
SET expediente_caracterizacion = CONCAT('CHAR-TEA-2024-', LPAD(ID_CARACTERIZACION, 4, '0'))
WHERE expediente_caracterizacion IS NULL;

UPDATE caracterizacion 
SET estado_caracterizacion = 'EN_PROCESO'
WHERE estado_caracterizacion IS NULL;
*/

-- ============================================
-- 9. Permisos y seguridad
-- ============================================

-- Asegurar que las tablas tengan los permisos correctos
-- Ajustar según el usuario de la base de datos

-- GRANT SELECT, INSERT, UPDATE, DELETE ON sitea.dimension_valoracion TO 'sitea_user'@'localhost';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON sitea.observacion_sistematica TO 'sitea_user'@'localhost';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON sitea.reunion_socializacion TO 'sitea_user'@'localhost';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON sitea.historial_caracterizacion TO 'sitea_user'@'localhost';

-- ============================================
-- FIN DEL SCRIPT
-- ============================================

-- Verificar las tablas creadas
SHOW TABLES LIKE '%caracterizacion%';
SHOW TABLES LIKE '%dimension%';
SHOW TABLES LIKE '%observacion%';

-- Verificar la estructura
DESCRIBE caracterizacion;
DESCRIBE dimension_valoracion;
DESCRIBE observacion_sistematica;
DESCRIBE reunion_socializacion;
DESCRIBE historial_caracterizacion;
