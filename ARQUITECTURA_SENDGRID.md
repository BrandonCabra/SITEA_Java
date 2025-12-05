# 🏗️ Arquitectura del Sistema - Email a Padres

## Flujo Completo de Envío de Credenciales

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    USUARIO REALIZA PREREGISTRO                          │
│                         login.xhtml                                      │
└────────────────────┬────────────────────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                    EstudianteTeaController                              │
│                  (Controlador JSF/MVC)                                   │
│                                                                          │
│  • Recibe datos del formulario                                          │
│  • Valida información básica                                            │
│  • Llama PadreAccuClienteService                                        │
└────────────────────┬────────────────────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────────────────────┐
│              PadreAccuClienteService (EJB)                              │
│                                                                          │
│  1. Genera usuario temporal: PADRE{timestamp}                           │
│  2. Genera contraseña temporal: Random 12+ caracteres                   │
│  3. Hashea contraseña con BCrypt                                        │
│  4. Busca/Asigna TipoDocumento                                          │
│  5. Busca/Asigna Rol (PADRE DE FAMILIA, ID=3)                           │
│  6. Crea entidad Usuarios                                               │
│  7. Persiste en BD mediante UsuariosFacade                              │
│  8. **LUEGO** Llama PadreEmailService.enviarCredencialesPadre()         │
└────────────────────┬────────────────────────────────────────────────────┘
                     │
                     ▼ (si creación exitosa)
┌─────────────────────────────────────────────────────────────────────────┐
│           PadreEmailService (EJB Stateless)                             │
│                                                                          │
│  1. Lee SENDGRID_API_KEY del entorno (validado)                        │
│  2. Lee SENDGRID_FROM_EMAIL del entorno (validado)          ◄───────┐  │
│  3. Construye template HTML personalizado                             │  │
│  4. Crea objeto Mail (from, to, subject, html)                       │  │
│  5. Inicializa cliente SendGrid con API Key                          │  │
│  6. Envía a través de SendGrid API (POST /mail/send)                 │  │
│  7. Valida respuesta (código 202 = éxito)                            │  │
│  8. Retorna true/false al controlador                                │  │
└────────────────────┬────────────────────────────────────────────────────┘
                     │                                       ▲
                     │                                       │
                     │    ¡AQUÍ ES DONDE ANTES FALLABA!     │
                     │                                       │
                     │    ❌ EMAIL NO VERIFICADO             │
                     │    ❌ STATUS 403 FORBIDDEN            │
                     │                                       │
                     │                                       │
                     ▼                    ┌──────────────────┘
        ┌────────────────────────────────┴──────────────────┐
        │         SENDGRID API CLOUD                        │
        │  (sendgrid.api.com/mail/send)                    │
        │                                                  │
        │  ✅ Valida API Key (SENDGRID_API_KEY)            │
        │  ✅ Valida FROM email (SENDGRID_FROM_EMAIL)      │
        │  ✅ Si falla: Retorna 403                        │
        │  ✅ Si éxito: Retorna 202 + envía email          │
        │                                                  │
        └───────────────┬──────────────────────────────────┘
                        │
                        ▼
        ┌────────────────────────────────────────────────┐
        │      EMAIL DEL PADRE/ACUDIENTE                │
        │  (Bandeja de entrada or SPAM)                 │
        │                                                │
        │  De: noreply@sitea.edu.co                     │
        │  Asunto: Credenciales de Acceso - SITEA      │
        │  Contenido:                                   │
        │  - Usuario: PADRE1704099200123               │
        │  - Contraseña: aBc123!@#Xyz789               │
        │  - Link acceso                                │
        │  - Instrucciones de seguridad                 │
        └────────────────────────────────────────────────┘
