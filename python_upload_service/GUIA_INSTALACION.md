# Guía de Instalación y Uso - Servicio de Carga Python

## 📋 Requisitos Previos

1. **Python 3.8 o superior**
   - Descargar desde: https://www.python.org/downloads/
   - Durante la instalación, marcar "Add Python to PATH"

2. **Proyecto Java SITEA funcionando**
   - El proyecto debe estar corriendo en tu servidor de aplicaciones

## 🚀 Instalación Rápida

### Opción 1: Usando el script automático (Windows)

1. Abre una terminal en la carpeta `python_upload_service`
2. Ejecuta:
```bash
start_service.bat
```

El script automáticamente:
- Verifica que Python esté instalado
- Instala las dependencias necesarias
- Inicia el servicio

### Opción 2: Instalación manual

1. Abre una terminal en la carpeta `python_upload_service`

2. Instala las dependencias:
```bash
pip install -r requirements.txt
```

3. Inicia el servicio:
```bash
python app.py
```

## ✅ Verificación

Si todo está correcto, verás:

```
==================================================
SITEA - Servicio de Carga de Archivos
==================================================
Puerto: 5000
Carpeta de uploads: uploads
Tamaño máximo: 16.0MB
Extensiones permitidas: pdf, doc, docx, xls, xlsx, jpg, jpeg, png, gif, txt, csv, zip, rar
==================================================
 * Running on http://0.0.0.0:5000
```

## 🌐 Acceso a la Interfaz Web

1. Asegúrate de que el servicio Python está corriendo
2. Inicia tu aplicación Java SITEA
3. Accede a: `http://localhost:8080/sitea/views/admin/gestionArchivos.xhtml`
   (Ajusta el puerto y contexto según tu configuración)

## 📁 Estructura de Archivos

```
python_upload_service/
├── app.py                    # Aplicación principal Flask
├── requirements.txt          # Dependencias Python
├── start_service.bat        # Script de inicio automático
├── README.md                # Documentación del servicio
├── GUIA_INSTALACION.md      # Esta guía
└── uploads/                 # Carpeta donde se guardan los archivos
```

## 🔧 Configuración

### Cambiar el puerto

Edita `app.py`, línea final:
```python
app.run(debug=True, host='0.0.0.0', port=5000)  # Cambia 5000 por el puerto deseado
```

### Cambiar tamaño máximo de archivo

Edita `app.py`:
```python
MAX_FILE_SIZE = 16 * 1024 * 1024  # 16MB - ajusta según necesites
```

### Agregar extensiones permitidas

Edita `app.py`:
```python
ALLOWED_EXTENSIONS = {
    'pdf', 'doc', 'docx', 'xls', 'xlsx', 
    'jpg', 'jpeg', 'png', 'gif',
    'txt', 'csv', 'zip', 'rar',
    'mp4', 'avi'  # Agrega las que necesites
}
```

## 🎯 Uso desde la Interfaz Web

1. **Verificar estado del servicio**
   - Al abrir la página, verás un indicador verde si el servicio está activo
   - Si está rojo, haz clic en "Verificar Servicio"

2. **Subir un archivo**
   - Selecciona el módulo correspondiente
   - Opcionalmente agrega una descripción
   - Haz clic en "Seleccionar" y elige tu archivo
   - Haz clic en "Subir"

3. **Ver archivos subidos**
   - La tabla muestra todos los archivos con su información
   - Puedes actualizar la lista con el botón "Actualizar Lista"

4. **Eliminar archivos**
   - Haz clic en el icono de papelera
   - Confirma la eliminación

## 🔍 Solución de Problemas

### El servicio no inicia

**Error: "Python no está instalado"**
- Instala Python desde https://www.python.org/
- Asegúrate de marcar "Add Python to PATH" durante la instalación

**Error: "No module named 'flask'"**
```bash
pip install -r requirements.txt
```

### El indicador está en rojo en la web

1. Verifica que el servicio Python esté corriendo
2. Revisa que esté en el puerto 5000
3. Haz clic en "Verificar Servicio"

### Error al subir archivos

**"Archivo demasiado grande"**
- El límite por defecto es 16MB
- Cambia `MAX_FILE_SIZE` en `app.py` si necesitas más

**"Tipo de archivo no permitido"**
- Verifica que la extensión esté en `ALLOWED_EXTENSIONS`
- Agrega la extensión si es necesaria

### Puerto 5000 ya en uso

Cambia el puerto en `app.py`:
```python
app.run(debug=True, host='0.0.0.0', port=5001)  # Usa otro puerto
```

Y actualiza `UploadServiceClient.java`:
```java
private static final String SERVICE_URL = "http://localhost:5001";
```

## 📊 Endpoints API (para desarrollo)

Si necesitas integrar desde otro lugar:

### Health Check
```
GET http://localhost:5000/health
```

### Subir archivo
```
POST http://localhost:5000/upload
Content-Type: multipart/form-data

Parámetros:
- file: archivo (requerido)
- usuario_id: ID del usuario (opcional)
- modulo: módulo del sistema (opcional)
- descripcion: descripción (opcional)
```

### Listar archivos
```
GET http://localhost:5000/files
```

### Eliminar archivo
```
DELETE http://localhost:5000/delete/<filename>
```

## 🛡️ Seguridad

Para producción, considera:

1. **Autenticación**: Agregar tokens de autenticación
2. **HTTPS**: Usar certificados SSL
3. **Validación**: Validar tipos MIME reales, no solo extensiones
4. **Límites**: Configurar rate limiting
5. **Almacenamiento**: Usar servicios cloud (S3, Azure Blob, etc.)

## 📞 Soporte

Si tienes problemas:
1. Revisa los logs en la consola donde corre el servicio
2. Verifica que ambos servicios (Java y Python) estén corriendo
3. Revisa la consola del navegador (F12) para errores JavaScript
