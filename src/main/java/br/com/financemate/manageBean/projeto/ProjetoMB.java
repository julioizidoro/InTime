package br.com.financemate.manageBean.projeto;


import br.com.financemate.facade.ProjetoFacade;
import br.com.financemate.facade.UsuarioFacade;
import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Membros;
import br.com.financemate.model.Projeto;
import br.com.financemate.model.Usuario;
import br.com.financemate.repository.ClienteRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@Named
@ViewScoped
public class ProjetoMB implements Serializable{
    
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Inject 
  private UsuarioLogadoMB usuarioLogadoBean;
  private Projeto projeto;
  private List<Projeto> listaProjeto;
  private int idCliente;
  private List<Cliente> listaCliente;
  private List<Usuario> listaUsuario;
  private List<Membros> listaMembros;
  private ClienteRepository clienteRepository;
  
  
  @PostConstruct
    public void init(){
        getUsuarioLogadoBean();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        projeto = (Projeto) session.getAttribute("projeto");
        session.removeAttribute("projeto");
        gerarListaProjeto();
        gerarListaUsuario();
        projeto = new Projeto();
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public UsuarioLogadoMB getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoMB usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public List<Projeto> getListaProjeto() {
        if(listaProjeto==null){
            gerarListaProjeto();
        }
        return listaProjeto;
    }

    public void setListaProjeto(List<Projeto> listaProjeto) {
        this.listaProjeto = listaProjeto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public List<Membros> getListaMembros() {
        return listaMembros;
    }

    public void setListaMembros(List<Membros> listaMembros) {
        this.listaMembros = listaMembros;
    }

   
    
    
    
    public String novo(){
       Map<String,Object> options = new HashMap<String, Object>();
       options.put("contentWidth", 410);
       RequestContext.getCurrentInstance().openDialog("cadProjeto");
       return "";
    }
  
    public void gerarListaCliente(){
        String sql =("select c from Cliente c where c.nomefantasia like '%" + " " + "%' and c.situacao='Ativo' and c.idcliente<>"
                + usuarioLogadoBean.getConfig().getCliente().getIdcliente() + " order by c.nomefantasia");
        listaCliente = clienteRepository.list(sql);
        if (listaCliente==null){
            listaCliente = new ArrayList<Cliente>();
        }
    }
    
    public void gerarListaProjeto() {
        ProjetoFacade projetoFacade = new ProjetoFacade();
        listaProjeto = projetoFacade.listar("");
        if (listaProjeto == null) {
            listaProjeto = new ArrayList<Projeto>();
        }
    }
    public void gerarListaUsuario() {
        UsuarioFacade usuarioFacade = new UsuarioFacade();
        listaUsuario = usuarioFacade.listarAtivos();
        if (listaUsuario == null) {
            listaUsuario = new ArrayList<Usuario>();
        }
    }
    
    
    public String editar(){
        if (this.projeto!=null){
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("projeto", projeto);   
            RequestContext.getCurrentInstance().openDialog("cadProjeto");
            return "";
        }
        return "";
    }
    
    public String modulo(Projeto projeto){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("projeto", projeto);
        return "consModulo";
    }
    
    public void relatorio(Projeto projeto){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("projeto", projeto);
    }
    
    
    public String vincularMembros(Projeto projetos) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("projeto", projetos);
        RequestContext.getCurrentInstance().openDialog("vincularMembros");
        return "";
    }
    
    public String adicionarMembros() {
        List<Usuario> lista = new ArrayList<>();
        for (int i = 0; i < listaUsuario.size(); i++) {
            if (listaUsuario.get(i).isSelecionado()) {
               lista.add(listaUsuario.get(i));
             } 
        }
        if (lista.size() == 0) {
            lista = null;
        }
        return "";
    }
    
    public void retornoDialogNovo(SelectEvent event) {
        gerarListaProjeto();
    }
   
   
    
  public String retornarImagemSituacao(Projeto projeto){
       String imagem;
       if(projeto.getSituacao().equalsIgnoreCase("Inativo")){
           imagem = "../../resources/img/sitacaoVermelha.png";
       }else{
           imagem = "../../resources/img/sitacaoVerde.png";
       }
       return imagem;
   }
    
}