package br.com.financemate.manageBean.projeto;


import br.com.financemate.facade.ProjetoFacade;
import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Projeto;
import br.com.financemate.repository.ClienteRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;


@Named
@ViewScoped
public class CadProjetoMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private UsuarioLogadoMB usuarioLogadoBean;
    private Projeto projeto;
    private Cliente cliente;
     private List<Cliente> listaCliente;
     private ClienteRepository clienteRepository;
    
    public CadProjetoMB() {
        getUsuarioLogadoBean();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        projeto = (Projeto) session.getAttribute("projeto");
        session.removeAttribute("projeto");
        if (projeto==null){
            projeto = new Projeto();
        }else {
            cliente = projeto.getCliente();
        }
    }

    public UsuarioLogadoMB getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoMB usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
   

    public List<Cliente> getListaCliente() {
        if(listaCliente==null){
            gerarListaCliente();
        }
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }
    
    public String cancelar(){
        RequestContext.getCurrentInstance().closeDialog(null);
        return "consProjeto";
    }
    
    public String salvar() {
        projeto.setCliente(cliente);
        projeto.setSituacao("Ativo");
        ProjetoFacade projetoFacade = new ProjetoFacade();
        projetoFacade.salvar(projeto);
        RequestContext.getCurrentInstance().closeDialog(projeto);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Cadastrado com Sucesso", ""));
        projeto = new Projeto();
        return "consProjeto";
    }
    
     public void gerarListaCliente(){
        String sql =("select c from Cliente c where c.nomefantasia like '%" + " " + "%' and c.situacao='Ativo' and c.idcliente<>"
                + usuarioLogadoBean.getConfig().getCliente().getIdcliente() + " order by c.nomefantasia");
        listaCliente = clienteRepository.list(sql);
        if (listaCliente==null){
            listaCliente = new ArrayList<Cliente>();
        }
    }
     
}