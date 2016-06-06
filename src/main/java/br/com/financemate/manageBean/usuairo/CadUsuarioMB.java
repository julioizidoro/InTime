package br.com.financemate.manageBean.usuairo;

import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
import br.com.financemate.manageBean.usuairo.UsuarioMB;
import br.com.financemate.facade.DepartamentoFacade;
import br.com.financemate.facade.PerfilFacade;
import br.com.financemate.facade.UsuarioFacade;
import br.com.financemate.model.Departamento;
import br.com.financemate.model.Perfil;
import br.com.financemate.model.Subdepartamento;
import br.com.financemate.model.Usuario;
import br.com.financemate.util.Criptografia;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class CadUsuarioMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject 
    UsuarioLogadoMB usuarioLogadoBean;
    private Usuario usuario;
    private List<Departamento> listaDepartamento;
    private List<Perfil> listaPerfil;
    private List<Subdepartamento> listaSubdepartamento;
    private Departamento Departamento;
    
    
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        usuario =  (Usuario) session.getAttribute("usuario");
        session.removeAttribute("usuario");
        if (usuario == null) {
            usuario = new Usuario();
            usuario = new Usuario();
            usuario.setSenha("1");
            usuario.setSituacao("Ativo");
            Departamento = new Departamento();
        }else {
            Departamento = usuario.getSubdepartamento().getDepartamento();
        }
        try {
            gerarListaDepartamento();
             gerarListaPerfil("");
        } catch (SQLException ex) {
            Logger.getLogger(CadUsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Departamento> getListaDepartamento() throws SQLException {
        if(listaDepartamento==null){
            gerarListaDepartamento();
        }
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public List<Subdepartamento> getListaSubdepartamento() throws SQLException {
        return listaSubdepartamento;
    }

    public void setListaSubdepartamento(List<Subdepartamento> listaSubdepartamento) {
        this.listaSubdepartamento = listaSubdepartamento;
    }

    public List<Perfil> getListaPerfil() {
        return listaPerfil;
    }

    public void setListaPerfil(List<Perfil> listaPerfil) {
        this.listaPerfil = listaPerfil;
    }

    public UsuarioLogadoMB getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoMB usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public Departamento getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(Departamento Departamento) {
        this.Departamento = Departamento;
    }
    
    public void gerarListaDepartamento() throws SQLException{
        DepartamentoFacade departamentoFacade = new DepartamentoFacade();
        listaDepartamento = departamentoFacade.listar("", usuarioLogadoBean.getConfig().getDepartamento().getIddepartamento());
        if (listaDepartamento==null){
            listaDepartamento = new ArrayList<Departamento>();
        }
    }
    
    
    
    public void gerarListaPerfil(String nomeTipoacesso) throws SQLException{
        PerfilFacade perfilFacade = new PerfilFacade();
        listaPerfil = perfilFacade.listar(nomeTipoacesso);
        if (listaPerfil==null){
            listaPerfil = new ArrayList<Perfil>();
        }
    }
    
    public String salvar() throws SQLException{
        if(usuarioLogadoBean.getUsuario().getPerfil().getCadsubdepartamentoincluir()){
            UsuarioFacade usuarioFacade = new UsuarioFacade();
            try {
                usuario.setSenha(Criptografia.encript(usuario.getSenha()));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
            }
            usuarioFacade.salvar(usuario);
            usuario = new Usuario();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Cadastrado com Sucesso", ""));
            return "consUsuario";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    
     public String cancelar(){
           return "consUsuario";
     }
    
    
}