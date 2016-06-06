package br.com.financemate.manageBean.processo;

import br.com.financemate.bean.Formatacao;
import br.com.financemate.model.Processoatividade;
import br.com.financemate.model.Processosituacao;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class ProcessoIniciadosAtividadesMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Processoatividade> listaAtividades;
   
    
    @PostConstruct
    public void ini(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        listaAtividades = (List<Processoatividade>) session.getAttribute("listaProcessoAtividades");
        session.removeAttribute("listaProcessoAtividades");
        verificaratividadeProcesso(); 
    }

    public List<Processoatividade> getListaAtividades() {
        return listaAtividades;
    }

    public void setListaAtividades(List<Processoatividade> listaAtividades) {
        this.listaAtividades = listaAtividades;
    }

    
    
    public boolean verificaratividadeProcesso() {
        boolean situacao = false;
        String sData = Formatacao.ConvercaoDataPadrao(new Date());
        Date dataAtual = Formatacao.ConvercaoStringDataBrasil(sData);
        for (int i = 0; i < listaAtividades.size(); i++) {
            if (listaAtividades.get(i).getAtividades().getSituacao()) {
                listaAtividades.get(i).setStatus("VD");
            } else {
                if (listaAtividades.get(i).getAtividades().getPrazo().before(dataAtual)) {
                    listaAtividades.get(i).setStatus("VM");
                } else {
                    listaAtividades.get(i).setStatus("AM");
                }
            }
        }
        return situacao;
    }
    
    public String imagemStatus(Processoatividade processoatividade) {
        if (processoatividade.getStatus().equalsIgnoreCase("VR")) {
            return "/resources/img/sitacaoVermelha.png";
        } else if (processoatividade.getStatus().equalsIgnoreCase("AM")) {
            return "/resources/img/sitacaoAmarela.png";
        } else if (processoatividade.getStatus().equalsIgnoreCase("VD")){
            return "/resources/img/sitacaoVerde.png";
        }return "/resources/img/bolaAmarela.png";
    }
    
     public String fechar(){
        RequestContext.getCurrentInstance().closeDialog("consProcessosIniciados");
        return "";
    }
    
    
    
}