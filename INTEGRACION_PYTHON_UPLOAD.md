# 🐍 Integración Python - Sistema de Carga de Archivos SITEA

## 📌 Resumen Ejecutivo

Se ha implementado un **microservicio Python Flask** para manejar la carga de archivos en el proyecto SITEA, integrado completamente con la aplicación Java/JSF existente.

### ✨ Características Principales

- ✅ Carga de archivos individual y múltiple
- ✅ Validación de tipos y tamaños
- ✅ Gestión completa (listar, eliminar)
- ✅ Integración transparente con Java
- ✅ Interfaz web moderna con PrimeFaces
- ✅ API REST completa
- ✅ Manejo robusto de errores

## 🏗️ Arquitectura

```
┌─────────────────────────────────────────────────────────┐
│                   NAVEGADOR WEB                         │
│              (gestionArchivos.xhtml)                    │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│              APLICACIÓN JAVA (JSF)                      │
│  ┌──────────────────────────────────────────────────┐  │
│  │  UploadController.java                           │  │
│  │  (Managed Bean)                                  │  │
│  └──────────────────┬───────────────────────────────┘  │
│                     │                                   │
│  ┌──────────────────▼───────────────────────────────┐  │
│  │  UploadServiceClient.java                        │  │
│  │  (Cliente HTTP)                                  │  │
│  └──────────────────┬───────────────────────────────┘  │
└─────────────────────┼───────────────────────────────────┘
                      │ HTTP REST
                      ▼
┌─────────────────────────────────────────────────────────┐
│         MICROSERVICIO PYTHON (Flask)                    │
│  ┌──────────────────────────────────────────────────┐  │
│  │  app.py                                          │  │
│  │  - POST /upload                                  │  │
│  │  - POST /upload/multiple                         │  │
│  │  - GET /files                                    │  │
│  │  - DELETE /delete/<filename>                     │  │
│  │  - GET /health                                   │  │
│  └──────────────────┬───────────────────────────────┘  │
└─────────────────────┼───────────────────────────────────┘
                      │
                      ▼
              ┌───────────────┐
              │  uploads/     │
              │  (Archivos)   │
              └───────────────┘
```

## 📂 Archivos Creados

### Python (Microservicio)
```
python_upload_service/
├── app.py                      # Aplicación Flask principal
├── requirements.txt            # Dependencias Python
├── start_service.bat          # Script de inicio automático
├── test_service.py            # Script de pruebas
├── README.md                  # Documentación del servicio
├── GUIA_INSTALACION.md        # Guía de instalación
└── uploads/                   # Carpeta de archivos (se crea automáticamente)
```

### Java (Integración)
```
src/main/java/com/sena/sitea/
├── controller/
│   └── UploadController.java          # Controlador JSF
└── util/
    └── UploadServiceClient.java       # Cliente HTTP para Python

src/main/webapp/views/admin/
└── gestionArchivos.xhtml              # Interfaz web
```

## 🚀 Inicio Rápido

### 1. Instalar Python
- Descargar desde: https://www.python.org/downloads/
- Versión mínima: Python 3.8
- ⚠️ Importante: Marcar "Add Python to PATH" durante instalación

### 2. Iniciar el Servicio Python

**Opción A - Automático (Recomendado):**
```bash
cd python_upload_service
start_service.bat
```

**Opción B - Manual:**
```bash
cd python_upload_service
pip install -r requirements.txt
python app.py
```

### 3. Verificar el Servicio
```bash
python test_service.py
```

### 4. Acceder a la Interfaz Web
1. Inicia tu aplicación Java SITEA
2. Navega a: `http://localhost:8080/sitea/views/admin/gestionArchivos.xhtml`

## 🎯 Uso de la Interfaz

### Subir Archivos

1. **Seleccionar módulo**: Elige el módulo correspondiente (Caracterización, PIAR, etc.)
2. **Agregar descripción** (opcional): Describe el contenido del archivo
3. **Seleccionar archivo**: Haz clic en "Seleccionar" y elige tu archivo
4. **Subir**: Haz clic en "Subir"

### Ver Archivos

- La tabla muestra todos los archivos subidos
- Información mostrada: nombre, tamaño, tipo, fecha
- Paginación automática (10 archivos por página)

### Eliminar Archivos

1. Haz clic en el icono de papelera (🗑️)
2. Confirma la eliminación
3. El archivo se elimina permanentemente

## 🔧 Configuración

### Extensiones Permitidas (por defecto)
```
pdf, doc, docx, xls, xlsx, jpg, jpeg, png, gif, txt, csv, zip, rar
```

Para agregar más, edita `app.py`:
```python
ALLOWED_EXTENSIONS = {
    'pdf', 'doc', 'docx', 'xls', 'xlsx', 
    'jpg', 'jpeg', 'png', 'gif',
    'txt', 'csv', 'zip', 'rar',
    'mp4', 'avi'  # Agrega aquí
}
```

### Tamaño Máximo (por defecto: 16MB)

Edita `app.py`:
```python
MAX_FILE_SIZE = 32 * 1024 * 1024  # 32MB
```

### Puerto del Servicio (por defecto: 5000)

Edita `app.py`:
```python
app.run(debug=True, host='0.0.0.0', port=5001)
```

Y actualiza `UploadServiceClient.java`:
```java
private static final String SERVICE_URL = "http://localhost:5001";
```

## 📡 API REST

### Health Check
```http
GET /health
```
**Respuesta:**
```json
{
  "status": "ok",
  "service": "SITEA Upload Service",
  "version": "1.0.0"
}
```

