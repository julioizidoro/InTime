package br.com.financemate.manageBean.util;

import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
import br.com.financemate.facade.NotificacaoFacade;
import br.com.financemate.model.Notificacao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@Named("MenuMB")
@SessionScoped
public class MenuMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    UsuarioLogadoMB usuarioLogadoBean;
    @Inject
    private InformacaoMB informacaoMB;
    private List<Notificacao> listaNotificacao;
    private String quantidade;
    

    public UsuarioLogadoMB getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoMB usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }


    public List<Notificacao> getListaNotificacao() {
        if (listaNotificacao==null){
            gerarLitaNotificacao();
        }
        return listaNotificacao;
    }

    public void setListaNotificacao(List<Notificacao> listaNotificacao) {
        this.listaNotificacao = listaNotificacao;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
    
    
    
    
    public String cliente(){
        gerarLitaNotificacao();
        if(usuarioLogadoBean.getUsuario().getPerfil().getCadcliente()){
            return"consCliente";    
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    
    public String departamento(){
        gerarLitaNotificacao();
        if(usuarioLogadoBean.getUsuario().getPerfil().getCaddepartamento()){
            return"consDepartamento";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    
    
     public String subdepartamento(){
         gerarLitaNotificacao();
         if(usuarioLogadoBean.getUsuario().getPerfil().getCadsubdepartamento()){
            return"consSubdepartamento";
         }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
     
      
    
      
      
    public String financeiro(){
      gerarLitaNotificacao();
      return"gestaoFinanceira";  
    }
    
    public String contabilidade(){
      gerarLitaNotificacao();
      return"contabilidade";  
    }
    
    public String tercerizacao(){
      gerarLitaNotificacao();
      return"tercerizacao";  
    }
    
    public String usuario(){
        gerarLitaNotificacao();
        if(usuarioLogadoBean.getUsuario().getPerfil().getCadusuario()){
            return"consUsuario";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    
     public String perfil(){
         gerarLitaNotificacao();
         if(usuarioLogadoBean.getUsuario().getPerfil().getCadperfil()){
            return"consPerfil";
         }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
     
     public String informacao(){
       gerarLitaNotificacao();
       informacaoMB.gerarListaInformacao();
       return "informacoes";
     }
     
     public void gerarLitaNotificacao(){
         NotificacaoFacade notificacaoFacade = new NotificacaoFacade();
         listaNotificacao = notificacaoFacade.listar(usuarioLogadoBean.getUsuario().getIdusuario());
         if (listaNotificacao==null){
             listaNotificacao = new ArrayList<Notificacao>();
             
         } 
         if (listaNotificacao.size()<10){
            quantidade=  "  0" + String.valueOf(listaNotificacao.size()) + " ";
        }else quantidade = " " + String.valueOf(listaNotificacao.size()) + " ";
         
     }
     
     public void concluirNotificacao(Notificacao notificacao){
         NotificacaoFacade notificacaoFacade = new NotificacaoFacade();
         notificacaoFacade.salvar(notificacao);
         gerarLitaNotificacao();
     }
     
      public void concluirNotificacaoIndividual(Notificacao notificacao){
        NotificacaoFacade notificacaoFacade = new NotificacaoFacade();
         notificacao.setLido(true);
         notificacaoFacade.salvar(notificacao);
         
         gerarLitaNotificacao();
         
     }
     
     public void concluirListaNotificacao(){
         if (listaNotificacao!=null){
             NotificacaoFacade notificacaoFacade = new NotificacaoFacade();
             for(int i=0;i<listaNotificacao.size();i++){
                 listaNotificacao.get(i).setLido(true);
                 notificacaoFacade.salvar(listaNotificacao.get(i));
             }
             gerarLitaNotificacao();
         }
     }
     
    public String processos() {
        gerarLitaNotificacao();
        return "consProcessos";
    }

    public String projeto() {
        gerarLitaNotificacao();
        return "consProjeto";
    }
     
    public String relatorioporcliente() {
        gerarLitaNotificacao();
        RequestContext.getCurrentInstance().openDialog("relatorioPorCliente");
        return "";
    }
    
    public String relatorioatividadesconcluidas() {
        gerarLitaNotificacao();
        RequestContext.getCurrentInstance().openDialog("relatorioAtividadesConcluidas");
        return "";
    }
    
    
   public String rotina(){
        if(usuarioLogadoBean.getUsuario().getPerfil().getCadrotina()){  
            gerarLitaNotificacao();
            return"consRotina";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
     
 }