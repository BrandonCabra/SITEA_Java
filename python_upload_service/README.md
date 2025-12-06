# Servicio de Carga de Archivos Python - SITEA

Microservicio Python Flask para manejar la carga de archivos en el proyecto SITEA.

## Instalación

1. Instalar Python 3.8 o superior
2. Instalar dependencias:
```bash
pip install -r requirements.txt
```

## Ejecución

```bash
python app.py
```

El servicio estará disponible en: `http://localhost:5000`

## Endpoints Disponibles

### 1. Health Check
```
GET /health
```
Verifica que el servicio está activo.

### 2. Subir Archivo Individual
```
POST /upload
Content-Type: multipart/form-data

Parámetros:
- file: archivo a subir (requerido)
- usuario_id: ID del usuario (opcional)
- modulo: módulo del sistema (opcional)
- descripcion: descripción del archivo (opcional)
```

### 3. Subir Múltiples Archivos
```
POST /upload/multiple
Content-Type: multipart/form-data

Parámetros:
- files: array de archivos (requerido)
```

### 4. Listar Archivos
```
GET /files
```

### 5. Eliminar Archivo
```
DELETE /delete/<filename>
```

## Configuración

- **Puerto**: 5000
- **Tamaño máximo**: 16MB
- **Extensiones permitidas**: pdf, doc, docx, xls, xlsx, jpg, jpeg, png, gif, txt, csv, zip, rar
- **Carpeta de uploads**: `uploads/`

## Integración con Java

Ver archivo `UploadServiceClient.java` para el cliente Java que consume este servicio.
