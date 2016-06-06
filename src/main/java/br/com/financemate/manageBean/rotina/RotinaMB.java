package br.com.financemate.manageBean.rotina;

import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
import br.com.financemate.facade.ParticipanteFacade;
import br.com.financemate.facade.RotinaAtividadeFacade;
import br.com.financemate.facade.RotinaFacade;
import br.com.financemate.facade.RotinaclienteFacade;
import br.com.financemate.model.Rotina;
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

/**
 *
 * @author Kamila
 */
@Named("RotinaMB")
@ViewScoped
public class RotinaMB  implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private UsuarioLogadoMB usuarioLogadoBean;
    private String nomeRotina;
    private List<Rotina> listaRotina;
    private AtividadesRepository atividadesRepository;
    
    @PostConstruct
    public void init(){
        gerarListaRotina();
    }

    public UsuarioLogadoMB getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoMB usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    
    public String getNomeRotina() {
        return nomeRotina;
    }

    public void setNomeRotina(String nomeRotina) {
        this.nomeRotina = nomeRotina;
    }

   
    public List<Rotina> getListaRotina() {
        return listaRotina;
    }

    public void setListaRotina(List<Rotina> listaRotina) {
        this.listaRotina = listaRotina;
    }

    
    public void gerarListaRotina()  {
        if (nomeRotina == null) {
            nomeRotina = "";
        }
        RotinaFacade rotinaFacade = new RotinaFacade();
        listaRotina = rotinaFacade.listar(nomeRotina);
        if (listaRotina == null) {
            listaRotina = new ArrayList<Rotina>();
        }
    }
    
    public String pesquisarNome(){
        gerarListaRotina();
        return "";
    }
    
    public String novo() {
        if (usuarioLogadoBean.getUsuario().getPerfil().getCadrotinaincluir()) {
            return "cadRotina";
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
            return "";
        }
    }
    
    public String editar(Rotina rotina) {
        if (usuarioLogadoBean.getUsuario().getPerfil().getCadrotinaeditar()) {
            if (rotina != null) {
                FacesContext fc = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
                session.setAttribute("rotina", rotina);
                return "cadRotina";
            } else {
                return "";
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
            return "";
        }
    }
    
    public String excluir(Rotina rotinaExcluir) {
        if (usuarioLogadoBean.getUsuario().getPerfil().getCadrotinaexcluir()) {
            RotinaFacade rotinaFacade = new RotinaFacade();
            Rotina rotina = rotinaExcluir;
            if (rotina != null) {
                if (rotina.getRotinaatividadeList() != null) {
                    for (int i = 0; i < rotina.getRotinaatividadeList().size(); i++) {
                        if (rotina.getRotinaatividadeList().get(i).getAtividades().getParticipanteList()!=null){
                            for(int n=0;n<rotina.getRotinaatividadeList().get(i).getAtividades().getParticipanteList().size();n++){
                                ParticipanteFacade participanteFacade = new ParticipanteFacade();
                                participanteFacade.excluir(rotina.getRotinaatividadeList().get(i).getAtividades().getParticipanteList().get(n).getIdparticipante());
                            }
                        }
                        atividadesRepository.remove(rotina.getRotinaatividadeList().get(i).getAtividades().getIdatividades().longValue());
                    }
                }
                RotinaAtividadeFacade rotinaAtividadeFacade = new RotinaAtividadeFacade();
                if ((rotina.getRotinaatividadeList()!=null) && (rotina.getRotinaatividadeList().size()>0)){
                    rotinaAtividadeFacade.excluir(rotina.getRotinaatividadeList().get(0).getIdrotinaatividade());
                }
                if (rotina.getRotinaclienteList() != null) {
                    RotinaclienteFacade rotinaclienteFacade = new RotinaclienteFacade();
                    for (int i = 0; i < rotina.getRotinaclienteList().size(); i++) {
                        rotinaclienteFacade.Excluir(rotina.getRotinaclienteList().get(i).getIdrotinacliente());
                    }
                }
                rotinaFacade.excluir(rotina.getIdrotina());
                FacesMessage mensagem = new FacesMessage("Sucesso! ", "Rotina Excluída");
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
                listaRotina.remove(rotinaExcluir);
                return "consRotina";
            }
        } else {
           FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
            return "";
        }
        return "";
    }
    
    
}