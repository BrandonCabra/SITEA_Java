/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bjcab
 */
public class Filtro implements Filter {

    // Lista de rutas públicas que no requieren autenticación
    private static final String[] RUTAS_PUBLICAS = {
        "/index.xhtml",
        "/webpage.xhtml",
        "/login.xhtml",
        "/acerca.xhtml",
        "/Servicios.xhtml",
        "/Servicios2.xhtml",
        "/manuales.xhtml",
        "/manuales2.xhtml",
        "/Equipo.xhtml",
        "/Equipo2.xhtml",
        "/Contacto.xhtml",
        "/Contacto2.xhtml",
        "/TemplateRegistro.xhtml",
        "/registro.xhtml"
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest solicitud = (HttpServletRequest) request;
        HttpServletResponse respuesta = (HttpServletResponse) response;

        respuesta.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        respuesta.setHeader("Pragma", "no-cache");
        respuesta.setDateHeader("Expires", 0);

        HttpSession sesion = solicitud.getSession(false);
        String rutaSolicitud = solicitud.getRequestURI();
        String raiz = solicitud.getContextPath();

        System.out.println("🔍 Ruta solicitada: " + rutaSolicitud);

        // Validar si hay sesión activa
        boolean validarSesion = (sesion != null && sesion.getAttribute("NUMERO_DOCUMENTO") != null);
        
        // Validar si es contenido estático (CSS, JS, imágenes, fuentes)
        boolean validarContenido = rutaSolicitud.contains("/resources/");
        
        // Validar si es raíz o una ruta pública
        boolean esRutaPublica = esRutaPublica(rutaSolicitud, raiz);

        System.out.println("✅ ¿Sesión válida?: " + validarSesion);
        System.out.println("✅ ¿Es ruta pública?: " + esRutaPublica);
        System.out.println("✅ ¿Es contenido estático?: " + validarContenido);

        if (validarSesion || esRutaPublica || validarContenido) {
            System.out.println("✅ Acceso PERMITIDO");
            chain.doFilter(request, response); // ✔️ Deja pasar
        } else {
            System.out.println("❌ Acceso RESTRINGIDO. Redirigiendo a login.");
            respuesta.sendRedirect(raiz + "/login.xhtml"); // 🚫 Redirección si no hay acceso válido
        }
    }

    /**
     * Verifica si la ruta solicitada es pública
     * @param rutaSolicitud La ruta solicitada
     * @param raiz El contexto raíz de la aplicación
     * @return true si es una ruta pública, false en caso contrario
     */
    private boolean esRutaPublica(String rutaSolicitud, String raiz) {
        // Si es la raíz, es pública
        if (rutaSolicitud.equals(raiz + "/")) {
            return true;
        }

        // Verificar contra la lista de rutas públicas
        for (String rutaPublica : RUTAS_PUBLICAS) {
            if (rutaSolicitud.equals(raiz + rutaPublica)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void destroy() {

    }

}