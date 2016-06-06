package br.com.financemate.manageBean.processo;

import br.com.financemate.bean.Formatacao;
import br.com.financemate.bean.SituacaoBean;
import br.com.financemate.facade.ProcessoSituacaoFacade;
import br.com.financemate.model.Atividades;
import br.com.financemate.model.Processo;
import br.com.financemate.model.Processoatividade;
import br.com.financemate.model.Processorotina;
import br.com.financemate.model.Processosituacao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class ProcessosIniciadosMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Processo processo;
    private List<Processosituacao> listaSituacao;
    private Processosituacao processosituacao;
    
    public ProcessosIniciadosMB(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        processo = (Processo) session.getAttribute("processo");
        session.removeAttribute("processo");
        gerarListaProcessoSituacao();
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public List<Processosituacao> getListaSituacao() {
        return listaSituacao;
    }

    public void setListaSituacao(List<Processosituacao> listaSituacao) {
        this.listaSituacao = listaSituacao;
    }

    public Processosituacao getProcessosituacao() {
        return processosituacao;
    }

    public void setProcessosituacao(Processosituacao processosituacao) {
        this.processosituacao = processosituacao;
    }

    
    
    public void gerarListaProcessoSituacao(){
        ProcessoSituacaoFacade processoSituacaoFacade = new ProcessoSituacaoFacade();
        try {
            listaSituacao = processoSituacaoFacade.listar("select s from Processosituacao s where s.dataabertura>='" + Formatacao.SubtarirDatas(new Date(), 30, "yyyy-MM-dd") + "'" +
                    " and s.processo.idprocesso=" + processo.getIdprocesso());
        } catch (Exception ex) {
            Logger.getLogger(ProcessosIniciadosMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (listaSituacao==null){
            listaSituacao = new ArrayList<Processosituacao>();
        }
        gerarStatusProcesso();
    }
    
    public String fechar(){
        return "consProcesso";
    }
    
    public void gerarStatusProcesso(){
        for(int i=0;i<listaSituacao.size();i++){
            listaSituacao.get(i).setSituacao("AM");
            if (listaSituacao.get(i).getDataconclusao()!=null){
                listaSituacao.get(i).setSituacao("VD");
            }else {
                boolean situacao= verificaratividadeProcesso(listaSituacao.get(i).getProcessoatividadeList());
                if (situacao){
                    listaSituacao.get(i).setSituacao("AM");
                }else {
                    listaSituacao.get(i).setSituacao("VR");
                }
            }
        }
    }
    
    public boolean verificaratividadeProcesso(List<Processoatividade> lista){
        boolean situacao=false;
        String sData = Formatacao.ConvercaoDataPadrao(new Date());
        Date dataAtual = Formatacao.ConvercaoStringDataBrasil(sData);
        if (lista!=null){
            for(int i=0;i<lista.size();i++){
                if (lista.get(i).getAtividades().getSituacao()){
                    situacao=true;
                }else {
                    if (lista.get(i).getAtividades().getPrazo().before(dataAtual)){
                        return false;
                    }else {
                        situacao = true;
                    }
                }
            }
        }
        return situacao;   
    }
    
    public String imagemStatus(Processosituacao processosituacao) {
        if (processosituacao.getSituacao().equalsIgnoreCase("VR")) {
            return "/resources/img/sitacaoVermelha.png";
        } else if (processosituacao.getSituacao().equalsIgnoreCase("AM")) {
            return "/resources/img/sitacaoAmarela.png";
        } else if (processosituacao.getSituacao().equalsIgnoreCase("VD")){
            return "/resources/img/sitacaoVerde.png";
        }return "/resources/img/sitacaoAmarela.png";
    }
    
     public String visualizarAtividadesIniciadas(){
       if ((processosituacao!=null) && (processosituacao.getProcessoatividadeList()!=null) && (processosituacao.getProcessoatividadeList().size()>0)){
            FacesContext fc = FacesContext.getCurrentInstance();
       HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
       session.setAttribute("listaProcessoAtividades",processosituacao.getProcessoatividadeList());
       Map<String,Object> options = new HashMap<String, Object>();
       options.put("contentWidth", 300);
       RequestContext.getCurrentInstance().openDialog("consAtividadesIniciadas");
       }else {
          FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage(null, new FacesMessage("Não a Atividade Cadastrada ", ""));
     }
      
       return "";
    }
    
    
}