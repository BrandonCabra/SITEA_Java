# Implementación RF-008: Contexto Escolar y Familiar con Pre-relleno

## Resumen General

Se ha implementado una solución **ágil y eficiente** para capturar el Contexto Escolar y Contexto Familiar durante la caracterización pedagógica. Los formularios están **pre-rellenados con datos del preregistro** para acelerar la captura de información nueva.

---

## 📋 Datos Pre-rellenados (Del Preregistro)

Los siguientes campos se muestran automáticamente del `Estudiante` registrado:

- **Nombre Completo** (primer y segundo nombre, primer y segundo apellido)
- **Número de Documento**
- **Fecha de Nacimiento** (formateado: dd/MM/yyyy)
- **Grado/Curso** (del curso asociado)
- **Teléfono**
- **Email Institucional**
- **Dirección de Residencia**

**Ventaja:** El psicoorientador no debe re-ingresar información ya disponible, ahorrando tiempo.

---

## 🏫 Formulario: Contexto Escolar (`contexto_escolar.xhtml`)

### Estructura

**Sección 1: Información General (PRE-RELLENADA)**
- Datos del estudiante en modo lectura (no editable)

**Sección 2: Contexto Escolar (CAMPOS A COMPLETAR)**

Los siguientes campos requieren captura:

| Campo | Tipo | Descripción |
|-------|------|-------------|
| **Infraestructura** | Textarea | Acceso, aulas, baños, áreas comunes |
| **Accesibilidad** | Textarea | Rampas, señalización, baños adaptados |
| **Recursos y Materiales** | Textarea | Materiales, tecnología, biblioteca, laboratorios |
| **Observaciones de Docentes** | Textarea | Comportamiento en clase, interacción con pares |
| **Barreras de Aprendizaje** | Textarea | Factores que impiden el aprendizaje |
| **Recomendaciones Institucionales** | Textarea | Recomendaciones para mejorar aprendizaje (opcional) |

### Flujo

```
1. Psicoorientador crea/inicia caracterización
   ↓
2. Se abre contexto_escolar.xhtml
   ↓
3. Datos del estudiante se muestran pre-rellenados (lectura)
   ↓
4. Psicoorientador completa campos de contexto escolar
   ↓
5. Botón "Guardar y Continuar →" → contexto_familiar.xhtml
```

### Backend

- **Controlador:** `CaracterizacionControllerMejorado.guardarContextoEscolar()`
- **Entity:** `ContextoEscolar` (nueva tabla normalizada)
- **Fachada:** `ContextoEscolarFacade`
- **Base de datos:** Tabla `contexto_escolar` (1:1 con `caracterizacion`)

---

## 👨‍👩‍👧 Formulario: Contexto Familiar (`contexto_familiar.xhtml`)

### Estructura

**Sección 1: Acudiente Principal** ⚠️ *Requeridos*
- Nombre Completo *
- Documento de Identidad
- Parentesco (dropdown: Madre, Padre, Abuelo, Tío, Hermano, Otro) *
- Teléfono
- Email * (se usará para enviar credenciales)

**Sección 2: Información de la Madre**
- Nombre Completo
- Documento de Identidad
- Teléfono
- Email
- Ocupación
- Escolaridad (dropdown: Primaria, Secundaria, Técnica, Tecnológica, Universitaria, Analfabeta)

**Sección 3: Información del Padre**
- Nombre Completo
- Documento de Identidad
- Teléfono
- Email
- Ocupación
- Escolaridad (dropdown con mismo rango)

**Sección 4: Composición y Relaciones Familiares**
- Otras personas en el hogar (textarea)
- Relaciones familiares y dinámicas (textarea)
- Comunicación familiar (textarea)

**Sección 5: Vivienda y Situación Socioeconómica**
- Tipo de Vivienda (dropdown: Casa propia, Apartamento, Inquilinato, etc.)
- Tenencia (dropdown: Propia, Arrendada, Prestada, Otra)
- Condiciones de la Vivienda (textarea)
- Situación Económica (textarea)

**Sección 6: Observaciones Adicionales**
- Campo de texto libre para notas relevantes (opcional)

### Características

- ✅ **Alerta informativa** sobre credenciales del acudiente
- ✅ **Validación en cliente** (campos requeridos marcados con *)
- ✅ **Selecciones con dropdown** para consistencia de datos
- ✅ **Campos opcionales** para mayor flexibilidad
- ✅ **Navegación intuítiva** (Volver ↔ Guardar y Continuar)

### Backend

