package br.com.financemate.bean;

import br.com.financemate.model.Cliente;
import br.com.financemate.model.Rotinacliente;

/**
 *
 * @author Kamila
 */
public class RotinaBean {
    
    private Cliente cliente;
    private Rotinacliente rotinacliente;
    private Rotinacliente rotinafixa;
    private boolean selecionado;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Rotinacliente getRotinacliente() {
        return rotinacliente;
    }

    public void setRotinacliente(Rotinacliente rotinacliente) {
        this.rotinacliente = rotinacliente;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    public Rotinacliente getRotinafixa() {
        return rotinafixa;
    }

    public void setRotinafixa(Rotinacliente rotinafixa) {
        this.rotinafixa = rotinafixa;
    }
    
    
}