```

---

## Datos que Fluyen

### 1. Entrada (Formulario de Preregistro)
```
nombreEstudiante: "Juan García"
apellidoEstudiante: "López"
expediente: "2024-1001"
nombrePadre: "María García"
emailPadre: "maria@gmail.com"
telefonoPadre: "3001234567"
tipoDocumento: "CEDULA"
numeroDocumento: "87654321"
```

### 2. Dentro de PadreAccuClienteService
```
usuarioTemporal: "PADRE1702608961234" (19 caracteres máximo)
passwordTemporal: "XyZ@9aBcDeFg" (generado aleatoriamente)
passwordHash: "$2a$10$HwDkbVXlhtmEhvV9jsxl/O3A3uxBz19XjamUbUaOkv56MxRN87fH6"
rolId: 3  (PADRE DE FAMILIA)
tipoDocumentoId: 1  (CEDULA)
```

### 3. Dentro de PadreEmailService
```
SENDGRID_API_KEY: "SG.xxxxxxxxxxxxx..."
SENDGRID_FROM_EMAIL: "noreply@sitea.edu.co"
correoDestino: "maria@gmail.com"
nombrePadre: "María García"
nombreEstudiante: "Juan García"
```

---

## Base de Datos

### Tabla: Usuarios (Padre creado)
```
┌──────────────┬───────────────────────────────────┐
│ id           │ (Auto-generado)                   │
├──────────────┼───────────────────────────────────┤
│ nombreUsuario│ PADRE1702608961234                │
├──────────────┼───────────────────────────────────┤
│ password     │ $2a$10$HwDkbVXl... (BCrypt hash) │
├──────────────┼───────────────────────────────────┤
│ email        │ maria@gmail.com                   │
├──────────────┼───────────────────────────────────┤
│ numeroDoc    │ 87654321                          │
├──────────────┼───────────────────────────────────┤
│ tipoDocId    │ 1 (CEDULA)                       │
├──────────────┼───────────────────────────────────┤
│ rolId        │ 3 (PADRE DE FAMILIA)              │
├──────────────┼───────────────────────────────────┤
│ estado       │ ACTIVO                            │
├──────────────┼───────────────────────────────────┤
│ fechaCreacion│ 2024-12-02 14:15:20               │
└──────────────┴───────────────────────────────────┘
```

---

## Variables de Entorno Requeridas

```bash
┌────────────────────────────────────────────────────┐
│  VARIABLES DE ENTORNO NECESARIAS                   │
├────────────────────────────────────────────────────┤
│                                                    │
│  SENDGRID_API_KEY = "SG.xxxxxxxxxxxxx"            │
│                                                    │
│  Obtener de:                                       │
│  https://app.sendgrid.com/settings/api_keys       │
│                                                    │
│  ─────────────────────────────────────────────    │
│                                                    │
│  SENDGRID_FROM_EMAIL = "noreply@sitea.edu.co"    │
│                                                    │
│  Obtener verificando email en:                     │
│  https://app.sendgrid.com/settings/sender_auth    │
│                                                    │
│  IMPORTANTE: Este email DEBE estar verificado     │
│             de lo contrario → Error 403           │
│                                                    │
└────────────────────────────────────────────────────┘
```

---

## Estados Posibles

### ✅ ÉXITO
```
LOGS:
  📧 Enviando credenciales desde: noreply@sitea.edu.co
  ✅ Email de credenciales enviado exitosamente a: maria@gmail.com

RESULTADO:
  → Cuenta padre creada en BD
  → Email recibido por padre
  → Padre puede login con credenciales
```

### ⚠️ FALLO: Variable No Configurada
```
LOGS:
  ❌ SENDGRID_API_KEY no está configurada
  ❌ SENDGRID_FROM_EMAIL no está configurada

ACCIÓN REQUERIDA:
  → Configurar variables de entorno
  → Reiniciar aplicación
  → Reintentar
```

### ❌ FALLO: Email No Verificado
```
LOGS:
  ❌ Error al enviar email: Status Code 403
  Response: {"errors":[{"message":"The from address does not match a verified Sender Identity"}]}

CAUSA:
  Email en SENDGRID_FROM_EMAIL no está verificado en SendGrid

SOLUCIÓN:
  → Ir a SendGrid > Sender Authentication
  → Verificar email
  → Actualizar SENDGRID_FROM_EMAIL
  → Reintentar
```

### ❌ FALLO: API Key Inválida
```
LOGS:
  ❌ Error al enviar email: Status Code 401
  Response: {"errors":[{"message":"Unauthorized"}]}

CAUSA:
  SENDGRID_API_KEY es inválido o expirado

SOLUCIÓN:
  → Generar nueva API Key en SendGrid
  → Actualizar SENDGRID_API_KEY
  → Reintentar
