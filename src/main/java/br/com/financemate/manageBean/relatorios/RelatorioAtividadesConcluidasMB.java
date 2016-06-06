package br.com.financemate.manageBean.relatorios;

import br.com.financemate.bean.Formatacao;
import br.com.financemate.bean.relatorios.AtividadesConcluidasBean;
import br.com.financemate.bean.relatorios.AtividadesConcluidasFactory;
import br.com.financemate.facade.DepartamentoFacade;
import br.com.financemate.facade.UsuarioFacade;
import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
import br.com.financemate.model.Atividades;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Departamento;
import br.com.financemate.model.Subdepartamento;
import br.com.financemate.model.Usuario;
import br.com.financemate.repository.AtividadesRepository;
import br.com.financemate.repository.ClienteRepository;
import br.com.financemate.util.GerarRelatorio;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;



import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Named
@ViewScoped
public class RelatorioAtividadesConcluidasMB implements Serializable{
    
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @Inject 
    private UsuarioLogadoMB usuarioLogadoBean;
    private List<Departamento> listaDepartamento;
    private List<Subdepartamento> listaSubdepartamento;
    private List<Cliente> listaCliente;
    private List<Usuario> listaUsuario;
    private Date dataInicial;
    private Date dataFinal;
    private Usuario usuario;
    private Cliente cliente;
    private Departamento departamento;
    private Subdepartamento subdepartamento;
    private AtividadesRepository atividadesRepository;
    private ClienteRepository clienteRepository;
    
    @PostConstruct
    public void init(){
        getUsuarioLogadoBean();
        gerarListaClientes();
        gerarListaDepartamento();
        gerarListaUsuario();
        usuario = new Usuario();
        departamento = new Departamento();
        subdepartamento = new Subdepartamento();
        cliente = new Cliente();
    }

    public UsuarioLogadoMB getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoMB usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public List<Departamento> getListaDepartamento() {
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public List<Subdepartamento> getListaSubdepartamento() {
        return listaSubdepartamento;
    }

    public void setListaSubdepartamento(List<Subdepartamento> listaSubdepartamento) {
        this.listaSubdepartamento = listaSubdepartamento;
    }

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }
    
    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
    
    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
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
    
    
    public void gerarListaClientes() {
        String sql =("select c from Cliente c where c.nomefantasia like '%" + " " + "%' and c.idcliente<>"
                + usuarioLogadoBean.getConfig().getCliente().getIdcliente() + " order by c.nomefantasia");
        listaCliente = clienteRepository.list(sql);
        if (listaCliente == null) {
            listaCliente = new ArrayList<Cliente>();
        }
    }
    
    public void gerarListaUsuario() {
        UsuarioFacade usuarioFacade = new UsuarioFacade();
        listaUsuario = usuarioFacade.listarAtivos();
        if (listaUsuario==null){
            listaUsuario = new ArrayList<Usuario>();
        }
    }
    
    public void gerarListaDepartamento() {
        DepartamentoFacade departamentoFacade = new DepartamentoFacade();
        listaDepartamento = departamentoFacade.listar("", usuarioLogadoBean.getConfig().getDepartamento().getIddepartamento());
        if (listaDepartamento==null){
            listaDepartamento = new ArrayList<Departamento>();
        }
    }
    
    private List<Atividades> gerarListaAtividadesConcluidas(){
        String sql = "Select a from Atividades a where a.prazo>='" + Formatacao.ConvercaoDataSql(dataInicial) + 
                "' and a.prazo<='" + Formatacao.ConvercaoDataSql(dataFinal) + "'  and a.situacao=1";
        if (cliente!=null){
            sql = sql + " and a.cliente.idcliente=" + cliente.getIdcliente();
        }
        if (departamento!=null){
            sql = sql + " and a.subdepartamento.departamento.iddepartamento=" + departamento.getIddepartamento();
        }
        if (subdepartamento!=null){
            sql = sql + " and a.subdepartamento.idsubdepartamento=" + subdepartamento.getIdsubdepartamento();
        }
        if (usuario!=null){
            sql = sql + " and a.usuario.idusuario=" + usuario.getIdusuario();
        }
        sql = sql + " order by a.usuario.nome, a.nome";
        List<Atividades> listaAtividade = atividadesRepository.list(sql);
        return listaAtividade;
    }
    
