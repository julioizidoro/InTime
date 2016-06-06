package br.com.financemate.manageBean.atividades;


import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
import br.com.financemate.manageBean.util.MenuMB;
import br.com.financemate.model.Atividades;
import br.com.financemate.repository.AtividadesRepository;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class CadAtividadeParticularMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private ListaAtividadesMB listaAtividadesMB;
    @Inject
    private UsuarioLogadoMB usuarioLogadoBean;
    @Inject
    private MenuMB menuMB;
    private Atividades atividades;
    private AtividadesRepository atividadesRepository;
    
    
    @PostConstruct
    public void init() {
        this.atividades = new Atividades();
        getUsuarioLogadoBean();
        iniciarAtividade();
        
    }

    public ListaAtividadesMB getListaAtividadesMB() {
        return listaAtividadesMB;
    }

    public void setListaAtividadesMB(ListaAtividadesMB listaAtividadesMB) {
        this.listaAtividadesMB = listaAtividadesMB;
    }

    public UsuarioLogadoMB getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoMB usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public MenuMB getMenuMB() {
        return menuMB;
    }

    public void setMenuMB(MenuMB menuMB) {
        this.menuMB = menuMB;
    }

    public Atividades getAtividades() {
        return atividades;
    }

    public void setAtividades(Atividades atividades) {
        this.atividades = atividades;
    }
    
    public void iniciarAtividade(){
            atividades = new Atividades();
            atividades.setSubdepartamento(usuarioLogadoBean.getConfig().getSubdepartamento());
            atividades.setEstado("Play");
            atividades.setInicio(BigInteger.valueOf(0));
            atividades.setTempo(0);
            atividades.setMostratempo("00:00");
            atividades.setCliente(usuarioLogadoBean.getConfig().getCliente());
            atividades.setPrioridade("3-normal");
            atividades.setPrazo(new Date());
            atividades.setTipo("A");
            atividades.setUsuario(usuarioLogadoBean.getUsuario());
            atividades.getComentariosList();
            atividades.setSituacao(false);
            atividades.setNome("");
    }
    
     public String salvarParticular() {
        if(usuarioLogadoBean.getUsuario().getPerfil().getTarefasincluir()){
            atividades = salvar(atividades);
            iniciarAtividade();
            return "";
        }else{
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }
        
    }
     
      public String fecharDialogAtividades(){
        listaAtividadesMB.gerarLitaAtividades();
        menuMB.gerarLitaNotificacao();
        RequestContext.getCurrentInstance().closeDialog(null);
        return "";
     }
      
      public Atividades salvar(Atividades atv){
        if (atv.getIdatividades()==null){
            atividadesRepository.create(atv);
        }else atividadesRepository.update(atv);
        return atv;
    }
}