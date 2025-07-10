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

        boolean validarSesion = (sesion != null && sesion.getAttribute("NUMERO_DOCUMENTO") != null);
        boolean validarRutaLogin = rutaSolicitud.equals(raiz + "/") || rutaSolicitud.equals(raiz + "/login.xhtml");
        boolean validarContenido = rutaSolicitud.contains("/resources/");

        // ✅ Añade aquí excepciones para las páginas públicas
        boolean validarRegistro = rutaSolicitud.contains("TemplateRegistro.xhtml") || rutaSolicitud.contains("registro.xhtml");

        if (validarSesion || validarRutaLogin || validarContenido || validarRegistro) {
            chain.doFilter(request, response); // ✔️ Deja pasar
        } else {
            System.out.println("❌ Acceso restringido. Redirigiendo a login.");
            respuesta.sendRedirect(raiz + "/login.xhtml"); // 🚫 Redirección si no hay acceso válido
        }
    }

    @Override
    public void destroy() {

    }

}