```

---

## Archivos Clave del Sistema

```
src/main/java/com/sena/sitea/
│
├── controllers/
│   └── EstudianteTeaController.java  ◄─── Punto de entrada
│
├── services/
│   ├── PadreAccuClienteService.java  ◄─── Crea cuenta
│   ├── PadreEmailService.java        ◄─── Envía email (ACTUALIZADO)
│   ├── PasswordHashService.java
│   └── UsuariosFacade.java
│
├── entities/
│   └── Usuarios.java  ◄─── Entidad con validaciones
│
└── tools/  (debug)
    ├── ValidatorRunner.java
    └── SimulatePadreCreationTest.java

Recursos:
├── SENDGRID_CONFIGURATION.md         ◄─── Docs técnicas
├── INSTRUCCIONES_SENDGRID_FINAL.md   ◄─── Paso a paso
├── configure_sendgrid.sh             ◄─── Script automático
└── RESUMEN_SOLUCION_SENDGRID.md      ◄─── Resumen técnico
```

---

## Comparación: Antes vs Después

### ❌ ANTES
```java
Email from = new Email("sitea.edu@gmail.com", "SITEA - Plataforma TEA");
```
**Problemas:**
- Email hardcodeado en código
- No flexible para diferentes ambientes
- No verificado en SendGrid → Error 403

### ✅ DESPUÉS
```java
String fromEmail = System.getenv("SENDGRID_FROM_EMAIL");
if (fromEmail == null || fromEmail.trim().isEmpty()) {
    System.err.println("❌ SENDGRID_FROM_EMAIL no está configurada");
    return false;
}
Email from = new Email(fromEmail, "SITEA - Plataforma TEA");
```
**Ventajas:**
- Email flexible desde variables de entorno
- Validación clara antes de usar
- Mensajes de error descriptivos
- Fácil cambiar según ambiente (dev/test/prod)

---

## Timeline de Ejecución

```
TIEMPO (ms)  │ ACCIÓN
─────────────┼──────────────────────────────────────
    0 ms     │ Usuario completa formulario preregistro
   50 ms     │ EstudianteTeaController recibe POST
  100 ms     │ PadreAccuClienteService.crearCuenta()
  150 ms     │ Genera usuario: PADRE1704099200123
  200 ms     │ Genera password: XyZ@9aBcDeFg
  250 ms     │ Hashea con BCrypt
  300 ms     │ Busca TipoDocumento (CEDULA)
  350 ms     │ Busca Rol (PADRE DE FAMILIA, ID=3)
  400 ms     │ Crea Usuarios entity
  500 ms     │ Persiste en BD
  550 ms     │ UsuariosFacade.create() completa
  600 ms     │ PadreEmailService.enviarCredencialesPadre()
  650 ms     │ Lee SENDGRID_API_KEY ✅
  700 ms     │ Lee SENDGRID_FROM_EMAIL ✅
  750 ms     │ Construye HTML template
  800 ms     │ POST a SendGrid API
 1500 ms     │ SendGrid responde (HTTP 202)
 1550 ms     │ Retorna true
 1600 ms     │ Controlador muestra: ✅ "Cuenta creada, email enviado"
 2000 ms     │ Email llega a gmail.com del padre
```

---

## Resumen Visual

```
┌─────────────────────────────────────────────────────┐
│  SISTEMA DE CREDENCIALES PARA PADRES                │
├─────────────────────────────────────────────────────┤
│                                                     │
│  ENTRADA (Preregistro)                              │
│    ↓                                                │
│  PROCESA (PadreAccuClienteService)                  │
│    ↓                                                │
│  PERSISTE en BD ✅                                  │
│    ↓                                                │
│  ENVÍA (PadreEmailService + SendGrid)               │
│    ├─ Lee variables de entorno                      │
│    ├─ Valida configuración                          │
│    ├─ Construye email personalizado                 │
│    ├─ Envía por SendGrid API                        │
│    └─ Retorna éxito/error                           │
│    ↓                                                │
│  RESULTADO                                          │
│    ├─ BD: Cuenta creada ✅                          │
│    └─ EMAIL: Credenciales enviadas ✅              │
│                                                     │
└─────────────────────────────────────────────────────┘
```

**Estado:** ✅ Sistema listo y en espera de configuración de variables de entorno
