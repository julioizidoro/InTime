package br.com.financemate.manageBean.atividades;


import br.com.financemate.bean.Formatacao;
import br.com.financemate.facade.ComentariosFacade;
import br.com.financemate.facade.NotificacaoFacade;
import br.com.financemate.facade.ProcessoAtividadeFacade;
import br.com.financemate.facade.ProcessoSituacaoFacade;
import br.com.financemate.facade.RotinaAtividadeFacade;
import br.com.financemate.facade.RotinaclienteFacade;
import br.com.financemate.facade.UsuarioFacade;
import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
import br.com.financemate.manageBean.util.MenuMB;
import br.com.financemate.model.Atividades;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Comentarios;
import br.com.financemate.model.Departamento;
import br.com.financemate.model.Notificacao;
import br.com.financemate.model.Processoatividade;
import br.com.financemate.model.Processosituacao;
import br.com.financemate.model.Rotinaatividade;
import br.com.financemate.model.Rotinacliente;
import br.com.financemate.model.Subdepartamento;
import br.com.financemate.model.Usuario;
import br.com.financemate.repository.AtividadesRepository;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
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
@Named("AtividadeMB")
@SessionScoped
public class AtividadeMB implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private UsuarioLogadoMB usuarioLogadoBean;
    @Inject
    private MenuMB menuMB;
    @Inject 
    private ListaAtividadesMB listaAtividadesMB;
    private Atividades atividades;
    private List<Atividades> listaAtividadesGeral;
    private List<Comentarios> listaComentarios;
    private List<Usuario> listaUsuario;
    private Usuario usuario;
    private Cliente cliente;
    private Departamento departamento;
    private Subdepartamento subdepartamento;
    private String titulo="Tarefas de Hoje";
    private String linha;
    private boolean checkConcluidas=false;
    private Comentarios comentarios;
    private String nomeAtividades;
    private int tipo=0;
    private String visualizar;
    private String corMenuSelecionadoDia="fonteconomica";
    private String corMenuSelecionadoSemana="fonteconomica";
    private String corMenuSelecionadoAtrasada="fonteconomica";
    private String corMenuSelecionadoTodas="fonteconomica";
    private String corMenuSelecionadoAmanha="fonteconomica";
    private String corMenuSelecionadoDois="fonteconomica";
    private String corMenuSelecionadoTres="fonteconomica";
    private String corMenuSelecionadoQuatro="fonteconomica";
    private String corMenuSelecionadoCinco="fonteconomica";
    private String corMenuSelecionadoSeis="fonteconomica";
    private String corMenuSelecionadoSete="fonteconomica";
    private String corMenuSelecionadoDepartamento="fonteconomica";
    private String corMenuSituacao="fonteconomica";
    private AtividadesRepository atividadesRepository;
    
    

    @PostConstruct
    public void init()  {
        getListaAtividadesMB();
        listaAtividadesMB.setMostrar(false);
        atividades = new Atividades();
        comentarios = new Comentarios();
        carregarTituloAtividades(atividades);
    }

    public List<Atividades> getListaAtividadesGeral()  {
        if (listaAtividadesGeral==null){
           carregarListaGeral();
        }
        return listaAtividadesGeral;
    }

    public void setListaAtividadesGeral(List<Atividades> listaAtividadesGeral) {
        this.listaAtividadesGeral = listaAtividadesGeral;
    }

    public String getVisualizar() {
        return visualizar;
    }

    public void setVisualizar(String visualizar) {
        this.visualizar = visualizar;
    }

    public String getCorMenuSelecionadoDia() {
        return corMenuSelecionadoDia;
    }

    public void setCorMenuSelecionadoDia(String corMenuSelecionadoDia) {
        this.corMenuSelecionadoDia = corMenuSelecionadoDia;
    }

    public String getCorMenuSelecionadoSemana() {
        return corMenuSelecionadoSemana;
    }

    public String getCorMenuSelecionadoDepartamento() {
        return corMenuSelecionadoDepartamento;
    }

    public void setCorMenuSelecionadoDepartamento(String corMenuSelecionadoDepartamento) {
        this.corMenuSelecionadoDepartamento = corMenuSelecionadoDepartamento;
    }

    
    public void setCorMenuSelecionadoSemana(String corMenuSelecionadoSemana) {
        this.corMenuSelecionadoSemana = corMenuSelecionadoSemana;
    }

    public String getCorMenuSelecionadoAtrasada() {
        return corMenuSelecionadoAtrasada;
    }

    public void setCorMenuSelecionadoAtrasada(String corMenuSelecionadoAtrasada) {
        this.corMenuSelecionadoAtrasada = corMenuSelecionadoAtrasada;
    }

    public String getCorMenuSelecionadoTodas() {
        return corMenuSelecionadoTodas;
    }

    public void setCorMenuSelecionadoTodas(String corMenuSelecionadoTodas) {
        this.corMenuSelecionadoTodas = corMenuSelecionadoTodas;
    }

    public String getCorMenuSelecionadoAmanha() {
        return corMenuSelecionadoAmanha;
    }

    public void setCorMenuSelecionadoAmanha(String corMenuSelecionadoAmanha) {
        this.corMenuSelecionadoAmanha = corMenuSelecionadoAmanha;
    }

    public String getCorMenuSelecionadoDois() {
        return corMenuSelecionadoDois;
    }

    public void setCorMenuSelecionadoDois(String corMenuSelecionadoDois) {
        this.corMenuSelecionadoDois = corMenuSelecionadoDois;
    }

    public String getCorMenuSelecionadoTres() {
        return corMenuSelecionadoTres;
    }

    public void setCorMenuSelecionadoTres(String corMenuSelecionadoTres) {
        this.corMenuSelecionadoTres = corMenuSelecionadoTres;
    }

    public String getCorMenuSelecionadoQuatro() {
        return corMenuSelecionadoQuatro;
    }

    public void setCorMenuSelecionadoQuatro(String corMenuSelecionadoQuatro) {
        this.corMenuSelecionadoQuatro = corMenuSelecionadoQuatro;
    }

    public String getCorMenuSelecionadoCinco() {
        return corMenuSelecionadoCinco;
    }

    public void setCorMenuSelecionadoCinco(String corMenuSelecionadoCinco) {
        this.corMenuSelecionadoCinco = corMenuSelecionadoCinco;
    }

    public String getCorMenuSelecionadoSeis() {
        return corMenuSelecionadoSeis;
    }

    public void setCorMenuSelecionadoSeis(String corMenuSelecionadoSeis) {
        this.corMenuSelecionadoSeis = corMenuSelecionadoSeis;
    }

    public String getCorMenuSelecionadoSete() {
        return corMenuSelecionadoSete;
    }

    public void setCorMenuSelecionadoSete(String corMenuSelecionadoSete) {
        this.corMenuSelecionadoSete = corMenuSelecionadoSete;
    }

    public String getCorMenuSituacao() {
        retornarCorMenu();
        return corMenuSituacao;
    }

    public void setCorMenuSituacao(String corMenuSituacao) {
        this.corMenuSituacao = corMenuSituacao;
    }


    public List<Comentarios> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(List<Comentarios> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public Comentarios getComentarios() {
        return comentarios;
    }

    public void setComentarios(Comentarios comentarios) {
        this.comentarios = comentarios;
    }
    
    public UsuarioLogadoMB getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoMB usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public Atividades getAtividades() {
        return atividades;
    }

    public void setAtividades(Atividades departamento) {
        this.atividades = departamento;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNomeAtividades() {
        return nomeAtividades;
    }

    public void setNomeAtividades(String nomeAtividades) {
        this.nomeAtividades = nomeAtividades;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public MenuMB getMenuMB() {
        return menuMB;
    }

    public void setMenuMB(MenuMB menuMB) {
        this.menuMB = menuMB;
    }

    public ListaAtividadesMB getListaAtividadesMB() {
        return listaAtividadesMB;
    }

    public void setListaAtividadesMB(ListaAtividadesMB listaAtividadesMB) {
        this.listaAtividadesMB = listaAtividadesMB;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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


    public boolean isCheckConcluidas() {
        return checkConcluidas;
    }

    public void setCheckConcluidas(boolean checkConcluidas) {
        this.checkConcluidas = checkConcluidas;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
    
    
    
    
    public String novo(){
        menuMB.gerarLitaNotificacao();
        if(usuarioLogadoBean.getUsuario().getPerfil().getTarefasincluir()){
            atividades = new Atividades();
            atividades.setTempo(0);
            atividades.setEstado("Play");
            atividades.setMostratempo("00:00");
            cliente = usuarioLogadoBean.getConfig().getFinacemate();
            atividades.setPrazo(new Date());
            atividades.setPrioridade("3-normal");
            tipo = 0;
            Map<String,Object> options = new HashMap<String, Object>();
            options.put("contentWidth", 580);
            RequestContext.getCurrentInstance().openDialog("cadastroTarefa", options, null);
        }else{
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";
    }
    
    public String novoParticular(){
        menuMB.gerarLitaNotificacao();
        if(usuarioLogadoBean.getUsuario().getPerfil().getTarefasincluir()){
            atividades = new Atividades();
            atividades.setTempo(0);
            atividades.setEstado("Play");
            atividades.setMostratempo("00:00");
            atividades.setPrazo(new Date());
            atividades.setPrioridade("3-normal");
            tipo=1;
            Map<String,Object> options = new HashMap<String, Object>();
            options.put("contentWidth", 430);
            RequestContext.getCurrentInstance().openDialog("cadastroTarefaParticular", options, null);
        }else{
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        
        return "";
    }
    
    
    public String mostarAtividadesDia(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadesMB.getListaAtividadedia();
        listaAtividadesMB.setAtividadeMenu("dia");
        listaAtividadesMB.gerarLitaAtividades();
        menuMB.gerarLitaNotificacao();
        titulo="Hoje " + Formatacao.ConvercaoDataIntime(new Date());
        retornarCorMenu();
        if (listaAtividadesMB.isMostrar()){
            listaAtividadesMB.setMostrar(false);
            return "";
        }else return "inicial";
    }
    
    public String mostarAtividadesSemana(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadesMB.getListaAtividadeSemana();
        listaAtividadesMB.setAtividadeMenu("semana");
        listaAtividadesMB.gerarLitaAtividades();
        menuMB.gerarLitaNotificacao();
        Date data = Formatacao.SomarDiasData(new Date(), 7);
        titulo="Semana ";
        retornarCorMenu();
        if (listaAtividadesMB.isMostrar()){
            listaAtividadesMB.setMostrar(false);
            return "";
        }else return "inicial";
    }
    
    public String mostarAtividadesAtrasadas(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadesMB.getListaAtividadeAtrasada();
        listaAtividadesMB.setAtividadeMenu("atrasada");
        listaAtividadesMB.gerarLitaAtividades();
        menuMB.gerarLitaNotificacao();
         titulo="Atrasadas";
         retornarCorMenu();
        if (listaAtividadesMB.isMostrar()){
            listaAtividadesMB.setMostrar(false);
            return "";
        }else return "inicial";
    }
    
    public String mostarTodasAtividades(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadesMB.getListaAtividadeTodas();
        listaAtividadesMB.setAtividadeMenu("todas");
        listaAtividadesMB.gerarLitaAtividades();
        menuMB.gerarLitaNotificacao();
        titulo="Todas";
        retornarCorMenu();
        if (listaAtividadesMB.isMostrar()){
            listaAtividadesMB.setMostrar(false);
            return "";
        }else return "inicial";
    }
    
    public String mostarAtividadesDepartamento(){
        menuMB.gerarLitaNotificacao();
        if(usuarioLogadoBean.getUsuario().getPerfil().getTarefasoutros()){
            listaAtividadesMB.setAtividadeMenu("departamento");
            titulo="Equipe";
            retornarCorMenu();
            if (listaAtividadesMB.isMostrar()){
                listaAtividadesMB.setMostrar(false);
                return "";
        }else return "tarefaDepartamento";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!", "Acesso Negado"));
        }
        return "";
    }
    
    public String mostarAtividadesAmanha(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadesMB.getListaAtividadesAmanha();
        listaAtividadesMB.setAtividadeMenu("amanha");
        listaAtividadesMB.gerarLitaAtividades();
        menuMB.gerarLitaNotificacao();
        Date data = Formatacao.SomarDiasData(new Date(), 1);
        titulo="Amanhã " + Formatacao.ConvercaoDataIntime(data);
        retornarCorMenu();
        if (listaAtividadesMB.isMostrar()){
            listaAtividadesMB.setMostrar(false);
            return "";
        }else return "inicial";
    }
    
    
    public String mostarAtividadesDois(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadesMB.getListaAtividadesDois();
        Date data = new Date();
        data = Formatacao.SomarDiasData(data, 2);
        listaAtividadesMB.setAtividadeMenu("dois");
        listaAtividadesMB.gerarLitaAtividades();
        menuMB.gerarLitaNotificacao();
        titulo=Formatacao.diaSemanaEscrito(data) + " " + Formatacao.ConvercaoDataIntime(data);
        retornarCorMenu();
        if (listaAtividadesMB.isMostrar()){
            listaAtividadesMB.setMostrar(false);
            return "";
        }else return "inicial";
    }
    
    
    
    public String mostarAtividadesTres(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadesMB.getListaAtividadesTres();
        Date data = new Date();
        data = Formatacao.SomarDiasData(data, 3);
        listaAtividadesMB.setAtividadeMenu("tres");
        listaAtividadesMB.gerarLitaAtividades();
        menuMB.gerarLitaNotificacao();
        titulo=Formatacao.diaSemanaEscrito(data) + " " + Formatacao.ConvercaoDataIntime(data);
        retornarCorMenu();
        if (listaAtividadesMB.isMostrar()){
            listaAtividadesMB.setMostrar(false);
            return "";
        }else return "inicial";
    }
    
    
    public String mostarAtividadesQuatro(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadesMB.getListaAtividadesQuatro();
        Date data = new Date();
        data = Formatacao.SomarDiasData(data, 4);
        listaAtividadesMB.setAtividadeMenu("quatro");
        listaAtividadesMB.gerarLitaAtividades();
        menuMB.gerarLitaNotificacao();
        titulo=Formatacao.diaSemanaEscrito(data) + " " +  Formatacao.ConvercaoDataIntime(data);
        retornarCorMenu();
        if (listaAtividadesMB.isMostrar()){
            listaAtividadesMB.setMostrar(false);
            return "";
        }else return "inicial";
    }
    
    
    public String mostarAtividadesCinco(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadesMB.getListaAtividadesCinco();
        Date data = new Date();
        data = Formatacao.SomarDiasData(data, 5);
        listaAtividadesMB.setAtividadeMenu("cinco");
        listaAtividadesMB.gerarLitaAtividades();
        menuMB.gerarLitaNotificacao();
        titulo=Formatacao.diaSemanaEscrito(data) + " " +  Formatacao.ConvercaoDataIntime(data);
        retornarCorMenu();
        if (listaAtividadesMB.isMostrar()){
            listaAtividadesMB.setMostrar(false);
            return "";
        }else return "inicial";
    }
    
    
    
    public String mostarAtividadesSeis(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadesMB.getListaAtividadesSeis();
        Date data = new Date();
        data = Formatacao.SomarDiasData(data, 6);
        listaAtividadesMB.setAtividadeMenu("seis");
        listaAtividadesMB.gerarLitaAtividades();
        menuMB.gerarLitaNotificacao();
        titulo=Formatacao.diaSemanaEscrito(data) + " " +  Formatacao.ConvercaoDataIntime(data);
        retornarCorMenu();
        if (listaAtividadesMB.isMostrar()){
            listaAtividadesMB.setMostrar(false);
            return "";
        }else return "inicial";
    }
    
     
    
    public String mostarAtividadesSete(){
        menuMB.gerarLitaNotificacao();
        listaAtividadesGeral = listaAtividadesMB.getListaAtividadesSete();
        Date data = new Date();
        data = Formatacao.SomarDiasData(data, 7);
        listaAtividadesMB.setAtividadeMenu("sete");
        listaAtividadesMB.gerarLitaAtividades();
        menuMB.gerarLitaNotificacao();
        titulo=Formatacao.diaSemanaEscrito(data) + " " +  Formatacao.ConvercaoDataIntime(data);
        retornarCorMenu();
        if (listaAtividadesMB.isMostrar()){
            listaAtividadesMB.setMostrar(false);
            return "";
        }else return "inicial";
    }
    
    public void carregarListaGeral(){
        listaAtividadesGeral = new ArrayList<Atividades>();
        if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("dia")){
            listaAtividadesGeral = listaAtividadesMB.getListaAtividadedia();
        }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("semana")){
            listaAtividadesGeral = listaAtividadesMB.getListaAtividadeSemana();
        }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("atrasada")){
            listaAtividadesGeral = listaAtividadesMB.getListaAtividadeAtrasada();
        }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("amanha")) {
                listaAtividadesGeral = listaAtividadesMB.getListaAtividadesAmanha();
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("dois")) {
                listaAtividadesGeral = listaAtividadesMB.getListaAtividadesDois();
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("tres")) {
                listaAtividadesGeral = listaAtividadesMB.getListaAtividadesTres();
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("quatro")) {
                listaAtividadesGeral = listaAtividadesMB.getListaAtividadesQuatro();
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("cinco")) {
                listaAtividadesGeral = listaAtividadesMB.getListaAtividadesCinco();
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("seis")) {
                listaAtividadesGeral = listaAtividadesMB.getListaAtividadesSeis();
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("sete")) {
                listaAtividadesGeral = listaAtividadesMB.getListaAtividadesSete();
            }else  if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("Todas")){
                listaAtividadesGeral = listaAtividadesMB.getListaAtividadeTodas();
            }
    }
    
    public String imagem(Atividades atividade) {
    	if (atividade.getPrioridade()==null){
    		atividade.setPrioridade("3-normal");
    	}
        if (atividade.getPrioridade().equalsIgnoreCase("1-urgente")) {
            return "/resources/img/prioridadeUrgente.png";
        } else if (atividade.getPrioridade().equalsIgnoreCase("2-alta")) {
            return "/resources/img/prioridadeAlta.png";
        } else {
            return "/resources/img/prioridadeNormal.png";
        }
    }
    
    
    public String salvarAtividadeConcluida(String linha) {
        int iLinha = Integer.parseInt(linha);
        atividades = listaAtividadesGeral.get(iLinha);
        if (atividades.getEstado().equalsIgnoreCase("Pause")) {
            Long termino = new Date().getTime();
            BigInteger valorInicio = atividades.getInicio();
            Long inicio = valorInicio.longValue();
            Long resultado = termino - inicio;
            resultado = resultado / 1000;
            resultado = resultado / 60;
            int tempo = resultado.intValue();
            tempo = tempo + atividades.getTempo();
            atividades.setTempo(tempo);
            atividades.setEstado("Play");
        }
        if (atividades.getParticipanteList() != null) {
            NotificacaoFacade notificacaoFacade = new NotificacaoFacade();
            for (int i = 0; i < atividades.getParticipanteList().size(); i++) {
                Notificacao notificacao = new Notificacao();
                notificacao.setLido(false);
                String texto = usuarioLogadoBean.getUsuario().getNome() + " concluiu "
                        + atividades.getNome();
                if (texto.length() > 100) {
                    texto = texto.substring(0, 100);
                }
                notificacao.setTexto(texto);
                notificacao.setUsuario(atividades.getParticipanteList().get(i).getUsuario());
                notificacaoFacade.salvar(notificacao);
            }
        }
        atividades.setSituacao(true);
        atividades = salvar(atividades);
        listaAtividadesGeral.remove(atividades);
         if (atividades.getTipo().equalsIgnoreCase("R")) {
            gerarProximasAtividades();
        }
        if (atividades.getTipo().equalsIgnoreCase("O")){
            salvarAtividadeProcesso();
        }
        listaAtividadesMB.gerarLitaAtividades();
        return "";
    }
    
    
    public String verComentarios(String linha) {
       this.linha = linha;
       listaComentarios =  listaAtividadesGeral.get(Integer.parseInt(linha)).getComentariosList();
       if (listaComentarios==null){
           listaComentarios = new ArrayList<Comentarios>();
       }
       return null;
    }
    
    public String salvarComentario() throws SQLException{
        int nLinha = Integer.parseInt(linha);
        ComentariosFacade comentariosFacade = new ComentariosFacade();
        comentarios.setUsuario(getUsuarioLogadoBean().getUsuario());
        comentarios.setAtividades(listaAtividadesGeral.get(nLinha));
        comentarios.setData(new Date());
        comentarios.setHora(Formatacao.foramtarHoraString());
        comentarios = comentariosFacade.salvar(comentarios);
        listaComentarios.add(comentarios);
        comentarios = new Comentarios();
        listaAtividadesGeral.get(nLinha).getComentariosList();
         if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("dia")){
            listaAtividadesMB.getListaAtividadedia().get(nLinha).setComentariosList(listaComentarios);
        }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("semana")){
            listaAtividadesMB.getListaAtividadeSemana().get(nLinha).setComentariosList(listaComentarios);
        }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("atrasada")){
            listaAtividadesMB.getListaAtividadeAtrasada().get(nLinha).setComentariosList(listaComentarios);
        }
         linha="0";
         atividades = new Atividades();
        carregarListaGeral();
        return "";
    }
    
    public void pegarLinha(String linha){
        this.linha = linha;
    }
    
    public String pesquisarNome(){
        listaAtividadesMB.listarTodasAtividades(nomeAtividades);
        return "tarefasTodas";
    } 
   
    public void iniciarAtividade(String linha){
        if(usuarioLogadoBean.getUsuario().getPerfil().getTarefatempo()){
            this.linha = linha;
            int nlinha = Integer.parseInt(linha);
            if (listaAtividadesGeral.get(nlinha).getEstado().equalsIgnoreCase("Play")){
                //Play
                Long inicio = new Date().getTime();
                listaAtividadesGeral.get(nlinha).setInicio(BigInteger.valueOf(inicio));
                listaAtividadesGeral.get(nlinha).setEstado("Pause");
                salvar(listaAtividadesGeral.get(nlinha));
            }else {
                //Pause
                Long termino = new Date().getTime();
                BigInteger valorInicio = listaAtividadesGeral.get(nlinha).getInicio();
                Long inicio = valorInicio.longValue();
                Long resultado = termino - inicio;
                resultado = resultado/1000;
                resultado = resultado/60;
                int tempo = resultado.intValue();
                int tempoAtual = listaAtividadesGeral.get(nlinha).getTempo();
                tempo = tempo + tempoAtual;
                listaAtividadesGeral.get(nlinha).setTempo(tempo);
                String sHora = calcularHorasTotal(tempo);
                listaAtividadesGeral.get(nlinha).setMostratempo(sHora);
                listaAtividadesGeral.get(nlinha).setEstado("Play");
                salvar(listaAtividadesGeral.get(nlinha));
                }
        }else{
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
    }
    
    public String carregarIcon(Atividades atividade){
        if (atividade.getEstado().equalsIgnoreCase("Play")) {
            return "../../resources/img/playI.png";
        }  else {
            return "../../resources/img/pauseI.png";
        }
    }
    
    public String calcularHorasTotal(int tempo){
    	String sTempo = "";
        if (tempo > 0) {
            int horas = tempo / 60;
            int minutos = tempo % 60;
            if (horas > 9) {
                sTempo = sTempo + String.valueOf(horas);
            } else {
                sTempo = sTempo + "0" + String.valueOf(horas);
            }
            sTempo = sTempo + ":";
            if (minutos > 9) {
                sTempo = sTempo + "" + String.valueOf(minutos);
            } else {
                sTempo = sTempo + "0" + String.valueOf(minutos);
            }
        }
        return sTempo;
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
    
    public String mostrarTempo(Atividades atifivade){
        String texto = "Tempo " + atifivade.getMostratempo();
        return texto;
    }
    
    public String quantidadeComentario(Atividades atividade){
        String quantidade = "(" + atividade.getComentariosList().size() + ")";
        return quantidade;
    }
    
    public void gerarProximasAtividades() {
        String peridicidade = "nada";
        Rotinacliente rotinaCliente = null;
        RotinaclienteFacade rotinaclienteFacade = new RotinaclienteFacade();
        atividades.getRotinaatividadeList();
        if (atividades.getRotinaatividadeList() != null) {
            if (atividades.getRotinaatividadeList().size()==0){
                RotinaAtividadeFacade rotinaAtividadeFacade = new RotinaAtividadeFacade();
                String sql ="Select r from Rotinaatividade r where r.atividades.idatividades=" + atividades.getIdatividades();
                List<Rotinaatividade> lista = rotinaAtividadeFacade.listar(sql);
                atividades.setRotinaatividadeList(lista);
            }
            if (atividades.getRotinaatividadeList().size() > 0) {
                rotinaCliente = rotinaclienteFacade.getRotinaCliente(atividades.getCliente().getIdcliente(), atividades.getRotinaatividadeList().get(0).getRotina().getIdrotina());
                peridicidade = rotinaCliente.getPeriodicidade();
            }
            if (peridicidade.equalsIgnoreCase("diaria")) {
                criarAtividadesDiaria(rotinaCliente);
            } else if (peridicidade.equalsIgnoreCase("semanal")) {
                criarAtividadesSemanal(rotinaCliente);
            } else {
                criarAtividadeMensalTrimestralAnual(peridicidade, rotinaCliente);
            }
        }
    }

    
    public void criarAtividadeMensalTrimestralAnual(String peridicidade, Rotinacliente rotinaCliente) {
        if (!peridicidade.equalsIgnoreCase("nada")) {
            Atividades atividades = new Atividades();
            atividades.setCliente(this.atividades.getCliente());
            atividades.setNome(this.atividades.getNome());
            atividades.setPrioridade(this.atividades.getPrioridade());
            atividades.setEstado("Play");
            atividades.setInicio(BigInteger.valueOf(0));
            atividades.setTempo(0);
            atividades.setMostratempo("00:00");
            atividades.setTipo("R");
            atividades.setSubdepartamento(this.atividades.getSubdepartamento());
            RotinaAtividadeFacade rotinaAtividadeFacade = new RotinaAtividadeFacade();
            Calendar c = Calendar.getInstance();
            c.setTime(rotinaCliente.getData());    
            if (peridicidade.equalsIgnoreCase("trimestral")){
                c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 3);
            }else if (peridicidade.equalsIgnoreCase("mensal")){
                c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
            }else if (peridicidade.equalsIgnoreCase("anual")){
                c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 1);
            }
            Date data = c.getTime();
            int diaSemana = Formatacao.diaSemana(c.getTime());
            if (diaSemana == 1) {
                data = Formatacao.SomarDiasData(data, 1);
            } else if (diaSemana == 7) {
                data = Formatacao.SomarDiasData(data, 2);
            }
            atividades.setPrazo(data);
            atividades.setUsuario(this.atividades.getUsuario());
            atividades = salvar(atividades);
            RotinaclienteFacade rotinaclienteFacade = new RotinaclienteFacade();
            rotinaCliente.setData(c.getTime());
            rotinaclienteFacade.salvar(rotinaCliente);
            Rotinaatividade rotinaatividade = new Rotinaatividade();
            rotinaatividade.setAtividades(atividades);
            rotinaatividade.setRotina(rotinaCliente.getRotina());
            rotinaAtividadeFacade.salvar(rotinaatividade);
        }
    }
    
    public void criarAtividadesDiaria(Rotinacliente rotinaCliente) {
        Date data = rotinaCliente.getData();
        Atividades atividades = new Atividades();
        atividades.setCliente(this.atividades.getCliente());
        atividades.setNome(this.atividades.getNome());
        atividades.setPrioridade(this.atividades.getPrioridade());
        atividades.setTipo("R");
        atividades.setEstado("Play");
        atividades.setInicio(BigInteger.valueOf(0));
        atividades.setTempo(0);
        atividades.setMostratempo("00:00");
        atividades.setSubdepartamento(this.atividades.getSubdepartamento());
        atividades.setUsuario(this.atividades.getUsuario());
        data = Formatacao.SomarDiasData(data, 1);
        int diaSemana = Formatacao.diaSemana(data);
        if (diaSemana == 1) {
            data = Formatacao.SomarDiasData(data, 1);
        } else if (diaSemana == 7) {
            data = Formatacao.SomarDiasData(data, 2);
        }
        atividades.setPrazo(data);
        atividades = salvar(atividades);
        RotinaclienteFacade rotinaclienteFacade = new RotinaclienteFacade();
        rotinaCliente.setData(data);
        Rotinaatividade rotinaatividade = new Rotinaatividade();
        rotinaatividade.setAtividades(atividades);
        rotinaatividade.setRotina(rotinaCliente.getRotina());
        RotinaAtividadeFacade rotinaAtividadeFacade = new RotinaAtividadeFacade();
        rotinaAtividadeFacade.salvar(rotinaatividade);
        rotinaclienteFacade.salvar(rotinaCliente);
    }
    
    public void criarAtividadesSemanal(Rotinacliente rotinaCliente) {
        Date data = Formatacao.SomarDiasData(rotinaCliente.getData(), 7);
        int diaSemana = Formatacao.diaSemana(data);
        if (diaSemana == 1) {
            data = Formatacao.SomarDiasData(data, 1);
        } else if (diaSemana == 7) {
            data = Formatacao.SomarDiasData(data, 2);
        }
        Atividades atividades = new Atividades();
        atividades.setCliente(this.atividades.getCliente());
        atividades.setNome(this.atividades.getNome());
        atividades.setPrioridade(this.atividades.getPrioridade());
        atividades.setTipo("R");
        atividades.setEstado("Play");
        atividades.setInicio(BigInteger.valueOf(0));
        atividades.setTempo(0);
        atividades.setMostratempo("00:00");
        atividades.setSubdepartamento(this.atividades.getSubdepartamento());
        atividades.setPrazo(data);
        atividades.setUsuario(this.atividades.getUsuario());
        atividades = salvar(atividades);
        RotinaclienteFacade rotinaclienteFacade = new RotinaclienteFacade();
        rotinaCliente.setData(data);
        rotinaclienteFacade.salvar(rotinaCliente);
        Rotinaatividade rotinaatividade = new Rotinaatividade();
        rotinaatividade.setAtividades(atividades);
        rotinaatividade.setRotina(rotinaCliente.getRotina());
        RotinaAtividadeFacade rotinaAtividadeFacade = new RotinaAtividadeFacade();
        rotinaAtividadeFacade.salvar(rotinaatividade);
    }
    
    
    public String excluir(){
        atividadesRepository.remove(atividades.getIdatividades().longValue());
        FacesMessage mensagem = new FacesMessage("Sucesso! ", "Atividade Excluída");
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
        listaAtividadesGeral.remove(atividades);
        listaAtividadesMB.gerarNumeroAtividades();
        return "inicial";
    }
    
    public String excluirAtrasadas(){
        atividadesRepository.remove(atividades.getIdatividades().longValue());
        FacesMessage mensagem = new FacesMessage("Sucesso! ", "Atividade Excluída");
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
        listaAtividadesMB.getListaAtividadeAtrasada().remove(atividades);
        listaAtividadesGeral.remove(atividades);
        listaAtividadesMB.gerarNumeroAtividades();
        return "";
    }
    
    public String excluirTodas(){
        atividadesRepository.remove(atividades.getIdatividades().longValue());
        FacesMessage mensagem = new FacesMessage("Sucesso! ", "Atividade Excluída");
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
        listaAtividadesGeral.remove(atividades);
        listaAtividadesMB.gerarNumeroAtividades();
        return "";
    }
    
    public void carregarListaAtividades(Atividades atividade){
            if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("dia")) {
                listaAtividadesMB.getListaAtividadedia();
            } else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("semana")) {
                listaAtividadesMB.getListaAtividadeSemana();
            } else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("atrasada")) {
                listaAtividadesMB.getListaAtividadeAtrasada();
            } else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("amanha")) {
                listaAtividadesMB.getListaAtividadesAmanha();
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("todas")){
                listaAtividadesMB.getListaAtividadeTodas();
            }
            carregarListaGeral();   
    }
    
    public void carregarTituloAtividades(Atividades atividade){
            if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("dia")) {
                listaAtividadesMB.setMostrar(true);
                mostarAtividadesDia();
            } else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("semana")) {
                listaAtividadesMB.setMostrar(true);
               mostarAtividadesSemana();
            } else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("atrasada")) {
                listaAtividadesMB.setMostrar(true);
                mostarAtividadesAtrasadas();
            } else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("amanha")) {
                listaAtividadesMB.setMostrar(true);
                mostarAtividadesAmanha();
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("todas")){
                listaAtividadesMB.setMostrar(true);
                mostarTodasAtividades();
            }
            else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("dois")){
                listaAtividadesMB.setMostrar(true);
                mostarAtividadesDois();
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("tres")){
                listaAtividadesMB.setMostrar(true);
                mostarAtividadesTres();
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("quatro")){
                listaAtividadesMB.setMostrar(true);
                mostarAtividadesQuatro();
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("cinco")){
                listaAtividadesMB.setMostrar(true);
                mostarAtividadesCinco();
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("seis")){
                listaAtividadesMB.setMostrar(true);
                mostarAtividadesSeis();
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("sete")){
                listaAtividadesMB.setMostrar(true);
                mostarAtividadesSete();
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("departamento")){
                listaAtividadesMB.setMostrar(true);
                mostarAtividadesDepartamento();
            }
            carregarListaGeral();   
    }
    
    public void salvarAtividadeEditar(Atividades atividades){
        String data = Formatacao.ConvercaoDataPadrao(atividades.getPrazo());
        Date novaData = Formatacao.ConvercaoStringDataBrasil(data);
        atividades.setPrazo(novaData);
        salvar(atividades);
        carregarTituloAtividades(atividades);
    }
    
    public void retornarCorMenu(){
            if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("dia")) {
                corMenuSelecionadoDia="fonteconomica2";
                corMenuSituacao="fonteconomica";
                corMenuSelecionadoSemana="fonteconomica";
                corMenuSelecionadoAtrasada="fonteconomica";
                corMenuSelecionadoAmanha="fonteconomica";
                corMenuSelecionadoTodas="fonteconomica";
                corMenuSelecionadoDois="fonteconomica";
                corMenuSelecionadoTres="fonteconomica";
                corMenuSelecionadoQuatro="fonteconomica";
                corMenuSelecionadoCinco="fonteconomica";
                corMenuSelecionadoSeis="fonteconomica";
                corMenuSelecionadoSete="fonteconomica";
                corMenuSelecionadoDepartamento="fonteconomica";
            } else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("semana")) {
               corMenuSelecionadoSemana="fonteconomica2";
               corMenuSituacao="fonteconomica";
               corMenuSelecionadoDia="fonteconomica";
               corMenuSelecionadoAtrasada="fonteconomica";
               corMenuSelecionadoAmanha="fonteconomica";
               corMenuSelecionadoTodas="fonteconomica";
               corMenuSelecionadoDois="fonteconomica";
               corMenuSelecionadoTres="fonteconomica";
               corMenuSelecionadoQuatro="fonteconomica";
               corMenuSelecionadoCinco="fonteconomica";
               corMenuSelecionadoSeis="fonteconomica";
               corMenuSelecionadoSete="fonteconomica";
               corMenuSelecionadoDepartamento="fonteconomica";
            } else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("atrasada")) {
                corMenuSelecionadoAtrasada="fonteconomica2";
                corMenuSituacao="fonteconomica";
                corMenuSelecionadoDia="fonteconomica";
                corMenuSelecionadoSemana="fonteconomica";
                corMenuSelecionadoAmanha="fonteconomica";
                corMenuSelecionadoTodas="fonteconomica";
                corMenuSelecionadoDois="fonteconomica";
                corMenuSelecionadoTres="fonteconomica";
                corMenuSelecionadoQuatro="fonteconomica";
                corMenuSelecionadoCinco="fonteconomica";
                corMenuSelecionadoSeis="fonteconomica";
                corMenuSelecionadoSete="fonteconomica";
                corMenuSelecionadoDepartamento="fonteconomica";
            } else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("amanha")) {
                corMenuSelecionadoAmanha="fonteconomica2";
                corMenuSituacao="fonteconomica";
                corMenuSelecionadoDia="fonteconomica";
                corMenuSelecionadoSemana="fonteconomica";
                corMenuSelecionadoAtrasada="fonteconomica";
                corMenuSelecionadoTodas="fonteconomica";
                corMenuSelecionadoDois="fonteconomica";
                corMenuSelecionadoTres="fonteconomica";
                corMenuSelecionadoQuatro="fonteconomica";
                corMenuSelecionadoCinco="fonteconomica";
                corMenuSelecionadoSeis="fonteconomica";
                corMenuSelecionadoSete="fonteconomica";
                corMenuSelecionadoDepartamento="fonteconomica";
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("todas")){
                corMenuSelecionadoTodas="fonteconomica2";
                corMenuSituacao="fonteconomica";
                corMenuSelecionadoDia="fonteconomica";
                corMenuSelecionadoSemana="fonteconomica";
                corMenuSelecionadoAtrasada="fonteconomica";
                corMenuSelecionadoAmanha="fonteconomica";
                corMenuSelecionadoDois="fonteconomica";
                corMenuSelecionadoTres="fonteconomica";
                corMenuSelecionadoQuatro="fonteconomica";
                corMenuSelecionadoCinco="fonteconomica";
                corMenuSelecionadoSeis="fonteconomica";
                corMenuSelecionadoSete="fonteconomica";
                corMenuSelecionadoDepartamento="fonteconomica";
            }
            else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("dois")){
                corMenuSelecionadoDois="fonteconomica2";
                corMenuSituacao="fonteconomica";
                corMenuSelecionadoDia="fonteconomica";
                corMenuSelecionadoSemana="fonteconomica";
                corMenuSelecionadoAtrasada="fonteconomica";
                corMenuSelecionadoAmanha="fonteconomica";
                corMenuSelecionadoTodas="fonteconomica";
                corMenuSelecionadoTres="fonteconomica";
                corMenuSelecionadoQuatro="fonteconomica";
                corMenuSelecionadoCinco="fonteconomica";
                corMenuSelecionadoSeis="fonteconomica";
                corMenuSelecionadoSete="fonteconomica";
                corMenuSelecionadoDepartamento="fonteconomica";
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("tres")){
                corMenuSelecionadoTres="fonteconomica2";
                corMenuSituacao="fonteconomica";
                corMenuSelecionadoDia="fonteconomica";
                corMenuSelecionadoSemana="fonteconomica";
                corMenuSelecionadoAtrasada="fonteconomica";
                corMenuSelecionadoAmanha="fonteconomica";
                corMenuSelecionadoTodas="fonteconomica";
                corMenuSelecionadoDois="fonteconomica";
                corMenuSelecionadoQuatro="fonteconomica";
                corMenuSelecionadoCinco="fonteconomica";
                corMenuSelecionadoSeis="fonteconomica";
                corMenuSelecionadoSete="fonteconomica";
                corMenuSelecionadoDepartamento="fonteconomica";
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("quatro")){
                corMenuSelecionadoQuatro="fonteconomica2";
                corMenuSituacao="fonteconomica";
                corMenuSelecionadoDia="fonteconomica";
                corMenuSelecionadoSemana="fonteconomica";
                corMenuSelecionadoAtrasada="fonteconomica";
                corMenuSelecionadoAmanha="fonteconomica";
                corMenuSelecionadoTodas="fonteconomica";
                corMenuSelecionadoDois="fonteconomica";
                corMenuSelecionadoTres="fonteconomica";
                corMenuSelecionadoCinco="fonteconomica";
                corMenuSelecionadoSeis="fonteconomica";
                corMenuSelecionadoSete="fonteconomica";
                corMenuSelecionadoDepartamento="fonteconomica";
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("cinco")){
                corMenuSelecionadoCinco="fonteconomica2";
                corMenuSituacao="fonteconomica";
                corMenuSelecionadoDia="fonteconomica";
                corMenuSelecionadoSemana="fonteconomica";
                corMenuSelecionadoAtrasada="fonteconomica";
                corMenuSelecionadoAmanha="fonteconomica";
                corMenuSelecionadoTodas="fonteconomica";
                corMenuSelecionadoDois="fonteconomica";
                corMenuSelecionadoTres="fonteconomica";
                corMenuSelecionadoQuatro="fonteconomica";
                corMenuSelecionadoSeis="fonteconomica";
                corMenuSelecionadoSete="fonteconomica";
                corMenuSelecionadoDepartamento="fonteconomica";
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("seis")){
                corMenuSelecionadoSeis="fonteconomica2";
                corMenuSituacao="fonteconomica";
                corMenuSelecionadoDia="fonteconomica";
                corMenuSelecionadoSemana="fonteconomica";
                corMenuSelecionadoAtrasada="fonteconomica";
                corMenuSelecionadoAmanha="fonteconomica";
                corMenuSelecionadoTodas="fonteconomica";
                corMenuSelecionadoDois="fonteconomica";
                corMenuSelecionadoTres="fonteconomica";
                corMenuSelecionadoQuatro="fonteconomica";
                corMenuSelecionadoCinco="fonteconomica";
                corMenuSelecionadoSete="fonteconomica";
                corMenuSelecionadoDepartamento="fonteconomica";
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("sete")){
                corMenuSelecionadoSete="fonteconomica2";
                corMenuSituacao="fonteconomica";
                corMenuSelecionadoDia="fonteconomica";
                corMenuSelecionadoSemana="fonteconomica";
                corMenuSelecionadoAtrasada="fonteconomica";
                corMenuSelecionadoAmanha="fonteconomica";
                corMenuSelecionadoTodas="fonteconomica";
                corMenuSelecionadoDois="fonteconomica";
                corMenuSelecionadoTres="fonteconomica";
                corMenuSelecionadoQuatro="fonteconomica";
                corMenuSelecionadoCinco="fonteconomica";
                corMenuSelecionadoSeis="fonteconomica";
                corMenuSelecionadoDepartamento="fonteconomica";
            }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("departamento")){
                corMenuSelecionadoDepartamento="fonteconomica2";
                corMenuSituacao="fonteconomica";
                corMenuSelecionadoDia="fonteconomica";
                corMenuSelecionadoSemana="fonteconomica";
                corMenuSelecionadoAtrasada="fonteconomica";
                corMenuSelecionadoAmanha="fonteconomica";
                corMenuSelecionadoTodas="fonteconomica";
                corMenuSelecionadoDois="fonteconomica";
                corMenuSelecionadoTres="fonteconomica";
                corMenuSelecionadoQuatro="fonteconomica";
                corMenuSelecionadoCinco="fonteconomica";
                corMenuSelecionadoSeis="fonteconomica";
                corMenuSelecionadoSete="fonteconomica";
            }else {
                corMenuSelecionadoDia="fonteconomica";
                corMenuSelecionadoSemana="fonteconomica";
                corMenuSelecionadoAtrasada="fonteconomica";
                corMenuSelecionadoAmanha="fonteconomica";
                corMenuSelecionadoTodas="fonteconomica";
                corMenuSelecionadoDois="fonteconomica";
                corMenuSelecionadoTres="fonteconomica";
                corMenuSelecionadoQuatro="fonteconomica";
                corMenuSelecionadoCinco="fonteconomica";
                corMenuSelecionadoSeis="fonteconomica";
                corMenuSelecionadoSete="fonteconomica";
                corMenuSelecionadoDepartamento="fonteconomica";
                corMenuSituacao="fonteconomica2";
            }
            carregarListaGeral();   
    }
    
    
    public void salvarAtividadeProcesso(){
        Processoatividade processoatividade = atividades.getProcessoatividadeList().get(0);
        processoatividade.setDataconclusao(new Date());
        ProcessoAtividadeFacade processoAtividadeFacade = new ProcessoAtividadeFacade();
        processoatividade = processoAtividadeFacade.salvar(processoatividade);
        List<Processoatividade> listaprocessoatividades = processoAtividadeFacade.listar("Select p From Processoatividade p where p.processosituacao.idprocessosituacao=" + processoatividade.getProcessosituacao().getIdprocessosituacao() +
                " and p.atividades.situacao=0");
        if ((listaprocessoatividades==null) || (listaprocessoatividades.size()==0)){
            Processosituacao processosituacao = processoatividade.getProcessosituacao();
            processosituacao.setDataconclusao(new Date());
            processosituacao.setSituacao("Concluído");
            ProcessoSituacaoFacade processoSituacaoFacade = new ProcessoSituacaoFacade();
            processoSituacaoFacade.salvar(processosituacao);
        }
    }
    
    public String situacao(){
        listaAtividadesMB.setAtividadeMenu(" ");
        corMenuSituacao="fonteconomica2";
        menuMB.gerarLitaNotificacao();
        retornarCorMenu();
        return"consSituacao";
    }
    
    public String salvarEditarAtividade(Atividades atividade){
        salvar(atividade);
        listaAtividadesMB.gerarLitaAtividades();
        return "";
    }
    
    public String cancelarEditarAtividade(){
        RequestContext.getCurrentInstance().closeDialog(null);
        return "";
    }
    
    public void editarData(Atividades atividades){
        this.atividades =atividades;
    }
    
     public void gerarListaUsuario() {
        UsuarioFacade usuarioFacade = new UsuarioFacade();
        listaUsuario = usuarioFacade.listarAtivos();

    }
    
    public void encaminharTarefa(Atividades atividades) {
        gerarListaUsuario();
        usuario = new Usuario();
    }
    
    
     public String cancelarEncaminharAtividade(){
      RequestContext.getCurrentInstance().closeDialog(null);
      return "inicial";
    }
     
     public String salvarEncaminharAtividade() {
        atividades.setUsuario(usuario);
        atividades = salvar(atividades);
        NotificacaoFacade notificacaoFacade = new NotificacaoFacade();
        String texto;
        Notificacao notificacao;
        notificacao = new Notificacao();
        notificacao.setLido(false);
        notificacao.setUsuario(usuario);
        texto = usuarioLogadoBean.getUsuario().getNome() + " encaminhou uma tarefa.";
        notificacao.setTexto(texto);
        notificacaoFacade.salvar(notificacao);
         listaAtividadesMB.gerarLitaAtividades();
        return "";
    }
     
    public Atividades salvar(Atividades atv){
        if (atv.getIdatividades()==null){
            atividadesRepository.create(atv);
        }else atividadesRepository.update(atv);
        return atv;
    }
   
}