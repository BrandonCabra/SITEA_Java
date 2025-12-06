"""
Script de prueba para verificar el servicio de carga de archivos
"""
import requests
import os

SERVICE_URL = "http://localhost:5000"

def test_health():
    """Prueba el endpoint de health check"""
    print("🔍 Probando health check...")
    try:
        response = requests.get(f"{SERVICE_URL}/health", timeout=3)
        if response.status_code == 200:
            data = response.json()
            print(f"✅ Servicio activo: {data['service']} v{data['version']}")
            return True
        else:
            print(f"❌ Error: código {response.status_code}")
            return False
    except Exception as e:
        print(f"❌ No se pudo conectar: {e}")
        return False

def test_upload():
    """Prueba subir un archivo de prueba"""
    print("\n📤 Probando carga de archivo...")
    
    # Crear archivo de prueba
    test_file_path = "test_file.txt"
    with open(test_file_path, "w") as f:
        f.write("Este es un archivo de prueba para SITEA\n")
        f.write("Generado automáticamente por test_service.py\n")
    
    try:
        with open(test_file_path, "rb") as f:
            files = {"file": (test_file_path, f)}
            data = {
                "usuario_id": "test_user",
                "modulo": "testing",
                "descripcion": "Archivo de prueba automática"
            }
            
            response = requests.post(f"{SERVICE_URL}/upload", files=files, data=data)
            
            if response.status_code == 200:
                result = response.json()
                if result["success"]:
                    print(f"✅ Archivo subido: {result['data']['filename']}")
                    print(f"   Tamaño: {result['data']['size']} bytes")
                    return True
                else:
                    print(f"❌ Error: {result.get('error', 'Unknown')}")
                    return False
            else:
                print(f"❌ Error HTTP: {response.status_code}")
                return False
                
    except Exception as e:
        print(f"❌ Error: {e}")
        return False
    finally:
        # Limpiar archivo de prueba
        if os.path.exists(test_file_path):
            os.remove(test_file_path)

def test_list_files():
    """Prueba listar archivos"""
    print("\n📋 Probando listado de archivos...")
    try:
        response = requests.get(f"{SERVICE_URL}/files")
        if response.status_code == 200:
            result = response.json()
            if result["success"]:
                count = result["count"]
                print(f"✅ Archivos encontrados: {count}")
                if count > 0:
                    print("\n   Últimos 3 archivos:")
                    for file in result["data"][:3]:
                        size_kb = file["size"] / 1024
                        print(f"   - {file['filename']} ({size_kb:.2f} KB)")
                return True
            else:
                print(f"❌ Error: {result.get('error', 'Unknown')}")
                return False
        else:
            print(f"❌ Error HTTP: {response.status_code}")
            return False
    except Exception as e:
        print(f"❌ Error: {e}")
        return False

def main():
    print("=" * 60)
    print("SITEA - Test del Servicio de Carga de Archivos")
    print("=" * 60)
    
    # Ejecutar pruebas
    tests_passed = 0
    tests_total = 3
    
    if test_health():
        tests_passed += 1
    
    if test_upload():
        tests_passed += 1
    
    if test_list_files():
        tests_passed += 1
    
    # Resumen
    print("\n" + "=" * 60)
    print(f"Resultado: {tests_passed}/{tests_total} pruebas exitosas")
    
    if tests_passed == tests_total:
        print("✅ Todas las pruebas pasaron correctamente")
        print("\n🎉 El servicio está funcionando perfectamente!")
        print("\nPuedes acceder a la interfaz web en:")
        print("http://localhost:8080/sitea/views/admin/gestionArchivos.xhtml")
    else:
        print("⚠️  Algunas pruebas fallaron")
        print("\nVerifica que:")
        print("1. El servicio Python esté corriendo (python app.py)")
        print("2. El puerto 5000 esté disponible")
        print("3. Las dependencias estén instaladas (pip install -r requirements.txt)")
    
    print("=" * 60)

if __name__ == "__main__":
    main()
