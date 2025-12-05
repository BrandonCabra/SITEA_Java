package com.sena.sitea.tools;

import com.sena.sitea.entities.Curso;
import com.sena.sitea.entities.Estudiante;
import com.sena.sitea.entities.TipoDocumento;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Simula la creación de un estudiante tal como lo hace el formulario de preregistro.
 */
public class SimulatePreregistroTest {

    public static void main(String[] args) {
        System.out.println("========== SIMULANDO PREREGISTRO DE ESTUDIANTE ==========\n");

        // 1. Crear Estudiante con datos del formulario
        Estudiante estudiante = new Estudiante();
        
        // Datos que SIEMPRE vienen del formulario de preregistro (crearestudiantetea.xhtml)
        estudiante.setNumeroDocumentoEstudiante("9876543210");
        estudiante.setPrimerNombreEstudiante("JUAN");
        estudiante.setSegundoNombreEstudiante("PABLO");
        estudiante.setPrimerApellidoEstudiante("MURILLO");
        estudiante.setSegundoApellidoEstudiante("GARCIA");
        estudiante.setFechaNacimiento(new Date("1/1/2010"));  // ejemplo
        estudiante.setDireccionEstudiante("calle 123");
        estudiante.setCorreoInstitucionalEstudiante("test@itirr.edu.co");
        
        // Datos opcionales del acudiente (usados por PadreAccuClienteService)
        estudiante.setAcudientePrincipal("Maria Garcia");
        estudiante.setCorreoContacto("maria@gmail.com");
        estudiante.setTelefonoAlternativo("3158102820");
        
        // Tipo de documento y curso (relaciones obligatorias)
        TipoDocumento td = new TipoDocumento();
        td.setIdTipoDocumento(4);  // tipo documento ID = 4 (de la BD)
        estudiante.setTipoDocumentoIdTipoDocumento(td);
        
        Curso curso = new Curso();
        curso.setIdCurso(11);  // curso ID = 11
        estudiante.setCursoIdCurso(curso);
        
        // Otros campos obligatorios que el controlador asigna
        estudiante.setFechaRegistro(new Date());
        estudiante.setCreatedAt(new Date());
        estudiante.setUpdatedAt(new Date());
        estudiante.setEstadoRegistro("ACTIVO");
        estudiante.setExpedienteId("EXP-TEA-2025-TEST");
        
        System.out.println("Estudiante construido:");
        System.out.println("  Nombre: " + estudiante.getPrimerNombreEstudiante() + " " + estudiante.getPrimerApellidoEstudiante());
        System.out.println("  Documento: " + estudiante.getNumeroDocumentoEstudiante());
        System.out.println("  Correo de contacto: " + estudiante.getCorreoContacto());
        System.out.println("  Acudiente: " + estudiante.getAcudientePrincipal());
        
        // 2. Validar la entidad Estudiante
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Estudiante>> violations = validator.validate(estudiante);
        
        System.out.println("\n--- Validación de Estudiante ---");
        if (violations.isEmpty()) {
            System.out.println("✓ Estudiante válido (sin violaciones)");
        } else {
            System.out.println("✗ Violaciones encontradas en Estudiante:");
            for (ConstraintViolation<Estudiante> cv : violations) {
                System.out.println("  • Campo: " + cv.getPropertyPath() + ", Mensaje: " + cv.getMessage());
            }
        }
        
        factory.close();
        
        System.out.println("\n========== FIN DE SIMULACIÓN ==========");
    }
}
