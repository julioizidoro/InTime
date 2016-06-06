package br.com.financemate.converter;

import br.com.financemate.model.Subdepartamento;
import java.util.ArrayList;
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
@FacesConverter(value="subDepartamentoConverter")
public class SubDepartamentoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<Subdepartamento> listasubdepartamentos = (List<Subdepartamento>) component.getAttributes().get("listaSubDepartamento");
        if (listasubdepartamentos == null){
            listasubdepartamentos = new ArrayList<Subdepartamento>();
            
        }
        for (Subdepartamento subdepartamento : listasubdepartamentos) {
            if (subdepartamento.getNome().equalsIgnoreCase(value)) {
                return subdepartamento;
            }
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value.toString().equalsIgnoreCase("0")) {
            return "Selecione";
        } else {
            Subdepartamento subdepartamento = (Subdepartamento) value;
            return subdepartamento.getNome();
        }

    }

}