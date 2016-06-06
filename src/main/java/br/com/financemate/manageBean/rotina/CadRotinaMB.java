package br.com.financemate.manageBean.rotina;

import br.com.financemate.bean.Formatacao;
import br.com.financemate.bean.RotinaBean;
import br.com.financemate.facade.DepartamentoFacade;
import br.com.financemate.facade.RotinaAtividadeFacade;
import br.com.financemate.facade.RotinaFacade;
import br.com.financemate.facade.RotinaclienteFacade;
import br.com.financemate.facade.UsuarioFacade;
import br.com.financemate.manageBean.atividades.ListaAtividadesMB;
import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
import br.com.financemate.model.Atividades;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Departamento;
import br.com.financemate.model.Rotina;
import br.com.financemate.model.Rotinaatividade;
import br.com.financemate.model.Rotinacliente;
import br.com.financemate.model.Usuario;
import br.com.financemate.repository.AtividadesRepository;
import br.com.financemate.repository.ClienteRepository;
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
import javax.servlet.http.HttpSession;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class CadRotinaMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private UsuarioLogadoMB usuarioLogadoBean;
    @Inject
    private ListaAtividadesMB listaAtividadesMB;
    private RotinaBean rotinabean;
    private Rotina rotina;
    private Departamento departamento;
    private List<Departamento> listaDepartamento;
    private List<Usuario> listaUsuario;
    private List<RotinaBean> listaRotinabean;
    private List<Rotina> listaRotina;
    private String prioridade;
    private String nomeRotinaAntigo;
    private boolean alteracaoEfetuada = false;
    private AtividadesRepository atividadesRepository;
    private ClienteRepository clienteRepository;
    
    
    @PostConstruct
    public void init(){
        getUsuarioLogadoBean();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        rotina = (Rotina) session.getAttribute("rotina");
        session.removeAttribute("rotina");
        if (rotina==null){
            rotina= new Rotina();
            gerarListaRotinaBean();
        }else {
            nomeRotinaAntigo = rotina.getNome();
            departamento = rotina.getSubdepartamento().getDepartamento();
            gerarListaRotinaBeanEditar();
        }
        gerarListaDepartamento();
        gerarListaUsuario();
    }

    public UsuarioLogadoMB getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoMB usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public RotinaBean getRotinabean() {
        return rotinabean;
    }

    public void setRotinabean(RotinaBean rotinabean) {
        this.rotinabean = rotinabean;
    }

    public Rotina getRotina() {
        return rotina;
    }

    public void setRotina(Rotina rotina) {
        this.rotina = rotina;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Departamento> getListaDepartamento() {
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public List<RotinaBean> getListaRotinabean() {
        return listaRotinabean;
    }

    public void setListaRotinabean(List<RotinaBean> listaRotinabean) {
        this.listaRotinabean = listaRotinabean;
    }

    public List<Rotina> getListaRotina() {
        return listaRotina;
    }

    public void setListaRotina(List<Rotina> listaRotina) {
        this.listaRotina = listaRotina;
    }

    public ListaAtividadesMB getListaAtividadesMB() {
        return listaAtividadesMB;
    }

    public void setListaAtividadesMB(ListaAtividadesMB listaAtividadesMB) {
        this.listaAtividadesMB = listaAtividadesMB;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getNomeRotinaAntigo() {
        return nomeRotinaAntigo;
    }

    public void setNomeRotinaAntigo(String nomeRotinaAntigo) {
        this.nomeRotinaAntigo = nomeRotinaAntigo;
    }
    
    
    
    public List<Cliente> gerarListaCliente() {
        String sql =("select c from Cliente c where c.nomefantasia like '%" + " " + "%' and c.situacao='Ativo' and c.idcliente<>"
                + usuarioLogadoBean.getConfig().getCliente().getIdcliente() + " order by c.nomefantasia");
        List<Cliente> listaCliente = clienteRepository.list(sql);
        if (listaCliente==null){
            listaCliente = new ArrayList<Cliente>();
        }
        return listaCliente;
    }
    
    public void gerarListaUsuario() {
        UsuarioFacade usuarioFacade = new UsuarioFacade();
        listaUsuario = usuarioFacade.listarAtivos();
        if (listaUsuario==null){
            listaUsuario = new ArrayList<Usuario>();
        }
    }
    
    public void gerarListaRotinaBean() {
        listaRotinabean = new ArrayList<RotinaBean>();
        List<Cliente> listaCliente = gerarListaCliente();
        if(listaCliente!=null){
          for(int i=0;i<listaCliente.size();i++){
              RotinaBean rb = new RotinaBean();
              rb.setCliente(listaCliente.get(i));
              rb.setRotinacliente(new Rotinacliente());
              rb.setRotinafixa(new Rotinacliente());
              listaRotinabean.add(rb);
          }
        }
    }
    
    public void gerarListaDepartamento() {
        DepartamentoFacade departamentoFacade = new DepartamentoFacade();
        listaDepartamento = departamentoFacade.listar("", usuarioLogadoBean.getConfig().getDepartamento().getIddepartamento());
        if (listaDepartamento==null){
            listaDepartamento = new ArrayList<Departamento>();
        }
    }
    
    public void gerarListaRotinaBeanEditar(){
        listaRotinabean = new ArrayList<RotinaBean>();
        List<Cliente> listaCliente = gerarListaCliente();
        RotinaclienteFacade rotinaclienteFacade = new RotinaclienteFacade();
        if(listaCliente!=null){
          for(int i=0;i<listaCliente.size();i++){
              RotinaBean rb = new RotinaBean();
              rb.setCliente(listaCliente.get(i));
              Rotinacliente rc = rotinaclienteFacade.getRotinaCliente(listaCliente.get(i).getIdcliente(), rotina.getIdrotina());
              if (rc!=null){
                  rb.setRotinacliente(rc);
                  Rotinacliente fixa = new Rotinacliente();
                  fixa.setCliente(rc.getCliente());
                  fixa.setData(rc.getData());
                  fixa.setIdrotinacliente(rc.getIdrotinacliente());
                  fixa.setPeriodicidade(rc.getPeriodicidade());
                  fixa.setRotina(rc.getRotina());
                  fixa.setSelecionado(true);
                  fixa.setUsuario(rc.getUsuario());
                  rb.setRotinafixa(fixa);
                  rb.setSelecionado(true);
              }else {
                  rb.setRotinacliente(new Rotinacliente());
                  rb.setRotinafixa(new Rotinacliente());
                  rb.setSelecionado(false);
              }
              listaRotinabean.add(rb);
          }
        }
    }
    
    public String salvar() {
        if (verificarDados()) {
            RotinaFacade rotinaFacade = new RotinaFacade();
            rotina = rotinaFacade.salvar(rotina);
            excluirRotina();
            salvarRotinaClienteAtividade();
            listaAtividadesMB.gerarLitaAtividades();
            return "consRotina";
        } else {
            FacesMessage mensagem = new FacesMessage("Erro! ", "Dados inválidos");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }
    }
    
    
    
    public void salvarRotinaClienteAtividade(){
        RotinaclienteFacade rotinaclienteFacade = new RotinaclienteFacade();
        for (int i=0;i<listaRotinabean.size();i++){
            if (listaRotinabean.get(i).isSelecionado()){
                Rotinacliente rc = new Rotinacliente();
                rc = listaRotinabean.get(i).getRotinacliente();
                rc.setCliente(listaRotinabean.get(i).getCliente());
                rc.setRotina(rotina);
                if (rc.getPeriodicidade().equalsIgnoreCase("diaria")) {
                    rc.setData(criarAtividadesDiaria(listaRotinabean.get(i)));
                } else if (rc.getPeriodicidade().equalsIgnoreCase("semanal")) {
                    rc.setData(criarAtividadesSemanal(listaRotinabean.get(i)));
                }else {
                    criarAtividadeMensalTrimestralAnual(listaRotinabean.get(i));
                } 
                rotinaclienteFacade.salvar(rc);
            }
        }
    }
    
    public void excluirRotina(){
        if (alteracaoEfetuada){
            String sql = "Select a from Rotinaatividade a where a.atividades.prazo>=" + Formatacao.ConvercaoDataSql(new Date())
                        + " and a.rotina.idrotina=" + rotina.getIdrotina();
            RotinaAtividadeFacade rotinaAtividadeFacade = new RotinaAtividadeFacade();
            List<Rotinaatividade> listaRotinaAtividade = rotinaAtividadeFacade.listar(sql);
            if (listaRotinaAtividade!=null){
                for(int i=0;i<listaRotinaAtividade.size();i++){
                    atividadesRepository.remove(listaRotinaAtividade.get(i).getAtividades().getIdatividades().longValue());
                    rotinaAtividadeFacade.excluir(listaRotinaAtividade.get(i).getIdrotinaatividade());
                }
            }
            List<Rotinacliente> listaRotinaCliente = rotina.getRotinaclienteList();
            if (listaRotinaCliente!=null){
                RotinaclienteFacade rotinaclienteFacade = new RotinaclienteFacade();
                for(int i=0;i<listaRotinaCliente.size();i++){
                    rotinaAtividadeFacade.excluir(listaRotinaCliente.get(i).getIdrotinacliente());
                }
            }
        }
    }
    
    public Date criarAtividadesDiaria(RotinaBean rotinaBean) {
        Date data = rotinaBean.getRotinacliente().getData();
        for (int i=0;i<7;i++){
            Atividades atividades = new Atividades();
            atividades.setCliente(rotinaBean.getCliente());
            atividades.setNome(rotina.getNome());
            atividades.setTipo("R");
            atividades.setUsuario(rotinaBean.getRotinacliente().getUsuario());
            atividades.setEstado("Play");
            atividades.setInicio(BigInteger.valueOf(0));
            atividades.setTempo(0);
            atividades.setMostratempo("00:00");
            atividades.setPrioridade(rotinaBean.getRotinacliente().getPrioridade());
            atividades.setSubdepartamento(rotina.getSubdepartamento());
            atividades.setPrazo(data);
            data = Formatacao.SomarDiasData(data, 1);
            int diaSemana = Formatacao.diaSemana(data);
            if (diaSemana==1){
                data = Formatacao.SomarDiasData(data, 1);
            }else if (diaSemana==7){
                data = Formatacao.SomarDiasData(data, 2);
            }
            salvarRotinaAtividade(atividades);
        }
        return data;
    }
    
    public Date criarAtividadesSemanal(RotinaBean rotinaBean) {
        Date data = rotinaBean.getRotinacliente().getData();
        int diaSemana = Formatacao.diaSemana(data);    
        for (int i=0;i<3;i++){
            Atividades atividades = new Atividades();
            atividades.setCliente(rotinaBean.getCliente());
            atividades.setNome(rotina.getNome());
            atividades.setPrioridade(rotinaBean.getRotinacliente().getPrioridade());
            atividades.setTipo("R");
            atividades.setUsuario(rotinaBean.getRotinacliente().getUsuario());
            atividades.setEstado("Play");
            atividades.setInicio(BigInteger.valueOf(0));
            atividades.setTempo(0);
            atividades.setMostratempo("00:00");
            atividades.setSubdepartamento(rotina.getSubdepartamento());
            atividades.setPrazo(data);
            atividades = salvar(atividades);
            salvarRotinaAtividade(atividades);
            int novoDiaSemana = -1;
            while (diaSemana!=novoDiaSemana){
                data = Formatacao.SomarDiasData(data, 1);
                novoDiaSemana = Formatacao.diaSemana(data);
            }
        }
        return data;
    }
    
        
    
    public void criarAtividadeMensalTrimestralAnual(RotinaBean rotinaBean) {
        Atividades atividades = new Atividades();
        atividades.setCliente(rotinaBean.getCliente());
        atividades.setNome(rotina.getNome());
        atividades.setPrioridade(rotinaBean.getRotinacliente().getPrioridade());
        atividades.setEstado("Play");
        atividades.setInicio(BigInteger.valueOf(0));
        atividades.setTempo(0);
        atividades.setMostratempo("00:00");
        atividades.setTipo("R");
        atividades.setUsuario(rotinaBean.getRotinacliente().getUsuario());
        atividades.setSubdepartamento(rotina.getSubdepartamento());
        atividades.setPrazo(rotinaBean.getRotinacliente().getData());
        int diaSemana = Formatacao.diaSemana(atividades.getPrazo());
        if (diaSemana == 1) {
            atividades.setPrazo(Formatacao.SomarDiasData(atividades.getPrazo(), 1));
        } else if (diaSemana == 7) {
            atividades.setPrazo(Formatacao.SomarDiasData(atividades.getPrazo(), 2));
        }
        atividades = salvar(atividades);
        salvarRotinaAtividade(atividades);
    }
    
    
    public void salvarRotinaAtividade(Atividades atividade){
        Rotinaatividade rotinaatividade = new Rotinaatividade();
        rotinaatividade.setAtividades(atividade);
        rotinaatividade.setRotina(rotina);
        RotinaAtividadeFacade rotinaAtividadeFacade = new RotinaAtividadeFacade();
        rotinaAtividadeFacade.salvar(rotinaatividade);
    }
    
    public String imagem(Rotinacliente rotinacliente) {
        if (rotinacliente.getPrioridade() != null) {
            if (rotinacliente.getPrioridade().equalsIgnoreCase("1-urgente")) {
                return "/resources/img/prioridadeUrgente.png";
            } else if (rotinacliente.getPrioridade().equalsIgnoreCase("2-alta")) {
                return "/resources/img/prioridadeAlta.png";
            } else {
                return "/resources/img/prioridadeNormal.png";
            }
        }
        return "";
    }
    
    public void verificarAkteracaoDados(CellEditEvent event){
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
                alteracaoEfetuada=true;
        }
    }
    
    public boolean verificarDados(){
        for(int i=0;i<listaRotinabean.size();i++){
            if (listaRotinabean.get(i).isSelecionado()){
                if (listaRotinabean.get(i).getRotinacliente().getPeriodicidade()==null){
                    return false;
                }
                if (listaRotinabean.get(i).getRotinacliente().getData()==null){
                    return false;
                }
                if (listaRotinabean.get(i).getRotinacliente().getUsuario()==null){
                    return false;
                }
                if (listaRotinabean.get(i).getRotinacliente().getPrioridade()==null){
                    return false;
                }
                if (departamento==null){
                    return false;
                }
                if (rotina.getSubdepartamento()==null){
                    return false;
                }
            }
        }
        return true;
    }
    
    public Atividades salvar(Atividades atv){
        if (atv.getIdatividades()==null){
            atividadesRepository.create(atv);
        }else atividadesRepository.update(atv);
        return atv;
    }
}