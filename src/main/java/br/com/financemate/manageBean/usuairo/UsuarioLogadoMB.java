package br.com.financemate.manageBean.usuairo;

import br.com.financemate.facade.ConfigFacade;
import br.com.financemate.manageBean.atividades.ListaAtividadesMB;
import br.com.financemate.facade.UsuarioFacade;
import br.com.financemate.manageBean.util.MenuMB;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Config;
import br.com.financemate.model.Usuario;
import br.com.financemate.util.Criptografia;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Kamila
 */

@Named
@SessionScoped
public class UsuarioLogadoMB implements Serializable{
    
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @Inject ListaAtividadesMB listaAtividadesMB;
    @Inject MenuMB menuMB;
    private Usuario usuario;
    private Cliente cliente;
    private String novaSenha;
    private String confirmaNovaSenha;
    private String nomeCliente;
    private String iniciais;
    private Config config;
    private String abrirDailog; 
    
    
    public UsuarioLogadoMB() {
        this.usuario = new Usuario();
    }
    
    public Usuario getUsuario() {
        if (usuario==null){
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNovaSenha() {
        return novaSenha;
    }
    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmaNovaSenha() {
        return confirmaNovaSenha;
    }

    public void setConfirmaNovaSenha(String confirmaNovaSenha) {
        this.confirmaNovaSenha = confirmaNovaSenha;
    }

    public MenuMB getMenuMB() {
        return menuMB;
    }

    public void setMenuMB(MenuMB menuMB) {
        this.menuMB = menuMB;
    }

    public String getIniciais() {
        return iniciais;
    }

    public void setIniciais(String iniciais) {
        this.iniciais = iniciais;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public ListaAtividadesMB getListaAtividadesMB() {
        return listaAtividadesMB;
    }

    public void setListaAtividadesMB(ListaAtividadesMB listaAtividadesMB) {
        this.listaAtividadesMB = listaAtividadesMB;
    }

    public String getAbrirDailog() {
        return abrirDailog;
    }

    public void setAbrirDailog(String abrirDailog) {
        this.abrirDailog = abrirDailog;
    }
    
    
    
    public String validarUsuario() throws SQLException {
        if ((usuario.getLogin() != null) && (usuario.getSenha() == null)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Login Invalido."));
        } else {
            UsuarioFacade usuarioFacade = new UsuarioFacade();
            try {
                usuario.setSenha(Criptografia.encript(usuario.getSenha()));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UsuarioLogadoMB.class.getName()).log(Level.SEVERE, null, ex);
            }
            usuario = usuarioFacade.consultar(usuario.getLogin(), usuario.getSenha());
            if (usuario == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Acesso Negado."));
            } else {
                ConfigFacade configFacade = new ConfigFacade();
                config = configFacade.consultar();
                gerarIniciis(usuario.getNome());
                listaAtividadesMB.gerarLitaAtividades();
                menuMB.gerarLitaNotificacao();
                return "inicial";
            }
        }
        usuario = new Usuario();
        return "";
    }
    
    public void erroLogin(String mensagem) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(mensagem, ""));
    }
    
    public void validarTrocarSenha(){
       if ((usuario.getLogin()!=null) && (usuario.getSenha()==null)){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Login Invalido."));
        }else{
           try {
                usuario.setSenha(Criptografia.encript(usuario.getSenha()));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UsuarioLogadoMB.class.getName()).log(Level.SEVERE, null, ex);
            }
            UsuarioFacade  usuarioFacade = new UsuarioFacade();
            usuario = usuarioFacade.consultar(usuario.getLogin(), usuario.getSenha());
            if (usuario==null){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Acesso Negado."));
               usuario = new Usuario();
               
            }else{
                Map<String, Object> options = new HashMap<String, Object>();
                options.put("contentWidth", 270);
                RequestContext.getCurrentInstance().openDialog("alterarSenha", options, null);
            
            }
        }
        
    }
    
    public String confirmaNovaSenha() {
        if ((novaSenha.length() > 0) && (confirmaNovaSenha.length() > 0)) {
            if (novaSenha.equalsIgnoreCase(confirmaNovaSenha)) {
                UsuarioFacade usuarioFacade = new UsuarioFacade();
                try {
                    usuario.setSenha(Criptografia.encript(novaSenha));
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(UsuarioLogadoMB.class.getName()).log(Level.SEVERE, null, ex);
                }
                usuario = usuarioFacade.salvar(usuario);
                novaSenha = "";
                confirmaNovaSenha = "";
                usuario = new Usuario();
                RequestContext.getCurrentInstance().closeDialog(null);
                return "";
            } else {
                novaSenha = "";
                confirmaNovaSenha = "";
                usuario = new Usuario();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Acesso Negado."));
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Acesso Negado."));
        }
        return "";
    }
    public String cancelarTrocaSenha(){
        usuario = new Usuario();
        novaSenha="";
        confirmaNovaSenha="";
        return "index";
    }
    public String deslogar(){
        usuario.setIdusuario(null);
        Map sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();  
        sessionMap.clear();  
        return "index";
    }
    
    public void gerarIniciis(String nome){
        char inicial1 = nome.charAt(0);
        char inicial2 = 0;
        for(int i=0;i<nome.length();i++){
            if(nome.charAt(i)==' '){
                inicial2 = nome.charAt(i+1);
                i = nome.length()+10;
            }
        }
        iniciais = String.valueOf(inicial1).toUpperCase() + String.valueOf(inicial2).toUpperCase();
    }
    
   
    
}