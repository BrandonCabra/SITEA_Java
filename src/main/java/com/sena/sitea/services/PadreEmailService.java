package com.sena.sitea.services;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.*;
import com.sendgrid.helpers.mail.objects.*;
import java.io.IOException;
import javax.ejb.Stateless;

/**
 * Servicio especializado para envío de credenciales a padres/acudientes
 * utilizando SendGrid API.
 */
@Stateless
public class PadreEmailService {

    /**
     * Envía las credenciales temporales del padre al correo especificado.
     *
     * @param correoDestino Email del padre/acudiente
     * @param nombrePadre Nombre del padre/acudiente
     * @param nombreEstudiante Nombre del estudiante
     * @param expedienteEstudiante Código de expediente del estudiante
     * @param usuarioTemporal Usuario temporal para acceso
     * @param passwordTemporal Contraseña temporal generada
     * @return true si se envió exitosamente, false en caso contrario
     */
    public boolean enviarCredencialesPadre(String correoDestino, String nombrePadre,
            String nombreEstudiante, String expedienteEstudiante,
            String usuarioTemporal, String passwordTemporal) {
        try {
            String apiKey = System.getenv("SENDGRID_API_KEY");
            if (apiKey == null || apiKey.trim().isEmpty()) {
                System.err.println("SENDGRID_API_KEY no está configurada");
                return false;
            }

            // Obtener dirección FROM de variables de entorno
            // IMPORTANTE: Esta dirección DEBE estar verificada en SendGrid
            String fromEmail = System.getenv("SENDGRID_FROM_EMAIL");
            if (fromEmail == null || fromEmail.trim().isEmpty()) {
                System.err.println("❌ SENDGRID_FROM_EMAIL no está configurada");
                System.err.println("   Debes establecer una dirección de correo verificada en SendGrid");
                System.err.println("   Instrucciones:");
                System.err.println("   1. Ve a SendGrid Dashboard > Sender Authentication");
                System.err.println("   2. Verifica un dominio o dirección de correo");
                System.err.println("   3. Configura SENDGRID_FROM_EMAIL con esa dirección");
                return false;
            }

            System.out.println("📧 Enviando credenciales desde: " + fromEmail);

            // Construir contenido HTML del correo
            String htmlContent = construirTemplateHTML(nombrePadre, nombreEstudiante, 
                                                        expedienteEstudiante, usuarioTemporal, 
                                                        passwordTemporal);

            // Configurar email con SendGrid
            Email from = new Email(fromEmail, "SITEA - Plataforma TEA");
            Email to = new Email(correoDestino);
            String subject = "Credenciales de Acceso - SITEA Plataforma de Caracterización";

            Content content = new Content("text/html", htmlContent);
            Mail mail = new Mail(from, subject, to, content);

            SendGrid sg = new SendGrid(apiKey);
            Request request = new Request();

            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            // Verificar que el envío fue exitoso (código 202)
            if (response.getStatusCode() == 202) {
                System.out.println("✅ Email de credenciales enviado exitosamente a: " + correoDestino);
                return true;
            } else {
                System.err.println("❌ Error al enviar email: Status Code " + response.getStatusCode());
                System.err.println("Response: " + response.getBody());
                return false;
            }

        } catch (IOException ex) {
            System.err.println("Error enviando credenciales a padre: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Construye el template HTML para el correo de credenciales.
     */
    private String construirTemplateHTML(String nombrePadre, String nombreEstudiante,
            String expedienteEstudiante, String usuarioTemporal, String passwordTemporal) {
        return "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <style>\n"
                + "        body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }\n"
                + "        .container { max-width: 600px; margin: 0 auto; padding: 20px; background-color: #f9f9f9; }\n"
                + "        .header { background-color: #007bff; color: white; padding: 20px; text-align: center; border-radius: 5px 5px 0 0; }\n"
                + "        .content { background-color: white; padding: 20px; border: 1px solid #ddd; }\n"
                + "        .credentials-box { background-color: #f0f8ff; padding: 15px; border-left: 4px solid #007bff; margin: 15px 0; }\n"
                + "        .credentials-box p { margin: 8px 0; }\n"
                + "        .label { font-weight: bold; color: #555; }\n"
                + "        .value { color: #007bff; font-family: 'Courier New', monospace; font-weight: bold; }\n"
                + "        .footer { background-color: #f0f0f0; padding: 15px; text-align: center; font-size: 12px; color: #666; border-radius: 0 0 5px 5px; }\n"
                + "        .warning { background-color: #fff3cd; padding: 10px; border-left: 4px solid #ffc107; margin: 15px 0; }\n"
                + "        .button { background-color: #007bff; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; display: inline-block; margin-top: 15px; }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"container\">\n"
                + "        <div class=\"header\">\n"
                + "            <h1>¡Bienvenido a SITEA!</h1>\n"
                + "            <p>Plataforma de Caracterización para Estudiantes con TEA/Autismo</p>\n"
                + "        </div>\n"
                + "        <div class=\"content\">\n"
                + "            <h2>Hola " + nombrePadre + ",</h2>\n"
                + "            <p>Se ha creado una cuenta en SITEA para facilitar el seguimiento académico y de caracterización de:</p>\n"
                + "            <p style=\"font-weight: bold; color: #007bff; font-size: 16px;\">" + nombreEstudiante + "</p>\n"
                + "            <p>Expediente: <span class=\"value\">" + expedienteEstudiante + "</span></p>\n"
                + "            \n"
                + "            <div class=\"credentials-box\">\n"
                + "                <p><span class=\"label\">Usuario:</span></p>\n"
                + "                <p class=\"value\">" + usuarioTemporal + "</p>\n"
                + "                \n"
                + "                <p style=\"margin-top: 12px;\"><span class=\"label\">Contraseña Temporal:</span></p>\n"
                + "                <p class=\"value\">" + passwordTemporal + "</p>\n"
                + "            </div>\n"
                + "            \n"
                + "            <div class=\"warning\">\n"
                + "                <strong>⚠️ Importante:</strong>\n"
                + "                <ul style=\"margin: 10px 0; padding-left: 20px;\">\n"
                + "                    <li>Esta es una contraseña temporal. Por seguridad, cámbiala en tu primer acceso.</li>\n"
                + "                    <li>No compartas esta información con terceros.</li>\n"
                + "                    <li>Guarda este correo en un lugar seguro para futuras referencias.</li>\n"
                + "                </ul>\n"
                + "            </div>\n"
                + "            \n"
                + "            <p>Para acceder a tu cuenta, ingresa al portal SITEA con las credenciales anteriores.</p>\n"
                + "            \n"
                + "            <p style=\"text-align: center;\">\n"
                + "                <a href=\"http://localhost:8080/sitea-1.0-SNAPSHOT/\" class=\"button\">Acceder a SITEA</a>\n"
                + "            </p>\n"
                + "            \n"
                + "            <hr style=\"margin: 20px 0; border: none; border-top: 1px solid #ddd;\">\n"
                + "            <p style=\"font-size: 14px;\"><strong>¿Necesitas ayuda?</strong></p>\n"
                + "            <p style=\"font-size: 14px;\">Si tienes problemas para acceder, contáctanos a: <strong>soporte@sitea.edu.co</strong></p>\n"
                + "        </div>\n"
                + "        <div class=\"footer\">\n"
                + "            <p>© 2025 SITEA - Plataforma de Caracterización Integral para TEA</p>\n"
                + "            <p>SENA - Servicio Nacional de Aprendizaje</p>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";
    }
}
