"""
Microservicio Python para carga de archivos - SITEA
Integración con proyecto Java/JSF
"""
from flask import Flask, request, jsonify
from flask_cors import CORS
from werkzeug.utils import secure_filename
import os
import uuid
import mimetypes
from datetime import datetime
import json

app = Flask(__name__)
CORS(app)  # Permitir peticiones desde el frontend Java

# Configuración
UPLOAD_FOLDER = 'uploads'
MAX_FILE_SIZE = 16 * 1024 * 1024  # 16MB
ALLOWED_EXTENSIONS = {
    'pdf', 'doc', 'docx', 'xls', 'xlsx', 
    'jpg', 'jpeg', 'png', 'gif',
    'txt', 'csv', 'zip', 'rar'
}

app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
app.config['MAX_CONTENT_LENGTH'] = MAX_FILE_SIZE

# Crear carpeta de uploads si no existe
os.makedirs(UPLOAD_FOLDER, exist_ok=True)

def allowed_file(filename):
    """Verifica si la extensión del archivo es permitida"""
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

def get_file_info(filepath):
    """Obtiene información del archivo"""
    stats = os.stat(filepath)
    return {
        'size': stats.st_size,
        'created': datetime.fromtimestamp(stats.st_ctime).isoformat(),
        'mime_type': mimetypes.guess_type(filepath)[0]
    }

@app.route('/health', methods=['GET'])
def health_check():
    """Endpoint para verificar que el servicio está activo"""
    return jsonify({
        'status': 'ok',
        'service': 'SITEA Upload Service',
        'version': '1.0.0'
    })

@app.route('/upload', methods=['POST'])
def upload_file():
    """Endpoint principal para subir archivos"""
    try:
        # Verificar que se envió un archivo
        if 'file' not in request.files:
            return jsonify({
                'success': False,
                'error': 'No se envió ningún archivo'
            }), 400
        
        file = request.files['file']
        
        # Verificar que el archivo tiene nombre
        if file.filename == '':
            return jsonify({
                'success': False,
                'error': 'Archivo sin nombre'
            }), 400
        
        # Verificar extensión permitida
        if not allowed_file(file.filename):
            return jsonify({
                'success': False,
                'error': f'Tipo de archivo no permitido. Extensiones válidas: {", ".join(ALLOWED_EXTENSIONS)}'
            }), 400
        
        # Generar nombre único para el archivo
        filename = secure_filename(file.filename)
        unique_id = str(uuid.uuid4())[:8]
        name, ext = os.path.splitext(filename)
        unique_filename = f"{name}_{unique_id}{ext}"
        
        # Guardar archivo
        filepath = os.path.join(app.config['UPLOAD_FOLDER'], unique_filename)
        file.save(filepath)
        
        # Obtener información del archivo
        file_info = get_file_info(filepath)
        
        # Metadata adicional del request
        metadata = {
            'usuario_id': request.form.get('usuario_id', 'unknown'),
            'modulo': request.form.get('modulo', 'general'),
            'descripcion': request.form.get('descripcion', '')
        }
        
        return jsonify({
            'success': True,
            'message': 'Archivo subido exitosamente',
            'data': {
                'filename': unique_filename,
                'original_filename': filename,
                'path': filepath,
                'size': file_info['size'],
                'mime_type': file_info['mime_type'],
                'created': file_info['created'],
                'metadata': metadata
            }
        }), 200
        
    except Exception as e:
        return jsonify({
            'success': False,
            'error': f'Error al subir archivo: {str(e)}'
        }), 500

@app.route('/upload/multiple', methods=['POST'])
def upload_multiple_files():
    """Endpoint para subir múltiples archivos"""
    try:
        files = request.files.getlist('files')
        
        if not files:
            return jsonify({
                'success': False,
                'error': 'No se enviaron archivos'
            }), 400
        
        uploaded_files = []
        errors = []
        
        for file in files:
            if file.filename == '':
                continue
                
            if not allowed_file(file.filename):
                errors.append(f'{file.filename}: tipo no permitido')
                continue
            
            try:
                filename = secure_filename(file.filename)
                unique_id = str(uuid.uuid4())[:8]
                name, ext = os.path.splitext(filename)
                unique_filename = f"{name}_{unique_id}{ext}"
                
                filepath = os.path.join(app.config['UPLOAD_FOLDER'], unique_filename)
                file.save(filepath)
                
                file_info = get_file_info(filepath)
                
                uploaded_files.append({
                    'filename': unique_filename,
                    'original_filename': filename,
                    'size': file_info['size'],
                    'mime_type': file_info['mime_type']
                })
                
            except Exception as e:
                errors.append(f'{file.filename}: {str(e)}')
        
        return jsonify({
            'success': len(uploaded_files) > 0,
            'message': f'{len(uploaded_files)} archivo(s) subido(s) exitosamente',
            'data': {
                'uploaded': uploaded_files,
                'errors': errors
            }
        }), 200
        
    except Exception as e:
        return jsonify({
            'success': False,
            'error': f'Error al subir archivos: {str(e)}'
        }), 500

@app.route('/files', methods=['GET'])
def list_files():
    """Lista todos los archivos subidos"""
    try:
        files = []
        for filename in os.listdir(app.config['UPLOAD_FOLDER']):
            filepath = os.path.join(app.config['UPLOAD_FOLDER'], filename)
            if os.path.isfile(filepath):
                file_info = get_file_info(filepath)
                files.append({
                    'filename': filename,
                    'size': file_info['size'],
                    'created': file_info['created'],
                    'mime_type': file_info['mime_type']
                })
        
        return jsonify({
            'success': True,
            'data': files,
            'count': len(files)
        }), 200
        
    except Exception as e:
        return jsonify({
            'success': False,
            'error': f'Error al listar archivos: {str(e)}'
        }), 500

@app.route('/delete/<filename>', methods=['DELETE'])
def delete_file(filename):
    """Elimina un archivo específico"""
    try:
        filepath = os.path.join(app.config['UPLOAD_FOLDER'], secure_filename(filename))
        
        if not os.path.exists(filepath):
            return jsonify({
                'success': False,
                'error': 'Archivo no encontrado'
            }), 404
        
        os.remove(filepath)
        
        return jsonify({
            'success': True,
            'message': f'Archivo {filename} eliminado exitosamente'
        }), 200
        
    except Exception as e:
        return jsonify({
            'success': False,
            'error': f'Error al eliminar archivo: {str(e)}'
        }), 500

@app.errorhandler(413)
def request_entity_too_large(error):
    """Maneja archivos demasiado grandes"""
    return jsonify({
        'success': False,
        'error': f'Archivo demasiado grande. Tamaño máximo: {MAX_FILE_SIZE / (1024*1024)}MB'
    }), 413

if __name__ == '__main__':
    print("=" * 50)
    print("SITEA - Servicio de Carga de Archivos")
    print("=" * 50)
    print(f"Puerto: 5000")
    print(f"Carpeta de uploads: {UPLOAD_FOLDER}")
    print(f"Tamaño máximo: {MAX_FILE_SIZE / (1024*1024)}MB")
    print(f"Extensiones permitidas: {', '.join(ALLOWED_EXTENSIONS)}")
    print("=" * 50)
    
    # Modo desarrollo (con warning)
    # app.run(debug=True, host='0.0.0.0', port=5000)
    
    # Modo producción (sin warning) - Descomenta para usar
    from waitress import serve
    print("Servidor iniciado en http://localhost:5000")
    print("Presiona Ctrl+C para detener")
    serve(app, host='0.0.0.0', port=50
