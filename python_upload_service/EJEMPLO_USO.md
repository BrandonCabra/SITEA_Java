# 📸 Ejemplo de Uso - Sistema de Carga de Archivos

## 🖥️ Interfaz Web

### Vista Principal

```
╔══════════════════════════════════════════════════════════════════╗
║          Gestión de Archivos con Python                         ║
╠══════════════════════════════════════════════════════════════════╣
║                                                                  ║
║  ┌────────────────────────────────────────────────────────────┐ ║
║  │ ✓ Servicio Python Activo                                   │ ║
║  └────────────────────────────────────────────────────────────┘ ║
║                                                                  ║
║  ┌─ Subir Archivo ──────────────────────────────────────────┐  ║
║  │                                                            │  ║
║  │  Módulo:        [Caracterización ▼]                       │  ║
║  │                                                            │  ║
║  │  Descripción:   ┌──────────────────────────────────────┐  │  ║
║  │                 │ Documento de caracterización...      │  │  ║
║  │                 └──────────────────────────────────────┘  │  ║
║  │                                                            │  ║
║  │  ┌──────────────────────────────────────────────────────┐ │  ║
║  │  │  Arrastra archivos aquí o haz clic para seleccionar │ │  ║
║  │  │                                                      │ │  ║
║  │  │  Tipos permitidos: PDF, DOC, DOCX, XLS, XLSX...    │ │  ║
║  │  │  Tamaño máximo: 16 MB                               │ │  ║
║  │  └──────────────────────────────────────────────────────┘ │  ║
║  │                                                            │  ║
║  │  [Seleccionar]  [Subir]  [Cancelar]                       │  ║
║  │                                                            │  ║
║  │  [🔄 Verificar Servicio]                                  │  ║
║  └────────────────────────────────────────────────────────────┘  ║
║                                                                  ║
║  ┌─ Archivos Subidos (15) ──────────────────────────────────┐  ║
║  │                                                            │  ║
║  │  [🔄 Actualizar Lista]                                    │  ║
║  │                                                            │  ║
║  │  ┌──────────────────────────────────────────────────────┐ │  ║
║  │  │ Nombre              │ Tamaño │ Tipo      │ Fecha     │ │  ║
║  │  ├──────────────────────────────────────────────────────┤ │  ║
║  │  │ 📄 doc_abc123.pdf   │ 2.5 MB │ PDF       │ 06/12/24  │🗑│ │  ║
║  │  │ 📄 excel_xyz789.xlsx│ 1.2 MB │ Excel     │ 06/12/24  │🗑│ │  ║
║  │  │ 📄 imagen_def456.jpg│ 856 KB │ Image     │ 05/12/24  │🗑│ │  ║
║  │  └──────────────────────────────────────────────────────┘ │  ║
║  │                                                            │  ║
║  │  Mostrando 1-10 de 15  [◀] 1 2 [▶]                       │  ║
║  └────────────────────────────────────────────────────────────┘  ║
╚══════════════════════════════════════════════════════════════════╝
```

## 🎬 Flujo de Uso

### 1️⃣ Verificar Servicio

```
Usuario accede a la página
    ↓
Sistema verifica si Python está activo
    ↓
Muestra indicador verde ✓ o rojo ✗
```

### 2️⃣ Subir Archivo

```
Usuario selecciona módulo
    ↓
Usuario escribe descripción (opcional)
    ↓
Usuario hace clic en "Seleccionar"
    ↓
Usuario elige archivo del sistema
    ↓
Usuario hace clic en "Subir"
    ↓
Archivo se envía al servicio Python
    ↓
Python valida y guarda el archivo
    ↓
Respuesta se muestra al usuario
    ↓
Lista de archivos se actualiza automáticamente
```

### 3️⃣ Ver Archivos

```
Sistema carga lista de archivos
    ↓
Muestra tabla con información
    ↓
Usuario puede paginar si hay más de 10
```

### 4️⃣ Eliminar Archivo

```
Usuario hace clic en 🗑️
    ↓
Sistema muestra confirmación
    ↓
Usuario confirma
    ↓
Archivo se elimina del servidor
    ↓
Lista se actualiza
```

## 💬 Mensajes del Sistema

### ✅ Mensajes de Éxito

```
┌────────────────────────────────────────┐
│ ✓ Éxito                                │
│ Archivo subido exitosamente:           │
│ documento.pdf                           │
└────────────────────────────────────────┘
```

```
┌────────────────────────────────────────┐
│ ✓ Éxito                                │
│ Archivo eliminado correctamente        │
└────────────────────────────────────────┘
```

### ⚠️ Mensajes de Advertencia

```
┌────────────────────────────────────────┐
│ ⚠ Servicio de carga no disponible     │
│ Asegúrate de que el servicio Python    │
│ esté ejecutándose en el puerto 5000    │
└────────────────────────────────────────┘
```

### ❌ Mensajes de Error

```
┌────────────────────────────────────────┐
│ ✗ Error                                │
│ Tipo de archivo no permitido           │
└────────────────────────────────────────┘
```

```
┌────────────────────────────────────────┐
│ ✗ Error                                │
│ Archivo demasiado grande (máx. 16MB)   │
└────────────────────────────────────────┘
```

