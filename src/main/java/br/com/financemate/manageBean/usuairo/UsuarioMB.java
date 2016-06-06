package br.com.financemate.manageBean.usuairo;

import br.com.financemate.manageBean.atividades.ListaAtividadesMB;
import br.com.financemate.facade.UsuarioFacade;
import br.com.financemate.model.Usuario;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
@Named("UsuarioMB")
@ViewScoped
public class UsuarioMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private UsuarioLogadoMB usuarioLogadoBean;
    @Inject ListaAtividadesMB listaAtividadesMB;
    private List<Usuario> listaUsuario;
    private String nomeUsuario;
    private String linha;
    

    public UsuarioLogadoMB getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoMB usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    
    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }
    

    public List<Usuario> getListaUsuario() {
        if (listaUsuario==null){
            gerarListaUsuarios("");
        }
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    
    
    public void gerarListaUsuarios(String nome) {
        UsuarioFacade usuarioFacade = new UsuarioFacade();
        listaUsuario = usuarioFacade.listarTodos(nome);
        if (listaUsuario == null) {
            listaUsuario = new ArrayList<Usuario>();
        }
    }
    
    public String novo() throws SQLException{
        if(usuarioLogadoBean.getUsuario().getPerfil().getCadsubdepartamentoincluir()){
            return "cadUsuario";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    
    
    
    public String editar(Usuario usuario) throws SQLException {
        if (usuarioLogadoBean.getUsuario().getPerfil().getCadusuarioeditar()) {
            if (usuario != null) {
                FacesContext fc = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
                session.setAttribute("usuario", usuario);
                return "cadUsuario";
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
        if(usuarioLogadoBean.getUsuario().getPerfil().getCadusuariosituacao()){
            if (linha!=null){
                int nlinha = Integer.parseInt(linha);
               if (nlinha>=0){
                   if (listaUsuario.get(nlinha).getSituacao().equalsIgnoreCase("Ativo")){
                       listaUsuario.get(nlinha).setSituacao("Inativo");
                   }else listaUsuario.get(nlinha).setSituacao("Ativo");
                   UsuarioFacade usuarioFacade = new UsuarioFacade();
                   usuarioFacade.salvar(listaUsuario.get(nlinha));
                   listaAtividadesMB.gerarListaUsuario();
                   return "consUsuario";
               } 
            }
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
     public String retornarImagemSituacao(Usuario usuario){
       String imagem;
       if(usuario.getSituacao().equalsIgnoreCase("Inativo")){
           imagem = "../../resources/img/sitacaoVermelha.png";
       }else{
           imagem = "../../resources/img/sitacaoVerde.png";
       }
       return imagem;
   }
     
    
    
}