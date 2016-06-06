package br.com.financemate.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "rotinacliente")
public class Rotinacliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrotinacliente")
    private Integer idrotinacliente;
    @Size(max = 50)
    @Column(name = "periodicidade")
    private String periodicidade;
    @Size(max = 50)
    @Column(name = "prioridade")
    private String prioridade;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "rotina_idrotina", referencedColumnName = "idrotina")
    @ManyToOne(optional = false)
    private Rotina rotina;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @Transient
    private boolean selecionado;

    public Rotinacliente() {
    }

    public Rotinacliente(Integer idrotinacliente) {
        this.idrotinacliente = idrotinacliente;
    }

    public Integer getIdrotinacliente() {
        return idrotinacliente;
    }

    public void setIdrotinacliente(Integer idrotinacliente) {
        this.idrotinacliente = idrotinacliente;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Rotina getRotina() {
        return rotina;
    }

    public void setRotina(Rotina rotina) {
        this.rotina = rotina;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrotinacliente != null ? idrotinacliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rotinacliente)) {
            return false;
        }
        Rotinacliente other = (Rotinacliente) object;
        if ((this.idrotinacliente == null && other.idrotinacliente != null) || (this.idrotinacliente != null && !this.idrotinacliente.equals(other.idrotinacliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Rotinacliente[ idrotinacliente=" + idrotinacliente + " ]";
    }
    
}