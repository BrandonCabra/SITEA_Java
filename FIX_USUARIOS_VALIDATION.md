# 🔧 FIX: Validación de Entidad Usuarios en PadreAccuClienteService

## Problema Identificado

Al registrar un estudiante con diagnóstico confirmado, la creación de la cuenta de padre fallaba con error:

```
javax.validation.ConstraintViolationException: 
One or more Bean Validation constraints were violated while executing Automatic Bean Validation 
on callback event: prePersist for class: com.sena.sitea.entities.Usuarios.
```

### Causa Raíz

La entidad `Usuarios` tiene dos campos `@ManyToOne(optional = false)` que son obligatorios:

1. **`tipoDocumentoIdTipoDocumento`** - Tipo de Documento (Cédula, Pasaporte, etc.)
2. **`rolIdRol`** - Rol del Usuario (PADRE, ESTUDIANTE, DOCENTE, etc.)

El código anterior solo asignaba `rolIdRol` pero NO asignaba `tipoDocumentoIdTipoDocumento`, lo que causaba que la validación fallara en el evento `@PrePersist` de JPA.

## Solución Implementada

Se actualizó `PadreAccuClienteService.crearCuentaPadre()` para:

### 1. Buscar y Asignar TipoDocumento

```java
// ⭐ IMPORTANTE: Asignar TipoDocumento (requerido por @NotNull en entidad)
// Asumir tipo de documento ID=1 (Cédula de Ciudadanía)
TipoDocumento tipoDocumento = em.find(TipoDocumento.class, 1);
if (tipoDocumento != null) {
    usuarioPadre.setTipoDocumentoIdTipoDocumento(tipoDocumento);
} else {
    // Fallback: buscar el primer tipo de documento disponible
    TipoDocumento tipoDocDefault = em.createNamedQuery("TipoDocumento.findAll", TipoDocumento.class)
        .setMaxResults(1)
        .getResultList()
        .stream()
        .findFirst()
        .orElseThrow(() -> new RuntimeException("No hay tipos de documento disponibles en la BD"));
    usuarioPadre.setTipoDocumentoIdTipoDocumento(tipoDocDefault);
}
```

**Estrategia:**
- Primero intenta buscar tipo de documento con ID=1 (generalmente Cédula)
- Si no existe, busca cualquier tipo de documento disponible
- Si no hay ninguno, lanza excepción clara

### 2. Mejorar Búsqueda de Rol PADRE

```java
// Asignar rol PADRE (ID = 4, asumir que existe)
Rol rolPadre = em.find(Rol.class, 4);
if (rolPadre != null) {
    usuarioPadre.setRolIdRol(rolPadre);
} else {
    // Fallback: buscar rol por nombre
    Rol rolDefault = em.createNamedQuery("Rol.findByNombreRol", Rol.class)
        .setParameter("nombreRol", "PADRE")
        .getResultList()
        .stream()
        .findFirst()
        .orElseThrow(() -> new RuntimeException("No existe rol PADRE en la BD"));
    usuarioPadre.setRolIdRol(rolDefault);
}
```

**Mejora:**
- Si el rol ID=4 no existe, busca por nombre "PADRE"
- Mejor manejo de errores con mensajes claros

### 3. Agregar Import Faltante

```java
import com.sena.sitea.entities.TipoDocumento;
```

## Validación de Campos Obligatorios

La entidad `Usuarios` requiere estos campos `@NotNull`:

| Campo | Descripción | Solución |
|-------|-------------|----------|
| `numeroDocumento` | ✅ Generado: `TEMP-PADRE-<timestamp>` | Implementado |
| `primerNombre` | ✅ Extraído de `acudientePrincipal` | Implementado |
| `primerApellido` | ✅ Extraído de `acudientePrincipal` | Implementado |
| `direccionUsuario` | ✅ De `estudiante.direccion` o "N/A" | Implementado |
| `password` | ✅ Hasheado SHA-256 | Implementado |
| `tipoDocumentoIdTipoDocumento` | ⭐ **ARREGLADO**: Busca TipoDocumento | **NUEVA LÓGICA** |
| `rolIdRol` | ✅ Busca Rol ID=4 con fallback | Mejorado |

## Cambios de Código

**Archivo:** `/src/main/java/com/sena/sitea/services/PadreAccuClienteService.java`

**Líneas cambiadas:** ~30-85

**Cambios principales:**
1. ✅ Importar `TipoDocumento`
2. ✅ Agregar búsqueda y asignación de `tipoDocumentoIdTipoDocumento`
3. ✅ Mejorar fallback de `rolIdRol`
4. ✅ Mejor logging de errores

## Comprobación

```
✅ BUILD SUCCESS
• 144 source files compilados
• 0 errores
• 5.1 segundos
```

## Cómo Probar

1. **Ir a:** Crear Estudiante → Pre-registro
2. **Llenar datos:**
   - Tipo Registro: `Diagnóstico Confirmado`
   - Acudiente: `Juan Pérez` (o similar)
   - Correo acudiente: Email válido
   - Todos los datos obligatorios
3. **Submit**
4. **Resultado esperado:**
   - ✅ Estudiante registrado
   - ✅ Expediente generado (EXP-TEA-YYYY-####)
   - ✅ Cuenta de padre creada
   - ✅ Email enviado vía SendGrid
   - ✅ UI muestra confirmación verde

## Posibles Errores Residuales

| Error | Causa | Solución |
|-------|-------|----------|
| "No hay tipos de documento" | No existen tipos en tabla `tipo_documento` | Ejecutar: `INSERT INTO tipo_documento VALUES (1, 'Cédula de Ciudadanía')` |
| "No existe rol PADRE" | No existe rol con ID=4 en tabla `rol` | Verificar que existe `INSERT INTO rol VALUES (4, 'PADRE')` |
| SENDGRID_API_KEY no configurada | Variable de entorno no seteada | Ejecutar: `export SENDGRID_API_KEY="SG.xxx"` |
| Email no enviado | API Key inválida | Verificar API Key en SendGrid dashboard |

## Próximos Pasos

1. **Prueba local:** Registra un estudiante con diagnóstico
2. **Verifica:** 
   - Estudiante en BD ✓
   - Padre en BD ✓
   - Email enviado ✓
3. **Revisa logs:** `grep -i "Error al crear" server.log`
4. **Monitorea:** Dashboard SendGrid para confirmación de envío

## Archivos Modificados

```
PadreAccuClienteService.java
├─ Agregado: import TipoDocumento
├─ Modificado: crearCuentaPadre() - líneas 31-85
├─ Agregado: Búsqueda de TipoDocumento con fallback
├─ Mejorado: Búsqueda de Rol con fallback
└─ Mejorado: Logging de errores
```

## Estado

✅ **ARREGLADO Y COMPILADO**
- Causa identificada
- Solución implementada
- Compilación exitosa (0 errores)
- Listo para testing

---

**Fecha:** 2 de diciembre de 2025  
**Versión:** 1.1 (FIX)  
**Compilación:** 144 archivos, SUCCESS
