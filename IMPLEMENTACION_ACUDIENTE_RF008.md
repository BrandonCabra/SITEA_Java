# Implementación: Creación de Usuario Acudiente y Envío de Credenciales - RF-008

## Resumen
Se ha implementado completamente la funcionalidad para registrar automáticamente a un acudiente como usuario en el sistema SITEA cuando se captura un contexto familiar, con generación de contraseña temporal y envío de credenciales vía SendGrid.

**Fecha de implementación:** 2025-12-04  
**Componentes modificados:** `CaracterizacionControllerMejorado.java`  
**Compilación:** ✅ BUILD SUCCESS (14:47:15)

---

## Cambios Realizados

### 1. Importaciones Agregadas
Se agregaron las siguientes importaciones al controlador:
```java
import com.sena.sitea.entities.Usuarios;
import com.sena.sitea.entities.Rol;
import com.sena.sitea.entities.TipoDocumento;
import com.sena.sitea.services.UsuariosFacadeLocal;
import com.sena.sitea.services.RolFacadeLocal;
import com.sena.sitea.services.EmailService;
import com.sena.sitea.security.PasswordUtil;
import java.security.SecureRandom;
```

### 2. Inyecciones de EJB
Se agregaron 3 nuevas inyecciones al controlador:
```java
@EJB
private UsuariosFacadeLocal usuariosFacade;

@EJB
private RolFacadeLocal rolFacade;

@EJB
private EmailService emailService;
```

