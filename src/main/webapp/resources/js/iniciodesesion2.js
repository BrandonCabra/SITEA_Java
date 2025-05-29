/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.getElementById("inicioSesionForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevenir la recarga de la página

    // Obtener los valores del formulario
    const tipoDocumento = document.getElementById("tipoDocumento").value;
    const numeroIdentificacion = document.getElementById("numeroIdentificacion").value;
    const contrasena = document.getElementById("contrasena").value;

    // Validar que el campo de número de identificación solo contenga números
    if (!/^\d+$/.test(numeroIdentificacion)) {
        alert("El número de identificación solo debe contener números.");
        return;
    }

    // Obtener registros guardados en el almacenamiento local
    const registros = JSON.parse(localStorage.getItem("registros")) || [];

    // Verificar las credenciales
    const usuarioValido = registros.find(registro => 
        registro.tipoDocumento === tipoDocumento && 
        registro.numeroDocumento === numeroIdentificacion && 
        registro.contrasena === contrasena
    );

    if (usuarioValido) {
        alert("Inicio de sesión exitoso");
        // Redirigir a una página de bienvenida
        window.location.href = "Modulos.html";
    } else {
        alert("Credenciales incorrectas. Por favor, intente nuevamente.");
    }
});

document.getElementById("numeroIdentificacion").addEventListener("input", function(event) {
    this.value = this.value.replace(/\D/g, ''); // Eliminar cualquier carácter no numérico
});

function validarFormulario() {
    let documento = document.getElementById("NUMERO_DOCUMENTO").value.trim();
    let password = document.getElementById("PASSWORD").value.trim();

    if (documento === "" || password === "") {
        alert("Todos los campos son obligatorios.");
        return false;
    }

    if (password.length < 6) {
        alert("La contraseña debe tener al menos 6 caracteres.");
        return false;
    }

    document.getElementById("loginForm").submit();
}
