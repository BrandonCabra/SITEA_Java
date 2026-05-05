import com.sena.sitea.entities.Rol;
import com.sena.sitea.services.RolFacadeLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Named;

@Named("rolConverter")
@RequestScoped
public class RolConverter implements Converter {

    @EJB
    private RolFacadeLocal rolFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) return null;
        return rolFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Rol) {
            Rol rol = (Rol) value;
            return (rol.getIdRol() != null) ? rol.getIdRol().toString() : "";
        }
        return "";
    }
}
