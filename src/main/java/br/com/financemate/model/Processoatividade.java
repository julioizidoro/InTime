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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "processoatividade")
@NamedQueries({
    @NamedQuery(name = "Processoatividade.findAll", query = "SELECT p FROM Processoatividade p")})
public class Processoatividade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprocessoatividade")
    private Integer idprocessoatividade;
    @Column(name = "datexecucao")
    @Temporal(TemporalType.DATE)
    private Date datexecucao;
    @Column(name = "dataconclusao")
    @Temporal(TemporalType.DATE)
    private Date dataconclusao;
    @JoinColumn(name = "atividades_idatividades", referencedColumnName = "idatividades")
    @ManyToOne(optional = false)
    private Atividades atividades;
    @JoinColumn(name = "processorotina_idprocessorotina", referencedColumnName = "idprocessorotina")
    @ManyToOne(optional = false)
    private Processorotina processorotina;
    @JoinColumn(name = "processosituacao_idprocessosituacao", referencedColumnName = "idprocessosituacao")
    @ManyToOne(optional = false)
    private Processosituacao processosituacao;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @Transient
    private boolean selecionado;
    @Transient
    private String status;

    public Processoatividade() {
    }

    public Processoatividade(Integer idprocessoatividade) {
        this.idprocessoatividade = idprocessoatividade;
    }

    public Integer getIdprocessoatividade() {
        return idprocessoatividade;
    }

    public void setIdprocessoatividade(Integer idprocessoatividade) {
        this.idprocessoatividade = idprocessoatividade;
    }

    public Date getDatexecucao() {
        return datexecucao;
    }

    public void setDatexecucao(Date datexecucao) {
        this.datexecucao = datexecucao;
    }

    public Date getDataconclusao() {
        return dataconclusao;
    }

    public void setDataconclusao(Date dataconclusao) {
        this.dataconclusao = dataconclusao;
    }

    public Atividades getAtividades() {
        return atividades;
    }

    public void setAtividades(Atividades atividades) {
        this.atividades = atividades;
    }

    public Processorotina getProcessorotina() {
        return processorotina;
    }

    public void setProcessorotina(Processorotina processorotina) {
        this.processorotina = processorotina;
    }

    public Processosituacao getProcessosituacao() {
        return processosituacao;
    }

    public void setProcessosituacao(Processosituacao processosituacao) {
        this.processosituacao = processosituacao;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprocessoatividade != null ? idprocessoatividade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Processoatividade)) {
            return false;
        }
        Processoatividade other = (Processoatividade) object;
        if ((this.idprocessoatividade == null && other.idprocessoatividade != null) || (this.idprocessoatividade != null && !this.idprocessoatividade.equals(other.idprocessoatividade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Processoatividade[ idprocessoatividade=" + idprocessoatividade + " ]";
    }
    
}