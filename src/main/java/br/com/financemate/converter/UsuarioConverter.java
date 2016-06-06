package br.com.financemate.converter;


import br.com.financemate.model.Usuario;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="usuarioConverter")
public class UsuarioConverter implements Converter{
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<Usuario> listaUsuario = (List<Usuario>) component.getAttributes().get("listaUsuario");
        for (Usuario u : listaUsuario) {
                if (u.getNome().equalsIgnoreCase(value)) {
                    return u;
                }
            }
        return null;
    }


     @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value.toString().equalsIgnoreCase("0")) {
            return "Selecione";
        } else {
            Usuario usuario = (Usuario) value;
            return usuario.getNome();
        }
    }
}