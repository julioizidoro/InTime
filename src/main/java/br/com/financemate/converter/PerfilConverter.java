package br.com.financemate.converter;

import br.com.financemate.model.Perfil;
import java.util.Iterator;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Wolverine
 */
@FacesConverter(value="perfilConverter")
public class PerfilConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<Perfil> perfil = (List<Perfil>) component.getAttributes().get("listaPerfil");
        for (Iterator<Perfil> iterator = perfil.iterator(); iterator.hasNext();) {
            Perfil p = (Perfil) iterator.next();
            if (p.getTipoacesso().equals(value)) {
                return p;
            }
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value.toString().equalsIgnoreCase("0")) {
            return "Selecione";
        } else {
            Perfil perfil = (Perfil) value;
            return perfil.getTipoacesso();
        }
    }
    
    
    
}