# Instrucciones: Ejecución de Migración SQL - Contexto Familiar

## 📋 Prerrequisitos

- ✅ Base de datos MariaDB/MySQL activa
- ✅ Credenciales de acceso con permisos CREATE TABLE
- ✅ Archivo de base de datos SITEA en funcionamiento

## 🚀 Pasos de Ejecución

### Opción 1: Línea de Comandos MySQL

```bash
# En terminal, conectarse a MySQL
mysql -u usuario -p

# En el prompt MySQL, seleccionar la base de datos
USE nombre_base_datos_sitea;

# Ejecutar el script de migración
SOURCE /ruta/completa/MIGRACION_CONTEXTO_FAMILIAR.sql;

# Verificar que la tabla fue creada
SHOW TABLES LIKE 'contexto_familiar';
DESCRIBE contexto_familiar;
```

### Opción 2: GUI - phpMyAdmin / DBeaver / HeidiSQL

1. **Abrir herramienta de administración (phpMyAdmin, DBeaver, etc.)**
2. **Conectarse a la base de datos SITEA**
3. **Crear nueva pestaña de SQL**
4. **Copiar y pegar contenido del archivo `MIGRACION_CONTEXTO_FAMILIAR.sql`**
5. **Ejecutar script**
6. **Verificar en consola que se creó la tabla sin errores**

### Opción 3: NetBeans (Directo desde el IDE)

1. **En NetBeans, abrir Services → Databases**
2. **Click derecho en la conexión → New SQL File**
3. **Pegar contenido de `MIGRACION_CONTEXTO_FAMILIAR.sql`**
4. **Ejecutar (botón ▶ o Ctrl+Shift+F5)**

---

## ✅ Verificación Post-Migración

Ejecutar en la consola SQL para confirmar:

```sql
-- Verificar que la tabla existe
SHOW TABLES LIKE 'contexto_familiar';

-- Describir estructura de la tabla
DESCRIBE contexto_familiar;

-- Contar registros (debe ser 0 inicialmente)
SELECT COUNT(*) as total_registros FROM contexto_familiar;

-- Verificar campos principales
SELECT 
    COLUMN_NAME, 
    COLUMN_TYPE, 
    IS_NULLABLE, 
    COLUMN_KEY
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_NAME = 'contexto_familiar' AND TABLE_SCHEMA = DATABASE()
ORDER BY ORDINAL_POSITION;

-- Verificar relaciones de foreign keys
SELECT 
    CONSTRAINT_NAME, 
    TABLE_NAME, 
    COLUMN_NAME, 
    REFERENCED_TABLE_NAME, 
    REFERENCED_COLUMN_NAME
FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE TABLE_NAME = 'contexto_familiar' AND TABLE_SCHEMA = DATABASE();

-- Verificar índices
SHOW INDEX FROM contexto_familiar;
```

---

## 🔍 Salida Esperada

```
mysql> SHOW TABLES LIKE 'contexto_familiar';
+---------------------------+
| Tables_in_sitea (contexto_familiar) |
+---------------------------+
| contexto_familiar         |
+---------------------------+
1 row in set (0.00 sec)

mysql> DESCRIBE contexto_familiar;
+----------------------------+----------+------+-----+---------+----------------+
| Field                      | Type     | Null | Key | Default | Extra          |
+----------------------------+----------+------+-----+---------+----------------+
| ID_CONTEXTO_FAMILIAR       | int      | NO   | PRI | NULL    | auto_increment |
| CARACTERIZACION_ID         | int      | NO   | UNI | NULL    |                |
| ACUDIENTE_NOMBRE           | varchar(120) | YES  |     | NULL    |                |
| ACUDIENTE_DOCUMENTO        | varchar(20)  | YES  |     | NULL    |                |
| ... (más campos)           |          |      |     |         |                |
+----------------------------+----------+------+-----+---------+----------------+
```

---

## ⚠️ Solución de Problemas

### Error: "Table 'contexto_familiar' already exists"

