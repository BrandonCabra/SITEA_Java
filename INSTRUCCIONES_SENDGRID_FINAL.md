# 🚀 INSTRUCCIONES FINALES - Configuración SendGrid

## Estado Actual ✅

```
✅ Código actualizado: PadreEmailService.java
✅ Variables de entorno configuradas en código
✅ Compilación: BUILD SUCCESS
✅ Cambios: Listos para producción
```

## Lo que se hizo

Tu sistema **YA ESTÁ LISTO** para enviar emails. Solo necesitas:

1. **Verificar un email en SendGrid** (1-5 minutos)
2. **Configurar 2 variables de entorno** (1 minuto)
3. **Reiniciar la aplicación** (1 minuto)

---

## PASO 1: Verificar Email en SendGrid ⚙️

### Acceder a SendGrid
```
https://app.sendgrid.com
```

### Verificar un Email
1. Haz clic en **Settings** (engranaje abajo a la izquierda)
2. Selecciona **Sender Authentication**
3. Haz clic en **Create New Sender** (botón azul)
4. Llena el formulario:
   - **From Email Address:** `noreply@sitea.edu.co` (o similar)
   - **From Name:** `SITEA - Plataforma TEA`
   - **Reply To:** `soporte@sitea.edu.co`
5. Haz clic en **Create**
6. SendGrid te enviará un email de confirmación
7. Abre tu email y haz clic en el enlace de confirmación
8. **¡Listo!** El email está verificado

### Si no puedes verificar un correo
- **Opción alternativa:** Verificar todo un dominio (requiere acceso a DNS)
- Ver documentación en `SENDGRID_CONFIGURATION.md`

---

## PASO 2: Obtener API Key 🔑

1. En SendGrid, ve a **Settings** → **API Keys**
2. Haz clic en **Create API Key**
3. Dale un nombre: `SITEA_Java_Production`
4. Selecciona **Full Access** (o acceso personalizado a correos)
5. Haz clic en **Create & Close**
6. **COPIA LA CLAVE** (aparece una sola vez)
7. Guárdala en un lugar seguro

---

## PASO 3: Configurar Variables de Entorno 💻

### OPCIÓN A: Script Automático (Recomendado)

```bash
cd /home/brandon/NetBeansProjects/SITEA_Java
./configure_sendgrid.sh
```

Te pedirá:
- SENDGRID_API_KEY (la que copiaste en Paso 2)
- SENDGRID_FROM_EMAIL (el email verificado en Paso 1)

El script guardará todo en `~/.bashrc`.

**Después, ejecuta:**
```bash
source ~/.bashrc
```

### OPCIÓN B: Manual en Linux/Mac

Abre terminal y ejecuta:
```bash
echo 'export SENDGRID_API_KEY="SG.xxxxxxxxxxxxx"' >> ~/.bashrc
echo 'export SENDGRID_FROM_EMAIL="noreply@sitea.edu.co"' >> ~/.bashrc
source ~/.bashrc
```

Reemplaza:
- `SG.xxxxxxxxxxxxx` → Tu API Key de SendGrid
- `noreply@sitea.edu.co` → El email verificado

### OPCIÓN C: En NetBeans (Windows o Mac)

1. Ve a **Tools** → **Options**
2. Busca **Java**
3. En la sección de configuración, busca variables de entorno
4. Añade:
   - Nombre: `SENDGRID_API_KEY` / Valor: Tu API Key
   - Nombre: `SENDGRID_FROM_EMAIL` / Valor: El email verificado

### Verificar que funcionó

```bash
echo $SENDGRID_API_KEY
echo $SENDGRID_FROM_EMAIL
```

Deberían mostrar tus valores.

---

## PASO 4: Reiniciar Aplicación 🔄

### Si usas NetBeans
1. Abre NetBeans
2. Click derecho en proyecto → **Clean**
3. Click derecho en proyecto → **Build**
4. Ve a **Services** → Click derecho en **GlassFish** → **Stop**
5. Espera 5 segundos
6. Click derecho en **GlassFish** → **Start**
7. Espera a que inicie (verás "GlassFish 5.0 is running")

### Si usas GlassFish directamente
```bash
# Detener
asadmin stop-domain

# Iniciar
asadmin start-domain
```

---

## PASO 5: Probar 🧪

1. Abre la aplicación: `http://localhost:8080/sitea-1.0-SNAPSHOT/`
2. Realiza un **nuevo registro de estudiante**
3. Completa el formulario de preregistro
4. Al guardar, se debe enviar email al padre

### Esperado en los logs
```
📧 Enviando credenciales desde: noreply@sitea.edu.co
✅ Email de credenciales enviado exitosamente a: [email_padre]
```

### Si recibes error
Revisa `SENDGRID_CONFIGURATION.md` en sección "Troubleshooting".

---

## 📊 Verificación Rápida

Ejecuta este comando para confirmar variables:
```bash
echo "API Key: $SENDGRID_API_KEY" && echo "FROM Email: $SENDGRID_FROM_EMAIL"
```

Debería mostrar:
```
API Key: SG.xxxxxxxxxxxxx
FROM Email: noreply@sitea.edu.co
```

---

## 🎯 Resumen (5 minutos)

| Paso | Tiempo | ¿Qué hacer? |
|------|--------|-----------|
| 1 | 5 min | Verificar email en SendGrid |
| 2 | 1 min | Copiar API Key de SendGrid |
| 3 | 1 min | Ejecutar script o configurar variables |
| 4 | 1 min | Reiniciar aplicación |
| 5 | 1 min | Probar con nuevo registro |
| **TOTAL** | **~9 min** | **Sistema funcionando** ✅ |

---

## ❓ Problemas Comunes

### "No recibí email de confirmación de SendGrid"
→ Revisa SPAM, o reintenta crear el Sender

### "Error: SENDGRID_FROM_EMAIL no está configurada"
→ Las variables no se guardaron. Ejecuta el script nuevamente

### "Error 403: from address does not match verified Sender Identity"
→ El email en `SENDGRID_FROM_EMAIL` no está verificado. Verifica el Paso 1

### "Email se envía pero llega a SPAM"
→ Normal en primeras pruebas. Marca como "No es spam" en tu cliente

### "Cambios no se aplican"
→ Reinicia GlassFish (no solo rebuild)

---

## 📖 Documentación Adicional

- `SENDGRID_CONFIGURATION.md` - Documentación técnica completa
- `RESUMEN_SOLUCION_SENDGRID.md` - Resumen de cambios técnicos
- `configure_sendgrid.sh` - Script de configuración automática

---

## ✅ Checklist Final

Marca cuando completes cada paso:

- [ ] Verificué un email en SendGrid (Paso 1)
- [ ] Obtuve la API Key (Paso 2)
- [ ] Configuré las variables de entorno (Paso 3)
- [ ] Reinicié GlassFish/NetBeans (Paso 4)
- [ ] Probé con un nuevo registro (Paso 5)
- [ ] Verifiqué que el email se envió al padre

**Si todo está marcado:** ✅ Sistema en producción

---

**Soporte:**
Si algo no funciona, revisa los logs en NetBeans:
- Window → Show Log → Output
- Busca líneas con "📧" o "❌"

¡Éxito! 🚀
