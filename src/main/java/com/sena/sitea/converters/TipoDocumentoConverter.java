package com.sena.sitea.converters;

import com.sena.sitea.controller.AdminController;
import com.sena.sitea.entities.TipoDocumento;
import com.sena.sitea.services.TipoDocumentoFacadeLocal;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

// Usamos @Named para permitir la inyección del EJB (funciona mejor en Glassfish 5 que @FacesConverter plano)
@Named("tipoDocumentoConverter")
@RequestScoped
public class TipoDocumentoConverter implements Converter {

    @EJB
    private TipoDocumentoFacadeLocal tipoDocumentoFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        try {
            int idBuscado = Integer.parseInt(value);

            // --- ESTRATEGIA 1: BÚSQUEDA EN MEMORIA (OPTIMIZACIÓN) ---
            // Intentamos obtener el AdminController de la sesión actual
            AdminController adminBean = context.getApplication()
                    .evaluateExpressionGet(context, "#{adminController}", AdminController.class);

            // Si el AdminController existe y ya tiene la lista cargada, buscamos ahí.
            // Esto evita ir a la base de datos innecesariamente.
            if (adminBean != null && adminBean.getListaTiposDoc() != null) {
                for (TipoDocumento td : adminBean.getListaTiposDoc()) {
                    if (td.getIdTipoDocumento() != null && td.getIdTipoDocumento() == idBuscado) {
                        return td;
                    }
                }
            }

            // --- ESTRATEGIA 2: BÚSQUEDA EN BASE DE DATOS (RESPALDO) ---
            // Si falló lo anterior (ej: estamos en el módulo de Registro público),
            // usamos el Facade para buscar en la BD.
            return tipoDocumentoFacade.find(idBuscado);

        } catch (NumberFormatException e) {
            return null;
        } catch (Exception e) {
            System.err.println("Error en TipoDocumentoConverter: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof TipoDocumento) {
            TipoDocumento tipo = (TipoDocumento) value;
            return (tipo.getIdTipoDocumento() != null) ? tipo.getIdTipoDocumento().toString() : "";
        }
        return "";
    }
}