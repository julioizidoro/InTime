package br.com.financemate.manageBean.cadastro;


import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
import br.com.financemate.manageBean.atividades.ListaAtividadesMB;
import br.com.financemate.model.Cliente;
import br.com.financemate.repository.ClienteRepository;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kamila
 */
@Named
@ViewScoped 
public class ClienteMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private UsuarioLogadoMB usuarioLogadoBean;
    @Inject 
    private ListaAtividadesMB listaAtividadesMB;
    private String nomeCliente;
    private List<Cliente> listaClientes;
    private String linha;
    private ClienteRepository clienteRepository;
    
    @PostConstruct
    public void init(){
        getUsuarioLogadoBean();
        gerarListaClientes();
    }
    
    public UsuarioLogadoMB getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoMB usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public List<Cliente> getListaClientes() {
         return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

   
    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }
    
    public void gerarListaClientes() {
        if (nomeCliente == null) {
            nomeCliente = "";
        }
        String sql =("select c from Cliente c where c.nomefantasia like '%" + " " + "%' and c.idcliente<>"
                + usuarioLogadoBean.getConfig().getCliente().getIdcliente() + " order by c.nomefantasia");
        listaClientes = clienteRepository.list(sql);
        if (listaClientes == null) {
            listaClientes = new ArrayList<Cliente>();
        }
    }
    
    public void pesquisarNome(){
        gerarListaClientes();
    } 
    
    public String novo(){
        if(usuarioLogadoBean.getUsuario().getPerfil().getCadclienteincluir()){
            return "cadCliente";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    
    public String editar(Cliente cliente) throws SQLException {
        if (usuarioLogadoBean.getUsuario().getPerfil().getCadclienteeditar()) {
            if (cliente != null) {
                FacesContext fc = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
                session.setAttribute("cliente", cliente);
                return "cadCliente";
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return null;
    }
    
    public void pegarLinhaTabela(String linha){
        this.linha = linha;
    }
    
    public String habilitarDesabilitar(){
        if(usuarioLogadoBean.getUsuario().getPerfil().getCadclientesituacao()){
            if (linha!=null){
                int nlinha = Integer.parseInt(linha);
               if (nlinha>=0){
                   if (listaClientes.get(nlinha).getSituacao().equalsIgnoreCase("Ativo")){
                       listaClientes.get(nlinha).setSituacao("Inativo");
                   }else listaClientes.get(nlinha).setSituacao("Ativo");
                   if (listaClientes.get(nlinha).getIdcliente() == null) {
                       clienteRepository.create(listaClientes.get(nlinha));
                   } else {
                       clienteRepository.update(listaClientes.get(nlinha));
                   }
                   listaAtividadesMB.gerarListaCliente();
                   return "consCliente";
               } 
            }
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    
        
   public String retornarImagemSituacao(Cliente cliente){
       String imagem;
       if(cliente.getSituacao().equalsIgnoreCase("Inativo")){
           imagem = "../../resources/img/sitacaoVermelha.png";
       }else{
           imagem = "../../resources/img/sitacaoVerde.png";
       }
       return imagem;
   }
}