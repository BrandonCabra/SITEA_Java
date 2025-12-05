# Guía de Integración y Testing - RF-008 Contexto Familiar + Acudiente

## Estado Actual ✅
- **Entidad `ContextoFamiliar`:** Creada (27 campos)
- **Facades `ContextoFamiliar`:** Creadas (Local + Implementación)
- **Vista `contexto_familiar.xhtml`:** Creada (con pre-llenado de datos)
- **Vista `contexto_escolar.xhtml`:** Corregida (EL expression fijo)
- **Validación en `Caracterizacion`:** Corregida (8 campos @NotNull inicializados)
- **Usuario Acudiente:** Implementado (creación + email SendGrid)
- **Compilación:** ✅ BUILD SUCCESS

---

## Paso 1: Ejecutar Migración BD

### Opción A: Desde MariaDB Client
```bash
# Conectarse a la BD
mysql -u root -p

# Usar BD correcta
USE sitea_db;  # o el nombre de tu BD

# Ejecutar script
source /home/brandon/NetBeansProjects/SITEA_Java/MIGRACION_CONTEXTO_FAMILIAR.sql;

# Verificar tablas creadas
SHOW TABLES LIKE '%contexto%';
DESC contexto_familiar;
```

### Opción B: Desde DBeaver/MySQL Workbench
1. Conectar a base de datos
2. Seleccionar BD SITEA
3. File → Open → `/home/brandon/NetBeansProjects/SITEA_Java/MIGRACION_CONTEXTO_FAMILIAR.sql`
4. Execute (Ctrl+Enter o botón ▶)
5. Verificar sin errores

### Opción C: Script desde Terminal
```bash
mysql -u root -p < /home/brandon/NetBeansProjects/SITEA_Java/MIGRACION_CONTEXTO_FAMILIAR.sql
```

### Verificación Post-Migración
```sql
-- Verificar tabla creada
SELECT TABLE_NAME FROM information_schema.TABLES 
WHERE TABLE_SCHEMA = 'sitea_db' AND TABLE_NAME = 'contexto_familiar';

-- Verificar estructura
DESC contexto_familiar;

-- Debe devolver 54 filas (columnas)
-- Campos clave: ID_CONTEXTO_FAMILIAR, CARACTERIZACION_ID, ACUDIENTE_*, MADRE_*, PADRE_*, etc.
```

---

## Paso 2: Configurar SendGrid (si no está ya configurado)

### Opción A: Variables de Entorno Sistema
```bash
# En ~/.bashrc o ~/.bash_profile
export SENDGRID_API_KEY="SG.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
export SENDGRID_FROM_EMAIL="noreply@sitea.edu.co"

# Recargar shell
source ~/.bashrc
```

### Opción B: Variables JVM en GlassFish
1. Abrir `/opt/glassfish7/domains/domain1/config/domain.xml` (o ruta instalación)
2. Buscar sección `<jvm-options>`
3. Agregar:
```xml
<jvm-options>-DSENDGRID_API_KEY=SG.xxxxxxxxxxx</jvm-options>
<jvm-options>-DSENDGRID_FROM_EMAIL=noreply@sitea.edu.co</jvm-options>
```
4. Reiniciar GlassFish

### Verificación SendGrid
```bash
# Ver si variable está configurada
echo $SENDGRID_API_KEY
echo $SENDGRID_FROM_EMAIL

# Debe devolver: SG.xxxxxxx y noreply@sitea.edu.co
```

---

## Paso 3: Compilar y Empaquetar

```bash
cd /home/brandon/NetBeansProjects/SITEA_Java

# Compilar
mvn -DskipTests clean compile

# Empaquetar (WAR)
mvn clean package -DskipTests

# Resultado: target/sitea.war
ls -lh target/sitea.war
```

---

## Paso 4: Desplegar en GlassFish

### Opción A: Desde NetBeans
1. Click derecho en proyecto → Clean and Build
2. Click derecho en proyecto → Deploy
3. GlassFish debería reniciar automáticamente

### Opción B: Comando Manual
```bash
# Copiar WAR a directorio de deployables de GlassFish
cp target/sitea.war /opt/glassfish7/domains/domain1/autodeploy/

# O usar cliente de deploy
/opt/glassfish7/bin/asadmin deploy target/sitea.war
```

