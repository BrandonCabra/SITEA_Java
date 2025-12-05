# Mapeo de Campos: Preregistro → Contexto Escolar → Contexto Familiar

## 📌 Datos Fluyen del Preregistro (Tabla: ESTUDIANTE)

```
PREREGISTRO (Estudiante)
├── primerNombreEstudiante          → [Mostrado en CONTEXTO ESCOLAR]
├── segundoNombreEstudiante         → [Mostrado en CONTEXTO ESCOLAR]
├── primerApellidoEstudiante        → [Mostrado en CONTEXTO ESCOLAR]
├── segundoApellidoEstudiante       → [Mostrado en CONTEXTO ESCOLAR]
├── numeroDocumentoEstudiante       → [Mostrado en CONTEXTO ESCOLAR]
├── fechaNacimiento                 → [Mostrado en CONTEXTO ESCOLAR, formato: dd/MM/yyyy]
├── cursoIdCurso.nombreCurso        → [Mostrado en CONTEXTO ESCOLAR como "Grado"]
├── telefonoEstudiante              → [Mostrado en CONTEXTO ESCOLAR]
├── correoInstitucionalEstudiante   → [Mostrado en CONTEXTO ESCOLAR]
└── direccionEstudiante             → [Mostrado en CONTEXTO ESCOLAR]
```

## 🏫 CONTEXTO ESCOLAR (Nueva Tabla: contexto_escolar)

```
Datos PRE-RELLENADOS (lectura, no editable):
├── Nombre Completo: primerNombreEstudiante + segundoNombreEstudiante + primerApellidoEstudiante + segundoApellidoEstudiante
├── Número de Documento: numeroDocumentoEstudiante
├── Fecha de Nacimiento: fechaNacimiento
├── Grado / Curso: cursoIdCurso.nombreCurso
├── Teléfono: telefonoEstudiante
├── Email Institucional: correoInstitucionalEstudiante
└── Dirección de Residencia: direccionEstudiante

Campos a CAPTURAR por el psicoorientador:
├── Infraestructura                                    → infraestructura (TEXT)
├── Accesibilidad y Adaptaciones                      → accesibilidad (TEXT)
├── Recursos y Materiales Disponibles                 → recursos (TEXT)
├── Observaciones de Docentes y Personal              → observacionesDocentes (TEXT)
├── Barreras de Aprendizaje Identificadas             → barrerasAprendizaje (TEXT)
└── Recomendaciones Institucionales                   → recomendacionesInstitucionales (TEXT)

Almacenamiento:
  Se mapean a tabla ContextoEscolar
  1:1 con tabla Caracterizacion
  Auditoría: created_at, updated_at, created_by, updated_by
```

## 👨‍👩‍👧 CONTEXTO FAMILIAR (Nueva Tabla: contexto_familiar)

```
ACUDIENTE PRINCIPAL (Required):
├── acudienteNombre          ← Texto libre (obligatorio)
├── acudienteDocumento       ← Texto libre
├── acudienteTelefono        ← Texto libre
├── acudienteEmail           ← Texto libre (obligatorio, para enviar credenciales)
└── acudienteParentesco      ← Dropdown: Madre, Padre, Abuelo(a), Tío(a), Hermano(a), Otro

MADRE:
├── madreNombre              ← Texto libre
├── madreDocumento           ← Texto libre
├── madreTelefono            ← Texto libre
├── madreEmail               ← Texto libre
├── madreOcupacion           ← Texto libre
└── madreEscolaridad         ← Dropdown: Primaria, Secundaria, Técnica, Tecnológica, Universitaria, Analfabeta

PADRE:
├── padreNombre              ← Texto libre
├── padreDocumento           ← Texto libre
├── padreTelefono            ← Texto libre
├── padreEmail               ← Texto libre
├── padreOcupacion           ← Texto libre
└── padreEscolaridad         ← Dropdown: Primaria, Secundaria, Técnica, Tecnológica, Universitaria, Analfabeta

COMPOSICIÓN Y RELACIONES FAMILIARES:
├── otrosFamiliares          ← Textarea (opcional)
├── relacionesFamiliares     ← Textarea (opcional)
└── comunicacionFamiliar     ← Textarea (opcional)

VIVIENDA Y SITUACIÓN SOCIOECONÓMICA:
├── tipoVivienda             ← Dropdown: Casa propia, Apartamento, Inquilinato, Habitación arrendada, etc.
├── tenenciaVivienda         ← Dropdown: Propia, Arrendada, Prestada, Otra
├── condicionesVivienda      ← Textarea (opcional)
└── situacionEconomica       ← Textarea (opcional)

OBSERVACIONES:
└── observacionesFamilia    ← Textarea (opcional)

Almacenamiento:
  Se mapean a tabla ContextoFamiliar
  1:1 con tabla Caracterizacion
  Auditoría: created_at, updated_at, created_by, updated_by
```

