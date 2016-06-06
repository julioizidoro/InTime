package br.com.financemate.manageBean.processo;

import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
import br.com.financemate.manageBean.atividades.ListaAtividadesMB;
import br.com.financemate.bean.Formatacao;
import br.com.financemate.facade.DepartamentoFacade;
import br.com.financemate.facade.ProcessoAtividadeFacade;
import br.com.financemate.facade.ProcessoRotinaFacade;
import br.com.financemate.facade.ProcessoSituacaoFacade;
import br.com.financemate.facade.UsuarioFacade;
import br.com.financemate.model.Atividades;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Departamento;
import br.com.financemate.model.Processo;
import br.com.financemate.model.Processoatividade;
import br.com.financemate.model.Processorotina;
import br.com.financemate.model.Processosituacao;
import br.com.financemate.model.Subdepartamento;
import br.com.financemate.model.Usuario;
import br.com.financemate.repository.AtividadesRepository;
import br.com.financemate.repository.ClienteRepository;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class IniciarProcessoMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private UsuarioLogadoMB usuarioLogadoBean;
    private Processosituacao processosituacao;
    private Processo processo;
    private Cliente cliente;
    private List<Departamento> listaDepartamento;
    private Departamento departamento;
    private Subdepartamento subdepartamento;
    private List<Processoatividade> listaProcessoAtividade;
    private List<Processorotina> listaProcessoRotina;
    private List<Usuario> listaUsuario;
    private List<Cliente> listaCliente;
    @Inject
    private ListaAtividadesMB listaAtividadesMB;
    private AtividadesRepository atividadesRepository;
    private ClienteRepository clienteRepository;
    
    @PostConstruct
     public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        processo = (Processo) session.getAttribute("processo");
        session.removeAttribute("processo");
        gerarListaDepartamento();
        departamento = processo.getSubdepartamento().getDepartamento();
        gerarListaCliente();
        subdepartamento = processo.getSubdepartamento();
        processosituacao = new Processosituacao();
        processosituacao.setDataabertura(new Date());
        gerarListaProcessoRotina();
        gerarListaProcessoAtividades();
        gerarListaUsuario();
        
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
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
    
    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public List<Departamento> getListaDepartamento() {
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public List<Processoatividade> getListaProcessoAtividade() {
        return listaProcessoAtividade;
    }

    public void setListaProcessoAtividade(List<Processoatividade> listaProcessoAtividade) {
        this.listaProcessoAtividade = listaProcessoAtividade;
    }

    public List<Processorotina> getListaProcessoRotina() {
        return listaProcessoRotina;
    }

    public void setListaProcessoRotina(List<Processorotina> listaProcessoRotina) {
        this.listaProcessoRotina = listaProcessoRotina;
    }

    public Processosituacao getProcessosituacao() {
        return processosituacao;
    }

    public void setProcessosituacao(Processosituacao processosituacao) {
        this.processosituacao = processosituacao;
    }

    public UsuarioLogadoMB getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoMB usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }
    
    
    
     public void gerarListaProcessoAtividades() {
        if (listaProcessoRotina != null) {
            listaProcessoAtividade = new ArrayList<Processoatividade>();
            for (int i = 0; i < listaProcessoRotina.size(); i++) {
                Processoatividade processoatividade = new Processoatividade();
                processoatividade.setProcessorotina(listaProcessoRotina.get(i));
                if (listaProcessoRotina.get(i).getDiasuteis() == 0) {
                    processoatividade.setDatexecucao(new Date());
                } else {
                    Date data = Formatacao.SomarDiasData(new Date(), listaProcessoRotina.get(i).getDiasuteis());
                    int diaSemana = Formatacao.diaSemana(data);
                    if (diaSemana == 1) {
                        data = Formatacao.SomarDiasData(data, 1);
                    } else if (diaSemana == 7) {
                        data = Formatacao.SomarDiasData(data, 2);
                    }
                    processoatividade.setDatexecucao(data);
                }
                processoatividade.setSelecionado(true);
                processoatividade.setUsuario(usuarioLogadoBean.getUsuario());
                listaProcessoAtividade.add(processoatividade);
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
    
    public void gerarListaUsuario(){
        UsuarioFacade usuarioFacade = new UsuarioFacade();
        listaUsuario = usuarioFacade.listarAtivos();
        if(listaUsuario==null){
            listaUsuario= new ArrayList<Usuario>();
        }
    }
    
    public String fechar(){
        RequestContext.getCurrentInstance().closeDialog(null);
        return "consProcesso";
    }
    
    public void gerarListaProcessoRotina(){
        ProcessoRotinaFacade rocessoRotinaFacade = new ProcessoRotinaFacade();
        listaProcessoRotina = rocessoRotinaFacade.listar("select r from Processorotina r where r.processo.idprocesso=" + processo.getIdprocesso());
        if (listaProcessoRotina==null){
            listaProcessoRotina = new ArrayList<Processorotina>();
        }
    }
    
    
    public String salvar() throws Exception {
        if ((cliente != null) && (cliente.getIdcliente() != null)) {
            ProcessoAtividadeFacade processoAtividadeFacade = new ProcessoAtividadeFacade();
            ProcessoSituacaoFacade processoSituacaoFacade = new ProcessoSituacaoFacade();
            processosituacao.setSituacao("Aberto");
            processosituacao.setCliente(cliente);
            processosituacao.setProcesso(processo);
            processosituacao.setUsuario(usuarioLogadoBean.getUsuario());
            processosituacao = processoSituacaoFacade.salvar(processosituacao);
            for (int i = 0; i < listaProcessoAtividade.size(); i++) {
                if (listaProcessoAtividade.get(i).isSelecionado()) {
                    Atividades atividades = new Atividades();
                    atividades.setUsuario(listaProcessoAtividade.get(i).getUsuario());
                    atividades.setNome(listaProcessoAtividade.get(i).getProcessorotina().getDescricao() + " - " + processo.getDescricao());
                    atividades.setPrioridade("3-normal");
                    atividades.setTipo("O");
                    atividades.setEstado("Play");
                    atividades.setInicio(BigInteger.valueOf(0));
                    atividades.setTempo(0);
                    atividades.setSituacao(false);
                    atividades.setMostratempo("00:00");
                    atividades.setSubdepartamento(processo.getSubdepartamento());
                    atividades.setPrazo(listaProcessoAtividade.get(i).getDatexecucao());
                    atividades.setCliente(cliente);
                    atividades = salvar(atividades);
                    Processoatividade processoatividade = listaProcessoAtividade.get(i);
                    processoatividade.setProcessosituacao(processosituacao);
                    processoatividade.setAtividades(atividades);
                    processoAtividadeFacade.salvar(processoatividade);
                }
            }
            RequestContext.getCurrentInstance().closeDialog(null);
            listaAtividadesMB.gerarLitaAtividades();
        }
        return "";

    }

     public void gerarListaCliente() {
        String sql =("select c from Cliente c where c.nomefantasia like '%" + " " + "%' and c.situacao='Ativo' and c.idcliente<>"
                + usuarioLogadoBean.getConfig().getCliente().getIdcliente() + " order by c.nomefantasia");
        listaCliente = clienteRepository.list(sql);
        if (listaCliente==null){
            listaCliente = new ArrayList<Cliente>();
        }
    }
     
     public Atividades salvar(Atividades atv){
        if (atv.getIdatividades()==null){
            atividadesRepository.create(atv);
        }else atividadesRepository.update(atv);
        return atv;
    }
}