/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


//<![CDATA[
function cambiarTipoRegistro() {
    const selector = document.getElementById('tipoRegistro');
    const valorSeleccionado = selector.value;
    
    // Buscar el campo oculto generado por JSF
    const form = selector.closest('form');
    const hiddenField = form.querySelector('input[id$="tipoRegistroHidden"]');
    
    // Actualizar el campo oculto para JSF
    if (hiddenField) {
        hiddenField.value = valorSeleccionado;
    }
    
    // Ocultar todos los paneles
    const panelDiagnostico = document.getElementById('panelDiagnostico');
    const panelSospecha = document.getElementById('panelSospecha');
    
    panelDiagnostico.style.display = 'none';
    panelSospecha.style.display = 'none';
    
    // Mostrar el panel correspondiente con animación suave
    if (valorSeleccionado === 'diagnostico') {
        panelDiagnostico.style.display = 'block';
        panelDiagnostico.style.opacity = '0';
        panelDiagnostico.style.transform = 'translateY(-10px)';
        
        setTimeout(() => {
            panelDiagnostico.style.transition = 'all 0.3s ease-in-out';
            panelDiagnostico.style.opacity = '1';
            panelDiagnostico.style.transform = 'translateY(0)';
        }, 10);
        
    } else if (valorSeleccionado === 'sospecha') {
        panelSospecha.style.display = 'block';
        panelSospecha.style.opacity = '0';
        panelSospecha.style.transform = 'translateY(-10px)';
        
        setTimeout(() => {
            panelSospecha.style.transition = 'all 0.3s ease-in-out';
            panelSospecha.style.opacity = '1';
            panelSospecha.style.transform = 'translateY(0)';
        }, 10);
    }
}


// Función para mostrar/ocultar sección de upload (prototipo)
function toggleUploadCertificadoPrototipo() {
    const checkbox = document.querySelector('input[id$="documentacionDisponible"]');
    const uploadSection = document.getElementById('uploadCertificadoPrototipo');
    
    if (checkbox && uploadSection) {
        if (checkbox.checked) {
            uploadSection.style.display = 'block';
            uploadSection.style.animation = 'fadeIn 0.3s ease-in-out';
        } else {
            uploadSection.style.display = 'none';
            limpiarArchivoPrototipo();
        }
    }
}

// Función para simular subida de archivo (prototipo)
function simularSubidaArchivo(input) {
    const archivo = input.files[0];
    const archivoSeleccionadoDiv = document.getElementById('archivoSeleccionadoPrototipo');
    const nombreArchivoSpan = document.getElementById('nombreArchivoSeleccionadoPrototipo');
    
    if (archivo) {
        // Validar tipo de archivo
        if (archivo.type !== 'application/pdf') {
            alert('PROTOTIPO: Por favor seleccione solo archivos PDF (.pdf)');
            input.value = '';
            return false;
        }
        
        // Validar tamaño (5MB max)
        const maxSize = 5 * 1024 * 1024; // 5MB en bytes
        if (archivo.size > maxSize) {
            alert('PROTOTIPO: El archivo es muy grande. El tamaño máximo permitido es 5MB.');
            input.value = '';
            return false;
        }
        
        // Mostrar información del archivo
        const sizeInMB = (archivo.size / (1024 * 1024)).toFixed(2);
        nombreArchivoSpan.textContent = `${archivo.name} (${sizeInMB} MB)`;
        archivoSeleccionadoDiv.style.display = 'block';
        
        console.log('PROTOTIPO: Archivo simulado seleccionado:', archivo.name);
        return true;
    }
    
    return false;
}

// Función para limpiar selección de archivo (prototipo)
function limpiarArchivoPrototipo() {
    const fileInput = document.getElementById('certificadoFilePrototipo');
    const archivoSeleccionadoDiv = document.getElementById('archivoSeleccionadoPrototipo');
    
    if (fileInput) fileInput.value = '';
    if (archivoSeleccionadoDiv) archivoSeleccionadoDiv.style.display = 'none';
    
    console.log('PROTOTIPO: Archivo limpiado');
}




// Función para subir archivo (usando FormData)
function subirArchivoCertificado(archivo) {
    const formData = new FormData();
    formData.append('certificado', archivo);
    
    // Opcional: mostrar barra de progreso
    console.log('Subiendo archivo:', archivo.name);
    
    // Aquí puedes implementar la subida real con fetch/Ajax si es necesario
    // Por ahora solo simularemos que se sube
    setTimeout(() => {
        console.log('Archivo subido exitosamente (simulado)');
    }, 1000);
}



// Recopilar señales de alerta seleccionadas antes del envío
function recopilarSenalesAlerta() {
    const checkboxes = document.querySelectorAll('input[name="senalesAlerta"]:checked');
    const senalesSeleccionadas = Array.from(checkboxes).map(cb => cb.value);
    
    // Opcional: crear un campo oculto con todas las señales seleccionadas
    const form = document.querySelector('form');
    let hiddenSenales = form.querySelector('input[name="senalesSeleccionadas"]');
    
    if (!hiddenSenales) {
        hiddenSenales = document.createElement('input');
        hiddenSenales.type = 'hidden';
        hiddenSenales.name = 'senalesSeleccionadas';
        form.appendChild(hiddenSenales);
    }
    
    hiddenSenales.value = senalesSeleccionadas.join(',');
}

// Restaurar estado al cargar la página (útil para edición)
document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form');
    const hiddenField = form.querySelector('input[id$="tipoRegistroHidden"]');
    
    if (hiddenField && hiddenField.value) {
        document.getElementById('tipoRegistro').value = hiddenField.value;
        cambiarTipoRegistro();
    }
});

// Ejecutar recopilación antes del submit
document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form');
    if (form) {
        form.addEventListener('submit', recopilarSenalesAlerta);
    }
    
// Actualizar el evento DOMContentLoaded para incluir el toggle
document.addEventListener('DOMContentLoaded', function() {
    console.log('DOM cargado, inicializando...');
    
     // Inicializar estado del upload
    const checkbox = document.querySelector('input[id$="documentacionDisponible"]');
    if (checkbox) {
        toggleUploadCertificado();
    }
    
});


// CONTACTOS FAMILIARES












// Validar antes del submit
function validarFormulario() {
    const tipoRegistro = document.getElementById('tipoRegistro').value;
    
    if (!tipoRegistro) {
        alert('Debe seleccionar un tipo de registro');
        return false;
    }
    
    if (tipoRegistro === 'diagnostico') {
        const tipoTEA = document.querySelector('input[id$="tipoTEA"]');
        if (!tipoTEA.value.trim()) {
            alert('Debe especificar el tipo de TEA');
            tipoTEA.focus();
            return false;
        }
    } else if (tipoRegistro === 'sospecha') {
        const checkboxes = document.querySelectorAll('input[name="senalesAlerta"]:checked');
        const senalPersonalizada = document.querySelector('textarea[id$="senalPersonalizada"]');
        
        if (checkboxes.length === 0 && !senalPersonalizada.value.trim()) {
            alert('Debe seleccionar al menos una señal de alerta o describir una personalizada');
            return false;
        }
    }
    
    return true;
}});

//]]>