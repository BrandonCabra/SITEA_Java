package com.sena.sitea.services;

import com.sena.sitea.entities.Estudiante;
import com.sena.sitea.entities.Rol;
import com.sena.sitea.entities.TipoDocumento;
import com.sena.sitea.entities.Usuarios;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;

/**
 * Servicio para crear automáticamente cuentas de padres/acudientes
 * vinculadas a expedientes de estudiantes TEA.
 */
@Stateless
public class PadreAccuClienteService {

    @PersistenceContext(unitName = "com.sena_sitea_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @EJB
    private PasswordHashService passwordHashService;

    @EJB
    private UsuariosFacadeLocal usuariosFacade;

    /**
     * Crea una cuenta de usuario para el padre/acudiente del estudiante.
     * Retorna: objeto con usuario y contraseña temporal para envío por correo.
     */
    public UsuarioPadreDTO crearCuentaPadre(Estudiante estudiante) {
        try {
            System.out.println("📋 Iniciando creación de cuenta de padre para estudiante: " + estudiante.getExpedienteId());
            
            // PASO 1: Validar datos del estudiante
            validarDatosEstudiante(estudiante);
            System.out.println("  ✓ Datos de estudiante validados");

            // PASO 2: Generar contraseña temporal
            String passwordTemporal = passwordHashService.generateTemporaryPassword();
            String passwordHash = passwordHashService.hashPassword(passwordTemporal);
            System.out.println("  ✓ Contraseña temporal generada");

            // PASO 3: Preparar nombre y apellido
            String[] nombreParts = estudiante.getAcudientePrincipal().trim().split("\\s+");
            String primerNombrePadre = (nombreParts.length > 0 && !nombreParts[0].isEmpty()) 
                ? nombreParts[0] : "Acudiente";
            String primerApellidoPadre = (nombreParts.length > 1 && !nombreParts[1].isEmpty()) 
                ? nombreParts[1] : "Temporal";
            System.out.println("  ✓ Nombre procesado: " + primerNombrePadre + " " + primerApellidoPadre);

            // PASO 4: Obtener TipoDocumento (REQUERIDO)
            TipoDocumento tipoDocumento = obtenerTipoDocumento();
            System.out.println("  ✓ TipoDocumento asignado: " + tipoDocumento.getIdTipoDocumento());

            // PASO 5: Obtener Rol PADRE (REQUERIDO)
            Rol rolPadre = obtenerRolPadre();
            System.out.println("  ✓ Rol asignado: " + rolPadre.getNombreRol() + " (ID: " + rolPadre.getIdRol() + ")");

            // PASO 6: Crear instancia de usuario
            Usuarios usuarioPadre = new Usuarios();
            usuarioPadre.setPrimerNombre(primerNombrePadre);
            usuarioPadre.setPrimerApellido(primerApellidoPadre);
            usuarioPadre.setNumeroDocumento(generarDocumentoPadre());
            usuarioPadre.setCorreoUsuario(estudiante.getCorreoContacto());
            usuarioPadre.setTelefonoUsuario(estudiante.getTelefonoAlternativo());
            usuarioPadre.setDireccionUsuario(
                estudiante.getDireccionEstudiante() != null && !estudiante.getDireccionEstudiante().trim().isEmpty()
                    ? estudiante.getDireccionEstudiante() : "No especificada"
            );
            usuarioPadre.setPassword(passwordHash);
            usuarioPadre.setFechaRegistroIdFechaRegistro(new Date());
            usuarioPadre.setEstatus("ACTIVO");
            usuarioPadre.setTipoDocumentoIdTipoDocumento(tipoDocumento);
            usuarioPadre.setRolIdRol(rolPadre);
            System.out.println("  ✓ Objeto Usuarios construido correctamente");

            // PASO 7: Persistir usuario
            usuariosFacade.create(usuarioPadre);
            System.out.println("  ✓ Usuario persistido con ID: " + usuarioPadre.getIdUsuario());

            // PASO 8: Retornar DTO
            UsuarioPadreDTO resultado = new UsuarioPadreDTO(
                usuarioPadre.getIdUsuario(),
                primerNombrePadre + " " + primerApellidoPadre,
                estudiante.getCorreoContacto(),
                usuarioPadre.getNumeroDocumento(),
                passwordTemporal,
                estudiante.getExpedienteId()
            );
            System.out.println("✅ Cuenta de padre creada exitosamente");
            return resultado;

        } catch (Exception e) {
            System.err.println("❌ Error al crear cuenta de padre/acudiente");
            System.err.println("   Tipo de excepción: " + e.getClass().getName());
            System.err.println("   Mensaje: " + e.getMessage());
            e.printStackTrace();
            
            // Log detallado de validaciones violadas
            Throwable cause = e;
            while (cause != null) {
                if (cause instanceof javax.validation.ConstraintViolationException) {
                    javax.validation.ConstraintViolationException cve = (javax.validation.ConstraintViolationException) cause;
                    System.err.println("\n   ⚠️  CONSTRAINT VIOLATIONS:");
                    for (javax.validation.ConstraintViolation<?> cv : cve.getConstraintViolations()) {
                        System.err.println("      • Campo: " + cv.getPropertyPath());
                        System.err.println("        Valor: " + cv.getInvalidValue());
                        System.err.println("        Mensaje: " + cv.getMessage());
                    }
                    break;
                }
                cause = cause.getCause();
            }
            
            throw new RuntimeException("Error al crear cuenta de padre: " + e.getMessage(), e);
        }
    }

    /**
     * Valida que el estudiante tenga todos los datos necesarios
     */
    private void validarDatosEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo");
        }
        if (estudiante.getAcudientePrincipal() == null || estudiante.getAcudientePrincipal().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del acudiente principal es obligatorio");
        }
        if (estudiante.getCorreoContacto() == null || estudiante.getCorreoContacto().trim().isEmpty()) {
            throw new IllegalArgumentException("El correo de contacto es obligatorio");
        }
    }

    /**
     * Obtiene el TipoDocumento con estrategias de fallback
     */
    private TipoDocumento obtenerTipoDocumento() {
        // Intentar primero con ID=1 (Cédula de Ciudadanía - más probable)
        try {
            TipoDocumento tipoDoc = em.find(TipoDocumento.class, 1);
            if (tipoDoc != null) {
                return tipoDoc;
            }
        } catch (Exception e) {
            System.err.println("  ⚠️  No se encontró TipoDocumento con ID=1");
        }
        
        // Fallback: buscar el primer tipo de documento disponible
        try {
            List<TipoDocumento> tipos = em.createNamedQuery("TipoDocumento.findAll", TipoDocumento.class)
                .setMaxResults(1)
                .getResultList();
            if (!tipos.isEmpty()) {
                return tipos.get(0);
            }
        } catch (Exception e) {
            System.err.println("  ⚠️  Error en findAll de TipoDocumento: " + e.getMessage());
        }
        
        // Si nada funciona, throw error explícito
        throw new RuntimeException("NO HAY TIPOS DE DOCUMENTO en la base de datos. Verifica la tabla tipo_documento");
    }

    /**
     * Obtiene el Rol PADRE con estrategias de fallback
     */
    private Rol obtenerRolPadre() {
        // Intentar primero con ID=3 (PADRE DE FAMILIA - verificado previamente)
        try {
            Rol rolPadre = em.find(Rol.class, 3);
            if (rolPadre != null) {
                return rolPadre;
            }
        } catch (Exception e) {
            System.err.println("  ⚠️  No se encontró Rol con ID=3");
        }
        
        // Fallback 1: buscar por nombre exacto "PADRE DE FAMILIA"
        try {
            List<Rol> roles = em.createNamedQuery("Rol.findByNombreRol", Rol.class)
                .setParameter("nombreRol", "PADRE DE FAMILIA")
                .getResultList();
            if (!roles.isEmpty()) {
                return roles.get(0);
            }
        } catch (Exception e) {
            System.err.println("  ⚠️  Error buscando por nombre 'PADRE DE FAMILIA': " + e.getMessage());
        }
        
        // Fallback 2: buscar por nombre parcial (contiene "PADRE")
        try {
            List<Rol> roles = em.createQuery(
                "SELECT r FROM Rol r WHERE r.nombreRol LIKE :nombre", Rol.class)
                .setParameter("nombre", "%PADRE%")
                .getResultList();
            if (!roles.isEmpty()) {
                return roles.get(0);
            }
        } catch (Exception e) {
            System.err.println("  ⚠️  Error buscando por nombre parcial '%PADRE%': " + e.getMessage());
        }
        
        // Fallback 3: listar todos los roles disponibles como último recurso
        try {
            List<Rol> todosRoles = em.createNamedQuery("Rol.findAll", Rol.class)
                .getResultList();
            System.err.println("  ℹ️  Roles disponibles en BD: " + todosRoles.size());
            for (Rol r : todosRoles) {
                System.err.println("     • ID=" + r.getIdRol() + ", Nombre=" + r.getNombreRol());
            }
            // Retornar el primero que no sea ADMINISTRADOR o ESTUDIANTE
            for (Rol r : todosRoles) {
                if (r.getNombreRol() != null && 
                    !r.getNombreRol().equalsIgnoreCase("ADMINISTRADOR") &&
                    !r.getNombreRol().equalsIgnoreCase("ESTUDIANTE")) {
                    return r;
                }
            }
        } catch (Exception e) {
            System.err.println("  ⚠️  Error listando todos los roles: " + e.getMessage());
        }
        
        // Si nada funciona, throw error explícito
        throw new RuntimeException("NO SE ENCUENTRA ROL PADRE. Roles disponibles: 1=ADMINISTRADOR, 2=PROFESOR, 3=PSICOORIENTADOR, 4=ESTUDIANTE, 5=PADRE");
    }

    /**
     * Genera un documento temporal para el padre con máximo 20 caracteres.
     * Usa un hash corto del timestamp para mantener unicidad dentro del límite.
     * Formato: PADRE + últimos 14 caracteres del timestamp = 19 caracteres máx.
     */
    private String generarDocumentoPadre() {
        long ts = System.currentTimeMillis();
        String tsStr = String.valueOf(ts);
        // Tomar últimos 14 caracteres del timestamp para asegurar < 20
        String shortTs = tsStr.length() > 14 ? tsStr.substring(tsStr.length() - 14) : tsStr;
        return "PADRE" + shortTs;  // "PADRE" (5) + 14 = 19 caracteres
    }

    /**
     * DTO con información del usuario padre para envío de correo.
     */
    public static class UsuarioPadreDTO {
        public Integer idUsuario;
        public String nombreCompleto;
        public String correo;
        public String numeroDocumento;
        public String passwordTemporal;
        public String expedienteEstudiante;

        public UsuarioPadreDTO(Integer idUsuario, String nombreCompleto, String correo,
                               String numeroDocumento, String passwordTemporal, String expediente) {
            this.idUsuario = idUsuario;
            this.nombreCompleto = nombreCompleto;
            this.correo = correo;
            this.numeroDocumento = numeroDocumento;
            this.passwordTemporal = passwordTemporal;
            this.expedienteEstudiante = expediente;
        }

        public Integer getIdUsuario() { return idUsuario; }
        public String getNombreCompleto() { return nombreCompleto; }
        public String getCorreo() { return correo; }
        public String getNumeroDocumento() { return numeroDocumento; }
        public String getPasswordTemporal() { return passwordTemporal; }
        public String getExpedienteEstudiante() { return expedienteEstudiante; }
    }
}