- **Controlador:** `CaracterizacionControllerMejorado.guardarContextoFamiliarYRegistrarAcudiente()`
- **Entity:** `ContextoFamiliar` (nueva tabla normalizada)
- **Fachadas:** `ContextoFamiliarFacadeLocal` e `ContextoFamiliarFacade`
- **Base de datos:** Tabla `contexto_familiar` (1:1 con `caracterizacion`)

### Stub para Registro de Acudiente

El método `registrarUsuarioAcudiente()` es un **placeholder** que debe implementar:

1. **Crear usuario** en tabla `Usuarios` con rol "acudiente"
2. **Generar password temporal** (seguro, aleatorio, 8-12 caracteres)
3. **Asociar con Estudiante** (crear relación si no existe)
4. **Enviar email** con credenciales vía SendGrid

```java
private void registrarUsuarioAcudiente(String nombre, String email, String documento) {
    // TODO: Implementar lógica completa
    // 1. Generar password temporal
    // 2. Crear registro en tabla Usuarios
    // 3. Asociar con Estudiante
    // 4. Enviar email con credenciales via SendGrid
}
```

---

## 🗄️ Entidades JPA

### ContextoEscolar

```java
@Entity
@Table(name = "contexto_escolar")
public class ContextoEscolar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContextoEscolar;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARACTERIZACION_ID")
    private Caracterizacion caracterizacion;
    
    // Campos de contexto
    private String infraestructura;
    private String accesibilidad;
    private String recursos;
    private String ambiente;
    private String observacionesDocentes;
    private String barrerasAprendizaje;
    private String recomendacionesInstitucionales;
    private String otrasNotas;
    
    // Auditoría
    private Date createdAt;
    private Date updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
}
```

### ContextoFamiliar

```java
@Entity
@Table(name = "contexto_familiar")
public class ContextoFamiliar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContextoFamiliar;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARACTERIZACION_ID")
    private Caracterizacion caracterizacion;
    
    // Acudiente principal
    private String acudienteNombre;
    private String acudienteDocumento;
    private String acudienteTelefono;
    private String acudienteEmail;
    private String acudienteParentesco;
    
    // Madre
    private String madreNombre;
    private String madreDocumento;
    private String madreTelefono;
    private String madreEmail;
    private String madreOcupacion;
    private String madreEscolaridad;
    
    // Padre
    private String padreNombre;
    private String padreDocumento;
    private String padreTelefono;
    private String padreEmail;
    private String padreOcupacion;
    private String padreEscolaridad;
    
    // Familia
    private String otrosFamiliares;
    private String relacionesFamiliares;
    private String comunicacionFamiliar;
    
    // Vivienda
    private String tipoVivienda;
    private String tenenciaVivienda;
    private String condicionesVivienda;
    private String situacionEconomica;
    
    // Observaciones
    private String observacionesFamilia;
    
    // Auditoría
    private Date createdAt;
    private Date updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
}
```

---

## 📊 Tablas de Base de Datos

### contexto_escolar

```sql
CREATE TABLE contexto_escolar (
    ID_CONTEXTO_ESCOLAR INT PRIMARY KEY AUTO_INCREMENT,
    CARACTERIZACION_ID INT NOT NULL UNIQUE,
    INFRAESTRUCTURA LONGTEXT,
    ACCESIBILIDAD LONGTEXT,
    RECURSOS LONGTEXT,
    AMBIENTE LONGTEXT,
    OBSERVACIONES_DOCENTES LONGTEXT,
    BARRERAS_APRENDIZAJE LONGTEXT,
    RECOMENDACIONES_INSTITUCIONALES LONGTEXT,
    OTRAS_NOTAS LONGTEXT,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CREATED_BY INT,
    UPDATED_BY INT,
    UNIQUE KEY (CARACTERIZACION_ID),
    FOREIGN KEY (CARACTERIZACION_ID) REFERENCES caracterizacion(ID_CARACTERIZACION)
);
```

### contexto_familiar

Ver archivo: `MIGRACION_CONTEXTO_FAMILIAR.sql`

---

## 🔄 Flujo Completo de Caracterización

