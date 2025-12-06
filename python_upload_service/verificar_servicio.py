"""
Script simple para verificar que el servicio está funcionando
"""
import requests

print("=" * 60)
print("Verificando servicio Python...")
print("=" * 60)

try:
    # Probar health check
    response = requests.get("http://localhost:5000/health", timeout=3)
    
    if response.status_code == 200:
        data = response.json()
        print("✅ SERVICIO FUNCIONANDO CORRECTAMENTE")
        print(f"   Servicio: {data['service']}")
        print(f"   Versión: {data['version']}")
        print(f"   Estado: {data['status']}")
        print("\n✅ Endpoints disponibles:")
        print("   - GET  http://localhost:5000/health")
        print("   - POST http://localhost:5000/upload")
        print("   - GET  http://localhost:5000/files")
        print("   - DELETE http://localhost:5000/delete/<filename>")
    else:
        print(f"❌ Error: Código de respuesta {response.status_code}")
        print(f"   Respuesta: {response.text}")
        
except requests.exceptions.ConnectionError:
    print("❌ NO SE PUEDE CONECTAR AL SERVICIO")
    print("\n🔧 Soluciones:")
    print("   1. Verifica que el servicio esté corriendo:")
    print("      python app.py")
    print("   2. Verifica que esté en el puerto 5000")
    print("   3. Revisa si hay errores en la consola del servicio")
    
except Exception as e:
    print(f"❌ Error inesperado: {e}")

print("=" * 60)
