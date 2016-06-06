
package br.com.financemate.manageBean.atividades;



import br.com.financemate.facade.NotificacaoFacade;
import br.com.financemate.facade.ParticipanteFacade;
import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
import br.com.financemate.manageBean.util.MenuMB;
import br.com.financemate.model.Atividades;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Comentarios;
import br.com.financemate.model.Departamento;
import br.com.financemate.model.Notificacao;
import br.com.financemate.model.Participante;
import br.com.financemate.model.Subdepartamento;
import br.com.financemate.model.Usuario;
import br.com.financemate.repository.AtividadesRepository;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class CadAtividadeMB implements Serializable{
    
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
    @Inject
    private AtividadeMB atividadeMB;
    private Atividades atividades;
    private List<Comentarios> listaComentarios;
    private Usuario usuario;
    private Cliente cliente;
    private Departamento departamento;
    private Subdepartamento subdepartamento;
    private List<Usuario> listaParticipante;
    private int idExecutor;
    private int tipo=0;
    private Usuario usuarioParticipante;
    private AtividadesRepository atividadesRepository;

    @PostConstruct
    public void init() {
        this.departamento = new Departamento();
        this.subdepartamento = new Subdepartamento();
        this.usuario = new Usuario();
        usuario = usuarioLogadoBean.getUsuario();
        getUsuarioLogadoBean();
        iniciarNovaAtividade();
        if(listaParticipante==null){
           listaParticipante = new ArrayList<Usuario>();
        }
    }
    
    

    public Atividades getAtividades() {
        return atividades;
    }

    public void setAtividades(Atividades atividades) {
        this.atividades = atividades;
    }

    public List<Comentarios> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(List<Comentarios> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public Usuario getUsuario() {
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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Subdepartamento getSubdepartamento() {
        return subdepartamento;
    }

    public void setSubdepartamento(Subdepartamento subdepartamento) {
        this.subdepartamento = subdepartamento;
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

    public List<Usuario> getListaParticipante() {
        return listaParticipante;
    }

    public void setListaParticipante(List<Usuario> listaParticipante) {
        this.listaParticipante = listaParticipante;
    }

    public Usuario getUsuarioParticipante() {
        return usuarioParticipante;
    }

    public void setUsuarioParticipante(Usuario usuarioParticipante) {
        this.usuarioParticipante = usuarioParticipante;
    }

    public AtividadeMB getAtividadeMB() {
        return atividadeMB;
    }

    public void setAtividadeMB(AtividadeMB atividadeMB) {
        this.atividadeMB = atividadeMB;
    }

    

    public int getIdExecutor() {
        return idExecutor;
    }

    public void setIdExecutor(int idExecutor) {
        this.idExecutor = idExecutor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public void iniciarNovaAtividade() {
        atividades = new Atividades();
        atividades.setTempo(0);
        atividades.setEstado("Play");
        atividades.setMostratempo("00:00");
        usuario = usuarioLogadoBean.getUsuario();
        if (cliente == null) {
            cliente = usuarioLogadoBean.getConfig().getFinacemate();
        }
        departamento = usuarioLogadoBean.getUsuario().getSubdepartamento().getDepartamento();
        subdepartamento = usuarioLogadoBean.getUsuario().getSubdepartamento();
        atividades.setPrazo(new Date());
        atividades.setPrioridade("3-normal");
    }
    
    public void adicionarParcitipante() {
        if (usuarioParticipante != null) {
            boolean naoExiste = true;
            for (int i = 0; i < listaParticipante.size(); i++) {
                int idIncluir = usuarioParticipante.getIdusuario();
                int idLista = listaParticipante.get(i).getIdusuario();
                if (idIncluir == idLista) {
                    naoExiste = true;
                    i = 1000;
                }
            }
            if (naoExiste) {
                listaParticipante.add(usuarioParticipante);
            }
        }
    }
    
    public void excluirParticipante(){
        if (usuarioParticipante != null) {
            for (int i = 0; i < listaParticipante.size(); i++) {
                int idIncluir = usuarioParticipante.getIdusuario();
                int idLista = listaParticipante.get(i).getIdusuario();
                if (idIncluir == idLista) {
                    listaParticipante.remove(i);
                    i = 1000;
                }
            }
        }
    }
    
        
    
    public String salvar() {
        if(usuarioLogadoBean.getUsuario().getPerfil().getTarefasincluir()){
            idExecutor = usuario.getIdusuario();
            atividades.setSubdepartamento(subdepartamento);
            atividades.setEstado("Play");
            atividades.setInicio(BigInteger.valueOf(0));
            atividades.setTempo(0);
            atividades.setMostratempo("00:00");
            atividades.setCliente(cliente);
            atividades.setTipo("A");
            atividades.getComentariosList();
            atividades.setUsuario(usuario);
            atividades.setSituacao(Boolean.FALSE);
            atividades = salvar(atividades);
            salvarUsuarioAtividade();
            iniciarNovaAtividade();
            return "";
        }else{
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }
    
    public void salvarUsuarioAtividade() {
        
        NotificacaoFacade notificacaoFacade = new NotificacaoFacade();
        String texto=usuarioLogadoBean.getUsuario().getNome() + " criou uma nova tarefa.";
        int idUsuarioLogado = usuarioLogadoBean.getUsuario().getIdusuario();
        int idUsuarioAtividade = usuario.getIdusuario();
        if (idUsuarioAtividade!=idUsuarioLogado) {
            Notificacao notificacao = new Notificacao();
            notificacao.setLido(false);
            notificacao.setUsuario(usuario);
            notificacao.setTexto(texto);
            notificacaoFacade.salvar(notificacao);
        }
        ParticipanteFacade participanteFacade = new ParticipanteFacade();
        for (int i = 0; i < listaParticipante.size(); i++) {
            Notificacao notificacao;
            notificacao = new Notificacao();
            notificacao.setLido(false);
            notificacao.setUsuario(listaParticipante.get(i));
            texto = usuarioLogadoBean.getUsuario().getNome() + " Criou uma nova tarefa.";
            notificacao.setTexto(texto);
            notificacaoFacade.salvar(notificacao);
            Participante participante = new Participante();
            participante.setAtividades(atividades);
            participante.setUsuario(listaParticipante.get(i));
            participanteFacade.salvar(participante);
            
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