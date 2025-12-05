# 📧 Configuración de SendGrid - SITEA

## 🎯 Estado Actual

El sistema está listo para enviar credenciales a padres/acudientes por email mediante **SendGrid**. Sin embargo, necesitas configurar la dirección de correo verificada.

## ❌ El Problema

SendGrid rechazó el envío con error **403 Forbidden** porque la dirección `sitea.edu@gmail.com` no está verificada en tu cuenta SendGrid.

**Error recibido:**
```
The from address does not match a verified Sender Identity
```

## ✅ Solución: Configurar Variables de Entorno

### 1. **Obtener una dirección verificada en SendGrid**

#### Opción A: Verificar un correo existente (Recomendado)
1. Inicia sesión en [SendGrid Dashboard](https://app.sendgrid.com)
2. Ve a **Settings** → **Sender Authentication** (o **Sender Identity**)
3. Haz clic en **Create New Sender** (o **Verify a Domain**)
4. Elige **Verify an Email Address**
5. Ingresa tu correo (ej: `noreply@sitea.edu.co` o `admin@sitea.edu.co`)
6. SendGrid te enviará un email de confirmación
7. Verifica el correo haciendo clic en el enlace
8. Una vez verificado, obtén la dirección completa

#### Opción B: Verificar un dominio (Más profesional)
1. Ve a **Settings** → **Sender Authentication**
2. Haz clic en **Verify a Domain**
3. Ingresa tu dominio (ej: `sitea.edu.co`)
4. SendGrid te dará registros DNS para configurar
5. Añade los registros DNS en tu proveedor de dominio
6. Una vez verificado, podrás usar cualquier dirección `@sitea.edu.co`

### 2. **Configurar variables de entorno**

#### En NetBeans (para desarrollo local)

**Opción 1: Variables de entorno del sistema**
```bash
# En Linux/Mac (añadir a ~/.bashrc o ~/.zshrc)
export SENDGRID_API_KEY="tu_api_key_aqui"
export SENDGRID_FROM_EMAIL="noreply@sitea.edu.co"
```

**Opción 2: A través de NetBeans**
1. Ve a **Tools** → **Options** → **Java**
2. Busca la sección de variables de entorno
3. Añade:
   - `SENDGRID_API_KEY`: Tu API key de SendGrid
   - `SENDGRID_FROM_EMAIL`: La dirección verificada (ej: `noreply@sitea.edu.co`)

#### En Producción (GlassFish)

**Opción 1: Mediante configuración de servidor**
1. Abre el archivo `glassfish/config/domain.xml`
2. Busca la sección `<java-config>`
3. Añade propiedades del sistema:
```xml
<jvm-options>-DSENDGRID_API_KEY=tu_api_key</jvm-options>
<jvm-options>-DSENDGRID_FROM_EMAIL=noreply@sitea.edu.co</jvm-options>
```

**Opción 2: Mediante variables de entorno del servidor**
1. Configura en el archivo `setenv.sh` de GlassFish
2. O establece en el contexto de deployment

### 3. **Verificar la configuración**

```bash
# Comprobar que las variables están configuradas
echo $SENDGRID_API_KEY
echo $SENDGRID_FROM_EMAIL
```

## 📋 Checklist de Configuración

- [ ] Dirección de correo verificada en SendGrid (ej: `noreply@sitea.edu.co`)
- [ ] API Key de SendGrid obtenida desde [Settings → API Keys](https://app.sendgrid.com/settings/api_keys)
- [ ] Variable `SENDGRID_API_KEY` configurada en el entorno
- [ ] Variable `SENDGRID_FROM_EMAIL` configurada con dirección verificada
- [ ] Proyecto recompilado (`mvn clean compile`)
- [ ] Aplicación reiniciada para leer nuevas variables de entorno

## 🧪 Prueba de Envío

Una vez configurado, cuando se realice un nuevo registro de estudiante:

1. Se crearán las credenciales del padre/acudiente
2. Se enviará el email desde la dirección configurada en `SENDGRID_FROM_EMAIL`
3. En los logs de la aplicación verás:
   - ✅ `📧 Enviando credenciales desde: noreply@sitea.edu.co`
   - ✅ `✅ Email de credenciales enviado exitosamente a: [email_padre]`

## 🔗 Links Útiles

- [SendGrid Dashboard](https://app.sendgrid.com)
- [Documentación - Verificar Sender Identity](https://docs.sendgrid.com/ui/account-and-settings/how-to-set-up-sender-authentication)
- [Obtener API Key](https://app.sendgrid.com/settings/api_keys)
- [Solucionar problemas de envío](https://docs.sendgrid.com/for-developers/sending-email/v3-ruby-mail-send-errors)

## 📞 Soporte

Si encuentras problemas:
1. Verifica que `SENDGRID_FROM_EMAIL` corresponde a una dirección verificada
2. Verifica que `SENDGRID_API_KEY` es válida y activa
3. Revisa los logs de la aplicación para mensajes de error específicos
4. Consulta el [estado de SendGrid](https://status.sendgrid.com)

---

## Cambios Realizados en el Código

### PadreEmailService.java

Se actualizó para:
1. **Leer `SENDGRID_FROM_EMAIL`** del entorno en lugar de usar email hardcodeado
2. **Validar** que la variable esté configurada antes de intentar envío
3. **Mostrar mensajes claros** si la variable no está configurada
4. **Usar la dirección verificada** en SendGrid automáticamente

**Antes:**
```java
Email from = new Email("sitea.edu@gmail.com", "SITEA - Plataforma TEA");
```

**Después:**
```java
String fromEmail = System.getenv("SENDGRID_FROM_EMAIL");
if (fromEmail == null || fromEmail.trim().isEmpty()) {
    System.err.println("❌ SENDGRID_FROM_EMAIL no está configurada");
    // ... instrucciones detalladas ...
    return false;
}
Email from = new Email(fromEmail, "SITEA - Plataforma TEA");
```

---

## Resumen de Pasos Rápidos

1. **Verifica correo en SendGrid**: https://app.sendgrid.com/settings/sender_auth
2. **Configura variables:**
   ```bash
   export SENDGRID_API_KEY="SG.xxxxxxxxxxxxx"
   export SENDGRID_FROM_EMAIL="noreply@sitea.edu.co"
   ```
3. **Reinicia NetBeans/GlassFish**
4. **Prueba con nuevo registro de estudiante**

✅ ¡Listo! El sistema enviará credenciales automáticamente.
