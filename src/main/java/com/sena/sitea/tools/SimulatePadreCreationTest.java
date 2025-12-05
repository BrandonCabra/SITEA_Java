package com.sena.sitea.tools;

import com.sena.sitea.entities.Estudiante;
import com.sena.sitea.entities.Rol;
import com.sena.sitea.entities.TipoDocumento;
import com.sena.sitea.entities.Usuarios;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Simula exactamente lo que hace PadreAccuClienteService.crearCuentaPadre()
 * para crear un Usuarios a partir de un Estudiante preregistrado.
 */
public class SimulatePadreCreationTest {

    public static void main(String[] args) {
        System.out.println("========== SIMULANDO CREACIÓN DE CUENTA PADRE ==========\n");

        // Simular Estudiante con datos del preregistro
        Estudiante estudiante = new Estudiante();
        estudiante.setAcudientePrincipal("Juan Perez");
        estudiante.setCorreoContacto("padre@example.com");
        estudiante.setTelefonoAlternativo("3100000000");
        estudiante.setDireccionEstudiante("Calle Falsa 123");
        estudiante.setExpedienteId("EXP-TEA-2025-001");
        
        System.out.println("Estudiante (acudiente):");
        System.out.println("  Acudiente: " + estudiante.getAcudientePrincipal());
        System.out.println("  Correo: " + estudiante.getCorreoContacto());
        System.out.println("  Teléfono: " + estudiante.getTelefonoAlternativo());
        System.out.println("  Dirección: " + estudiante.getDireccionEstudiante());

        // Paso 1: Procesar nombre y apellido
        String[] nombreParts = estudiante.getAcudientePrincipal().trim().split("\\s+");
        String primerNombrePadre = (nombreParts.length > 0 && !nombreParts[0].isEmpty()) ? nombreParts[0] : "Acudiente";
        String primerApellidoPadre = (nombreParts.length > 1 && !nombreParts[1].isEmpty()) ? nombreParts[1] : "Temporal";
        System.out.println("\n✓ Nombre procesado: " + primerNombrePadre + " " + primerApellidoPadre);

        // Crear Usuarios (THIS IS WHERE THE FAILURE HAPPENS)
        Usuarios usuarioPadre = new Usuarios();
        
        // NUEVO: Usar método generarDocumentoPadre() CORREGIDO (ahora respeta límite de 20 chars)
        String docTemporal = generarDocumentoCorregido();
        
        // Campos que SÍ se asignan en el servicio actual
        usuarioPadre.setPrimerNombre(primerNombrePadre);
        usuarioPadre.setPrimerApellido(primerApellidoPadre);
        usuarioPadre.setNumeroDocumento(docTemporal);
        usuarioPadre.setCorreoUsuario(estudiante.getCorreoContacto());
        usuarioPadre.setTelefonoUsuario(estudiante.getTelefonoAlternativo());
        usuarioPadre.setDireccionUsuario(
            estudiante.getDireccionEstudiante() != null && !estudiante.getDireccionEstudiante().trim().isEmpty()
                ? estudiante.getDireccionEstudiante() : "No especificada"
        );
        usuarioPadre.setPassword("hashed-password-12345");
        usuarioPadre.setFechaRegistroIdFechaRegistro(new Date());
        usuarioPadre.setEstatus("ACTIVO");
        
        // Asignar TipoDocumento
        TipoDocumento td = new TipoDocumento();
        td.setIdTipoDocumento(1);
        td.setNombreTipoDocumento("CEDULA");
        usuarioPadre.setTipoDocumentoIdTipoDocumento(td);
        
        // Asignar Rol PADRE DE FAMILIA
        Rol rol = new Rol();
        rol.setIdRol(3);
        rol.setNombreRol("PADRE DE FAMILIA");
        usuarioPadre.setRolIdRol(rol);
        
        System.out.println("\n✓ Usuarios construido:");
        System.out.println("  Nombre: " + usuarioPadre.getPrimerNombre() + " " + usuarioPadre.getPrimerApellido());
        System.out.println("  Documento: " + usuarioPadre.getNumeroDocumento());
        System.out.println("  Correo: " + usuarioPadre.getCorreoUsuario());
        System.out.println("  Dirección: " + usuarioPadre.getDireccionUsuario());
        System.out.println("  Password: " + (usuarioPadre.getPassword() != null ? "[HASH SET]" : "[NULL]"));
        System.out.println("  TipoDocumento ID: " + (usuarioPadre.getTipoDocumentoIdTipoDocumento() != null ? usuarioPadre.getTipoDocumentoIdTipoDocumento().getIdTipoDocumento() : "[NULL]"));
        System.out.println("  Rol ID: " + (usuarioPadre.getRolIdRol() != null ? usuarioPadre.getRolIdRol().getIdRol() : "[NULL]"));

        // Paso 3: Validar el Usuarios
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Usuarios>> violations = validator.validate(usuarioPadre);
        
        System.out.println("\n--- Validación de Usuarios (antes de persist) ---");
        if (violations.isEmpty()) {
            System.out.println("✓ Usuarios válido (sin violaciones en local)");
        } else {
            System.out.println("✗ VIOLACIONES ENCONTRADAS:");
            for (ConstraintViolation<Usuarios> cv : violations) {
                System.out.println("  • Campo: " + cv.getPropertyPath());
                System.out.println("    Valor inválido: " + cv.getInvalidValue());
                System.out.println("    Mensaje: " + cv.getMessage());
                System.out.println();
            }
        }
        
        factory.close();

        System.out.println("\n========== FIN DE SIMULACIÓN ==========");
        System.out.println("\nNOTA: Si no hay violaciones aquí pero SÍ las hay en el servidor,");
        System.out.println("puede ser por:");
        System.out.println("  1. Datos reales del formulario diferentes (p.ej. campo nulo en runtime)");
        System.out.println("  2. Validaciones adicionales del contenedor JPA");
        System.out.println("  3. Listeners o validadores registrados en el servidor");
    }
    
    /**
     * Genera un documento temporal para el padre con máximo 20 caracteres.
     * Espeja la corrección en PadreAccuClienteService.
     */
    private static String generarDocumentoCorregido() {
        long ts = System.currentTimeMillis();
        String tsStr = String.valueOf(ts);
        // Tomar últimos 14 caracteres del timestamp para asegurar < 20
        String shortTs = tsStr.length() > 14 ? tsStr.substring(tsStr.length() - 14) : tsStr;
        return "PADRE" + shortTs;  // "PADRE" (5) + 14 = 19 caracteres
    }
}