### Subir Archivo
```http
POST /upload
Content-Type: multipart/form-data

Parámetros:
- file: archivo (requerido)
- usuario_id: ID del usuario (opcional)
- modulo: módulo del sistema (opcional)
- descripcion: descripción (opcional)
```

**Respuesta exitosa:**
```json
{
  "success": true,
  "message": "Archivo subido exitosamente",
  "data": {
    "filename": "documento_abc123.pdf",
    "original_filename": "documento.pdf",
    "size": 1024000,
    "mime_type": "application/pdf",
    "created": "2024-12-06T10:30:00"
  }
}
```

### Listar Archivos
```http
GET /files
```

**Respuesta:**
```json
{
  "success": true,
  "count": 5,
  "data": [
    {
      "filename": "documento_abc123.pdf",
      "size": 1024000,
      "created": "2024-12-06T10:30:00",
      "mime_type": "application/pdf"
    }
  ]
}
```

### Eliminar Archivo
```http
DELETE /delete/<filename>
```

**Respuesta:**
```json
{
  "success": true,
  "message": "Archivo documento_abc123.pdf eliminado exitosamente"
}
```

## 🔍 Solución de Problemas

### Problema: Indicador rojo "Servicio No Disponible"

**Solución:**
1. Verifica que el servicio Python esté corriendo
2. Abre una terminal y ejecuta:
   ```bash
   cd python_upload_service
   python app.py
   ```
3. Haz clic en "Verificar Servicio" en la interfaz web

### Problema: "Python no está instalado"

**Solución:**
1. Descarga Python desde https://www.python.org/
2. Durante la instalación, marca "Add Python to PATH"
3. Reinicia la terminal
4. Verifica: `python --version`

### Problema: "No module named 'flask'"

**Solución:**
```bash
cd python_upload_service
pip install -r requirements.txt
```

### Problema: Puerto 5000 ya en uso

**Solución:**
1. Cambia el puerto en `app.py` (línea final)
2. Actualiza `UploadServiceClient.java` con el nuevo puerto
3. Recompila el proyecto Java

### Problema: Archivo demasiado grande

**Solución:**
- Aumenta `MAX_FILE_SIZE` en `app.py`
- Por defecto es 16MB

## 🎨 Personalización de la Vista

La vista está en: `src/main/webapp/views/admin/gestionArchivos.xhtml`

### Cambiar módulos disponibles

Edita el `<p:selectOneMenu>`:
```xml
<p:selectOneMenu id="modulo" value="#{uploadController.modulo}">
    <f:selectItem itemLabel="Mi Módulo" itemValue="mi_modulo" />
    <!-- Agrega más aquí -->
</p:selectOneMenu>
```

### Cambiar estilos

Los estilos CSS están en el `<style>` del archivo XHTML. Puedes modificarlos según tu diseño.

## 🔐 Consideraciones de Seguridad

### Para Desarrollo (Actual)
✅ Validación de extensiones
✅ Límite de tamaño
✅ Nombres de archivo seguros
✅ CORS habilitado

### Para Producción (Recomendado)
- [ ] Implementar autenticación (JWT, OAuth)
- [ ] Usar HTTPS
- [ ] Validar tipos MIME reales
- [ ] Implementar rate limiting
- [ ] Escaneo de virus
- [ ] Almacenamiento en cloud (S3, Azure Blob)
- [ ] Logs de auditoría
- [ ] Backup automático

## 📊 Ventajas de esta Solución

1. **Separación de responsabilidades**: Python maneja archivos, Java maneja lógica de negocio
2. **Escalabilidad**: El servicio Python puede correr en otro servidor
3. **Flexibilidad**: Fácil agregar nuevas funcionalidades en Python
4. **Mantenibilidad**: Código Python simple y claro
5. **Integración transparente**: El usuario no nota que hay dos tecnologías

## 🚀 Próximos Pasos Sugeridos

1. **Integrar con base de datos**: Guardar metadata de archivos en tu BD
2. **Agregar autenticación**: Validar usuarios antes de subir
3. **Implementar categorías**: Organizar archivos por tipo/módulo
4. **Agregar preview**: Vista previa de imágenes y PDFs
5. **Notificaciones**: Alertas cuando se suben archivos importantes
6. **Compresión**: Comprimir archivos grandes automáticamente
7. **Versionado**: Mantener versiones de archivos

## 📞 Testing

### Prueba Manual
1. Inicia el servicio Python
2. Ejecuta: `python test_service.py`
3. Verifica que todas las pruebas pasen

### Prueba desde la Web
1. Accede a la interfaz
2. Sube un archivo de prueba
3. Verifica que aparezca en la lista
4. Elimínalo

## 📝 Notas Importantes

- El servicio Python debe estar corriendo para que funcione la carga
- Los archivos se guardan en `python_upload_service/uploads/`
- El servicio usa el puerto 5000 por defecto
- La aplicación Java se comunica vía HTTP REST
- No se requieren cambios en la base de datos actual

## ✅ Checklist de Implementación

- [x] Servicio Python Flask creado
- [x] Cliente Java implementado
- [x] Controlador JSF creado
- [x] Vista web diseñada
- [x] Scripts de inicio creados
- [x] Documentación completa
- [x] Script de pruebas incluido
- [ ] Desplegar en producción
- [ ] Configurar backups
- [ ] Implementar seguridad adicional

---

**¡El sistema está listo para usar!** 🎉

Para cualquier duda, revisa la documentación en `python_upload_service/GUIA_INSTALACION.md`
