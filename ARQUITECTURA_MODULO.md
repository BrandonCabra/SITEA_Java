# Arquitectura del Módulo de Caracterización - SITEA

## 📐 Diagrama de Capas

```
┌─────────────────────────────────────────────────────────────┐
│                    CAPA DE PRESENTACIÓN                      │
│                         (JSF/XHTML)                          │
├─────────────────────────────────────────────────────────────┤
│  index.xhtml                                                 │
│  crearcaracterizacion.xhtml (original)                       │
│  crearcaracterizacion_mejorado.xhtml (nuevo)                 │
│  gestionarcaracterizacion.xhtml                              │
│  dashboardRegistro.xhtml                                     │
└─────────────────────────────────────────────────────────────┘
                            ↓↑
┌─────────────────────────────────────────────────────────────┐
│                   CAPA DE CONTROLADORES                      │
│                    (Managed Beans)                           │
├─────────────────────────────────────────────────────────────┤
│  Caracterizacioncontroller (original - mejorado)             │
│  CaracterizacionControllerMejorado (nuevo)                   │
│    - crearPreRegistro()                                      │
│    - iniciarCaracterizacion()                                │
│    - validarDuplicidad()                                     │
│    - generarExpedienteId()                                   │
│    - inicializarDimensiones()                                │
│    - registrarObservacion()                                  │
│    - guardarDimension()                                      │
│    - obtenerCaracterizacionesFiltradas()                     │
└─────────────────────────────────────────────────────────────┘
                            ↓↑
┌─────────────────────────────────────────────────────────────┐
│                    CAPA DE SERVICIOS                         │
│                      (EJB Facades)                           │
├─────────────────────────────────────────────────────────────┤
│  CaracterizacionFacade / CaracterizacionFacadeLocal          │
│  DimensionValoracionFacade / DimensionValoracionFacadeLocal  │
│  ObservacionSistematicaFacade / ObservacionSistematicaFacadeLocal │
│  EstudianteFacade / EstudianteFacadeLocal                    │
│                                                              │
│  Métodos CRUD:                                               │
│    - create(), edit(), remove(), find(), findAll()           │
│  Métodos Personalizados:                                     │
│    - findByCaracterizacion()                                 │
│    - contarPorDiagnostico()                                  │
└─────────────────────────────────────────────────────────────┘
                            ↓↑
┌─────────────────────────────────────────────────────────────┐
│                   CAPA DE PERSISTENCIA                       │
│                      (JPA Entities)                          │
├─────────────────────────────────────────────────────────────┤
│  Caracterizacion (actualizada)                               │
│    + expedienteCaracterizacion: String                       │
│    + estadoCaracterizacion: String                           │
│    + fechaInicio: Date                                       │
│    + fechaFinalizacion: Date                                 │
│    + createdAt, updatedAt, createdBy, updatedBy              │
│                                                              │
│  DimensionValoracion (nueva)                                 │
│    + nombreDimension: String                                 │
│    + descripcion, fortalezas, areasApoyo: Text               │
│    + puntuacion: Integer                                     │
│    + estado: String                                          │
│                                                              │
│  ObservacionSistematica (nueva)                              │
│    + fechaObservacion: Timestamp                             │
│    + entorno: String                                         │
│    + descripcion, contexto: Text                             │
│    + evidencias: String                                      │
│    + observador: String                                      │
│                                                              │
│  Estudiante (existente)                                      │
│    + expedienteId: String                                    │
│    + diagnosticoCertificado: Boolean                         │
│    + tipoTea: String                                         │
└─────────────────────────────────────────────────────────────┘
                            ↓↑
┌─────────────────────────────────────────────────────────────┐
│                    CAPA DE DATOS                             │
│                      (MySQL 5.7+)                            │
├─────────────────────────────────────────────────────────────┤
│  Tablas:                                                     │
│    - caracterizacion (actualizada)                           │
│    - dimension_valoracion (nueva)                            │
│    - observacion_sistematica (nueva)                         │
│    - reunion_socializacion (nueva)                           │
│    - historial_caracterizacion (nueva)                       │
│    - estudiante (existente)                                  │
│                                                              │
│  Vistas:                                                     │
│    - v_resumen_caracterizaciones                             │
│    - v_dashboard_dimensiones                                 │
│                                                              │
│  Procedimientos:                                             │
│    - inicializar_dimensiones(p_caracterizacion_id)           │
└─────────────────────────────────────────────────────────────┘
```

