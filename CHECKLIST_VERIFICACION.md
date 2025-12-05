# ✅ Checklist de Verificación - Módulo de Caracterización

## Pre-Implementación

### Respaldo
- [ ] Hacer backup de la base de datos actual
  ```bash
  mysqldump -u root -p sitea > backup_sitea_$(date +%Y%m%d).sql
  ```
- [ ] Hacer backup del código fuente actual
  ```bash
  tar -czf backup_sitea_$(date +%Y%m%d).tar.gz src/
  ```

### Requisitos del Sistema
- [ ] Java JDK 8 instalado
- [ ] Maven 3.x instalado
- [ ] GlassFish 4.x o superior
- [ ] MySQL 5.7 o superior
- [ ] Conexión a base de datos configurada

## Implementación

### 1. Base de Datos
- [ ] Conectar a MySQL
- [ ] Seleccionar base de datos sitea
- [ ] Ejecutar script: `database_updates_caracterizacion.sql`
- [ ] Verificar tablas creadas:
  - [ ] `dimension_valoracion`
  - [ ] `observacion_sistematica`
  - [ ] `reunion_socializacion`
  - [ ] `historial_caracterizacion`
- [ ] Verificar columnas agregadas a `caracterizacion`:
  - [ ] `expediente_caracterizacion`
  - [ ] `estado_caracterizacion`
  - [ ] `fecha_inicio`
  - [ ] `fecha_finalizacion`
  - [ ] `created_at`, `updated_at`
  - [ ] `created_by`, `updated_by`
- [ ] Verificar vistas creadas:
  - [ ] `v_resumen_caracterizaciones`
  - [ ] `v_dashboard_dimensiones`
- [ ] Verificar procedimiento almacenado:
  - [ ] `inicializar_dimensiones`

### 2. Código Fuente
- [ ] Verificar archivos nuevos en `src/main/java/com/sena/sitea/entities/`:
  - [ ] `ObservacionSistematica.java`
  - [ ] `DimensionValoracion.java`
- [ ] Verificar archivos nuevos en `src/main/java/com/sena/sitea/services/`:
  - [ ] `ObservacionSistematicaFacade.java`
  - [ ] `ObservacionSistematicaFacadeLocal.java`
  - [ ] `DimensionValoracionFacade.java`
  - [ ] `DimensionValoracionFacadeLocal.java`
- [ ] Verificar archivos nuevos en `src/main/java/com/sena/sitea/controller/`:
  - [ ] `CaracterizacionControllerMejorado.java`
- [ ] Verificar archivos modificados:
  - [ ] `Caracterizacion.java` (campos agregados)
  - [ ] `Caracterizacioncontroller.java` (bugs corregidos)
- [ ] Verificar archivo nuevo en `src/main/webapp/views/caracterizacion/`:
  - [ ] `crearcaracterizacion_mejorado.xhtml`

### 3. Compilación
- [ ] Ejecutar: `mvn clean`
- [ ] Ejecutar: `mvn compile`
- [ ] Verificar que no hay errores de compilación
- [ ] Ejecutar: `mvn install`
- [ ] Verificar que se genera: `target/sitea-1.0-SNAPSHOT.war`

### 4. Despliegue
- [ ] Detener GlassFish (si está corriendo)
- [ ] Limpiar directorio de despliegue anterior
- [ ] Copiar WAR a autodeploy o desplegar manualmente
- [ ] Iniciar GlassFish
- [ ] Verificar en logs que no hay errores
- [ ] Verificar que la aplicación está desplegada

## Post-Implementación

### 5. Verificación Funcional

#### Acceso al Sistema
- [ ] Abrir navegador en: `http://localhost:8080/sitea`
- [ ] Login como ADMINISTRADOR
- [ ] Login como PSICOORIENTADOR
- [ ] Verificar que carga sin errores

#### Módulo de Caracterización
- [ ] Navegar a: Módulos > Caracterización Pedagógica y Social
- [ ] Verificar que carga la página index.xhtml
- [ ] Verificar que se muestran las opciones:
  - [ ] Pre-Registro Estudiante
  - [ ] Iniciar Caracterización
  - [ ] Gestionar Caracterización
  - [ ] Reporte Caracterización Institución

#### Pre-registro de Estudiante (RF-001)
- [ ] Click en "Pre-Registro Estudiante"
- [ ] Llenar formulario con datos de prueba:
  - Nombre: Juan
  - Apellido: Pérez
  - Documento: 1234567890
  - Tipo TEA: Autismo
  - Diagnóstico certificado: Sí
