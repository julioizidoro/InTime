package br.com.financemate.converter;

import br.com.financemate.model.Departamento;
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
@FacesConverter(value="departamentoConverter")
public class DepartamentoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<Departamento> listaDepartamentos = (List<Departamento>) component.getAttributes().get("listaDepartamento");
        for (Departamento departamento : listaDepartamentos) {
            if (departamento.getNome().equalsIgnoreCase(value)) {
                return departamento;
            }
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value.toString().equalsIgnoreCase("0")) {
            return "Selecione";
        } else {
            Departamento departamento = (Departamento) value;
            return departamento.getNome();
        }
    }

}