## 🔄 Flujo de Datos Principal

### Flujo 1: Pre-registro de Estudiante

```
Usuario (Psicoorientador)
    ↓
[Vista: dashboardRegistro.xhtml]
    ↓ (Formulario)
[Controlador: CaracterizacionControllerMejorado.crearPreRegistro()]
    ↓ (Validación)
[Método: validarDuplicidad()]
    ↓ (Si no existe)
[Método: generarExpedienteId()] → "EXP-TEA-2024-0001"
    ↓
[Servicio: EstudianteFacade.create()]
    ↓
[Entidad: Estudiante]
    ↓
[Base de Datos: INSERT INTO estudiante]
    ↓
[Mensaje: "Estudiante registrado exitosamente"]
```

### Flujo 2: Iniciar Caracterización

```
Usuario (Psicoorientador)
    ↓
[Vista: index.xhtml] → Click "Iniciar Caracterización"
    ↓
[Controlador: CaracterizacionControllerMejorado.iniciarCaracterizacion()]
    ↓ (Verificar si existe caracterización activa)
[Método: generarExpedienteCaracterizacion()] → "CHAR-TEA-2024-0001"
    ↓
[Servicio: CaracterizacionFacade.create()]
    ↓
[Entidad: Caracterizacion]
    ↓
[Base de Datos: INSERT INTO caracterizacion]
    ↓
[Método: inicializarDimensiones()]
    ↓ (Loop 8 veces)
[Servicio: DimensionValoracionFacade.create()]
    ↓
[Base de Datos: INSERT INTO dimension_valoracion] × 8
    ↓
[Redirigir: dashboard.xhtml]
```

### Flujo 3: Registrar Observación

```
Usuario (Psicoorientador/Profesor)
    ↓
[Vista: observaciones.xhtml] → Formulario
    ↓
[Controlador: CaracterizacionControllerMejorado.registrarObservacion()]
    ↓ (Obtener usuario actual)
[Login.getUsuario().getNombreUsuario()]
    ↓
[Servicio: ObservacionSistematicaFacade.create()]
    ↓
[Entidad: ObservacionSistematica]
    ↓
[Base de Datos: INSERT INTO observacion_sistematica]
    ↓
[Método: cargarObservaciones()]
    ↓
[Actualizar vista con nueva observación]
```

### Flujo 4: Valorar Dimensión

```
Usuario (Psicoorientador)
    ↓
[Vista: dashboard.xhtml] → Seleccionar dimensión
    ↓
[Vista: valorarDimension.xhtml] → Formulario
    ↓
[Controlador: CaracterizacionControllerMejorado.guardarDimension()]
    ↓ (Actualizar estado)
dimensionActual.setEstado("COMPLETADA")
    ↓
[Servicio: DimensionValoracionFacade.edit()]
    ↓
[Base de Datos: UPDATE dimension_valoracion]
    ↓
[Método: calcularPorcentajeAvance()]
    ↓
[Actualizar progreso en dashboard]
```

## 🗂️ Modelo de Datos Relacional

