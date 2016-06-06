package br.com.financemate.manageBean.projeto;

import br.com.financemate.bean.Formatacao;
import br.com.financemate.facade.ModuloFacade;
import br.com.financemate.model.Atividademodulo;
import br.com.financemate.model.Modulos;
import br.com.financemate.model.Projeto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

@Named
@ViewScoped
public class ModuloMB implements Serializable{
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Modulos modulos;
    private List<Modulos> listaModulos;
    private Projeto projeto;
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        projeto = (Projeto) session.getAttribute("projeto");
        session.removeAttribute("projeto");
        modulos = new Modulos();
        if (projeto!=null){
            gerarListaModulos();
        }else{
            listaModulos = new ArrayList<Modulos>();
        }
    }

    public Modulos getModulos() {
        return modulos;
    }

    public void setModulos(Modulos modulos) {
        this.modulos = modulos;
    }

    public List<Modulos> getListaModulos() {
        return listaModulos;
    }

    public void setListaModulos(List<Modulos> listaModulos) {
        this.listaModulos = listaModulos;
    }
    
    
    public void gerarListaModulos(){
        String sql = "Select m from Modulos m where m.projeto.idprojeto=" + projeto.getIdprojeto();
        ModuloFacade moduloFacade = new ModuloFacade();
        listaModulos = moduloFacade.listar(sql);
        if (listaModulos==null){
            listaModulos = new ArrayList<Modulos>();
        }
        for(int i=0;i<listaModulos.size();i++){
            listaModulos.get(i).setStatus(verificarStatus(listaModulos.get(i)));
        }
    }
    
    public String novo(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("projeto", projeto);
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 450);
        RequestContext.getCurrentInstance().openDialog("cadModulo", options, null);
      
        return "";
    }
    
      
      
    public String editar(){
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("modulos", modulos);
            RequestContext.getCurrentInstance().openDialog("cadModulo");
            return "";
    }
    
    public String voltar(){
        return "consProjeto";
    }
    
   public String atividadesModulo(Modulos modulos){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("modulos", modulos);
        return "consAtividadeModulo";
   }
   
   public String imagemStatus(Modulos modulo) {
        if (modulo.getStatus().equalsIgnoreCase("VR")) {
            return "/resources/img/sitacaoVermelha.png";
        } else if (modulo.getStatus().equalsIgnoreCase("AM")) {
            return "/resources/img/sitacaoAmarela.png";
        } else if (modulo.getStatus().equalsIgnoreCase("VD")){
            return "/resources/img/sitacaoVerde.png";
        }return "/resources/img/sitacaoAmarela.png";
    }
   
    public String verificarStatus(Modulos modulo) {
        boolean situacao = false;
        String sData = Formatacao.ConvercaoDataPadrao(new Date());
        Date dataAtual = Formatacao.ConvercaoStringDataBrasil(sData);
        List<Atividademodulo> lista = modulo.getAtividademoduloList();
        if (lista != null) {
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getAtividades() != null) {
                    if (lista.get(i).getAtividades().getSituacao()) {
                        return "VD";
                    } else {
                        if (lista.get(i).getAtividades().getPrazo().before(dataAtual)) {
                            return "VR";
                        } else {
                            return "AM";
                        }
                    }
                }
            }
        }
        return "AM";
    }
    public void retornoDialogNovo(SelectEvent event) {
       gerarListaModulos();
    }
}