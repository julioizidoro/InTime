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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "rotina")
public class Rotina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrotina")
    private Integer idrotina;
    @Size(max = 200)
    @Column(name = "nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rotina")
    private List<Rotinaatividade> rotinaatividadeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rotina")
    private List<Rotinacliente> rotinaclienteList;
    @JoinColumn(name = "subdepartamento_idsubdepartamento", referencedColumnName = "idsubdepartamento")
    @ManyToOne(optional = false)
    private Subdepartamento subdepartamento;

    public Rotina() {
    }

    public Rotina(Integer idrotina) {
        this.idrotina = idrotina;
    }

    public Integer getIdrotina() {
        return idrotina;
    }

    public void setIdrotina(Integer idrotina) {
        this.idrotina = idrotina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Rotinaatividade> getRotinaatividadeList() {
        return rotinaatividadeList;
    }

    public void setRotinaatividadeList(List<Rotinaatividade> rotinaatividadeList) {
        this.rotinaatividadeList = rotinaatividadeList;
    }

    public List<Rotinacliente> getRotinaclienteList() {
        return rotinaclienteList;
    }

    public void setRotinaclienteList(List<Rotinacliente> rotinaclienteList) {
        this.rotinaclienteList = rotinaclienteList;
    }

    public Subdepartamento getSubdepartamento() {
        return subdepartamento;
    }

    public void setSubdepartamento(Subdepartamento subdepartamento) {
        this.subdepartamento = subdepartamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrotina != null ? idrotina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rotina)) {
            return false;
        }
        Rotina other = (Rotina) object;
        if ((this.idrotina == null && other.idrotina != null) || (this.idrotina != null && !this.idrotina.equals(other.idrotina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Rotina[ idrotina=" + idrotina + " ]";
    }
    
}