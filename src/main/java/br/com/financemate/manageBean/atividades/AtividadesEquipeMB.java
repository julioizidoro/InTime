package br.com.financemate.manageBean.atividades;

import br.com.financemate.bean.Formatacao;
import br.com.financemate.facade.ComentariosFacade;
import br.com.financemate.facade.NotificacaoFacade;
import br.com.financemate.facade.ProcessoAtividadeFacade;
import br.com.financemate.facade.ProcessoSituacaoFacade;
import br.com.financemate.facade.RotinaAtividadeFacade;
import br.com.financemate.facade.RotinaclienteFacade;
import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
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
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class AtividadesEquipeMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private UsuarioLogadoMB usuarioLogadoBean;
    @Inject
    private ListaAtividadesMB listaAtividadesMB;
    private List<Atividades> listaAtividades;
    private Departamento departamento;
    private Subdepartamento subdepartamento;
    private Cliente cliente;
    private Usuario usuario;
    private String visualizar;
    private Atividades atividades;
    private List<Comentarios> listaComentarios;
    private Comentarios comentarios;
    private String linha;
    private AtividadesRepository atividadesRepository;

    public AtividadesEquipeMB() {
//        this.departamento = new Departamento();
//        this.subdepartamento = new Subdepartamento();
//        this.cliente = new Cliente();
//        this.usuario = new Usuario();
        this.visualizar="";
        listaAtividades = new ArrayList<Atividades>();
        atividades = new Atividades();
        comentarios = new Comentarios();
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public List<Comentarios> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(List<Comentarios> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public Comentarios getComentarios() {
        return comentarios;
    }

    public void setComentarios(Comentarios comentarios) {
        this.comentarios = comentarios;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getVisualizar() {
        return visualizar;
    }

    public void setVisualizar(String visualizar) {
        this.visualizar = visualizar;
    }

    public UsuarioLogadoMB getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoMB usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public List<Atividades> getListaAtividades() {
        return listaAtividades;
    }

    public void setListaAtividades(List<Atividades> listaAtividades) {
        this.listaAtividades = listaAtividades;
    }

    public ListaAtividadesMB getListaAtividadesMB() {
        return listaAtividadesMB;
    }

    public void setListaAtividadesMB(ListaAtividadesMB listaAtividadesMB) {
        this.listaAtividadesMB = listaAtividadesMB;
    }

    public Atividades getAtividades() {
        return atividades;
    }

    public void setAtividades(Atividades atividades) {
        this.atividades = atividades;
    }

    
    
    public String filtrarTarefasDepartamento(){
        boolean checkConcluidas = false;
        String sql = "Select a From Atividades a where a.situacao=" + checkConcluidas;
        if (visualizar.equalsIgnoreCase("proxsete")){
            Date data7 = Formatacao.SomarDiasData(new Date(), 7);
            sql = sql + " and a.prazo>='" + Formatacao.ConvercaoDataSql(new Date()) + "' and a.prazo<='" 
                    + Formatacao.ConvercaoDataSql(data7) + "' ";
        }
        if (visualizar.equalsIgnoreCase("hoje")){
            sql = sql + " and a.prazo='" + Formatacao.ConvercaoDataSql(new Date()) + "' ";
        }
        if (visualizar.equalsIgnoreCase("atrasadas")){
            sql = sql + " and a.prazo<'" + Formatacao.ConvercaoDataSql(new Date()) + "' ";
        }
        if (departamento!=null){
            sql = sql + " and a.subdepartamento.departamento.iddepartamento=" + departamento.getIddepartamento();
            if (subdepartamento!=null){
                sql = sql + " and a.subdepartamento.idsubdepartamento=" + subdepartamento.getIdsubdepartamento();
            }
        }
        if (cliente!=null){
            sql = sql + " and a.cliente.idcliente=" + cliente.getIdcliente();
        }
        if (usuarioLogadoBean.getUsuario().getPerfil().getTarefaeditaroutros()) {
            if (usuario!=null) {
                sql = sql + " and a.usuario.idusuario=" + usuario.getIdusuario();
            }
        } else {
            sql = sql + " and a.usuario.idusuario=" + usuarioLogadoBean.getUsuario().getIdusuario();
        }
        sql = sql + " order by a.prazo, a.prioridade, a.nome";
        listaAtividades = atividadesRepository.list(sql);
        if (listaAtividades==null){
            listaAtividades = new ArrayList<Atividades>();
        }
        return "";
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
    
    public void salvarEditarLinhaTabela(Atividades atividade) {
        salvar(atividade);
    }
    
    
    public String imagem(Atividades atividade) {
        if (atividade.getPrioridade().equalsIgnoreCase("1-urgente")) {
            return "/resources/img/prioridadeUrgente.png";
        } else if (atividade.getPrioridade().equalsIgnoreCase("2-alta")) {
            return "/resources/img/prioridadeAlta.png";
        } else {
            return "/resources/img/prioridadeNormal.png";
        }
    }
    
    public String salvarEditarAtividade(Atividades atividade){
        salvar(atividade);
        listaAtividadesMB.gerarLitaAtividades();
        return "";
    }
    
    public String salvarComentario() throws SQLException{
        int nLinha = Integer.parseInt(linha);
        ComentariosFacade comentariosFacade = new ComentariosFacade();
        comentarios.setUsuario(getUsuarioLogadoBean().getUsuario());
        comentarios.setAtividades(listaAtividades.get(nLinha));
        comentarios.setData(new Date());
        comentarios.setHora(Formatacao.foramtarHoraString());
        comentarios = comentariosFacade.salvar(comentarios);
        listaComentarios.add(comentarios);
        comentarios = new Comentarios();
        listaAtividades.get(nLinha).getComentariosList();
         if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("dia")){
            listaAtividadesMB.getListaAtividadedia().get(nLinha).setComentariosList(listaComentarios);
        }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("semana")){
            listaAtividadesMB.getListaAtividadeSemana().get(nLinha).setComentariosList(listaComentarios);
        }else if (listaAtividadesMB.getAtividadeMenu().equalsIgnoreCase("atrasada")){
            listaAtividadesMB.getListaAtividadeAtrasada().get(nLinha).setComentariosList(listaComentarios);
        }
         linha="0";
         atividades = new Atividades();
        return "";
    }
    
    public String salvarAtividadeConcluida(String linha) {
        int iLinha = Integer.parseInt(linha);
        atividades = listaAtividades.get(iLinha);
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
        listaAtividades.remove(atividades);
         if (atividades.getTipo().equalsIgnoreCase("R")) {
            gerarProximasAtividades();
        }
        if (atividades.getTipo().equalsIgnoreCase("O")){
            salvarAtividadeProcesso();
        }
        listaAtividadesMB.gerarLitaAtividades();
        return "";
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
    
    public Atividades salvar(Atividades atv){
        if (atv.getIdatividades()==null){
            atividadesRepository.create(atv);
        }else atividadesRepository.update(atv);
        return atv;
    }
}