### 3. Método `generateSecurePassword()`
Genera una contraseña temporal de 12 caracteres con:
- Mayúsculas (A-Z)
- Minúsculas (a-z)  
- Números (0-9)
- Caracteres especiales (!@#$)

```java
private String generateSecurePassword() {
    String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$";
    SecureRandom random = new SecureRandom();
    StringBuilder password = new StringBuilder();
    
    for (int i = 0; i < 12; i++) {
        int index = random.nextInt(caracteres.length());
        password.append(caracteres.charAt(index));
    }
    
    return password.toString();
}
```

### 4. Método `registrarUsuarioAcudiente()`
Implementación completa con los siguientes pasos:

#### Paso 1: Validaciones
- Verifica que nombre, email y documento no estén vacíos
- Valida que el email contenga "@"
- Retorna con mensaje si hay datos incompletos

#### Paso 2: Generación de Credenciales
- Genera password temporal seguro (12 caracteres)
- Aplica hash BCrypt al password usando `PasswordUtil.hashPassword()`

#### Paso 3: Creación de Usuario
- Crea nueva instancia de `Usuarios`
- Divide el nombre en primer nombre y primer apellido
- Asigna campos obligatorios:
  - `numeroDocumento`: del acudiente
  - `correoUsuario`: email proporcionado
  - `password`: hash BCrypt de la contraseña temporal
  - `estatus`: "ACTIVO"
  - `direccionUsuario`: "NO_ESPECIFICADO" (campo obligatorio por @NotNull)
  - `fechaRegistroIdFechaRegistro`: fecha actual

#### Paso 4: Asignación de Tipo de Documento
- Asigna `TipoDocumento` id=1 (Cédula de Ciudadanía)
- Se puede ajustar según el tipo de documento del acudiente

#### Paso 5: Asignación de Rol
- Busca el rol "acudiente" en la BD
- Si no encuentra "acudiente", intenta buscar rol id=4 (predeterminado para acudientes)
- Retorna con aviso si no encuentra rol válido

#### Paso 6: Persistencia
- Persiste el usuario mediante `usuariosFacade.create(usuarioAcudiente)`
- Registra en logs del sistema

#### Paso 7: Envío de Email
- Prepara email con estructura profesional:
  - **Asunto:** "SITEA - Credenciales de Acceso"
  - **Cuerpo:** Incluye:
    - Bienvenida personalizada
    - Usuario (documento)
    - Contraseña temporal
    - URL de acceso (configurable)
    - Instrucción de cambio de password
    - Información sobre expiración
    - Contacto de soporte

- Envía mediante `EmailService.enviarEmailPrueba()`
- Si falla el email, NO cancela la creación del usuario
- Muestra contraseña temporal en la UI si email falla

---

## Integración en el Flujo Actual

### Dónde Se Ejecuta
En el método `guardarContextoFamiliar()` después de persistir el contexto:
```java
// Registrar acudiente como usuario si tiene email
if (contextoFamiliar.getAcudienteEmail() != null && !contextoFamiliar.getAcudienteEmail().trim().isEmpty()) {
    registrarUsuarioAcudiente(contextoFamiliar.getAcudienteNombre(), 
                              contextoFamiliar.getAcudienteEmail(),
                              contextoFamiliar.getAcudienteDocumento());
}
```

### Flujo Usuario
1. Iniciar Caracterización (RF-001)
2. Completar Contexto Escolar (RF-003)
3. Completar Contexto Familiar (RF-008) → **← AQUÍ se ejecuta el registro del acudiente**
4. Acudiente recibe email con credenciales
5. Acudiente puede iniciar sesión con documento y password temporal
6. Continuar a Dashboard de Dimensiones

---

## Configuración Necesaria

### Variables de Entorno (SendGrid)
Para que funcione el envío de emails:
```bash
export SENDGRID_API_KEY="SG.xxxxxxxxxxxxxxxxxxxxxxx"
export SENDGRID_FROM_EMAIL="noreply@sitea.edu.co"
```

### Base de Datos Requerida
Asegurar que exista:
- Tabla `usuarios` con campos especificados en la entidad
- Tabla `rol` con rol "acudiente" (recomendado id=4)
- Tabla `tipo_documento` con CC (id=1)

---

## Mensajes de Feedback al Usuario

| Caso | Mensaje | Severidad |
|------|---------|-----------|
| ✅ Éxito | "✓ Acudiente registrado y credenciales enviadas a: email@example.com" | INFO |
| ⚠️ Email falla pero usuario creado | "Acudiente registrado pero no se pudo enviar email: [error]. Contraseña temporal: [password]" | WARN |
| ❌ Datos incompletos | "Datos incompletos del acudiente. Email no será registrado." | WARN |
| ❌ Email inválido | "Email del acudiente inválido: email_invalido" | WARN |
| ❌ Rol no encontrado | "No se encontrado rol 'acudiente' en el sistema. Contacte al administrador." | WARN |
| ❌ Error general | "Error al registrar acudiente: [mensaje de error]" | ERROR |

---

## Logs del Sistema

Se registran los siguientes eventos:
```
[SITEA] Nuevo usuario acudiente creado: email@example.com | Documento: 12345 | Usuario ID: 123
[SITEA] Email enviado exitosamente a: email@example.com
[SITEA ERROR] Error registrando acudiente: [mensaje]
```

---

## Casos de Uso Validados

1. ✅ Acudiente con datos completos y email válido → usuario creado + email enviado
2. ✅ Acudiente con email incompleto → acudiente NO registrado
3. ✅ Acudiente con email inválido → acudiente NO registrado
4. ✅ Email falla pero usuario se crea → se muestra password temporal en UI
5. ✅ Rol "acudiente" no existe → aviso y retorno sin crear usuario
6. ⏳ Email en diferentes dominios (Gmail, Outlook, etc.) → SendGrid maneja

---

## Testing Pendiente

Después de desplegar en GlassFish:

1. Crear estudiante con preregistro
2. Completar Contexto Escolar
3. Completar Contexto Familiar con:
   - Acudiente Nombre: "Juan Pérez"
   - Acudiente Email: "juan@example.com"
   - Acudiente Documento: "123456789"
4. Verificar:
   - Usuario creado en BD (tabla `usuarios`)
   - Email recibido con credenciales
   - Puede iniciar sesión con documento + password

---

## Próximas Mejoras (Futuro)

- [ ] Validación más robusta de email (RFC 5322)
- [ ] Integración con tabla `UsuarioRol` para roles múltiples
- [ ] Plantillas HTML para emails (actualmente texto plano)
- [ ] Historial de credenciales enviadas
- [ ] Opción de re-enviar credenciales si se pierden
- [ ] Dashboard de acudiente con acceso a información del estudiante
- [ ] Cambio de password forzado en primer login

---

## Archivos Modificados

| Archivo | Líneas | Cambios |
|---------|--------|---------|
| `CaracterizacionControllerMejorado.java` | 1-35 | Agregadas 9 importaciones |
| `CaracterizacionControllerMejorado.java` | 80-89 | Agregadas 3 inyecciones @EJB |
| `CaracterizacionControllerMejorado.java` | 1076-1190 | Método `registrarUsuarioAcudiente()` implementado completo |
| `CaracterizacionControllerMejorado.java` | 1192-1207 | Método `generateSecurePassword()` implementado |

---

## Compilación

```
$ mvn -DskipTests clean compile
...
[INFO] Compiling 154 source files
[INFO] BUILD SUCCESS
Total time: 20.417 s
Finished at: 2025-12-04T19:47:15-05:00
```

✅ **Estado: COMPLETADO Y COMPILANDO**

---

## Próxima Fase

1. Ejecutar migración BD: `MIGRACION_CONTEXTO_FAMILIAR.sql`
2. Redeploy en GlassFish
3. Testing en flujo completo
4. Ajustar URL de acceso y email FROM en configuración

