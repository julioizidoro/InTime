package br.com.financemate.manageBean.atividades;

import br.com.financemate.bean.Formatacao;
import br.com.financemate.facade.DepartamentoFacade;
import br.com.financemate.facade.UsuarioFacade;
import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
import br.com.financemate.model.Atividades;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Departamento;
import br.com.financemate.model.Usuario;
import br.com.financemate.repository.AtividadesRepository;
import br.com.financemate.repository.ClienteRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Wolverine
 */
@Named
@SessionScoped
public class ListaAtividadesMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject 
    private UsuarioLogadoMB usuarioLogadoBean;
    private Date dataDia;
    
    private List<Atividades> listaAtividadedia;
    private List<Atividades> listaAtividadeSemana;
    private List<Atividades> listaAtividadeAtrasada;
    private List<Atividades> listaAtividadeTodas;
    private List<Atividades> listaAtividadesAmanha;
    private List<Atividades> listaAtividadesDois;
    private List<Atividades> listaAtividadesTres;
    private List<Atividades> listaAtividadesQuatro;
    private List<Atividades> listaAtividadesCinco;
    private List<Atividades> listaAtividadesSeis;
    private List<Atividades> listaAtividadesSete;
    private List<Departamento> listaDepartamento;
    private List<Cliente> listaCliente;
    private List<Usuario> listaUsuario;
    private String ndia;
    private String nsemana;
    private String natrasada;
    private String ndepartamento;
    private String namanha;
    private String dois;
    private String tres;
    private String quatro;
    private String cinco;
    private String seis;
    private String sete;
    private String todas;
    private boolean checkConcluidas=false;
    private String atividadeMenu="dia";
    private boolean mostrar=false;
    private boolean alteracao=false;
    private AtividadesRepository atividadesRepository;
    private ClienteRepository clienteRepository;

    @PostConstruct
    public void init(){
        String data = Formatacao.ConvercaoDataPadrao(new Date());
        dataDia = Formatacao.ConvercaoStringDataBrasil(data);
    }
    
    public boolean isCheckConcluidas() {
        return checkConcluidas;
    }

    public void setCheckConcluidas(boolean checkConcluidas) {
        this.checkConcluidas = checkConcluidas;
    }

    public UsuarioLogadoMB getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoMB usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public List<Atividades> getListaAtividadedia() {
        return listaAtividadedia;
    }

    public void setListaAtividadedia(List<Atividades> listaAtividadedia) {
        this.listaAtividadedia = listaAtividadedia;
    }

    public List<Atividades> getListaAtividadeSemana() {
        return listaAtividadeSemana;
    }

    public void setListaAtividadeSemana(List<Atividades> listaAtividadeSemana) {
        this.listaAtividadeSemana = listaAtividadeSemana;
    }

    public List<Atividades> getListaAtividadeAtrasada() {
        return listaAtividadeAtrasada;
    }

    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }

    public boolean isAlteracao() {
        return alteracao;
    }

    public void setAlteracao(boolean alteracao) {
        this.alteracao = alteracao;
    }

    public void setListaAtividadeAtrasada(List<Atividades> listaAtividadeAtrasada) {
        this.listaAtividadeAtrasada = listaAtividadeAtrasada;
    }

    public List<Atividades> getListaAtividadeTodas() {
        return listaAtividadeTodas;
    }

    public void setListaAtividadeTodas(List<Atividades> listaAtividadeTodas) {
        this.listaAtividadeTodas = listaAtividadeTodas;
    }

    public List<Atividades> getListaAtividadesAmanha() {
        return listaAtividadesAmanha;
    }

    public void setListaAtividadesAmanha(List<Atividades> listaAtividadesAmanha) {
        this.listaAtividadesAmanha = listaAtividadesAmanha;
    }

    public List<Atividades> getListaAtividadesDois() {
        return listaAtividadesDois;
    }

    public void setListaAtividadesDois(List<Atividades> listaAtividadesDois) {
        this.listaAtividadesDois = listaAtividadesDois;
    }

    public List<Atividades> getListaAtividadesTres() {
        return listaAtividadesTres;
    }

    public void setListaAtividadesTres(List<Atividades> listaAtividadesTres) {
        this.listaAtividadesTres = listaAtividadesTres;
    }

    public List<Atividades> getListaAtividadesQuatro() {
        return listaAtividadesQuatro;
    }

    public void setListaAtividadesQuatro(List<Atividades> listaAtividadesQuatro) {
        this.listaAtividadesQuatro = listaAtividadesQuatro;
    }

    public List<Atividades> getListaAtividadesCinco() {
        return listaAtividadesCinco;
    }

    public void setListaAtividadesCinco(List<Atividades> listaAtividadesCinco) {
        this.listaAtividadesCinco = listaAtividadesCinco;
    }

    public List<Atividades> getListaAtividadesSeis() {
        return listaAtividadesSeis;
    }

    public void setListaAtividadesSeis(List<Atividades> listaAtividadesSeis) {
        this.listaAtividadesSeis = listaAtividadesSeis;
    }

    public List<Atividades> getListaAtividadesSete() {
        return listaAtividadesSete;
    }

    public void setListaAtividadesSete(List<Atividades> listaAtividadesSete) {
        this.listaAtividadesSete = listaAtividadesSete;
    }

    public String getNdia() {
        return ndia;
    }

    public void setNdia(String ndia) {
        this.ndia = ndia;
    }

    public String getNsemana() {
        return nsemana;
    }

    public void setNsemana(String nsemana) {
        this.nsemana = nsemana;
    }

    public String getNatrasada() {
        return natrasada;
    }

    public void setNatrasada(String natrasada) {
        this.natrasada = natrasada;
    }

    public String getNdepartamento() {
        return ndepartamento;
    }

    public void setNdepartamento(String ndepartamento) {
        this.ndepartamento = ndepartamento;
    }

    public String getNamanha() {
        return namanha;
    }

    public void setNamanha(String namanha) {
        this.namanha = namanha;
    }

    public String getDois() {
        return dois;
    }

    public void setDois(String dois) {
        this.dois = dois;
    }

    public String getTres() {
        return tres;
    }

    public void setTres(String tres) {
        this.tres = tres;
    }

    public String getQuatro() {
        return quatro;
    }

    public void setQuatro(String quatro) {
        this.quatro = quatro;
    }

    public String getCinco() {
        return cinco;
    }

    public void setCinco(String cinco) {
        this.cinco = cinco;
    }

    public String getSeis() {
        return seis;
    }

    public void setSeis(String seis) {
        this.seis = seis;
    }

    public String getSete() {
        return sete;
    }

    public void setSete(String sete) {
        this.sete = sete;
    }


    public String getTodas() {
        return todas;
    }

    public void setTodas(String todas) {
        this.todas = todas;
    }

    public List<Departamento> getListaDepartamento() {
        if(listaDepartamento==null){
            gerarListaDepartamento();
        }
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public List<Cliente> getListaCliente() {
        if(listaCliente==null){
            gerarListaCliente();
        }
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public List<Usuario> getListaUsuario() {
        if(listaUsuario==null){
            gerarListaUsuario();
        }
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public String getAtividadeMenu() {
        return atividadeMenu;
    }

    public void setAtividadeMenu(String atividadeMenu) {
        this.atividadeMenu = atividadeMenu;
    }
    
    public void gerarListaCliente() {
        String sql =("select c from Cliente c where c.nomefantasia like '%" + " " + "%' and c.situacao='Ativo' and c.idcliente<>"
                + usuarioLogadoBean.getConfig().getCliente().getIdcliente() + " order by c.nomefantasia");
        listaCliente = clienteRepository.list(sql);
        if (listaCliente==null){
            listaCliente = new ArrayList<Cliente>();
        }
    }
    
    public void gerarListaDepartamento() {
        DepartamentoFacade departamentoFacade = new DepartamentoFacade();
        listaDepartamento = departamentoFacade.listar("", usuarioLogadoBean.getConfig().getDepartamento().getIddepartamento());
        if (listaDepartamento==null){
            listaDepartamento = new ArrayList<Departamento>();
        }
    }
    
    public void gerarListaUsuario() {
        UsuarioFacade usuarioFacade = new UsuarioFacade();
        listaUsuario = usuarioFacade.listarAtivos();
        if (listaUsuario==null){
            listaUsuario = new ArrayList<Usuario>();
        }
    }
    
    public void gerarNumeroAtividades() {
        if (listaAtividadedia.size() < 10) {
            ndia = "Hoje (0" + String.valueOf(listaAtividadedia.size()) + ")";
        } else {
            ndia = "Hoje (" + String.valueOf(listaAtividadedia.size()) + ")";
        }
        if (listaAtividadeSemana.size() < 10) {
            nsemana = "Semana (0" + String.valueOf(listaAtividadeSemana.size()) + ")";
        } else {
            nsemana = "Semana (" + String.valueOf(listaAtividadeSemana.size()) + ")";
        }
        if (listaAtividadeAtrasada.size() < 10) {
            natrasada = "Atrasadas (0" + String.valueOf(listaAtividadeAtrasada.size()) + ")";
        } else {
            natrasada = "Atrasadas (" + String.valueOf(listaAtividadeAtrasada.size()) + ")";
        }
        if (listaAtividadesAmanha.size() < 10) {
            namanha = Formatacao.diaSemanaEscrito(Formatacao.SomarDiasData(dataDia, 1)) + " (0" + String.valueOf(listaAtividadesAmanha.size()) + ")";
        } else {
            namanha = Formatacao.diaSemanaEscrito(Formatacao.SomarDiasData(dataDia, 1)) + " (" + String.valueOf(listaAtividadesAmanha.size()) + ")";
        }
        if (listaAtividadesDois.size() < 10) {
            dois = Formatacao.diaSemanaEscrito(Formatacao.SomarDiasData(dataDia, 2)) + "  (0" + String.valueOf(listaAtividadesDois.size()) + ")";
        } else {
            dois = Formatacao.diaSemanaEscrito(Formatacao.SomarDiasData(dataDia, 2)) + " (" + String.valueOf(listaAtividadesDois.size()) + ")";
        }
        if (listaAtividadesTres.size() < 10) {
            tres = Formatacao.diaSemanaEscrito(Formatacao.SomarDiasData(dataDia, 3)) + "  (0" + String.valueOf(listaAtividadesTres.size()) + ")";
        } else {
            tres = Formatacao.diaSemanaEscrito(Formatacao.SomarDiasData(dataDia, 3)) + " (" + String.valueOf(listaAtividadesTres.size()) + ")";
        }
        if (listaAtividadesQuatro.size() < 10) {
            quatro = Formatacao.diaSemanaEscrito(Formatacao.SomarDiasData(dataDia, 4)) + "  (0" + String.valueOf(listaAtividadesQuatro.size()) + ")";
        } else {
            quatro = Formatacao.diaSemanaEscrito(Formatacao.SomarDiasData(dataDia, 4)) + " (" + String.valueOf(listaAtividadesQuatro.size()) + ")";
        }
        if (listaAtividadesCinco.size() < 10) {
            cinco = Formatacao.diaSemanaEscrito(Formatacao.SomarDiasData(dataDia, 5)) + "  (0" + String.valueOf(listaAtividadesCinco.size()) + ")";
        } else {
            cinco = Formatacao.diaSemanaEscrito(Formatacao.SomarDiasData(dataDia, 5)) + " (" + String.valueOf(listaAtividadesCinco.size()) + ")";
        }
        if (listaAtividadesSeis.size() < 10) {
            seis = Formatacao.diaSemanaEscrito(Formatacao.SomarDiasData(dataDia, 6)) + "  (0" + String.valueOf(listaAtividadesSeis.size()) + ")";
        } else {
            seis = Formatacao.diaSemanaEscrito(Formatacao.SomarDiasData(dataDia, 6)) + " (" + String.valueOf(listaAtividadesSeis.size()) + ")";
        }
        if (listaAtividadesSete.size() < 10) {
            sete = Formatacao.diaSemanaEscrito(Formatacao.SomarDiasData(dataDia, 7)) + "  (0" + String.valueOf(listaAtividadesSete.size()) + ")";
        } else {
            sete = Formatacao.diaSemanaEscrito(Formatacao.SomarDiasData(dataDia, 7)) + " (" + String.valueOf(listaAtividadesSete.size()) + ")";
        }
        if (listaAtividadeTodas.size()<10){
            todas = "Todas (0" + String.valueOf(listaAtividadeTodas.size()) + ")";
        }else todas = "Todas (" + String.valueOf(listaAtividadeTodas.size()) +")";
    }
    
//    public  void listarAtividadesDia()  {
//        AtividadeFacade atividadeFacade = new AtividadeFacade();
//        String sql = "Select a from Atividades a where  a.prazo<='" + Formatacao.ConvercaoDataSql(new Date()) + 
//                "' and a.situacao=" + isCheckConcluidas() + " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
//                " order by a.prazo, a.prioridade, a.nome";
//        listaAtividadedia = atividadeFacade.lista(sql);
//        if (listaAtividadedia==null){
//            listaAtividadedia = new ArrayList<Atividades>();
//        }
//        
//        
//    }
//    
//    public  void listarAtividadesSemana()  {
//        AtividadeFacade atividadesAtividadeFacade = new AtividadeFacade();
//        Date data = Formatacao.SomarDiasData(new Date(), 7);
//        String sql = "Select a from Atividades a where a.prazo>='" + Formatacao.ConvercaoDataSql(new Date()) + 
//                "' and a.prazo<='" + Formatacao.ConvercaoDataSql(data) + "'  and a.situacao=" + isCheckConcluidas() + 
//                " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario()  +
//                " order by a.prazo, a.prioridade, a.nome";
//        listaAtividadeSemana = atividadesAtividadeFacade.lista(sql);
//        if (listaAtividadeSemana==null){
//            listaAtividadeSemana = new ArrayList<Atividades>();
//        }
//        if (listaAtividadeSemana.size()<10){
//            nsemana= "Semana (0" + String.valueOf(listaAtividadeSemana.size()) + ")";
//        }else nsemana = "Semana (" + String.valueOf(listaAtividadeSemana.size()) + ")";
//    }
//    
//    public  void listarAtividadesAtrasadas()  {
//        AtividadeFacade atividadesAtividadeFacade = new AtividadeFacade();
//        String sql = "Select a from Atividades a where a.prazo<'" + Formatacao.ConvercaoDataSql(new Date()) + 
//                 "' and a.situacao=FALSE  and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
//                " order by a.prazo, a.prioridade, a.nome";
//        listaAtividadeAtrasada = atividadesAtividadeFacade.lista(sql);
//        if (listaAtividadeAtrasada==null){
//            listaAtividadeAtrasada = new ArrayList<Atividades>();
//        }
//        if (listaAtividadeAtrasada.size()<10){
//            natrasada = "Atrasadas (0" + String.valueOf(listaAtividadeAtrasada.size())+")";
//        }else natrasada = "Atrasadas (" + String.valueOf(listaAtividadeAtrasada.size())+")";
//    }
//    
//    public void listarAtividadesAmanha(){
//        AtividadeFacade atividadesAtividadeFacade = new AtividadeFacade();
//        Date data = new Date();
//        data = Formatacao.SomarDiasData(data, 1);
//        String sql = "Select a from Atividades a where a.prazo='" + Formatacao.ConvercaoDataSql(data) + "' and a.situacao=" + isCheckConcluidas() + 
//                " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
//                " order by a.prazo, a.prioridade, a.nome";
//        listaAtividadesAmanha = atividadesAtividadeFacade.lista(sql);
//        if(listaAtividadesAmanha==null){
//            listaAtividadesAmanha = new ArrayList<Atividades>();
//        }
//        listarAtividadesSemana();
//        if (listaAtividadesAmanha.size()<10){
//            namanha= Formatacao.diaSemanaEscrito(data) + " (0" + String.valueOf(listaAtividadesAmanha.size()) + ")";
//        }else namanha = Formatacao.diaSemanaEscrito(data) + " (" + String.valueOf(listaAtividadesAmanha.size()) + ")";
//    }
//    
//    public void listarAtividadesDois(){
//        AtividadeFacade atividadesAtividadeFacade = new AtividadeFacade();
//        Date data = new Date();
//        data = Formatacao.SomarDiasData(data, 2);
//        String sql = "Select a from Atividades a where a.prazo='" + Formatacao.ConvercaoDataSql(data) + "' and a.situacao=" + isCheckConcluidas() + 
//                " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
//                " order by a.prazo, a.prioridade, a.nome";
//        listaAtividadesDois = atividadesAtividadeFacade.lista(sql);
//        if(listaAtividadesDois==null){
//            listaAtividadesDois = new ArrayList<Atividades>();
//        }
//        listarAtividadesSemana();
//        if (listaAtividadesDois.size()<10){
//            dois= Formatacao.diaSemanaEscrito(data) + "  (0" + String.valueOf(listaAtividadesDois.size()) + ")";
//        }else dois = Formatacao.diaSemanaEscrito(data) + " (" + String.valueOf(listaAtividadesDois.size()) + ")";
//    }
//    
//    public void listarAtividadesTres(){
//        AtividadeFacade atividadesAtividadeFacade = new AtividadeFacade();
//        Date data = new Date();
//        data = Formatacao.SomarDiasData(data, 3);
//        String sql = "Select a from Atividades a where a.prazo='" + Formatacao.ConvercaoDataSql(data) + "' and a.situacao=" + isCheckConcluidas() + 
//                " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
//                " order by a.prazo, a.prioridade, a.nome";
//        listaAtividadesTres = atividadesAtividadeFacade.lista(sql);
//        if(listaAtividadesTres==null){
//            listaAtividadesTres = new ArrayList<Atividades>();
//        }
//        listarAtividadesSemana();
//        if (listaAtividadesTres.size()<10){
//            tres= Formatacao.diaSemanaEscrito(data) + "  (0" + String.valueOf(listaAtividadesTres.size()) + ")";
//        }else tres = Formatacao.diaSemanaEscrito(data) + " (" + String.valueOf(listaAtividadesTres.size()) + ")";
//    }
//    
//    public void listarAtividadesQuatro(){
//        AtividadeFacade atividadesAtividadeFacade = new AtividadeFacade();
//        Date data = new Date();
//        data = Formatacao.SomarDiasData(data, 4);
//        String sql = "Select a from Atividades a where a.prazo='" + Formatacao.ConvercaoDataSql(data) + "' and a.situacao=" + isCheckConcluidas() + 
//                " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
//                " order by a.prazo, a.prioridade, a.nome";
//        listaAtividadesQuatro = atividadesAtividadeFacade.lista(sql);
//        if(listaAtividadesQuatro==null){
//            listaAtividadesQuatro = new ArrayList<Atividades>();
//        }
//        listarAtividadesSemana();
//        if (listaAtividadesQuatro.size()<10){
//            quatro= Formatacao.diaSemanaEscrito(data) + "  (0" + String.valueOf(listaAtividadesQuatro.size()) + ")";
//        }else quatro = Formatacao.diaSemanaEscrito(data) + " (" + String.valueOf(listaAtividadesQuatro.size()) + ")";
//    }
//    
//    
//    
//    public void listarAtividadesCinco(){
//        AtividadeFacade atividadesAtividadeFacade = new AtividadeFacade();
//        Date data = new Date();
//        data = Formatacao.SomarDiasData(data, 5);
//        String sql = "Select a from Atividades a where a.prazo='" + Formatacao.ConvercaoDataSql(data) + "' and a.situacao=" + isCheckConcluidas() + 
//                " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
//                " order by a.prazo, a.prioridade, a.nome";
//        listaAtividadesCinco = atividadesAtividadeFacade.lista(sql);
//        if(listaAtividadesCinco==null){
//            listaAtividadesCinco = new ArrayList<Atividades>();
//        }
//        listarAtividadesSemana();
//        if (listaAtividadesCinco.size()<10){
//            cinco= Formatacao.diaSemanaEscrito(data) + "  (0" + String.valueOf(listaAtividadesCinco.size()) + ")";
//        }else cinco = Formatacao.diaSemanaEscrito(data) + " (" + String.valueOf(listaAtividadesCinco.size()) + ")";
//    }
//    
//    
//    public void listarAtividadesSeis(){
//        AtividadeFacade atividadesAtividadeFacade = new AtividadeFacade();
//        Date data = new Date();
//        data = Formatacao.SomarDiasData(data, 6);
//        String sql = "Select a from Atividades a where a.prazo='" + Formatacao.ConvercaoDataSql(data) + "' and a.situacao=" + isCheckConcluidas() + 
//                " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
//                " order by a.prazo, a.prioridade, a.nome";
//        listaAtividadesSeis = atividadesAtividadeFacade.lista(sql);
//        if(listaAtividadesSeis==null){
//            listaAtividadesSeis = new ArrayList<Atividades>();
//        }
//        listarAtividadesSemana();
//        if (listaAtividadesSeis.size()<10){
//            seis= Formatacao.diaSemanaEscrito(data) + "  (0" + String.valueOf(listaAtividadesSeis.size()) + ")";
//        }else seis = Formatacao.diaSemanaEscrito(data) + " (" + String.valueOf(listaAtividadesSeis.size()) + ")";
//    }
//    
//    
//     public void listarAtividadesSete(){
//        AtividadeFacade atividadesAtividadeFacade = new AtividadeFacade();
//        Date data = new Date();
//        data = Formatacao.SomarDiasData(data, 7);
//        String sql = "Select a from Atividades a where a.prazo='" + Formatacao.ConvercaoDataSql(data) + "' and a.situacao=" + isCheckConcluidas() + 
//                " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
//                " order by a.prazo, a.prioridade, a.nome";
//        listaAtividadesSete = atividadesAtividadeFacade.lista(sql);
//        if(listaAtividadesSete==null){
//            listaAtividadesSete = new ArrayList<Atividades>();
//        }
//        listarAtividadesSemana();
    //        if (listaAtividadesSete.size()<10){
    //            sete= Formatacao.diaSemanaEscrito(data) + "  (0" + String.valueOf(listaAtividadesSete.size()) + ")";
    //        }else sete = Formatacao.diaSemanaEscrito(data) + " (" + String.valueOf(listaAtividadesSete.size()) + ")";
//    }
//     
//     
//     
//    public void atualizarLista(Atividades atividade){
//        Date dataAtual = new Date();
//        String sData = Formatacao.ConvercaoDataPadrao(dataAtual);
//        dataAtual = Formatacao.ConvercaoStringDataBrasil(sData);
//        if (atividade.getPrazo().equals(dataAtual)){
//            listarAtividadesDia();
//        }else {
//            dataAtual = Formatacao.SomarDiasData(dataAtual,1);
//            if (atividade.getPrazo().equals(dataAtual)){
//                listarAtividadesAmanha();
//            }else {
//                dataAtual = Formatacao.SomarDiasData(dataAtual,2);
//                if (atividade.getPrazo().equals(dataAtual)){
//                    listarAtividadesDois();
//                }else {
//                    dataAtual = Formatacao.SomarDiasData(dataAtual,3);
//                    if (atividade.getPrazo().equals(dataAtual)){
//                        listarAtividadesTres();
//                    }else {
//                        dataAtual = Formatacao.SomarDiasData(dataAtual,4);
//                        if (atividade.getPrazo().equals(dataAtual)){
//                            listarAtividadesQuatro();
//                        }else {
//                            dataAtual = Formatacao.SomarDiasData(dataAtual,5);
//                            if (atividade.getPrazo().equals(dataAtual)){
//                                listarAtividadesCinco();
//                            }else {
//                                dataAtual = Formatacao.SomarDiasData(dataAtual,6);
//                                if (atividade.getPrazo().equals(dataAtual)){
//                                    listarAtividadesSeis();
//                                }else {
//                                    dataAtual = Formatacao.SomarDiasData(dataAtual,7);
//                                    if (atividade.getPrazo().equals(dataAtual)){
//                                        listarAtividadesSete();
//                                    }else {
//                                        if (atividade.getPrazo().before(dataAtual)){
//                                            listarAtividadesAtrasadas();
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }    
//    }
    
    public void iniciarListar(){
        listaAtividadeAtrasada = new ArrayList<Atividades>();
        listaAtividadeSemana = new ArrayList<Atividades>();
        listaAtividadeTodas = new ArrayList<Atividades>();
        listaAtividadedia = new ArrayList<Atividades>();
        listaAtividadesAmanha = new ArrayList<Atividades>();
        listaAtividadesCinco = new ArrayList<Atividades>();
        listaAtividadesDois = new ArrayList<Atividades>();
        listaAtividadesQuatro = new ArrayList<Atividades>();
        listaAtividadesSeis = new ArrayList<Atividades>();
        listaAtividadesSete = new ArrayList<Atividades>();
        listaAtividadesTres = new ArrayList<Atividades>();
    }
    
    public void gerarLitaAtividades(){
        iniciarListar();
        String sql = "Select a from Atividades a where  a.situacao=" + isCheckConcluidas() + " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
                " order by a.prazo, a.prioridade, a.nome";
        List<Atividades> lista = atividadesRepository.list(sql);
        if (lista==null){
            lista = new ArrayList<Atividades>();
        }
        for (int i=0;i<lista.size();i++){
            listaAtividadeTodas.add(lista.get(i));
            if (lista.get(i).getPrazo().after(dataDia)){
                gerarListaAtivdadesFuturas(lista.get(i));
            }else {
                listaAtividadedia.add(lista.get(i));
                if (lista.get(i).getPrazo().before(dataDia)){
                    listaAtividadeAtrasada.add(lista.get(i));
                }else {
                    listaAtividadeSemana.add(lista.get(i));
                }
            }
        }
        gerarNumeroAtividades();
    }
    
    public void gerarListaAtivdadesFuturas(Atividades atividade) {
        Date dia = Formatacao.SomarDiasData(dataDia, 1);
        if (atividade.getPrazo().equals(dia)) {
            listaAtividadesAmanha.add(atividade);
            listaAtividadeSemana.add(atividade);
        } else {
            dia = Formatacao.SomarDiasData(dataDia, 2);
            if (atividade.getPrazo().equals(dia)) {
                listaAtividadesDois.add(atividade);
                listaAtividadeSemana.add(atividade);
            } else {
                dia = Formatacao.SomarDiasData(dataDia, 3);
                if (atividade.getPrazo().equals(dia)) {
                    listaAtividadesTres.add(atividade);
                    listaAtividadeSemana.add(atividade);
                } else {
                    dia = Formatacao.SomarDiasData(dataDia, 4);
                    if (atividade.getPrazo().equals(dia)) {
                        listaAtividadesQuatro.add(atividade);
                        listaAtividadeSemana.add(atividade);
                    } else {
                        dia = Formatacao.SomarDiasData(dataDia, 5);
                        if (atividade.getPrazo().equals(dia)) {
                            listaAtividadesCinco.add(atividade);
                            listaAtividadeSemana.add(atividade);
                        } else {
                            dia = Formatacao.SomarDiasData(dataDia, 6);
                            if (atividade.getPrazo().equals(dia)) {
                                listaAtividadesSeis.add(atividade);
                                listaAtividadeSemana.add(atividade);
                            } else {
                                dia = Formatacao.SomarDiasData(dataDia, 7);
                                if (atividade.getPrazo().equals(dia)) {
                                    listaAtividadesSete.add(atividade);
                                    listaAtividadeSemana.add(atividade);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public  void listarTodasAtividades(String nomeAtividades)  {
        if (nomeAtividades == null) {
            nomeAtividades = " ";
        }
        String sql = "Select a from Atividades a where a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario() +
                " and a.nome like '%" + nomeAtividades + "%' and a.situacao=FALSE " 
                + " order by a.prazo, a.prioridade, a.nome";
        listaAtividadeTodas = atividadesRepository.list(sql);
        if (listaAtividadeTodas==null){
            listaAtividadeTodas = new ArrayList<Atividades>();
        }
        if (listaAtividadeTodas.size()<10){
            todas = "Todas (0" + String.valueOf(listaAtividadeTodas.size()) + ")";
        }else todas = "Todas (" + String.valueOf(listaAtividadeTodas.size()) +")";
    }

}