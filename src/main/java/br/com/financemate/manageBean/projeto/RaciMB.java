package br.com.financemate.manageBean.projeto;


import br.com.financemate.facade.MembrosFacade;
import br.com.financemate.facade.NotificacaoFacade;
import br.com.financemate.facade.ParticipanteFacade;
import br.com.financemate.facade.RaciFacade;
import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
import br.com.financemate.model.Atividademodulo;
import br.com.financemate.model.Atividades;
import br.com.financemate.model.Membros;
import br.com.financemate.model.Modulos;
import br.com.financemate.model.Notificacao;
import br.com.financemate.model.Participante;
import br.com.financemate.model.Raci;
import br.com.financemate.repository.AtividadesRepository;
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
public class RaciMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private UsuarioLogadoMB usuarioLogadoBean;
    private Raci raci;
    private List<Raci> listaRaci;
    private Atividademodulo atividademodulo;
    private List<Membros> listaMembros;
    private Membros membros;
    private AtividadesRepository atividadesRepository;
    
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        atividademodulo = (Atividademodulo) session.getAttribute("atividademodulo");
        session.removeAttribute("atividademodulo");
        gerarListaMembros();
        if (raci==null){
            raci = new Raci();
            raci.setAtividademodulo(atividademodulo);
        }
        if (atividademodulo!=null){
            gerarListaRaci();
        }else{
            listaRaci = new ArrayList<Raci>();
        }
    }

    public UsuarioLogadoMB getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoMB usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public List<Raci> getListaRaci() {
        if (listaRaci==null){
            gerarListaRaci();
        }
        return listaRaci;
    }

    public void setListaRaci(List<Raci> listaRaci) {
        this.listaRaci = listaRaci;
    }

    public Atividademodulo getAtividademodulo() {
        return atividademodulo;
    }

    public void setAtividademodulo(Atividademodulo atividademodulo) {
        this.atividademodulo = atividademodulo;
    }

    public Raci getRaci() {
        return raci;
    }

    public void setRaci(Raci raci) {
        this.raci = raci;
    }

    public List<Membros> getListaMembros() {
        return listaMembros;
    }

    public void setListaMembros(List<Membros> listaMembros) {
        this.listaMembros = listaMembros;
    }

    public Membros getMembros() {
        return membros;
    }

    public void setMembros(Membros membros) {
        this.membros = membros;
    }

    

    
    
    
    private void gerarListaRaci() {
        String sql = "Select r from Raci r where r.atividademodulo.idatividademodulo=" + atividademodulo.getIdatividademodulo();
        RaciFacade raciFacade = new RaciFacade();
        listaRaci = raciFacade.listar(sql);
        if (listaRaci == null) {
            listaRaci = new ArrayList<Raci>();
         }
    }
    
    public void salvarRaci(Modulos modulos){
        RaciFacade raciFacade = new RaciFacade(); 
        raci.setMembros(membros);
        raciFacade.salvar(raci);
        modulos = atividademodulo.getModulos();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("modulos", modulos);
        gerarListaRaci();
    }
    
    public void gerarListaMembros() {
        String sql = "Select m from Membros m where m.projeto.idprojeto=" + atividademodulo.getModulos().getProjeto().getIdprojeto();
        MembrosFacade MembrosFacade = new MembrosFacade();
        listaMembros = MembrosFacade.listar(sql);
        if (listaMembros == null) {
            listaMembros = new ArrayList<Membros>();
         }
    }
    
    public void excluir(Raci raci){
        RaciFacade raciFacade = new RaciFacade();
        raciFacade.excluir(raci.getIdraci());
        gerarListaRaci();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Excluido com Sucesso", ""));
    }
    
    public String confirmar(){
        if (listaRaci!=null){
            RaciFacade raciFacade = new RaciFacade();
            for(int i=0;i<listaRaci.size();i++){
                raciFacade.salvar(listaRaci.get(i));
                String sql = "Select a From Atividades a where a.idatividademodulo=" + listaRaci.get(i).getAtividademodulo().getIdatividademodulo();
                Atividades atividades = atividadesRepository.find(sql);
                if (listaRaci.get(i).getR()){
                    if (atividades!=null){
                        atividades.setUsuario(listaRaci.get(i).getMembros().getUsuario());
                        salvar(atividades);
                    }
                }else {
                    if ((listaRaci.get(i).getA()) || listaRaci.get(i).getC() || listaRaci.get(i).getI()){
                        Participante participante = new Participante();
                        participante.setAtividades(atividades);
                        participante.setUsuario(listaRaci.get(i).getMembros().getUsuario());
                        ParticipanteFacade participanteFacade = new ParticipanteFacade();
                        participante = participanteFacade.salvar(participante);
                        NotificacaoFacade notificacaoFacade = new NotificacaoFacade();
                        Notificacao notificacao;
                        notificacao = new Notificacao();
                        notificacao.setLido(false);
                        notificacao.setUsuario(participante.getUsuario());
                        String texto = usuarioLogadoBean.getUsuario().getNome() + " Criou uma nova tarefa no prjeto . " + listaRaci.get(i).getAtividademodulo().getModulos().getProjeto().getNome();
                        notificacao.setTexto(texto);
                        notificacaoFacade.salvar(notificacao);
                    }
                    
                }
                
            }
        }
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