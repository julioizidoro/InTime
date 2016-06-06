package br.com.financemate.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "raci")
public class Raci implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idraci")
    private Integer idraci;
    @Column(name = "r")
    private Boolean r;
    @Column(name = "a")
    private Boolean a;
    @Column(name = "c")
    private Boolean c;
    @Column(name = "i")
    private Boolean i;
    @JoinColumn(name = "atividademodulo_idatividademodulo", referencedColumnName = "idatividademodulo")
    @ManyToOne(optional = false)
    private Atividademodulo atividademodulo;
    @JoinColumn(name = "membros_idmembros", referencedColumnName = "idmembros")
    @ManyToOne(optional = false)
    private Membros membros;
    

    public Raci() {
    }

    public Raci(Integer idraci) {
        this.idraci = idraci;
    }

    public Integer getIdraci() {
        return idraci;
    }

    public void setIdraci(Integer idraci) {
        this.idraci = idraci;
    }

    public Boolean getR() {
        return r;
    }

    public void setR(Boolean r) {
        this.r = r;
    }

    public Boolean getA() {
        return a;
    }

    public void setA(Boolean a) {
        this.a = a;
    }

    public Boolean getC() {
        return c;
    }

    public void setC(Boolean c) {
        this.c = c;
    }

    public Boolean getI() {
        return i;
    }

    public void setI(Boolean i) {
        this.i = i;
    }

    public Atividademodulo getAtividademodulo() {
        return atividademodulo;
    }

    public void setAtividademodulo(Atividademodulo atividademodulo) {
        this.atividademodulo = atividademodulo;
    }

    public Membros getMembros() {
        return membros;
    }

    public void setMembros(Membros membros) {
        this.membros = membros;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idraci != null ? idraci.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Raci)) {
            return false;
        }
        Raci other = (Raci) object;
        if ((this.idraci == null && other.idraci != null) || (this.idraci != null && !this.idraci.equals(other.idraci))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Raci[ idraci=" + idraci + " ]";
    }
    
}