    public List<AtividadesConcluidasBean>  gerarDS(){
        List<Atividades> listaAtividade = gerarListaAtividadesConcluidas();
        List<AtividadesConcluidasBean> listaAtividadesBean = null;
        if (listaAtividade!=null){
            listaAtividadesBean = new ArrayList<AtividadesConcluidasBean>();
            for(int i=0;i<listaAtividade.size();i++){
                AtividadesConcluidasBean atividadesConcluidasBean = new AtividadesConcluidasBean();
                atividadesConcluidasBean.setAtividade(listaAtividade.get(i).getNome());
                atividadesConcluidasBean.setCliente(listaAtividade.get(i).getCliente().getNomefantasia());
                atividadesConcluidasBean.setDepartamento(listaAtividade.get(i).getSubdepartamento().getDepartamento().getNome());
                atividadesConcluidasBean.setDuracao(listaAtividade.get(i).getMostratempo());
                atividadesConcluidasBean.setTempo(listaAtividade.get(i).getTempo());
                atividadesConcluidasBean.setSubDepartamento(listaAtividade.get(i).getSubdepartamento().getNome());
                atividadesConcluidasBean.setUsuario(listaAtividade.get(i).getUsuario().getNome());
                listaAtividadesBean.add(atividadesConcluidasBean);
            }
        }
        return listaAtividadesBean;
    }
    
	public void gerarRelatorioAtividadesConsluidas() throws JRException, IOException {
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();
		String caminhoRelatorio = "/report/atividades/reportatividadesconcluidas.jasper";
		Map parameters = new HashMap();
		String nomeArquivo = "AtividadesConcluidas";
		List<AtividadesConcluidasBean> lista = gerarDS();
		if ((lista == null) || (lista.size() == 0)) {
			FacesContext context = FacesContext.getCurrentInstance();
        	context.addMessage(null, new FacesMessage("Relatório não possui páginas", ""));
		} else {
			parameters.put("totalatividades", String.valueOf(lista.size()));
			parameters.put("totalhoras", calcularHorasTotal(lista));
			File f = new File(servletContext.getRealPath("/report/logorelatorio.png"));
			BufferedImage logo = ImageIO.read(f);
			parameters.put("logo", logo);
			AtividadesConcluidasFactory.setListaAtividades(lista);
			JRDataSource jrds = new JRBeanCollectionDataSource(AtividadesConcluidasFactory.getListaAtividades());
			GerarRelatorio gerarRelatorio = new GerarRelatorio();
			gerarRelatorio.gerarRelatorioDSPDF(caminhoRelatorio, parameters, jrds, nomeArquivo);
		}
	}
    
    public String calcularHorasTotal(List<AtividadesConcluidasBean> lista){
        int tempo = 0;
        for (int i = 0; i < lista.size(); i++) {
            tempo = tempo + lista.get(i).getTempo();
        }
        String sTempo = "00:00";
        if (tempo > 0) {
            int horas = tempo / 60;
            int minutos = tempo % 60;
            int dias = horas / 24;;
            horas = horas % 24;
            if (dias>0){
            	sTempo = String.valueOf(dias) + " dia(s), ";
            }
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
    
    public String cancelar(){
        return null;
    }
    
    public void verificarTempo(){
    	String sql = "Select a from Atividades a";
        List<Atividades> listaAtividade = atividadesRepository.list(sql);
        if (listaAtividade!=null){
        	for(int i=0;i<listaAtividade.size();i++){
        		String tempo="";
        		if (listaAtividade.get(i).getTempo()>0){
        			if (listaAtividade.get(i).getTempo()<60){
        				if (listaAtividade.get(i).getTempo()<10){
        					tempo = "00:0" + String.valueOf(listaAtividade.get(i).getTempo());
        				}else {
        					tempo = "00:" + String.valueOf(listaAtividade.get(i).getTempo());
        				}
        			}else {
        				int horas = listaAtividade.get(i).getTempo()/60;
        				if (horas>99){
        					horas = 99;
        				}
        				int minutos =  listaAtividade.get(i).getTempo() % 60;
        				if (horas<10){
        					tempo = "0" + String.valueOf(horas);
        				}else {
        					tempo = String.valueOf(horas);
        				}
        				if (minutos<10){
        					tempo = tempo + ":0" + String.valueOf(minutos);
        				}else {
        					tempo = tempo + ":" + String.valueOf(minutos);
        				}
        			}
        			listaAtividade.get(i).setMostratempo(tempo);
        			salvar(listaAtividade.get(i));
        		}
        	}
        }
    }
    
    public Atividades salvar(Atividades atv){
        if (atv.getIdatividades()==null){
            atividadesRepository.create(atv);
        }else atividadesRepository.update(atv);
        return atv;
    }
}