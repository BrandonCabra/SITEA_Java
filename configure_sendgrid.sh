#!/bin/bash

# ============================================
# Script de Configuración SendGrid - SITEA
# ============================================

echo "=========================================="
echo "  📧 Configurador SendGrid para SITEA"
echo "=========================================="
echo ""

# Verificar si las variables ya están configuradas
echo "🔍 Verificando configuración actual..."
echo ""

if [ -n "$SENDGRID_API_KEY" ]; then
    echo "✅ SENDGRID_API_KEY ya está configurada"
else
    echo "❌ SENDGRID_API_KEY no está configurada"
fi

if [ -n "$SENDGRID_FROM_EMAIL" ]; then
    echo "✅ SENDGRID_FROM_EMAIL ya está configurada como: $SENDGRID_FROM_EMAIL"
else
    echo "❌ SENDGRID_FROM_EMAIL no está configurada"
fi

echo ""
echo "=========================================="
echo "  ⚙️  Configuración Interactiva"
echo "=========================================="
echo ""

# Solicitar API Key
echo "Ingresa tu SendGrid API Key:"
echo "(Obtén la clave en: https://app.sendgrid.com/settings/api_keys)"
read -p "SENDGRID_API_KEY: " api_key

# Solicitar FROM Email
echo ""
echo "Ingresa el email verificado en SendGrid:"
echo "(Ej: noreply@sitea.edu.co, admin@sitea.edu.co)"
read -p "SENDGRID_FROM_EMAIL: " from_email

# Validar que no están vacíos
if [ -z "$api_key" ] || [ -z "$from_email" ]; then
    echo "❌ Error: Debes proporcionar ambos valores"
    exit 1
fi

# Proponer guardar en bashrc
echo ""
echo "=========================================="
echo "  💾 Guardar Configuración"
echo "=========================================="
echo ""

read -p "¿Deseas guardar estas variables en ~/.bashrc? (s/n): " save_choice

if [ "$save_choice" = "s" ] || [ "$save_choice" = "S" ]; then
    echo ""
    echo "export SENDGRID_API_KEY=\"$api_key\"" >> ~/.bashrc
    echo "export SENDGRID_FROM_EMAIL=\"$from_email\"" >> ~/.bashrc
    echo "✅ Variables guardadas en ~/.bashrc"
    echo ""
    echo "📝 Para aplicar los cambios, ejecuta:"
    echo "   source ~/.bashrc"
    echo ""
else
    echo "⚠️  Las variables no fueron guardadas."
    echo "   Deberás configurarlas manualmente en NetBeans o GlassFish."
fi

# Mostrar las variables configuradas
export SENDGRID_API_KEY="$api_key"
export SENDGRID_FROM_EMAIL="$from_email"

echo "=========================================="
echo "  ✅ Configuración Completada"
echo "=========================================="
echo ""
echo "Variables activas en esta sesión:"
echo "  SENDGRID_API_KEY: ${SENDGRID_API_KEY:0:10}...${SENDGRID_API_KEY: -5}"
echo "  SENDGRID_FROM_EMAIL: $SENDGRID_FROM_EMAIL"
echo ""
echo "Próximos pasos:"
echo "  1. Inicia NetBeans o reinicia GlassFish"
echo "  2. Realiza un nuevo registro de estudiante"
echo "  3. Las credenciales del padre se enviarán por email"
echo ""
echo "Para más información:"
echo "  📖 Lee: SENDGRID_CONFIGURATION.md"
echo ""