- [ ] Click en "Guardar"
- [ ] Verificar mensaje: "Estudiante registrado exitosamente"
- [ ] Verificar que se generó expediente: EXP-TEA-2024-XXXX
- [ ] Verificar en BD:
  ```sql
  SELECT * FROM estudiante WHERE numero_documento_estudiante = '1234567890';
  ```

#### Validación de Duplicidad (RF-006)
- [ ] Intentar registrar el mismo estudiante nuevamente
- [ ] Verificar mensaje: "Este estudiante ya se encuentra registrado"
- [ ] Verificar que NO se crea registro duplicado

#### Iniciar Caracterización (RF-008)
- [ ] Ir a "Iniciar Caracterización"
- [ ] Buscar el estudiante registrado
- [ ] Click en "Iniciar"
- [ ] Verificar mensaje: "Caracterización iniciada exitosamente"
- [ ] Verificar que se generó expediente: CHAR-TEA-2024-XXXX
- [ ] Verificar en BD:
  ```sql
  SELECT * FROM caracterizacion WHERE expediente_caracterizacion LIKE 'CHAR-TEA%';
  ```

#### Dimensiones Inicializadas (RF-009)
- [ ] Verificar en BD que se crearon 8 dimensiones:
  ```sql
  SELECT COUNT(*) FROM dimension_valoracion 
  WHERE caracterizacion_id = (SELECT MAX(ID_CARACTERIZACION) FROM caracterizacion);
  ```
- [ ] Resultado esperado: 8
- [ ] Verificar nombres de dimensiones:
  - [ ] Contexto y vida familiar
  - [ ] Habilidades intelectuales
  - [ ] Bienestar emocional
  - [ ] Conducta adaptativa y desarrollo personal
  - [ ] Salud y bienestar físico
  - [ ] Participación e inclusión social
  - [ ] Control del propio entorno
  - [ ] Dimensión pedagógica

#### Crear Caracterización (Formulario Mejorado)
- [ ] Ir a "Iniciar Caracterización"
- [ ] Seleccionar estudiante sin caracterización
- [ ] Llenar formulario completo:
  - [ ] Código caracterización
  - [ ] Contexto académico (verificar textarea)
  - [ ] Contexto familiar (verificar textarea)
  - [ ] Contexto escolar (verificar textarea)
  - [ ] Diagnóstico (verificar textarea)
  - [ ] Valoración pedagógica (verificar textarea)
  - [ ] Barreras de aprendizaje (verificar textarea)
  - [ ] Recomendaciones (verificar textarea)
  - [ ] Corresponsabilidad (verificar textarea)
- [ ] Verificar contadores de caracteres funcionando
- [ ] Click en "Registrar Caracterización"
- [ ] Verificar mensaje: "Caracterización registrada correctamente"

#### Gestionar Caracterizaciones (RF-004)
- [ ] Ir a "Gestionar Caracterización"
- [ ] Verificar que se muestra tabla con caracterizaciones
- [ ] Verificar columnas:
  - [ ] Caracterización
  - [ ] Estudiante
  - [ ] Documento
  - [ ] Diagnóstico
  - [ ] Valoración Pedagógica
  - [ ] Barrera de Aprendizaje
  - [ ] Acciones
- [ ] Verificar botones de acción:
  - [ ] Editar (solo ADMIN/PSICOORIENTADOR)
  - [ ] Eliminar (solo ADMIN/PSICOORIENTADOR)

#### Editar Caracterización (RF-002)
- [ ] Click en "Editar" en una caracterización
- [ ] Modificar algún campo
- [ ] Click en "Actualizar Caracterización"
- [ ] Verificar mensaje: "Caracterización editada correctamente"
- [ ] Verificar que los cambios se guardaron

#### Eliminar Caracterización (RF-003)
- [ ] Click en "Eliminar" en una caracterización de prueba
- [ ] Confirmar eliminación
- [ ] Verificar mensaje: "Caracterización eliminada correctamente"
- [ ] Verificar que ya no aparece en la lista

### 6. Verificación de Datos