```
DASHBOARD INICIO
    ↓
[Psicoorientador selecciona estudiante y clic "Iniciar Caracterización"]
    ↓
CONTEXTO ESCOLAR
├─ Pre-rellenado: datos del estudiante (nombre, grado, dirección, etc.)
├─ Captura: infraestructura, accesibilidad, recursos, ambiente, etc.
└─ Botón: "Guardar y Continuar →"
    ↓
CONTEXTO FAMILIAR
├─ Captura: datos acudiente principal
├─ Captura: datos madre y padre
├─ Captura: composición familiar, relaciones, vivienda
├─ Genera: usuario acudiente (con password temporal)
├─ Envía: credenciales por email (SendGrid - PENDIENTE)
└─ Botón: "Guardar y Continuar →"
    ↓
DASHBOARD DIMENSIONES
├─ Muestra: 8 dimensiones MEN (Pendiente → Completada)
├─ Permite: valoración por dimensión
└─ Al completar todas: genera informe final
```

---

## 📂 Archivos Creados/Modificados

### Creados

| Archivo | Descripción |
|---------|-------------|
| `src/main/java/com/sena/sitea/entities/ContextoFamiliar.java` | Entity JPA para contexto familiar |
| `src/main/java/com/sena/sitea/services/ContextoFamiliarFacadeLocal.java` | Interface de servicio |
| `src/main/java/com/sena/sitea/services/ContextoFamiliarFacade.java` | Implementación de servicio |
| `src/main/webapp/views/caracterizacion/contexto_familiar.xhtml` | Vista formulario familia |
| `MIGRACION_CONTEXTO_FAMILIAR.sql` | Script SQL para tabla |

### Modificados

| Archivo | Cambios |
|---------|---------|
| `CaracterizacionControllerMejorado.java` | Inyección de `ContextoFamiliarFacadeLocal`, propiedades `contextoFamiliar` y `contextoEscolar`, método `guardarContextoFamiliarYRegistrarAcudiente()`, método `registrarUsuarioAcudiente()` (stub) |
| `contexto_escolar.xhtml` | Pre-relleno de datos, mejora de formulario, navegación a contexto_familiar |

---

## ✅ Estado Actual

- ✅ **Compilación:** BUILD SUCCESS
- ✅ **Entidades JPA:** Creadas y compiladas
- ✅ **Fachadas EJB:** Implementadas
- ✅ **Vistas XHTML:** Funcionales con pre-relleno
- ✅ **Controlador:** Métodos para guardar contexto escolar y familiar
- ⏳ **Pendiente:** Implementar creación de usuario acudiente + envío de email

---

## 🚀 Próximos Pasos

### Fase 1: Implementar Registro de Acudiente
1. Crear método para generar password temporal seguro
2. Implementar creación de usuario `Usuarios` con rol "acudiente"
3. Crear relación entre acudiente y estudiante (si no existe tabla)
4. Integrar con SendGrid para envío de credenciales

### Fase 2: Ejecutar Migración SQL
1. Ejecutar `MIGRACION_CONTEXTO_FAMILIAR.sql` en base de datos
2. Verificar tablas creadas correctamente

### Fase 3: Pruebas Integrales
1. Pruebas manuales en GlassFish
2. Validar flujo completo: inicio → contexto escolar → contexto familiar → dimensiones
3. Verificar persistencia de datos en BD
4. Validar envío de emails a acudientes

### Fase 4: Validación UI/UX
1. Pre-rellenado de datos
2. Navegación intuitiva
3. Validaciones en cliente
4. Mensajes de éxito/error

---

## 💾 SQL: Ejecutar en Base de Datos

Antes de ejecutar la aplicación, ejecutar el script de migración:

```bash
# En terminal MySQL:
mysql -u usuario -p nombre_base_datos < MIGRACION_CONTEXTO_FAMILIAR.sql

# O en phpMyAdmin/DBeaver:
# Copiar y ejecutar contenido de MIGRACION_CONTEXTO_FAMILIAR.sql
```

---

## 📝 Notas de Implementación

- **Pre-relleno:** Utiliza EL (Expression Language) de JSF para mostrar datos del bean
- **Normalizacion:** Datos de contexto se almacenan en tablas separadas (no en `caracterizacion`)
- **Auditoría:** Todos los cambios se registran con created_at/updated_at y created_by/updated_by
- **Validación:** Campos requeridos marcados con `required="true"` en JSF
- **Navegación:** Flujo lineal asegurado con botones "Volver" y "Guardar y Continuar"
- **Responsiva:** Formularios adaptables a diferentes tamaños de pantalla

---

## 📞 Soporte

Para preguntas o mejoras, contactar al equipo SITEA.

**Versión:** 1.0  
**Fecha:** 04 de diciembre de 2025  
**Estado:** RF-008 Fase 1 Completada - Falta integración de usuario acudiente