**Causa:** La tabla ya existe en la base de datos.

**Solución:**
```sql
-- Opción 1: Borrar tabla e intentar nuevamente
DROP TABLE IF EXISTS contexto_familiar;
SOURCE MIGRACION_CONTEXTO_FAMILIAR.sql;

-- Opción 2: Si no desea perder datos, hacer backup primero
-- Exportar tabla > Hacer cambios > Reimportar
```

### Error: "Column 'CARACTERIZACION_ID' doesn't exist"

**Causa:** Problemas de indización o el script se ejecutó parcialmente.

**Solución:**
```sql
-- Verificar estructura de tabla caracterizacion
DESCRIBE caracterizacion;

-- Ejecutar migración nuevamente
SOURCE MIGRACION_CONTEXTO_FAMILIAR.sql;
```

### Error: "Foreign key constraint fails"

**Causa:** La tabla `caracterizacion` o tabla `usuarios` no existe o tienen estructura diferente.

**Solución:**
```sql
-- Verificar que tablas dependendencia existen
SHOW TABLES LIKE 'caracterizacion';
SHOW TABLES LIKE 'usuarios';

-- Verificar tipos de datos de claves primarias
DESCRIBE caracterizacion;  -- ID_CARACTERIZACION debe ser INT
DESCRIBE usuarios;         -- ID_USUARIO debe ser INT
```

### Error: "Syntax error near 'FOREIGN KEY'"

**Causa:** Sintaxis SQL incompatible (versión vieja de MySQL).

**Solución:**
```sql
-- Verificar versión de MySQL
SELECT VERSION();

-- Si es MySQL < 5.7, actualizar o contactar administrador
-- Las constrains FOREIGN KEY son estándar desde MySQL 3.23+
```

---

## 📝 Rollback (Revertir Migración)

Si necesita deshacer los cambios:

```sql
-- Hacer backup primero
-- mysqldump -u usuario -p nombre_base_datos > backup_antes_rollback.sql

-- Luego eliminar tabla
DROP TABLE IF EXISTS contexto_familiar;
```

---

## ✨ Script SQL Alternativo (Versión Simplificada)

Si tiene problemas con el script completo, puede usar esta versión minimalista:

```sql
CREATE TABLE contexto_familiar (
    ID_CONTEXTO_FAMILIAR INT PRIMARY KEY AUTO_INCREMENT,
    CARACTERIZACION_ID INT NOT NULL,
    ACUDIENTE_NOMBRE VARCHAR(120),
    ACUDIENTE_EMAIL VARCHAR(120),
    ACUDIENTE_PARENTESCO VARCHAR(45),
    MADRE_NOMBRE VARCHAR(120),
    PADRE_NOMBRE VARCHAR(120),
    OTROS_FAMILIARES LONGTEXT,
    RELACIONES_FAMILIARES LONGTEXT,
    TIPO_VIVIENDA VARCHAR(45),
    SITUACION_ECONOMICA LONGTEXT,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CREATED_BY INT,
    UPDATED_BY INT,
    UNIQUE KEY (CARACTERIZACION_ID),
    FOREIGN KEY (CARACTERIZACION_ID) REFERENCES caracterizacion(ID_CARACTERIZACION)
);
```

---

## 📞 Contacto y Soporte

- **Script:** `MIGRACION_CONTEXTO_FAMILIAR.sql`
- **Base de datos:** MariaDB/MySQL 5.7+
- **Documentación:** `IMPLEMENTACION_RF008_CONTEXTO_FAMILIAR.md`
- **Equipo:** SITEA - Caracterización

---

## 🎯 Próximos Pasos Después de Migración

1. ✅ Ejecutar este script SQL
2. ✅ Verificar que tabla se creó correctamente
3. ⏳ Ejecutar aplicación en GlassFish
4. ⏳ Realizar pruebas de flujo completo
5. ⏳ Validar persistencia de datos

---

**Versión:** 1.0  
**Fecha:** 04 de diciembre de 2025  
**Estado:** Listo para ejecutar