## 🔄 Estados del Sistema

### Estado: Servicio Activo ✅

```
┌────────────────────────────────────────┐
│ ✓ Servicio Python Activo               │
│ (Fondo verde)                           │
└────────────────────────────────────────┘

- Botón de subir: HABILITADO
- Funcionalidad: COMPLETA
```

### Estado: Servicio Inactivo ❌

```
┌────────────────────────────────────────┐
│ ✗ Servicio Python No Disponible        │
│ (Fondo rojo)                            │
└────────────────────────────────────────┘

- Botón de subir: DESHABILITADO
- Funcionalidad: LIMITADA (solo ver archivos)
```

## 📋 Ejemplo de Sesión Completa

### Paso 1: Inicio
```
[Usuario] Abre http://localhost:8080/sitea/views/admin/gestionArchivos.xhtml
[Sistema] Verifica servicio Python
[Sistema] Muestra: "✓ Servicio Python Activo"
[Sistema] Carga lista de archivos existentes
```

### Paso 2: Preparar Carga
```
[Usuario] Selecciona módulo: "Caracterización"
[Usuario] Escribe descripción: "Formulario de caracterización familiar"
[Usuario] Hace clic en "Seleccionar"
[Sistema] Abre diálogo de archivos
```

### Paso 3: Seleccionar Archivo
```
[Usuario] Selecciona: "caracterizacion_familia_lopez.pdf" (2.3 MB)
[Sistema] Valida extensión: ✓ PDF permitido
[Sistema] Valida tamaño: ✓ 2.3 MB < 16 MB
[Sistema] Habilita botón "Subir"
```

### Paso 4: Subir
```
[Usuario] Hace clic en "Subir"
[Sistema] Muestra indicador de carga
[Sistema] Envía archivo a Python (POST /upload)
[Python]  Recibe archivo
[Python]  Genera nombre único: "caracterizacion_familia_lopez_a1b2c3d4.pdf"
[Python]  Guarda en uploads/
[Python]  Responde: {"success": true, "filename": "..."}
[Sistema] Muestra: "✓ Archivo subido exitosamente"
[Sistema] Actualiza lista de archivos
```

### Paso 5: Verificar
```
[Usuario] Ve el archivo en la tabla
[Sistema] Muestra:
          - Nombre: caracterizacion_familia_lopez_a1b2c3d4.pdf
          - Tamaño: 2.3 MB
          - Tipo: application/pdf
          - Fecha: 06/12/2024 14:30
```

### Paso 6: Eliminar (Opcional)
```
[Usuario] Hace clic en 🗑️ junto al archivo
[Sistema] Muestra: "¿Está seguro de eliminar este archivo?"
[Usuario] Hace clic en "Sí"
[Sistema] Envía petición a Python (DELETE /delete/...)
[Python]  Elimina archivo físico
[Python]  Responde: {"success": true}
[Sistema] Muestra: "✓ Archivo eliminado correctamente"
[Sistema] Actualiza lista
```

## 🎨 Colores y Estilos

### Indicadores de Estado
- **Verde (#d4edda)**: Servicio activo, operaciones exitosas
- **Rojo (#f8d7da)**: Servicio inactivo, errores
- **Amarillo (#fff3cd)**: Advertencias
- **Azul (#cce5ff)**: Información

### Iconos Usados
- ✓ : Éxito
- ✗ : Error
- ⚠ : Advertencia
- 📄 : Archivo
- 🗑️ : Eliminar
- 🔄 : Actualizar
- ▼ : Desplegable

## 📱 Responsive

La interfaz se adapta a diferentes tamaños de pantalla:

### Desktop (> 1024px)
- Tabla completa con todas las columnas
- Botones con texto completo

### Tablet (768px - 1024px)
- Tabla con columnas principales
- Algunos textos abreviados

### Mobile (< 768px)
- Vista de tarjetas en lugar de tabla
- Botones con solo iconos

## 🎯 Casos de Uso Comunes

### Caso 1: Subir Documento de Caracterización
```
Módulo: Caracterización
Archivo: formulario_caracterizacion.pdf
Descripción: "Caracterización familiar - Familia Pérez"
Resultado: ✓ Subido exitosamente
```

### Caso 2: Subir Múltiples Imágenes
```
Módulo: PIAR
Archivos: foto1.jpg, foto2.jpg, foto3.jpg
Descripción: "Evidencias fotográficas del estudiante"
Resultado: ✓ 3 archivos subidos
```

### Caso 3: Subir Excel de Notas
```
Módulo: Gestión Estudiantil
Archivo: notas_periodo_1.xlsx
Descripción: "Consolidado de notas primer periodo"
Resultado: ✓ Subido exitosamente
```

## 🔍 Tips de Uso

1. **Nombres descriptivos**: Usa nombres de archivo claros
2. **Organización**: Selecciona el módulo correcto
3. **Descripciones**: Agrega contexto en la descripción
4. **Verificación**: Revisa la lista después de subir
5. **Limpieza**: Elimina archivos obsoletos regularmente

---

**¡Interfaz lista para usar!** 🎉
