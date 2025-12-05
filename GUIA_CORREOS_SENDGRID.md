== GUÍA: Configuración de SendGrid para envío de credenciales de padres ==

CONFIGURACIÓN SENDGRID (Recomendado)

1. **Requisitos previos:**
   - Tener cuenta en SendGrid (https://sendgrid.com)
   - Contar con una API Key válida
   - Tener un email verificado en SendGrid como remitente

2. **Configurar la API Key en el servidor:**

   **Opción A: Variable de entorno (RECOMENDADO para producción)**
   ```bash
   # En Linux/Mac, agregá a ~/.bashrc o ~/.bash_profile:
   export SENDGRID_API_KEY="SG.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
   
   # Luego recargá:
   source ~/.bashrc
   
   # Verificá que esté disponible:
   echo $SENDGRID_API_KEY
   ```

   **Opción B: Variable JVM (para desarrollo en GlassFish)**
   - Ir a: GlassFish Admin Console (http://localhost:4848)
   - Configurations > server-config > JVM Settings > VM Options
   - Agregar: `-DSENDGRID_API_KEY=SG.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx`
   - Reiniciar GlassFish

3. **Cambiar el email remitente en el código:**
   
   En `EmailService.java` y `PadreEmailService.java`, buscar la línea:
   ```java
   Email from = new Email("sitea.edu@gmail.com", "SITEA - Plataforma TEA");
   ```
   Y reemplazar con tu email verificado en SendGrid.

4. **Endpoint de correos en PadreEmailService:**
   
   El servicio `PadreEmailService` ya está configurado para:
   - Enviar correos HTML con credenciales temporales a padres
   - Generar un template personalizado con marca SITEA
   - Usar la API Key automáticamente desde la variable de entorno
   - Retornar estado de envío (true/false) para manejo de errores

== SERVICIOS IMPLEMENTADOS ==

1. **EmailService** (com.sena.sitea.services.EmailService)
   - Métodos: enviarEmailPrueba(), enviarEmailMasivo()
   - Uso: Correos generales de prueba y masivos (via controladores EmailTestBean y EmailMasivoBean)

2. **PadreEmailService** (com.sena.sitea.services.PadreEmailService)
   - Método: enviarCredencialesPadre()
   - Uso: Envío automático de credenciales al crear cuenta de padre (via EstudianteTeaController)
   - Template: HTML responsivo con branding SITEA

3. **PadreAccuClienteService** (com.sena.sitea.services.PadreAccuClienteService)
   - Método: crearCuentaPadre()
   - Acción: Crea usuario PADRE en BD + genera contraseña temporal (SHA-256 hasheada)

== FLUJO DE CREACIÓN DE CUENTA DE PADRE ==

Cuando se registra un estudiante con diagnóstico confirmado (tipoRegistro=diagnostico):

1. EstudianteTeaController.crearEstudianteP2()
   ├─ Valida datos del estudiante
   ├─ Crea expediente con ExpedienteService (genera "EXP-TEA-YYYY-####")
   ├─ Persiste Estudiante en BD
   │
   └─ Si diagnostico confirmado:
      ├─ PadreAccuClienteService.crearCuentaPadre()
      │  ├─ Crea Usuarios entity con rol=PADRE
      │  ├─ Genera documento temporal: "TEMP-PADRE-<timestamp>"
      │  ├─ Genera contraseña temporal: 12 caracteres aleatorios
      │  ├─ Hashea contraseña (SHA-256 + salt)
      │  └─ Retorna UsuarioPadreDTO con credenciales
      │
      ├─ PadreEmailService.enviarCredencialesPadre()
      │  ├─ Obtiene API Key de variable de entorno
      │  ├─ Construye template HTML personalizado
      │  ├─ Envía via SendGrid API (endpoint: mail/send)
      │  └─ Retorna true/false según estado
      │
      └─ Mensajes en UI:
         ├─ Success: "Cuenta de padre/acudiente creada automáticamente"
         ├─ Info: "Credenciales enviadas a: [correo]"
         └─ Warning (si falla email): "Cuenta creada pero error al enviar correo"

== DEPENDENCIAS MAVEN ==

SendGrid ya está agregado en pom.xml:
```xml
<dependency>
    <groupId>com.sendgrid</groupId>
    <artifactId>sendgrid-java</artifactId>
    <version>4.8.0</version>
</dependency>
```

Si no está, agregalo manualmente en pom.xml.

== TESTING DE ENVÍOS ==

1. **Correo de prueba individual:**
   - URL: http://localhost:8080/sitea-1.0-SNAPSHOT/views/protocolosRutas/index.xhtml
   - Usar controlador: EmailTestBean
   - Ingresa: correo, asunto, mensaje
   - Click "Enviar Email"

2. **Correos masivos:**
   - Misma página
   - Usar controlador: EmailMasivoBean
   - Ingresa: asunto, mensaje, destinatarios (uno por línea)
   - Click "Enviar a Todos"

3. **Verificar credenciales de padres:**
   - Registra un estudiante con diagnóstico confirmado
   - El sistema crea cuenta de padre automáticamente
   - Verifica que el correo llegue al acudiente (chequea Spam/Promociones)

== TROUBLESHOOTING ==

| Problema | Solución |
|----------|----------|
| "SENDGRID_API_KEY no está configurada" | Verificar que la variable de entorno esté seteada. Reiniciar terminal/GlassFish |
| "Error al enviar email: Status Code 401" | API Key inválida o vencida. Generar nueva en SendGrid dashboard |
| "Error al enviar email: Status Code 400" | Email remitente no verificado en SendGrid. Verificar "Sender Authentication" |
| Correo no llega a bandeja | Revisar carpeta Spam/Promociones. Whitelisting del dominio sitea.edu@gmail.com |
| NullPointerException en PadreEmailService | Chequear que correoDestino no sea null. Validar Estudiante.correoContacto |

== VARIABLES DE ENTORNO (COMPLETO) ==

Para producción, configurar estas variables en el servidor:

```bash
# SendGrid
export SENDGRID_API_KEY="SG.xxxxx"

# Base de datos (si aplica)
export DB_URL="jdbc:mysql://localhost:3306/sitea_db"
export DB_USER="root"
export DB_PASSWORD="xxxxx"

# Email remitente (si quieres hacerlo dinámico)
export SITEA_EMAIL_FROM="sitea.edu@gmail.com"
export SITEA_EMAIL_FROM_NAME="SITEA - Plataforma TEA"
```

Luego actualizar los servicios para leer estas variables si lo requieres.

== ALTERNATIVAS A SENDGRID ==

Si en el futuro necesitas cambiar de proveedor:

1. **Mailgun** (API REST similar)
2. **Postmark** (especializado en transaccionales)
3. **AWS SES** (escalable, barato)
4. **JavaMail + SMTP directo** (ver sección al pie)

== VOLVER A JAVAMAIL (Si es necesario) ==

Si necesitas desactivar SendGrid y usar JavaMail:

1. Comentar inyección de PadreEmailService en EstudianteTeaController
2. Revertir a:
   ```java
   @Resource(mappedName = "mail/sitea")
   private Session mailSession;
   ```
3. Seguir pasos de "GUÍA ANTIGUA: Configuración de JavaMail en GlassFish" (abajo)

---

== GUÍA ANTIGUA: Configuración de JavaMail en GlassFish ==

*(Mantenida para referencia, no se usa actualmente)*

1. Acceder a http://localhost:4848 (GlassFish Admin Console)
2. Ir a: Configurations > server-config > Java Mail Sessions
3. Click "New..." y llenar:
   - JNDI Name: mail/sitea
   - Host: smtp.gmail.com
   - Default User: tu_email@gmail.com
   - Default Password: tu_contraseña_app
   - Port: 587
4. Agregar propiedades:
   - mail.smtp.auth=true
   - mail.smtp.starttls.enable=true
   - mail.smtp.starttls.required=true
5. Reiniciar: `asadmin stop-domain && asadmin start-domain`

*(Ver líneas 140+ del código para implementación con @Resource)*

