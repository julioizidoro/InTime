package br.com.financemate.manageBean.processo;

import br.com.financemate.facade.ProcessoFacade;
import br.com.financemate.facade.ProcessoRotinaFacade;
import br.com.financemate.model.Processo;
import br.com.financemate.model.Processoatividade;
import br.com.financemate.model.Processorotina;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class ProcessoMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Processo> listaProcesso;
    private Processo processo;
    private String nomeProcesso;
    
    @PostConstruct
    public void init(){
        gerarListaProcesso();
    }

    public List<Processo> getListaProcesso() {
        return listaProcesso;
    }

    public void setListaProcesso(List<Processo> listaProcesso) {
        this.listaProcesso = listaProcesso;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public String getNomeProcesso() {
        return nomeProcesso;
    }

    public void setNomeProcesso(String nomeProcesso) {
        this.nomeProcesso = nomeProcesso;
    }
    
    
    
    public String visualizarProcessosIniciados(Processo processos){
       FacesContext fc = FacesContext.getCurrentInstance();
       HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
       session.setAttribute("processo", processos);
       return "consProcessosIniciados";
    }
    
    
    public String iniciarProcesso(Processo processos){
       FacesContext fc = FacesContext.getCurrentInstance();
       HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
       session.setAttribute("processo", processos);   
       Map<String,Object> options = new HashMap<String, Object>();
       options.put("contentWidth", 405);
       RequestContext.getCurrentInstance().openDialog("inicializarProcessos");
       return "";
    }
    
    public String novo(){
       Map<String,Object> options = new HashMap<String, Object>();
       options.put("contentWidth", 410);
       RequestContext.getCurrentInstance().openDialog("cadProcesso");
       return "";
    }
    
    public void gerarListaProcesso(){
        if (nomeProcesso == null) {
            nomeProcesso = "";
        }
        ProcessoFacade processoFacade = new ProcessoFacade();
        listaProcesso = processoFacade.listar("select p from Processo p where p.descricao like '%" +nomeProcesso+ "%' order by p.descricao");
        if (listaProcesso==null){
            listaProcesso = new ArrayList<Processo>();
        }
        if (listaProcesso.size()>0){
            this.processo = listaProcesso.get(0);
        }
    }
     
    public String novoProcessoRotina(Processo processos){
        this.processo = processos;
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("processo", processos);
        RequestContext.getCurrentInstance().openDialog("cadRotinaProcesso");
        return "";
    }
    
    public void retornoDialogNovo(SelectEvent event) {
        Processo processo = (Processo) event.getObject();
        gerarListaProcesso();
    }
    
    public void retornoDialogAtividadeProcesso(SelectEvent event) {
        if (event!=null){
            Processorotina processorotina = (Processorotina) event.getObject();
            processo = processorotina.getProcesso();
        }
        gerarListaProcesso();
    }
    
    public String alterarProcessoAtividade(Processorotina processorotina){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("processorotina", processorotina);
        session.setAttribute("processo", processorotina.getProcesso());
        RequestContext.getCurrentInstance().openDialog("cadRotinaProcesso");
        return "";
    }
    
    public void excluirProcessoRotina(Processorotina processorotina){
        processo = processorotina.getProcesso();
        if (verificarAtividadesProcessoRotina(processorotina.getProcessoatividadeList())){
            ProcessoRotinaFacade processoRotinaFacade = new ProcessoRotinaFacade();
            processoRotinaFacade.excluir(processorotina);
        }
        gerarListaProcesso();
    }
    
    public boolean verificarAtividadesProcessoRotina(List<Processoatividade> lista) {
        if (lista != null) {
            for (int i = 0; i < lista.size(); i++) {
                if (!lista.get(i).getAtividades().getSituacao()) {
                    return false;
                }
            }
        }
        return true;
    }
}