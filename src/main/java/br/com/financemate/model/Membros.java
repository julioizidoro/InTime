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

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "membros")
public class Membros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmembros")
    private Integer idmembros;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membros")
    private List<Raci> raciList;
    @JoinColumn(name = "projeto_idprojeto", referencedColumnName = "idprojeto")
    @ManyToOne(optional = false)
    private Projeto projeto;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Membros() {
    }

    public Membros(Integer idmembros) {
        this.idmembros = idmembros;
    }

    public Integer getIdmembros() {
        return idmembros;
    }

    public void setIdmembros(Integer idmembros) {
        this.idmembros = idmembros;
    }

    public List<Raci> getRaciList() {
        return raciList;
    }

    public void setRaciList(List<Raci> raciList) {
        this.raciList = raciList;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmembros != null ? idmembros.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membros)) {
            return false;
        }
        Membros other = (Membros) object;
        if ((this.idmembros == null && other.idmembros != null) || (this.idmembros != null && !this.idmembros.equals(other.idmembros))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Membros[ idmembros=" + idmembros + " ]";
    }
    
}