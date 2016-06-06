package br.com.financemate.manageBean.processo;

import br.com.financemate.facade.ProcessoRotinaFacade;
import br.com.financemate.model.Processo;
import br.com.financemate.model.Processorotina;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class CadProcessoRotinaMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Processorotina processorotina;
    private Processo processo;
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        Processo processos = (Processo) session.getAttribute("processo");
        processorotina = (Processorotina) session.getAttribute("processorotina");
        session.removeAttribute("processorotina");
        if (processorotina==null){
            processorotina = new Processorotina();
            processorotina.setProcesso(processos);
        }
         session.removeAttribute("processo");
         session.removeAttribute("processorotina");
    }

    public Processorotina getProcessorotina() {
        return processorotina;
    }

    public void setProcessorotina(Processorotina processorotina) {
        this.processorotina = processorotina;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }
    
    public String salvar(){
        if (processorotina.getDiasuteis() != null) {
            ProcessoRotinaFacade processoRotinaFacade = new ProcessoRotinaFacade();
            processorotina = processoRotinaFacade.salvar(processorotina);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Cadastrado com Sucesso", ""));
            RequestContext.getCurrentInstance().closeDialog(processorotina);
            return "";
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("É obrigatório preencher dias uteis", ""));

        }
        return "";

    }
    
    public String fechar(){
        RequestContext.getCurrentInstance().closeDialog(null);
        return "";
    }
    
}