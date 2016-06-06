package br.com.financemate.manageBean.cadastro;

import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
import br.com.financemate.facade.DepartamentoFacade;
import br.com.financemate.model.Departamento;
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
@Named("DepartamentoMB")
@ViewScoped
public class DepartamentoMB implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private UsuarioLogadoMB usuarioLogadoBean;
    private List<Departamento> listaDepartamento;
   
    
    @PostConstruct
    public void init(){
        getUsuarioLogadoBean();
        gerarListaDepartamento("");
    }
    

    public UsuarioLogadoMB getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoMB usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public List<Departamento> getListaDepartamento() {
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }
    
    public void gerarListaDepartamento(String nome) {
        DepartamentoFacade departamentoFacade = new DepartamentoFacade();
        listaDepartamento = departamentoFacade.listar(nome, usuarioLogadoBean.getConfig().getDepartamento().getIddepartamento());
        if (listaDepartamento == null) {
            listaDepartamento = new ArrayList<Departamento>();
        }
    }

    public String novo() throws SQLException {
        if (usuarioLogadoBean.getUsuario().getPerfil().getCaddepartamentoincluir()) {
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("contentWidth", 450);
            RequestContext.getCurrentInstance().openDialog("cadDepartamento");
            return "";
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }

    

    public String editar(Departamento departamento) throws SQLException {
        if (departamento != null) {
            if (usuarioLogadoBean.getUsuario().getPerfil().getCaddepartamentoeditar()) {
                FacesContext fc = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
                session.setAttribute("departamento", departamento);
                RequestContext.getCurrentInstance().openDialog("cadDepartamento");
                return "";
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
            }
        }
        return null;
    }

    public void retornoDialogNovo(SelectEvent event) {
        gerarListaDepartamento("");
    }
}