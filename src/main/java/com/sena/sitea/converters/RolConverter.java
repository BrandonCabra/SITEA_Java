package com.sena.sitea.converters;

import com.sena.sitea.controller.AdminController;
import com.sena.sitea.entities.Rol;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("rolConverter")
public class RolConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        try {
            // 1. Obtenemos el AdminController de la sesión
            AdminController bean = (AdminController) context.getApplication()
                    .evaluateExpressionGet(context, "#{adminController}", AdminController.class);

            // 2. Buscamos el Rol en la lista que ya tiene cargada (Ahorra consulta a BD)
            if (bean != null && bean.getListaRoles() != null) {
                for (Rol r : bean.getListaRoles()) {
                    // Asumiendo que el ID es numérico, lo comparamos como String
                    if (r.getIdRol().toString().equals(value)) {
                        return r;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error convirtiendo Rol: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || !(value instanceof Rol)) {
            return "";
        }
        // Devolvemos el ID como String para el value HTML
        return ((Rol) value).getIdRol().toString();
    }
}