### Opción C: Admin Console
1. Ir a http://localhost:4848 (Admin Console)
2. Applications → Deploy
3. Seleccionar `target/sitea.war`
4. Clic en Deploy

### Verificación Deployment
```bash
# Ver logs
tail -f /opt/glassfish7/domains/domain1/logs/server.log

# Buscar errores
grep -i "error\|exception" /opt/glassfish7/domains/domain1/logs/server.log | tail -20
```

---

## Paso 5: Testing Flujo Completo

### Escenario 1: Crear Estudiante con Contexto Familiar

#### Pre-requisitos
- GlassFish ejecutando
- BD migrada
- SendGrid configurado
- Usuario docente autenticado

#### Pasos
1. **Acceder a aplicación:** http://localhost:8080/sitea/
2. **Iniciar sesión** como docente
3. **Ir a Dashboard** → Caracterización
4. **Crear nuevo estudiante** (pre-registro)
   - Nombre: "Juan Pérez García"
   - Documento: "10123456789"
   - Fecha Nacimiento: 2010-05-15
   - Curso: Transición
   - Clic en "Crear Pre-registro"
   
5. **Iniciar Caracterización**
   - Seleccionar estudiante creado
   - Clic en "Iniciar Caracterización"
   
6. **Completar Contexto Escolar**
   - Ver campo "Grado" pre-llenado: "Transición"
   - Completar observaciones
   - Clic en "Guardar y Continuar"
   
7. **Completar Contexto Familiar**
   - **Acudiente Requerido:**
     - Nombre: "María López Ramírez"
     - Documento: "65987321"
     - Teléfono: "3105551234"
     - Email: **test@gmail.com** (importante: email real para recibir el email)
     - Parentesco: "Madre"
   
   - **Madre (Opcional):**
     - Nombre: "María López Ramírez"
     - Ocupación: "Empleada doméstica"
     - Escolaridad: "Primaria"
   
   - **Observaciones:** "Familia nuclear, buen apoyo del acudiente"
   
   - Clic en "Guardar Contexto Familiar"
   
8. **Verificaciones:**
   - ✅ Mensaje: "✓ Acudiente registrado y credenciales enviadas a: test@gmail.com"
   - ✅ Revisar correo (Gmail, Inbox)
   - ✅ Email debe contener:
     - Asunto: "SITEA - Credenciales de Acceso"
     - Usuario (Documento): 65987321
     - Contraseña temporal: [12 caracteres aleatorios]
     - URL de acceso
   - ✅ Verificar en BD que usuario fue creado:
     ```sql
     SELECT * FROM usuarios WHERE numero_documento = '65987321';
     SELECT * FROM contexto_familiar WHERE acudiente_email = 'test@gmail.com';
     ```
   - ✅ Continuar a Dashboard de Dimensiones

---

### Escenario 2: Acudiente Inicia Sesión

#### Pasos
1. **Ir a login:** http://localhost:8080/sitea/login.xhtml
2. **Ingresa credenciales:**
   - Usuario: 65987321
   - Contraseña: [la recibida en email]
3. **Debe mostrar:** Panel de acudiente (pendiente implementación de dashboard acudiente)

---

### Escenario 3: Casos de Error

#### Caso 3.1: Email inválido
- Contexto: Acudiente Email = "correo_invalido"
- Resultado: Mensaje "Email del acudiente inválido: correo_invalido"
- Usuario NO creado

#### Caso 3.2: Email vacío
- Contexto: Acudiente Email = ""
- Resultado: Contexto familiar guardado, acudiente NO registrado
- Mensaje: "Datos incompletos del acudiente"

#### Caso 3.3: SendGrid no configurado
- Contexto: Sin SENDGRID_API_KEY
- Resultado: Usuario creado PERO email NO enviado
- Mensaje: "Acudiente registrado pero no se pudo enviar email... Contraseña temporal: [password]"

---

## Paso 6: Verificación de Logs

### Logs a Buscar

```bash
# En consola de GlassFish o archivo de logs

# Éxito
[SITEA] Nuevo usuario acudiente creado: test@gmail.com | Documento: 65987321 | Usuario ID: 45
[SITEA] Email enviado exitosamente a: test@gmail.com

# Errores comunes
[SITEA ERROR] Error registrando acudiente: [mensaje específico]
[SITEA ERROR] No se pudo enviar email a test@gmail.com: [mensaje]

# SendGrid
Error enviando correos masivos
SENDGRID_API_KEY no está configurada
```