## 🔄 Flujo de Datos

```
┌─────────────────────────────────────────────────────────────────┐
│ PREREGISTRO ESTUDIANTE (Tabla: ESTUDIANTE)                      │
│ • Nombre, Documento, Teléfono, Email, Dirección, etc.          │
└─────────────────────────────────────────────────────────────────┘
                              ↓
        [Psicoorientador inicia caracterización]
                              ↓
┌─────────────────────────────────────────────────────────────────┐
│ CONTEXTO ESCOLAR (Tabla: CONTEXTO_ESCOLAR)                      │
│                                                                  │
│ PRE-RELLENADO (Read-only):                                     │
│   Estudiante: Juan Pérez García                                │
│   Documento: 1234567890                                        │
│   Grado: 5º                                                    │
│   Dirección: Carrera 10 #123, Bogotá                          │
│                                                                  │
│ A CAPTURAR (Editable):                                         │
│   Infraestructura: [Texto...]                                 │
│   Accesibilidad: [Texto...]                                   │
│   Recursos: [Texto...]                                        │
│   Observaciones: [Texto...]                                   │
│   Barreras: [Texto...]                                        │
│   Recomendaciones: [Texto...]                                 │
│                                                                  │
│ Botón: "Guardar y Continuar →"                                │
└─────────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────────┐
│ CONTEXTO FAMILIAR (Tabla: CONTEXTO_FAMILIAR)                    │
│                                                                  │
│ ACUDIENTE:                                                     │
│   Nombre: [Texto] *Requerido                                  │
│   Email: [Texto] *Requerido                                   │
│   Parentesco: [Dropdown] *Requerido                           │
│                                                                  │
│ MADRE:                                                         │
│   Nombre: [Texto]                                             │
│   Teléfono: [Texto]                                           │
│   Escolaridad: [Dropdown]                                     │
│   ...                                                          │
│                                                                  │
│ PADRE:                                                         │
│   Nombre: [Texto]                                             │
│   Teléfono: [Texto]                                           │
│   Escolaridad: [Dropdown]                                     │
│   ...                                                          │
│                                                                  │
│ FAMILIA:                                                       │
│   Otros miembros: [Textarea]                                  │
│   Relaciones: [Textarea]                                      │
│   Comunicación: [Textarea]                                    │
│                                                                  │
│ VIVIENDA:                                                      │
│   Tipo: [Dropdown]                                            │
│   Tenencia: [Dropdown]                                        │
│   Condiciones: [Textarea]                                     │
│   Situación económica: [Textarea]                             │
│                                                                  │
│ Botón: "Guardar y Registrar Acudiente →"                      │
│         ↓                                                       │
│         (Crea usuario acudiente + Envía email)                │
└─────────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────────┐
│ DASHBOARD DIMENSIONES                                           │
│ • Inicializar 8 dimensiones MEN                                 │
│ • Permitir valoración por dimensión                             │
│ • Generar informe final cuando todas estén completadas         │
└─────────────────────────────────────────────────────────────────┘
```

## 🎯 Beneficios de esta Arquitectura

| Característica | Beneficio |
|---|---|
| **Pre-relleno de datos** | Ahorra tiempo al psicoorientador (no re-ingresa datos del preregistro) |
| **Tablas normalizadas** | Cada contexto (escolar, familiar) en tabla separada (NO en Caracterizacion) |
| **Validaciones en UI** | Campos obligatorios marcados, valores pre-seleccionados en dropdowns |
| **Flujo lineal** | Navegación clara: preregistro → contexto escolar → contexto familiar → dimensiones |
| **Auditoría** | Cada registro incluye created_by, updated_by, timestamps para trazabilidad |
| **Formularios ágiles** | Secciones claras, instrucciones en español, UX pensada para rapidez |
| **Relación 1:1** | Cada caracterización tiene máximo 1 contexto escolar y 1 contexto familiar |
| **Integridad referencial** | Foreign keys y unique constraints aseguran consistencia de datos |

## 📊 Campos Totales a Capturar

### Contexto Escolar
- **Total de campos:** 6 (todos de tipo LONGTEXT)
- **Requeridos:** 5
- **Opcionales:** 1

### Contexto Familiar
- **Total de campos:** 27
- **Requeridos:** 3 (acudienteNombre, acudienteParentesco, acudienteEmail)
- **Opcionales:** 24
- **Dropdowns:** 5 (para consistencia)

**Total general:** 33 campos nuevos distribuidos en 2 formularios

## ⚡ Tiempo Estimado de Captura

- **Contexto Escolar:** 10-15 minutos (con instrucciones claras)
- **Contexto Familiar:** 5-10 minutos (pre-rellenado, dropdowns)
- **Total por estudiante:** ~20-25 minutos

---

**Actualizado:** 04 de diciembre de 2025  
**Versión:** RF-008 Fase 1
