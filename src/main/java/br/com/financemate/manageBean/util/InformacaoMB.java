package br.com.financemate.manageBean.util;

import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
import br.com.financemate.bean.Formatacao;
import br.com.financemate.facade.ParticipanteFacade;
import br.com.financemate.model.Atividades;
import br.com.financemate.model.Participante;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Wolverine
 */

@Named("InformacaoMB")
@SessionScoped
public class InformacaoMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject 
    private UsuarioLogadoMB usuarioLogadoBean;
    private List<Atividades> listaInformacao;
    

    public UsuarioLogadoMB getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoMB usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public List<Atividades> getListaInformacao() {
        return listaInformacao;
    }

    public void setListaInformacao(List<Atividades> listaInformacao) {
        this.listaInformacao = listaInformacao;
    }

   
    public void gerarListaInformacao(){
        ParticipanteFacade participanteFacade = new ParticipanteFacade();
        String sql = "Select p from Participante p where p.atividades.situacao=FALSE" + 
                " and p.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
                " order by p.atividades.prazo, p.atividades.prioridade, p.atividades.nome";
        List<Participante> listaParticipantes = participanteFacade.listar(sql);
        if (listaParticipantes!=null){
            listaInformacao = new ArrayList<Atividades>();
            for(int i=0;i<listaParticipantes.size();i++){
                listaInformacao.add(listaParticipantes.get(i).getAtividades());
            }
        }
    }
    
    public String atrasadas(Atividades atividade) {
        if (atividade.getPrazo() != null) {
            Date data = new Date();
            String sData = Formatacao.ConvercaoDataPadrao(data);
            data = Formatacao.ConvercaoStringDataBrasil(sData);
            boolean bdata = atividade.getPrazo().before(data);
            if (bdata) {
                return "atrasado";
            } else {
                return "normal";
            }
        }
        return "normal";
    }
    
}