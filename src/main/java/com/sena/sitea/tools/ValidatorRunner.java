package com.sena.sitea.tools;

import com.sena.sitea.entities.Rol;
import com.sena.sitea.entities.TipoDocumento;
import com.sena.sitea.entities.Usuarios;
import com.sena.sitea.entities.Estudiante;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Clase de ayuda para reproducir localmente las validaciones de Bean Validation
 * sobre una instancia de `Usuarios` construida de forma similar a
 * `PadreAccuClienteService`.
 */
public class ValidatorRunner {

    public static void main(String[] args) {
        System.out.println("Iniciando ValidatorRunner...");

        // Construir un Estudiante de prueba (sólo los campos usados por el servicio)
        Estudiante est = new Estudiante();
        est.setAcudientePrincipal("Juan Perez");
        est.setCorreoContacto("padre@example.com");
        est.setTelefonoAlternativo("3100000000");
        est.setDireccionEstudiante("Calle Falsa 123");
        est.setExpedienteId("EXP-TEST-001");

        // Recrear la lógica de construcción de Usuarios tal como hace el servicio
        Usuarios u = new Usuarios();
        String[] nombreParts = est.getAcudientePrincipal().trim().split("\\s+");
        String primerNombrePadre = (nombreParts.length > 0 && !nombreParts[0].isEmpty()) ? nombreParts[0] : "Acudiente";
        String primerApellidoPadre = (nombreParts.length > 1 && !nombreParts[1].isEmpty()) ? nombreParts[1] : "Temporal";

        u.setPrimerNombre(primerNombrePadre);
        u.setPrimerApellido(primerApellidoPadre);
        u.setNumeroDocumento("TEMP-PADRE-12345");
        u.setCorreoUsuario(est.getCorreoContacto());
        u.setTelefonoUsuario(est.getTelefonoAlternativo());
        u.setDireccionUsuario(est.getDireccionEstudiante() != null && !est.getDireccionEstudiante().trim().isEmpty() ? est.getDireccionEstudiante() : "No especificada");
        u.setPassword("temporal-hash");
        u.setFechaRegistroIdFechaRegistro(new Date());
        u.setEstatus("ACTIVO");

        // TipoDocumento y Rol: crear objetos mínimos con id y nombre para simular
        TipoDocumento td = new TipoDocumento();
        td.setIdTipoDocumento(1);
        td.setNombreTipoDocumento("Cédula");
        u.setTipoDocumentoIdTipoDocumento(td);

        Rol rol = new Rol();
        rol.setIdRol(3);
        rol.setNombreRol("PADRE DE FAMILIA");
        u.setRolIdRol(rol);

        // Ejecutar validación
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Usuarios>> violations = validator.validate(u);

        if (violations.isEmpty()) {
            System.out.println("No se encontraron violaciones de validación en Usuarios (local).");
        } else {
            System.out.println("Se encontraron violaciones de validación:");
            for (ConstraintViolation<Usuarios> cv : violations) {
                System.out.println(" - Propiedad: " + cv.getPropertyPath() + ", Valor: " + cv.getInvalidValue() + ", Mensaje: " + cv.getMessage());
            }
        }

        // Información adicional: imprimir campos importantes
        System.out.println("Usuario construido: nombre=" + u.getPrimerNombre() + " " + u.getPrimerApellido());
        System.out.println(" numeroDocumento=" + u.getNumeroDocumento());
        System.out.println(" direccion=" + u.getDireccionUsuario());
        System.out.println(" correo=" + u.getCorreoUsuario());
        System.out.println(" rol.id=" + (u.getRolIdRol() != null ? u.getRolIdRol().getIdRol() : null));
        System.out.println(" tipoDoc.id=" + (u.getTipoDocumentoIdTipoDocumento() != null ? u.getTipoDocumentoIdTipoDocumento().getIdTipoDocumento() : null));

        factory.close();
    }
}
