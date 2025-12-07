/**
 * SI TEA - Sistema de Información para Trastorno del Espectro Autista
 * JavaScript Principal
 * 
 * Este archivo contiene funciones JavaScript comunes para todas las páginas
 */

// Inicialización cuando el DOM está listo
document.addEventListener('DOMContentLoaded', function() {
    console.log('SI TEA - Sistema cargado correctamente');
    
    // Inicializar tooltips de Bootstrap si existen
    initializeTooltips();
    
    // Agregar animaciones suaves al scroll
    initializeSmoothScroll();
});

/**
 * Inicializa los tooltips de Bootstrap
 */
function initializeTooltips() {
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
}

/**
 * Inicializa el scroll suave para enlaces internos
 */
function initializeSmoothScroll() {
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            const href = this.getAttribute('href');
            if (href !== '#' && href !== '#!') {
                e.preventDefault();
                const target = document.querySelector(href);
                if (target) {
                    target.scrollIntoView({
                        behavior: 'smooth',
                        block: 'start'
                    });
                }
            }
        });
    });
}

/**
 * Muestra un mensaje de confirmación
 * @param {string} message - Mensaje a mostrar
 * @returns {boolean} - true si el usuario confirma
 */
function confirmarAccion(message) {
    return confirm(message || '¿Está seguro de realizar esta acción?');
}

/**
 * Valida un formulario antes de enviarlo
 * @param {string} formId - ID del formulario
 * @returns {boolean} - true si el formulario es válido
 */
function validarFormulario(formId) {
    const form = document.getElementById(formId);
    if (!form) return false;
    
    // Bootstrap validation
    if (!form.checkValidity()) {
        form.classList.add('was-validated');
        return false;
    }
    
    return true;
}

/**
 * Muestra un mensaje de éxito temporal
 * @param {string} message - Mensaje a mostrar
 */
function mostrarMensajeExito(message) {
    mostrarMensaje(message, 'success');
}

/**
 * Muestra un mensaje de error temporal
 * @param {string} message - Mensaje a mostrar
 */
function mostrarMensajeError(message) {
    mostrarMensaje(message, 'danger');
}

/**
 * Muestra un mensaje temporal en la parte superior de la página
 * @param {string} message - Mensaje a mostrar
 * @param {string} type - Tipo de alerta (success, danger, warning, info)
 */
function mostrarMensaje(message, type) {
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type} alert-dismissible fade show position-fixed top-0 start-50 translate-middle-x mt-3`;
    alertDiv.style.zIndex = '9999';
    alertDiv.innerHTML = `
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;
    
    document.body.appendChild(alertDiv);
    
    // Auto-cerrar después de 5 segundos
    setTimeout(() => {
        alertDiv.remove();
    }, 5000);
}

/**
 * Formatea una fecha en formato DD/MM/YYYY
 * @param {Date} date - Fecha a formatear
 * @returns {string} - Fecha formateada
 */
function formatearFecha(date) {
    if (!(date instanceof Date)) {
        date = new Date(date);
    }
    
    const dia = String(date.getDate()).padStart(2, '0');
    const mes = String(date.getMonth() + 1).padStart(2, '0');
    const anio = date.getFullYear();
    
    return `${dia}/${mes}/${anio}`;
}

/**
 * Valida un email
 * @param {string} email - Email a validar
 * @returns {boolean} - true si el email es válido
 */
function validarEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}

/**
 * Valida un teléfono colombiano
 * @param {string} telefono - Teléfono a validar
 * @returns {boolean} - true si el teléfono es válido
 */
function validarTelefono(telefono) {
    // Formato: 10 dígitos (3XX XXX XXXX)
    const regex = /^3\d{9}$/;
    return regex.test(telefono.replace(/\s/g, ''));
}

/**
 * Capitaliza la primera letra de cada palabra
 * @param {string} str - Texto a capitalizar
 * @returns {string} - Texto capitalizado
 */
function capitalizarTexto(str) {
    return str.replace(/\b\w/g, l => l.toUpperCase());
}

/**
 * Limpia un formulario
 * @param {string} formId - ID del formulario
 */
function limpiarFormulario(formId) {
    const form = document.getElementById(formId);
    if (form) {
        form.reset();
        form.classList.remove('was-validated');
    }
}

/**
 * Deshabilita un botón durante una operación
 * @param {string} buttonId - ID del botón
 * @param {string} textoEspera - Texto a mostrar mientras espera
 */
function deshabilitarBoton(buttonId, textoEspera) {
    const button = document.getElementById(buttonId);
    if (button) {
        button.disabled = true;
        button.dataset.originalText = button.textContent;
        button.innerHTML = `<span class="spinner-border spinner-border-sm me-2"></span>${textoEspera || 'Procesando...'}`;
    }
}

/**
 * Habilita un botón después de una operación
 * @param {string} buttonId - ID del botón
 */
function habilitarBoton(buttonId) {
    const button = document.getElementById(buttonId);
    if (button) {
        button.disabled = false;
        button.textContent = button.dataset.originalText || 'Enviar';
    }
}

// Exportar funciones para uso global
window.SITEA = {
    confirmarAccion,
    validarFormulario,
    mostrarMensajeExito,
    mostrarMensajeError,
    mostrarMensaje,
    formatearFecha,
    validarEmail,
    validarTelefono,
    capitalizarTexto,
    limpiarFormulario,
    deshabilitarBoton,
    habilitarBoton
};
