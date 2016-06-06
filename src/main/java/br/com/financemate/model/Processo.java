package br.com.financemate.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "processo")
@NamedQueries({
    @NamedQuery(name = "Processo.findAll", query = "SELECT p FROM Processo p")})
public class Processo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprocesso")
    private Integer idprocesso;
    @Size(max = 100)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "processo")
    private List<Processorotina> processorotinaList;
    @JoinColumn(name = "subdepartamento_idsubdepartamento", referencedColumnName = "idsubdepartamento")
    @ManyToOne(optional = false)
    private Subdepartamento subdepartamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "processo")
    private List<Processosituacao> processosituacaoList;

    public Processo() {
    }

    public Processo(Integer idprocesso) {
        this.idprocesso = idprocesso;
    }

    public Integer getIdprocesso() {
        return idprocesso;
    }

    public void setIdprocesso(Integer idprocesso) {
        this.idprocesso = idprocesso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Processorotina> getProcessorotinaList() {
        return processorotinaList;
    }

    public void setProcessorotinaList(List<Processorotina> processorotinaList) {
        this.processorotinaList = processorotinaList;
    }

    public Subdepartamento getSubdepartamento() {
        return subdepartamento;
    }

    public void setSubdepartamento(Subdepartamento subdepartamento) {
        this.subdepartamento = subdepartamento;
    }

    public List<Processosituacao> getProcessosituacaoList() {
        return processosituacaoList;
    }

    public void setProcessosituacaoList(List<Processosituacao> processosituacaoList) {
        this.processosituacaoList = processosituacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprocesso != null ? idprocesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Processo)) {
            return false;
        }
        Processo other = (Processo) object;
        if ((this.idprocesso == null && other.idprocesso != null) || (this.idprocesso != null && !this.idprocesso.equals(other.idprocesso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Processo[ idprocesso=" + idprocesso + " ]";
    }
    
}