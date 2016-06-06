package br.com.financemate.manageBean.cadastro;

import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
import br.com.financemate.facade.DepartamentoFacade;
import br.com.financemate.facade.SubdepartamentoFacade;
import br.com.financemate.model.Departamento;
import br.com.financemate.model.Subdepartamento;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Kamila
 */
@Named("SubdepartamentoMB")
@ViewScoped
public class SubdepartamentoMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private UsuarioLogadoMB usuarioLogadoBean;
    private List<Subdepartamento> listaSubdepartamento;
    
    @PostConstruct
    public void init(){
        getUsuarioLogadoBean();
        gerarListaSubdepartamento();
    }
    
    public UsuarioLogadoMB getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoMB usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public List<Subdepartamento> getListaSubdepartamento() {
        return listaSubdepartamento;
    }

    public void setListaSubdepartamento(List<Subdepartamento> listaSubdepartamento) {
        this.listaSubdepartamento = listaSubdepartamento;
    }
    
    
    public void gerarListaSubdepartamento() {
        SubdepartamentoFacade subdepartamentoFacade = new SubdepartamentoFacade();
        listaSubdepartamento = subdepartamentoFacade.listar(usuarioLogadoBean.getConfig().getSubdepartamento().getIdsubdepartamento());
        if (listaSubdepartamento == null) {
            listaSubdepartamento = new ArrayList<Subdepartamento>();
        }
    }
    
    public String novo() throws SQLException{
        if(usuarioLogadoBean.getUsuario().getPerfil().getCadsubdepartamentoincluir()){
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("contentWidth", 450);
            RequestContext.getCurrentInstance().openDialog("cadSubdepartamento");
            return "";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    
    public String editar(Subdepartamento subdepartamento) throws SQLException{
        if (subdepartamento != null) {
            if (usuarioLogadoBean.getUsuario().getPerfil().getCadsubdepartamentoeditar()) {
                FacesContext fc = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
                session.setAttribute("subdepartamento", subdepartamento);
                RequestContext.getCurrentInstance().openDialog("cadSubdepartamento");
                return "";
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
            }
        }
        return null;
    }
    
    public String habilitarDesabilitar(String linha){
        if(usuarioLogadoBean.getUsuario().getPerfil().getCadsubdepartamentosituacao()){
            if (linha!=null){
                int nlinha = Integer.parseInt(linha);
               if (nlinha>=0){
                   if (listaSubdepartamento.get(nlinha).getSituacao().equalsIgnoreCase("Ativo")){
                       listaSubdepartamento.get(nlinha).setSituacao("Inativo");
                   }else listaSubdepartamento.get(nlinha).setSituacao("Ativo");
                   SubdepartamentoFacade subdepartamentoFacade = new SubdepartamentoFacade();
                   subdepartamentoFacade.salvar(listaSubdepartamento.get(nlinha));
                   return "consSubdepartamento";
               } 
            }
        }else{
           FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    
     public void retornoDialogNovo(SelectEvent event) {
         gerarListaSubdepartamento();
    }
     
     
     public String retornarImagemSituacao(Subdepartamento subdepartamento){
       String imagem;
       if(subdepartamento.getSituacao().equalsIgnoreCase("Inativo")){
           imagem = "../../resources/img/sitacaoVermelha.png";
       }else{
           imagem = "../../resources/img/sitacaoVerde.png";
       }
       return imagem;
   }
}