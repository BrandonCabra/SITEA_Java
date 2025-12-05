# GUÍA RÁPIDA: Integración SendGrid en SITEA

## 1. Obtener API Key de SendGrid

1. Ir a https://sendgrid.com y crear cuenta
2. Ir a Settings > API Keys
3. Click "Create API Key"
4. Dar nombre: `SITEA-Production` (o tu preferencia)
5. Seleccionar permisos: **Mail Send** (al mínimo)
6. Copiar la clave generada (solo aparece una vez)

## 2. Configurar variable de entorno

### Linux/Mac:

```bash
# Editar archivo de configuración del shell
nano ~/.bashrc  # o ~/.zshrc para macOS reciente

# Agregar al final:
export SENDGRID_API_KEY="SG.Tu_Clave_Aqui"

# Guardar (Ctrl+O, Enter, Ctrl+X)

# Recargar configuración:
source ~/.bashrc

# Verificar:
echo $SENDGRID_API_KEY
```

### Windows (CMD):

```cmd
setx SENDGRID_API_KEY "SG.Tu_Clave_Aqui"
# Cerrar y abrir CMD nuevamente
echo %SENDGRID_API_KEY%
```

### Windows (PowerShell):

```powershell
[Environment]::SetEnvironmentVariable("SENDGRID_API_KEY", "SG.Tu_Clave_Aqui", "User")
# Cerrar y abrir PowerShell
$env:SENDGRID_API_KEY
```

## 3. Configurar email remitente en SendGrid

1. En SendGrid Dashboard: Settings > Sender Authentication
2. Click "Create New Sender"
3. Llenar datos:
   - **From Email:** sitea.edu@gmail.com (o tu dominio)
   - **From Name:** SITEA - Plataforma TEA
   - **Reply-To:** soporte@sitea.edu.co
4. Verificar email (ir al inbox y confirmar)
5. Una vez verificado, ya puedes usarlo como remitente

## 4. Verificar en código

En `PadreEmailService.java` (línea ~43):
```java
Email from = new Email("sitea.edu@gmail.com", "SITEA - Plataforma TEA");
```

**Cambiar por tu email verificado en SendGrid.**

## 5. Pruebas

### Opción A: Correo de prueba
1. Ir a: http://localhost:8080/sitea-1.0-SNAPSHOT/views/protocolosRutas/index.xhtml
2. Seccion "Prueba SendGrid Email"
3. Ingresa email, asunto, mensaje
4. Click "Enviar Email"
5. Revisa bandeja (incluyendo Spam)

### Opción B: Crear estudiante con diagnóstico
1. Ir a: Crear Estudiante > Pre-registro
2. Llenar datos
3. Tipo Registro: **"Diagnóstico Confirmado"**
4. Datos acudiente: nombre y correo válido
5. Submit
6. Sistema crea cuenta de padre y envía correo automáticamente
7. Padre recibe correo con credenciales temporales

## 6. Mensajes de éxito esperados

En consola (GlassFish logs):
```
Status Code: 202
Email enviado correctamente!
```

En UI (FacesMessage):
- Verde: "Cuenta Padre Creada - Se creó cuenta para: [Nombre] y se envió correo de credenciales"
- Naranja (warning): "Aviso - Cuenta de padre creada pero hubo problema al enviar el correo"

## 7. Verificar logs

### GlassFish:
```bash
# Linux
tail -f /path/to/glassfish/domains/domain1/logs/server.log

# Windows
type %GLASSFISH_HOME%\domains\domain1\logs\server.log
```

### Buscar errores:
```bash
grep -i "SENDGRID_API_KEY\|sendgrid\|email" server.log
```

## 8. Troubleshooting

| Error | Causa | Solución |
|-------|-------|----------|
| `SENDGRID_API_KEY no está configurada` | Variable no existe | Ejecutar: `echo $SENDGRID_API_KEY` para verificar. Reiniciar GlassFish |
| `Status Code: 401` | API Key inválida | Generar nueva clave en SendGrid. Copiar completa sin espacios |
| `Status Code: 400` | Email remitente no verificado | Ir a SendGrid > Sender Authentication y verificar email |
| Email no llega | Spam/Bloqueado | Chequear carpeta Spam. Whitelisting del dominio |
| `NullPointerException` | Datos incompletos | Verificar que estudiante tenga correoContacto y acudientePrincipal |

## 9. Dashboard SendGrid

Para monitorear:
- **Analytics:** https://app.sendgrid.com/stats
  - Emails enviados, bounces, opens, clicks
- **Email Activity:** https://app.sendgrid.com/email_activity
  - Detalles de cada email (logs)
- **Suppression Management:** https://app.sendgrid.com/suppressions
  - Emails rechazados/bloqueados

## 10. Límites y Cuotas

- **Plan Free:** 100 emails/día
- **Pro:** 600 emails/hora
- **Requests API:** 600 por minuto

Si planeas envío masivo a padres (ej: notificaciones), considera plan de pago.

## 11. Campos de correo de padre (Customize HTML)

El template actual en `PadreEmailService.java` incluye:
- Nombre del padre/acudiente
- Nombre del estudiante
- Código de expediente
- Credenciales temporales (usuario/contraseña)
- Instrucciones de cambio de contraseña
- Link a portal SITEA
- Footer con contacto de soporte

Para personalizarlo:
1. Editar método `construirTemplateHTML()` en `PadreEmailService.java`
2. Cambiar colores, textos, links
3. Mantener las variables: `{nombrePadre}`, `{nombreEstudiante}`, `{expediente}`, etc.

## 12. Próximos pasos

- [ ] Configurar variable de entorno SENDGRID_API_KEY en servidor producción
- [ ] Verificar email remitente en SendGrid (Sender Authentication)
- [ ] Cambiar URL del portal (línea 88 de PadreEmailService) si no es localhost
- [ ] Cambiar email de soporte (línea 92) por el real
- [ ] Probar con correo real de padre/acudiente
- [ ] Monitorear Analytics en SendGrid dashboard
- [ ] Crear contacto en SendGrid para incidencias (support@sendgrid.com)

---

**Autor:** Sistema SITEA  
**Fecha:** 2025-12-02  
**Versión:** 1.0
