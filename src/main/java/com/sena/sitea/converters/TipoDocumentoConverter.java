package com.sena.sitea.converters;

import com.sena.sitea.entities.TipoDocumento;
import com.sena.sitea.services.TipoDocumentoFacadeLocal;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

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
            return tipoDocumentoFacade.find(Integer.valueOf(value));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof TipoDocumento) {
            TipoDocumento tipo = (TipoDocumento) value;
            return (tipo.getIdTipoDocumento() != null) ? tipo.getIdTipoDocumento().toString() : "";
        }
        return "";
    }
}
