@echo off
echo ========================================
echo SITEA - Servicio de Carga de Archivos
echo ========================================
echo.

REM Verificar si Python está instalado
python --version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Python no está instalado o no está en el PATH
    echo Por favor instala Python 3.8 o superior desde https://www.python.org/
    pause
    exit /b 1
)

echo Python detectado correctamente
echo.

REM Verificar si las dependencias están instaladas
echo Verificando dependencias...
pip show Flask >nul 2>&1
if errorlevel 1 (
    echo Instalando dependencias...
    pip install -r requirements.txt
    if errorlevel 1 (
        echo ERROR: No se pudieron instalar las dependencias
        pause
        exit /b 1
    )
) else (
    echo Dependencias ya instaladas
)

echo.
echo Iniciando servicio en http://localhost:5000
echo Presiona Ctrl+C para detener el servicio
echo.

python app.py

pause
