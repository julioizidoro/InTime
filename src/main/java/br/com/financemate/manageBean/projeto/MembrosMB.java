package br.com.financemate.manageBean.projeto;

import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
import br.com.financemate.facade.MembrosFacade;
import br.com.financemate.facade.UsuarioFacade;
import br.com.financemate.model.Membros;
import br.com.financemate.model.Projeto;
import br.com.financemate.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class MembrosMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private UsuarioLogadoMB usuarioLogadoBean;
    private List<Membros> listaMembros;
    private Membros membros;
    private List<Usuario> listaUsuario;
    private int idUsuario;
    private Projeto projeto;
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        projeto = (Projeto) session.getAttribute("projeto");
        session.removeAttribute("projeto");
        idUsuario = usuarioLogadoBean.getUsuario().getIdusuario();
        if (membros==null){
            membros = new Membros();
            membros.setProjeto(projeto);
        }
        if (membros!=null){
            gerarListaMembros();
        }else{
            listaMembros = new ArrayList<Membros>();
        }
    }
    

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public List<Membros> getListaMembros() {
        if (listaMembros==null){
            gerarListaMembros();
        }
        return listaMembros;
    }

    public void setListaM(List<Membros> listaMembros) {
        this.listaMembros = listaMembros;
    }

    public List<Usuario> getListaUsuario() {
        if (listaUsuario==null){
            gerarListaUsuario();
        }
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public Membros getMembros() {
        return membros;
    }

    public void setMembros(Membros membros) {
        this.membros = membros;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
    
    
    
    
    public void gerarListaUsuario() {
        UsuarioFacade usuarioFacade = new UsuarioFacade();
        listaUsuario = usuarioFacade.listarAtivos();
        if (listaUsuario == null) {
            listaUsuario = new ArrayList<Usuario>();
        }
    }
    
    public void gerarListaMembros() {
        String sql = "Select m from Membros m where m.projeto.idprojeto=" + projeto.getIdprojeto();
        MembrosFacade MembrosFacade = new MembrosFacade();
        listaMembros = MembrosFacade.listar(sql);
        if (listaMembros == null) {
            listaMembros = new ArrayList<Membros>();
         }
    }
    
    public String salvar(){
        
        RequestContext.getCurrentInstance().closeDialog(null);
        return "";
    }
    
    public void adicionarMembro(Projeto projetos){
        UsuarioFacade usuarioFacade = new UsuarioFacade();
        Usuario usuario = usuarioFacade.consultar(idUsuario);
        membros.setUsuario(usuario);
        MembrosFacade membrosFacade = new MembrosFacade();
        membrosFacade.salvar(membros);
        membros = new Membros();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("projeto", projetos);
        gerarListaMembros();
        membros = new Membros();
        membros.setProjeto(projeto);
    }
    
    
    public void excluir(Membros membros){
        MembrosFacade membrosFacade = new MembrosFacade();
        membrosFacade.excluir(membros.getIdmembros());
        gerarListaMembros();
    
    }
}