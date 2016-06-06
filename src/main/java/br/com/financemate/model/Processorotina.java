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
@Table(name = "processorotina")
@NamedQueries({
    @NamedQuery(name = "Processorotina.findAll", query = "SELECT p FROM Processorotina p")})
public class Processorotina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprocessorotina")
    private Integer idprocessorotina;
    @Size(max = 100)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "diasuteis")
    private Integer diasuteis;
    @JoinColumn(name = "processo_idprocesso", referencedColumnName = "idprocesso")
    @ManyToOne(optional = false)
    private Processo processo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "processorotina")
    private List<Processoatividade> processoatividadeList;

    public Processorotina() {
    }

    public Processorotina(Integer idprocessorotina) {
        this.idprocessorotina = idprocessorotina;
    }

    public Integer getIdprocessorotina() {
        return idprocessorotina;
    }

    public void setIdprocessorotina(Integer idprocessorotina) {
        this.idprocessorotina = idprocessorotina;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getDiasuteis() {
        return diasuteis;
    }

    public void setDiasuteis(Integer diasuteis) {
        this.diasuteis = diasuteis;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public List<Processoatividade> getProcessoatividadeList() {
        return processoatividadeList;
    }

    public void setProcessoatividadeList(List<Processoatividade> processoatividadeList) {
        this.processoatividadeList = processoatividadeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprocessorotina != null ? idprocessorotina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Processorotina)) {
            return false;
        }
        Processorotina other = (Processorotina) object;
        if ((this.idprocessorotina == null && other.idprocessorotina != null) || (this.idprocessorotina != null && !this.idprocessorotina.equals(other.idprocessorotina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Processorotina[ idprocessorotina=" + idprocessorotina + " ]";
    }
    
}