```
┌─────────────────────────┐
│      ESTUDIANTE         │
│─────────────────────────│
│ PK: ID_ESTUDIANTE       │
│     expediente_id       │◄──────┐
│     numero_documento    │       │
│     nombres             │       │
│     tipo_tea            │       │
│     diagnostico_cert    │       │
│     estado_registro     │       │
└─────────────────────────┘       │
                                  │ FK
                                  │
┌─────────────────────────────────┴───────┐
│         CARACTERIZACION                 │
│─────────────────────────────────────────│
│ PK: ID_CARACTERIZACION                  │
│ FK: ESTUDIANTE_ID_ESTUDIANTE            │
│     expediente_caracterizacion          │
│     codigo_caracterizacion              │
│     estado_caracterizacion              │
│     contexto_academico                  │
│     contexto_familiar                   │
│     contexto_escolar                    │
│     diagnostico                         │
│     valoracion_pedagogica               │
│     barra_de_aprendizaje                │
│     recomendaciones                     │
│     corresponsabilidad                  │
│     fecha_inicio                        │
│     fecha_finalizacion                  │
│     created_at, updated_at              │
│     created_by, updated_by              │
└─────────────────────────────────────────┘
         │                    │
         │ 1:N                │ 1:N
         ↓                    ↓
┌──────────────────┐  ┌──────────────────────┐
│ DIMENSION_       │  │ OBSERVACION_         │
│ VALORACION       │  │ SISTEMATICA          │
│──────────────────│  │──────────────────────│
│ PK: ID_DIMENSION │  │ PK: ID_OBSERVACION   │
│ FK: CARACTERIZ.. │  │ FK: CARACTERIZ..     │
│ nombre_dimension │  │ fecha_observacion    │
│ descripcion      │  │ entorno              │
│ fortalezas       │  │ descripcion          │
│ areas_apoyo      │  │ contexto             │
│ puntuacion       │  │ evidencias           │
│ estado           │  │ observador           │
│ fecha_valoracion │  │ created_at           │
└──────────────────┘  └──────────────────────┘

         │ 1:N
         ↓
┌──────────────────────┐
│ REUNION_             │
│ SOCIALIZACION        │
│──────────────────────│
│ PK: ID_REUNION       │
│ FK: CARACTERIZ..     │
│ tipo_reunion         │
│ fecha_programada     │
│ fecha_realizada      │
│ participantes        │
│ acuerdos             │
│ estado               │
│ acta_url             │
└──────────────────────┘

         │ 1:N
         ↓
┌──────────────────────┐
│ HISTORIAL_           │
│ CARACTERIZACION      │
│──────────────────────│
│ PK: ID_HISTORIAL     │
│ FK: CARACTERIZ..     │
│ accion               │
│ descripcion          │
│ usuario_id           │
│ fecha_accion         │
│ datos_anteriores     │
│ datos_nuevos         │
└──────────────────────┘
```

## 🎨 Patrones de Diseño Utilizados

### 1. MVC (Model-View-Controller)
- **Model**: Entidades JPA (Caracterizacion, DimensionValoracion, etc.)
- **View**: Archivos XHTML (JSF)
- **Controller**: Managed Beans (@Named, @SessionScoped)

### 2. DAO (Data Access Object) / Facade
- AbstractFacade como clase base
- Facades específicos para cada entidad
- Interfaces Local para contratos

### 3. Dependency Injection
- @EJB para inyección de servicios
- @Inject para beans CDI
- Gestión de ciclo de vida por el contenedor

### 4. Session Facade
- Facades encapsulan lógica de negocio
- Transacciones gestionadas por EJB
- Reducción de llamadas remotas

### 5. Template Method
- AbstractFacade define estructura
- Facades concretos implementan detalles
- Reutilización de código CRUD

## 🔐 Seguridad y Validación

### Niveles de Validación

```
┌─────────────────────────────────────────┐
│  1. VALIDACIÓN CLIENTE (JavaScript)     │
│     - Campos requeridos                 │
│     - Formato de datos                  │
│     - Longitud de campos                │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│  2. VALIDACIÓN JSF (Bean Validation)    │
│     - @NotNull, @Size                   │
│     - required="true"                   │
│     - Mensajes de error                 │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│  3. VALIDACIÓN NEGOCIO (Controlador)    │
│     - Duplicidad                        │
│     - Reglas de negocio                 │
│     - Permisos por rol                  │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│  4. VALIDACIÓN BD (Constraints)         │
│     - Foreign Keys                      │
│     - Unique constraints                │
│     - NOT NULL                          │
└─────────────────────────────────────────┘
```

