package br.com.financemate.manageBean.cadastro;


import br.com.financemate.model.Cliente;
import br.com.financemate.repository.ClienteRepository;
import java.io.Serializable;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class CadClienteMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
    private boolean contabilidade;
    private boolean gestaofinanceira;
    private boolean tercerizacao;
    private boolean outros;
    private ClienteRepository clienteRepository;
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        cliente= (Cliente) session.getAttribute("cliente");
        session.removeAttribute("cliente");
        if (cliente==null){
            cliente = new Cliente();
            cliente.setSituacao("Ativo");
            cliente.setContabilidade(false);
            cliente.setGetaofinanceira(false);
            cliente.setTercerizacao(false);
            cliente.setOutros(false);
            contabilidade=true;
            tercerizacao=true;
            gestaofinanceira=true;
            outros=true;
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isContabilidade() {
        return contabilidade;
    }

    public void setContabilidade(boolean contabilidade) {
        this.contabilidade = contabilidade;
    }

    public boolean isGestaofinanceira() {
        return gestaofinanceira;
    }

    public void setGestaofinanceira(boolean gestaofinanceira) {
        this.gestaofinanceira = gestaofinanceira;
    }

    public boolean isTercerizacao() {
        return tercerizacao;
    }

    public void setTercerizacao(boolean tercerizacao) {
        this.tercerizacao = tercerizacao;
    }

    public boolean isOutros() {
        return outros;
    }

    public void setOutros(boolean outros) {
        this.outros = outros;
    }
    
    public String salvar() throws SQLException {
        if (cliente.getIdcliente()==null){
            clienteRepository.create(cliente);
        }else clienteRepository.update(cliente);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Cadastrado com Sucesso", ""));
        return "consCliente";
    }
    
    public void alterarComboBox(){
        if (cliente.getContabilidade()){
            contabilidade = false;
        }else {
            contabilidade=true;
            cliente.setValorcontabilidade(0.0f);
        }
        if (cliente.getGetaofinanceira()){
            gestaofinanceira=false;
        }else {
            gestaofinanceira=true;
            cliente.setValorgestaofinanceira(0.0f);
        }
        if (cliente.getTercerizacao()){
            tercerizacao=false;
        }else {
            tercerizacao=true;
            cliente.setValortercerizacao(0.0f);
        }
        if (cliente.getOutros()){
            outros=false;
        }else {
            outros=true;
            cliente.setValoroutros(0.0f);
        }
    }
    
}