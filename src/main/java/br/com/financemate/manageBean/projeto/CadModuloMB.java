package br.com.financemate.manageBean.projeto;

import br.com.financemate.facade.ModuloFacade;
import br.com.financemate.model.Modulos;
import br.com.financemate.model.Projeto;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;


@Named
@ViewScoped
public class CadModuloMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Modulos modulos;
    
     public CadModuloMB() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        Projeto projeto = (Projeto) session.getAttribute("projeto");
        modulos = (Modulos) session.getAttribute("modulos");
        session.removeAttribute("modulos");
        if (modulos==null){
            modulos = new Modulos();
            modulos.setProjeto(projeto);
            session.removeAttribute("projeto");
        }
    }

    public Modulos getModulos() {
        return modulos;
    }

    public void setModulos(Modulos modulos) {
        this.modulos = modulos;
    }
    
    public String salvar(){
        ModuloFacade moduloFacade = new ModuloFacade();  
        moduloFacade.salvar(modulos);
        RequestContext.getCurrentInstance().closeDialog(modulos);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Cadastrado com Sucesso", ""));
        return "consModulo";
    }
    
    public String cancelar(){
        RequestContext.getCurrentInstance().closeDialog(null);
        return "consModulo";
    }
}