### Control de Acceso por Rol

```
ADMINISTRADOR
    ├── Crear pre-registro ✓
    ├── Editar pre-registro ✓
    ├── Eliminar pre-registro ✓
    ├── Iniciar caracterización ✓
    ├── Editar caracterización ✓
    ├── Eliminar caracterización ✓
    ├── Ver todas las caracterizaciones ✓
    └── Exportar reportes ✓

PSICOORIENTADOR
    ├── Crear pre-registro ✓
    ├── Editar pre-registro ✓
    ├── Eliminar pre-registro ✓
    ├── Iniciar caracterización ✓
    ├── Valorar dimensiones ✓
    ├── Registrar observaciones ✓
    ├── Programar reuniones ✓
    └── Generar reportes ✓

PROFESOR
    ├── Ver caracterizaciones ✓
    ├── Registrar observaciones ✓
    ├── Recomendar/Solicitar ✓
    └── Ver reportes (limitado) ✓

PADRE DE FAMILIA
    ├── Ver caracterización (propia) ✓
    ├── Completar formulario familiar ✓
    ├── Ver recomendaciones ✓
    └── Recibir notificaciones ✓
```

## 📊 Métricas de Calidad del Código

### Cobertura de Requisitos Funcionales

```
FASE 1: Pre-registro y Expedientes
████████████████████░░ 85% (6/7 RF implementados)

FASE 2: Dimensiones y Valoración
████████████░░░░░░░░░░ 60% (3/5 RF implementados)

FASE 3: Contextos y Observaciones
███████████████░░░░░░░ 75% (4/5 RF implementados)

FASE 4: Reportes
░░░░░░░░░░░░░░░░░░░░░░ 0% (0/3 RF implementados)

FASE 5: Seguimiento
████████░░░░░░░░░░░░░░ 40% (3/7 RF implementados)

TOTAL: ████████████░░░░░░░░ 60% (16/27 RF)
```

### Complejidad Ciclomática

```
CaracterizacionControllerMejorado
├── crearPreRegistro()              CC: 5 (Baja)
├── validarDuplicidad()             CC: 3 (Baja)
├── generarExpedienteId()           CC: 4 (Baja)
├── iniciarCaracterizacion()        CC: 6 (Media)
├── inicializarDimensiones()        CC: 2 (Baja)
├── obtenerCaracterizacionesFiltradas() CC: 7 (Media)
└── calcularPorcentajeAvance()      CC: 4 (Baja)

Promedio: CC: 4.4 (Aceptable)
```

## 🚀 Performance y Optimización

### Estrategias Implementadas

1. **Lazy Loading**
   - Relaciones JPA con FetchType.LAZY
   - Carga bajo demanda de colecciones

2. **Índices de Base de Datos**
   - Índices en campos de búsqueda frecuente
   - Índices en foreign keys
   - Índices compuestos para consultas complejas

3. **Caching**
   - Session scope para beans
   - Reutilización de listas cargadas
   - Cache de segundo nivel (pendiente)

4. **Paginación**
   - DataTables con paginación cliente
   - Preparado para paginación servidor

### Tiempos de Respuesta Esperados

```
Operación                    Tiempo Esperado
─────────────────────────────────────────────
Crear pre-registro           < 500ms
Iniciar caracterización      < 1s
Cargar lista (50 registros)  < 300ms
Guardar dimensión            < 400ms
Registrar observación        < 400ms
Generar reporte PDF          < 3s (pendiente)
```

---

**Arquitectura diseñada para**:
- ✅ Escalabilidad
- ✅ Mantenibilidad
- ✅ Extensibilidad
- ✅ Testabilidad
- ✅ Seguridad

**Versión**: 1.0  
**Última actualización**: Diciembre 2024