### Dónde Buscar Logs

```bash
# GlassFish server.log
tail -f /opt/glassfish7/domains/domain1/logs/server.log

# Consola de NetBeans (si ejecuta desde IDE)
# Output tab en NetBeans

# Archivo de aplicación (si configura)
cat /var/log/sitea/application.log
```

---

## Paso 7: Checklist de Validación

| Item | ✓ | Notas |
|------|---|-------|
| BD migrada (tabla contexto_familiar existe) | [ ] | Verificar con DESC contexto_familiar |
| SendGrid configurado | [ ] | echo $SENDGRID_API_KEY |
| Aplicación compilada | [ ] | mvn clean compile: BUILD SUCCESS |
| GlassFish iniciado | [ ] | http://localhost:4848 accesible |
| Aplicación desplegada | [ ] | http://localhost:8080/sitea/ accesible |
| Usuario docente autenticado | [ ] | Puede acceder a Dashboard |
| Contexto Escolar funciona | [ ] | Grado pre-llenado correctamente |
| Contexto Familiar se guarda | [ ] | Sin errores de persistencia |
| Acudiente usuario creado | [ ] | Visible en tabla usuarios |
| Email recibido | [ ] | Con credenciales válidas |
| Acudiente puede iniciar sesión | [ ] | Documento + password funciona |

---

## Troubleshooting

### Problema 1: "Cannot find symbol: class ContextoFamiliarFacadeLocal"
**Solución:** Ejecutar `mvn clean compile` nuevamente

### Problema 2: Email no se envía (Rol no encontrado)
**Verificar:**
```sql
SELECT * FROM rol WHERE nombre_rol = 'acudiente';
-- Si está vacío, insertar:
INSERT INTO rol (nombre_rol) VALUES ('acudiente');
-- O usar rol existente (ajustar ID en código si es necesario)
```

### Problema 3: "SENDGRID_API_KEY no está configurada"
**Solución:**
```bash
export SENDGRID_API_KEY="SG.xxxxxxxxxxxxxxxxxxxxxxx"
export SENDGRID_FROM_EMAIL="noreply@sitea.edu.co"

# Reiniciar GlassFish para que tome las variables
```

### Problema 4: "Constraint Violation on Usuarios.primerApellido"
**Causa:** Campo vacío cuando acudiente solo tiene un nombre  
**Verificar:** En código se asigna "SIN_APELLIDO" como fallback (ya está implementado)

### Problema 5: "ELException in contexto_escolar.xhtml"
**Verificar:** Ya debe estar corregido (gradoIdGrado.nivelGrado)  
Si persiste: `mvn clean compile` + redeploy

---

## Configuración Recomendada Post-Deploy

### En Producción

1. **Cambiar email FROM en `EmailService.java`:**
   ```java
   // Línea ~47
   Email from = new Email("noreply@sitea.edu.co");
   ```

2. **Cambiar URL en `registrarUsuarioAcudiente()`:**
   ```java
   // Línea ~1146
   "http://localhost:8080/sitea/" → "https://tu.dominio.com/sitea/"
   ```

3. **Configurar TimeZone para auditoría:**
   ```bash
   # En domain.xml
   <jvm-options>-Duser.timezone=America/Bogota</jvm-options>
   ```

4. **Habilitar logs de auditoría:**
   ```sql
   -- Crear tabla de auditoría (opcional)
   CREATE TABLE audit_acudiente (
       id INT AUTO_INCREMENT PRIMARY KEY,
       usuario_id INT,
       accion VARCHAR(255),
       timestamp DATETIME DEFAULT NOW()
   );
   ```

---

## Próxima Fase: Features Opcionales

- [ ] Dashboard de acudiente (ver información del estudiante)
- [ ] Historial de sesiones del acudiente
- [ ] Notificaciones de evaluaciones
- [ ] Re-envío de credenciales
- [ ] Cambio de contraseña forzado en primer login
- [ ] 2FA (autenticación de dos factores)

---

## Contacto y Soporte

Para errores específicos, ejecutar:
```bash
mvn -e clean compile  # Ver stack trace completo
```

Y buscar en logs:
```bash
grep -i "error\|exception" /opt/glassfish7/domains/domain1/logs/server.log
```

