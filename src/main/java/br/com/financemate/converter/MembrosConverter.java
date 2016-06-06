package br.com.financemate.converter;

import br.com.financemate.model.Membros;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Kamila
 */
@FacesConverter(value="MembrosConverter")
public class MembrosConverter implements Converter{
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<Membros> listaMembros = (List<Membros>) component.getAttributes().get("listaMembros");
        for (Membros membros : listaMembros) {
                if (membros.getUsuario().getNome().equalsIgnoreCase(value)) {
                    return membros;
                }
            }
        return null;
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value.toString().equalsIgnoreCase("0")) {
            return "Selecione";
        } else {
            Membros membros = (Membros) value;
            return membros.getUsuario().getNome();
        }
    }
    
}