#### Consultas SQL de Verificación
```sql
-- Verificar expedientes generados
SELECT expediente_id, numero_documento_estudiante, 
       CONCAT(primer_nombre_estudiante, ' ', primer_apellido_estudiante) as nombre
FROM estudiante 
WHERE expediente_id LIKE 'EXP-TEA%';

-- Verificar caracterizaciones
SELECT expediente_caracterizacion, estado_caracterizacion, 
       fecha_inicio, created_at
FROM caracterizacion
ORDER BY created_at DESC;

-- Verificar dimensiones por caracterización
SELECT c.expediente_caracterizacion, 
       dv.nombre_dimension, 
       dv.estado
FROM caracterizacion c
LEFT JOIN dimension_valoracion dv ON c.ID_CARACTERIZACION = dv.CARACTERIZACION_ID
ORDER BY c.ID_CARACTERIZACION, dv.ID_DIMENSION;

-- Verificar vista de resumen
SELECT * FROM v_resumen_caracterizaciones;

-- Verificar integridad referencial
SELECT 
    (SELECT COUNT(*) FROM caracterizacion) as total_caracterizaciones,
    (SELECT COUNT(*) FROM dimension_valoracion) as total_dimensiones,
    (SELECT COUNT(*) FROM observacion_sistematica) as total_observaciones;
```

### 7. Pruebas de Roles

#### Como ADMINISTRADOR
- [ ] Puede crear pre-registro
- [ ] Puede editar pre-registro
- [ ] Puede eliminar pre-registro
- [ ] Puede iniciar caracterización
- [ ] Puede editar caracterización
- [ ] Puede eliminar caracterización
- [ ] Puede ver todas las caracterizaciones

#### Como PSICOORIENTADOR
- [ ] Puede crear pre-registro
- [ ] Puede editar pre-registro
- [ ] Puede eliminar pre-registro
- [ ] Puede iniciar caracterización
- [ ] Puede editar caracterización
- [ ] Puede ver caracterizaciones

#### Como PROFESOR
- [ ] Puede ver caracterizaciones
- [ ] NO puede editar
- [ ] NO puede eliminar
- [ ] Botón "Recomendar/Solicitar" visible

### 8. Pruebas de Validación

#### Validaciones de Formulario
- [ ] Intentar guardar sin seleccionar estudiante
  - Esperado: Mensaje "Debe seleccionar un estudiante"
- [ ] Intentar guardar con campos vacíos
  - Esperado: Mensajes de "Campo requerido"
- [ ] Intentar ingresar más caracteres del límite
  - Esperado: Campo no permite más caracteres
- [ ] Verificar que contadores actualizan en tiempo real

#### Validaciones de Negocio
- [ ] Intentar iniciar caracterización para estudiante con caracterización activa
  - Esperado: Mensaje de advertencia
- [ ] Verificar que expedientes son únicos
- [ ] Verificar que consecutivos incrementan correctamente

### 9. Pruebas de Performance

#### Tiempos de Respuesta
- [ ] Cargar lista de caracterizaciones (< 1 segundo)
- [ ] Crear pre-registro (< 500ms)
- [ ] Iniciar caracterización (< 1 segundo)
- [ ] Guardar caracterización (< 500ms)
- [ ] Editar caracterización (< 500ms)

#### Carga de Datos
- [ ] Crear 10 estudiantes de prueba
- [ ] Crear 10 caracterizaciones
- [ ] Verificar que la tabla carga correctamente
- [ ] Verificar que DataTables funciona (ordenar, buscar, paginar)

### 10. Verificación de Logs

#### GlassFish Logs
- [ ] Abrir: `glassfish/domains/domain1/logs/server.log`
- [ ] Verificar que NO hay:
  - [ ] Errores de SQL
  - [ ] NullPointerException
  - [ ] ClassNotFoundException
  - [ ] Errores de despliegue
- [ ] Verificar que SÍ hay:
  - [ ] Aplicación desplegada exitosamente
  - [ ] Entidades JPA registradas

#### MySQL Logs
- [ ] Verificar que NO hay errores de constraint
- [ ] Verificar que las queries se ejecutan correctamente

## Rollback (Si algo falla)

### Restaurar Base de Datos
```bash
mysql -u root -p sitea < backup_sitea_YYYYMMDD.sql
```

### Restaurar Código
```bash
tar -xzf backup_sitea_YYYYMMDD.tar.gz
mvn clean install
```

### Redesplegar Versión Anterior
```bash
cp backup/sitea-old.war /path/to/glassfish/autodeploy/
```

## Documentación Final

- [ ] Actualizar documentación de usuario
- [ ] Actualizar manual técnico
- [ ] Documentar cambios en changelog
- [ ] Notificar al equipo de los cambios

## Firma de Aprobación

```
Implementado por: _______________________  Fecha: __________

Verificado por:   _______________________  Fecha: __________

Aprobado por:     _______________________  Fecha: __________
```

---

**Nota**: Este checklist debe completarse en orden. Si algún paso falla, detener y resolver antes de continuar.

**Tiempo estimado total**: 30-45 minutos
